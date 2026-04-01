package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MirroringDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2660a;
    public final /* synthetic */ MirroringDelegate b;

    public /* synthetic */ m(MirroringDelegate mirroringDelegate, int i2) {
        this.f2660a = i2;
        this.b = mirroringDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2660a;
        MirroringDelegate mirroringDelegate = this.b;
        switch (i2) {
            case 0:
                mirroringDelegate.onBitmapLoaded(objArr);
                return;
            case 1:
                mirroringDelegate.onWindowFocusChanged(objArr);
                return;
            case 2:
                mirroringDelegate.onVideoStarted(objArr);
                return;
            default:
                mirroringDelegate.onMotionPlayViewerChanged(objArr);
                return;
        }
    }
}
