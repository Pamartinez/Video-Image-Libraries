package rf;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;

/* renamed from: rf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1251a extends FilterInputStream {
    public int d;

    public C1251a(ByteArrayInputStream byteArrayInputStream, int i2) {
        super(byteArrayInputStream);
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
        long skip = super.skip(Math.min(j2, (long) this.d));
        if (skip >= 0) {
            this.d = (int) (((long) this.d) - skip);
        }
        return skip;
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
