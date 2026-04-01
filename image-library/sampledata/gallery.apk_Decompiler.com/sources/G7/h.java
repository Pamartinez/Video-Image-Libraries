package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiProcessingViewHandler;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AiProcessingViewHandler e;
    public final /* synthetic */ EventMessage f;

    public /* synthetic */ h(AiProcessingViewHandler aiProcessingViewHandler, EventMessage eventMessage, int i2) {
        this.d = i2;
        this.e = aiProcessingViewHandler;
        this.f = eventMessage;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleBlackboardEvent$3(this.f);
                return;
            default:
                this.e.lambda$handleBlackboardEvent$4(this.f);
                return;
        }
    }
}
