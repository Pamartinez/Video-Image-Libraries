package com.google.android.material.bottomappbar;

import W1.a;
import W1.b;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomAppBar$Behavior extends HideBottomViewOnScrollBehavior<b> {
    public WeakReference l;

    public BottomAppBar$Behavior() {
        new a(this);
        new Rect();
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (view == null) {
            this.l = new WeakReference((Object) null);
            int i7 = b.d;
            throw null;
        }
        throw new ClassCastException();
    }

    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i7) {
        view.getClass();
        throw new ClassCastException();
    }

    public BottomAppBar$Behavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new a(this);
        new Rect();
    }
}
