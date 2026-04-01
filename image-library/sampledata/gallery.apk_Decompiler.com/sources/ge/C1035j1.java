package ge;

import ee.C0973f;
import ee.C0981n;
import io.grpc.Deadline;

/* renamed from: ge.j1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1035j1 implements o1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4526a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C1035j1(int i2, Object obj) {
        this.f4526a = i2;
        this.b = obj;
    }

    public final void a(v1 v1Var) {
        switch (this.f4526a) {
            case 0:
                v1Var.f4567a.w((C0973f) this.b);
                return;
            case 1:
                v1Var.f4567a.u((Deadline) this.b);
                return;
            default:
                v1Var.f4567a.b((C0981n) this.b);
                return;
        }
    }
}
