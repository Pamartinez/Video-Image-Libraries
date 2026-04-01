package c2;

import android.animation.ValueAnimator;
import android.view.ViewGroup;
import com.google.android.material.chip.SeslChipGroup;
import com.samsung.android.gallery.widget.animations.viewer.InstantSlowMoSaveClipAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ k(Object obj, int i2, int i7, int i8) {
        this.d = i8;
        this.g = obj;
        this.e = i2;
        this.f = i7;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        int i7 = this.f;
        int i8 = this.e;
        Object obj = this.g;
        switch (i2) {
            case 0:
                SeslChipGroup seslChipGroup = (SeslChipGroup) obj;
                int i10 = SeslChipGroup.r;
                ViewGroup.LayoutParams layoutParams = seslChipGroup.getLayoutParams();
                int floatValue = i8 + ((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * ((float) i7)));
                layoutParams.height = floatValue;
                seslChipGroup.q = floatValue;
                seslChipGroup.setLayoutParams(layoutParams);
                return;
            default:
                ((InstantSlowMoSaveClipAnimation) obj).lambda$createTextAnimation$0(i8, i7, valueAnimator);
                return;
        }
    }
}
