package ee;

import He.F;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: ee.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0990x {
    public static final AtomicLong d = new AtomicLong();

    /* renamed from: a  reason: collision with root package name */
    public final String f4314a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final long f4315c;

    public C0990x(String str, String str2, long j2) {
        F.n(str, "typeName");
        F.i("empty type", !str.isEmpty());
        this.f4314a = str;
        this.b = str2;
        this.f4315c = j2;
    }

    public static C0990x a(Class cls, String str) {
        String simpleName = cls.getSimpleName();
        if (simpleName.isEmpty()) {
            simpleName = cls.getName().substring(cls.getPackage().getName().length() + 1);
        }
        return new C0990x(simpleName, str, d.incrementAndGet());
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f4314a + "<" + this.f4315c + ">");
        String str = this.b;
        if (str != null) {
            sb2.append(": (");
            sb2.append(str);
            sb2.append(')');
        }
        return sb2.toString();
    }
}
