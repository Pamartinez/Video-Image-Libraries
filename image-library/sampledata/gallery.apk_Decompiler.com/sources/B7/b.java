package B7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverAudioController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FlipCoverAudioController e;

    public /* synthetic */ b(FlipCoverAudioController flipCoverAudioController, int i2) {
        this.d = i2;
        this.e = flipCoverAudioController;
    }

    public final void run() {
        int i2 = this.d;
        FlipCoverAudioController flipCoverAudioController = this.e;
        switch (i2) {
            case 0:
                flipCoverAudioController.lambda$disableAudioIcon$3();
                return;
            default:
                flipCoverAudioController.lambda$updateHasAudio$2();
                return;
        }
    }
}
