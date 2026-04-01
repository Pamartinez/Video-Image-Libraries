package A0;

import D0.e;
import K0.a;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends k {

    /* renamed from: i  reason: collision with root package name */
    public final PointF f13i = new PointF();

    /* renamed from: j  reason: collision with root package name */
    public final float[] f14j = new float[2];
    public final float[] k = new float[2];
    public final PathMeasure l = new PathMeasure();
    public m m;

    public n(ArrayList arrayList) {
        super(arrayList);
    }

    public final Object g(a aVar, float f) {
        float f5;
        m mVar = (m) aVar;
        Path path = mVar.q;
        if (path == null) {
            return (PointF) aVar.b;
        }
        e eVar = this.e;
        if (eVar != null) {
            f5 = f;
            PointF pointF = (PointF) eVar.L(mVar.g, mVar.f371h.floatValue(), (PointF) mVar.b, (PointF) mVar.f370c, e(), f5, this.d);
            if (pointF != null) {
                return pointF;
            }
        } else {
            f5 = f;
        }
        m mVar2 = this.m;
        PathMeasure pathMeasure = this.l;
        if (mVar2 != mVar) {
            pathMeasure.setPath(path, false);
            this.m = mVar;
        }
        float length = pathMeasure.getLength();
        float f8 = f5 * length;
        float[] fArr = this.f14j;
        float[] fArr2 = this.k;
        pathMeasure.getPosTan(f8, fArr, fArr2);
        float f10 = fArr[0];
        float f11 = fArr[1];
        PointF pointF2 = this.f13i;
        pointF2.set(f10, f11);
        if (f8 < 0.0f) {
            pointF2.offset(fArr2[0] * f8, fArr2[1] * f8);
            return pointF2;
        }
        if (f8 > length) {
            float f12 = f8 - length;
            pointF2.offset(fArr2[0] * f12, fArr2[1] * f12);
        }
        return pointF2;
    }
}
