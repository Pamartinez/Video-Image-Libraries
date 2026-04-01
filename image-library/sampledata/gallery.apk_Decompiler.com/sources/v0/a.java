package V0;

import Tf.m;
import W0.c;
import W0.f;
import W0.i;
import W0.j;
import c1.C0089a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends j {

    /* renamed from: i  reason: collision with root package name */
    public static final int f864i = i.a(b.class);

    /* renamed from: h  reason: collision with root package name */
    public final int f865h;

    public a(W0.a aVar, C0089a aVar2, m mVar, f fVar, f fVar2, c cVar) {
        super(aVar, aVar2, mVar, fVar, fVar2);
        this.f865h = f864i;
    }

    public final j b(W0.a aVar) {
        if (this.e == aVar) {
            return this;
        }
        return new a(this, aVar);
    }

    public a(a aVar, int i2, int i7) {
        super((j) aVar, i2);
        this.f865h = i7;
    }

    public a(a aVar, W0.a aVar2) {
        super((j) aVar, aVar2);
        this.f865h = aVar.f865h;
    }
}
