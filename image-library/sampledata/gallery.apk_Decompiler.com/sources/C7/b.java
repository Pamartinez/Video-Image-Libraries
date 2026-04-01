package C7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupCountUi;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2295a;
    public final /* synthetic */ GroupCountUi b;

    public /* synthetic */ b(GroupCountUi groupCountUi, int i2) {
        this.f2295a = i2;
        this.b = groupCountUi;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2295a;
        GroupCountUi groupCountUi = this.b;
        switch (i2) {
            case 0:
                groupCountUi.onBindView(objArr);
                return;
            default:
                groupCountUi.setCountViewText(objArr);
                return;
        }
    }
}
