package com.samsung.android.gallery.app.ui.list.picker.search;

import A4.C0371f;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.search.CollectionFragment;
import com.samsung.android.gallery.app.ui.list.search.CollectionPresenter;
import com.samsung.android.gallery.app.ui.list.search.ICollectionView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollectionPickerFragment<V extends ICollectionView, P extends CollectionPresenter<V>> extends CollectionFragment<V, P> {
    View mMainLayout;

    private int getContentViewTopPadding(boolean z) {
        return PickerUtil.getContentViewTopPadding(this.mBlackboard, z);
    }

    private boolean isClipboardOpened() {
        return ((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onApplyWindowInsets$0(WindowInsets windowInsets, View view, WindowInsets windowInsets2) {
        PickerViewUtil.adjustViewAreaMargin(windowInsets, ((CollectionPresenter) this.mPresenter).getActivityToolBar(), ((CollectionPresenter) this.mPresenter).getClipboardView(), view);
    }

    private void updateContentViewPadding(boolean z, boolean z3) {
        if (this.mMainLayout != null) {
            int contentViewTopPadding = getContentViewTopPadding(z);
            if (z3) {
                PickerViewUtil.setContentViewTopPaddingWithAnimation(this.mMainLayout, contentViewTopPadding);
            } else {
                PickerViewUtil.setContentViewTopPadding(this.mMainLayout, contentViewTopPadding);
            }
            this.mMainLayout.invalidate();
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = view.findViewById(R.id.main_layout);
        this.mToolbar = GalleryToolbar.findActivityToolbar(view);
        this.mRecyclerView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.BOTTOM);
    }

    public int getLayoutId() {
        return R.layout.fragment_search_picker_layout;
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (!isConfigStateChanged(2)) {
            updateContentViewPadding(isClipboardOpened(), false);
        }
    }

    public boolean isFadingEdgeExtended() {
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        Optional.ofNullable(view.getRootWindowInsets()).ifPresent(new C0371f((Object) this, (Object) windowInsets, (Object) view, 17));
        updateContentViewPadding(((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue(), false);
        return windowInsets;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateContentViewPadding(isClipboardOpened(), false);
    }

    public void operateClipboard(boolean z) {
        updateContentViewPadding(z, true);
    }

    public boolean supportActivityToolbar() {
        return true;
    }

    public void updateToolbarSelectionCount(GalleryToolbar galleryToolbar) {
        if (galleryToolbar == null || isDestroyed() || !PickerUtil.isMultiplePickLaunchMode(this.mBlackboard)) {
            super.updateToolbarSelectionCount(galleryToolbar);
        } else {
            galleryToolbar.setSelectedCountInfo(Clipboard.getInstance(this.mBlackboard).getTotalCount(), getTotalSelectableCount(), PickerUtil.getMaxPickCount(this.mBlackboard));
        }
    }

    public CollectionPresenter<?> createPresenter(ICollectionView iCollectionView) {
        return new CollectionPresenter<ICollectionView>(this.mBlackboard, iCollectionView) {
            public MenuDataBinding createMenuDataBinding() {
                return null;
            }
        };
    }
}
