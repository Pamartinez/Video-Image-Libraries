package nf;

/* renamed from: nf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1207d {

    /* renamed from: a  reason: collision with root package name */
    public final int f4954a;
    public final int b;

    public C1207d(int i2, int i7) {
        this.f4954a = i2;
        this.b = i7;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [nf.d, nf.b] */
    public static C1205b a(C1207d dVar) {
        return new C1207d(dVar.f4954a + dVar.b, 1);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [nf.d, nf.b] */
    public static C1205b b() {
        return new C1207d(0, 1);
    }
}
