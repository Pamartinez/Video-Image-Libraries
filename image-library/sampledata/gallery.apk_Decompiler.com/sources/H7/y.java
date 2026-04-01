package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2357a;
    public final /* synthetic */ VideoController b;

    public /* synthetic */ y(VideoController videoController, int i2) {
        this.f2357a = i2;
        this.b = videoController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2357a;
        VideoController videoController = this.b;
        switch (i2) {
            case 0:
                videoController.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                videoController.setVideoControllerVisibility(objArr);
                return;
            case 2:
                videoController.onVideoStarted(objArr);
                return;
            case 3:
                videoController.onVideoStopped(objArr);
                return;
            case 4:
                videoController.onVideoError(objArr);
                return;
            case 5:
                videoController.onPlayTimeUpdated(objArr);
                return;
            case 6:
                videoController.onRequestVideoSeek(objArr);
                return;
            default:
                videoController.onVideoSpeedChangeCompleted(objArr);
                return;
        }
    }
}
