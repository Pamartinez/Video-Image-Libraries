package androidx.fragment.app;

import androidx.fragment.app.SpecialEffectsController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SpecialEffectsController e;
    public final /* synthetic */ SpecialEffectsController.FragmentStateManagerOperation f;

    public /* synthetic */ k(SpecialEffectsController specialEffectsController, SpecialEffectsController.FragmentStateManagerOperation fragmentStateManagerOperation, int i2) {
        this.d = i2;
        this.e = specialEffectsController;
        this.f = fragmentStateManagerOperation;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SpecialEffectsController.enqueue$lambda$4$lambda$2(this.e, this.f);
                return;
            default:
                SpecialEffectsController.enqueue$lambda$4$lambda$3(this.e, this.f);
                return;
        }
    }
}
