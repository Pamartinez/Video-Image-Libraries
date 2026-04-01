package S2;

import L2.a;
import T2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends l {
    public static final j[] e = new j[511];
    public static final j f = i(0);

    static {
        i(-1);
        i(1);
        i(2);
        i(3);
        i(4);
        i(5);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: S2.j[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: S2.j} */
    /* JADX WARNING: type inference failed for: r2v2, types: [S2.l, S2.j] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static S2.j i(int r4) {
        /*
            r0 = 2147483647(0x7fffffff, float:NaN)
            r0 = r0 & r4
            S2.j[] r1 = e
            int r2 = r1.length
            int r0 = r0 % r2
            r2 = r1[r0]
            if (r2 == 0) goto L_0x0011
            int r3 = r2.d
            if (r3 != r4) goto L_0x0011
            return r2
        L_0x0011:
            S2.j r2 = new S2.j
            r2.<init>(r4)
            r1[r0] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: S2.j.i(int):S2.j");
    }

    public final String a() {
        return Integer.toString(this.d);
    }

    public final String e() {
        return "int";
    }

    public final c getType() {
        return c.f830o;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("int{0x");
        int i2 = this.d;
        sb2.append(a.E(i2));
        sb2.append(" / ");
        sb2.append(i2);
        sb2.append('}');
        return sb2.toString();
    }
}
