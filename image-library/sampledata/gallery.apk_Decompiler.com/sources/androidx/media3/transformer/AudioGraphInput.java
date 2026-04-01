package androidx.media3.transformer;

import F2.N;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.ChannelMixingAudioProcessor;
import androidx.media3.common.audio.ChannelMixingMatrix;
import androidx.media3.common.audio.SonicAudioProcessor;
import androidx.media3.common.audio.SpeedChangingAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AudioGraphInput implements GraphInput {
    private AudioProcessingPipeline audioProcessingPipeline;
    private final Queue<DecoderInputBuffer> availableInputBuffers = new ConcurrentLinkedQueue();
    private DecoderInputBuffer currentInputBufferBeingOutput;
    private long currentItemExpectedInputDurationUs;
    private long currentItemInputBytesRead;
    private boolean currentItemSilenceAppended;
    private boolean inputBlocked;
    private boolean isCurrentItemLast;
    private final AudioProcessor.AudioFormat outputAudioFormat;
    private final Queue<DecoderInputBuffer> pendingInputBuffers;
    private final Queue<MediaItemChange> pendingMediaItemChanges;
    private boolean processedFirstMediaItemChange;
    private boolean queueEndOfStreamAfterSilence;
    private boolean receivedEndOfStreamFromInput;
    private SilentAudioGenerator silentAudioGenerator;
    private final AtomicLong startTimeUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaItemChange {
        public final long durationUs;
        public final EditedMediaItem editedMediaItem;
        public final Format format;
        public final boolean isLast;

        public MediaItemChange(EditedMediaItem editedMediaItem2, long j2, Format format2, boolean z) {
            this.editedMediaItem = editedMediaItem2;
            this.durationUs = j2;
            this.format = format2;
            this.isLast = z;
        }
    }

    public AudioGraphInput(AudioProcessor.AudioFormat audioFormat, EditedMediaItem editedMediaItem, Format format) {
        AudioProcessor.AudioFormat audioFormat2 = new AudioProcessor.AudioFormat(format);
        Assertions.checkArgument(AudioGraph.isInputAudioFormatValid(audioFormat2), audioFormat2);
        boolean z = false;
        ByteBuffer order = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
        for (int i2 = 0; i2 < 10; i2++) {
            DecoderInputBuffer decoderInputBuffer = new DecoderInputBuffer(2);
            decoderInputBuffer.data = order;
            this.availableInputBuffers.add(decoderInputBuffer);
        }
        this.pendingInputBuffers = new ConcurrentLinkedQueue();
        this.pendingMediaItemChanges = new ConcurrentLinkedQueue();
        this.silentAudioGenerator = new SilentAudioGenerator(audioFormat2);
        AudioProcessingPipeline configureProcessing = configureProcessing(editedMediaItem, format, audioFormat2, audioFormat);
        this.audioProcessingPipeline = configureProcessing;
        configureProcessing.flush();
        AudioProcessor.AudioFormat outputAudioFormat2 = this.audioProcessingPipeline.getOutputAudioFormat();
        this.outputAudioFormat = outputAudioFormat2;
        Assertions.checkArgument(outputAudioFormat2.encoding == 2 ? true : z, outputAudioFormat2);
        this.startTimeUs = new AtomicLong(-9223372036854775807L);
        this.currentItemExpectedInputDurationUs = -9223372036854775807L;
    }

    private void appendSilence() {
        this.silentAudioGenerator.addSilence(this.currentItemExpectedInputDurationUs - currentItemActualInputDurationUs());
        this.currentItemSilenceAppended = true;
        if (this.isCurrentItemLast) {
            this.queueEndOfStreamAfterSilence = true;
        }
    }

    private void clearAndAddToAvailableBuffers(DecoderInputBuffer decoderInputBuffer) {
        decoderInputBuffer.clear();
        decoderInputBuffer.timeUs = 0;
        this.availableInputBuffers.add(decoderInputBuffer);
    }

    private void configureForPendingMediaItemChange() {
        AudioProcessor.AudioFormat audioFormat;
        MediaItemChange mediaItemChange = (MediaItemChange) Assertions.checkStateNotNull(this.pendingMediaItemChanges.poll());
        this.currentItemInputBytesRead = 0;
        this.isCurrentItemLast = mediaItemChange.isLast;
        this.currentItemSilenceAppended = false;
        if (mediaItemChange.format != null) {
            this.currentItemExpectedInputDurationUs = mediaItemChange.durationUs;
            audioFormat = new AudioProcessor.AudioFormat(mediaItemChange.format);
            this.silentAudioGenerator = new SilentAudioGenerator(audioFormat);
        } else {
            if (mediaItemChange.editedMediaItem.effects.audioProcessors.isEmpty()) {
                this.currentItemExpectedInputDurationUs = mediaItemChange.editedMediaItem.getDurationAfterEffectsApplied(mediaItemChange.durationUs);
            } else {
                this.currentItemExpectedInputDurationUs = mediaItemChange.durationUs;
            }
            AudioProcessor.AudioFormat audioFormat2 = this.silentAudioGenerator.audioFormat;
            this.startTimeUs.compareAndSet(-9223372036854775807L, 0);
            appendSilence();
            audioFormat = audioFormat2;
        }
        if (this.processedFirstMediaItemChange) {
            this.audioProcessingPipeline = configureProcessing(mediaItemChange.editedMediaItem, mediaItemChange.format, audioFormat, this.outputAudioFormat);
        }
        this.audioProcessingPipeline.flush();
        this.receivedEndOfStreamFromInput = false;
        this.processedFirstMediaItemChange = true;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
    private static AudioProcessingPipeline configureProcessing(EditedMediaItem editedMediaItem, Format format, AudioProcessor.AudioFormat audioFormat, AudioProcessor.AudioFormat audioFormat2) {
        int i2;
        int i7;
        ? n = new N(4);
        if (!(!editedMediaItem.flattenForSlowMotion || format == null || format.metadata == null)) {
            n.a(new SpeedChangingAudioProcessor(new SegmentSpeedProvider(format.metadata)));
        }
        n.c(editedMediaItem.effects.audioProcessors);
        if (audioFormat2.sampleRate != -1) {
            SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();
            sonicAudioProcessor.setOutputSampleRateHz(audioFormat2.sampleRate);
            n.a(sonicAudioProcessor);
        }
        int i8 = audioFormat2.channelCount;
        if (i8 == 1 || i8 == 2) {
            ChannelMixingAudioProcessor channelMixingAudioProcessor = new ChannelMixingAudioProcessor();
            channelMixingAudioProcessor.putChannelMixingMatrix(ChannelMixingMatrix.createForConstantGain(1, audioFormat2.channelCount));
            channelMixingAudioProcessor.putChannelMixingMatrix(ChannelMixingMatrix.createForConstantGain(2, audioFormat2.channelCount));
            n.a(channelMixingAudioProcessor);
        }
        AudioProcessingPipeline audioProcessingPipeline2 = new AudioProcessingPipeline(n.f());
        AudioProcessor.AudioFormat configure = audioProcessingPipeline2.configure(audioFormat);
        int i10 = audioFormat2.sampleRate;
        if ((i10 == -1 || i10 == configure.sampleRate) && (((i2 = audioFormat2.channelCount) == -1 || i2 == configure.channelCount) && ((i7 = audioFormat2.encoding) == -1 || i7 == configure.encoding))) {
            return audioProcessingPipeline2;
        }
        throw new AudioProcessor.UnhandledAudioFormatException("Audio can not be modified to match downstream format", audioFormat);
    }

    private long currentItemActualInputDurationUs() {
        long j2 = this.currentItemInputBytesRead;
        AudioProcessor.AudioFormat audioFormat = this.silentAudioGenerator.audioFormat;
        return Util.sampleCountToDurationUs(j2 / ((long) audioFormat.bytesPerFrame), audioFormat.sampleRate);
    }

    private ByteBuffer feedOutputFromInput() {
        if (this.silentAudioGenerator.hasRemaining()) {
            return this.silentAudioGenerator.getBuffer();
        }
        DecoderInputBuffer decoderInputBuffer = this.currentInputBufferBeingOutput;
        if (decoderInputBuffer != null) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkStateNotNull(decoderInputBuffer.data);
            if (byteBuffer.hasRemaining()) {
                return byteBuffer;
            }
            clearAndAddToAvailableBuffers((DecoderInputBuffer) Assertions.checkStateNotNull(this.currentInputBufferBeingOutput));
            this.currentInputBufferBeingOutput = null;
        }
        DecoderInputBuffer poll = this.pendingInputBuffers.poll();
        if (poll == null) {
            if (!this.pendingMediaItemChanges.isEmpty() && shouldAppendSilence()) {
                appendSilence();
            }
            return AudioProcessor.EMPTY_BUFFER;
        }
        ByteBuffer byteBuffer2 = poll.data;
        this.receivedEndOfStreamFromInput = poll.isEndOfStream();
        if (byteBuffer2 == null || !byteBuffer2.hasRemaining() || this.receivedEndOfStreamFromInput) {
            clearAndAddToAvailableBuffers(poll);
            if (this.receivedEndOfStreamFromInput && shouldAppendSilence()) {
                appendSilence();
            }
            return AudioProcessor.EMPTY_BUFFER;
        }
        this.currentInputBufferBeingOutput = poll;
        this.currentItemInputBytesRead += (long) byteBuffer2.remaining();
        return byteBuffer2;
    }

    private boolean feedProcessingPipelineFromInput() {
        if (this.silentAudioGenerator.hasRemaining()) {
            ByteBuffer buffer = this.silentAudioGenerator.getBuffer();
            this.audioProcessingPipeline.queueInput(buffer);
            if (buffer.hasRemaining()) {
                return false;
            }
            if (this.silentAudioGenerator.hasRemaining()) {
                return true;
            }
            this.audioProcessingPipeline.queueEndOfStream();
            return false;
        }
        DecoderInputBuffer peek = this.pendingInputBuffers.peek();
        if (peek == null) {
            if (!this.pendingMediaItemChanges.isEmpty()) {
                if (shouldAppendSilence()) {
                    appendSilence();
                    return true;
                }
                this.audioProcessingPipeline.queueEndOfStream();
            }
            return false;
        } else if (!peek.isEndOfStream()) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(peek.data);
            this.audioProcessingPipeline.queueInput(byteBuffer);
            this.currentItemInputBytesRead += ((long) byteBuffer.remaining()) - ((long) byteBuffer.remaining());
            if (byteBuffer.hasRemaining()) {
                return false;
            }
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
            return true;
        } else if (shouldAppendSilence()) {
            appendSilence();
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
            return true;
        } else {
            this.audioProcessingPipeline.queueEndOfStream();
            this.receivedEndOfStreamFromInput = true;
            clearAndAddToAvailableBuffers(this.pendingInputBuffers.remove());
            return false;
        }
    }

    private ByteBuffer getOutputInternal() {
        if (!this.processedFirstMediaItemChange) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        if (!this.audioProcessingPipeline.isOperational()) {
            return feedOutputFromInput();
        }
        do {
        } while (feedProcessingPipelineFromInput());
        return this.audioProcessingPipeline.getOutput();
    }

    private boolean hasDataToOutput() {
        ByteBuffer byteBuffer;
        if (!this.processedFirstMediaItemChange) {
            return false;
        }
        DecoderInputBuffer decoderInputBuffer = this.currentInputBufferBeingOutput;
        if ((decoderInputBuffer != null && (byteBuffer = decoderInputBuffer.data) != null && byteBuffer.hasRemaining()) || this.silentAudioGenerator.hasRemaining() || !this.pendingInputBuffers.isEmpty()) {
            return true;
        }
        if (!this.audioProcessingPipeline.isOperational() || this.audioProcessingPipeline.isEnded()) {
            return false;
        }
        return true;
    }

    private boolean shouldAppendSilence() {
        if (this.currentItemSilenceAppended) {
            return false;
        }
        long j2 = this.currentItemExpectedInputDurationUs;
        if (j2 == -9223372036854775807L || j2 - currentItemActualInputDurationUs() <= 2000) {
            return false;
        }
        return true;
    }

    public DecoderInputBuffer getInputBuffer() {
        if (this.inputBlocked || !this.pendingMediaItemChanges.isEmpty()) {
            return null;
        }
        return this.availableInputBuffers.peek();
    }

    public ByteBuffer getOutput() {
        ByteBuffer outputInternal = getOutputInternal();
        if (outputInternal.hasRemaining()) {
            return outputInternal;
        }
        if (!hasDataToOutput() && !this.pendingMediaItemChanges.isEmpty()) {
            configureForPendingMediaItemChange();
        }
        return AudioProcessor.EMPTY_BUFFER;
    }

    public AudioProcessor.AudioFormat getOutputAudioFormat() {
        return this.outputAudioFormat;
    }

    public long getStartTimeUs() {
        return this.startTimeUs.get();
    }

    public boolean isEnded() {
        if (hasDataToOutput() || !this.pendingMediaItemChanges.isEmpty()) {
            return false;
        }
        if (this.currentItemExpectedInputDurationUs != -9223372036854775807L) {
            if (!this.isCurrentItemLast || (!this.receivedEndOfStreamFromInput && !this.queueEndOfStreamAfterSilence)) {
                return false;
            }
            return true;
        } else if (this.receivedEndOfStreamFromInput || this.queueEndOfStreamAfterSilence) {
            return true;
        } else {
            return false;
        }
    }

    public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j2, Format format, boolean z) {
        boolean z3;
        if (format == null) {
            if (j2 != -9223372036854775807L) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkState(z3, "Could not generate silent audio because duration is unknown.");
        } else {
            Assertions.checkState(MimeTypes.isAudio(format.sampleMimeType));
            AudioProcessor.AudioFormat audioFormat = new AudioProcessor.AudioFormat(format);
            Assertions.checkState(AudioGraph.isInputAudioFormatValid(audioFormat), audioFormat);
        }
        this.pendingMediaItemChanges.add(new MediaItemChange(editedMediaItem, j2, format, z));
    }

    public boolean queueInputBuffer() {
        if (this.inputBlocked) {
            return false;
        }
        Assertions.checkState(this.pendingMediaItemChanges.isEmpty());
        DecoderInputBuffer remove = this.availableInputBuffers.remove();
        this.pendingInputBuffers.add(remove);
        this.startTimeUs.compareAndSet(-9223372036854775807L, remove.timeUs);
        return true;
    }

    public void release() {
        this.audioProcessingPipeline.reset();
    }
}
