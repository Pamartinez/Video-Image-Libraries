package com.samsung.android.gallery.app.ui.list.albums.pictures;

import A4.B;
import A4.C0368c;
import A4.C0369d;
import A4.C0385u;
import A4.L;
import Bb.l;
import C4.i;
import I4.b;
import M4.a;
import M4.c;
import M4.d;
import M4.e;
import M4.f;
import M4.g;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumsPaneSlideAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterDelegate;
import com.samsung.android.gallery.app.ui.list.albums.pictures.header.AlbumPicturesHeaderView;
import com.samsung.android.gallery.app.ui.list.dragdrop.SplitDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.appbar.BlurCustomCover;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.effects.ListProtectionGradient;
import com.samsung.android.gallery.widget.effects.ListProtectionGradientImpl;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GallerySwipeView;
import com.samsung.android.gallery.widget.listview.SimpleGestureListener;
import com.samsung.android.gallery.widget.listview.noitem.GalleryListNoItemView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPicturesFragment<V extends IAlbumPicturesView, P extends AlbumPicturesPresenter<?>> extends PicturesFragment<V, P> implements IAlbumPicturesView, AlbumPicturesLayoutManager.SplitLayoutListener, IAlbumsPaneSlideAnimationManager.OnSlideAnimationListener, SplitDragAndDropDelegate.SplitHandler {
    private int mAlbumCount;
    View mAlbumList;
    private AlbumsPaneFragment<?, ?> mAlbumsPane;
    protected View mCreateButton;
    private Runnable mCreateViewPoolExtras;
    private NoItemView mEmptyViewText;
    private AlbumPicturesHeaderView mHeaderView;
    private boolean mIsAlbumChanged;
    boolean mIsRtl;
    protected boolean mIsSplitBlocked;
    private boolean mIsSplitMode;
    /* access modifiers changed from: private */
    public AlbumPicturesLayoutManager mLayoutManager;
    private final ScreenShotFilterDelegate mScreenShotFilterDelegate;
    private Runnable mShowSplitAnimation;
    private final SimpleGestureListener mSimpleGestureListener;
    private IAlbumsPaneSlideAnimationManager mSlideAnimationManager;
    private int mVerticalOffset = 0;

    public AlbumPicturesFragment() {
        ScreenShotFilterDelegate screenShotFilterDelegate;
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            screenShotFilterDelegate = new ScreenShotFilterDelegate(this);
        } else {
            screenShotFilterDelegate = null;
        }
        this.mScreenShotFilterDelegate = screenShotFilterDelegate;
        this.mSimpleGestureListener = new SimpleGestureListener() {
            private boolean allowSwipe(MotionEvent motionEvent) {
                if (AlbumPicturesFragment.this.isSplitBlocked() || AlbumPicturesFragment.this.isSelectionMode() || (AlbumPicturesFragment.this.isDexMode() && AlbumPicturesFragment.this.mListAdapter != null && AlbumPicturesFragment.this.mListAdapter.isOnKeyCombination() && motionEvent.getToolType(0) == 3)) {
                    return false;
                }
                return true;
            }

            public void onNoItemSwipeLeft(MotionEvent motionEvent, MotionEvent motionEvent2) {
                if (allowSwipe(motionEvent)) {
                    if ((AlbumPicturesFragment.this.isSplitMode() && !AlbumPicturesFragment.this.mIsRtl) || (!AlbumPicturesFragment.this.isSplitMode() && AlbumPicturesFragment.this.mIsRtl)) {
                        AlbumPicturesFragment.this.showNoItemViewAnimation();
                    }
                }
            }

            public void onNoItemSwipeRight(MotionEvent motionEvent, MotionEvent motionEvent2) {
                if (allowSwipe(motionEvent)) {
                    if ((!AlbumPicturesFragment.this.isSplitMode() && !AlbumPicturesFragment.this.mIsRtl) || (AlbumPicturesFragment.this.isSplitMode() && AlbumPicturesFragment.this.mIsRtl)) {
                        AlbumPicturesFragment.this.showNoItemViewAnimation();
                    }
                }
            }

            public void onSwipeLeft(MotionEvent motionEvent, MotionEvent motionEvent2) {
                if (allowSwipe(motionEvent)) {
                    if (AlbumPicturesFragment.this.isSplitMode()) {
                        AlbumPicturesFragment albumPicturesFragment = AlbumPicturesFragment.this;
                        if (!albumPicturesFragment.mIsRtl) {
                            albumPicturesFragment.hideSplitAnimation();
                            return;
                        }
                    }
                    if (!AlbumPicturesFragment.this.isSplitMode()) {
                        AlbumPicturesFragment albumPicturesFragment2 = AlbumPicturesFragment.this;
                        if (albumPicturesFragment2.mIsRtl) {
                            albumPicturesFragment2.showSplitAnimation();
                        }
                    }
                }
            }

            public void onSwipeRight(MotionEvent motionEvent, MotionEvent motionEvent2) {
                if (allowSwipe(motionEvent)) {
                    if (!AlbumPicturesFragment.this.isSplitMode()) {
                        AlbumPicturesFragment albumPicturesFragment = AlbumPicturesFragment.this;
                        if (!albumPicturesFragment.mIsRtl) {
                            albumPicturesFragment.showSplitAnimation();
                            return;
                        }
                    }
                    if (AlbumPicturesFragment.this.isSplitMode()) {
                        AlbumPicturesFragment albumPicturesFragment2 = AlbumPicturesFragment.this;
                        if (albumPicturesFragment2.mIsRtl) {
                            albumPicturesFragment2.hideSplitAnimation();
                        }
                    }
                }
            }
        };
        this.mUesDataCollector = true;
    }

    /* access modifiers changed from: private */
    public void createViewPoolExtras() {
        this.mCreateViewPoolExtras = null;
        GalleryListView listView = getListView();
        if (!isDestroyed() && listView != null) {
            int maxMonthXsDataPoolSize = getMaxMonthXsDataPoolSize() - this.mViewPool.getRecycledViewCount(3);
            Log.d(this.TAG, "createViewPoolMonthEx", Integer.valueOf(maxMonthXsDataPoolSize));
            if (maxMonthXsDataPoolSize > 0) {
                createViewPool(maxMonthXsDataPoolSize, 3);
            }
        }
    }

    private void disableSplitMode() {
        if (this.mIsSplitMode && this.mAlbumList != null) {
            resetSlideAnimation();
            moveAlbumListToClosedPosition();
            this.mIsSplitMode = false;
            setSplitModeToAlbumPane();
        }
    }

    private void expandAppbar() {
        if (!hasCustomCover()) {
            return;
        }
        if (isPortrait() || isTabletDpi()) {
            Optional.ofNullable(this.mAppBarLayout).ifPresent(new b(25));
        }
    }

    private void inflateHeaderView() {
        if (this.mHeaderView == null) {
            AlbumPicturesHeaderView albumPicturesHeaderView = new AlbumPicturesHeaderView(getEventContext());
            this.mHeaderView = albumPicturesHeaderView;
            albumPicturesHeaderView.findViewById(R.id.close_button).setOnClickListener(new c(this, 1));
            if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
                this.mScreenShotFilterDelegate.bindView(this.mHeaderView);
            }
        }
    }

    /* access modifiers changed from: private */
    public void initAlbumListPosition() {
        if (!this.mIsSplitMode) {
            Optional.ofNullable(this.mAlbumList).ifPresent(new a(this, 7));
        }
    }

    private void initSplitMode() {
        boolean z;
        if (isSplitBlocked() || !loadSplitModeFromPreference()) {
            z = false;
        } else {
            z = true;
        }
        this.mIsSplitMode = z;
        if (z) {
            this.mAlbumList.setVisibility(0);
        } else if (this.mAlbumList.getWidth() != 0) {
            initAlbumListPosition();
        } else {
            this.mAlbumList.setVisibility(4);
            this.mAlbumList.post(new M4.b(this, 0));
        }
    }

    private boolean isAlbumPaneDataPrepared() {
        AlbumsPaneFragment<?, ?> albumsPaneFragment = this.mAlbumsPane;
        if (albumsPaneFragment == null || albumsPaneFragment.getAdapter() == null) {
            return false;
        }
        return true;
    }

    private boolean isAppbarDisabled() {
        if (!this.mIsSplitBlocked || !ArgumentsUtil.getArgValue(getLocationKey(), "album_split_blocked", false)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isAppbarScrolling() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout == null || !galleryAppBarLayout.isPartiallyVisible() || this.mAppBarLayout.getTotalScrollRange() == this.mAppBarLayout.getVisibleHeight()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isHeaderViewScrolling() {
        AlbumPicturesHeaderView albumPicturesHeaderView = this.mHeaderView;
        if (albumPicturesHeaderView != null && albumPicturesHeaderView.isListViewScrolling()) {
            return true;
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER || !this.mScreenShotFilterDelegate.isListViewScrolling()) {
            return false;
        }
        return true;
    }

    private boolean isMxVirtualAlbum() {
        return LocationKey.isMxVirtualAlbum(getLocationKey());
    }

    private boolean isSplitDisabled() {
        if (!Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT) || Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT_SPLIT_MODE)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$adjustToolbarLayout$38(GalleryToolbar galleryToolbar) {
        ViewParent parent = galleryToolbar.getParent();
        if (parent instanceof FloatingToolbarLayout) {
            ((CoordinatorLayout.LayoutParams) ((FloatingToolbarLayout) parent).getLayoutParams()).anchorGravity = 48;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeAlbum$35(String str) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.reInit(str);
        } else {
            Log.w(this.TAG, "skip changeAlbum");
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8521));
        BlackboardUtils.publishDataRequestForce(this.mBlackboard, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createProtectionGradient$27() {
        return !isLandscape();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleOrientationChange$21(int i2) {
        Optional.ofNullable(this.mCustomCoverHolder).ifPresent(new C0369d(i2, 7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleOrientationChange$23(AlbumPicturesHeaderView albumPicturesHeaderView) {
        albumPicturesHeaderView.handleOrientationChange(getEventContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateHeaderView$33(View view) {
        ((AlbumPicturesPresenter) this.mPresenter).onTipCardCloseClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAlbumListPosition$26(View view) {
        moveAlbumListToClosedPosition();
        view.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeEmptyView$2(GalleryListView galleryListView) {
        galleryListView.setEmptyView(this.mEmptyView);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onChangeAlbum$29(GalleryListView galleryListView) {
        galleryListView.scrollToPosition(0);
        galleryListView.resetDefaultMaxScroll();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onChangeAlbum$30(String str) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.reInit(str);
        } else {
            Log.w(this.TAG, "skip onChangeAlbum");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$24() {
        ((AlbumPicturesPresenter) this.mPresenter).updateToolbar(getToolbar());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEmptyViewVisibilityChanged$3(View view) {
        ((AlbumPicturesPresenter) this.mPresenter).addItemToAlbum();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$19(CustomCover customCover) {
        customCover.updateViewLayout(getResources().getConfiguration().orientation);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onSlideEnd$13(GalleryListView galleryListView) {
        galleryListView.updateGoToTopPosition();
        galleryListView.setGoToTopVisibility(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshEmptyView$4(NoItemView noItemView) {
        noItemView.setVisibility(0);
        if (((AlbumPicturesPresenter) this.mPresenter).isRecentAlbum()) {
            noItemView.setLabel(getString(R.string.empty_set_description_no_recent));
            noItemView.setDescription(getString(R.string.empty_set_description_recent_no_item_vi));
        } else if (((AlbumPicturesPresenter) this.mPresenter).isFavoriteAlbum()) {
            noItemView.setLabel(getString(R.string.empty_set_description_no_favorites));
            noItemView.setDescription(getString(R.string.empty_set_description_favorite_no_item_vi));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$removeEmptyViewListener$9(GalleryListNoItemView galleryListNoItemView) {
        galleryListNoItemView.setGestureListener((SimpleGestureListener) null);
        galleryListNoItemView.setAppbar((GalleryAppBarLayout) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$removeListeners$7(GallerySwipeView gallerySwipeView) {
        gallerySwipeView.setGestureListener((SimpleGestureListener) null);
        gallerySwipeView.setAppbarScrollListener((GallerySwipeView.AppbarScrollListener) null);
        gallerySwipeView.setHeaderViewScrollListener((GallerySwipeView.HeaderViewScrollListener) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetSlideAnimation$14(GalleryListView galleryListView) {
        galleryListView.setColumnCount(getPinchColumn());
        PicturesLayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            layoutManager.setSpanCount(galleryListView.getColumnCount());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetSlideAnimation$15(GalleryListNoItemView galleryListNoItemView) {
        setEmptyViewMargin();
        refreshEmptyView();
        galleryListNoItemView.finishOnGoingAnimation();
        setOngoingSlideAnimation(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetSlideAnimation$16(GalleryListView galleryListView) {
        galleryListView.setColumnCount(getPinchColumn());
        PicturesLayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            layoutManager.setSpanCount(galleryListView.getColumnCount());
        }
        AlbumPicturesViewAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.resetTimelineMode();
            adapter.updateMaxGridSize(this.mGridSpans.spanMax());
            if (useConcatOnExtendedMonth() && adapter.isMonthForViewing()) {
                adapter.notifyDataSetChanged();
            }
        }
        setOngoingSlideAnimation(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEmptyViewListener$8(boolean z, GalleryListNoItemView galleryListNoItemView) {
        SimpleGestureListener simpleGestureListener;
        if (z) {
            simpleGestureListener = this.mSimpleGestureListener;
        } else {
            simpleGestureListener = null;
        }
        galleryListNoItemView.setGestureListener(simpleGestureListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setListeners$6(boolean z, GallerySwipeView gallerySwipeView) {
        SimpleGestureListener simpleGestureListener;
        f fVar;
        f fVar2 = null;
        if (z) {
            simpleGestureListener = this.mSimpleGestureListener;
        } else {
            simpleGestureListener = null;
        }
        gallerySwipeView.setGestureListener(simpleGestureListener);
        if (z) {
            fVar = new f(this);
        } else {
            fVar = null;
        }
        gallerySwipeView.setAppbarScrollListener(fVar);
        if (z) {
            fVar2 = new f(this);
        }
        gallerySwipeView.setHeaderViewScrollListener(fVar2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSplitModeToAlbumPane$41(AlbumsPaneFragment albumsPaneFragment) {
        albumsPaneFragment.setSplitMode(this.mIsSplitMode);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showNoItemViewAnimation$18(GalleryListNoItemView galleryListNoItemView) {
        galleryListNoItemView.prepareOnGoingAnimation();
        this.mSlideAnimationManager.onPrepareNoItemAnimation();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSplitAnimation$17() {
        P p6;
        GalleryListView listView = getListView();
        if (listView != null && listView.getChildCount() > 0 && (p6 = this.mPresenter) != null) {
            ((AlbumPicturesPresenter) p6).setImmediateToggleSplitState(true);
            setOngoingSlideAnimation(true);
            int depth = listView.getDepth();
            int i2 = super.getPinchColumn()[depth];
            int convert = GridValue.convert(depth, this.mSplitPinchColumnArray[depth], true, false);
            int i7 = this.mSplitPinchColumnArray[0];
            Optional.ofNullable(getAdapter()).ifPresent(new d(11));
            this.mSlideAnimationManager.onPrepareAnimation(i2, convert, i7);
            SeApiCompat.announceVoiceAssistant(getContext(), getString(R.string.speak_album_sidebar_opened));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAdapter$32(boolean z, AlbumPicturesViewAdapter albumPicturesViewAdapter) {
        albumPicturesViewAdapter.setHeaderView(this.mHeaderView);
        if (z) {
            albumPicturesViewAdapter.notifyItemChanged(0);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateAddItemButtonEnable$5(boolean z, View view) {
        float f;
        view.setEnabled(z);
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        view.setAlpha(f);
        view.setClickable(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAlbumSyncMenu$43(MediaItem mediaItem) {
        Optional.ofNullable((AlbumPicturesPresenter) this.mPresenter).ifPresent(new L(AlbumHelper.getInstance().isCloudOnlyAlbum((FileItemInterface) mediaItem), 16));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAlbumsPaneBottomPadding$39(AppBarLayout appBarLayout, int i2, GalleryListView galleryListView) {
        int i7;
        WindowInsets rootWindowInsets = galleryListView.getRootWindowInsets();
        int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(rootWindowInsets);
        int dimensionPixelOffset = galleryListView.getResources().getDimensionPixelOffset(R.dimen.list_view_default_bottom_padding);
        if (appBarLayout == null || appBarLayout.seslCanChangeToHideState() || appBarLayout.getTotalScrollRange() + i2 < 0) {
            i7 = 0;
        } else {
            i7 = WindowUtils.getSystemInsetsTop(rootWindowInsets) + this.mToolbar.getHeight();
        }
        int i8 = systemInsetsBottom + dimensionPixelOffset + i7;
        if (galleryListView.getPaddingBottom() != i8) {
            ViewMarginUtils.setBottomPadding(galleryListView, i8);
            galleryListView.setClipToPadding(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAlbumsPaneBottomPadding$40(AppBarLayout appBarLayout, int i2) {
        AlbumsPaneFragment<?, ?> albumsPaneFragment = this.mAlbumsPane;
        if (albumsPaneFragment != null && this.mToolbar != null) {
            Optional.ofNullable(albumsPaneFragment.getListView()).ifPresent(new i((Object) this, (Object) appBarLayout, i2, 3));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$updateFloatingToolbarLayout$44(GalleryAppBarLayout galleryAppBarLayout) {
        boolean z;
        if (galleryAppBarLayout.seslGetHeightProPortion() > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void lambda$updateFloatingToolbarLayout$45(GalleryToolbar galleryToolbar) {
        boolean z;
        ViewParent parent = galleryToolbar.getParent();
        if (parent instanceof FloatingToolbarLayout) {
            FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) parent;
            boolean z3 = floatingToolbarLayout.L;
            boolean booleanValue = ((Boolean) Optional.ofNullable(getAppbarLayout()).map(new L5.b(2)).orElse(Boolean.FALSE)).booleanValue();
            boolean z7 = false;
            if (!isLandscape() || isTabletDpi() || booleanValue) {
                z = false;
            } else {
                z = true;
            }
            if (z3 != z) {
                floatingToolbarLayout.p(z);
            }
            if (!z || this.mFloatingMode) {
                z7 = true;
            }
            galleryToolbar.setFloatingMode(z7);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeaderView$31(AtomicBoolean atomicBoolean, MediaItem mediaItem) {
        boolean z;
        if (getContext() == null || (!needToShowTipCard(mediaItem) && !needToShowScreenShotFilterView(mediaItem))) {
            z = true;
        } else {
            z = false;
        }
        atomicBoolean.set(z);
    }

    private void moveAlbumListToClosedPosition() {
        int i2;
        float x9 = this.mAlbumList.getX();
        if (this.mIsRtl) {
            i2 = this.mAlbumList.getWidth();
        } else {
            i2 = -this.mAlbumList.getWidth();
        }
        this.mAlbumList.setX(x9 + ((float) i2));
    }

    private boolean needToShowScreenShotFilterView(MediaItem mediaItem) {
        boolean isScreenShot = mediaItem.isScreenShot();
        boolean hasFilterData = this.mScreenShotFilterDelegate.hasFilterData();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "needToShowScreenShotFilterView {isScreenShotAlbum=" + isScreenShot + ", hasFilterData=" + hasFilterData + "}");
        if (!PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER || !isScreenShot || !hasFilterData) {
            return false;
        }
        return true;
    }

    private boolean needToShowTipCard(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isMergedAlbum() || !mediaItem.isAlbumShowInfo() || mediaItem.getChildList().size() <= 1) {
            return false;
        }
        return true;
    }

    private void refreshEmptyView() {
        if (((AlbumPicturesPresenter) this.mPresenter).isVirtualAlbum()) {
            ViewUtils.setVisibility(this.mCreateButton, 8);
            Optional.ofNullable(this.mEmptyViewText).ifPresent(new a(this, 5));
            return;
        }
        boolean z = true;
        adjustEmptyViewButtonLayout(true);
        ViewUtils.setVisibility(this.mEmptyViewText, 8);
        if (isSelectionMode() || isUpsm()) {
            z = false;
        }
        updateAddItemButtonEnable(z);
    }

    private void removeEmptyViewListener() {
        Optional.ofNullable((GalleryListNoItemView) this.mEmptyView).ifPresent(new d(4));
    }

    private void removeListeners() {
        Optional.ofNullable((GallerySwipeView) getListView()).ifPresent(new d(3));
    }

    private void resetSlideAnimation() {
        if (isEmptyViewShowing()) {
            Optional.ofNullable(getListView()).ifPresent(new a(this, 2));
            Optional.ofNullable((GalleryListNoItemView) this.mEmptyView).ifPresent(new a(this, 3));
            return;
        }
        this.mLayoutManager.recalculatePosition();
        Optional.ofNullable(getListView()).ifPresent(new a(this, 4));
    }

    private void saveSplitMode() {
        PreferenceFeatures.setEnabled(PreferenceFeatures.AlbumSplitMode, this.mIsSplitMode);
    }

    private void setEmptyViewListener(boolean z) {
        Optional.ofNullable((GalleryListNoItemView) this.mEmptyView).ifPresent(new e(this, z, 0));
    }

    private void setEmptyViewMargin() {
        int i2;
        if (!ViewUtils.isGone(this.mEmptyView) && !isDestroyed()) {
            View view = this.mEmptyView;
            if (!supportSplitFeature() || !this.mIsSplitMode) {
                i2 = 0;
            } else {
                i2 = getResources().getDimensionPixelOffset(R.dimen.albums_pane_width);
            }
            ViewMarginUtils.setStartMargin(view, i2);
        }
    }

    private void setListeners(boolean z) {
        Optional.ofNullable((GallerySwipeView) getListView()).ifPresent(new e(this, z, 1));
    }

    /* access modifiers changed from: private */
    public void setNestedScrollToAlbumPane(boolean z) {
        Optional.ofNullable(this.mAlbumsPane).ifPresent(new L(z, 15));
    }

    private void setOngoingSlideAnimation(boolean z) {
        ((GallerySwipeView) getListView()).setOngoingAnimation(z);
    }

    private void setScrollListener() {
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                boolean z;
                AlbumPicturesFragment albumPicturesFragment = AlbumPicturesFragment.this;
                if (i2 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                albumPicturesFragment.setNestedScrollToAlbumPane(z);
            }
        });
    }

    private void setSplitFeature(boolean z) {
        if (!z || isMxVirtualAlbum()) {
            disableSplitMode();
        } else if (this.mAlbumsPane == null) {
            initSplitMode();
            this.mAlbumsPane = new AlbumsPaneFragment<>();
            setSplitModeToAlbumPane();
            getChildFragmentManager().beginTransaction().add((int) R.id.albums_left_pane, (Fragment) this.mAlbumsPane).commitAllowingStateLoss();
        }
        setListeners(z);
        setEmptyViewListener(z);
    }

    private void setSplitModeToAlbumPane() {
        Optional.ofNullable(this.mAlbumsPane).ifPresent(new a(this, 10));
    }

    private void updateAdapter(boolean z) {
        Optional.ofNullable(getAdapter()).ifPresent(new e(this, z, 2));
    }

    private void updateAddItemButtonEnable(boolean z) {
        if (ViewUtils.isVisible(this.mEmptyView)) {
            Optional.ofNullable(this.mCreateButton).ifPresent(new L(z, 14));
        }
    }

    private void updateAlbumSyncMenu() {
        P p6;
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_MENU_ON_TOOL_BAR) && (p6 = this.mPresenter) != null) {
            MediaItem currentItem = ((AlbumPicturesPresenter) p6).getCurrentItem();
            if (((AlbumPicturesPresenter) this.mPresenter).supportAlbumSync(currentItem)) {
                ThreadUtil.postOnBgThread(new H.a(27, this, currentItem));
            }
        }
    }

    private void updateFloatingToolbarLayout() {
        if (!isAppbarDisabled()) {
            Optional.ofNullable(getToolbar()).ifPresent(new a(this, 8));
        }
    }

    private boolean useConcatOnExtendedMonth() {
        if (!PocFeatures.isEnabled(PocFeatures.AlbumMonthExtend) || !PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL) {
            return false;
        }
        return true;
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        super.adjustToolbarLayout(windowInsets);
        Optional.ofNullable(getToolbar()).ifPresent(new d(2));
    }

    public void bindView(View view) {
        boolean z;
        super.bindView(view);
        this.mAlbumList = view.findViewById(R.id.albums_left_pane);
        this.mIsRtl = view.getResources().getBoolean(R.bool.is_right_to_left);
        if (isAppbarDisabled()) {
            Optional.ofNullable(getAppbarLayout()).ifPresent(new l(17));
            return;
        }
        if (!isLandscape() || isTabletDpi()) {
            z = true;
        } else {
            z = false;
        }
        this.mFloatingMode = z;
        updateFloatingToolbarLayout();
    }

    public void changeAlbum(String str) {
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.scrollToPosition(0);
            listView.resetDefaultMaxScroll();
            setLocationKey(str);
            Optional.ofNullable((AlbumPicturesPresenter) this.mPresenter).ifPresent(new B(str, 10));
            listView.post(new g(this, str, 1));
            this.mIsAlbumChanged = true;
        }
    }

    public void closeMediaData() {
        super.closeMediaData();
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate.closeScreenShotFilterData();
        }
    }

    public CustomCover createCustomCover(View view) {
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER || isAppbarDisabled()) {
            return null;
        }
        return new BlurCustomCover(view.findViewById(R.id.custom_cover));
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new SplitDragAndDropDelegate(this).setSplitHandler(this);
    }

    public ListProtectionGradient createProtectionGradient() {
        if (isAppbarDisabled() || PickerUtil.isPickerMode(this.mBlackboard)) {
            return super.createProtectionGradient();
        }
        return new ListProtectionGradientImpl(new C0385u(7, this));
    }

    public void exitSelectionMode(boolean z) {
        super.exitSelectionMode(z);
        updateAddItemButtonEnable(true);
    }

    public int getAlbumListWidth() {
        if (this.mAlbumList == null || isDestroyed()) {
            return 0;
        }
        return getResources().getDimensionPixelOffset(R.dimen.albums_pane_width);
    }

    public int getCluster(int i2) {
        int i7;
        if (getAdapter() != null) {
            i7 = getAdapter().getCurrentMode();
        } else {
            i7 = -1;
        }
        if (i7 >= 0) {
            return i7;
        }
        return super.getCluster(i2);
    }

    public int getDefaultSidePadding() {
        return getResources().getDimensionPixelOffset(R.dimen.album_pictures_side_spacing);
    }

    public int getLayoutId() {
        return R.layout.fragment_album_pictures_layout;
    }

    public int getMaxColumnSize() {
        return getMaxColumnSize(isSplitMode() || isDrawerOpen());
    }

    public int[] getPinchColumn() {
        boolean z;
        if (isSplitMode() || isDrawerOpen()) {
            z = true;
        } else {
            z = false;
        }
        return getPinchColumn(z);
    }

    public int getPreferenceDefault() {
        return getGridHelper().getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return getGridHelper().getPreferenceName();
    }

    public String getScreenId() {
        MediaItem mediaItem;
        String locationKey = getLocationKey();
        if (!isSelectionMode()) {
            Blackboard blackboard = this.mBlackboard;
            if (blackboard != null && (mediaItem = (MediaItem) blackboard.read("data://albums/current", new MediaItem())) != null && mediaItem.isMergedAlbum()) {
                return AnalyticsScreenId.SCREEN_SPLIT_VIEW_MERGED_ALBUM.toString();
            }
            if (LocationKey.isMxVirtualFavoriteAlbum(locationKey)) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_FAVORITE.toString();
            }
            if (LocationKey.isMxVirtualRecentAlbum(locationKey)) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_RECENT.toString();
            }
            return AnalyticsScreenId.SCREEN_SPLIT_VIEW_NORMAL.toString();
        } else if (LocationKey.isMxVirtualFavoriteAlbum(locationKey)) {
            return AnalyticsScreenId.SCREEN_SMART_ALBUM_FAVORITE_EDIT.toString();
        } else {
            if (LocationKey.isMxVirtualRecentAlbum(locationKey)) {
                return AnalyticsScreenId.SCREEN_SMART_ALBUM_RECENT_EDIT.toString();
            }
            if (getAdapter().getSelectionModeByLongPress()) {
                return AnalyticsScreenId.SCREEN_SPLIT_VIEW_LONG_PRESS.toString();
            }
            return AnalyticsScreenId.SCREEN_SPLIT_VIEW_EDIT.toString();
        }
    }

    public MediaItem[] getSelectedItemsForSplitDrag() {
        if (isOnAdvancedMouseFocused()) {
            return getSelectedItems();
        }
        if (getAdapter() != null) {
            return getAdapter().getSelectedItemsFromBuffer();
        }
        return new MediaItem[0];
    }

    public int getStartPinchDepth() {
        return getPinchDepthRecoveredIfAbsent();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.albums_pane_width);
        ViewUtils.setWidth(this.mAlbumList, dimensionPixelOffset);
        if (!this.mIsSplitMode) {
            this.mAlbumList.setX((float) (-dimensionPixelOffset));
        }
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.post(new C0368c(this, i2, 9));
        } else {
            Optional.ofNullable(this.mCustomCoverHolder).ifPresent(new C0369d(i2, 6));
        }
        Optional.ofNullable(this.mHeaderView).ifPresent(new a(this, 1));
    }

    public boolean hasCustomCover() {
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER || this.mCustomCoverHolder == null) {
            return false;
        }
        return true;
    }

    public void hideSplitAnimation() {
        P p6;
        if (!isSplitClosed()) {
            if (!isDrawerMode()) {
                GalleryListView listView = getListView();
                if (listView != null && listView.getChildCount() > 0 && (p6 = this.mPresenter) != null) {
                    ((AlbumPicturesPresenter) p6).setImmediateToggleSplitState(false);
                    setOngoingSlideAnimation(true);
                    int[] pinchColumn = super.getPinchColumn();
                    int depth = listView.getDepth();
                    int i2 = this.mSplitPinchColumnArray[depth];
                    int convert = GridValue.convert(depth, pinchColumn[depth], false, false);
                    int i7 = pinchColumn[0];
                    Optional.ofNullable(getAdapter()).ifPresent(new d(11));
                    this.mSlideAnimationManager.onPrepareAnimation(i2, convert, i7);
                    SeApiCompat.announceVoiceAssistant(getContext(), getString(R.string.speak_album_sidebar_closed));
                    return;
                }
                return;
            }
            this.mBlackboard.post("command://CloseDrawer", (Object) null);
        }
    }

    public void initView(View view) {
        super.initView(view);
        ((GallerySwipeView) getListView()).setSwipeAnimationManager(this.mSlideAnimationManager.getAnimationManager());
    }

    public void initializeEmptyView() {
        Optional.ofNullable(getListView()).ifPresent(new a(this, 6));
    }

    public boolean isAlbumChanged() {
        return this.mIsAlbumChanged;
    }

    public boolean isAlbumPaneAvailable() {
        if (this.mAlbumsPane != null) {
            return true;
        }
        return false;
    }

    public boolean isEnterTransition() {
        return isEnterTransitionOnGoing();
    }

    public boolean isSplitBlocked() {
        return this.mIsSplitBlocked;
    }

    public boolean isSplitClosed() {
        if (isDrawerMode()) {
            if (!isDrawerOpen()) {
                return true;
            }
            return false;
        } else if (!isSplitMode()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSplitMode() {
        return this.mIsSplitMode;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001a, code lost:
        if (com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue(r3, "album_split_blocked", false) != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadArguments(android.os.Bundle r3) {
        /*
            r2 = this;
            super.loadArguments(r3)
            java.lang.String r3 = r2.getLocationKey()
            boolean r0 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isShortcutAlbum(r3)
            if (r0 != 0) goto L_0x001c
            boolean r0 = r2.isSplitDisabled()
            if (r0 != 0) goto L_0x001c
            java.lang.String r0 = "album_split_blocked"
            r1 = 0
            boolean r3 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue((java.lang.String) r3, (java.lang.String) r0, (boolean) r1)
            if (r3 == 0) goto L_0x001d
        L_0x001c:
            r1 = 1
        L_0x001d:
            r2.mIsSplitBlocked = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment.loadArguments(android.os.Bundle):void");
    }

    public boolean loadSplitModeFromPreference() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.AlbumSplitMode) || LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    public void onAlbumPaneDataPrepared() {
        Runnable runnable = this.mShowSplitAnimation;
        if (runnable != null) {
            runnable.run();
            this.mShowSplitAnimation = null;
        }
    }

    public void onAppbarVerticalOffset(AppBarLayout appBarLayout, int i2) {
        updateAlbumsPaneBottomPadding(appBarLayout, i2);
    }

    public void onAppbarVisibleRatio(float f) {
        CustomCover customCover = this.mCustomCoverHolder;
        if (customCover != null) {
            customCover.setAlphaOnCoverView(f);
        }
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        adjustEmptyViewButtonLayout(false);
        updateFloatingToolbarLayout();
        return windowInsets;
    }

    public boolean onBackPressed() {
        boolean z;
        if (!isSelectionMode()) {
            Optional.ofNullable(this.mBlackboard).ifPresent(new b(26));
        }
        P p6 = this.mPresenter;
        if ((p6 == null || !((AlbumPicturesPresenter) p6).isConsumeBackPress()) && !super.onBackPressed()) {
            z = false;
        } else {
            z = true;
        }
        if (!z && !isMxVirtualAlbum()) {
            Optional.ofNullable(getToolbar()).ifPresent(new b(27));
        }
        return z;
    }

    public void onBindView(View view) {
        setSplitFeature(supportSplitFeature());
        if (LocationKey.isShortcutAlbum(getLocationKey())) {
            this.mBlackboard.publish("shortcut_album_loading", Boolean.TRUE);
        }
        super.onBindView(view);
        setScrollListener();
        expandAppbar();
    }

    public void onChangeAlbum(String str) {
        Optional.ofNullable(getListView()).ifPresent(new d(1));
        ThreadUtil.postOnUiThread(new g(this, str, 0));
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ((AlbumPicturesPresenter) this.mPresenter).getQuickSharePrivacyTip().onConfigurationChanged(getToolbar());
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.post(new M4.b(this, 2));
        } else {
            ((AlbumPicturesPresenter) this.mPresenter).updateToolbar(getToolbar());
        }
    }

    public void onDataChangedOnUi() {
        updateHeaderView(false, false);
        updateAlbumSyncMenu();
        super.onDataChangedOnUi();
    }

    public void onDestroy() {
        removeChildFragments();
        Optional.ofNullable(this.mSlideAnimationManager).ifPresent(new d(9));
        removeListeners();
        removeEmptyViewListener();
        Optional.ofNullable(this.mHeaderView).ifPresent(new d(10));
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate.unbindView();
        }
        super.onDestroy();
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        if (this.mPresenter != null) {
            int loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(((AlbumPicturesPresenter) this.mPresenter).getCurrentItem().getAlbumID()), 12);
            Logger.dumpLog(printWriter, str + "sortBy : " + loadSortBy);
        }
    }

    public void onEmptyViewLayoutChecked() {
        if (ViewUtils.isVisible(this.mEmptyView)) {
            refreshEmptyView();
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        View view2 = this.mEmptyView;
        if (view2 != null) {
            SimpleGestureListener simpleGestureListener = null;
            if (view2.getVisibility() != 8) {
                GalleryListNoItemView galleryListNoItemView = (GalleryListNoItemView) this.mEmptyView;
                if (supportSplitFeature()) {
                    simpleGestureListener = this.mSimpleGestureListener;
                }
                galleryListNoItemView.setGestureListener(simpleGestureListener);
                ((GalleryListNoItemView) this.mEmptyView).setAppbar(this.mAppBarLayout);
                setEmptyViewMargin();
            } else {
                ((GalleryListNoItemView) this.mEmptyView).setGestureListener((SimpleGestureListener) null);
                ((GalleryListNoItemView) this.mEmptyView).setAppbar((GalleryAppBarLayout) null);
            }
            if (this.mEmptyView.getVisibility() == 0) {
                if (this.mCreateButton == null) {
                    View findViewById = this.mEmptyView.findViewById(R.id.create_button_layout);
                    this.mCreateButton = findViewById;
                    findViewById.setOnClickListener(new c(this, 0));
                    ViewUtils.setAccessibilityRoleDescription(this.mCreateButton, R.string.speak_button);
                }
                if (this.mEmptyViewText == null) {
                    this.mEmptyViewText = (NoItemView) this.mEmptyView.findViewById(R.id.empty_view_text);
                }
            }
            refreshEmptyView();
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
            if (baseListViewAdapter != null) {
                baseListViewAdapter.onGridSizeChanged();
            }
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        GalleryListView galleryListView;
        DragAndDropDelegate dragAndDropDelegate = this.mDragAndDropDelegate;
        if (isAlbumPaneAvailable()) {
            galleryListView = this.mAlbumsPane.getListView();
        } else {
            galleryListView = null;
        }
        if (dragAndDropDelegate.onKeyDown(galleryListView, i2, keyEvent) || super.onKeyDown(i2, keyEvent)) {
            return true;
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        Optional.ofNullable(this.mCustomCoverHolder).ifPresent(new a(this, 9));
    }

    public void onSelectionModeChanged(boolean z) {
        this.mIsAlbumChanged = false;
    }

    public void onSlideEnd() {
        this.mIsSplitMode = !this.mIsSplitMode;
        setSplitModeToAlbumPane();
        saveSplitMode();
        resetSlideAnimation();
        Optional.ofNullable(getListView()).ifPresent(new d(7));
    }

    public void onSlideStart() {
        Optional.ofNullable(getListView()).ifPresent(new d(8));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateCustomCover(0, ((AlbumPicturesPresenter) this.mPresenter).getCurrentItem());
    }

    public void openMediaData() {
        super.openMediaData();
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate.openScreenShotFilterData();
        }
    }

    public void preloadViewPool() {
        boolean z = PocFeatures.ALBUM_PICTURES_EXTEND;
        if (z && PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && supportViewPool() && GridValue.isMonthXs(getGridSpans().valueOf())) {
            createViewPool(10, 3);
        }
        super.preloadViewPool();
        if (z && supportViewPool() && supportMonthForViewing()) {
            M4.b bVar = new M4.b(this, 1);
            this.mCreateViewPoolExtras = bVar;
            ThreadUtil.postOnBgThreadDelayed(bVar, 1000);
        }
    }

    public void releaseRunnable() {
        Runnable runnable;
        if (PocFeatures.ALBUM_PICTURES_EXTEND && (runnable = this.mCreateViewPoolExtras) != null) {
            ThreadUtil.removeCallbackOnBgThread(runnable);
            this.mCreateViewPoolExtras = null;
        }
        super.releaseRunnable();
    }

    public void resetScreenShotFilter() {
        if (PreferenceFeatures.OneUi8x.SCREEN_SHOT_FILTER_REORDER) {
            this.mScreenShotFilterDelegate.resetFilter();
        }
        this.mScreenShotFilterDelegate.updateScreenShotFilter();
    }

    public void setLocationKey(String str) {
        super.setLocationKey(str);
    }

    public void showNoItemViewAnimation() {
        Optional.ofNullable((GalleryListNoItemView) this.mEmptyView).ifPresent(new a(this, 0));
    }

    public void showSplitAnimation() {
        if (isSplitClosed()) {
            if (isDrawerMode()) {
                this.mBlackboard.post("command://OpenDrawer", (Object) null);
                return;
            }
            this.mShowSplitAnimation = new M4.b(this, 3);
            if (isAlbumPaneDataPrepared()) {
                this.mShowSplitAnimation.run();
                this.mShowSplitAnimation = null;
            }
        }
    }

    public boolean skipEmptyView() {
        P p6 = this.mPresenter;
        if (p6 == null || !((AlbumPicturesPresenter) p6).skipEmptyView()) {
            return false;
        }
        return true;
    }

    public void startShrinkAnimation() {
        if (isDisplayWithDrawer()) {
            Optional.ofNullable(this.mBlackboard).ifPresent(new d(5));
        } else {
            super.startShrinkAnimation();
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportPivotOnFingerPos() {
        return false;
    }

    public boolean supportSplitFeature() {
        if (PocFeatures.isEnabled(PocFeatures.WifiGalleryClient) || isMxVirtualAlbum()) {
            return false;
        }
        if ((!isDisplayWithDrawer() || !isDrawerMode()) && !LocationKey.isShortcutAlbum(getLocationKey()) && !LocationKey.isAlbumPicturesFromSearchCluster(getLocationKey())) {
            return true;
        }
        return false;
    }

    public boolean supportTimeline() {
        try {
            int albumID = ((AlbumPicturesPresenter) this.mPresenter).getCurrentItem().getAlbumID();
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlbumTimeline)) {
                return !BucketUtils.isRecent(albumID);
            }
            return SortByType.isGroupByDate(String.valueOf(albumID));
        } catch (Exception unused) {
            return false;
        }
    }

    public void toggleSplitMode() {
        String str;
        if (isDrawerMode()) {
            Blackboard blackboard = this.mBlackboard;
            if (isDrawerOpen()) {
                str = "command://CloseDrawer";
            } else {
                str = "command://OpenDrawer";
            }
            blackboard.post(str, (Object) null);
        } else if (!isSplitMode()) {
            showSplitAnimation();
        } else {
            hideSplitAnimation();
        }
    }

    public void updateAlbumsPaneBottomPadding(AppBarLayout appBarLayout, int i2) {
        if (this.mVerticalOffset != i2) {
            this.mVerticalOffset = i2;
            AlbumsPaneFragment<?, ?> albumsPaneFragment = this.mAlbumsPane;
            if (albumsPaneFragment != null) {
                Optional.ofNullable(albumsPaneFragment.getListView()).ifPresent(new d(6));
                this.mRecyclerView.postDelayed(new Ab.b((Object) this, (Object) appBarLayout, i2, 23), 100);
            }
        }
    }

    public boolean updateCustomCover(int i2, Object obj) {
        CustomCover customCover;
        MediaItem mediaItem;
        try {
            if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER || (customCover = this.mCustomCoverHolder) == null) {
                return false;
            }
            if (i2 == 0) {
                if (obj instanceof MediaItem) {
                    mediaItem = (MediaItem) obj;
                } else {
                    mediaItem = ((AlbumPicturesPresenter) this.mPresenter).getCurrentItem();
                }
                customCover.bindImage(mediaItem);
                return true;
            } else if (i2 == 1) {
                customCover.updateDecorView(i2, obj);
                Optional.ofNullable(this.mAppBarLayout).ifPresent(new b(28));
                return true;
            } else if (i2 != 2) {
                return false;
            } else {
                customCover.updateDecorView(i2, obj);
                Optional.ofNullable(this.mAppBarLayout).ifPresent(new b(29));
                return true;
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "update custom cover failed", e.getMessage());
            return false;
        }
    }

    public void updateFilterView() {
        updateHeaderView(true, true);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateHeaderView(boolean r8, boolean r9) {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean
            r1 = 0
            r0.<init>(r1)
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r7.getBlackboard()
            com.samsung.android.gallery.module.data.MediaItem r3 = new com.samsung.android.gallery.module.data.MediaItem
            r3.<init>()
            java.lang.String r4 = "data://albums/current"
            java.lang.Object r2 = r2.read(r4, r3)
            com.samsung.android.gallery.module.data.MediaItem r2 = (com.samsung.android.gallery.module.data.MediaItem) r2
            boolean r3 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER
            r4 = 1
            if (r3 == 0) goto L_0x0038
            com.samsung.android.gallery.support.utils.LatchBuilder r3 = new com.samsung.android.gallery.support.utils.LatchBuilder
            java.lang.String r5 = "needToShowTipCard"
            r3.<init>(r5)
            J6.c r5 = new J6.c
            r6 = 8
            r5.<init>(r7, r0, r2, r6)
            com.samsung.android.gallery.support.utils.LatchBuilder r3 = r3.addWorker(r5)
            r5 = 1000(0x3e8, double:4.94E-321)
            com.samsung.android.gallery.support.utils.LatchBuilder r3 = r3.setTimeout(r5)
            r3.start()
            goto L_0x004b
        L_0x0038:
            android.content.Context r3 = r7.getContext()
            if (r3 == 0) goto L_0x0047
            boolean r3 = r7.needToShowTipCard(r2)
            if (r3 != 0) goto L_0x0045
            goto L_0x0047
        L_0x0045:
            r3 = r1
            goto L_0x0048
        L_0x0047:
            r3 = r4
        L_0x0048:
            r0.set(r3)
        L_0x004b:
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x005c
            com.samsung.android.gallery.app.ui.list.albums.pictures.header.AlbumPicturesHeaderView r0 = r7.mHeaderView
            if (r0 == 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r4 = r1
        L_0x0057:
            r9 = r9 | r4
            r0 = 0
            r7.mHeaderView = r0
            goto L_0x007b
        L_0x005c:
            int r0 = r2.getItemCount()
            com.samsung.android.gallery.app.ui.list.albums.pictures.header.AlbumPicturesHeaderView r3 = r7.mHeaderView
            if (r3 == 0) goto L_0x006c
            int r3 = r7.mAlbumCount
            int r5 = r2.getItemCount()
            if (r3 == r5) goto L_0x006d
        L_0x006c:
            r1 = r4
        L_0x006d:
            r9 = r9 | r1
            r7.inflateHeaderView()
            com.samsung.android.gallery.app.ui.list.albums.pictures.header.AlbumPicturesHeaderView r1 = r7.mHeaderView
            com.samsung.android.gallery.app.controller.EventContext r3 = r7.getEventContext()
            r1.updateHeaderView(r3, r2)
            r1 = r0
        L_0x007b:
            if (r9 == 0) goto L_0x0082
            r7.updateAdapter(r8)
            r7.mAlbumCount = r1
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment.updateHeaderView(boolean, boolean):void");
    }

    public void updateTabMode(boolean z) {
        super.updateTabMode(z);
        setSplitFeature(z);
        setEmptyViewMargin();
        if (z) {
            Optional.ofNullable((AlbumPicturesPresenter) this.mPresenter).ifPresent(new d(0));
        }
    }

    public int getMaxColumnSize(boolean z) {
        return this.mGridSpans.spanMax(z);
    }

    public AlbumPicturesLayoutManager createLayoutManager() {
        AlbumPicturesFragment albumPicturesFragment;
        IAlbumsPaneSlideAnimationManager iAlbumsPaneSlideAnimationManager;
        AnonymousClass1 r0 = new AlbumPicturesLayoutManager(getContext(), getColumnCount(), this) {
            private int getColumn(int i2) {
                return AlbumPicturesFragment.this.getPinchColumn()[i2];
            }

            public int getExtraLayoutSpace(RecyclerView.State state) {
                if (AlbumPicturesFragment.this.getListView().getScrollState() != 0) {
                    return AlbumPicturesFragment.this.getListView().getHeight() / 3;
                }
                return 0;
            }

            public int getSpacing(int i2) {
                return AlbumPicturesFragment.this.getGridSpacing(i2);
            }

            public boolean isAppbarCollapsed() {
                if (!(AlbumPicturesFragment.this.mAppBarLayout == null || AlbumPicturesFragment.this.mToolbar == null)) {
                    int visibleHeight = AlbumPicturesFragment.this.mAppBarLayout.getVisibleHeight();
                    int top = AlbumPicturesFragment.this.mToolbar.getTop() + AlbumPicturesFragment.this.mToolbar.getHeight();
                    if (visibleHeight > 0 || Math.abs(visibleHeight) >= top) {
                        return false;
                    }
                    return true;
                }
                return false;
            }

            public boolean isAppbarVisible() {
                return AlbumPicturesFragment.this.isAppBarPartiallyVisible();
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                if (spanCount > AlbumPicturesFragment.this.getPinchColumn()[0] + 30) {
                    spanCount = 1;
                }
                if (this.mListAdapter != null && AlbumPicturesFragment.this.isClusteringChanged(i2, spanCount)) {
                    Log.i("GalleryGridLayoutManager", "setSpanCount#isClusteringChanged {" + spanCount + ',' + i2 + ",(" + getColumn(0) + ',' + getColumn(1) + ")}");
                    this.mListAdapter.onClusterChanged(spanCount);
                }
                AlbumPicturesFragment.this.onGridChanged(i2, spanCount);
            }
        };
        this.mLayoutManager = r0;
        r0.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                AlbumPicturesViewAdapter adapter = AlbumPicturesFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getHintStartSpan(i2, adapter.getGridSize());
                }
                Log.w(AlbumPicturesFragment.this.TAG, "span index 0 (null adapter)");
                return 0;
            }

            public int getSpanSize(int i2) {
                if (AlbumPicturesFragment.this.mListAdapter == null) {
                    return 1;
                }
                if (AlbumPicturesFragment.this.mListAdapter.isData(i2)) {
                    AlbumPicturesFragment albumPicturesFragment = AlbumPicturesFragment.this;
                    if (!albumPicturesFragment.useFullSpan(i2, albumPicturesFragment.mListAdapter)) {
                        if (AlbumPicturesFragment.this.mListAdapter.isRealRatio()) {
                            return Math.min(AlbumPicturesFragment.this.mListAdapter.getSpanSize(i2), AlbumPicturesFragment.this.mLayoutManager.getSpanCount());
                        }
                        return 1;
                    }
                }
                return AlbumPicturesFragment.this.mLayoutManager.getSpanCount();
            }
        });
        if (useConcatOnExtendedMonth()) {
            albumPicturesFragment = this;
            iAlbumsPaneSlideAnimationManager = new AlbumsPaneSlideAnimationManagerV2(this.mLayoutManager, this.mAlbumList, createClusterInfo(), getListView(), new J5.c(3, this), albumPicturesFragment);
        } else {
            albumPicturesFragment = this;
            iAlbumsPaneSlideAnimationManager = new AlbumsPaneSlideAnimationManager(albumPicturesFragment.mLayoutManager, albumPicturesFragment.getListView(), albumPicturesFragment.mAlbumList, new J5.c(3, albumPicturesFragment), albumPicturesFragment);
        }
        albumPicturesFragment.mSlideAnimationManager = iAlbumsPaneSlideAnimationManager;
        return albumPicturesFragment.mLayoutManager;
    }

    public AlbumPicturesViewAdapter<?> createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumPicturesViewAdapter<>(this, getLocationKey(), this.mHeaderView, isRealRatioSupported());
    }

    public AlbumPicturesPresenter<?> createPresenter(IAlbumPicturesView iAlbumPicturesView) {
        return new AlbumPicturesPresenter<>(this.mBlackboard, iAlbumPicturesView);
    }

    public AlbumPicturesViewAdapter<?> getAdapter() {
        return (AlbumPicturesViewAdapter) this.mListAdapter;
    }
}
