package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoController e;

    public /* synthetic */ z(VideoController videoController, int i2) {
        this.d = i2;
        this.e = videoController;
    }

    public final void run() {
        int i2 = this.d;
        VideoController videoController = this.e;
        switch (i2) {
            case 0:
                videoController.lambda$onVideoStopped$2();
                return;
            case 1:
                videoController.lambda$onVideoError$3();
                return;
            default:
                videoController.updateTouchArea();
                return;
        }
    }
}
