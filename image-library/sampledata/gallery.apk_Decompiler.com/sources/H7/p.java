package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoTipHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2350a;
    public final /* synthetic */ InstantSlowMoTipHandler b;

    public /* synthetic */ p(InstantSlowMoTipHandler instantSlowMoTipHandler, int i2) {
        this.f2350a = i2;
        this.b = instantSlowMoTipHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2350a;
        InstantSlowMoTipHandler instantSlowMoTipHandler = this.b;
        switch (i2) {
            case 0:
                instantSlowMoTipHandler.onVideoStarted(objArr);
                return;
            default:
                instantSlowMoTipHandler.handleInstantSlowMoPlay(objArr);
                return;
        }
    }
}
