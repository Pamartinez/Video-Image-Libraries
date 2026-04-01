package com.google.android.material.oneui.floatingactioncontainer;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.oneui.floatingactioncontainer.behavior.AppBarScrollBehavior;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import m2.a;
import q2.u;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u00032\u00020\u0004B\u001b\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/google/android/material/oneui/floatingactioncontainer/FloatingGroupLayout$FloatingActionBehavior", "Lq2/u;", "T", "Lcom/google/android/material/oneui/floatingactioncontainer/behavior/AppBarScrollBehavior;", "Lm2/a;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingGroupLayout$FloatingActionBehavior<T extends u> extends AppBarScrollBehavior<T> implements a {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatingGroupLayout$FloatingActionBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        new Rect();
    }

    /* renamed from: a */
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, u uVar, int i2) {
        j.e(coordinatorLayout, "parent");
        j.e(uVar, "child");
        if (uVar.getShowBackgroundAtFirst$material_release() && uVar.getProjectionView$material_release().getParent() != null) {
            uVar.getProjectionView$material_release().f(true);
            uVar.getProjectionView$material_release().e(1.0f, true);
        }
        return super.onLayoutChild(coordinatorLayout, uVar, i2);
    }

    public final String getLogTag() {
        return "FloatingActionBehavior";
    }
}
