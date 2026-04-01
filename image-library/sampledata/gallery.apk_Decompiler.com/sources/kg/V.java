package kg;

import ig.f;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class V extends I {
    public final String b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public V(f fVar) {
        super(fVar);
        j.e(fVar, "primitive");
        this.b = fVar.i() + "Array";
    }

    public final String i() {
        return this.b;
    }
}
