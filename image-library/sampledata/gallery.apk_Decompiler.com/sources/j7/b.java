package j7;

import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerPresenter;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2652a;
    public final /* synthetic */ VuContainerPresenter b;

    public /* synthetic */ b(VuContainerPresenter vuContainerPresenter, int i2) {
        this.f2652a = i2;
        this.b = vuContainerPresenter;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2652a;
        VuContainerPresenter vuContainerPresenter = this.b;
        switch (i2) {
            case 0:
                vuContainerPresenter.onBackKeyEvent(objArr);
                return;
            case 1:
                vuContainerPresenter.onToggleOsd(objArr);
                return;
            case 2:
                vuContainerPresenter.lambda$setActionInvoker$2(objArr);
                return;
            case 3:
                vuContainerPresenter.lambda$setActionInvoker$3(objArr);
                return;
            case 4:
                vuContainerPresenter.lambda$setActionInvoker$4(objArr);
                return;
            case 5:
                vuContainerPresenter.lambda$setActionInvoker$5(objArr);
                return;
            default:
                vuContainerPresenter.lambda$setActionInvoker$6(objArr);
                return;
        }
    }
}
