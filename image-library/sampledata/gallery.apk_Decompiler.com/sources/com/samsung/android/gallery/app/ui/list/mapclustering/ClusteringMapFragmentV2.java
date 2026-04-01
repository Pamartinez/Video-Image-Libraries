package com.samsung.android.gallery.app.ui.list.mapclustering;

import E5.b;
import T3.e;
import U5.c;
import V8.a;
import W4.d;
import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenterV2;
import com.samsung.android.gallery.app.ui.list.mapclustering.IClusteringMapViewV2;
import com.samsung.android.gallery.app.ui.list.mapclustering.list.bottomsheet.BottomSheetListAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusteringMapFragmentV2<V extends IClusteringMapViewV2, P extends ClusteringMapPresenterV2<V>> extends PicturesFragment<V, P> implements IClusteringMapViewV2, SearchPicturesLocationBehavior.BottomSheetCallback {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 32768 && ClusteringMapFragmentV2.this.getMapView() != null) {
                ClusteringMapFragmentV2.this.getMapView().setImportantForAccessibility(1);
            }
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }
    };
    protected SearchPicturesLocationBehavior<View> mBehavior;
    private View mBottomSheetLayout;
    private View mBottomSheetLayoutContainer;
    private TextView mCountView;
    private String mCurrentMakerId;
    private FloatingToolbarLayout mFloatingToolbar;
    private String mId;
    private String mIds;
    private boolean mIsSelectedLocationShown = true;
    private String mListLocationKey = "location://map/cluster";
    /* access modifiers changed from: private */
    public MediaData mListMediaData;
    final MediaData.OnDataChangeListener mListMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            MediaItem read;
            ((ClusteringMapPresenterV2) ClusteringMapFragmentV2.this.mPresenter).ignoreNextDataChanged();
            ClusteringMapFragmentV2.super.onDataChangedOnUi();
            if (ClusteringMapFragmentV2.this.updateItemCounts() <= 0) {
                ClusteringMapFragmentV2.this.hideList();
            } else if (ClusteringMapFragmentV2.this.mMarkerManagerDelegate.getMarkerIncludeFocusedId() == null && (read = ClusteringMapFragmentV2.this.mListMediaData.read(0)) != null) {
                ClusteringMapFragmentV2.this.changeClickedMarker(ClusteringMapFragmentV2.this.mMarkerManagerDelegate.getMarkerIncludeFocusedId(read.getFileId()));
            }
        }

        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new a(this, 1));
        }
    };
    private ViewGroup mMainLayout;
    protected MapContainer mMapContainer;
    private MediaData mMapData;
    private ViewGroup mMapLayout;
    private WeakReference<View> mMapViewRef;
    protected SearchMarkerManagerDelegate mMarkerManagerDelegate;
    private long mMaxDateTime = -1;
    final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            ClusteringMapFragmentV2.this.onDataChangedOnUi();
        }

        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new a(this, 0));
        }
    };
    private long mMinDateTime = -1;
    private int mSortBy;
    private boolean mSupportViewAllButton;
    private final AtomicBoolean mUseLatitudeOffset = new AtomicBoolean(false);

    private void applyMapAlphaAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mMapLayout, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    /* access modifiers changed from: private */
    public ICluster<MapItem> changeClickedMarker(IMapMarker iMapMarker) {
        ICluster<MapItem> iCluster;
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate == null || iMapMarker == null) {
            iCluster = null;
        } else {
            iCluster = searchMarkerManagerDelegate.onMarkerClicked(iMapMarker);
        }
        if (iCluster == null) {
            Log.e(this.TAG, "Fail to show list");
            hideList();
            return null;
        }
        Log.v(this.TAG, "change clicked marker {" + this.mCurrentMakerId + ArcCommonLog.TAG_COMMA + iMapMarker.getId() + ArcCommonLog.TAG_COMMA + iCluster.getSize() + "}");
        Optional.ofNullable(this.mMapContainer.getView()).ifPresent(new e(28));
        if (!TextUtils.equals(this.mCurrentMakerId, iMapMarker.getId())) {
            this.mCurrentMakerId = iMapMarker.getId();
            requestClusterListData(getGpsRange(iCluster), getIdList(iCluster));
        }
        this.mUseLatitudeOffset.set(true);
        return iCluster;
    }

    private void createMap(Bundle bundle) {
        Log.d(this.TAG, "createMap");
        MapContainer createMap = GalleryMapFactory.createMap(getContext());
        this.mMapContainer = createMap;
        createMap.onCreate(bundle);
        this.mMapContainer.getMapAsync(new d(this));
    }

    private String getBucketId() {
        if (this.mIsSelectedLocationShown) {
            return this.mId;
        }
        return null;
    }

    private String getBucketIds() {
        if (this.mIsSelectedLocationShown) {
            return this.mIds;
        }
        return null;
    }

    private String getClusterDataKey(String str, String str2) {
        return new UriBuilder("location://map/cluster").appendUri(String.valueOf(hashCode())).appendArg("sub", getSub(getListLocationKey())).appendArg("gps_range", str).appendArg("file_ids", str2).appendArg("start_time", getMinDateTime()).appendArg("end_time", getMaxDateTime()).appendArg("ids", getBucketIds()).appendArg("id", getBucketId()).appendArg("sort_by", getSortBy()).build();
    }

    private int getInitialBehaviorState() {
        return 5;
    }

    private double getLatitudeOffset() {
        MapVisibleRegion visibleRegion;
        if (!useLatitudeOffset() || (visibleRegion = this.mMapContainer.getVisibleRegion()) == null) {
            return MapUtil.INVALID_LOCATION;
        }
        MapLatLngBounds mapLatLngBounds = visibleRegion.latLngBounds;
        return (mapLatLngBounds.northeast.latitude - mapLatLngBounds.southwest.latitude) / 6.0d;
    }

    /* access modifiers changed from: private */
    public View getMapView() {
        WeakReference<View> weakReference = this.mMapViewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private long getMaxDateTime() {
        if (this.mIsSelectedLocationShown) {
            return this.mMaxDateTime;
        }
        return -1;
    }

    private long getMinDateTime() {
        if (this.mIsSelectedLocationShown) {
            return this.mMinDateTime;
        }
        return -1;
    }

    private int getSortBy() {
        if (this.mIsSelectedLocationShown) {
            return this.mSortBy;
        }
        return 12;
    }

    private String getSub(String str) {
        if (!this.mIsSelectedLocationShown || ArgumentsUtil.getArgValue(str, "searchResultOnMapView", false)) {
            return null;
        }
        return ArgumentsUtil.getArgValue(str, "sub", (String) null);
    }

    /* access modifiers changed from: private */
    public boolean hideList() {
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior == null || SheetBehaviorCompat.isHidden(searchPicturesLocationBehavior.getState())) {
            return false;
        }
        Optional.ofNullable(this.mMarkerManagerDelegate).ifPresent(new e(27));
        setBehaviorState(5);
        return true;
    }

    private void initBehavior() {
        SearchPicturesLocationBehavior<View> from = SearchPicturesLocationBehavior.from(this.mBottomSheetLayout);
        this.mBehavior = from;
        from.addBottomSheetCallback(this);
        setBehaviorState(getInitialBehaviorState());
        if (PocFeatures.isEnabled(PocFeatures.MapViewBlur)) {
            this.mBehavior.setBlurTarget(getMapView());
        }
    }

    private void initMapData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://map");
        this.mMapData = open;
        open.register(this.mMediaDataChangeListener);
    }

    private void initMapView() {
        if (this.mMapViewRef == null) {
            Log.d(this.TAG, "initView");
            this.mMapViewRef = new WeakReference<>(this.mMapContainer.getView());
            if (getMapView() != null) {
                getMapView().setImportantForAccessibility(4);
                this.mMapLayout.addView(getMapView());
            }
            if (isGoogleMap()) {
                ((GoogleMapContainer) this.mMapContainer).initCopyright();
            }
        }
    }

    private boolean isGoogleMap() {
        return this.mMapContainer instanceof GoogleMapContainer;
    }

    private boolean isListEmpty() {
        MediaData mediaData = this.mListMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return true;
        }
        return false;
    }

    private boolean isMapWithoutFilter() {
        return LocationKey.isMapMatch(ArgumentsUtil.removeArgs(getLocationKey()));
    }

    private boolean isRecentSearchPictures() {
        if (!LocationKey.isSearchCategoryLocation(getLocationKey()) || ArgumentsUtil.getArgValue(getLocationKey(), "sub") != null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveCameraToInitialLocation$3(double[] dArr) {
        moveCameraWithOffset(dArr, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBehaviorExpandedOffset$2() {
        int i2;
        View view = getView();
        if (view != null) {
            WindowUtils.getSystemInsetsTop(view.getRootWindowInsets());
            int intValue = ((Integer) Optional.ofNullable(getToolbar()).map(new a(4)).orElse(0)).intValue();
            Resources resources = view.getResources();
            if (view.getResources().getConfiguration().orientation == 2) {
                i2 = R.dimen.search_pictures_location_expanded_top_offset_land;
            } else {
                i2 = R.dimen.search_pictures_location_expanded_top_offset_port;
            }
            int dimensionPixelOffset = resources.getDimensionPixelOffset(i2);
            int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.mapview_bottom_sheet_bottom_margin);
            if (isGoogleMap()) {
                ((GoogleMapContainer) this.mMapContainer).setTopPadding(0);
            }
            this.mBehavior.setExpandedOffset(intValue + dimensionPixelOffset2 + dimensionPixelOffset);
        }
    }

    private void loadInitialListItems() {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate;
        ICluster<MapItem> changeClickedMarker;
        if (isListEmpty() && (searchMarkerManagerDelegate = this.mMarkerManagerDelegate) != null) {
            MarkerWithPosition entryMarkerItems = searchMarkerManagerDelegate.getEntryMarkerItems();
            if (((ClusteringMapPresenterV2) this.mPresenter).hasEntryItem() && entryMarkerItems != null && this.mMarkerManagerDelegate.getClickedMarker() == null && (changeClickedMarker = changeClickedMarker(entryMarkerItems.getMarker())) != null) {
                setBehaviorState(6);
                moveCameraWithOffset(changeClickedMarker.getPosition(), true);
            }
        }
    }

    private void moveCameraToInitialLocation() {
        double[] initialLocations = ((ClusteringMapPresenterV2) this.mPresenter).getInitialLocations();
        this.mMapContainer.setZoomLevel(13.0f);
        ThreadUtil.postOnUiThread(new c(16, this, initialLocations));
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.updateVisibleRegion();
        }
    }

    private void onMapReload() {
        ((ClusteringMapPresenterV2) this.mPresenter).startCluster();
    }

    /* access modifiers changed from: private */
    public void onMarkerTransitionEnd() {
        IMapMarker markerIncludeFocusedId;
        loadInitialListItems();
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null && (markerIncludeFocusedId = searchMarkerManagerDelegate.getMarkerIncludeFocusedId()) != null) {
            changeClickedMarker(markerIncludeFocusedId);
        }
    }

    private void requestClusterListData(String str, String str2) {
        boolean z;
        this.mListLocationKey = getClusterDataKey(str, str2);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("requestClusterListData { listMediaData null (");
        if (this.mListMediaData == null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("), locationKey : ");
        sb2.append(this.mListLocationKey);
        sb2.append("}");
        Log.d(stringCompat, sb2.toString());
        MediaData mediaData = this.mListMediaData;
        if (mediaData == null) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(this.mListLocationKey);
            this.mListMediaData = open;
            open.register(this.mListMediaDataChangeListener);
            return;
        }
        mediaData.reopen(this.mListLocationKey);
    }

    private void setBehaviorState(int i2) {
        boolean z;
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior != null) {
            searchPicturesLocationBehavior.setState(i2);
            MapContainer mapContainer = this.mMapContainer;
            if (i2 == 5) {
                z = true;
            } else {
                z = false;
            }
            mapContainer.setZoomControlsEnabled(z);
        }
    }

    private void setMapListeners() {
        this.mMapContainer.setOnCameraIdleListener(new d(this));
        this.mMapContainer.setMarkerOnClickListener(new d(this));
        this.mMapContainer.setMapOnClickListener(new d(this));
    }

    private void updateBehaviorExpandedOffset() {
        ThreadUtil.postOnUiThread(new W4.c(this, 0));
    }

    private void updateBottomSheetMargin() {
        ViewUtils.setFlexibleSideMargin(this.mMainLayout, this.mBottomSheetLayout);
    }

    /* access modifiers changed from: private */
    public int updateItemCounts() {
        int dataCount = getDataCount();
        ViewUtils.setText(this.mCountView, ((ClusteringMapPresenterV2) this.mPresenter).getItemCountText(dataCount));
        return dataCount;
    }

    private boolean useLatitudeOffset() {
        if ((isListEmpty() && ((ClusteringMapPresenterV2) this.mPresenter).hasEntryItem()) || this.mUseLatitudeOffset.getAndSet(false)) {
            return true;
        }
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior == null || SheetBehaviorCompat.isClosed(searchPicturesLocationBehavior.getState())) {
            return false;
        }
        return true;
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = (ViewGroup) view.findViewById(R.id.main_layout);
        this.mCountView = (TextView) view.findViewById(R.id.item_count);
        this.mBottomSheetLayout = view.findViewById(R.id.bottom_sheet_layout);
        this.mBottomSheetLayoutContainer = view.findViewById(R.id.bottom_sheet_layout_container);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.map_container);
        this.mMapLayout = viewGroup;
        viewGroup.setAccessibilityDelegate(this.mAccessibilityDelegate);
        this.mFloatingToolbar = (FloatingToolbarLayout) view.findViewById(R.id.sesl_floating_toolbar_layout);
        applyMapAlphaAnimation();
        this.mRecyclerView.setClipToPadding(false);
        updateBottomSheetMargin();
    }

    public void cameraIdle() {
        if (this.mMarkerManagerDelegate != null && getActivity() != null && getActivity().hasWindowFocus()) {
            this.mMarkerManagerDelegate.updateVisibleRegion();
            if (((ClusteringMapPresenterV2) this.mPresenter).needToForceClusterOnCameraIdle()) {
                ((ClusteringMapPresenterV2) this.mPresenter).startCluster();
            } else {
                updateVisibleMarkers();
            }
            if (getMapView() != null) {
                getMapView().setImportantForAccessibility(4);
            }
        }
    }

    public void closeMediaData() {
        MediaData mediaData = this.mListMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mListMediaDataChangeListener);
            this.mListMediaData.close();
            this.mListMediaData = null;
        }
        MediaData mediaData2 = this.mMapData;
        if (mediaData2 != null) {
            mediaData2.unregister(this.mMediaDataChangeListener);
            this.mMapData.close();
            this.mMapData = null;
        }
        super.closeMediaData();
    }

    public SimpleAutoScroller createSimpleAutoScroller(int i2) {
        return super.createSimpleAutoScroller(i2).setPostAnim();
    }

    public String getGpsRange(ICluster<MapItem> iCluster) {
        double d = Double.MAX_VALUE;
        double d2 = Double.MIN_VALUE;
        double d3 = Double.MIN_VALUE;
        double d5 = Double.MAX_VALUE;
        for (MapItem position : iCluster.getItems()) {
            double[] position2 = position.getPosition();
            d = Math.min(d, position2[0]);
            d2 = Math.max(d2, position2[0]);
            d5 = Math.min(d5, position2[1]);
            d3 = Math.max(d3, position2[1]);
        }
        return d + GlobalPostProcInternalPPInterface.SPLIT_REGEX + d2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + d5 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + d3;
    }

    public String getIdList(ICluster<MapItem> iCluster) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        iCluster.getItems().forEach(new B5.e(stringJoiner, 6));
        return stringJoiner.toString();
    }

    public int getLayoutId() {
        return R.layout.fragment_clustering_map_layout_v2;
    }

    public String getListLocationKey() {
        return this.mListLocationKey;
    }

    public MediaData getMapData() {
        if (this.mIsSelectedLocationShown) {
            return this.mMediaData;
        }
        return this.mMapData;
    }

    public double getMapZoom() {
        return this.mMapContainer.getMapZoom();
    }

    public MediaData getMediaData(String str) {
        return this.mListMediaData;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_LOCATION_CATEGORY_PICTURES.toString();
    }

    public int getStartPinchDepth() {
        return 2;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.updateMarkerSize(getContext());
        }
        if (this.mMapContainer.hasMap()) {
            this.mMapContainer.handleDensityChanged(getContext());
        }
        ((ClusteringMapPresenterV2) this.mPresenter).startCluster();
    }

    public boolean hasMap() {
        MapContainer mapContainer = this.mMapContainer;
        if (mapContainer == null || !mapContainer.hasMap()) {
            return false;
        }
        return true;
    }

    public void initMarkerManager() {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = new SearchMarkerManagerDelegate(getContext(), this.mMapContainer);
        this.mMarkerManagerDelegate = searchMarkerManagerDelegate;
        searchMarkerManagerDelegate.setIconManager(getContext(), true);
        this.mMarkerManagerDelegate.setOnTransitionEndListener(new W4.c(this, 1));
    }

    public void initView(View view) {
        super.initView(view);
        initMapView();
        initMarkerManager();
        initBehavior();
    }

    public boolean isDataShowing() {
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior == null || SheetBehaviorCompat.isHidden(searchPicturesLocationBehavior.getState())) {
            return false;
        }
        return true;
    }

    public boolean isRecentEntryItem() {
        return isRecentSearchPictures();
    }

    public void loadArguments(Bundle bundle) {
        boolean z;
        long[] array;
        super.loadArguments(bundle);
        if (isMapWithoutFilter() || isRecentSearchPictures()) {
            z = false;
        } else {
            z = true;
        }
        this.mSupportViewAllButton = z;
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "cluster_map_date", "");
        if (!TextUtils.isEmpty(argValue) && (array = Stream.of(argValue.split("-")).mapToLong(new b(7)).toArray()) != null && array.length > 1) {
            this.mMinDateTime = Math.min(array[0], array[1]);
            this.mMaxDateTime = Math.max(array[0], array[1]);
        }
        this.mIds = ArgumentsUtil.getArgValue(getLocationKey(), "ids", (String) null);
        this.mId = ArgumentsUtil.getArgValue(getLocationKey(), "id", (String) null);
        this.mSortBy = ArgumentsUtil.getArgValue(getLocationKey(), "sort_by", 12);
    }

    public void moveCameraWithOffset(double[] dArr, boolean z) {
        if (!isDestroyed() && MapUtil.isValidLocation(dArr[0], dArr[1])) {
            if (!z || !isGoogleMap()) {
                float mapZoom = (float) getMapZoom();
                this.mMapContainer.moveCamera(dArr[0] - getLatitudeOffset(), dArr[1]);
                this.mMapContainer.setZoomLevel(mapZoom);
                return;
            }
            ((GoogleMapContainer) this.mMapContainer).animateCamera(dArr[0] - getLatitudeOffset(), dArr[1], (float) getMapZoom());
        }
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.topMargin = WindowUtils.getSystemInsetsTop(rootWindowInsets);
            marginLayoutParams.bottomMargin = WindowUtils.getSystemInsetsBottom(rootWindowInsets);
            view.setLayoutParams(marginLayoutParams);
        }
        if (!PocFeatures.isEnabled(PocFeatures.GradientProtection)) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.sesl_fading_edge_bottom_height);
            int systemInsetsBottom = dimensionPixelOffset - WindowUtils.getSystemInsetsBottom(rootWindowInsets);
            if (systemInsetsBottom > 0) {
                dimensionPixelOffset = systemInsetsBottom;
            }
            this.mRecyclerView.seslSetFadingEdgeEnabled(true, 0, dimensionPixelOffset);
        }
        return windowInsets;
    }

    public boolean onBackPressed() {
        if (super.onBackPressed() || hideList()) {
            return true;
        }
        return false;
    }

    public void onClusterZoomChanged() {
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior != null && searchPicturesLocationBehavior.getState() != 5) {
            setBehaviorState(4);
        }
    }

    public void onClustersChanged(Set<ICluster<MapItem>> set) {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.startMarkerTransition(set);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior != null) {
            searchPicturesLocationBehavior.onConfigurationChanged();
        }
        updateBottomSheetMargin();
        updateBehaviorExpandedOffset();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        createMap(bundle);
    }

    public void onDataChangedOnUi() {
        ((ClusteringMapPresenterV2) this.mPresenter).notifyDataChanged(getMapData());
    }

    public void onDataRangeInserted(int i2, int i7) {
        ((ClusteringMapPresenterV2) this.mPresenter).updateClusterItems(getMapData());
        if (i2 == 3000) {
            this.mBlackboard.publish("lifecycle://onTimeLineFullSwapDone", Long.valueOf(System.currentTimeMillis()));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mMapContainer.onDestroy();
        try {
            ViewUtils.removeSelf(getMapView());
            ViewUtils.removeAllViews((ViewGroup) getMapView());
        } catch (Exception unused) {
        }
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.setOnTransitionEndListener((Runnable) null);
            this.mMarkerManagerDelegate.onDestroy();
            this.mMarkerManagerDelegate = null;
        }
        SearchPicturesLocationBehavior<View> searchPicturesLocationBehavior = this.mBehavior;
        if (searchPicturesLocationBehavior != null) {
            searchPicturesLocationBehavior.removeBottomSheetCallback(this);
            this.mBehavior = null;
        }
    }

    public void onLowMemory() {
        try {
            this.mMapContainer.onLowMemory();
        } catch (Error | Exception unused) {
        }
        super.onLowMemory();
    }

    public void onMapClicked() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_VIEW_LOCATION_SELECT_MAP);
    }

    public void onMapReady(Object obj) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "OnMapReady " + obj);
        setMapListeners();
        moveCameraToInitialLocation();
        this.mMapContainer.onClusteredMapReady();
    }

    public void onMarkerClicked(Object obj) {
        ICluster<MapItem> changeClickedMarker = changeClickedMarker(this.mMapContainer.getGalleryMarker(obj));
        if (changeClickedMarker != null) {
            SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
            if (searchMarkerManagerDelegate != null) {
                searchMarkerManagerDelegate.unsetEntryMarker();
            }
            setBehaviorState(6);
            moveCameraWithOffset(changeClickedMarker.getPosition(), true);
        }
    }

    public void onPause() {
        this.mMapContainer.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mMapContainer.onResume();
    }

    public void onSelectionModeChanged(boolean z) {
        super.onSelectionModeChanged(z);
        if (z && SheetBehaviorCompat.isClosed(this.mBehavior.getState())) {
            setBehaviorState(6);
        }
    }

    public void onSlide(float f, int i2) {
        ViewMarginUtils.setBottomPadding((View) this.mRecyclerView.getParent(), i2);
    }

    public void onStart() {
        super.onStart();
        this.mMapContainer.onStart();
    }

    public void onStateChanged(int i2) {
        if (isSelectionMode() && (SheetBehaviorCompat.isCollapsed(i2) || SheetBehaviorCompat.isHidden(i2))) {
            exitSelectionMode(true);
        }
        if (!SheetBehaviorCompat.isDragging(i2) && !SheetBehaviorCompat.isSettling(i2)) {
            invalidateOptionsMenu();
        }
    }

    public void onStop() {
        this.mMapContainer.onStop();
        super.onStop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FloatingToolbarLayout floatingToolbarLayout = this.mFloatingToolbar;
        if (floatingToolbarLayout != null) {
            floatingToolbarLayout.m(true, true);
        }
        if (GalleryMapFactory.isEmptyMap(this.mMapContainer)) {
            createMap((Bundle) null);
            ((ClusteringMapPresenterV2) this.mPresenter).onViewCreated(view);
            onMapReload();
        }
        updateBehaviorExpandedOffset();
    }

    public void reopenMapData(boolean z) {
        if (this.mIsSelectedLocationShown != z) {
            this.mIsSelectedLocationShown = z;
            hideList();
        }
        if (this.mIsSelectedLocationShown || this.mMapData != null) {
            onDataChangedOnUi();
        } else {
            initMapData();
        }
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public boolean supportViewAll() {
        return this.mSupportViewAllButton;
    }

    public void updateVisibleMarkers() {
        SearchMarkerManagerDelegate searchMarkerManagerDelegate = this.mMarkerManagerDelegate;
        if (searchMarkerManagerDelegate != null) {
            searchMarkerManagerDelegate.updateVisibleMarkers();
        }
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new BottomSheetListAdapter(this, getLocationKey(), isRealRatioSupported());
    }

    public ClusteringMapPresenterV2 createPresenter(IClusteringMapViewV2 iClusteringMapViewV2) {
        return new ClusteringMapPresenterV2(this.mBlackboard, iClusteringMapViewV2);
    }

    public void finish() {
    }
}
