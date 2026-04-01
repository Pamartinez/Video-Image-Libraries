package c4;

import android.os.Handler;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Consumer;

/* renamed from: c4.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0437g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaCaptureService e;

    public /* synthetic */ C0437g(MediaCaptureService mediaCaptureService, int i2) {
        this.d = i2;
        this.e = mediaCaptureService;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$handlePreparedListenerCallback$1((Handler) obj);
                return;
            case 1:
                this.e.lambda$startViewer$3((QueryParams) obj);
                return;
            case 2:
                this.e.lambda$onMediaCaptureError$2((Handler) obj);
                return;
            case 3:
                this.e.onCompleted(((Boolean) obj).booleanValue());
                return;
            case 4:
                this.e.onUpdated(((Integer) obj).intValue());
                return;
            case 5:
                this.e.lambda$onInterruptService$9((Handler) obj);
                return;
            case 6:
                this.e.lambda$onTerminateService$11((Handler) obj);
                return;
            default:
                this.e.lambda$unsubscribeEvents$12((Blackboard) obj);
                return;
        }
    }
}
