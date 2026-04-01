package ge;

import L2.a;
import ee.C0967D;
import ee.C0968a;
import ee.E;
import ee.Q;
import ee.Y;
import ee.a0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class I1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C0968a f4447a = new C0968a("io.grpc.internal.GrpcAttributes.securityLevel");
    public static final C0968a b = new C0968a("io.grpc.internal.GrpcAttributes.clientEagAttrs");

    public static Set a(String str, Map map) {
        Y y;
        boolean z;
        List c5 = C1043m0.c(str, map);
        if (c5 == null) {
            return null;
        }
        EnumSet<E> noneOf = EnumSet.noneOf(Y.class);
        for (Object next : c5) {
            if (next instanceof Double) {
                Double d = (Double) next;
                int intValue = d.intValue();
                boolean z3 = false;
                if (((double) intValue) == d.doubleValue()) {
                    z = true;
                } else {
                    z = false;
                }
                a.G("Status code %s is not integral", next, z);
                y = a0.d(intValue).f4290a;
                if (y.c() == d.intValue()) {
                    z3 = true;
                }
                a.G("Status code %s is not valid", next, z3);
            } else if (next instanceof String) {
                try {
                    y = Y.valueOf((String) next);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Status code " + next + " is not valid", e);
                }
            } else {
                throw new RuntimeException("Can not convert status code " + next + " to Status.Code, because its type is " + next.getClass());
            }
            noneOf.add(y);
        }
        return Collections.unmodifiableSet(noneOf);
    }

    public static List b(Map map) {
        String h5;
        ArrayList arrayList = new ArrayList();
        if (map.containsKey("loadBalancingConfig")) {
            List c5 = C1043m0.c("loadBalancingConfig", map);
            if (c5 == null) {
                c5 = null;
            } else {
                C1043m0.a(c5);
            }
            arrayList.addAll(c5);
        }
        if (arrayList.isEmpty() && (h5 = C1043m0.h("loadBalancingPolicy", map)) != null) {
            arrayList.add(Collections.singletonMap(h5.toLowerCase(Locale.ROOT), Collections.EMPTY_MAP));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static Q e(List list, E e) {
        Q q;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            G1 g12 = (G1) it.next();
            String str = g12.f4432a;
            C0967D a7 = e.a(str);
            if (a7 == null) {
                arrayList.add(str);
            } else {
                if (!arrayList.isEmpty()) {
                    Logger.getLogger(I1.class.getName()).log(Level.FINEST, "{0} specified by Service Config are not available", arrayList);
                }
                try {
                    q = new Q((Object) new Y0(C1043m0.b("shuffleAddressList", g12.b)));
                } catch (RuntimeException e7) {
                    q = new Q(a0.f4289o.f(e7).g("Failed parsing configuration for pick_first"));
                }
                if (q.f4279a != null) {
                    return q;
                }
                return new Q((Object) new H1(a7, q.b));
            }
        }
        a0 a0Var = a0.g;
        return new Q(a0Var.g("None of " + arrayList + " specified by Service Config are available."));
    }

    public static List f(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.size() == 1) {
                String str = (String) ((Map.Entry) map.entrySet().iterator().next()).getKey();
                arrayList.add(new G1(str, C1043m0.g(str, map)));
            } else {
                throw new RuntimeException("There are " + map.size() + " fields in a LoadBalancingConfig object. Exactly one is expected. Config=" + map);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public abstract boolean c(F1 f12);

    public abstract void d(F1 f12);
}
