package A0;

import D0.e;
import J0.f;
import K0.a;
import K0.c;
import android.graphics.PointF;
import c0.C0086a;
import i.C0212a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends k {

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f10i;

    /* renamed from: j  reason: collision with root package name */
    public final Object f11j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(int i2, List list) {
        super(list);
        this.f10i = i2;
        switch (i2) {
            case 1:
                super(list);
                this.f11j = new PointF();
                return;
            case 2:
                super(list);
                this.f11j = new c();
                return;
            default:
                int i7 = 0;
                for (int i8 = 0; i8 < list.size(); i8++) {
                    E0.c cVar = (E0.c) ((a) list.get(i8)).b;
                    if (cVar != null) {
                        i7 = Math.max(i7, cVar.b.length);
                    }
                }
                this.f11j = new E0.c(new int[i7], new float[i7]);
                return;
        }
    }

    public final Object g(a aVar, float f) {
        Object obj;
        float f5;
        switch (this.f10i) {
            case 0:
                float f8 = f;
                E0.c cVar = (E0.c) this.f11j;
                E0.c cVar2 = (E0.c) aVar.b;
                E0.c cVar3 = (E0.c) aVar.f370c;
                int[] iArr = cVar.b;
                float[] fArr = cVar.f126a;
                boolean equals = cVar2.equals(cVar3);
                int[] iArr2 = cVar2.b;
                if (equals) {
                    cVar.a(cVar2);
                } else if (f8 <= 0.0f) {
                    cVar.a(cVar2);
                } else if (f8 >= 1.0f) {
                    cVar.a(cVar3);
                } else {
                    int length = iArr2.length;
                    int[] iArr3 = cVar3.b;
                    if (length == iArr3.length) {
                        for (int i2 = 0; i2 < iArr2.length; i2++) {
                            fArr[i2] = f.e(cVar2.f126a[i2], cVar3.f126a[i2], f8);
                            iArr[i2] = com.samsung.context.sdk.samsunganalytics.internal.sender.c.u(f8, iArr2[i2], iArr3[i2]);
                        }
                        for (int length2 = iArr2.length; length2 < fArr.length; length2++) {
                            fArr[length2] = fArr[iArr2.length - 1];
                            iArr[length2] = iArr[iArr2.length - 1];
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder("Cannot interpolate between gradients. Lengths vary (");
                        sb2.append(iArr2.length);
                        sb2.append(" vs ");
                        throw new IllegalArgumentException(C0086a.l(sb2, iArr3.length, ")"));
                    }
                }
                return cVar;
            case 1:
                float f10 = f;
                return l(aVar, f10, f10, f10);
            default:
                c cVar4 = (c) this.f11j;
                Object obj2 = aVar.b;
                if (obj2 == null || (obj = aVar.f370c) == null) {
                    throw new IllegalStateException("Missing values for keyframe.");
                }
                c cVar5 = (c) obj2;
                c cVar6 = (c) obj;
                e eVar = this.e;
                if (eVar != null) {
                    f5 = f;
                    c cVar7 = (c) eVar.L(aVar.g, aVar.f371h.floatValue(), cVar5, cVar6, f5, e(), this.d);
                    if (cVar7 != null) {
                        return cVar7;
                    }
                } else {
                    f5 = f;
                }
                float e = f.e(cVar5.f378a, cVar6.f378a, f5);
                float e7 = f.e(cVar5.b, cVar6.b, f5);
                cVar4.f378a = e;
                cVar4.b = e7;
                return cVar4;
        }
    }

    public /* bridge */ /* synthetic */ Object h(a aVar, float f, float f5, float f8) {
        switch (this.f10i) {
            case 1:
                return l(aVar, f, f5, f8);
            default:
                return super.h(aVar, f, f5, f8);
        }
    }

    public PointF l(a aVar, float f, float f5, float f8) {
        Object obj;
        PointF pointF = (PointF) this.f11j;
        Object obj2 = aVar.b;
        if (obj2 == null || (obj = aVar.f370c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF2 = (PointF) obj2;
        PointF pointF3 = (PointF) obj;
        e eVar = this.e;
        if (eVar != null) {
            PointF pointF4 = (PointF) eVar.L(aVar.g, aVar.f371h.floatValue(), pointF2, pointF3, f, e(), this.d);
            if (pointF4 != null) {
                return pointF4;
            }
        }
        float f10 = pointF2.x;
        float a7 = C0212a.a(pointF3.x, f10, f5, f10);
        float f11 = pointF2.y;
        pointF.set(a7, C0212a.a(pointF3.y, f11, f8, f11));
        return pointF;
    }
}
