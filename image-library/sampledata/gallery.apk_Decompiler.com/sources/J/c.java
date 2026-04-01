package J;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnalyticsListener.EventTime e;
    public final /* synthetic */ int f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ c(AnalyticsListener.EventTime eventTime, int i2, int i7, boolean z) {
        this.d = i7;
        this.e = eventTime;
        this.g = z;
        this.f = i2;
    }

    public final void invoke(Object obj) {
        switch (this.d) {
            case 0:
                ((AnalyticsListener) obj).onPlayWhenReadyChanged(this.e, this.g, this.f);
                return;
            case 1:
                ((AnalyticsListener) obj).onDeviceVolumeChanged(this.e, this.f, this.g);
                return;
            default:
                ((AnalyticsListener) obj).onPlayerStateChanged(this.e, this.g, this.f);
                return;
        }
    }

    public /* synthetic */ c(AnalyticsListener.EventTime eventTime, int i2, boolean z) {
        this.d = 1;
        this.e = eventTime;
        this.f = i2;
        this.g = z;
    }
}
