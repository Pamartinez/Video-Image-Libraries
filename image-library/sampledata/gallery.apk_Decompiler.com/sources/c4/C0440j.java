package c4;

import com.samsung.android.gallery.app.service.RemasterService;

/* renamed from: c4.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0440j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemasterService e;

    public /* synthetic */ C0440j(RemasterService remasterService, int i2) {
        this.d = i2;
        this.e = remasterService;
    }

    public final void run() {
        int i2 = this.d;
        RemasterService remasterService = this.e;
        switch (i2) {
            case 0:
                remasterService.onInterruptInternal();
                return;
            default:
                remasterService.stopSelf();
                return;
        }
    }
}
