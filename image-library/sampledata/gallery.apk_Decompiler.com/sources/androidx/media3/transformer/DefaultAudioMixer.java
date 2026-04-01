package androidx.media3.transformer;

import android.util.SparseArray;
import androidx.media3.common.audio.AudioMixingUtil;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.ChannelMixingMatrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.AudioMixer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultAudioMixer implements AudioMixer {
    private int bufferSizeFrames;
    /* access modifiers changed from: private */
    public final boolean clipFloatOutput;
    private long endPosition;
    private long inputLimit;
    private long maxPositionOfRemovedSources;
    private long mixerStartTimeUs;
    private MixingBuffer[] mixingBuffers;
    private int nextSourceId;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private long outputPosition;
    private final boolean outputSilenceWithNoSources;
    private final SparseArray<SourceInfo> sources;
    private final boolean useConstantPowerMixingMatrices;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements AudioMixer.Factory {
        private final boolean clipFloatOutput;
        private final boolean outputSilenceWithNoSources;
        private final boolean useConstantPowerMixingMatrices;

        public Factory() {
            this(false, true, false);
        }

        public Factory(boolean z, boolean z3, boolean z7) {
            this.outputSilenceWithNoSources = z;
            this.clipFloatOutput = z3;
            this.useConstantPowerMixingMatrices = z7;
        }

        public DefaultAudioMixer create() {
            return new DefaultAudioMixer(this.outputSilenceWithNoSources, this.clipFloatOutput, this.useConstantPowerMixingMatrices);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MixingBuffer {
        public final ByteBuffer buffer;
        public final long limit;
        public final long position;

        public MixingBuffer(ByteBuffer byteBuffer, long j2, long j3) {
            this.buffer = byteBuffer;
            this.position = j2;
            this.limit = j3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SourceInfo {
        private final AudioProcessor.AudioFormat audioFormat;
        private final ChannelMixingMatrix baseChannelMixingMatrix;
        private ChannelMixingMatrix channelMixingMatrix;
        public long position;

        public SourceInfo(AudioProcessor.AudioFormat audioFormat2, ChannelMixingMatrix channelMixingMatrix2, long j2) {
            this.audioFormat = audioFormat2;
            this.baseChannelMixingMatrix = channelMixingMatrix2;
            this.position = j2;
            this.channelMixingMatrix = channelMixingMatrix2;
        }

        public void discardTo(ByteBuffer byteBuffer, long j2) {
            boolean z;
            if (j2 >= this.position) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            byteBuffer.position((((int) (j2 - this.position)) * this.audioFormat.bytesPerFrame) + byteBuffer.position());
            this.position = j2;
        }

        public ChannelMixingMatrix getChannelMixingMatrix() {
            return this.channelMixingMatrix;
        }

        public long getPositionAfterBuffer(ByteBuffer byteBuffer) {
            return this.position + ((long) (byteBuffer.remaining() / this.audioFormat.bytesPerFrame));
        }

        public void mixTo(ByteBuffer byteBuffer, long j2, ByteBuffer byteBuffer2, AudioProcessor.AudioFormat audioFormat2) {
            boolean z;
            if (j2 >= this.position) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            ByteBuffer byteBuffer3 = byteBuffer;
            ByteBuffer byteBuffer4 = byteBuffer2;
            AudioProcessor.AudioFormat audioFormat3 = audioFormat2;
            AudioMixingUtil.mix(byteBuffer3, this.audioFormat, byteBuffer4, audioFormat3, this.channelMixingMatrix, (int) (j2 - this.position), true, DefaultAudioMixer.this.clipFloatOutput);
            this.position = j2;
        }
    }

    private MixingBuffer allocateMixingBuffer(long j2) {
        ByteBuffer order = ByteBuffer.allocateDirect(this.bufferSizeFrames * this.outputAudioFormat.bytesPerFrame).order(ByteOrder.nativeOrder());
        order.mark();
        return new MixingBuffer(order, j2, j2 + ((long) this.bufferSizeFrames));
    }

    private void checkStateIsConfigured() {
        Assertions.checkState(!this.outputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET), "Audio mixer is not configured.");
    }

    private SourceInfo getSourceById(int i2) {
        Assertions.checkState(Util.contains(this.sources, i2), "Source not found.");
        return this.sources.get(i2);
    }

    private void updateInputFrameLimit() {
        this.inputLimit = Math.min(this.endPosition, this.outputPosition + ((long) this.bufferSizeFrames));
    }

    public int addSource(AudioProcessor.AudioFormat audioFormat, long j2) {
        ChannelMixingMatrix createForConstantGain;
        checkStateIsConfigured();
        if (supportsSourceAudioFormat(audioFormat)) {
            long durationUsToSampleCount = Util.durationUsToSampleCount(j2 - this.mixerStartTimeUs, audioFormat.sampleRate);
            int i2 = this.nextSourceId;
            this.nextSourceId = i2 + 1;
            SparseArray<SourceInfo> sparseArray = this.sources;
            if (this.useConstantPowerMixingMatrices) {
                createForConstantGain = ChannelMixingMatrix.createForConstantPower(audioFormat.channelCount, this.outputAudioFormat.channelCount);
            } else {
                createForConstantGain = ChannelMixingMatrix.createForConstantGain(audioFormat.channelCount, this.outputAudioFormat.channelCount);
            }
            AudioProcessor.AudioFormat audioFormat2 = audioFormat;
            sparseArray.append(i2, new SourceInfo(audioFormat2, createForConstantGain, durationUsToSampleCount));
            DebugTraceUtil.logEvent("AudioMixer", "RegisterNewInputStream", j2, "source(%s):%s", Integer.valueOf(i2), audioFormat2);
            return i2;
        }
        AudioProcessor.AudioFormat audioFormat3 = audioFormat;
        throw new AudioProcessor.UnhandledAudioFormatException("Can not add source. MixerFormat=" + this.outputAudioFormat, audioFormat3);
    }

    public void configure(AudioProcessor.AudioFormat audioFormat, int i2, long j2) {
        boolean z;
        Assertions.checkState(this.outputAudioFormat.equals(AudioProcessor.AudioFormat.NOT_SET), "Audio mixer already configured.");
        if (i2 == -1) {
            i2 = 500;
        }
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (AudioMixingUtil.canMix(audioFormat)) {
            this.outputAudioFormat = audioFormat;
            this.bufferSizeFrames = (i2 * audioFormat.sampleRate) / 1000;
            this.mixerStartTimeUs = j2;
            DebugTraceUtil.logEvent("AudioMixer", "OutputFormat", j2, "%s", audioFormat);
            this.mixingBuffers = new MixingBuffer[]{allocateMixingBuffer(0), allocateMixingBuffer((long) this.bufferSizeFrames)};
            updateInputFrameLimit();
            return;
        }
        throw new AudioProcessor.UnhandledAudioFormatException("Can not mix to this AudioFormat.", audioFormat);
    }

    public ByteBuffer getOutput() {
        checkStateIsConfigured();
        if (isEnded()) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        long j2 = this.endPosition;
        if (this.sources.size() == 0) {
            j2 = Math.min(j2, this.maxPositionOfRemovedSources);
        }
        for (int i2 = 0; i2 < this.sources.size(); i2++) {
            j2 = Math.min(j2, this.sources.valueAt(i2).position);
        }
        if (j2 <= this.outputPosition) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        MixingBuffer mixingBuffer = this.mixingBuffers[0];
        long min = Math.min(j2, mixingBuffer.limit);
        ByteBuffer duplicate = mixingBuffer.buffer.duplicate();
        duplicate.position(((int) (this.outputPosition - mixingBuffer.position)) * this.outputAudioFormat.bytesPerFrame).limit(((int) (min - mixingBuffer.position)) * this.outputAudioFormat.bytesPerFrame);
        ByteBuffer order = duplicate.slice().order(ByteOrder.nativeOrder());
        if (min == mixingBuffer.limit) {
            MixingBuffer[] mixingBufferArr = this.mixingBuffers;
            MixingBuffer mixingBuffer2 = mixingBufferArr[1];
            mixingBufferArr[0] = mixingBuffer2;
            mixingBufferArr[1] = allocateMixingBuffer(mixingBuffer2.limit);
        }
        this.outputPosition = min;
        updateInputFrameLimit();
        DebugTraceUtil.logEvent("AudioMixer", "ProducedOutput", -9223372036854775807L, "bytesOutput=%s", Integer.valueOf(order.remaining()));
        return order;
    }

    public boolean hasSource(int i2) {
        checkStateIsConfigured();
        return Util.contains(this.sources, i2);
    }

    public boolean isEnded() {
        checkStateIsConfigured();
        long j2 = this.outputPosition;
        if (j2 >= this.endPosition) {
            return true;
        }
        if (j2 < this.maxPositionOfRemovedSources || this.sources.size() != 0) {
            return false;
        }
        return true;
    }

    public void queueInput(int i2, ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        checkStateIsConfigured();
        if (byteBuffer.hasRemaining()) {
            SourceInfo sourceById = getSourceById(i2);
            if (sourceById.position < this.inputLimit) {
                long min = Math.min(sourceById.getPositionAfterBuffer(byteBuffer), this.inputLimit);
                if (sourceById.getChannelMixingMatrix().isZero()) {
                    sourceById.discardTo(byteBuffer, min);
                    return;
                }
                long j2 = sourceById.position;
                long j3 = this.outputPosition;
                if (j2 < j3) {
                    sourceById.discardTo(byteBuffer, Math.min(min, j3));
                    if (sourceById.position == min) {
                        return;
                    }
                }
                MixingBuffer[] mixingBufferArr = this.mixingBuffers;
                int length = mixingBufferArr.length;
                int i7 = 0;
                while (i7 < length) {
                    MixingBuffer mixingBuffer = mixingBufferArr[i7];
                    long j8 = sourceById.position;
                    if (j8 >= mixingBuffer.limit) {
                        byteBuffer2 = byteBuffer;
                    } else {
                        int i8 = ((int) (j8 - mixingBuffer.position)) * this.outputAudioFormat.bytesPerFrame;
                        ByteBuffer byteBuffer3 = mixingBuffer.buffer;
                        byteBuffer3.position(byteBuffer3.position() + i8);
                        byteBuffer2 = byteBuffer;
                        sourceById.mixTo(byteBuffer2, Math.min(min, mixingBuffer.limit), mixingBuffer.buffer, this.outputAudioFormat);
                        mixingBuffer.buffer.reset();
                        if (sourceById.position == min) {
                            return;
                        }
                    }
                    i7++;
                    byteBuffer = byteBuffer2;
                }
            }
        }
    }

    public void removeSource(int i2) {
        checkStateIsConfigured();
        this.maxPositionOfRemovedSources = Math.max(this.maxPositionOfRemovedSources, getSourceById(i2).position);
        this.sources.delete(i2);
    }

    public void reset() {
        this.sources.clear();
        this.nextSourceId = 0;
        this.outputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.bufferSizeFrames = -1;
        this.mixingBuffers = new MixingBuffer[0];
        this.mixerStartTimeUs = -9223372036854775807L;
        this.inputLimit = -1;
        long j2 = 0;
        this.outputPosition = 0;
        this.endPosition = Long.MAX_VALUE;
        if (this.outputSilenceWithNoSources) {
            j2 = Long.MAX_VALUE;
        }
        this.maxPositionOfRemovedSources = j2;
    }

    public boolean supportsSourceAudioFormat(AudioProcessor.AudioFormat audioFormat) {
        checkStateIsConfigured();
        return AudioMixingUtil.canMix(audioFormat, this.outputAudioFormat);
    }

    private DefaultAudioMixer(boolean z, boolean z3, boolean z7) {
        this.outputSilenceWithNoSources = z;
        this.clipFloatOutput = z3;
        this.useConstantPowerMixingMatrices = z7;
        this.sources = new SparseArray<>();
        this.outputAudioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.bufferSizeFrames = -1;
        this.mixingBuffers = new MixingBuffer[0];
        this.mixerStartTimeUs = -9223372036854775807L;
        this.inputLimit = -1;
        this.endPosition = Long.MAX_VALUE;
        if (z) {
            this.maxPositionOfRemovedSources = Long.MAX_VALUE;
        }
    }
}
