package Xa;

import com.samsung.android.gallery.support.library.v9.media.Sec100MediaPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SemMediaPlayer.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Sec100MediaPlayerCompat f2902a;

    public /* synthetic */ b(Sec100MediaPlayerCompat sec100MediaPlayerCompat) {
        this.f2902a = sec100MediaPlayerCompat;
    }

    public final boolean onError(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        return this.f2902a.onError(semMediaPlayer, i2, i7);
    }
}
