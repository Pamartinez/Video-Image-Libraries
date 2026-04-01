package F2;

import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A0 extends C0010c0 {
    public final transient Y g;

    /* renamed from: h  reason: collision with root package name */
    public final transient Object[] f231h;

    /* renamed from: i  reason: collision with root package name */
    public final transient int f232i;

    /* renamed from: j  reason: collision with root package name */
    public final transient int f233j;

    public A0(Y y, Object[] objArr, int i2, int i7) {
        this.g = y;
        this.f231h = objArr;
        this.f232i = i2;
        this.f233j = i7;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.g.get(key))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int q(int i2, Object[] objArr) {
        return p().q(i2, objArr);
    }

    public final int size() {
        return this.f233j;
    }

    public final boolean u() {
        return true;
    }

    public final O0 v() {
        return p().listIterator(0);
    }

    public final U z() {
        return new z0(this);
    }
}
