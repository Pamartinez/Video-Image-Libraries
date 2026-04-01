package F7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoMediaPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoMediaPlayer e;

    public /* synthetic */ a(MotionPhotoMediaPlayer motionPhotoMediaPlayer, int i2) {
        this.d = i2;
        this.e = motionPhotoMediaPlayer;
    }

    public final void run() {
        int i2 = this.d;
        MotionPhotoMediaPlayer motionPhotoMediaPlayer = this.e;
        switch (i2) {
            case 0:
                motionPhotoMediaPlayer.lambda$setMotionPhotoMode$1();
                return;
            case 1:
                motionPhotoMediaPlayer.hideMediaView();
                return;
            case 2:
                motionPhotoMediaPlayer.lambda$onVideoCompleted$5();
                return;
            case 3:
                motionPhotoMediaPlayer.lambda$onVideoPlay$4();
                return;
            case 4:
                motionPhotoMediaPlayer.lambda$playMotionPhotoVideo$2();
                return;
            default:
                motionPhotoMediaPlayer.lambda$playMotionPhotoVideo$3();
                return;
        }
    }
}
