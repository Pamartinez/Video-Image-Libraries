package Qf;

import Ae.b;
import G0.e;
import Pe.p;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    public static final i f3686a = new Object();

    public static final void a(AbstractCollection abstractCollection, Object obj) {
        if (obj != null) {
            abstractCollection.add(obj);
        }
    }

    public static final List d(ArrayList arrayList) {
        j.e(arrayList, "<this>");
        int size = arrayList.size();
        if (size == 0) {
            return C1202t.d;
        }
        if (size == 1) {
            return C0246a.e0(C1194l.L0(arrayList));
        }
        arrayList.trimToSize();
        return arrayList;
    }

    public static h e() {
        return new h(0);
    }

    public static Object f(Collection collection, a aVar, k kVar) {
        e eVar = new e(6);
        for (Object g : collection) {
            g(g, aVar, eVar, kVar);
        }
        return kVar.j();
    }

    public static void g(Object obj, a aVar, e eVar, k kVar) {
        if (obj == null) {
            Object[] objArr = new Object[3];
            switch (22) {
                case 1:
                case 5:
                case 8:
                case 11:
                case 15:
                case 18:
                case 21:
                case 23:
                    objArr[0] = "neighbors";
                    break;
                case 2:
                case 12:
                case 16:
                case 19:
                case 24:
                    objArr[0] = "visited";
                    break;
                case 3:
                case 6:
                case 13:
                case 25:
                    objArr[0] = "handler";
                    break;
                case 9:
                    objArr[0] = "predicate";
                    break;
                case 10:
                case 14:
                    objArr[0] = "node";
                    break;
                case 22:
                    objArr[0] = "current";
                    break;
                default:
                    objArr[0] = "nodes";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS";
            switch (22) {
                case 7:
                case 8:
                case 9:
                    objArr[2] = "ifAny";
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    objArr[2] = "dfsFromNode";
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    objArr[2] = "topologicalOrder";
                    break;
                case 22:
                case 23:
                case 24:
                case 25:
                    objArr[2] = "doDfs";
                    break;
                default:
                    objArr[2] = "dfs";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        } else if (((HashSet) eVar.d).add(obj) && kVar.c(obj)) {
            for (Object g : aVar.a(obj)) {
                g(g, aVar, eVar, kVar);
            }
            kVar.b(obj);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [boolean[], java.io.Serializable] */
    public static Boolean h(Collection collection, a aVar, b bVar) {
        return (Boolean) f(collection, aVar, new p(bVar, new boolean[1], 1));
    }

    public static final boolean i(Throwable th) {
        Class cls = th.getClass();
        while (!j.a(cls.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            cls = cls.getSuperclass();
            if (cls == null) {
                return false;
            }
        }
        return true;
    }

    public static void k(Object obj) {
        if (obj instanceof j) {
            throw ((j) obj).f3685a;
        }
    }

    public abstract boolean c(Object obj);

    public abstract Object j();

    public void b(Object obj) {
    }
}
