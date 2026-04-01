package androidx.media3.transformer;

import android.media.MediaCodec;
import android.media.metrics.LogSessionId;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ExoAssetLoaderVideoRenderer extends ExoAssetLoaderBaseRenderer {
    private final List<Long> decodeOnlyPresentationTimestamps = new ArrayList();
    private final Codec.DecoderFactory decoderFactory;
    private final boolean flattenForSlowMotion;
    private final int hdrMode;
    private final LogSessionId logSessionId;
    private int maxDecoderPendingFrameCount = -1;
    private SefSlowMotionFlattener sefVideoSlowMotionFlattener;

    public ExoAssetLoaderVideoRenderer(boolean z, Codec.DecoderFactory decoderFactory2, int i2, TransformerMediaClock transformerMediaClock, AssetLoader.Listener listener, LogSessionId logSessionId2) {
        super(2, transformerMediaClock, listener);
        this.flattenForSlowMotion = z;
        this.decoderFactory = decoderFactory2;
        this.hdrMode = i2;
        this.logSessionId = logSessionId2;
    }

    private boolean isDecodeOnlyBuffer(long j2) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.decodeOnlyPresentationTimestamps.get(i2).longValue() == j2) {
                this.decodeOnlyPresentationTimestamps.remove(i2);
                return true;
            }
        }
        return false;
    }

    public boolean feedConsumerFromDecoder() {
        if (this.decoder.isEnded()) {
            this.sampleConsumer.signalEndOfVideoInput();
            this.isEnded = true;
            return false;
        }
        MediaCodec.BufferInfo outputBufferInfo = this.decoder.getOutputBufferInfo();
        if (outputBufferInfo == null) {
            return false;
        }
        long j2 = outputBufferInfo.presentationTimeUs;
        long j3 = j2 - this.streamStartPositionUs;
        if (j3 < 0 || isDecodeOnlyBuffer(j2)) {
            this.decoder.releaseOutputBuffer(false);
            return true;
        } else if (this.sampleConsumer.getPendingVideoFrameCount() == this.maxDecoderPendingFrameCount || !this.sampleConsumer.registerVideoFrame(j3)) {
            return false;
        } else {
            this.decoder.releaseOutputBuffer(j3);
            return true;
        }
    }

    public long getDurationToProgressUs(long j2, long j3) {
        if (getState() == 1) {
            return 1000000;
        }
        int i2 = this.maxDecoderPendingFrameCount;
        if (i2 == -1) {
            return 10000;
        }
        return ((long) i2) * 2000;
    }

    public String getName() {
        return "ExoAssetLoaderVideoRenderer";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4.hdrMode == 1) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initDecoder(androidx.media3.common.Format r5) {
        /*
            r4 = this;
            androidx.media3.transformer.SampleConsumer r0 = r4.sampleConsumer
            androidx.media3.common.util.Assertions.checkStateNotNull(r0)
            androidx.media3.common.ColorInfo r0 = r5.colorInfo
            boolean r0 = androidx.media3.common.ColorInfo.isTransferHdr(r0)
            if (r0 == 0) goto L_0x0013
            int r0 = r4.hdrMode
            r1 = 1
            if (r0 != r1) goto L_0x0013
            goto L_0x0014
        L_0x0013:
            r1 = 0
        L_0x0014:
            androidx.media3.transformer.Codec$DecoderFactory r0 = r4.decoderFactory
            androidx.media3.transformer.SampleConsumer r2 = r4.sampleConsumer
            android.view.Surface r2 = r2.getInputSurface()
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
            android.view.Surface r2 = (android.view.Surface) r2
            android.media.metrics.LogSessionId r3 = r4.logSessionId
            androidx.media3.transformer.Codec r5 = r0.createForVideoDecoding(r5, r2, r1, r3)
            r4.decoder = r5
            int r5 = r5.getMaxPendingFrameCount()
            r4.maxDecoderPendingFrameCount = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.ExoAssetLoaderVideoRenderer.initDecoder(androidx.media3.common.Format):void");
    }

    public void onDecoderInputReady(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.timeUs < getLastResetPositionUs()) {
            this.decodeOnlyPresentationTimestamps.add(Long.valueOf(decoderInputBuffer.timeUs));
        }
    }

    public void onInputFormatRead(Format format) {
        if (this.flattenForSlowMotion) {
            this.sefVideoSlowMotionFlattener = new SefSlowMotionFlattener(format);
        }
    }

    public Format overrideInputFormat(Format format) {
        if (this.hdrMode != 3 || !ColorInfo.isTransferHdr(format.colorInfo)) {
            return format;
        }
        return format.buildUpon().setColorInfo(ColorInfo.SDR_BT709_LIMITED).build();
    }

    public Format overrideOutputFormat(Format format) {
        ColorInfo validColor = TransformerUtil.getValidColor(format.colorInfo);
        boolean z = true;
        if (this.hdrMode != 1) {
            z = false;
        }
        return format.buildUpon().setColorInfo(TransformerUtil.getDecoderOutputColor(validColor, z)).build();
    }

    public boolean shouldDropInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.isEndOfStream()) {
            return false;
        }
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data);
        if (this.sefVideoSlowMotionFlattener != null) {
            long streamOffsetUs = getStreamOffsetUs();
            if (this.sefVideoSlowMotionFlattener.dropOrTransformSample(byteBuffer, decoderInputBuffer.timeUs - streamOffsetUs)) {
                byteBuffer.clear();
                return true;
            }
            decoderInputBuffer.timeUs = streamOffsetUs + this.sefVideoSlowMotionFlattener.getSamplePresentationTimeUs();
        }
        if (this.decoder == null) {
            decoderInputBuffer.timeUs -= this.streamStartPositionUs;
        }
        return false;
    }
}
