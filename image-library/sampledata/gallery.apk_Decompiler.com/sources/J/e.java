package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ e(AnalyticsListener.EventTime eventTime, int i2, int i7) {
        this.d = eventTime;
        this.e = i2;
        this.f = i7;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onSurfaceSizeChanged(this.d, this.e, this.f);
    }
}
