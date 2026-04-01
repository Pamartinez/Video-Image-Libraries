package com.samsung.android.gallery.app.ui.list.picker.search;

import A4.C0371f;
import U9.b;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesPickerFragment<V extends ISearchPicturesView, P extends SearchPicturesPickerPresenter> extends SearchPicturesFragment<V, P> {
    private ViewGroup mSearchToolbarArea;
    private GalleryToolbar mToolbar;

    private int getContentViewTopPadding(boolean z) {
        return PickerUtil.getContentViewTopPadding(this.mBlackboard, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$1(WindowInsets windowInsets, View view, WindowInsets windowInsets2) {
        PickerViewUtil.adjustViewAreaMargin(windowInsets, ((SearchPicturesPickerPresenter) this.mPresenter).getActivityToolBar(), ((SearchPicturesPickerPresenter) this.mPresenter).getClipboardView(), view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEmptyViews$0(View view) {
        setEmptyViewMargin(view.getRootWindowInsets());
    }

    private void setEmptyViewMargin(WindowInsets windowInsets) {
        if (isEmptyViewShowing()) {
            PickerViewUtil.adjustEmptyViewMargin(this.mEmptyView, WindowUtils.getIMEInsetsBottom(windowInsets) + WindowUtils.getSystemInsetsBottom(windowInsets));
        }
    }

    private void updateContentViewPadding(boolean z, boolean z3) {
        if (this.mMainLayout != null) {
            int contentViewTopPadding = getContentViewTopPadding(z);
            if (z3) {
                ViewGroup viewGroup = this.mMainLayout;
                PickerViewUtil.setContentViewTopPaddingWithAnimation(viewGroup, viewGroup.getPaddingTop(), contentViewTopPadding);
                return;
            }
            PickerViewUtil.setContentViewTopPadding(this.mMainLayout, contentViewTopPadding);
        }
    }

    private void updatePadding() {
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSearchToolbarArea = (ViewGroup) view.findViewById(R.id.search_toolbar_area);
        this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
    }

    public int getLayoutId() {
        return R.layout.fragment_search_pictures_picker_layout;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public boolean isPicker() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        Optional.ofNullable(view.getRootWindowInsets()).ifPresent(new C0371f((Object) this, (Object) windowInsets, (Object) view, 20));
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
        setEmptyViewMargin(windowInsets);
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
        updatePadding();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (getContext() != null) {
            ((SearchPicturesPickerPresenter) this.mPresenter).enterSelectionMode(getContext().getString(R.string.done), ((SearchPicturesPickerPresenter) this.mPresenter).getMaxCount(), (EventContext.OnSelectionListener) null);
        } else {
            Log.pke(this.TAG, "null context");
        }
    }

    public void operateClipboard(boolean z) {
        updateContentViewPadding(z, true);
    }

    public void setBackgroundAndSystemUiBarColor(boolean z) {
        int i2;
        super.setBackgroundAndSystemUiBarColor(z);
        ViewGroup viewGroup = this.mSearchToolbarArea;
        Context context = getContext();
        if (z) {
            i2 = R.color.actionbar_fw_bg_color;
        } else {
            i2 = R.color.actionbar_bg_color;
        }
        viewGroup.setBackgroundColor(context.getColor(i2));
    }

    public void setEnabledHeader(boolean z) {
        this.mMultipleHeaderContainer.setEnabled(true, false);
    }

    public void setOptionsMenu(boolean z) {
        super.setOptionsMenu(false);
    }

    public void startShrinkAnimation() {
        this.mBlackboard.post("command://UiEventStartShrinkAnimation", (Object) null);
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public boolean supportTabLayout() {
        return LocationKey.isSearchKeywordTab(getLocationKey());
    }

    public void updateEmptyViews() {
        super.updateEmptyViews();
        Optional.ofNullable(getView()).ifPresent(new b(27, this));
    }

    public void updateToolbarSelectionCount(GalleryToolbar galleryToolbar) {
        if (!supportTabLayout() || galleryToolbar == null || isDestroyed() || !PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            super.updateToolbarSelectionCount(galleryToolbar);
        } else {
            galleryToolbar.setSelectedCountInfo(Clipboard.getInstance(this.mBlackboard).getTotalCount(), getTotalSelectableCount(), PickerUtil.getMaxPickCount(this.mBlackboard));
        }
    }

    public PicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        View createHeaderView = createHeaderView();
        if (createHeaderView != null) {
            return new SearchPicturesPickerAdapter(this, getLocationKey(), createHeaderView, isSupportRealRatio());
        }
        return super.createListViewAdapter(galleryListView);
    }

    public SearchPicturesPickerPresenter createPresenter(ISearchPicturesView iSearchPicturesView) {
        return new SearchPicturesPickerPresenter(this.mBlackboard, iSearchPicturesView);
    }

    public void requestFocusToSearchToolbarCloseButton() {
    }
}
