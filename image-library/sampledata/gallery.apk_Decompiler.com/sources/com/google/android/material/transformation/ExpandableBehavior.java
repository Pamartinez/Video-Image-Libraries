package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import java.util.List;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
    public ExpandableBehavior() {
    }

    public abstract boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2);

    public final boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        view2.getClass();
        throw new ClassCastException();
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (!ViewCompat.isLaidOut(view)) {
            List<View> dependencies = coordinatorLayout.getDependencies(view);
            int size = dependencies.size();
            for (int i7 = 0; i7 < size; i7++) {
                layoutDependsOn(coordinatorLayout, view, dependencies.get(i7));
            }
        }
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
