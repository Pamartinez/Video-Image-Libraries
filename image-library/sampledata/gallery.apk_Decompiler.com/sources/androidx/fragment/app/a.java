package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                DefaultSpecialEffectsController$AnimationEffect$onCommit$1.onAnimationEnd$lambda$0((ViewGroup) this.e, (View) this.f, (DefaultSpecialEffectsController.AnimationEffect) this.g);
                return;
            case 1:
                DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$12((SpecialEffectsController.Operation) this.e, (SpecialEffectsController.Operation) this.f, (DefaultSpecialEffectsController.TransitionEffect) this.g);
                return;
            default:
                DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$13((FragmentTransitionImpl) this.e, (View) this.f, (Rect) this.g);
                return;
        }
    }
}
