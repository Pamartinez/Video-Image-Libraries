package androidx.media3.exoplayer;

import androidx.media3.exoplayer.AudioBecomingNoisyManager;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker;
import androidx.media3.exoplayer.StreamVolumeManager;

/* renamed from: androidx.media3.exoplayer.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0079b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0079b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((AudioBecomingNoisyManager.AudioBecomingNoisyReceiver) obj).callListenerIfEnabled();
                return;
            case 1:
                ((DefaultSuitableOutputChecker.ImplApi23) obj).lambda$disable$2();
                return;
            case 2:
                ((DefaultSuitableOutputChecker.ImplApi35) obj).lambda$disable$2();
                return;
            case 3:
                ((ExoPlayerImpl) obj).lambda$new$3();
                return;
            default:
                ((StreamVolumeManager.VolumeChangeReceiver) obj).lambda$onReceive$0();
                return;
        }
    }
}
