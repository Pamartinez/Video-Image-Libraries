package B7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverVideoShotModeHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FlipCoverVideoShotModeHandler e;

    public /* synthetic */ f(FlipCoverVideoShotModeHandler flipCoverVideoShotModeHandler, int i2) {
        this.d = i2;
        this.e = flipCoverVideoShotModeHandler;
    }

    public final void run() {
        int i2 = this.d;
        FlipCoverVideoShotModeHandler flipCoverVideoShotModeHandler = this.e;
        switch (i2) {
            case 0:
                flipCoverVideoShotModeHandler.lambda$onViewAttached$0();
                return;
            default:
                flipCoverVideoShotModeHandler.lambda$updateShotModeHandler$2();
                return;
        }
    }
}
