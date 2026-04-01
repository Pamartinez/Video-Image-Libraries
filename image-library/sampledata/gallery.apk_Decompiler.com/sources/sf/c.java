package Sf;

import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements k, d {

    /* renamed from: a  reason: collision with root package name */
    public final k f3729a;
    public final int b;

    public c(k kVar, int i2) {
        j.e(kVar, "sequence");
        this.f3729a = kVar;
        this.b = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException(("count must be non-negative, but was " + i2 + '.').toString());
        }
    }

    public final k a() {
        int i2 = this.b + 1;
        if (i2 < 0) {
            return new c(this, 1);
        }
        return new c(this.f3729a, i2);
    }

    public final Iterator iterator() {
        return new b(this);
    }
}
