package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaSourceList.ForwardingEventListener e;
    public final /* synthetic */ Pair f;

    public /* synthetic */ u(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, int i2) {
        this.d = i2;
        this.e = forwardingEventListener;
        this.f = pair;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onDrmKeysLoaded$7(this.f);
                return;
            case 1:
                this.e.lambda$onDrmSessionReleased$11(this.f);
                return;
            case 2:
                this.e.lambda$onDrmKeysRemoved$10(this.f);
                return;
            default:
                this.e.lambda$onDrmKeysRestored$9(this.f);
                return;
        }
    }
}
