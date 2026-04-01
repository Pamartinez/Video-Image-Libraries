package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ InstantSlowMoSaveClipHandler e;

    public /* synthetic */ i(InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler, int i2) {
        this.d = i2;
        this.e = instantSlowMoSaveClipHandler;
    }

    public final void run() {
        int i2 = this.d;
        InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler = this.e;
        switch (i2) {
            case 0:
                instantSlowMoSaveClipHandler.hide();
                return;
            case 1:
                instantSlowMoSaveClipHandler.lambda$getMediaCaptureFrame$9();
                return;
            default:
                instantSlowMoSaveClipHandler.lambda$onStartSaveClipView$4();
                return;
        }
    }
}
