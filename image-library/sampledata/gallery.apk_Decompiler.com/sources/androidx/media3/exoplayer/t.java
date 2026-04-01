package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaSourceList.ForwardingEventListener e;
    public final /* synthetic */ Pair f;
    public final /* synthetic */ LoadEventInfo g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f1024h;

    public /* synthetic */ t(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
        this.d = i2;
        this.e = forwardingEventListener;
        this.f = pair;
        this.g = loadEventInfo;
        this.f1024h = mediaLoadData;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onLoadCanceled$2(this.f, this.g, this.f1024h);
                return;
            default:
                this.e.lambda$onLoadCompleted$1(this.f, this.g, this.f1024h);
                return;
        }
    }
}
