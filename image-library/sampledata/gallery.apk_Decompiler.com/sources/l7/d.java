package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsListHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2384a;
    public final /* synthetic */ DetailsListHandler b;

    public /* synthetic */ d(DetailsListHandler detailsListHandler, int i2) {
        this.f2384a = i2;
        this.b = detailsListHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2384a;
        DetailsListHandler detailsListHandler = this.b;
        switch (i2) {
            case 0:
                detailsListHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                detailsListHandler.onLoadDone(objArr);
                return;
            case 2:
                detailsListHandler.onBottomSheetStateChanged(objArr);
                return;
            default:
                detailsListHandler.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
