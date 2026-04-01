package io.grpc.binder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {
    public static final b b = new b(1);

    /* renamed from: a  reason: collision with root package name */
    public final int f4623a;

    public b(int i2) {
        this.f4623a = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass() && this.f4623a == ((b) obj).f4623a) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f4623a;
    }

    public final String toString() {
        return "BindServiceFlags{" + Integer.toHexString(this.f4623a) + "}";
    }
}
