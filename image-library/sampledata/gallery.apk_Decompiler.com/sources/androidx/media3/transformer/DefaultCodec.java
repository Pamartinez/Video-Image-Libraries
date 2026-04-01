package androidx.media3.transformer;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.ExportException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultCodec implements Codec {
    private final Format configurationFormat;
    private final MediaFormat configurationMediaFormat;
    private int inputBufferIndex = -1;
    private boolean inputStreamEnded;
    private final Surface inputSurface;
    private final boolean isDecoder;
    private final boolean isVideo;
    private final int maxPendingFrameCount;
    private final MediaCodec mediaCodec;
    private ByteBuffer outputBuffer;
    private int outputBufferIndex = -1;
    private final MediaCodec.BufferInfo outputBufferInfo = new MediaCodec.BufferInfo();
    private Format outputFormat;
    private boolean outputStreamEnded;
    private final AtomicBoolean videoOutputStarted = new AtomicBoolean();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api29 {
        public static String getCanonicalName(MediaCodec mediaCodec) {
            return mediaCodec.getCanonicalName();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.view.Surface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.media.MediaCodec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.media.MediaCodec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.view.Surface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.view.Surface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: android.media.MediaCodec} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultCodec(android.content.Context r10, androidx.media3.common.Format r11, android.media.MediaFormat r12, java.lang.String r13, boolean r14, android.view.Surface r15) {
        /*
            r9 = this;
            r9.<init>()
            r9.configurationFormat = r11
            r9.configurationMediaFormat = r12
            r9.isDecoder = r14
            java.lang.String r0 = r11.sampleMimeType
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = androidx.media3.common.MimeTypes.isVideo(r0)
            r9.isVideo = r2
            android.media.MediaCodec$BufferInfo r0 = new android.media.MediaCodec$BufferInfo
            r0.<init>()
            r9.outputBufferInfo = r0
            r0 = -1
            r9.inputBufferIndex = r0
            r9.outputBufferIndex = r0
            java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean
            r0.<init>()
            r9.videoOutputStarted = r0
            java.lang.String r6 = "%s"
            java.lang.Object[] r7 = new java.lang.Object[]{r11}
            java.lang.String r3 = "InputFormat"
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1 = r14
            androidx.media3.effect.DebugTraceUtil.logCodecEvent(r1, r2, r3, r4, r6, r7)
            r11 = r1
            boolean r14 = isSdrToneMappingEnabled(r12)
            r1 = 0
            android.media.MediaCodec r3 = android.media.MediaCodec.createByCodecName(r13)     // Catch:{ Exception -> 0x0071 }
            configureCodec(r3, r12, r11, r15)     // Catch:{ Exception -> 0x0058 }
            if (r14 == 0) goto L_0x005b
            android.media.MediaFormat r14 = r3.getInputFormat()     // Catch:{ Exception -> 0x0058 }
            boolean r14 = isSdrToneMappingEnabled(r14)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r15 = "Tone-mapping requested but not supported by the decoder."
            androidx.media3.common.util.Assertions.checkArgument(r14, r15)     // Catch:{ Exception -> 0x0058 }
            goto L_0x005b
        L_0x0058:
            r0 = move-exception
            r10 = r0
            goto L_0x0074
        L_0x005b:
            if (r2 == 0) goto L_0x0063
            if (r11 != 0) goto L_0x0063
            android.view.Surface r1 = r3.createInputSurface()     // Catch:{ Exception -> 0x0058 }
        L_0x0063:
            startCodec(r3)     // Catch:{ Exception -> 0x0058 }
            r9.mediaCodec = r3
            r9.inputSurface = r1
            int r10 = androidx.media3.common.util.Util.getMaxPendingFramesCountForMediaCodecDecoders(r10)
            r9.maxPendingFrameCount = r10
            return
        L_0x0071:
            r0 = move-exception
            r10 = r0
            r3 = r1
        L_0x0074:
            java.lang.String r14 = "DefaultCodec"
            java.lang.String r15 = "MediaCodec error"
            androidx.media3.common.util.Log.d(r14, r15, r10)
            if (r1 == 0) goto L_0x0080
            r1.release()
        L_0x0080:
            if (r3 == 0) goto L_0x0085
            r3.release()
        L_0x0085:
            boolean r14 = r10 instanceof java.io.IOException
            if (r14 != 0) goto L_0x009d
            boolean r14 = r10 instanceof android.media.MediaCodec.CodecException
            if (r14 == 0) goto L_0x008e
            goto L_0x009d
        L_0x008e:
            boolean r14 = r10 instanceof java.lang.IllegalArgumentException
            if (r14 == 0) goto L_0x009a
            if (r11 == 0) goto L_0x0097
            r14 = 3003(0xbbb, float:4.208E-42)
            goto L_0x00a4
        L_0x0097:
            r14 = 4003(0xfa3, float:5.61E-42)
            goto L_0x00a4
        L_0x009a:
            r14 = 1001(0x3e9, float:1.403E-42)
            goto L_0x00a4
        L_0x009d:
            if (r11 == 0) goto L_0x00a2
            r14 = 3001(0xbb9, float:4.205E-42)
            goto L_0x00a4
        L_0x00a2:
            r14 = 4001(0xfa1, float:5.607E-42)
        L_0x00a4:
            boolean r9 = r9.isVideo
            r8 = r10
            r10 = r9
            r9 = r12
            r12 = r8
            r8 = r14
            r14 = r13
            r13 = r8
            androidx.media3.transformer.ExportException r9 = createExportException(r9, r10, r11, r12, r13, r14)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.DefaultCodec.<init>(android.content.Context, androidx.media3.common.Format, android.media.MediaFormat, java.lang.String, boolean, android.view.Surface):void");
    }

    private static void configureCodec(MediaCodec mediaCodec2, MediaFormat mediaFormat, boolean z, Surface surface) {
        TraceUtil.beginSection("configureCodec");
        mediaCodec2.configure(mediaFormat, surface, (MediaCrypto) null, z ^ true ? 1 : 0);
        TraceUtil.endSection();
    }

    private static Format convertToFormat(MediaFormat mediaFormat, boolean z, Metadata metadata) {
        Format createFormatFromMediaFormat = MediaFormatUtil.createFormatFromMediaFormat(mediaFormat);
        Format.Builder metadata2 = createFormatFromMediaFormat.buildUpon().setMetadata(metadata);
        if (z && createFormatFromMediaFormat.pcmEncoding == -1 && Objects.equals(createFormatFromMediaFormat.sampleMimeType, "audio/raw")) {
            metadata2.setPcmEncoding(2);
        }
        return metadata2.build();
    }

    private ExportException createExportException(Exception exc) {
        MediaFormat mediaFormat = this.configurationMediaFormat;
        boolean z = this.isVideo;
        boolean z3 = this.isDecoder;
        return createExportException(mediaFormat, z, z3, exc, z3 ? 3002 : 4002, getName());
    }

    private void debugTraceLogEvent(String str, long j2) {
        debugTraceLogEvent(str, j2, "", new Object[0]);
    }

    private static boolean isSdrToneMappingEnabled(MediaFormat mediaFormat) {
        if (Build.VERSION.SDK_INT < 31 || MediaFormatUtil.getInteger(mediaFormat, "color-transfer-request", 0) != 3) {
            return false;
        }
        return true;
    }

    private boolean maybeDequeueOutputBuffer(boolean z) {
        if (this.outputBufferIndex >= 0) {
            return true;
        }
        if (this.outputStreamEnded) {
            return false;
        }
        try {
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(this.outputBufferInfo, 0);
            this.outputBufferIndex = dequeueOutputBuffer;
            if (dequeueOutputBuffer < 0) {
                if (dequeueOutputBuffer == -2) {
                    this.outputFormat = convertToFormat(this.mediaCodec.getOutputFormat(), this.isDecoder, this.configurationFormat.metadata);
                    if (this.isDecoder && Objects.equals(this.configurationFormat.sampleMimeType, "audio/raw")) {
                        this.outputFormat = this.outputFormat.buildUpon().setChannelCount(this.configurationFormat.channelCount).setPcmEncoding(this.configurationFormat.pcmEncoding).build();
                    }
                    if (!this.isDecoder && this.isVideo) {
                        this.videoOutputStarted.set(true);
                    }
                    debugTraceLogEvent("OutputFormat", this.outputBufferInfo.presentationTimeUs, "%s", this.outputFormat);
                }
                return false;
            }
            if ((this.outputBufferInfo.flags & 4) != 0) {
                this.outputStreamEnded = true;
                debugTraceLogEvent("OutputEnded", Long.MIN_VALUE);
                MediaCodec.BufferInfo bufferInfo = this.outputBufferInfo;
                if (bufferInfo.size == 0) {
                    releaseOutputBuffer(false);
                    return false;
                }
                bufferInfo.flags &= -5;
            }
            if ((this.outputBufferInfo.flags & 2) != 0) {
                releaseOutputBuffer(false);
                return false;
            }
            if (z) {
                try {
                    ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(this.mediaCodec.getOutputBuffer(this.outputBufferIndex));
                    this.outputBuffer = byteBuffer;
                    byteBuffer.position(this.outputBufferInfo.offset);
                    ByteBuffer byteBuffer2 = this.outputBuffer;
                    MediaCodec.BufferInfo bufferInfo2 = this.outputBufferInfo;
                    byteBuffer2.limit(bufferInfo2.offset + bufferInfo2.size);
                } catch (RuntimeException e) {
                    RuntimeException runtimeException = e;
                    Log.d("DefaultCodec", "MediaCodec error", runtimeException);
                    throw createExportException(runtimeException);
                }
            }
            return true;
        } catch (RuntimeException e7) {
            RuntimeException runtimeException2 = e7;
            Log.d("DefaultCodec", "MediaCodec error", runtimeException2);
            throw createExportException(runtimeException2);
        }
    }

    private static void startCodec(MediaCodec mediaCodec2) {
        TraceUtil.beginSection("startCodec");
        mediaCodec2.start();
        TraceUtil.endSection();
    }

    public Format getConfigurationFormat() {
        return this.configurationFormat;
    }

    public Format getInputFormat() {
        try {
            return convertToFormat(this.mediaCodec.getInputFormat(), this.isDecoder, this.configurationFormat.metadata);
        } catch (RuntimeException e) {
            Log.d("DefaultCodec", "MediaCodec error", e);
            throw createExportException(e);
        }
    }

    public Surface getInputSurface() {
        return (Surface) Assertions.checkStateNotNull(this.inputSurface);
    }

    public int getMaxPendingFrameCount() {
        return this.maxPendingFrameCount;
    }

    public String getName() {
        return Api29.getCanonicalName(this.mediaCodec);
    }

    public ByteBuffer getOutputBuffer() {
        if (!maybeDequeueOutputBuffer(true)) {
            return null;
        }
        MediaCodec.BufferInfo bufferInfo = this.outputBufferInfo;
        debugTraceLogEvent("ProducedOutput", bufferInfo.presentationTimeUs, "bytesOutput=%s", Integer.valueOf(bufferInfo.size));
        return this.outputBuffer;
    }

    public MediaCodec.BufferInfo getOutputBufferInfo() {
        if (maybeDequeueOutputBuffer(false)) {
            return this.outputBufferInfo;
        }
        return null;
    }

    public Format getOutputFormat() {
        maybeDequeueOutputBuffer(false);
        return this.outputFormat;
    }

    public boolean isEnded() {
        if (!this.outputStreamEnded || this.outputBufferIndex != -1) {
            return false;
        }
        return true;
    }

    public boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (this.inputStreamEnded) {
            return false;
        }
        if (this.inputBufferIndex < 0) {
            try {
                int dequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(0);
                this.inputBufferIndex = dequeueInputBuffer;
                if (dequeueInputBuffer < 0) {
                    return false;
                }
                try {
                    decoderInputBuffer.data = this.mediaCodec.getInputBuffer(dequeueInputBuffer);
                    decoderInputBuffer.clear();
                } catch (RuntimeException e) {
                    Log.d("DefaultCodec", "MediaCodec error", e);
                    throw createExportException(e);
                }
            } catch (RuntimeException e7) {
                Log.d("DefaultCodec", "MediaCodec error", e7);
                throw createExportException(e7);
            }
        }
        Assertions.checkNotNull(decoderInputBuffer.data);
        return true;
    }

    public void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        int i2;
        int i7;
        int i8;
        long j2;
        int i10;
        int i11;
        DecoderInputBuffer decoderInputBuffer2 = decoderInputBuffer;
        boolean z = true;
        Assertions.checkState(!this.inputStreamEnded, "Input buffer can not be queued after the input stream has ended.");
        ByteBuffer byteBuffer = decoderInputBuffer2.data;
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            i7 = 0;
            i2 = 0;
        } else {
            i7 = decoderInputBuffer2.data.position();
            i2 = decoderInputBuffer2.data.remaining();
        }
        long j3 = decoderInputBuffer2.timeUs;
        if (decoderInputBuffer2.isEndOfStream()) {
            this.inputStreamEnded = true;
            debugTraceLogEvent("InputEnded", Long.MIN_VALUE);
            if (this.isDecoder) {
                ByteBuffer byteBuffer2 = decoderInputBuffer2.data;
                if (byteBuffer2 != null && byteBuffer2.hasRemaining()) {
                    z = false;
                }
                Assertions.checkState(z);
                j3 = 0;
                i11 = 0;
                i10 = 0;
            } else {
                i11 = i7;
                i10 = i2;
            }
            j2 = j3;
            i8 = 4;
        } else {
            i11 = i7;
            i8 = 0;
            i10 = i2;
            j2 = j3;
        }
        try {
            this.mediaCodec.queueInputBuffer(this.inputBufferIndex, i11, i10, j2, i8);
            debugTraceLogEvent("AcceptedInput", j2, "bytes=%s", Integer.valueOf(i10));
            this.inputBufferIndex = -1;
            decoderInputBuffer2.data = null;
        } catch (RuntimeException e) {
            Log.d("DefaultCodec", "MediaCodec error", e);
            throw createExportException(e);
        }
    }

    public void release() {
        this.outputBuffer = null;
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
        }
        this.mediaCodec.release();
    }

    public void releaseOutputBuffer(boolean z) {
        releaseOutputBuffer(z, ((MediaCodec.BufferInfo) Assertions.checkStateNotNull(this.outputBufferInfo)).presentationTimeUs);
    }

    public void signalEndOfInputStream() {
        if (!this.videoOutputStarted.get()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        debugTraceLogEvent("InputEnded", Long.MIN_VALUE);
        try {
            this.mediaCodec.signalEndOfInputStream();
        } catch (RuntimeException e) {
            Log.d("DefaultCodec", "MediaCodec error", e);
            throw createExportException(e);
        }
    }

    private void debugTraceLogEvent(String str, long j2, String str2, Object... objArr) {
        DebugTraceUtil.logCodecEvent(this.isDecoder, this.isVideo, str, j2, str2, objArr);
    }

    public void releaseOutputBuffer(long j2) {
        releaseOutputBuffer(true, j2);
    }

    public void releaseOutputBuffer(boolean z, long j2) {
        this.outputBuffer = null;
        if (z) {
            try {
                this.mediaCodec.releaseOutputBuffer(this.outputBufferIndex, 1000 * j2);
                debugTraceLogEvent("ProducedOutput", j2);
            } catch (RuntimeException e) {
                Log.d("DefaultCodec", "MediaCodec error", e);
                throw createExportException(e);
            }
        } else {
            this.mediaCodec.releaseOutputBuffer(this.outputBufferIndex, false);
        }
        this.outputBufferIndex = -1;
    }

    private static ExportException createExportException(MediaFormat mediaFormat, boolean z, boolean z3, Exception exc, int i2, String str) {
        return ExportException.createForCodec(exc, i2, new ExportException.CodecInfo(mediaFormat.toString(), z, z3, str));
    }
}
