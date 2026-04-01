package androidx.graphics.shapes;

import Ae.b;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB'\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016¨\u0006\u001f"}, d2 = {"Landroidx/graphics/shapes/RoundedPolygon;", "", "", "Landroidx/graphics/shapes/Feature;", "features", "", "centerX", "centerY", "<init>", "(Ljava/util/List;FF)V", "", "toString", "()Ljava/lang/String;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Ljava/util/List;", "getFeatures$graphics_shapes_release", "()Ljava/util/List;", "F", "getCenterX", "()F", "getCenterY", "Landroidx/graphics/shapes/Cubic;", "cubics", "getCubics", "Companion", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RoundedPolygon {
    public static final Companion Companion = new Companion((e) null);
    private final float centerX;
    private final float centerY;
    private final List<Cubic> cubics;
    private final List<Feature> features;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/graphics/shapes/RoundedPolygon$Companion;", "", "()V", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00eb A[LOOP:0: B:9:0x008e->B:31:0x00eb, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009e A[EDGE_INSN: B:46:0x009e->B:15:0x009e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RoundedPolygon(java.util.List<? extends androidx.graphics.shapes.Feature> r18, float r19, float r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "features"
            kotlin.jvm.internal.j.e(r1, r2)
            r0.<init>()
            r0.features = r1
            r2 = r19
            r0.centerX = r2
            r2 = r20
            r0.centerY = r2
            oe.c r2 = o1.C0246a.R()
            int r3 = r1.size()
            r4 = 1
            r5 = 0
            r6 = 0
            if (r3 <= 0) goto L_0x0084
            java.lang.Object r3 = r1.get(r5)
            androidx.graphics.shapes.Feature r3 = (androidx.graphics.shapes.Feature) r3
            java.util.List r3 = r3.getCubics()
            int r3 = r3.size()
            r7 = 3
            if (r3 != r7) goto L_0x0084
            java.lang.Object r3 = r1.get(r5)
            androidx.graphics.shapes.Feature r3 = (androidx.graphics.shapes.Feature) r3
            java.util.List r3 = r3.getCubics()
            java.lang.Object r3 = r3.get(r4)
            androidx.graphics.shapes.Cubic r3 = (androidx.graphics.shapes.Cubic) r3
            r7 = 1056964608(0x3f000000, float:0.5)
            me.i r3 = r3.split(r7)
            java.lang.Object r7 = r3.d
            androidx.graphics.shapes.Cubic r7 = (androidx.graphics.shapes.Cubic) r7
            java.lang.Object r3 = r3.e
            androidx.graphics.shapes.Cubic r3 = (androidx.graphics.shapes.Cubic) r3
            r8 = 2
            androidx.graphics.shapes.Cubic[] r9 = new androidx.graphics.shapes.Cubic[r8]
            java.lang.Object r10 = r1.get(r5)
            androidx.graphics.shapes.Feature r10 = (androidx.graphics.shapes.Feature) r10
            java.util.List r10 = r10.getCubics()
            java.lang.Object r10 = r10.get(r5)
            r9[r5] = r10
            r9[r4] = r7
            java.util.ArrayList r7 = ne.C1195m.s0(r9)
            androidx.graphics.shapes.Cubic[] r9 = new androidx.graphics.shapes.Cubic[r8]
            r9[r5] = r3
            java.lang.Object r3 = r1.get(r5)
            androidx.graphics.shapes.Feature r3 = (androidx.graphics.shapes.Feature) r3
            java.util.List r3 = r3.getCubics()
            java.lang.Object r3 = r3.get(r8)
            r9[r4] = r3
            java.util.ArrayList r3 = ne.C1195m.s0(r9)
            goto L_0x0086
        L_0x0084:
            r3 = r6
            r7 = r3
        L_0x0086:
            int r1 = r1.size()
            if (r1 < 0) goto L_0x00ee
            r9 = r5
            r8 = r6
        L_0x008e:
            if (r9 != 0) goto L_0x0094
            if (r3 == 0) goto L_0x0094
            r10 = r3
            goto L_0x00af
        L_0x0094:
            java.util.List<androidx.graphics.shapes.Feature> r10 = r0.features
            int r10 = r10.size()
            if (r9 != r10) goto L_0x00a3
            if (r7 != 0) goto L_0x00a1
        L_0x009e:
            r1 = r6
            r6 = r8
            goto L_0x00ef
        L_0x00a1:
            r10 = r7
            goto L_0x00af
        L_0x00a3:
            java.util.List<androidx.graphics.shapes.Feature> r10 = r0.features
            java.lang.Object r10 = r10.get(r9)
            androidx.graphics.shapes.Feature r10 = (androidx.graphics.shapes.Feature) r10
            java.util.List r10 = r10.getCubics()
        L_0x00af:
            int r11 = r10.size()
            r12 = r5
        L_0x00b4:
            if (r12 >= r11) goto L_0x00e9
            java.lang.Object r13 = r10.get(r12)
            androidx.graphics.shapes.Cubic r13 = (androidx.graphics.shapes.Cubic) r13
            boolean r14 = r13.zeroLength$graphics_shapes_release()
            if (r14 != 0) goto L_0x00ce
            if (r8 == 0) goto L_0x00c7
            r2.add(r8)
        L_0x00c7:
            if (r6 != 0) goto L_0x00cc
            r6 = r13
            r8 = r6
            goto L_0x00e6
        L_0x00cc:
            r8 = r13
            goto L_0x00e6
        L_0x00ce:
            if (r8 == 0) goto L_0x00e6
            float[] r14 = r8.getPoints$graphics_shapes_release()
            r15 = 6
            float r16 = r13.getAnchor1X()
            r14[r15] = r16
            float[] r14 = r8.getPoints$graphics_shapes_release()
            r15 = 7
            float r13 = r13.getAnchor1Y()
            r14[r15] = r13
        L_0x00e6:
            int r12 = r12 + 1
            goto L_0x00b4
        L_0x00e9:
            if (r9 == r1) goto L_0x009e
            int r9 = r9 + 1
            goto L_0x008e
        L_0x00ee:
            r1 = r6
        L_0x00ef:
            if (r6 == 0) goto L_0x011a
            if (r1 == 0) goto L_0x011a
            float r7 = r6.getAnchor0X()
            float r8 = r6.getAnchor0Y()
            float r9 = r6.getControl0X()
            float r10 = r6.getControl0Y()
            float r11 = r6.getControl1X()
            float r12 = r6.getControl1Y()
            float r13 = r1.getAnchor0X()
            float r14 = r1.getAnchor0Y()
            androidx.graphics.shapes.Cubic r1 = androidx.graphics.shapes.CubicKt.Cubic(r7, r8, r9, r10, r11, r12, r13, r14)
            r2.add(r1)
        L_0x011a:
            oe.c r1 = o1.C0246a.K(r2)
            r0.cubics = r1
            int r2 = r1.p()
            int r2 = r2 - r4
            java.lang.Object r2 = r1.get(r2)
            int r1 = r1.p()
        L_0x012d:
            if (r5 >= r1) goto L_0x016a
            java.util.List<androidx.graphics.shapes.Cubic> r3 = r0.cubics
            java.lang.Object r3 = r3.get(r5)
            androidx.graphics.shapes.Cubic r3 = (androidx.graphics.shapes.Cubic) r3
            float r4 = r3.getAnchor0X()
            androidx.graphics.shapes.Cubic r2 = (androidx.graphics.shapes.Cubic) r2
            float r6 = r2.getAnchor1X()
            float r4 = r4 - r6
            float r4 = java.lang.Math.abs(r4)
            r6 = 953267991(0x38d1b717, float:1.0E-4)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x0162
            float r4 = r3.getAnchor0Y()
            float r2 = r2.getAnchor1Y()
            float r4 = r4 - r2
            float r2 = java.lang.Math.abs(r4)
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x0162
            int r5 = r5 + 1
            r2 = r3
            goto L_0x012d
        L_0x0162:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "RoundedPolygon must be contiguous, with the anchor points of all curves matching the anchor points of the preceding and succeeding cubics"
            r0.<init>(r1)
            throw r0
        L_0x016a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.graphics.shapes.RoundedPolygon.<init>(java.util.List, float, float):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundedPolygon)) {
            return false;
        }
        return j.a(this.features, ((RoundedPolygon) obj).features);
    }

    public final List<Cubic> getCubics() {
        return this.cubics;
    }

    public int hashCode() {
        return this.features.hashCode();
    }

    public String toString() {
        return "[RoundedPolygon. Cubics = " + C1194l.R0(this.cubics, (String) null, (String) null, (String) null, (b) null, 63) + " || Features = " + C1194l.R0(this.features, (String) null, (String) null, (String) null, (b) null, 63) + " || Center = (" + this.centerX + ArcCommonLog.TAG_COMMA + this.centerY + ")]";
    }
}
