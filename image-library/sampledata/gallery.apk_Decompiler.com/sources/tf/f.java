package Tf;

import com.samsung.scsp.common.Header;
import i.C0212a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {
    public static final f d;

    /* renamed from: a  reason: collision with root package name */
    public final boolean f3820a;
    public final d b;

    /* renamed from: c  reason: collision with root package name */
    public final e f3821c;

    static {
        d dVar = d.f3818a;
        e eVar = e.b;
        d = new f(false, dVar, eVar);
        new f(true, dVar, eVar);
    }

    public f(boolean z, d dVar, e eVar) {
        j.e(dVar, Header.BYTES);
        j.e(eVar, "number");
        this.f3820a = z;
        this.b = dVar;
        this.f3821c = eVar;
    }

    public final String toString() {
        StringBuilder s = C0212a.s("HexFormat(\n    upperCase = ");
        s.append(this.f3820a);
        s.append(",\n    bytes = BytesHexFormat(\n");
        this.b.a(s, "        ");
        s.append(10);
        s.append("    ),");
        s.append(10);
        s.append("    number = NumberHexFormat(");
        s.append(10);
        this.f3821c.a(s, "        ");
        s.append(10);
        s.append("    )");
        s.append(10);
        s.append(")");
        return s.toString();
    }
}
