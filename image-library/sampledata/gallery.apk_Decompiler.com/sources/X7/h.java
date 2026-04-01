package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureMotionPhotoHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2749a;
    public final /* synthetic */ ObjectCaptureMotionPhotoHandler b;

    public /* synthetic */ h(ObjectCaptureMotionPhotoHandler objectCaptureMotionPhotoHandler, int i2) {
        this.f2749a = i2;
        this.b = objectCaptureMotionPhotoHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2749a;
        ObjectCaptureMotionPhotoHandler objectCaptureMotionPhotoHandler = this.b;
        switch (i2) {
            case 0:
                objectCaptureMotionPhotoHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                objectCaptureMotionPhotoHandler.onRequestVideoSeek(objArr);
                return;
            case 2:
                objectCaptureMotionPhotoHandler.onVideoPlayPauseClicked(objArr);
                return;
            case 3:
                objectCaptureMotionPhotoHandler.onVideoStarted(objArr);
                return;
            case 4:
                objectCaptureMotionPhotoHandler.onVideoStopped(objArr);
                return;
            case 5:
                objectCaptureMotionPhotoHandler.onMotionPlayViewerChanged(objArr);
                return;
            default:
                objectCaptureMotionPhotoHandler.lambda$addAdditionalEventListener$2(objArr);
                return;
        }
    }
}
