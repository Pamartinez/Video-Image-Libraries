package androidx.media3.effect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ FinalShaderProgramWrapper d;
    public final /* synthetic */ Exception e;
    public final /* synthetic */ long f;

    public /* synthetic */ q(FinalShaderProgramWrapper finalShaderProgramWrapper, Exception exc, long j2) {
        this.d = finalShaderProgramWrapper;
        this.e = exc;
        this.f = j2;
    }

    public final void run() {
        this.d.lambda$renderFrame$6(this.e, this.f);
    }
}
