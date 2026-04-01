package androidx.media3.exoplayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ B(Object obj, boolean z, boolean z3, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = z;
        this.f = z3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((WakeLockManager) this.g).lambda$setEnabled$0(this.e, this.f);
                return;
            default:
                ((WifiLockManager) this.g).lambda$setEnabled$0(this.e, this.f);
                return;
        }
    }
}
