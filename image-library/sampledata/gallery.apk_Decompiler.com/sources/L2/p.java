package L2;

import R2.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    public final b f405a;
    public final r b;

    /* renamed from: c  reason: collision with root package name */
    public int f406c = -1;
    public k d;

    public p(b bVar, r rVar) {
        this.f405a = bVar;
        this.b = rVar;
    }

    public final k a() {
        if (this.d == null) {
            this.f405a.f();
            if (this.d == null) {
                throw new AssertionError();
            }
        }
        return this.d;
    }

    public final String toString() {
        return "v" + this.f406c + "(" + this.b + ")";
    }
}
