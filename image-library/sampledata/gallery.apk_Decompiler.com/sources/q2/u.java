package q2;

import Ba.l;
import D5.d;
import S1.e;
import S1.v;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.oneui.common.BlurSupportable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.SeslScrollable;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingGroupLayout$FloatingActionBehavior;
import com.google.android.material.oneui.floatingactioncontainer.FloatingGroupLayout$getLifeCycleObserver$1;
import com.sec.android.gallery3d.R;
import h2.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.jvm.internal.j;
import m2.a;
import ne.C1195m;
import r2.C0269a;
import s2.C0275e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class u extends FrameLayout implements CoordinatorLayout.AttachedBehavior, BlurSupportable, a {

    /* renamed from: K  reason: collision with root package name */
    public static final /* synthetic */ int f1890K = 0;

    /* renamed from: A  reason: collision with root package name */
    public final c f1891A;
    public final Handler B;

    /* renamed from: C  reason: collision with root package name */
    public boolean f1892C;
    public C0268b D;
    public int E;

    /* renamed from: F  reason: collision with root package name */
    public int f1893F;

    /* renamed from: G  reason: collision with root package name */
    public Boolean f1894G;

    /* renamed from: H  reason: collision with root package name */
    public Boolean f1895H;

    /* renamed from: I  reason: collision with root package name */
    public final d f1896I;

    /* renamed from: J  reason: collision with root package name */
    public final r f1897J;
    public final AttributeSet d;
    public final ObjectAnimator e;
    public float f = 1.0f;
    public final ArrayList g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    public boolean f1898h;

    /* renamed from: i  reason: collision with root package name */
    public s f1899i;

    /* renamed from: j  reason: collision with root package name */
    public s f1900j;
    public boolean k = true;
    public final o l;
    public boolean m;
    public final LinkedHashMap n;

    /* renamed from: o  reason: collision with root package name */
    public WeakReference f1901o;

    /* renamed from: p  reason: collision with root package name */
    public WeakReference f1902p;
    public WeakReference q;
    public boolean r;
    public final boolean s;
    public int t;
    public boolean u;
    public final int v;
    public FloatingGroupLayout$getLifeCycleObserver$1 w;

    /* renamed from: x  reason: collision with root package name */
    public final Handler f1903x;
    public final c y;
    public final Handler z;

    public u(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.d = attributeSet;
        Context context2 = getContext();
        j.d(context2, "getContext()");
        o oVar = new o(context2);
        this.l = oVar;
        this.m = true;
        this.n = new LinkedHashMap();
        this.r = true;
        this.s = true;
        this.u = true;
        this.v = context.getResources().getDimensionPixelOffset(R.dimen.sesl_floating_layout_hide_start_scroll_range);
        this.f1903x = new Handler(Looper.getMainLooper());
        this.y = new c(this, 0);
        this.z = new Handler(Looper.getMainLooper());
        this.f1891A = new c(this, 1);
        this.B = new Handler(Looper.getMainLooper());
        this.f1893F = -1;
        this.f1896I = new d(0, this);
        p pVar = new p(this);
        v vVar = new v(2, this);
        p.a(context, attributeSet, 0, 0);
        int[] iArr = Q1.a.q;
        Context context3 = context;
        AttributeSet attributeSet2 = attributeSet;
        p.b(context3, attributeSet2, iArr, 0, 0, new int[0]);
        TypedArray obtainStyledAttributes = context3.obtainStyledAttributes(attributeSet2, iArr, 0, 0);
        j.d(obtainStyledAttributes, "obtainStyledAttributes(\n…tyleAttr, 0\n            )");
        if (obtainStyledAttributes.hasValue(2)) {
            this.u = obtainStyledAttributes.getBoolean(2, true);
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.r = obtainStyledAttributes.getBoolean(1, true);
        }
        if (obtainStyledAttributes.hasValue(0)) {
            this.s = obtainStyledAttributes.getBoolean(0, true);
        }
        if (this.s) {
            oVar.getPrjBgEndFirstView().b(context3);
            oVar.getPrjBgStartFirstView().b(context3);
            oVar.getPrjBgStartSecondView().b(context3);
        }
        obtainStyledAttributes.recycle();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, pVar, new float[]{getAlpha()});
        j.d(ofFloat, "ofFloat(this, mAlphaAnimProperty, alpha)");
        this.e = ofFloat;
        ofFloat.setDuration(150);
        ofFloat.addListener(vVar);
        ViewConfiguration.get(context3).getScaledTouchSlop();
        this.f1897J = new r(this);
    }

    private final SeslScrollable getScrollable() {
        WeakReference weakReference = this.q;
        if (weakReference != null) {
            return (SeslScrollable) weakReference.get();
        }
        return null;
    }

    private final SeslScrollable getScrollableView() {
        SeslScrollable recyclerView = getRecyclerView();
        if (recyclerView == null) {
            recyclerView = getNestedScrollView();
        }
        if (recyclerView == null) {
            return getScrollable();
        }
        return recyclerView;
    }

    public final void a(List list) {
        j.e(list, "views");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Rect rect = new Rect();
            this.n.put((View) it.next(), rect);
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
        o oVar = this.l;
        if (indexOfChild(oVar) != 0) {
            removeView(oVar);
            super.addView(oVar, 0, layoutParams2);
        }
        if (Build.VERSION.SDK_INT >= 35) {
            a(C1195m.q0(oVar.getPrjBgEndFirstView(), oVar.getPrjBgStartFirstView(), oVar.getPrjBgStartSecondView()));
        }
        super.addView(view, i2, layoutParams);
    }

    public final void b(Context context) {
        if (context instanceof LifecycleOwner) {
            FloatingGroupLayout$getLifeCycleObserver$1 floatingGroupLayout$getLifeCycleObserver$1 = this.w;
            if (floatingGroupLayout$getLifeCycleObserver$1 != null) {
                ((LifecycleOwner) context).getLifecycle().removeObserver(floatingGroupLayout$getLifeCycleObserver$1);
            }
            FloatingGroupLayout$getLifeCycleObserver$1 floatingGroupLayout$getLifeCycleObserver$12 = new FloatingGroupLayout$getLifeCycleObserver$1(this);
            this.w = floatingGroupLayout$getLifeCycleObserver$12;
            ((LifecycleOwner) context).getLifecycle().addObserver(floatingGroupLayout$getLifeCycleObserver$12);
        }
    }

    public void c() {
        if (this.f1893F != -1) {
            C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
            floatingScrollableManager$material_release.f1906h = this.f1893F;
            floatingScrollableManager$material_release.i(-1);
            floatingScrollableManager$material_release.k();
        }
        Boolean bool = this.f1894G;
        if (bool != null) {
            getFloatingScrollableManager$material_release().f = bool.booleanValue();
        }
        Boolean bool2 = this.f1895H;
        if (bool2 != null) {
            getFloatingScrollableManager$material_release().g = bool2.booleanValue();
        }
    }

    public final void clearBlurInfo(Context context) {
        j.e(context, "context");
        Set keySet = this.n.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object next : keySet) {
            if (next instanceof BlurSupportable) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((BlurSupportable) it.next()).clearBlurInfo(context);
        }
    }

    public final void d(int i2) {
        c cVar = this.y;
        Handler handler = this.f1903x;
        if (i2 > 0) {
            if (getAlpha() == 1.0f) {
                this.t += i2;
            }
            if (this.t > this.v) {
                o(false, false);
                handler.removeCallbacks(cVar);
                this.t = 0;
                return;
            }
            return;
        }
        handler.removeCallbacks(cVar);
        this.t = 0;
        if (getAlpha() != 1.0f) {
            o(true, false);
        }
    }

    public final void e() {
        RecyclerView recyclerView;
        NestedScrollView nestedScrollView;
        getFloatingScrollableManager$material_release().f(this.f1897J);
        WeakReference weakReference = this.f1901o;
        if (weakReference != null) {
            recyclerView = (RecyclerView) weakReference.get();
        } else {
            recyclerView = null;
        }
        s sVar = this.f1899i;
        if (!(sVar == null || recyclerView == null)) {
            recyclerView.removeOnAttachStateChangeListener(sVar);
        }
        this.f1899i = null;
        WeakHashMap weakHashMap = C0269a.q;
        Gd.a.i(this, recyclerView);
        WeakReference weakReference2 = this.f1901o;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.f1901o = null;
        WeakReference weakReference3 = this.f1902p;
        if (weakReference3 != null) {
            nestedScrollView = (NestedScrollView) weakReference3.get();
        } else {
            nestedScrollView = null;
        }
        s sVar2 = this.f1900j;
        if (!(sVar2 == null || nestedScrollView == null)) {
            nestedScrollView.removeOnAttachStateChangeListener(sVar2);
        }
        this.f1900j = null;
        Gd.a.i(this, nestedScrollView);
        WeakReference weakReference4 = this.f1902p;
        if (weakReference4 != null) {
            weakReference4.clear();
        }
        this.f1902p = null;
        Gd.a.i(this, getScrollable());
        WeakReference weakReference5 = this.q;
        if (weakReference5 != null) {
            weakReference5.clear();
        }
        this.q = null;
    }

    public final void f(boolean z3) {
        LogTagHelperKt.info(this, "FloatingLayout Transition enabled:" + z3 + " show:true");
        this.r = z3;
        if (!z3) {
            o(true, true);
        }
    }

    public final AppBarLayout g(List list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) list.get(i2);
            if (view instanceof AppBarLayout) {
                return (AppBarLayout) view;
            }
        }
        LogTagHelperKt.warn(this, "Can't find AppBarLayout " + list.size());
        return null;
    }

    public final AppBarLayout getAppBarLayout$material_release() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        ViewParent parent = getParent();
        j.c(parent, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        List<View> dependencies = ((CoordinatorLayout) parent).getDependencies(this);
        j.d(dependencies, "coordinatorLayout.getDependencies(this)");
        return g(dependencies);
    }

    public final AttributeSet getAttrs() {
        return this.d;
    }

    public CoordinatorLayout.Behavior<?> getBehavior() {
        Context context = getContext();
        j.d(context, "context");
        return new FloatingGroupLayout$FloatingActionBehavior(context, this.d);
    }

    public final C0268b getFloatingAware$material_release() {
        C0268b bVar = this.D;
        if (bVar == null) {
            return new g(this);
        }
        return bVar;
    }

    public final C0269a getFloatingScrollableManager$material_release() {
        WeakHashMap weakHashMap = C0269a.q;
        return Gd.a.z(this, getScrollableView(), (C0275e) null);
    }

    public String getLogTag() {
        return "FloatingGroupLayout";
    }

    public final Boolean getManageFadingEdgeBottomOffset() {
        return this.f1895H;
    }

    public final Boolean getManageGoToTopOffset() {
        return this.f1894G;
    }

    public final NestedScrollView getNestedScrollView() {
        WeakReference weakReference = this.f1902p;
        if (weakReference != null) {
            return (NestedScrollView) weakReference.get();
        }
        return null;
    }

    public SeslScrollable.SeslOnGoToTopClickListener getOnGoToTopClickListener$material_release() {
        return null;
    }

    public final ViewTreeObserver.OnPreDrawListener getOnPreDrawListener() {
        return this.f1896I;
    }

    public /* bridge */ /* synthetic */ String getPrefix() {
        return "";
    }

    public final o getProjectionView$material_release() {
        return this.l;
    }

    public final RecyclerView getRecyclerView() {
        WeakReference weakReference = this.f1901o;
        if (weakReference != null) {
            return (RecyclerView) weakReference.get();
        }
        return null;
    }

    public final boolean getShowBackgroundAtFirst$material_release() {
        return this.u;
    }

    public /* bridge */ /* synthetic */ String getVersion() {
        return "[sesl8-material:2.0.67]";
    }

    public final v getVisibleState() {
        if (this.e.isRunning()) {
            float f5 = this.f;
            if (f5 == 1.0f) {
                return v.STATE_ANIMATING_TO_SHOW;
            }
            if (f5 == 0.0f) {
                return v.STATE_ANIMATING_TO_HIDE;
            }
        } else if (getAlpha() == 1.0f) {
            return v.STATE_SHOW;
        } else {
            if (getAlpha() == 0.0f) {
                return v.STATE_HIDE;
            }
        }
        LogTagHelperKt.error(this, "Invalid State on getVisibleState from:" + getAlpha() + " to:" + this.f + " now:" + getAlpha());
        return v.STATE_SHOW;
    }

    public final int getWindowInsetBottom() {
        return this.f1893F;
    }

    public final boolean getWithAppBarLayout$material_release() {
        return this.k;
    }

    public final void h() {
        for (View invalidate : this.n.keySet()) {
            invalidate.invalidate();
        }
    }

    public final void i() {
        if (j()) {
            for (View invalidate : this.n.keySet()) {
                invalidate.invalidate();
            }
        }
    }

    public boolean j() {
        return true;
    }

    public abstract void k(AppBarLayout appBarLayout, int i2);

    public void l() {
        int i2;
        int i7;
        o oVar = this.l;
        float max = Math.max(oVar.getPrjBgEndFirstView().getElevation(), Math.max(oVar.getPrjBgStartFirstView().getElevation(), oVar.getPrjBgStartSecondView().getElevation()));
        if (max > 0.0f) {
            int i8 = (int) max;
            int paddingLeft = getPaddingLeft();
            if (getPaddingTop() == 0) {
                i2 = i8;
            } else {
                i2 = getPaddingTop();
            }
            int paddingRight = getPaddingRight();
            if (getPaddingBottom() == 0) {
                i7 = i8 * 2;
            } else {
                i7 = getPaddingBottom();
            }
            setPadding(paddingLeft, i2, paddingRight, i7);
        }
    }

    public final void m(boolean z3, boolean z7) {
        float f5;
        o oVar = this.l;
        oVar.f(z7);
        if (z3) {
            f5 = 1.0f;
        } else {
            f5 = 0.0f;
        }
        oVar.e(f5, !z7);
        if (z3) {
            h();
        }
    }

    public final void n(float f5, float f8) {
        k kVar;
        for (C0267a aVar : C0267a.values()) {
            o oVar = this.l;
            oVar.getClass();
            j.e(aVar, "type");
            int i2 = l.f1883a[aVar.ordinal()];
            if (i2 == 1) {
                kVar = oVar.f;
            } else if (i2 == 2) {
                kVar = oVar.g;
            } else if (i2 == 3) {
                kVar = oVar.e;
            } else {
                throw new RuntimeException();
            }
            j.e(kVar, "targetView");
            SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder());
            SpringForce springForce = new SpringForce();
            springForce.setDampingRatio(1.0f);
            springForce.setStiffness(361.0f);
            float f10 = (float) 10000;
            springForce.setFinalPosition(f8 * f10);
            springAnimation.setSpring(springForce);
            springAnimation.setStartValue(f10 * f5);
            springAnimation.addUpdateListener(new l(kVar, 2));
            springAnimation.addEndListener(new t(this));
            springAnimation.start();
        }
    }

    public final void o(boolean z3, boolean z7) {
        long j2;
        if (this.r) {
            float f5 = 0.0f;
            if (!z3 || getAlpha() == 0.0f) {
                if (z3) {
                    this.z.removeCallbacks(this.f1891A);
                }
                LogTagHelperKt.info(this, "StartViewAlphaAnimation show:" + z3 + " immediately:" + z7);
                if (z3) {
                    f5 = 1.0f;
                }
                if (f5 == 1.0f) {
                    n(0.94f, 1.0f);
                } else {
                    n(1.0f, 0.94f);
                }
                if (z7) {
                    j2 = 0;
                } else {
                    j2 = 150;
                }
                ObjectAnimator objectAnimator = this.e;
                objectAnimator.setDuration(j2);
                if (!objectAnimator.isRunning()) {
                    objectAnimator.setFloatValues(new float[]{getAlpha(), f5});
                    this.f = f5;
                    objectAnimator.start();
                } else if (this.f != f5) {
                    this.f = f5;
                    objectAnimator.end();
                    objectAnimator.setFloatValues(new float[]{getAlpha(), f5});
                    objectAnimator.start();
                }
            }
        }
    }

    public void onDetachedFromWindow() {
        FloatingGroupLayout$getLifeCycleObserver$1 floatingGroupLayout$getLifeCycleObserver$1;
        LogTagHelperKt.debug(this, "onDetachedFromWindow " + this);
        if ((getContext() instanceof LifecycleOwner) && (floatingGroupLayout$getLifeCycleObserver$1 = this.w) != null) {
            Context context = getContext();
            j.c(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
            ((LifecycleOwner) context).getLifecycle().removeObserver(floatingGroupLayout$getLifeCycleObserver$1);
        }
        this.w = null;
        super.onDetachedFromWindow();
        e();
        this.z.removeCallbacks(this.f1891A);
        this.l.f1886j.setEmpty();
        this.B.removeCallbacksAndMessages((Object) null);
        this.f1892C = false;
        getViewTreeObserver().removeOnPreDrawListener(this.f1896I);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (getVisibleState() != v.STATE_SHOW) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z3, int i2, int i7, int i8, int i10) {
        if (this.m) {
            AppBarLayout appBarLayout$material_release = getAppBarLayout$material_release();
            if (appBarLayout$material_release != null) {
                appBarLayout$material_release.addOnOffsetChangedListener((e) new d(1, this));
            }
            this.m = false;
        }
        if (!this.u || (getParent() instanceof CoordinatorLayout)) {
            o oVar = this.l;
            if (oVar.getParent() != null) {
                oVar.f(true);
            }
        } else {
            m(true, false);
        }
        super.onLayout(z3, i2, i7, i8, i10);
    }

    public void onMeasure(int i2, int i7) {
        if (getChildCount() > 1) {
            if (this.u) {
                l();
            }
            View childAt = getChildAt(1);
            measureChild(childAt, i2, i7);
            childAt.getMeasuredWidth();
            getPaddingStart();
            getPaddingEnd();
            int paddingBottom = getPaddingBottom() + getPaddingTop() + childAt.getMeasuredHeight();
            View.MeasureSpec.makeMeasureSpec(paddingBottom, 1073741824);
            setMeasuredDimension(i2, paddingBottom);
            this.l.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingStart()) - getPaddingEnd(), 1073741824), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
            return;
        }
        super.onMeasure(i2, i7);
    }

    public final void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        LogTagHelperKt.debug(this, "onWindowVisibilityChanged visibility=" + i2 + ' ' + this);
        d dVar = this.f1896I;
        if (i2 == 0) {
            getViewTreeObserver().addOnPreDrawListener(dVar);
            return;
        }
        this.B.removeCallbacksAndMessages((Object) null);
        this.f1892C = false;
        getViewTreeObserver().removeOnPreDrawListener(dVar);
    }

    public void setBlurMode(int i2) {
        Set keySet = this.n.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object next : keySet) {
            if (next instanceof BlurSupportable) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((BlurSupportable) it.next()).setBlurMode(i2);
        }
    }

    public final void setColorForFloatingBackground(int i2) {
        Drawable drawable;
        GradientDrawable gradientDrawable;
        Drawable drawable2;
        GradientDrawable gradientDrawable2;
        Object obj;
        o oVar = this.l;
        Drawable background = oVar.getPrjBgEndFirstView().getBackground();
        GradientDrawable gradientDrawable3 = null;
        if (background != null) {
            drawable = background.mutate();
        } else {
            drawable = null;
        }
        if (drawable instanceof GradientDrawable) {
            gradientDrawable = (GradientDrawable) drawable;
        } else {
            gradientDrawable = null;
        }
        if (gradientDrawable != null) {
            gradientDrawable.setColor(ColorStateList.valueOf(i2));
        }
        Drawable background2 = oVar.getPrjBgStartFirstView().getBackground();
        if (background2 != null) {
            drawable2 = background2.mutate();
        } else {
            drawable2 = null;
        }
        if (drawable2 instanceof GradientDrawable) {
            gradientDrawable2 = (GradientDrawable) drawable2;
        } else {
            gradientDrawable2 = null;
        }
        if (gradientDrawable2 != null) {
            gradientDrawable2.setColor(ColorStateList.valueOf(i2));
        }
        Drawable background3 = oVar.getPrjBgStartSecondView().getBackground();
        if (background3 != null) {
            obj = background3.mutate();
        } else {
            obj = null;
        }
        if (obj instanceof GradientDrawable) {
            gradientDrawable3 = (GradientDrawable) obj;
        }
        if (gradientDrawable3 != null) {
            gradientDrawable3.setColor(ColorStateList.valueOf(i2));
        }
    }

    public void setElevationForFloatingBackground(Float f5) {
        this.l.setElevation(f5);
    }

    public void setFloatingAware(C0268b bVar) {
        if (bVar == null) {
            bVar = new g((u) null);
        }
        this.D = bVar;
    }

    public final void setFloatingScrollableAdapter(C0275e eVar) {
        j.e(eVar, "floatingScrollableAdapter");
        e();
        if (eVar.g() == null) {
            LogTagHelperKt.warn(this, "setFloatingScrollableAdapter fail(getFloatingScrollable return null), scrollableAdapter=" + eVar);
            return;
        }
        WeakHashMap weakHashMap = C0269a.q;
        if (eVar.g() == null) {
            Log.w("FloatingScrollManager", "getInstance fail. using default (adapter scrollable is null), scrollableAdapter=" + eVar);
        } else {
            Gd.a.z(this, eVar.g(), eVar);
        }
        this.q = new WeakReference(eVar.g());
        getFloatingScrollableManager$material_release().e(this.f1897J);
        c();
    }

    public final void setManageFadingEdgeBottomOffset(Boolean bool) {
        this.f1895H = bool;
    }

    public final void setManageGoToTopOffset(Boolean bool) {
        this.f1894G = bool;
    }

    public void setNestedScrollView(NestedScrollView nestedScrollView) {
        j.e(nestedScrollView, "nestedScrollView");
        LogTagHelperKt.info(this, "setNestedScrollView isSame=" + nestedScrollView.equals(getNestedScrollView()) + ", nestedScrollView=" + nestedScrollView + '(' + nestedScrollView.hashCode() + ')');
        if (!nestedScrollView.equals(getNestedScrollView())) {
            e();
            this.f1902p = new WeakReference(nestedScrollView);
            getFloatingScrollableManager$material_release().j(nestedScrollView);
            getFloatingScrollableManager$material_release().e(this.f1897J);
            c();
            if (this.f1900j == null) {
                this.f1900j = new s(this, 0);
            }
            s sVar = this.f1900j;
            if (sVar != null) {
                nestedScrollView.addOnAttachStateChangeListener(sVar);
            }
            Context context = nestedScrollView.getContext();
            j.d(context, "nestedScrollView.context");
            b(context);
        }
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        j.e(recyclerView, "recyclerView");
        LogTagHelperKt.info(this, "setRecyclerView isSame=" + recyclerView.equals(getRecyclerView()) + ", recyclerView=" + recyclerView + '(' + recyclerView.hashCode() + ')');
        if (!recyclerView.equals(getRecyclerView())) {
            e();
            this.f1901o = new WeakReference(recyclerView);
            getFloatingScrollableManager$material_release().j(recyclerView);
            getFloatingScrollableManager$material_release().e(this.f1897J);
            c();
            if (this.f1899i == null) {
                this.f1899i = new s(this, 1);
            }
            s sVar = this.f1899i;
            if (sVar != null) {
                recyclerView.addOnAttachStateChangeListener(sVar);
            }
            Context context = recyclerView.getContext();
            j.d(context, "recyclerView.context");
            b(context);
        }
    }

    public final void setShowBackgroundAtFirst$material_release(boolean z3) {
        this.u = z3;
    }

    public final void setTintForFloatingBackground(int i2) {
        Drawable mutate;
        Drawable mutate2;
        Drawable mutate3;
        o oVar = this.l;
        Drawable background = oVar.getPrjBgEndFirstView().getBackground();
        if (!(background == null || (mutate3 = background.mutate()) == null)) {
            mutate3.setTint(i2);
        }
        Drawable background2 = oVar.getPrjBgStartFirstView().getBackground();
        if (!(background2 == null || (mutate2 = background2.mutate()) == null)) {
            mutate2.setTint(i2);
        }
        Drawable background3 = oVar.getPrjBgStartSecondView().getBackground();
        if (background3 != null && (mutate = background3.mutate()) != null) {
            mutate.setTint(i2);
        }
    }

    public final void setWindowBottomInset(int i2) {
        this.f1893F = i2;
        C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
        floatingScrollableManager$material_release.f1906h = i2;
        floatingScrollableManager$material_release.i(-1);
        floatingScrollableManager$material_release.k();
    }

    public final void setWindowInsetBottom(int i2) {
        this.f1893F = i2;
    }

    public final void setWithAppBarLayout$material_release(boolean z3) {
        this.k = z3;
    }

    public final void setLayoutAlphaAnimationListener$material_release(h hVar) {
    }
}
