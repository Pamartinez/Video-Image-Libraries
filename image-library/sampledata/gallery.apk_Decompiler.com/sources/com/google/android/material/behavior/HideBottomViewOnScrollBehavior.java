package com.google.android.material.behavior;

import R1.a;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Iterator;
import java.util.LinkedHashSet;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public final LinkedHashSet d = new LinkedHashSet();
    public int e;
    public int f;
    public TimeInterpolator g;

    /* renamed from: h  reason: collision with root package name */
    public TimeInterpolator f1399h;

    /* renamed from: i  reason: collision with root package name */
    public int f1400i = 0;

    /* renamed from: j  reason: collision with root package name */
    public int f1401j = 2;
    public ViewPropertyAnimator k;

    public HideBottomViewOnScrollBehavior() {
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        this.f1400i = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.e = k.L(view.getContext(), R.attr.motionDurationLong2, 225);
        this.f = k.L(view.getContext(), R.attr.motionDurationMedium4, 175);
        this.g = k.M(view.getContext(), R.attr.motionEasingEmphasizedInterpolator, a.d);
        this.f1399h = k.M(view.getContext(), R.attr.motionEasingEmphasizedInterpolator, a.f641c);
        return super.onLayoutChild(coordinatorLayout, view, i2);
    }

    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        LinkedHashSet linkedHashSet = this.d;
        if (i7 > 0) {
            if (this.f1401j != 1) {
                ViewPropertyAnimator viewPropertyAnimator = this.k;
                if (viewPropertyAnimator != null) {
                    viewPropertyAnimator.cancel();
                    view.clearAnimation();
                }
                this.f1401j = 1;
                Iterator it = linkedHashSet.iterator();
                if (!it.hasNext()) {
                    int i12 = this.f1400i;
                    this.k = view.animate().translationY((float) i12).setInterpolator(this.f1399h).setDuration((long) this.f).setListener(new B2.k(2, this));
                    return;
                }
                throw C0212a.h(it);
            }
        } else if (i7 < 0 && this.f1401j != 2) {
            ViewPropertyAnimator viewPropertyAnimator2 = this.k;
            if (viewPropertyAnimator2 != null) {
                viewPropertyAnimator2.cancel();
                view.clearAnimation();
            }
            this.f1401j = 2;
            Iterator it2 = linkedHashSet.iterator();
            if (!it2.hasNext()) {
                this.k = view.animate().translationY((float) 0).setInterpolator(this.g).setDuration((long) this.e).setListener(new B2.k(2, this));
                return;
            }
            throw C0212a.h(it2);
        }
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i7) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
