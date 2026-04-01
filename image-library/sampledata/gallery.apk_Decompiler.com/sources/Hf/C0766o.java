package Hf;

import kotlin.jvm.internal.j;

/* renamed from: Hf.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0766o extends C0765n {
    public final B e;

    public C0766o(B b) {
        this.e = b;
    }

    public final B B0(boolean z) {
        if (z == u0()) {
            return this;
        }
        return this.e.y0(z).A0(p0());
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        if (i2 != p0()) {
            return new D(this, i2);
        }
        return this;
    }

    public final B D0() {
        return this.e;
    }
}
