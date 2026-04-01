package com.samsung.android.gallery.module.cache;

import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MemoryCacheMgr<Object, ObjectType> {
    private final LruCacheEvict<Object, ObjectType> mCache;
    private AbsCacheMgr$EvictListener<Object, ObjectType> mEvictListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LruCacheEvict<Object, ObjectType> extends LruCache<Object, ObjectType> {
        AbsCacheMgr$EvictListener mListener;

        public LruCacheEvict(int i2, AbsCacheMgr$EvictListener absCacheMgr$EvictListener) {
            super(i2);
            this.mListener = absCacheMgr$EvictListener;
        }

        public void entryRemoved(boolean z, Object object, ObjectType objecttype, ObjectType objecttype2) {
            AbsCacheMgr$EvictListener absCacheMgr$EvictListener;
            super.entryRemoved(z, object, objecttype, objecttype2);
            if (z && (absCacheMgr$EvictListener = this.mListener) != null) {
                absCacheMgr$EvictListener.OnEvicted(object, objecttype);
            }
        }

        public void setListener(AbsCacheMgr$EvictListener<Object, ObjectType> absCacheMgr$EvictListener) {
            this.mListener = absCacheMgr$EvictListener;
        }
    }

    public MemoryCacheMgr(int i2, AbsCacheMgr$EvictListener<Object, ObjectType> absCacheMgr$EvictListener) {
        this.mEvictListener = absCacheMgr$EvictListener;
        this.mCache = new LruCacheEvict<>(i2, absCacheMgr$EvictListener);
    }

    public void addCache(Object object, ObjectType objecttype) {
        this.mCache.put(object, objecttype);
    }

    public void clearCache() {
        this.mCache.evictAll();
    }

    public ObjectType getCache(Object object) {
        return this.mCache.get(object);
    }

    public int getSize() {
        return this.mCache.size();
    }

    public ObjectType removeCache(Object object) {
        return this.mCache.remove(object);
    }

    public void resizeCache(int i2) {
        this.mCache.resize(i2);
    }

    public void setListener(AbsCacheMgr$EvictListener<Object, ObjectType> absCacheMgr$EvictListener) {
        this.mEvictListener = absCacheMgr$EvictListener;
        this.mCache.setListener(absCacheMgr$EvictListener);
    }

    public Map<Object, ObjectType> snapshot() {
        return this.mCache.snapshot();
    }

    public synchronized String toString() {
        StringBuffer stringBuffer;
        try {
            Set<Map.Entry<K, V>> entrySet = this.mCache.snapshot().entrySet();
            stringBuffer = new StringBuffer("L[" + entrySet.size() + "] Key={");
            Iterator<Map.Entry<K, V>> it = entrySet.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                stringBuffer.append(it.next().getKey());
                stringBuffer.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                i2++;
                if (i2 > 160) {
                    break;
                }
            }
            stringBuffer.append("}");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return stringBuffer.toString();
    }

    public void trimCache(int i2) {
        this.mCache.trimToSize(i2);
    }

    public void clearCache(boolean z) {
        this.mCache.evictAll(z);
    }
}
