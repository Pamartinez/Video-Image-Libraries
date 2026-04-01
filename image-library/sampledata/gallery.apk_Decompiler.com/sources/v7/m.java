package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ExitHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2727a;
    public final /* synthetic */ ExitHandler b;

    public /* synthetic */ m(ExitHandler exitHandler, int i2) {
        this.f2727a = i2;
        this.b = exitHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2727a;
        ExitHandler exitHandler = this.b;
        switch (i2) {
            case 0:
                exitHandler.initPhotoView(objArr);
                return;
            case 1:
                exitHandler.initMediaView(objArr);
                return;
            case 2:
                exitHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 3:
                exitHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 4:
                exitHandler.onResetExitGesture(objArr);
                return;
            case 5:
                exitHandler.handleContentViewTouch(objArr);
                return;
            default:
                exitHandler.onObjectCaptureDone(objArr);
                return;
        }
    }
}
