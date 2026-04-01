package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2348a;
    public final /* synthetic */ InstantSlowMoSaveClipHandler b;

    public /* synthetic */ k(InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler, int i2) {
        this.f2348a = i2;
        this.b = instantSlowMoSaveClipHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2348a;
        InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler = this.b;
        switch (i2) {
            case 0:
                instantSlowMoSaveClipHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                instantSlowMoSaveClipHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                instantSlowMoSaveClipHandler.onStartSaveClipView(objArr);
                return;
            case 3:
                instantSlowMoSaveClipHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            default:
                instantSlowMoSaveClipHandler.lambda$addActionInvokeListener$3(objArr);
                return;
        }
    }
}
