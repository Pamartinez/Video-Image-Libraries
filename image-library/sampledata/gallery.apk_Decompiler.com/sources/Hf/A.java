package Hf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends C0766o {
    public final /* synthetic */ int f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A(B b, int i2) {
        super(b);
        this.f = i2;
    }

    public final C0765n F0(B b) {
        switch (this.f) {
            case 0:
                return new A(b, 0);
            default:
                return new A(b, 1);
        }
    }

    public final boolean u0() {
        switch (this.f) {
            case 0:
                return false;
            default:
                return true;
        }
    }
}
