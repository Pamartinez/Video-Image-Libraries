package com.samsung.android.gallery.app.ui.list.picker.stories;

import W8.C0579a;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.picker.stories.StoriesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.stories.StoriesFragment;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPickerFragment<V extends IStoriesView, P extends StoriesPickerPresenter> extends StoriesFragment<V, P> {
    private GalleryToolbar mToolbar;

    private void disableAppbarLayout() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.setScrollEnable(false, getListView());
            this.mAppBarLayout.setExpanded(false);
        }
    }

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
            }
        }
    }

    public String getFragmentTag(String str) {
        return "StoriesPickerFragment";
    }

    public int getLayoutId() {
        return R.layout.fragment_stories_picker_layout;
    }

    public String getLocationWithExtraArgs() {
        return ArgumentsUtil.appendArgs(getLocationKey(), "fromPictureFrame", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
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

    public boolean onBackPressed() {
        return false;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        Optional.ofNullable((ViewStub) view.findViewById(R.id.appbar_container)).ifPresent(new C0579a(4));
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        ((StoriesPickerPresenter) this.mPresenter).enterSelectionMode((CharSequence) null, -1, (EventContext.OnSelectionListener) null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        disableAppbarLayout();
        initToolbarPickerMode();
        enterSelectionMode(0);
    }

    public void postAnalyticsLog() {
        super.postAnalyticsLog();
        if (((StoriesPickerPresenter) this.mPresenter).getLaunchMode() == LaunchModeType.ACTION_MULTIPLE_PICK) {
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

    public StoriesPickerPresenter<IStoriesView> createPresenter(IStoriesView iStoriesView) {
        return new StoriesPickerPresenter<>(this.mBlackboard, iStoriesView);
    }
}
