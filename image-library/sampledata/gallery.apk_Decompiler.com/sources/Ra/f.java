package Ra;

import android.media.MediaPlayer;
import com.samsung.android.gallery.support.library.v0.media.SemMediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements MediaPlayer.OnSeekCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SemMediaPlayerCompat f2874a;

    public /* synthetic */ f(SemMediaPlayerCompat semMediaPlayerCompat) {
        this.f2874a = semMediaPlayerCompat;
    }

    public final void onSeekComplete(MediaPlayer mediaPlayer) {
        this.f2874a.lambda$completeSeekTo$0(mediaPlayer);
    }
}
