package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnalyticsListener.EventTime e;

    public /* synthetic */ f(AnalyticsListener.EventTime eventTime, int i2) {
        this.d = i2;
        this.e = eventTime;
    }

    public final void invoke(Object obj) {
        int i2 = this.d;
        AnalyticsListener.EventTime eventTime = this.e;
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (i2) {
            case 0:
                analyticsListener.onPlayerReleased(eventTime);
                return;
            case 1:
                analyticsListener.onDrmSessionReleased(eventTime);
                return;
            case 2:
                analyticsListener.onDrmKeysRemoved(eventTime);
                return;
            case 3:
                analyticsListener.onDrmKeysRestored(eventTime);
                return;
            default:
                analyticsListener.onDrmKeysLoaded(eventTime);
                return;
        }
    }
}
