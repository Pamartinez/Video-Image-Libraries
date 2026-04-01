package c4;

import com.samsung.android.gallery.app.service.MediaCaptureService;

/* renamed from: c4.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0436f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaCaptureService e;

    public /* synthetic */ C0436f(MediaCaptureService mediaCaptureService, int i2) {
        this.d = i2;
        this.e = mediaCaptureService;
    }

    public final void run() {
        int i2 = this.d;
        MediaCaptureService mediaCaptureService = this.e;
        switch (i2) {
            case 0:
                mediaCaptureService.onInterruptInternal();
                return;
            case 1:
                mediaCaptureService.onInterruptService();
                return;
            case 2:
                mediaCaptureService.onTerminateService();
                return;
            case 3:
                mediaCaptureService.stopSelf();
                return;
            case 4:
                mediaCaptureService.onMediaCapturePrepared();
                return;
            case 5:
                mediaCaptureService.lambda$startViewer$4();
                return;
            case 6:
                mediaCaptureService.onSuccess();
                return;
            default:
                mediaCaptureService.onFail();
                return;
        }
    }
}
