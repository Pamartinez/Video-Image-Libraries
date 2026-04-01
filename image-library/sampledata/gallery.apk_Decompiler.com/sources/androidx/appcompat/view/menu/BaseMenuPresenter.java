package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseMenuPresenter implements MenuPresenter {
    private MenuPresenter.Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public BaseMenuPresenter(Context context, int i2, int i7) {
        this.mSystemContext = context;
        this.mSystemInflater = LayoutInflater.from(context);
        this.mMenuLayoutRes = i2;
        this.mItemLayoutRes = i7;
    }

    public void addItemView(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.mMenuView).addView(view, i2);
    }

    public abstract void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public MenuView.ItemView createItemView(ViewGroup viewGroup) {
        return (MenuView.ItemView) this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    public MenuPresenter.Callback getCallback() {
        return this.mCallback;
    }

    public int getId() {
        return this.mId;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView itemView;
        if (view instanceof MenuView.ItemView) {
            itemView = (MenuView.ItemView) view;
        } else {
            itemView = createItemView(viewGroup);
        }
        bindItemView(menuItemImpl, itemView);
        return (View) itemView;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            MenuView menuView = (MenuView) this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false);
            this.mMenuView = menuView;
            menuView.initialize(this.mMenu);
            updateMenuView(true);
        }
        return this.mMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onSubMenuSelected(androidx.appcompat.view.menu.SubMenuBuilder r2) {
        /*
            r1 = this;
            androidx.appcompat.view.menu.MenuPresenter$Callback r0 = r1.mCallback
            if (r0 == 0) goto L_0x000e
            if (r2 == 0) goto L_0x0007
            goto L_0x0009
        L_0x0007:
            androidx.appcompat.view.menu.MenuBuilder r2 = r1.mMenu
        L_0x0009:
            boolean r1 = r0.onOpenSubMenu(r2)
            return r1
        L_0x000e:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.BaseMenuPresenter.onSubMenuSelected(androidx.appcompat.view.menu.SubMenuBuilder):boolean");
    }

    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    public void setId(int i2) {
        this.mId = i2;
    }

    public boolean shouldIncludeItem(int i2, MenuItemImpl menuItemImpl) {
        return true;
    }

    public void updateMenuView(boolean z) {
        MenuItemImpl menuItemImpl;
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup != null) {
            MenuBuilder menuBuilder = this.mMenu;
            int i2 = 0;
            if (menuBuilder != null) {
                menuBuilder.flagActionItems();
                ArrayList<MenuItemImpl> visibleItems = this.mMenu.getVisibleItems();
                int size = visibleItems.size();
                int i7 = 0;
                for (int i8 = 0; i8 < size; i8++) {
                    MenuItemImpl menuItemImpl2 = visibleItems.get(i8);
                    if (shouldIncludeItem(i7, menuItemImpl2)) {
                        View childAt = viewGroup.getChildAt(i7);
                        if (childAt instanceof MenuView.ItemView) {
                            menuItemImpl = ((MenuView.ItemView) childAt).getItemData();
                        } else {
                            menuItemImpl = null;
                        }
                        View itemView = getItemView(menuItemImpl2, childAt, viewGroup);
                        if (menuItemImpl2 != menuItemImpl) {
                            itemView.setPressed(false);
                            itemView.jumpDrawablesToCurrentState();
                        }
                        if (itemView != childAt) {
                            addItemView(itemView, i7);
                        }
                        i7++;
                    }
                }
                i2 = i7;
            }
            while (i2 < viewGroup.getChildCount()) {
                if (!filterLeftoverView(viewGroup, i2)) {
                    i2++;
                }
            }
        }
    }
}
