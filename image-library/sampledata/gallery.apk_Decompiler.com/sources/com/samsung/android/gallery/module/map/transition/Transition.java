package com.samsung.android.gallery.module.map.transition;

import android.os.AsyncTask;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.clustering.MercatorProjection;
import com.samsung.android.gallery.module.map.clustering.Point;
import com.samsung.android.gallery.module.map.clustering.QuadTreeClusterAlgorithm;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerCollection;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.support.utils.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Transition extends AsyncTask<Void, Void, MarkerCollection> {
    private Runnable mCallback;
    private final boolean mClear;
    private final boolean mClusterChanged;
    private MarkerCollection mMarkerCollection;
    private WeakReference<BaseMarkerManager> mMarkerManagerRef;
    private MercatorProjection mMercatorProjection;
    private WeakReference<Set<ICluster<MapItem>>> mNewClustersRef;
    private TransitionQueueScheduler mScheduler;
    private final boolean mZoomIn;

    public Transition(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set, boolean z) {
        this.mClusterChanged = true;
        this.mZoomIn = z;
        this.mClear = false;
        init(baseMarkerManager, set);
    }

    private void createMarkers(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        List<Point> pointsFromClusters = getPointsFromClusters(baseMarkerManager, baseMarkerManager.getCurrentMarkerCollection().getAllClusters());
        for (ICluster next : set) {
            if (baseMarkerManager.isVisible(next.getPosition())) {
                double[] dArr = null;
                if (this.mZoomIn) {
                    double[] findIncludedClusterSet = findIncludedClusterSet(baseMarkerManager.getCurrentMarkerCollection().getAllClusters(), next);
                    if (findIncludedClusterSet == null) {
                        Point findClosestCluster = findClosestCluster(pointsFromClusters, this.mMercatorProjection.toPoint(next.getPosition()));
                        if (findClosestCluster != null) {
                            dArr = this.mMercatorProjection.toLatLng(findClosestCluster);
                        }
                        findIncludedClusterSet = dArr;
                    }
                    this.mScheduler.addTask(new AddMarkerTask(findIncludedClusterSet, next, this.mMarkerCollection, baseMarkerManager));
                } else {
                    this.mScheduler.addTask(new AddMarkerTask((double[]) null, next, this.mMarkerCollection, baseMarkerManager));
                }
            }
        }
    }

    private MarkerCollection doTransition(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        this.mScheduler = new TransitionQueueScheduler();
        createMarkers(baseMarkerManager, set);
        this.mScheduler.waitUntilFree();
        baseMarkerManager.getCurrentMarkerCollection().getAllClusters().removeAll(this.mMarkerCollection.getAllClusters());
        removeMarkers(baseMarkerManager, set);
        this.mScheduler.waitUntilFree();
        return this.mMarkerCollection;
    }

    private static Point findClosestCluster(List<Point> list, Point point) {
        Point point2 = null;
        if (list != null && !list.isEmpty()) {
            double d = 10000.0d;
            for (Point next : list) {
                double distanceSquared = QuadTreeClusterAlgorithm.distanceSquared(next, point);
                if (distanceSquared < d) {
                    point2 = next;
                    d = distanceSquared;
                }
            }
        }
        return point2;
    }

    private double[] findIncludedClusterSet(Set<ICluster<MapItem>> set, ICluster<MapItem> iCluster) {
        for (ICluster next : set) {
            if (next.getItems().contains(iCluster.getTopItem())) {
                return next.getPosition();
            }
        }
        return null;
    }

    private List<Point> getPointsFromClusters(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        ArrayList arrayList = new ArrayList();
        for (ICluster next : set) {
            if (baseMarkerManager.isVisible(next.getPosition())) {
                arrayList.add(this.mMercatorProjection.toPoint(next.getPosition()));
            }
        }
        return arrayList;
    }

    private void init(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        this.mMarkerManagerRef = new WeakReference<>(baseMarkerManager);
        this.mNewClustersRef = new WeakReference<>(set);
        this.mMarkerCollection = new MarkerCollection();
        this.mMercatorProjection = new MercatorProjection(baseMarkerManager.getCurrentZoom(), baseMarkerManager.getMapZoom());
    }

    private void removeMarkers(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        List<Point> pointsFromClusters = getPointsFromClusters(baseMarkerManager, set);
        for (MarkerWithPosition next : baseMarkerManager.getCurrentMarkerCollection().getAllMarkers()) {
            boolean isVisible = baseMarkerManager.isVisible(next.getPosition());
            double[] dArr = null;
            if (this.mZoomIn || !isVisible) {
                this.mScheduler.addTask(new RemoveMarkerTask((double[]) null, next, baseMarkerManager));
            } else {
                double[] findIncludedClusterSet = findIncludedClusterSet(set, next.getCluster());
                if (findIncludedClusterSet == null) {
                    Point findClosestCluster = findClosestCluster(pointsFromClusters, this.mMercatorProjection.toPoint(next.getPosition()));
                    if (findClosestCluster != null) {
                        dArr = this.mMercatorProjection.toLatLng(findClosestCluster);
                    }
                    findIncludedClusterSet = dArr;
                }
                this.mScheduler.addTask(new RemoveMarkerTask(findIncludedClusterSet, next, baseMarkerManager));
            }
        }
    }

    private MarkerCollection updateMarkerVisibilities(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        this.mMarkerCollection = baseMarkerManager.getCurrentMarkerCollection();
        this.mScheduler = new TransitionQueueScheduler();
        if (this.mClear) {
            removeMarkers(baseMarkerManager, set);
            this.mScheduler.waitUntilFree();
        }
        for (ICluster next : set) {
            if (baseMarkerManager.isVisible(next.getPosition()) && !this.mMarkerCollection.containsPosition(next.getPosition())) {
                this.mScheduler.addTask(new AddMarkerTask((double[]) null, next, this.mMarkerCollection, baseMarkerManager));
            }
        }
        for (MarkerWithPosition next2 : this.mMarkerCollection.getAllMarkers()) {
            if (!baseMarkerManager.isVisible(next2.getPosition())) {
                this.mScheduler.addTask(new RemoveMarkerTask((double[]) null, next2, baseMarkerManager));
            }
        }
        this.mScheduler.waitUntilFree();
        return this.mMarkerCollection;
    }

    public void setCallback(Runnable runnable) {
        this.mCallback = runnable;
    }

    public MarkerCollection doInBackground(Void... voidArr) {
        Log.i("Transition", "Transition task start - zoomIn[" + this.mZoomIn + "]");
        BaseMarkerManager baseMarkerManager = this.mMarkerManagerRef.get();
        Set set = this.mNewClustersRef.get();
        if (baseMarkerManager == null || set == null) {
            if (baseMarkerManager == null) {
                return null;
            }
            return baseMarkerManager.getCurrentMarkerCollection();
        } else if (this.mClusterChanged) {
            return doTransition(baseMarkerManager, set);
        } else {
            return updateMarkerVisibilities(baseMarkerManager, set);
        }
    }

    public void onCancelled(MarkerCollection markerCollection) {
        TransitionQueueScheduler transitionQueueScheduler = this.mScheduler;
        if (transitionQueueScheduler != null) {
            transitionQueueScheduler.clear();
            this.mScheduler = null;
        }
    }

    public void onPostExecute(MarkerCollection markerCollection) {
        BaseMarkerManager baseMarkerManager = this.mMarkerManagerRef.get();
        if (baseMarkerManager == null || markerCollection == null) {
            Log.e("Transition", "fail to transition: " + baseMarkerManager + ArcCommonLog.TAG_COMMA + markerCollection);
            return;
        }
        baseMarkerManager.updateMarkerCollection(markerCollection);
        this.mCallback.run();
    }

    public void onCancelled() {
        TransitionQueueScheduler transitionQueueScheduler = this.mScheduler;
        if (transitionQueueScheduler != null) {
            transitionQueueScheduler.clear();
            this.mScheduler = null;
        }
    }

    public Transition(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set, boolean z, boolean z3) {
        this.mClusterChanged = false;
        this.mZoomIn = z;
        this.mClear = z3;
        init(baseMarkerManager, set);
    }

    public Transition(BaseMarkerManager baseMarkerManager, Set<ICluster<MapItem>> set) {
        this.mClusterChanged = false;
        this.mZoomIn = false;
        this.mClear = false;
        init(baseMarkerManager, set);
    }
}
