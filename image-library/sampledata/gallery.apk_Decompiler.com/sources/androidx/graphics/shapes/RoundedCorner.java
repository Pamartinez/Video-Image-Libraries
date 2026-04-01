package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import ne.C1195m;
import o1.C0246a;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b \b\u0002\u0018\u00002\u00020\u0001B7\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJf\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0014\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0015\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0016\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019JJ\u0010\u001f\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00032\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u001b\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u001c\u001a\u00060\u0002j\u0002`\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\"2\u0006\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000bH\u0007¢\u0006\u0004\b#\u0010$R!\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b&\u0010'R!\u0010\u0005\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b(\u0010'R!\u0010\u0006\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b)\u0010'R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010*\u001a\u0004\b+\u0010,R!\u0010\u001c\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001c\u0010%\u001a\u0004\b-\u0010'R!\u0010.\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b.\u0010%\u001a\u0004\b/\u0010'R\u0017\u00100\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0017\u00104\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b4\u00101\u001a\u0004\b5\u00103R\u0017\u00106\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b6\u00101\u001a\u0004\b7\u00103R\u0017\u00108\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b8\u00101\u001a\u0004\b9\u00103R\u0017\u0010:\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b:\u00101\u001a\u0004\b;\u00103R,\u0010<\u001a\u00060\u0002j\u0002`\u00038\u0006@\u0006X\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b<\u0010%\u001a\u0004\b=\u0010'\"\u0004\b>\u0010?R\u0011\u0010A\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b@\u00103\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006B"}, d2 = {"Landroidx/graphics/shapes/RoundedCorner;", "", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "p0", "p1", "p2", "Landroidx/graphics/shapes/CornerRounding;", "rounding", "<init>", "(JJJLandroidx/graphics/shapes/CornerRounding;Lkotlin/jvm/internal/e;)V", "", "allowedCut", "calculateActualSmoothingValue", "(F)F", "actualRoundCut", "actualSmoothingValues", "corner", "sideStart", "circleSegmentIntersection", "otherCircleSegmentIntersection", "circleCenter", "actualR", "Landroidx/graphics/shapes/Cubic;", "computeFlankingCurve-oAJzIJU", "(FFJJJJJF)Landroidx/graphics/shapes/Cubic;", "computeFlankingCurve", "d0", "d1", "lineIntersection-CBFvKDc", "(JJJJ)Landroidx/collection/FloatFloatPair;", "lineIntersection", "allowedCut0", "allowedCut1", "", "getCubics", "(FF)Ljava/util/List;", "J", "getP0-1ufDz9w", "()J", "getP1-1ufDz9w", "getP2-1ufDz9w", "Landroidx/graphics/shapes/CornerRounding;", "getRounding", "()Landroidx/graphics/shapes/CornerRounding;", "getD1-1ufDz9w", "d2", "getD2-1ufDz9w", "cornerRadius", "F", "getCornerRadius", "()F", "smoothing", "getSmoothing", "cosAngle", "getCosAngle", "sinAngle", "getSinAngle", "expectedRoundCut", "getExpectedRoundCut", "center", "getCenter-1ufDz9w", "setCenter-DnnuFBc", "(J)V", "getExpectedCut", "expectedCut", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class RoundedCorner {
    private long center;
    private final float cornerRadius;
    private final float cosAngle;

    /* renamed from: d1  reason: collision with root package name */
    private final long f993d1;
    private final long d2;
    private final float expectedRoundCut;

    /* renamed from: p0  reason: collision with root package name */
    private final long f994p0;

    /* renamed from: p1  reason: collision with root package name */
    private final long f995p1;

    /* renamed from: p2  reason: collision with root package name */
    private final long f996p2;
    private final CornerRounding rounding;
    private final float sinAngle;
    private final float smoothing;

    public /* synthetic */ RoundedCorner(long j2, long j3, long j8, CornerRounding cornerRounding, e eVar) {
        this(j2, j3, j8, cornerRounding);
    }

    private final float calculateActualSmoothingValue(float f) {
        if (f > getExpectedCut()) {
            return this.smoothing;
        }
        float f5 = this.expectedRoundCut;
        if (f <= f5) {
            return 0.0f;
        }
        return ((f - f5) * this.smoothing) / (getExpectedCut() - this.expectedRoundCut);
    }

    /* renamed from: computeFlankingCurve-oAJzIJU  reason: not valid java name */
    private final Cubic m20computeFlankingCurveoAJzIJU(float f, float f5, long j2, long j3, long j8, long j10, long j11, float f8) {
        float f10 = f5;
        long j12 = j2;
        long j13 = j11;
        long j14 = j3;
        long r72 = PointKt.m12getDirectionDnnuFBc(PointKt.m17minusybeJwSQ(j14, j12));
        long r9 = PointKt.m18plusybeJwSQ(j12, PointKt.m19timesso9K2fw(PointKt.m19timesso9K2fw(r72, f), ((float) 1) + f10));
        long j15 = j8;
        long r0 = PointKt.m16interpolatedLqxh1s(j15, PointKt.m9divso9K2fw(PointKt.m18plusybeJwSQ(j8, j10), 2.0f), f10);
        long r02 = PointKt.m18plusybeJwSQ(j13, PointKt.m19timesso9K2fw(Utils.directionVector(PointKt.m14getXDnnuFBc(r0) - PointKt.m14getXDnnuFBc(j13), PointKt.m15getYDnnuFBc(r0) - PointKt.m15getYDnnuFBc(j13)), f8));
        long j16 = r72;
        long j17 = r02;
        long j18 = j14;
        long j19 = j17;
        FloatFloatPair r03 = m21lineIntersectionCBFvKDc(j18, j16, j19, Utils.m25rotate90DnnuFBc(PointKt.m17minusybeJwSQ(r02, j13)));
        if (r03 != null) {
            j15 = r03.m6unboximpl();
        }
        return new Cubic(r9, PointKt.m9divso9K2fw(PointKt.m18plusybeJwSQ(r9, PointKt.m19timesso9K2fw(j15, 2.0f)), 3.0f), j15, j19, (e) null);
    }

    /* renamed from: lineIntersection-CBFvKDc  reason: not valid java name */
    private final FloatFloatPair m21lineIntersectionCBFvKDc(long j2, long j3, long j8, long j10) {
        long r10 = Utils.m25rotate90DnnuFBc(j10);
        float r32 = PointKt.m11dotProductybeJwSQ(j3, r10);
        if (Math.abs(r32) < 1.0E-4f) {
            return null;
        }
        float r82 = PointKt.m11dotProductybeJwSQ(PointKt.m17minusybeJwSQ(j8, j2), r10);
        if (Math.abs(r32) < Math.abs(r82) * 1.0E-4f) {
            return null;
        }
        return FloatFloatPair.m0boximpl(PointKt.m18plusybeJwSQ(j2, PointKt.m19timesso9K2fw(j3, r82 / r32)));
    }

    /* renamed from: getCenter-1ufDz9w  reason: not valid java name */
    public final long m22getCenter1ufDz9w() {
        return this.center;
    }

    public final List<Cubic> getCubics(float f, float f5) {
        float min = Math.min(f, f5);
        float f8 = this.expectedRoundCut;
        if (f8 < 1.0E-4f || min < 1.0E-4f || this.cornerRadius < 1.0E-4f) {
            long j2 = this.f995p1;
            this.center = j2;
            return C0246a.e0(Cubic.Companion.straightLine(PointKt.m14getXDnnuFBc(j2), PointKt.m15getYDnnuFBc(this.f995p1), PointKt.m14getXDnnuFBc(this.f995p1), PointKt.m15getYDnnuFBc(this.f995p1)));
        }
        float min2 = Math.min(min, f8);
        float calculateActualSmoothingValue = calculateActualSmoothingValue(f);
        float calculateActualSmoothingValue2 = calculateActualSmoothingValue(f5);
        float f10 = (this.cornerRadius * min2) / this.expectedRoundCut;
        float square = Utils.square(f10);
        this.center = PointKt.m18plusybeJwSQ(this.f995p1, PointKt.m19timesso9K2fw(PointKt.m12getDirectionDnnuFBc(PointKt.m9divso9K2fw(PointKt.m18plusybeJwSQ(this.f993d1, this.d2), 2.0f)), (float) Math.sqrt((double) (Utils.square(min2) + square))));
        long r72 = PointKt.m18plusybeJwSQ(this.f995p1, PointKt.m19timesso9K2fw(this.f993d1, min2));
        long r9 = PointKt.m18plusybeJwSQ(this.f995p1, PointKt.m19timesso9K2fw(this.d2, min2));
        Cubic r15 = m20computeFlankingCurveoAJzIJU(min2, calculateActualSmoothingValue, this.f995p1, this.f994p0, r72, r9, this.center, f10);
        long j3 = r9;
        long j8 = r72;
        float f11 = calculateActualSmoothingValue2;
        Cubic reverse = m20computeFlankingCurveoAJzIJU(min2, f11, this.f995p1, this.f996p2, j3, j8, this.center, f10).reverse();
        return C1195m.q0(r15, Cubic.Companion.circularArc(PointKt.m14getXDnnuFBc(this.center), PointKt.m15getYDnnuFBc(this.center), r15.getAnchor1X(), r15.getAnchor1Y(), reverse.getAnchor0X(), reverse.getAnchor0Y()), reverse);
    }

    public final float getExpectedCut() {
        return (((float) 1) + this.smoothing) * this.expectedRoundCut;
    }

    public final float getExpectedRoundCut() {
        return this.expectedRoundCut;
    }

    private RoundedCorner(long j2, long j3, long j8, CornerRounding cornerRounding) {
        this.f994p0 = j2;
        this.f995p1 = j3;
        this.f996p2 = j8;
        this.rounding = cornerRounding;
        long r5 = PointKt.m12getDirectionDnnuFBc(PointKt.m17minusybeJwSQ(j2, j3));
        this.f993d1 = r5;
        long r72 = PointKt.m12getDirectionDnnuFBc(PointKt.m17minusybeJwSQ(j8, j3));
        this.d2 = r72;
        float radius = cornerRounding != null ? cornerRounding.getRadius() : 0.0f;
        this.cornerRadius = radius;
        this.smoothing = cornerRounding != null ? cornerRounding.getSmoothing() : 0.0f;
        float r52 = PointKt.m11dotProductybeJwSQ(r5, r72);
        this.cosAngle = r52;
        float f = (float) 1;
        float sqrt = (float) Math.sqrt((double) (f - Utils.square(r52)));
        this.sinAngle = sqrt;
        this.expectedRoundCut = ((double) sqrt) > 0.001d ? ((r52 + f) * radius) / sqrt : 0.0f;
        this.center = FloatFloatPair.m1constructorimpl(0.0f, 0.0f);
    }
}
