package androidx.media3.exoplayer;

import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.MediaPeriodHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ListenerSet.Event, MediaPeriodHolder.Factory {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ h(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void invoke(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((Player.Listener) obj).onMediaMetadataChanged((MediaMetadata) obj2);
                return;
            default:
                ((Player.Listener) obj).onDeviceInfoChanged((DeviceInfo) obj2);
                return;
        }
    }
}
