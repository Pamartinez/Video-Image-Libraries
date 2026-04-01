package androidx.media3.transformer;

import android.media.MediaCodec;
import android.media.metrics.LogSessionId;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Codec {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DecoderFactory {
        Codec createForAudioDecoding(Format format, LogSessionId logSessionId);

        Codec createForVideoDecoding(Format format, Surface surface, boolean z, LogSessionId logSessionId);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EncoderFactory {
        boolean audioNeedsEncoding();

        Codec createForAudioEncoding(Format format, LogSessionId logSessionId);

        Codec createForVideoEncoding(Format format, LogSessionId logSessionId);

        boolean videoNeedsEncoding();
    }

    Format getConfigurationFormat();

    Format getInputFormat();

    Surface getInputSurface();

    int getMaxPendingFrameCount();

    String getName();

    ByteBuffer getOutputBuffer();

    MediaCodec.BufferInfo getOutputBufferInfo();

    Format getOutputFormat();

    boolean isEnded();

    boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer);

    void queueInputBuffer(DecoderInputBuffer decoderInputBuffer);

    void release();

    void releaseOutputBuffer(long j2);

    void releaseOutputBuffer(boolean z);

    void signalEndOfInputStream();
}
