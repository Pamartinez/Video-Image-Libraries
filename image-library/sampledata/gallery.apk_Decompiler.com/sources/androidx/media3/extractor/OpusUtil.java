package androidx.media3.extractor;

import com.samsung.scsp.framework.core.network.Network;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OpusUtil {
    public static List<byte[]> buildInitializationData(byte[] bArr) {
        long sampleCountToNanoseconds = sampleCountToNanoseconds((long) getPreSkipSamples(bArr));
        long sampleCountToNanoseconds2 = sampleCountToNanoseconds(3840);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(buildNativeOrderByteArray(sampleCountToNanoseconds));
        arrayList.add(buildNativeOrderByteArray(sampleCountToNanoseconds2));
        return arrayList;
    }

    private static byte[] buildNativeOrderByteArray(long j2) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j2).array();
    }

    public static int getChannelCount(byte[] bArr) {
        return bArr[9] & 255;
    }

    private static long getPacketDurationUs(byte b, byte b5) {
        byte b8;
        byte b10 = b & 255;
        byte b11 = b & 3;
        if (b11 != 0) {
            b8 = 2;
            if (!(b11 == 1 || b11 == 2)) {
                b8 = b5 & 63;
            }
        } else {
            b8 = 1;
        }
        int i2 = b10 >> 3;
        int i7 = i2 & 3;
        return ((long) b8) * ((long) (i2 >= 16 ? 2500 << i7 : i2 >= 12 ? 10000 << (i2 & 1) : i7 == 3 ? Network.DEFAULT_TIMEOUT : 10000 << i7));
    }

    public static int getPreSkipSamples(byte[] bArr) {
        return (bArr[10] & 255) | ((bArr[11] & 255) << 8);
    }

    private static long sampleCountToNanoseconds(long j2) {
        return (j2 * 1000000000) / 48000;
    }

    public static long getPacketDurationUs(byte[] bArr) {
        byte b = 0;
        byte b5 = bArr[0];
        if (bArr.length > 1) {
            b = bArr[1];
        }
        return getPacketDurationUs(b5, b);
    }
}
