package Vf;

import Ae.d;
import Qe.B;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: Vf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0862c extends i0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater k = AtomicReferenceFieldUpdater.newUpdater(C0862c.class, Object.class, "_disposer$volatile");
    private volatile /* synthetic */ Object _disposer$volatile;

    /* renamed from: h  reason: collision with root package name */
    public final C0875l f3851h;

    /* renamed from: i  reason: collision with root package name */
    public O f3852i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ C0866e f3853j;

    public C0862c(C0866e eVar, C0875l lVar) {
        this.f3853j = eVar;
        this.f3851h = lVar;
    }

    public final boolean j() {
        return false;
    }

    public final void k(Throwable th) {
        C0875l lVar = this.f3851h;
        if (th != null) {
            lVar.getClass();
            B D = lVar.D(new C0883u(th, false), (d) null);
            if (D != null) {
                lVar.j(D);
                C0864d dVar = (C0864d) k.get(this);
                if (dVar != null) {
                    dVar.b();
                    return;
                }
                return;
            }
            return;
        }
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = C0866e.b;
        C0866e eVar = this.f3853j;
        if (atomicIntegerFieldUpdater.decrementAndGet(eVar) == 0) {
            G[] gArr = eVar.f3858a;
            ArrayList arrayList = new ArrayList(gArr.length);
            for (G c5 : gArr) {
                arrayList.add(c5.c());
            }
            lVar.resumeWith(arrayList);
        }
    }
}
