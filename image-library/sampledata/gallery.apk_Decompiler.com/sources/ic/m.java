package ic;

import com.samsung.android.gallery.widget.videoview.mediaplayer.AudioManagerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.InstantSlowMoPlayDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerListener e;

    public /* synthetic */ m(MediaPlayerListener mediaPlayerListener, int i2) {
        this.d = i2;
        this.e = mediaPlayerListener;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaPlayerListener mediaPlayerListener = this.e;
        switch (i2) {
            case 0:
                ((AudioManagerDelegate) obj).addMediaPlayerListener(mediaPlayerListener);
                return;
            case 1:
                ((InstantSlowMoPlayDelegate) obj).addMediaPlayerListener(mediaPlayerListener);
                return;
            case 2:
                ((AudioManagerDelegate) obj).removeMediaPlayerListener(mediaPlayerListener);
                return;
            default:
                ((InstantSlowMoPlayDelegate) obj).removeMediaPlayerListener(mediaPlayerListener);
                return;
        }
    }
}
