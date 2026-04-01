package G7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShotModeHandler e;

    public /* synthetic */ a(ShotModeHandler shotModeHandler, int i2) {
        this.d = i2;
        this.e = shotModeHandler;
    }

    public final void run() {
        int i2 = this.d;
        ShotModeHandler shotModeHandler = this.e;
        switch (i2) {
            case 0:
                shotModeHandler.lambda$handleBlackboardEvent$9();
                return;
            case 1:
                shotModeHandler.lambda$handleBlackboardEvent$8();
                return;
            case 2:
                shotModeHandler.lambda$executeShotMode$18();
                return;
            case 3:
                shotModeHandler.updateTouchArea();
                return;
            case 4:
                shotModeHandler.lambda$setDecorVisibilityWhenEnterVideoPlayer$20();
                return;
            default:
                shotModeHandler.lambda$onViewAttached$15();
                return;
        }
    }
}
