package com.samsung.android.gallery.module.map.clustering;

import com.samsung.android.gallery.support.utils.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReadWriteLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ClusterTask {
    private WeakReference<ReadWriteLock> mAlgorithmLockRef;
    private WeakReference<IAlgorithm<IClusterItem>> mAlgorithmRef;
    private WeakReference<IClusteringListener<IClusterItem>> mClusteringListener;
    FutureTask<Set<ICluster<IClusterItem>>> mFutureTask;
    double mZoom;
    Callable<Set<ICluster<IClusterItem>>> task = new a(this);

    public ClusterTask(IAlgorithm<IClusterItem> iAlgorithm, ReadWriteLock readWriteLock, IClusteringListener<IClusterItem> iClusteringListener) {
        this.mAlgorithmRef = new WeakReference<>(iAlgorithm);
        this.mClusteringListener = new WeakReference<>(iClusteringListener);
        this.mAlgorithmLockRef = new WeakReference<>(readWriteLock);
        this.mFutureTask = new FutureTask<>(this.task);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Set lambda$new$0() {
        ReadWriteLock readWriteLock = this.mAlgorithmLockRef.get();
        IAlgorithm iAlgorithm = this.mAlgorithmRef.get();
        if (!(readWriteLock == null || iAlgorithm == null)) {
            readWriteLock.readLock().lock();
            try {
                return iAlgorithm.getClusters(this.mZoom);
            } catch (IllegalStateException unused) {
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
        return null;
    }

    public void cancel() {
        this.mFutureTask.cancel(true);
        if (this.mAlgorithmRef.get() != null) {
            this.mAlgorithmRef.get().interrupt();
        }
    }

    public ArrayList<double[]> getLargestClusteredLocations(Double d) {
        ReadWriteLock readWriteLock = this.mAlgorithmLockRef.get();
        IAlgorithm iAlgorithm = this.mAlgorithmRef.get();
        ICluster iCluster = null;
        if (iAlgorithm == null) {
            return null;
        }
        readWriteLock.readLock().lock();
        try {
            try {
                int i2 = 0;
                for (ICluster iCluster2 : iAlgorithm.getClusters(d.doubleValue())) {
                    if (i2 < iCluster2.getItems().size()) {
                        i2 = iCluster2.getItems().size();
                        iCluster = iCluster2;
                    }
                }
                ArrayList<double[]> arrayList = new ArrayList<>();
                if (iCluster != null) {
                    for (IClusterItem position : iCluster.getItems()) {
                        arrayList.add(position.getPosition());
                    }
                }
                readWriteLock.readLock().unlock();
                return arrayList;
            } catch (Throwable th) {
                readWriteLock.readLock().unlock();
                throw th;
            }
        } catch (IllegalStateException unused) {
            readWriteLock.readLock().unlock();
            return null;
        }
    }

    public void start(double d) {
        Set set;
        this.mZoom = d;
        try {
            new Thread(this.mFutureTask).start();
            set = this.mFutureTask.get();
        } catch (InterruptedException | CancellationException unused) {
            set = null;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        IClusteringListener iClusteringListener = this.mClusteringListener.get();
        if (iClusteringListener != null) {
            iClusteringListener.onClustersChanged(set);
            if (set == null) {
                Log.e("ClusterTask", "Cluster is null");
                return;
            }
            Log.i("ClusterTask", "Cluster size = " + set.size());
        }
    }
}
