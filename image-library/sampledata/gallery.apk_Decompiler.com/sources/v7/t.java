package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.TableModeHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2734a;
    public final /* synthetic */ TableModeHandler b;

    public /* synthetic */ t(TableModeHandler tableModeHandler, int i2) {
        this.f2734a = i2;
        this.b = tableModeHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2734a;
        TableModeHandler tableModeHandler = this.b;
        switch (i2) {
            case 0:
                tableModeHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                tableModeHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                tableModeHandler.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
