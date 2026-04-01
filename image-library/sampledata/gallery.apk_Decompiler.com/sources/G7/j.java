package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiProcessingViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2647a;
    public final /* synthetic */ AiProcessingViewHandler b;

    public /* synthetic */ j(AiProcessingViewHandler aiProcessingViewHandler, int i2) {
        this.f2647a = i2;
        this.b = aiProcessingViewHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2647a;
        AiProcessingViewHandler aiProcessingViewHandler = this.b;
        switch (i2) {
            case 0:
                aiProcessingViewHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                aiProcessingViewHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                aiProcessingViewHandler.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
