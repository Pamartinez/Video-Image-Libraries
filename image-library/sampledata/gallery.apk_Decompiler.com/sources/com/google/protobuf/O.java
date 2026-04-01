package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O implements U {

    /* renamed from: a  reason: collision with root package name */
    public U[] f1585a;

    public final f0 a(Class cls) {
        for (U u : this.f1585a) {
            if (u.b(cls)) {
                return u.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public final boolean b(Class cls) {
        for (U b : this.f1585a) {
            if (b.b(cls)) {
                return true;
            }
        }
        return false;
    }
}
