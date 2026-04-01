package Hf;

import If.b;
import If.e;
import If.f;
import Kf.d;
import Qf.h;
import Qf.k;
import java.util.ArrayDeque;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class L {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f3431a;
    public final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final b f3432c;
    public final e d;
    public final f e;
    public int f;
    public ArrayDeque g;

    /* renamed from: h  reason: collision with root package name */
    public h f3433h;

    public L(boolean z, boolean z3, b bVar, e eVar, f fVar) {
        j.e(bVar, "typeSystemContext");
        j.e(eVar, "kotlinTypePreparator");
        j.e(fVar, "kotlinTypeRefiner");
        this.f3431a = z;
        this.b = z3;
        this.f3432c = bVar;
        this.d = eVar;
        this.e = fVar;
    }

    public final void a() {
        ArrayDeque arrayDeque = this.g;
        j.b(arrayDeque);
        arrayDeque.clear();
        h hVar = this.f3433h;
        j.b(hVar);
        hVar.clear();
    }

    public final void b() {
        if (this.g == null) {
            this.g = new ArrayDeque(4);
        }
        if (this.f3433h == null) {
            int i2 = h.f;
            this.f3433h = k.e();
        }
    }

    public final c0 c(d dVar) {
        j.e(dVar, "type");
        return this.d.a(dVar);
    }

    public final C0774x d(d dVar) {
        j.e(dVar, "type");
        this.e.getClass();
        return (C0774x) dVar;
    }
}
