package K;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DrmSessionEventListener.EventDispatcher e;
    public final /* synthetic */ DrmSessionEventListener f;

    public /* synthetic */ b(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, int i2) {
        this.d = i2;
        this.e = eventDispatcher;
        this.f = drmSessionEventListener;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$drmSessionReleased$5(this.f);
                return;
            case 1:
                this.e.lambda$drmKeysRemoved$4(this.f);
                return;
            case 2:
                this.e.lambda$drmKeysRestored$3(this.f);
                return;
            default:
                this.e.lambda$drmKeysLoaded$1(this.f);
                return;
        }
    }
}
