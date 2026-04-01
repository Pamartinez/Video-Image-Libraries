package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioController;

/* renamed from: H7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0398a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AudioController e;

    public /* synthetic */ C0398a(AudioController audioController, int i2) {
        this.d = i2;
        this.e = audioController;
    }

    public final void run() {
        int i2 = this.d;
        AudioController audioController = this.e;
        switch (i2) {
            case 0:
                audioController.lambda$updateHasAudio$4();
                return;
            case 1:
                audioController.lambda$setIconForSharedVideo$3();
                return;
            case 2:
                audioController.setViewHide();
                return;
            case 3:
                audioController.lambda$disableAudioIcon$6();
                return;
            default:
                audioController.updateTouchArea();
                return;
        }
    }
}
