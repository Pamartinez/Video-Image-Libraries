package G7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShotModeHandler e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ c(ShotModeHandler shotModeHandler, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = shotModeHandler;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$invalidate$16(this.f);
                return;
            case 1:
                this.e.lambda$updateShotModeHandler$17(this.f);
                return;
            default:
                this.e.lambda$addActionInvokeListener$4(this.f);
                return;
        }
    }
}
