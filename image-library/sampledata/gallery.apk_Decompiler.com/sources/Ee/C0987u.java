package ee;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

/* renamed from: ee.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0987u {
    public static final C0987u d = new C0987u();

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentSkipListMap f4311a = new ConcurrentSkipListMap();
    public final ConcurrentHashMap b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    public final ConcurrentHashMap f4312c = new ConcurrentHashMap();

    static {
        Logger.getLogger(C0987u.class.getName());
    }

    public C0987u() {
        new ConcurrentSkipListMap();
        new ConcurrentHashMap();
    }
}
