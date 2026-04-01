package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ l(int i2, int i7) {
        this.d = i2;
        this.e = i7;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onSurfaceSizeChanged(this.d, this.e);
    }
}
