package Vf;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Q implements C0861b0 {
    public final boolean d;

    public Q(boolean z) {
        this.d = z;
    }

    public final q0 c() {
        return null;
    }

    public final boolean isActive() {
        return this.d;
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("Empty{");
        if (this.d) {
            str = "Active";
        } else {
            str = "New";
        }
        return C0086a.m(sb2, str, '}');
    }
}
