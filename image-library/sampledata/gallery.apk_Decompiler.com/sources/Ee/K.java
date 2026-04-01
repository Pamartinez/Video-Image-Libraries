package ee;

import He.F;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K extends I {
    public final L e;

    public K(String str, boolean z, L l) {
        super(str, l, z);
        F.l(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", str, "-bin");
        this.e = l;
    }

    public final byte[] b(Object obj) {
        byte[] a7 = this.e.a(obj);
        F.n(a7, "null marshaller.toAsciiString()");
        return a7;
    }
}
