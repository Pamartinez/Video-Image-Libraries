package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.graphics.Insets;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, NestedScrollingParent2, NestedScrollingParent3 {
    static final int[] ATTRS = {R$attr.actionBarSize, 16842841};
    private static final Rect EMPTY_RECT = new Rect();
    private static final WindowInsetsCompat NON_EMPTY_SYSTEM_WINDOW_INSETS = new WindowInsetsCompat.Builder().setSystemWindowInsets(Insets.of(0, 1, 0, 1)).build();
    private static final Rect ZERO_INSETS = new Rect();
    private boolean mActionBarExtendsIntoSystemInsets = false;
    private int mActionBarHeight;
    ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    boolean mAnimatingForFling;
    private final Rect mBaseContentInsets = new Rect();
    private WindowInsetsCompat mBaseInnerInsets;
    private final Rect mBaseInnerInsetsRect = new Rect();
    private ContentFrameLayout mContent;
    private final Rect mContentInsets = new Rect();
    ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private boolean mDecorFitsSystemWindows = true;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private WindowInsetsCompat mInnerInsets;
    private final Rect mInnerInsetsRect = new Rect();
    private final Rect mLastBaseContentInsets = new Rect();
    private WindowInsetsCompat mLastBaseInnerInsets;
    private final Rect mLastBaseInnerInsetsRect = new Rect();
    private WindowInsetsCompat mLastInnerInsets;
    private final Rect mLastInnerInsetsRect = new Rect();
    private int mLastSystemUiVisibility;
    private final NoSystemUiLayoutFlagView mNoSystemUiLayoutFlagView;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final Rect mSystemInsets = new Rect();
    private final Rect mTmpRect = new Rect();
    final AnimatorListenerAdapter mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ActionBarVisibilityCallback {
        void enableContentAnimations(boolean z);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int i2);

        void showForSystem();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class NoSystemUiLayoutFlagView extends View {
        public NoSystemUiLayoutFlagView(Context context) {
            super(context);
            setWillNotDraw(true);
        }

        public int getWindowSystemUiVisibility() {
            return 0;
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.CONSUMED;
        this.mBaseInnerInsets = windowInsetsCompat;
        this.mLastBaseInnerInsets = windowInsetsCompat;
        this.mInnerInsets = windowInsetsCompat;
        this.mLastInnerInsets = windowInsetsCompat;
        this.mTopAnimatorListener = new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.mCurrentActionBarTopAnimator = null;
                actionBarOverlayLayout.mAnimatingForFling = false;
            }

            public void onAnimationEnd(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.mCurrentActionBarTopAnimator = null;
                actionBarOverlayLayout.mAnimatingForFling = false;
            }
        };
        this.mRemoveActionBarHideOffset = new Runnable() {
            public void run() {
                ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.mCurrentActionBarTopAnimator = actionBarOverlayLayout.mActionBarTop.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
            }
        };
        this.mAddActionBarHideOffset = new Runnable() {
            public void run() {
                ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.mCurrentActionBarTopAnimator = actionBarOverlayLayout.mActionBarTop.animate().translationY((float) (-ActionBarOverlayLayout.this.mActionBarTop.getHeight())).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
            }
        };
        init(context);
        this.mParentHelper = new NestedScrollingParentHelper(this);
        NoSystemUiLayoutFlagView noSystemUiLayoutFlagView = new NoSystemUiLayoutFlagView(context);
        this.mNoSystemUiLayoutFlagView = noSystemUiLayoutFlagView;
        addView(noSystemUiLayoutFlagView);
    }

    private void addActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View view, Rect rect, boolean z, boolean z3, boolean z7, boolean z9, boolean z10) {
        if (z) {
            View view2 = view;
            return setMargin(view2, EMPTY_RECT, z3, z7, z9, z10) | setPadding(view2, rect, z3, z7, z9, z10);
        }
        View view3 = view;
        Rect rect2 = rect;
        View view4 = view3;
        return setMargin(view4, rect2, z3, z7, z9, z10) | setPadding(view4, EMPTY_RECT, z3, z7, z9, z10);
    }

    private boolean decorFitsSystemWindows() {
        ViewCompat.computeSystemWindowInsets(this.mNoSystemUiLayoutFlagView, NON_EMPTY_SYSTEM_WINDOW_INSETS, this.mTmpRect);
        return !this.mTmpRect.equals(ZERO_INSETS);
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of ".concat(view.getClass().getSimpleName()));
    }

    private void init(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(ATTRS);
        boolean z = false;
        this.mActionBarHeight = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.mWindowContentOverlay = drawable;
        if (drawable == null) {
            z = true;
        }
        setWillNotDraw(z);
        obtainStyledAttributes.recycle();
        this.mFlingEstimator = new OverScroller(context);
    }

    private void postAddActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void postRemoveActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    private void removeActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    private boolean setMargin(View view, Rect rect, boolean z, boolean z3, boolean z7, boolean z9) {
        boolean z10;
        int i2;
        int i7;
        int i8;
        int i10;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!z || layoutParams.leftMargin == (i10 = rect.left)) {
            z10 = false;
        } else {
            layoutParams.leftMargin = i10;
            z10 = true;
        }
        if (z3 && layoutParams.topMargin != (i8 = rect.top)) {
            layoutParams.topMargin = i8;
            z10 = true;
        }
        if (z7 && layoutParams.rightMargin != (i7 = rect.right)) {
            layoutParams.rightMargin = i7;
            z10 = true;
        }
        if (!z9 || layoutParams.bottomMargin == (i2 = rect.bottom)) {
            return z10;
        }
        layoutParams.bottomMargin = i2;
        return true;
    }

    private boolean setPadding(View view, Rect rect, boolean z, boolean z3, boolean z7, boolean z9) {
        int i2;
        int i7;
        int i8;
        int i10;
        if ((!z || view.getPaddingLeft() == rect.left) && ((!z3 || view.getPaddingTop() == rect.top) && ((!z7 || view.getPaddingRight() == rect.right) && (!z9 || view.getPaddingBottom() == rect.bottom)))) {
            return false;
        }
        if (z) {
            i2 = rect.left;
        } else {
            i2 = view.getPaddingLeft();
        }
        if (z3) {
            i7 = rect.top;
        } else {
            i7 = view.getPaddingTop();
        }
        if (z7) {
            i8 = rect.right;
        } else {
            i8 = view.getPaddingRight();
        }
        if (z9) {
            i10 = rect.bottom;
        } else {
            i10 = view.getPaddingBottom();
        }
        view.setPadding(i2, i7, i8, i10);
        return true;
    }

    private boolean shouldHideActionBarOnFling(float f) {
        this.mFlingEstimator.fling(0, 0, 0, (int) f, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight()) {
            return true;
        }
        return false;
    }

    public boolean canShowOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void dismissPopups() {
        pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    public void draw(Canvas canvas) {
        int i2;
        super.draw(canvas);
        if (this.mWindowContentOverlay != null) {
            if (this.mActionBarTop.getVisibility() == 0) {
                i2 = (int) (this.mActionBarTop.getTranslationY() + ((float) this.mActionBarTop.getBottom()) + 0.5f);
            } else {
                i2 = 0;
            }
            this.mWindowContentOverlay.setBounds(0, i2, getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + i2);
            this.mWindowContentOverlay.draw(canvas);
        }
    }

    public boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.mActionBarTop;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public CharSequence getTitle() {
        pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    public void haltActionBarHideOffsetAnimations() {
        removeCallbacks(this.mRemoveActionBarHideOffset);
        removeCallbacks(this.mAddActionBarHideOffset);
        ViewPropertyAnimator viewPropertyAnimator = this.mCurrentActionBarTopAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public boolean hideOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public void initFeature(int i2) {
        pullChildren();
        if (i2 == 2) {
            this.mDecorToolbar.initProgress();
        } else if (i2 == 5) {
            this.mDecorToolbar.initIndeterminateProgress();
        } else if (i2 == 109) {
            setOverlayMode(true);
        }
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    public boolean isOverflowMenuShowPending() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        boolean z;
        boolean z3;
        boolean z7;
        pullChildren();
        int windowSystemUiVisibility = getWindowSystemUiVisibility();
        boolean z9 = false;
        boolean z10 = true;
        if ((windowSystemUiVisibility & 256) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((windowSystemUiVisibility & 1536) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean decorFitsSystemWindows = decorFitsSystemWindows();
        this.mDecorFitsSystemWindows = decorFitsSystemWindows;
        if (!decorFitsSystemWindows || (z && z3)) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.mActionBarExtendsIntoSystemInsets = z7;
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            if (!z && !z7) {
                z9 = true;
            }
            actionBarVisibilityCallback.enableContentAnimations(z9);
        }
        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, this);
        this.mSystemInsets.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        boolean applyInsets = applyInsets(this.mActionBarTop, this.mSystemInsets, this.mActionBarExtendsIntoSystemInsets, true, true, true, false);
        ViewCompat.computeSystemWindowInsets(this, windowInsetsCompat, this.mBaseContentInsets);
        Rect rect = this.mBaseContentInsets;
        WindowInsetsCompat inset = windowInsetsCompat.inset(rect.left, rect.top, rect.right, rect.bottom);
        this.mBaseInnerInsets = inset;
        if (!this.mLastBaseInnerInsets.equals(inset)) {
            this.mLastBaseInnerInsets = this.mBaseInnerInsets;
            applyInsets = true;
        }
        if (!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
        } else {
            z10 = applyInsets;
        }
        if (z10) {
            requestLayout();
        }
        return windowInsetsCompat.consumeDisplayCutout().consumeSystemWindowInsets().consumeStableInsets().toWindowInsets();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        init(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i12 = layoutParams.leftMargin + paddingLeft;
                int i13 = layoutParams.topMargin + paddingTop;
                childAt.layout(i12, i13, measuredWidth + i12, measuredHeight + i13);
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        boolean z;
        int i8;
        Insets insets;
        pullChildren();
        int i10 = i2;
        int i11 = i7;
        measureChildWithMargins(this.mActionBarTop, i10, 0, i11, 0);
        LayoutParams layoutParams = (LayoutParams) this.mActionBarTop.getLayoutParams();
        int max = Math.max(0, this.mActionBarTop.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
        int max2 = Math.max(0, this.mActionBarTop.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.mActionBarTop.getMeasuredState());
        if ((ViewCompat.getWindowSystemUiVisibility(this) & 256) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i8 = this.mActionBarHeight;
            if (this.mActionBarExtendsIntoSystemInsets) {
                i8 += this.mSystemInsets.top;
            }
            if (this.mHasNonEmbeddedTabs && this.mActionBarTop.getTabContainer() != null) {
                i8 += this.mActionBarHeight;
            }
        } else if (this.mActionBarTop.getVisibility() != 8) {
            i8 = this.mActionBarTop.getMeasuredHeight();
        } else {
            i8 = 0;
        }
        this.mContentInsets.set(this.mBaseContentInsets);
        WindowInsetsCompat windowInsetsCompat = this.mBaseInnerInsets;
        this.mInnerInsets = windowInsetsCompat;
        if (this.mOverlayMode || z || !this.mDecorFitsSystemWindows) {
            if (this.mActionBarExtendsIntoSystemInsets) {
                insets = Insets.of(windowInsetsCompat.getSystemWindowInsetLeft(), Math.max(this.mInnerInsets.getSystemWindowInsetTop(), i8), this.mInnerInsets.getSystemWindowInsetRight(), Math.max(this.mInnerInsets.getSystemWindowInsetBottom(), 0));
            } else {
                insets = Insets.of(windowInsetsCompat.getSystemWindowInsetLeft(), this.mInnerInsets.getSystemWindowInsetTop() + i8, this.mInnerInsets.getSystemWindowInsetRight(), this.mInnerInsets.getSystemWindowInsetBottom());
            }
            this.mInnerInsets = new WindowInsetsCompat.Builder(this.mInnerInsets).setSystemWindowInsets(insets).build();
        } else {
            if (this.mActionBarExtendsIntoSystemInsets) {
                Rect rect = this.mContentInsets;
                rect.top = Math.max(rect.top, i8);
                Rect rect2 = this.mContentInsets;
                rect2.bottom = Math.max(rect2.bottom, 0);
            } else {
                Rect rect3 = this.mContentInsets;
                rect3.top += i8;
                rect3.bottom = rect3.bottom;
            }
            this.mInnerInsets = this.mInnerInsets.inset(0, i8, 0, 0);
        }
        setMargin(this.mContent, this.mContentInsets, true, true, true, true);
        if (!this.mLastInnerInsets.equals(this.mInnerInsets)) {
            WindowInsetsCompat windowInsetsCompat2 = this.mInnerInsets;
            this.mLastInnerInsets = windowInsetsCompat2;
            ViewCompat.dispatchApplyWindowInsets(this.mContent, windowInsetsCompat2);
        }
        measureChildWithMargins(this.mContent, i10, 0, i11, 0);
        LayoutParams layoutParams2 = (LayoutParams) this.mContent.getLayoutParams();
        int max3 = Math.max(max, this.mContent.getMeasuredWidth() + layoutParams2.leftMargin + layoutParams2.rightMargin);
        int max4 = Math.max(max2, this.mContent.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.mContent.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + max3, getSuggestedMinimumWidth()), i10, combineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + max4, getSuggestedMinimumHeight()), i11, combineMeasuredStates2 << 16));
    }

    public boolean onNestedFling(View view, float f, float f5, boolean z) {
        if (!this.mHideOnContentScroll || !z) {
            return false;
        }
        if (shouldHideActionBarOnFling(f5)) {
            addActionBarHideOffset();
        } else {
            removeActionBarHideOffset();
        }
        this.mAnimatingForFling = true;
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f5) {
        return false;
    }

    public void onNestedPreScroll(View view, int i2, int i7, int[] iArr) {
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        onNestedScroll(view, i2, i7, i8, i10, i11);
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i7) {
        if (i7 == 0) {
            onNestedScrollAccepted(view, view2, i2);
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i2, int i7) {
        return i7 == 0 && onStartNestedScroll(view, view2, i2);
    }

    public void onStopNestedScroll(View view, int i2) {
        if (i2 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Deprecated
    public void onWindowSystemUiVisibilityChanged(int i2) {
        boolean z;
        boolean z3;
        super.onWindowSystemUiVisibilityChanged(i2);
        pullChildren();
        int i7 = this.mLastSystemUiVisibility ^ i2;
        this.mLastSystemUiVisibility = i2;
        boolean z7 = false;
        if ((i2 & 4) == 0) {
            z = true;
        } else {
            z = false;
        }
        if ((i2 & 256) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            if (!z3 && !this.mActionBarExtendsIntoSystemInsets) {
                z7 = true;
            }
            actionBarVisibilityCallback.enableContentAnimations(z7);
            if (z || !z3) {
                this.mActionBarVisibilityCallback.showForSystem();
            } else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if ((i7 & 256) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets(this);
        }
    }

    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.mWindowVisibility = i2;
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.onWindowVisibilityChanged(i2);
        }
    }

    public void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.mDecorToolbar = getDecorToolbar(findViewById(R$id.action_bar));
        }
    }

    public void setActionBarHideOffset(int i2) {
        haltActionBarHideOffsetAnimations();
        this.mActionBarTop.setTranslationY((float) (-Math.max(0, Math.min(i2, this.mActionBarTop.getHeight()))));
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarVisibilityCallback) {
        this.mActionBarVisibilityCallback = actionBarVisibilityCallback;
        if (getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            int i2 = this.mLastSystemUiVisibility;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                ViewCompat.requestApplyInsets(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.mHasNonEmbeddedTabs = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = z;
            if (!z) {
                haltActionBarHideOffsetAnimations();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int i2) {
        pullChildren();
        this.mDecorToolbar.setIcon(i2);
    }

    public void setLogo(int i2) {
        pullChildren();
        this.mDecorToolbar.setLogo(i2);
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        pullChildren();
        this.mDecorToolbar.setMenu(menu, callback);
    }

    public void setMenuPrepared() {
        pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean z) {
        this.mOverlayMode = z;
    }

    public void setWindowCallback(Window.Callback callback) {
        pullChildren();
        this.mDecorToolbar.setWindowCallback(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        pullChildren();
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void onNestedPreScroll(View view, int i2, int i7, int[] iArr, int i8) {
        if (i8 == 0) {
            onNestedPreScroll(view, i2, i7, iArr);
        }
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11) {
        if (i11 == 0) {
            onNestedScroll(view, i2, i7, i8, i10);
        }
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i2);
        this.mHideOnContentScrollReference = getActionBarHideOffset();
        haltActionBarHideOffsetAnimations();
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.onContentScrollStarted();
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.mActionBarTop.getVisibility() != 0) {
            return false;
        }
        return this.mHideOnContentScroll;
    }

    public void onStopNestedScroll(View view) {
        if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                postRemoveActionBarHideOffset();
            } else {
                postAddActionBarHideOffset();
            }
        }
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.onContentScrollStopped();
        }
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10) {
        int i11 = this.mHideOnContentScrollReference + i7;
        this.mHideOnContentScrollReference = i11;
        setActionBarHideOffset(i11);
    }

    public void setIcon(Drawable drawable) {
        pullChildren();
        this.mDecorToolbar.setIcon(drawable);
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i2) {
    }
}
