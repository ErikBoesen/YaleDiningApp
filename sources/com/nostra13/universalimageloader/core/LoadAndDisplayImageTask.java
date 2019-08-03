package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.C0214L;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask implements Runnable {
    private static final int ATTEMPT_COUNT_TO_DECODE_BITMAP = 3;
    private static final int BUFFER_SIZE = 8192;
    private final ImageLoaderConfiguration configuration;
    private final ImageDownloader downloader = this.configuration.downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageView imageView;
    final ImageLoadingListener listener;
    private final boolean loggingEnabled = this.configuration.loggingEnabled;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader = this.configuration.networkDeniedDownloader;
    final DisplayImageOptions options;
    private final ImageSize targetSize;
    final String uri;

    public LoadAndDisplayImageTask(ImageLoaderEngine engine2, ImageLoadingInfo imageLoadingInfo2, Handler handler2) {
        this.engine = engine2;
        this.imageLoadingInfo = imageLoadingInfo2;
        this.handler = handler2;
        this.configuration = engine2.configuration;
        this.uri = imageLoadingInfo2.uri;
        this.memoryCacheKey = imageLoadingInfo2.memoryCacheKey;
        this.imageView = imageLoadingInfo2.imageView;
        this.targetSize = imageLoadingInfo2.targetSize;
        this.options = imageLoadingInfo2.options;
        this.listener = imageLoadingInfo2.listener;
    }

    public void run() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (pause) {
                log("ImageLoader is paused. Waiting...  [%s]", this.memoryCacheKey);
                try {
                    pause.wait();
                    log(".. Resume loading [%s]", this.memoryCacheKey);
                } catch (InterruptedException e) {
                    C0214L.m411e("Task was interrupted [%s]", this.memoryCacheKey);
                    return;
                }
            }
        }
        if (!checkTaskIsNotActual()) {
            if (this.options.shouldDelayBeforeLoading()) {
                log("Delay %d ms before loading...  [%s]", Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey);
                try {
                    Thread.sleep((long) this.options.getDelayBeforeLoading());
                    if (checkTaskIsNotActual()) {
                        return;
                    }
                } catch (InterruptedException e2) {
                    C0214L.m411e("Task was interrupted [%s]", this.memoryCacheKey);
                    return;
                }
            }
            ReentrantLock loadFromUriLock = this.imageLoadingInfo.loadFromUriLock;
            log("Start display image task [%s]", this.memoryCacheKey);
            if (loadFromUriLock.isLocked()) {
                log("Image already is loading. Waiting... [%s]", this.memoryCacheKey);
            }
            loadFromUriLock.lock();
            try {
                if (!checkTaskIsNotActual()) {
                    Bitmap bmp = (Bitmap) this.configuration.memoryCache.get(this.memoryCacheKey);
                    if (bmp == null) {
                        bmp = tryLoadBitmap();
                        if (bmp == null) {
                            loadFromUriLock.unlock();
                            return;
                        } else if (checkTaskIsNotActual() || checkTaskIsInterrupted()) {
                            loadFromUriLock.unlock();
                            return;
                        } else {
                            if (this.options.shouldPreProcess()) {
                                log("PreProcess image before caching in memory [%s]", this.memoryCacheKey);
                                bmp = this.options.getPreProcessor().process(bmp);
                            }
                            if (this.options.isCacheInMemory()) {
                                log("Cache image in memory [%s]", this.memoryCacheKey);
                                this.configuration.memoryCache.put(this.memoryCacheKey, bmp);
                            }
                        }
                    } else {
                        log("...Get cached bitmap from memory after waiting. [%s]", this.memoryCacheKey);
                    }
                    if (this.options.shouldPostProcess()) {
                        log("PostProcess image before displaying [%s]", this.memoryCacheKey);
                        bmp = this.options.getPostProcessor().process(bmp);
                    }
                    loadFromUriLock.unlock();
                    if (!checkTaskIsNotActual() && !checkTaskIsInterrupted()) {
                        DisplayBitmapTask displayBitmapTask = new DisplayBitmapTask(bmp, this.imageLoadingInfo, this.engine);
                        displayBitmapTask.setLoggingEnabled(this.loggingEnabled);
                        this.handler.post(displayBitmapTask);
                    }
                }
            } finally {
                loadFromUriLock.unlock();
            }
        }
    }

    private boolean checkTaskIsNotActual() {
        boolean imageViewWasReused;
        if (!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageView))) {
            imageViewWasReused = true;
        } else {
            imageViewWasReused = false;
        }
        if (imageViewWasReused) {
            this.handler.post(new Runnable() {
                public void run() {
                    LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageView);
                }
            });
        }
        if (imageViewWasReused) {
            log("ImageView is reused for another image. Task is cancelled. [%s]", this.memoryCacheKey);
        }
        return imageViewWasReused;
    }

    private boolean checkTaskIsInterrupted() {
        boolean interrupted = Thread.interrupted();
        if (interrupted) {
            log("Task was interrupted [%s]", this.memoryCacheKey);
        }
        return interrupted;
    }

    private Bitmap tryLoadBitmap() {
        URI imageUriForDecoding;
        DiscCacheAware discCache = this.configuration.discCache;
        File imageFile = discCache.get(this.uri);
        Bitmap bitmap = null;
        try {
            if (imageFile.exists()) {
                log("Load image from disc cache [%s]", this.memoryCacheKey);
                Bitmap b = decodeImage(imageFile.toURI());
                if (b != null) {
                    return b;
                }
            }
            log("Load image from Internet [%s]", this.memoryCacheKey);
            if (this.options.isCacheOnDisc()) {
                log("Cache image on disc [%s]", this.memoryCacheKey);
                saveImageOnDisc(imageFile);
                discCache.put(this.uri, imageFile);
                imageUriForDecoding = imageFile.toURI();
            } else {
                imageUriForDecoding = new URI(this.uri);
            }
            bitmap = decodeImage(imageUriForDecoding);
            if (bitmap == null) {
                fireImageLoadingFailedEvent(FailReason.IO_ERROR);
            }
        } catch (IllegalStateException e) {
            fireImageLoadingFailedEvent(FailReason.NETWORK_DENIED);
        } catch (UnsupportedOperationException e2) {
            C0214L.m412e(e2);
            fireImageLoadingFailedEvent(FailReason.UNSUPPORTED_URI_SCHEME);
        } catch (IOException e3) {
            C0214L.m412e(e3);
            fireImageLoadingFailedEvent(FailReason.IO_ERROR);
            if (imageFile.exists()) {
                imageFile.delete();
            }
        } catch (OutOfMemoryError e4) {
            C0214L.m412e(e4);
            fireImageLoadingFailedEvent(FailReason.OUT_OF_MEMORY);
        } catch (Throwable e5) {
            C0214L.m412e(e5);
            fireImageLoadingFailedEvent(FailReason.UNKNOWN);
        }
        return bitmap;
    }

    private Bitmap decodeImage(URI imageUri) throws IOException {
        if (this.configuration.handleOutOfMemory) {
            return decodeWithOOMHandling(imageUri);
        }
        ImageDecoder decoder = new ImageDecoder(imageUri, getDownloader(), this.options);
        decoder.setLoggingEnabled(this.loggingEnabled);
        return decoder.decode(this.targetSize, this.options.getImageScaleType(), ViewScaleType.fromImageView(this.imageView));
    }

    private Bitmap decodeWithOOMHandling(URI imageUri) throws IOException {
        Bitmap result = null;
        ImageDecoder decoder = new ImageDecoder(imageUri, getDownloader(), this.options);
        decoder.setLoggingEnabled(this.loggingEnabled);
        int attempt = 1;
        while (attempt <= 3) {
            try {
                result = decoder.decode(this.targetSize, this.options.getImageScaleType(), ViewScaleType.fromImageView(this.imageView));
                return result;
            } catch (OutOfMemoryError e) {
                C0214L.m412e(e);
                switch (attempt) {
                    case 1:
                        System.gc();
                        break;
                    case 2:
                        this.configuration.memoryCache.clear();
                        System.gc();
                        break;
                    case 3:
                        throw e;
                }
                SystemClock.sleep((long) (attempt * 1000));
                attempt++;
            }
        }
        return result;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0091, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0095, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveImageOnDisc(java.io.File r14) throws java.io.IOException, java.net.URISyntaxException {
        /*
            r13 = this;
            r12 = 8192(0x2000, float:1.14794E-41)
            java.io.File r1 = r14.getParentFile()
            boolean r9 = r1.exists()
            if (r9 != 0) goto L_0x000f
            r1.mkdirs()
        L_0x000f:
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r9 = r13.configuration
            int r8 = r9.maxImageWidthForDiscCache
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r9 = r13.configuration
            int r4 = r9.maxImageHeightForDiscCache
            if (r8 > 0) goto L_0x001b
            if (r4 <= 0) goto L_0x0066
        L_0x001b:
            com.nostra13.universalimageloader.core.assist.ImageSize r7 = new com.nostra13.universalimageloader.core.assist.ImageSize
            r7.<init>(r8, r4)
            com.nostra13.universalimageloader.core.ImageDecoder r3 = new com.nostra13.universalimageloader.core.ImageDecoder
            java.net.URI r9 = new java.net.URI
            java.lang.String r10 = r13.uri
            r9.<init>(r10)
            com.nostra13.universalimageloader.core.download.ImageDownloader r10 = r13.getDownloader()
            com.nostra13.universalimageloader.core.DisplayImageOptions r11 = r13.options
            r3.<init>(r9, r10, r11)
            boolean r9 = r13.loggingEnabled
            r3.setLoggingEnabled(r9)
            com.nostra13.universalimageloader.core.assist.ImageScaleType r9 = com.nostra13.universalimageloader.core.assist.ImageScaleType.IN_SAMPLE_INT
            com.nostra13.universalimageloader.core.assist.ViewScaleType r10 = com.nostra13.universalimageloader.core.assist.ViewScaleType.FIT_INSIDE
            android.graphics.Bitmap r0 = r3.decode(r7, r9, r10)
            if (r0 == 0) goto L_0x0066
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream
            java.io.FileOutputStream r9 = new java.io.FileOutputStream
            r9.<init>(r14)
            r6.<init>(r9, r12)
            r2 = 0
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r9 = r13.configuration     // Catch:{ all -> 0x0061 }
            android.graphics.Bitmap$CompressFormat r9 = r9.imageCompressFormatForDiscCache     // Catch:{ all -> 0x0061 }
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r10 = r13.configuration     // Catch:{ all -> 0x0061 }
            int r10 = r10.imageQualityForDiscCache     // Catch:{ all -> 0x0061 }
            boolean r2 = r0.compress(r9, r10, r6)     // Catch:{ all -> 0x0061 }
            com.nostra13.universalimageloader.utils.IoUtils.closeSilently(r6)
            if (r2 == 0) goto L_0x0066
            r0.recycle()
        L_0x0060:
            return
        L_0x0061:
            r9 = move-exception
            com.nostra13.universalimageloader.utils.IoUtils.closeSilently(r6)
            throw r9
        L_0x0066:
            com.nostra13.universalimageloader.core.download.ImageDownloader r9 = r13.getDownloader()
            java.net.URI r10 = new java.net.URI
            java.lang.String r11 = r13.uri
            r10.<init>(r11)
            com.nostra13.universalimageloader.core.DisplayImageOptions r11 = r13.options
            java.lang.Object r11 = r11.getExtraForDownloader()
            java.io.InputStream r5 = r9.getStream(r10, r11)
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0096 }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ all -> 0x0096 }
            r9.<init>(r14)     // Catch:{ all -> 0x0096 }
            r10 = 8192(0x2000, float:1.14794E-41)
            r6.<init>(r9, r10)     // Catch:{ all -> 0x0096 }
            com.nostra13.universalimageloader.utils.IoUtils.copyStream(r5, r6)     // Catch:{ all -> 0x0091 }
            com.nostra13.universalimageloader.utils.IoUtils.closeSilently(r6)     // Catch:{ all -> 0x0096 }
            com.nostra13.universalimageloader.utils.IoUtils.closeSilently(r5)
            goto L_0x0060
        L_0x0091:
            r9 = move-exception
            com.nostra13.universalimageloader.utils.IoUtils.closeSilently(r6)     // Catch:{ all -> 0x0096 }
            throw r9     // Catch:{ all -> 0x0096 }
        L_0x0096:
            r9 = move-exception
            com.nostra13.universalimageloader.utils.IoUtils.closeSilently(r5)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.saveImageOnDisc(java.io.File):void");
    }

    private void fireImageLoadingFailedEvent(final FailReason failReason) {
        if (!Thread.interrupted()) {
            this.handler.post(new Runnable() {
                public void run() {
                    if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail()) {
                        LoadAndDisplayImageTask.this.imageView.setImageResource(LoadAndDisplayImageTask.this.options.getImageOnFail());
                    }
                    LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageView, failReason);
                }
            });
        }
    }

    private ImageDownloader getDownloader() {
        return this.engine.isNetworkDenied() ? this.networkDeniedDownloader : this.downloader;
    }

    /* access modifiers changed from: 0000 */
    public String getLoadingUri() {
        return this.uri;
    }

    private void log(String message, Object... args) {
        if (this.loggingEnabled) {
            C0214L.m414i(message, args);
        }
    }
}
