package com.samsung.android.gallery.app.ui.list.picker.search.suggestion;

import E7.c;
import U5.b;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.search.suggestion.ISuggestionContainerView;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerFragment;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerPresenter;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionContainerPickerFragment<V extends ISuggestionContainerView, P extends SuggestionContainerPresenter> extends SuggestionContainerFragment<V, P> {
    private GalleryToolbar mToolbar;

    private int getContentViewTopPadding(boolean z) {
        return PickerUtil.getContentViewTopPadding(this.mBlackboard, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$0(View view, WindowInsets windowInsets) {
        PickerViewUtil.adjustTopAreaMargin(windowInsets, ((SuggestionContainerPresenter) this.mPresenter).getActivityToolBar(), ((SuggestionContainerPresenter) this.mPresenter).getClipboardView());
        PickerViewUtil.adjustContentAreaMargin(view, SystemUi.getToolBarHeight(view.getContext()) + WindowUtils.getSystemInsetsTop(windowInsets));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBackgroundAndSystemUiBarColor$1(boolean z, GalleryToolbar galleryToolbar) {
        galleryToolbar.setBackgroundColor(getContext(), z);
    }

    private void setBackgroundAndSystemUiBarColor(boolean z) {
        Optional.ofNullable(getToolbar()).ifPresent(new c(this, z, 10));
        this.mBlackboard.postEvent(EventMessage.obtain(1086, Boolean.valueOf(z)));
    }

    private void updateContentViewPadding(boolean z, boolean z3) {
        if (this.mMainLayout != null) {
            int contentViewTopPadding = getContentViewTopPadding(z);
            if (z3) {
                View view = this.mMainLayout;
                PickerViewUtil.setContentViewTopPaddingWithAnimation(view, view.getPaddingTop(), contentViewTopPadding);
                return;
            }
            PickerViewUtil.setContentViewTopPadding(this.mMainLayout, contentViewTopPadding);
        }
    }

    public String getFragmentTag(String str) {
        return "SuggestionContainerPickerFragment";
    }

    public int getLayoutId() {
        return R.layout.search_suggestion_container_picker;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        updateToolbar();
        updateSelectionToolBar();
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        Optional.ofNullable(view.getRootWindowInsets()).ifPresent(new b(23, this, view));
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
        return windowInsets;
    }

    public void onBindView(View view) {
        if (getActivity() != null) {
            this.mToolbar = (GalleryToolbar) getActivity().findViewById(R.id.activity_toolbar);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setBackgroundAndSystemUiBarColor(true);
    }

    public void onDestroy() {
        setBackgroundAndSystemUiBarColor(false);
        super.onDestroy();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setBackgroundAndSystemUiBarColor(true);
    }

    public void operateClipboard(boolean z) {
        updateContentViewPadding(z, true);
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public void updatePadding() {
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
    }

    public void updateSelectionToolBar() {
        if (isDestroyed() || !isViewActive()) {
            Log.w(this.TAG, "update selection toolbar. this is not active");
            return;
        }
        GalleryToolbar toolbar = getToolbar();
        if (toolbar == null) {
            Log.w(this.TAG, "ignore update selection toolbar. toolbar is null");
        } else if (isSinglePickLaunchMode()) {
            Log.w(this.TAG, "ignore update selection toolbar. not multiple pick");
        } else {
            toolbar.setSelectedCountInfo(Clipboard.getInstance(this.mBlackboard).getTotalCount(), -1, PickerUtil.getMaxPickCount(this.mBlackboard));
            invalidateOptionsMenu();
        }
    }

    public SuggestionContainerPickerPresenter createPresenter(ISuggestionContainerView iSuggestionContainerView) {
        return new SuggestionContainerPickerPresenter(this.mBlackboard, iSuggestionContainerView);
    }
}
