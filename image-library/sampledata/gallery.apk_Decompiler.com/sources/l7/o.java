package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsStateHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2390a;
    public final /* synthetic */ DetailsStateHandler b;

    public /* synthetic */ o(DetailsStateHandler detailsStateHandler, int i2) {
        this.f2390a = i2;
        this.b = detailsStateHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2390a;
        DetailsStateHandler detailsStateHandler = this.b;
        switch (i2) {
            case 0:
                detailsStateHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                detailsStateHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                detailsStateHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            default:
                detailsStateHandler.onRequestDetailsShowOrHide(objArr);
                return;
        }
    }
}
