package q2;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ ActionBarContextView d;
    public final /* synthetic */ FloatingToolbarLayout e;

    public /* synthetic */ w(ActionBarContextView actionBarContextView, FloatingToolbarLayout floatingToolbarLayout) {
        this.d = actionBarContextView;
        this.e = floatingToolbarLayout;
    }

    public final void onGlobalLayout() {
        boolean z;
        int i2 = FloatingToolbarLayout.f1480f0;
        ActionBarContextView actionBarContextView = this.d;
        int i7 = 0;
        if (actionBarContextView.getVisibility() == 0) {
            z = true;
        } else {
            z = false;
        }
        FloatingToolbarLayout floatingToolbarLayout = this.e;
        if (z != floatingToolbarLayout.U) {
            o projectionView$material_release = floatingToolbarLayout.getProjectionView$material_release();
            int i8 = o.n;
            projectionView$material_release.f(true);
            floatingToolbarLayout.U = z;
            if (z) {
                Iterator it = floatingToolbarLayout.f1484P.iterator();
                while (it.hasNext()) {
                    View findViewById = actionBarContextView.findViewById(((Number) it.next()).intValue());
                    if (findViewById != null) {
                        findViewById.post(new t(7, floatingToolbarLayout));
                    }
                }
            }
        }
        Toolbar toolbar$material_release = floatingToolbarLayout.getToolbar$material_release();
        if (toolbar$material_release != null) {
            if (floatingToolbarLayout.U) {
                i7 = 4;
            }
            toolbar$material_release.setVisibility(i7);
            CoordinatorLayout.Behavior<?> behavior = floatingToolbarLayout.getBehavior();
            j.c(behavior, "null cannot be cast to non-null type com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout.FloatingToolbarBehavior<*>");
            AppBarLayout appBarLayout = ((FloatingToolbarLayout.FloatingToolbarBehavior) behavior).d;
            if (appBarLayout != null) {
                floatingToolbarLayout.u(appBarLayout, true);
            }
        }
    }
}
