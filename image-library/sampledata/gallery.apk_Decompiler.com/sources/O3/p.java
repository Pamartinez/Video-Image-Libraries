package O3;

import com.samsung.android.gallery.app.controller.internals.LongExposureCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LongExposureCmd e;

    public /* synthetic */ p(LongExposureCmd longExposureCmd, int i2) {
        this.d = i2;
        this.e = longExposureCmd;
    }

    public final void run() {
        int i2 = this.d;
        LongExposureCmd longExposureCmd = this.e;
        switch (i2) {
            case 0:
                longExposureCmd.onCancel();
                return;
            default:
                longExposureCmd.lambda$startLongExposure$0();
                return;
        }
    }
}
