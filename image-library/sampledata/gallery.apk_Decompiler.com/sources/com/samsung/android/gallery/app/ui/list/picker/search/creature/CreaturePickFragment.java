package com.samsung.android.gallery.app.ui.list.picker.search.creature;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.picker.search.creature.CreaturePickPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreaturePickFragment<V extends ICreatureSelectView, P extends CreaturePickPresenter> extends CreatureSelectFragment<V, P> implements ICreatureSelectView {
    private void disableAppbarLayout() {
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.setScrollEnable(false, getListView());
            this.mAppBarLayout.setExpanded(false);
        }
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        super.adjustToolbarLayout(windowInsets);
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            ViewMarginUtils.setTopPadding((View) galleryToolbar.getParent(), WindowUtils.getSystemInsetsTop(windowInsets));
        }
    }

    public int getLayoutId() {
        return R.layout.fragment_people_picker_layout;
    }

    public int getSelectedItemCount() {
        return ((CreaturePickPresenter) this.mPresenter).getSelectedCreatures().size();
    }

    public MediaItem[] getSelectedItems() {
        return ((CreaturePickPresenter) this.mPresenter).getSelectedItems();
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(view, windowInsets);
        getListView().updateGoToTopBottomPadding((float) getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
        return onApplyWindowInsets;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        disableAppbarLayout();
    }

    public boolean supportExitPredictiveBack() {
        return BackPressUtils.supportPredictiveBack(getContext());
    }

    public boolean useActivityToolbar() {
        return false;
    }

    public CreaturePickPresenter createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new CreaturePickPresenter(this.mBlackboard, iCreatureSelectView);
    }

    public void adjustPickerViewAreaMargin(View view, WindowInsets windowInsets) {
    }

    public void updateContentViewPadding(boolean z, boolean z3) {
    }
}
