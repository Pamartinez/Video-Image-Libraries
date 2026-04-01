package ig;

import Ae.b;
import L1.d;
import android.graphics.Rect;
import android.graphics.RectF;
import kg.P;
import kg.T;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;
import n2.C0244g;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends k implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(int i2, Object obj) {
        super(1);
        this.d = i2;
        this.e = obj;
    }

    /* JADX WARNING: type inference failed for: r2v19, types: [Ae.b, kotlin.jvm.internal.k] */
    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                int intValue = ((Number) obj).intValue();
                StringBuilder sb2 = new StringBuilder();
                h hVar = (h) this.e;
                sb2.append(hVar.f[intValue]);
                sb2.append(": ");
                sb2.append(hVar.g[intValue].i());
                return sb2.toString();
            case 1:
                a aVar = (a) obj;
                j.e(aVar, "$this$buildSerialDescriptor");
                ((P) this.e).getClass();
                aVar.b = C1202t.d;
                return x.f4917a;
            case 2:
                int intValue2 = ((Number) obj).intValue();
                StringBuilder sb3 = new StringBuilder();
                T t = (T) this.e;
                sb3.append(t.e[intValue2]);
                sb3.append(": ");
                sb3.append(t.h(intValue2).i());
                return sb3.toString();
            case 3:
                RectF rectF = (RectF) obj;
                j.e(rectF, "it");
                C0244g gVar = (C0244g) this.e;
                if (!gVar.b.isEmpty()) {
                    gVar.f1848a.invoke(rectF);
                }
                return x.f4917a;
            default:
                RectF rectF2 = (RectF) obj;
                j.e(rectF2, "rectF");
                q2.k kVar = (q2.k) this.e;
                kVar.g.invoke();
                Rect rect = new Rect();
                rectF2.roundOut(rect);
                d.v(rect, kVar);
                return x.f4917a;
        }
    }
}
