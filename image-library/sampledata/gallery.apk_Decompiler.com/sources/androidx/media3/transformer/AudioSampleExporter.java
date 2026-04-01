package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.DebugTraceUtil;
import java.nio.ByteBuffer;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AudioSampleExporter extends SampleExporter {
    private final AudioGraph audioGraph;
    private final Codec encoder;
    private final AudioProcessor.AudioFormat encoderInputAudioFormat;
    private final DecoderInputBuffer encoderInputBuffer;
    private final DecoderInputBuffer encoderOutputBuffer;
    private long encoderTotalInputBytes;
    private final AudioGraphInput firstInput;
    private final Format firstInputFormat;
    private boolean returnedFirstInput;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v0, types: [F2.N, F2.Q] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AudioSampleExporter(androidx.media3.common.Format r6, androidx.media3.common.Format r7, androidx.media3.transformer.TransformationRequest r8, androidx.media3.transformer.EditedMediaItem r9, F2.U r10, androidx.media3.transformer.AudioMixer.Factory r11, androidx.media3.transformer.Codec.EncoderFactory r12, androidx.media3.transformer.MuxerWrapper r13, androidx.media3.transformer.FallbackListener r14, android.media.metrics.LogSessionId r15) {
        /*
            r5 = this;
            r5.<init>(r6, r13)
            androidx.media3.common.audio.SonicAudioProcessor r0 = new androidx.media3.common.audio.SonicAudioProcessor
            r0.<init>()
            androidx.media3.transformer.AudioGraph r1 = new androidx.media3.transformer.AudioGraph
            F2.Q r2 = new F2.Q
            r3 = 4
            r2.<init>(r3)
            r2.c(r10)
            r2.a(r0)
            F2.y0 r10 = r2.f()
            r1.<init>(r11, r10)
            r5.audioGraph = r1
            r5.firstInputFormat = r7
            androidx.media3.transformer.AudioGraphInput r10 = r1.registerInput(r9, r7)
            androidx.media3.common.audio.AudioProcessor$AudioFormat r11 = r1.getOutputAudioFormat()
            androidx.media3.common.audio.AudioProcessor$AudioFormat r2 = androidx.media3.common.audio.AudioProcessor.AudioFormat.NOT_SET
            boolean r2 = r11.equals(r2)
            r3 = 1
            r2 = r2 ^ r3
            androidx.media3.common.util.Assertions.checkState(r2)
            androidx.media3.common.Format$Builder r2 = new androidx.media3.common.Format$Builder
            r2.<init>()
            java.lang.String r4 = r8.audioMimeType
            if (r4 == 0) goto L_0x003e
            goto L_0x0047
        L_0x003e:
            java.lang.String r6 = r6.sampleMimeType
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)
            r4 = r6
            java.lang.String r4 = (java.lang.String) r4
        L_0x0047:
            androidx.media3.common.Format$Builder r6 = r2.setSampleMimeType(r4)
            int r2 = r11.sampleRate
            androidx.media3.common.Format$Builder r6 = r6.setSampleRate(r2)
            int r2 = r11.channelCount
            androidx.media3.common.Format$Builder r6 = r6.setChannelCount(r2)
            int r2 = r11.encoding
            androidx.media3.common.Format$Builder r6 = r6.setPcmEncoding(r2)
            java.lang.String r2 = r7.codecs
            androidx.media3.common.Format$Builder r6 = r6.setCodecs(r2)
            androidx.media3.common.Format r6 = r6.build()
            androidx.media3.common.Format$Builder r2 = r6.buildUpon()
            F2.U r13 = r13.getSupportedSampleMimeTypes(r3)
            java.lang.String r13 = androidx.media3.transformer.SampleExporter.findSupportedMimeTypeForEncoderAndMuxer(r6, r13)
            androidx.media3.common.Format$Builder r13 = r2.setSampleMimeType(r13)
            androidx.media3.common.Format r13 = r13.build()
            androidx.media3.transformer.Codec r12 = r12.createForAudioEncoding(r13, r15)
            r5.encoder = r12
            androidx.media3.common.audio.AudioProcessor$AudioFormat r13 = new androidx.media3.common.audio.AudioProcessor$AudioFormat
            androidx.media3.common.Format r15 = r12.getInputFormat()
            r13.<init>(r15)
            int r15 = r13.sampleRate
            int r2 = r11.sampleRate
            if (r15 == r2) goto L_0x00a0
            r1.reset()
            int r10 = r13.sampleRate
            r0.setOutputSampleRateHz(r10)
            androidx.media3.transformer.AudioGraphInput r10 = r1.registerInput(r9, r7)
            androidx.media3.common.audio.AudioProcessor$AudioFormat r11 = r1.getOutputAudioFormat()
        L_0x00a0:
            r5.firstInput = r10
            r5.encoderInputAudioFormat = r11
            androidx.media3.decoder.DecoderInputBuffer r7 = new androidx.media3.decoder.DecoderInputBuffer
            r9 = 0
            r7.<init>(r9)
            r5.encoderInputBuffer = r7
            androidx.media3.decoder.DecoderInputBuffer r7 = new androidx.media3.decoder.DecoderInputBuffer
            r7.<init>(r9)
            r5.encoderOutputBuffer = r7
            androidx.media3.common.Format r5 = r12.getConfigurationFormat()
            androidx.media3.transformer.TransformationRequest r5 = createFallbackTransformationRequest(r8, r6, r5)
            r14.onTransformationRequestFinalized(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.AudioSampleExporter.<init>(androidx.media3.common.Format, androidx.media3.common.Format, androidx.media3.transformer.TransformationRequest, androidx.media3.transformer.EditedMediaItem, F2.U, androidx.media3.transformer.AudioMixer$Factory, androidx.media3.transformer.Codec$EncoderFactory, androidx.media3.transformer.MuxerWrapper, androidx.media3.transformer.FallbackListener, android.media.metrics.LogSessionId):void");
    }

    private static TransformationRequest createFallbackTransformationRequest(TransformationRequest transformationRequest, Format format, Format format2) {
        if (Objects.equals(format.sampleMimeType, format2.sampleMimeType)) {
            return transformationRequest;
        }
        return transformationRequest.buildUpon().setAudioMimeType(format2.sampleMimeType).build();
    }

    private void feedEncoder(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = (ByteBuffer) Assertions.checkNotNull(this.encoderInputBuffer.data);
        int limit = byteBuffer.limit();
        byteBuffer.limit(Math.min(limit, byteBuffer2.capacity() + byteBuffer.position()));
        byteBuffer2.put(byteBuffer);
        this.encoderInputBuffer.timeUs = getOutputAudioDurationUs();
        this.encoderTotalInputBytes += (long) byteBuffer2.position();
        this.encoderInputBuffer.setFlags(0);
        this.encoderInputBuffer.flip();
        byteBuffer.limit(limit);
        this.encoder.queueInputBuffer(this.encoderInputBuffer);
    }

    private long getOutputAudioDurationUs() {
        long j2 = this.encoderTotalInputBytes;
        AudioProcessor.AudioFormat audioFormat = this.encoderInputAudioFormat;
        return ((j2 / ((long) audioFormat.bytesPerFrame)) * 1000000) / ((long) audioFormat.sampleRate);
    }

    private void queueEndOfStreamToEncoder() {
        boolean z;
        if (((ByteBuffer) Assertions.checkNotNull(this.encoderInputBuffer.data)).position() == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.encoderInputBuffer.timeUs = getOutputAudioDurationUs();
        this.encoderInputBuffer.addFlag(4);
        this.encoderInputBuffer.flip();
        this.encoder.queueInputBuffer(this.encoderInputBuffer);
    }

    public DecoderInputBuffer getMuxerInputBuffer() {
        this.encoderOutputBuffer.data = this.encoder.getOutputBuffer();
        DecoderInputBuffer decoderInputBuffer = this.encoderOutputBuffer;
        if (decoderInputBuffer.data == null) {
            return null;
        }
        decoderInputBuffer.timeUs = ((MediaCodec.BufferInfo) Assertions.checkNotNull(this.encoder.getOutputBufferInfo())).presentationTimeUs;
        this.encoderOutputBuffer.setFlags(1);
        return this.encoderOutputBuffer;
    }

    public Format getMuxerInputFormat() {
        return this.encoder.getOutputFormat();
    }

    public boolean isMuxerInputEnded() {
        return this.encoder.isEnded();
    }

    public boolean processDataUpToMuxer() {
        ByteBuffer output = this.audioGraph.getOutput();
        if (!this.encoder.maybeDequeueInputBuffer(this.encoderInputBuffer)) {
            return false;
        }
        if (this.audioGraph.isEnded()) {
            DebugTraceUtil.logEvent("AudioGraph", "OutputEnded", Long.MIN_VALUE);
            queueEndOfStreamToEncoder();
            return false;
        } else if (!output.hasRemaining()) {
            return false;
        } else {
            feedEncoder(output);
            return true;
        }
    }

    public void release() {
        this.audioGraph.reset();
        this.encoder.release();
    }

    public void releaseMuxerInputBuffer() {
        this.encoder.releaseOutputBuffer(false);
    }

    public AudioGraphInput getInput(EditedMediaItem editedMediaItem, Format format, int i2) {
        if (this.returnedFirstInput) {
            return this.audioGraph.registerInput(editedMediaItem, format);
        }
        this.returnedFirstInput = true;
        Assertions.checkState(format.equals(this.firstInputFormat));
        return this.firstInput;
    }
}
