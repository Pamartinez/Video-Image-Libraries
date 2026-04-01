package S1;

import android.view.WindowInsets;
import android.view.WindowInsetsController;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements WindowInsetsController.OnControllableInsetsChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SeslImmersiveScrollBehavior f789a;

    public o(SeslImmersiveScrollBehavior seslImmersiveScrollBehavior) {
        this.f789a = seslImmersiveScrollBehavior;
    }

    public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i2) {
        SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = this.f789a;
        AppBarLayout appBarLayout = seslImmersiveScrollBehavior.f1372H;
        if (appBarLayout != null && appBarLayout.getCurrentOrientation() == 2 && !seslImmersiveScrollBehavior.D() && !seslImmersiveScrollBehavior.k0) {
            windowInsetsController.hide(WindowInsets.Type.navigationBars());
            windowInsetsController.show(WindowInsets.Type.navigationBars());
            windowInsetsController.setSystemBarsBehavior(2);
            seslImmersiveScrollBehavior.k0 = true;
        }
        if (seslImmersiveScrollBehavior.f1384f0 && i2 == 8) {
            WindowInsets rootWindowInsets = seslImmersiveScrollBehavior.L.getRootWindowInsets();
            seslImmersiveScrollBehavior.b0 = rootWindowInsets;
            if (rootWindowInsets != null && rootWindowInsets.isVisible(WindowInsets.Type.statusBars()) && seslImmersiveScrollBehavior.B()) {
                seslImmersiveScrollBehavior.F(true);
            }
        }
    }
}
