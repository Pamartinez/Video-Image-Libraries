package Sf;

import Ae.b;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements k {

    /* renamed from: a  reason: collision with root package name */
    public final k f3734a;
    public final b b;

    /* renamed from: c  reason: collision with root package name */
    public final b f3735c;

    public h(k kVar, b bVar, b bVar2) {
        j.e(bVar, "transformer");
        this.f3734a = kVar;
        this.b = bVar;
        this.f3735c = bVar2;
    }

    public final Iterator iterator() {
        return new f(this);
    }
}
