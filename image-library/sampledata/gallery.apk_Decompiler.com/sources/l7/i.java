package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2387a;
    public final /* synthetic */ DetailsSlideHandler b;

    public /* synthetic */ i(DetailsSlideHandler detailsSlideHandler, int i2) {
        this.f2387a = i2;
        this.b = detailsSlideHandler;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2387a;
        DetailsSlideHandler detailsSlideHandler = this.b;
        switch (i2) {
            case 0:
                return detailsSlideHandler.lambda$onInitialized$13();
            case 1:
                return detailsSlideHandler.lambda$onInitialized$14();
            case 2:
                return detailsSlideHandler.lambda$onInitialized$15();
            default:
                return detailsSlideHandler.lambda$onInitialized$16();
        }
    }
}
