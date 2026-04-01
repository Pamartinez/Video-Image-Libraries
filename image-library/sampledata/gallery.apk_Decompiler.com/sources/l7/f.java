package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsLoadHandler e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ f(DetailsLoadHandler detailsLoadHandler, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = detailsLoadHandler;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$load$13(this.f);
                return;
            case 1:
                this.e.lambda$handleBlackboardEvent$8(this.f);
                return;
            case 2:
                this.e.lambda$handleBlackboardEvent$9(this.f);
                return;
            default:
                this.e.lambda$load$11(this.f);
                return;
        }
    }
}
