package U7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterFinishingViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2455a;
    public final /* synthetic */ RemasterFinishingViewHandler b;

    public /* synthetic */ c(RemasterFinishingViewHandler remasterFinishingViewHandler, int i2) {
        this.f2455a = i2;
        this.b = remasterFinishingViewHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2455a;
        RemasterFinishingViewHandler remasterFinishingViewHandler = this.b;
        switch (i2) {
            case 0:
                remasterFinishingViewHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                remasterFinishingViewHandler.onPreparedRemasterView(objArr);
                return;
            default:
                remasterFinishingViewHandler.onReplacedHandlerLayout(objArr);
                return;
        }
    }
}
