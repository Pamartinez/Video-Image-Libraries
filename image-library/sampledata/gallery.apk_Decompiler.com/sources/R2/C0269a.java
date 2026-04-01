package r2;

import android.graphics.Rect;
import android.view.View;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.SeslScrollable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.WeakHashMap;
import kotlin.jvm.internal.j;
import m2.a;
import q2.r;
import s2.C0272b;
import s2.C0274d;
import s2.C0275e;

/* renamed from: r2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0269a implements C0275e, a {
    public static final WeakHashMap q = new WeakHashMap();
    public static final WeakHashMap r = new WeakHashMap();
    public static final C0269a s = new C0269a(new Object());
    public static final Object t = new Object();
    public C0275e d;
    public final /* synthetic */ C0275e e;
    public boolean f = true;
    public boolean g = true;

    /* renamed from: h  reason: collision with root package name */
    public int f1906h;

    /* renamed from: i  reason: collision with root package name */
    public int f1907i;

    /* renamed from: j  reason: collision with root package name */
    public int f1908j;
    public int k;
    public int l;
    public int m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public int f1909o;

    /* renamed from: p  reason: collision with root package name */
    public int f1910p;

    public C0269a(C0275e eVar) {
        this.d = eVar;
        this.e = eVar;
    }

    public final void a() {
        this.e.a();
    }

    public final boolean b(int i2, int i7, int i8) {
        return this.e.b(i2, i7, i8);
    }

    public final boolean c(SeslScrollable seslScrollable) {
        return this.e.c(seslScrollable);
    }

    public final void e(r rVar) {
        j.e(rVar, "listener");
        this.e.e(rVar);
    }

    public final void f(r rVar) {
        j.e(rVar, "listener");
        this.e.f(rVar);
    }

    public final SeslScrollable g() {
        return this.e.g();
    }

    public final String getLogTag() {
        return "FloatingScrollableManager";
    }

    public final void h(int i2, int i7, int i8) {
        C0274d dVar;
        if (i2 == -1) {
            i2 = this.f1907i;
        }
        this.f1907i = i2;
        if (i7 == -1) {
            i7 = this.f1908j;
        }
        this.f1908j = i7;
        if (i8 == -1) {
            i8 = this.k;
        }
        this.k = i8;
        int i10 = i7 + this.f1906h + i8;
        C0275e eVar = this.d;
        View view = null;
        if (eVar instanceof C0274d) {
            dVar = (C0274d) eVar;
        } else {
            dVar = null;
        }
        if (dVar != null) {
            this.f1907i = i2;
        }
        new Rect(0, this.f1907i, 0, i10);
        SeslScrollable g3 = this.e.g();
        if (g3 != null) {
            if (g3 instanceof View) {
                view = (View) g3;
            }
            if (view != null) {
                g3.seslSetAvailableBounds(new Rect(0, this.f1907i, view.getMeasuredWidth(), view.getMeasuredHeight() - i10));
            }
        }
    }

    public final void i(int i2) {
        if (i2 == -1) {
            i2 = this.f1909o;
        }
        this.f1909o = i2;
        int i7 = i2 + this.f1906h;
        C0275e eVar = this.e;
        SeslScrollable g3 = eVar.g();
        if (g3 != null) {
            g3.seslSetHoverBottomPadding(i7);
            g3.seslSetFloatingBottomLayoutHeight(this.f1909o);
        }
        SeslScrollable g10 = eVar.g();
        if (g10 != null) {
            g10.seslSetScrollBarBottomOffset(this.f1910p + this.f1909o + this.f1906h);
        }
    }

    public final void j(SeslScrollable seslScrollable) {
        C0275e eVar;
        j.e(seslScrollable, "floatingScrollableView");
        LogTagHelperKt.debug(this, "setFloatingScrollableView floatingScrollableView=" + seslScrollable.hashCode());
        if (!j.a(this.d.g(), seslScrollable)) {
            if (seslScrollable instanceof RecyclerView) {
                eVar = new C0274d((RecyclerView) seslScrollable);
            } else if (seslScrollable instanceof NestedScrollView) {
                eVar = new C0272b((NestedScrollView) seslScrollable);
            } else {
                eVar = null;
            }
            if (eVar != null) {
                LogTagHelperKt.debug(this, "setFloatingScrollableView change Adapter=" + eVar);
                this.d = eVar;
                return;
            }
            throw new Exception("setFloatingScrollableView type error " + seslScrollable);
        }
    }

    public final void k() {
        View view;
        if (!this.f) {
            LogTagHelperKt.debug(this, "updateGoToTopOffset off");
            return;
        }
        SeslScrollable g3 = this.e.g();
        if (g3 != null) {
            g3.seslSetGoToTopBottomPadding(g3.seslGetGoToTopDefaultBottomPadding() + this.l + this.m + this.f1906h);
            if (g3 instanceof View) {
                view = (View) g3;
            } else {
                view = null;
            }
            if (view != null) {
                view.requestLayout();
            }
        }
    }
}
