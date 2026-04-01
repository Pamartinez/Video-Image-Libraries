package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u001d\u001a\u001a\u0010\u0005\u001a\u00020\u0002*\u00060\u0000j\u0002`\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a&\u0010\t\u001a\u00020\u0002*\u00060\u0000j\u0002`\u00012\n\u0010\u0006\u001a\u00060\u0000j\u0002`\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a*\u0010\t\u001a\u00020\u0002*\u00060\u0000j\u0002`\u00012\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a&\u0010\u0011\u001a\u00020\u000e*\u00060\u0000j\u0002`\u00012\n\u0010\u0006\u001a\u00060\u0000j\u0002`\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001e\u0010\u0014\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0017\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\n\u0010\u0006\u001a\u00060\u0000j\u0002`\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a+\u0010\u0019\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\n\u0010\u0006\u001a\u00060\u0000j\u0002`\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016\u001a'\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u001a\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a'\u0010\u001f\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u001a\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001c\u001a6\u0010%\u001a\u00060\u0000j\u0002`\u00012\n\u0010 \u001a\u00060\u0000j\u0002`\u00012\n\u0010!\u001a\u00060\u0000j\u0002`\u00012\u0006\u0010\"\u001a\u00020\u0002H\u0000ø\u0001\u0000¢\u0006\u0004\b#\u0010$\"\u001c\u0010'\u001a\u00020\u0002*\u00060\u0000j\u0002`\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0004\"\u001c\u0010)\u001a\u00020\u0002*\u00060\u0000j\u0002`\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0004*\f\b\u0000\u0010*\"\u00020\u00002\u00020\u0000\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "", "getDistance-DnnuFBc", "(J)F", "getDistance", "other", "dotProduct-ybeJwSQ", "(JJ)F", "dotProduct", "otherX", "otherY", "dotProduct-5P9i7ZU", "(JFF)F", "", "clockwise-ybeJwSQ", "(JJ)Z", "clockwise", "getDirection-DnnuFBc", "(J)J", "getDirection", "minus-ybeJwSQ", "(JJ)J", "minus", "plus-ybeJwSQ", "plus", "operand", "times-so9K2fw", "(JF)J", "times", "div-so9K2fw", "div", "start", "stop", "fraction", "interpolate-dLqxh1s", "(JJF)J", "interpolate", "getX-DnnuFBc", "x", "getY-DnnuFBc", "y", "Point", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PointKt {
    /* renamed from: clockwise-ybeJwSQ  reason: not valid java name */
    public static final boolean m8clockwiseybeJwSQ(long j2, long j3) {
        if ((m15getYDnnuFBc(j3) * m14getXDnnuFBc(j2)) - (m14getXDnnuFBc(j3) * m15getYDnnuFBc(j2)) > 0.0f) {
            return true;
        }
        return false;
    }

    /* renamed from: div-so9K2fw  reason: not valid java name */
    public static final long m9divso9K2fw(long j2, float f) {
        return FloatFloatPair.m1constructorimpl(m14getXDnnuFBc(j2) / f, m15getYDnnuFBc(j2) / f);
    }

    /* renamed from: dotProduct-5P9i7ZU  reason: not valid java name */
    public static final float m10dotProduct5P9i7ZU(long j2, float f, float f5) {
        return (m15getYDnnuFBc(j2) * f5) + (m14getXDnnuFBc(j2) * f);
    }

    /* renamed from: dotProduct-ybeJwSQ  reason: not valid java name */
    public static final float m11dotProductybeJwSQ(long j2, long j3) {
        float r0 = m14getXDnnuFBc(j2);
        return (m15getYDnnuFBc(j3) * m15getYDnnuFBc(j2)) + (m14getXDnnuFBc(j3) * r0);
    }

    /* renamed from: getDirection-DnnuFBc  reason: not valid java name */
    public static final long m12getDirectionDnnuFBc(long j2) {
        float r0 = m13getDistanceDnnuFBc(j2);
        if (r0 > 0.0f) {
            return m9divso9K2fw(j2, r0);
        }
        throw new IllegalArgumentException("Can't get the direction of a 0-length vector");
    }

    /* renamed from: getDistance-DnnuFBc  reason: not valid java name */
    public static final float m13getDistanceDnnuFBc(long j2) {
        return (float) Math.sqrt((double) ((m15getYDnnuFBc(j2) * m15getYDnnuFBc(j2)) + (m14getXDnnuFBc(j2) * m14getXDnnuFBc(j2))));
    }

    /* renamed from: getX-DnnuFBc  reason: not valid java name */
    public static final float m14getXDnnuFBc(long j2) {
        return Float.intBitsToFloat((int) (j2 >> 32));
    }

    /* renamed from: getY-DnnuFBc  reason: not valid java name */
    public static final float m15getYDnnuFBc(long j2) {
        return Float.intBitsToFloat((int) (j2 & 4294967295L));
    }

    /* renamed from: interpolate-dLqxh1s  reason: not valid java name */
    public static final long m16interpolatedLqxh1s(long j2, long j3, float f) {
        return FloatFloatPair.m1constructorimpl(Utils.interpolate(m14getXDnnuFBc(j2), m14getXDnnuFBc(j3), f), Utils.interpolate(m15getYDnnuFBc(j2), m15getYDnnuFBc(j3), f));
    }

    /* renamed from: minus-ybeJwSQ  reason: not valid java name */
    public static final long m17minusybeJwSQ(long j2, long j3) {
        return FloatFloatPair.m1constructorimpl(m14getXDnnuFBc(j2) - m14getXDnnuFBc(j3), m15getYDnnuFBc(j2) - m15getYDnnuFBc(j3));
    }

    /* renamed from: plus-ybeJwSQ  reason: not valid java name */
    public static final long m18plusybeJwSQ(long j2, long j3) {
        return FloatFloatPair.m1constructorimpl(m14getXDnnuFBc(j3) + m14getXDnnuFBc(j2), m15getYDnnuFBc(j3) + m15getYDnnuFBc(j2));
    }

    /* renamed from: times-so9K2fw  reason: not valid java name */
    public static final long m19timesso9K2fw(long j2, float f) {
        return FloatFloatPair.m1constructorimpl(m14getXDnnuFBc(j2) * f, m15getYDnnuFBc(j2) * f);
    }
}
