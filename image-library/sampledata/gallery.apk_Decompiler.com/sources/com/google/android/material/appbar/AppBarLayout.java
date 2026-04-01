package com.google.android.material.appbar;

import Q1.a;
import S1.b;
import S1.c;
import S1.d;
import S1.e;
import S1.f;
import S1.l;
import S1.m;
import S1.o;
import S1.y;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.animation.SeslAnimationUtils;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.util.SeslMisc;
import androidx.coordinatorlayout.widget.AppBarLayoutBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.oneui.floatingactioncontainer.FloatingTopLayout$FloatingTopBehavior;
import com.sec.android.gallery3d.R;
import h2.q;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import o1.C0246a;
import og.k;
import q2.C0266A;
import q2.z;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior, AppBarLayoutBehavior {
    private static final int DEF_STYLE_RES = 2131952914;
    /* access modifiers changed from: private */
    public static final int SYSTEM_BARS = WindowInsetsCompat.Type.systemBars();
    /* access modifiers changed from: private */
    public static final String TAG = "AppBarLayout";
    /* access modifiers changed from: private */
    public static final int TAPPABLE_ELEMENT = WindowInsetsCompat.Type.tappableElement();
    private final float appBarElevation;
    private Behavior behavior;
    private int currentOffset;
    private int downPreScrollRange = -1;
    private int downScrollRange = -1;
    private final boolean hasLiftOnScrollColor;
    private boolean haveChildWithInterpolator;
    private boolean isMouse = false;
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;
    private ValueAnimator liftOnScrollColorAnimator;
    private final long liftOnScrollColorDuration;
    private final TimeInterpolator liftOnScrollColorInterpolator;
    private ValueAnimator.AnimatorUpdateListener liftOnScrollColorUpdateListener;
    private final List<Object> liftOnScrollListeners = new ArrayList();
    private WeakReference<View> liftOnScrollTargetView;
    private int liftOnScrollTargetViewId;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted = false;
    private List<c> listeners;
    private int mAdditionalScrollRange = 0;
    private boolean mAllowStartNestedScroll = true;
    private boolean mAllowStateToHideAppRequestValue = true;
    private boolean mAllowStateToHideInternal = true;
    private boolean mAllowStateToHideRequested = false;
    List<Animator.AnimatorListener> mAnimateOffsetListener;
    List<b> mAppBarStateChangedListener;
    private f mAppbarState;
    private Drawable mBackground;
    private int mBottomPadding = 0;
    private float mCollapsedHeight;
    private int mCurrentOrientation;
    private int mCurrentScreenHeight;
    private int mCustomHeight = -1;
    private float mCustomHeightProportion;
    private boolean mHasSuggestion = false;
    private float mHeightProportion;
    private List<Object> mImmOffsetListener;
    private int mImmersiveTopInset = 0;
    private boolean mIsActivatedByUser = false;
    private boolean mIsActivatedImmersiveScroll = false;
    private boolean mIsCanImmScroll = false;
    private boolean mIsDetachedState = false;
    boolean mIsFirstLayoutAppBar = true;
    private boolean mIsLiftHided;
    private boolean mIsReservedImmersiveDetachOption = false;
    /* access modifiers changed from: private */
    public Insets mLastSysInsets;
    /* access modifiers changed from: private */
    public Insets mLastTappableInsets;
    private boolean mOrientationChanged;
    private int mProportionExtraHeight = 0;
    private boolean mRequestedForceExpanded = false;
    private boolean mReservedFitSystemWindow = false;
    private Resources mResources;
    private boolean mRestoreAnim = false;
    private boolean mSetCustomHeight;
    private boolean mSetCustomProportion;
    private boolean mUseCollapsedHeight = false;
    private boolean mUseCustomHeight;
    private boolean mUseCustomPadding;
    private boolean mUseFloatingToolbar = true;
    private int pendingAction = 0;
    private Drawable statusBarForeground;
    private Integer statusBarForegroundOriginalColor;
    private int[] tmpStatesArray;
    private int totalScrollRange = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScrollingViewBehavior extends l {
        public ScrollingViewBehavior() {
        }

        public static AppBarLayout e(List list) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) list.get(i2);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        public final boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).getBehavior();
            if (behavior instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).f1349p) + this.f786h) - d(view2));
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.isLiftOnScroll()) {
                return false;
            }
            appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
            return false;
        }

        public final void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout, (AccessibilityDelegateCompat) null);
            }
        }

        public final boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout e = e(coordinatorLayout.getDependencies(view));
            if (e != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                int width = coordinatorLayout.getWidth();
                int height = coordinatorLayout.getHeight();
                Rect rect3 = this.f;
                rect3.set(0, 0, width, height);
                if (!rect3.contains(rect2)) {
                    e.setExpanded(false, !z);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f619I);
            this.f787i = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r2v2, types: [S1.f, java.lang.Object] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppBarLayout(android.content.Context r13, android.util.AttributeSet r14) {
        /*
            r12 = this;
            int r6 = DEF_STYLE_RES
            r5 = 2130968642(0x7f040042, float:1.7545943E38)
            android.content.Context r0 = D2.a.a(r13, r14, r5, r6)
            r12.<init>(r0, r14, r5)
            r8 = -1
            r12.totalScrollRange = r8
            r12.downPreScrollRange = r8
            r12.downScrollRange = r8
            r9 = 0
            r12.pendingAction = r9
            r12.lifted = r9
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r12.liftOnScrollListeners = r0
            r10 = 1
            r12.mIsFirstLayoutAppBar = r10
            r12.mCustomHeight = r8
            r12.mAllowStartNestedScroll = r10
            r12.mProportionExtraHeight = r9
            r12.mRequestedForceExpanded = r9
            r12.mBottomPadding = r9
            r12.mUseCollapsedHeight = r9
            r12.isMouse = r9
            r12.mIsReservedImmersiveDetachOption = r9
            r12.mReservedFitSystemWindow = r9
            r12.mIsDetachedState = r9
            r12.mHasSuggestion = r9
            r12.mUseFloatingToolbar = r10
            r12.mAllowStateToHideInternal = r10
            r12.mAllowStateToHideAppRequestValue = r10
            r12.mAllowStateToHideRequested = r9
            r12.mIsActivatedImmersiveScroll = r9
            r12.mIsActivatedByUser = r9
            r12.mIsCanImmScroll = r9
            r12.mRestoreAnim = r9
            r12.mAdditionalScrollRange = r9
            r12.mImmersiveTopInset = r9
            r0 = 0
            r12.mLastTappableInsets = r0
            r12.mLastSysInsets = r0
            android.content.Context r11 = r12.getContext()
            r12.setOrientation(r10)
            android.content.Context r2 = r12.getContext()
            int[] r4 = S1.y.f794a
            int[] r7 = new int[r9]
            r3 = r14
            android.content.res.TypedArray r4 = h2.p.d(r2, r3, r4, r5, r6, r7)
            boolean r3 = r4.hasValue(r9)     // Catch:{ all -> 0x0077 }
            if (r3 == 0) goto L_0x007a
            int r3 = r4.getResourceId(r9, r9)     // Catch:{ all -> 0x0077 }
            android.animation.StateListAnimator r2 = android.animation.AnimatorInflater.loadStateListAnimator(r2, r3)     // Catch:{ all -> 0x0077 }
            r12.setStateListAnimator(r2)     // Catch:{ all -> 0x0077 }
            goto L_0x007a
        L_0x0077:
            r0 = move-exception
            goto L_0x0280
        L_0x007a:
            r4.recycle()
            int[] r4 = Q1.a.f626a
            int[] r7 = new int[r9]
            r3 = r14
            r2 = r11
            android.content.res.TypedArray r6 = h2.p.d(r2, r3, r4, r5, r6, r7)
            r7 = r2
            S1.f r2 = new S1.f
            r2.<init>()
            r2.f779a = r9
            r12.mAppbarState = r2
            android.content.res.Resources r2 = r12.getResources()
            r12.mResources = r2
            boolean r2 = androidx.appcompat.util.SeslMisc.isLightTheme(r7)
            boolean r3 = r6.hasValue(r9)
            if (r3 == 0) goto L_0x00ab
            android.graphics.drawable.Drawable r2 = r6.getDrawable(r9)
            r12.mBackground = r2
            androidx.core.view.ViewCompat.setBackground(r12, r2)
            goto L_0x00bd
        L_0x00ab:
            r12.mBackground = r0
            android.content.res.Resources r3 = r12.mResources
            if (r2 == 0) goto L_0x00b4
            int r2 = androidx.appcompat.R$color.sesl_action_bar_background_color_light
            goto L_0x00b6
        L_0x00b4:
            int r2 = androidx.appcompat.R$color.sesl_action_bar_background_color_dark
        L_0x00b6:
            int r2 = r3.getColor(r2)
            r12.setBackgroundColor(r2)
        L_0x00bd:
            r2 = 7
            android.content.res.ColorStateList r3 = B1.a.u(r7, r6, r2)
            if (r3 == 0) goto L_0x00c6
            r2 = r10
            goto L_0x00c7
        L_0x00c6:
            r2 = r9
        L_0x00c7:
            r12.hasLiftOnScrollColor = r2
            android.graphics.drawable.Drawable r2 = r12.getBackground()
            android.content.res.ColorStateList r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.B(r2)
            if (r2 == 0) goto L_0x0114
            x2.g r4 = new x2.g
            r4.<init>()
            r4.k(r2)
            if (r3 == 0) goto L_0x0106
            android.content.Context r5 = r12.getContext()
            r11 = 2130968882(0x7f040132, float:1.754643E38)
            android.util.TypedValue r11 = og.k.J(r5, r11)
            if (r11 == 0) goto L_0x00f9
            int r0 = r11.resourceId
            if (r0 == 0) goto L_0x00f3
            int r0 = androidx.core.content.ContextCompat.getColor(r5, r0)
            goto L_0x00f5
        L_0x00f3:
            int r0 = r11.data
        L_0x00f5:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x00f9:
            r5 = r0
            S1.a r0 = new S1.a
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r12.liftOnScrollColorUpdateListener = r0
            androidx.core.view.ViewCompat.setBackground(r12, r4)
            goto L_0x0114
        L_0x0106:
            r4.i(r7)
            Kb.b r0 = new Kb.b
            r2 = 1
            r0.<init>(r2, r12, r4)
            r12.liftOnScrollColorUpdateListener = r0
            androidx.core.view.ViewCompat.setBackground(r12, r4)
        L_0x0114:
            android.content.res.Resources r0 = r12.getResources()
            r2 = 2131361792(0x7f0a0000, float:1.8343346E38)
            int r0 = r0.getInteger(r2)
            r2 = 2130969543(0x7f0403c7, float:1.754777E38)
            int r0 = og.k.L(r7, r2, r0)
            long r2 = (long) r0
            r12.liftOnScrollColorDuration = r2
            r0 = 2130969561(0x7f0403d9, float:1.7547807E38)
            android.view.animation.LinearInterpolator r2 = R1.a.f640a
            android.animation.TimeInterpolator r0 = og.k.M(r7, r0, r2)
            r12.liftOnScrollColorInterpolator = r0
            r0 = 5
            boolean r2 = r6.hasValue(r0)
            if (r2 == 0) goto L_0x0141
            boolean r0 = r6.getBoolean(r0, r9)
            r12.f(r0, r9, r9)
        L_0x0141:
            r0 = 10
            boolean r2 = r6.hasValue(r0)
            if (r2 == 0) goto L_0x0152
            boolean r0 = r6.getBoolean(r0, r9)
            if (r0 == 0) goto L_0x0152
            r12.seslSetHide(r9, r10)
        L_0x0152:
            r0 = 4
            boolean r2 = r6.hasValue(r0)
            if (r2 == 0) goto L_0x0161
            int r2 = r6.getDimensionPixelSize(r0, r9)
            float r2 = (float) r2
            S1.y.a(r12, r2)
        L_0x0161:
            r2 = 11
            boolean r3 = r6.hasValue(r2)
            if (r3 == 0) goto L_0x016f
            boolean r2 = r6.getBoolean(r2, r9)
            r12.mUseCustomHeight = r2
        L_0x016f:
            r2 = 9
            boolean r3 = r6.hasValue(r2)
            r4 = 1053273620(0x3ec7ae14, float:0.39)
            if (r3 == 0) goto L_0x0183
            r12.mSetCustomProportion = r10
            float r2 = r6.getFloat(r2, r4)
            r12.mCustomHeightProportion = r2
            goto L_0x0187
        L_0x0183:
            r12.mSetCustomProportion = r9
            r12.mCustomHeightProportion = r4
        L_0x0187:
            android.content.Context r2 = r12.getContext()
            float r2 = S1.m.b(r2)
            r12.mHeightProportion = r2
            r2 = 12
            boolean r3 = r6.hasValue(r2)
            if (r3 == 0) goto L_0x019f
            boolean r2 = r6.getBoolean(r2, r9)
            r12.mUseCustomPadding = r2
        L_0x019f:
            boolean r2 = r12.mUseCustomPadding
            if (r2 == 0) goto L_0x01aa
            int r2 = r6.getDimensionPixelSize(r10, r9)
            r12.mBottomPadding = r2
            goto L_0x01b5
        L_0x01aa:
            android.content.res.Resources r2 = r12.mResources
            r3 = 2131168136(0x7f070b88, float:1.7950565E38)
            int r2 = r2.getDimensionPixelOffset(r3)
            r12.mBottomPadding = r2
        L_0x01b5:
            int r2 = r12.mBottomPadding
            r12.setPadding(r9, r9, r9, r2)
            android.content.res.Resources r2 = r12.mResources
            int r3 = androidx.appcompat.R$dimen.sesl_action_bar_height_with_padding
            int r2 = r2.getDimensionPixelSize(r3)
            int r3 = r12.mBottomPadding
            int r2 = r2 + r3
            float r2 = (float) r2
            r12.mCollapsedHeight = r2
            r12.e(r2, r9)
            boolean r2 = r6.hasValue(r0)
            if (r2 == 0) goto L_0x01d9
            int r0 = r6.getDimensionPixelSize(r0, r9)
            float r0 = (float) r0
            S1.y.a(r12, r0)
        L_0x01d9:
            r0 = 3
            boolean r2 = r6.hasValue(r0)
            if (r2 == 0) goto L_0x01e7
            boolean r0 = r6.getBoolean(r0, r9)
            r12.setKeyboardNavigationCluster(r0)
        L_0x01e7:
            r0 = 2
            boolean r2 = r6.hasValue(r0)
            if (r2 == 0) goto L_0x01f5
            boolean r0 = r6.getBoolean(r0, r9)
            r12.setTouchscreenBlocksFocus(r0)
        L_0x01f5:
            android.content.res.Resources r0 = r12.getResources()
            r2 = 2131165633(0x7f0701c1, float:1.7945489E38)
            float r0 = r0.getDimension(r2)
            r12.appBarElevation = r0
            r0 = 6
            boolean r0 = r6.getBoolean(r0, r9)
            r12.liftOnScroll = r0
            r0 = 8
            int r0 = r6.getResourceId(r0, r8)
            r12.liftOnScrollTargetViewId = r0
            r0 = 13
            boolean r0 = r6.getBoolean(r0, r10)
            r12.mUseFloatingToolbar = r0
            r0 = 14
            android.graphics.drawable.Drawable r0 = r6.getDrawable(r0)
            r12.setStatusBarForeground(r0)
            r6.recycle()
            B1.b r0 = new B1.b
            r2 = 6
            r0.<init>(r2, r12)
            androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(r12, r0)
            android.content.res.Resources r0 = r12.mResources
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.orientation
            r12.mCurrentOrientation = r0
            android.content.res.Resources r0 = r12.mResources
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.screenHeightDp
            r12.mCurrentScreenHeight = r0
            java.lang.String r0 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Init : mUseCustomHeight = "
            r2.<init>(r3)
            boolean r3 = r12.mUseCustomHeight
            r2.append(r3)
            java.lang.String r3 = ", mCustomHeightProportion = "
            r2.append(r3)
            float r3 = r12.mCustomHeightProportion
            r2.append(r3)
            java.lang.String r3 = ", mHeightProportion = "
            r2.append(r3)
            float r3 = r12.mHeightProportion
            r2.append(r3)
            java.lang.String r3 = ", mUseCustomPadding = "
            r2.append(r3)
            boolean r3 = r12.mUseCustomPadding
            r2.append(r3)
            java.lang.String r3 = ", mCurrentScreenHeight = "
            r2.append(r3)
            int r1 = r12.mCurrentScreenHeight
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            android.util.Log.i(r0, r1)
            return
        L_0x0280:
            r4.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public static void a(AppBarLayout appBarLayout, C0340g gVar, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        gVar.j(floatValue);
        Drawable drawable = appBarLayout.statusBarForeground;
        if (drawable instanceof C0340g) {
            ((C0340g) drawable).j(floatValue);
        }
        Iterator<Object> it = appBarLayout.liftOnScrollListeners.iterator();
        if (it.hasNext()) {
            throw C0212a.h(it);
        }
    }

    public static void b(AppBarLayout appBarLayout, ColorStateList colorStateList, ColorStateList colorStateList2, C0340g gVar, Integer num, ValueAnimator valueAnimator) {
        Integer num2;
        int c02 = C0246a.c0(((Float) valueAnimator.getAnimatedValue()).floatValue(), colorStateList.getDefaultColor(), colorStateList2.getDefaultColor());
        gVar.k(ColorStateList.valueOf(c02));
        if (!(appBarLayout.statusBarForeground == null || (num2 = appBarLayout.statusBarForegroundOriginalColor) == null || !num2.equals(num))) {
            DrawableCompat.setTint(appBarLayout.statusBarForeground, c02);
        }
        if (!appBarLayout.liftOnScrollListeners.isEmpty()) {
            for (Object obj : appBarLayout.liftOnScrollListeners) {
                if (obj != null) {
                    throw new ClassCastException();
                } else if (gVar.d.f2104c != null) {
                    throw null;
                }
            }
        }
    }

    private float getDifferImmHeightRatio() {
        float windowHeight = (float) getWindowHeight();
        float immersiveTopInset = (float) getImmersiveTopInset();
        if (windowHeight == 0.0f) {
            windowHeight = 1.0f;
        }
        return immersiveTopInset / windowHeight;
    }

    private float getHeightPercent() {
        if (!this.mUseCustomHeight) {
            return this.mHeightProportion;
        }
        float f = this.mCustomHeightProportion;
        float f5 = 0.0f;
        if (f == 0.0f) {
            return 0.0f;
        }
        if (getCanImmScroll()) {
            f5 = getDifferImmHeightRatio();
        }
        return f + f5;
    }

    /* access modifiers changed from: private */
    public SeslImmersiveScrollBehavior getImmBehavior() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            return null;
        }
        CoordinatorLayout.Behavior behavior2 = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (behavior2 instanceof SeslImmersiveScrollBehavior) {
            return (SeslImmersiveScrollBehavior) behavior2;
        }
        return null;
    }

    private int getWindowHeight() {
        if (this.mIsActivatedImmersiveScroll) {
            return getContext().getResources().getDisplayMetrics().heightPixels;
        }
        return m.c(this);
    }

    public void addOnOffsetChangedListener(c cVar) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        if (cVar != null && !this.listeners.contains(cVar)) {
            this.listeners.add(cVar);
        }
    }

    public void appBarStateChanged(int i2, int i7) {
        List<b> list = this.mAppBarStateChangedListener;
        if (list != null) {
            Iterator<b> it = list.iterator();
            while (it.hasNext()) {
                z zVar = (z) it.next();
                FloatingTopLayout$FloatingTopBehavior floatingTopLayout$FloatingTopBehavior = zVar.f1905a;
                C0266A a7 = zVar.b;
                j.e(a7, "$child");
                floatingTopLayout$FloatingTopBehavior.c(i2, i7, a7);
            }
        }
    }

    public final int c() {
        int windowHeight = getWindowHeight();
        float heightPercent = ((float) windowHeight) * getHeightPercent();
        if (heightPercent == 0.0f) {
            updateInternalCollapsedHeightOnce();
            heightPercent = seslGetCollapsedHeight();
        }
        StringBuilder sb2 = new StringBuilder("[calculateInternalHeight] orientation:");
        sb2.append(this.mResources.getConfiguration().orientation);
        sb2.append(", density:");
        N2.j.x(sb2, this.mResources.getConfiguration().densityDpi, ", windowHeight:", windowHeight, ", heightDp:");
        sb2.append(heightPercent);
        StringBuilder sb3 = new StringBuilder(sb2.toString());
        if (!this.mUseCustomHeight) {
            sb3.append(", [3]mHeightProportion : ");
            sb3.append(this.mHeightProportion);
            if (!(this.mHeightProportion == 0.0f || this.mProportionExtraHeight == 0)) {
                sb3.append(", [4]mProportionExtraHeight : ");
                sb3.append(this.mProportionExtraHeight);
                heightPercent += (float) this.mProportionExtraHeight;
            }
        } else if (this.mSetCustomProportion) {
            sb3.append(", [1]mCustomHeightProportion : ");
            sb3.append(this.mCustomHeightProportion);
        } else if (this.mSetCustomHeight) {
            heightPercent = (float) (getImmersiveTopInset() + this.mCustomHeight);
            sb3.append(", [2]CustomHeight : ");
            sb3.append(this.mCustomHeight);
        }
        Log.i(TAG, sb3.toString());
        return (int) heightPercent;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof d;
    }

    public final void d() {
        f fVar;
        Behavior behavior2 = this.behavior;
        if (behavior2 == null || this.totalScrollRange == -1 || this.pendingAction != 0) {
            fVar = null;
        } else {
            fVar = behavior2.s(AbsSavedState.EMPTY_STATE, this);
        }
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if (fVar != null) {
            Behavior behavior3 = this.behavior;
            if (behavior3.s == null) {
                behavior3.s = fVar;
            }
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean z;
        if (motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (this.liftOnScrollTargetView == null || !canScrollVertically(-1)) {
                z = false;
            } else {
                z = true;
            }
            handleAxisScroll(axisValue, z);
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.statusBarForeground != null && getTopInset() > 0) {
            int save = canvas.save();
            canvas.translate(0.0f, (float) (-this.currentOffset));
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarForeground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final void e(float f, boolean z) {
        this.totalScrollRange = -1;
        this.mUseCollapsedHeight = z;
        this.mCollapsedHeight = f;
    }

    public final void f(boolean z, boolean z3, boolean z7) {
        int i2;
        setLifted(!z);
        int i7 = 0;
        seslSetLiftHided(false);
        if (z) {
            this.pendingAction = 1;
        } else if (seslGetImmersiveScroll()) {
            this.pendingAction = 512;
        } else {
            this.pendingAction = 2;
        }
        int i8 = this.pendingAction;
        if (z3) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        if (z7) {
            i7 = 8;
        }
        this.pendingAction = i8 | i2 | i7;
        requestLayout();
    }

    public final void g(float f, float f5) {
        ValueAnimator valueAnimator = this.liftOnScrollColorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f5});
        this.liftOnScrollColorAnimator = ofFloat;
        ofFloat.setDuration(this.liftOnScrollColorDuration);
        this.liftOnScrollColorAnimator.setInterpolator(this.liftOnScrollColorInterpolator);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.liftOnScrollColorUpdateListener;
        if (animatorUpdateListener != null) {
            this.liftOnScrollColorAnimator.addUpdateListener(animatorUpdateListener);
        }
        this.liftOnScrollColorAnimator.start();
    }

    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        Behavior behavior2 = new Behavior();
        this.behavior = behavior2;
        return behavior2;
    }

    public boolean getCanImmScroll() {
        return this.mIsCanImmScroll;
    }

    public int getCurrentOrientation() {
        return this.mCurrentOrientation;
    }

    public int getDownNestedPreScrollRange() {
        int i2;
        int minimumHeight;
        int i7 = this.downPreScrollRange;
        if (i7 != -1) {
            return i7;
        }
        if (useCollapsedHeight()) {
            return (int) (seslGetCollapsedHeight() + ((float) (-getHeight())));
        }
        int childCount = getChildCount() - 1;
        int i8 = 0;
        while (true) {
            if (childCount < 0) {
                break;
            }
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i10 = dVar.f777a;
                if ((i10 & 5) == 5) {
                    int i11 = dVar.topMargin + dVar.bottomMargin;
                    if ((i10 & 8) != 0) {
                        minimumHeight = ViewCompat.getMinimumHeight(childAt);
                    } else if ((i10 & 2) != 0) {
                        minimumHeight = measuredHeight - ViewCompat.getMinimumHeight(childAt);
                    } else {
                        i2 = i11 + measuredHeight;
                        if (childCount == 0 && ViewCompat.getFitsSystemWindows(childAt)) {
                            i2 = Math.min(i2, measuredHeight - getTopInset());
                        }
                        i8 += i2;
                    }
                    i2 = minimumHeight + i11;
                    i2 = Math.min(i2, measuredHeight - getTopInset());
                    i8 += i2;
                } else if (getCanImmScroll()) {
                    i8 = (int) (seslGetCollapsedHeight() + ((float) seslGetAdditionalScrollRange()) + ((float) i8));
                }
            }
            childCount--;
        }
        int max = Math.max(0, i8);
        this.downPreScrollRange = max;
        return max;
    }

    public int getDownNestedScrollRange() {
        int minimumHeight;
        int i2;
        int i7 = this.downScrollRange;
        if (i7 != -1) {
            return i7;
        }
        int childCount = getChildCount();
        int i8 = 0;
        int i10 = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredHeight = dVar.topMargin + dVar.bottomMargin + childAt.getMeasuredHeight();
                int i11 = dVar.f777a;
                if ((i11 & 1) == 0) {
                    break;
                }
                i10 += measuredHeight;
                if ((i11 & 2) != 0) {
                    if (!this.mIsCanImmScroll || !(childAt instanceof CollapsingToolbarLayout)) {
                        minimumHeight = ViewCompat.getMinimumHeight(childAt);
                    } else {
                        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) childAt;
                        View view = collapsingToolbarLayout.f;
                        if (view != null) {
                            View view2 = collapsingToolbarLayout.g;
                            if (!(view2 == null || view2 == collapsingToolbarLayout)) {
                                view = view2;
                            }
                            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                                i2 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                                minimumHeight = ViewCompat.getMinimumHeight(collapsingToolbarLayout) - i2;
                            }
                        }
                        i2 = 0;
                        minimumHeight = ViewCompat.getMinimumHeight(collapsingToolbarLayout) - i2;
                    }
                    i10 -= minimumHeight;
                }
            }
            i8++;
        }
        int max = Math.max(0, i10);
        this.downScrollRange = max;
        return max;
    }

    public final int getImmersiveTopInset() {
        if (this.mIsCanImmScroll) {
            return this.mImmersiveTopInset;
        }
        return 0;
    }

    public boolean getIsMouse() {
        return this.isMouse;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    public C0340g getMaterialShapeBackground() {
        Drawable background = getBackground();
        if (background instanceof C0340g) {
            return (C0340g) background;
        }
        return null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight == 0) {
            int childCount = getChildCount();
            if (childCount >= 1) {
                minimumHeight = ViewCompat.getMinimumHeight(getChildAt(childCount - 1));
            } else {
                minimumHeight = 0;
            }
            if (minimumHeight == 0) {
                return getHeight() / 3;
            }
        }
        return (minimumHeight * 2) + topInset;
    }

    public int getPendingAction() {
        return this.pendingAction;
    }

    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.getSystemWindowInsetTop();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i2 = this.totalScrollRange;
        if (i2 != -1) {
            return i2;
        }
        int childCount = getChildCount();
        int i7 = 0;
        int i8 = 0;
        while (true) {
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i10 = dVar.f777a;
                if ((i10 & 1) == 0) {
                    break;
                }
                int i11 = measuredHeight + dVar.topMargin + dVar.bottomMargin + i8;
                if (i7 == 0 && ViewCompat.getFitsSystemWindows(childAt)) {
                    i11 -= getTopInset();
                }
                i8 = i11;
                if ((i10 & 2) != 0) {
                    if (getCanImmScroll()) {
                        i8 += seslGetAdditionalScrollRange() + getTopInset() + this.mBottomPadding;
                    } else {
                        i8 = c() - ((int) seslGetCollapsedHeight());
                    }
                }
            }
            i7++;
        }
        int max = Math.max(0, i8);
        this.totalScrollRange = max;
        return max;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public final void h() {
        int c5 = c();
        if (getHeight() != c5 && this.pendingAction == 256 && ((float) getTop()) == ((float) getHeight()) - seslGetCollapsedHeight()) {
            this.pendingAction = 10;
            requestLayout();
        }
        boolean z = this.mUseCustomHeight;
        if (!z || (z && (this.mSetCustomProportion || this.mSetCustomHeight))) {
            try {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) getLayoutParams();
                layoutParams.height = c5;
                setLayoutParams(layoutParams);
            } catch (ClassCastException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        if (this.mIsActivatedImmersiveScroll) {
            StringBuilder sb2 = new StringBuilder("[updateInternalHeight] mUseCustomHeight : " + this.mUseCustomHeight + ", mSetCustomProportion : " + this.mSetCustomProportion + ", mSetCustomHeight : " + this.mSetCustomHeight + ", mIsImmersiveScroll : " + this.mIsActivatedImmersiveScroll + ", mIsSetByUser : " + this.mIsActivatedByUser + ", mImmersiveTopInset : " + this.mImmersiveTopInset);
            WindowInsets rootWindowInsets = getRootView().getRootWindowInsets();
            if (rootWindowInsets != null) {
                sb2.append("\n");
                sb2.append(rootWindowInsets);
            }
            Log.i(TAG, sb2.toString());
        }
    }

    public void handleAxisScroll(float f, boolean z) {
        if (f < 0.0f) {
            if (useFloatingToolbar()) {
                seslSetHide();
            } else {
                setExpanded(false);
            }
        } else if (f > 0.0f && !z) {
            setExpanded(!seslIsHided());
        }
    }

    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    public boolean hasScrollableChildren() {
        if (getTotalScrollRange() != 0) {
            return true;
        }
        return false;
    }

    public void internalActivateImmersiveScroll(boolean z, boolean z3) {
        this.mIsActivatedImmersiveScroll = z;
        this.mIsActivatedByUser = z3;
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior == null) {
            return;
        }
        if (!z || immBehavior.B()) {
            immBehavior.F(this.mRestoreAnim);
        }
    }

    public void internalProportion(float f) {
        if (!this.mUseCustomHeight && this.mHeightProportion != f) {
            this.mHeightProportion = f;
            h();
        }
    }

    public boolean isDetachedState() {
        return this.mIsDetachedState;
    }

    public boolean isImmersiveActivatedByUser() {
        return this.mIsActivatedByUser;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    public boolean isLifted() {
        return this.lifted;
    }

    public boolean isRequestedForceExpanded() {
        return this.mRequestedForceExpanded;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsDetachedState = false;
        k.Q(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        int i2;
        Drawable drawable;
        super.onConfigurationChanged(configuration);
        Drawable drawable2 = this.mBackground;
        if (drawable2 != null) {
            if (drawable2 == getBackground()) {
                drawable = this.mBackground;
            } else {
                drawable = getBackground();
            }
            setBackgroundDrawable(drawable);
        } else if (getBackground() != null) {
            Drawable background = getBackground();
            this.mBackground = background;
            setBackgroundDrawable(background);
        } else {
            this.mBackground = null;
            Resources resources = this.mResources;
            if (SeslMisc.isLightTheme(getContext())) {
                i2 = R$color.sesl_action_bar_background_color_light;
            } else {
                i2 = R$color.sesl_action_bar_background_color_dark;
            }
            setBackgroundColor(resources.getColor(i2));
        }
        if (!(this.mCurrentScreenHeight == configuration.screenHeightDp && this.mCurrentOrientation == configuration.orientation)) {
            boolean z = this.mUseCustomPadding;
            if (!z && !this.mUseCollapsedHeight) {
                Log.i(TAG, "Update bottom padding");
                int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.sesl_extended_appbar_bottom_padding);
                this.mBottomPadding = dimensionPixelSize;
                setPadding(0, 0, 0, dimensionPixelSize);
                float dimensionPixelSize2 = (float) (this.mResources.getDimensionPixelSize(R$dimen.sesl_action_bar_height_with_padding) + this.mBottomPadding);
                this.mCollapsedHeight = dimensionPixelSize2;
                e(dimensionPixelSize2, false);
            } else if (z && this.mBottomPadding == 0 && !this.mUseCollapsedHeight) {
                float dimensionPixelSize3 = (float) this.mResources.getDimensionPixelSize(R$dimen.sesl_action_bar_height_with_padding);
                this.mCollapsedHeight = dimensionPixelSize3;
                e(dimensionPixelSize3, false);
            }
        }
        if (!this.mSetCustomProportion) {
            this.mHeightProportion = m.b(getContext());
        }
        h();
        String str = TAG;
        Log.i(str, "onConfigurationChanged : mCollapsedHeight = " + this.mCollapsedHeight + ", mHeightProportion = " + this.mHeightProportion + ", mHasSuggestion = " + this.mHasSuggestion + ", mUseCollapsedHeight = " + this.mUseCollapsedHeight);
        if (useFloatingToolbar()) {
            if (seslGetCurrentAppBarState() == 4) {
                seslSetHide(false);
            } else if (this.lifted) {
                f(false, false, true);
            } else {
                f(true, false, true);
            }
        } else if (this.lifted || (this.mCurrentOrientation == 1 && configuration.orientation == 2)) {
            f(false, false, true);
        } else {
            f(true, false, true);
        }
        int i7 = this.mCurrentOrientation;
        int i8 = configuration.orientation;
        if (i7 != i8) {
            this.mOrientationChanged = true;
        }
        this.mCurrentOrientation = i8;
        this.mCurrentScreenHeight = configuration.screenHeightDp;
    }

    public int[] onCreateDrawableState(int i2) {
        int i7;
        int i8;
        int i10;
        int i11;
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + iArr.length);
        boolean z = this.liftable;
        if (z) {
            i7 = R.attr.state_liftable;
        } else {
            i7 = -2130969843;
        }
        iArr[0] = i7;
        if (!z || !this.lifted) {
            i8 = -2130969844;
        } else {
            i8 = R.attr.state_lifted;
        }
        iArr[1] = i8;
        if (z) {
            i10 = R.attr.state_collapsible;
        } else {
            i10 = -2130969837;
        }
        iArr[2] = i10;
        if (!z || !this.lifted) {
            i11 = -2130969836;
        } else {
            i11 = R.attr.state_collapsed;
        }
        iArr[3] = i11;
        return View.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    public void onDetachedFromWindow() {
        this.mIsDetachedState = true;
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            if (this.mIsReservedImmersiveDetachOption && this.mReservedFitSystemWindow) {
                Log.i("SeslImmersiveScrollBehavior", "fits system window Immersive detached");
                Activity a7 = q.a(immBehavior.f1375K);
                if (!(a7 == null || immBehavior.f1372H == null)) {
                    a7.getWindow().setDecorFitsSystemWindows(true);
                    View view = immBehavior.Q;
                    if (view != null) {
                        view.setTranslationY(0.0f);
                    }
                }
                View view2 = immBehavior.f1377N;
                if (!(view2 == null || view2.getTranslationY() == 0.0f)) {
                    immBehavior.f1377N.setTranslationY(0.0f);
                }
            }
            Log.i("SeslImmersiveScrollBehavior", "DetachedFromWindow");
            o oVar = immBehavior.a0;
            if (oVar != null) {
                immBehavior.Z.removeOnControllableInsetsChangedListener(oVar);
                immBehavior.a0 = null;
            }
            immBehavior.Y = null;
            immBehavior.f1381X = null;
            immBehavior.f1382c0 = false;
        }
        super.onDetachedFromWindow();
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    public void onImmOffsetChanged(int i2) {
        if (!willNotDraw()) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        List<Object> list = this.mImmOffsetListener;
        if (list != null) {
            int size = list.size();
            int i7 = 0;
            while (i7 < size) {
                if (this.mImmOffsetListener.get(i7) == null) {
                    i7++;
                } else {
                    throw new ClassCastException();
                }
            }
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        boolean z3 = true;
        if (ViewCompat.getFitsSystemWindows(this) && getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() != 8 && !ViewCompat.getFitsSystemWindows(childAt)) {
                int topInset = getTopInset();
                for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                    ViewCompat.offsetTopAndBottom(getChildAt(childCount), topInset);
                }
            }
        }
        if (this.mIsFirstLayoutAppBar) {
            d();
            this.mIsFirstLayoutAppBar = false;
        }
        this.haveChildWithInterpolator = false;
        int childCount2 = getChildCount();
        int i11 = 0;
        while (true) {
            if (i11 >= childCount2) {
                break;
            } else if (((d) getChildAt(i11).getLayoutParams()).f778c != null) {
                this.haveChildWithInterpolator = true;
                break;
            } else {
                i11++;
            }
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (!this.liftableOverride) {
            if (!this.liftOnScroll) {
                int childCount3 = getChildCount();
                int i12 = 0;
                while (true) {
                    if (i12 >= childCount3) {
                        z3 = false;
                        break;
                    }
                    int i13 = ((d) getChildAt(i12).getLayoutParams()).f777a;
                    if ((i13 & 1) == 1 && (i13 & 10) != 0) {
                        break;
                    }
                    i12++;
                }
            }
            if (this.liftable != z3) {
                this.liftable = z3;
                refreshDrawableState();
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        h();
        if (getLayoutParams().height >= 0) {
            super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getLayoutParams().height, 1073741824));
            if (this.pendingAction == 256 && getHeight() > 0 && getHeight() != getLayoutParams().height) {
                if (((float) getTop()) == seslGetCollapsedHeight() + ((float) (-getHeight()))) {
                    Log.i(TAG, "Height changed and refresh collapsed offset");
                    this.pendingAction = 10;
                }
            }
        } else {
            super.onMeasure(i2, i7);
        }
        int mode = View.MeasureSpec.getMode(i7);
        if (mode != 1073741824 && ViewCompat.getFitsSystemWindows(this) && getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() != 8 && !ViewCompat.getFitsSystemWindows(childAt)) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = MathUtils.clamp(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i7));
                } else if (mode == 0) {
                    measuredHeight += getTopInset();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        d();
    }

    public void onOffsetChanged(int i2) {
        this.currentOffset = i2;
        int totalScrollRange2 = getTotalScrollRange();
        getHeight();
        seslGetCollapsedHeight();
        int i7 = 4;
        if (getCanImmScroll() && Math.abs(i2) >= totalScrollRange2) {
            f fVar = this.mAppbarState;
            if (fVar.f779a != 4) {
                fVar.f779a = 4;
            }
        }
        int i8 = this.mAppbarState.f779a;
        boolean z = true;
        if (i2 != (-getHeight())) {
            float f = (float) i2;
            if (f < seslGetCollapsedHeight() + ((float) (-getHeight()))) {
                i7 = 6;
            } else {
                if (f == seslGetCollapsedHeight() + ((float) (-getHeight()))) {
                    i7 = 2;
                } else if (i2 < 0) {
                    i7 = 3;
                } else if (i2 == 0) {
                    i7 = 1;
                } else {
                    i7 = 0;
                }
            }
        }
        if (i8 != i7) {
            if ((i7 & 1) != 0) {
                z = false;
            }
            setLifted(z);
            this.mAppbarState.f779a = i7;
            appBarStateChanged(i8, i7);
        }
        if (!willNotDraw()) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        List<c> list = this.listeners;
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                c cVar = this.listeners.get(i10);
                if (cVar != null) {
                    cVar.onOffsetChanged(this, i2);
                }
            }
        }
    }

    public WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2;
        boolean z;
        if (ViewCompat.getFitsSystemWindows(this)) {
            windowInsetsCompat2 = windowInsetsCompat;
        } else {
            windowInsetsCompat2 = null;
        }
        if (!ObjectsCompat.equals(this.lastInsets, windowInsetsCompat2)) {
            this.lastInsets = windowInsetsCompat2;
            if (this.statusBarForeground == null || getTopInset() <= 0) {
                z = false;
            } else {
                z = true;
            }
            setWillNotDraw(!z);
            requestLayout();
        }
        return windowInsetsCompat;
    }

    public void removeOnOffsetChangedListener(c cVar) {
        List<c> list = this.listeners;
        if (list != null && cVar != null) {
            list.remove(cVar);
        }
    }

    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    public void seslAddAppBarStateChangedListener(b bVar) {
        if (this.mAppBarStateChangedListener == null) {
            this.mAppBarStateChangedListener = new ArrayList();
        }
        if (!this.mAppBarStateChangedListener.contains(bVar)) {
            this.mAppBarStateChangedListener.add(bVar);
        }
    }

    public void seslAllowStartNestedScroll(boolean z) {
        this.mAllowStartNestedScroll = z;
    }

    public boolean seslCanChangeToHideState() {
        if (this.mAllowStateToHideRequested) {
            return this.mAllowStateToHideAppRequestValue;
        }
        if ((seslGetCurrentAppBarState() & 4) != 0) {
            return true;
        }
        return this.mAllowStateToHideInternal;
    }

    public int seslGetAdditionalScrollRange() {
        return this.mAdditionalScrollRange;
    }

    public f seslGetAppBarState() {
        return this.mAppbarState;
    }

    public float seslGetCollapsedHeight() {
        return this.mCollapsedHeight + ((float) getImmersiveTopInset());
    }

    public int seslGetCurrentAppBarState() {
        return this.mAppbarState.f779a;
    }

    public float seslGetDefaultCollapsedHeight() {
        return (float) (this.mResources.getDimensionPixelSize(R$dimen.sesl_action_bar_height_with_padding) + this.mBottomPadding);
    }

    public float seslGetHeightProPortion() {
        return this.mHeightProportion;
    }

    public boolean seslGetImmersiveScroll() {
        return seslIsActivatedImmsersiveScroll();
    }

    public int seslGetProportionExtraHeight() {
        return this.mProportionExtraHeight;
    }

    public final int seslGetTotalFullyScrollRange() {
        return c();
    }

    public void seslInternalSetAllowStateToHide(boolean z) {
        this.mAllowStateToHideInternal = z;
        if (!this.mAllowStateToHideRequested && !z && seslIsHided() && this.mOrientationChanged) {
            f(false, false, true);
        }
        this.mOrientationChanged = false;
    }

    public boolean seslIsActivatedImmsersiveScroll() {
        return this.mIsActivatedImmersiveScroll;
    }

    public boolean seslIsAllowStartNestedScroll() {
        return this.mAllowStartNestedScroll;
    }

    public boolean seslIsCollapsed() {
        if (this.lifted || seslGetCollapsedHeight() == ((float) getHeight()) || this.mHeightProportion == 0.0f) {
            return true;
        }
        return false;
    }

    public boolean seslIsHided() {
        if (useFloatingToolbar() && ((float) getBottom()) < seslGetCollapsedHeight()) {
            return true;
        }
        return false;
    }

    public void seslSetAllowStateToHide(boolean z) {
        this.mAllowStateToHideRequested = !z;
        this.mAllowStateToHideAppRequestValue = z;
    }

    public void seslSetCollapsedHeight(float f) {
        e(f, true);
    }

    public void seslSetCustomHeightProportion(boolean z, float f) {
        if (f > 1.0f) {
            Log.e(TAG, "Height proportion float range is 0..1");
            return;
        }
        String str = TAG;
        Log.i(str, "seslSetCustomHeightProportion: useCustomHeight = " + z + ", heightProportion = " + f);
        this.mUseCustomHeight = z;
        this.mSetCustomProportion = z;
        this.mSetCustomHeight = false;
        this.mCustomHeightProportion = f;
        h();
        requestLayout();
    }

    public void seslSetExpanded(boolean z) {
        setExpanded(z);
    }

    public void seslSetHide() {
        seslSetHide(ViewCompat.isLaidOut(this));
    }

    public void seslSetIsMouse(boolean z) {
        this.isMouse = z;
    }

    @Deprecated
    public void seslSetLiftHided(boolean z) {
        this.mIsLiftHided = z;
    }

    public void seslSetProportionExtraHeight(int i2) {
        if (this.mProportionExtraHeight != i2) {
            this.mProportionExtraHeight = i2;
            h();
            if (seslGetCurrentAppBarState() == 2) {
                this.pendingAction = 2;
            }
            requestLayout();
        }
    }

    public void seslSetSuggestion(boolean z) {
        this.mHasSuggestion = z;
    }

    public void seslStopForceExpanded() {
        if (this.mRequestedForceExpanded) {
            Log.i(TAG, "stop seslStartForceExpanded");
            this.mRequestedForceExpanded = false;
        }
    }

    public void setCanImmScroll(boolean z) {
        if (this.mIsCanImmScroll != z) {
            this.mIsCanImmScroll = z;
            d();
            requestLayout();
        }
    }

    public void setElevation(float f) {
        super.setElevation(f);
        k.O(this, f);
    }

    public void setExpanded(boolean z) {
        setExpanded(z, ViewCompat.isLaidOut(this));
    }

    public void setImmersiveTopInset(int i2) {
        this.mImmersiveTopInset = i2;
    }

    public void setLiftOnScroll(boolean z) {
        this.liftOnScroll = z;
    }

    public void setLiftOnScrollTargetView(View view) {
        this.liftOnScrollTargetViewId = -1;
        if (view == null) {
            WeakReference<View> weakReference = this.liftOnScrollTargetView;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.liftOnScrollTargetView = null;
            return;
        }
        this.liftOnScrollTargetView = new WeakReference<>(view);
    }

    public void setLiftOnScrollTargetViewId(int i2) {
        this.liftOnScrollTargetViewId = i2;
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    public void setLiftableOverrideEnabled(boolean z) {
        this.liftableOverride = z;
    }

    public boolean setLifted(boolean z) {
        return setLiftedState(z, true);
    }

    public boolean setLiftedState(boolean z) {
        return setLiftedState(z, !this.liftableOverride);
    }

    public void setOrientation(int i2) {
        if (i2 == 1) {
            super.setOrientation(i2);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2;
        boolean z;
        Drawable drawable3 = this.statusBarForeground;
        if (drawable3 != drawable) {
            Integer num = null;
            if (drawable3 != null) {
                drawable3.setCallback((Drawable.Callback) null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            } else {
                drawable2 = null;
            }
            this.statusBarForeground = drawable2;
            if (drawable2 instanceof C0340g) {
                num = Integer.valueOf(((C0340g) drawable2).w);
            } else {
                ColorStateList B = com.samsung.context.sdk.samsunganalytics.internal.sender.c.B(drawable2);
                if (B != null) {
                    num = Integer.valueOf(B.getDefaultColor());
                }
            }
            this.statusBarForegroundOriginalColor = num;
            Drawable drawable4 = this.statusBarForeground;
            boolean z3 = false;
            if (drawable4 != null) {
                if (drawable4.isStateful()) {
                    this.statusBarForeground.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.statusBarForeground, ViewCompat.getLayoutDirection(this));
                Drawable drawable5 = this.statusBarForeground;
                if (getVisibility() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                drawable5.setVisible(z, false);
                this.statusBarForeground.setCallback(this);
            }
            if (this.statusBarForeground != null && getTopInset() > 0) {
                z3 = true;
            }
            setWillNotDraw(!z3);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarForegroundColor(int i2) {
        setStatusBarForeground(new ColorDrawable(i2));
    }

    public void setStatusBarForegroundResource(int i2) {
        setStatusBarForeground(AppCompatResources.getDrawable(getContext(), i2));
    }

    @Deprecated
    public void setTargetElevation(float f) {
        y.a(this, f);
    }

    public void setUseFloatingToolbar(boolean z) {
        String str = TAG;
        Log.i(str, "setUseFloatingToolbar " + z);
        this.mUseFloatingToolbar = z;
    }

    public void setVisibility(int i2) {
        boolean z;
        super.setVisibility(i2);
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    public boolean shouldLift(View view) {
        int i2;
        View view2;
        View view3 = null;
        if (this.liftOnScrollTargetView == null && (i2 = this.liftOnScrollTargetViewId) != -1) {
            if (view != null) {
                view2 = view.findViewById(i2);
            } else {
                view2 = null;
            }
            if (view2 == null && (getParent() instanceof ViewGroup)) {
                view2 = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
            }
            if (view2 != null) {
                this.liftOnScrollTargetView = new WeakReference<>(view2);
            }
        }
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            view3 = weakReference.get();
        }
        if (view3 != null) {
            view = view3;
        }
        if (view == null) {
            return false;
        }
        if (view.canScrollVertically(-1) || view.getScrollY() > 0) {
            return true;
        }
        return false;
    }

    public void updateInternalCollapsedHeight() {
        if (useCollapsedHeight()) {
            return;
        }
        if (getImmBehavior() == null || !getCanImmScroll()) {
            float seslGetCollapsedHeight = seslGetCollapsedHeight();
            float height = (float) (getHeight() - getTotalScrollRange());
            if (height != seslGetCollapsedHeight && height > 0.0f) {
                String str = TAG;
                Log.i(str, "Internal collapsedHeight/ oldCollapsedHeight :" + seslGetCollapsedHeight + " newCollapsedHeight :" + height);
                e(height, false);
                h();
            }
        }
    }

    public void updateInternalCollapsedHeightOnce() {
        if (useCollapsedHeight()) {
            return;
        }
        if (getImmBehavior() == null || !getCanImmScroll()) {
            float seslGetCollapsedHeight = seslGetCollapsedHeight();
            String str = TAG;
            Log.i(str, "update InternalCollapsedHeight from updateInternalHeight() : " + seslGetCollapsedHeight);
            e(seslGetCollapsedHeight, false);
        }
    }

    public boolean useCollapsedHeight() {
        return this.mUseCollapsedHeight;
    }

    public boolean useFloatingToolbar() {
        return this.mUseFloatingToolbar;
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.statusBarForeground) {
            return true;
        }
        return false;
    }

    public void seslSetHide(boolean z) {
        if (seslCanChangeToHideState()) {
            seslSetHide(z, true);
        }
    }

    public void setExpanded(boolean z, boolean z3) {
        f(z, z3, true);
    }

    public boolean setLiftedState(boolean z, boolean z3) {
        float f;
        if (!z3 || this.lifted == z) {
            return false;
        }
        this.lifted = z;
        refreshDrawableState();
        if (!(getBackground() instanceof C0340g)) {
            return true;
        }
        float f5 = 0.0f;
        if (this.hasLiftOnScrollColor) {
            float f8 = z ? 0.0f : 1.0f;
            if (z) {
                f5 = 1.0f;
            }
            g(f8, f5);
            return true;
        } else if (!this.liftOnScroll) {
            return true;
        } else {
            if (z) {
                f = 0.0f;
            } else {
                f = this.appBarElevation;
            }
            if (z) {
                f5 = this.appBarElevation;
            }
            g(f, f5);
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [S1.d, android.widget.LinearLayout$LayoutParams] */
    public d generateDefaultLayoutParams() {
        ? layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.f777a = 1;
        return layoutParams;
    }

    public void removeOnOffsetChangedListener(e eVar) {
        removeOnOffsetChangedListener((c) eVar);
    }

    public void seslSetAllowStateToHide(boolean z, boolean z3) {
        this.mAllowStateToHideRequested = z3;
        this.mAllowStateToHideAppRequestValue = z;
    }

    public void seslSetHide(boolean z, boolean z3) {
        if (seslCanChangeToHideState() || z3) {
            setLifted(true);
            seslSetLiftHided(true);
            int i2 = 0;
            int i7 = (z ? 4 : 0) | 256;
            if (z3) {
                i2 = 8;
            }
            this.pendingAction = i7 | i2;
            requestLayout();
        }
    }

    public void addOnOffsetChangedListener(e eVar) {
        addOnOffsetChangedListener((c) eVar);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [S1.d, android.widget.LinearLayout$LayoutParams] */
    public d generateLayoutParams(AttributeSet attributeSet) {
        D0.e eVar;
        Context context = getContext();
        ? layoutParams = new LinearLayout.LayoutParams(context, attributeSet);
        layoutParams.f777a = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b);
        layoutParams.f777a = obtainStyledAttributes.getInt(1, 0);
        if (obtainStyledAttributes.getInt(0, 0) != 1) {
            eVar = null;
        } else {
            eVar = new D0.e(16);
        }
        layoutParams.b = eVar;
        if (obtainStyledAttributes.hasValue(2)) {
            layoutParams.f778c = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(2, 0));
        }
        obtainStyledAttributes.recycle();
        return layoutParams;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BaseBehavior<T extends AppBarLayout> extends S1.k {

        /* renamed from: A  reason: collision with root package name */
        public boolean f1345A = false;
        public boolean B;

        /* renamed from: C  reason: collision with root package name */
        public int f1346C = -1;
        public float D = 0.0f;
        public boolean E = false;

        /* renamed from: F  reason: collision with root package name */
        public boolean f1347F = false;

        /* renamed from: G  reason: collision with root package name */
        public boolean f1348G = false;

        /* renamed from: p  reason: collision with root package name */
        public int f1349p;
        public int q;
        public ValueAnimator r;
        public f s;
        public WeakReference t;
        public d u;
        public boolean v;
        public boolean w = false;

        /* renamed from: x  reason: collision with root package name */
        public boolean f1350x = false;
        public float y;
        public float z;

        public BaseBehavior() {
            this.f783i = -1;
            this.k = -1;
        }

        public static View h(BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).getBehavior() instanceof ScrollingViewBehavior) {
                    return childAt;
                }
            }
            return null;
        }

        public static int j(AppBarLayout appBarLayout, int i2) {
            int i7;
            if (appBarLayout.isLifted()) {
                i7 = appBarLayout.getPaddingBottom();
            } else {
                i7 = 0;
            }
            int i8 = i2 + i7;
            int childCount = appBarLayout.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = appBarLayout.getChildAt(i10);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                d dVar = (d) childAt.getLayoutParams();
                if ((dVar.f777a & 32) == 32) {
                    top -= dVar.topMargin;
                    bottom += dVar.bottomMargin;
                }
                if (appBarLayout.seslGetAdditionalScrollRange() != 0) {
                    bottom += appBarLayout.seslGetAdditionalScrollRange();
                }
                int i11 = -i8;
                if (top <= i11 && bottom >= i11) {
                    return i10;
                }
            }
            return -1;
        }

        public static int k(AppBarLayout appBarLayout) {
            Behavior behavior = (Behavior) ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
            if (!appBarLayout.getCanImmScroll() || !(behavior instanceof SeslImmersiveScrollBehavior)) {
                return 0;
            }
            return appBarLayout.seslGetAdditionalScrollRange() + ((int) appBarLayout.seslGetCollapsedHeight());
        }

        public static int l(AppBarLayout appBarLayout) {
            if (!appBarLayout.useFloatingToolbar()) {
                return (int) (appBarLayout.seslGetCollapsedHeight() + ((float) (-appBarLayout.getHeight())));
            } else if (!appBarLayout.seslCanChangeToHideState()) {
                return (int) (appBarLayout.seslGetCollapsedHeight() + ((float) (-appBarLayout.getHeight())));
            } else {
                return appBarLayout.getTopInset() + (-appBarLayout.getHeight());
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
            if (((float) r9) <= (r8.seslGetCollapsedHeight() + ((float) (-r8.getHeight())))) goto L_0x007d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static void v(androidx.coordinatorlayout.widget.CoordinatorLayout r7, com.google.android.material.appbar.AppBarLayout r8, int r9, int r10) {
            /*
                int r0 = java.lang.Math.abs(r9)
                int r1 = r8.getChildCount()
                r2 = 0
                r3 = r2
            L_0x000a:
                r4 = 0
                if (r3 >= r1) goto L_0x0021
                android.view.View r5 = r8.getChildAt(r3)
                int r6 = r5.getTop()
                if (r0 < r6) goto L_0x001e
                int r6 = r5.getBottom()
                if (r0 > r6) goto L_0x001e
                goto L_0x0022
            L_0x001e:
                int r3 = r3 + 1
                goto L_0x000a
            L_0x0021:
                r5 = r4
            L_0x0022:
                if (r5 == 0) goto L_0x007c
                android.view.ViewGroup$LayoutParams r0 = r5.getLayoutParams()
                S1.d r0 = (S1.d) r0
                int r0 = r0.f777a
                boolean r1 = r8.useFloatingToolbar()
                r3 = 1
                if (r1 == 0) goto L_0x0044
                float r9 = (float) r9
                int r10 = r8.getHeight()
                int r10 = -r10
                float r10 = (float) r10
                float r0 = r8.seslGetCollapsedHeight()
                float r0 = r0 + r10
                int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r9 > 0) goto L_0x007c
                goto L_0x007d
            L_0x0044:
                r1 = r0 & 1
                if (r1 == 0) goto L_0x007c
                int r1 = androidx.core.view.ViewCompat.getMinimumHeight(r5)
                if (r10 <= 0) goto L_0x0065
                r10 = r0 & 12
                if (r10 == 0) goto L_0x0065
                int r9 = -r9
                int r10 = r5.getBottom()
                int r10 = r10 - r1
                int r0 = r8.getTopInset()
                int r10 = r10 - r0
                int r0 = r8.getImmersiveTopInset()
                int r10 = r10 - r0
                if (r9 < r10) goto L_0x007c
                goto L_0x007d
            L_0x0065:
                r10 = r0 & 2
                if (r10 == 0) goto L_0x007c
                int r9 = -r9
                int r10 = r5.getBottom()
                int r10 = r10 - r1
                int r0 = r8.getTopInset()
                int r10 = r10 - r0
                int r0 = r8.getImmersiveTopInset()
                int r10 = r10 - r0
                if (r9 < r10) goto L_0x007c
                goto L_0x007d
            L_0x007c:
                r3 = r2
            L_0x007d:
                boolean r9 = r8.isLiftOnScroll()
                if (r9 == 0) goto L_0x00a3
                int r9 = r7.getChildCount()
                r10 = r2
            L_0x0088:
                if (r10 >= r9) goto L_0x009f
                android.view.View r0 = r7.getChildAt(r10)
                boolean r1 = r0 instanceof androidx.core.view.NestedScrollingChild
                if (r1 != 0) goto L_0x009e
                boolean r1 = r0 instanceof android.widget.AbsListView
                if (r1 != 0) goto L_0x009e
                boolean r1 = r0 instanceof android.widget.ScrollView
                if (r1 == 0) goto L_0x009b
                goto L_0x009e
            L_0x009b:
                int r10 = r10 + 1
                goto L_0x0088
            L_0x009e:
                r4 = r0
            L_0x009f:
                boolean r3 = r8.shouldLift(r4)
            L_0x00a3:
                boolean r9 = r8.setLiftedState(r3)
                if (r9 == 0) goto L_0x00f8
                java.util.List r7 = r7.getDependents(r8)
                int r9 = r7.size()
            L_0x00b1:
                if (r2 >= r9) goto L_0x00f8
                java.lang.Object r10 = r7.get(r2)
                android.view.View r10 = (android.view.View) r10
                android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r10 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r10
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r10 = r10.getBehavior()
                boolean r0 = r10 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r0 == 0) goto L_0x00f5
                com.google.android.material.appbar.AppBarLayout$ScrollingViewBehavior r10 = (com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior) r10
                int r7 = r10.f787i
                if (r7 == 0) goto L_0x00f8
                android.graphics.drawable.Drawable r7 = r8.getBackground()
                if (r7 == 0) goto L_0x00da
                android.graphics.drawable.Drawable r7 = r8.getBackground()
                r7.jumpToCurrentState()
            L_0x00da:
                android.graphics.drawable.Drawable r7 = r8.getForeground()
                if (r7 == 0) goto L_0x00e7
                android.graphics.drawable.Drawable r7 = r8.getForeground()
                r7.jumpToCurrentState()
            L_0x00e7:
                android.animation.StateListAnimator r7 = r8.getStateListAnimator()
                if (r7 == 0) goto L_0x00f8
                android.animation.StateListAnimator r7 = r8.getStateListAnimator()
                r7.jumpToCurrentState()
                return
            L_0x00f5:
                int r2 = r2 + 1
                goto L_0x00b1
            L_0x00f8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.v(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int):void");
        }

        public final int d() {
            return a() + this.f1349p;
        }

        public final int f(CoordinatorLayout coordinatorLayout, View view, int i2, int i7, int i8) {
            int i10;
            int i11;
            int i12;
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int d = d();
            int i13 = 0;
            if (d < i7 || d > i8) {
                this.f1349p = 0;
            } else {
                int clamp = MathUtils.clamp(i2, i7, i8);
                if (d != clamp) {
                    if (appBarLayout.hasChildWithInterpolator()) {
                        int abs = Math.abs(clamp);
                        int childCount = appBarLayout.getChildCount();
                        int i14 = 0;
                        while (true) {
                            if (i14 >= childCount) {
                                break;
                            }
                            View childAt = appBarLayout.getChildAt(i14);
                            d dVar = (d) childAt.getLayoutParams();
                            Interpolator interpolator = dVar.f778c;
                            if (abs < childAt.getTop() || abs > childAt.getBottom()) {
                                i14++;
                            } else if (interpolator != null) {
                                int i15 = dVar.f777a;
                                if ((i15 & 1) != 0) {
                                    i12 = childAt.getHeight() + dVar.topMargin + dVar.bottomMargin;
                                    if ((i15 & 2) != 0) {
                                        i12 -= ViewCompat.getMinimumHeight(childAt);
                                    }
                                } else {
                                    i12 = 0;
                                }
                                if (ViewCompat.getFitsSystemWindows(childAt)) {
                                    i12 -= appBarLayout.getTopInset();
                                }
                                if (i12 > 0) {
                                    float f = (float) i12;
                                    i10 = (childAt.getTop() + Math.round(interpolator.getInterpolation(((float) (abs - childAt.getTop())) / f) * f)) * Integer.signum(clamp);
                                }
                            }
                        }
                    }
                    i10 = clamp;
                    boolean c5 = c(i10);
                    int i16 = d - clamp;
                    this.f1349p = clamp - i10;
                    int i17 = 1;
                    if (c5) {
                        int i18 = 0;
                        while (i18 < appBarLayout.getChildCount()) {
                            d dVar2 = (d) appBarLayout.getChildAt(i18).getLayoutParams();
                            D0.e eVar = dVar2.b;
                            if (!(eVar == null || (dVar2.f777a & i17) == 0)) {
                                View childAt2 = appBarLayout.getChildAt(i18);
                                Rect rect = (Rect) eVar.f;
                                Rect rect2 = (Rect) eVar.e;
                                childAt2.getDrawingRect(rect2);
                                appBarLayout.offsetDescendantRectToMyCoords(childAt2, rect2);
                                rect2.offset(0, -appBarLayout.getTopInset());
                                float abs2 = ((float) rect2.top) - Math.abs((float) a());
                                if (abs2 <= 0.0f) {
                                    float clamp2 = 1.0f - MathUtils.clamp(Math.abs(abs2 / ((float) rect2.height())), 0.0f, 1.0f);
                                    float height = (-abs2) - ((((float) rect2.height()) * 0.3f) * (1.0f - (clamp2 * clamp2)));
                                    childAt2.setTranslationY(height);
                                    childAt2.getDrawingRect(rect);
                                    rect.offset(0, (int) (-height));
                                    if (height >= ((float) rect.height())) {
                                        childAt2.setVisibility(4);
                                    } else {
                                        childAt2.setVisibility(0);
                                    }
                                    ViewCompat.setClipBounds(childAt2, rect);
                                } else {
                                    ViewCompat.setClipBounds(childAt2, (Rect) null);
                                    childAt2.setTranslationY(0.0f);
                                    childAt2.setVisibility(0);
                                }
                            }
                            i18++;
                            i17 = 1;
                        }
                    }
                    if (!c5 && appBarLayout.hasChildWithInterpolator()) {
                        coordinatorLayout2.dispatchDependentViewsChanged(appBarLayout);
                    }
                    appBarLayout.onOffsetChanged(a());
                    if (clamp < d) {
                        i11 = -1;
                    } else {
                        i11 = 1;
                    }
                    v(coordinatorLayout2, appBarLayout, clamp, i11);
                    i13 = i16;
                }
            }
            if (!ViewCompat.hasAccessibilityDelegate(coordinatorLayout2)) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout2, new c(coordinatorLayout2, this, appBarLayout));
            }
            return i13;
        }

        public final void i(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2) {
            int i7;
            long j2;
            int i8 = 250;
            if (Math.abs(this.D) <= 0.0f || Math.abs(this.D) > 3000.0f) {
                i7 = 250;
            } else {
                i7 = (int) (((double) (3000.0f - Math.abs(this.D))) * 0.4d);
            }
            if (i7 <= 250) {
                i7 = 250;
            }
            boolean z3 = false;
            if (this.E) {
                this.E = false;
            } else {
                i8 = i7;
            }
            if (Math.abs(this.D) < 2000.0f || appBarLayout.isRequestedForceExpanded()) {
                int d = d();
                if (d == i2) {
                    ValueAnimator valueAnimator = this.r;
                    if (valueAnimator != null && valueAnimator.isRunning()) {
                        this.r.cancel();
                    }
                } else {
                    ValueAnimator valueAnimator2 = this.r;
                    if (valueAnimator2 == null) {
                        ValueAnimator valueAnimator3 = new ValueAnimator();
                        this.r = valueAnimator3;
                        valueAnimator3.setInterpolator(SeslAnimationUtils.SINE_OUT_80);
                        this.r.addUpdateListener(new a(coordinatorLayout, this, appBarLayout));
                        this.r.addListener(new b(this, appBarLayout));
                    } else {
                        valueAnimator2.cancel();
                    }
                    if (Math.abs(i2 - d) < 10) {
                        z3 = true;
                    }
                    ValueAnimator valueAnimator4 = this.r;
                    if (z3) {
                        j2 = 0;
                    } else {
                        j2 = (long) Math.min(i8, 450);
                    }
                    valueAnimator4.setDuration(j2);
                    this.r.setIntValues(new int[]{d, i2});
                    this.r.start();
                }
            }
            this.D = 0.0f;
        }

        public final boolean m(AppBarLayout appBarLayout) {
            if (this.B) {
                return false;
            }
            int j2 = j(appBarLayout, d());
            if (j2 < 0 || (((d) appBarLayout.getChildAt(j2).getLayoutParams()).f777a & 65536) != 65536) {
                return true;
            }
            return false;
        }

        /* renamed from: n */
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i7, int i8, int i10) {
            if (((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).height != -2) {
                return super.onMeasureChild(coordinatorLayout, appBarLayout, i2, i7, i8, i10);
            }
            coordinatorLayout.onMeasureChild(appBarLayout, i2, i7, View.MeasureSpec.makeMeasureSpec(0, 0), i10);
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x007b  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0081  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0084  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x009f  */
        /* renamed from: o */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNestedPreScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r14, com.google.android.material.appbar.AppBarLayout r15, android.view.View r16, int r17, int r18, int[] r19, int r20) {
            /*
                r13 = this;
                r3 = r18
                if (r3 == 0) goto L_0x00e3
                r0 = 0
                r1 = 0
                r6 = 1
                if (r3 >= 0) goto L_0x003a
                int r2 = r15.getTotalScrollRange()
                int r2 = -r2
                int r4 = r15.getDownNestedPreScrollRange()
                int r4 = r4 + r2
                r13.w = r6
                r13.f1350x = r1
                int r5 = r15.getBottom()
                double r7 = (double) r5
                int r5 = r15.getHeight()
                double r9 = (double) r5
                r11 = 4602858963157741732(0x3fe0a3d70a3d70a4, double:0.52)
                double r9 = r9 * r11
                int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r5 < 0) goto L_0x002d
                r13.E = r6
            L_0x002d:
                r5 = -30
                if (r3 >= r5) goto L_0x0035
                r13.w = r6
                goto L_0x00a2
            L_0x0035:
                r13.D = r0
                r13.w = r1
                goto L_0x00a2
            L_0x003a:
                boolean r2 = r15.useFloatingToolbar()
                if (r2 == 0) goto L_0x0058
                int r2 = r15.getHeight()
                int r2 = -r2
                boolean r4 = r15.seslCanChangeToHideState()
                if (r4 != 0) goto L_0x0063
                int r2 = r15.getHeight()
                int r2 = -r2
                float r2 = (float) r2
                float r4 = r15.seslGetCollapsedHeight()
            L_0x0055:
                float r4 = r4 + r2
                int r2 = (int) r4
                goto L_0x0063
            L_0x0058:
                int r2 = r15.getHeight()
                int r2 = -r2
                float r2 = (float) r2
                float r4 = r15.seslGetCollapsedHeight()
                goto L_0x0055
            L_0x0063:
                r13.w = r1
                r13.f1350x = r6
                int r4 = r15.getBottom()
                double r4 = (double) r4
                int r7 = r15.getHeight()
                double r7 = (double) r7
                r9 = 4601417811276983173(0x3fdb851eb851eb85, double:0.43)
                double r7 = r7 * r9
                int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r4 > 0) goto L_0x007d
                r13.E = r6
            L_0x007d:
                r4 = 30
                if (r3 <= r4) goto L_0x0084
                r13.f1350x = r6
                goto L_0x0088
            L_0x0084:
                r13.D = r0
                r13.f1350x = r1
            L_0x0088:
                boolean r0 = r13.f1347F
                if (r0 == 0) goto L_0x0094
                int r0 = r13.a()
                if (r0 != r2) goto L_0x0094
                r13.f1347F = r6
            L_0x0094:
                int r0 = r13.a()
                int r4 = r15.getHeight()
                int r4 = -r4
                if (r0 != r4) goto L_0x00a1
                r13.f1348G = r6
            L_0x00a1:
                r4 = r1
            L_0x00a2:
                S1.j r0 = r13.f
                if (r0 == 0) goto L_0x00ad
                android.widget.OverScroller r0 = r13.g
                if (r0 == 0) goto L_0x00ad
                r0.forceFinished(r6)
            L_0x00ad:
                if (r2 == r4) goto L_0x00e3
                boolean r0 = r15.useFloatingToolbar()
                if (r0 == 0) goto L_0x00d8
                if (r3 <= 0) goto L_0x00d8
                boolean r0 = r13.v
                if (r0 == 0) goto L_0x00d8
                int r0 = r15.getHeight()
                int r2 = -r0
                boolean r0 = r15.seslCanChangeToHideState()
                if (r0 != 0) goto L_0x00d2
                int r0 = r15.getHeight()
                int r0 = -r0
                float r0 = (float) r0
                float r2 = r15.seslGetCollapsedHeight()
                float r2 = r2 + r0
                int r2 = (int) r2
            L_0x00d2:
                r0 = r13
                r5 = r1
                r4 = r2
                r1 = r14
            L_0x00d6:
                r2 = r15
                goto L_0x00dd
            L_0x00d8:
                r0 = r13
                r1 = r14
                r5 = r4
                r4 = r2
                goto L_0x00d6
            L_0x00dd:
                int r14 = r0.e(r1, r2, r3, r4, r5)
                r19[r6] = r14
            L_0x00e3:
                boolean r14 = r15.isLiftOnScroll()
                if (r14 == 0) goto L_0x00f0
                boolean r14 = r15.shouldLift(r16)
                r15.setLiftedState(r14)
            L_0x00f0:
                r14 = r16
                r1 = r20
                r13.u(r3, r15, r14, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.onNestedPreScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.View, int, int, int[], int):void");
        }

        public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            boolean z3;
            int i7;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.onLayoutChild(coordinatorLayout, appBarLayout, i2);
            float f = 0.0f;
            this.D = 0.0f;
            int pendingAction = appBarLayout.getPendingAction();
            f fVar = this.s;
            if (fVar == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    if ((pendingAction & 4) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if ((pendingAction & 2) != 0) {
                        float k = (float) ((k(appBarLayout) + (-appBarLayout.getTotalScrollRange())) - appBarLayout.getImmersiveTopInset());
                        if (z3) {
                            i(coordinatorLayout, appBarLayout, (int) k);
                        } else {
                            g(coordinatorLayout, appBarLayout, (int) k);
                        }
                    } else if ((pendingAction & 512) != 0) {
                        float k10 = (float) (k(appBarLayout) + (-appBarLayout.getTotalScrollRange()));
                        if (!(coordinatorLayout.getContext().getResources().getConfiguration().orientation == 1 && appBarLayout.getImmersiveTopInset() == 0 && appBarLayout.seslGetHeightProPortion() == 0.0f)) {
                            f = k10;
                        }
                        if (z3) {
                            i(coordinatorLayout, appBarLayout, (int) f);
                        } else {
                            g(coordinatorLayout, appBarLayout, (int) f);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z3) {
                            i(coordinatorLayout, appBarLayout, 0);
                        } else {
                            g(coordinatorLayout, appBarLayout, 0);
                        }
                    } else if ((pendingAction & 256) != 0) {
                        if (z3) {
                            i(coordinatorLayout, appBarLayout, -appBarLayout.seslGetTotalFullyScrollRange());
                        } else {
                            g(coordinatorLayout, appBarLayout, -appBarLayout.seslGetTotalFullyScrollRange());
                        }
                    }
                }
            } else if (fVar.d) {
                g(coordinatorLayout, appBarLayout, -appBarLayout.getHeight());
            } else if (fVar.e) {
                if (fVar.k == appBarLayout.getMeasuredHeight()) {
                    g(coordinatorLayout, appBarLayout, this.s.f1394j);
                } else {
                    g(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
                }
            } else if (fVar.f) {
                g(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(fVar.g);
                if (childAt != null) {
                    int i8 = -childAt.getBottom();
                    if (this.s.f1393i) {
                        i7 = appBarLayout.getTopInset() + ViewCompat.getMinimumHeight(childAt) + i8;
                    } else {
                        i7 = Math.round(((float) childAt.getHeight()) * this.s.f1392h) + i8;
                    }
                    g(coordinatorLayout, appBarLayout, i7);
                } else {
                    Log.e(AppBarLayout.TAG, "Failed get firstVisible child skip the offset control");
                }
            }
            appBarLayout.resetPendingAction();
            this.s = null;
            if (appBarLayout.useFloatingToolbar()) {
                c(MathUtils.clamp(a(), -appBarLayout.getHeight(), 0));
            } else if (appBarLayout.useCollapsedHeight()) {
                c(MathUtils.clamp(a(), (int) (appBarLayout.seslGetCollapsedHeight() + ((float) (-appBarLayout.getHeight()))), 0));
            } else {
                c(MathUtils.clamp(a(), -appBarLayout.getTotalScrollRange(), 0));
            }
            v(coordinatorLayout, appBarLayout, a(), 0);
            appBarLayout.onOffsetChanged(a());
            if (!ViewCompat.hasAccessibilityDelegate(coordinatorLayout)) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout, new c(coordinatorLayout, this, appBarLayout));
            }
            return true;
        }

        public final boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View view, View view2, float f, float f5) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            this.D = f5;
            if (f != 0.0f) {
                return false;
            }
            if (f5 < -300.0f) {
                this.w = true;
                this.f1350x = false;
            } else if (f5 > 300.0f) {
                this.w = false;
                this.f1350x = true;
            } else {
                this.D = 0.0f;
                this.w = false;
                this.f1350x = false;
                return true;
            }
            return super.onNestedPreFling(coordinatorLayout, appBarLayout, view2, f, f5);
        }

        public final void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof f) {
                f fVar = (f) parcelable;
                this.s = fVar;
                super.onRestoreInstanceState(coordinatorLayout, appBarLayout, fVar.getSuperState());
                return;
            }
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
            this.s = null;
        }

        public final Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            Parcelable onSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, appBarLayout);
            f s5 = s(onSaveInstanceState, appBarLayout);
            if (s5 == null) {
                return onSaveInstanceState;
            }
            return s5;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
            if (r0 != 3) goto L_0x0089;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r6, android.view.View r7, android.view.MotionEvent r8) {
            /*
                r5 = this;
                com.google.android.material.appbar.AppBarLayout r7 = (com.google.android.material.appbar.AppBarLayout) r7
                int r0 = r5.f1346C
                if (r0 >= 0) goto L_0x0014
                android.content.Context r0 = r6.getContext()
                android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
                int r0 = r0.getScaledTouchSlop()
                r5.f1346C = r0
            L_0x0014:
                int r0 = r8.getAction()
                boolean r1 = r7.getIsMouse()
                r5.B = r1
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x007c
                if (r0 == r1) goto L_0x004b
                r3 = 2
                if (r0 == r3) goto L_0x002b
                r3 = 3
                if (r0 == r3) goto L_0x004b
                goto L_0x0089
            L_0x002b:
                r5.f1345A = r1
                float r0 = r8.getY()
                float r1 = r5.z
                float r1 = r0 - r1
                int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r2 == 0) goto L_0x003b
                r5.y = r1
            L_0x003b:
                float r1 = r5.y
                float r1 = java.lang.Math.abs(r1)
                int r2 = r5.f1346C
                float r2 = (float) r2
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 <= 0) goto L_0x0089
                r5.z = r0
                goto L_0x0089
            L_0x004b:
                float r0 = r5.y
                float r0 = java.lang.Math.abs(r0)
                r3 = 1101529088(0x41a80000, float:21.0)
                int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                r3 = 0
                if (r0 <= 0) goto L_0x006c
                float r0 = r5.y
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 >= 0) goto L_0x0063
                r5.f1350x = r1
                r5.w = r3
                goto L_0x0072
            L_0x0063:
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 <= 0) goto L_0x0072
                r5.f1350x = r3
                r5.w = r1
                goto L_0x0072
            L_0x006c:
                r5.f1350x = r3
                r5.w = r3
                r5.z = r2
            L_0x0072:
                boolean r0 = r5.f1345A
                if (r0 == 0) goto L_0x0089
                r5.f1345A = r3
                r5.t(r6, r7)
                goto L_0x0089
            L_0x007c:
                r5.f1345A = r1
                r8.getX()
                float r0 = r8.getY()
                r5.z = r0
                r5.y = r2
            L_0x0089:
                boolean r5 = super.onTouchEvent(r6, r7, r8)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
        }

        /* renamed from: p */
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
            int i12;
            AppBarLayout appBarLayout2;
            CoordinatorLayout coordinatorLayout2;
            BaseBehavior baseBehavior;
            if (!appBarLayout.useFloatingToolbar()) {
                baseBehavior = this;
                coordinatorLayout2 = coordinatorLayout;
                appBarLayout2 = appBarLayout;
                i12 = i10;
                if (baseBehavior.m(appBarLayout2)) {
                    if (i12 < 0) {
                        iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, -appBarLayout2.getDownNestedScrollRange(), 0);
                        baseBehavior.u(i12, appBarLayout2, view, i11);
                    } else {
                        ViewCompat.stopNestedScroll(view, 1);
                    }
                } else if (i12 < 0) {
                    iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, -appBarLayout2.getDownNestedScrollRange(), 0);
                    baseBehavior.u(i12, appBarLayout2, view, i11);
                }
            } else if (!this.f1347F || !m(appBarLayout)) {
                baseBehavior = this;
                coordinatorLayout2 = coordinatorLayout;
                appBarLayout2 = appBarLayout;
                i12 = i10;
                if (baseBehavior.m(appBarLayout2)) {
                    int seslGetCollapsedHeight = (-appBarLayout2.getHeight()) + ((int) appBarLayout2.seslGetCollapsedHeight());
                    if (i12 < 0 && appBarLayout2.getTop() >= seslGetCollapsedHeight) {
                        iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, seslGetCollapsedHeight, 0);
                    } else if (i12 < 0 && appBarLayout2.getTop() < seslGetCollapsedHeight) {
                        int i13 = seslGetCollapsedHeight;
                        int i14 = i13;
                        iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, -appBarLayout2.getHeight(), i13);
                        if (appBarLayout2.getTop() == i14) {
                            ViewCompat.stopNestedScroll(view, 1);
                        }
                    }
                } else if (i12 < 0) {
                    appBarLayout2.getHeight();
                    appBarLayout2.seslGetCollapsedHeight();
                    iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, -appBarLayout2.getHeight(), 0);
                }
            } else if (i10 >= 0 || this.f1348G || a() >= (-appBarLayout.getDownNestedScrollRange())) {
                baseBehavior = this;
                coordinatorLayout2 = coordinatorLayout;
                appBarLayout2 = appBarLayout;
                i12 = i10;
                if (i12 < 0) {
                    iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, -appBarLayout2.getDownNestedScrollRange(), 0);
                    baseBehavior.u(i12, appBarLayout2, view, i11);
                } else {
                    ViewCompat.stopNestedScroll(view, 1);
                }
            } else {
                baseBehavior = this;
                coordinatorLayout2 = coordinatorLayout;
                appBarLayout2 = appBarLayout;
                i12 = i10;
                iArr[1] = baseBehavior.e(coordinatorLayout2, appBarLayout2, i12, -appBarLayout.getHeight(), -appBarLayout.getDownNestedScrollRange());
                baseBehavior.u(i12, appBarLayout2, view, i11);
            }
            if (i12 == 0 && !ViewCompat.hasAccessibilityDelegate(coordinatorLayout2)) {
                ViewCompat.setAccessibilityDelegate(coordinatorLayout2, new c(coordinatorLayout2, baseBehavior, appBarLayout2));
            }
        }

        /* renamed from: q */
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i7) {
            boolean z3;
            if ((i2 & 2) == 0 || (!appBarLayout.isLiftOnScroll() && (!appBarLayout.hasScrollableChildren() || coordinatorLayout.getHeight() - view.getHeight() > appBarLayout.getHeight()))) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (!z3 && appBarLayout.useFloatingToolbar()) {
                z3 = true;
            }
            if (!appBarLayout.seslIsAllowStartNestedScroll()) {
                z3 = false;
            }
            if (z3 && this.r != null && !appBarLayout.isRequestedForceExpanded()) {
                this.r.cancel();
            }
            if (((float) appBarLayout.getBottom()) <= appBarLayout.seslGetCollapsedHeight()) {
                this.v = true;
                appBarLayout.setLifted(true);
                this.y = 0.0f;
                if (appBarLayout.getBottom() <= 0) {
                    appBarLayout.seslSetLiftHided(true);
                }
            } else {
                this.v = false;
                appBarLayout.setLifted(false);
                appBarLayout.seslSetLiftHided(false);
            }
            appBarLayout.updateInternalCollapsedHeight();
            this.t = null;
            this.q = i7;
            this.B = appBarLayout.getIsMouse();
            return z3;
        }

        /* renamed from: r */
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
            int i7;
            int i8 = this.n;
            if (i8 == 3 || i8 == 1 || (i7 = this.m) == 3 || i7 == 1) {
                t(coordinatorLayout, appBarLayout);
            }
            if (this.q == 0 || i2 == 1) {
                if (appBarLayout.isLiftOnScroll()) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
                }
                if (this.f1348G) {
                    this.f1348G = false;
                }
            }
            this.t = new WeakReference(view);
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [androidx.customview.view.AbsSavedState, com.google.android.material.appbar.f] */
        public final f s(Parcelable parcelable, AppBarLayout appBarLayout) {
            boolean z3;
            boolean z7;
            boolean z9;
            int i2;
            int a7 = a();
            int childCount = appBarLayout.getChildCount();
            boolean z10 = false;
            int i7 = 0;
            while (i7 < childCount) {
                View childAt = appBarLayout.getChildAt(i7);
                int bottom = childAt.getBottom() + a7;
                if (childAt.getTop() + a7 > 0 || bottom < 0) {
                    i7++;
                } else {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.EMPTY_STATE;
                    }
                    ? absSavedState = new AbsSavedState(parcelable);
                    if (a7 == 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    absSavedState.f = z3;
                    if (z3 || (i2 = -a7) < appBarLayout.getTotalScrollRange() || i2 >= appBarLayout.getHeight()) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    absSavedState.e = z7;
                    if (z7 || appBarLayout.getHeight() == 0 || (-a7) != appBarLayout.getHeight()) {
                        z9 = false;
                    } else {
                        z9 = true;
                    }
                    absSavedState.d = z9;
                    absSavedState.g = i7;
                    if (bottom == appBarLayout.getTopInset() + ViewCompat.getMinimumHeight(childAt)) {
                        z10 = true;
                    }
                    absSavedState.f1393i = z10;
                    absSavedState.f1392h = ((float) bottom) / ((float) childAt.getHeight());
                    absSavedState.f1394j = a7;
                    absSavedState.k = childAt.getMeasuredHeight();
                    return absSavedState;
                }
            }
            return null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d9, code lost:
            if (((float) r0) >= (((float) (r8 + r1)) * 0.52f)) goto L_0x00db;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00dd, code lost:
            r0 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00fa, code lost:
            if (((float) r0) < (((float) (r8 + r1)) * 0.43f)) goto L_0x00dd;
         */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00ff  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x010a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void t(androidx.coordinatorlayout.widget.CoordinatorLayout r12, com.google.android.material.appbar.AppBarLayout r13) {
            /*
                r11 = this;
                int r0 = r13.getTopInset()
                int r1 = r13.getPaddingTop()
                int r1 = r1 + r0
                int r0 = r11.d()
                int r0 = r0 - r1
                int r1 = j(r13, r0)
                r2 = 0
                r3 = 0
                r4 = r3
            L_0x0015:
                int r5 = r12.getChildCount()
                if (r4 >= r5) goto L_0x0036
                android.view.View r5 = r12.getChildAt(r4)
                android.view.ViewGroup$LayoutParams r6 = r5.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r6
                boolean r7 = r5 instanceof com.google.android.material.appbar.AppBarLayout
                if (r7 == 0) goto L_0x002a
                goto L_0x0033
            L_0x002a:
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r6 = r6.getBehavior()
                boolean r6 = r6 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r6 == 0) goto L_0x0033
                r2 = r5
            L_0x0033:
                int r4 = r4 + 1
                goto L_0x0015
            L_0x0036:
                r4 = 1
                if (r2 != 0) goto L_0x003d
                android.view.View r2 = r12.getChildAt(r4)
            L_0x003d:
                if (r1 < 0) goto L_0x018e
                android.view.View r5 = r13.getChildAt(r1)
                android.view.ViewGroup$LayoutParams r6 = r5.getLayoutParams()
                S1.d r6 = (S1.d) r6
                int r7 = r6.f777a
                r8 = r7 & 4096(0x1000, float:5.74E-42)
                r9 = 4096(0x1000, float:5.74E-42)
                if (r8 != r9) goto L_0x0054
                r11.f785o = r4
                return
            L_0x0054:
                r11.f785o = r3
                boolean r4 = r13.getCanImmScroll()
                if (r4 == 0) goto L_0x0061
                int r4 = r13.seslGetAdditionalScrollRange()
                goto L_0x0062
            L_0x0061:
                r4 = r3
            L_0x0062:
                int r8 = r11.a()
                float r8 = (float) r8
                int r9 = r13.getHeight()
                int r9 = -r9
                float r9 = (float) r9
                float r10 = r13.seslGetCollapsedHeight()
                float r10 = r10 + r9
                int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
                if (r8 <= 0) goto L_0x0139
                int r4 = r13.getHeight()
                int r4 = -r4
                float r8 = (float) r4
                float r9 = r13.seslGetCollapsedHeight()
                float r9 = r9 + r8
                int r8 = (int) r9
                if (r1 != 0) goto L_0x0097
                boolean r1 = androidx.core.view.ViewCompat.getFitsSystemWindows(r13)
                if (r1 == 0) goto L_0x0097
                boolean r1 = androidx.core.view.ViewCompat.getFitsSystemWindows(r5)
                if (r1 == 0) goto L_0x0097
                int r1 = r13.getTopInset()
                int r1 = 0 - r1
                goto L_0x0098
            L_0x0097:
                r1 = r3
            L_0x0098:
                r9 = r7 & 2
                r10 = 2
                if (r9 != r10) goto L_0x00b1
                boolean r5 = r13.getCanImmScroll()
                if (r5 == 0) goto L_0x00c0
                float r5 = (float) r8
                float r8 = r13.seslGetCollapsedHeight()
                int r9 = r13.getPaddingBottom()
                float r9 = (float) r9
                float r8 = r8 - r9
                float r8 = r8 + r5
                int r8 = (int) r8
                goto L_0x00c0
            L_0x00b1:
                r9 = r7 & 5
                r10 = 5
                if (r9 != r10) goto L_0x00c0
                int r5 = androidx.core.view.ViewCompat.getMinimumHeight(r5)
                int r5 = r5 + r8
                if (r0 >= r5) goto L_0x00bf
                r1 = r5
                goto L_0x00c0
            L_0x00bf:
                r8 = r5
            L_0x00c0:
                r5 = 32
                r7 = r7 & r5
                if (r7 != r5) goto L_0x00cb
                int r5 = r6.topMargin
                int r1 = r1 + r5
                int r5 = r6.bottomMargin
                int r8 = r8 - r5
            L_0x00cb:
                boolean r5 = r11.v
                if (r5 == 0) goto L_0x00df
                float r0 = (float) r0
                int r5 = r8 + r1
                float r5 = (float) r5
                r6 = 1057300152(0x3f051eb8, float:0.52)
                float r5 = r5 * r6
                int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r0 < 0) goto L_0x00dd
            L_0x00db:
                r0 = r1
                goto L_0x00fd
            L_0x00dd:
                r0 = r8
                goto L_0x00fd
            L_0x00df:
                boolean r5 = r13.useFloatingToolbar()
                r6 = 1054615798(0x3edc28f6, float:0.43)
                if (r5 == 0) goto L_0x00f3
                float r0 = (float) r0
                int r5 = r8 + r1
                float r5 = (float) r5
                float r5 = r5 * r6
                int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r0 >= 0) goto L_0x00db
                r0 = r4
                goto L_0x00fd
            L_0x00f3:
                float r0 = (float) r0
                int r5 = r8 + r1
                float r5 = (float) r5
                float r5 = r5 * r6
                int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r0 >= 0) goto L_0x00db
                goto L_0x00dd
            L_0x00fd:
                if (r2 != 0) goto L_0x010a
                java.lang.String r1 = com.google.android.material.appbar.AppBarLayout.TAG
                java.lang.String r2 = "coordinatorLayout.getChildAt(1) is null"
                android.util.Log.w(r1, r2)
            L_0x0108:
                r1 = r0
                goto L_0x012d
            L_0x010a:
                boolean r5 = r11.f1350x
                if (r5 == 0) goto L_0x011a
                boolean r0 = r13.seslCanChangeToHideState()
                if (r0 != 0) goto L_0x0115
                r4 = r8
            L_0x0115:
                r11.f1350x = r3
                r11.w = r3
                r0 = r4
            L_0x011a:
                boolean r4 = r11.w
                if (r4 == 0) goto L_0x0108
                int r2 = r2.getTop()
                float r2 = (float) r2
                float r4 = r13.seslGetCollapsedHeight()
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 <= 0) goto L_0x0108
                r11.w = r3
            L_0x012d:
                int r0 = l(r13)
                int r0 = androidx.core.math.MathUtils.clamp((int) r1, (int) r0, (int) r3)
                r11.i(r12, r13, r0)
                return
            L_0x0139:
                int r0 = r11.a()
                float r0 = (float) r0
                float r1 = r13.seslGetCollapsedHeight()
                float r1 = -r1
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 >= 0) goto L_0x0148
                goto L_0x018e
            L_0x0148:
                boolean r0 = r13.getCanImmScroll()
                if (r0 == 0) goto L_0x018e
                float r0 = r13.seslGetCollapsedHeight()
                int r0 = (int) r0
                int r1 = r13.getTotalScrollRange()
                int r0 = r0 - r1
                int r0 = r0 + r4
                int r1 = r13.getTotalScrollRange()
                int r1 = -r1
                int r2 = r13.getBottom()
                int r2 = r2 + r4
                double r4 = (double) r2
                float r2 = r13.seslGetCollapsedHeight()
                double r6 = (double) r2
                r8 = 4602318531202457272(0x3fdeb851eb851eb8, double:0.48)
                double r6 = r6 * r8
                int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r2 < 0) goto L_0x0175
                r2 = r0
                goto L_0x0176
            L_0x0175:
                r2 = r1
            L_0x0176:
                boolean r4 = r11.f1350x
                if (r4 == 0) goto L_0x017b
                goto L_0x017c
            L_0x017b:
                r1 = r2
            L_0x017c:
                boolean r2 = r11.w
                if (r2 == 0) goto L_0x0181
                goto L_0x0182
            L_0x0181:
                r0 = r1
            L_0x0182:
                int r1 = r13.getTotalScrollRange()
                int r1 = -r1
                int r0 = androidx.core.math.MathUtils.clamp((int) r0, (int) r1, (int) r3)
                r11.i(r12, r13, r0)
            L_0x018e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.t(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout):void");
        }

        public final void u(int i2, AppBarLayout appBarLayout, View view, int i7) {
            if (i7 == 1) {
                int d = d();
                if ((i2 < 0 && d == 0) || (i2 > 0 && d == (-appBarLayout.getDownNestedScrollRange()))) {
                    ViewCompat.stopNestedScroll(view, 1);
                }
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f783i = -1;
            this.k = -1;
        }
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [S1.d, android.widget.LinearLayout$LayoutParams] */
    /* JADX WARNING: type inference failed for: r1v4, types: [S1.d, android.widget.LinearLayout$LayoutParams] */
    /* JADX WARNING: type inference failed for: r1v5, types: [S1.d, android.widget.LinearLayout$LayoutParams] */
    public d generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ? layoutParams2 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams) layoutParams);
            layoutParams2.f777a = 1;
            return layoutParams2;
        } else if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ? layoutParams3 = new LinearLayout.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            layoutParams3.f777a = 1;
            return layoutParams3;
        } else {
            ? layoutParams4 = new LinearLayout.LayoutParams(layoutParams);
            layoutParams4.f777a = 1;
            return layoutParams4;
        }
    }
}
