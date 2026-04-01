package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener d;
    public final /* synthetic */ Pair e;
    public final /* synthetic */ LoadEventInfo f;
    public final /* synthetic */ MediaLoadData g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ IOException f1026h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f1027i;

    public /* synthetic */ x(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.d = forwardingEventListener;
        this.e = pair;
        this.f = loadEventInfo;
        this.g = mediaLoadData;
        this.f1026h = iOException;
        this.f1027i = z;
    }

    public final void run() {
        this.d.lambda$onLoadError$3(this.e, this.f, this.g, this.f1026h, this.f1027i);
    }
}
