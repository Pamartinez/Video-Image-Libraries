package z2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.ViewPropertyAnimator;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* renamed from: z2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0365d extends AnimatorListenerAdapter {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ q f;

    public /* synthetic */ C0365d(q qVar, int i2, int i7) {
        this.d = i7;
        this.f = qVar;
        this.e = i2;
    }

    public final void onAnimationEnd(Animator animator) {
        switch (this.d) {
            case 0:
                this.f.c(this.e);
                return;
            default:
                this.f.c(this.e);
                return;
        }
    }

    public void onAnimationStart(Animator animator) {
        switch (this.d) {
            case 1:
                q qVar = this.f;
                SnackbarContentLayout snackbarContentLayout = qVar.f2222j;
                int i2 = qVar.b;
                snackbarContentLayout.d.setAlpha(1.0f);
                long j2 = (long) i2;
                ViewPropertyAnimator duration = snackbarContentLayout.d.animate().alpha(0.0f).setDuration(j2);
                TimeInterpolator timeInterpolator = snackbarContentLayout.f;
                long j3 = (long) 0;
                duration.setInterpolator(timeInterpolator).setStartDelay(j3).start();
                if (snackbarContentLayout.e.getVisibility() == 0) {
                    snackbarContentLayout.e.setAlpha(1.0f);
                    snackbarContentLayout.e.animate().alpha(0.0f).setDuration(j2).setInterpolator(timeInterpolator).setStartDelay(j3).start();
                    return;
                }
                return;
            default:
                super.onAnimationStart(animator);
                return;
        }
    }
}
