package androidx.media3.exoplayer;

import E2.h;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.StreamVolumeManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ListenerSet.Event, h {
    public Object apply(Object obj) {
        return StreamVolumeManager.lambda$release$11((StreamVolumeManager.StreamVolumeState) obj);
    }

    public void invoke(Object obj) {
        ((Player.Listener) obj).onPlayerError(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(1), 1003));
    }
}
