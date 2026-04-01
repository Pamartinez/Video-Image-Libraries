package kotlin.jvm.internal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements c {
    public final Class d;

    public n(Class cls, String str) {
        j.e(cls, "jClass");
        this.d = cls;
    }

    public final Class b() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        if (j.a(this.d, ((n) obj).d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return this.d + " (Kotlin reflection is not available)";
    }
}
