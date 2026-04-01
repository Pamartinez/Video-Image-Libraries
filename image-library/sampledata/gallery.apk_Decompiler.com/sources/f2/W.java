package F2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class W {

    /* renamed from: a  reason: collision with root package name */
    public final Object f252a;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f253c;

    public W(Object obj, Object obj2, Object obj3) {
        this.f252a = obj;
        this.b = obj2;
        this.f253c = obj3;
    }

    public final IllegalArgumentException a() {
        StringBuilder sb2 = new StringBuilder("Multiple entries with same key: ");
        Object obj = this.f252a;
        sb2.append(obj);
        sb2.append("=");
        sb2.append(this.b);
        sb2.append(" and ");
        sb2.append(obj);
        sb2.append("=");
        sb2.append(this.f253c);
        return new IllegalArgumentException(sb2.toString());
    }
}
