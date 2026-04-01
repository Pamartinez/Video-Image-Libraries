package androidx.fragment.app;

import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SpecialEffectsController.Operation e;
    public final /* synthetic */ DefaultSpecialEffectsController.TransitionEffect f;

    public /* synthetic */ c(SpecialEffectsController.Operation operation, DefaultSpecialEffectsController.TransitionEffect transitionEffect, int i2) {
        this.d = i2;
        this.e = operation;
        this.f = transitionEffect;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                DefaultSpecialEffectsController.TransitionEffect.onStart$lambda$6$lambda$5(this.e, this.f);
                return;
            default:
                DefaultSpecialEffectsController.TransitionEffect.onCommit$lambda$11$lambda$10(this.e, this.f);
                return;
        }
    }
}
