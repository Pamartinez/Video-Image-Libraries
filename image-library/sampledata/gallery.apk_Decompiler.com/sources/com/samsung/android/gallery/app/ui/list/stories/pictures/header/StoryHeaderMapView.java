package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import A4.C0367b;
import A4.C0384t;
import A4.O;
import Ab.a;
import B6.b;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesHeaderView;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.MultiMarkerMapFragment;
import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderMapView extends StoryHeaderBasic implements HeaderDataListener {
    private View.OnAttachStateChangeListener mAttachStateChangeListener;
    private Bitmap mBitmapLandscape;
    private Bitmap mBitmapPortrait;
    private HeaderSimpleData mData;
    private FragmentManager mFragmentManager;
    private String mLatitudeList = "";
    private String mLongitudeList = "";
    private MultiMarkerMapFragment mMapFragment;
    FrameLayout mMapView;
    private double mMapViewFirstZoomLevel = 13.0d;
    private int mOrientation;
    private AtomicBoolean mViewAttached = new AtomicBoolean(false);

    private void clearBitmap() {
        if (this.mBitmapPortrait != null) {
            this.mBitmapPortrait = null;
        }
        if (this.mBitmapLandscape != null) {
            this.mBitmapLandscape = null;
        }
    }

    private Bitmap getBitmap() {
        if (this.mOrientation == 1) {
            return this.mBitmapPortrait;
        }
        return this.mBitmapLandscape;
    }

    private ViewGroup getMapViewRootView(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.story_pictures_map_view);
        if (viewGroup2 != null) {
            return viewGroup2;
        }
        ViewGroup viewGroup3 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        viewGroup.addView(viewGroup3);
        return viewGroup3;
    }

    private void initMapFragment() {
        View view;
        long j2;
        try {
            System.currentTimeMillis();
            if (!isMapDataEmpty()) {
                ViewGroup viewGroup = this.mRootView;
                if (viewGroup != null) {
                    if (viewGroup.findViewById(R.id.map_view) != null) {
                        MediaItem mediaItem = this.mMediaItem;
                        if (mediaItem != null) {
                            j2 = mediaItem.getFileId();
                        } else {
                            j2 = -1;
                        }
                        publishInitialLocation(j2, false, (String) Optional.ofNullable(this.mMediaItem).map(new C0384t(20)).orElse((Object) null));
                        MultiMarkerMapFragment multiMarkerMapFragment = new MultiMarkerMapFragment("location://map/filteredFromStoryPictures", this.mView.getLocationKey());
                        this.mMapFragment = multiMarkerMapFragment;
                        multiMarkerMapFragment.setMapSnapshotReadyListener(new O(10, this));
                        this.mMapFragment.setMapPlacedListener(new C0367b(8, this));
                        this.mFragmentManager.beginTransaction().replace(R.id.map_view, this.mMapFragment).commitAllowingStateLoss();
                        return;
                    }
                }
                String str = this.TAG;
                ViewGroup viewGroup2 = this.mRootView;
                if (viewGroup2 != null) {
                    view = viewGroup2.findViewById(R.id.map_view);
                } else {
                    view = null;
                }
                Log.d(str, "initMapFragment failed", viewGroup2, view);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            Log.e((CharSequence) this.TAG, "initMapFragment failed", e);
            this.mMapFragment = null;
        }
    }

    private void initMapViewIfAvailable() {
        if (isMapDataEmpty()) {
            setMapViewVisibility(false);
            return;
        }
        bindMapFragment();
        setMapViewVisibility(true);
    }

    private boolean isMapDataChanged(String[] strArr) {
        String str;
        String str2 = this.mLatitudeList;
        if ((str2 == null || str2.equals(strArr[0])) && (((str = this.mLongitudeList) == null || str.equals(strArr[1])) && ((this.mLatitudeList != null || strArr[0] == null) && (this.mLongitudeList != null || strArr[1] == null)))) {
            return false;
        }
        return true;
    }

    private boolean isMapDataEmpty() {
        if (TextUtils.isEmpty(this.mLatitudeList) || TextUtils.isEmpty(this.mLongitudeList)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewAttachedToWindow$0() {
        boolean z;
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup == null || !viewGroup.isAttachedToWindow()) {
            z = false;
        } else {
            z = true;
        }
        if (!this.mViewAttached.getAndSet(z) && z) {
            initMapViewIfAvailable();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSnapshot$3() {
        FrameLayout frameLayout = this.mMapView;
        if (frameLayout != null) {
            frameLayout.setBackground(new BitmapDrawable(this.mMapView.getContext().getResources(), getBitmap()));
        }
    }

    private void moveToMapView() {
        long j2;
        if (isMapDataEmpty()) {
            Log.e(this.TAG, "moveToMapView failed = no location");
            return;
        }
        String build = new UriBuilder("location://map/filteredFromStoryPictures").appendArg("dataKey", BlackboardUtils.readLocationKeyCurrent(this.mBlackBoard)).build();
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = -1;
        }
        publishInitialLocation(j2, true, (String) Optional.ofNullable(this.mMediaItem).map(new C0384t(21)).orElse((Object) null));
        this.mBlackBoard.post("command://MoveURL", build);
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SHOW_MAP_VIEW_STORY);
    }

    private void onMapDataUpdated(String[] strArr) {
        if (strArr != null && isMapDataChanged(strArr)) {
            clearBitmap();
            this.mLatitudeList = strArr[0];
            this.mLongitudeList = strArr[1];
            initMapViewIfAvailable();
        }
    }

    /* access modifiers changed from: private */
    public void onMapPlaced(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList != null && !arrayList.isEmpty()) {
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            StringJoiner stringJoiner2 = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                double[] dArr = (double[]) it.next();
                stringJoiner.add(String.valueOf(dArr[0]));
                stringJoiner2.add(String.valueOf(dArr[1]));
            }
        }
        MultiMarkerMapFragment multiMarkerMapFragment = this.mMapFragment;
        if (multiMarkerMapFragment != null) {
            this.mMapViewFirstZoomLevel = (double) multiMarkerMapFragment.getZoomLevelWithMarker();
        }
    }

    /* access modifiers changed from: private */
    public void onViewAttachedToWindow(View view) {
        String str = this.TAG;
        Log.v(str, "onViewAttachedToWindow" + this);
        ThreadUtil.postOnUiThreadDelayed(new b(this, 1), 10);
    }

    /* access modifiers changed from: private */
    public void onViewDetachedFromWindow(View view) {
        Log.v(this.TAG, "onViewDetachedFromWindow");
        this.mViewAttached.set(false);
    }

    private void publishInitialLocation(long j2, boolean z, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitudes", this.mLatitudeList);
            jSONObject.put("longitudes", this.mLongitudeList);
            jSONObject.put("entryItem", j2);
            jSONObject.put(FileApiContract.Parameter.PATH, str);
            if (z) {
                double d = this.mMapViewFirstZoomLevel;
                if (d == -1.0d) {
                    d = 13.0d;
                }
                jSONObject.put("mapZoomLevel", d);
            }
            this.mBlackBoard.publish("data://user/map/InitialLocation", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void removeMapFragment() {
        MultiMarkerMapFragment multiMarkerMapFragment = this.mMapFragment;
        if (multiMarkerMapFragment != null) {
            try {
                this.mMapViewFirstZoomLevel = (double) multiMarkerMapFragment.getZoomLevelWithMarker();
                this.mFragmentManager.beginTransaction().remove(this.mMapFragment).commitAllowingStateLoss();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "removeMapFragment failed", (Throwable) e);
            }
            this.mMapFragment = null;
        }
    }

    private void setBitmap(Bitmap bitmap) {
        if (this.mOrientation == 1) {
            this.mBitmapPortrait = bitmap;
        } else {
            this.mBitmapLandscape = bitmap;
        }
    }

    private void setMapViewVisibility(boolean z) {
        int i2;
        ViewGroup viewGroup = this.mRootView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(viewGroup, i2);
    }

    /* access modifiers changed from: private */
    public void snapshotReady(Bitmap bitmap) {
        setBitmap(bitmap);
        updateSnapshot();
        Log.v(this.TAG, "snapshotReady");
    }

    private void updateSnapshot() {
        ThreadUtil.postOnUiThread(new b(this, 0));
        removeMapFragment();
    }

    public boolean accept(HeaderType headerType) {
        if (headerType == HeaderType.MAP) {
            return true;
        }
        return false;
    }

    public void bindMapFragment() {
        boolean z;
        if (this.mMediaItem == null || !this.mViewAttached.get()) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("bindMapFragment ignored {item=");
            if (this.mMediaItem != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            sb2.append(",attached=");
            sb2.append(this.mViewAttached.get());
            sb2.append("}");
            Log.d(str, sb2.toString());
        } else if (this.mMapFragment == null && getBitmap() == null) {
            IStoryPicturesHeaderView iStoryPicturesHeaderView = this.mView;
            if (iStoryPicturesHeaderView == null || !iStoryPicturesHeaderView.isLayoutAnimDone()) {
                Log.v(this.TAG, "wait next intMapFragment");
            } else {
                initMapFragment();
            }
        } else if (getBitmap() != null) {
            updateSnapshot();
        }
    }

    public void bindView(ViewGroup viewGroup) {
        if (this.mMediaItem == null) {
            Log.e(this.TAG, "bind failed. null MediaItem");
            return;
        }
        ViewGroup mapViewRootView = getMapViewRootView(viewGroup);
        this.mRootView = mapViewRootView;
        FrameLayout frameLayout = (FrameLayout) mapViewRootView.findViewById(R.id.map_view);
        this.mMapView = frameLayout;
        frameLayout.setOnClickListener(new a(4, this));
        AnonymousClass1 r32 = new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                StoryHeaderMapView.this.onViewAttachedToWindow(view);
            }

            public void onViewDetachedFromWindow(View view) {
                StoryHeaderMapView.this.onViewDetachedFromWindow(view);
            }
        };
        this.mAttachStateChangeListener = r32;
        this.mRootView.addOnAttachStateChangeListener(r32);
        if (this.mRootView.isAttachedToWindow()) {
            onViewAttachedToWindow(this.mRootView);
        }
    }

    public void clear() {
        this.mData.unregister(this);
        clearBitmap();
        removeMapFragment();
        this.mMapView.setBackground((Drawable) null);
        this.mMapView = null;
        this.mRootView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        this.mAttachStateChangeListener = null;
        ViewUtils.removeSelf(this.mRootView);
        this.mRootView = null;
    }

    public int getContainerResId() {
        return R.id.stories_pictures_map_view_container;
    }

    public int getLayoutId() {
        return R.layout.story_pictures_header_map_view_layout;
    }

    public void handleOrientationChange(int i2) {
        String str = this.TAG;
        Log.v(str, "handleOrientationChange {" + this.mOrientation + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        this.mOrientation = i2;
        removeMapFragment();
        if (!this.mViewAttached.get()) {
            return;
        }
        if (getBitmap() == null) {
            initMapViewIfAvailable();
        } else {
            updateSnapshot();
        }
    }

    public void init(IStoryPicturesHeaderView iStoryPicturesHeaderView, ViewGroup viewGroup, MediaItem mediaItem, HeaderSimpleData headerSimpleData) {
        super.init(iStoryPicturesHeaderView, viewGroup, mediaItem, headerSimpleData);
        this.mFragmentManager = this.mView.getChildFragmentManager();
        this.mOrientation = viewGroup.getResources().getConfiguration().orientation;
        this.mData = headerSimpleData;
        headerSimpleData.register(this);
    }

    public void layoutAnimationDone() {
        initMapViewIfAvailable();
    }

    public void loadData(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void notifyDataChanged() {
        onMapDataUpdated(this.mData.getLatLongList());
    }

    public void onMapViewClicked(View view) {
        FrameLayout frameLayout = this.mMapView;
        if (frameLayout == null || !frameLayout.isEnabled()) {
            Log.e(this.TAG, "onMapViewClicked destroyed or disabled");
            return;
        }
        if (view != null) {
            view.playSoundEffect(0);
        }
        moveToMapView();
    }

    public void pause() {
        removeMapFragment();
    }

    public void setEnabled(boolean z) {
        float f;
        FrameLayout frameLayout = this.mMapView;
        if (frameLayout != null) {
            frameLayout.setEnabled(z);
            FrameLayout frameLayout2 = this.mMapView;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            frameLayout2.setAlpha(f);
        }
    }

    public boolean supported(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_LOCATION) || !PreferenceFeatures.OneUi40.SUPPORT_STORY_PICTURES_MAP || Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            return false;
        }
        int storyCategoryType = MediaItemStory.getStoryCategoryType(mediaItem);
        if (storyCategoryType == StoryCategoryType.TRIP.ordinal() || storyCategoryType == StoryCategoryType.SPECIAL_DAY.ordinal() || MediaItemStory.getStoryType(mediaItem) == StoryType.RESTAURANT.getValue() || MediaItemStory.getStoryType(mediaItem) == StoryType.COLLECTION_SCENERY_STORY.getValue()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + hashCode() + " {" + this.mViewAttached.get() + "}";
    }
}
