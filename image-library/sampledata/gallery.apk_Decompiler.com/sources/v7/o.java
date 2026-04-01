package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MotionPhotoPlayViewerStateHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2729a;
    public final /* synthetic */ MotionPhotoPlayViewerStateHandler b;

    public /* synthetic */ o(MotionPhotoPlayViewerStateHandler motionPhotoPlayViewerStateHandler, int i2) {
        this.f2729a = i2;
        this.b = motionPhotoPlayViewerStateHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2729a;
        MotionPhotoPlayViewerStateHandler motionPhotoPlayViewerStateHandler = this.b;
        switch (i2) {
            case 0:
                motionPhotoPlayViewerStateHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                motionPhotoPlayViewerStateHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                motionPhotoPlayViewerStateHandler.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
