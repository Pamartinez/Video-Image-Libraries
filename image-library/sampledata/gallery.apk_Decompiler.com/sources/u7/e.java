package U7;

import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessingViewHandler;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2456a;
    public final /* synthetic */ RemasterProcessingViewHandler b;

    public /* synthetic */ e(RemasterProcessingViewHandler remasterProcessingViewHandler, int i2) {
        this.f2456a = i2;
        this.b = remasterProcessingViewHandler;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2456a;
        RemasterProcessingViewHandler remasterProcessingViewHandler = this.b;
        switch (i2) {
            case 0:
                return remasterProcessingViewHandler.lambda$fadeOutLoadingIcon$7((ImageView) obj);
            default:
                return remasterProcessingViewHandler.lambda$showBackgroundView$5((View) obj);
        }
    }
}
