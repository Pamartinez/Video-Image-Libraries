package com.google.android.material.oneui.floatingactioncontainer.behavior;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/google/android/material/oneui/floatingactioncontainer/behavior/AppBarScrollBehavior;", "Landroid/view/View;", "T", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppBarScrollBehavior<T extends View> extends CoordinatorLayout.Behavior<T> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppBarScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        new Rect();
    }

    public final boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        j.e(coordinatorLayout, "parent");
        j.e(view, "child");
        j.e(view2, "dependency");
        return view2 instanceof AppBarLayout;
    }

    public final boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        j.e(coordinatorLayout, "parent");
        j.e(view, "child");
        j.e(view2, "dependency");
        if (view2 instanceof AppBarLayout) {
            return true;
        }
        return false;
    }
}
