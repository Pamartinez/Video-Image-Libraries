package H2;

import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f340a = 0;

    static {
        new OutputStream();
    }

    public static byte[] a(ArrayDeque arrayDeque, int i2) {
        if (arrayDeque.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr = (byte[]) arrayDeque.remove();
        if (bArr.length == i2) {
            return bArr;
        }
        int length = i2 - bArr.length;
        byte[] copyOf = Arrays.copyOf(bArr, i2);
        while (length > 0) {
            byte[] bArr2 = (byte[]) arrayDeque.remove();
            int min = Math.min(length, bArr2.length);
            System.arraycopy(bArr2, 0, copyOf, i2 - length, min);
            length -= min;
        }
        return copyOf;
    }

    public static byte[] b(InputStream inputStream) {
        int i2;
        inputStream.getClass();
        ArrayDeque arrayDeque = new ArrayDeque(20);
        int min = Math.min(SerializeOptions.SORT, Math.max(128, Integer.highestOneBit(0) * 2));
        int i7 = 0;
        while (i7 < 2147483639) {
            int min2 = Math.min(min, 2147483639 - i7);
            byte[] bArr = new byte[min2];
            arrayDeque.add(bArr);
            int i8 = 0;
            while (i8 < min2) {
                int read = inputStream.read(bArr, i8, min2 - i8);
                if (read == -1) {
                    return a(arrayDeque, i7);
                }
                i8 += read;
                i7 += read;
            }
            if (min < 4096) {
                i2 = 4;
            } else {
                i2 = 2;
            }
            min = C0246a.j0(((long) min) * ((long) i2));
        }
        if (inputStream.read() == -1) {
            return a(arrayDeque, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }
}
