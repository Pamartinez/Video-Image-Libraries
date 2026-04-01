package androidx.media3.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoFrameProcessor {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        VideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, Listener listener);
    }

    Surface getInputSurface();

    int getPendingInputFrameCount();

    boolean queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator);

    boolean queueInputTexture(int i2, long j2);

    boolean registerInputFrame();

    void registerInputStream(int i2, Format format, List<Effect> list, long j2);

    void release();

    void renderOutputFrame(long j2);

    void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener);

    void setOutputSurfaceInfo(SurfaceInfo surfaceInfo);

    void signalEndOfInput();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onEnded();

        void onError(VideoFrameProcessingException videoFrameProcessingException);

        void onOutputFrameRateChanged(float f) {
        }

        void onOutputFrameAvailableForRendering(long j2, boolean z) {
        }

        void onOutputSizeChanged(int i2, int i7) {
        }

        void onInputStreamRegistered(int i2, Format format, List<Effect> list) {
        }
    }
}
