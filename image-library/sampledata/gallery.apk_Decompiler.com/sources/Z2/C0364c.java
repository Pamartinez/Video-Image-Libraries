package z2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.ViewPropertyAnimator;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* renamed from: z2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0364c extends AnimatorListenerAdapter {
    public final /* synthetic */ int d;
    public final /* synthetic */ q e;

    public /* synthetic */ C0364c(q qVar, int i2) {
        this.d = i2;
        this.e = qVar;
    }

    public final void onAnimationEnd(Animator animator) {
        switch (this.d) {
            case 0:
                this.e.d();
                return;
            default:
                this.e.d();
                return;
        }
    }

    public void onAnimationStart(Animator animator) {
        switch (this.d) {
            case 1:
                q qVar = this.e;
                SnackbarContentLayout snackbarContentLayout = qVar.f2222j;
                int i2 = qVar.f2219c;
                int i7 = qVar.f2218a;
                int i8 = i2 - i7;
                snackbarContentLayout.d.setAlpha(0.0f);
                long j2 = (long) i7;
                ViewPropertyAnimator duration = snackbarContentLayout.d.animate().alpha(1.0f).setDuration(j2);
                TimeInterpolator timeInterpolator = snackbarContentLayout.f;
                long j3 = (long) i8;
                duration.setInterpolator(timeInterpolator).setStartDelay(j3).start();
                if (snackbarContentLayout.e.getVisibility() == 0) {
                    snackbarContentLayout.e.setAlpha(0.0f);
                    snackbarContentLayout.e.animate().alpha(1.0f).setDuration(j2).setInterpolator(timeInterpolator).setStartDelay(j3).start();
                    return;
                }
                return;
            default:
                super.onAnimationStart(animator);
                return;
        }
    }
}
