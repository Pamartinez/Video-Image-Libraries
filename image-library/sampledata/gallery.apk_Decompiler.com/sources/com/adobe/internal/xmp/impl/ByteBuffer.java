package com.adobe.internal.xmp.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ByteBuffer {
    private byte[] buffer;
    private String encoding;
    private int length;

    public ByteBuffer(int i2) {
        this.encoding = null;
        this.buffer = new byte[i2];
        this.length = 0;
    }

    private void ensureCapacity(int i2) {
        byte[] bArr = this.buffer;
        if (i2 > bArr.length) {
            byte[] bArr2 = new byte[(bArr.length * 2)];
            this.buffer = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
    }

    public void append(byte b) {
        ensureCapacity(this.length + 1);
        byte[] bArr = this.buffer;
        int i2 = this.length;
        this.length = i2 + 1;
        bArr[i2] = b;
    }

    public byte byteAt(int i2) {
        if (i2 < this.length) {
            return this.buffer[i2];
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public int charAt(int i2) {
        if (i2 < this.length) {
            return this.buffer[i2] & 255;
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public InputStream getByteStream() {
        return new ByteArrayInputStream(this.buffer, 0, this.length);
    }

    public String getEncoding() {
        if (this.encoding == null) {
            int i2 = this.length;
            if (i2 < 2) {
                this.encoding = "UTF-8";
            } else {
                byte[] bArr = this.buffer;
                byte b = bArr[0];
                if (b == 0) {
                    if (i2 < 4 || bArr[1] != 0) {
                        this.encoding = "UTF-16BE";
                    } else if ((bArr[2] & 255) == 254 && (bArr[3] & 255) == 255) {
                        this.encoding = "UTF-32BE";
                    } else {
                        this.encoding = "UTF-32";
                    }
                } else if ((b & 255) < 128) {
                    if (bArr[1] != 0) {
                        this.encoding = "UTF-8";
                    } else if (i2 < 4 || bArr[2] != 0) {
                        this.encoding = "UTF-16LE";
                    } else {
                        this.encoding = "UTF-32LE";
                    }
                } else if ((b & 255) == 239) {
                    this.encoding = "UTF-8";
                } else if ((b & 255) == 254) {
                    this.encoding = "UTF-16";
                } else if (i2 < 4 || bArr[2] != 0) {
                    this.encoding = "UTF-16";
                } else {
                    this.encoding = "UTF-32";
                }
            }
        }
        return this.encoding;
    }

    public int length() {
        return this.length;
    }

    public void append(byte[] bArr, int i2, int i7) {
        ensureCapacity(this.length + i7);
        System.arraycopy(bArr, i2, this.buffer, this.length, i7);
        this.length += i7;
    }

    public ByteBuffer(byte[] bArr) {
        this.encoding = null;
        this.buffer = bArr;
        this.length = bArr.length;
    }

    public void append(byte[] bArr) {
        append(bArr, 0, bArr.length);
    }

    public void append(ByteBuffer byteBuffer) {
        append(byteBuffer.buffer, 0, byteBuffer.length);
    }

    public ByteBuffer(byte[] bArr, int i2) {
        this.encoding = null;
        if (i2 <= bArr.length) {
            this.buffer = bArr;
            this.length = i2;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
    }

    public ByteBuffer(InputStream inputStream) {
        this.encoding = null;
        this.length = 0;
        this.buffer = new byte[16384];
        while (true) {
            int read = inputStream.read(this.buffer, this.length, 16384);
            if (read > 0) {
                int i2 = this.length + read;
                this.length = i2;
                if (read == 16384) {
                    ensureCapacity(i2 + 16384);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public ByteBuffer(byte[] bArr, int i2, int i7) {
        this.encoding = null;
        if (i7 <= bArr.length - i2) {
            byte[] bArr2 = new byte[i7];
            this.buffer = bArr2;
            System.arraycopy(bArr, i2, bArr2, 0, i7);
            this.length = i7;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
    }
}
