package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.C0203a;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.aj */
public class C0066aj extends ImageView implements ConnectionCallbacks, C0203a {
    /* access modifiers changed from: private */
    public static final String TAG = C0066aj.class.getSimpleName();

    /* renamed from: bg */
    private int f22bg;
    /* access modifiers changed from: private */

    /* renamed from: bh */
    public boolean f23bh;

    /* renamed from: bi */
    private boolean f24bi;
    /* access modifiers changed from: private */

    /* renamed from: bj */
    public Bitmap f25bj;

    /* renamed from: bk */
    private PlusClient f26bk;
    private Uri mUri;

    /* renamed from: com.google.android.gms.internal.aj$a */
    private class C0067a extends AsyncTask<ParcelFileDescriptor, Void, Bitmap> {

        /* renamed from: bg */
        private final int f27bg;

        C0067a(int i) {
            this.f27bg = i;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(ParcelFileDescriptor... parcelFileDescriptorArr) {
            ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArr[0];
            try {
                Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                if (this.f27bg > 0) {
                    decodeFileDescriptor = C0066aj.m70a(decodeFileDescriptor, this.f27bg);
                    try {
                    } catch (IOException e) {
                        Log.e(C0066aj.TAG, "closed failed", e);
                    }
                } else {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException e2) {
                        Log.e(C0066aj.TAG, "closed failed", e2);
                    }
                }
                return decodeFileDescriptor;
            } finally {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException e3) {
                    Log.e(C0066aj.TAG, "closed failed", e3);
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onPostExecute(Bitmap bitmap) {
            C0066aj.this.f25bj = bitmap;
            if (C0066aj.this.f23bh) {
                C0066aj.this.setImageBitmap(C0066aj.this.f25bj);
            }
        }
    }

    public C0066aj(Context context) {
        super(context);
    }

    public C0066aj(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C0066aj(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: D */
    private void m68D() {
        boolean z = this.mUri != null && "android.resource".equals(this.mUri.getScheme());
        if (this.f24bi) {
            if (this.mUri == null) {
                setImageBitmap(null);
            } else if (z || (this.f26bk != null && this.f26bk.isConnected())) {
                if (z) {
                    setImageURI(this.mUri);
                } else {
                    this.f26bk.mo1931a(this, this.mUri, this.f22bg);
                }
                this.f24bi = false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public static Bitmap m70a(Bitmap bitmap, int i) {
        double width = (double) bitmap.getWidth();
        double height = (double) bitmap.getHeight();
        double d = width > height ? ((double) i) / width : ((double) i) / height;
        return Bitmap.createScaledBitmap(bitmap, (int) ((width * d) + 0.5d), (int) ((d * height) + 0.5d), true);
    }

    /* renamed from: a */
    public void mo1229a(Uri uri, int i) {
        boolean z = false;
        boolean equals = this.mUri == null ? uri == null : this.mUri.equals(uri);
        if (this.f22bg == i) {
            z = true;
        }
        if (!equals || !z) {
            this.mUri = uri;
            this.f22bg = i;
            this.f24bi = true;
            m68D();
        }
    }

    /* renamed from: a */
    public void mo1230a(ConnectionResult connectionResult, ParcelFileDescriptor parcelFileDescriptor) {
        if (connectionResult.isSuccess()) {
            this.f24bi = false;
            if (parcelFileDescriptor != null) {
                new C0067a(this.f22bg).execute(new ParcelFileDescriptor[]{parcelFileDescriptor});
            }
        }
    }

    /* renamed from: a */
    public void mo1231a(PlusClient plusClient) {
        if (plusClient != this.f26bk) {
            if (this.f26bk != null && this.f26bk.isConnectionCallbacksRegistered(this)) {
                this.f26bk.unregisterConnectionCallbacks(this);
            }
            this.f26bk = plusClient;
            this.f26bk.registerConnectionCallbacks(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f23bh = true;
        if (this.f26bk != null && !this.f26bk.isConnectionCallbacksRegistered(this)) {
            this.f26bk.registerConnectionCallbacks(this);
        }
        if (this.f25bj != null) {
            setImageBitmap(this.f25bj);
        }
    }

    public void onConnected() {
        m68D();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f23bh = false;
        if (this.f26bk != null && this.f26bk.isConnectionCallbacksRegistered(this)) {
            this.f26bk.unregisterConnectionCallbacks(this);
        }
    }

    public void onDisconnected() {
    }
}
