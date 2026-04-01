package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnalyticsListener.EventTime e;
    public final /* synthetic */ LoadEventInfo f;
    public final /* synthetic */ MediaLoadData g;

    public /* synthetic */ h(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
        this.d = i2;
        this.e = eventTime;
        this.f = loadEventInfo;
        this.g = mediaLoadData;
    }

    public final void invoke(Object obj) {
        switch (this.d) {
            case 0:
                ((AnalyticsListener) obj).onLoadCanceled(this.e, this.f, this.g);
                return;
            default:
                ((AnalyticsListener) obj).onLoadCompleted(this.e, this.f, this.g);
                return;
        }
    }
}
