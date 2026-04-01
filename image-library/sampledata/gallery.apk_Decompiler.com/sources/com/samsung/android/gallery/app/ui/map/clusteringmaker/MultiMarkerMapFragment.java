package com.samsung.android.gallery.app.ui.map.clusteringmaker;

import U5.c;
import X6.a;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.IMultiMarkerMapView;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.MultiMarkerMapPresenter;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiMarkerMapFragment<V extends IMultiMarkerMapView, P extends MultiMarkerMapPresenter<V>> extends GalleryMapFragment<V, P> implements IMultiMarkerMapView {
    private String mDataLocationKey;
    private Consumer<ArrayList<double[]>> mMapPlacedConsumer;
    private BaseMarkerManager mMarkerManager;
    private boolean mShouldCheckVisibleMarker = false;
    private MapContainer.OnSnapshotReadyListener mSnapshotReady;

    public MultiMarkerMapFragment(String str, String str2) {
        setLocationKey(str);
        this.mDataLocationKey = str2;
    }

    private void firstCameraIdleWithMultipleLocations() {
        if (this.mMapContainer.isThereAtLeastOneVisibleMarker(((MultiMarkerMapPresenter) this.mPresenter).getValidLocations())) {
            updateVisibleMarkers();
        } else {
            moveCameraForSingleLocation(((MultiMarkerMapPresenter) this.mPresenter).getValidLocations());
        }
    }

    private void initialMapPlaced(ArrayList<double[]> arrayList) {
        Consumer<ArrayList<double[]>> consumer = this.mMapPlacedConsumer;
        if (consumer != null) {
            consumer.accept(this.mMapContainer.getAllVisibleLocations(arrayList));
        }
    }

    private void moveCamera() {
        setMapListeners();
        moveCameraForMultipleLocations(((MultiMarkerMapPresenter) this.mPresenter).getValidLocations());
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        if (baseMarkerManager != null) {
            baseMarkerManager.updateVisibleRegion();
        }
    }

    private void moveCameraForMultipleLocations(ArrayList<double[]> arrayList) {
        if (!isDestroyed()) {
            removeDuplications(arrayList);
            boolean z = false;
            if (arrayList.isEmpty() || arrayList.size() == 1) {
                moveCameraForSingleLocation(arrayList);
            } else {
                this.mShouldCheckVisibleMarker = true;
                try {
                    this.mMapContainer.moveCameraToCenter(arrayList);
                    if (this.mMapContainer.isAllMarkerVisible(arrayList)) {
                        this.mMapContainer.setZoomLevel(getZoomLevelWithMarker());
                    } else {
                        z = moveLargestClusteredLocation();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    moveCameraForSingleLocation(arrayList);
                }
            }
            if (!z) {
                ((MultiMarkerMapPresenter) this.mPresenter).startCluster();
            }
            initialMapPlaced(arrayList);
        }
    }

    private void moveCameraForSingleLocation(ArrayList<double[]> arrayList) {
        if (arrayList.isEmpty()) {
            this.mMapContainer.moveCamera((double) MapUtil.INVALID_LOCATION, (double) MapUtil.INVALID_LOCATION);
        } else {
            this.mMapContainer.moveCamera(arrayList.get(0)[0], arrayList.get(0)[1]);
        }
    }

    private boolean moveLargestClusteredLocation() {
        ArrayList<double[]> largestClusteredLocations = ((MultiMarkerMapPresenter) this.mPresenter).getLargestClusteredLocations(this.mMapContainer.getMapZoom());
        if (largestClusteredLocations.isEmpty()) {
            return false;
        }
        removeDuplications(largestClusteredLocations);
        if (largestClusteredLocations.size() == 1) {
            moveCameraForSingleLocation(largestClusteredLocations);
            return false;
        }
        this.mMapContainer.moveCameraToCenter(largestClusteredLocations);
        ((MultiMarkerMapPresenter) this.mPresenter).requestCluster(this.mMapContainer.getMapZoom());
        this.mMapContainer.setZoomLevel(getZoomLevelWithMarker());
        return true;
    }

    /* access modifiers changed from: private */
    public void onMapClicked() {
        View view = getView();
        if (view != null && view.getParent() != null) {
            ((View) view.getParent()).callOnClick();
        }
    }

    /* access modifiers changed from: private */
    public void onMarkerClicked(Object obj) {
        onMapClicked();
    }

    private void removeDuplications(ArrayList<double[]> arrayList) {
        HashMap hashMap = new HashMap();
        Iterator<double[]> it = arrayList.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            hashMap.put(next[0] + " " + next[1], next);
        }
        arrayList.clear();
        arrayList.addAll(hashMap.values());
    }

    private void setMapListeners() {
        this.mMapContainer.setOnCameraIdleListener(new a(this));
        this.mMapContainer.setMapOnClickListener(new a(this));
        this.mMapContainer.setMarkerOnClickListener(new a(this));
        this.mMapContainer.setSnapShotCallback(new a(this));
    }

    public void cameraIdle() {
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        if (baseMarkerManager != null) {
            baseMarkerManager.updateVisibleRegion();
            if (this.mShouldCheckVisibleMarker && !((MultiMarkerMapPresenter) this.mPresenter).needToForceClusterOnCameraIdle()) {
                this.mShouldCheckVisibleMarker = false;
                firstCameraIdleWithMultipleLocations();
            } else if (((MultiMarkerMapPresenter) this.mPresenter).needToForceClusterOnCameraIdle()) {
                ((MultiMarkerMapPresenter) this.mPresenter).startCluster();
            } else {
                updateVisibleMarkers();
            }
        }
    }

    public double getMapZoom() {
        return this.mMapContainer.getMapZoom();
    }

    public float getZoomLevelWithMarker() {
        if (this.mMapContainer.getMapZoom() > 13.0d) {
            return 13.0f;
        }
        return Math.max(((float) this.mMapContainer.getMapZoom()) - 0.1f, 1.0f);
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        if (baseMarkerManager != null) {
            baseMarkerManager.updateMarkerSize(getContext());
        }
        if (this.mMapContainer.hasMap()) {
            this.mMapContainer.handleDensityChanged(getContext());
        }
        ((MultiMarkerMapPresenter) this.mPresenter).startCluster();
    }

    public void initView(View view) {
        super.initView(view);
        this.mMarkerManager = GalleryMapFactory.createSimpleMarkerManager(getContext(), this.mMapContainer);
    }

    public void onBindView(View view) {
        super.onBindView(view);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    public void onClustersChanged(Set<ICluster<MapItem>> set) {
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        if (baseMarkerManager != null) {
            baseMarkerManager.startMarkerTransition(set);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        if (baseMarkerManager != null) {
            baseMarkerManager.destroy();
            this.mMarkerManager = null;
        }
        this.mSnapshotReady = null;
    }

    /* renamed from: onMapReady */
    public void lambda$onMapReady$0(Object obj) {
        if (!isDestroyed()) {
            View view = getView();
            if (view != null && view.getWidth() > 0) {
                super.onMapReady(obj);
                this.mMapContainer.setAllGesturesEnabled(false);
                this.mMapContainer.setZoomControlsEnabled(false);
                moveCamera();
            } else if (view != null) {
                view.post(new c(20, this, obj));
            }
        }
    }

    public void onMapReload() {
        super.onMapReload();
        ((MultiMarkerMapPresenter) this.mPresenter).startCluster();
    }

    public void setMapPlacedListener(Consumer<ArrayList<double[]>> consumer) {
        this.mMapPlacedConsumer = consumer;
    }

    public void setMapSnapshotReadyListener(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        this.mSnapshotReady = onSnapshotReadyListener;
    }

    public void snapshotReady(Bitmap bitmap) {
        MapContainer.OnSnapshotReadyListener onSnapshotReadyListener = this.mSnapshotReady;
        if (onSnapshotReadyListener != null) {
            onSnapshotReadyListener.onSnapshotReady(bitmap);
        }
    }

    public boolean supportToolbar() {
        return false;
    }

    public void updateVisibleMarkers() {
        BaseMarkerManager baseMarkerManager = this.mMarkerManager;
        if (baseMarkerManager != null) {
            baseMarkerManager.updateVisibleMarkers();
        }
    }

    public P createPresenter(V v) {
        return new MultiMarkerMapPresenter(getBlackboard(), this, getLocationKey(), this.mDataLocationKey);
    }

    public void onPrepareSharedTransitionV2() {
    }

    public void setScreenMode() {
    }
}
