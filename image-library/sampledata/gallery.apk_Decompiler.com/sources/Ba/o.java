package Ba;

import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements MediaPlayerCompat.OnInfoListener, MediaPlayerCompat.OnPreparedListener, MediaPlayerCompat.OnErrorListener, MediaPlayerCompat.OnCompletionListener {
    public final /* synthetic */ MotionPhotoViewPlayer d;

    public /* synthetic */ o(MotionPhotoViewPlayer motionPhotoViewPlayer) {
        this.d = motionPhotoViewPlayer;
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
