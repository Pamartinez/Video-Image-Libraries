package androidx.media3.transformer;

import android.media.MediaCodec;
import android.media.metrics.LogSessionId;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ExoAssetLoaderAudioRenderer extends ExoAssetLoaderBaseRenderer {
    private final Codec.DecoderFactory decoderFactory;
    private boolean hasPendingConsumerInput;
    private final LogSessionId logSessionId;

    public ExoAssetLoaderAudioRenderer(Codec.DecoderFactory decoderFactory2, TransformerMediaClock transformerMediaClock, AssetLoader.Listener listener, LogSessionId logSessionId2) {
        super(1, transformerMediaClock, listener);
        this.decoderFactory = decoderFactory2;
        this.logSessionId = logSessionId2;
    }

    public boolean feedConsumerFromDecoder() {
        DecoderInputBuffer inputBuffer = this.sampleConsumer.getInputBuffer();
        if (inputBuffer == null) {
            return false;
        }
        if (!this.hasPendingConsumerInput) {
            if (this.decoder.isEnded()) {
                ((ByteBuffer) Assertions.checkNotNull(inputBuffer.data)).limit(0);
                inputBuffer.addFlag(4);
                this.isEnded = this.sampleConsumer.queueInputBuffer();
                return false;
            }
            ByteBuffer outputBuffer = this.decoder.getOutputBuffer();
            if (outputBuffer == null) {
                return false;
            }
            inputBuffer.ensureSpaceForWrite(outputBuffer.limit());
            inputBuffer.data.put(outputBuffer).flip();
            MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) Assertions.checkNotNull(this.decoder.getOutputBufferInfo());
            inputBuffer.timeUs = bufferInfo.presentationTimeUs;
            inputBuffer.setFlags(bufferInfo.flags);
            this.decoder.releaseOutputBuffer(false);
            this.hasPendingConsumerInput = true;
        }
        if (!this.sampleConsumer.queueInputBuffer()) {
            return false;
        }
        this.hasPendingConsumerInput = false;
        return true;
    }

    public String getName() {
        return "ExoAssetLoaderAudioRenderer";
    }

    public void initDecoder(Format format) {
        this.decoder = this.decoderFactory.createForAudioDecoding(format, this.logSessionId);
    }

    public boolean shouldDropInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.isEndOfStream()) {
            return false;
        }
        long j2 = decoderInputBuffer.timeUs - this.streamStartPositionUs;
        decoderInputBuffer.timeUs = j2;
        if (this.decoder == null || j2 >= 0) {
            return false;
        }
        decoderInputBuffer.clear();
        return true;
    }
}
