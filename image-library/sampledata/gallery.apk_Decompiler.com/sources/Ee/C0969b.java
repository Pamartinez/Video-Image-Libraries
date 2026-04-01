package ee;

import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;

/* renamed from: ee.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0969b {
    public static final C0969b b = new C0969b(new IdentityHashMap());

    /* renamed from: a  reason: collision with root package name */
    public final IdentityHashMap f4292a;

    public C0969b(IdentityHashMap identityHashMap) {
        this.f4292a = identityHashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 == 0) goto L_0x0054
            java.lang.Class<ee.b> r2 = ee.C0969b.class
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L_0x0010
            goto L_0x0054
        L_0x0010:
            ee.b r5 = (ee.C0969b) r5
            java.util.IdentityHashMap r5 = r5.f4292a
            java.util.IdentityHashMap r4 = r4.f4292a
            int r2 = r4.size()
            int r3 = r5.size()
            if (r2 == r3) goto L_0x0021
            return r1
        L_0x0021:
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0029:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x0053
            java.lang.Object r2 = r4.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            boolean r3 = r5.containsKey(r3)
            if (r3 != 0) goto L_0x0040
            return r1
        L_0x0040:
            java.lang.Object r3 = r2.getValue()
            java.lang.Object r2 = r2.getKey()
            java.lang.Object r2 = r5.get(r2)
            boolean r2 = D1.f.p(r3, r2)
            if (r2 != 0) goto L_0x0029
            return r1
        L_0x0053:
            return r0
        L_0x0054:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ee.C0969b.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i2 = 0;
        for (Map.Entry entry : this.f4292a.entrySet()) {
            i2 += Arrays.hashCode(new Object[]{entry.getKey(), entry.getValue()});
        }
        return i2;
    }

    public final String toString() {
        return this.f4292a.toString();
    }
}
