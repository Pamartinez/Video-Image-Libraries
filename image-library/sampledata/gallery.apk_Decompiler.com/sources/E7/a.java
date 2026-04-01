package E7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoController;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2320a;
    public final /* synthetic */ LiveEffectVideoController b;

    public /* synthetic */ a(LiveEffectVideoController liveEffectVideoController, int i2) {
        this.f2320a = i2;
        this.b = liveEffectVideoController;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2320a;
        LiveEffectVideoController liveEffectVideoController = this.b;
        switch (i2) {
            case 0:
                liveEffectVideoController.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                liveEffectVideoController.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
