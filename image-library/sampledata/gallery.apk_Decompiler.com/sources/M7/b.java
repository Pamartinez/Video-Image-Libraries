package m7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverMenuDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2668a;
    public final /* synthetic */ FlipCoverMenuDelegate b;

    public /* synthetic */ b(FlipCoverMenuDelegate flipCoverMenuDelegate, int i2) {
        this.f2668a = i2;
        this.b = flipCoverMenuDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2668a;
        FlipCoverMenuDelegate flipCoverMenuDelegate = this.b;
        switch (i2) {
            case 0:
                flipCoverMenuDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            case 1:
                flipCoverMenuDelegate.lambda$setActionInvokeListener$1(objArr);
                return;
            default:
                flipCoverMenuDelegate.lambda$setActionInvokeListener$2(objArr);
                return;
        }
    }
}
