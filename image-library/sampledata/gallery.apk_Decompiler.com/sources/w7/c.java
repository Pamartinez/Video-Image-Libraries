package W7;

import com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenViewerHolder;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SingleTakenViewerHolder e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ c(SingleTakenViewerHolder singleTakenViewerHolder, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = singleTakenViewerHolder;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onItemChanged$2(this.f);
                return;
            default:
                this.e.lambda$onSubItemsUpdated$3(this.f);
                return;
        }
    }
}
