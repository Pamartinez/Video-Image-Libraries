package c2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends AnimatorListenerAdapter {
    public final void onAnimationEnd(Animator animator) {
        View view = (View) ((p) animator).d.get();
        if (view != null) {
            view.setAlpha(1.0f);
        }
    }
}
