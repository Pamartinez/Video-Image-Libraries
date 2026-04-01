package ge;

import G0.c;
import He.F;
import ee.a0;
import io.grpc.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class T extends Q0 {
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final a0 f4476h;

    /* renamed from: i  reason: collision with root package name */
    public final C1054s f4477i;

    /* renamed from: j  reason: collision with root package name */
    public final a[] f4478j;

    public T(a0 a0Var, C1054s sVar, a[] aVarArr) {
        super(0);
        F.i("error must not be OK", !a0Var.e());
        this.f4476h = a0Var;
        this.f4477i = sVar;
        this.f4478j = aVarArr;
    }

    public final void e(c cVar) {
        cVar.i(this.f4476h, "error");
        cVar.i(this.f4477i, "progress");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [ee.M, java.lang.Object] */
    public final void k(C1056t tVar) {
        F.t(!this.g, "already started");
        this.g = true;
        for (a aVar : this.f4478j) {
            aVar.getClass();
        }
        tVar.u0(this.f4476h, this.f4477i, new Object());
    }

    public T(a0 a0Var, a[] aVarArr) {
        this(a0Var, C1054s.PROCESSED, aVarArr);
    }
}
