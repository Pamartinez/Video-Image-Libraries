package Sf;

import Ae.b;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements k {

    /* renamed from: a  reason: collision with root package name */
    public final k f3739a;
    public final b b;

    public u(k kVar, b bVar) {
        j.e(bVar, "transformer");
        this.f3739a = kVar;
        this.b = bVar;
    }

    public final Iterator iterator() {
        return new t(this);
    }
}
