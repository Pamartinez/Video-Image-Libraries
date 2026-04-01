package Bf;

import Hf.C0774x;
import Qe.C0812b;
import Qe.C0816f;
import Qe.C0823m;
import Te.C0853n;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends a implements e {
    public final /* synthetic */ int f = 1;
    public final C1240g g;

    /* renamed from: h  reason: collision with root package name */
    public final C0823m f3322h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(C0812b bVar, C0774x xVar, C1240g gVar) {
        super(xVar);
        j.e(xVar, "receiverType");
        this.f3322h = (C0853n) bVar;
        this.g = gVar;
    }

    public final C1240g C0() {
        switch (this.f) {
            case 0:
                return this.g;
            default:
                return this.g;
        }
    }

    public final String toString() {
        switch (this.f) {
            case 0:
                return getType() + ": Ctx { " + ((C0816f) this.f3322h) + " }";
            default:
                return "Cxt { " + ((C0853n) this.f3322h) + " }";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(C0816f fVar, C0774x xVar, C1240g gVar) {
        super(xVar);
        j.e(xVar, "receiverType");
        this.f3322h = fVar;
        this.g = gVar;
    }
}
