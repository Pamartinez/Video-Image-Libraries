package S7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.DivideHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2426a;
    public final /* synthetic */ DivideHandler b;

    public /* synthetic */ a(DivideHandler divideHandler, int i2) {
        this.f2426a = i2;
        this.b = divideHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2426a;
        DivideHandler divideHandler = this.b;
        switch (i2) {
            case 0:
                divideHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                divideHandler.onZoomed(objArr);
                return;
            case 2:
                divideHandler.separateTo(objArr);
                return;
            case 3:
                divideHandler.expandHandlerTouchArea(objArr);
                return;
            case 4:
                divideHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 5:
                divideHandler.onReplacedHandlerLayout(objArr);
                return;
            default:
                divideHandler.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
