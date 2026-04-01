package c4;

import android.os.Handler;
import com.samsung.android.gallery.app.service.RemasterService;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import java.util.function.Consumer;

/* renamed from: c4.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0439i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterService e;

    public /* synthetic */ C0439i(RemasterService remasterService, int i2) {
        this.d = i2;
        this.e = remasterService;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        RemasterService remasterService = this.e;
        switch (i2) {
            case 0:
                remasterService.lambda$onInterruptService$2((Handler) obj);
                return;
            case 1:
                remasterService.onCompleted((RemasterHelper.Result) obj);
                return;
            case 2:
                remasterService.onProgress((Double) obj);
                return;
            default:
                remasterService.lambda$onTerminateService$3((Handler) obj);
                return;
        }
    }
}
