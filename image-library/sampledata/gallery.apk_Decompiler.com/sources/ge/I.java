package ge;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class I implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ L e;

    public /* synthetic */ I(L l, int i2) {
        this.d = i2;
        this.e = l;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.f.j();
                return;
            case 1:
                this.e.c();
                return;
            case 2:
                this.e.f.flush();
                return;
            default:
                this.e.f.t();
                return;
        }
    }
}
