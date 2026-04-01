package P2;

import N2.j;
import U2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class t extends I {
    public final void k() {
        String str;
        if (c().size() > 65536) {
            if (this instanceof v) {
                str = "methods";
            } else {
                str = "fields";
            }
            StringBuilder k = j.k("Too many ", str, ": ");
            k.append(c().size());
            k.append("; max is 65536");
            throw new c(k.toString(), (Exception) null);
        }
        int i2 = 0;
        for (s g : c()) {
            g.g(i2);
            i2++;
        }
    }
}
