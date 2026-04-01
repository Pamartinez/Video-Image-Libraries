package S1;

import B0.a;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.FrameLayout;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements Animator.AnimatorListener {
    public final /* synthetic */ ObjectAnimator d;
    public final /* synthetic */ a e;

    public u(ObjectAnimator objectAnimator, a aVar) {
        this.d = objectAnimator;
        this.e = aVar;
    }

    public final void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
    }

    public final void onAnimationEnd(Animator animator) {
        View view;
        j.e(animator, "animation");
        Object target = this.d.getTarget();
        if (target instanceof View) {
            view = (View) target;
        } else {
            view = null;
        }
        if (view != null) {
            ((FrameLayout) this.e.d).removeView(view);
        }
    }

    public final void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
    }

    public final void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
    }
}
