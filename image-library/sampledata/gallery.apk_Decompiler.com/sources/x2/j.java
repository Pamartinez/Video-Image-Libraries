package X2;

import D0.e;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends C0061a {
    public static final Pattern f = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    public final r e(e eVar) {
        String[] b;
        String str;
        String a7 = u.a(eVar);
        String str2 = null;
        if (!a7.startsWith("MATMSG:") || (b = u.b("TO:", a7, ';', true)) == null) {
            return null;
        }
        for (String str3 : b) {
            if (str3 == null || !f.matcher(str3).matches() || str3.indexOf(64) < 0) {
                return null;
            }
        }
        String[] b5 = u.b("SUB:", a7, ';', false);
        if (b5 == null) {
            str = null;
        } else {
            str = b5[0];
        }
        String[] b8 = u.b("BODY:", a7, ';', false);
        if (b8 != null) {
            str2 = b8[0];
        }
        return new h(b, (String[]) null, (String[]) null, str, str2);
    }
}
