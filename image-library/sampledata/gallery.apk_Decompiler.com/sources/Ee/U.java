package ee;

import F2.D0;
import F2.Y;
import ge.Q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class U {
    public static final Logger d = Logger.getLogger(U.class.getName());
    public static U e;

    /* renamed from: a  reason: collision with root package name */
    public String f4282a = "unknown";
    public final LinkedHashSet b = new LinkedHashSet();

    /* renamed from: c  reason: collision with root package name */
    public Y f4283c = D0.k;

    public static synchronized U b() {
        U u;
        synchronized (U.class) {
            try {
                if (e == null) {
                    Class<T> cls = T.class;
                    ArrayList arrayList = new ArrayList();
                    Class<Q> cls2 = Q.class;
                    boolean z = Q.f4471a;
                    arrayList.add(cls2);
                    List<T> a7 = C0971d.a(cls, Collections.unmodifiableList(arrayList), T.class.getClassLoader(), new Z(5));
                    if (a7.isEmpty()) {
                        d.warning("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
                    }
                    e = new U();
                    for (T t : a7) {
                        Logger logger = d;
                        logger.fine("Service loader found " + t);
                        e.a(t);
                    }
                    e.c();
                }
            } catch (ClassNotFoundException e7) {
                d.log(Level.FINE, "Unable to find DNS NameResolver", e7);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            u = e;
        }
        return u;
    }

    public final synchronized void a(T t) {
        t.getClass();
        this.b.add(t);
    }

    public final synchronized void c() {
        try {
            HashMap hashMap = new HashMap();
            String str = "unknown";
            Iterator it = this.b.iterator();
            char c5 = 0;
            while (it.hasNext()) {
                T t = (T) it.next();
                String a7 = t.a();
                if (((T) hashMap.get(a7)) == null) {
                    hashMap.put(a7, t);
                }
                if (c5 < 5) {
                    str = t.a();
                    c5 = 5;
                }
            }
            this.f4283c = Y.a(hashMap);
            this.f4282a = str;
        } finally {
            while (true) {
            }
        }
    }
}
