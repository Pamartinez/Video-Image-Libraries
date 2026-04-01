package C0;

import android.graphics.PointF;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final PointF f85a;
    public final PointF b;

    /* renamed from: c  reason: collision with root package name */
    public final PointF f86c;

    public a() {
        this.f85a = new PointF();
        this.b = new PointF();
        this.f86c = new PointF();
    }

    public final String toString() {
        PointF pointF = this.f86c;
        Float valueOf = Float.valueOf(pointF.x);
        Float valueOf2 = Float.valueOf(pointF.y);
        PointF pointF2 = this.f85a;
        Float valueOf3 = Float.valueOf(pointF2.x);
        Float valueOf4 = Float.valueOf(pointF2.y);
        PointF pointF3 = this.b;
        return String.format("v=%.2f,%.2f cp1=%.2f,%.2f cp2=%.2f,%.2f", new Object[]{valueOf, valueOf2, valueOf3, valueOf4, Float.valueOf(pointF3.x), Float.valueOf(pointF3.y)});
    }

    public a(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f85a = pointF;
        this.b = pointF2;
        this.f86c = pointF3;
    }
}
