package S1;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import q2.C0268b;
import q2.o;
import q2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v implements Animator.AnimatorListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ v(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onAnimationCancel(Animator animator) {
        switch (this.d) {
        }
        j.e(animator, "animation");
    }

    public final void onAnimationEnd(Animator animator) {
        q2.v vVar;
        boolean z;
        switch (this.d) {
            case 0:
            case 1:
                j.e(animator, "animation");
                return;
            case 2:
                j.e(animator, "animation");
                u uVar = (u) this.e;
                q2.v vVar2 = q2.v.STATE_NONE;
                Iterator it = uVar.g.iterator();
                while (it.hasNext()) {
                    if (it.next() == null) {
                        float alpha = uVar.getAlpha();
                        if (alpha == 0.0f) {
                            vVar = q2.v.STATE_HIDE;
                            continue;
                        } else if (alpha == 1.0f) {
                            vVar = q2.v.STATE_SHOW;
                            continue;
                        } else {
                            vVar = q2.v.STATE_SHOW;
                            continue;
                        }
                        if (vVar != vVar2) {
                            throw null;
                        }
                    } else {
                        throw new ClassCastException();
                    }
                }
                return;
            default:
                j.e(animator, "animation");
                FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) this.e;
                Toolbar toolbar$material_release = floatingToolbarLayout.getToolbar$material_release();
                boolean z3 = false;
                if (toolbar$material_release != null) {
                    if (floatingToolbarLayout.getAlpha() == 1.0f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    toolbar$material_release.seslSetEatingHover(!z);
                }
                ActionBarContextView r = floatingToolbarLayout.getActionModeBarView();
                if (r != null) {
                    if (floatingToolbarLayout.getAlpha() == 1.0f) {
                        z3 = true;
                    }
                    r.seslSetEatingTouchOnly(!z3);
                    return;
                }
                return;
        }
    }

    public final void onAnimationRepeat(Animator animator) {
        switch (this.d) {
        }
        j.e(animator, "animation");
    }

    public final void onAnimationStart(Animator animator) {
        View view;
        switch (this.d) {
            case 0:
                j.e(animator, "animation");
                Object target = ((ObjectAnimator) this.e).getTarget();
                if (target instanceof View) {
                    view = (View) target;
                } else {
                    view = null;
                }
                if (view != null) {
                    view.setVisibility(0);
                    return;
                }
                return;
            case 1:
                j.e(animator, "animation");
                o oVar = (o) this.e;
                C0268b floatingAware$material_release = oVar.getParentFloatingLayout().getFloatingAware$material_release();
                if (oVar.getAlpha() == 0.0f) {
                    floatingAware$material_release.onStartShowFloatingBackground();
                    return;
                } else {
                    floatingAware$material_release.onStartHideFloatingBackground();
                    return;
                }
            default:
                j.e(animator, "animation");
                return;
        }
    }
}
