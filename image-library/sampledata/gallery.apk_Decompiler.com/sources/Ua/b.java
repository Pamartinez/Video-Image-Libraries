package Ua;

import com.samsung.android.gallery.support.library.v12.media.Sec130BgmVideoPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SemMediaPlayer.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Sec130BgmVideoPlayerCompat f2885a;

    public /* synthetic */ b(Sec130BgmVideoPlayerCompat sec130BgmVideoPlayerCompat) {
        this.f2885a = sec130BgmVideoPlayerCompat;
    }

    public final boolean onError(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        return this.f2885a.onBgmError(semMediaPlayer, i2, i7);
    }
}
