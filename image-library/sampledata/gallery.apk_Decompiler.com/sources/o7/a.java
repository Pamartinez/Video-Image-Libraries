package o7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.DexNavigationDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2689a;
    public final /* synthetic */ DexNavigationDelegate b;

    public /* synthetic */ a(DexNavigationDelegate dexNavigationDelegate, int i2) {
        this.f2689a = i2;
        this.b = dexNavigationDelegate;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2689a;
        DexNavigationDelegate dexNavigationDelegate = this.b;
        switch (i2) {
            case 0:
                dexNavigationDelegate.setAlpha(objArr);
                return;
            case 1:
                dexNavigationDelegate.lambda$setActionInvokeListener$0(objArr);
                return;
            default:
                dexNavigationDelegate.lambda$setActionInvokeListener$1(objArr);
                return;
        }
    }
}
