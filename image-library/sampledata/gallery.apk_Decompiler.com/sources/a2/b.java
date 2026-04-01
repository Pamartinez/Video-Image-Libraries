package A2;

import android.view.animation.Animation;
import com.google.android.material.tabs.SeslTabRoundRectIndicator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Animation.AnimationListener {
    public final /* synthetic */ SeslTabRoundRectIndicator d;

    public b(SeslTabRoundRectIndicator seslTabRoundRectIndicator) {
        this.d = seslTabRoundRectIndicator;
    }

    public final void onAnimationEnd(Animation animation) {
        this.d.d = null;
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
    }
}
