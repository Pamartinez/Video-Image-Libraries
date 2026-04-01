package G7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShotModeHandler e;

    public /* synthetic */ d(ShotModeHandler shotModeHandler, int i2) {
        this.d = i2;
        this.e = shotModeHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ShotModeHandler shotModeHandler = this.e;
        switch (i2) {
            case 0:
                shotModeHandler.lambda$addActionInvokeListener$5((MediaItem) obj);
                return;
            default:
                shotModeHandler.downloadCompleted(obj);
                return;
        }
    }
}
