package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.R$dimen;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuAdapter extends BaseAdapter {
    MenuBuilder mAdapterMenu;
    private int mExpandedIndex = -1;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private int mInitPaddingBottom;
    private int mInitPaddingTop;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i2) {
        this.mOverflowOnly = z;
        this.mInflater = layoutInflater;
        this.mAdapterMenu = menuBuilder;
        this.mItemLayoutRes = i2;
        findExpandedIndex();
    }

    public void findExpandedIndex() {
        MenuItemImpl expandedItem = this.mAdapterMenu.getExpandedItem();
        if (expandedItem != null) {
            ArrayList<MenuItemImpl> nonActionItems = this.mAdapterMenu.getNonActionItems();
            int size = nonActionItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (nonActionItems.get(i2) == expandedItem) {
                    this.mExpandedIndex = i2;
                    return;
                }
            }
        }
        this.mExpandedIndex = -1;
    }

    public MenuBuilder getAdapterMenu() {
        return this.mAdapterMenu;
    }

    public int getCount() {
        ArrayList<MenuItemImpl> arrayList;
        if (this.mOverflowOnly) {
            arrayList = this.mAdapterMenu.getNonActionItems();
        } else {
            arrayList = this.mAdapterMenu.getVisibleItems();
        }
        if (this.mExpandedIndex < 0) {
            return arrayList.size();
        }
        return arrayList.size() - 1;
    }

    public long getItemId(int i2) {
        return (long) i2;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        int i7;
        boolean z;
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayoutRes, viewGroup, false);
            this.mInitPaddingTop = view.getPaddingTop();
            this.mInitPaddingBottom = view.getPaddingBottom();
        }
        int groupId = getItem(i2).getGroupId();
        int i8 = i2 - 1;
        if (i8 >= 0) {
            i7 = getItem(i8).getGroupId();
        } else {
            i7 = groupId;
        }
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (!this.mAdapterMenu.isGroupDividerEnabled() || groupId == i7) {
            z = false;
        } else {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.mForceShowIcon) {
            listMenuItemView.setForceShowIcon(true);
        }
        itemView.initialize(getItem(i2), 0);
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R$dimen.sesl_popup_menu_first_last_item_vertical_edge_padding);
        int i10 = this.mInitPaddingTop + dimensionPixelSize;
        int i11 = this.mInitPaddingBottom + dimensionPixelSize;
        int paddingLeft = view.getPaddingLeft();
        if (i2 != 0) {
            i10 = this.mInitPaddingTop;
        }
        int paddingRight = view.getPaddingRight();
        if (i2 != getCount() - 1) {
            i11 = this.mInitPaddingBottom;
        }
        view.setPadding(paddingLeft, i10, paddingRight, i11);
        return view;
    }

    public boolean isEnabled(int i2) {
        ArrayList<MenuItemImpl> arrayList;
        if (this.mOverflowOnly) {
            arrayList = this.mAdapterMenu.getNonActionItems();
        } else {
            arrayList = this.mAdapterMenu.getVisibleItems();
        }
        return arrayList.get(i2).isEnabled();
    }

    public void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public MenuItemImpl getItem(int i2) {
        ArrayList<MenuItemImpl> nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        int i7 = this.mExpandedIndex;
        if (i7 >= 0 && i2 >= i7) {
            i2++;
        }
        return nonActionItems.get(i2);
    }
}
