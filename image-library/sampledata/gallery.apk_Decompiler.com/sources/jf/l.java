package Jf;

import B1.a;
import Hf.M;
import Qe.C0822l;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public static final l f3482a = new Object();
    public static final e b = e.d;

    /* renamed from: c  reason: collision with root package name */
    public static final a f3483c = new a(C1240g.g(String.format(b.ERROR_CLASS.a(), Arrays.copyOf(new Object[]{"unknown class"}, 1))));
    public static final i d = c(k.CYCLIC_SUPERTYPES, new String[0]);
    public static final i e = c(k.ERROR_PROPERTY_TYPE, new String[0]);
    public static final Set f = a.S(new f());

    public static final g a(h hVar, boolean z, String... strArr) {
        j.e(hVar, "kind");
        j.e(strArr, "formatParams");
        if (!z) {
            return new g(hVar, (String[]) Arrays.copyOf(strArr, strArr.length));
        }
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        j.e(strArr2, "formatParams");
        return new g(hVar, (String[]) Arrays.copyOf(strArr2, strArr2.length));
    }

    public static final g b(h hVar, String... strArr) {
        j.e(hVar, "kind");
        return a(hVar, false, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final i c(k kVar, String... strArr) {
        j.e(kVar, "kind");
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        j.e(strArr2, "formatParams");
        return e(kVar, C1202t.d, d(kVar, (String[]) Arrays.copyOf(strArr2, strArr2.length)), (String[]) Arrays.copyOf(strArr2, strArr2.length));
    }

    public static j d(k kVar, String... strArr) {
        j.e(kVar, "kind");
        j.e(strArr, "formatParams");
        return new j(kVar, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static i e(k kVar, List list, M m, String... strArr) {
        j.e(kVar, "kind");
        j.e(strArr, "formatParams");
        return new i(m, b(h.ERROR_TYPE_SCOPE, m.toString()), kVar, list, false, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final boolean f(C0822l lVar) {
        if (lVar == null) {
            return false;
        }
        if ((lVar instanceof a) || (lVar.g() instanceof a) || lVar == b) {
            return true;
        }
        return false;
    }
}
