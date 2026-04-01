package Yb;

import android.animation.ValueAnimator;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowPageTransformer;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectControl;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                ((SimpleSlideShowPageTransformer) this.e).lambda$transformVelocityAboutDuration$1((ViewPager2) this.f, (AtomicReference) this.g, valueAnimator);
                return;
            default:
                AnimatableAttribute.applyAttrs$lambda$3$lambda$0((ValueAnimator) this.e, (AnimatableAttribute) this.f, (VibeEffectControl) this.g, valueAnimator);
                return;
        }
    }
}
