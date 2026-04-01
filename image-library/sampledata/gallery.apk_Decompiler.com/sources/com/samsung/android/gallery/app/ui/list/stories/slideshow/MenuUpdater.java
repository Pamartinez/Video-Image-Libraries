package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import android.view.Menu;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuUpdater {
    private final ISlideshowV2View mView;

    public MenuUpdater(ISlideshowV2View iSlideshowV2View) {
        this.mView = iSlideshowV2View;
    }

    private void updateMenu(MenuDataBinding menuDataBinding) {
        boolean z;
        boolean z3;
        boolean equals = Order.TIME_DESC.equals(this.mView.getSortType());
        boolean z7 = false;
        if (!this.mView.fromSelection() || !PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave)) {
            z = false;
        } else {
            z = true;
        }
        boolean supportReordering = this.mView.supportReordering();
        if (!supportReordering || !equals) {
            z3 = false;
        } else {
            z3 = true;
        }
        menuDataBinding.setVisible((int) R.id.action_order_asc, z3);
        if (supportReordering && !equals) {
            z7 = true;
        }
        menuDataBinding.setVisible((int) R.id.action_order_desc, z7);
        menuDataBinding.setVisible((int) R.id.action_share, z);
        menuDataBinding.setVisible((int) R.id.action_save_as_video, z);
    }

    public void prepareOptionsMenu(Menu menu, MenuDataBinding menuDataBinding) {
        if (menuDataBinding != null) {
            menuDataBinding.prepareOptionsMenu(menu);
            updateMenu(menuDataBinding);
        }
    }
}
