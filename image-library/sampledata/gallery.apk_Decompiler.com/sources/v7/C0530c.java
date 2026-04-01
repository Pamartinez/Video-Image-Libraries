package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ActionModeHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: v7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0530c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2720a;
    public final /* synthetic */ ActionModeHandler b;

    public /* synthetic */ C0530c(ActionModeHandler actionModeHandler, int i2) {
        this.f2720a = i2;
        this.b = actionModeHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2720a;
        ActionModeHandler actionModeHandler = this.b;
        switch (i2) {
            case 0:
                actionModeHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                actionModeHandler.onStartActionMode(objArr);
                return;
            default:
                actionModeHandler.onFinishActionMode(objArr);
                return;
        }
    }
}
