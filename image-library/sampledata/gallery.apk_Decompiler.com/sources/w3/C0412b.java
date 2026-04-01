package W3;

import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;

/* renamed from: W3.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0412b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SaveCaptureCmd e;

    public /* synthetic */ C0412b(SaveCaptureCmd saveCaptureCmd, int i2) {
        this.d = i2;
        this.e = saveCaptureCmd;
    }

    public final void run() {
        int i2 = this.d;
        SaveCaptureCmd saveCaptureCmd = this.e;
        switch (i2) {
            case 0:
                saveCaptureCmd.lambda$onScanComplete$3();
                return;
            case 1:
                saveCaptureCmd.notifyComplete();
                return;
            case 2:
                saveCaptureCmd.lambda$sendProcessingEvent$4();
                return;
            case 3:
                saveCaptureCmd.lambda$saveCapturedFileWithBitmap$8();
                return;
            default:
                saveCaptureCmd.lambda$onUpdateQuickCropMediaItem$6();
                return;
        }
    }
}
