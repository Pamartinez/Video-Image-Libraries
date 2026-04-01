package L7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsLoadHandler e;

    public /* synthetic */ g(DetailsLoadHandler detailsLoadHandler, int i2) {
        this.d = i2;
        this.e = detailsLoadHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        DetailsLoadHandler detailsLoadHandler = this.e;
        switch (i2) {
            case 0:
                detailsLoadHandler.lambda$onViewRecycled$6((View) obj);
                return;
            case 1:
                detailsLoadHandler.lambda$preLoadMapInfos$14((View) obj);
                return;
            default:
                detailsLoadHandler.lambda$addActionInvokeListener$0((MediaItem) obj);
                return;
        }
    }
}
