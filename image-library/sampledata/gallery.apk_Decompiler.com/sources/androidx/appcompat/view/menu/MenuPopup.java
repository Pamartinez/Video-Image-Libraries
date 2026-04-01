package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MenuPopup implements ShowableListMenu, MenuPresenter, AdapterView.OnItemClickListener {
    private Rect mEpicenterBounds;

    public static int measureIndividualMenuWidth(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i7 = 0;
        int i8 = 0;
        View view = null;
        for (int i10 = 0; i10 < count; i10++) {
            int itemViewType = listAdapter.getItemViewType(i10);
            if (itemViewType != i8) {
                view = null;
                i8 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i10, view, viewGroup);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i2) {
                return i2;
            }
            if (measuredWidth > i7) {
                i7 = measuredWidth;
            }
        }
        return i7;
    }

    public static boolean shouldPreserveIconSpacing(MenuBuilder menuBuilder) {
        int size = menuBuilder.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menuBuilder.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    public static MenuAdapter toMenuAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            return (MenuAdapter) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        }
        return (MenuAdapter) listAdapter;
    }

    public boolean closeMenuOnSubMenuOpened() {
        return true;
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }

    public int getId() {
        return 0;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        int i7;
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        MenuBuilder menuBuilder = toMenuAdapter(listAdapter).mAdapterMenu;
        MenuItem menuItem = (MenuItem) listAdapter.getItem(i2);
        if (closeMenuOnSubMenuOpened()) {
            i7 = 0;
        } else {
            i7 = 4;
        }
        menuBuilder.performItemAction(menuItem, this, i7);
    }

    public void setEpicenterBounds(Rect rect) {
        this.mEpicenterBounds = rect;
    }

    public abstract void setForceShowIcon(boolean z);

    public abstract void setHorizontalOffset(int i2);

    public abstract void setShowTitle(boolean z);

    public abstract void setVerticalOffset(int i2);

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }
}
