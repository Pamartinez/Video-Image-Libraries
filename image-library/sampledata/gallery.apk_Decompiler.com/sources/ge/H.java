package ge;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ L f;

    public /* synthetic */ H(L l, int i2, int i7) {
        this.d = i7;
        this.f = l;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.f.f.f(this.e);
                return;
            case 1:
                this.f.f.g(this.e);
                return;
            default:
                this.f.f.h(this.e);
                return;
        }
    }
}
