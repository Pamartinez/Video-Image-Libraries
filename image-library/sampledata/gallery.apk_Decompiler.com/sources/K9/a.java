package K9;

import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ NonDestructionManager e;

    public /* synthetic */ a(NonDestructionManager nonDestructionManager, int i2) {
        this.d = i2;
        this.e = nonDestructionManager;
    }

    public final void run() {
        int i2 = this.d;
        NonDestructionManager nonDestructionManager = this.e;
        switch (i2) {
            case 0:
                nonDestructionManager.lambda$new$1();
                return;
            default:
                nonDestructionManager.lambda$new$0();
                return;
        }
    }
}
