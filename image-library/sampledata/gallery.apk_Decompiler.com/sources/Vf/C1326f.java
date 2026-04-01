package vf;

import kotlin.jvm.internal.j;
import qf.C1235b;

/* renamed from: vf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1326f {

    /* renamed from: a  reason: collision with root package name */
    public final C1235b f5157a;
    public final int b;

    public C1326f(C1235b bVar, int i2) {
        this.f5157a = bVar;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1326f)) {
            return false;
        }
        C1326f fVar = (C1326f) obj;
        if (j.a(this.f5157a, fVar.f5157a) && this.b == fVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.f5157a.hashCode() * 31);
    }

    public final String toString() {
        int i2;
        StringBuilder sb2 = new StringBuilder();
        int i7 = 0;
        while (true) {
            i2 = this.b;
            if (i7 >= i2) {
                break;
            }
            sb2.append("kotlin/Array<");
            i7++;
        }
        sb2.append(this.f5157a);
        for (int i8 = 0; i8 < i2; i8++) {
            sb2.append(">");
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }
}
