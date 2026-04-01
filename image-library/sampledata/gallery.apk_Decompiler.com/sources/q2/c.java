package q2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ u e;

    public /* synthetic */ c(u uVar, int i2) {
        this.d = i2;
        this.e = uVar;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                u uVar = this.e;
                uVar.f1903x.removeCallbacks(uVar.y);
                uVar.t = 0;
                return;
            default:
                u uVar2 = this.e;
                uVar2.f1903x.removeCallbacks(uVar2.y);
                uVar2.t = 0;
                uVar2.o(true, false);
                return;
        }
    }
}
