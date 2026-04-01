package c4;

import android.os.Handler;
import com.samsung.android.gallery.app.service.VideoConversionService;
import com.samsung.android.gallery.module.media.VideoConversionHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Consumer;

/* renamed from: c4.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0443m implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoConversionService e;

    public /* synthetic */ C0443m(VideoConversionService videoConversionService, int i2) {
        this.d = i2;
        this.e = videoConversionService;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$unsubscribeEvents$5((Blackboard) obj);
                return;
            case 1:
                this.e.lambda$updateAndNotify$6((VideoConversionHelper) obj);
                return;
            case 2:
                this.e.onCompleted(((Boolean) obj).booleanValue());
                return;
            case 3:
                this.e.onUpdated(((Integer) obj).intValue());
                return;
            case 4:
                this.e.lambda$onTerminateService$4((Handler) obj);
                return;
            case 5:
                this.e.lambda$onStartService$3((Handler) obj);
                return;
            default:
                this.e.lambda$onInterruptService$2((Handler) obj);
                return;
        }
    }
}
