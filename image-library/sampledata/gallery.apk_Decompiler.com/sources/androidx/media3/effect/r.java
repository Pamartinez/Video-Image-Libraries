package androidx.media3.effect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FinalShaderProgramWrapper e;
    public final /* synthetic */ long f;

    public /* synthetic */ r(FinalShaderProgramWrapper finalShaderProgramWrapper, long j2, int i2) {
        this.d = i2;
        this.e = finalShaderProgramWrapper;
        this.f = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$queueInputFrame$1(this.f);
                return;
            default:
                this.e.lambda$queueInputFrame$2(this.f);
                return;
        }
    }
}
