package androidx.media3.common.util;

import c0.C0086a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LongArray {
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public void add(long j2) {
        int i2 = this.size;
        long[] jArr = this.values;
        if (i2 == jArr.length) {
            this.values = Arrays.copyOf(jArr, i2 * 2);
        }
        long[] jArr2 = this.values;
        int i7 = this.size;
        this.size = i7 + 1;
        jArr2[i7] = j2;
    }

    public void addAll(long[] jArr) {
        int length = this.size + jArr.length;
        long[] jArr2 = this.values;
        if (length > jArr2.length) {
            this.values = Arrays.copyOf(jArr2, Math.max(jArr2.length * 2, length));
        }
        System.arraycopy(jArr, 0, this.values, this.size, jArr.length);
        this.size = length;
    }

    public long get(int i2) {
        if (i2 >= 0 && i2 < this.size) {
            return this.values[i2];
        }
        StringBuilder o2 = C0086a.o(i2, "Invalid index ", ", size is ");
        o2.append(this.size);
        throw new IndexOutOfBoundsException(o2.toString());
    }

    public int size() {
        return this.size;
    }

    public LongArray(int i2) {
        this.values = new long[i2];
    }
}
