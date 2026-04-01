package ee;

import E2.g;
import He.F;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends I {
    public final H e;

    public G(String str, H h5) {
        super(str, h5, false);
        F.l(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", str, "-bin");
        F.n(h5, "marshaller");
        this.e = h5;
    }

    public final byte[] b(Object obj) {
        String a7 = this.e.a(obj);
        F.n(a7, "null marshaller.toAsciiString()");
        return a7.getBytes(g.f166a);
    }
}
