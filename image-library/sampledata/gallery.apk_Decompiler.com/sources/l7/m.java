package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsSlideHandler e;

    public /* synthetic */ m(DetailsSlideHandler detailsSlideHandler, int i2) {
        this.d = i2;
        this.e = detailsSlideHandler;
    }

    public final Object get() {
        int i2 = this.d;
        DetailsSlideHandler detailsSlideHandler = this.e;
        switch (i2) {
            case 0:
                return detailsSlideHandler.getRootViewSize();
            case 1:
                return detailsSlideHandler.getScaleBaseViewSize();
            case 2:
                return detailsSlideHandler.lambda$onInitialized$17();
            default:
                return detailsSlideHandler.getBitmapSize();
        }
    }
}
