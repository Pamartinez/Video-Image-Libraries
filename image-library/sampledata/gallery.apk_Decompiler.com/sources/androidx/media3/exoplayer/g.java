package androidx.media3.exoplayer;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ g(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }

    public final void invoke(Object obj) {
        switch (this.d) {
            case 0:
                ((Player.Listener) obj).onTimelineChanged(((PlaybackInfo) this.f).timeline, this.e);
                return;
            default:
                ((Player.Listener) obj).onMediaItemTransition((MediaItem) this.f, this.e);
                return;
        }
    }
}
