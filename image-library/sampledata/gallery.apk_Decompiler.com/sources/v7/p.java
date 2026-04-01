package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MyTagHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2730a;
    public final /* synthetic */ MyTagHandler b;

    public /* synthetic */ p(MyTagHandler myTagHandler, int i2) {
        this.f2730a = i2;
        this.b = myTagHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2730a;
        MyTagHandler myTagHandler = this.b;
        switch (i2) {
            case 0:
                myTagHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                myTagHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                myTagHandler.onBottomSheetSlide(objArr);
                return;
            default:
                myTagHandler.onBottomSheetStateChanged(objArr);
                return;
        }
    }
}
