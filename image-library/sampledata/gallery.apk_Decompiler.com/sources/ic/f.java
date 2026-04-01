package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewImp e;
    public final /* synthetic */ int f;

    public /* synthetic */ f(MediaPlayerViewImp mediaPlayerViewImp, int i2, int i7) {
        this.d = i7;
        this.e = mediaPlayerViewImp;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setVideoFrc$48(this.f);
                return;
            case 1:
                this.e.lambda$setRenderingPosition$30(this.f);
                return;
            default:
                this.e.lambda$pauseOnReady$5(this.f);
                return;
        }
    }
}
