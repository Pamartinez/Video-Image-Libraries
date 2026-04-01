package com.google.android.material.oneui.floatingactioncontainer;

import Tf.n;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import q2.C0266A;
import q2.o;
import q2.u;
import q2.z;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"com/google/android/material/oneui/floatingactioncontainer/FloatingTopLayout$FloatingTopBehavior", "Lq2/A;", "T", "Lcom/google/android/material/oneui/floatingactioncontainer/FloatingGroupLayout$FloatingActionBehavior;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingTopLayout$FloatingTopBehavior<T extends C0266A> extends FloatingGroupLayout$FloatingActionBehavior<T> {
    public AppBarLayout d;
    public boolean e = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatingTopLayout$FloatingTopBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
    }

    public static String b(int i2) {
        String str;
        if ((i2 & 4) != 0) {
            str = "HIDE ";
        } else {
            str = "";
        }
        if ((i2 & 2) != 0) {
            str = str.concat("COLLAPSED ");
        }
        if ((i2 & 1) != 0) {
            str = C0212a.A(str, "EXPANDED");
        }
        return "[ " + n.R0(str).toString() + " ]";
    }

    public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, u uVar, int i2) {
        d(coordinatorLayout, (C0266A) uVar, i2);
        return false;
    }

    public void c(int i2, int i7, C0266A a7) {
        j.e(a7, "child");
        LogTagHelperKt.info(this, "AppBarState Changed old:" + b(i2) + " new:" + b(i7));
        int i8 = i7 & 4;
        if (i8 != 0 && (i2 & 4) == 0) {
            C0266A.q(a7, true, 6);
        } else if (i8 == 0 && (i2 & 4) != 0) {
            C0266A.q(a7, false, 6);
        }
    }

    public void d(CoordinatorLayout coordinatorLayout, C0266A a7, int i2) {
        j.e(coordinatorLayout, "parent");
        j.e(a7, "child");
        List<View> dependencies = coordinatorLayout.getDependencies(a7);
        j.d(dependencies, "parent.getDependencies(child)");
        AppBarLayout g = a7.g(dependencies);
        if (g != null) {
            LogTagHelperKt.info(this, "onLayoutChild of Behavior First AppBarState " + b(g.seslGetAppBarState().f779a));
            if (this.e) {
                if ((g.seslGetAppBarState().f779a & 4) != 0) {
                    C0266A.q(a7, true, 4);
                } else {
                    C0266A.q(a7, false, 4);
                }
                g.seslAddAppBarStateChangedListener(new z(this, a7));
                this.e = false;
                o projectionView$material_release = a7.getProjectionView$material_release();
                int i7 = o.n;
                projectionView$material_release.f(true);
            }
            this.d = g;
        }
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        CoordinatorLayout.LayoutParams layoutParams2;
        CoordinatorLayout.LayoutParams layoutParams3;
        j.e(layoutParams, ActionHandler.PARAMS);
        if (layoutParams.getAnchorId() == -1) {
            layoutParams2 = layoutParams;
        } else {
            layoutParams2 = null;
        }
        if (layoutParams2 != null) {
            LogTagHelperKt.error(this, "anchorId is not set");
        }
        if (layoutParams.gravity == 0) {
            layoutParams3 = layoutParams;
        } else {
            layoutParams3 = null;
        }
        if (layoutParams3 != null) {
            layoutParams3.gravity = 48;
        }
        if (layoutParams.anchorGravity != 0) {
            layoutParams = null;
        }
        if (layoutParams != null) {
            layoutParams.anchorGravity = 80;
        }
    }

    public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        d(coordinatorLayout, (C0266A) view, i2);
        return false;
    }
}
