package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerDelegate e;
    public final /* synthetic */ int f;

    public /* synthetic */ d(MediaPlayerDelegate mediaPlayerDelegate, int i2, int i7) {
        this.d = i7;
        this.e = mediaPlayerDelegate;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$seekTo$18(this.f);
                return;
            case 1:
                this.e.lambda$visualSeekTo$22(this.f);
                return;
            case 2:
                this.e.lambda$onVideoPlay$5(this.f);
                return;
            default:
                this.e.lambda$onVideoPaused$6(this.f);
                return;
        }
    }
}
