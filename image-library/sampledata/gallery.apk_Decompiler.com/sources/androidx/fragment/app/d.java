package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4;
import androidx.fragment.app.SpecialEffectsController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ d(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                DefaultSpecialEffectsController$TransitionEffect$onStart$4.AnonymousClass2.invoke$lambda$2((DefaultSpecialEffectsController.TransitionEffect) this.e, (ViewGroup) this.f);
                return;
            default:
                DefaultSpecialEffectsController.collectEffects$lambda$2((DefaultSpecialEffectsController) this.e, (SpecialEffectsController.Operation) this.f);
                return;
        }
    }
}
