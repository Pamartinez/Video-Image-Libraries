package U3;

import com.samsung.android.gallery.app.controller.story.SaveHideRuleCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SaveHideRuleCmd e;

    public /* synthetic */ c(SaveHideRuleCmd saveHideRuleCmd, int i2) {
        this.d = i2;
        this.e = saveHideRuleCmd;
    }

    public final void run() {
        int i2 = this.d;
        SaveHideRuleCmd saveHideRuleCmd = this.e;
        switch (i2) {
            case 0:
                saveHideRuleCmd.lambda$hideOperation$2();
                return;
            default:
                saveHideRuleCmd.lambda$showHideSceneOptionDialog$6();
                return;
        }
    }
}
