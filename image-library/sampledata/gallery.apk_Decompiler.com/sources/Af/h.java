package Af;

import Ae.b;
import Qe.C0814d;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import tf.C1311o;
import tf.C1312p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends C1312p {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ArrayList f3314c;
    public final /* synthetic */ i d;

    public h(ArrayList arrayList, i iVar) {
        this.f3314c = arrayList;
        this.d = iVar;
    }

    public final void b(C0814d dVar) {
        j.e(dVar, "fakeOverride");
        C1311o.r(dVar, (b) null);
        this.f3314c.add(dVar);
    }

    public final void d(C0814d dVar, C0814d dVar2) {
        j.e(dVar2, "fromCurrent");
        throw new IllegalStateException(("Conflict in scope of " + this.d.b + ": " + dVar + " vs " + dVar2).toString());
    }
}
