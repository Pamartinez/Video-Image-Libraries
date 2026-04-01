package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiProcessingViewHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiProcessingViewHandler e;

    public /* synthetic */ i(AiProcessingViewHandler aiProcessingViewHandler, int i2) {
        this.d = i2;
        this.e = aiProcessingViewHandler;
    }

    public final void run() {
        int i2 = this.d;
        AiProcessingViewHandler aiProcessingViewHandler = this.e;
        switch (i2) {
            case 0:
                aiProcessingViewHandler.hideAiProcessingEffect();
                return;
            case 1:
                aiProcessingViewHandler.lambda$onConfigurationChanged$5();
                return;
            default:
                aiProcessingViewHandler.lambda$updateEffectViewSize$8();
                return;
        }
    }
}
