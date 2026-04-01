package J;

import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnalyticsListener.EventTime e;
    public final /* synthetic */ PlaybackException f;

    public /* synthetic */ b(AnalyticsListener.EventTime eventTime, PlaybackException playbackException, int i2) {
        this.d = i2;
        this.e = eventTime;
        this.f = playbackException;
    }

    public final void invoke(Object obj) {
        switch (this.d) {
            case 0:
                ((AnalyticsListener) obj).onPlayerErrorChanged(this.e, this.f);
                return;
            default:
                ((AnalyticsListener) obj).onPlayerError(this.e, this.f);
                return;
        }
    }
}
