package com.samsung.android.livetranslation.data;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageBuffer {
    private int mBaseImageHash;
    private byte[] mImageBuffer;

    public ImageBuffer(byte[] bArr, int i2) {
        this.mImageBuffer = bArr;
        this.mBaseImageHash = i2;
    }

    public int getBaseImageHash() {
        return this.mBaseImageHash;
    }

    public byte[] getImageBuffer() {
        return this.mImageBuffer;
    }
}
