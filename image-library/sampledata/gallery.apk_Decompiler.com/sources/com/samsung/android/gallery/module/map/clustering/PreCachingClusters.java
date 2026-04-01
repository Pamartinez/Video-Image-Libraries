package com.samsung.android.gallery.module.map.clustering;

import com.samsung.android.gallery.module.map.clustering.IClusterItem;
import com.samsung.android.gallery.support.cache.LruCache;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreCachingClusters<T extends IClusterItem> implements IAlgorithm<T> {
    private final LruCache<Double, Set<ICluster<T>>> mCache = new LruCache<>(5);
    private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();
    private final IAlgorithm<T> mRealClustering;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreCacheRunnable<T extends IClusterItem> implements Runnable {
        private final WeakReference<PreCachingClusters<T>> mCachingManager;
        private final double mZoom;

        public PreCacheRunnable(PreCachingClusters<T> preCachingClusters, double d) {
            this.mCachingManager = new WeakReference<>(preCachingClusters);
            this.mZoom = d;
        }

        public void run() {
            try {
                Thread.sleep((long) ((Math.random() * 500.0d) + 500.0d));
            } catch (InterruptedException unused) {
            }
            PreCachingClusters preCachingClusters = this.mCachingManager.get();
            if (preCachingClusters != null) {
                try {
                    preCachingClusters.getClustersInternal(this.mZoom);
                } catch (IllegalStateException unused2) {
                }
            }
        }
    }

    public PreCachingClusters(IAlgorithm<T> iAlgorithm) {
        this.mRealClustering = iAlgorithm;
    }

    private void clearCache() {
        this.mCacheLock.writeLock().lock();
        try {
            this.mCache.evictAll();
        } finally {
            this.mCacheLock.writeLock().unlock();
        }
    }

    /* access modifiers changed from: private */
    public Set<ICluster<T>> getClustersInternal(double d) {
        this.mCacheLock.readLock().lock();
        try {
            Set<ICluster<T>> set = this.mCache.get(Double.valueOf(d));
            if (set != null) {
                return set;
            }
            this.mCacheLock.writeLock().lock();
            try {
                Set<ICluster<T>> clusters = this.mRealClustering.getClusters(d);
                this.mCache.put(Double.valueOf(d), clusters);
                return clusters;
            } finally {
                this.mCacheLock.writeLock().unlock();
            }
        } finally {
            this.mCacheLock.readLock().unlock();
        }
    }

    public void addItem(T t) {
        this.mRealClustering.addItem(t);
        clearCache();
    }

    public void clearItems() {
        this.mRealClustering.clearItems();
        clearCache();
    }

    public Set<ICluster<T>> getClusters(double d) {
        Set<ICluster<T>> clustersInternal = getClustersInternal(d);
        double d2 = d + 0.05000000074505806d;
        if (this.mCache.get(Double.valueOf(d2)) == null) {
            new Thread(new PreCacheRunnable(this, d2)).start();
        }
        double d3 = d - 0.05000000074505806d;
        if (this.mCache.get(Double.valueOf(d3)) == null) {
            new Thread(new PreCacheRunnable(this, d3)).start();
        }
        return clustersInternal;
    }

    public void interrupt() {
        this.mRealClustering.interrupt();
    }
}
