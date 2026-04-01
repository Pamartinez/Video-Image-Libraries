package mg;

import ig.f;
import kotlin.jvm.internal.j;
import lg.C1174b;
import lg.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends a {

    /* renamed from: h  reason: collision with root package name */
    public final l f4931h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(C1174b bVar, l lVar) {
        super(bVar);
        j.e(bVar, "json");
        j.e(lVar, "value");
        this.f4931h = lVar;
        this.d.add("primitive");
    }

    public final l T() {
        return this.f4931h;
    }

    public final int d(f fVar) {
        j.e(fVar, "descriptor");
        return 0;
    }

    public final l i(String str) {
        j.e(str, "tag");
        if (str == "primitive") {
            return this.f4931h;
        }
        throw new IllegalArgumentException("This input can only handle primitives with 'primitive' tag");
    }
}
