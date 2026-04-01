package com.samsung.o3dp.jpeg3dcontainer.util;

import android.util.Log;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ByteUtil {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final long MAX_UNSIGNED_INT = 4294967295L;
    private static final String TAG = "ByteUtil";
    private static final int UNSIGNED_INT_BYTES = 4;

    public static boolean compare(byte[] bArr, int i2, int i7, byte[] bArr2) {
        for (int i8 = 0; i8 < i7; i8++) {
            if (bArr[i2 + i8] != bArr2[i8]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] intToByteArray(int i2) {
        return new byte[]{(byte) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER), (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER)};
    }

    public static void putLongAsUInt(long j2, byte[] bArr, int i2, ByteOrder byteOrder) {
        if (j2 > MAX_UNSIGNED_INT) {
            Log.w(TAG, "srcLong: " + j2 + " > MAX_UNSIGNED_INT: 4294967295");
        } else if (j2 < 0) {
            Log.w(TAG, "srcLong (" + j2 + ") < 0");
        }
        System.arraycopy(ByteBuffer.allocate(4).order(byteOrder).putInt((int) (j2 & MAX_UNSIGNED_INT)).array(), 0, bArr, i2, 4);
    }

    public static byte[] readNBytes(InputStream inputStream, int i2) {
        byte[] bArr = new byte[i2];
        int i7 = 0;
        while (i7 < i2) {
            int read = inputStream.read(bArr, i7, i2 - i7);
            if (read == -1) {
                break;
            }
            i7 += read;
        }
        if (i7 >= i2) {
            return bArr;
        }
        byte[] bArr2 = new byte[i7];
        System.arraycopy(bArr, 0, bArr2, 0, i7);
        return bArr2;
    }

    public static byte[] readUntilStopSequence(InputStream inputStream, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i7 = (i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER;
        int i8 = i2 & ScoverState.TYPE_NFC_SMART_COVER;
        int i10 = -1;
        while (true) {
            int read = inputStream.read();
            if (read != -1 && (i10 != i7 || read != i8)) {
                if (i10 != -1) {
                    byteArrayOutputStream.write(i10);
                }
                i10 = read;
            } else if (i10 != -1 && read == -1) {
                byteArrayOutputStream.write(i10);
            }
        }
        byteArrayOutputStream.write(i10);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] toByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr, 0, 4096);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static int toInt16(byte[] bArr, ByteOrder byteOrder) {
        if (bArr.length == 2) {
            byte b = bArr[0] & 255;
            byte b5 = bArr[1] & 255;
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return b5 | (b << 8);
            }
            return (b5 << 8) | b;
        }
        throw new IllegalArgumentException("Byte array must be non-null and exactly 2 bytes long.");
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[(bArr.length + bArr2.length + bArr3.length)];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        int length = bArr.length;
        System.arraycopy(bArr2, 0, bArr4, length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, length + bArr2.length, bArr3.length);
        return bArr4;
    }
}
