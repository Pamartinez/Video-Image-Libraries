package com.google.protobuf;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Q extends LinkedHashMap {
    public static final Q e;
    public boolean d = true;

    static {
        Q q = new Q();
        e = q;
        q.d = false;
    }

    public static int a(Object obj) {
        if (obj instanceof byte[]) {
            Charset charset = D.f1578a;
            int i2 = r0;
            for (byte b : (byte[]) obj) {
                i2 = (i2 * 31) + b;
            }
            if (i2 == 0) {
                return 1;
            }
            return i2;
        } else if (!(obj instanceof Internal$EnumLite)) {
            return obj.hashCode();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public final void b() {
        if (!this.d) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.LinkedHashMap, com.google.protobuf.Q] */
    public final Q c() {
        if (isEmpty()) {
            return new Q();
        }
        ? linkedHashMap = new LinkedHashMap(this);
        linkedHashMap.d = true;
        return linkedHashMap;
    }

    public final void clear() {
        b();
        super.clear();
    }

    public final Set entrySet() {
        if (isEmpty()) {
            return Collections.EMPTY_SET;
        }
        return super.entrySet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof java.util.Map
            r1 = 0
            if (r0 == 0) goto L_0x005e
            java.util.Map r6 = (java.util.Map) r6
            r0 = 1
            if (r5 != r6) goto L_0x000c
        L_0x000a:
            r5 = r0
            goto L_0x005b
        L_0x000c:
            int r2 = r5.size()
            int r3 = r6.size()
            if (r2 == r3) goto L_0x0018
        L_0x0016:
            r5 = r1
            goto L_0x005b
        L_0x0018:
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0020:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x000a
            java.lang.Object r2 = r5.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            boolean r3 = r6.containsKey(r3)
            if (r3 != 0) goto L_0x0037
            goto L_0x0016
        L_0x0037:
            java.lang.Object r3 = r2.getValue()
            java.lang.Object r2 = r2.getKey()
            java.lang.Object r2 = r6.get(r2)
            boolean r4 = r3 instanceof byte[]
            if (r4 == 0) goto L_0x0054
            boolean r4 = r2 instanceof byte[]
            if (r4 == 0) goto L_0x0054
            byte[] r3 = (byte[]) r3
            byte[] r2 = (byte[]) r2
            boolean r2 = java.util.Arrays.equals(r3, r2)
            goto L_0x0058
        L_0x0054:
            boolean r2 = r3.equals(r2)
        L_0x0058:
            if (r2 != 0) goto L_0x0020
            goto L_0x0016
        L_0x005b:
            if (r5 == 0) goto L_0x005e
            return r0
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Q.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i2 = 0;
        for (Map.Entry entry : entrySet()) {
            i2 += a(entry.getValue()) ^ a(entry.getKey());
        }
        return i2;
    }

    public final Object put(Object obj, Object obj2) {
        b();
        Charset charset = D.f1578a;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    public final void putAll(Map map) {
        b();
        for (Object next : map.keySet()) {
            Charset charset = D.f1578a;
            next.getClass();
            map.get(next).getClass();
        }
        super.putAll(map);
    }

    public final Object remove(Object obj) {
        b();
        return super.remove(obj);
    }
}
