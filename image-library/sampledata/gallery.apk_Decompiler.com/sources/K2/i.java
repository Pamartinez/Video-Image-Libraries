package k2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.animation.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends AnimatorListenerAdapter {
    public final /* synthetic */ PathInterpolator d;
    public final /* synthetic */ m e;

    public i(m mVar, PathInterpolator pathInterpolator) {
        this.e = mVar;
        this.d = pathInterpolator;
    }

    public final void onAnimationEnd(Animator animator) {
        m mVar = this.e;
        mVar.e.b();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(mVar.e, "y", new float[]{0.0f});
        ofFloat.setDuration(400);
        ofFloat.setInterpolator(this.d);
        ofFloat.start();
        super.onAnimationEnd(animator);
    }
}
