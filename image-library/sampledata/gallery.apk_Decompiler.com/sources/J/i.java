package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime d;
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;
    public final /* synthetic */ long g;

    public /* synthetic */ i(AnalyticsListener.EventTime eventTime, int i2, long j2, long j3) {
        this.d = eventTime;
        this.e = i2;
        this.f = j2;
        this.g = j3;
    }

    public final void invoke(Object obj) {
        DefaultAnalyticsCollector.lambda$onBandwidthSample$63(this.d, this.e, this.f, this.g, (AnalyticsListener) obj);
    }
}
