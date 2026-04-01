package androidx.media3.exoplayer.source;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ProgressiveMediaPeriod e;

    public /* synthetic */ d(ProgressiveMediaPeriod progressiveMediaPeriod, int i2) {
        this.d = i2;
        this.e = progressiveMediaPeriod;
    }

    public final void run() {
        int i2 = this.d;
        ProgressiveMediaPeriod progressiveMediaPeriod = this.e;
        switch (i2) {
            case 0:
                progressiveMediaPeriod.lambda$onLengthKnown$2();
                return;
            case 1:
                progressiveMediaPeriod.maybeFinishPrepare();
                return;
            default:
                progressiveMediaPeriod.lambda$new$0();
                return;
        }
    }
}
