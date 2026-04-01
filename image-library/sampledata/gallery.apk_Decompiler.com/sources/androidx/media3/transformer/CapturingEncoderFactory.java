package androidx.media3.transformer;

import android.media.metrics.LogSessionId;
import androidx.media3.common.Format;
import androidx.media3.transformer.Codec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class CapturingEncoderFactory implements Codec.EncoderFactory {
    private String audioEncoderName;
    private final Codec.EncoderFactory encoderFactory;
    private String videoEncoderName;

    public CapturingEncoderFactory(Codec.EncoderFactory encoderFactory2) {
        this.encoderFactory = encoderFactory2;
    }

    public boolean audioNeedsEncoding() {
        return this.encoderFactory.audioNeedsEncoding();
    }

    public Codec createForAudioEncoding(Format format, LogSessionId logSessionId) {
        Codec createForAudioEncoding = this.encoderFactory.createForAudioEncoding(format, logSessionId);
        this.audioEncoderName = createForAudioEncoding.getName();
        return createForAudioEncoding;
    }

    public Codec createForVideoEncoding(Format format, LogSessionId logSessionId) {
        Codec createForVideoEncoding = this.encoderFactory.createForVideoEncoding(format, logSessionId);
        this.videoEncoderName = createForVideoEncoding.getName();
        return createForVideoEncoding;
    }

    public String getAudioEncoderName() {
        return this.audioEncoderName;
    }

    public String getVideoEncoderName() {
        return this.videoEncoderName;
    }

    public boolean videoNeedsEncoding() {
        return this.encoderFactory.videoNeedsEncoding();
    }
}
