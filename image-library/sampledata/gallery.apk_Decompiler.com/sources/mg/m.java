package mg;

import ig.f;
import kotlin.jvm.internal.j;
import lg.C1174b;
import lg.C1176d;
import lg.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends a {

    /* renamed from: h  reason: collision with root package name */
    public final C1176d f4935h;

    /* renamed from: i  reason: collision with root package name */
    public final int f4936i;

    /* renamed from: j  reason: collision with root package name */
    public int f4937j = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(C1174b bVar, C1176d dVar) {
        super(bVar);
        j.e(bVar, "json");
        this.f4935h = dVar;
        this.f4936i = dVar.d.size();
    }

    public final String Q(f fVar, int i2) {
        j.e(fVar, "descriptor");
        return String.valueOf(i2);
    }

    public final l T() {
        return this.f4935h;
    }

    public final int d(f fVar) {
        j.e(fVar, "descriptor");
        int i2 = this.f4937j;
        if (i2 >= this.f4936i - 1) {
            return -1;
        }
        int i7 = i2 + 1;
        this.f4937j = i7;
        return i7;
    }

    public final l i(String str) {
        j.e(str, "tag");
        return (l) this.f4935h.d.get(Integer.parseInt(str));
    }
}
