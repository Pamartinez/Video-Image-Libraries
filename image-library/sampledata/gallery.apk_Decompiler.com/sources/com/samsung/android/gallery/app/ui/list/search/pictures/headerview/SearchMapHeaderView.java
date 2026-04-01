package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import com.samsung.android.gallery.app.ui.map.staticmarker.StaticMarkerSearchMapFragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
import z5.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMapHeaderView extends SearchCountHeaderView {
    private static final boolean FEATURE_IS_CHINESE = Features.isEnabled(Features.IS_CHINESE_DEVICE);
    private Bitmap mBitmapLandscape;
    private Bitmap mBitmapPortrait;
    private final Blackboard mBlackboard;
    private final FragmentManager mFragmentManager;
    /* access modifiers changed from: private */
    public boolean mIsViewAttached;
    private StaticMarkerSearchMapFragment mMapFragment;
    FrameLayout mMapView;
    private int mOldMediaItemCode = -1;
    private final AtomicBoolean mOnceInit = new AtomicBoolean(false);
    private int mOrientation;

    public SearchMapHeaderView(ViewGroup viewGroup, FragmentManager fragmentManager, Blackboard blackboard) {
        super(viewGroup);
        this.mBlackboard = blackboard;
        this.mOrientation = viewGroup.getResources().getConfiguration().orientation;
        this.mFragmentManager = fragmentManager;
        this.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                SearchMapHeaderView.this.mIsViewAttached = true;
                if (SearchMapHeaderView.this.mMediaItem != null) {
                    SearchMapHeaderView.this.loadMapFragment();
                }
            }

            public void onViewDetachedFromWindow(View view) {
                SearchMapHeaderView.this.mIsViewAttached = false;
            }
        });
    }

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

    /* access modifiers changed from: private */
    public void initMapFragment() {
        if (!this.mIsViewAttached) {
            Log.sw(this.TAG, "initMapFragment skip. view detached");
            return;
        }
        ViewGroup viewGroup = this.mRootView;
        View view = null;
        if (viewGroup == null || viewGroup.findViewById(R.id.map_view) == null) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("initMapFragment failed ");
            sb2.append(this.mRootView);
            sb2.append(NumericEnum.SEP);
            ViewGroup viewGroup2 = this.mRootView;
            if (viewGroup2 != null) {
                view = viewGroup2.findViewById(R.id.map_view);
            }
            sb2.append(view);
            Log.sw(str, sb2.toString());
            return;
        }
        try {
            System.currentTimeMillis();
            removeMapFragment();
            StaticMarkerSearchMapFragment staticMarkerSearchMapFragment = new StaticMarkerSearchMapFragment();
            this.mMapFragment = staticMarkerSearchMapFragment;
            staticMarkerSearchMapFragment.setMapSnapshotReadyListener(new l(this));
            this.mMapFragment.setLocation(this.mMediaItem.getLatitude(), this.mMediaItem.getLongitude());
            this.mMapFragment.setMediaItem(this.mMediaItem);
            this.mFragmentManager.beginTransaction().replace(R.id.map_view, this.mMapFragment).commitAllowingStateLoss();
        } catch (IllegalArgumentException | IllegalStateException e) {
            String str2 = this.TAG;
            Log.se(str2, "initMapFragment failed" + e);
            this.mMapFragment = null;
        }
    }

    private void initMapFragmentDelayed() {
        if (!this.mOnceInit.getAndSet(true)) {
            initMapFragment();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new x7.l(11, this), 300);
        }
    }

    private boolean isMapLoading() {
        if (this.mMapFragment != null) {
            return true;
        }
        return false;
    }

    private boolean isMediaItemChanged(MediaItem mediaItem) {
        int i2 = this.mOldMediaItemCode;
        if (i2 == -1 || mediaItem == null || i2 == mediaItem.hashCode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void loadMapFragment() {
        boolean z = false;
        if (this.mMediaItem == null || !this.mIsViewAttached) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("resume failed {item=");
            if (this.mMediaItem != null) {
                z = true;
            }
            sb2.append(z);
            sb2.append(",attached=");
            sb2.append(this.mIsViewAttached);
            sb2.append("}");
            Log.se(str, sb2.toString());
            return;
        }
        String str2 = this.TAG;
        StringBuilder sb3 = new StringBuilder("resume {");
        sb3.append(this.mOnceInit);
        sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (getBitmap() != null) {
            z = true;
        }
        sb3.append(z);
        sb3.append("}");
        Log.s(str2, sb3.toString());
        if (this.mMapFragment == null && getBitmap() == null) {
            initMapFragmentDelayed();
        } else if (!FEATURE_IS_CHINESE) {
            if (isMediaItemChanged(this.mMediaItem)) {
                Log.s(this.TAG, "MediaItem is changed");
                clearBitmap();
                initMapFragment();
            } else if (getBitmap() != null) {
                updateMapView();
            }
        }
        this.mOldMediaItemCode = this.mMediaItem.hashCode();
    }

    private void removeMapFragment() {
        StaticMarkerSearchMapFragment staticMarkerSearchMapFragment = this.mMapFragment;
        if (staticMarkerSearchMapFragment != null) {
            try {
                staticMarkerSearchMapFragment.keepMarker();
                this.mMapFragment.pauseContainer();
                this.mFragmentManager.beginTransaction().remove(this.mMapFragment).commitAllowingStateLoss();
            } catch (Exception e) {
                String str = this.TAG;
                Log.se(str, "removeMapFragment failed" + e);
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

    private void updateMapView() {
        FrameLayout frameLayout = this.mMapView;
        if (frameLayout != null) {
            frameLayout.setBackground(new BitmapDrawable(this.mMapView.getContext().getResources(), getBitmap()));
        }
        removeMapFragment();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMapView = (FrameLayout) view.findViewById(R.id.map_view);
    }

    public int getLayoutId() {
        return R.layout.recycler_item_pictures_header_map_layout;
    }

    public void handleOrientationChange(int i2) {
        String str = this.TAG;
        Log.s(str, "handleOrientationChange {" + this.mOrientation + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        this.mOrientation = i2;
        if (!FEATURE_IS_CHINESE) {
            removeMapFragment();
            if (!this.mIsViewAttached) {
                return;
            }
            if (getBitmap() == null) {
                initMapFragment();
            } else {
                updateMapView();
            }
        }
    }

    public void handleResolutionChanged() {
        Log.s(this.TAG, "handleResolutionChanged");
        if (!FEATURE_IS_CHINESE && !isMapLoading()) {
            removeMapFragment();
            clearBitmap();
            initMapFragment();
        }
    }

    public void initHeaderItem() {
        loadMapFragment();
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        return !ViewUtils.isTouchedOnView(this.mMapView, motionEvent);
    }

    public void onDestroyView() {
        removeMapFragment();
    }

    public boolean onHeaderItemClicked() {
        boolean z;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onHeaderItemClicked[");
        if (this.mBlackboard != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("]");
        Log.s(str, sb2.toString());
        if (this.mBlackboard == null) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitude", this.mMediaItem.getLatitude());
            jSONObject.put("longitude", this.mMediaItem.getLongitude());
            jSONObject.put("entryItem", this.mMediaItem.getFileId());
            jSONObject.put(FileApiContract.Parameter.PATH, this.mMediaItem.getPath());
            this.mBlackboard.publish("data://user/map/InitialLocation", jSONObject);
            this.mBlackboard.post("command://MoveURL", "location://map");
            return true;
        } catch (Exception e) {
            Log.se(this.TAG, e.getMessage());
            return false;
        }
    }

    public void recycle() {
        onDestroyView();
    }

    public void setEnabled(boolean z) {
        float f;
        FrameLayout frameLayout = this.mMapView;
        if (frameLayout != null) {
            if (z) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            frameLayout.setAlpha(f);
        }
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.se(this.TAG, "setHeaderItem failed. null MediaItem");
            return false;
        }
        this.mMediaItem = mediaItem;
        return true;
    }

    public void setItemCount(int i2) {
        int i7;
        super.setItemCount(i2);
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            FrameLayout frameLayout = this.mMapView;
            if (i2 > 0) {
                i7 = 0;
            } else {
                i7 = 8;
            }
            frameLayout.setVisibility(i7);
        }
    }

    public void showCountHeaderOnly(boolean z) {
        ViewUtils.setVisibleOrGone(this.mMapView, !z);
    }

    public void snapshotReady(Bitmap bitmap) {
        setBitmap(bitmap);
        updateMapView();
    }
}
