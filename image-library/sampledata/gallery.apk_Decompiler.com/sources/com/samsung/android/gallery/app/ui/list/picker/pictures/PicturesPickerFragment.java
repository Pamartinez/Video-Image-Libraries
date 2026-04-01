package com.samsung.android.gallery.app.ui.list.picker.pictures;

import A4.C0371f;
import U9.b;
import W8.C0579a;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.abstraction.CoverPickType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesPickerFragment<V extends IPicturesView, P extends PicturesPickerPresenter> extends PicturesFragment<V, P> implements IPicturesView {
    protected View mPicturesLayout;
    private ScreenShotFilterDelegate mScreenShotFilterDelegate;
    private GalleryToolbar mToolbar;

    public PicturesPickerFragment() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate = new ScreenShotFilterDelegate(this);
        }
    }

    private View createHeaderView() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER || !BucketUtils.isScreenshot(ArgumentsUtil.getArgValue(getLocationKey(), "id", 0))) {
            return null;
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.screen_shot_filter_view_layout, (ViewGroup) null, false);
        ViewMarginUtils.setStartPadding(inflate, getResources().getDimensionPixelOffset(R.dimen.tip_card_padding_start));
        ViewMarginUtils.setEndPadding(inflate, getResources().getDimensionPixelOffset(R.dimen.tip_card_padding_end));
        this.mScreenShotFilterDelegate.bindView(inflate);
        return inflate;
    }

    private int getContentViewTopPadding(boolean z) {
        return PickerUtil.getContentViewTopPadding(this.mBlackboard, z);
    }

    private void handleCommonConfigurationChanged() {
        if (getContext() != null) {
            updatePadding();
        }
    }

    private boolean isGroupByDate() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "mergedAlbumId");
            if (!TextUtils.isEmpty(argValue)) {
                return SortByType.isGroupByDate(argValue);
            }
        }
        return SortByType.isGroupByDate(ArgumentsUtil.getArgValue(getLocationKey(), "id"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$0(WindowInsets windowInsets, View view, WindowInsets windowInsets2) {
        PickerViewUtil.adjustViewAreaMargin(windowInsets, ((PicturesPickerPresenter) this.mPresenter).getActivityToolBar(), ((PicturesPickerPresenter) this.mPresenter).getClipboardView(), view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onGridChanged$1(String str) {
        Blackboard.publishGlobal(str, Integer.valueOf(((PicturesPickerPresenter) this.mPresenter).getCurrentViewDepth()));
    }

    private void updateContentViewPadding(boolean z, boolean z3) {
        if (this.mPicturesLayout != null) {
            int contentViewTopPadding = getContentViewTopPadding(z);
            if (z3) {
                View view = this.mPicturesLayout;
                PickerViewUtil.setContentViewTopPaddingWithAnimation(view, view.getPaddingTop(), contentViewTopPadding);
                return;
            }
            PickerViewUtil.setContentViewTopPadding(this.mPicturesLayout, contentViewTopPadding);
        }
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        if (z || ((PicturesPickerPresenter) this.mPresenter).isCoverItemPick() || ((PicturesPickerPresenter) this.mPresenter).isRequireCrop()) {
            super.addSharedTransition(listViewHolder, mediaItem, i2, z);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mPicturesLayout = view.findViewById(R.id.picture_picker_layout);
        this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
    }

    public void closeMediaData() {
        super.closeMediaData();
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate.closeScreenShotFilterData();
        }
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public int getLayoutId() {
        return R.layout.fragment_video_picker_layout;
    }

    public int getPreferenceDefault() {
        return getGridHelper().getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return getGridHelper().getPreferenceName();
    }

    public String getScreenId() {
        CoverPickType coverPickType;
        P p6 = this.mPresenter;
        if (p6 == null) {
            return null;
        }
        if (((PicturesPickerPresenter) p6).getLaunchMode() == LaunchModeType.ACTION_MULTIPLE_PICK) {
            return AnalyticsScreenId.SCREEN_SPLIT_VIEW_PICK.toString();
        }
        if (((PicturesPickerPresenter) this.mPresenter).getLaunchMode() != LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return AnalyticsScreenId.SCREEN_SPLIT_VIEW_PICK.toString();
        }
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent", null);
        if (launchIntent == null) {
            coverPickType = CoverPickType.NONE;
        } else {
            coverPickType = CoverPickType.getType(launchIntent.getCoverPickType());
        }
        return coverPickType.getScreenId();
    }

    public int getStartPinchDepth() {
        int loadPinchDepth = loadPinchDepth();
        if (this.mGridSpans.selectable(loadPinchDepth)) {
            return loadPinchDepth;
        }
        int indexOfNearSelectable = this.mGridSpans.indexOfNearSelectable(loadPinchDepth);
        savePinchDepth(indexOfNearSelectable);
        Log.e((CharSequence) this.TAG, "getStartPinch changed", Integer.valueOf(loadPinchDepth), Integer.valueOf(indexOfNearSelectable));
        return indexOfNearSelectable;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        handleCommonConfigurationChanged();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (!isConfigStateChanged(2)) {
            handleCommonConfigurationChanged();
        }
    }

    public boolean isAllowAdvancedMouseEvent() {
        return !PickerUtil.isSinglePickLaunchMode(this.mBlackboard);
    }

    public boolean isFadingEdgeExtended() {
        return false;
    }

    public boolean isPicker() {
        return true;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        if (((PicturesPickerPresenter) this.mPresenter).getLaunchMode() == LaunchModeType.ACTION_MULTIPLE_PICK) {
            int[] iArr = new int[2];
            getView().getLocationInWindow(iArr);
            int i7 = iArr[1];
            if (ViewUtils.isTouchedOnRange(motionEvent, i7, getContentViewTopPadding(true ^ isLandscape()) + i7)) {
                return false;
            }
        }
        return super.isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2);
    }

    public void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
    }

    public int[] loadPinchColumnResource() {
        return getGridHelper().getGridArray(getContext());
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        Optional.ofNullable(view.getRootWindowInsets()).ifPresent(new C0371f((Object) this, (Object) windowInsets, (Object) view, 15));
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
        if (!supportTabLayout()) {
            getListView().updateGoToTopBottomPadding((float) getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
        }
        return windowInsets;
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        if (getActivity() != null) {
            this.mToolbar = (GalleryToolbar) getActivity().findViewById(R.id.activity_toolbar);
        }
        if (this.mToolbar == null) {
            Optional.ofNullable((ViewStub) view.findViewById(R.id.appbar_container)).ifPresent(new C0579a(4));
            this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        }
        updatePadding();
    }

    public void onDataChangedOnUi() {
        if (isDestroyed()) {
            Log.pke(this.TAG, "Fragment destroyed");
            return;
        }
        super.onDataChangedOnUi();
        if (getContext() != null) {
            ((PicturesPickerPresenter) this.mPresenter).enterSelectionMode(getContext().getString(R.string.done), ((PicturesPickerPresenter) this.mPresenter).getMaxCount(), (EventContext.OnSelectionListener) null);
        } else {
            Log.pke(this.TAG, "null context");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate.unbindView();
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            GridHelper gridHelper = getGridHelper();
            savePinchDepth(i2);
            if (this.mPresenter != null) {
                Optional.ofNullable(gridHelper.getNotifyKey()).ifPresent(new b(21, this));
            }
            super.onGridChanged(i2, i7);
        }
    }

    public void openMediaData() {
        super.openMediaData();
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            this.mScreenShotFilterDelegate.openScreenShotFilterData();
        }
    }

    public void operateClipboard(boolean z) {
        updateContentViewPadding(z, true);
    }

    public void postAnalyticsLog() {
        super.postAnalyticsLog();
        if (((PicturesPickerPresenter) this.mPresenter).getLaunchMode() == LaunchModeType.ACTION_MULTIPLE_PICK) {
            this.mBlackboard.postEvent(EventMessage.obtain(1028, getScreenId()));
        }
    }

    public void resetScreenShotFilter() {
        this.mScreenShotFilterDelegate.updateScreenShotFilter();
    }

    public void savePinchDepth(String str, int i2) {
        StringCompat stringCompat = this.TAG;
        Log.pk(stringCompat, "savePinchDepth {" + str + "=" + i2 + "}");
        getGridHelper().saveGridDepth(getDepthFromGridSize(i2));
        getGridHelper().saveGridCount(i2);
    }

    public void startShrinkAnimation() {
        if (getParentFragment() != null) {
            this.mBlackboard.post("command://UiEventStartShrinkAnimation", (Object) null);
        } else {
            super.startShrinkAnimation();
        }
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        if (!BackPressUtils.supportPredictiveBack(getContext()) || isMoveMode() || isDrawerOpen() || isSearchToolbarFocused() || ((Integer) getBlackboard().read("data://picker_mode/stack_size", 0)).intValue() != 1) {
            return false;
        }
        return true;
    }

    public boolean supportTimeline() {
        try {
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlbumTimeline) || isGroupByDate()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePadding() {
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
    }

    public PicturesPickerAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new PicturesPickerAdapter(this, getLocationKey(), createHeaderView(), isRealRatioSupported());
    }

    public PicturesPickerPresenter createPresenter(IPicturesView iPicturesView) {
        return new PicturesPickerPresenter(this.mBlackboard, iPicturesView);
    }

    public void adjustAppbarHeightOffset(WindowInsets windowInsets) {
    }
}
