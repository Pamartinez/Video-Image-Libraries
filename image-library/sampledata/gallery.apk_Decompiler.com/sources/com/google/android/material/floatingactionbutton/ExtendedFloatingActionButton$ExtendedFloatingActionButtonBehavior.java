package com.google.android.material.floatingactionbutton;

import Q1.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior<T> extends CoordinatorLayout.Behavior<T> {
    public ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior() {
    }

    public final boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
        if (view == null) {
            return super.getInsetDodgeRect(coordinatorLayout, null, rect);
        }
        throw new ClassCastException();
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    public final boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        boolean z;
        if (view != null) {
            throw new ClassCastException();
        } else if (!(view2 instanceof AppBarLayout)) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                z = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
            throw null;
        } else {
            throw null;
        }
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        boolean z;
        if (view == null) {
            List<View> dependencies = coordinatorLayout.getDependencies((View) null);
            int size = dependencies.size();
            int i7 = 0;
            while (i7 < size) {
                View view2 = dependencies.get(i7);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                        z = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        i7++;
                    } else {
                        throw null;
                    }
                } else {
                    throw null;
                }
            }
            coordinatorLayout.onLayoutChild((View) null, i2);
            return true;
        }
        throw new ClassCastException();
    }

    public ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f631o);
        obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
    }
}
