package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuPopupWindow;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private static final int ITEM_LAYOUT = R$layout.sesl_popup_menu_item_layout;
    private static final int SUB_MENU_ITEM_LAYOUT = R$layout.sesl_popup_sub_menu_item_layout;
    private final MenuAdapter mAdapter;
    private boolean mAllowScrollingAnchorParent = true;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener() {
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = StandardMenuPopup.this.mTreeObserver;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    StandardMenuPopup.this.mTreeObserver = view.getViewTreeObserver();
                }
                StandardMenuPopup standardMenuPopup = StandardMenuPopup.this;
                standardMenuPopup.mTreeObserver.removeGlobalOnLayoutListener(standardMenuPopup.mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }

        public void onViewAttachedToWindow(View view) {
        }
    };
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity = 0;
    private boolean mForceShowUpper;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            if (StandardMenuPopup.this.isShowing()) {
                View view = StandardMenuPopup.this.mShownAnchorView;
                if (view == null || !view.isShown()) {
                    StandardMenuPopup.this.dismiss();
                } else {
                    StandardMenuPopup.this.mPopup.show();
                }
            }
        }
    };
    private boolean mHasContentWidth;
    private boolean mIsSubMenu = false;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    final MenuPopupWindow mPopup;
    private int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private boolean mShowTitle;
    View mShownAnchorView;
    private ListView mTmpListView = null;
    ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;

    public StandardMenuPopup(Context context, MenuBuilder menuBuilder, View view, int i2, int i7, boolean z) {
        int i8 = 0;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843945, typedValue, false);
        if (typedValue.data != 0) {
            this.mContext = new ContextThemeWrapper(context, typedValue.data);
        } else {
            this.mContext = context;
        }
        this.mMenu = menuBuilder;
        this.mIsSubMenu = menuBuilder instanceof SubMenuBuilder;
        this.mOverflowOnly = z;
        LayoutInflater from = LayoutInflater.from(context);
        int size = menuBuilder.size();
        while (true) {
            if (i8 >= size) {
                this.mAdapter = new MenuAdapter(menuBuilder, from, this.mOverflowOnly, ITEM_LAYOUT);
                break;
            } else if (((MenuItemImpl) this.mMenu.getItem(i8)).isExclusiveCheckable()) {
                this.mAdapter = new MenuAdapter(menuBuilder, from, this.mOverflowOnly, SUB_MENU_ITEM_LAYOUT);
                break;
            } else {
                i8++;
            }
        }
        this.mPopupStyleAttr = i2;
        this.mPopupStyleRes = i7;
        this.mPopupMaxWidth = context.getResources().getDisplayMetrics().widthPixels - (this.mContext.getResources().getDimensionPixelOffset(R$dimen.sesl_menu_popup_offset_horizontal) * 2);
        this.mAnchorView = view;
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.mContext, (AttributeSet) null, i2, i7);
        this.mPopup = menuPopupWindow;
        menuPopupWindow.setIsOverflowPopup(this.mOverflowOnly);
        menuBuilder.addMenuPresenter(this, context);
    }

    private boolean tryShow() {
        View view;
        boolean z;
        ListView listView;
        if (isShowing()) {
            return true;
        }
        if (this.mWasDismissed || (view = this.mAnchorView) == null) {
            return false;
        }
        this.mShownAnchorView = view;
        if (this.mOverlapAnchorSet) {
            this.mPopup.setOverlapAnchor(this.mOverlapAnchor);
            this.mPopup.seslForceShowUpper(this.mForceShowUpper);
        }
        boolean z3 = this.mAllowScrollingAnchorParent;
        if (!z3) {
            this.mPopup.seslSetAllowScrollingAnchorParent(z3);
        }
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mPopup.setModal(true);
        View view2 = this.mShownAnchorView;
        if (this.mTreeObserver == null) {
            z = true;
        } else {
            z = false;
        }
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.mTreeObserver = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
        }
        view2.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
        this.mPopup.setAnchorView(view2);
        this.mPopup.setDropDownGravity(this.mDropDownGravity);
        if (!this.mHasContentWidth) {
            this.mContentWidth = MenuPopup.measureIndividualMenuWidth(this.mAdapter, (ViewGroup) null, this.mContext, this.mPopupMaxWidth);
            this.mHasContentWidth = true;
        }
        this.mPopup.setContentWidth(this.mContentWidth);
        this.mPopup.setInputMethodMode(2);
        this.mPopup.setEpicenterBounds(getEpicenterBounds());
        this.mPopup.show();
        ListView listView2 = this.mPopup.getListView();
        listView2.setOnKeyListener(this);
        if (this.mIsSubMenu) {
            listView = null;
        } else {
            listView = listView2;
        }
        this.mTmpListView = listView;
        if (this.mShowTitle && this.mMenu.getHeaderTitle() != null && !this.mIsSubMenu) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.mContext).inflate(R$layout.sesl_popup_menu_header_item_layout, listView2, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.mMenu.getHeaderTitle());
            }
            frameLayout.setEnabled(false);
            listView2.addHeaderView(frameLayout, (Object) null, false);
        }
        this.mPopup.setAdapter(this.mAdapter);
        this.mPopup.show();
        return true;
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListView getListView() {
        return this.mPopup.getListView();
    }

    public boolean isShowing() {
        if (this.mWasDismissed || !this.mPopup.isShowing()) {
            return false;
        }
        return true;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.mMenu) {
            dismiss();
            MenuPresenter.Callback callback = this.mPresenterCallback;
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    public void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close();
        ViewTreeObserver viewTreeObserver = this.mTreeObserver;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mTreeObserver = null;
        }
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        MenuItem menuItem;
        if (subMenuBuilder.hasVisibleItems()) {
            SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder2, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
            menuPopupHelper.setPresenterCallback(this.mPresenterCallback);
            menuPopupHelper.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(subMenuBuilder2));
            menuPopupHelper.setOnDismissListener(this.mOnDismissListener);
            View view = null;
            this.mOnDismissListener = null;
            int size = this.mMenu.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    menuItem = null;
                    break;
                }
                menuItem = this.mMenu.getItem(i2);
                if (menuItem.hasSubMenu() && subMenuBuilder2 == menuItem.getSubMenu()) {
                    break;
                }
                i2++;
            }
            int count = this.mAdapter.getCount();
            int i7 = 0;
            while (true) {
                if (i7 >= count) {
                    i7 = -1;
                    break;
                } else if (menuItem == this.mAdapter.getItem(i7)) {
                    break;
                } else {
                    i7++;
                }
            }
            ListView listView = this.mTmpListView;
            if (listView != null) {
                int firstVisiblePosition = i7 - listView.getFirstVisiblePosition();
                if (firstVisiblePosition >= 0) {
                    this.mTmpListView.getChildCount();
                }
                view = this.mTmpListView.getChildAt(firstVisiblePosition);
            }
            if (view != null) {
                view.getMeasuredHeight();
            }
            menuPopupHelper.setGravity(this.mDropDownGravity);
            this.mMenu.close(false);
            if (menuPopupHelper.tryShow(0, 0)) {
                MenuPresenter.Callback callback = this.mPresenterCallback;
                if (callback == null) {
                    return true;
                }
                callback.onOpenSubMenu(subMenuBuilder2);
                return true;
            }
        }
        return false;
    }

    public void seslForceShowUpper(boolean z) {
        this.mForceShowUpper = z;
    }

    public PopupWindow seslGetPopupWindow() {
        MenuPopupWindow menuPopupWindow = this.mPopup;
        if (menuPopupWindow != null) {
            return menuPopupWindow.seslGetPopupWindow();
        }
        return null;
    }

    public void seslSetAllowScrollingAnchorParent(boolean z) {
        this.mAllowScrollingAnchorParent = z;
    }

    public void seslSetHeight(int i2) {
        MenuPopupWindow menuPopupWindow = this.mPopup;
        if (menuPopupWindow != null) {
            menuPopupWindow.setHeight(i2);
        }
    }

    public void seslSetOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean z) {
        this.mAdapter.setForceShowIcon(z);
    }

    public void setGravity(int i2) {
        this.mDropDownGravity = i2;
    }

    public void setHorizontalOffset(int i2) {
        this.mPopup.setHorizontalOffset(i2);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    public void setVerticalOffset(int i2) {
        this.mPopup.setVerticalOffset(i2);
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    public void updateMenuView(boolean z) {
        this.mHasContentWidth = false;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    public void addMenu(MenuBuilder menuBuilder) {
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
    }
}
