package F2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x0 extends M {
    public static final x0 m = new x0();

    /* renamed from: h  reason: collision with root package name */
    public final transient Object f275h;

    /* renamed from: i  reason: collision with root package name */
    public final transient Object[] f276i;

    /* renamed from: j  reason: collision with root package name */
    public final transient int f277j;
    public final transient int k;
    public final transient x0 l;

    public x0() {
        this.f275h = null;
        this.f276i = new Object[0];
        this.f277j = 0;
        this.k = 0;
        this.l = this;
    }

    public final C0010c0 b() {
        return new A0(this, this.f276i, this.f277j, this.k);
    }

    public final C0010c0 c() {
        return new B0(this, new C0(this.f276i, this.f277j, this.k));
    }

    public final boolean f() {
        return false;
    }

    public final Object get(Object obj) {
        Object k10 = D0.k(this.f275h, this.f276i, this.k, this.f277j, obj);
        if (k10 == null) {
            return null;
        }
        return k10;
    }

    public final int size() {
        return this.k;
    }

    public x0(int i2, Object[] objArr) {
        this.f276i = objArr;
        this.k = i2;
        this.f277j = 0;
        int w = i2 >= 2 ? C0010c0.w(i2) : 0;
        Object j2 = D0.j(objArr, i2, w, 0);
        if (!(j2 instanceof Object[])) {
            this.f275h = j2;
            Object j3 = D0.j(objArr, i2, w, 1);
            if (!(j3 instanceof Object[])) {
                this.l = new x0(j3, objArr, i2, this);
                return;
            }
            throw ((W) ((Object[]) j3)[2]).a();
        }
        throw ((W) ((Object[]) j2)[2]).a();
    }

    public x0(Object obj, Object[] objArr, int i2, x0 x0Var) {
        this.f275h = obj;
        this.f276i = objArr;
        this.f277j = 1;
        this.k = i2;
        this.l = x0Var;
    }
}
