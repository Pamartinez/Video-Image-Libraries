package S7;

import android.util.Size;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterViewerHolder e;
    public final /* synthetic */ Size f;

    public /* synthetic */ h(RemasterViewerHolder remasterViewerHolder, Size size, int i2) {
        this.d = i2;
        this.e = remasterViewerHolder;
        this.f = size;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$updateSize$2(this.f);
                return;
            default:
                this.e.lambda$updateSize$3(this.f);
                return;
        }
    }
}
