package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"androidx/fragment/app/DefaultSpecialEffectsController$AnimatorEffect$onStart$2", "Landroid/animation/AnimatorListenerAdapter;", "Landroid/animation/Animator;", "anim", "Lme/x;", "onAnimationEnd", "(Landroid/animation/Animator;)V", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSpecialEffectsController$AnimatorEffect$onStart$2 extends AnimatorListenerAdapter {
    final /* synthetic */ ViewGroup $container;
    final /* synthetic */ boolean $isHideOperation;
    final /* synthetic */ SpecialEffectsController.Operation $operation;
    final /* synthetic */ View $viewToAnimate;
    final /* synthetic */ DefaultSpecialEffectsController.AnimatorEffect this$0;

    public DefaultSpecialEffectsController$AnimatorEffect$onStart$2(ViewGroup viewGroup, View view, boolean z, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController.AnimatorEffect animatorEffect) {
        this.$container = viewGroup;
        this.$viewToAnimate = view;
        this.$isHideOperation = z;
        this.$operation = operation;
        this.this$0 = animatorEffect;
    }

    public void onAnimationEnd(Animator animator) {
        j.e(animator, "anim");
        this.$container.endViewTransition(this.$viewToAnimate);
        if (this.$isHideOperation) {
            SpecialEffectsController.Operation.State finalState = this.$operation.getFinalState();
            View view = this.$viewToAnimate;
            j.d(view, "viewToAnimate");
            finalState.applyState(view, this.$container);
        }
        this.this$0.getAnimatorInfo().getOperation().completeEffect(this.this$0);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animator from operation " + this.$operation + " has ended.");
        }
    }
}
