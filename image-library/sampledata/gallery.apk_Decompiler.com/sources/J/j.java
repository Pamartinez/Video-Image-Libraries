package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnalyticsListener.EventTime e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ j(AnalyticsListener.EventTime eventTime, int i2, boolean z) {
        this.d = i2;
        this.e = eventTime;
        this.f = z;
    }

    public final void invoke(Object obj) {
        switch (this.d) {
            case 0:
                DefaultAnalyticsCollector.lambda$onIsLoadingChanged$35(this.e, this.f, (AnalyticsListener) obj);
                return;
            default:
                ((AnalyticsListener) obj).onIsPlayingChanged(this.e, this.f);
                return;
        }
    }
}
