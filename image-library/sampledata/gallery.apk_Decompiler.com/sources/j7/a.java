package j7;

import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerFragment;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2651a;
    public final /* synthetic */ VuContainerFragment b;

    public /* synthetic */ a(VuContainerFragment vuContainerFragment, int i2) {
        this.f2651a = i2;
        this.b = vuContainerFragment;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2651a;
        VuContainerFragment vuContainerFragment = this.b;
        switch (i2) {
            case 0:
                vuContainerFragment.lambda$setActionInvoker$0(objArr);
                return;
            case 1:
                vuContainerFragment.lambda$setActionInvoker$1(objArr);
                return;
            case 2:
                vuContainerFragment.lambda$setActionInvoker$2(objArr);
                return;
            case 3:
                vuContainerFragment.lambda$setActionInvoker$3(objArr);
                return;
            case 4:
                vuContainerFragment.lambda$setActionInvoker$4(objArr);
                return;
            default:
                vuContainerFragment.lambda$setActionInvoker$5(objArr);
                return;
        }
    }
}
