package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.AbsDecorBgHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* renamed from: v7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0528a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2719a;
    public final /* synthetic */ AbsDecorBgHandler b;

    public /* synthetic */ C0528a(AbsDecorBgHandler absDecorBgHandler, int i2) {
        this.f2719a = i2;
        this.b = absDecorBgHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2719a;
        AbsDecorBgHandler absDecorBgHandler = this.b;
        switch (i2) {
            case 0:
                absDecorBgHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                absDecorBgHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                absDecorBgHandler.lambda$addActionInvokeListener$2(objArr);
                return;
            default:
                absDecorBgHandler.lambda$addActionInvokeListener$3(objArr);
                return;
        }
    }
}
