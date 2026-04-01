package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ShrinkToCameraHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2733a;
    public final /* synthetic */ ShrinkToCameraHandler b;

    public /* synthetic */ s(ShrinkToCameraHandler shrinkToCameraHandler, int i2) {
        this.f2733a = i2;
        this.b = shrinkToCameraHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2733a;
        ShrinkToCameraHandler shrinkToCameraHandler = this.b;
        switch (i2) {
            case 0:
                shrinkToCameraHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                shrinkToCameraHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                shrinkToCameraHandler.onHideMainView(objArr);
                return;
            default:
                shrinkToCameraHandler.onPublishTransitionInfo(objArr);
                return;
        }
    }
}
