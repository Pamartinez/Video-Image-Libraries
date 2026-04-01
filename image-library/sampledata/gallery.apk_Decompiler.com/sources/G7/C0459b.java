package g7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: g7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0459b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2642a;
    public final /* synthetic */ AiEditHandler b;

    public /* synthetic */ C0459b(AiEditHandler aiEditHandler, int i2) {
        this.f2642a = i2;
        this.b = aiEditHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2642a;
        AiEditHandler aiEditHandler = this.b;
        switch (i2) {
            case 0:
                aiEditHandler.executeAfterDetailsCollapsed(objArr);
                return;
            case 1:
                aiEditHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            case 2:
                aiEditHandler.onRequestAiEditDirectly(objArr);
                return;
            case 3:
                aiEditHandler.onOverlayStateChanged(objArr);
                return;
            case 4:
                aiEditHandler.lambda$addActionInvokeListener$4(objArr);
                return;
            case 5:
                aiEditHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 6:
                aiEditHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 7:
                aiEditHandler.onBottomSheetStateChanged(objArr);
                return;
            case 8:
                aiEditHandler.onBottomSheetSlide(objArr);
                return;
            default:
                aiEditHandler.onRemoveTimeLapseEffect(objArr);
                return;
        }
    }
}
