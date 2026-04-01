package Xa;

import com.samsung.android.gallery.support.library.v9.media.Sec100MediaPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SemMediaPlayer.OnInitCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Sec100MediaPlayerCompat f2901a;

    public /* synthetic */ a(Sec100MediaPlayerCompat sec100MediaPlayerCompat) {
        this.f2901a = sec100MediaPlayerCompat;
    }

    public final void onInitComplete(SemMediaPlayer semMediaPlayer, SemMediaPlayer.TrackInfo[] trackInfoArr) {
        this.f2901a.onPrepared(semMediaPlayer, trackInfoArr);
    }
}
