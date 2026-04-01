package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import java.io.IOException;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DrmSession {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DrmSessionException extends IOException {
        public final int errorCode;

        public DrmSessionException(Throwable th, int i2) {
            super(th);
            this.errorCode = i2;
        }
    }

    DrmSessionException getError();

    int getState();

    boolean playClearSamplesWithoutKeys();

    Map<String, String> queryKeyStatus();

    void release(DrmSessionEventListener.EventDispatcher eventDispatcher);
}
