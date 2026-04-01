package F2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B0 extends C0010c0 {
    public final transient Y g;

    /* renamed from: h  reason: collision with root package name */
    public final transient C0 f234h;

    public B0(Y y, C0 c02) {
        this.g = y;
        this.f234h = c02;
    }

    public final boolean contains(Object obj) {
        if (this.g.get(obj) != null) {
            return true;
        }
        return false;
    }

    public final U p() {
        return this.f234h;
    }

    public final int q(int i2, Object[] objArr) {
        return this.f234h.q(i2, objArr);
    }

    public final int size() {
        return this.g.size();
    }

    public final boolean u() {
        return true;
    }

    public final O0 v() {
        return this.f234h.listIterator(0);
    }
}
