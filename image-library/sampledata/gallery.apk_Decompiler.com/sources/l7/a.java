package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2382a;
    public final /* synthetic */ DetailsHandler b;

    public /* synthetic */ a(DetailsHandler detailsHandler, int i2) {
        this.f2382a = i2;
        this.b = detailsHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2382a;
        DetailsHandler detailsHandler = this.b;
        switch (i2) {
            case 0:
                detailsHandler.lambda$new$4(objArr);
                return;
            case 1:
                detailsHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 2:
                detailsHandler.onBottomSheetStateChanged(objArr);
                return;
            case 3:
                detailsHandler.postDetailsSaLog(objArr);
                return;
            case 4:
                detailsHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                detailsHandler.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
