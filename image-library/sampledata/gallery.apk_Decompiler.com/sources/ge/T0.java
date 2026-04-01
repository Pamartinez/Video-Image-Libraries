package ge;

import B2.A;
import e5.C0451a;
import ee.C0964A;
import ee.C0971d;
import ee.C0984q;
import ee.e0;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class T0 extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4479c = 0;
    public final AtomicBoolean d;
    public final Object e;
    public final /* synthetic */ C0984q f;

    public T0(V0 v02, V0 v03) {
        this.f = v02;
        this.d = new AtomicBoolean(false);
        this.e = v03;
    }

    public final C0964A j() {
        switch (this.f4479c) {
            case 0:
                if (this.d.compareAndSet(false, true)) {
                    e0 e0Var = ((F0) ((V0) this.f).d.f).m;
                    V0 v02 = (V0) this.e;
                    Objects.requireNonNull(v02);
                    e0Var.execute(new C0451a(19, v02));
                }
                return C0964A.d;
            default:
                if (this.d.compareAndSet(false, true)) {
                    ((F0) ((C1008a1) this.f).d.f).m.execute(new A(12, (Object) this));
                }
                return C0964A.d;
        }
    }

    public T0(C1008a1 a1Var, E0 e02) {
        this.f = a1Var;
        this.d = new AtomicBoolean(false);
        this.e = e02;
    }
}
