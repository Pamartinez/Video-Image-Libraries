package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioEraserController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: H7.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0401d implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2343a;
    public final /* synthetic */ AudioEraserController b;

    public /* synthetic */ C0401d(AudioEraserController audioEraserController, int i2) {
        this.f2343a = i2;
        this.b = audioEraserController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2343a;
        AudioEraserController audioEraserController = this.b;
        switch (i2) {
            case 0:
                audioEraserController.updateHasAudio(objArr);
                return;
            case 1:
                audioEraserController.onAudioMuteChanged(objArr);
                return;
            case 2:
                audioEraserController.onAudioEraserChanged(objArr);
                return;
            case 3:
                audioEraserController.onToggleAudioEraser(objArr);
                return;
            case 4:
                audioEraserController.onAudioEraserInitCompleted(objArr);
                return;
            case 5:
                audioEraserController.lambda$addActionInvokeListener$2(objArr);
                return;
            case 6:
                audioEraserController.onAudioIconDisabled(objArr);
                return;
            case 7:
                audioEraserController.handleInstantSlowMoPlay(objArr);
                return;
            case 8:
                audioEraserController.onVideoPrepared(objArr);
                return;
            case 9:
                audioEraserController.onVideoStarted(objArr);
                return;
            case 10:
                audioEraserController.onVideoStopped(objArr);
                return;
            default:
                audioEraserController.lambda$addActionInvokeListener$3(objArr);
                return;
        }
    }
}
