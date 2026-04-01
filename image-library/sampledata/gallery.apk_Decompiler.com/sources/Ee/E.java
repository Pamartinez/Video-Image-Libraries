package ee;

import He.F;
import ge.C1011b1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E {

    /* renamed from: c  reason: collision with root package name */
    public static final Logger f4270c;
    public static E d;
    public static final List e;

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashSet f4271a = new LinkedHashSet();
    public final LinkedHashMap b = new LinkedHashMap();

    static {
        Logger logger = Logger.getLogger(E.class.getName());
        f4270c = logger;
        ArrayList arrayList = new ArrayList();
        Class<C1011b1> cls = C1011b1.class;
        try {
            boolean z = C1011b1.f4497a;
            arrayList.add(cls);
        } catch (ClassNotFoundException e7) {
            logger.log(Level.WARNING, "Unable to find pick-first LoadBalancer", e7);
        }
        try {
            arrayList.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e8) {
            logger.log(Level.FINE, "Unable to find round-robin LoadBalancer", e8);
        }
        e = Collections.unmodifiableList(arrayList);
    }

    public final synchronized C0967D a(String str) {
        LinkedHashMap linkedHashMap;
        linkedHashMap = this.b;
        F.n(str, "policy");
        return (C0967D) linkedHashMap.get(str);
    }

    public final synchronized void b() {
        this.b.clear();
        Iterator it = this.f4271a.iterator();
        while (it.hasNext()) {
            C0967D d2 = (C0967D) it.next();
            d2.getClass();
            if (((C0967D) this.b.get("pick_first")) == null) {
                this.b.put("pick_first", d2);
            }
        }
    }
}
