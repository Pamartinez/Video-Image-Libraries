package gc;

import android.graphics.Bitmap;
import android.view.PixelCopy;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements PixelCopy.OnPixelCopyFinishedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Consumer f3271a;
    public final /* synthetic */ Bitmap b;

    public /* synthetic */ f(Bitmap bitmap, Consumer consumer) {
        this.f3271a = consumer;
        this.b = bitmap;
    }

    public final void onPixelCopyFinished(int i2) {
        ViewUtils.lambda$loadSurfaceCaptureBitmap$0(this.f3271a, this.b, i2);
    }
}
