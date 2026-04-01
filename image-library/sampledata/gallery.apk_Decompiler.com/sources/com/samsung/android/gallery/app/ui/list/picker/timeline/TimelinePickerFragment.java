package com.samsung.android.gallery.app.ui.list.picker.timeline;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.timeline.TimelinePickerPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.ClusterPositionFinder;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineViewDummyAdapter;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelinePickerFragment<V extends IPicturesView, P extends TimelinePickerPresenter> extends PicturesPickerFragment<V, P> implements IPicturesView {
    private ClusterPositionFinder mClusterPositionFinder;
    private boolean mIsFirstLaunch = true;
    ViewStub mSmartAlbumLayoutStub;

    private void createToolbarSmartAlbumView() {
        if (supportSmartAlbum()) {
            SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
            if (smartAlbumHolder != null) {
                smartAlbumHolder.inflateSmartAlbumIfNecessary();
            }
            GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
            if (galleryAppBarLayout != null) {
                galleryAppBarLayout.setCollapsedHeightInPickMode(PickerUtil.getAppbarCollapsedHeight(galleryAppBarLayout.getContext()));
            }
        } else {
            GalleryAppBarLayout galleryAppBarLayout2 = this.mAppBarLayout;
            if (galleryAppBarLayout2 != null) {
                galleryAppBarLayout2.setScrollEnable(false, getListView());
                this.mAppBarLayout.setExpanded(false);
            }
        }
        if (this.mAppBarLayout != null && PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            this.mAppBarLayout.setMultiplePickMode();
            GalleryAppBarLayout galleryAppBarLayout3 = this.mAppBarLayout;
            galleryAppBarLayout3.setTopOffset(PickerUtil.getAppbarTopOffsetInMultiPick(galleryAppBarLayout3.getContext()));
        }
    }

    private void handleCommonConfigurationChanged() {
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null) {
            smartAlbumHolder.updateLayout();
        }
    }

    private boolean hasTargetCluster() {
        if (!this.mIsFirstLaunch) {
            return false;
        }
        this.mIsFirstLaunch = false;
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.hasTargetCluster()) {
            return false;
        }
        return true;
    }

    private void initToolbarPickerMode() {
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null) {
            Blackboard blackboard = this.mBlackboard;
            toolbar.enterPickerMode(blackboard, PickerUtil.getUsageTitle(blackboard));
            if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard) && Clipboard.getInstance(this.mBlackboard).getTotalCount() == 0) {
                toolbar.setSelectedCountInfo(0, -1, PickerUtil.getMaxPickCount(this.mBlackboard));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(int i2) {
        GalleryAppBarLayout galleryAppBarLayout;
        if (i2 > 0 && (galleryAppBarLayout = this.mAppBarLayout) != null) {
            galleryAppBarLayout.setExpanded(false, false);
        }
    }

    private boolean needRefreshScrollPosition(float f) {
        if (f < 1.0f || getListView() == null || getListView().computeVerticalScrollOffset() <= 0) {
            return false;
        }
        return true;
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSmartAlbumLayoutStub = (ViewStub) view.findViewById(R.id.smart_album_layout_stub);
    }

    public SmartAlbumHolder createSmartAlbumHolder() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH) {
            return null;
        }
        return new SmartAlbumHolder(this.mSmartAlbumLayoutStub, getBlackboard(), getLocationKey(), getAnalyticsScreenId(getScreenId()));
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new TimelineViewDummyAdapter(getListView(), getColumnCount());
    }

    public int getLayoutId() {
        return R.layout.fragment_timeline_picker_layout;
    }

    public String getScreenId() {
        if (this.mPresenter == null) {
            return null;
        }
        return AnalyticsScreenId.SCREEN_TIME_VIEW_PICK.toString();
    }

    public int getStartPinchDepth() {
        int gridDepth = getGridHelper().getGridDepth();
        if (this.mGridSpans.selectable(gridDepth)) {
            return gridDepth;
        }
        int indexOfNearSelectable = this.mGridSpans.indexOfNearSelectable(gridDepth);
        savePinchDepth(indexOfNearSelectable);
        Log.e((CharSequence) this.TAG, "getStartPinch changed", Integer.valueOf(gridDepth), Integer.valueOf(indexOfNearSelectable));
        return indexOfNearSelectable;
    }

    public int getViewTypeFirstTimeline() {
        return -2;
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

    public boolean isCheckingTargetCluster() {
        ClusterPositionFinder clusterPositionFinder = this.mClusterPositionFinder;
        if (clusterPositionFinder == null || !clusterPositionFinder.isCheckingTargetPosition()) {
            return false;
        }
        return true;
    }

    public void onAppbarVisibleRatio(float f) {
        super.onAppbarVisibleRatio(f);
        if (needRefreshScrollPosition(f)) {
            getListView().refreshScrollPosition();
        }
    }

    public void onDestroy() {
        ClusterPositionFinder clusterPositionFinder = this.mClusterPositionFinder;
        if (clusterPositionFinder != null) {
            clusterPositionFinder.onDestroy();
            this.mClusterPositionFinder = null;
        }
        super.onDestroy();
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            P p6 = this.mPresenter;
            if (p6 != null) {
                Blackboard.publishGlobal("command://TimelineViewChanged", Integer.valueOf(((TimelinePickerPresenter) p6).getCurrentViewDepth()));
            }
            super.onGridChanged(i2, i7);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        createToolbarSmartAlbumView();
        initToolbarPickerMode();
        if (hasTargetCluster() && this.mClusterPositionFinder == null) {
            this.mClusterPositionFinder = new ClusterPositionFinder(getListView(), new a(14, this));
        }
    }

    public void startPostponedEnterTransition() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.startPostponedEnterTransition();
        } else {
            super.startPostponedEnterTransition();
        }
    }

    public void startShrinkAnimation() {
        this.mBlackboard.post("command://UiEventStartShrinkAnimation", (Object) null);
    }

    public boolean supportExitPredictiveBack() {
        if (!BackPressUtils.supportPredictiveBack(getContext()) || isMoveMode() || isDrawerOpen() || isSearchToolbarFocused()) {
            return false;
        }
        return true;
    }

    public boolean supportSmartAlbum() {
        return !PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH;
    }

    public boolean supportTabLayout() {
        return true;
    }

    public boolean supportTimeline() {
        return true;
    }

    public boolean supportYearTimeline() {
        return this.mGridSpans.hasYear;
    }

    public TimelinePickerAdapter createListViewAdapter(GalleryListView galleryListView) {
        TimelinePickerAdapter timelinePickerAdapter = new TimelinePickerAdapter(this, getLocationKey(), isRealRatioSupported());
        ClusterPositionFinder clusterPositionFinder = this.mClusterPositionFinder;
        if (clusterPositionFinder != null) {
            clusterPositionFinder.setAdapter(timelinePickerAdapter);
            this.mClusterPositionFinder.checkFullLoadDone();
        }
        return timelinePickerAdapter;
    }

    public TimelinePickerPresenter createPresenter(IPicturesView iPicturesView) {
        return new TimelinePickerPresenter(this.mBlackboard, iPicturesView);
    }

    public void setSmartAlbumEnabled(boolean z) {
    }
}
