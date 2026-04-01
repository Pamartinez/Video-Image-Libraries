package androidx.cardview.widget;

import android.graphics.drawable.Drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class RoundRectDrawableWithShadow extends Drawable {
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));

    public static float calculateHorizontalPadding(float f, float f5, boolean z) {
        if (!z) {
            return f;
        }
        return (float) (((1.0d - COS_45) * ((double) f5)) + ((double) f));
    }

    public static float calculateVerticalPadding(float f, float f5, boolean z) {
        if (!z) {
            return f * 1.5f;
        }
        return (float) (((1.0d - COS_45) * ((double) f5)) + ((double) (f * 1.5f)));
    }
}
