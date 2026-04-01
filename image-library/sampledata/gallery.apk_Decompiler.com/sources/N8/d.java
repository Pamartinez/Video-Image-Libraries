package N8;

import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureHelper e;

    public /* synthetic */ d(ObjectCaptureHelper objectCaptureHelper, int i2) {
        this.d = i2;
        this.e = objectCaptureHelper;
    }

    public final void run() {
        int i2 = this.d;
        ObjectCaptureHelper objectCaptureHelper = this.e;
        switch (i2) {
            case 0:
                objectCaptureHelper.lambda$sendFakeUp$6();
                return;
            case 1:
                objectCaptureHelper.lambda$showProgressBar$8();
                return;
            default:
                objectCaptureHelper.lambda$hideProgressBar$2();
                return;
        }
    }
}
