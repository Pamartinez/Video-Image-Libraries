package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ InstantSlowMoSaveClipHandler e;
    public final /* synthetic */ EventMessage f;

    public /* synthetic */ l(InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler, EventMessage eventMessage, int i2) {
        this.d = i2;
        this.e = instantSlowMoSaveClipHandler;
        this.f = eventMessage;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleBlackboardEvent$10(this.f);
                return;
            default:
                this.e.lambda$handleBlackboardEvent$11(this.f);
                return;
        }
    }
}
