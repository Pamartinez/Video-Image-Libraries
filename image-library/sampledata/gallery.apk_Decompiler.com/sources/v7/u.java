package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.TransitionHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2735a;
    public final /* synthetic */ TransitionHandler b;

    public /* synthetic */ u(TransitionHandler transitionHandler, int i2) {
        this.f2735a = i2;
        this.b = transitionHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2735a;
        TransitionHandler transitionHandler = this.b;
        switch (i2) {
            case 0:
                transitionHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                transitionHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                transitionHandler.onBackKeyPressed(objArr);
                return;
        }
    }
}
