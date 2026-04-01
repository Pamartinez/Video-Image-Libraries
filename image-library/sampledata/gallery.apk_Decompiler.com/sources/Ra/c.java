package Ra;

import android.media.MediaPlayer;
import com.samsung.android.gallery.support.library.v0.media.GedMediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements MediaPlayer.OnCompletionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GedMediaPlayerCompat f2871a;

    public /* synthetic */ c(GedMediaPlayerCompat gedMediaPlayerCompat) {
        this.f2871a = gedMediaPlayerCompat;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.f2871a.onCompletion(mediaPlayer);
    }
}
