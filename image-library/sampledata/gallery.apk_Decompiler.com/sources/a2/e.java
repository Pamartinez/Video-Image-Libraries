package A2;

import android.animation.ValueAnimator;
import android.widget.TextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ e(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                ((TabLayout) this.e).scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
                return;
            case 1:
                ((TextInputLayout) this.e).y0.p(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
            case 2:
                ((CollapsingToolbarLayout) this.e).setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                return;
            case 3:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C0340g gVar = ((BottomSheetBehavior) this.e).l;
                if (gVar != null) {
                    gVar.l(floatValue);
                    return;
                }
                return;
            default:
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TextView textView = (TextView) this.e;
                textView.setScaleX(floatValue2);
                textView.setScaleY(floatValue2);
                return;
        }
    }
}
