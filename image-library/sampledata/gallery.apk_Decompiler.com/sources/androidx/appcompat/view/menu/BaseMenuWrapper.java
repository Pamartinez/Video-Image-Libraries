package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class BaseMenuWrapper {
    final Context mContext;
    private SimpleArrayMap<SupportMenuItem, MenuItem> mMenuItems;
    private SimpleArrayMap<Object, SubMenu> mSubMenus;

    public BaseMenuWrapper(Context context) {
        this.mContext = context;
    }

    public final MenuItem getMenuItemWrapper(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.mMenuItems == null) {
            this.mMenuItems = new SimpleArrayMap<>();
        }
        MenuItem menuItem2 = this.mMenuItems.get(supportMenuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.mContext, supportMenuItem);
        this.mMenuItems.put(supportMenuItem, menuItemWrapperICS);
        return menuItemWrapperICS;
    }

    public final void internalClear() {
        SimpleArrayMap<SupportMenuItem, MenuItem> simpleArrayMap = this.mMenuItems;
        if (simpleArrayMap != null) {
            simpleArrayMap.clear();
        }
        SimpleArrayMap<Object, SubMenu> simpleArrayMap2 = this.mSubMenus;
        if (simpleArrayMap2 != null) {
            simpleArrayMap2.clear();
        }
    }

    public final void internalRemoveGroup(int i2) {
        if (this.mMenuItems != null) {
            int i7 = 0;
            while (i7 < this.mMenuItems.size()) {
                if (this.mMenuItems.keyAt(i7).getGroupId() == i2) {
                    this.mMenuItems.removeAt(i7);
                    i7--;
                }
                i7++;
            }
        }
    }

    public final void internalRemoveItem(int i2) {
        if (this.mMenuItems != null) {
            for (int i7 = 0; i7 < this.mMenuItems.size(); i7++) {
                if (this.mMenuItems.keyAt(i7).getItemId() == i2) {
                    this.mMenuItems.removeAt(i7);
                    return;
                }
            }
        }
    }

    public final SubMenu getSubMenuWrapper(SubMenu subMenu) {
        return subMenu;
    }
}
