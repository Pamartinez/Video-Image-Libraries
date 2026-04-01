package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GlShaderProgram {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ErrorListener {
        void onError(VideoFrameProcessingException videoFrameProcessingException);
    }

    void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j2);

    void release();

    void releaseOutputFrame(GlTextureInfo glTextureInfo);

    void setErrorListener(Executor executor, ErrorListener errorListener);

    void setInputListener(InputListener inputListener);

    void setOutputListener(OutputListener outputListener);

    void signalEndOfCurrentInputStream();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface InputListener {
        void onReadyToAcceptInputFrame() {
        }

        void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OutputListener {
        void onCurrentOutputStreamEnded() {
        }

        void onOutputFrameAvailable(GlTextureInfo glTextureInfo, long j2) {
        }
    }
}
