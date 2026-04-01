package rf;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.appfunctions.schema.internal.dependencies.d0;
import i.C0212a;
import java.io.OutputStream;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class x extends C1255e {
    public final byte[] e;
    public int f = 0;

    public x(byte[] bArr) {
        this.e = bArr;
    }

    public final void B(OutputStream outputStream, int i2, int i7) {
        outputStream.write(this.e, i2, i7);
    }

    public final boolean C(x xVar, int i2, int i7) {
        byte[] bArr = xVar.e;
        int length = bArr.length;
        byte[] bArr2 = this.e;
        if (i7 > length) {
            int length2 = bArr2.length;
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Length too large: ");
            sb2.append(i7);
            sb2.append(length2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (i2 + i7 <= bArr.length) {
            int i8 = 0;
            while (i8 < i7) {
                if (bArr2[i8] != bArr[i2]) {
                    return false;
                }
                i8++;
                i2++;
            }
            return true;
        } else {
            int length3 = xVar.e.length;
            StringBuilder sb3 = new StringBuilder(59);
            sb3.append("Ran off end of other: ");
            sb3.append(i2);
            sb3.append(ArcCommonLog.TAG_COMMA);
            sb3.append(i7);
            sb3.append(ArcCommonLog.TAG_COMMA);
            sb3.append(length3);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1255e) || size() != ((C1255e) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof x) {
            return C((x) obj, 0, size());
        }
        if (obj instanceof C) {
            return obj.equals(this);
        }
        String valueOf = String.valueOf(obj.getClass());
        throw new IllegalArgumentException(C0212a.p(new StringBuilder(valueOf.length() + 49), "Has a new type of ByteString been created? Found ", valueOf));
    }

    public final int hashCode() {
        int i2 = this.f;
        if (i2 == 0) {
            int size = size();
            i2 = w(size, 0, size);
            if (i2 == 0) {
                i2 = 1;
            }
            this.f = i2;
        }
        return i2;
    }

    public Iterator iterator() {
        return new d0(this);
    }

    public void r(int i2, int i7, int i8, byte[] bArr) {
        System.arraycopy(this.e, i2, bArr, i7, i8);
    }

    public final int s() {
        return 0;
    }

    public int size() {
        return this.e.length;
    }

    public final boolean t() {
        return true;
    }

    public final boolean u() {
        byte[] bArr = this.e;
        if (G.c(0, bArr.length, bArr) == 0) {
            return true;
        }
        return false;
    }

    public final int w(int i2, int i7, int i8) {
        for (int i10 = i7; i10 < i7 + i8; i10++) {
            i2 = (i2 * 31) + this.e[i10];
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0049, code lost:
        if (r6[r8] > -65) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0092, code lost:
        if (r6[r7] > -65) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r6[r8] > -65) goto L_0x0094;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int x(int r7, int r8, int r9) {
        /*
            r6 = this;
            int r9 = r9 + r8
            byte[] r6 = r6.e
            if (r7 == 0) goto L_0x0095
            if (r8 < r9) goto L_0x0008
            return r7
        L_0x0008:
            byte r0 = (byte) r7
            r1 = -32
            r2 = -1
            r3 = -65
            if (r0 >= r1) goto L_0x001f
            r7 = -62
            if (r0 < r7) goto L_0x0094
            int r7 = r8 + 1
            byte r8 = r6[r8]
            if (r8 <= r3) goto L_0x001c
            goto L_0x0094
        L_0x001c:
            r8 = r7
            goto L_0x0095
        L_0x001f:
            r4 = -16
            if (r0 >= r4) goto L_0x004c
            int r7 = r7 >> 8
            int r7 = ~r7
            byte r7 = (byte) r7
            if (r7 != 0) goto L_0x0037
            int r7 = r8 + 1
            byte r8 = r6[r8]
            if (r7 < r9) goto L_0x0034
            int r6 = rf.G.a(r0, r8)
            return r6
        L_0x0034:
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x0037:
            if (r7 > r3) goto L_0x0094
            r4 = -96
            if (r0 != r1) goto L_0x003f
            if (r7 < r4) goto L_0x0094
        L_0x003f:
            r1 = -19
            if (r0 != r1) goto L_0x0045
            if (r7 >= r4) goto L_0x0094
        L_0x0045:
            int r7 = r8 + 1
            byte r8 = r6[r8]
            if (r8 <= r3) goto L_0x001c
            goto L_0x0094
        L_0x004c:
            int r1 = r7 >> 8
            int r1 = ~r1
            byte r1 = (byte) r1
            if (r1 != 0) goto L_0x005f
            int r7 = r8 + 1
            byte r1 = r6[r8]
            if (r7 < r9) goto L_0x005d
            int r6 = rf.G.a(r0, r1)
            return r6
        L_0x005d:
            r8 = 0
            goto L_0x0065
        L_0x005f:
            int r7 = r7 >> 16
            byte r7 = (byte) r7
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x0065:
            if (r8 != 0) goto L_0x0081
            int r8 = r7 + 1
            byte r7 = r6[r7]
            if (r8 < r9) goto L_0x007e
            r6 = -12
            if (r0 > r6) goto L_0x007d
            if (r1 > r3) goto L_0x007d
            if (r7 <= r3) goto L_0x0076
            goto L_0x007d
        L_0x0076:
            int r6 = r1 << 8
            r6 = r6 ^ r0
            int r7 = r7 << 16
            r6 = r6 ^ r7
            return r6
        L_0x007d:
            return r2
        L_0x007e:
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x0081:
            if (r1 > r3) goto L_0x0094
            int r0 = r0 << 28
            int r1 = r1 + 112
            int r1 = r1 + r0
            int r0 = r1 >> 30
            if (r0 != 0) goto L_0x0094
            if (r8 > r3) goto L_0x0094
            int r8 = r7 + 1
            byte r7 = r6[r7]
            if (r7 <= r3) goto L_0x0095
        L_0x0094:
            return r2
        L_0x0095:
            int r6 = rf.G.c(r8, r9, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.x.x(int, int, int):int");
    }

    public final int y() {
        return this.f;
    }

    public final String z() {
        byte[] bArr = this.e;
        return new String(bArr, 0, bArr.length, "UTF-8");
    }
}
