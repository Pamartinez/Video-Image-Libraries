package og;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends InputStream {
    public final /* synthetic */ i d;

    public h(i iVar) {
        this.d = iVar;
    }

    public final int available() {
        i iVar = this.d;
        if (!iVar.f) {
            return (int) Math.min(iVar.d.e, 2147483647L);
        }
        throw new IOException("closed");
    }

    public final void close() {
        this.d.close();
    }

    public final int read() {
        i iVar = this.d;
        a aVar = iVar.d;
        if (iVar.f) {
            throw new IOException("closed");
        } else if (aVar.e == 0 && iVar.e.e(aVar, 8192) == -1) {
            return -1;
        } else {
            return aVar.c() & 255;
        }
    }

    public final String toString() {
        return this.d + ".inputStream()";
    }

    public final int read(byte[] bArr, int i2, int i7) {
        i iVar = this.d;
        a aVar = iVar.d;
        if (!iVar.f) {
            n.a((long) bArr.length, (long) i2, (long) i7);
            if (aVar.e == 0 && iVar.e.e(aVar, 8192) == -1) {
                return -1;
            }
            return aVar.read(bArr, i2, i7);
        }
        throw new IOException("closed");
    }
}
