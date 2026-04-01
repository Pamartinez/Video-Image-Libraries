package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u00002\u00020\u0001:\u0002\n\u000bB\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/graphics/shapes/Feature;", "", "", "Landroidx/graphics/shapes/Cubic;", "cubics", "<init>", "(Ljava/util/List;)V", "Ljava/util/List;", "getCubics", "()Ljava/util/List;", "Corner", "Edge", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Feature {
    private final List<Cubic> cubics;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006\u0012\n\u0010\b\u001a\u00060\u0005j\u0002`\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR!\u0010\u0007\u001a\u00060\u0005j\u0002`\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R!\u0010\b\u001a\u00060\u0005j\u0002`\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/graphics/shapes/Feature$Corner;", "Landroidx/graphics/shapes/Feature;", "", "Landroidx/graphics/shapes/Cubic;", "cubics", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "vertex", "roundedCenter", "", "convex", "<init>", "(Ljava/util/List;JJZLkotlin/jvm/internal/e;)V", "", "toString", "()Ljava/lang/String;", "J", "getVertex-1ufDz9w", "()J", "getRoundedCenter-1ufDz9w", "Z", "getConvex", "()Z", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Corner extends Feature {
        private final boolean convex;
        private final long roundedCenter;
        private final long vertex;

        public /* synthetic */ Corner(List list, long j2, long j3, boolean z, e eVar) {
            this(list, j2, j3, z);
        }

        public String toString() {
            return "Corner: vertex=" + FloatFloatPair.m5toStringimpl(this.vertex) + ", center=" + FloatFloatPair.m5toStringimpl(this.roundedCenter) + ", convex=" + this.convex;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private Corner(List<? extends Cubic> list, long j2, long j3, boolean z) {
            super(list);
            j.e(list, "cubics");
            this.vertex = j2;
            this.roundedCenter = j3;
            this.convex = z;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/graphics/shapes/Feature$Edge;", "Landroidx/graphics/shapes/Feature;", "", "Landroidx/graphics/shapes/Cubic;", "cubics", "<init>", "(Ljava/util/List;)V", "", "toString", "()Ljava/lang/String;", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Edge extends Feature {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Edge(List<? extends Cubic> list) {
            super(list);
            j.e(list, "cubics");
        }

        public String toString() {
            return "Edge";
        }
    }

    public Feature(List<? extends Cubic> list) {
        j.e(list, "cubics");
        this.cubics = list;
    }

    public final List<Cubic> getCubics() {
        return this.cubics;
    }
}
