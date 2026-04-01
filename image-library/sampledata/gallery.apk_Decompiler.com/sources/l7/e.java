package L7;

import android.util.Size;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsLoadHandler e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Size g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Size f2385h;

    public /* synthetic */ e(DetailsLoadHandler detailsLoadHandler, MediaItem mediaItem, Size size, Size size2, int i2) {
        this.d = i2;
        this.e = detailsLoadHandler;
        this.f = mediaItem;
        this.g = size;
        this.f2385h = size2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$load$12(this.f, this.g, this.f2385h);
                return;
            default:
                this.e.lambda$handleBlackboardEvent$7(this.f, this.g, this.f2385h);
                return;
        }
    }
}
