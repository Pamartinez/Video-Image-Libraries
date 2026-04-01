package I3;

import com.samsung.android.gallery.app.controller.creature.RemoveBackgroundEffectInfoCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemoveBackgroundEffectInfoCmd e;

    public /* synthetic */ j(RemoveBackgroundEffectInfoCmd removeBackgroundEffectInfoCmd, int i2) {
        this.d = i2;
        this.e = removeBackgroundEffectInfoCmd;
    }

    public final void run() {
        int i2 = this.d;
        RemoveBackgroundEffectInfoCmd removeBackgroundEffectInfoCmd = this.e;
        switch (i2) {
            case 0:
                removeBackgroundEffectInfoCmd.lambda$onConfirmed$1();
                return;
            default:
                removeBackgroundEffectInfoCmd.lambda$onConfirmed$0();
                return;
        }
    }
}
