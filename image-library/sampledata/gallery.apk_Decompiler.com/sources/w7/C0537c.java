package w7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.MotionPhotoCaptureHandler;
import java.util.function.BooleanSupplier;

/* renamed from: w7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0537c implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2741a;
    public final /* synthetic */ MotionPhotoCaptureHandler b;

    public /* synthetic */ C0537c(MotionPhotoCaptureHandler motionPhotoCaptureHandler, int i2) {
        this.f2741a = i2;
        this.b = motionPhotoCaptureHandler;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2741a;
        MotionPhotoCaptureHandler motionPhotoCaptureHandler = this.b;
        switch (i2) {
            case 0:
                return motionPhotoCaptureHandler.lambda$switchToImageCaptureInternal$0();
            default:
                return motionPhotoCaptureHandler.isSupportVideoCapture();
        }
    }
}
