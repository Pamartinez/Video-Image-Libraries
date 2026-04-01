package com.google.android.material.oneui.floatingactioncontainer;

import Q1.a;
import S1.v;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import androidx.core.view.ViewGroupKt;
import androidx.core.widget.SeslScrollable;
import c0.C0086a;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.samsung.android.sdk.cover.ScoverState;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1195m;
import q2.C0266A;
import q2.C0267a;
import q2.C0268b;
import q2.o;
import q2.p;
import q2.u;
import q2.w;
import q2.y;
import r2.C0269a;
import s2.C0275e;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001:\u0002#$J\u0013\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\r\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001d\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001e\u0010\u001cR\u0014\u0010\"\u001a\u00020\u001f8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/google/android/material/oneui/floatingactioncontainer/FloatingToolbarLayout;", "Lq2/A;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "getBehavior", "()Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "Lq2/b;", "floatingAware", "Lme/x;", "setFloatingAware", "(Lq2/b;)V", "Landroidx/appcompat/widget/Toolbar;", "getToolbar$material_release", "()Landroidx/appcompat/widget/Toolbar;", "getToolbar", "", "", "getToolbarChildPosition", "()Ljava/util/List;", "Landroidx/appcompat/widget/ActionBarContextView;", "getActionModeBarView", "()Landroidx/appcompat/widget/ActionBarContextView;", "Lcom/google/android/material/appbar/AppBarLayout;", "appbarLayout", "setTitleAlphaByCollapsingToolbarLayoutPolicy", "(Lcom/google/android/material/appbar/AppBarLayout;)V", "", "alpha", "setAlphaForToolbarTitleViGroup", "(F)V", "setAlphaForToolbar", "setAlphaForTitleViCustomView", "", "getLogTag", "()Ljava/lang/String;", "logTag", "q2/y", "FloatingToolbarBehavior", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingToolbarLayout extends C0266A {

    /* renamed from: f0  reason: collision with root package name */
    public static final /* synthetic */ int f1480f0 = 0;

    /* renamed from: M  reason: collision with root package name */
    public Toolbar f1481M;

    /* renamed from: N  reason: collision with root package name */
    public final ViewStubCompat f1482N;

    /* renamed from: O  reason: collision with root package name */
    public ActionBarContextView f1483O;

    /* renamed from: P  reason: collision with root package name */
    public final ArrayList f1484P = new ArrayList();
    public final boolean Q;
    public final boolean R;
    public final boolean S;
    public final boolean T;
    public boolean U;
    public int V;

    /* renamed from: W  reason: collision with root package name */
    public int f1485W;
    public boolean a0 = true;
    public ArrayList b0;

    /* renamed from: c0  reason: collision with root package name */
    public int f1486c0;
    public ObjectAnimator d0;

    /* renamed from: e0  reason: collision with root package name */
    public final p f1487e0;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/google/android/material/oneui/floatingactioncontainer/FloatingToolbarLayout$FloatingToolbarBehavior;", "Lcom/google/android/material/oneui/floatingactioncontainer/FloatingToolbarLayout;", "T", "Lcom/google/android/material/oneui/floatingactioncontainer/FloatingTopLayout$FloatingTopBehavior;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FloatingToolbarBehavior<T extends FloatingToolbarLayout> extends FloatingTopLayout$FloatingTopBehavior<T> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatingToolbarBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            j.e(context, "context");
        }

        public final /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, u uVar, int i2) {
            d(coordinatorLayout, (FloatingToolbarLayout) uVar, i2);
            return false;
        }

        public final void c(int i2, int i7, C0266A a7) {
            FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) a7;
            j.e(floatingToolbarLayout, "child");
            super.c(i2, i7, floatingToolbarLayout);
            boolean z = true;
            if (i7 == 4) {
                floatingToolbarLayout.t(false, true);
            } else if ((i7 & 4) != 0) {
                if ((i2 & 2) != 0) {
                    floatingToolbarLayout.t(false, false);
                }
            } else if (i7 == 2) {
                floatingToolbarLayout.t(true, false);
            }
            Toolbar toolbar$material_release = floatingToolbarLayout.getToolbar$material_release();
            if (toolbar$material_release != null) {
                if ((i7 & 4) != 0) {
                    z = false;
                }
                toolbar$material_release.seslSetEatingTouchOnly(z);
            }
        }

        /* renamed from: e */
        public final void d(CoordinatorLayout coordinatorLayout, FloatingToolbarLayout floatingToolbarLayout, int i2) {
            AppBarLayout appBarLayout$material_release;
            j.e(coordinatorLayout, "parent");
            j.e(floatingToolbarLayout, "child");
            if (this.e && (appBarLayout$material_release = floatingToolbarLayout.getAppBarLayout$material_release()) != null) {
                floatingToolbarLayout.u(appBarLayout$material_release, true);
            }
            f(coordinatorLayout, floatingToolbarLayout);
            super.d(coordinatorLayout, floatingToolbarLayout, i2);
        }

        public final void f(CoordinatorLayout coordinatorLayout, FloatingToolbarLayout floatingToolbarLayout) {
            int i2;
            int i7;
            int i8;
            List<View> dependencies = coordinatorLayout.getDependencies(floatingToolbarLayout);
            j.d(dependencies, "parent.getDependencies(child)");
            AppBarLayout g = floatingToolbarLayout.g(dependencies);
            if (g != null) {
                View recyclerView = floatingToolbarLayout.getRecyclerView();
                if (recyclerView == null) {
                    recyclerView = floatingToolbarLayout.getNestedScrollView();
                }
                if (g.useFloatingToolbar() && recyclerView != null) {
                    if (!j.a(floatingToolbarLayout.getFloatingScrollableManager$material_release().e.g(), recyclerView)) {
                        LogTagHelperKt.warn(floatingToolbarLayout, "isStateToHideCondition floatingScrollableView is not synced (" + recyclerView + ") != (" + floatingToolbarLayout.getFloatingScrollableManager$material_release().e.g() + ')');
                    }
                    if (!recyclerView.canScrollVertically(-1)) {
                        AppBarLayout appBarLayout$material_release = floatingToolbarLayout.getAppBarLayout$material_release();
                        if (appBarLayout$material_release != null) {
                            i2 = appBarLayout$material_release.getTop() + appBarLayout$material_release.getHeight();
                            i7 = Math.max(i2 - ((int) appBarLayout$material_release.seslGetCollapsedHeight()), 0);
                        } else {
                            i7 = 0;
                            i2 = 0;
                        }
                        SeslScrollable g3 = floatingToolbarLayout.getFloatingScrollableManager$material_release().e.g();
                        if (!(g3 == null || floatingToolbarLayout.getFloatingScrollableManager$material_release().e.c(g3) || floatingToolbarLayout.getAppBarLayout$material_release() == null)) {
                            AppBarLayout appBarLayout$material_release2 = floatingToolbarLayout.getAppBarLayout$material_release();
                            if (appBarLayout$material_release2 != null) {
                                if ((appBarLayout$material_release2.seslGetAppBarState().f779a & 4) != 0) {
                                    i8 = appBarLayout$material_release2.getMeasuredHeight();
                                } else {
                                    i8 = 0;
                                }
                                floatingToolbarLayout.f1485W = appBarLayout$material_release2.getTop() + appBarLayout$material_release2.getHeight();
                            } else {
                                i8 = 0;
                            }
                            StringBuilder o2 = C0086a.o(i8, "Update avail rect because avail bottom is zero. update top=", ", bottom=");
                            o2.append(floatingToolbarLayout.f1485W);
                            LogTagHelperKt.info(floatingToolbarLayout, o2.toString());
                            floatingToolbarLayout.getFloatingScrollableManager$material_release().h(i8, -1, floatingToolbarLayout.f1485W);
                        }
                        if (floatingToolbarLayout.getFloatingScrollableManager$material_release().e.b(coordinatorLayout.getHeight(), i2, i7)) {
                            g.seslInternalSetAllowStateToHide(false);
                            LogTagHelperKt.info(this, "Force disable floating appbar because of it is no scrollable");
                            return;
                        }
                    }
                    g.seslInternalSetAllowStateToHide(true);
                }
            }
        }

        public final /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
            d(coordinatorLayout, (FloatingToolbarLayout) view, i2);
            return false;
        }

        public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i7) {
            FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) view;
            j.e(coordinatorLayout, "coordinatorLayout");
            j.e(floatingToolbarLayout, "child");
            j.e(view2, "directTargetChild");
            j.e(view3, "target");
            f(coordinatorLayout, floatingToolbarLayout);
            return false;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatingToolbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        this.L = true;
        C1195m.s0(-1, -1, -1, -1, -1, -1);
        this.b0 = C1195m.s0(-1, -1, -1, -1, -1, -1);
        this.d0 = new ObjectAnimator();
        this.f1487e0 = new p(this);
        LayoutInflater.from(context).inflate(R.layout.sesl_floating_appbar_action_mode_view_stub, this, true);
        this.f1482N = (ViewStubCompat) findViewById(R.id.action_mode_bar_stub);
        h2.p.a(context, attributeSet, 0, 0);
        int[] iArr = a.r;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        h2.p.b(context2, attributeSet2, iArr, 0, 0, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, iArr, 0, 0);
        j.d(obtainStyledAttributes, "obtainStyledAttributes(\n…tyleAttr, 0\n            )");
        if (obtainStyledAttributes.hasValue(0)) {
            this.Q = true;
            this.R = obtainStyledAttributes.getBoolean(0, false);
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.S = true;
            this.T = obtainStyledAttributes.getBoolean(1, false);
        }
    }

    /* access modifiers changed from: private */
    public final ActionBarContextView getActionModeBarView() {
        return (ActionBarContextView) findViewById(R.id.action_mode_bar);
    }

    private final List<Integer> getToolbarChildPosition() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        View referenceView = getFloatingAware$material_release().getReferenceView(C0267a.START_FIRST);
        View referenceView2 = getFloatingAware$material_release().getReferenceView(C0267a.START_SECOND);
        View referenceView3 = getFloatingAware$material_release().getReferenceView(C0267a.END_FIRST);
        int i12 = -1;
        ArrayList s0 = C1195m.s0(-1, -1, -1, -1, -1, -1);
        if (referenceView != null) {
            i2 = referenceView.getLeft();
        } else {
            i2 = -1;
        }
        s0.set(0, Integer.valueOf(i2));
        if (referenceView != null) {
            i7 = referenceView.getRight();
        } else {
            i7 = -1;
        }
        s0.set(1, Integer.valueOf(i7));
        if (referenceView2 != null) {
            i8 = referenceView2.getLeft();
        } else {
            i8 = -1;
        }
        s0.set(2, Integer.valueOf(i8));
        if (referenceView2 != null) {
            i10 = referenceView2.getRight();
        } else {
            i10 = -1;
        }
        s0.set(3, Integer.valueOf(i10));
        if (referenceView3 != null) {
            i11 = referenceView3.getLeft();
        } else {
            i11 = -1;
        }
        s0.set(4, Integer.valueOf(i11));
        if (referenceView3 != null) {
            i12 = referenceView3.getRight();
        }
        s0.set(5, Integer.valueOf(i12));
        return s0;
    }

    private final void setAlphaForTitleViCustomView(float f) {
        Iterator it = this.f1484P.iterator();
        while (it.hasNext()) {
            View findViewById = findViewById(((Number) it.next()).intValue());
            if (findViewById != null) {
                findViewById.setAlpha(f);
            }
        }
    }

    private final void setAlphaForToolbar(float f) {
        Toolbar toolbar$material_release = getToolbar$material_release();
        if (toolbar$material_release != null) {
            toolbar$material_release.seslSetTitleAlpha(f);
            Drawable background = toolbar$material_release.getBackground();
            if (background != null) {
                background.mutate().setAlpha((int) (((float) ScoverState.TYPE_NFC_SMART_COVER) * f));
                toolbar$material_release.seslSetSubtitleAlpha(f);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setAlphaForToolbarTitleViGroup(float f) {
        setAlphaForToolbar(f);
        setAlphaForTitleViCustomView(f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0050, code lost:
        if (r1 > 255.0f) goto L_0x0052;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void setTitleAlphaByCollapsingToolbarLayoutPolicy(com.google.android.material.appbar.AppBarLayout r6) {
        /*
            r5 = this;
            boolean r0 = r6.useCollapsedHeight()
            if (r0 == 0) goto L_0x000b
            float r0 = r6.seslGetCollapsedHeight()
            goto L_0x0016
        L_0x000b:
            android.content.res.Resources r0 = r5.getResources()
            int r1 = androidx.appcompat.R$dimen.sesl_action_bar_height_with_padding
            int r0 = r0.getDimensionPixelSize(r1)
            float r0 = (float) r0
        L_0x0016:
            int r1 = r6.getHeight()
            float r1 = (float) r1
            r2 = 1041395352(0x3e126e98, float:0.143)
            float r1 = r1 * r2
            int r2 = r6.getTop()
            int r2 = java.lang.Math.abs(r2)
            int r3 = r6.getBottom()
            int r0 = (int) r0
            r4 = 1132396544(0x437f0000, float:255.0)
            if (r3 <= r0) goto L_0x0054
            boolean r0 = r6.seslIsCollapsed()
            if (r0 == 0) goto L_0x0037
            goto L_0x0054
        L_0x0037:
            int r6 = r6.getHeight()
            float r6 = (float) r6
            r0 = 1051931443(0x3eb33333, float:0.35)
            float r6 = r6 * r0
            r0 = 150(0x96, float:2.1E-43)
            float r0 = (float) r0
            float r0 = r0 / r1
            float r1 = (float) r2
            float r1 = r1 - r6
            float r1 = r1 * r0
            r6 = 0
            int r0 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x004e
            r1 = r6
            goto L_0x005f
        L_0x004e:
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x005f
        L_0x0052:
            r1 = r4
            goto L_0x005f
        L_0x0054:
            androidx.appcompat.widget.Toolbar r6 = r5.getToolbar$material_release()
            if (r6 == 0) goto L_0x0052
            r0 = 1
            r6.setTitleAccessibilityEnabled(r0)
            goto L_0x0052
        L_0x005f:
            float r1 = r1 / r4
            r5.setAlphaForToolbarTitleViGroup(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout.setTitleAlphaByCollapsingToolbarLayoutPolicy(com.google.android.material.appbar.AppBarLayout):void");
    }

    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Toolbar) {
            Toolbar toolbar = (Toolbar) view;
            this.f1481M = toolbar;
            if (this.Q) {
                p(this.R);
            }
            if (this.S) {
                m(this.T, true);
            }
            toolbar.getViewTreeObserver().addOnPreDrawListener(new S1.p(1, toolbar));
            super.addView(view, i2, layoutParams);
            setFloatingAware((C0268b) null);
            getProjectionView$material_release().e(0.0f, true);
            v vVar = new v(3, this);
            o oVar = this.l;
            oVar.getClass();
            oVar.k.addListener(vVar);
            return;
        }
        super.addView(view, i2, layoutParams);
    }

    public final void c() {
        super.c();
        C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
        int i2 = this.V;
        SeslScrollable g = floatingScrollableManager$material_release.e.g();
        if (g != null) {
            g.seslForceTopFadingEdgeClamped(i2);
        }
        getFloatingScrollableManager$material_release().h(-1, -1, this.f1485W);
        if (!getWithAppBarLayout$material_release()) {
            C0269a floatingScrollableManager$material_release2 = getFloatingScrollableManager$material_release();
            int measuredHeight = getMeasuredHeight();
            floatingScrollableManager$material_release2.getClass();
            int max = Math.max(0, measuredHeight);
            if (floatingScrollableManager$material_release2.n != max) {
                floatingScrollableManager$material_release2.n = max;
                SeslScrollable g3 = floatingScrollableManager$material_release2.e.g();
                if (g3 != null) {
                    g3.seslSetHoverTopPadding(floatingScrollableManager$material_release2.n);
                }
            }
        }
    }

    public CoordinatorLayout.Behavior<?> getBehavior() {
        Context context = getContext();
        j.d(context, "context");
        return new FloatingToolbarBehavior(context, getAttrs());
    }

    public String getLogTag() {
        return "FloatingToolbarLayout";
    }

    public /* bridge */ /* synthetic */ String getPrefix() {
        return "";
    }

    public final Toolbar getToolbar$material_release() {
        Toolbar toolbar = this.f1481M;
        if (toolbar != null) {
            return toolbar;
        }
        for (View view : ViewGroupKt.getChildren(this)) {
            if (view instanceof Toolbar) {
                Toolbar toolbar2 = (Toolbar) view;
                this.f1481M = toolbar2;
                return toolbar2;
            }
        }
        LogTagHelperKt.warn(this, "FloatingToolbar layout Not have a Toolbar");
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int min = Math.min(stackTrace.length, 7);
        for (int i2 = 0; i2 < min; i2++) {
            String stackTraceElement = stackTrace[i2].toString();
            j.d(stackTraceElement, "stackTrace.elementAt(index).toString()");
            LogTagHelperKt.warn(this, stackTraceElement);
        }
        return null;
    }

    public /* bridge */ /* synthetic */ String getVersion() {
        return "[sesl8-material:2.0.67]";
    }

    public final boolean j() {
        if (getAlpha() == 0.0f || getProjectionView$material_release().getAlpha() == 0.0f) {
            return false;
        }
        return true;
    }

    public final void k(AppBarLayout appBarLayout, int i2) {
        int i7;
        View view;
        SeslScrollable g;
        SeslScrollable g3;
        this.f1485W = appBarLayout.getHeight() + i2;
        int seslGetCollapsedHeight = (int) appBarLayout.seslGetCollapsedHeight();
        int i8 = seslGetCollapsedHeight - this.f1485W;
        if (i8 < 0) {
            i8 = 0;
        }
        int seslGetProportionExtraHeight = appBarLayout.seslGetProportionExtraHeight();
        C0269a floatingScrollableManager$material_release = getFloatingScrollableManager$material_release();
        floatingScrollableManager$material_release.f1910p = seslGetCollapsedHeight;
        C0275e eVar = floatingScrollableManager$material_release.e;
        SeslScrollable g10 = eVar.g();
        if (g10 != null) {
            g10.seslSetScrollBarTopOffset(i8);
        }
        SeslScrollable g11 = eVar.g();
        if (g11 != null) {
            g11.seslSetScrollBarBottomOffset(floatingScrollableManager$material_release.f1910p + floatingScrollableManager$material_release.f1909o + floatingScrollableManager$material_release.f1906h);
        }
        int i10 = i8 - seslGetProportionExtraHeight;
        if (i10 < 0) {
            i10 = 0;
        }
        this.V = i10;
        C0269a floatingScrollableManager$material_release2 = getFloatingScrollableManager$material_release();
        int i11 = this.V;
        SeslScrollable g12 = floatingScrollableManager$material_release2.e.g();
        if (g12 != null) {
            g12.seslForceTopFadingEdgeClamped(i11);
        }
        if ((appBarLayout.seslGetAppBarState().f779a & 4) != 0) {
            i7 = getMeasuredHeight();
        } else {
            i7 = 0;
        }
        getFloatingScrollableManager$material_release().h(i7, -1, this.f1485W);
        if (appBarLayout.useFloatingToolbar()) {
            C0269a floatingScrollableManager$material_release3 = getFloatingScrollableManager$material_release();
            int top = appBarLayout.getTop() + appBarLayout.getHeight();
            if (floatingScrollableManager$material_release3.g && (g3 = floatingScrollableManager$material_release3.e.g()) != null) {
                g3.seslSetBottomScrollOffset(top);
            }
            if (seslGetCollapsedHeight - this.f1485W >= 0) {
                C0269a floatingScrollableManager$material_release4 = getFloatingScrollableManager$material_release();
                floatingScrollableManager$material_release4.getClass();
                int max = Math.max(0, i8);
                if (floatingScrollableManager$material_release4.n != max) {
                    floatingScrollableManager$material_release4.n = max;
                    SeslScrollable g13 = floatingScrollableManager$material_release4.e.g();
                    if (g13 != null) {
                        g13.seslSetHoverTopPadding(floatingScrollableManager$material_release4.n);
                    }
                }
            }
        } else {
            C0269a floatingScrollableManager$material_release5 = getFloatingScrollableManager$material_release();
            int top2 = (int) (((float) (appBarLayout.getTop() + appBarLayout.getHeight())) - appBarLayout.seslGetCollapsedHeight());
            if (floatingScrollableManager$material_release5.g && (g = floatingScrollableManager$material_release5.e.g()) != null) {
                g.seslSetBottomScrollOffset(top2);
            }
        }
        SeslScrollable g14 = getFloatingScrollableManager$material_release().e.g();
        if (g14 instanceof View) {
            view = (View) g14;
        } else {
            view = null;
        }
        if (view != null) {
            view.invalidate();
        }
        C0269a floatingScrollableManager$material_release6 = getFloatingScrollableManager$material_release();
        floatingScrollableManager$material_release6.l = appBarLayout.getTop() + appBarLayout.getMeasuredHeight();
        floatingScrollableManager$material_release6.k();
        if (i2 != 0) {
            i();
        }
        u(appBarLayout, false);
    }

    public final void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        super.onDraw(canvas);
        ViewStubCompat viewStubCompat = this.f1482N;
        if (viewStubCompat != null) {
            viewStubCompat.bringToFront();
            viewStubCompat.invalidate();
        }
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        Toolbar toolbar;
        boolean z3;
        boolean z7;
        boolean z9;
        Toolbar toolbar$material_release = getToolbar$material_release();
        if (toolbar$material_release != null) {
            if (this.a0) {
                if (getAppBarLayout$material_release() != null) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                this.k = z9;
                getProjectionView$material_release().f(false);
                c();
                this.a0 = false;
                this.f1486c0 = toolbar$material_release.getChildCount();
                int min = Math.min(3, toolbar$material_release.getChildCount());
                for (int i11 = 0; i11 < min; i11++) {
                    View childAt = toolbar$material_release.getChildAt(i11);
                    int i12 = i11 * 2;
                    this.b0.set(i12, Integer.valueOf(childAt.getLeft()));
                    this.b0.set(i12 + 1, Integer.valueOf(childAt.getRight()));
                }
            }
            if (!getWithAppBarLayout$material_release()) {
                getProjectionView$material_release().f(false);
            }
            if (this.f1483O == null) {
                ActionBarContextView actionBarContextView = (ActionBarContextView) findViewById(R.id.action_mode_bar);
                this.f1483O = actionBarContextView;
                if (actionBarContextView != null) {
                    actionBarContextView.getViewTreeObserver().addOnGlobalLayoutListener(new w(actionBarContextView, this));
                }
            }
        } else {
            getProjectionView$material_release().f(false);
        }
        Toolbar toolbar$material_release2 = getToolbar$material_release();
        if (toolbar$material_release2 != null) {
            if (getAlpha() == 1.0f) {
                z7 = true;
            } else {
                z7 = false;
            }
            toolbar$material_release2.seslSetEatingHover(!z7);
        }
        ActionBarContextView actionModeBarView = getActionModeBarView();
        if (actionModeBarView != null) {
            if (getAlpha() == 1.0f) {
                z3 = true;
            } else {
                z3 = false;
            }
            actionModeBarView.seslSetEatingTouchOnly(!z3);
        }
        super.onLayout(z, i2, i7, i8, i10);
        if (toolbar$material_release != null && (toolbar = this.f1481M) != null) {
            if (this.f1486c0 != toolbar.getChildCount()) {
                getProjectionView$material_release().f(true);
                this.f1486c0 = toolbar.getChildCount();
                return;
            }
            ArrayList s0 = C1195m.s0(-1, -1, -1, -1, -1, -1);
            int min2 = Math.min(3, toolbar.getChildCount());
            for (int i13 = 0; i13 < min2; i13++) {
                View childAt2 = toolbar.getChildAt(i13);
                int i14 = i13 * 2;
                s0.set(i14, Integer.valueOf(childAt2.getLeft()));
                s0.set(i14 + 1, Integer.valueOf(childAt2.getRight()));
            }
            if (!j.a(this.b0, s0)) {
                getProjectionView$material_release().f(true);
                this.b0 = s0;
            }
        }
    }

    public final void onMeasure(int i2, int i7) {
        ViewStubCompat viewStubCompat = this.f1482N;
        if (viewStubCompat != null) {
            viewStubCompat.bringToFront();
            viewStubCompat.invalidate();
        }
        Toolbar toolbar$material_release = getToolbar$material_release();
        if (toolbar$material_release != null) {
            measureChild(toolbar$material_release, i2, i7);
            ActionBarContextView actionModeBarView = getActionModeBarView();
            if (actionModeBarView != null) {
                actionModeBarView.measure(i2, i7);
            }
            setMeasuredDimension(getPaddingEnd() + getPaddingStart() + toolbar$material_release.getMeasuredWidth(), getPaddingBottom() + getPaddingTop() + toolbar$material_release.getMeasuredHeight());
            getProjectionView$material_release().measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingStart()) - getPaddingEnd(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            AppBarLayout appBarLayout$material_release = getAppBarLayout$material_release();
            if (appBarLayout$material_release != null) {
                for (View view : ViewGroupKt.getChildren(appBarLayout$material_release)) {
                    if (view instanceof CollapsingToolbarLayout) {
                        ((CollapsingToolbarLayout) view).setMinimumHeight(toolbar$material_release.getMeasuredHeight());
                        return;
                    }
                }
                return;
            }
            return;
        }
        super.onMeasure(i2, i7);
    }

    public void setFloatingAware(C0268b bVar) {
        ActionBarContextView actionBarContextView = this.f1483O;
        boolean z = false;
        if (actionBarContextView != null && actionBarContextView.getVisibility() == 0) {
            z = true;
        }
        this.U = z;
        if (bVar == null) {
            LogTagHelperKt.info(this, "Use default FloatingToolbarAware FloatingAware");
        } else {
            LogTagHelperKt.info(this, "Use custom CustomAware(Toolbar) FloatingAware");
        }
        if (bVar == null) {
            bVar = new y(this);
        }
        super.setFloatingAware(bVar);
    }

    public final void t(boolean z, boolean z3) {
        float f;
        long j2;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        if (this.f1481M != null) {
            if (this.d0.isRunning() || z3) {
                this.d0.cancel();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f1481M, this.f1487e0, new float[]{f});
            j.d(ofFloat, "ofFloat(toolbar, titleAlphaAnimProperty, end)");
            this.d0 = ofFloat;
            if (z3) {
                j2 = 0;
            } else if (z) {
                j2 = 150;
            } else {
                j2 = 100;
            }
            ofFloat.setDuration(j2);
            ofFloat.start();
        }
    }

    public final void u(AppBarLayout appBarLayout, boolean z) {
        AppBarLayout appBarLayout$material_release;
        j.e(appBarLayout, "appbarLayout");
        if (((float) appBarLayout.getBottom()) >= appBarLayout.seslGetCollapsedHeight()) {
            if (this.d0.isRunning()) {
                this.d0.cancel();
            }
            setTitleAlphaByCollapsingToolbarLayoutPolicy(appBarLayout);
        } else if (z) {
            setAlphaForToolbarTitleViGroup(0.0f);
            if (getAppBarLayout$material_release() != null && (appBarLayout$material_release = getAppBarLayout$material_release()) != null && appBarLayout$material_release.seslGetCurrentAppBarState() == 2) {
                setAlphaForToolbarTitleViGroup(1.0f);
            }
        }
    }

    public final void l() {
    }
}
