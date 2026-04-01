package com.adobe.internal.xmp.impl;

import java.io.OutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CountOutputStream extends OutputStream {
    private int bytesWritten = 0;
    private final OutputStream out;

    public CountOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    public int getBytesWritten() {
        return this.bytesWritten;
    }

    public void write(byte[] bArr, int i2, int i7) {
        this.out.write(bArr, i2, i7);
        this.bytesWritten += i7;
    }

    public void write(byte[] bArr) {
        this.out.write(bArr);
        this.bytesWritten += bArr.length;
    }

    public void write(int i2) {
        this.out.write(i2);
        this.bytesWritten++;
    }
}
