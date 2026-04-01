package z2;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ q e;

    public /* synthetic */ e(q qVar, int i2, byte b) {
        this.d = i2;
        this.e = qVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        q qVar = this.e;
        switch (i2) {
            case 0:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                qVar.f2221i.setScaleX(floatValue);
                qVar.f2221i.setScaleY(floatValue);
                return;
            case 1:
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                int i7 = q.y;
                qVar.f2221i.setTranslationY((float) intValue);
                return;
            default:
                int intValue2 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                int i8 = q.y;
                qVar.f2221i.setTranslationY((float) intValue2);
                return;
        }
    }

    public e(q qVar, int i2) {
        this.d = 1;
        this.e = qVar;
    }
}
