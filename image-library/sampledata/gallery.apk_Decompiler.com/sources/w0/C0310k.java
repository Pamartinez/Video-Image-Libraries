package w0;

import java.math.BigInteger;
import java.util.Objects;

/* renamed from: w0.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0310k extends C0304e {

    /* renamed from: c  reason: collision with root package name */
    public final BigInteger f1985c;

    public C0310k(C0308i iVar, BigInteger bigInteger) {
        super(iVar);
        Objects.requireNonNull(bigInteger);
        this.f1985c = bigInteger;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0310k) {
            C0310k kVar = (C0310k) obj;
            if (!super.equals(obj) || !this.f1985c.equals(kVar.f1985c)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f1985c.hashCode() ^ super.hashCode();
    }

    public final String toString() {
        return this.f1985c.toString();
    }
}
