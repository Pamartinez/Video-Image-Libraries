package Ra;

import android.media.MediaPlayer;
import com.samsung.android.gallery.support.library.v0.media.GedMediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements MediaPlayer.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GedMediaPlayerCompat f2870a;

    public /* synthetic */ b(GedMediaPlayerCompat gedMediaPlayerCompat) {
        this.f2870a = gedMediaPlayerCompat;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i2, int i7) {
        return this.f2870a.onError(mediaPlayer, i2, i7);
    }
}
