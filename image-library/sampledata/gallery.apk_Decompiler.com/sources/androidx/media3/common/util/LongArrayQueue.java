package androidx.media3.common.util;

import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LongArrayQueue {
    private long[] data;
    private int headIndex;
    private int size;
    private int tailIndex;
    private int wrapAroundMask;

    public LongArrayQueue() {
        this(16);
    }

    private void doubleArraySize() {
        long[] jArr = this.data;
        int length = jArr.length << 1;
        if (length >= 0) {
            long[] jArr2 = new long[length];
            int length2 = jArr.length;
            int i2 = this.headIndex;
            int i7 = length2 - i2;
            System.arraycopy(jArr, i2, jArr2, 0, i7);
            System.arraycopy(this.data, 0, jArr2, i7, i2);
            this.headIndex = 0;
            this.tailIndex = this.size - 1;
            this.data = jArr2;
            this.wrapAroundMask = jArr2.length - 1;
            return;
        }
        throw new IllegalStateException();
    }

    public void add(long j2) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        int i2 = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = i2;
        this.data[i2] = j2;
        this.size++;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public long element() {
        if (this.size != 0) {
            return this.data[this.headIndex];
        }
        throw new NoSuchElementException();
    }

    public long remove() {
        int i2 = this.size;
        if (i2 != 0) {
            long[] jArr = this.data;
            int i7 = this.headIndex;
            long j2 = jArr[i7];
            this.headIndex = this.wrapAroundMask & (i7 + 1);
            this.size = i2 - 1;
            return j2;
        }
        throw new NoSuchElementException();
    }

    public LongArrayQueue(int i2) {
        Assertions.checkArgument(i2 >= 0 && i2 <= 1073741824);
        i2 = i2 == 0 ? 1 : i2;
        i2 = Integer.bitCount(i2) != 1 ? Integer.highestOneBit(i2 - 1) << 1 : i2;
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
        long[] jArr = new long[i2];
        this.data = jArr;
        this.wrapAroundMask = jArr.length - 1;
    }
}
