package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"androidx/fragment/app/DefaultSpecialEffectsController$AnimationEffect$onCommit$1", "Landroid/view/animation/Animation$AnimationListener;", "Landroid/view/animation/Animation;", "animation", "Lme/x;", "onAnimationStart", "(Landroid/view/animation/Animation;)V", "onAnimationEnd", "onAnimationRepeat", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSpecialEffectsController$AnimationEffect$onCommit$1 implements Animation.AnimationListener {
    final /* synthetic */ ViewGroup $container;
    final /* synthetic */ SpecialEffectsController.Operation $operation;
    final /* synthetic */ View $viewToAnimate;
    final /* synthetic */ DefaultSpecialEffectsController.AnimationEffect this$0;

    public DefaultSpecialEffectsController$AnimationEffect$onCommit$1(SpecialEffectsController.Operation operation, ViewGroup viewGroup, View view, DefaultSpecialEffectsController.AnimationEffect animationEffect) {
        this.$operation = operation;
        this.$container = viewGroup;
        this.$viewToAnimate = view;
        this.this$0 = animationEffect;
    }

    /* access modifiers changed from: private */
    public static final void onAnimationEnd$lambda$0(ViewGroup viewGroup, View view, DefaultSpecialEffectsController.AnimationEffect animationEffect) {
        j.e(viewGroup, "$container");
        j.e(animationEffect, "this$0");
        viewGroup.endViewTransition(view);
        animationEffect.getAnimationInfo().getOperation().completeEffect(animationEffect);
    }

    public void onAnimationEnd(Animation animation) {
        j.e(animation, "animation");
        ViewGroup viewGroup = this.$container;
        viewGroup.post(new a(viewGroup, this.$viewToAnimate, this.this$0, 0));
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + this.$operation + " has ended.");
        }
    }

    public void onAnimationRepeat(Animation animation) {
        j.e(animation, "animation");
    }

    public void onAnimationStart(Animation animation) {
        j.e(animation, "animation");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + this.$operation + " has reached onAnimationStart.");
        }
    }
}
