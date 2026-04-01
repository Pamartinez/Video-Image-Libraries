package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class EncodedSampleExporter extends SampleExporter implements GraphInput {
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
    private final Queue<DecoderInputBuffer> availableInputBuffers = new ConcurrentLinkedQueue();
    private final Format format;
    private boolean hasReachedAllocationTarget;
    private final long initialTimestampOffsetUs;
    private volatile boolean inputEnded;
    private long mediaItemOffsetUs;
    private DecoderInputBuffer nextInputBuffer;
    private final AtomicLong nextMediaItemOffsetUs = new AtomicLong();
    private final Queue<DecoderInputBuffer> pendingInputBuffers = new ConcurrentLinkedQueue();
    private long totalBufferSizeBytes;

    public EncodedSampleExporter(Format format2, TransformationRequest transformationRequest, MuxerWrapper muxerWrapper, FallbackListener fallbackListener, long j2) {
        super(format2, muxerWrapper);
        this.format = format2;
        this.initialTimestampOffsetUs = j2;
        fallbackListener.onTransformationRequestFinalized(transformationRequest);
    }

    public DecoderInputBuffer getInputBuffer() {
        if (this.nextInputBuffer == null) {
            DecoderInputBuffer poll = this.availableInputBuffers.poll();
            this.nextInputBuffer = poll;
            if (!this.hasReachedAllocationTarget) {
                if (poll == null) {
                    DecoderInputBuffer decoderInputBuffer = new DecoderInputBuffer(2);
                    this.nextInputBuffer = decoderInputBuffer;
                    decoderInputBuffer.data = EMPTY_BUFFER;
                } else {
                    this.totalBufferSizeBytes -= (long) ((ByteBuffer) Assertions.checkNotNull(poll.data)).capacity();
                }
            }
        }
        return this.nextInputBuffer;
    }

    public DecoderInputBuffer getMuxerInputBuffer() {
        return this.pendingInputBuffers.peek();
    }

    public Format getMuxerInputFormat() {
        return this.format;
    }

    public boolean isMuxerInputEnded() {
        if (!this.inputEnded || !this.pendingInputBuffers.isEmpty()) {
            return false;
        }
        return true;
    }

    public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j2, Format format2, boolean z) {
        this.mediaItemOffsetUs = this.nextMediaItemOffsetUs.get();
        this.nextMediaItemOffsetUs.addAndGet(j2);
    }

    public boolean queueInputBuffer() {
        boolean z;
        DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) Assertions.checkNotNull(this.nextInputBuffer);
        this.nextInputBuffer = null;
        if (decoderInputBuffer.isEndOfStream()) {
            this.inputEnded = true;
        } else {
            decoderInputBuffer.timeUs = this.mediaItemOffsetUs + this.initialTimestampOffsetUs + decoderInputBuffer.timeUs;
            this.pendingInputBuffers.add(decoderInputBuffer);
        }
        if (!this.hasReachedAllocationTarget) {
            int size = this.pendingInputBuffers.size() + this.availableInputBuffers.size();
            long capacity = this.totalBufferSizeBytes + ((long) ((ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data)).capacity());
            this.totalBufferSizeBytes = capacity;
            if (size < 10 || (size < 200 && capacity < 2097152)) {
                z = false;
            } else {
                z = true;
            }
            this.hasReachedAllocationTarget = z;
        }
        return true;
    }

    public void releaseMuxerInputBuffer() {
        DecoderInputBuffer remove = this.pendingInputBuffers.remove();
        remove.clear();
        remove.timeUs = 0;
        this.availableInputBuffers.add(remove);
    }

    public void release() {
    }

    public GraphInput getInput(EditedMediaItem editedMediaItem, Format format2, int i2) {
        return this;
    }
}
