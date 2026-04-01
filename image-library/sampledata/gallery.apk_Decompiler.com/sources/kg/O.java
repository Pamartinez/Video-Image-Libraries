package kg;

import gg.a;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O implements a {

    /* renamed from: a  reason: collision with root package name */
    public final a f4676a;
    public final Z b;

    public O(a aVar) {
        j.e(aVar, "serializer");
        this.f4676a = aVar;
        this.b = new Z(aVar.getDescriptor());
    }

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        if (cVar.D()) {
            return cVar.g(this.f4676a);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || O.class != obj.getClass() || !j.a(this.f4676a, ((O) obj).f4676a)) {
            return false;
        }
        return true;
    }

    public final f getDescriptor() {
        return this.b;
    }

    public final int hashCode() {
        return this.f4676a.hashCode();
    }
}
