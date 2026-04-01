package w7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.CaptureHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: w7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0535a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2740a;
    public final /* synthetic */ CaptureHandler b;

    public /* synthetic */ C0535a(CaptureHandler captureHandler, int i2) {
        this.f2740a = i2;
        this.b = captureHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2740a;
        CaptureHandler captureHandler = this.b;
        switch (i2) {
            case 0:
                captureHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                captureHandler.lambda$addActionInvokeListener$4(objArr);
                return;
            case 2:
                captureHandler.lambda$addActionInvokeListener$5(objArr);
                return;
            case 3:
                captureHandler.lambda$addActionInvokeListener$6(objArr);
                return;
            case 4:
                captureHandler.lambda$addActionInvokeListener$7(objArr);
                return;
            case 5:
                captureHandler.lambda$addActionInvokeListener$8(objArr);
                return;
            case 6:
                captureHandler.lambda$addActionInvokeListener$9(objArr);
                return;
            case 7:
                captureHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 8:
                captureHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            case 9:
                captureHandler.lambda$addActionInvokeListener$3(objArr);
                return;
            case 10:
                captureHandler.onZoomChanged(objArr);
                return;
            case 11:
                captureHandler.onCapture(objArr);
                return;
            default:
                captureHandler.onChangePreviewControllable(objArr);
                return;
        }
    }
}
