package androidx.collection;

import java.lang.reflect.Array;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ArraySetJvmUtil {
    public static <T> T[] resizeForToArray(T[] tArr, int i2) {
        if (tArr.length < i2) {
            return (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i2);
        }
        if (tArr.length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }
}
