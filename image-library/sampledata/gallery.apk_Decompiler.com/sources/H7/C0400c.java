package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioEraserController;

/* renamed from: H7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0400c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AudioEraserController e;

    public /* synthetic */ C0400c(AudioEraserController audioEraserController, int i2) {
        this.d = i2;
        this.e = audioEraserController;
    }

    public final void run() {
        int i2 = this.d;
        AudioEraserController audioEraserController = this.e;
        switch (i2) {
            case 0:
                audioEraserController.updateTouchArea();
                return;
            case 1:
                audioEraserController.lambda$onResolutionChanged$5();
                return;
            case 2:
                audioEraserController.lambda$hideAudioIcon$8();
                return;
            case 3:
                audioEraserController.lambda$onVideoStopped$10();
                return;
            case 4:
                audioEraserController.lambda$hideEraserButton$7();
                return;
            case 5:
                audioEraserController.lambda$onAudioEraserInitCompleted$11();
                return;
            case 6:
                audioEraserController.lambda$updateHasAudio$6();
                return;
            case 7:
                audioEraserController.lambda$onVideoStarted$9();
                return;
            case 8:
                audioEraserController.lambda$initAudioEraserButton$12();
                return;
            case 9:
                audioEraserController.lambda$initAudioEraserButton$13();
                return;
            default:
                audioEraserController.lambda$handleBlackboardEvent$4();
                return;
        }
    }
}
