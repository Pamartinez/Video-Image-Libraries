package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ Player.PositionInfo e;
    public final /* synthetic */ Player.PositionInfo f;

    public /* synthetic */ n(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) {
        this.d = i2;
        this.e = positionInfo;
        this.f = positionInfo2;
    }

    public final void invoke(Object obj) {
        ExoPlayerImpl.lambda$updatePlaybackInfo$15(this.d, this.e, this.f, (Player.Listener) obj);
    }
}
