package com.nostra13.universalimageloader.core.assist;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MemoryCacheUtil {
    private static final String MEMORY_CACHE_KEY_FORMAT = "%1$s_%2$dx%3$d";
    private static final String URI_AND_SIZE_SEPARATOR = "_";

    private MemoryCacheUtil() {
    }

    public static String generateKey(String imageUri, ImageSize targetSize) {
        return String.format(MEMORY_CACHE_KEY_FORMAT, new Object[]{imageUri, Integer.valueOf(targetSize.getWidth()), Integer.valueOf(targetSize.getHeight())});
    }

    public static Comparator<String> createFuzzyKeyComparator() {
        return new Comparator<String>() {
            public int compare(String key1, String key2) {
                return key1.substring(0, key1.lastIndexOf(MemoryCacheUtil.URI_AND_SIZE_SEPARATOR)).compareTo(key2.substring(0, key2.lastIndexOf(MemoryCacheUtil.URI_AND_SIZE_SEPARATOR)));
            }
        };
    }

    public static List<Bitmap> findCachedBitmapsForImageUri(String imageUri, MemoryCacheAware<String, Bitmap> memoryCache) {
        List<Bitmap> values = new ArrayList<>();
        for (String key : memoryCache.keys()) {
            if (key.startsWith(imageUri)) {
                values.add(memoryCache.get(key));
            }
        }
        return values;
    }

    public static List<String> findCacheKeysForImageUri(String imageUri, MemoryCacheAware<String, Bitmap> memoryCache) {
        List<String> values = new ArrayList<>();
        for (String key : memoryCache.keys()) {
            if (key.startsWith(imageUri)) {
                values.add(key);
            }
        }
        return values;
    }

    public static void removeFromCache(String imageUri, MemoryCacheAware<String, Bitmap> memoryCache) {
        List<String> keysToRemove = new ArrayList<>();
        for (String key : memoryCache.keys()) {
            if (key.startsWith(imageUri)) {
                keysToRemove.add(key);
            }
        }
        for (String keyToRemove : keysToRemove) {
            memoryCache.remove(keyToRemove);
        }
    }
}
