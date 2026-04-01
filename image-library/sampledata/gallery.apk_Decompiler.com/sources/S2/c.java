package S2;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends a {
    public final b d;

    public c(b bVar) {
        if (!bVar.d) {
            this.d = bVar;
            return;
        }
        throw new U2.c("mutable instance", (Exception) null);
    }

    public final String a() {
        return this.d.f("{", "}", true);
    }

    public final int d(a aVar) {
        return this.d.compareTo(((c) aVar).d);
    }

    public final String e() {
        return "array";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        return this.d.equals(((c) obj).d);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.d.e);
    }

    public final String toString() {
        return this.d.f("array{", "}", false);
    }
}
