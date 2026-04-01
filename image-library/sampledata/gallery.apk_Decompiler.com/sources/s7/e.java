package S7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2429a;
    public final /* synthetic */ RemasterLayoutHandler b;

    public /* synthetic */ e(RemasterLayoutHandler remasterLayoutHandler, int i2) {
        this.f2429a = i2;
        this.b = remasterLayoutHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2429a;
        RemasterLayoutHandler remasterLayoutHandler = this.b;
        switch (i2) {
            case 0:
                remasterLayoutHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                remasterLayoutHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                remasterLayoutHandler.onRemastered(objArr);
                return;
            default:
                remasterLayoutHandler.onLoadedFocusData(objArr);
                return;
        }
    }
}
