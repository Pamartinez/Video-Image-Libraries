package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aS\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001aG\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u000b\u0010\u000f\u001a\u001b\u0010\u0012\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a/\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"", "numVertices", "", "radius", "centerX", "centerY", "Landroidx/graphics/shapes/CornerRounding;", "rounding", "", "perVertexRounding", "Landroidx/graphics/shapes/RoundedPolygon;", "RoundedPolygon", "(IFFFLandroidx/graphics/shapes/CornerRounding;Ljava/util/List;)Landroidx/graphics/shapes/RoundedPolygon;", "", "vertices", "([FLandroidx/graphics/shapes/CornerRounding;Ljava/util/List;FF)Landroidx/graphics/shapes/RoundedPolygon;", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "calculateCenter", "([F)J", "verticesFromNumVerts", "(IFFF)[F", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RoundedPolygonKt {
    public static final RoundedPolygon RoundedPolygon(int i2, float f, float f5, float f8, CornerRounding cornerRounding, List<CornerRounding> list) {
        j.e(cornerRounding, "rounding");
        return RoundedPolygon(verticesFromNumVerts(i2, f, f5, f8), cornerRounding, list, f5, f8);
    }

    public static /* synthetic */ RoundedPolygon RoundedPolygon$default(int i2, float f, float f5, float f8, CornerRounding cornerRounding, List list, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            f = 1.0f;
        }
        if ((i7 & 4) != 0) {
            f5 = 0.0f;
        }
        if ((i7 & 8) != 0) {
            f8 = 0.0f;
        }
        if ((i7 & 16) != 0) {
            cornerRounding = CornerRounding.Unrounded;
        }
        if ((i7 & 32) != 0) {
            list = null;
        }
        return RoundedPolygon(i2, f, f5, f8, cornerRounding, list);
    }

    private static final long calculateCenter(float[] fArr) {
        float f = 0.0f;
        int i2 = 0;
        float f5 = 0.0f;
        while (i2 < fArr.length) {
            int i7 = i2 + 1;
            f += fArr[i2];
            i2 += 2;
            f5 += fArr[i7];
        }
        float f8 = (float) 2;
        return FloatFloatPair.m1constructorimpl((f / ((float) fArr.length)) / f8, (f5 / ((float) fArr.length)) / f8);
    }

    private static final float[] verticesFromNumVerts(int i2, float f, float f5, float f8) {
        float[] fArr = new float[(i2 * 2)];
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            float f10 = f;
            long r72 = PointKt.m18plusybeJwSQ(Utils.m24radialToCartesianL6JJ3z0$default(f10, (Utils.getFloatPi() / ((float) i2)) * ((float) 2) * ((float) i7), 0, 4, (Object) null), FloatFloatPair.m1constructorimpl(f5, f8));
            int i10 = i8 + 1;
            fArr[i8] = PointKt.m14getXDnnuFBc(r72);
            i8 += 2;
            fArr[i10] = PointKt.m15getYDnnuFBc(r72);
            i7++;
            f = f10;
        }
        return fArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r11 = r1.get(r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final androidx.graphics.shapes.RoundedPolygon RoundedPolygon(float[] r23, androidx.graphics.shapes.CornerRounding r24, java.util.List<androidx.graphics.shapes.CornerRounding> r25, float r26, float r27) {
        /*
            r0 = r23
            r1 = r25
            r2 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            java.lang.String r3 = "vertices"
            kotlin.jvm.internal.j.e(r0, r3)
            java.lang.String r3 = "rounding"
            r4 = r24
            kotlin.jvm.internal.j.e(r4, r3)
            int r3 = r0.length
            r5 = 6
            if (r3 < r5) goto L_0x0273
            int r3 = r0.length
            r5 = 2
            int r3 = r3 % r5
            r6 = 1
            if (r3 == r6) goto L_0x026b
            if (r1 == 0) goto L_0x0034
            int r3 = r1.size()
            int r3 = r3 * r5
            int r7 = r0.length
            if (r3 != r7) goto L_0x002c
            goto L_0x0034
        L_0x002c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "perVertexRounding list should be either null or the same size as the number of vertices (vertices.size / 2)"
            r0.<init>(r1)
            throw r0
        L_0x0034:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r7 = r0.length
            int r7 = r7 / r5
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r9 = 0
            r10 = r9
        L_0x0042:
            if (r10 >= r7) goto L_0x0090
            if (r1 == 0) goto L_0x0052
            java.lang.Object r11 = r1.get(r10)
            androidx.graphics.shapes.CornerRounding r11 = (androidx.graphics.shapes.CornerRounding) r11
            if (r11 != 0) goto L_0x004f
            goto L_0x0052
        L_0x004f:
            r19 = r11
            goto L_0x0054
        L_0x0052:
            r19 = r4
        L_0x0054:
            int r11 = r10 + r7
            int r11 = r11 - r6
            int r11 = r11 % r7
            int r11 = r11 * r5
            int r21 = r10 + 1
            int r12 = r21 % r7
            int r12 = r12 * r5
            r13 = r12
            androidx.graphics.shapes.RoundedCorner r12 = new androidx.graphics.shapes.RoundedCorner
            r14 = r0[r11]
            int r11 = r11 + r6
            r11 = r0[r11]
            long r14 = androidx.collection.FloatFloatPair.m1constructorimpl(r14, r11)
            int r10 = r10 * 2
            r11 = r0[r10]
            int r10 = r10 + r6
            r10 = r0[r10]
            long r10 = androidx.collection.FloatFloatPair.m1constructorimpl(r11, r10)
            r22 = r6
            r6 = r0[r13]
            int r13 = r13 + 1
            r13 = r0[r13]
            long r17 = androidx.collection.FloatFloatPair.m1constructorimpl(r6, r13)
            r20 = 0
            r13 = r14
            r15 = r10
            r12.<init>(r13, r15, r17, r19, r20)
            r8.add(r12)
            r10 = r21
            r6 = r22
            goto L_0x0042
        L_0x0090:
            r22 = r6
            Ge.e r1 = B1.a.Z(r9, r7)
            java.util.ArrayList r4 = new java.util.ArrayList
            r6 = 10
            int r6 = ne.C1196n.w0(r1, r6)
            r4.<init>(r6)
            java.util.Iterator r1 = r1.iterator()
        L_0x00a5:
            r6 = r1
            Ge.d r6 = (Ge.d) r6
            boolean r6 = r6.f
            if (r6 == 0) goto L_0x0122
            r6 = r1
            ne.y r6 = (ne.y) r6
            int r6 = r6.nextInt()
            java.lang.Object r10 = r8.get(r6)
            androidx.graphics.shapes.RoundedCorner r10 = (androidx.graphics.shapes.RoundedCorner) r10
            float r10 = r10.getExpectedRoundCut()
            int r11 = r6 + 1
            int r11 = r11 % r7
            java.lang.Object r12 = r8.get(r11)
            androidx.graphics.shapes.RoundedCorner r12 = (androidx.graphics.shapes.RoundedCorner) r12
            float r12 = r12.getExpectedRoundCut()
            float r10 = r10 + r12
            java.lang.Object r12 = r8.get(r6)
            androidx.graphics.shapes.RoundedCorner r12 = (androidx.graphics.shapes.RoundedCorner) r12
            float r12 = r12.getExpectedCut()
            java.lang.Object r13 = r8.get(r11)
            androidx.graphics.shapes.RoundedCorner r13 = (androidx.graphics.shapes.RoundedCorner) r13
            float r13 = r13.getExpectedCut()
            float r12 = r12 + r13
            int r6 = r6 * r5
            r13 = r0[r6]
            int r6 = r6 + 1
            r6 = r0[r6]
            int r11 = r11 * r5
            r14 = r0[r11]
            int r11 = r11 + 1
            r11 = r0[r11]
            float r13 = r13 - r14
            float r6 = r6 - r11
            float r6 = androidx.graphics.shapes.Utils.distance(r13, r6)
            int r11 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r11 <= 0) goto L_0x0108
            float r6 = r6 / r10
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r10 = 0
            java.lang.Float r10 = java.lang.Float.valueOf(r10)
            me.i r11 = new me.i
            r11.<init>(r6, r10)
            goto L_0x011e
        L_0x0108:
            int r11 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r11 <= 0) goto L_0x0119
            float r6 = r6 - r10
            float r12 = r12 - r10
            float r6 = r6 / r12
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            me.i r11 = new me.i
            r11.<init>(r2, r6)
            goto L_0x011e
        L_0x0119:
            me.i r11 = new me.i
            r11.<init>(r2, r2)
        L_0x011e:
            r4.add(r11)
            goto L_0x00a5
        L_0x0122:
            r1 = r9
        L_0x0123:
            if (r1 >= r7) goto L_0x018b
            androidx.collection.MutableFloatList r2 = new androidx.collection.MutableFloatList
            r2.<init>(r5)
            r6 = r9
        L_0x012b:
            if (r6 >= r5) goto L_0x0171
            int r10 = r1 + r7
            int r10 = r10 + -1
            int r10 = r10 + r6
            int r10 = r10 % r7
            java.lang.Object r10 = r4.get(r10)
            me.i r10 = (me.i) r10
            java.lang.Object r11 = r10.d
            java.lang.Number r11 = (java.lang.Number) r11
            float r11 = r11.floatValue()
            java.lang.Object r10 = r10.e
            java.lang.Number r10 = (java.lang.Number) r10
            float r10 = r10.floatValue()
            java.lang.Object r12 = r8.get(r1)
            androidx.graphics.shapes.RoundedCorner r12 = (androidx.graphics.shapes.RoundedCorner) r12
            float r12 = r12.getExpectedRoundCut()
            float r12 = r12 * r11
            java.lang.Object r11 = r8.get(r1)
            androidx.graphics.shapes.RoundedCorner r11 = (androidx.graphics.shapes.RoundedCorner) r11
            float r11 = r11.getExpectedCut()
            java.lang.Object r13 = r8.get(r1)
            androidx.graphics.shapes.RoundedCorner r13 = (androidx.graphics.shapes.RoundedCorner) r13
            float r13 = r13.getExpectedRoundCut()
            float r11 = r11 - r13
            float r11 = r11 * r10
            float r11 = r11 + r12
            r2.add(r11)
            int r6 = r6 + 1
            goto L_0x012b
        L_0x0171:
            java.lang.Object r6 = r8.get(r1)
            androidx.graphics.shapes.RoundedCorner r6 = (androidx.graphics.shapes.RoundedCorner) r6
            float r10 = r2.get(r9)
            r11 = r22
            float r2 = r2.get(r11)
            java.util.List r2 = r6.getCubics(r10, r2)
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x0123
        L_0x018b:
            r11 = r22
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x0192:
            if (r9 >= r7) goto L_0x023e
            int r2 = r9 + r7
            int r2 = r2 - r11
            int r2 = r2 % r7
            int r4 = r9 + 1
            int r6 = r4 % r7
            int r10 = r9 * 2
            r12 = r0[r10]
            int r10 = r10 + r11
            r10 = r0[r10]
            long r12 = androidx.collection.FloatFloatPair.m1constructorimpl(r12, r10)
            int r2 = r2 * r5
            r10 = r0[r2]
            int r2 = r2 + r11
            r2 = r0[r2]
            long r14 = androidx.collection.FloatFloatPair.m1constructorimpl(r10, r2)
            int r2 = r6 * 2
            r10 = r0[r2]
            int r2 = r2 + r11
            r2 = r0[r2]
            r24 = r6
            long r5 = androidx.collection.FloatFloatPair.m1constructorimpl(r10, r2)
            long r14 = androidx.graphics.shapes.PointKt.m17minusybeJwSQ(r12, r14)
            long r5 = androidx.graphics.shapes.PointKt.m17minusybeJwSQ(r5, r12)
            boolean r19 = androidx.graphics.shapes.PointKt.m8clockwiseybeJwSQ(r14, r5)
            r15 = r12
            androidx.graphics.shapes.Feature$Corner r13 = new androidx.graphics.shapes.Feature$Corner
            java.lang.Object r2 = r3.get(r9)
            r14 = r2
            java.util.List r14 = (java.util.List) r14
            java.lang.Object r2 = r8.get(r9)
            androidx.graphics.shapes.RoundedCorner r2 = (androidx.graphics.shapes.RoundedCorner) r2
            long r17 = r2.m22getCenter1ufDz9w()
            r20 = 0
            r13.<init>(r14, r15, r17, r19, r20)
            r1.add(r13)
            androidx.graphics.shapes.Feature$Edge r2 = new androidx.graphics.shapes.Feature$Edge
            androidx.graphics.shapes.Cubic$Companion r5 = androidx.graphics.shapes.Cubic.Companion
            java.lang.Object r6 = r3.get(r9)
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = ne.C1194l.T0(r6)
            androidx.graphics.shapes.Cubic r6 = (androidx.graphics.shapes.Cubic) r6
            float r6 = r6.getAnchor1X()
            java.lang.Object r9 = r3.get(r9)
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = ne.C1194l.T0(r9)
            androidx.graphics.shapes.Cubic r9 = (androidx.graphics.shapes.Cubic) r9
            float r9 = r9.getAnchor1Y()
            r10 = r24
            java.lang.Object r12 = r3.get(r10)
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r12 = ne.C1194l.L0(r12)
            androidx.graphics.shapes.Cubic r12 = (androidx.graphics.shapes.Cubic) r12
            float r12 = r12.getAnchor0X()
            java.lang.Object r10 = r3.get(r10)
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r10 = ne.C1194l.L0(r10)
            androidx.graphics.shapes.Cubic r10 = (androidx.graphics.shapes.Cubic) r10
            float r10 = r10.getAnchor0Y()
            androidx.graphics.shapes.Cubic r5 = r5.straightLine(r6, r9, r12, r10)
            java.util.List r5 = o1.C0246a.e0(r5)
            r2.<init>(r5)
            r1.add(r2)
            r9 = r4
            r5 = 2
            goto L_0x0192
        L_0x023e:
            r2 = 1
            int r3 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1))
            if (r3 != 0) goto L_0x0244
            goto L_0x0248
        L_0x0244:
            int r2 = (r27 > r2 ? 1 : (r27 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x024d
        L_0x0248:
            long r2 = calculateCenter(r0)
            goto L_0x0251
        L_0x024d:
            long r2 = androidx.collection.FloatFloatPair.m1constructorimpl(r26, r27)
        L_0x0251:
            r0 = 32
            long r4 = r2 >> r0
            int r0 = (int) r4
            float r0 = java.lang.Float.intBitsToFloat(r0)
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r2 = r2 & r4
            int r2 = (int) r2
            float r2 = java.lang.Float.intBitsToFloat(r2)
            androidx.graphics.shapes.RoundedPolygon r3 = new androidx.graphics.shapes.RoundedPolygon
            r3.<init>(r1, r0, r2)
            return r3
        L_0x026b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The vertices array should have even size"
            r0.<init>(r1)
            throw r0
        L_0x0273:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Polygons must have at least 3 vertices"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.graphics.shapes.RoundedPolygonKt.RoundedPolygon(float[], androidx.graphics.shapes.CornerRounding, java.util.List, float, float):androidx.graphics.shapes.RoundedPolygon");
    }
}
