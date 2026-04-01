package Vf;

import c0.C0086a;
import kotlin.jvm.internal.j;
import qe.C1225a;

/* renamed from: Vf.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0888z extends C1225a {
    public static final C0887y e = new Object();
    public final String d = "dsl-translate";

    public C0888z() {
        super(e);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C0888z) && j.a(this.d, ((C0888z) obj).d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return C0086a.m(new StringBuilder("CoroutineName("), this.d, ')');
    }
}
