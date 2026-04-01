package R1;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final LinearInterpolator f640a = new LinearInterpolator();
    public static final FastOutSlowInInterpolator b = new FastOutSlowInInterpolator();

    /* renamed from: c  reason: collision with root package name */
    public static final FastOutLinearInInterpolator f641c = new FastOutLinearInInterpolator();
    public static final LinearOutSlowInInterpolator d = new LinearOutSlowInInterpolator();
    public static final DecelerateInterpolator e = new DecelerateInterpolator();

    public static float a(float f, float f5, float f8) {
        return C0212a.a(f5, f, f8, f);
    }

    public static float b(float f, float f5, float f8, float f10, float f11) {
        if (f11 <= f8) {
            return f;
        }
        if (f11 >= f10) {
            return f5;
        }
        return a(f, f5, (f11 - f8) / (f10 - f8));
    }

    public static int c(float f, int i2, int i7) {
        return Math.round(f * ((float) (i7 - i2))) + i2;
    }
}
