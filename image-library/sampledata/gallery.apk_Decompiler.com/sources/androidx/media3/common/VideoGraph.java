package androidx.media3.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoGraph {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        VideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, Listener listener, Executor executor, long j2, boolean z);
    }

    Surface getInputSurface(int i2);

    int getPendingInputFrameCount(int i2);

    boolean hasProducedFrameWithTimestampZero();

    void initialize();

    boolean queueInputBitmap(int i2, Bitmap bitmap, TimestampIterator timestampIterator);

    void registerInput(int i2);

    boolean registerInputFrame(int i2);

    void registerInputStream(int i2, int i7, Format format, List<Effect> list, long j2);

    void release();

    void renderOutputFrame(long j2);

    void setCompositionEffects(List<Effect> list);

    void setCompositorSettings(VideoCompositorSettings videoCompositorSettings);

    void setOutputSurfaceInfo(SurfaceInfo surfaceInfo);

    void signalEndOfInput(int i2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onEnded(long j2);

        void onError(VideoFrameProcessingException videoFrameProcessingException);

        void onOutputFrameAvailableForRendering(long j2, boolean z);

        void onOutputSizeChanged(int i2, int i7);

        void onOutputFrameRateChanged(float f) {
        }
    }
}
