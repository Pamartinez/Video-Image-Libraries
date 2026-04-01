package B2;

import android.animation.ValueAnimator;
import com.google.android.material.internal.CheckableImageButton;

/* renamed from: B2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0001b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0003d e;

    public /* synthetic */ C0001b(C0003d dVar, int i2) {
        this.d = i2;
        this.e = dVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        C0003d dVar = this.e;
        dVar.getClass();
        switch (i2) {
            case 0:
                dVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
            default:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CheckableImageButton checkableImageButton = dVar.d;
                checkableImageButton.setScaleX(floatValue);
                checkableImageButton.setScaleY(floatValue);
                return;
        }
    }
}
