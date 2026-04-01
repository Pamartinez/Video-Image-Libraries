package androidx.collection.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0000\u001a \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0000\u001a\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0000\u001a\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0000\u001a\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0016"}, d2 = {"EMPTY_INTS", "", "EMPTY_LONGS", "", "EMPTY_OBJECTS", "", "", "[Ljava/lang/Object;", "binarySearch", "", "array", "size", "value", "", "equal", "", "a", "b", "idealByteArraySize", "need", "idealIntArraySize", "idealLongArraySize", "collection"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ContainerHelpersKt {
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final Object[] EMPTY_OBJECTS = new Object[0];

    public static final int binarySearch(int[] iArr, int i2, int i7) {
        j.e(iArr, "array");
        int i8 = i2 - 1;
        int i10 = 0;
        while (i10 <= i8) {
            int i11 = (i10 + i8) >>> 1;
            int i12 = iArr[i11];
            if (i12 < i7) {
                i10 = i11 + 1;
            } else if (i12 <= i7) {
                return i11;
            } else {
                i8 = i11 - 1;
            }
        }
        return ~i10;
    }

    public static final boolean equal(Object obj, Object obj2) {
        return j.a(obj, obj2);
    }

    public static final int idealByteArraySize(int i2) {
        for (int i7 = 4; i7 < 32; i7++) {
            int i8 = (1 << i7) - 12;
            if (i2 <= i8) {
                return i8;
            }
        }
        return i2;
    }

    public static final int idealIntArraySize(int i2) {
        return idealByteArraySize(i2 * 4) / 4;
    }

    public static final int idealLongArraySize(int i2) {
        return idealByteArraySize(i2 * 8) / 8;
    }

    public static final int binarySearch(long[] jArr, int i2, long j2) {
        j.e(jArr, "array");
        int i7 = i2 - 1;
        int i8 = 0;
        while (i8 <= i7) {
            int i10 = (i8 + i7) >>> 1;
            int i11 = (jArr[i10] > j2 ? 1 : (jArr[i10] == j2 ? 0 : -1));
            if (i11 < 0) {
                i8 = i10 + 1;
            } else if (i11 <= 0) {
                return i10;
            } else {
                i7 = i10 - 1;
            }
        }
        return ~i8;
    }
}
