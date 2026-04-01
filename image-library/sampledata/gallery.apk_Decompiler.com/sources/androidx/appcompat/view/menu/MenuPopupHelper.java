package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuPopupHelper {
    private boolean mAllowScrollingAnchorParent;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private boolean mForceShowUpper;
    private final PopupWindow.OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private boolean mOverflowOnly;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    private MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mSeslPopupHeight;

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i2) {
        this(context, menuBuilder, view, z, i2, 0);
    }

    private MenuPopup createPopup() {
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        Math.min(point.x, point.y);
        this.mContext.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width);
        StandardMenuPopup standardMenuPopup = new StandardMenuPopup(this.mContext, this.mMenu, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
        if (this.mOverlapAnchorSet) {
            standardMenuPopup.seslSetOverlapAnchor(this.mOverlapAnchor);
            standardMenuPopup.seslForceShowUpper(this.mForceShowUpper);
        }
        int i2 = this.mSeslPopupHeight;
        if (i2 != -1) {
            standardMenuPopup.seslSetHeight(i2);
        }
        standardMenuPopup.seslForceShowUpper(this.mForceShowUpper);
        if (!this.mAllowScrollingAnchorParent) {
            standardMenuPopup.seslSetAllowScrollingAnchorParent(false);
        }
        standardMenuPopup.addMenu(this.mMenu);
        standardMenuPopup.setOnDismissListener(this.mInternalOnDismissListener);
        standardMenuPopup.setAnchorView(this.mAnchorView);
        standardMenuPopup.setCallback(this.mPresenterCallback);
        standardMenuPopup.setForceShowIcon(this.mForceShowIcon);
        standardMenuPopup.setGravity(this.mDropDownGravity);
        return standardMenuPopup;
    }

    private void showPopup(int i2, int i7, boolean z, boolean z3) {
        MenuPopup popup = getPopup();
        popup.setShowTitle(z3);
        if (z) {
            boolean z7 = true;
            if (ViewCompat.getLayoutDirection(this.mAnchorView) != 1) {
                z7 = false;
            }
            int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R$dimen.sesl_menu_popup_offset_horizontal);
            if (z7) {
                popup.setHorizontalOffset(dimensionPixelOffset + i2);
            } else {
                popup.setHorizontalOffset(i2 - dimensionPixelOffset);
            }
            popup.setVerticalOffset(i7);
            int i8 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            popup.setEpicenterBounds(new Rect(i2 - i8, i7 - i8, i2 + i8, i7 + i8));
        }
        popup.show();
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public MenuPopup getPopup() {
        if (this.mPopup == null) {
            this.mPopup = createPopup();
        }
        return this.mPopup;
    }

    public boolean isShowing() {
        MenuPopup menuPopup = this.mPopup;
        if (menuPopup == null || !menuPopup.isShowing()) {
            return false;
        }
        return true;
    }

    public void onDismiss() {
        this.mPopup = null;
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void seslForceShowUpper(boolean z) {
        this.mForceShowUpper = z;
    }

    public PopupWindow seslGetPopupWindow() {
        MenuPopup menuPopup = this.mPopup;
        if (menuPopup instanceof StandardMenuPopup) {
            return ((StandardMenuPopup) menuPopup).seslGetPopupWindow();
        }
        return null;
    }

    public void seslSetHeight(int i2) {
        this.mSeslPopupHeight = i2;
    }

    public void seslSetOverflowOnly(boolean z) {
        this.mOverflowOnly = z;
    }

    public void seslSetOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        MenuPopup menuPopup = this.mPopup;
        if (menuPopup != null) {
            menuPopup.setForceShowIcon(z);
        }
    }

    public void setGravity(int i2) {
        this.mDropDownGravity = i2;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setPresenterCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
        MenuPopup menuPopup = this.mPopup;
        if (menuPopup != null) {
            menuPopup.setCallback(callback);
        }
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        if (isShowing()) {
            return true;
        }
        if (this.mAnchorView == null) {
            return false;
        }
        showPopup(0, 0, false, false);
        return true;
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i2, int i7) {
        this.mDropDownGravity = 8388611;
        this.mForceShowUpper = false;
        this.mAllowScrollingAnchorParent = true;
        this.mSeslPopupHeight = -1;
        this.mInternalOnDismissListener = new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                MenuPopupHelper.this.onDismiss();
            }
        };
        this.mContext = context;
        this.mMenu = menuBuilder;
        this.mAnchorView = view;
        this.mOverflowOnly = z;
        this.mPopupStyleAttr = i2;
        this.mPopupStyleRes = i7;
    }

    public void show(int i2, int i7) {
        if (!tryShow(i2, i7)) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow(int i2, int i7) {
        if (isShowing()) {
            return true;
        }
        if (this.mAnchorView == null) {
            return false;
        }
        showPopup(i2, i7, true, true);
        return true;
    }
}
