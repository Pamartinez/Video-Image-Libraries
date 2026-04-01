package com.samsung.android.gallery.app.ui.list.picker.stories;

import W8.C0579a;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.picker.stories.StoriesPickerPresenterV2;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPickerFragmentV2<V extends IStoriesPinchView, P extends StoriesPickerPresenterV2> extends StoriesPinchViewFragment<V, P> {
    private GalleryToolbar mToolbar;

    private void initToolbarPickerMode() {
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null) {
            Blackboard blackboard = this.mBlackboard;
            toolbar.enterPickerMode(blackboard, PickerUtil.getUsageTitle(blackboard));
            if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
                if (Clipboard.getInstance(this.mBlackboard).getTotalCount() == 0) {
                    toolbar.setSelectedCountInfo(0, -1, PickerUtil.getMaxPickCount(this.mBlackboard));
                }
                toolbar.setCheckBoxVisible(false);
                return;
            }
            updateToolbar();
            invalidateToolbar();
        }
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        super.adjustToolbarLayout(windowInsets);
        ViewMarginUtils.setTopPadding((View) this.mToolbar.getParent(), WindowUtils.getSystemInsetsTop(windowInsets));
    }

    public View createHeaderView() {
        return null;
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoriesPickerAdapter(this, getLocationKey(), createHeaderView());
    }

    public String getFragmentTag(String str) {
        return "StoriesPickerFragmentV2";
    }

    public int getLayoutId() {
        return R.layout.fragment_stories_picker_layout;
    }

    public String getLocationWithExtraArgs() {
        return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(getLocationKey(), "fromPictureFrame", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE), "from_picker", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public String getScreenId() {
        return null;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            GalleryToolbar toolbar = getToolbar();
            if (toolbar != null) {
                Blackboard blackboard = this.mBlackboard;
                toolbar.enterPickerMode(blackboard, PickerUtil.getUsageTitle(blackboard));
            }
            updateToolbar();
            invalidateToolbar();
        }
    }

    public boolean isFadingEdgeExtended() {
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(view, windowInsets);
        getListView().updateGoToTopBottomPadding((float) getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
        return onApplyWindowInsets;
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        Optional.ofNullable((ViewStub) view.findViewById(R.id.appbar_container)).ifPresent(new C0579a(4));
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            ((StoriesPickerPresenterV2) this.mPresenter).enterSelectionMode((CharSequence) null, -1, (EventContext.OnSelectionListener) null);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initToolbarPickerMode();
        if (PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            enterSelectionMode(0);
        }
    }

    public void postAnalyticsLog() {
        super.postAnalyticsLog();
        if (((StoriesPickerPresenterV2) this.mPresenter).getLaunchMode() == LaunchModeType.ACTION_MULTIPLE_PICK) {
            this.mBlackboard.postEvent(EventMessage.obtain(1028, getScreenId()));
        }
    }

    public void setScreenMode() {
        enterFullListScreen(false);
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public boolean supportLongClick() {
        return false;
    }

    public boolean supportPinView() {
        return false;
    }

    public boolean supportTabLayout() {
        return false;
    }

    public StoriesPickerPresenterV2<IStoriesPinchView> createPresenter(IStoriesPinchView iStoriesPinchView) {
        return new StoriesPickerPresenterV2<>(this.mBlackboard, iStoriesPinchView);
    }

    public void onListItemFavoriteClick(View view, MediaItem mediaItem, int i2) {
    }

    public void updateFavorite(int i2, int i7, boolean z) {
    }
}
