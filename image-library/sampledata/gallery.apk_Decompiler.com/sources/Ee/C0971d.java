package ee;

import Hf.C0772v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/* renamed from: ee.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0971d {

    /* renamed from: a  reason: collision with root package name */
    public static final C0968a f4293a = new C0968a("io.grpc.Grpc.TRANSPORT_ATTR_REMOTE_ADDR");
    public static final C0968a b = new C0968a("io.grpc.Grpc.TRANSPORT_ATTR_LOCAL_ADDR");

    public static List a(Class cls, Iterable iterable, ClassLoader classLoader, Z z) {
        ArrayList arrayList;
        try {
            Class.forName("android.app.Application", false, classLoader);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Class cls2 = (Class) it.next();
                Object obj = null;
                try {
                    obj = cls2.asSubclass(cls).getConstructor((Class[]) null).newInstance((Object[]) null);
                } catch (ClassCastException unused) {
                } catch (Throwable th) {
                    throw new ServiceConfigurationError(String.format("Provider %s could not be instantiated %s", new Object[]{cls2.getName(), th}), th);
                }
                if (obj != null) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        } catch (Exception unused2) {
            ServiceLoader load = ServiceLoader.load(cls, classLoader);
            if (!load.iterator().hasNext()) {
                arrayList = ServiceLoader.load(cls);
            } else {
                arrayList = load;
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object next : arrayList) {
            switch (z.d) {
                case 3:
                    ((C0967D) next).getClass();
                    break;
                default:
                    ((T) next).getClass();
                    break;
            }
            arrayList3.add(next);
        }
        Collections.sort(arrayList3, Collections.reverseOrder(new C0772v(2, z)));
        return Collections.unmodifiableList(arrayList3);
    }

    public abstract void b(C0970c cVar, String str);

    public abstract void c(C0970c cVar, String str, Object... objArr);

    public abstract void d(a0 a0Var, M m);

    public abstract void e(a0 a0Var);

    public abstract void f(M m);

    public abstract void g(Object obj);

    public abstract void i(S s);

    public abstract C0964A j();

    public void h() {
    }
}
