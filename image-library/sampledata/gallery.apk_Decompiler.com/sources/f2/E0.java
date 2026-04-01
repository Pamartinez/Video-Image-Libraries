package F2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E0 extends C0010c0 {
    public static final Object[] l;
    public static final E0 m;
    public final transient Object[] g;

    /* renamed from: h  reason: collision with root package name */
    public final transient int f241h;

    /* renamed from: i  reason: collision with root package name */
    public final transient Object[] f242i;

    /* renamed from: j  reason: collision with root package name */
    public final transient int f243j;
    public final transient int k;

    static {
        Object[] objArr = new Object[0];
        l = objArr;
        m = new E0(0, 0, 0, objArr, objArr);
    }

    public E0(int i2, int i7, int i8, Object[] objArr, Object[] objArr2) {
        this.g = objArr;
        this.f241h = i2;
        this.f242i = objArr2;
        this.f243j = i7;
        this.k = i8;
    }

    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.f242i;
            if (objArr.length != 0) {
                int q = C0040v.q(obj);
                while (true) {
                    int i2 = q & this.f243j;
                    Object obj2 = objArr[i2];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    q = i2 + 1;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f241h;
    }

    public final int q(int i2, Object[] objArr) {
        Object[] objArr2 = this.g;
        int i7 = this.k;
        System.arraycopy(objArr2, 0, objArr, i2, i7);
        return i2 + i7;
    }

    public final Object[] r() {
        return this.g;
    }

    public final int s() {
        return this.k;
    }

    public final int size() {
        return this.k;
    }

    public final int t() {
        return 0;
    }

    public final boolean u() {
        return false;
    }

    public final O0 v() {
        return p().listIterator(0);
    }

    public final U z() {
        return U.w(this.k, this.g);
    }
}
