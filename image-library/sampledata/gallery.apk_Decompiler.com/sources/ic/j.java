package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewImp e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ j(MediaPlayerViewImp mediaPlayerViewImp, boolean z, int i2) {
        this.d = i2;
        this.e = mediaPlayerViewImp;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setLooping$25(this.f);
                return;
            case 1:
                this.e.lambda$close$16(this.f);
                return;
            case 2:
                this.e.lambda$setPlaybackLoop$47(this.f);
                return;
            default:
                this.e.lambda$setSupportTimeTick$26(this.f);
                return;
        }
    }
}
