package androidx.core.content.res;

import java.lang.reflect.Array;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class GrowingArrayUtils {
    public static <T> T[] append(T[] tArr, int i2, T t) {
        if (i2 + 1 > tArr.length) {
            T[] tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), growSize(i2));
            System.arraycopy(tArr, 0, tArr2, 0, i2);
            tArr = tArr2;
        }
        tArr[i2] = t;
        return tArr;
    }

    public static int growSize(int i2) {
        if (i2 <= 4) {
            return 8;
        }
        return i2 * 2;
    }

    public static int[] append(int[] iArr, int i2, int i7) {
        if (i2 + 1 > iArr.length) {
            int[] iArr2 = new int[growSize(i2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr = iArr2;
        }
        iArr[i2] = i7;
        return iArr;
    }
}
