package com.samsung.android.gallery.app.ui.list.search;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.LocationItemAdapterV2;
import com.samsung.android.gallery.app.ui.map.search.SearchMapFragmentContainerView;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.module.data.LocationValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryLocation2CardHolder extends CategoryCardHolderV2 {
    protected static long sLastCapturedId;
    protected static long sLastCapturedTime;
    protected static long sLastQueryTime;
    /* access modifiers changed from: private */
    public Runnable mAttachMapRunnable = new g(this, 0);
    protected LocationValue mLatestLocationInfo = new LocationValue();
    private FrameLayout mMapContainer;
    private ImageView mMapImageView;
    private SearchMapFragmentContainerView mSearchMapView;
    protected ISearchView mView;
    private final View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View view) {
            CategoryLocation2CardHolder.this.mAttachMapRunnable.run();
        }

        public void onViewDetachedFromWindow(View view) {
            ThreadUtil.removeCallbackOnUiThread(CategoryLocation2CardHolder.this.mAttachMapRunnable);
        }
    };

    public CategoryLocation2CardHolder(View view, int i2) {
        super(view, i2);
        String loadString = GalleryPreference.getInstanceCache().loadString("search_map_latest_loc", (String) null);
        if (loadString != null) {
            this.mLatestLocationInfo = LocationValue.parse(loadString);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: attachMapFragment */
    public void lambda$new$8() {
        boolean z;
        if (!isAvailableAddMapView()) {
            String str = this.TAG;
            ISearchView iSearchView = this.mView;
            boolean z3 = false;
            if (iSearchView == null || !iSearchView.isActive()) {
                z = false;
            } else {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            Boolean valueOf2 = Boolean.valueOf(this.mMapContainer.isAttachedToWindow());
            if (this.mSearchMapView != null) {
                z3 = true;
            }
            Log.w((CharSequence) str, "AvailableAddMapView(failed) : ", valueOf, valueOf2, Boolean.valueOf(z3));
        } else if (this.mView.getListView().isScrollIdle()) {
            this.mSearchMapView.attachMapFragment();
        } else {
            ThreadUtil.postOnUiThreadDelayed(this.mAttachMapRunnable, 100);
        }
    }

    private boolean isAvailableAddMapView() {
        ISearchView iSearchView = this.mView;
        if (iSearchView == null || !iSearchView.isActive() || !this.mMapContainer.isAttachedToWindow() || this.mSearchMapView == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addMapView$6(LocationValue locationValue, Bitmap bitmap, byte[] bArr) {
        Log.d(this.TAG, "onSnapShot#caching", locationValue, Logger.getSimpleName((Object) bitmap));
        BitmapCache.putDiskCache(7, bArr, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addMapView$7(LocationValue locationValue, Bitmap bitmap) {
        if (bitmap != null) {
            byte[] cacheKey = getCacheKey(locationValue);
            long currentTimeMillis = System.currentTimeMillis();
            if (sLastCapturedId != locationValue.id || currentTimeMillis - sLastCapturedTime > 3600000 || !CacheManager.getInstance().containsKey(7, cacheKey)) {
                sLastCapturedTime = currentTimeMillis;
                sLastCapturedId = locationValue.id;
                ThreadUtil.postOnBgThread(new k(this, locationValue, bitmap, cacheKey));
            }
        }
        if (ViewUtils.isVisible(this.mMapImageView)) {
            SimpleAnimator.alphaOut(this.mMapImageView, 100);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindCachedMapImage$4(Bitmap bitmap) {
        this.mMapImageView.setImageBitmap(bitmap);
        ViewUtils.setVisibleOrGone(this.mMapImageView, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindLocationInfo$1(MediaData mediaData, SearchMapFragmentContainerView searchMapFragmentContainerView) {
        searchMapFragmentContainerView.bindData(mediaData, this.mLatestLocationInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindLocationInfo$2(MediaData mediaData) {
        Optional.ofNullable(this.mSearchMapView).ifPresent(new f(0, this, mediaData));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadLatestLocationInfo$0(MediaData mediaData) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        sLastQueryTime = currentTimeMillis;
        Bundle latestLocationInfo = new LocationApi().getLatestLocationInfo(this.mLatestLocationInfo.computeTakenTime());
        if (latestLocationInfo != null) {
            LocationValue parse = LocationValue.parse(latestLocationInfo);
            if (!parse.equals(this.mLatestLocationInfo)) {
                GalleryPreference.getInstanceCache().saveState("search_map_latest_loc", parse.value());
                this.mLatestLocationInfo = parse;
                bindLocationInfo(mediaData);
            }
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("saveLatestLoc");
            if (parse != this.mLatestLocationInfo) {
                str = "#skip";
            } else {
                str = " {" + parse + "}";
            }
            Log.d(str2, C0086a.j(currentTimeMillis, str, " +", sb2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setClickListeners$5(View view) {
        SearchMapFragmentContainerView searchMapFragmentContainerView = this.mSearchMapView;
        if (searchMapFragmentContainerView != null) {
            searchMapFragmentContainerView.moveToCurrentLocation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMapCacheImage$3() {
        byte[] cacheKey = getCacheKey(this.mLatestLocationInfo);
        Bitmap diskCache = BitmapCache.getDiskCache(7, cacheKey);
        if (diskCache != null) {
            this.mView.getBlackboard().publish("data:///SearchMapCacheBitmap/".concat(new String(cacheKey)), diskCache.copy(diskCache.getConfig(), true));
            bindCachedMapImage(diskCache);
        }
    }

    private void loadLatestLocationInfo(MediaData mediaData) {
        boolean z;
        String str;
        if (System.currentTimeMillis() - sLastQueryTime < 2000) {
            z = true;
        } else {
            z = false;
        }
        String str2 = this.TAG;
        if (z) {
            str = "#skip";
        } else {
            str = "";
        }
        Log.d(str2, "loadLatestLoc".concat(str), this.mLatestLocationInfo);
        if (!z) {
            SimpleThreadPool.getInstance().execute(new i(this, mediaData, 0));
        }
    }

    /* access modifiers changed from: private */
    public void moveMap(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mSearchMapView != null && !ViewUtils.isVisible(this.mMapImageView)) {
            this.mSearchMapView.moveTo(mediaItem);
        }
    }

    private void setClickListeners(View view) {
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            View findViewById = view.findViewById(R.id.current_location_button);
            ViewUtils.setVisibility(findViewById, 0);
            ViewUtils.setOnClickListener(findViewById, new c(1, this));
        }
    }

    public void addMapView(SearchMapFragmentContainerView searchMapFragmentContainerView, MediaData mediaData) {
        LocationValue locationValue = this.mLatestLocationInfo;
        this.mSearchMapView = searchMapFragmentContainerView;
        searchMapFragmentContainerView.setTouchBlock(PickerUtil.isPickerMode(this.mView.getBlackboard()));
        this.mSearchMapView.bindData(mediaData, locationValue);
        this.mSearchMapView.setOnSnapShotReadyListener(new j(this, locationValue));
        ViewUtils.removeSelf(this.mSearchMapView);
        this.mMapContainer.addView(this.mSearchMapView, 0);
        if (this.mSearchMapView.isMapFragmentAdded()) {
            return;
        }
        if (this.mMapContainer.isAttachedToWindow()) {
            this.mAttachMapRunnable.run();
        } else {
            this.mMapContainer.addOnAttachStateChangeListener(this.onAttachStateChangeListener);
        }
    }

    public void bind(ISearchView iSearchView, MediaData mediaData) {
        this.mView = iSearchView;
        bindBasicInfo(iSearchView, mediaData);
        bindListView(iSearchView, mediaData.getLocationKey());
        loadLatestMap(mediaData);
        ViewUtils.setViewEnabled(this.mMapContainer, !PickerUtil.isPickerMode(iSearchView.getBlackboard()));
    }

    public void bindCachedMapImage(Bitmap bitmap) {
        if (bitmap != null) {
            ThreadUtil.runOnUiThread(new i(this, bitmap, 2));
        }
    }

    public void bindLocationInfo(MediaData mediaData) {
        ThreadUtil.postOnUiThread(new i(this, mediaData, 1));
    }

    public void bindMapView(ISearchView iSearchView, SearchMapFragmentContainerView searchMapFragmentContainerView, MediaData mediaData) {
        addMapView(searchMapFragmentContainerView, mediaData);
    }

    public void bindView(View view) {
        super.bindView(view);
        if (supportInteractiveMap()) {
            this.mMapContainer = (FrameLayout) view.findViewById(R.id.search_map_view_container);
            this.mMapImageView = (ImageView) view.findViewById(R.id.map_image);
            if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                ViewMarginUtils.setTopMargin(this.mMapContainer, getDimensionPixelOffset(R.dimen.search_card_padding_top_61));
            }
            setClickListeners(view);
        }
    }

    public void createCategoryPropertyHelper(String str) {
        if (this.mPropertyHelper == null) {
            this.mPropertyHelper = CategoryPropertyHelper.getInstance("location://search/fileList/Category/LocationMap", false);
        }
    }

    public CategoryItemAdapterV2 createItemAdapter(ISearchView iSearchView, String str) {
        LocationItemAdapterV2 locationItemAdapterV2 = new LocationItemAdapterV2(iSearchView, str, this.mListView, this.mPropertyHelper);
        locationItemAdapterV2.setOnLocationTitleClickedListener(new h(this, 0));
        return locationItemAdapterV2;
    }

    public byte[] getCacheKey(LocationValue locationValue) {
        if (locationValue == null) {
            locationValue = new LocationValue();
        }
        View decorView = ((Activity) this.itemView.getContext()).getWindow().getDecorView();
        return MapUtil.getSearchDiskCacheKey(locationValue.latitude, locationValue.longitude, decorView.getWidth(), decorView.getHeight()).getBytes();
    }

    public void initListView(ISearchView iSearchView, String str) {
        CategoryItemAdapterV2 categoryItemAdapterV2 = this.mItemAdapter;
        if (categoryItemAdapterV2 != null) {
            categoryItemAdapterV2.notifyDataChanged((Runnable) null);
        } else {
            initializeAdapter(iSearchView, str);
        }
    }

    public void initializeAdapter(ISearchView iSearchView, String str) {
        if (this.mListView != null) {
            if (!isMediaDataAvailable(iSearchView, str)) {
                String str2 = this.TAG;
                Log.sw(str2, "bindListView : mediaData is not available " + str);
                return;
            }
            CategoryItemAdapterV2 createItemAdapter = createItemAdapter(iSearchView, str);
            this.mItemAdapter = createItemAdapter;
            this.mListView.setAdapter(createItemAdapter);
            this.mListView.setLayoutManager(new LinearLayoutManager(iSearchView.getContext(), 0, false));
        }
    }

    public void loadLatestMap(MediaData mediaData) {
        loadLatestLocationInfo(mediaData);
        setMapCacheImage();
    }

    public void recycle() {
        super.recycle();
        FrameLayout frameLayout = this.mMapContainer;
        if (frameLayout != null) {
            frameLayout.removeOnAttachStateChangeListener(this.onAttachStateChangeListener);
        }
        this.mSearchMapView = null;
    }

    public void setMapCacheImage() {
        if (this.mSearchMapView == null) {
            Bitmap bitmap = (Bitmap) this.mView.getBlackboard().read("data:///SearchMapCacheBitmap/".concat(new String(getCacheKey(this.mLatestLocationInfo))));
            if (bitmap != null) {
                bindCachedMapImage(bitmap);
                return;
            }
            this.mView.getBlackboard().eraseWildNum("data:///SearchMapCacheBitmap");
            SimpleThreadPool.getInstance().execute(new g(this, 1));
        }
    }

    public boolean supportInteractiveMap() {
        return true;
    }
}
