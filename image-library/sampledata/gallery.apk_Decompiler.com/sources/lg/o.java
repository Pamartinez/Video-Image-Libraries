package lg;

import Ae.b;
import ig.a;
import java.util.Map;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;
import mg.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends k implements b {
    public static final o e = new o(1, 0);
    public static final o f = new o(1, 1);
    public final /* synthetic */ int d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(int i2, int i7) {
        super(i2);
        this.d = i7;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                a aVar = (a) obj;
                j.e(aVar, "$this$buildSerialDescriptor");
                a.a(aVar, "JsonPrimitive", new q(n.e));
                a.a(aVar, "JsonNull", new q(n.f));
                a.a(aVar, "JsonLiteral", new q(n.g));
                a.a(aVar, "JsonObject", new q(n.f4904h));
                a.a(aVar, "JsonArray", new q(n.f4905i));
                return x.f4917a;
            default:
                Map.Entry entry = (Map.Entry) obj;
                j.e(entry, "<name for destructuring parameter 0>");
                StringBuilder sb2 = new StringBuilder();
                t.a(sb2, (String) entry.getKey());
                sb2.append(':');
                sb2.append((l) entry.getValue());
                String sb3 = sb2.toString();
                j.d(sb3, "toString(...)");
                return sb3;
        }
    }
}
