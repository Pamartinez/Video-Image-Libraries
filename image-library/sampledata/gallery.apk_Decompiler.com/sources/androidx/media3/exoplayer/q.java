package androidx.media3.exoplayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ ExoPlayerImplInternal d;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ q(ExoPlayerImplInternal exoPlayerImplInternal, int i2, boolean z) {
        this.d = exoPlayerImplInternal;
        this.e = i2;
        this.f = z;
    }

    public final void run() {
        this.d.lambda$maybeTriggerOnRendererReadyChanged$1(this.e, this.f);
    }
}
