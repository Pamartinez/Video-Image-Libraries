package N2;

import U2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends d implements Comparable {
    public static final b f = new d(0);

    public final String a() {
        return h("", "");
    }

    /* renamed from: g */
    public final int compareTo(b bVar) {
        if (this == bVar) {
            return 0;
        }
        int length = this.e.length;
        int length2 = bVar.e.length;
        int min = Math.min(length, length2);
        for (int i2 = 0; i2 < min; i2++) {
            int a7 = ((a) d(i2)).compareTo((a) bVar.d(i2));
            if (a7 != 0) {
                return a7;
            }
        }
        if (length < length2) {
            return -1;
        }
        if (length > length2) {
            return 1;
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String h(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 100
            r0.<init>(r1)
            java.lang.Object[] r1 = r7.e
            int r2 = r1.length
            java.lang.String r3 = "catch "
            N2.j.z(r0, r8, r9, r3)
            r9 = 0
            r3 = r9
        L_0x0011:
            if (r3 >= r2) goto L_0x0067
            java.lang.Object r4 = r7.d(r3)
            N2.a r4 = (N2.a) r4
            if (r3 == 0) goto L_0x0022
            java.lang.String r5 = ",\n"
            java.lang.String r6 = "  "
            N2.j.z(r0, r5, r8, r6)
        L_0x0022:
            int r5 = r2 + -1
            if (r3 != r5) goto L_0x0043
            int r5 = r1.length
            if (r5 != 0) goto L_0x002b
            r5 = r9
            goto L_0x003b
        L_0x002b:
            int r5 = r5 + -1
            java.lang.Object r5 = r7.d(r5)
            N2.a r5 = (N2.a) r5
            S2.u r5 = r5.d
            S2.u r6 = S2.u.g
            boolean r5 = r5.equals(r6)
        L_0x003b:
            if (r5 == 0) goto L_0x0043
            java.lang.String r5 = "<any>"
            r0.append(r5)
            goto L_0x004e
        L_0x0043:
            S2.u r5 = r4.d
            T2.c r5 = r5.d
            java.lang.String r5 = r5.a()
            r0.append(r5)
        L_0x004e:
            java.lang.String r5 = " -> "
            r0.append(r5)
            int r4 = r4.e
            char r5 = (char) r4
            if (r4 != r5) goto L_0x005d
            java.lang.String r4 = L2.a.D(r4)
            goto L_0x0061
        L_0x005d:
            java.lang.String r4 = L2.a.E(r4)
        L_0x0061:
            r0.append(r4)
            int r3 = r3 + 1
            goto L_0x0011
        L_0x0067:
            java.lang.String r7 = r0.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: N2.b.h(java.lang.String, java.lang.String):java.lang.String");
    }
}
