package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoPlayViHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2347a;
    public final /* synthetic */ InstantSlowMoPlayViHandler b;

    public /* synthetic */ h(InstantSlowMoPlayViHandler instantSlowMoPlayViHandler, int i2) {
        this.f2347a = i2;
        this.b = instantSlowMoPlayViHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2347a;
        InstantSlowMoPlayViHandler instantSlowMoPlayViHandler = this.b;
        switch (i2) {
            case 0:
                instantSlowMoPlayViHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                instantSlowMoPlayViHandler.handleInstantSlowMoPlayVi(objArr);
                return;
            default:
                instantSlowMoPlayViHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
