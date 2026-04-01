package androidx.core.math;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MathUtils {
    public static float clamp(float f, float f5, float f8) {
        if (f < f5) {
            return f5;
        }
        return f > f8 ? f8 : f;
    }

    public static int clamp(int i2, int i7, int i8) {
        if (i2 < i7) {
            return i7;
        }
        return i2 > i8 ? i8 : i2;
    }
}
