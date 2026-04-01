package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewOriginalVideoMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewOriginalVideoMenuItem e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ i(ViewOriginalVideoMenuItem viewOriginalVideoMenuItem, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = viewOriginalVideoMenuItem;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onMenuSelectInternal$3(this.f);
                return;
            default:
                this.e.lambda$onMenuSelectInternal$2(this.f);
                return;
        }
    }
}
