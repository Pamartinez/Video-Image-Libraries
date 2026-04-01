package com.google.android.material.behavior;

import V1.a;
import V1.b;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ViewDragHelper;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import z2.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public ViewDragHelper d;
    public l e;
    public boolean f;
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public int f1402h = 2;

    /* renamed from: i  reason: collision with root package name */
    public final float f1403i = 0.5f;

    /* renamed from: j  reason: collision with root package name */
    public float f1404j = 0.0f;
    public float k = 0.5f;
    public final a l = new a(this);

    public boolean a(View view) {
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z = this.f;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f = false;
        }
        if (z) {
            if (this.d == null) {
                this.d = ViewDragHelper.create(coordinatorLayout, this.l);
            }
            if (this.g || !this.d.shouldInterceptTouchEvent(motionEvent)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, view, i2);
        if (ViewCompat.getImportantForAccessibility(view) == 0) {
            ViewCompat.setImportantForAccessibility(view, 1);
            ViewCompat.removeAccessibilityAction(view, MediaDefs.Meta.SEF.SEF_MIN_SIZE);
            if (a(view)) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, (CharSequence) null, new b(this));
            }
        }
        return onLayoutChild;
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.d == null) {
            return false;
        }
        if (this.g && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.d.processTouchEvent(motionEvent);
        return true;
    }
}
