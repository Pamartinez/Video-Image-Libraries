package com.samsung.android.sum.core.filter;

import A4.C0368c;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import bc.C0584a;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.SurfaceChannel;
import com.samsung.android.sum.core.channel.SurfaceChannelConfig;
import com.samsung.android.sum.core.channel.SurfaceReadChannel;
import com.samsung.android.sum.core.descriptor.CodecDescriptor;
import com.samsung.android.sum.core.exception.StreamFilterExitException;
import com.samsung.android.sum.core.functional.BufferSupplier;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.SocVendor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecoderFilter extends MediaCodecFilter implements BufferSupplier {
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SP_M = 261;
    private static final int INIT_LOGGABLE_DELAY_MS = 100;
    private static final int MAX_TIME_DEQUEUE_INPUT_MS = 2000;
    private static final String TAG = Def.tagOf((Class<?>) DecoderFilter.class);
    private static final int TIMEOUT_DEQUEUE_INPUT_US = 50000;
    private static final int TIMEOUT_DEQUEUE_OUTPUT_US = 10000;
    private static final int TIMEOUT_SURFACE_READ_CHANNEL_MS = 3000;

    public DecoderFilter(CodecDescriptor codecDescriptor) {
        super(codecDescriptor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getBuffer$1() {
        CountingLatch countingLatch = this.bufferInUseCounter;
        if (countingLatch != null) {
            countingLatch.down();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(int i2) {
        try {
            this.mediaCodec.releaseOutputBuffer(i2, false);
            this.bufferInUseCounter.down();
        } catch (Exception e) {
            String str = TAG;
            SLog.w(str, "failed to release buffer on codec: " + e);
        }
    }

    public void configCodec(Message message) {
        boolean z;
        boolean z3;
        Surface surface;
        SLog.d(getTag(), "configCodec: " + message);
        int await = this.codecOnReadyCountingLatch.await(0);
        if (await == 0) {
            z = true;
        } else {
            z = false;
        }
        Def.require(z, C0086a.i(await, "count="), new Object[0]);
        if (this.mediaId == -1) {
            z3 = true;
        } else {
            z3 = false;
        }
        Def.require(z3, "contentsId=" + this.mediaId, new Object[0]);
        this.mediaId = ((Integer) message.get("media-id", -1)).intValue();
        try {
            MediaFormat mediaFormat = (MediaFormat) message.get("media-format");
            BufferChannel outputChannel = getOutputChannel(((CodecDescriptor) getDescriptor()).getMediaType());
            SLog.d(getTag(), "outputChannel: " + outputChannel);
            if (outputChannel instanceof SurfaceReadChannel) {
                ((SurfaceReadChannel) outputChannel).configure(new SurfaceChannelConfig.Builder().setWidth(mediaFormat.getInteger("width")).setHeight(mediaFormat.getInteger("height")).setFormat(34).build());
                surface = ((SurfaceReadChannel) outputChannel).getSurface();
            } else {
                surface = null;
            }
            if (mediaFormat.getInteger("color-standard", -1) == 6) {
                String str = TAG;
                SLog.d(str, "File is HDR set color format");
                int integer = mediaFormat.getInteger("color-format", -1);
                if (integer == -1) {
                    String name = SocVendor.currentSoc().name();
                    SLog.d(str, "Set default color format for " + name);
                    if (name.equals(SocVendor.SLSI.name())) {
                        mediaFormat.setInteger("color-format", 261);
                    } else if (name.equals(SocVendor.QCOM.name())) {
                        mediaFormat.setInteger("color-format", 2135033992);
                    }
                } else {
                    SLog.d(str, "Using client-provided color format: " + integer);
                }
            }
            mediaFormat.setInteger("vendor.qti-ext-dec-forceNonUBWC.value", 1);
            mediaFormat.setLong("vendor.sec-dec-output.buffers.usage.value", 1);
            SLog.d(getTag(), "(id: #" + this.mediaId + ") media-format=" + mediaFormat);
            this.bufferInUseCounter = CountingLatch.downOf();
            MediaCodec createDecoderByType = MediaCodec.createDecoderByType((String) message.get(MediaDefs.Image.HEIF.HEIF_MIME_BOX));
            this.mediaCodec = createDecoderByType;
            createDecoderByType.configure(mediaFormat, surface, (MediaCrypto) null, 0);
            this.mediaCodec.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized MediaBuffer getBuffer() {
        SLog.d(getTag(), "getBuffer");
        this.codecOnReadyCountingLatch.await(1, "Wait until codec ready...");
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = 100;
        long j3 = currentTimeMillis;
        while (true) {
            this.cvPause.block();
            int dequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(50000);
            if (dequeueInputBuffer < 0) {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (currentTimeMillis2 - currentTimeMillis > 2000) {
                    SLog.w(getTag(), "Time out to wait input buffer for 2s");
                    return null;
                }
                long j8 = currentTimeMillis2 - j3;
                if (j8 > j2) {
                    SLog.w(getTag(), Def.fmtstr("wait decoder input buffer for %d ms from %d ms", Long.valueOf(j8), Long.valueOf(j3)));
                    j2 *= 2;
                    j3 = currentTimeMillis2;
                }
            } else {
                Tag tag = getTag();
                SLog.d(tag, "success to dequeue input buffer: " + dequeueInputBuffer);
                ByteBuffer inputBuffer = this.mediaCodec.getInputBuffer(dequeueInputBuffer);
                this.bufferInUseCounter.up();
                MediaBuffer of2 = MediaBuffer.of(((CodecDescriptor) getDescriptor()).getMediaType(), inputBuffer);
                of2.setExtra("buffer-idx", Integer.valueOf(dequeueInputBuffer));
                of2.addOnReleaseListener(new C0584a(23, this));
                return of2;
            }
        }
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        boolean isRunInstant;
        MutableMediaBuffer mutableMediaBuffer2 = mutableMediaBuffer;
        Tag tag = getTag();
        StringBuilder sb2 = new StringBuilder("run: ibuf=");
        MediaBuffer mediaBuffer2 = mediaBuffer;
        sb2.append(mediaBuffer2);
        sb2.append(", obuf=");
        sb2.append(mutableMediaBuffer2);
        SLog.d(tag, sb2.toString());
        this.codecOnReadyCountingLatch.await(1);
        if (this.mediaCodec != null) {
            CodecDescriptor codecDescriptor = (CodecDescriptor) getDescriptor();
            MediaType mediaType = codecDescriptor.getMediaType();
            BufferChannel inputChannel = getInputChannel(mediaType);
            BufferChannel outputChannel = getOutputChannel(mediaType);
            this.isReachedInputEos = false;
            this.isReachedOutputEos = false;
            addTag(mediaType, this.mediaCodec.getCodecInfo().getCanonicalName());
            while (true) {
                try {
                    if (!this.isReachedInputEos || !this.isReachedOutputEos) {
                        this.cvPause.block();
                        if (!this.isReachedInputEos) {
                            MediaBuffer mediaBuffer3 = (MediaBuffer) inputChannel.receive();
                            ((ByteBuffer) mediaBuffer3.getTypedData(ByteBuffer.class)).flip();
                            int intValue = ((Integer) mediaBuffer3.getExtra("chunk-size")).intValue();
                            int intValue2 = ((Integer) mediaBuffer3.getExtra("buffer-idx")).intValue();
                            if (intValue < 0) {
                                this.mediaCodec.queueInputBuffer(intValue2, 0, 0, 0, 4);
                                this.isReachedInputEos = true;
                            } else {
                                this.mediaCodec.queueInputBuffer(intValue2, 0, intValue, ((Long) mediaBuffer3.getExtra(Message.KEY_TIMESTAMP_US)).longValue(), 0);
                            }
                            mediaBuffer3.release();
                        }
                        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                        int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 10000);
                        SLog.v(getTag(), "Dequeue output buffer: index=" + dequeueOutputBuffer + ", size=" + bufferInfo.size + ", flags=" + bufferInfo.flags + ", timestampUs=" + bufferInfo.presentationTimeUs + ", offset=" + bufferInfo.offset);
                        if (dequeueOutputBuffer == -1) {
                            SLog.d(getTag(), "retry dequeue output buffer");
                        } else if (dequeueOutputBuffer == -2) {
                            SLog.d(getTag(), "output format changed: " + this.mediaCodec.getOutputFormat());
                        } else if (dequeueOutputBuffer < 0) {
                            continue;
                        } else {
                            if ((bufferInfo.flags & 4) != 0) {
                                SLog.i(getTag(), "reached EOS");
                                this.isReachedOutputEos = true;
                                outputChannel.send(MediaBuffer.newMetaAlloc().setFlags(8).setExtra("media-id", Integer.valueOf(this.mediaId)).setExtra(Message.KEY_WHOLE_FRAMES, Integer.valueOf(this.processedFrames)).setExtra(Message.KEY_END_TIME_US, this.lastTimestampToProcess.get(Integer.valueOf(this.mediaId))).allocate());
                            }
                            if (bufferInfo.size == 0) {
                                continue;
                            } else if (this.startTimeUs.get() <= 0 || bufferInfo.presentationTimeUs >= this.startTimeUs.get()) {
                                if (!(outputChannel instanceof SurfaceChannel)) {
                                    ByteBuffer outputBuffer = this.mediaCodec.getOutputBuffer(dequeueOutputBuffer);
                                    outputBuffer.rewind();
                                    MediaBuffer wrap = MediaBuffer.newAudioAlloc().setShape(outputBuffer.limit()).setTimestampUs(bufferInfo.presentationTimeUs).setOnReleaseListener(new C0368c(this, dequeueOutputBuffer, 24)).wrap(outputBuffer);
                                    this.bufferInUseCounter.up();
                                    outputChannel.send(wrap);
                                } else if (!outputChannel.isClosedForSend()) {
                                    outputChannel.send(MediaBuffer.newAlloc(mediaType).setExtra("block-check", Integer.valueOf(this.processedFrames)).setTimestampUs(bufferInfo.presentationTimeUs).allocateMutable());
                                    this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, bufferInfo.presentationTimeUs * 1000);
                                } else {
                                    throw new CancellationException("output channel is already closed");
                                }
                                this.lastTimestampToProcess.put(Integer.valueOf(this.mediaId), Long.valueOf(bufferInfo.presentationTimeUs));
                                this.processedFrames++;
                                SLog.d(getTag(), "# of decoded frames: " + this.processedFrames + " ts=" + bufferInfo.presentationTimeUs);
                            } else {
                                SLog.d(getTag(), "drop sample of " + bufferInfo.presentationTimeUs + " before " + this.startTimeUs.get());
                                this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                            }
                        }
                    } else if (isRunInstant) {
                        return mutableMediaBuffer2;
                    } else {
                        this.mediaId = -1;
                        this.codecOnReadyCountingLatch.reset();
                        return mutableMediaBuffer2;
                    }
                } finally {
                    if (codecDescriptor.isRunInstant()) {
                        if (outputChannel instanceof SurfaceReadChannel) {
                            ((SurfaceReadChannel) outputChannel).reset();
                        }
                        release();
                    } else {
                        this.mediaId = -1;
                        this.codecOnReadyCountingLatch.reset();
                    }
                }
            }
        } else {
            mediaBuffer2.release();
            throw new StreamFilterExitException("no media-codec given, might be released");
        }
    }
}
