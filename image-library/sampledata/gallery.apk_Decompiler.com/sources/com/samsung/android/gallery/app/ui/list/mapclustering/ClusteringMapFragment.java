package com.samsung.android.gallery.app.ui.list.mapclustering;

import U9.b;
import W4.a;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenter;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapView;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.manager.IMarkerIconManager;
import com.samsung.android.gallery.module.map.manager.MarkerIconManager;
import com.samsung.android.gallery.module.map.manager.SmallMapMarkerIconManager;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusteringMapFragment<V extends IClusteringMapView, P extends ClusteringMapPresenter<V>> extends GalleryMapFragment<V, P> implements IClusteringMapView {
    private long mEntryId;
    private IMarkerIconManager mIconManager;
    protected MarkerManager mMarkerManager;
    private final AtomicBoolean mOnceInit = new AtomicBoolean(false);
    private boolean mRetryMarkerClicked;
    private boolean mShouldCheckVisibleMarker = false;
    protected GalleryToolbar mToolbar;

    private void firstCameraIdleWithMultipleLocations() {
        if (this.mMapContainer.isThereAtLeastOneVisibleMarker(((ClusteringMapPresenter) this.mPresenter).getValidLocations())) {
            updateVisibleMarkers();
        } else {
            moveCameraForSingleLocation(((ClusteringMapPresenter) this.mPresenter).getValidLocations());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setToolbar$0(GalleryToolbar galleryToolbar) {
        galleryToolbar.setTitle(getToolbarTitleId());
        galleryToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_with_inset);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateInitEntryMarker$1(long j2, MapItem mapItem) {
        if (mapItem.getMediaItem().getFileId() == j2) {
            return true;
        }
        return false;
    }

    private void moveCameraForSingleLocation(ArrayList<double[]> arrayList) {
        if (arrayList.isEmpty()) {
            this.mMapContainer.moveCamera((double) MapUtil.INVALID_LOCATION, (double) MapUtil.INVALID_LOCATION);
        } else {
            this.mMapContainer.moveCamera(arrayList.get(0)[0], arrayList.get(0)[1]);
        }
    }

    /* access modifiers changed from: private */
    public void onMarkersTransitionEnd() {
        ICluster<MapItem> cluster;
        if (this.mEntryId > 0 && BaseMarkerManager.getCurrentCollection(this.mMarkerManager).getAllMarkers().size() > 0) {
            this.mEntryId = updateInitEntryMarker(this.mEntryId);
            BaseMarkerManager.setOnTransitionEndListener(this.mMarkerManager, (Runnable) null);
        } else if (this.mRetryMarkerClicked) {
            this.mRetryMarkerClicked = false;
            MarkerWithPosition entryMarker = BaseMarkerManager.getEntryMarker(this.mMarkerManager);
            if (entryMarker != null && (cluster = entryMarker.getCluster()) != null) {
                for (MarkerWithPosition next : BaseMarkerManager.getCurrentCollection(this.mMarkerManager).getAllMarkers()) {
                    if (next.getCluster().getPosition()[0] == cluster.getPosition()[0] && next.getCluster().getPosition()[1] == cluster.getPosition()[1]) {
                        break;
                    }
                }
            }
            BaseMarkerManager.setOnTransitionEndListener(this.mMarkerManager, (Runnable) null);
        }
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
    }

    private void setToolbar() {
        Optional.ofNullable(getToolbar()).ifPresent(new b(8, this));
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0043 A[LOOP:1: B:9:0x0043->B:12:0x0057, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long updateInitEntryMarker(long r6) {
        /*
            r5 = this;
            com.samsung.android.gallery.module.map.abstraction.MarkerManager r0 = r5.mMarkerManager
            if (r0 != 0) goto L_0x0005
            goto L_0x0059
        L_0x0005:
            r1 = 0
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x005a
            java.util.Set r0 = com.samsung.android.gallery.module.map.transition.BaseMarkerManager.getClusters(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x0013:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0035
            java.lang.Object r1 = r0.next()
            com.samsung.android.gallery.module.map.clustering.ICluster r1 = (com.samsung.android.gallery.module.map.clustering.ICluster) r1
            java.util.List r2 = r1.getItems()
            java.util.stream.Stream r2 = r2.stream()
            W4.b r3 = new W4.b
            r4 = 0
            r3.<init>(r6, r4)
            boolean r2 = r2.anyMatch(r3)
            r1.setEntryItem(r2)
            goto L_0x0013
        L_0x0035:
            com.samsung.android.gallery.module.map.abstraction.MarkerManager r5 = r5.mMarkerManager
            com.samsung.android.gallery.module.map.transition.abstraction.MarkerCollection r5 = com.samsung.android.gallery.module.map.transition.BaseMarkerManager.getCurrentCollection(r5)
            java.util.Set r5 = r5.getAllMarkers()
            java.util.Iterator r5 = r5.iterator()
        L_0x0043:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0059
            java.lang.Object r0 = r5.next()
            com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition r0 = (com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition) r0
            com.samsung.android.gallery.module.map.clustering.ICluster r0 = r0.getCluster()
            boolean r0 = r0.isEntryItem()
            if (r0 == 0) goto L_0x0043
        L_0x0059:
            return r6
        L_0x005a:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragment.updateInitEntryMarker(long):long");
    }

    private void updateToolbarConfigurations() {
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.dispatchConfigurationChanged(true, getResources().getConfiguration());
        }
    }

    private void updateVisibleRegion() {
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            markerManager.updateVisibleRegion();
        }
    }

    public void attachIconManager(MarkerManager<?> markerManager, IMarkerIconManager iMarkerIconManager) {
        BaseMarkerManager.setIconManager(markerManager, iMarkerIconManager);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
    }

    public void cameraIdle() {
        if (this.mMarkerManager != null && getActivity() != null && getActivity().hasWindowFocus() && isResumed()) {
            updateVisibleRegion();
            if (this.mShouldCheckVisibleMarker) {
                this.mShouldCheckVisibleMarker = false;
                firstCameraIdleWithMultipleLocations();
            } else if (((ClusteringMapPresenter) this.mPresenter).needToForceClusterOnCameraIdle()) {
                ((ClusteringMapPresenter) this.mPresenter).startCluster();
            } else {
                updateVisibleMarkers();
            }
        }
    }

    public MarkerIconManager createIconMarker() {
        return new SmallMapMarkerIconManager(getContext());
    }

    public FrameLayout getMapViewParent(View view) {
        return (FrameLayout) view.findViewById(R.id.map_container);
    }

    public double getMapZoom() {
        return this.mMapContainer.getMapZoom();
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public int getToolbarTitleId() {
        return R.string.map_view;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            markerManager.updateMarkerSize(getContext());
        }
        if (this.mMapContainer.hasMap()) {
            this.mMapContainer.handleDensityChanged(getContext());
        }
        ((ClusteringMapPresenter) this.mPresenter).startCluster();
    }

    public void initCopyright(MapContainer mapContainer) {
        if (mapContainer instanceof GoogleMapContainer) {
            ((GoogleMapContainer) mapContainer).initCopyright();
        }
    }

    public void initView(View view) {
        super.initView(view);
        this.mMarkerManager = GalleryMapFactory.createMarkerManager(getContext(), this.mMapContainer);
        MarkerIconManager createIconMarker = createIconMarker();
        this.mIconManager = createIconMarker;
        attachIconManager(this.mMarkerManager, createIconMarker);
    }

    public boolean isFilterable() {
        return getLocationKey().startsWith("location://map/filtered");
    }

    public void moveCamera() {
        if (isFilterable()) {
            moveCameraForSingleLocation(((ClusteringMapPresenter) this.mPresenter).getValidLocations());
        } else {
            moveCameraForSingleLocation(((ClusteringMapPresenter) this.mPresenter).getValidLocations());
        }
    }

    public void moveCameraForMultipleLocations() {
        ArrayList<double[]> validLocations = ((ClusteringMapPresenter) this.mPresenter).getValidLocations();
        removeDuplications(validLocations);
        if (validLocations.isEmpty() || validLocations.size() == 1) {
            moveCameraForSingleLocation(validLocations);
            return;
        }
        this.mShouldCheckVisibleMarker = true;
        try {
            this.mMapContainer.moveCameraToCenter(validLocations);
        } catch (Exception e) {
            e.printStackTrace();
            moveCameraForSingleLocation(validLocations);
        }
    }

    public boolean needToRegisterInsetListener() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            marginLayoutParams.leftMargin = WindowUtils.getSystemInsetsLeft(rootWindowInsets);
            marginLayoutParams.rightMargin = WindowUtils.getSystemInsetsRight(rootWindowInsets);
            marginLayoutParams.topMargin = WindowUtils.getSystemInsetsTop(rootWindowInsets);
            marginLayoutParams.bottomMargin = WindowUtils.getSystemInsetsBottom(rootWindowInsets);
            view.setLayoutParams(marginLayoutParams);
        }
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (!this.mOnceInit.getAndSet(true)) {
            this.mEntryId = ((ClusteringMapPresenter) this.mPresenter).getEntryItemKey();
        }
    }

    public void onBindView(View view) {
        GalleryToolbar galleryToolbar;
        super.onBindView(view);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
        if (supportToolbar()) {
            setToolbar();
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            if (appCompatActivity != null && (galleryToolbar = this.mToolbar) != null) {
                appCompatActivity.setSupportActionBar(galleryToolbar);
                this.mToolbar.bringToFront();
                return;
            }
            return;
        }
        ViewUtils.setVisibleOrGone(this.mToolbar, false);
    }

    public void onClustersChanged(Set<ICluster<MapItem>> set) {
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            BaseMarkerManager.setOnTransitionEndListener(markerManager, new V3.b(7, this));
            this.mMarkerManager.startMarkerTransition(set);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            BaseMarkerManager.setOnTransitionEndListener(markerManager, (Runnable) null);
            this.mMarkerManager.destroy();
            this.mMarkerManager = null;
        }
    }

    public void onMapReady(Object obj) {
        super.onMapReady(obj);
        if (!isDestroyed()) {
            setMapListeners();
            moveCamera();
            this.mMapContainer.onClusteredMapReady();
            this.mMapContainer.setZoomControlsEnabled(supportZoom());
            ((ClusteringMapPresenter) this.mPresenter).initFirstMapViewZoomLevel();
            updateVisibleRegion();
            ((ClusteringMapPresenter) this.mPresenter).onMapReady();
        }
    }

    public void onMapReload() {
        super.onMapReload();
        ((ClusteringMapPresenter) this.mPresenter).startCluster();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (isViewReady()) {
            updateToolbarConfigurations();
        }
    }

    public void registerWindowInsetListener(List<View> list) {
        if (needToRegisterInsetListener()) {
            list.add(getView());
        }
    }

    public void retryMarkerClicked() {
        this.mRetryMarkerClicked = true;
    }

    public void setMapZoomLevel(float f) {
        MapContainer mapContainer = this.mMapContainer;
        if (mapContainer != null) {
            mapContainer.setZoomLevel(f);
        }
    }

    public void startPostponedEnterTransition() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
    }

    public boolean supportZoom() {
        return true;
    }

    public void updateVisibleMarkers() {
        MarkerManager markerManager = this.mMarkerManager;
        if (markerManager != null) {
            markerManager.updateVisibleMarkers();
        }
    }

    public ClusteringMapPresenter createPresenter(IClusteringMapView iClusteringMapView) {
        if (isFilterable()) {
            return new FilterableClusteringMapPresenter(getBlackboard(), this, getLocationKey());
        }
        return new ClusteringMapPresenter(getBlackboard(), this);
    }

    public void finish() {
    }

    public void onMapClicked() {
    }

    public void onMarkerClicked(Object obj) {
    }
}
