package androidx.media3.exoplayer;

import E2.h;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.exoplayer.StreamVolumeManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements BackgroundThreadStateHandler.StateChangeListener, h {
    public final /* synthetic */ StreamVolumeManager d;

    public /* synthetic */ y(StreamVolumeManager streamVolumeManager) {
        this.d = streamVolumeManager;
    }

    public Object apply(Object obj) {
        return this.d.lambda$release$12((StreamVolumeManager.StreamVolumeState) obj);
    }

    public void onStateChanged(Object obj, Object obj2) {
        this.d.onStreamVolumeStateChanged((StreamVolumeManager.StreamVolumeState) obj, (StreamVolumeManager.StreamVolumeState) obj2);
    }
}
