package com.samsung.android.gallery.app.ui.list.search.category;

import Fa.C0571z;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CategoryMenuUpdater extends ListMenuUpdater {
    public CategoryMenuUpdater(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
        super(iBaseListView, iMenuDelegation);
    }

    private boolean isOneCreatureSelected() {
        if (this.mInterface.getSelectedItemCount() == 1) {
            return true;
        }
        return false;
    }

    private boolean isVisibleSearchMenu(MenuDataBinding.SelectionMode selectionMode) {
        boolean z = PreferenceFeatures.OneUi8x.IS_ONE_UI_80;
        return false;
    }

    private void updateAddMenu(MenuItem menuItem) {
        if (menuItem != null) {
            if (isOneCreatureSelected()) {
                setMenuVisibility(menuItem, true);
                if (Arrays.stream(this.mInterface.getSelectedItems()).noneMatch(new e(2))) {
                    setMenuTitle(menuItem, R.string.add_name);
                } else {
                    setMenuTitle(menuItem, R.string.edit_name);
                }
            } else {
                setMenuVisibility(menuItem, false);
            }
        }
    }

    private void updateMergeMenu(MenuItem menuItem) {
        if (menuItem != null) {
            if (this.mInterface.getSelectedItemCount() < 2) {
                setMenuVisibility(menuItem, false);
                return;
            }
            setMenuVisibility(menuItem, true);
            if (Arrays.stream(this.mInterface.getSelectedItems()).allMatch(new C0571z(18))) {
                setMenuIcon(menuItem, R.drawable.gallery_ic_menu_group);
            } else if (Arrays.stream(this.mInterface.getSelectedItems()).allMatch(new C0571z(19))) {
                setMenuIcon(menuItem, R.drawable.gallery_ic_search_edit_pet_name_group);
            } else {
                setMenuVisibility(menuItem, false);
            }
        }
    }

    private void updateSelectDoneMenu(MenuItem menuItem) {
        setMenuVisibility(menuItem, isVisibleSelectDoneMenu());
        setMenuAvailability(menuItem, isEnableSelectDoneMenu());
        int selectDoneMenuTitle = getSelectDoneMenuTitle();
        if (selectDoneMenuTitle > 0) {
            setMenuTitle(menuItem, selectDoneMenuTitle);
        }
    }

    public int getSelectDoneMenuTitle() {
        return -1;
    }

    public boolean isEnableSelectDoneMenu() {
        return true;
    }

    public boolean isVisibleEditDoneMenu() {
        return false;
    }

    public boolean isVisibleHideMenu() {
        if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || getDataCount() <= 0 || !LocationKey.isSearchCategoryCreatureMatch(getLocationKey())) {
            return false;
        }
        return true;
    }

    public boolean isVisibleSelectDoneMenu() {
        String removeArgs = ArgumentsUtil.removeArgs(getLocationKey());
        if ((PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE && (LocationKey.isSearchCategoryPeopleHideMatch(removeArgs) || LocationKey.isSearchCategoryPeopleAndPetsHideMatch(removeArgs))) || LocationKey.isSearchCategoryHiddenPeopleMatch(removeArgs) || LocationKey.isSearchCategoryHiddenPeopleAndPetsMatch(removeArgs)) {
            return true;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !LocationKey.isSearchCategoryPeopleSelectMatch(removeArgs) || getView().getDataCount() <= 0) {
            return false;
        }
        return true;
    }

    public void setMenuAvailability(MenuItem menuItem, boolean z) {
        if (menuItem != null) {
            menuItem.setEnabled(z);
        }
    }

    public void setMenuIcon(MenuItem menuItem, int i2) {
        if (menuItem != null) {
            menuItem.setIcon(i2);
        }
    }

    public void setMenuTitle(MenuItem menuItem, int i2) {
        if (menuItem != null) {
            menuItem.setTitle(getView().getResources().getString(i2));
        }
    }

    public void setMenuVisibility(MenuItem menuItem, boolean z) {
        if (menuItem != null) {
            menuItem.setVisible(z);
        }
    }

    public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
        setMenuVisibility(menu.findItem(R.id.action_search), isVisibleSearchMenu(selectionMode));
        setMenuVisibility(menu.findItem(R.id.action_hide_people_and_pets), isVisibleHideMenu());
        setMenuVisibility(menu.findItem(R.id.action_edit_done), isVisibleEditDoneMenu());
        updateSelectDoneMenu(menu.findItem(R.id.action_select_done));
        updateAddMenu(menu.findItem(R.id.action_add_name));
        updateMergeMenu(menu.findItem(R.id.action_merge_creature));
    }
}
