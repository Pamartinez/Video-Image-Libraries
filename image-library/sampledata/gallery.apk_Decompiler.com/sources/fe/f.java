package fe;

import com.adobe.internal.xmp.options.SerializeOptions;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4346a;
    public static final LinkedBlockingQueue b;

    static {
        int max = Math.max(16384, SerializeOptions.SORT);
        f4346a = max;
        b = new LinkedBlockingQueue(131072 / max);
    }

    public static byte[] a(int i2) {
        byte[] bArr;
        if (i2 != f4346a || (bArr = (byte[]) b.poll()) == null) {
            return new byte[i2];
        }
        return bArr;
    }

    public static void b(byte[] bArr) {
        if (bArr.length == f4346a) {
            b.offer(bArr);
        }
    }
}
