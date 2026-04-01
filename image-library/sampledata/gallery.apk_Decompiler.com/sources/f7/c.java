package F7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2325a;
    public final /* synthetic */ MotionPhotoViewModeHandler b;

    public /* synthetic */ c(MotionPhotoViewModeHandler motionPhotoViewModeHandler, int i2) {
        this.f2325a = i2;
        this.b = motionPhotoViewModeHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2325a;
        MotionPhotoViewModeHandler motionPhotoViewModeHandler = this.b;
        switch (i2) {
            case 0:
                motionPhotoViewModeHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                motionPhotoViewModeHandler.onViewReady(objArr);
                return;
            default:
                motionPhotoViewModeHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
