package s7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: s7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0515a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2706a;
    public final /* synthetic */ TransitionDelegate b;

    public /* synthetic */ C0515a(TransitionDelegate transitionDelegate, int i2) {
        this.f2706a = i2;
        this.b = transitionDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2706a;
        TransitionDelegate transitionDelegate = this.b;
        switch (i2) {
            case 0:
                transitionDelegate.startSelectionViewVi(objArr);
                return;
            case 1:
                transitionDelegate.startRemasterViewVi(objArr);
                return;
            case 2:
                transitionDelegate.startGroupPanelVI(objArr);
                return;
            case 3:
                transitionDelegate.prepareReturnTransition(objArr);
                return;
            default:
                transitionDelegate.onBitmapLoaded(objArr);
                return;
        }
    }
}
