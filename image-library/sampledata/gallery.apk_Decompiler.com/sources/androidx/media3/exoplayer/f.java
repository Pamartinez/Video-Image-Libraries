package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker;
import androidx.media3.exoplayer.ExoPlayerImplInternal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ f(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((DefaultSuitableOutputChecker.ImplApi23) this.f).lambda$enable$1((Context) this.e);
                return;
            case 1:
                ((DefaultSuitableOutputChecker.ImplApi35) this.f).lambda$enable$1((Context) this.e);
                return;
            case 2:
                ((ExoPlayerImpl) this.f).lambda$new$1((ExoPlayerImplInternal.PlaybackInfoUpdate) this.e);
                return;
            default:
                ((ExoPlayerImplInternal) this.f).lambda$sendMessageToTargetThread$2((PlayerMessage) this.e);
                return;
        }
    }
}
