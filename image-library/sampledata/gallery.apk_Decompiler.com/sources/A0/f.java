package A0;

import C0.c;
import D0.e;
import K0.a;
import android.graphics.PointF;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends k {

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f4i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i2, List list) {
        super(list);
        this.f4i = i2;
    }

    public final Object g(a aVar, float f) {
        Object obj;
        float f5;
        c cVar;
        switch (this.f4i) {
            case 0:
                return Integer.valueOf(l(aVar, f));
            case 1:
                return Integer.valueOf(l(aVar, f));
            default:
                Object obj2 = aVar.b;
                e eVar = this.e;
                if (eVar != null) {
                    float f8 = aVar.g;
                    Float f10 = aVar.f371h;
                    if (f10 == null) {
                        f5 = Float.MAX_VALUE;
                    } else {
                        f5 = f10.floatValue();
                    }
                    c cVar2 = (c) obj2;
                    Object obj3 = aVar.f370c;
                    if (obj3 == null) {
                        cVar = cVar2;
                    } else {
                        cVar = (c) obj3;
                    }
                    return (c) eVar.L(f8, f5, cVar2, cVar, f, d(), this.d);
                } else if (f != 1.0f || (obj = aVar.f370c) == null) {
                    return (c) obj2;
                } else {
                    return (c) obj;
                }
        }
    }

    public int l(a aVar, float f) {
        Float f5;
        Integer num;
        int i2;
        float f8;
        switch (this.f4i) {
            case 0:
                float f10 = f;
                Object obj = aVar.b;
                Object obj2 = aVar.b;
                if (obj == null || aVar.f370c == null) {
                    throw new IllegalStateException("Missing values for keyframe.");
                }
                e eVar = this.e;
                if (eVar == null || (f5 = aVar.f371h) == null || (num = (Integer) eVar.L(aVar.g, f5.floatValue(), (Integer) obj2, (Integer) aVar.f370c, f10, e(), this.d)) == null) {
                    return com.samsung.context.sdk.samsunganalytics.internal.sender.c.u(J0.f.b(f10, 0.0f, 1.0f), ((Integer) obj2).intValue(), ((Integer) aVar.f370c).intValue());
                }
                return num.intValue();
            default:
                Object obj3 = aVar.b;
                Object obj4 = aVar.b;
                if (obj3 != null) {
                    Object obj5 = aVar.f370c;
                    if (obj5 == null) {
                        if (aVar.k == 784923401) {
                            aVar.k = ((Integer) obj3).intValue();
                        }
                        i2 = aVar.k;
                    } else {
                        if (aVar.l == 784923401) {
                            aVar.l = ((Integer) obj5).intValue();
                        }
                        i2 = aVar.l;
                    }
                    e eVar2 = this.e;
                    if (eVar2 != null) {
                        f8 = f;
                        Integer num2 = (Integer) eVar2.L(aVar.g, aVar.f371h.floatValue(), (Integer) obj4, Integer.valueOf(i2), f8, e(), this.d);
                        if (num2 != null) {
                            return num2.intValue();
                        }
                    } else {
                        f8 = f;
                    }
                    if (aVar.k == 784923401) {
                        aVar.k = ((Integer) obj4).intValue();
                    }
                    int i7 = aVar.k;
                    PointF pointF = J0.f.f362a;
                    return (int) ((f8 * ((float) (i2 - i7))) + ((float) i7));
                }
                throw new IllegalStateException("Missing values for keyframe.");
        }
    }
}
