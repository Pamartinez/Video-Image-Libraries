package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureImageHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2748a;
    public final /* synthetic */ ObjectCaptureImageHandler b;

    public /* synthetic */ f(ObjectCaptureImageHandler objectCaptureImageHandler, int i2) {
        this.f2748a = i2;
        this.b = objectCaptureImageHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2748a;
        ObjectCaptureImageHandler objectCaptureImageHandler = this.b;
        switch (i2) {
            case 0:
                objectCaptureImageHandler.onCurrentItemChanged(objArr);
                return;
            case 1:
                objectCaptureImageHandler.onGIFAnimationMode(objArr);
                return;
            default:
                objectCaptureImageHandler.lambda$addActionInvokeListener$0(objArr);
                return;
        }
    }
}
