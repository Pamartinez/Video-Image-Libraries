package F9;

import com.samsung.android.gallery.module.media.GalleryMediaSession;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;

    public /* synthetic */ b(int i2, long j2) {
        this.d = i2;
        this.e = j2;
    }

    public final void run() {
        GalleryMediaSession.lambda$setPlaybackState$1(this.d, this.e);
    }
}
