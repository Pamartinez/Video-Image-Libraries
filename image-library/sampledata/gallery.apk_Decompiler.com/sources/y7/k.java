package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionMotionPhotoHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2761a;
    public final /* synthetic */ TextExtractionMotionPhotoHandler b;

    public /* synthetic */ k(TextExtractionMotionPhotoHandler textExtractionMotionPhotoHandler, int i2) {
        this.f2761a = i2;
        this.b = textExtractionMotionPhotoHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2761a;
        TextExtractionMotionPhotoHandler textExtractionMotionPhotoHandler = this.b;
        switch (i2) {
            case 0:
                textExtractionMotionPhotoHandler.onRequestUpdateMargin(objArr);
                return;
            case 1:
                textExtractionMotionPhotoHandler.onMotionPlayViewerChanged(objArr);
                return;
            case 2:
                textExtractionMotionPhotoHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 3:
                textExtractionMotionPhotoHandler.onVideoStarted(objArr);
                return;
            case 4:
                textExtractionMotionPhotoHandler.onVideoStopped(objArr);
                return;
            case 5:
                textExtractionMotionPhotoHandler.onRequestVideoSeek(objArr);
                return;
            case 6:
                textExtractionMotionPhotoHandler.onRequestVideoSeekFinish(objArr);
                return;
            default:
                textExtractionMotionPhotoHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
