package androidx.media3.exoplayer;

import android.os.HandlerThread;
import android.os.Looper;
import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaybackLooperProvider {
    private HandlerThread internalPlaybackThread;
    private final Object lock;
    private Looper playbackLooper;
    private int referenceCount;

    public PlaybackLooperProvider() {
        this((Looper) null);
    }

    public Looper obtainLooper() {
        Looper looper;
        boolean z;
        synchronized (this.lock) {
            try {
                if (this.playbackLooper == null) {
                    if (this.referenceCount == 0 && this.internalPlaybackThread == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Assertions.checkState(z);
                    HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
                    this.internalPlaybackThread = handlerThread;
                    handlerThread.start();
                    this.playbackLooper = this.internalPlaybackThread.getLooper();
                }
                this.referenceCount++;
                looper = this.playbackLooper;
            } catch (Throwable th) {
                throw th;
            }
        }
        return looper;
    }

    public void releaseLooper() {
        boolean z;
        HandlerThread handlerThread;
        synchronized (this.lock) {
            try {
                if (this.referenceCount > 0) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkState(z);
                int i2 = this.referenceCount - 1;
                this.referenceCount = i2;
                if (i2 == 0 && (handlerThread = this.internalPlaybackThread) != null) {
                    handlerThread.quit();
                    this.internalPlaybackThread = null;
                    this.playbackLooper = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public PlaybackLooperProvider(Looper looper) {
        this.lock = new Object();
        this.playbackLooper = looper;
        this.internalPlaybackThread = null;
        this.referenceCount = 0;
    }
}
