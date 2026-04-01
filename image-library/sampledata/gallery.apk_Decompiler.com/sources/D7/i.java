package D7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.MotionPhotoImageLoader;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2305a;
    public final /* synthetic */ MotionPhotoImageLoader b;

    public /* synthetic */ i(MotionPhotoImageLoader motionPhotoImageLoader, int i2) {
        this.f2305a = i2;
        this.b = motionPhotoImageLoader;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2305a;
        MotionPhotoImageLoader motionPhotoImageLoader = this.b;
        switch (i2) {
            case 0:
                motionPhotoImageLoader.setToMotionPhotoOriginalImage(objArr);
                return;
            default:
                motionPhotoImageLoader.lambda$addActionInvokeListener$0(objArr);
                return;
        }
    }
}
