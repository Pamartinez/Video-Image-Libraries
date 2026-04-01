package androidx.media3.exoplayer.drm;

import K.a;
import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DrmSessionManager {
    public static final DrmSessionManager DRM_UNSUPPORTED = new DrmSessionManager() {
        public DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            if (format.drmInitData == null) {
                return null;
            }
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1), 6001));
        }

        public int getCryptoType(Format format) {
            if (format.drmInitData != null) {
                return 1;
            }
            return 0;
        }

        public void setPlayer(Looper looper, PlayerId playerId) {
        }
    };

    DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    int getCryptoType(Format format);

    DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        return DrmSessionReference.EMPTY;
    }

    void setPlayer(Looper looper, PlayerId playerId);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DrmSessionReference {
        public static final DrmSessionReference EMPTY = new a(2);

        void release();

        /* access modifiers changed from: private */
        static /* synthetic */ void lambda$static$0() {
        }
    }

    void prepare() {
    }

    void release() {
    }
}
