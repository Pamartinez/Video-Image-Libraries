package com.samsung.android.gallery.app.ui.map.search;

import V8.a;
import W8.C0579a;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions;
import b7.C0429a;
import com.samsung.android.gallery.app.ui.map.LocationPermissionUtil;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment;
import com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer;
import com.samsung.android.gallery.app.ui.map.search.ISearchMapView;
import com.samsung.android.gallery.app.ui.map.search.SearchMapPresenter;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.LocationValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMapFragment<V extends ISearchMapView, P extends SearchMapPresenter<V>> extends GalleryMapFragment<V, P> implements ISearchMapView {
    private long mFocusedId;
    private boolean mIsMapReady;
    private boolean mIsMapVisible;
    private LocationValue mLatestLocationInfo;
    private MapContainer.OnSnapshotReadyListener mListener;
    private MediaData mMapMediaData;
    private SearchMarkerManagerDelegate mMarkerManagerDelegate;
    private ActivityResultLauncher<String[]> mRequestPermissionLauncher;

    /* access modifiers changed from: private */
    public void cameraIdle() {
        if (this.mMarkerManagerDelegate != null && getActivity() != null && getActivity().hasWindowFocus()) {
            this.mMarkerManagerDelegate.updateVisibleRegion();
            if (((SearchMapPresenter) this.mPresenter).needToForceClusterOnCameraIdle()) {
                ((SearchMapPresenter) this.mPresenter).startCluster();
            } else {
                updateVisibleMarkers();
            }
        }
    }

    private boolean isGoogleMap() {
        return this.mMapContainer instanceof GoogleMapContainer;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerPermissionLauncher$0(Map map) {
        boolean containsValue = map.containsValue(Boolean.TRUE);
        Log.d(this.TAG, "onRequestPermissionsResult", Boolean.valueOf(containsValue));
        if (containsValue) {
            P p6 = this.mPresenter;
            if (p6 != null) {
                ((SearchMapPresenter) p6).moveToCurrentLocation();
            }
            this.mRequestPermissionLauncher.unregister();
            return;
        }
        LocationPermissionUtil.showPermissionRationaleDialog(getActivity());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$takeSnapShot$3(Bitmap bitmap) {
        onSnapShotReady(bitmap);
        this.mMapContainer.setOnCameraIdleListener(new C0429a(this));
    }

    private void moveToMapView(MediaItem mediaItem, boolean z) {
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(getScreenId(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
        SearchLocationKeyBuilder viewAll = new SearchLocationKeyBuilder(mediaItem, this.mBlackboard).searchToolbarEnabled(true).viewAll(true);
        if (z) {
            viewAll.showCluster(false).subCategory("");
        }
        this.mBlackboard.post("command://MoveURL", viewAll.build());
    }

    /* access modifiers changed from: private */
    public void onMapClicked() {
        moveToMapView((MediaItem) ((List) getMediaData().getAllData().stream().sorted(Comparator.comparing(new a(21)).reversed()).collect(Collectors.toList())).get(0), true);
    }

    /* access modifiers changed from: private */
    public void onMarkerClicked(Object obj) {
        if (this.mMarkerManagerDelegate != null) {
            IMapMarker galleryMarker = this.mMapContainer.getGalleryMarker(obj);
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || !this.mMarkerManagerDelegate.changeEntryMarker(galleryMarker)) {
                ThumbnailInterface item = this.mMarkerManagerDelegate.getItem(galleryMarker);
                if (item instanceof MediaItem) {
                    moveToMapView((MediaItem) item, true);
                }
            }
            Optional.ofNullable(this.mMapContainer.getView()).ifPresent(new C0579a(15));
        }
    }

    private void onSnapShotReady(Bitmap bitmap) {
        MapContainer.OnSnapshotReadyListener onSnapshotReadyListener = this.mListener;
        if (onSnapshotReadyListener != null) {
            onSnapshotReadyListener.onSnapshotReady(bitmap);
        }
        this.mIsMapVisible = true;
    }

    private void registerPermissionLauncher() {
        if (getActivity() != null && !LocationPermissionUtil.hasLocationPermission(getActivity(), false)) {
            this.mRequestPermissionLauncher = registerForActivityResult(new ActivityResultContracts$RequestMultiplePermissions(), new C0429a(this));
        }
    }

    private void setMapListeners() {
        this.mMapContainer.setOnCameraIdleListener(new C0429a(this));
        this.mMapContainer.setMarkerOnClickListener(new C0429a(this));
        this.mMapContainer.setMapOnClickListener(new C0429a(this));
    }

    private void takeSnapShot() {
        this.mMapContainer.setSnapShotCallback(new C0429a(this));
    }

    public void animateCamera(double[] dArr) {
        if (isGoogleMap()) {
            ((GoogleMapContainer) this.mMapContainer).animateCamera(dArr[0], dArr[1], (float) dArr[2]);
            return;
        }
        this.mMapContainer.moveCamera(dArr[0], dArr[1]);
        this.mMapContainer.setZoomLevel((float) dArr[2]);
    }

    public void bindData(MediaData mediaData, LocationValue locationValue) {
        this.mMapMediaData = mediaData;
        P p6 = this.mPresenter;
        if (p6 != null) {
            this.mFocusedId = locationValue.id;
            ((SearchMapPresenter) p6).onDataChanged(locationValue);
            if (this.mIsMapReady) {
                moveCameraToInitialLocation();
                return;
            }
            return;
        }
        this.mLatestLocationInfo = locationValue;
    }

    public void bindView(View view) {
        super.bindView(view);
    }

    public double getMapZoom() {
        return this.mMapContainer.getMapZoom();
    }

    public MediaData getMediaData() {
        return this.mMapMediaData;
    }

    public ActivityResultLauncher<String[]> getPermissionLauncher() {
        return this.mRequestPermissionLauncher;
    }

    public void handleDensityChange(int i2) {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.updateMarkerSize(getContext());
        }
        if (this.mMapContainer.hasMap()) {
            this.mMapContainer.handleDensityChanged(getContext());
        }
        ((SearchMapPresenter) this.mPresenter).startCluster();
    }

    public void initView(View view) {
        super.initView(view);
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = new SearchMarkerManagerDelegate(getContext(), this.mMapContainer);
        this.mMarkerManagerDelegate = searchMarkerManagerDelegate;
        searchMarkerManagerDelegate.setIconManager(getContext(), false, R.drawable.gallery_map_view_popup_normal_selected, R.drawable.gallery_map_view_popup_normal_selected);
    }

    public boolean isActivityToolbarSupported() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH || !PickerUtil.isPickerMode(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    public boolean isMapVisible() {
        return this.mIsMapVisible;
    }

    public void moveCameraToInitialLocation() {
        double[] location;
        if (!isDestroyed() && (location = ((SearchMapPresenter) this.mPresenter).getLocation()) != null) {
            this.mMapContainer.moveCamera(location[0], location[1]);
            this.mMapContainer.setZoomLevel(14.0f);
            SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
            if (searchMarkerManagerDelegate != null) {
                searchMarkerManagerDelegate.updateVisibleRegion();
            }
        }
    }

    public void moveTo(MediaItem mediaItem) {
        this.mFocusedId = mediaItem.getFileId();
        animateCamera(new double[]{mediaItem.getLatitude(), mediaItem.getLongitude(), getMapZoom()});
    }

    public void moveToCurrentLocation() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((SearchMapPresenter) p6).moveToCurrentLocation();
        }
    }

    public void onClustersChanged(Set<ICluster<MapItem>> set) {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.startMarkerTransition(set);
        }
        if (isMapVisible() || !isGoogleMap()) {
            onSnapShotReady((Bitmap) null);
        } else {
            takeSnapShot();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        registerPermissionLauncher();
    }

    public void onDestroy() {
        super.onDestroy();
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.onDestroy();
            this.mMarkerManagerDelegate = null;
        }
        this.mListener = null;
    }

    public void onMapReady(Object obj) {
        super.onMapReady(obj);
        setMapListeners();
        moveCameraToInitialLocation();
        this.mMapContainer.onClusteredMapReady();
        this.mMapContainer.setZoomControlsEnabled(false);
        this.mMapContainer.setAllGesturesEnabled(false);
        this.mIsMapReady = true;
    }

    public void onMapReload() {
        ((SearchMapPresenter) this.mPresenter).startCluster();
    }

    public void setOnSnapShotReadyListener(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        this.mListener = onSnapshotReadyListener;
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public void updateVisibleMarkers() {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            this.mFocusedId = searchMarkerManagerDelegate.updateVisibleMarkers(this.mFocusedId);
        }
    }

    public SearchMapPresenter createPresenter(ISearchMapView iSearchMapView) {
        return new SearchMapPresenter(getBlackboard(), this, this.mLatestLocationInfo);
    }

    public void finish() {
    }
}
