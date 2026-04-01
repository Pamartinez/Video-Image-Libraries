package jf;

import B1.a;
import Df.q;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Jf.k;
import Jf.l;
import Tf.n;
import c0.C0086a;
import ef.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.j;
import lf.Q;
import yf.C1359c;

/* renamed from: jf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1108h implements q {
    public static final C1108h b = new Object();

    /* renamed from: c  reason: collision with root package name */
    public static final C1108h f4644c = new Object();
    public static final C1108h d = new Object();

    public static String[] a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add("<init>(" + str + ")V");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static C1113m c(String str) {
        C1359c cVar;
        j.e(str, "representation");
        char charAt = str.charAt(0);
        C1359c[] values = C1359c.values();
        int length = values.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                cVar = null;
                break;
            }
            cVar = values[i2];
            if (cVar.d().charAt(0) == charAt) {
                break;
            }
            i2++;
        }
        if (cVar != null) {
            return new C1112l(cVar);
        }
        if (charAt == 'V') {
            return new C1112l((C1359c) null);
        }
        if (charAt != '[') {
            if (charAt == 'L' && str.length() > 0) {
                boolean s = a.s(str.charAt(n.x0(str)), ';', false);
            }
            String substring = str.substring(1, str.length() - 1);
            j.d(substring, "substring(...)");
            return new C1111k(substring);
        }
        String substring2 = str.substring(1);
        j.d(substring2, "substring(...)");
        return new C1110j(c(substring2));
    }

    public static C1111k d(String str) {
        j.e(str, "internalName");
        return new C1111k(str);
    }

    public static LinkedHashSet e(String str, String... strArr) {
        j.e(str, "internalName");
        j.e(strArr, "signatures");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            linkedHashSet.add(str + '.' + str2);
        }
        return linkedHashSet;
    }

    public static LinkedHashSet f(String str, String... strArr) {
        j.e(strArr, "signatures");
        return e("java/lang/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static LinkedHashSet g(String str, String... strArr) {
        return e("java/util/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static String h(C1113m mVar) {
        String d2;
        j.e(mVar, "type");
        if (mVar instanceof C1110j) {
            return "[" + h(((C1110j) mVar).f4645i);
        } else if (mVar instanceof C1112l) {
            C1359c cVar = ((C1112l) mVar).f4647i;
            if (cVar == null || (d2 = cVar.d()) == null) {
                return "V";
            }
            return d2;
        } else if (mVar instanceof C1111k) {
            return C0086a.m(new StringBuilder("L"), ((C1111k) mVar).f4646i, ';');
        } else {
            throw new RuntimeException();
        }
    }

    public C0774x b(Q q, String str, B b5, B b8) {
        j.e(q, "proto");
        j.e(str, "flexibleId");
        j.e(b5, "lowerBound");
        j.e(b8, "upperBound");
        if (!str.equals("kotlin.jvm.PlatformType")) {
            return l.c(k.ERROR_FLEXIBLE_TYPE, str, b5.toString(), b8.toString());
        }
        if (q.k(of.k.g)) {
            return new i(b5, b8);
        }
        return C0754c.f(b5, b8);
    }
}
