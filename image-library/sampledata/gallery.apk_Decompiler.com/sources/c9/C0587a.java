package c9;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;

/* renamed from: c9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0587a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DirectorsViewCache e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ C0587a(DirectorsViewCache directorsViewCache, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = directorsViewCache;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateMediaItem$0(this.f);
                return;
            default:
                this.e.lambda$deleteFromCache$1(this.f);
                return;
        }
    }
}
