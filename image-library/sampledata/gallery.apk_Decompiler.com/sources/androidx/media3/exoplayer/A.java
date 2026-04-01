package androidx.media3.exoplayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;

    public /* synthetic */ A(Object obj, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((WakeLockManager) this.f).lambda$setStayAwake$1(this.e);
                return;
            default:
                ((WifiLockManager) this.f).lambda$setStayAwake$1(this.e);
                return;
        }
    }
}
