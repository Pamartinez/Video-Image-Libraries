package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnalyticsListener.EventTime e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(AnalyticsListener.EventTime eventTime, int i2, int i7) {
        this.d = i7;
        this.e = eventTime;
        this.f = i2;
    }

    public final void invoke(Object obj) {
        switch (this.d) {
            case 0:
                ((AnalyticsListener) obj).onPlaybackSuppressionReasonChanged(this.e, this.f);
                return;
            case 1:
                ((AnalyticsListener) obj).onPlaybackStateChanged(this.e, this.f);
                return;
            case 2:
                DefaultAnalyticsCollector.lambda$onDrmSessionAcquired$64(this.e, this.f, (AnalyticsListener) obj);
                return;
            case 3:
                ((AnalyticsListener) obj).onAudioSessionIdChanged(this.e, this.f);
                return;
            default:
                ((AnalyticsListener) obj).onTimelineChanged(this.e, this.f);
                return;
        }
    }
}
