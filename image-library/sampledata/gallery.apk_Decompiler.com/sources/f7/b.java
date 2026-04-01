package F7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoMediaPlayer;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2324a;
    public final /* synthetic */ MotionPhotoMediaPlayer b;

    public /* synthetic */ b(MotionPhotoMediaPlayer motionPhotoMediaPlayer, int i2) {
        this.f2324a = i2;
        this.b = motionPhotoMediaPlayer;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2324a;
        MotionPhotoMediaPlayer motionPhotoMediaPlayer = this.b;
        switch (i2) {
            case 0:
                motionPhotoMediaPlayer.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                motionPhotoMediaPlayer.onMotionPlayViewerChanged(objArr);
                return;
            default:
                motionPhotoMediaPlayer.onImageLoaded(objArr);
                return;
        }
    }
}
