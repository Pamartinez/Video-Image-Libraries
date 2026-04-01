package lg;

import Ed.b;
import kg.Q;
import kg.e0;
import mg.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class m {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f4903a = 0;

    static {
        Q.a(e0.f4693a, "kotlinx.serialization.json.JsonUnquotedLiteral");
    }

    public static final int a(C c5) {
        try {
            long j2 = new b(c5.i()).j();
            if (-2147483648L <= j2 && j2 <= 2147483647L) {
                return (int) j2;
            }
            throw new NumberFormatException(c5.i() + " is not an Int");
        } catch (d e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
