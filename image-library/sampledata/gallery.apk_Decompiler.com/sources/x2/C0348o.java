package x2;

/* renamed from: x2.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0348o extends C0352s {

    /* renamed from: a  reason: collision with root package name */
    public final C0350q f2133a;
    public final float b;

    /* renamed from: c  reason: collision with root package name */
    public final float f2134c;

    public C0348o(C0350q qVar, float f, float f5) {
        this.f2133a = qVar;
        this.b = f;
        this.f2134c = f5;
    }

    public final float a() {
        C0350q qVar = this.f2133a;
        return (float) Math.toDegrees(Math.atan((double) ((qVar.f2137c - this.f2134c) / (qVar.b - this.b))));
    }
}
