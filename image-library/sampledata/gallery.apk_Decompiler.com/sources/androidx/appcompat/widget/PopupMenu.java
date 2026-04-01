package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PopupMenu {
    private final View mAnchor;
    private final Context mContext;
    private final MenuBuilder mMenu;
    OnMenuItemClickListener mMenuItemClickListener;
    OnDismissListener mOnDismissListener;
    final MenuPopupHelper mPopup;
    private int mXOffset;
    private int mYOffset;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDismissListener {
        void onDismiss(PopupMenu popupMenu);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public PopupMenu(Context context, View view, int i2) {
        this(context, view, i2, R$attr.popupMenuStyle, 0);
    }

    public void dismiss() {
        this.mPopup.dismiss();
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContext);
    }

    public void seslForceShowUpper(boolean z) {
        MenuPopupHelper menuPopupHelper = this.mPopup;
        if (menuPopupHelper != null) {
            menuPopupHelper.seslForceShowUpper(z);
        }
    }

    public PopupWindow seslGetPopupWindow() {
        MenuPopupHelper menuPopupHelper = this.mPopup;
        if (menuPopupHelper != null) {
            return menuPopupHelper.seslGetPopupWindow();
        }
        return null;
    }

    public boolean seslIsShowing() {
        MenuPopupHelper menuPopupHelper = this.mPopup;
        if (menuPopupHelper == null || !menuPopupHelper.isShowing()) {
            return false;
        }
        return true;
    }

    public void seslSetHeight(int i2) {
        MenuPopupHelper menuPopupHelper = this.mPopup;
        if (menuPopupHelper != null) {
            menuPopupHelper.seslSetHeight(i2);
        }
    }

    public void seslSetOffset(int i2, int i7) {
        if (this.mAnchor.getLayoutDirection() == 1) {
            this.mXOffset = -i2;
        } else {
            this.mXOffset = i2;
        }
        this.mYOffset = i7;
    }

    public void seslSetOverflowOnly(boolean z) {
        this.mPopup.seslSetOverflowOnly(z);
    }

    public void seslSetOverlapAnchor(boolean z) {
        MenuPopupHelper menuPopupHelper = this.mPopup;
        if (menuPopupHelper != null) {
            menuPopupHelper.seslSetOverlapAnchor(z);
        }
    }

    public void setGravity(int i2) {
        this.mPopup.setGravity(i2);
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mMenuItemClickListener = onMenuItemClickListener;
    }

    public void show() {
        this.mPopup.show(this.mXOffset, this.mYOffset);
    }

    public PopupMenu(Context context, View view, int i2, int i7, int i8) {
        this.mXOffset = 0;
        this.mYOffset = 0;
        this.mContext = context;
        this.mAnchor = view;
        MenuBuilder menuBuilder = new MenuBuilder(context);
        this.mMenu = menuBuilder;
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                OnMenuItemClickListener onMenuItemClickListener = PopupMenu.this.mMenuItemClickListener;
                if (onMenuItemClickListener != null) {
                    return onMenuItemClickListener.onMenuItemClick(menuItem);
                }
                return false;
            }

            public void onMenuModeChange(MenuBuilder menuBuilder) {
            }
        });
        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(context, menuBuilder, view, false, i7, i8);
        this.mPopup = menuPopupHelper;
        menuPopupHelper.seslSetOverflowOnly(true);
        menuPopupHelper.setGravity(i2);
        menuPopupHelper.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                PopupMenu popupMenu = PopupMenu.this;
                OnDismissListener onDismissListener = popupMenu.mOnDismissListener;
                if (onDismissListener != null) {
                    onDismissListener.onDismiss(popupMenu);
                }
            }
        });
    }
}
