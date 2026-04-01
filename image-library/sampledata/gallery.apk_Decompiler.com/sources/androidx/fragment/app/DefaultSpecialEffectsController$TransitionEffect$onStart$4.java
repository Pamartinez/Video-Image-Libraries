package androidx.fragment.app;

import Ae.a;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.u;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lme/x;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultSpecialEffectsController$TransitionEffect$onStart$4 extends k implements a {
    final /* synthetic */ ViewGroup $container;
    final /* synthetic */ Object $mergedTransition;
    final /* synthetic */ u $seekCancelLambda;
    final /* synthetic */ DefaultSpecialEffectsController.TransitionEffect this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController$TransitionEffect$onStart$4(DefaultSpecialEffectsController.TransitionEffect transitionEffect, ViewGroup viewGroup, Object obj, u uVar) {
        super(0);
        this.this$0 = transitionEffect;
        this.$container = viewGroup;
        this.$mergedTransition = obj;
        this.$seekCancelLambda = uVar;
    }

    public final void invoke() {
        DefaultSpecialEffectsController.TransitionEffect transitionEffect = this.this$0;
        transitionEffect.setController(transitionEffect.getTransitionImpl().controlDelayedTransition(this.$container, this.$mergedTransition));
        boolean z = this.this$0.getController() != null;
        final Object obj = this.$mergedTransition;
        final ViewGroup viewGroup = this.$container;
        if (z) {
            u uVar = this.$seekCancelLambda;
            final DefaultSpecialEffectsController.TransitionEffect transitionEffect2 = this.this$0;
            uVar.d = new a() {
                /* access modifiers changed from: private */
                public static final void invoke$lambda$2(DefaultSpecialEffectsController.TransitionEffect transitionEffect, ViewGroup viewGroup) {
                    j.e(transitionEffect, "this$0");
                    j.e(viewGroup, "$container");
                    for (DefaultSpecialEffectsController.TransitionInfo operation : transitionEffect.getTransitionInfos()) {
                        SpecialEffectsController.Operation operation2 = operation.getOperation();
                        View view = operation2.getFragment().getView();
                        if (view != null) {
                            operation2.getFinalState().applyState(view, viewGroup);
                        }
                    }
                }

                /* access modifiers changed from: private */
                public static final void invoke$lambda$4(DefaultSpecialEffectsController.TransitionEffect transitionEffect) {
                    j.e(transitionEffect, "this$0");
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Transition for all operations has completed");
                    }
                    for (DefaultSpecialEffectsController.TransitionInfo operation : transitionEffect.getTransitionInfos()) {
                        operation.getOperation().completeEffect(transitionEffect);
                    }
                }

                public final void invoke() {
                    Iterable<DefaultSpecialEffectsController.TransitionInfo> transitionInfos = transitionEffect2.getTransitionInfos();
                    if (!(transitionInfos instanceof Collection) || !((Collection) transitionInfos).isEmpty()) {
                        for (DefaultSpecialEffectsController.TransitionInfo operation : transitionInfos) {
                            if (!operation.getOperation().isSeeking()) {
                                if (FragmentManager.isLoggingEnabled(2)) {
                                    Log.v("FragmentManager", "Completing animating immediately");
                                }
                                CancellationSignal cancellationSignal = new CancellationSignal();
                                transitionEffect2.getTransitionImpl().setListenerForTransitionEnd(transitionEffect2.getTransitionInfos().get(0).getOperation().getFragment(), obj, cancellationSignal, new b(2, transitionEffect2));
                                cancellationSignal.cancel();
                                return;
                            }
                        }
                    }
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animating to start");
                    }
                    FragmentTransitionImpl transitionImpl = transitionEffect2.getTransitionImpl();
                    Object controller = transitionEffect2.getController();
                    j.b(controller);
                    transitionImpl.animateToStart(controller, new d(0, transitionEffect2, viewGroup));
                }
            };
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Started executing operations from " + this.this$0.getFirstOut() + " to " + this.this$0.getLastIn());
                return;
            }
            return;
        }
        throw new IllegalStateException(("Unable to start transition " + obj + " for container " + viewGroup + '.').toString());
    }
}
