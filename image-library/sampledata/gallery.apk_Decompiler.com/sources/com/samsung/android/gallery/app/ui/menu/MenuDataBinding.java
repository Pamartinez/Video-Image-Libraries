package com.samsung.android.gallery.app.ui.menu;

import A4.C0369d;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.menu.SeslMenuItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuDataBinding {
    private static final HashMap<String, Integer> CREATE_MENU_ID_MAP = new HashMap() {
        {
            Integer valueOf = Integer.valueOf(R.id.action_create_albums);
            put("location://albums", valueOf);
            put("location://albums/all", valueOf);
            put("location://sharing/albums", Integer.valueOf(R.id.action_create_shared_album));
            put("location://story/albums", Integer.valueOf(R.id.action_create_story_album));
            put("location://albums/fileList", Integer.valueOf(R.id.action_add_items_in_album));
        }
    };
    private static final HashMap<String, Integer> DELETE_MENU_ID_MAP = new HashMap() {
        {
            Integer valueOf = Integer.valueOf(R.id.action_delete_album_in_list);
            put("location://albums", valueOf);
            put("location://albums/all", valueOf);
            put("location://folder/root", valueOf);
            put("location://story/albums", Integer.valueOf(R.id.action_delete_story_album_in_list));
            put("location://sharing/albums", Integer.valueOf(R.id.action_delete_shared_album_in_list));
        }
    };
    private final Observer mCallback = new Observer() {
        public void update(Observable observable, Object obj) {
            if (MenuDataBinding.this.mPropertyChangedCallback != null) {
                MenuDataBinding.this.mPropertyChangedCallback.update(observable, obj);
            }
        }
    };
    private final SparseArray<MenuData> mList;
    private Menu mMenu;
    private final int mMenuResId;
    /* access modifiers changed from: private */
    public Observer mPropertyChangedCallback;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ItemMode {
        NOT_DEFINED,
        NO_ITEM,
        ANY_ITEM,
        SELECTED_ITEM
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class MenuData extends Observable {
        private int mGroupId;
        private int[] mIconArray;
        private int mIconResId;
        private int mIndex;
        private boolean mIsEnabled;
        private boolean mIsExcluded;
        private boolean mIsVisible;
        private final int mResId;
        /* access modifiers changed from: private */
        public int mShowAsAction = -1;
        private String mTitle;
        private int[] mTitleArray;

        public MenuData(int i2) {
            this.mResId = i2;
            this.mIsExcluded = getDefaultExcluding();
            this.mIsVisible = getDefaultVisibility();
            this.mIsEnabled = getDefaultEnabling();
            this.mIconResId = getDefaultIconResId();
            this.mGroupId = getDefaultGroupResId();
            this.mTitle = getDefaultTitle();
            this.mIconArray = getIconArray();
            this.mTitleArray = getTitleArray();
            int defaultIndex = getDefaultIndex();
            this.mIndex = defaultIndex;
            int[] iArr = this.mTitleArray;
            if (iArr != null) {
                this.mTitle = AppResources.getString(iArr[defaultIndex]);
            }
            int[] iArr2 = this.mIconArray;
            if (iArr2 != null) {
                this.mIconResId = iArr2[this.mIndex];
            }
        }

        private boolean valid(int i2) {
            int[] iArr = this.mTitleArray;
            if (iArr != null && i2 < iArr.length) {
                return true;
            }
            int[] iArr2 = this.mIconArray;
            if (iArr2 == null || i2 >= iArr2.length) {
                return false;
            }
            return true;
        }

        public boolean getDefaultEnabling() {
            return true;
        }

        public boolean getDefaultExcluding() {
            return false;
        }

        public int getDefaultGroupResId() {
            return -1;
        }

        public int getDefaultIconResId() {
            return -1;
        }

        public int getDefaultIndex() {
            return 0;
        }

        public String getDefaultTitle() {
            return this.mTitle;
        }

        public boolean getDefaultVisibility() {
            return false;
        }

        public int[] getIconArray() {
            return null;
        }

        public final int getIconId() {
            return this.mIconResId;
        }

        public final int getId() {
            return this.mResId;
        }

        public int[] getTitleArray() {
            return null;
        }

        public final boolean isAfw() {
            return Features.isEnabled(Features.IS_AFW_MODE);
        }

        public final boolean isDexMode(Blackboard blackboard) {
            if (blackboard == null || !DeviceInfo.isDexMode(BlackboardUtils.readActivity(blackboard))) {
                return false;
            }
            return true;
        }

        public final boolean isEnabled() {
            return this.mIsEnabled;
        }

        public final boolean isExcluded() {
            return this.mIsExcluded;
        }

        public final boolean isKnox() {
            return Features.isEnabled(Features.IS_KNOX_MODE);
        }

        public final boolean isUpsm() {
            return Features.isEnabled(Features.IS_UPSM);
        }

        public final boolean isVisible() {
            if (!this.mIsVisible || this.mIsExcluded) {
                return false;
            }
            return true;
        }

        public final void setEnabled(boolean z) {
            if (this.mIsEnabled != z) {
                this.mIsEnabled = z;
                if (!this.mIsExcluded) {
                    notifyObservers();
                }
            }
        }

        public final void setGroupId(int i2) {
            if (this.mGroupId != i2) {
                this.mGroupId = i2;
            }
        }

        public final boolean setIndex(IntSupplier intSupplier) {
            return !this.mIsExcluded && setIndex(intSupplier.getAsInt());
        }

        public final void setShowAsAction(int i2) {
            this.mShowAsAction = i2;
        }

        public final void setVisible(boolean z) {
            if (this.mIsVisible != z) {
                this.mIsVisible = z;
                if (!this.mIsExcluded) {
                    setChanged();
                    notifyObservers();
                }
            }
        }

        public void updateTitleArray() {
            int i2;
            int[] titleArray = getTitleArray();
            if (!Arrays.equals(this.mTitleArray, titleArray)) {
                this.mTitleArray = titleArray;
                if (titleArray != null && (i2 = this.mIndex) < titleArray.length) {
                    this.mTitle = AppResources.getString(titleArray[i2]);
                }
            }
        }

        public final boolean setIndex(int i2) {
            if (this.mIndex == i2 || !valid(i2)) {
                return false;
            }
            this.mIndex = i2;
            int[] iArr = this.mIconArray;
            if (iArr != null) {
                this.mIconResId = iArr[i2];
            }
            int[] iArr2 = this.mTitleArray;
            if (iArr2 != null) {
                this.mTitle = AppResources.getString(iArr2[i2]);
            }
            if (this.mIsExcluded) {
                return true;
            }
            notifyObservers();
            return true;
        }

        public MenuData(int i2, boolean z, boolean z3) {
            this.mResId = i2;
            this.mIsVisible = z;
            this.mIsExcluded = z3;
            this.mIsEnabled = getDefaultEnabling();
            this.mIconResId = getDefaultIconResId();
            this.mGroupId = getDefaultGroupResId();
            this.mTitle = getDefaultTitle();
            this.mIconArray = getIconArray();
            this.mTitleArray = getTitleArray();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MenuGroupIdMapHolder {
        private static final HashMap<ItemMode, Integer> map = new HashMap<ItemMode, Integer>() {
            {
                put(ItemMode.NO_ITEM, Integer.valueOf(R.id.no_item));
                put(ItemMode.ANY_ITEM, Integer.valueOf(R.id.normal_mode));
                put(ItemMode.SELECTED_ITEM, Integer.valueOf(R.id.select_mode));
            }
        };

        public static int get(ItemMode itemMode) {
            Integer num = map.get(itemMode);
            if (num == null) {
                return R.id.normal_mode;
            }
            return num.intValue();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SelectionMode {
        NORMAL,
        SELECT,
        SELECT_DONE
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleMenuData extends MenuData {
        public SimpleMenuData(int i2) {
            super(i2, false, true);
        }

        public SimpleMenuData(int i2, boolean z) {
            super(i2, z, false);
        }

        public SimpleMenuData(int i2, boolean z, boolean z3) {
            super(i2, z, z3);
        }
    }

    public MenuDataBinding(int i2) {
        this.mMenuResId = i2;
        this.mList = new SparseArray<>();
    }

    public void addBinding(MenuData menuData) {
        menuData.addObserver(this.mCallback);
        this.mList.put(menuData.getId(), menuData);
    }

    public boolean checkSelectItemMode(ItemMode itemMode, MenuItem menuItem) {
        if (itemMode == ItemMode.SELECTED_ITEM) {
            return true;
        }
        return false;
    }

    public MenuItem findMenuItem(int i2) {
        Menu menu = this.mMenu;
        if (menu == null) {
            return null;
        }
        return menu.findItem(i2);
    }

    public MenuItem findMenuItemWithItemMode(int i2, ItemMode itemMode) {
        if (this.mMenu == null) {
            return null;
        }
        int i7 = MenuGroupIdMapHolder.get(itemMode);
        int size = this.mMenu.size();
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = this.mMenu.getItem(i8);
            if (item != null && item.getGroupId() == i7 && item.getItemId() == i2) {
                return item;
            }
        }
        return null;
    }

    public int getCreateMenuId(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        HashMap<String, Integer> hashMap = CREATE_MENU_ID_MAP;
        if (hashMap.containsKey(removeArgs)) {
            return hashMap.get(removeArgs).intValue();
        }
        return R.id.action_create;
    }

    public int getDeleteMenuId(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        HashMap<String, Integer> hashMap = DELETE_MENU_ID_MAP;
        if (hashMap.containsKey(removeArgs)) {
            return hashMap.get(removeArgs).intValue();
        }
        return R.id.action_delete;
    }

    public int getId() {
        return this.mMenuResId;
    }

    public MenuData getMenuData(int i2) {
        MenuData menuData = this.mList.get(i2);
        if (menuData == null || menuData.isExcluded()) {
            return null;
        }
        return menuData;
    }

    public boolean getVisibility(int i2) {
        MenuData menuData = this.mList.get(i2);
        assertNoMenuData(i2, menuData);
        if (menuData == null || !menuData.isVisible()) {
            return false;
        }
        return true;
    }

    public boolean hasBadge(MenuItem menuItem) {
        return (menuItem instanceof SeslMenuItem) && ((SeslMenuItem) menuItem).getBadgeText() != null;
    }

    public boolean hasBinding(int i2) {
        MenuData menuData = this.mList.get(i2);
        if (menuData == null || menuData.isExcluded() || !hasItem(i2)) {
            return false;
        }
        return true;
    }

    public boolean hasItem(int i2) {
        Menu menu = this.mMenu;
        if (menu == null || menu.findItem(i2) == null) {
            return false;
        }
        return true;
    }

    public boolean hasVisibleItems() {
        Menu menu = this.mMenu;
        if (menu == null || !menu.hasVisibleItems()) {
            return false;
        }
        return true;
    }

    public boolean isItemVisible(int i2) {
        MenuItem menuItem;
        Menu menu = this.mMenu;
        if (menu == null) {
            menuItem = null;
        } else {
            menuItem = menu.findItem(i2);
        }
        if (menuItem == null || !menuItem.isVisible()) {
            return false;
        }
        return true;
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuItem findItem;
        for (int i2 = 0; i2 < this.mList.size(); i2++) {
            SparseArray<MenuData> sparseArray = this.mList;
            MenuData menuData = sparseArray.get(sparseArray.keyAt(i2));
            if (!(menuData == null || (findItem = menu.findItem(menuData.getId())) == null)) {
                if (findItem.isVisible() != menuData.isVisible()) {
                    findItem.setVisible(menuData.isVisible());
                }
                if (findItem.isEnabled() != menuData.isEnabled()) {
                    findItem.setEnabled(menuData.isEnabled());
                }
                if (menuData.getIconId() > 0) {
                    findItem.setIcon(menuData.getIconId());
                }
                if (menuData.mShowAsAction >= 0) {
                    findItem.setShowAsAction(menuData.mShowAsAction);
                }
                menuData.setGroupId(findItem.getGroupId());
            }
        }
    }

    public void setBadge(int i2, String str) {
        Menu menu = this.mMenu;
        if (menu != null) {
            MenuItem findItem = menu.findItem(i2);
            if (findItem instanceof SeslMenuItem) {
                ((SeslMenuItem) findItem).setBadgeText(str);
            }
        }
    }

    public boolean setEnabled(int i2, boolean z) {
        MenuData menuData = this.mList.get(i2);
        assertNoMenuData(i2, menuData);
        if (menuData == null || menuData.isEnabled() == z) {
            return false;
        }
        menuData.setEnabled(z);
        return true;
    }

    public void setMenu(Menu menu) {
        this.mMenu = menu;
    }

    public void setNewBadge(int i2, boolean z) {
        String str;
        if (this.mMenu != null) {
            if (z) {
                str = AppResources.getString(R.string.new_badge_text);
            } else {
                str = null;
            }
            setBadge(i2, str);
        }
    }

    public void setPropertyChangedCallback(Observer observer) {
        this.mPropertyChangedCallback = observer;
    }

    public void setShowAsAction(int i2, int i7) {
        Optional.ofNullable(getMenuData(i2)).ifPresent(new C0369d(i7, 18));
    }

    public boolean setVisible(int i2, BooleanSupplier booleanSupplier) {
        boolean asBoolean;
        MenuData menuData = this.mList.get(i2);
        if (menuData == null || menuData.isExcluded() || menuData.isVisible() == (asBoolean = booleanSupplier.getAsBoolean())) {
            return false;
        }
        menuData.setVisible(asBoolean);
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r10 == com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode.SELECTED_ITEM) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        if (checkSelectItemMode(r10, r1) == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0075, code lost:
        if (checkSelectItemMode(r10, r1) != false) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0080, code lost:
        if (r10 == com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode.ANY_ITEM) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0087, code lost:
        if (r10 == com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode.NO_ITEM) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateGroupVisible(android.view.Menu r8, com.samsung.android.gallery.app.ui.menu.MenuDataBinding.SelectionMode r9, com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode r10, int r11, boolean r12) {
        /*
            r7 = this;
            r11 = 0
            r0 = r11
        L_0x0002:
            int r1 = r8.size()
            if (r0 >= r1) goto L_0x009f
            android.view.MenuItem r1 = r8.getItem(r0)
            android.util.SparseArray<com.samsung.android.gallery.app.ui.menu.MenuDataBinding$MenuData> r2 = r7.mList
            int r3 = r1.getItemId()
            java.lang.Object r2 = r2.get(r3)
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$MenuData r2 = (com.samsung.android.gallery.app.ui.menu.MenuDataBinding.MenuData) r2
            r3 = 1
            if (r2 == 0) goto L_0x0024
            boolean r4 = r2.isVisible()
            if (r4 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            r4 = r11
            goto L_0x0025
        L_0x0024:
            r4 = r3
        L_0x0025:
            if (r2 == 0) goto L_0x0037
            java.lang.String r5 = r2.getDefaultTitle()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x0032
            goto L_0x0037
        L_0x0032:
            java.lang.String r2 = r2.getDefaultTitle()
            goto L_0x003d
        L_0x0037:
            java.lang.CharSequence r2 = r1.getTitle()
            java.lang.String r2 = (java.lang.String) r2
        L_0x003d:
            int r5 = r1.getGroupId()
            r6 = 2131297781(0x7f0905f5, float:1.8213517E38)
            if (r5 == r6) goto L_0x0083
            r6 = 2131297792(0x7f090600, float:1.8213539E38)
            if (r5 == r6) goto L_0x0078
            switch(r5) {
                case 2131298198: goto L_0x006b;
                case 2131298199: goto L_0x005a;
                case 2131298200: goto L_0x006b;
                case 2131298201: goto L_0x006b;
                case 2131298202: goto L_0x004f;
                default: goto L_0x004e;
            }
        L_0x004e:
            goto L_0x009b
        L_0x004f:
            if (r4 == 0) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$SelectionMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.SelectionMode.SELECT_DONE
            if (r9 != r4) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$ItemMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode.SELECTED_ITEM
            if (r10 != r4) goto L_0x005c
            goto L_0x0089
        L_0x005a:
            if (r12 == 0) goto L_0x005e
        L_0x005c:
            r3 = r11
            goto L_0x0089
        L_0x005e:
            if (r4 == 0) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$SelectionMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.SelectionMode.SELECT
            if (r9 != r4) goto L_0x005c
            boolean r4 = r7.checkSelectItemMode(r10, r1)
            if (r4 == 0) goto L_0x005c
            goto L_0x0089
        L_0x006b:
            if (r4 == 0) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$SelectionMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.SelectionMode.SELECT
            if (r9 != r4) goto L_0x005c
            boolean r4 = r7.checkSelectItemMode(r10, r1)
            if (r4 == 0) goto L_0x005c
            goto L_0x0089
        L_0x0078:
            if (r4 == 0) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$SelectionMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.SelectionMode.NORMAL
            if (r9 != r4) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$ItemMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode.ANY_ITEM
            if (r10 != r4) goto L_0x005c
            goto L_0x0089
        L_0x0083:
            if (r4 == 0) goto L_0x005c
            com.samsung.android.gallery.app.ui.menu.MenuDataBinding$ItemMode r4 = com.samsung.android.gallery.app.ui.menu.MenuDataBinding.ItemMode.NO_ITEM
            if (r10 != r4) goto L_0x005c
        L_0x0089:
            boolean r4 = r1.isVisible()
            if (r4 == r3) goto L_0x0092
            r1.setVisible(r3)
        L_0x0092:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x009b
            r1.setTitle(r2)
        L_0x009b:
            int r0 = r0 + 1
            goto L_0x0002
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.menu.MenuDataBinding.updateGroupVisible(android.view.Menu, com.samsung.android.gallery.app.ui.menu.MenuDataBinding$SelectionMode, com.samsung.android.gallery.app.ui.menu.MenuDataBinding$ItemMode, int, boolean):void");
    }

    public boolean hasBadge(int i2) {
        return hasBadge(findMenuItem(i2));
    }

    public boolean setVisible(int i2, boolean z) {
        MenuData menuData = this.mList.get(i2);
        assertNoMenuData(i2, menuData);
        if (menuData == null || menuData.isExcluded() || menuData.isVisible() == z) {
            return false;
        }
        menuData.setVisible(z);
        return true;
    }

    public void assertNoMenuData(int i2, MenuData menuData) {
    }
}
