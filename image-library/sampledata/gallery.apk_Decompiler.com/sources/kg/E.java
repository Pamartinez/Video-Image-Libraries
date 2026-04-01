package kg;

import gg.a;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E implements A {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f4666a;

    public E(a aVar) {
        this.f4666a = aVar;
    }

    public final a[] childSerializers() {
        return new a[]{this.f4666a};
    }

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        throw new IllegalStateException("unsupported");
    }

    public final f getDescriptor() {
        throw new IllegalStateException("unsupported");
    }

    public final a[] typeParametersSerializers() {
        return Q.b;
    }
}
