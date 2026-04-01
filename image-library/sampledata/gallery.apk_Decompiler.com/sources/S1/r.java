package S1;

import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends WindowInsetsAnimation.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SeslImmersiveScrollBehavior f791a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r(SeslImmersiveScrollBehavior seslImmersiveScrollBehavior) {
        super(1);
        this.f791a = seslImmersiveScrollBehavior;
    }

    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        r.super.onEnd(windowInsetsAnimation);
        SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = this.f791a;
        if (seslImmersiveScrollBehavior.f1376M != null && !seslImmersiveScrollBehavior.f1372H.isDetachedState()) {
            seslImmersiveScrollBehavior.b0 = seslImmersiveScrollBehavior.f1376M.getRootWindowInsets();
            WindowInsets windowInsets = seslImmersiveScrollBehavior.b0;
            if (windowInsets != null) {
                seslImmersiveScrollBehavior.f1376M.dispatchApplyWindowInsets(windowInsets);
            }
        }
    }

    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
        return windowInsets;
    }
}
