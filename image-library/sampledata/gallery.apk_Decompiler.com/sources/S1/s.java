package S1;

import android.animation.ValueAnimator;
import android.util.Log;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int[] d;
    public final /* synthetic */ CoordinatorLayout e;
    public final /* synthetic */ AppBarLayout f;
    public final /* synthetic */ SeslImmersiveScrollBehavior g;

    public s(SeslImmersiveScrollBehavior seslImmersiveScrollBehavior, int[] iArr, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        this.g = seslImmersiveScrollBehavior;
        this.d = iArr;
        this.e = coordinatorLayout;
        this.f = appBarLayout;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = this.g;
        if (seslImmersiveScrollBehavior.f1379P == null) {
            Log.e("SeslImmersiveScrollBehavior", "mTargetView is null");
            return;
        }
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        int i2 = seslImmersiveScrollBehavior.f1385g0 - intValue;
        this.d[0] = i2;
        seslImmersiveScrollBehavior.f1379P.scrollBy(0, -i2);
        seslImmersiveScrollBehavior.g(this.e, this.f, intValue);
        seslImmersiveScrollBehavior.f1385g0 = intValue;
    }
}
