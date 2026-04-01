package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerDelegate e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    public /* synthetic */ c(MediaPlayerDelegate mediaPlayerDelegate, int i2, int i7, int i8) {
        this.d = i8;
        this.e = mediaPlayerDelegate;
        this.f = i2;
        this.g = i7;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onVideoError$7(this.f, this.g);
                return;
            default:
                this.e.lambda$setPlaybackRange$24(this.f, this.g);
                return;
        }
    }
}
