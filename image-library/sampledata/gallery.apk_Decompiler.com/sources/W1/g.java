package w1;

import O1.a;
import t1.C0276a;
import u1.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements C0315b {
    public static g b;

    /* renamed from: c  reason: collision with root package name */
    public static final h f2006c = new h(0, false, false, 0, 0);

    /* renamed from: a  reason: collision with root package name */
    public Object f2007a;

    public /* synthetic */ g(Object obj) {
        this.f2007a = obj;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [w1.g, java.lang.Object] */
    public static synchronized g b() {
        g gVar;
        synchronized (g.class) {
            try {
                if (b == null) {
                    b = new Object();
                }
                gVar = b;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return gVar;
    }

    public void a(C0276a aVar) {
        a aVar2 = (a) this.f2007a;
        if (aVar.e == 0) {
            aVar2.d((e) null, aVar2.w);
            return;
        }
        g gVar = aVar2.f1343o;
        if (gVar != null) {
            ((h) gVar.f2007a).a(aVar);
        }
    }
}
