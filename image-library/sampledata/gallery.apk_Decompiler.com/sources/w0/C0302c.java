package w0;

import java.util.Arrays;

/* renamed from: w0.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0302c extends C0303d {
    public final byte[] d;

    public C0302c(byte[] bArr) {
        super(C0308i.BYTE_STRING);
        if (bArr == null) {
            this.d = null;
        } else {
            this.d = bArr;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0302c) {
            C0302c cVar = (C0302c) obj;
            if (!super.equals(obj) || !Arrays.equals(this.d, cVar.d)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.d) ^ super.hashCode();
    }
}
