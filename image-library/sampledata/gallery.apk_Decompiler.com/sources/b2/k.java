package B2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsetsAnimationController;
import com.google.android.material.appbar.SeslImmersiveScrollBehavior;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.chip.SeslChipGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends AnimatorListenerAdapter {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ k(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onAnimationEnd(Animator animator) {
        switch (this.d) {
            case 0:
                l lVar = (l) this.e;
                lVar.p();
                lVar.r.start();
                return;
            case 1:
                super.onAnimationEnd(animator);
                SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = (SeslImmersiveScrollBehavior) this.e;
                View view = seslImmersiveScrollBehavior.f1378O;
                if (view != null) {
                    view.setTranslationY(0.0f);
                }
                WindowInsetsAnimationController windowInsetsAnimationController = seslImmersiveScrollBehavior.Y;
                if (windowInsetsAnimationController != null) {
                    windowInsetsAnimationController.finish(true);
                    return;
                }
                return;
            case 2:
                ((HideBottomViewOnScrollBehavior) this.e).k = null;
                return;
            default:
                super.onAnimationEnd(animator);
                SeslChipGroup seslChipGroup = (SeslChipGroup) this.e;
                ViewGroup.LayoutParams layoutParams = seslChipGroup.getLayoutParams();
                layoutParams.height = -2;
                seslChipGroup.q = 0;
                seslChipGroup.setLayoutParams(layoutParams);
                return;
        }
    }
}
