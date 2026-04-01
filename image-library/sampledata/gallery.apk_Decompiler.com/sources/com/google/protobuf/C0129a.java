package com.google.protobuf;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: com.google.protobuf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0129a extends FilterInputStream {
    public int d;

    public C0129a(InputStream inputStream, int i2) {
        super(inputStream);
        this.d = i2;
    }

    public final int available() {
        return Math.min(super.available(), this.d);
    }

    public final int read() {
        if (this.d <= 0) {
            return -1;
        }
        int read = super.read();
        if (read >= 0) {
            this.d--;
        }
        return read;
    }

    public final long skip(long j2) {
        int skip = (int) super.skip(Math.min(j2, (long) this.d));
        if (skip >= 0) {
            this.d -= skip;
        }
        return (long) skip;
    }

    public final int read(byte[] bArr, int i2, int i7) {
        int i8 = this.d;
        if (i8 <= 0) {
            return -1;
        }
        int read = super.read(bArr, i2, Math.min(i7, i8));
        if (read >= 0) {
            this.d -= read;
        }
        return read;
    }
}
