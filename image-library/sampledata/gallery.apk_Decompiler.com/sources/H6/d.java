package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.MediaControllerDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaControllerDelegate e;

    public /* synthetic */ d(MediaControllerDelegate mediaControllerDelegate, int i2) {
        this.d = i2;
        this.e = mediaControllerDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaControllerDelegate mediaControllerDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                mediaControllerDelegate.onVideoPlay(objArr);
                return;
            case 1:
                mediaControllerDelegate.onVideoPause(objArr);
                return;
            case 2:
                mediaControllerDelegate.onPlayTimeUpdated(objArr);
                return;
            case 3:
                mediaControllerDelegate.onRequestVideoSeek(objArr);
                return;
            default:
                mediaControllerDelegate.initializeAudioIcon(objArr);
                return;
        }
    }
}
