package androidx.media3.exoplayer;

/* renamed from: androidx.media3.exoplayer.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0078a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AudioBecomingNoisyManager e;

    public /* synthetic */ C0078a(AudioBecomingNoisyManager audioBecomingNoisyManager, int i2) {
        this.d = i2;
        this.e = audioBecomingNoisyManager;
    }

    public final void run() {
        int i2 = this.d;
        AudioBecomingNoisyManager audioBecomingNoisyManager = this.e;
        switch (i2) {
            case 0:
                audioBecomingNoisyManager.lambda$setEnabled$0();
                return;
            default:
                audioBecomingNoisyManager.lambda$setEnabled$1();
                return;
        }
    }
}
