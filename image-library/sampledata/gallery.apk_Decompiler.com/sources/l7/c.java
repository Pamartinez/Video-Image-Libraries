package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ DetailsLoadResult f;

    public /* synthetic */ c(MediaItem mediaItem, DetailsLoadResult detailsLoadResult, int i2) {
        this.d = i2;
        this.e = mediaItem;
        this.f = detailsLoadResult;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((DetailsItem) obj).refineData(this.e, this.f);
                return;
            default:
                ((DetailsItem) obj).updateView(this.e, this.f);
                return;
        }
    }
}
