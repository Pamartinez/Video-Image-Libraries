package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2386a;
    public final /* synthetic */ DetailsLoadHandler b;

    public /* synthetic */ h(DetailsLoadHandler detailsLoadHandler, int i2) {
        this.f2386a = i2;
        this.b = detailsLoadHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2386a;
        DetailsLoadHandler detailsLoadHandler = this.b;
        switch (i2) {
            case 0:
                detailsLoadHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 1:
                detailsLoadHandler.onBottomSheetStateChanged(objArr);
                return;
            case 2:
                detailsLoadHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            default:
                detailsLoadHandler.lambda$addActionInvokeListener$3(objArr);
                return;
        }
    }
}
