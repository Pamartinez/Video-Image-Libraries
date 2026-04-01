package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S {
    public static Q a(Object obj, Object obj2) {
        Q q = (Q) obj;
        Q q10 = (Q) obj2;
        if (!q10.isEmpty()) {
            if (!q.d) {
                q = q.c();
            }
            q.b();
            if (!q10.isEmpty()) {
                q.putAll(q10);
            }
        }
        return q;
    }
}
