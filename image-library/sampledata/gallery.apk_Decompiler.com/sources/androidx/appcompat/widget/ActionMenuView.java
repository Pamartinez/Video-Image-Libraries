package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    private int mActionButtonPaddingEnd;
    private int mActionButtonPaddingStart;
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private int mLastItemEndPadding;
    private MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private String mOverflowBadgeText;
    private int mOverflowButtonMinWidth;
    private int mOverflowButtonPaddingEnd;
    private int mOverflowButtonPaddingStart;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
            this.isOverflowButton = false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            OnMenuItemClickListener onMenuItemClickListener = ActionMenuView.this.mOnMenuItemClickListener;
            if (onMenuItemClickListener == null || !onMenuItemClickListener.onMenuItemClick(menuItem)) {
                return false;
            }
            return true;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            MenuBuilder.Callback callback = ActionMenuView.this.mMenuBuilderCallback;
            if (callback != null) {
                callback.onMenuModeChange(menuBuilder);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    private int getNumericValue(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    public static int measureChildForCells(View view, int i2, int i7, int i8, int i10) {
        ActionMenuItemView actionMenuItemView;
        boolean z;
        int i11;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i8) - i10, View.MeasureSpec.getMode(i8));
        if (view instanceof ActionMenuItemView) {
            actionMenuItemView = (ActionMenuItemView) view;
        } else {
            actionMenuItemView = null;
        }
        boolean z3 = false;
        if (actionMenuItemView == null || !actionMenuItemView.hasText()) {
            z = false;
        } else {
            z = true;
        }
        if (i7 > 0) {
            i11 = 2;
            if (!z || i7 >= 2) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i7 * i2, Integer.MIN_VALUE), makeMeasureSpec);
                int measuredWidth = view.getMeasuredWidth();
                int i12 = measuredWidth / i2;
                if (measuredWidth % i2 != 0) {
                    i12++;
                }
                if (!z || i12 >= 2) {
                    i11 = i12;
                }
                if (!layoutParams.isOverflowButton && z) {
                    z3 = true;
                }
                layoutParams.expandable = z3;
                layoutParams.cellsUsed = i11;
                view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i11, 1073741824), makeMeasureSpec);
                return i11;
            }
        }
        i11 = 0;
        z3 = true;
        layoutParams.expandable = z3;
        layoutParams.cellsUsed = i11;
        view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i11, 1073741824), makeMeasureSpec);
        return i11;
    }

    private void onMeasureExactFormat(int i2, int i7) {
        boolean z;
        long j2;
        int i8;
        int i10;
        boolean z3;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z7;
        int i15;
        boolean z9;
        boolean z10;
        boolean z11;
        int i16;
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i7);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, paddingBottom, -2);
        int i17 = size - paddingRight;
        int i18 = this.mMinCellSize;
        int i19 = i17 / i18;
        int i20 = i17 % i18;
        if (i19 == 0) {
            setMeasuredDimension(i17, 0);
            return;
        }
        int i21 = (i20 / i19) + i18;
        int childCount = getChildCount();
        int i22 = 0;
        int i23 = 0;
        boolean z12 = false;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        long j3 = 0;
        while (i23 < childCount) {
            View childAt = getChildAt(i23);
            int i27 = size2;
            if (childAt.getVisibility() == 8) {
                i15 = paddingBottom;
            } else {
                boolean z13 = childAt instanceof ActionMenuItemView;
                i24++;
                if (z13) {
                    int i28 = this.mGeneratedItemPadding;
                    z9 = z13;
                    z10 = false;
                    childAt.setPadding(i28, 0, i28, 0);
                } else {
                    z9 = z13;
                    z10 = false;
                }
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.expanded = z10;
                layoutParams.extraPixels = z10 ? 1 : 0;
                layoutParams.cellsUsed = z10;
                layoutParams.expandable = z10;
                layoutParams.leftMargin = z10;
                layoutParams.rightMargin = z10;
                if (!z9 || !((ActionMenuItemView) childAt).hasText()) {
                    z11 = false;
                } else {
                    z11 = true;
                }
                layoutParams.preventEdgeOffset = z11;
                if (layoutParams.isOverflowButton) {
                    i16 = 1;
                } else {
                    i16 = i19;
                }
                int measureChildForCells = measureChildForCells(childAt, i21, i16, childMeasureSpec, paddingBottom);
                i25 = Math.max(i25, measureChildForCells);
                i15 = paddingBottom;
                if (layoutParams.expandable) {
                    i26++;
                }
                if (layoutParams.isOverflowButton) {
                    z12 = true;
                }
                i19 -= measureChildForCells;
                i22 = Math.max(i22, childAt.getMeasuredHeight());
                if (measureChildForCells == 1) {
                    j3 |= (long) (1 << i23);
                    i19 = i19;
                } else {
                    int i29 = i19;
                }
            }
            i23++;
            size2 = i27;
            paddingBottom = i15;
        }
        int i30 = size2;
        char c5 = 2;
        if (!z12 || i24 != 2) {
            z = false;
        } else {
            z = true;
        }
        boolean z14 = false;
        while (true) {
            if (i26 <= 0 || i19 <= 0) {
                char c6 = c5;
                i8 = i21;
                i10 = i22;
                j2 = 1;
            } else {
                long j8 = 0;
                char c8 = c5;
                int i31 = Integer.MAX_VALUE;
                int i32 = 0;
                int i33 = 0;
                j2 = 1;
                while (i33 < childCount) {
                    boolean z15 = z;
                    LayoutParams layoutParams2 = (LayoutParams) getChildAt(i33).getLayoutParams();
                    int i34 = i21;
                    if (layoutParams2.expandable) {
                        int i35 = layoutParams2.cellsUsed;
                        if (i35 < i31) {
                            j8 = 1 << i33;
                            i31 = i35;
                            i32 = 1;
                        } else if (i35 == i31) {
                            j8 |= 1 << i33;
                            i32++;
                        }
                    }
                    i33++;
                    i21 = i34;
                    z = z15;
                }
                boolean z16 = z;
                i8 = i21;
                j3 |= j8;
                if (i32 > i19) {
                    i10 = i22;
                    break;
                }
                int i36 = i31 + 1;
                int i37 = 0;
                while (i37 < childCount) {
                    View childAt2 = getChildAt(i37);
                    LayoutParams layoutParams3 = (LayoutParams) childAt2.getLayoutParams();
                    int i38 = i22;
                    long j10 = (long) (1 << i37);
                    if ((j8 & j10) == 0) {
                        if (layoutParams3.cellsUsed == i36) {
                            j3 |= j10;
                        }
                        i14 = i37;
                    } else {
                        if (!z16 || !layoutParams3.preventEdgeOffset) {
                            i14 = i37;
                            z7 = true;
                        } else if (i19 == 1) {
                            int i39 = this.mGeneratedItemPadding;
                            z7 = true;
                            i14 = i37;
                            childAt2.setPadding(i39 + i8, 0, i39, 0);
                        } else {
                            i14 = i37;
                            z7 = true;
                        }
                        layoutParams3.cellsUsed++;
                        layoutParams3.expanded = z7;
                        i19--;
                    }
                    i37 = i14 + 1;
                    i22 = i38;
                }
                i21 = i8;
                c5 = c8;
                z = z16;
                z14 = true;
            }
        }
        if (z12 || i24 != 1) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (i19 <= 0 || j3 == 0 || (i19 >= i24 - 1 && !z3 && i25 <= 1)) {
            i11 = 0;
        } else {
            float bitCount = (float) Long.bitCount(j3);
            if (!z3) {
                if ((j3 & j2) != 0) {
                    i11 = 0;
                    if (!((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                        bitCount -= 0.5f;
                    }
                } else {
                    i11 = 0;
                }
                int i40 = childCount - 1;
                if ((j3 & ((long) (1 << i40))) != 0 && !((LayoutParams) getChildAt(i40).getLayoutParams()).preventEdgeOffset) {
                    bitCount -= 0.5f;
                }
            } else {
                i11 = 0;
            }
            if (bitCount > 0.0f) {
                i13 = (int) (((float) (i19 * i8)) / bitCount);
            } else {
                i13 = i11;
            }
            for (int i41 = i11; i41 < childCount; i41++) {
                if ((j3 & ((long) (1 << i41))) != 0) {
                    View childAt3 = getChildAt(i41);
                    LayoutParams layoutParams4 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams4.extraPixels = i13;
                        layoutParams4.expanded = true;
                        if (i41 == 0 && !layoutParams4.preventEdgeOffset) {
                            layoutParams4.leftMargin = (-i13) / 2;
                        }
                        z14 = true;
                    } else if (layoutParams4.isOverflowButton) {
                        layoutParams4.extraPixels = i13;
                        layoutParams4.expanded = true;
                        layoutParams4.rightMargin = (-i13) / 2;
                        z14 = true;
                    } else {
                        if (i41 != 0) {
                            layoutParams4.leftMargin = i13 / 2;
                        }
                        if (i41 != childCount - 1) {
                            layoutParams4.rightMargin = i13 / 2;
                        }
                    }
                }
            }
        }
        if (z14) {
            for (int i42 = i11; i42 < childCount; i42++) {
                View childAt4 = getChildAt(i42);
                LayoutParams layoutParams5 = (LayoutParams) childAt4.getLayoutParams();
                if (layoutParams5.expanded) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((layoutParams5.cellsUsed * i8) + layoutParams5.extraPixels, 1073741824), childMeasureSpec);
                }
            }
        }
        if (mode != 1073741824) {
            i12 = i10;
        } else {
            i12 = i30;
        }
        setMeasuredDimension(i17, i12);
    }

    private void updateValue() {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.View, R$attr.actionOverflowButtonStyle, 0);
        this.mOverflowButtonMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.View_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        Resources resources = getResources();
        int i2 = R$dimen.sesl_action_button_side_padding;
        this.mActionButtonPaddingStart = resources.getDimensionPixelSize(i2);
        this.mActionButtonPaddingEnd = getResources().getDimensionPixelSize(i2);
        this.mOverflowButtonPaddingStart = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_overflow_padding_start);
        this.mOverflowButtonPaddingEnd = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_overflow_padding_end);
        this.mLastItemEndPadding = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_last_padding);
    }

    public void addView(View view) {
        super.addView(view);
        addShowAnimation(view);
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus();
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.isOverflowButton = true;
        return generateDefaultLayoutParams;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.mMenu = menuBuilder;
            menuBuilder.setCallback(new MenuBuilderCallback());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.mPresenter = actionMenuPresenter;
            actionMenuPresenter.setReserveOverflow(true);
            ActionMenuPresenter actionMenuPresenter2 = this.mPresenter;
            MenuPresenter.Callback callback = this.mActionMenuPresenterCallback;
            if (callback == null) {
                callback = new ActionMenuPresenterCallback();
            }
            actionMenuPresenter2.setCallback(callback);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    public String getOverflowBadgeText() {
        return this.mOverflowBadgeText;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public int getSumOfDigitsInBadges() {
        if (this.mMenu == null) {
            return 0;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < this.mMenu.size(); i7++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.mMenu.getItem(i7);
            if (menuItemImpl.isVisible()) {
                i2 += getNumericValue(menuItemImpl.getBadgeText());
            }
        }
        return i2;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean hasSupportDividerBeforeChildAt(int i2) {
        boolean z = false;
        if (i2 == 0) {
            return false;
        }
        View childAt = getChildAt(i2 - 1);
        View childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        if (i2 <= 0 || !(childAt2 instanceof ActionMenuChildView)) {
            return z;
        }
        return ((ActionMenuChildView) childAt2).needsDividerBefore() | z;
    }

    public boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter == null || !actionMenuPresenter.hideOverflowMenu()) {
            return false;
        }
        return true;
    }

    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            return menuBuilder.performItemAction(menuItemImpl, 0);
        }
        return false;
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter == null || !actionMenuPresenter.isOverflowMenuShowPending()) {
            return false;
        }
        return true;
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter == null || !actionMenuPresenter.isOverflowMenuShowing()) {
            return false;
        }
        return true;
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.onConfigurationChanged(configuration);
            this.mPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
        updateValue();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        int i13;
        if (!this.mFormatItems) {
            super.onLayout(z, i2, i7, i8, i10);
            return;
        }
        int childCount = getChildCount();
        int i14 = (i10 - i7) / 2;
        int dividerWidth = getDividerWidth();
        int i15 = i8 - i2;
        int paddingRight = (i15 - getPaddingRight()) - getPaddingLeft();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i16 = 0;
        int i17 = 0;
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt = getChildAt(i18);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(i18)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (isLayoutRtl) {
                        i12 = getPaddingLeft() + layoutParams.leftMargin;
                        i13 = i12 + measuredWidth;
                    } else {
                        i13 = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        i12 = i13 - measuredWidth;
                    }
                    int i19 = i14 - (measuredHeight / 2);
                    childAt.layout(i12, i19, i13, measuredHeight + i19);
                    paddingRight -= measuredWidth;
                    i16 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                    hasSupportDividerBeforeChildAt(i18);
                    i17++;
                }
            }
        }
        if (childCount == 1 && i16 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i20 = (i15 / 2) - (measuredWidth2 / 2);
            int i21 = i14 - (measuredHeight2 / 2);
            childAt2.layout(i20, i21, measuredWidth2 + i20, measuredHeight2 + i21);
            return;
        }
        int i22 = i17 - (i16 ^ 1);
        if (i22 > 0) {
            i11 = paddingRight / i22;
        } else {
            i11 = 0;
        }
        int max = Math.max(0, i11);
        if (isLayoutRtl) {
            int width = getWidth() - getPaddingRight();
            for (int i23 = 0; i23 < childCount; i23++) {
                View childAt3 = getChildAt(i23);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                    int i24 = width - layoutParams2.rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i25 = i14 - (measuredHeight3 / 2);
                    childAt3.layout(i24 - measuredWidth3, i25, i24, measuredHeight3 + i25);
                    width = i24 - ((measuredWidth3 + layoutParams2.leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i26 = 0; i26 < childCount; i26++) {
            View childAt4 = getChildAt(i26);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                int i27 = paddingLeft + layoutParams3.leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i28 = i14 - (measuredHeight4 / 2);
                childAt4.layout(i27, i28, i27 + measuredWidth4, measuredHeight4 + i28);
                paddingLeft = measuredWidth4 + layoutParams3.rightMargin + max + i27;
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        boolean z;
        MenuBuilder menuBuilder;
        boolean z3 = this.mFormatItems;
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            z = true;
        } else {
            z = false;
        }
        this.mFormatItems = z;
        if (z3 != z) {
            this.mFormatItemsWidth = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (!(!this.mFormatItems || (menuBuilder = this.mMenu) == null || size == this.mFormatItemsWidth)) {
            this.mFormatItemsWidth = size;
            menuBuilder.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (!this.mFormatItems || childCount <= 0) {
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
                if (childAt instanceof ActionMenuItemView) {
                    ViewCompat.setPaddingRelative(childAt, this.mActionButtonPaddingStart, 0, this.mActionButtonPaddingEnd, 0);
                    int i10 = childCount - 1;
                    if (i8 == i10) {
                        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) childAt;
                        if (!actionMenuItemView.hasText()) {
                            actionMenuItemView.setIsLastItem(true);
                        }
                        childAt.setLayoutParams(layoutParams);
                    } else if (i8 < i10) {
                        ActionMenuItemView actionMenuItemView2 = (ActionMenuItemView) childAt;
                        if (!actionMenuItemView2.hasText()) {
                            actionMenuItemView2.setIsLastItem(false);
                        }
                    }
                } else if (layoutParams.isOverflowButton) {
                    if (childAt instanceof ActionMenuPresenter.OverflowMenuButton) {
                        ViewGroup viewGroup = (ViewGroup) childAt;
                        viewGroup.getChildAt(0).setPaddingRelative(this.mOverflowButtonPaddingStart, 0, this.mOverflowButtonPaddingEnd, 0);
                        viewGroup.getChildAt(0).setMinimumWidth(this.mOverflowButtonMinWidth);
                    } else {
                        ViewCompat.setPaddingRelative(childAt, this.mOverflowButtonPaddingStart, 0, this.mOverflowButtonPaddingEnd, 0);
                        childAt.setMinimumWidth(this.mOverflowButtonMinWidth);
                    }
                }
            }
            super.onMeasure(i2, i7);
            return;
        }
        onMeasureExactFormat(i2, i7);
    }

    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public boolean seslIsShowOverflowButton() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).isOverflowButton) {
                return true;
            }
        }
        return false;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.setExpandedActionViewsExclusive(z);
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.mPresenter.setOverflowIcon(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
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

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        actionMenuPresenter.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter == null || !actionMenuPresenter.showOverflowMenu()) {
            return false;
        }
        return true;
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
        this.mOverflowBadgeText = context.getResources().getString(R$string.sesl_action_menu_overflow_badge_text_n);
        updateValue();
    }

    public void addView(View view, int i2) {
        super.addView(view, i2);
        addShowAnimation(view);
    }

    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, layoutParams);
        addShowAnimation(view);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        if (layoutParams instanceof LayoutParams) {
            layoutParams2 = new LayoutParams((LayoutParams) layoutParams);
        } else {
            layoutParams2 = new LayoutParams(layoutParams);
        }
        if (layoutParams2.gravity <= 0) {
            layoutParams2.gravity = 16;
        }
        return layoutParams2;
    }

    private void addShowAnimation(View view) {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }
    }
}
