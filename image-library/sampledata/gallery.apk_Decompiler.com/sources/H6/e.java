package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.MediaControllerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaControllerDelegate e;

    public /* synthetic */ e(MediaControllerDelegate mediaControllerDelegate, int i2) {
        this.d = i2;
        this.e = mediaControllerDelegate;
    }

    public final void run() {
        int i2 = this.d;
        MediaControllerDelegate mediaControllerDelegate = this.e;
        switch (i2) {
            case 0:
                mediaControllerDelegate.lambda$onVideoPlay$0();
                return;
            default:
                mediaControllerDelegate.lambda$onVideoPause$1();
                return;
        }
    }
}
