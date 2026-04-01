package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TsUtil {
    public static int findSyncBytePosition(byte[] bArr, int i2, int i7) {
        while (i2 < i7 && bArr[i2] != 71) {
            i2++;
        }
        return i2;
    }

    public static boolean isStartOfTsPacket(byte[] bArr, int i2, int i7, int i8) {
        int i10 = 0;
        for (int i11 = -4; i11 <= 4; i11++) {
            int i12 = (i11 * 188) + i8;
            if (i12 < i2 || i12 >= i7 || bArr[i12] != 71) {
                i10 = 0;
            } else {
                i10++;
                if (i10 == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public static long readPcrFromPacket(ParsableByteArray parsableByteArray, int i2, int i7) {
        parsableByteArray.setPosition(i2);
        if (parsableByteArray.bytesLeft() < 5) {
            return -9223372036854775807L;
        }
        int readInt = parsableByteArray.readInt();
        if ((8388608 & readInt) != 0 || ((2096896 & readInt) >> 8) != i7 || (readInt & 32) == 0 || parsableByteArray.readUnsignedByte() < 7 || parsableByteArray.bytesLeft() < 7 || (parsableByteArray.readUnsignedByte() & 16) != 16) {
            return -9223372036854775807L;
        }
        byte[] bArr = new byte[6];
        parsableByteArray.readBytes(bArr, 0, 6);
        return readPcrValueFromPcrBytes(bArr);
    }

    private static long readPcrValueFromPcrBytes(byte[] bArr) {
        return ((((long) bArr[0]) & 255) << 25) | ((((long) bArr[1]) & 255) << 17) | ((((long) bArr[2]) & 255) << 9) | ((((long) bArr[3]) & 255) << 1) | ((255 & ((long) bArr[4])) >> 7);
    }
}
