package androidx.media3.container;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ReorderingBufferQueue {
    private BuffersWithTimestamp lastQueuedBuffer;
    private final OutputConsumer outputConsumer;
    private final PriorityQueue<BuffersWithTimestamp> pendingBuffers = new PriorityQueue<>();
    private int reorderingQueueSize = -1;
    private final ArrayDeque<BuffersWithTimestamp> unusedBuffersWithTimestamp = new ArrayDeque<>();
    private final ArrayDeque<ParsableByteArray> unusedParsableByteArrays = new ArrayDeque<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BuffersWithTimestamp implements Comparable<BuffersWithTimestamp> {
        public final List<ParsableByteArray> nalBuffers = new ArrayList();
        public long presentationTimeUs = -9223372036854775807L;

        public void init(long j2, ParsableByteArray parsableByteArray) {
            boolean z;
            if (j2 != -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            Assertions.checkState(this.nalBuffers.isEmpty());
            this.presentationTimeUs = j2;
            this.nalBuffers.add(parsableByteArray);
        }

        public int compareTo(BuffersWithTimestamp buffersWithTimestamp) {
            return Long.compare(this.presentationTimeUs, buffersWithTimestamp.presentationTimeUs);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OutputConsumer {
        void consume(long j2, ParsableByteArray parsableByteArray);
    }

    public ReorderingBufferQueue(OutputConsumer outputConsumer2) {
        this.outputConsumer = outputConsumer2;
    }

    private ParsableByteArray copy(ParsableByteArray parsableByteArray) {
        ParsableByteArray parsableByteArray2;
        if (this.unusedParsableByteArrays.isEmpty()) {
            parsableByteArray2 = new ParsableByteArray();
        } else {
            parsableByteArray2 = this.unusedParsableByteArrays.pop();
        }
        parsableByteArray2.reset(parsableByteArray.bytesLeft());
        System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), parsableByteArray2.getData(), 0, parsableByteArray2.bytesLeft());
        return parsableByteArray2;
    }

    private void flushQueueDownToSize(int i2) {
        while (this.pendingBuffers.size() > i2) {
            BuffersWithTimestamp buffersWithTimestamp = (BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.poll());
            for (int i7 = 0; i7 < buffersWithTimestamp.nalBuffers.size(); i7++) {
                this.outputConsumer.consume(buffersWithTimestamp.presentationTimeUs, buffersWithTimestamp.nalBuffers.get(i7));
                this.unusedParsableByteArrays.push(buffersWithTimestamp.nalBuffers.get(i7));
            }
            buffersWithTimestamp.nalBuffers.clear();
            BuffersWithTimestamp buffersWithTimestamp2 = this.lastQueuedBuffer;
            if (buffersWithTimestamp2 != null && buffersWithTimestamp2.presentationTimeUs == buffersWithTimestamp.presentationTimeUs) {
                this.lastQueuedBuffer = null;
            }
            this.unusedBuffersWithTimestamp.push(buffersWithTimestamp);
        }
    }

    public void add(long j2, ParsableByteArray parsableByteArray) {
        BuffersWithTimestamp buffersWithTimestamp;
        int i2 = this.reorderingQueueSize;
        if (i2 == 0 || (i2 != -1 && this.pendingBuffers.size() >= this.reorderingQueueSize && j2 < ((BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.peek())).presentationTimeUs)) {
            this.outputConsumer.consume(j2, parsableByteArray);
            return;
        }
        ParsableByteArray copy = copy(parsableByteArray);
        BuffersWithTimestamp buffersWithTimestamp2 = this.lastQueuedBuffer;
        if (buffersWithTimestamp2 == null || j2 != buffersWithTimestamp2.presentationTimeUs) {
            if (this.unusedBuffersWithTimestamp.isEmpty()) {
                buffersWithTimestamp = new BuffersWithTimestamp();
            } else {
                buffersWithTimestamp = this.unusedBuffersWithTimestamp.pop();
            }
            buffersWithTimestamp.init(j2, copy);
            this.pendingBuffers.add(buffersWithTimestamp);
            this.lastQueuedBuffer = buffersWithTimestamp;
            int i7 = this.reorderingQueueSize;
            if (i7 != -1) {
                flushQueueDownToSize(i7);
                return;
            }
            return;
        }
        buffersWithTimestamp2.nalBuffers.add(copy);
    }

    public void clear() {
        this.pendingBuffers.clear();
    }

    public void flush() {
        flushQueueDownToSize(0);
    }

    public int getMaxSize() {
        return this.reorderingQueueSize;
    }

    public void setMaxSize(int i2) {
        boolean z;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.reorderingQueueSize = i2;
        flushQueueDownToSize(i2);
    }
}
