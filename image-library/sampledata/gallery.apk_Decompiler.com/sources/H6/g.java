package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.MediaPlayerViewDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewDelegate e;

    public /* synthetic */ g(MediaPlayerViewDelegate mediaPlayerViewDelegate, int i2) {
        this.d = i2;
        this.e = mediaPlayerViewDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaPlayerViewDelegate mediaPlayerViewDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                mediaPlayerViewDelegate.onEnterTransitionEnd(objArr);
                return;
            case 1:
                mediaPlayerViewDelegate.onPlayPauseButtonClicked(objArr);
                return;
            case 2:
                mediaPlayerViewDelegate.onRequestVideoSeekBegin(objArr);
                return;
            case 3:
                mediaPlayerViewDelegate.onRequestVideoSeekFinish(objArr);
                return;
            case 4:
                mediaPlayerViewDelegate.onRequestVideoSeek(objArr);
                return;
            case 5:
                mediaPlayerViewDelegate.onUserAudioMute(objArr);
                return;
            default:
                mediaPlayerViewDelegate.replayVideo(objArr);
                return;
        }
    }
}
