package androidx.media3.effect;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.VideoFrameProcessingException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoCompositor extends GlTextureProducer {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onEnded();

        void onError(VideoFrameProcessingException videoFrameProcessingException);
    }

    void queueInputTexture(int i2, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, ColorInfo colorInfo, long j2);

    void registerInputSource(int i2);

    void release();

    void setVideoCompositorSettings(VideoCompositorSettings videoCompositorSettings);

    void signalEndOfInputSource(int i2);
}
