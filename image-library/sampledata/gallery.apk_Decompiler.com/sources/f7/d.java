package F7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoViewModeHandler e;

    public /* synthetic */ d(MotionPhotoViewModeHandler motionPhotoViewModeHandler, int i2) {
        this.d = i2;
        this.e = motionPhotoViewModeHandler;
    }

    public final void run() {
        int i2 = this.d;
        MotionPhotoViewModeHandler motionPhotoViewModeHandler = this.e;
        switch (i2) {
            case 0:
                motionPhotoViewModeHandler.lambda$onPageSelected$2();
                return;
            default:
                motionPhotoViewModeHandler.lambda$onViewModeSelected$5();
                return;
        }
    }
}
