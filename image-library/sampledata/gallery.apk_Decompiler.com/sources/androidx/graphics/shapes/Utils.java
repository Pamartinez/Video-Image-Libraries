package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\u0007\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\u0007\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\t\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\n\u001a4\u0010\u000f\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\f\u001a\u00060\u0005j\u0002`\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001e\u0010\u0012\u001a\u00060\u0005j\u0002`\u0006*\u00060\u0005j\u0002`\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0017\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a'\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0018\u0010\u0019\"\u001e\u0010\u001a\u001a\u00060\u0005j\u0002`\u00068\u0000X\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u001a\u0010\u001e\u001a\u00020\u00008\u0000XD¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u001a\u0010\"\u001a\u00020\u00008\u0000XD¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"", "x", "y", "distance", "(FF)F", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "directionVector", "(FF)J", "angleRadians", "(F)J", "radius", "center", "radialToCartesian-L6JJ3z0", "(FFJ)J", "radialToCartesian", "rotate90-DnnuFBc", "(J)J", "rotate90", "square", "(F)F", "start", "stop", "fraction", "interpolate", "(FFF)F", "Zero", "J", "getZero", "()J", "FloatPi", "F", "getFloatPi", "()F", "TwoPi", "getTwoPi", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Utils {
    private static final float FloatPi = 3.1415927f;
    private static final float TwoPi = 6.2831855f;
    private static final long Zero = FloatFloatPair.m1constructorimpl(0.0f, 0.0f);

    public static final long directionVector(float f, float f5) {
        float distance = distance(f, f5);
        if (distance > 0.0f) {
            return FloatFloatPair.m1constructorimpl(f / distance, f5 / distance);
        }
        throw new IllegalArgumentException("Required distance greater than zero");
    }

    public static final float distance(float f, float f5) {
        return (float) Math.sqrt((double) ((f5 * f5) + (f * f)));
    }

    public static final float getFloatPi() {
        return FloatPi;
    }

    public static final float interpolate(float f, float f5, float f8) {
        return (f8 * f5) + ((((float) 1) - f8) * f);
    }

    /* renamed from: radialToCartesian-L6JJ3z0  reason: not valid java name */
    public static final long m23radialToCartesianL6JJ3z0(float f, float f5, long j2) {
        return PointKt.m18plusybeJwSQ(PointKt.m19timesso9K2fw(directionVector(f5), f), j2);
    }

    /* renamed from: radialToCartesian-L6JJ3z0$default  reason: not valid java name */
    public static /* synthetic */ long m24radialToCartesianL6JJ3z0$default(float f, float f5, long j2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            j2 = Zero;
        }
        return m23radialToCartesianL6JJ3z0(f, f5, j2);
    }

    /* renamed from: rotate90-DnnuFBc  reason: not valid java name */
    public static final long m25rotate90DnnuFBc(long j2) {
        return FloatFloatPair.m1constructorimpl(-PointKt.m15getYDnnuFBc(j2), PointKt.m14getXDnnuFBc(j2));
    }

    public static final float square(float f) {
        return f * f;
    }

    public static final long directionVector(float f) {
        double d = (double) f;
        return FloatFloatPair.m1constructorimpl((float) Math.cos(d), (float) Math.sin(d));
    }
}
