package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AwesomeIntelligenceHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AwesomeIntelligenceHandler e;

    public /* synthetic */ k(AwesomeIntelligenceHandler awesomeIntelligenceHandler, int i2) {
        this.d = i2;
        this.e = awesomeIntelligenceHandler;
    }

    public final void run() {
        int i2 = this.d;
        AwesomeIntelligenceHandler awesomeIntelligenceHandler = this.e;
        switch (i2) {
            case 0:
                awesomeIntelligenceHandler.lambda$execute$4();
                return;
            case 1:
                awesomeIntelligenceHandler.lambda$execute$5();
                return;
            default:
                awesomeIntelligenceHandler.lambda$execute$3();
                return;
        }
    }
}
