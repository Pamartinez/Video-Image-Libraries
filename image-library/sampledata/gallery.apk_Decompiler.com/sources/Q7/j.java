package Q7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewerMenuItem e;
    public final /* synthetic */ View f;

    public /* synthetic */ j(ViewerMenuItem viewerMenuItem, View view, int i2) {
        this.d = i2;
        this.e = viewerMenuItem;
        this.f = view;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onMenuSelect$24(this.f);
                return;
            default:
                this.e.lambda$onMenuSelectAfterRotateRecovery$25(this.f);
                return;
        }
    }
}
