package androidx.media3.exoplayer.upstream;

import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener d;
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;
    public final /* synthetic */ long g;

    public /* synthetic */ a(BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener handlerAndListener, int i2, long j2, long j3) {
        this.d = handlerAndListener;
        this.e = i2;
        this.f = j2;
        this.g = j3;
    }

    public final void run() {
        this.d.listener.onBandwidthSample(this.e, this.f, this.g);
    }
}
