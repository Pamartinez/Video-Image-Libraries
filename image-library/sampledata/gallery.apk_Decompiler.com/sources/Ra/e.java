package Ra;

import android.media.MediaPlayer;
import com.samsung.android.gallery.support.library.v0.media.GedMediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements MediaPlayer.OnVideoSizeChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GedMediaPlayerCompat f2873a;

    public /* synthetic */ e(GedMediaPlayerCompat gedMediaPlayerCompat) {
        this.f2873a = gedMediaPlayerCompat;
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i7) {
        this.f2873a.onVideoSizeChangedListener(mediaPlayer, i2, i7);
    }
}
