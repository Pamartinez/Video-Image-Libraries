package sf;

import Tf.v;
import kotlin.jvm.internal.j;

/* renamed from: sf.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1294u extends w {
    public C1294u() {
        super("HTML", 1);
    }

    public final String a(String str) {
        j.e(str, "string");
        return v.s0(v.s0(str, "<", "&lt;"), ">", "&gt;");
    }
}
