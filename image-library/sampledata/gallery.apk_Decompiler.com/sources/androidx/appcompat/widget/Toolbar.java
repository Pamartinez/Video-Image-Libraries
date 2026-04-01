package androidx.appcompat.widget;

import H4.a;
import ad.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.core.view.SeslTouchTargetDelegate;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;
import h.C0199b;
import h.C0200c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Toolbar extends ViewGroup implements MenuHost {
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mAllowEatingTouch;
    private OnBackInvokedCallback mBackInvokedCallback;
    private boolean mBackInvokedCallbackEnabled;
    private OnBackInvokedDispatcher mBackInvokedDispatcher;
    private Drawable mBackground;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private boolean mForceNotEatingHover;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    MenuBuilder.Callback mMenuBuilderCallback;
    final MenuHostHelper mMenuHostHelper;
    ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private Drawable mNavButtonIconDrawable;
    private ImageButton mNavButtonView;
    private View.OnClickListener mNavButtonViewListener;
    private CharSequence mNavTooltipText;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListenerForTD;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private ArrayList<MenuItem> mProvidedMenuItems;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private int mUserTopPadding;
    private ToolbarWidgetWrapper mWrapper;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api33Impl {
        public static OnBackInvokedDispatcher findOnBackInvokedDispatcher(View view) {
            return view.findOnBackInvokedDispatcher();
        }

        public static OnBackInvokedCallback newOnBackInvokedCallback(Runnable runnable) {
            Objects.requireNonNull(runnable);
            return new C0200c(runnable);
        }

        public static void tryRegisterOnBackInvokedCallback(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) obj2);
        }

        public static void tryUnregisterOnBackInvokedCallback(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    private void addCustomViewsWithGravity(List<View> list, int i2) {
        boolean z;
        if (getLayoutDirection() == 1) {
            z = true;
        } else {
            z = false;
        }
        int childCount = getChildCount();
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, getLayoutDirection());
        list.clear();
        if (z) {
            for (int i7 = childCount - 1; i7 >= 0; i7--) {
                View childAt = getChildAt(i7);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mViewType == 0 && shouldLayout(childAt) && getChildHorizontalGravity(layoutParams.gravity) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt2 = getChildAt(i8);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.mViewType == 0 && shouldLayout(childAt2) && getChildHorizontalGravity(layoutParams2.gravity) == absoluteGravity) {
                list.add(childAt2);
            }
        }
    }

    private void addSystemView(View view, boolean z) {
        LayoutParams layoutParams;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = generateDefaultLayoutParams();
            } else if (!checkLayoutParams(layoutParams2)) {
                layoutParams = generateLayoutParams(layoutParams2);
            } else {
                layoutParams = (LayoutParams) layoutParams2;
            }
            layoutParams.mViewType = 1;
            if (z && this.mExpandedActionView != null) {
                view.setLayoutParams(layoutParams);
                this.mHiddenViews.add(view);
            } else if (view.getParent() == null) {
                addView(view, layoutParams);
            }
        }
    }

    private void ensureContentInsets() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }

    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext());
        }
    }

    private void ensureMenu() {
        ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder menuBuilder = (MenuBuilder) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
            updateBackInvokedCallbackState();
        }
    }

    private void ensureMenuView() {
        if (this.mMenuView == null) {
            this.mMenuView = new ActionMenuView(getContext());
            int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.sesl_action_menu_view_padding_end);
            this.mMenuView.setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.sesl_action_menu_view_padding_start), 0, dimensionPixelSize, 0);
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, new MenuBuilder.Callback() {
                public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                    MenuBuilder.Callback callback = Toolbar.this.mMenuBuilderCallback;
                    if (callback == null || !callback.onMenuItemSelected(menuBuilder, menuItem)) {
                        return false;
                    }
                    return true;
                }

                public void onMenuModeChange(MenuBuilder menuBuilder) {
                    if (!Toolbar.this.mMenuView.isOverflowMenuShowing()) {
                        Toolbar.this.mMenuHostHelper.onPrepareMenu(menuBuilder);
                    }
                    MenuBuilder.Callback callback = Toolbar.this.mMenuBuilderCallback;
                    if (callback != null) {
                        callback.onMenuModeChange(menuBuilder);
                    }
                }
            });
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (this.mButtonGravity & 112) | 8388613;
            this.mMenuView.setLayoutParams(generateDefaultLayoutParams);
            addSystemView(this.mMenuView, false);
        }
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(getContext(), (AttributeSet) null, R$attr.toolbarNavigationButtonStyle);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (this.mButtonGravity & 112) | 8388611;
            this.mNavButtonView.setLayoutParams(generateDefaultLayoutParams);
            SeslViewReflector.semSetHoverPopupType(this.mNavButtonView, SeslHoverPopupWindowReflector.getField_TYPE_NONE());
            if (!TextUtils.isEmpty(this.mNavTooltipText)) {
                TooltipCompat.setTooltipText(this.mNavButtonView, this.mNavTooltipText);
            }
        }
    }

    private int getChildHorizontalGravity(int i2) {
        int layoutDirection = getLayoutDirection();
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, layoutDirection) & 7;
        if (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) {
            return absoluteGravity;
        }
        if (layoutDirection == 1) {
            return 5;
        }
        return 3;
    }

    private int getChildTop(View view, int i2) {
        int i7;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        if (i2 > 0) {
            i7 = (measuredHeight - i2) / 2;
        } else {
            i7 = 0;
        }
        int childVerticalGravity = getChildVerticalGravity(layoutParams.gravity);
        if (childVerticalGravity == 48) {
            return getPaddingTop();
        }
        if (childVerticalGravity == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i7;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i8 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i10 = layoutParams.topMargin;
        if (i8 < i10) {
            i8 = i10;
        } else {
            int i11 = (((height - paddingBottom) - measuredHeight) - i8) - paddingTop;
            int i12 = layoutParams.bottomMargin;
            if (i11 < i12) {
                i8 = Math.max(0, i8 - (i12 - i11));
            }
        }
        return paddingTop + i8;
    }

    private int getChildVerticalGravity(int i2) {
        int i7 = i2 & 112;
        if (i7 == 16 || i7 == 48 || i7 == 80) {
            return i7;
        }
        return this.mGravity & 112;
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i2 = 0; i2 < menu.size(); i2++) {
            arrayList.add(menu.getItem(i2));
        }
        return arrayList;
    }

    private int getHorizontalMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    private int getVerticalMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int getViewListMeasuredWidth(List<View> list, int[] iArr) {
        int i2 = iArr[0];
        int i7 = iArr[1];
        int size = list.size();
        int i8 = 0;
        int i10 = 0;
        while (i8 < size) {
            View view = list.get(i8);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int i11 = layoutParams.leftMargin - i2;
            int i12 = layoutParams.rightMargin - i7;
            int max = Math.max(0, i11);
            int max2 = Math.max(0, i12);
            int max3 = Math.max(0, -i11);
            int max4 = Math.max(0, -i12);
            i10 += view.getMeasuredWidth() + max + max2;
            i8++;
            i7 = max4;
            i2 = max3;
        }
        return i10;
    }

    private boolean isChildOrHidden(View view) {
        if (view.getParent() == this || this.mHiddenViews.contains(view)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetTouchDelegateForToolbar$0(ViewGroup viewGroup) {
        boolean z;
        boolean z3;
        View view;
        boolean z7;
        int i2;
        int i7;
        int i8;
        int i10;
        SeslTouchTargetDelegate seslTouchTargetDelegate = new SeslTouchTargetDelegate(viewGroup);
        if (getLayoutDirection() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (shouldLayout(this.mNavButtonView)) {
            int top = this.mNavButtonView.getTop();
            int height = viewGroup.getHeight() - this.mNavButtonView.getBottom();
            if (z) {
                i10 = getResources().getDimensionPixelOffset(R$dimen.sesl_navigation_up_touch_delegate_right);
                i8 = 0;
            } else {
                i8 = getResources().getDimensionPixelOffset(R$dimen.sesl_navigation_up_touch_delegate_right);
                i10 = 0;
            }
            seslTouchTargetDelegate.addTouchDelegate((View) this.mNavButtonView, SeslTouchTargetDelegate.ExtraInsets.of(i10, top, i8, height));
            z3 = true;
        } else {
            z3 = false;
        }
        int childCount = viewGroup.getChildCount();
        int i11 = 0;
        while (true) {
            if (i11 >= childCount) {
                view = null;
                break;
            }
            view = viewGroup.getChildAt(i11);
            if (view instanceof ActionMenuView) {
                break;
            }
            i11++;
        }
        if (view != null && view.getVisibility() == 0) {
            ViewGroup viewGroup2 = (ViewGroup) view;
            int childCount2 = viewGroup2.getChildCount();
            for (int i12 = 0; i12 < childCount2; i12++) {
                View childAt = viewGroup2.getChildAt(i12);
                if (childAt.getVisibility() == 0) {
                    int measuredWidth = childAt.getMeasuredWidth() / 2;
                    if (!(childAt instanceof ActionMenuItemView) || !((ActionMenuItemView) childAt).seslIsTextButtonVisible()) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    if (i12 != 0 || z7) {
                        i7 = 0;
                        i2 = 0;
                    } else if (z) {
                        i2 = measuredWidth;
                        i7 = 0;
                    } else {
                        i7 = measuredWidth;
                        i2 = 0;
                    }
                    seslTouchTargetDelegate.addTouchDelegate(childAt, SeslTouchTargetDelegate.ExtraInsets.of(i7, measuredWidth, i2, measuredWidth));
                    z3 = true;
                }
            }
        }
        if (z3) {
            viewGroup.setTouchDelegate(seslTouchTargetDelegate);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetTouchDelegateForToolbar$1() {
        post(new C0199b(0, this, this));
    }

    private int layoutChildLeft(View view, int i2, int[] iArr, int i7) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i8 = layoutParams.leftMargin - iArr[0];
        int max = Math.max(0, i8) + i2;
        iArr[0] = Math.max(0, -i8);
        int childTop = getChildTop(view, i7);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, childTop, max + measuredWidth, view.getMeasuredHeight() + childTop);
        return measuredWidth + layoutParams.rightMargin + max;
    }

    private int layoutChildRight(View view, int i2, int[] iArr, int i7) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i8 = layoutParams.rightMargin - iArr[1];
        int max = i2 - Math.max(0, i8);
        iArr[1] = Math.max(0, -i8);
        int childTop = getChildTop(view, i7);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, childTop, max, view.getMeasuredHeight() + childTop);
        return max - (measuredWidth + layoutParams.leftMargin);
    }

    private int measureChildCollapseMargins(View view, int i2, int i7, int i8, int i10, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i11 = marginLayoutParams.leftMargin - iArr[0];
        int i12 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i12) + Math.max(0, i11);
        iArr[0] = Math.max(0, -i11);
        iArr[1] = Math.max(0, -i12);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + max + i7, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i8, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private void measureChildConstrained(View view, int i2, int i7, int i8, int i10, int i11) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i8, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i11 >= 0) {
            if (mode != 0) {
                i11 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i11);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void onCreateMenu() {
        Menu menu = getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        this.mMenuHostHelper.onCreateMenu(menu, getMenuInflater());
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.mProvidedMenuItems = currentMenuItems2;
        this.mMenuHostHelper.onPrepareMenu(menu);
    }

    private void postShowOverflowMenu() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    private void seslRemoveListenerForTouchDelegate() {
        if (this.mOnGlobalLayoutListenerForTD != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerForTD);
            this.mOnGlobalLayoutListenerForTD = null;
        }
    }

    private void seslSetTouchDelegateForToolbar() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null && this.mOnGlobalLayoutListenerForTD == null) {
            b bVar = new b(1, this);
            this.mOnGlobalLayoutListenerForTD = bVar;
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
        }
    }

    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (shouldLayout(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean shouldLayout(View view) {
        if (view == null || view.getParent() != this || view.getVisibility() == 8) {
            return false;
        }
        return true;
    }

    public void addChildrenForExpandedActionView() {
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            addView(this.mHiddenViews.get(size));
        }
        this.mHiddenViews.clear();
    }

    public void addMenuProvider(MenuProvider menuProvider) {
        this.mMenuHostHelper.addMenuProvider(menuProvider);
    }

    public void addView(View view) {
        super.addView(view);
        startCustomViewAddAnimation(view);
    }

    public boolean canShowOverflowMenu() {
        ActionMenuView actionMenuView;
        if (getVisibility() != 0 || (actionMenuView = this.mMenuView) == null || !actionMenuView.isOverflowReserved()) {
            return false;
        }
        return true;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!super.checkLayoutParams(layoutParams) || !(layoutParams instanceof LayoutParams)) {
            return false;
        }
        return true;
    }

    public void collapseActionView() {
        MenuItemImpl menuItemImpl;
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        if (expandedActionViewMenuPresenter == null) {
            menuItemImpl = null;
        } else {
            menuItemImpl = expandedActionViewMenuPresenter.mCurrentExpandedItem;
        }
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.dismissPopupMenus();
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            TooltipCompat.seslSetNextTooltipForceBelow(true);
            TooltipCompat.seslSetNextTooltipForceActionBarPosX(true);
        } else if (action == 10) {
            TooltipCompat.seslSetNextTooltipForceBelow(false);
            TooltipCompat.seslSetNextTooltipForceActionBarPosX(false);
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), (AttributeSet) null, R$attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (this.mButtonGravity & 112) | 8388611;
            generateDefaultLayoutParams.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams(generateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toolbar.this.collapseActionView();
                }
            });
            SeslViewReflector.semSetHoverPopupType(this.mCollapseButtonView, SeslHoverPopupWindowReflector.getField_TYPE_NONE());
            if (!TextUtils.isEmpty(this.mCollapseDescription)) {
                TooltipCompat.setTooltipText(this.mCollapseButtonView, this.mCollapseDescription);
            }
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.getEnd();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.mContentInsetEndWithActions;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        return getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.getLeft();
        }
        return 0;
    }

    public int getContentInsetRight() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.getRight();
        }
        return 0;
    }

    public int getContentInsetStart() {
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.getStart();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.mContentInsetStartWithNavigation;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        return getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        MenuBuilder peekMenu;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || (peekMenu = actionMenuView.peekMenu()) == null || !peekMenu.hasVisibleItems()) {
            return getContentInsetEnd();
        }
        return Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
    }

    public int getCurrentContentInsetLeft() {
        if (getLayoutDirection() == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (getLayoutDirection() == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
        }
        return getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.getMenu();
    }

    public View getNavButtonView() {
        return this.mNavButtonView;
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public final TextView getSubTitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        if (expandedActionViewMenuPresenter == null || expandedActionViewMenuPresenter.mCurrentExpandedItem == null) {
            return false;
        }
        return true;
    }

    public boolean hideOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || !actionMenuView.hideOverflowMenu()) {
            return false;
        }
        return true;
    }

    public void inflateMenu(int i2) {
        getMenuInflater().inflate(i2, getMenu());
    }

    public void invalidateMenu() {
        Iterator<MenuItem> it = this.mProvidedMenuItems.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(it.next().getItemId());
        }
        onCreateMenu();
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || !actionMenuView.isOverflowMenuShowPending()) {
            return false;
        }
        return true;
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || !actionMenuView.isOverflowMenuShowing()) {
            return false;
        }
        return true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBackInvokedCallbackState();
        int i2 = this.mUserTopPadding;
        if (i2 == -1) {
            i2 = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding);
        }
        setPadding(0, i2, 0, 0);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(R$styleable.AppCompatTheme);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.AppCompatTheme_actionBarSize, 0);
        obtainStyledAttributes.recycle();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = dimensionPixelSize + i2;
        setLayoutParams(layoutParams);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(R$styleable.AppCompatTheme);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.AppCompatTheme_actionBarSize, 0);
        if (this.mNavButtonView != null) {
            obtainStyledAttributes.recycle();
            obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.View, R$attr.actionOverflowButtonStyle, 0);
            this.mNavButtonView.setMinimumHeight(obtainStyledAttributes.getDimensionPixelSize(R$styleable.View_android_minHeight, 0));
        }
        obtainStyledAttributes.recycle();
        int i2 = this.mUserTopPadding;
        if (i2 == -1) {
            i2 = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding);
        }
        setPadding(0, i2, 0, 0);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = dimensionPixelSize + i2;
        setLayoutParams(layoutParams);
        TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.Toolbar, 16843946, 0);
        int dimensionPixelSize2 = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.Toolbar_maxButtonHeight, -1);
        if (dimensionPixelSize2 >= -1) {
            this.mMaxButtonHeight = dimensionPixelSize2;
        }
        int dimensionPixelSize3 = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.Toolbar_android_minHeight, -1);
        if (dimensionPixelSize3 >= -1) {
            setMinimumHeight(dimensionPixelSize3);
        }
        obtainStyledAttributes2.recycle();
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null && actionMenuView.isOverflowMenuShowing()) {
            this.mMenuView.hideOverflowMenu();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
        updateBackInvokedCallbackState();
        seslRemoveListenerForTouchDelegate();
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (this.mForceNotEatingHover) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x02b0 A[LOOP:0: B:104:0x02ae->B:105:0x02b0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02ce A[LOOP:1: B:107:0x02cc->B:108:0x02ce, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0305 A[LOOP:2: B:115:0x0303->B:116:0x0305, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0237  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            r19 = this;
            r0 = r19
            int r1 = r0.getLayoutDirection()
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L_0x000c
            r1 = r3
            goto L_0x000d
        L_0x000c:
            r1 = r2
        L_0x000d:
            int r4 = r0.getWidth()
            int r5 = r0.getHeight()
            int r6 = r0.getPaddingLeft()
            int r7 = r0.getPaddingRight()
            int r8 = r0.getPaddingTop()
            int r9 = r0.getPaddingBottom()
            int r10 = r4 - r7
            int[] r11 = r0.mTempMargins
            r11[r3] = r2
            r11[r2] = r2
            int r12 = androidx.core.view.ViewCompat.getMinimumHeight(r0)
            if (r12 < 0) goto L_0x003a
            int r13 = r24 - r22
            int r12 = java.lang.Math.max(r12, r13)
            goto L_0x003b
        L_0x003a:
            r12 = r2
        L_0x003b:
            android.widget.ImageButton r13 = r0.mNavButtonView
            boolean r13 = r0.shouldLayout(r13)
            if (r13 == 0) goto L_0x006f
            android.widget.ImageButton r13 = r0.mNavButtonView
            int r13 = r13.getLayoutDirection()
            android.graphics.drawable.Drawable r14 = r0.mNavButtonIconDrawable
            int r14 = r14.getLayoutDirection()
            if (r13 == r14) goto L_0x005c
            android.graphics.drawable.Drawable r13 = r0.mNavButtonIconDrawable
            android.widget.ImageButton r14 = r0.mNavButtonView
            int r14 = r14.getLayoutDirection()
            r13.setLayoutDirection(r14)
        L_0x005c:
            if (r1 == 0) goto L_0x0067
            android.widget.ImageButton r13 = r0.mNavButtonView
            int r13 = r0.layoutChildRight(r13, r10, r11, r12)
            r14 = r13
            r13 = r6
            goto L_0x0071
        L_0x0067:
            android.widget.ImageButton r13 = r0.mNavButtonView
            int r13 = r0.layoutChildLeft(r13, r6, r11, r12)
        L_0x006d:
            r14 = r10
            goto L_0x0071
        L_0x006f:
            r13 = r6
            goto L_0x006d
        L_0x0071:
            android.widget.ImageButton r15 = r0.mCollapseButtonView
            boolean r15 = r0.shouldLayout(r15)
            if (r15 == 0) goto L_0x0088
            if (r1 == 0) goto L_0x0082
            android.widget.ImageButton r15 = r0.mCollapseButtonView
            int r14 = r0.layoutChildRight(r15, r14, r11, r12)
            goto L_0x0088
        L_0x0082:
            android.widget.ImageButton r15 = r0.mCollapseButtonView
            int r13 = r0.layoutChildLeft(r15, r13, r11, r12)
        L_0x0088:
            androidx.appcompat.widget.ActionMenuView r15 = r0.mMenuView
            boolean r15 = r0.shouldLayout(r15)
            if (r15 == 0) goto L_0x009f
            if (r1 == 0) goto L_0x0099
            androidx.appcompat.widget.ActionMenuView r15 = r0.mMenuView
            int r13 = r0.layoutChildLeft(r15, r13, r11, r12)
            goto L_0x009f
        L_0x0099:
            androidx.appcompat.widget.ActionMenuView r15 = r0.mMenuView
            int r14 = r0.layoutChildRight(r15, r14, r11, r12)
        L_0x009f:
            int r15 = r0.getCurrentContentInsetLeft()
            int r16 = r0.getCurrentContentInsetRight()
            r20 = r3
            int r3 = r15 - r13
            int r3 = java.lang.Math.max(r2, r3)
            r11[r2] = r3
            int r3 = r10 - r14
            int r3 = r16 - r3
            int r3 = java.lang.Math.max(r2, r3)
            r11[r20] = r3
            int r3 = java.lang.Math.max(r13, r15)
            int r10 = r10 - r16
            int r10 = java.lang.Math.min(r14, r10)
            android.view.View r13 = r0.mExpandedActionView
            boolean r13 = r0.shouldLayout(r13)
            if (r13 == 0) goto L_0x00dc
            if (r1 == 0) goto L_0x00d6
            android.view.View r13 = r0.mExpandedActionView
            int r10 = r0.layoutChildRight(r13, r10, r11, r12)
            goto L_0x00dc
        L_0x00d6:
            android.view.View r13 = r0.mExpandedActionView
            int r3 = r0.layoutChildLeft(r13, r3, r11, r12)
        L_0x00dc:
            android.widget.ImageView r13 = r0.mLogoView
            boolean r13 = r0.shouldLayout(r13)
            if (r13 == 0) goto L_0x00f3
            if (r1 == 0) goto L_0x00ed
            android.widget.ImageView r13 = r0.mLogoView
            int r10 = r0.layoutChildRight(r13, r10, r11, r12)
            goto L_0x00f3
        L_0x00ed:
            android.widget.ImageView r13 = r0.mLogoView
            int r3 = r0.layoutChildLeft(r13, r3, r11, r12)
        L_0x00f3:
            android.widget.TextView r13 = r0.mTitleTextView
            boolean r13 = r0.shouldLayout(r13)
            android.widget.TextView r14 = r0.mSubtitleTextView
            boolean r14 = r0.shouldLayout(r14)
            if (r13 == 0) goto L_0x0118
            android.widget.TextView r15 = r0.mTitleTextView
            android.view.ViewGroup$LayoutParams r15 = r15.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r15 = (androidx.appcompat.widget.Toolbar.LayoutParams) r15
            int r2 = r15.topMargin
            r23 = r1
            android.widget.TextView r1 = r0.mTitleTextView
            int r1 = r1.getMeasuredHeight()
            int r1 = r1 + r2
            int r2 = r15.bottomMargin
            int r1 = r1 + r2
            goto L_0x011b
        L_0x0118:
            r23 = r1
            r1 = 0
        L_0x011b:
            if (r14 == 0) goto L_0x0136
            android.widget.TextView r2 = r0.mSubtitleTextView
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r2 = (androidx.appcompat.widget.Toolbar.LayoutParams) r2
            int r15 = r2.topMargin
            r22 = r1
            android.widget.TextView r1 = r0.mSubtitleTextView
            int r1 = r1.getMeasuredHeight()
            int r1 = r1 + r15
            int r2 = r2.bottomMargin
            int r1 = r1 + r2
            int r1 = r1 + r22
            goto L_0x0138
        L_0x0136:
            r22 = r1
        L_0x0138:
            if (r13 != 0) goto L_0x0140
            if (r14 == 0) goto L_0x013d
            goto L_0x0140
        L_0x013d:
            r1 = 0
            goto L_0x02a0
        L_0x0140:
            if (r13 == 0) goto L_0x0145
            android.widget.TextView r2 = r0.mTitleTextView
            goto L_0x0147
        L_0x0145:
            android.widget.TextView r2 = r0.mSubtitleTextView
        L_0x0147:
            if (r14 == 0) goto L_0x014c
            android.widget.TextView r15 = r0.mSubtitleTextView
            goto L_0x014e
        L_0x014c:
            android.widget.TextView r15 = r0.mTitleTextView
        L_0x014e:
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r2 = (androidx.appcompat.widget.Toolbar.LayoutParams) r2
            android.view.ViewGroup$LayoutParams r15 = r15.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r15 = (androidx.appcompat.widget.Toolbar.LayoutParams) r15
            r22 = r1
            if (r13 == 0) goto L_0x0166
            android.widget.TextView r1 = r0.mTitleTextView
            int r1 = r1.getMeasuredWidth()
            if (r1 > 0) goto L_0x0170
        L_0x0166:
            if (r14 == 0) goto L_0x0173
            android.widget.TextView r1 = r0.mSubtitleTextView
            int r1 = r1.getMeasuredWidth()
            if (r1 <= 0) goto L_0x0173
        L_0x0170:
            r24 = r20
            goto L_0x0175
        L_0x0173:
            r24 = 0
        L_0x0175:
            int r1 = r0.mGravity
            r1 = r1 & 112(0x70, float:1.57E-43)
            r16 = r3
            r3 = 48
            if (r1 == r3) goto L_0x01bb
            r3 = 80
            if (r1 == r3) goto L_0x01b1
            int r1 = r5 - r8
            int r1 = r1 - r9
            int r1 = r1 - r22
            int r1 = r1 / 2
            int r3 = r2.topMargin
            r17 = r3
            int r3 = r0.mTitleMarginTop
            r18 = r3
            int r3 = r17 + r18
            if (r1 >= r3) goto L_0x0199
            int r1 = r17 + r18
            goto L_0x01af
        L_0x0199:
            int r5 = r5 - r9
            int r5 = r5 - r22
            int r5 = r5 - r1
            int r5 = r5 - r8
            int r2 = r2.bottomMargin
            int r3 = r0.mTitleMarginBottom
            int r2 = r2 + r3
            if (r5 >= r2) goto L_0x01af
            int r2 = r15.bottomMargin
            int r2 = r2 + r3
            int r2 = r2 - r5
            int r1 = r1 - r2
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
        L_0x01af:
            int r8 = r8 + r1
            goto L_0x01c6
        L_0x01b1:
            int r5 = r5 - r9
            int r1 = r15.bottomMargin
            int r5 = r5 - r1
            int r1 = r0.mTitleMarginBottom
            int r5 = r5 - r1
            int r8 = r5 - r22
            goto L_0x01c6
        L_0x01bb:
            int r1 = r0.getPaddingTop()
            int r2 = r2.topMargin
            int r1 = r1 + r2
            int r2 = r0.mTitleMarginTop
            int r8 = r1 + r2
        L_0x01c6:
            if (r23 == 0) goto L_0x0237
            if (r24 == 0) goto L_0x01cd
            int r1 = r0.mTitleMarginStart
            goto L_0x01ce
        L_0x01cd:
            r1 = 0
        L_0x01ce:
            r2 = r11[r20]
            int r1 = r1 - r2
            r2 = 0
            int r3 = java.lang.Math.max(r2, r1)
            int r10 = r10 - r3
            int r1 = -r1
            int r1 = java.lang.Math.max(r2, r1)
            r11[r20] = r1
            if (r13 == 0) goto L_0x0204
            android.widget.TextView r1 = r0.mTitleTextView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r1 = (androidx.appcompat.widget.Toolbar.LayoutParams) r1
            android.widget.TextView r2 = r0.mTitleTextView
            int r2 = r2.getMeasuredWidth()
            int r2 = r10 - r2
            android.widget.TextView r3 = r0.mTitleTextView
            int r3 = r3.getMeasuredHeight()
            int r3 = r3 + r8
            android.widget.TextView r5 = r0.mTitleTextView
            r5.layout(r2, r8, r10, r3)
            int r5 = r0.mTitleMarginEnd
            int r2 = r2 - r5
            int r1 = r1.bottomMargin
            int r8 = r3 + r1
            goto L_0x0205
        L_0x0204:
            r2 = r10
        L_0x0205:
            if (r14 == 0) goto L_0x022b
            android.widget.TextView r1 = r0.mSubtitleTextView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r1 = (androidx.appcompat.widget.Toolbar.LayoutParams) r1
            int r1 = r1.topMargin
            int r8 = r8 + r1
            android.widget.TextView r1 = r0.mSubtitleTextView
            int r1 = r1.getMeasuredWidth()
            int r1 = r10 - r1
            android.widget.TextView r3 = r0.mSubtitleTextView
            int r3 = r3.getMeasuredHeight()
            int r3 = r3 + r8
            android.widget.TextView r5 = r0.mSubtitleTextView
            r5.layout(r1, r8, r10, r3)
            int r1 = r0.mTitleMarginEnd
            int r1 = r10 - r1
            goto L_0x022c
        L_0x022b:
            r1 = r10
        L_0x022c:
            if (r24 == 0) goto L_0x0233
            int r1 = java.lang.Math.min(r2, r1)
            r10 = r1
        L_0x0233:
            r3 = r16
            goto L_0x013d
        L_0x0237:
            if (r24 == 0) goto L_0x023d
            int r2 = r0.mTitleMarginStart
        L_0x023b:
            r1 = 0
            goto L_0x023f
        L_0x023d:
            r2 = 0
            goto L_0x023b
        L_0x023f:
            r3 = r11[r1]
            int r2 = r2 - r3
            int r3 = java.lang.Math.max(r1, r2)
            int r3 = r3 + r16
            int r2 = -r2
            int r2 = java.lang.Math.max(r1, r2)
            r11[r1] = r2
            if (r13 == 0) goto L_0x0274
            android.widget.TextView r2 = r0.mTitleTextView
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r2 = (androidx.appcompat.widget.Toolbar.LayoutParams) r2
            android.widget.TextView r5 = r0.mTitleTextView
            int r5 = r5.getMeasuredWidth()
            int r5 = r5 + r3
            android.widget.TextView r9 = r0.mTitleTextView
            int r9 = r9.getMeasuredHeight()
            int r9 = r9 + r8
            android.widget.TextView r13 = r0.mTitleTextView
            r13.layout(r3, r8, r5, r9)
            int r8 = r0.mTitleMarginEnd
            int r5 = r5 + r8
            int r2 = r2.bottomMargin
            int r8 = r9 + r2
            goto L_0x0275
        L_0x0274:
            r5 = r3
        L_0x0275:
            if (r14 == 0) goto L_0x0299
            android.widget.TextView r2 = r0.mSubtitleTextView
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            androidx.appcompat.widget.Toolbar$LayoutParams r2 = (androidx.appcompat.widget.Toolbar.LayoutParams) r2
            int r2 = r2.topMargin
            int r8 = r8 + r2
            android.widget.TextView r2 = r0.mSubtitleTextView
            int r2 = r2.getMeasuredWidth()
            int r2 = r2 + r3
            android.widget.TextView r9 = r0.mSubtitleTextView
            int r9 = r9.getMeasuredHeight()
            int r9 = r9 + r8
            android.widget.TextView r13 = r0.mSubtitleTextView
            r13.layout(r3, r8, r2, r9)
            int r8 = r0.mTitleMarginEnd
            int r2 = r2 + r8
            goto L_0x029a
        L_0x0299:
            r2 = r3
        L_0x029a:
            if (r24 == 0) goto L_0x02a0
            int r3 = java.lang.Math.max(r5, r2)
        L_0x02a0:
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            r5 = 3
            r0.addCustomViewsWithGravity(r2, r5)
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            int r2 = r2.size()
            r5 = r3
            r3 = r1
        L_0x02ae:
            if (r3 >= r2) goto L_0x02bf
            java.util.ArrayList<android.view.View> r8 = r0.mTempViews
            java.lang.Object r8 = r8.get(r3)
            android.view.View r8 = (android.view.View) r8
            int r5 = r0.layoutChildLeft(r8, r5, r11, r12)
            int r3 = r3 + 1
            goto L_0x02ae
        L_0x02bf:
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            r3 = 5
            r0.addCustomViewsWithGravity(r2, r3)
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            int r2 = r2.size()
            r3 = r1
        L_0x02cc:
            if (r3 >= r2) goto L_0x02dd
            java.util.ArrayList<android.view.View> r8 = r0.mTempViews
            java.lang.Object r8 = r8.get(r3)
            android.view.View r8 = (android.view.View) r8
            int r10 = r0.layoutChildRight(r8, r10, r11, r12)
            int r3 = r3 + 1
            goto L_0x02cc
        L_0x02dd:
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            r3 = r20
            r0.addCustomViewsWithGravity(r2, r3)
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            int r2 = r0.getViewListMeasuredWidth(r2, r11)
            int r4 = r4 - r6
            int r4 = r4 - r7
            int r4 = r4 / 2
            int r4 = r4 + r6
            int r3 = r2 / 2
            int r4 = r4 - r3
            int r2 = r2 + r4
            if (r4 >= r5) goto L_0x02f6
            goto L_0x02fd
        L_0x02f6:
            if (r2 <= r10) goto L_0x02fc
            int r2 = r2 - r10
            int r5 = r4 - r2
            goto L_0x02fd
        L_0x02fc:
            r5 = r4
        L_0x02fd:
            java.util.ArrayList<android.view.View> r2 = r0.mTempViews
            int r2 = r2.size()
        L_0x0303:
            if (r1 >= r2) goto L_0x0314
            java.util.ArrayList<android.view.View> r3 = r0.mTempViews
            java.lang.Object r3 = r3.get(r1)
            android.view.View r3 = (android.view.View) r3
            int r5 = r0.layoutChildLeft(r3, r5, r11, r12)
            int r1 = r1 + 1
            goto L_0x0303
        L_0x0314:
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r0.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        int[] iArr;
        int i13;
        int i14;
        int i15;
        int i16;
        int[] iArr2 = this.mTempMargins;
        char isLayoutRtl = ViewUtils.isLayoutRtl(this);
        char c5 = isLayoutRtl ^ 1;
        int i17 = 0;
        if (shouldLayout(this.mNavButtonView)) {
            measureChildConstrained(this.mNavButtonView, i2, 0, i7, 0, this.mMaxButtonHeight);
            i11 = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
            int max = Math.max(0, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
            int combineMeasuredStates = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
            Drawable drawable = this.mNavButtonView.getDrawable();
            Drawable background = this.mNavButtonView.getBackground();
            if (!(drawable == null || background == null)) {
                int paddingLeft = (this.mNavButtonView.getPaddingLeft() - this.mNavButtonView.getPaddingRight()) / 2;
                DrawableCompat.setHotspotBounds(background, paddingLeft, 0, paddingLeft + i11, max);
            }
            i10 = max;
            i8 = combineMeasuredStates;
        } else {
            i11 = 0;
            i10 = 0;
            i8 = 0;
        }
        if (shouldLayout(this.mCollapseButtonView)) {
            measureChildConstrained(this.mCollapseButtonView, i2, 0, i7, 0, this.mMaxButtonHeight);
            i11 = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
            i10 = Math.max(i10, this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView));
            i8 = View.combineMeasuredStates(i8, this.mCollapseButtonView.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max2 = Math.max(currentContentInsetStart, i11);
        iArr2[isLayoutRtl] = Math.max(0, currentContentInsetStart - i11);
        if (shouldLayout(this.mMenuView)) {
            measureChildConstrained(this.mMenuView, i2, max2, i7, 0, this.mMaxButtonHeight);
            i12 = this.mMenuView.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
            i10 = Math.max(i10, this.mMenuView.getMeasuredHeight() + getVerticalMargins(this.mMenuView));
            i8 = View.combineMeasuredStates(i8, this.mMenuView.getMeasuredState());
        } else {
            i12 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max3 = max2 + Math.max(currentContentInsetEnd, i12);
        iArr2[c5] = Math.max(0, currentContentInsetEnd - i12);
        if (shouldLayout(this.mExpandedActionView)) {
            iArr = iArr2;
            max3 += measureChildCollapseMargins(this.mExpandedActionView, i2, max3, i7, 0, iArr);
            i10 = Math.max(i10, this.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView));
            i8 = View.combineMeasuredStates(i8, this.mExpandedActionView.getMeasuredState());
        } else {
            iArr = iArr2;
        }
        if (shouldLayout(this.mLogoView)) {
            max3 += measureChildCollapseMargins(this.mLogoView, i2, max3, i7, 0, iArr);
            i10 = Math.max(i10, this.mLogoView.getMeasuredHeight() + getVerticalMargins(this.mLogoView));
            i8 = View.combineMeasuredStates(i8, this.mLogoView.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt = getChildAt(i18);
            if (((LayoutParams) childAt.getLayoutParams()).mViewType != 0 || !shouldLayout(childAt)) {
                i13 = i13;
            } else {
                i13 += measureChildCollapseMargins(childAt, i2, i13, i7, 0, iArr);
                int max4 = Math.max(i10, childAt.getMeasuredHeight() + getVerticalMargins(childAt));
                i8 = View.combineMeasuredStates(i8, childAt.getMeasuredState());
                i10 = max4;
            }
        }
        int i19 = i13;
        int i20 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int i21 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (shouldLayout(this.mTitleTextView)) {
            Context context = getContext();
            int i22 = this.mTitleTextAppearance;
            int[] iArr3 = R$styleable.TextAppearance;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i22, iArr3);
            int i23 = R$styleable.TextAppearance_android_textSize;
            TypedValue peekValue = obtainStyledAttributes.peekValue(i23);
            float dimensionPixelSize = (float) getResources().getDimensionPixelSize(R$dimen.sesl_toolbar_title_text_size);
            if (!TextUtils.isEmpty(this.mSubtitleText)) {
                dimensionPixelSize = (float) getResources().getDimensionPixelSize(R$dimen.sesl_toolbar_title_text_size_with_subtitle);
            }
            if (peekValue != null && TextUtils.isEmpty(this.mSubtitleText)) {
                dimensionPixelSize = TypedValue.complexToFloat(peekValue.data);
            }
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(this.mSubtitleTextAppearance, iArr3);
            TypedValue peekValue2 = obtainStyledAttributes2.peekValue(i23);
            obtainStyledAttributes2.recycle();
            float dimensionPixelSize2 = (float) getResources().getDimensionPixelSize(R$dimen.sesl_toolbar_subtitle_text_size);
            if (peekValue2 != null) {
                dimensionPixelSize2 = TypedValue.complexToFloat(peekValue2.data);
            }
            float f = getContext().getResources().getConfiguration().fontScale;
            if (f > 1.2f) {
                f = 1.2f;
            }
            if (dimensionPixelSize == -1.0f || !TextUtils.isEmpty(this.mSubtitleText)) {
                this.mTitleTextView.setTextSize(0, dimensionPixelSize * f);
                this.mSubtitleTextView.setTextSize(1, dimensionPixelSize2 * f);
            } else {
                this.mTitleTextView.setTextSize(1, dimensionPixelSize * f);
            }
            measureChildCollapseMargins(this.mTitleTextView, i2, i19 + i21, i7, i20, iArr);
            int measuredWidth = this.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
            int measuredHeight = this.mTitleTextView.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
            i16 = measuredWidth;
            i14 = View.combineMeasuredStates(i8, this.mTitleTextView.getMeasuredState());
            i15 = measuredHeight;
        } else {
            i16 = 0;
            i14 = i8;
            i15 = 0;
        }
        if (shouldLayout(this.mSubtitleTextView)) {
            i16 = Math.max(i16, measureChildCollapseMargins(this.mSubtitleTextView, i2, i19 + i21, i7, i20 + i15, iArr));
            i15 += this.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView);
            i14 = View.combineMeasuredStates(i14, this.mSubtitleTextView.getMeasuredState());
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop() + Math.max(i10, i15);
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + i19 + i16, getSuggestedMinimumWidth()), i2, -16777216 & i14);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i7, i14 << 16);
        if (!shouldCollapse()) {
            i17 = resolveSizeAndState2;
        }
        setMeasuredDimension(resolveSizeAndState, i17);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuBuilder menuBuilder;
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            menuBuilder = actionMenuView.peekMenu();
        } else {
            menuBuilder = null;
        }
        int i2 = savedState.expandedMenuItemId;
        if (!(i2 == 0 || this.mExpandedMenuPresenter == null || menuBuilder == null || (findItem = menuBuilder.findItem(i2)) == null)) {
            findItem.expandActionView();
        }
        if (savedState.isOverflowOpen) {
            postShowOverflowMenu();
        }
    }

    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        ensureContentInsets();
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        rtlSpacingHelper.setDirection(z);
    }

    public Parcelable onSaveInstanceState() {
        MenuItemImpl menuItemImpl;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.mExpandedMenuPresenter;
        if (!(expandedActionViewMenuPresenter == null || (menuItemImpl = expandedActionViewMenuPresenter.mCurrentExpandedItem) == null)) {
            savedState.expandedMenuItemId = menuItemImpl.getItemId();
        }
        savedState.isOverflowOpen = isOverflowMenuShowing();
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = this.mAllowEatingTouch;
        if (!z) {
            return super.onTouchEvent(motionEvent);
        }
        return z;
    }

    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            seslSetTouchDelegateForToolbar();
        } else {
            seslRemoveListenerForTouchDelegate();
        }
    }

    public void removeChildrenForExpandedActionView() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((LayoutParams) childAt.getLayoutParams()).mViewType == 2 || childAt == this.mMenuView)) {
                removeViewAt(childCount);
                this.mHiddenViews.add(childAt);
            }
        }
    }

    public void removeMenuProvider(MenuProvider menuProvider) {
        this.mMenuHostHelper.removeMenuProvider(menuProvider);
    }

    public void removeView(View view) {
        super.removeView(view);
    }

    public View seslGetCustomView() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (((LayoutParams) childAt.getLayoutParams()).mViewType == 0) {
                return childAt;
            }
        }
        return null;
    }

    public ActionMenuView seslGetMenuView() {
        return this.mMenuView;
    }

    public void seslSetEatingHover(boolean z) {
        this.mForceNotEatingHover = !z;
    }

    public void seslSetEatingTouchOnly(boolean z) {
        if (this.mAllowEatingTouch != z) {
            this.mAllowEatingTouch = z;
        }
    }

    public void seslSetSubtitleAlpha(float f) {
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setAlpha(f);
        }
    }

    public void seslSetTitleAlpha(float f) {
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setAlpha(f);
        }
    }

    public void seslSetUserTopPadding(int i2) {
        this.mUserTopPadding = i2;
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if (this.mBackInvokedCallbackEnabled != z) {
            this.mBackInvokedCallbackEnabled = z;
            updateBackInvokedCallbackState();
        }
    }

    public void setCollapseContentDescription(int i2) {
        setCollapseContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setCollapseIcon(int i2) {
        setCollapseIcon(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetsRelative(int i2, int i7) {
        ensureContentInsets();
        this.mContentInsets.setRelative(i2, i7);
    }

    public void setLogo(int i2) {
        setLogo(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setMenu(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder != null || this.mMenuView != null) {
            ensureMenuView();
            MenuBuilder peekMenu = this.mMenuView.peekMenu();
            if (peekMenu != menuBuilder) {
                if (peekMenu != null) {
                    peekMenu.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    peekMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if (this.mExpandedMenuPresenter == null) {
                    this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
                }
                actionMenuPresenter.setExpandedActionViewsExclusive(true);
                if (menuBuilder != null) {
                    menuBuilder.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
                    menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                } else {
                    actionMenuPresenter.initForMenu(this.mPopupContext, (MenuBuilder) null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, (MenuBuilder) null);
                    actionMenuPresenter.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(actionMenuPresenter);
                this.mOuterActionMenuPresenter = actionMenuPresenter;
                updateBackInvokedCallbackState();
            }
        }
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.setMenuCallbacks(callback, callback2);
        }
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        ensureNavButtonView();
        this.mNavButtonViewListener = onClickListener;
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOverflowIcon(Drawable drawable) {
        ensureMenu();
        this.mMenuView.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.mPopupTheme != i2) {
            this.mPopupTheme = i2;
            if (i2 == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextAppearance(Context context, int i2) {
        this.mSubtitleTextAppearance = i2;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void setSubtitleTextColor(int i2) {
        setSubtitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleAccessibilityEnabled(boolean z) {
        if (z) {
            TextView textView = this.mTitleTextView;
            if (textView != null) {
                textView.setImportantForAccessibility(1);
                this.mTitleTextView.setFocusable(true);
            }
            TextView textView2 = this.mSubtitleTextView;
            if (textView2 != null) {
                textView2.setImportantForAccessibility(1);
                this.mSubtitleTextView.setFocusable(true);
                return;
            }
            return;
        }
        TextView textView3 = this.mTitleTextView;
        if (textView3 != null) {
            textView3.setImportantForAccessibility(2);
            this.mTitleTextView.setFocusable(false);
        }
        TextView textView4 = this.mSubtitleTextView;
        if (textView4 != null) {
            textView4.setImportantForAccessibility(2);
            this.mSubtitleTextView.setFocusable(false);
        }
    }

    public void setTitleMarginBottom(int i2) {
        this.mTitleMarginBottom = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.mTitleMarginEnd = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.mTitleMarginStart = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.mTitleMarginTop = i2;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i2) {
        this.mTitleTextAppearance = i2;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void setTitleTextColor(int i2) {
        setTitleTextColor(ColorStateList.valueOf(i2));
    }

    public boolean showOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || !actionMenuView.showOverflowMenu()) {
            return false;
        }
        return true;
    }

    public void updateBackInvokedCallbackState() {
        boolean z;
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher = Api33Impl.findOnBackInvokedDispatcher(this);
            if (!hasExpandedActionView() || findOnBackInvokedDispatcher == null || !isAttachedToWindow() || !this.mBackInvokedCallbackEnabled) {
                z = false;
            } else {
                z = true;
            }
            if (z && this.mBackInvokedDispatcher == null) {
                if (this.mBackInvokedCallback == null) {
                    this.mBackInvokedCallback = Api33Impl.newOnBackInvokedCallback(new a(this, 1));
                }
                Api33Impl.tryRegisterOnBackInvokedCallback(findOnBackInvokedDispatcher, this.mBackInvokedCallback);
                this.mBackInvokedDispatcher = findOnBackInvokedDispatcher;
            } else if (!z && (onBackInvokedDispatcher = this.mBackInvokedDispatcher) != null) {
                Api33Impl.tryUnregisterOnBackInvokedCallback(onBackInvokedDispatcher, this.mBackInvokedCallback);
                this.mBackInvokedDispatcher = null;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ActionBar.LayoutParams {
        int mViewType = 0;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void copyMarginsFromCompat(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
            this.gravity = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ActionBar.LayoutParams) layoutParams);
            this.mViewType = layoutParams.mViewType;
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super((ViewGroup.LayoutParams) marginLayoutParams);
            copyMarginsFromCompat(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<>();
        this.mHiddenViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mMenuHostHelper = new MenuHostHelper(new a(this, 2));
        this.mProvidedMenuItems = new ArrayList<>();
        this.mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (Toolbar.this.mMenuHostHelper.onMenuItemSelected(menuItem)) {
                    return true;
                }
                OnMenuItemClickListener onMenuItemClickListener = Toolbar.this.mOnMenuItemClickListener;
                if (onMenuItemClickListener != null) {
                    return onMenuItemClickListener.onMenuItemClick(menuItem);
                }
                return false;
            }
        };
        this.mShowOverflowMenuRunnable = new Runnable() {
            public void run() {
                Toolbar.this.showOverflowMenu();
            }
        };
        this.mUserTopPadding = -1;
        this.mNavButtonViewListener = null;
        this.mAllowEatingTouch = true;
        Context context2 = getContext();
        int[] iArr = R$styleable.Toolbar;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context2, attributeSet, iArr, i2, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        this.mTitleTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = obtainStyledAttributes.getInteger(R$styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = obtainStyledAttributes.getInteger(R$styleable.Toolbar_buttonGravity, 48);
        this.mBackground = obtainStyledAttributes.getDrawable(R$styleable.Toolbar_background);
        this.mNavTooltipText = obtainStyledAttributes.getText(R$styleable.Toolbar_tooltipText);
        setBackground(this.mBackground);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_titleMargin, 0);
        this.mTitleMarginBottom = dimensionPixelOffset;
        this.mTitleMarginTop = dimensionPixelOffset;
        this.mTitleMarginEnd = dimensionPixelOffset;
        this.mTitleMarginStart = dimensionPixelOffset;
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginStart, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.mTitleMarginStart = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginEnd, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.mTitleMarginEnd = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginTop, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.mTitleMarginTop = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginBottom, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.mTitleMarginBottom = dimensionPixelOffset5;
        }
        this.mMaxButtonHeight = obtainStyledAttributes.getDimensionPixelSize(R$styleable.Toolbar_maxButtonHeight, -1);
        int dimensionPixelOffset6 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.Toolbar_contentInsetLeft, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.Toolbar_contentInsetRight, 0);
        ensureContentInsets();
        this.mContentInsets.setAbsolute(dimensionPixelSize, dimensionPixelSize2);
        if (!(dimensionPixelOffset6 == Integer.MIN_VALUE && dimensionPixelOffset7 == Integer.MIN_VALUE)) {
            this.mContentInsets.setRelative(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.mContentInsetStartWithNavigation = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = obtainStyledAttributes.getDrawable(R$styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = obtainStyledAttributes.getText(R$styleable.Toolbar_collapseContentDescription);
        CharSequence text = obtainStyledAttributes.getText(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = obtainStyledAttributes.getText(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.mPopupContext = getContext();
        setPopupTheme(obtainStyledAttributes.getResourceId(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.Toolbar_navigationIcon);
        if (drawable != null) {
            setNavigationIcon(drawable);
        }
        CharSequence text3 = obtainStyledAttributes.getText(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R$styleable.Toolbar_logo);
        if (drawable2 != null) {
            setLogo(drawable2);
        }
        CharSequence text4 = obtainStyledAttributes.getText(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        int i7 = R$styleable.Toolbar_titleTextColor;
        if (obtainStyledAttributes.hasValue(i7)) {
            setTitleTextColor(obtainStyledAttributes.getColorStateList(i7));
        }
        int i8 = R$styleable.Toolbar_subtitleTextColor;
        if (obtainStyledAttributes.hasValue(i8)) {
            setSubtitleTextColor(obtainStyledAttributes.getColorStateList(i8));
        }
        int i10 = R$styleable.Toolbar_menu;
        if (obtainStyledAttributes.hasValue(i10)) {
            inflateMenu(obtainStyledAttributes.getResourceId(i10, 0));
        }
        obtainStyledAttributes.recycle();
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureCollapseButtonView();
        }
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
            TooltipCompat.setTooltipText(this.mCollapseButtonView, charSequence);
            this.mCollapseDescription = charSequence;
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            ensureCollapseButtonView();
            this.mCollapseButtonView.setImageDrawable(drawable);
            return;
        }
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            imageButton.setImageDrawable(this.mCollapseIcon);
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            ensureLogoView();
            if (!isChildOrHidden(this.mLogoView)) {
                addSystemView(this.mLogoView, true);
            }
        } else {
            ImageView imageView = this.mLogoView;
            if (imageView != null && isChildOrHidden(imageView)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView imageView2 = this.mLogoView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureLogoView();
        }
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureNavButtonView();
        }
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
            TooltipCompat.setTooltipText(this.mNavButtonView, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            ensureNavButtonView();
            if (!isChildOrHidden(this.mNavButtonView)) {
                addSystemView(this.mNavButtonView, true);
            }
        } else {
            ImageButton imageButton = this.mNavButtonView;
            if (imageButton != null && isChildOrHidden(imageButton)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton imageButton2 = this.mNavButtonView;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
            this.mNavButtonIconDrawable = drawable;
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.mSubtitleTextView = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.mSubtitleTextAppearance;
                if (i2 != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.mSubtitleTextColor;
                if (colorStateList != null) {
                    this.mSubtitleTextView.setTextColor(colorStateList);
                }
                TextView textView = this.mTitleTextView;
                if (textView != null) {
                    this.mSubtitleTextView.setAlpha(textView.getAlpha());
                }
            }
            if (!isChildOrHidden(this.mSubtitleTextView)) {
                addSystemView(this.mSubtitleTextView, true);
            }
        } else {
            TextView textView2 = this.mSubtitleTextView;
            if (textView2 != null && isChildOrHidden(textView2)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        }
        TextView textView3 = this.mSubtitleTextView;
        if (textView3 != null) {
            textView3.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.mSubtitleTextColor = colorStateList;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.mTitleTextView = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.mTitleTextAppearance;
                if (i2 != 0) {
                    this.mTitleTextView.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.mTitleTextColor;
                if (colorStateList != null) {
                    this.mTitleTextView.setTextColor(colorStateList);
                }
            }
            if (!isChildOrHidden(this.mTitleTextView)) {
                addSystemView(this.mTitleTextView, true);
            }
        } else {
            TextView textView = this.mTitleTextView;
            if (textView != null && isChildOrHidden(textView)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        }
        TextView textView2 = this.mTitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.mTitleTextColor = colorStateList;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        int expandedMenuItemId;
        boolean isOverflowOpen;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.expandedMenuItemId = parcel.readInt();
            this.isOverflowOpen = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.expandedMenuItemId);
            parcel.writeInt(this.isOverflowOpen ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, layoutParams);
        startCustomViewAddAnimation(view);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ActionBar.LayoutParams) {
            return new LayoutParams((ActionBar.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        public ExpandedActionViewMenuPresenter() {
        }

        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            View view = Toolbar.this.mExpandedActionView;
            if (view instanceof CollapsibleActionView) {
                ((CollapsibleActionView) view).onActionViewCollapsed();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.mExpandedActionView);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.mCollapseButtonView);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.mExpandedActionView = null;
            toolbar3.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            Toolbar.this.updateBackInvokedCallbackState();
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            ViewParent parent;
            Toolbar toolbar;
            Toolbar.this.ensureCollapseButtonView();
            ViewParent parent2 = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar toolbar2 = Toolbar.this;
            if (parent2 != toolbar2) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar2.mCollapseButtonView);
                }
                Toolbar toolbar3 = Toolbar.this;
                toolbar3.addView(toolbar3.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = menuItemImpl.getActionView();
            this.mCurrentExpandedItem = menuItemImpl;
            View view = Toolbar.this.mExpandedActionView;
            if (!(view == null || (parent = view.getParent()) == (toolbar = Toolbar.this))) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.mExpandedActionView);
                }
                LayoutParams generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.gravity = (toolbar4.mButtonGravity & 112) | 8388611;
                generateDefaultLayoutParams.mViewType = 2;
                toolbar4.mExpandedActionView.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(true);
            View view2 = Toolbar.this.mExpandedActionView;
            if (view2 instanceof CollapsibleActionView) {
                ((CollapsibleActionView) view2).onActionViewExpanded();
            }
            Toolbar.this.updateBackInvokedCallbackState();
            return true;
        }

        public boolean flagActionItems() {
            return false;
        }

        public int getId() {
            return 0;
        }

        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            MenuItemImpl menuItemImpl;
            MenuBuilder menuBuilder2 = this.mMenu;
            if (!(menuBuilder2 == null || (menuItemImpl = this.mCurrentExpandedItem) == null)) {
                menuBuilder2.collapseItemActionView(menuItemImpl);
            }
            this.mMenu = menuBuilder;
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public void updateMenuView(boolean z) {
            if (this.mCurrentExpandedItem != null) {
                MenuBuilder menuBuilder = this.mMenu;
                if (menuBuilder != null) {
                    int size = menuBuilder.size();
                    int i2 = 0;
                    while (i2 < size) {
                        if (this.mMenu.getItem(i2) != this.mCurrentExpandedItem) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                }
                collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }
    }

    private void startCustomViewAddAnimation(View view) {
    }
}
