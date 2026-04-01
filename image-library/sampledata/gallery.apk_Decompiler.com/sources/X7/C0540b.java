package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: x7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0540b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2744a;
    public final /* synthetic */ ObjectCaptureHandler b;

    public /* synthetic */ C0540b(ObjectCaptureHandler objectCaptureHandler, int i2) {
        this.f2744a = i2;
        this.b = objectCaptureHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2744a;
        ObjectCaptureHandler objectCaptureHandler = this.b;
        switch (i2) {
            case 0:
                objectCaptureHandler.onToggleOsd(objArr);
                return;
            case 1:
                objectCaptureHandler.onBottomSheetSlide(objArr);
                return;
            case 2:
                objectCaptureHandler.onObjectCaptureRequest(objArr);
                return;
            case 3:
                objectCaptureHandler.onMenuClicked(objArr);
                return;
            case 4:
                objectCaptureHandler.onViewReady(objArr);
                return;
            case 5:
                objectCaptureHandler.onClipDragEnd(objArr);
                return;
            case 6:
                objectCaptureHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 7:
                objectCaptureHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 8:
                objectCaptureHandler.onConsumeEvent(objArr);
                return;
            default:
                objectCaptureHandler.disableObjectCaptureView(objArr);
                return;
        }
    }
}
