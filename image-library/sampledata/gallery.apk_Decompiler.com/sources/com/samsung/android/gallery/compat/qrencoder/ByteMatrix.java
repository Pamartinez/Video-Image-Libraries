package com.samsung.android.gallery.compat.qrencoder;

import java.lang.reflect.Array;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;

    public ByteMatrix(int i2, int i7) {
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i7;
        this.bytes = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        this.width = i2;
        this.height = i7;
    }

    public void clear(byte b) {
        for (byte[] fill : this.bytes) {
            Arrays.fill(fill, b);
        }
    }

    public byte get(int i2, int i7) {
        return this.bytes[i7][i2];
    }

    public byte[][] getArray() {
        return this.bytes;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void set(int i2, int i7, int i8) {
        this.bytes[i7][i2] = (byte) i8;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int i2 = 0; i2 < this.height; i2++) {
            byte[] bArr = this.bytes[i2];
            for (int i7 = 0; i7 < this.width; i7++) {
                byte b = bArr[i7];
                if (b == 0) {
                    sb2.append(" 0");
                } else if (b != 1) {
                    sb2.append("  ");
                } else {
                    sb2.append(" 1");
                }
            }
            sb2.append(10);
        }
        return sb2.toString();
    }

    public void set(int i2, int i7, boolean z) {
        this.bytes[i7][i2] = z ? (byte) 1 : 0;
    }
}
