package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureProcessingHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2750a;
    public final /* synthetic */ ObjectCaptureProcessingHandler b;

    public /* synthetic */ j(ObjectCaptureProcessingHandler objectCaptureProcessingHandler, int i2) {
        this.f2750a = i2;
        this.b = objectCaptureProcessingHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2750a;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler = this.b;
        switch (i2) {
            case 0:
                objectCaptureProcessingHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                objectCaptureProcessingHandler.onProgressEnd(objArr);
                return;
            case 2:
                objectCaptureProcessingHandler.onProgressStart(objArr);
                return;
            case 3:
                objectCaptureProcessingHandler.onViewReady(objArr);
                return;
            default:
                objectCaptureProcessingHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
