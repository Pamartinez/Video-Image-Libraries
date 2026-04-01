package q2;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingTopLayout$FloatingTopBehavior;
import kotlin.jvm.internal.j;

/* renamed from: q2.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0266A extends u {
    public boolean L;

    public static void q(C0266A a7, boolean z, int i2) {
        boolean z3;
        float f;
        if ((i2 & 2) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        a7.getClass();
        if (a7.L) {
            if (z) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            a7.getProjectionView$material_release().f(true);
            a7.getProjectionView$material_release().e(f, !z3);
        }
    }

    public CoordinatorLayout.Behavior<?> getBehavior() {
        Context context = getContext();
        j.d(context, "context");
        return new FloatingTopLayout$FloatingTopBehavior(context, getAttrs());
    }

    public final boolean getEnablePrjAlphaTransition$material_release() {
        return this.L;
    }

    public String getLogTag() {
        return "FloatingTopLayout";
    }

    public /* bridge */ /* synthetic */ String getPrefix() {
        return "";
    }

    public /* bridge */ /* synthetic */ String getVersion() {
        return "[sesl8-material:2.0.67]";
    }

    public final void p(boolean z) {
        LogTagHelperKt.info(this, "enable Toolbar Item BG Transition enabled:" + z + " show:true");
        this.L = z;
        if (z) {
            AppBarLayout appBarLayout$material_release = getAppBarLayout$material_release();
            if (appBarLayout$material_release != null) {
                q(this, appBarLayout$material_release.seslIsHided(), 6);
                return;
            }
            return;
        }
        m(true, false);
    }

    public final void setEnablePrjAlphaTransition$material_release(boolean z) {
        this.L = z;
    }
}
