package V0;

import Tf.m;
import U0.a;
import U0.b;
import W0.f;
import W0.i;
import W0.j;
import c1.C0089a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends j {

    /* renamed from: j  reason: collision with root package name */
    public static final b f870j;
    public static final int k = i.a(m.class);

    /* renamed from: h  reason: collision with root package name */
    public final Q0.i f871h;

    /* renamed from: i  reason: collision with root package name */
    public final int f872i;

    /* JADX WARNING: type inference failed for: r0v0, types: [U0.b, java.lang.Object] */
    static {
        ? obj = new Object();
        int i2 = a.e;
        f870j = obj;
    }

    public l(W0.a aVar, C0089a aVar2, m mVar, f fVar, f fVar2) {
        super(aVar, aVar2, mVar, fVar, fVar2);
        this.f872i = k;
        this.f871h = f870j;
    }

    public final j b(W0.a aVar) {
        if (this.e == aVar) {
            return this;
        }
        return new l(this, aVar);
    }

    public l(l lVar, int i2, int i7) {
        super((j) lVar, i2);
        this.f872i = i7;
        this.f871h = lVar.f871h;
    }

    public l(l lVar, W0.a aVar) {
        super((j) lVar, aVar);
        this.f872i = lVar.f872i;
        this.f871h = lVar.f871h;
    }

    public l(l lVar, Q0.i iVar) {
        super(lVar);
        this.f872i = lVar.f872i;
        this.f871h = iVar;
    }
}
