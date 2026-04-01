package A0;

import D1.g;
import K0.a;
import android.graphics.Path;
import android.graphics.PointF;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends a {
    public Path q;
    public final a r;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(C0332j jVar, a aVar) {
        super(jVar, (PointF) aVar.b, (PointF) aVar.f370c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.f371h);
        this.r = aVar;
        d();
    }

    public final void d() {
        boolean z;
        Object obj;
        Object obj2 = this.f370c;
        Object obj3 = this.b;
        if (obj2 == null || obj3 == null || !((PointF) obj3).equals(((PointF) obj2).x, ((PointF) obj2).y)) {
            z = false;
        } else {
            z = true;
        }
        if (obj3 != null && (obj = this.f370c) != null && !z) {
            PointF pointF = (PointF) obj3;
            PointF pointF2 = (PointF) obj;
            a aVar = this.r;
            PointF pointF3 = aVar.f374o;
            PointF pointF4 = aVar.f375p;
            g gVar = J0.g.f363a;
            Path path = new Path();
            path.moveTo(pointF.x, pointF.y);
            if (pointF3 == null || pointF4 == null || (pointF3.length() == 0.0f && pointF4.length() == 0.0f)) {
                path.lineTo(pointF2.x, pointF2.y);
            } else {
                float f = pointF.x;
                float f5 = pointF2.x;
                float f8 = pointF2.y;
                path.cubicTo(pointF3.x + f, pointF.y + pointF3.y, f5 + pointF4.x, f8 + pointF4.y, f5, f8);
            }
            this.q = path;
        }
    }
}
