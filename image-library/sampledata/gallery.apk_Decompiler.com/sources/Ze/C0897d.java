package Ze;

import Ae.b;
import Df.C0736b;
import Ne.i;
import Qe.C0814d;
import Qe.C0831v;
import Te.K;
import Te.Q;
import a.C0068a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.z;
import xf.C1353d;

/* renamed from: Ze.d  reason: case insensitive filesystem */
public final class C0897d implements b {
    public static final C0897d e = new C0897d(0);
    public static final C0897d f = new C0897d(1);
    public static final C0897d g = new C0897d(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C0897d f3939h = new C0897d(3);

    /* renamed from: i  reason: collision with root package name */
    public static final C0897d f3940i = new C0897d(4);

    /* renamed from: j  reason: collision with root package name */
    public static final C0897d f3941j = new C0897d(5);
    public static final C0897d k = new C0897d(6);
    public final /* synthetic */ int d;

    public /* synthetic */ C0897d(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        C0814d b;
        String n;
        boolean z = false;
        switch (this.d) {
            case 0:
                C0814d dVar = (C0814d) obj;
                int i2 = C0898e.l;
                j.e(dVar, "it");
                return Boolean.valueOf(C1194l.G0(H.f, C0068a.n(dVar)));
            case 1:
                C0814d dVar2 = (C0814d) obj;
                int i7 = C0898e.l;
                j.e(dVar2, "it");
                if ((dVar2 instanceof C0831v) && C1194l.G0(H.f, C0068a.n(dVar2))) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 2:
                C0814d dVar3 = (C0814d) obj;
                j.e(dVar3, "it");
                return Boolean.valueOf(c.G(dVar3));
            case 3:
                return ((Q) obj).getType();
            case 4:
                C0814d dVar4 = (C0814d) obj;
                j.e(dVar4, "it");
                return Boolean.valueOf(c.G(C1353d.k(dVar4)));
            case 5:
                C0814d dVar5 = (C0814d) obj;
                j.e(dVar5, "it");
                int i8 = C0896c.l;
                K k10 = (K) dVar5;
                if (i.z(k10) && C1353d.b(k10, new C0736b(12, k10)) != null) {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                C0814d dVar6 = (C0814d) obj;
                j.e(dVar6, "it");
                if (i.z(dVar6)) {
                    int i10 = C0898e.l;
                    E e7 = null;
                    if (!(!H.e.contains(dVar6.getName()) || (b = C1353d.b(dVar6, f)) == null || (n = C0068a.n(b)) == null)) {
                        e7 = H.b.contains(n) ? E.ONE_COLLECTION_PARAMETER : ((G) z.Y(n, H.d)) == G.NULL ? E.OBJECT_PARAMETER_GENERIC : E.OBJECT_PARAMETER_NON_GENERIC;
                    }
                    if (e7 != null) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
        }
    }
}
