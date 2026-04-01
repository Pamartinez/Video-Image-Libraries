package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.MediaPlayerViewDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewDelegate e;

    public /* synthetic */ h(MediaPlayerViewDelegate mediaPlayerViewDelegate, int i2) {
        this.d = i2;
        this.e = mediaPlayerViewDelegate;
    }

    public final void run() {
        int i2 = this.d;
        MediaPlayerViewDelegate mediaPlayerViewDelegate = this.e;
        switch (i2) {
            case 0:
                mediaPlayerViewDelegate.playHideAnimation();
                return;
            default:
                mediaPlayerViewDelegate.lambda$onVideoPlay$0();
                return;
        }
    }
}
