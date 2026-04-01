package jf;

import c0.C0086a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s {

    /* renamed from: a  reason: collision with root package name */
    public final String f4652a;

    public s(String str) {
        this.f4652a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof s) && j.a(this.f4652a, ((s) obj).f4652a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f4652a.hashCode();
    }

    public final String toString() {
        return C0086a.m(new StringBuilder("MemberSignature(signature="), this.f4652a, ')');
    }
}
