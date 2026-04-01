package androidx.media3.transformer;

import android.media.metrics.LogSessionId;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.transformer.Codec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class CapturingDecoderFactory implements Codec.DecoderFactory {
    private String audioDecoderName;
    private final Codec.DecoderFactory decoderFactory;
    private String videoDecoderName;

    public CapturingDecoderFactory(Codec.DecoderFactory decoderFactory2) {
        this.decoderFactory = decoderFactory2;
    }

    public Codec createForAudioDecoding(Format format, LogSessionId logSessionId) {
        Codec createForAudioDecoding = this.decoderFactory.createForAudioDecoding(format, logSessionId);
        this.audioDecoderName = createForAudioDecoding.getName();
        return createForAudioDecoding;
    }

    public Codec createForVideoDecoding(Format format, Surface surface, boolean z, LogSessionId logSessionId) {
        Codec createForVideoDecoding = this.decoderFactory.createForVideoDecoding(format, surface, z, logSessionId);
        this.videoDecoderName = createForVideoDecoding.getName();
        return createForVideoDecoding;
    }

    public String getAudioDecoderName() {
        return this.audioDecoderName;
    }

    public String getVideoDecoderName() {
        return this.videoDecoderName;
    }
}
