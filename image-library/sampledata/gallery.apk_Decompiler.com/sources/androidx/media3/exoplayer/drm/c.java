package androidx.media3.exoplayer.drm;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ DefaultDrmSessionManager.PreacquiredSessionReference d;
    public final /* synthetic */ Format e;

    public /* synthetic */ c(DefaultDrmSessionManager.PreacquiredSessionReference preacquiredSessionReference, Format format) {
        this.d = preacquiredSessionReference;
        this.e = format;
    }

    public final void run() {
        this.d.lambda$acquire$0(this.e);
    }
}
