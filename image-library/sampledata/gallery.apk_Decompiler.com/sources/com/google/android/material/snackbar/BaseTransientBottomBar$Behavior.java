package com.google.android.material.snackbar;

import D0.f;
import G0.c;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import z2.k;
import z2.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {
    public final c m;

    public BaseTransientBottomBar$Behavior() {
        c cVar = new c(20, false);
        this.f1404j = Math.min(Math.max(0.0f, 0.1f), 1.0f);
        this.k = Math.min(Math.max(0.0f, 0.6f), 1.0f);
        this.f1402h = 0;
        this.m = cVar;
    }

    public final boolean a(View view) {
        this.m.getClass();
        return view instanceof p;
    }

    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        c cVar = this.m;
        cVar.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                f.F().S((k) cVar.e);
            }
        } else if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            f.F().Q((k) cVar.e);
        }
        return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
    }
}
