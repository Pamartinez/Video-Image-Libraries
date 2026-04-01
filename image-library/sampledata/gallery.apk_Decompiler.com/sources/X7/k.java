package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureVideoHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2751a;
    public final /* synthetic */ ObjectCaptureVideoHandler b;

    public /* synthetic */ k(ObjectCaptureVideoHandler objectCaptureVideoHandler, int i2) {
        this.f2751a = i2;
        this.b = objectCaptureVideoHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2751a;
        ObjectCaptureVideoHandler objectCaptureVideoHandler = this.b;
        switch (i2) {
            case 0:
                objectCaptureVideoHandler.onVideoPlayPauseClicked(objArr);
                return;
            case 1:
                objectCaptureVideoHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 2:
                objectCaptureVideoHandler.onRequestVideoSeek(objArr);
                return;
            case 3:
                objectCaptureVideoHandler.onVideoStarted(objArr);
                return;
            case 4:
                objectCaptureVideoHandler.onVideoStopped(objArr);
                return;
            case 5:
                objectCaptureVideoHandler.lambda$addAdditionalEventListener$1(objArr);
                return;
            case 6:
                objectCaptureVideoHandler.lambda$addAdditionalEventListener$2(objArr);
                return;
            default:
                objectCaptureVideoHandler.lambda$addAdditionalEventListener$3(objArr);
                return;
        }
    }
}
