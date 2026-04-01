package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionBarContextView extends AbsActionBarView {
    private static float MAX_FONT_SCALE = 1.2f;
    private boolean mAllowEatingTouch;
    private boolean mCheckActionModeOn;
    private View mClose;
    private View mCloseButton;
    private int mCloseItemLayout;
    private View mCustomView;
    private boolean mIsActionModeAccessibilityOn;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void ensureMenuView() {
        if (this.mMenuView != null) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.sesl_action_menu_view_padding_end);
            this.mMenuView.setPadding(getResources().getDimensionPixelSize(R$dimen.sesl_action_menu_view_padding_start), 0, dimensionPixelSize, 0);
        }
    }

    private void initTitle() {
        int i2;
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.sesl_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleLayout = linearLayout;
            this.mTitleView = (TextView) linearLayout.findViewById(R$id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R$id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean isEmpty = TextUtils.isEmpty(this.mTitle);
        boolean isEmpty2 = TextUtils.isEmpty(this.mSubtitle);
        TextView textView = this.mSubtitleView;
        int i7 = 8;
        if (!isEmpty2) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        LinearLayout linearLayout2 = this.mTitleLayout;
        if (!isEmpty || !isEmpty2) {
            i7 = 0;
        }
        linearLayout2.setVisibility(i7);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    public void closeMode() {
        if (this.mClose == null) {
            killMode();
        }
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public boolean getIsActionModeAccessibilityOn() {
        return this.mIsActionModeAccessibilityOn;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void initForMode(final ActionMode actionMode) {
        this.mCheckActionModeOn = true;
        View view = this.mClose;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
            this.mClose = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.mClose);
        }
        View findViewById = this.mClose.findViewById(R$id.action_mode_close_button);
        this.mCloseButton = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                actionMode.finish();
            }
        });
        MenuBuilder menuBuilder = (MenuBuilder) actionMode.getMenu();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter = actionMenuPresenter2;
        actionMenuPresenter2.setReserveOverflow(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        menuBuilder.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        ActionMenuView actionMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        this.mMenuView = actionMenuView;
        actionMenuView.setBackground((Drawable) null);
        ensureMenuView();
        addView(this.mMenuView, layoutParams);
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
        this.mActionMenuPresenter = null;
        View view = this.mCloseButton;
        if (view != null) {
            view.setOnClickListener((View.OnClickListener) null);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding);
        setPadding(0, dimensionPixelSize, 0, 0);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.ActionMode, 16843668, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMode_height, -1);
        obtainStyledAttributes.recycle();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = dimensionPixelSize2 + dimensionPixelSize;
        setLayoutParams(layoutParams);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.ActionMode, 16843668, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMode_height, -1);
        if (dimensionPixelSize >= 0) {
            setContentHeight(dimensionPixelSize);
        }
        setPadding(0, getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding), 0, 0);
        obtainStyledAttributes.recycle();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            Log.d("ActionBarContextView", "onInitializeAccessibilityEvent Check ActionMode :" + this.mCheckActionModeOn);
            if (this.mCheckActionModeOn) {
                this.mIsActionModeAccessibilityOn = true;
                this.mCheckActionModeOn = false;
            } else {
                this.mIsActionModeAccessibilityOn = false;
            }
            Log.d("ActionBarContextView", "onInitializeAccessibilityEvent mIsActionModeAccessibilityOn :" + this.mIsActionModeAccessibilityOn);
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        int paddingRight;
        int i12;
        int i13;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        if (isLayoutRtl) {
            i11 = (i8 - i2) - getPaddingRight();
        } else {
            i11 = getPaddingLeft();
        }
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i10 - i7) - getPaddingTop()) - getPaddingBottom();
        View view = this.mClose;
        if (!(view == null || view.getVisibility() == 8)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            if (isLayoutRtl) {
                i12 = marginLayoutParams.rightMargin;
            } else {
                i12 = marginLayoutParams.leftMargin;
            }
            if (isLayoutRtl) {
                i13 = marginLayoutParams.leftMargin;
            } else {
                i13 = marginLayoutParams.rightMargin;
            }
            int i14 = i13;
            int next = AbsActionBarView.next(i11, i12, isLayoutRtl);
            i11 = AbsActionBarView.next(next + positionChild(this.mClose, next, paddingTop, paddingTop2, isLayoutRtl), i14, isLayoutRtl);
        }
        int i15 = i11;
        LinearLayout linearLayout = this.mTitleLayout;
        if (!(linearLayout == null || this.mCustomView != null || linearLayout.getVisibility() == 8)) {
            i15 += positionChild(this.mTitleLayout, i15, paddingTop, paddingTop2, isLayoutRtl);
        }
        View view2 = this.mCustomView;
        if (view2 != null) {
            positionChild(view2, i15, paddingTop, paddingTop2, isLayoutRtl);
        }
        if (isLayoutRtl) {
            paddingRight = getPaddingLeft();
        } else {
            paddingRight = (i8 - i2) - getPaddingRight();
        }
        int i16 = paddingRight;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            positionChild(actionMenuView, i16, paddingTop, paddingTop2, !isLayoutRtl);
        }
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        boolean z;
        int i11 = 1073741824;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        } else if (View.MeasureSpec.getMode(i7) != 0) {
            int size = View.MeasureSpec.getSize(i2);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding);
            int i12 = this.mContentHeight;
            if (i12 > 0) {
                i8 = i12 + dimensionPixelSize;
            } else {
                i8 = View.MeasureSpec.getSize(i7);
            }
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i13 = i8 - paddingBottom;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i13, Integer.MIN_VALUE);
            View view = this.mClose;
            if (view != null && view.getVisibility() == 0) {
                int measureChildView = measureChildView(this.mClose, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
                paddingLeft = measureChildView - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = this.mMenuView;
            if (!(actionMenuView == null || actionMenuView.getParent() != this || this.mMenuView.getChildCount() == 0)) {
                paddingLeft = measureChildView(this.mMenuView, paddingLeft, makeMeasureSpec, 0);
            }
            if (this.mTitleLayout != null && this.mCustomView == null) {
                Context context = getContext();
                boolean z3 = true;
                if (this.mTitleView != null) {
                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.mTitleStyleRes, R$styleable.TextAppearance);
                    TypedValue peekValue = obtainStyledAttributes.peekValue(R$styleable.TextAppearance_android_textSize);
                    obtainStyledAttributes.recycle();
                    float complexToFloat = TypedValue.complexToFloat(peekValue.data);
                    if (TextUtils.isEmpty(this.mSubtitle)) {
                        this.mTitleView.setTextSize(1, complexToFloat * Math.min(context.getResources().getConfiguration().fontScale, MAX_FONT_SCALE));
                    } else {
                        this.mTitleView.setTextSize(1, complexToFloat);
                    }
                }
                View view2 = this.mClose;
                int i14 = 8;
                if (view2 == null || view2.getVisibility() == 8) {
                    int dimension = (int) context.getResources().getDimension(R$dimen.sesl_toolbar_content_inset);
                    if (ViewCompat.getLayoutDirection(this) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    TextView textView = this.mTitleView;
                    if (textView != null && textView.getVisibility() == 0) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTitleView.getLayoutParams();
                        if (z) {
                            layoutParams.leftMargin = dimension;
                        } else {
                            layoutParams.rightMargin = dimension;
                        }
                        this.mTitleView.setLayoutParams(layoutParams);
                    }
                    TextView textView2 = this.mSubtitleView;
                    if (textView2 != null && textView2.getVisibility() == 0) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mSubtitleView.getLayoutParams();
                        if (z) {
                            layoutParams2.leftMargin = dimension;
                        } else {
                            layoutParams2.rightMargin = dimension;
                        }
                        this.mSubtitleView.setLayoutParams(layoutParams2);
                    }
                }
                if (this.mTitleOptional) {
                    this.mTitleLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.mTitleLayout.getMeasuredWidth();
                    if (measuredWidth > paddingLeft) {
                        z3 = false;
                    }
                    if (z3) {
                        paddingLeft -= measuredWidth;
                    }
                    LinearLayout linearLayout = this.mTitleLayout;
                    if (z3) {
                        i14 = 0;
                    }
                    linearLayout.setVisibility(i14);
                } else {
                    paddingLeft = measureChildView(this.mTitleLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view3 = this.mCustomView;
            if (view3 != null) {
                ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                int i15 = layoutParams3.width;
                if (i15 != -2) {
                    i10 = 1073741824;
                } else {
                    i10 = Integer.MIN_VALUE;
                }
                if (i15 >= 0) {
                    paddingLeft = Math.min(i15, paddingLeft);
                }
                int i16 = layoutParams3.height;
                if (i16 == -2) {
                    i11 = Integer.MIN_VALUE;
                }
                if (i16 >= 0) {
                    i13 = Math.min(i16, i13);
                }
                this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i10), View.MeasureSpec.makeMeasureSpec(i13, i11));
            }
            if (this.mContentHeight <= 0) {
                int childCount = getChildCount();
                int i17 = 0;
                for (int i18 = 0; i18 < childCount; i18++) {
                    int measuredHeight = getChildAt(i18).getMeasuredHeight() + paddingBottom;
                    if (measuredHeight > i17) {
                        i17 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i17);
                return;
            }
            setMeasuredDimension(size, i8);
        } else {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mAllowEatingTouch;
    }

    public View seslGetCloseButton() {
        return this.mCloseButton;
    }

    public View seslGetCustomView() {
        return this.mCustomView;
    }

    public ActionMenuView seslGetMenuView() {
        return this.mMenuView;
    }

    public void seslSetEatingTouchOnly(boolean z) {
        if (this.mAllowEatingTouch != z) {
            this.mAllowEatingTouch = z;
        }
    }

    public void setContentHeight(int i2) {
        this.mContentHeight = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.mCustomView;
        if (view2 != null) {
            removeView(view2);
        }
        this.mCustomView = view;
        if (!(view == null || (linearLayout = this.mTitleLayout) == null)) {
            removeView(linearLayout);
            this.mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        initTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        initTitle();
        ViewCompat.setAccessibilityPaneTitle(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z;
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i2, long j2) {
        return super.setupAnimatorToVisibility(i2, j2);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mAllowEatingTouch = true;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.ActionMode, i2, 0);
        setBackground(obtainStyledAttributes.getDrawable(R$styleable.ActionMode_background));
        this.mTitleStyleRes = obtainStyledAttributes.getResourceId(R$styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = obtainStyledAttributes.getResourceId(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(R$styleable.ActionMode_height, 0);
        this.mCloseItemLayout = obtainStyledAttributes.getResourceId(R$styleable.ActionMode_closeItemLayout, R$layout.sesl_action_mode_close_item);
        obtainStyledAttributes.recycle();
    }
}
