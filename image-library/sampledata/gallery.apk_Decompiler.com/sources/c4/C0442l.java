package c4;

import com.samsung.android.gallery.app.service.StorySaveService;

/* renamed from: c4.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0442l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StorySaveService e;

    public /* synthetic */ C0442l(StorySaveService storySaveService, int i2) {
        this.d = i2;
        this.e = storySaveService;
    }

    public final void run() {
        int i2 = this.d;
        StorySaveService storySaveService = this.e;
        switch (i2) {
            case 0:
                storySaveService.stopSelf();
                return;
            default:
                storySaveService.lambda$startViewer$1();
                return;
        }
    }
}
