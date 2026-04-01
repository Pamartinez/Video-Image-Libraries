package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.EditDetailsHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2392a;
    public final /* synthetic */ EditDetailsHandler b;

    public /* synthetic */ r(EditDetailsHandler editDetailsHandler, int i2) {
        this.f2392a = i2;
        this.b = editDetailsHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2392a;
        EditDetailsHandler editDetailsHandler = this.b;
        switch (i2) {
            case 0:
                editDetailsHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                editDetailsHandler.onRequestExit(objArr);
                return;
            default:
                editDetailsHandler.lambda$addActionInvokeListener$1(objArr);
                return;
        }
    }
}
