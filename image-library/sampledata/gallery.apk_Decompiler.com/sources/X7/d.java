package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureHandler e;

    public /* synthetic */ d(ObjectCaptureHandler objectCaptureHandler, int i2) {
        this.d = i2;
        this.e = objectCaptureHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ObjectCaptureHandler objectCaptureHandler = this.e;
        switch (i2) {
            case 0:
                objectCaptureHandler.postAnalyticsDetailLog((String[]) obj);
                return;
            default:
                objectCaptureHandler.requestDismissKeyGuardCallback((Runnable) obj);
                return;
        }
    }
}
