package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ListenerSet.Event {
    public final /* synthetic */ int d;

    public /* synthetic */ m(int i2) {
        this.d = i2;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onAudioSessionIdChanged(this.d);
    }
}
