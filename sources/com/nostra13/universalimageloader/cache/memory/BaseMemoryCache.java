package com.nostra13.universalimageloader.cache.memory;

import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseMemoryCache<K, V> implements MemoryCacheAware<K, V> {
    private final Map<K, Reference<V>> softMap = Collections.synchronizedMap(new HashMap());

    /* access modifiers changed from: protected */
    public abstract Reference<V> createReference(V v);

    public V get(K key) {
        Reference<V> reference = (Reference) this.softMap.get(key);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public boolean put(K key, V value) {
        this.softMap.put(key, createReference(value));
        return true;
    }

    public void remove(K key) {
        this.softMap.remove(key);
    }

    public Collection<K> keys() {
        return this.softMap.keySet();
    }

    public void clear() {
        this.softMap.clear();
    }
}
