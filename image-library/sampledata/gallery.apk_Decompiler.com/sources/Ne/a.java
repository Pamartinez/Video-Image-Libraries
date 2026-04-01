package Ne;

import Gf.m;
import java.util.ServiceLoader;
import kotlin.jvm.internal.j;
import ne.C1194l;

public final class a implements Ae.a {
    public static final a e = new a(0);
    public static final a f = new a(1);
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                b bVar = b.f3543a;
                Class<c> cls = c.class;
                ServiceLoader<S> load = ServiceLoader.load(cls, cls.getClassLoader());
                j.b(load);
                c cVar = (c) C1194l.M0(load);
                if (cVar != null) {
                    return cVar;
                }
                throw new IllegalStateException("No BuiltInsLoader implementation was found. Please ensure that the META-INF/services/ is not stripped from your application and that the Java virtual machine is not running under a security manager");
            default:
                i iVar = new i(new m("DefaultBuiltIns"));
                iVar.c();
                return iVar;
        }
    }
}
