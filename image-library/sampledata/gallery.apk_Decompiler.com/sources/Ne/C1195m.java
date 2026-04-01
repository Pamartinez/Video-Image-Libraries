package ne;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import o1.C0246a;

/* renamed from: ne.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1195m extends C0246a {
    public static ArrayList o0(Object... objArr) {
        if (objArr.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(new C1190h(objArr, true));
    }

    public static int p0(List list) {
        j.e(list, "<this>");
        return list.size() - 1;
    }

    public static List q0(Object... objArr) {
        j.e(objArr, "elements");
        if (objArr.length > 0) {
            return C1192j.a0(objArr);
        }
        return C1202t.d;
    }

    public static List r0(Object obj) {
        if (obj != null) {
            return C0246a.e0(obj);
        }
        return C1202t.d;
    }

    public static ArrayList s0(Object... objArr) {
        if (objArr.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(new C1190h(objArr, true));
    }

    public static final List t0(List list) {
        int size = list.size();
        if (size == 0) {
            return C1202t.d;
        }
        if (size != 1) {
            return list;
        }
        return C0246a.e0(list.get(0));
    }

    public static void u0() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void v0() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
