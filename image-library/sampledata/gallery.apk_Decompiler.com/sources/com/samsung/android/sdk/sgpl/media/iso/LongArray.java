package com.samsung.android.sdk.sgpl.media.iso;

import N2.j;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongArray implements Cloneable {
    private static final int MIN_CAPACITY_INCREMENT = 12;
    private int mSize;
    private long[] mValues;

    private LongArray(long[] jArr, int i2) {
        this.mValues = jArr;
        this.mSize = checkArgumentInRange(i2, 0, jArr.length, "size");
    }

    public static int checkArgumentInRange(int i2, int i7, int i8, String str) {
        if (i2 < i7) {
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too low)", new Object[]{str, Integer.valueOf(i7), Integer.valueOf(i8)}));
        } else if (i2 <= i8) {
            return i2;
        } else {
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too high)", new Object[]{str, Integer.valueOf(i7), Integer.valueOf(i8)}));
        }
    }

    public static int checkArgumentNonnegative(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException();
    }

    public static void checkBounds(int i2, int i7) {
        if (i7 < 0 || i2 <= i7) {
            throw new ArrayIndexOutOfBoundsException(j.b(i2, i7, "length=", "; index="));
        }
    }

    public static boolean elementsEqual(LongArray longArray, LongArray longArray2) {
        if (longArray == null || longArray2 == null) {
            if (longArray == longArray2) {
                return true;
            }
            return false;
        } else if (longArray.mSize != longArray2.mSize) {
            return false;
        } else {
            for (int i2 = 0; i2 < longArray.mSize; i2++) {
                if (longArray.get(i2) != longArray2.get(i2)) {
                    return false;
                }
            }
            return true;
        }
    }

    private void ensureCapacity(int i2) {
        int i7;
        int i8 = this.mSize;
        int i10 = i2 + i8;
        long[] jArr = this.mValues;
        if (i10 >= jArr.length) {
            if (i8 < 6) {
                i7 = 12;
            } else {
                i7 = i8 >> 1;
            }
            int i11 = i7 + i8;
            if (i11 > i10) {
                i10 = i11;
            }
            long[] jArr2 = new long[i10];
            System.arraycopy(jArr, 0, jArr2, 0, i8);
            this.mValues = jArr2;
        }
    }

    public static LongArray fromArray(long[] jArr, int i2) {
        return wrap(Arrays.copyOf(jArr, i2));
    }

    public static LongArray wrap(long[] jArr) {
        return new LongArray(jArr, jArr.length);
    }

    public void add(long j2) {
        add(this.mSize, j2);
    }

    public void addAll(LongArray longArray) {
        int i2 = longArray.mSize;
        ensureCapacity(i2);
        System.arraycopy(longArray.mValues, 0, this.mValues, this.mSize, i2);
        this.mSize += i2;
    }

    public void clear() {
        this.mSize = 0;
    }

    public long get(int i2) {
        checkBounds(this.mSize, i2);
        return this.mValues[i2];
    }

    public int indexOf(long j2) {
        int i2 = this.mSize;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.mValues[i7] == j2) {
                return i7;
            }
        }
        return -1;
    }

    public void remove(int i2) {
        checkBounds(this.mSize, i2);
        long[] jArr = this.mValues;
        System.arraycopy(jArr, i2 + 1, jArr, i2, (this.mSize - i2) - 1);
        this.mSize--;
    }

    public void resize(int i2) {
        checkArgumentNonnegative(i2);
        long[] jArr = this.mValues;
        if (i2 <= jArr.length) {
            Arrays.fill(jArr, i2, jArr.length, 0);
        } else {
            ensureCapacity(i2 - this.mSize);
        }
        this.mSize = i2;
    }

    public void set(int i2, long j2) {
        checkBounds(this.mSize, i2);
        this.mValues[i2] = j2;
    }

    public int size() {
        return this.mSize;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.mValues, this.mSize);
    }

    public void add(int i2, long j2) {
        ensureCapacity(1);
        int i7 = this.mSize;
        int i8 = i7 - i2;
        int i10 = i7 + 1;
        this.mSize = i10;
        checkBounds(i10, i2);
        if (i8 != 0) {
            long[] jArr = this.mValues;
            System.arraycopy(jArr, i2, jArr, i2 + 1, i8);
        }
        this.mValues[i2] = j2;
    }

    public LongArray clone() {
        try {
            LongArray longArray = (LongArray) super.clone();
            try {
                longArray.mValues = (long[]) this.mValues.clone();
                return longArray;
            } catch (CloneNotSupportedException unused) {
                return longArray;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public LongArray() {
        this(10);
    }

    public LongArray(int i2) {
        if (i2 == 0) {
            this.mValues = new long[0];
        } else {
            this.mValues = new long[i2];
        }
        this.mSize = 0;
    }
}
