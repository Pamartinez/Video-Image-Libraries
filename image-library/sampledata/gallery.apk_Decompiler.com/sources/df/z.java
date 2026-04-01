package Df;

import B1.b;
import Qe.Q;
import nf.C1209f;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class z {

    /* renamed from: a  reason: collision with root package name */
    public final C1209f f3369a;
    public final b b;

    /* renamed from: c  reason: collision with root package name */
    public final Q f3370c;

    public z(C1209f fVar, b bVar, Q q) {
        this.f3369a = fVar;
        this.b = bVar;
        this.f3370c = q;
    }

    public abstract C1236c a();

    public final String toString() {
        return getClass().getSimpleName() + ": " + a();
    }
}
