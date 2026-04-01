package com.samsung.android.gallery.module.map.clustering;

import U3.a;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapViewV2;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterManager {
    private final IAlgorithm<IClusterItem> mAlgorithm;
    private final ReadWriteLock mAlgorithmLock;
    private ClusterTask mClusterTask;
    private final ReadWriteLock mClusterTaskLock;
    private IClusterZoomListener mClusterZoomListener;
    private final IClusteringListener<IClusterItem> mClusteringListener;
    private double mZoom;

    public ClusterManager(IClusteringListener iClusteringListener, IProjection iProjection) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.mAlgorithmLock = reentrantReadWriteLock;
        this.mClusterTaskLock = new ReentrantReadWriteLock();
        this.mZoom = -1.0d;
        PreCachingClusters preCachingClusters = new PreCachingClusters(new QuadTreeClusterAlgorithm(iProjection));
        this.mAlgorithm = preCachingClusters;
        this.mClusteringListener = iClusteringListener;
        this.mClusterTask = new ClusterTask(preCachingClusters, reentrantReadWriteLock, iClusteringListener);
    }

    private void setZoom(double d) {
        IClusterZoomListener iClusterZoomListener;
        if (!(this.mZoom == d || (iClusterZoomListener = this.mClusterZoomListener) == null)) {
            ((IClusteringMapViewV2) ((a) iClusterZoomListener).e).onClusterZoomChanged();
        }
        this.mZoom = d;
    }

    public void addItem(IClusterItem iClusterItem) {
        this.mAlgorithmLock.writeLock().lock();
        try {
            this.mAlgorithm.addItem(iClusterItem);
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void clearItems() {
        this.mAlgorithmLock.writeLock().lock();
        try {
            this.mAlgorithm.clearItems();
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void cluster(double d) {
        setZoom(d);
        this.mClusterTaskLock.writeLock().lock();
        try {
            this.mClusterTask.cancel();
            ClusterTask clusterTask = new ClusterTask(this.mAlgorithm, this.mAlgorithmLock, this.mClusteringListener);
            this.mClusterTask = clusterTask;
            clusterTask.start(d);
        } finally {
            this.mClusterTaskLock.writeLock().unlock();
        }
    }

    public ArrayList<double[]> getLargestClusteredLocations(double d) {
        ClusterTask clusterTask = this.mClusterTask;
        if (clusterTask != null) {
            return clusterTask.getLargestClusteredLocations(Double.valueOf(d));
        }
        return new ArrayList<>();
    }

    public boolean isFirstTry() {
        if (this.mZoom == -1.0d) {
            return true;
        }
        return false;
    }

    public boolean isZoomLevelChanged(double d) {
        if (this.mZoom != d) {
            return true;
        }
        return false;
    }

    public ClusterManager(IClusteringListener iClusteringListener, IProjection iProjection, IClusterZoomListener iClusterZoomListener) {
        this(iClusteringListener, iProjection);
        this.mClusterZoomListener = iClusterZoomListener;
    }
}
