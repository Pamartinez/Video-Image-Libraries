package androidx.media3.exoplayer.drm;

import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ErrorStateDrmSession implements DrmSession {
    private final DrmSession.DrmSessionException error;

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    public DrmSession.DrmSessionException getError() {
        return this.error;
    }

    public int getState() {
        return 1;
    }

    public boolean playClearSamplesWithoutKeys() {
        return false;
    }

    public Map<String, String> queryKeyStatus() {
        return null;
    }

    public void release(DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }
}
