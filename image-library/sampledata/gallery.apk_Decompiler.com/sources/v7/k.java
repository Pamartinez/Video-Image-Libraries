package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DragAndDropHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2726a;
    public final /* synthetic */ DragAndDropHandler b;

    public /* synthetic */ k(DragAndDropHandler dragAndDropHandler, int i2) {
        this.f2726a = i2;
        this.b = dragAndDropHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2726a;
        DragAndDropHandler dragAndDropHandler = this.b;
        switch (i2) {
            case 0:
                dragAndDropHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            default:
                dragAndDropHandler.onObjectCaptureDone(objArr);
                return;
        }
    }
}
