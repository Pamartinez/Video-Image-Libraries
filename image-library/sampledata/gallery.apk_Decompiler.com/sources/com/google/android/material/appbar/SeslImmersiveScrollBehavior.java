package com.google.android.material.appbar;

import N2.j;
import S1.e;
import S1.h;
import S1.m;
import S1.n;
import S1.o;
import S1.p;
import S1.q;
import S1.r;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.util.SeslDisplayUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslImmersiveScrollBehavior extends AppBarLayout.Behavior {

    /* renamed from: H  reason: collision with root package name */
    public AppBarLayout f1372H;

    /* renamed from: I  reason: collision with root package name */
    public CoordinatorLayout f1373I;

    /* renamed from: J  reason: collision with root package name */
    public CollapsingToolbarLayout f1374J;

    /* renamed from: K  reason: collision with root package name */
    public Context f1375K;
    public View L;

    /* renamed from: M  reason: collision with root package name */
    public View f1376M;

    /* renamed from: N  reason: collision with root package name */
    public View f1377N;

    /* renamed from: O  reason: collision with root package name */
    public View f1378O;

    /* renamed from: P  reason: collision with root package name */
    public View f1379P;
    public View Q;
    public int R;
    public int S;
    public int T;
    public float U = 0.0f;
    public boolean V = true;

    /* renamed from: W  reason: collision with root package name */
    public boolean f1380W;

    /* renamed from: X  reason: collision with root package name */
    public CancellationSignal f1381X;
    public WindowInsetsAnimationController Y;
    public WindowInsetsController Z = null;
    public o a0 = null;
    public WindowInsets b0;

    /* renamed from: c0  reason: collision with root package name */
    public boolean f1382c0;
    public boolean d0;

    /* renamed from: e0  reason: collision with root package name */
    public boolean f1383e0 = true;

    /* renamed from: f0  reason: collision with root package name */
    public final boolean f1384f0 = true;

    /* renamed from: g0  reason: collision with root package name */
    public int f1385g0;
    public ValueAnimator h0;

    /* renamed from: i0  reason: collision with root package name */
    public int f1386i0;

    /* renamed from: j0  reason: collision with root package name */
    public boolean f1387j0 = false;
    public boolean k0 = false;
    public final n l0 = new n(this, Looper.getMainLooper(), 0);

    /* renamed from: m0  reason: collision with root package name */
    public final h f1388m0 = new h(this);

    /* renamed from: n0  reason: collision with root package name */
    public final q f1389n0 = new q(this);
    public final r o0 = new r(this);

    public SeslImmersiveScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1375K = context;
        J();
        H();
    }

    public static boolean C(WindowInsets windowInsets) {
        int statusBars = WindowInsets.Type.statusBars();
        if (windowInsets.getDisplayCutout() == null && windowInsets.getInsets(statusBars).top == 0) {
            return true;
        }
        return false;
    }

    public final void A(boolean z) {
        if (this.Z != null) {
            WindowInsets rootWindowInsets = this.L.getRootWindowInsets();
            this.b0 = rootWindowInsets;
            if (rootWindowInsets != null) {
                boolean isVisible = rootWindowInsets.isVisible(WindowInsets.Type.statusBars());
                boolean isVisible2 = this.b0.isVisible(WindowInsets.Type.navigationBars());
                if (!isVisible || !isVisible2 || B() || z) {
                    try {
                        this.Z.show(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                    } catch (IllegalStateException unused) {
                        Log.w("SeslImmersiveScrollBehavior", "forceRestoreWindowInset: mWindowInsetsController.show failed!");
                    }
                }
            }
        }
    }

    public final boolean B() {
        AppBarLayout appBarLayout = this.f1372H;
        if (appBarLayout == null) {
            return false;
        }
        if (((float) (this.f1372H.getPaddingBottom() + appBarLayout.getBottom())) < this.f1372H.seslGetCollapsedHeight()) {
            return true;
        }
        return false;
    }

    public final boolean D() {
        if (this.b0 == null) {
            if (this.L == null) {
                this.L = this.f1372H.getRootView();
            }
            this.b0 = this.L.getRootWindowInsets();
        }
        WindowInsets windowInsets = this.b0;
        if (windowInsets == null || windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom != 0) {
            return true;
        }
        return false;
    }

    public final void E(boolean z, boolean z3) {
        if (this.V != z) {
            this.V = z;
            A(z3);
            G(z);
            if (z != this.f1372H.getCanImmScroll()) {
                this.f1372H.setCanImmScroll(z);
            }
        }
    }

    public final void F(boolean z) {
        AppBarLayout appBarLayout;
        Log.i("SeslImmersiveScrollBehavior", " Restore top and bottom areas [Animate] " + z);
        this.f1383e0 = z;
        AppBarLayout appBarLayout2 = this.f1372H;
        n nVar = this.l0;
        if (appBarLayout2 != null && B()) {
            if (nVar.hasMessages(100)) {
                nVar.removeMessages(100);
            }
            nVar.sendEmptyMessageDelayed(100, 100);
        }
        if (this.Q != null && this.f1378O != null && !nVar.hasMessages(100) && (appBarLayout = this.f1372H) != null && !appBarLayout.seslIsActivatedImmsersiveScroll()) {
            this.Q.setTranslationY(0.0f);
        }
    }

    public final void G(boolean z) {
        AppBarLayout appBarLayout;
        AppBarLayout appBarLayout2;
        View view;
        int i2;
        AppBarLayout appBarLayout3;
        if (this.L != null && (appBarLayout = this.f1372H) != null) {
            if (this.f1375K == null) {
                Context context = appBarLayout.getContext();
                this.f1375K = context;
                if (context == null) {
                    return;
                }
            }
            Activity a7 = h2.q.a(this.f1375K);
            if (a7 == null && (appBarLayout3 = this.f1372H) != null) {
                this.f1375K = appBarLayout3.getContext();
                a7 = h2.q.a(this.f1372H.getContext());
            }
            if (a7 != null) {
                Window window = a7.getWindow();
                if (z) {
                    WindowInsets windowInsets = this.b0;
                    if (windowInsets == null || !C(windowInsets)) {
                        this.f1372H.setImmersiveTopInset(this.R);
                    } else {
                        this.f1372H.setImmersiveTopInset(0);
                    }
                    window.setDecorFitsSystemWindows(false);
                    window.getDecorView().setFitsSystemWindows(false);
                    WindowInsets windowInsets2 = this.b0;
                    if (windowInsets2 != null && (i2 = windowInsets2.getInsets(WindowInsets.Type.statusBars()).top) != 0 && i2 != this.R) {
                        this.R = i2;
                        this.f1372H.setImmersiveTopInset(i2);
                        return;
                    }
                    return;
                }
                this.f1372H.setImmersiveTopInset(0);
                window.setDecorFitsSystemWindows(true);
                window.getDecorView().setFitsSystemWindows(true);
                if (!D() && (appBarLayout2 = this.f1372H) != null && appBarLayout2.getCurrentOrientation() == 2) {
                    WindowInsetsController windowInsetsController = this.Z;
                    if (windowInsetsController == null && (view = this.L) != null && this.Y == null && windowInsetsController == null) {
                        this.Z = view.getWindowInsetsController();
                    }
                    WindowInsets rootWindowInsets = this.L.getRootWindowInsets();
                    this.b0 = rootWindowInsets;
                    if (this.Z != null && rootWindowInsets != null && rootWindowInsets.getInsets(WindowInsets.Type.statusBars()).top != 0) {
                        try {
                            this.Z.hide(WindowInsets.Type.statusBars());
                        } catch (IllegalStateException unused) {
                            Log.w("SeslImmersiveScrollBehavior", "setupDecorsFitSystemWindowState: mWindowInsetsController.hide failed!");
                        }
                    }
                }
            }
        }
    }

    public final void H() {
        AppBarLayout appBarLayout = this.f1372H;
        if (appBarLayout != null) {
            if (this.f1375K == null) {
                Context context = appBarLayout.getContext();
                this.f1375K = context;
                if (context == null) {
                    return;
                }
            }
            Resources resources = this.f1375K.getResources();
            float b = m.b(this.f1375K);
            float f = 0.0f;
            if (b != 0.0f) {
                f = (((float) this.R) / ((float) resources.getDisplayMetrics().heightPixels)) + b;
            }
            if (this.V) {
                this.f1372H.internalProportion(f);
            } else {
                this.f1372H.internalProportion(b);
            }
        }
    }

    public final boolean I() {
        AppBarLayout appBarLayout = this.f1372H;
        if (appBarLayout == null) {
            return false;
        }
        int currentOrientation = appBarLayout.getCurrentOrientation();
        if (this.f1386i0 != currentOrientation) {
            this.f1386i0 = currentOrientation;
            A(true);
            this.k0 = false;
        }
        if (currentOrientation == 1) {
            return true;
        }
        if (currentOrientation != 2) {
            Log.e("SeslImmersiveScrollBehavior", "ERROR, e : AppbarLayout Configuration is wrong");
        }
        return false;
    }

    public final void J() {
        Context context = this.f1375K;
        if (context != null) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                this.R = resources.getDimensionPixelSize(identifier);
            }
            int identifier2 = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (identifier2 > 0) {
                this.S = resources.getDimensionPixelSize(identifier2);
            }
            View view = this.L;
            if (view != null) {
                WindowInsets rootWindowInsets = view.getRootWindowInsets();
                this.b0 = rootWindowInsets;
                if (rootWindowInsets != null) {
                    this.R = rootWindowInsets.getInsets(WindowInsets.Type.statusBars()).top;
                    int i2 = this.b0.getInsets(WindowInsets.Type.navigationBars()).bottom;
                    this.T = i2;
                    this.S = i2;
                    if (this.f1378O == null) {
                        this.S = 0;
                    }
                }
            }
        }
    }

    public final void b(CoordinatorLayout coordinatorLayout, View view, int i2) {
        boolean z;
        AppBarLayout appBarLayout = (AppBarLayout) view;
        coordinatorLayout.onLayoutChild(appBarLayout, i2);
        WindowInsetsController windowInsetsController = this.Z;
        if (windowInsetsController != null && this.a0 == null) {
            o oVar = new o(this);
            this.a0 = oVar;
            windowInsetsController.addOnControllableInsetsChangedListener(oVar);
        }
        AppBarLayout appBarLayout2 = this.f1372H;
        if (appBarLayout2 == null || appBarLayout != appBarLayout2) {
            Log.d("SeslImmersiveScrollBehavior", "initImmViews mNeedInit=false");
            int i7 = 0;
            this.V = false;
            this.f1372H = appBarLayout;
            this.f1373I = coordinatorLayout;
            appBarLayout.addOnOffsetChangedListener((e) this.f1388m0);
            if (!this.f1372H.isImmersiveActivatedByUser()) {
                Context context = this.f1375K;
                if (context == null) {
                    z = false;
                } else {
                    z = SeslDisplayUtils.isDexEnabled(context);
                }
                if (!z) {
                    this.f1372H.internalActivateImmersiveScroll(true, false);
                }
            }
            View rootView = this.f1372H.getRootView();
            this.L = rootView;
            View findViewById = rootView.findViewById(16908290);
            this.f1376M = findViewById;
            findViewById.setWindowInsetsAnimationCallback(this.o0);
            z();
            y();
            while (true) {
                if (i7 >= appBarLayout.getChildCount()) {
                    break;
                }
                View childAt = appBarLayout.getChildAt(i7);
                if (this.f1374J != null) {
                    break;
                } else if (childAt instanceof CollapsingToolbarLayout) {
                    this.f1374J = (CollapsingToolbarLayout) childAt;
                    break;
                } else {
                    i7++;
                }
            }
            View findViewById2 = coordinatorLayout.findViewById(R.id.bottom_bar_overlay);
            if (this.Q == null || findViewById2 != null) {
                this.Q = findViewById2;
            }
        }
    }

    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (motionEvent.getToolType(0) == 3) {
            z = true;
        }
        if (this.f1380W != z) {
            this.f1380W = z;
            AppBarLayout appBarLayout = this.f1372H;
            if (appBarLayout != null) {
                appBarLayout.seslSetIsMouse(z);
                y();
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    /* renamed from: n */
    public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i2, int i7, int i8, int i10) {
        y();
        return super.onMeasureChild(coordinatorLayout, appBarLayout, i2, i7, i8, i10);
    }

    /* renamed from: o */
    public final void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i7, int[] iArr, int i8) {
        this.f1379P = view;
        if (this.f1381X != null) {
            iArr[0] = i2;
            iArr[1] = i7;
            return;
        }
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i2, i7, iArr, i8);
    }

    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        AppBarLayout appBarLayout = (AppBarLayout) view;
        boolean z = false;
        int toolType = motionEvent.getToolType(0);
        if (toolType == 0) {
            return super.onInterceptTouchEvent(coordinatorLayout, appBarLayout, motionEvent);
        }
        if (toolType == 3) {
            z = true;
        }
        if (this.f1380W != z) {
            this.f1380W = z;
            appBarLayout.seslSetIsMouse(z);
        }
        return super.onInterceptTouchEvent(coordinatorLayout, appBarLayout, motionEvent);
    }

    /* renamed from: p */
    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        this.f1379P = view;
        super.onNestedScroll(coordinatorLayout, appBarLayout, view, i2, i7, i8, i10, i11, iArr);
    }

    /* renamed from: q */
    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i2, int i7) {
        WindowInsetsAnimationController windowInsetsAnimationController;
        this.f1379P = view2;
        if (y() && (windowInsetsAnimationController = this.Y) == null) {
            View view3 = this.L;
            if (view3 != null && windowInsetsAnimationController == null && this.Z == null) {
                this.Z = view3.getWindowInsetsController();
            }
            if (this.f1381X == null) {
                this.f1381X = new CancellationSignal();
            }
            int statusBars = WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars();
            if (!C(this.b0)) {
                try {
                    this.Z.hide(statusBars);
                } catch (IllegalStateException unused) {
                    Log.w("SeslImmersiveScrollBehavior", "startAnimationControlRequest: mWindowInsetsController.hide failed!");
                }
            }
            this.Z.setSystemBarsBehavior(2);
            this.Z.controlWindowInsetsAnimation(statusBars, -1, (Interpolator) null, this.f1381X, this.f1389n0);
        }
        return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i2, i7);
    }

    /* renamed from: r */
    public final void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i2) {
        this.f1379P = view;
        super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i2);
    }

    public final boolean w() {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        AppBarLayout appBarLayout;
        WindowInsets windowInsets;
        if (this.f1372H != null) {
            Context context = this.f1375K;
            if (context == null) {
                z = false;
            } else {
                z = SeslDisplayUtils.isDexEnabled(context);
            }
            if (!z) {
                if (this.f1372H.getIsMouse()) {
                    E(false, false);
                    return false;
                }
                Context context2 = this.f1375K;
                if (context2 == null) {
                    z3 = false;
                } else {
                    z3 = ((AccessibilityManager) context2.getSystemService("accessibility")).isTouchExplorationEnabled();
                }
                if (z3) {
                    Log.i("SeslImmersiveScrollBehavior", "Disable ImmersiveScroll due to accessibility enabled");
                    I();
                    E(false, true);
                    return false;
                } else if (this.f1372H.seslIsActivatedImmsersiveScroll()) {
                    E(true, false);
                    try {
                        z7 = this.f1375K.getApplicationContext().getResources().getBoolean(Resources.getSystem().getIdentifier("config_navBarCanMove", "bool", "android"));
                    } catch (Exception e) {
                        j.D(e, new StringBuilder("ERROR, e : "), "SeslImmersiveScrollBehavior");
                        z7 = true;
                    }
                    if (!z7 || I() || ((windowInsets = this.b0) != null && windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom > 0)) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    Context context3 = this.f1375K;
                    if (context3 != null) {
                        Activity a7 = h2.q.a(context3);
                        if (a7 == null && (appBarLayout = this.f1372H) != null) {
                            this.f1375K = appBarLayout.getContext();
                            a7 = h2.q.a(this.f1372H.getContext());
                        }
                        if (a7 != null) {
                            boolean isInMultiWindowMode = a7.isInMultiWindowMode();
                            if (this.d0 != isInMultiWindowMode) {
                                A(true);
                                x();
                            }
                            this.d0 = isInMultiWindowMode;
                            if (isInMultiWindowMode) {
                                return false;
                            }
                        }
                    }
                    return z9;
                } else {
                    AppBarLayout appBarLayout2 = this.f1372H;
                    if (appBarLayout2 != null && appBarLayout2.isImmersiveActivatedByUser()) {
                        x();
                    }
                    E(false, false);
                }
            }
        }
        return false;
    }

    public final void x() {
        boolean z;
        View view = this.L;
        if (view != null) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            this.b0 = rootWindowInsets;
            if (rootWindowInsets != null) {
                boolean isVisible = rootWindowInsets.isVisible(WindowInsets.Type.statusBars());
                boolean isVisible2 = this.b0.isVisible(WindowInsets.Type.navigationBars());
                if (isVisible || isVisible2) {
                    z = true;
                } else {
                    z = false;
                }
                this.f1382c0 = z;
            }
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.Y;
        if (windowInsetsAnimationController != null) {
            windowInsetsAnimationController.finish(this.f1382c0);
        }
        CancellationSignal cancellationSignal = this.f1381X;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        this.Y = null;
        this.f1381X = null;
        this.f1382c0 = false;
    }

    public final boolean y() {
        AppBarLayout appBarLayout = this.f1372H;
        if (appBarLayout == null || appBarLayout.isDetachedState()) {
            return false;
        }
        boolean w = w();
        G(w);
        H();
        J();
        return w;
    }

    public final void z() {
        View view = this.L;
        if (view != null && this.f1375K != null) {
            this.b0 = view.getRootWindowInsets();
            this.L.getViewTreeObserver().addOnPreDrawListener(new p(0, this));
            J();
        }
    }
}
