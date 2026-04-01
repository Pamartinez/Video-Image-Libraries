package F7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionVideoController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2326a;
    public final /* synthetic */ MotionVideoController b;

    public /* synthetic */ e(MotionVideoController motionVideoController, int i2) {
        this.f2326a = i2;
        this.b = motionVideoController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2326a;
        MotionVideoController motionVideoController = this.b;
        switch (i2) {
            case 0:
                motionVideoController.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                motionVideoController.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
