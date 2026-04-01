package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCapturePopup e;

    public /* synthetic */ c(ObjectCapturePopup objectCapturePopup, int i2) {
        this.d = i2;
        this.e = objectCapturePopup;
    }

    public final void run() {
        int i2 = this.d;
        ObjectCapturePopup objectCapturePopup = this.e;
        switch (i2) {
            case 0:
                ObjectCapturePopup._init_$lambda$1(objectCapturePopup);
                return;
            default:
                ObjectCapturePopup$createOverflowAnimationListener$listener$1.onAnimationEnd$lambda$0(objectCapturePopup);
                return;
        }
    }
}
