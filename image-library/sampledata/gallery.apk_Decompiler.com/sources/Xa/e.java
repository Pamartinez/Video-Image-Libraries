package Xa;

import com.samsung.android.gallery.support.library.v9.media.Sec100MediaPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SemMediaPlayer.OnVideoSizeChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Sec100MediaPlayerCompat f2905a;

    public /* synthetic */ e(Sec100MediaPlayerCompat sec100MediaPlayerCompat) {
        this.f2905a = sec100MediaPlayerCompat;
    }

    public final void onVideoSizeChanged(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        this.f2905a.onVideoSizeChangedListener(semMediaPlayer, i2, i7);
    }
}
