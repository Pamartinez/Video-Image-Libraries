package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DexZoomButtonUi;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2724a;
    public final /* synthetic */ DexZoomButtonUi b;

    public /* synthetic */ h(DexZoomButtonUi dexZoomButtonUi, int i2) {
        this.f2724a = i2;
        this.b = dexZoomButtonUi;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2724a;
        DexZoomButtonUi dexZoomButtonUi = this.b;
        switch (i2) {
            case 0:
                dexZoomButtonUi.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                dexZoomButtonUi.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                dexZoomButtonUi.lambda$addActionInvokeListener$2(objArr);
                return;
            case 3:
                dexZoomButtonUi.lambda$addActionInvokeListener$3(objArr);
                return;
            default:
                dexZoomButtonUi.lambda$addActionInvokeListener$4(objArr);
                return;
        }
    }
}
