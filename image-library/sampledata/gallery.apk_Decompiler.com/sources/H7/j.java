package H7;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ InstantSlowMoSaveClipHandler e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ j(InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = instantSlowMoSaveClipHandler;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getMediaCaptureFrame$6(this.f);
                return;
            default:
                this.e.lambda$getMediaCaptureFrame$8(this.f);
                return;
        }
    }
}
