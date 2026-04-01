package P1;

import Kd.a;
import android.location.Location;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final h f571a;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, P1.h] */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, Kd.a] */
    public c() {
        ? obj = new Object();
        obj.b = new Object();
        ? obj2 = new Object();
        obj2.e = new Object();
        obj.d = obj2;
        this.f571a = obj;
    }

    public final void a(Location location) {
        h hVar = this.f571a;
        synchronized (hVar.b) {
            hVar.n();
            hVar.f575a = true;
            hVar.f576c = location;
        }
        ((a) hVar.d).i(hVar);
    }

    public final void b(Exception exc) {
        h hVar = this.f571a;
        hVar.getClass();
        r.c(exc, "Exception must not be null");
        synchronized (hVar.b) {
            try {
                if (!hVar.f575a) {
                    hVar.f575a = true;
                    hVar.e = exc;
                    ((a) hVar.d).i(hVar);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
