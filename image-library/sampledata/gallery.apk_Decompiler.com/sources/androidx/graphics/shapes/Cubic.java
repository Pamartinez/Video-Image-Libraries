package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0018\b\u0016\u0018\u0000 82\u00020\u0001:\u00018B\u0013\b\u0000\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B9\b\u0010\u0012\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\t\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\n\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\u000b\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0004\b\u0004\u0010\fJ!\u0010\u0011\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0015\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00162\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001f\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&R\u0011\u0010)\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0011\u0010+\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b*\u0010(R\u0011\u0010-\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b,\u0010(R\u0011\u0010/\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b.\u0010(R\u0011\u00101\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b0\u0010(R\u0011\u00103\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b2\u0010(R\u0011\u00105\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b4\u0010(R\u0011\u00107\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b6\u0010(\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00069"}, d2 = {"Landroidx/graphics/shapes/Cubic;", "", "", "points", "<init>", "([F)V", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "anchor0", "control0", "control1", "anchor1", "(JJJJLkotlin/jvm/internal/e;)V", "", "t", "pointOnCurve-OOQOV4g$graphics_shapes_release", "(F)J", "pointOnCurve", "", "zeroLength$graphics_shapes_release", "()Z", "zeroLength", "Lme/i;", "split", "(F)Lme/i;", "reverse", "()Landroidx/graphics/shapes/Cubic;", "", "toString", "()Ljava/lang/String;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "[F", "getPoints$graphics_shapes_release", "()[F", "getAnchor0X", "()F", "anchor0X", "getAnchor0Y", "anchor0Y", "getControl0X", "control0X", "getControl0Y", "control0Y", "getControl1X", "control1X", "getControl1Y", "control1Y", "getAnchor1X", "anchor1X", "getAnchor1Y", "anchor1Y", "Companion", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Cubic {
    public static final Companion Companion = new Companion((e) null);
    private final float[] points;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J(\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007¨\u0006\r"}, d2 = {"Landroidx/graphics/shapes/Cubic$Companion;", "", "()V", "circularArc", "Landroidx/graphics/shapes/Cubic;", "centerX", "", "centerY", "x0", "y0", "x1", "y1", "straightLine", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final Cubic circularArc(float f, float f5, float f8, float f10, float f11, float f12) {
            boolean z;
            float f13;
            float f14 = f8;
            float f15 = f10;
            float f16 = f11;
            float f17 = f12;
            float f18 = f14 - f;
            float f19 = f15 - f5;
            long directionVector = Utils.directionVector(f18, f19);
            float f20 = f16 - f;
            float f21 = f17 - f5;
            long directionVector2 = Utils.directionVector(f20, f21);
            long r12 = Utils.m25rotate90DnnuFBc(directionVector);
            long r14 = Utils.m25rotate90DnnuFBc(directionVector2);
            if (PointKt.m10dotProduct5P9i7ZU(r12, f20, f21) >= 0.0f) {
                z = true;
            } else {
                z = false;
            }
            float r42 = PointKt.m11dotProductybeJwSQ(directionVector, directionVector2);
            if (r42 > 0.999f) {
                return straightLine(f14, f15, f16, f17);
            }
            float f22 = (float) 1;
            float f23 = f22 - r42;
            float sqrt = ((((float) Math.sqrt((double) (((float) 2) * f23))) - ((float) Math.sqrt((double) (f22 - (r42 * r42))))) * ((Utils.distance(f18, f19) * 4.0f) / 3.0f)) / f23;
            if (z) {
                f13 = 1.0f;
            } else {
                f13 = -1.0f;
            }
            float f24 = sqrt * f13;
            return CubicKt.Cubic(f14, f15, (PointKt.m14getXDnnuFBc(r12) * f24) + f14, (PointKt.m15getYDnnuFBc(r12) * f24) + f15, f16 - (PointKt.m14getXDnnuFBc(r14) * f24), f17 - (PointKt.m15getYDnnuFBc(r14) * f24), f16, f17);
        }

        public final Cubic straightLine(float f, float f5, float f8, float f10) {
            return CubicKt.Cubic(f, f5, Utils.interpolate(f, f8, 0.33333334f), Utils.interpolate(f5, f10, 0.33333334f), Utils.interpolate(f, f8, 0.6666667f), Utils.interpolate(f5, f10, 0.6666667f), f8, f10);
        }

        private Companion() {
        }
    }

    public /* synthetic */ Cubic(long j2, long j3, long j8, long j10, e eVar) {
        this(j2, j3, j8, j10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cubic)) {
            return false;
        }
        return Arrays.equals(this.points, ((Cubic) obj).points);
    }

    public final float getAnchor0X() {
        return this.points[0];
    }

    public final float getAnchor0Y() {
        return this.points[1];
    }

    public final float getAnchor1X() {
        return this.points[6];
    }

    public final float getAnchor1Y() {
        return this.points[7];
    }

    public final float getControl0X() {
        return this.points[2];
    }

    public final float getControl0Y() {
        return this.points[3];
    }

    public final float getControl1X() {
        return this.points[4];
    }

    public final float getControl1Y() {
        return this.points[5];
    }

    public final float[] getPoints$graphics_shapes_release() {
        return this.points;
    }

    public int hashCode() {
        return Arrays.hashCode(this.points);
    }

    /* renamed from: pointOnCurve-OOQOV4g$graphics_shapes_release  reason: not valid java name */
    public final long m7pointOnCurveOOQOV4g$graphics_shapes_release(float f) {
        float f5 = ((float) 1) - f;
        float f8 = f5 * f5 * f5;
        float f10 = ((float) 3) * f;
        float f11 = f10 * f5 * f5;
        float f12 = f10 * f * f5;
        float control1X = (getControl1X() * f12) + (getControl0X() * f11) + (getAnchor0X() * f8);
        float f13 = f * f * f;
        float anchor1X = (getAnchor1X() * f13) + control1X;
        float control0Y = (getControl0Y() * f11) + (getAnchor0Y() * f8);
        return FloatFloatPair.m1constructorimpl(anchor1X, (getAnchor1Y() * f13) + (getControl1Y() * f12) + control0Y);
    }

    public final Cubic reverse() {
        return CubicKt.Cubic(getAnchor1X(), getAnchor1Y(), getControl1X(), getControl1Y(), getControl0X(), getControl0Y(), getAnchor0X(), getAnchor0Y());
    }

    public final i split(float f) {
        float f5 = ((float) 1) - f;
        long r1 = m7pointOnCurveOOQOV4g$graphics_shapes_release(f);
        float f8 = f5 * f5;
        float f10 = ((float) 2) * f5 * f;
        float f11 = f * f;
        return new i(CubicKt.Cubic(getAnchor0X(), getAnchor0Y(), (getAnchor0X() * f5) + (getControl0X() * f), (getAnchor0Y() * f5) + (getControl0Y() * f), (getControl1X() * f11) + (getControl0X() * f10) + (getAnchor0X() * f8), (getControl1Y() * f11) + (getControl0Y() * f10) + (getAnchor0Y() * f8), PointKt.m14getXDnnuFBc(r1), PointKt.m15getYDnnuFBc(r1)), CubicKt.Cubic(PointKt.m14getXDnnuFBc(r1), PointKt.m15getYDnnuFBc(r1), (getAnchor1X() * f11) + (getControl1X() * f10) + (getControl0X() * f8), (getAnchor1Y() * f11) + (getControl1Y() * f10) + (getControl0Y() * f8), (getAnchor1X() * f) + (getControl1X() * f5), (getAnchor1Y() * f) + (getControl1Y() * f5), getAnchor1X(), getAnchor1Y()));
    }

    public String toString() {
        return "anchor0: (" + getAnchor0X() + ArcCommonLog.TAG_COMMA + getAnchor0Y() + ") control0: (" + getControl0X() + ArcCommonLog.TAG_COMMA + getControl0Y() + "), control1: (" + getControl1X() + ArcCommonLog.TAG_COMMA + getControl1Y() + "), anchor1: (" + getAnchor1X() + ArcCommonLog.TAG_COMMA + getAnchor1Y() + ')';
    }

    public final boolean zeroLength$graphics_shapes_release() {
        if (Math.abs(getAnchor0X() - getAnchor1X()) >= 1.0E-4f || Math.abs(getAnchor0Y() - getAnchor1Y()) >= 1.0E-4f) {
            return false;
        }
        return true;
    }

    public Cubic(float[] fArr) {
        j.e(fArr, "points");
        this.points = fArr;
        if (fArr.length != 8) {
            throw new IllegalArgumentException("Points array size should be 8");
        }
    }

    private Cubic(long j2, long j3, long j8, long j10) {
        this(new float[]{PointKt.m14getXDnnuFBc(j2), PointKt.m15getYDnnuFBc(j2), PointKt.m14getXDnnuFBc(j3), PointKt.m15getYDnnuFBc(j3), PointKt.m14getXDnnuFBc(j8), PointKt.m15getYDnnuFBc(j8), PointKt.m14getXDnnuFBc(j10), PointKt.m15getYDnnuFBc(j10)});
    }
}
