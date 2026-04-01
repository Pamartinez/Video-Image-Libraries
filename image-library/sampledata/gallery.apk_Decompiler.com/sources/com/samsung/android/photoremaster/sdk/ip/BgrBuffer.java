package com.samsung.android.photoremaster.sdk.ip;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgrBuffer {
    private final byte[] data;
    private String filePath;
    private final PrSize size;

    public BgrBuffer(PrSize prSize, byte[] bArr) {
        this.size = prSize;
        this.data = bArr;
        int height = prSize.getHeight() * prSize.getWidth() * 3;
        if (bArr.length < height) {
            StringBuilder o2 = C0086a.o(height, "Expected minimum data size of ", ", but received ");
            o2.append(bArr.length);
            throw new IllegalArgumentException(o2.toString());
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public PrSize getSize() {
        return this.size;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public BgrBuffer(PrSize prSize, byte[] bArr, String str) {
        this.size = prSize;
        this.data = bArr;
        int height = prSize.getHeight() * prSize.getWidth() * 3;
        if (bArr.length >= height) {
            this.filePath = str;
            return;
        }
        StringBuilder o2 = C0086a.o(height, "Expected minimum data size of ", ", but received ");
        o2.append(bArr.length);
        throw new IllegalArgumentException(o2.toString());
    }
}
