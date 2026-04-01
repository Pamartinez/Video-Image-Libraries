package Ze;

import Bf.e;
import Qe.C0814d;
import Qe.C0822l;
import Qe.C0825o;
import Qe.C0826p;
import Qe.C0827q;
import Qe.H;
import Te.B;
import Ue.a;
import Ue.b;
import Ue.c;
import java.util.HashMap;
import tf.C1301e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    public static final C0826p f3951a;
    public static final C0826p b;

    /* renamed from: c  reason: collision with root package name */
    public static final C0826p f3952c;
    public static final HashMap d;

    static {
        a aVar = a.f3825c;
        C0826p pVar = new C0826p(aVar, 9);
        f3951a = pVar;
        c cVar = c.f3827c;
        C0826p pVar2 = new C0826p(cVar, 10);
        b = pVar2;
        b bVar = b.f3826c;
        C0826p pVar3 = new C0826p(bVar, 11);
        f3952c = pVar3;
        HashMap hashMap = new HashMap();
        d = hashMap;
        hashMap.put(aVar, pVar);
        hashMap.put(cVar, pVar2);
        hashMap.put(bVar, pVar3);
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 5 || i2 == 6) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 5 || i2 == 6) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "from";
                break;
            case 2:
                objArr[0] = "first";
                break;
            case 3:
                objArr[0] = "second";
                break;
            case 4:
                objArr[0] = "visibility";
                break;
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
                break;
            default:
                objArr[0] = "what";
                break;
        }
        if (i2 == 5 || i2 == 6) {
            objArr[1] = "toDescriptorVisibility";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
        }
        if (i2 == 2 || i2 == 3) {
            objArr[2] = "areInSamePackage";
        } else if (i2 == 4) {
            objArr[2] = "toDescriptorVisibility";
        } else if (!(i2 == 5 || i2 == 6)) {
            objArr[2] = "isVisibleForProtectedAndPackage";
        }
        String format = String.format(str, objArr);
        if (i2 == 5 || i2 == 6) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static boolean b(e eVar, C0825o oVar, C0822l lVar) {
        C0825o oVar2;
        if (lVar != null) {
            if (oVar instanceof C0814d) {
                oVar2 = C1301e.t((C0814d) oVar);
            } else {
                int i2 = C1301e.f5137a;
                oVar2 = oVar;
            }
            if (c(oVar2, lVar)) {
                return true;
            }
            return C0827q.f3676c.a(eVar, oVar, lVar);
        }
        a(1);
        throw null;
    }

    public static boolean c(C0825o oVar, C0822l lVar) {
        if (oVar == null) {
            a(2);
            throw null;
        } else if (lVar != null) {
            Class<H> cls = H.class;
            H h5 = (H) C1301e.i(oVar, cls, false);
            H h6 = (H) C1301e.i(lVar, cls, false);
            if (h6 == null || h5 == null || !((B) h5).f3743i.equals(((B) h6).f3743i)) {
                return false;
            }
            return true;
        } else {
            a(3);
            throw null;
        }
    }
}
