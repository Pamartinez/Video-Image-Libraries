package Vf;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class g0 extends n0 implements C0881s {
    public final boolean f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g0() {
        super(true);
        C0879p pVar;
        C0879p pVar2;
        boolean z = true;
        C((C0867e0) null);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n0.e;
        C0878o oVar = (C0878o) atomicReferenceFieldUpdater.get(this);
        if (oVar instanceof C0879p) {
            pVar = (C0879p) oVar;
        } else {
            pVar = null;
        }
        if (pVar != null) {
            n0 i2 = pVar.i();
            while (true) {
                if (!i2.x()) {
                    C0878o oVar2 = (C0878o) atomicReferenceFieldUpdater.get(i2);
                    if (oVar2 instanceof C0879p) {
                        pVar2 = (C0879p) oVar2;
                    } else {
                        pVar2 = null;
                    }
                    if (pVar2 == null) {
                        break;
                    }
                    i2 = pVar2.i();
                } else {
                    break;
                }
            }
        }
        z = false;
        this.f = z;
    }

    public final boolean x() {
        return this.f;
    }

    public final boolean y() {
        return true;
    }
}
