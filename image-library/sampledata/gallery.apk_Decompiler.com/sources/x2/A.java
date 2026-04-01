package X2;

import D0.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends u {
    public final r e(e eVar) {
        int indexOf;
        String a7 = u.a(eVar);
        String str = null;
        if ((!a7.startsWith("urlto:") && !a7.startsWith("URLTO:")) || (indexOf = a7.indexOf(58, 6)) < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = a7.substring(6, indexOf);
        }
        return new y(a7.substring(indexOf + 1), str);
    }
}
