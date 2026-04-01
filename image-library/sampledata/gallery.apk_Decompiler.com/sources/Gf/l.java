package Gf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3414a;
    public final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f3415c;

    public /* synthetic */ l(Object obj, boolean z, int i2) {
        this.f3414a = i2;
        this.f3415c = obj;
        this.b = z;
    }

    public String toString() {
        switch (this.f3414a) {
            case 0:
                if (this.b) {
                    return "FALL_THROUGH";
                }
                return String.valueOf(this.f3415c);
            default:
                return super.toString();
        }
    }
}
