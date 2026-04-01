package B7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverAudioController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2267a;
    public final /* synthetic */ FlipCoverAudioController b;

    public /* synthetic */ a(FlipCoverAudioController flipCoverAudioController, int i2) {
        this.f2267a = i2;
        this.b = flipCoverAudioController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2267a;
        FlipCoverAudioController flipCoverAudioController = this.b;
        switch (i2) {
            case 0:
                flipCoverAudioController.setAudioButton(objArr);
                return;
            case 1:
                flipCoverAudioController.onToggleSound(objArr);
                return;
            case 2:
                flipCoverAudioController.updateHasAudio(objArr);
                return;
            case 3:
                flipCoverAudioController.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                flipCoverAudioController.onAudioMuteChanged(objArr);
                return;
        }
    }
}
