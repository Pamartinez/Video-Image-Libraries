package androidx.media3.effect;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.effect.GlShaderProgram;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class TextureManager implements GlShaderProgram.InputListener {
    private final Object lock = new Object();
    protected final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public TextureManager(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
    }

    public Surface getInputSurface() {
        throw new UnsupportedOperationException();
    }

    public abstract int getPendingFrameCount();

    public void queueInputBitmap(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        throw new UnsupportedOperationException();
    }

    public void queueInputTexture(int i2, long j2) {
        throw new UnsupportedOperationException();
    }

    public void registerInputFrame(FrameInfo frameInfo) {
        throw new UnsupportedOperationException();
    }

    public abstract void release();

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        throw new UnsupportedOperationException();
    }

    public abstract void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram);

    public abstract void signalEndOfCurrentInputStream();

    public void setInputFrameInfo(FrameInfo frameInfo, boolean z) {
    }
}
