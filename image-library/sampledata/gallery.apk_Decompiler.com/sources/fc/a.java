package fc;

import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.gallery.widget.transition.StoryRadiusTransition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ StoryRadiusTransition d;
    public final /* synthetic */ View e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ a(StoryRadiusTransition storyRadiusTransition, View view, boolean z, boolean z3) {
        this.d = storyRadiusTransition;
        this.e = view;
        this.f = z;
        this.g = z3;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$createRadiusAnimator$0(this.e, this.f, this.g, valueAnimator);
    }
}
