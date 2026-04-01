package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLayoutHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2383a;
    public final /* synthetic */ DetailsLayoutHandler b;

    public /* synthetic */ b(DetailsLayoutHandler detailsLayoutHandler, int i2) {
        this.f2383a = i2;
        this.b = detailsLayoutHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2383a;
        DetailsLayoutHandler detailsLayoutHandler = this.b;
        switch (i2) {
            case 0:
                detailsLayoutHandler.onBottomSheetStateChanged(objArr);
                return;
            case 1:
                detailsLayoutHandler.initDetailView(objArr);
                return;
            default:
                detailsLayoutHandler.onOverlayStateChanged(objArr);
                return;
        }
    }
}
