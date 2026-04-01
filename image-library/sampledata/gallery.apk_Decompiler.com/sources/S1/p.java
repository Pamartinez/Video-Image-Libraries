package S1;

import android.view.ViewTreeObserver;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ p(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onPreDraw() {
        switch (this.d) {
            case 0:
                SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = (SeslImmersiveScrollBehavior) this.e;
                seslImmersiveScrollBehavior.L.getViewTreeObserver().removeOnPreDrawListener(this);
                seslImmersiveScrollBehavior.f1377N = seslImmersiveScrollBehavior.L.findViewById(16908335);
                seslImmersiveScrollBehavior.f1378O = seslImmersiveScrollBehavior.L.findViewById(16908336);
                return false;
            default:
                ((Toolbar) this.e).getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
        }
    }
}
