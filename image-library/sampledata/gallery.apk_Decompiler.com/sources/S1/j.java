package S1;

import D0.e;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.appbar.AppBarLayout;
import ee.C0970c;
import ee.C0971d;
import ee.C0975h;
import ee.M;
import ee.a0;
import ee.d0;
import ee.e0;
import ge.C1002C;
import ge.C1003D;
import ge.F0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j implements Runnable {
    public final /* synthetic */ int d;
    public final Object e;
    public final Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = obj2;
        this.f = obj3;
    }

    public final void run() {
        OverScroller overScroller;
        switch (this.d) {
            case 0:
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.e;
                k kVar = (k) this.g;
                View view = (View) this.f;
                if (view != null && (overScroller = kVar.g) != null) {
                    if (overScroller.computeScrollOffset()) {
                        kVar.g(coordinatorLayout, view, kVar.g.getCurrY());
                        ViewCompat.postOnAnimation(view, this);
                        return;
                    }
                    AppBarLayout appBarLayout = (AppBarLayout) view;
                    OverScroller overScroller2 = ((AppBarLayout.BaseBehavior) kVar).g;
                    if (overScroller2 != null) {
                        overScroller2.forceFinished(true);
                        return;
                    }
                    return;
                }
                return;
            case 1:
                ((e0) this.g).execute((d0) this.e);
                return;
            case 2:
                ((C1003D) this.g).f4388i.l((C0971d) this.e, (M) this.f);
                return;
            case 3:
                ((C1002C) this.g).f4386c.d((a0) this.e, (M) this.f);
                return;
            default:
                C0975h hVar = (C0975h) this.f;
                C0971d dVar = (C0971d) this.e;
                e eVar = (e) this.g;
                F0 f02 = (F0) eVar.f;
                if (eVar == f02.f4425x) {
                    f02.y = dVar;
                    f02.E.h(dVar);
                    if (hVar != C0975h.SHUTDOWN) {
                        ((F0) eVar.f).f4413N.c(C0970c.INFO, "Entering {0} state with picker: {1}", hVar, dVar);
                        ((F0) eVar.f).r.a(hVar);
                        return;
                    }
                    return;
                }
                return;
        }
    }

    public String toString() {
        switch (this.d) {
            case 1:
                return ((Runnable) this.f).toString() + "(scheduled in SynchronizationContext)";
            default:
                return super.toString();
        }
    }
}
