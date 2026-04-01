package androidx.core.widget;

import a6.g;
import android.content.Context;
import android.widget.ImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslGoToTopImageView extends ImageView {
    private WindowLocationProvider mWindowLocationProvider;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface WindowLocationProvider {
    }

    public SeslGoToTopImageView(Context context) {
        super(context);
    }

    public void getLocationInWindow(int[] iArr) {
        super.getLocationInWindow(iArr);
        WindowLocationProvider windowLocationProvider = this.mWindowLocationProvider;
        if (windowLocationProvider != null) {
            ((SeslGoToTopController) ((g) windowLocationProvider).e).lambda$ensureView$0(iArr);
        }
    }

    public WindowLocationProvider getWindowLocationProvider() {
        return this.mWindowLocationProvider;
    }

    public void setWindowLocationProvider(WindowLocationProvider windowLocationProvider) {
        if (this.mWindowLocationProvider != windowLocationProvider) {
            this.mWindowLocationProvider = windowLocationProvider;
        }
    }
}
