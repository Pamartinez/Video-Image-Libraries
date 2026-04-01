package androidx.media3.datasource;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DataSourceUtil {
    public static void closeQuietly(DataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] readToEnd(DataSource dataSource) {
        byte[] bArr = new byte[1024];
        int i2 = 0;
        int i7 = 0;
        while (i2 != -1) {
            if (i7 == bArr.length) {
                bArr = Arrays.copyOf(bArr, bArr.length * 2);
            }
            i2 = dataSource.read(bArr, i7, bArr.length - i7);
            if (i2 != -1) {
                i7 += i2;
            }
        }
        return Arrays.copyOf(bArr, i7);
    }
}
