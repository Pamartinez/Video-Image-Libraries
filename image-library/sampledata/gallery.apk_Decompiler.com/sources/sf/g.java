package Sf;

import Ae.b;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements k {

    /* renamed from: a  reason: collision with root package name */
    public final k f3732a;
    public final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final b f3733c;

    public g(k kVar, boolean z, b bVar) {
        j.e(kVar, "sequence");
        this.f3732a = kVar;
        this.b = z;
        this.f3733c = bVar;
    }

    public final Iterator iterator() {
        return new f(this);
    }
}
