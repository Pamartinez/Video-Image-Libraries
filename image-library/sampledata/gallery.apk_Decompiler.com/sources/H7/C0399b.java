package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: H7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0399b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2342a;
    public final /* synthetic */ AudioController b;

    public /* synthetic */ C0399b(AudioController audioController, int i2) {
        this.f2342a = i2;
        this.b = audioController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2342a;
        AudioController audioController = this.b;
        switch (i2) {
            case 0:
                audioController.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                audioController.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                audioController.lambda$addActionInvokeListener$2(objArr);
                return;
            case 3:
                audioController.updateHasAudio(objArr);
                return;
            case 4:
                audioController.onAudioMuteChanged(objArr);
                return;
            case 5:
                audioController.onToggleSound(objArr);
                return;
            default:
                audioController.setIconForSharedVideo(objArr);
                return;
        }
    }
}
