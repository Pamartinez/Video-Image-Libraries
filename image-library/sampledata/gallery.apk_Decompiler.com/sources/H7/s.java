package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaViewPlayerHandler e;

    public /* synthetic */ s(MediaViewPlayerHandler mediaViewPlayerHandler, int i2) {
        this.d = i2;
        this.e = mediaViewPlayerHandler;
    }

    public final void run() {
        int i2 = this.d;
        MediaViewPlayerHandler mediaViewPlayerHandler = this.e;
        switch (i2) {
            case 0:
                mediaViewPlayerHandler.lambda$onVideoCompleted$25();
                return;
            case 1:
                mediaViewPlayerHandler.hideMediaView();
                return;
            case 2:
                mediaViewPlayerHandler.lambda$isOnNoMemory$29();
                return;
            case 3:
                mediaViewPlayerHandler.lambda$handleBlackboardEvent$21();
                return;
            case 4:
                mediaViewPlayerHandler.runPreview();
                return;
            case 5:
                mediaViewPlayerHandler.lambda$onUnsupportedVideo$28();
                return;
            case 6:
                mediaViewPlayerHandler.lambda$onVideoInfo$27();
                return;
            case 7:
                mediaViewPlayerHandler.lambda$runPreview$19();
                return;
            default:
                mediaViewPlayerHandler.lambda$onVideoPlay$23();
                return;
        }
    }
}
