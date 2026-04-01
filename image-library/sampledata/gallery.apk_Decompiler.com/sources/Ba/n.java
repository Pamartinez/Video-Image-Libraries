package Ba;

import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoViewPlayer e;

    public /* synthetic */ n(MotionPhotoViewPlayer motionPhotoViewPlayer, int i2) {
        this.d = i2;
        this.e = motionPhotoViewPlayer;
    }

    public final void run() {
        int i2 = this.d;
        MotionPhotoViewPlayer motionPhotoViewPlayer = this.e;
        switch (i2) {
            case 0:
                motionPhotoViewPlayer.captureFrameInner();
                return;
            case 1:
                motionPhotoViewPlayer.lambda$onVideoRendered$11();
                return;
            default:
                motionPhotoViewPlayer.prepareVideo();
                return;
        }
    }
}
