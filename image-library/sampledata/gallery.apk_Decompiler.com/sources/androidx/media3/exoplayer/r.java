package androidx.media3.exoplayer;

import F2.Q;
import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ r(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaPeriodQueue) this.e).lambda$notifyQueueUpdate$0((Q) this.f, (MediaSource.MediaPeriodId) this.g);
                return;
            case 1:
                ((MediaSourceList.ForwardingEventListener) this.e).lambda$onDrmSessionManagerError$8((Pair) this.f, (Exception) this.g);
                return;
            default:
                ((MediaSourceList.ForwardingEventListener) this.e).lambda$onDownstreamFormatChanged$5((Pair) this.f, (MediaLoadData) this.g);
                return;
        }
    }
}
