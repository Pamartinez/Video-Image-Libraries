package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener d;
    public final /* synthetic */ Pair e;
    public final /* synthetic */ int f;

    public /* synthetic */ v(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, int i2) {
        this.d = forwardingEventListener;
        this.e = pair;
        this.f = i2;
    }

    public final void run() {
        this.d.lambda$onDrmSessionAcquired$6(this.e, this.f);
    }
}
