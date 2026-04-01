package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DlnaUi2;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2725a;
    public final /* synthetic */ DlnaUi2 b;

    public /* synthetic */ j(DlnaUi2 dlnaUi2, int i2) {
        this.f2725a = i2;
        this.b = dlnaUi2;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2725a;
        DlnaUi2 dlnaUi2 = this.b;
        switch (i2) {
            case 0:
                dlnaUi2.onDlnaButtonView(objArr);
                return;
            default:
                dlnaUi2.updateDlnaButton(objArr);
                return;
        }
    }
}
