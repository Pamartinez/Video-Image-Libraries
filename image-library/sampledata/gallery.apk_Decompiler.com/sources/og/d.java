package og;

import ie.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements m {
    public final /* synthetic */ InputStream d;

    public d(c cVar, InputStream inputStream) {
        this.d = inputStream;
    }

    public final void close() {
        this.d.close();
    }

    public final long e(a aVar, long j2) {
        try {
            if (!Thread.interrupted()) {
                j j3 = aVar.j(1);
                int read = this.d.read(j3.f5016a, j3.f5017c, (int) Math.min(8192, (long) (8192 - j3.f5017c)));
                if (read != -1) {
                    j3.f5017c += read;
                    long j8 = (long) read;
                    aVar.e += j8;
                    return j8;
                } else if (j3.b != j3.f5017c) {
                    return -1;
                } else {
                    aVar.d = j3.a();
                    k.H(j3);
                    return -1;
                }
            } else {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException("interrupted");
            }
        } catch (AssertionError e) {
            if (e.getCause() == null || e.getMessage() == null || !e.getMessage().contains("getsockname failed")) {
                throw e;
            }
            throw new IOException(e);
        }
    }

    public final String toString() {
        return "source(" + this.d + ")";
    }
}
