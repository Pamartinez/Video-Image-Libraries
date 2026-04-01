package com.samsung.android.sdk.sgpl.media.iso;

import com.samsung.android.sdk.cover.ScoverState;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Memory {
    public static int peekInt(byte[] bArr, int i2, ByteOrder byteOrder) {
        byte b;
        int i7;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i8 = (bArr[i2 + 1] & 255) << 16;
            b = i8 | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 2] & 255) << 8);
            i7 = bArr[i2 + 3] & 255;
        } else {
            int i10 = (bArr[i2 + 1] & 255) << 8;
            b = i10 | (bArr[i2] & 255) | ((bArr[i2 + 2] & 255) << 16);
            i7 = (bArr[i2 + 3] & 255) << 24;
        }
        return i7 | b;
    }

    public static void pokeInt(byte[] bArr, int i2, int i7, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            bArr[i2] = (byte) ((i7 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
            bArr[i2 + 1] = (byte) ((i7 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
            bArr[i2 + 2] = (byte) ((i7 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
            bArr[i2 + 3] = (byte) (i7 & ScoverState.TYPE_NFC_SMART_COVER);
            return;
        }
        bArr[i2] = (byte) (i7 & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[i2 + 1] = (byte) ((i7 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[i2 + 2] = (byte) ((i7 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[i2 + 3] = (byte) ((i7 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
    }
}
