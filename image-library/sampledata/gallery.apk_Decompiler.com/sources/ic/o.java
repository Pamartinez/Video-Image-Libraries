package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewImp e;

    public /* synthetic */ o(MediaPlayerViewImp mediaPlayerViewImp, int i2) {
        this.d = i2;
        this.e = mediaPlayerViewImp;
    }

    public final void run() {
        int i2 = this.d;
        MediaPlayerViewImp mediaPlayerViewImp = this.e;
        switch (i2) {
            case 0:
                mediaPlayerViewImp.lambda$resume$7();
                return;
            case 1:
                mediaPlayerViewImp.lambda$onComputeVideo$3();
                return;
            case 2:
                mediaPlayerViewImp.lambda$disableWfdTcp$27();
                return;
            case 3:
                mediaPlayerViewImp.lambda$refreshCaptureView$14();
                return;
            case 4:
                mediaPlayerViewImp.lambda$release3dEffect$46();
                return;
            case 5:
                mediaPlayerViewImp.lambda$setColorCorrect$50();
                return;
            default:
                mediaPlayerViewImp.lambda$onVideoPlay$17();
                return;
        }
    }
}
