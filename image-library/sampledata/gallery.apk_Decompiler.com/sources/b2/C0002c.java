package B2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: B2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0002c extends AnimatorListenerAdapter {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0003d e;

    public /* synthetic */ C0002c(C0003d dVar, int i2) {
        this.d = i2;
        this.e = dVar;
    }

    public void onAnimationEnd(Animator animator) {
        switch (this.d) {
            case 1:
                this.e.b.h(false);
                return;
            default:
                super.onAnimationEnd(animator);
                return;
        }
    }

    public void onAnimationStart(Animator animator) {
        switch (this.d) {
            case 0:
                this.e.b.h(true);
                return;
            default:
                super.onAnimationStart(animator);
                return;
        }
    }
}
