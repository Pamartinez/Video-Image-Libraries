package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.menu;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.Mode;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.abstraction.SelectInfoSupplier;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuDelegatorOneUi61 extends AbsMenuDelegator {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MenuFactory {
        public static MenuDataBinding create(IBaseListView iBaseListView) {
            int i2;
            if (isOnDemandStory(iBaseListView)) {
                i2 = R.menu.menu_story_highlight_list_on_demand;
            } else {
                i2 = R.menu.menu_story_highlight_list_oneui61;
            }
            return new MenuDataBinding(i2);
        }

        private static boolean isOnDemandStory(IBaseListView iBaseListView) {
            return "location://stories/category/ondemand".equals(ArgumentsUtil.getArgValue(iBaseListView.getLocationKey(), "categoryKey", ""));
        }
    }

    public MenuDelegatorOneUi61(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
        super(iBaseListView, iMenuDelegation);
    }

    private int getEnabledGroupId(Mode mode) {
        if (Mode.EDIT_CURATION.equals(mode)) {
            return R.id.select_mode_with_done;
        }
        if (Mode.SELECT.equals(mode)) {
            return R.id.select_mode_bottom;
        }
        return R.id.normal_mode;
    }

    private void inflateMenu() {
        if (this.mMenuMode == null) {
            this.mMenu.clear();
            this.mToolbar.inflateMenu(this.mMenuDataBinding.getId());
            this.mMenuDataBinding.setMenu(this.mMenu);
        }
    }

    private boolean isSelectedAll() {
        SelectInfoSupplier selectInfoSupplier = getSelectInfoSupplier();
        if (selectInfoSupplier == null || !selectInfoSupplier.isAllSelected()) {
            return false;
        }
        return true;
    }

    private boolean supportAddContentToStory() {
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "categoryKey");
        if (argValue == null || !argValue.startsWith("location://stories/category/trip")) {
            return true;
        }
        return false;
    }

    private boolean supportAddToSharedAlbum() {
        if (!MdeSharingService.getInstance().isServiceSupported() || isUpsm()) {
            return false;
        }
        return true;
    }

    private void updateMenuItemState(MenuItem menuItem, boolean z) {
        boolean z3;
        MenuDataBinding.MenuData menuData = this.mMenuDataBinding.getMenuData(menuItem.getItemId());
        if (menuData != null) {
            z = z & menuData.isExcluded() & menuData.isVisible();
            z3 = menuData.isEnabled();
        } else {
            z3 = true;
        }
        if (z != menuItem.isVisible()) {
            menuItem.setVisible(z);
        }
        if (z3 != menuItem.isEnabled()) {
            menuItem.setEnabled(z3);
        }
        updateViewAlpha(menuItem.getItemId(), z3);
    }

    private void updateMenuVisibility(Mode mode, MenuItem menuItem, boolean z) {
        if (menuItem != null) {
            updateMenuItemState(menuItem, z);
            if (Mode.SELECT.equals(mode) && z) {
                if (menuItem.getItemId() == R.id.action_remove_from_story_in_list) {
                    menuItem.setVisible(!isSelectedAll());
                }
                if (menuItem.getItemId() == R.id.action_add_items_to_shared_album_on_selection) {
                    menuItem.setVisible(supportAddToSharedAlbum());
                }
            } else if (Mode.CURATION.equals(mode) && z && menuItem.getItemId() == R.id.action_add_content_to_story) {
                menuItem.setVisible(supportAddContentToStory());
            }
        }
    }

    public MenuDataBinding createMenuDataBinding(IBaseListView iBaseListView) {
        return MenuFactory.create(iBaseListView);
    }

    public void invalidateOptionMenu(Mode mode) {
        boolean z;
        if (this.mMenu != null) {
            inflateMenu();
            this.mMenuMode = mode;
            int enabledGroupId = getEnabledGroupId(mode);
            for (int i2 = 0; i2 < this.mMenu.size(); i2++) {
                MenuItem item = this.mMenu.getItem(i2);
                if (item.getGroupId() == enabledGroupId) {
                    z = true;
                } else {
                    z = false;
                }
                updateMenuVisibility(mode, item, z);
            }
            if (Mode.SELECT.equals(mode)) {
                updateBottomBarMenuAction(this.mMenu);
            }
            hideBottomSelectionMenu(this.mMenu);
            updateDoneMenu(mode);
            updateCancelMenu(mode);
        }
    }

    public void updateCancelMenu(Mode mode) {
        boolean z;
        MenuItem findItem = this.mMenu.findItem(R.id.action_cancel);
        if (Mode.REMOVE.equals(mode) || Mode.SELECT.equals(mode)) {
            z = true;
        } else {
            z = false;
        }
        if (findItem != null && findItem.isVisible() != z) {
            findItem.setVisible(z);
        }
    }
}
