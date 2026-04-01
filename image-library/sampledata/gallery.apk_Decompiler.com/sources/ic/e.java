package ic;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements MediaPlayerCompat.OnInfoListener, MediaPlayerCompat.OnPreparedListener, MediaPlayerCompat.OnErrorListener, MediaPlayerCompat.OnCompletionListener, MediaPlayerCompat.OnVideoSizeChangedListener {
    public final /* synthetic */ MediaPlayerDelegate d;

    public /* synthetic */ e(MediaPlayerDelegate mediaPlayerDelegate) {
        this.d = mediaPlayerDelegate;
    }

    public void a(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        this.d.onVideoSizeChanged(mediaPlayerCompat, i2, i7);
    }

    public void onCompletion(MediaPlayerCompat mediaPlayerCompat) {
        this.d.onVideoCompleted(mediaPlayerCompat);
    }

    public boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return this.d.onVideoError(mediaPlayerCompat, i2, i7);
    }

    public boolean onInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return this.d.onVideoInfo(mediaPlayerCompat, i2, i7);
    }

    public void onPrepared(MediaPlayerCompat mediaPlayerCompat) {
        this.d.onVideoPrepared(mediaPlayerCompat);
    }
}
