package com.nostra13.universalimageloader.cache.disc;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class LimitedDiscCache extends BaseDiscCache {
    private int cacheSize;
    private final Map<File, Long> lastUsageDates;
    private int sizeLimit;

    /* access modifiers changed from: protected */
    public abstract int getSize(File file);

    public LimitedDiscCache(File cacheDir, int sizeLimit2) {
        this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator(), sizeLimit2);
    }

    public LimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int sizeLimit2) {
        super(cacheDir, fileNameGenerator);
        this.cacheSize = 0;
        this.lastUsageDates = Collections.synchronizedMap(new HashMap());
        this.sizeLimit = sizeLimit2;
        calculateCacheSizeAndFillUsageMap();
    }

    private void calculateCacheSizeAndFillUsageMap() {
        File[] arr$;
        int size = 0;
        for (File cachedFile : this.cacheDir.listFiles()) {
            size += getSize(cachedFile);
            this.lastUsageDates.put(cachedFile, Long.valueOf(cachedFile.lastModified()));
        }
        this.cacheSize = size;
    }

    public void put(String key, File file) {
        int valueSize = getSize(file);
        while (this.cacheSize + valueSize > this.sizeLimit) {
            int freedSize = removeNext();
            if (freedSize == 0) {
                break;
            }
            this.cacheSize -= freedSize;
        }
        this.cacheSize += valueSize;
        Long currentTime = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(currentTime.longValue());
        this.lastUsageDates.put(file, currentTime);
    }

    public File get(String key) {
        File file = super.get(key);
        Long currentTime = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(currentTime.longValue());
        this.lastUsageDates.put(file, currentTime);
        return file;
    }

    public void clear() {
        this.lastUsageDates.clear();
        this.cacheSize = 0;
        super.clear();
    }

    private int removeNext() {
        if (this.lastUsageDates.isEmpty()) {
            return 0;
        }
        Long oldestUsage = null;
        File mostLongUsedFile = null;
        Set<Entry<File, Long>> entries = this.lastUsageDates.entrySet();
        synchronized (this.lastUsageDates) {
            for (Entry<File, Long> entry : entries) {
                if (mostLongUsedFile == null) {
                    mostLongUsedFile = (File) entry.getKey();
                    oldestUsage = (Long) entry.getValue();
                } else {
                    Long lastValueUsage = (Long) entry.getValue();
                    if (lastValueUsage.longValue() < oldestUsage.longValue()) {
                        oldestUsage = lastValueUsage;
                        mostLongUsedFile = (File) entry.getKey();
                    }
                }
            }
        }
        int size = getSize(mostLongUsedFile);
        if (!mostLongUsedFile.delete()) {
            return size;
        }
        this.lastUsageDates.remove(mostLongUsedFile);
        return size;
    }
}
