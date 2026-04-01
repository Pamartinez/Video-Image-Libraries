package ge;

import ee.F;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K0 extends W {

    /* renamed from: c  reason: collision with root package name */
    public static final ReferenceQueue f4453c = new ReferenceQueue();
    public static final ConcurrentHashMap d = new ConcurrentHashMap();
    public static final Logger e = Logger.getLogger(K0.class.getName());
    public final J0 b;

    public K0(F0 f02) {
        super(f02);
        this.b = new J0(this, f02, f4453c, d);
    }

    public final F h() {
        J0 j02 = this.b;
        if (!j02.e.getAndSet(true)) {
            j02.clear();
        }
        F0 f02 = this.f4486a;
        f02.n();
        return f02;
    }
}
