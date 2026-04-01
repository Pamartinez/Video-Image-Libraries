package Xa;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v9.media.Sec115MediaPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements SemMediaPlayer.OnPlaybackCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Sec115MediaPlayerCompat f2906a;
    public final /* synthetic */ MediaPlayerCompat.OnPlayBackCompleteListener b;

    public /* synthetic */ f(Sec115MediaPlayerCompat sec115MediaPlayerCompat, MediaPlayerCompat.OnPlayBackCompleteListener onPlayBackCompleteListener) {
        this.f2906a = sec115MediaPlayerCompat;
        this.b = onPlayBackCompleteListener;
    }

    public final void onPlaybackComplete(SemMediaPlayer semMediaPlayer) {
        this.f2906a.lambda$setPlaybackCompleteListener$0(this.b, semMediaPlayer);
    }
}
