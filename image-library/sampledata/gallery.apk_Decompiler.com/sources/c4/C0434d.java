package c4;

import android.os.Handler;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import java.util.function.Consumer;

/* renamed from: c4.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0434d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HighlightEncodeService e;

    public /* synthetic */ C0434d(HighlightEncodeService highlightEncodeService, int i2) {
        this.d = i2;
        this.e = highlightEncodeService;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HighlightEncodeService highlightEncodeService = this.e;
        switch (i2) {
            case 0:
                highlightEncodeService.lambda$onStartService$2((Boolean) obj);
                return;
            default:
                highlightEncodeService.lambda$onTerminateService$1((Handler) obj);
                return;
        }
    }
}
