package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener d;
    public final /* synthetic */ Pair e;
    public final /* synthetic */ LoadEventInfo f;
    public final /* synthetic */ MediaLoadData g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f1025h;

    public /* synthetic */ w(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
        this.d = forwardingEventListener;
        this.e = pair;
        this.f = loadEventInfo;
        this.g = mediaLoadData;
        this.f1025h = i2;
    }

    public final void run() {
        this.d.lambda$onLoadStarted$0(this.e, this.f, this.g, this.f1025h);
    }
}
