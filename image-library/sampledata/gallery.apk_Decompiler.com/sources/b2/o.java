package B2;

import android.util.SparseArray;
import androidx.appcompat.widget.TintTypedArray;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.IOException;
import java.io.OutputStream;
import rf.C1252b;
import rf.C1255e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public final int f54a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public final Cloneable f55c;
    public final Object d;

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Cloneable, byte[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public o(java.io.OutputStream r1, byte[] r2) {
        /*
            r0 = this;
            r0.<init>()
            r0.d = r1
            r0.f55c = r2
            r1 = 0
            r0.b = r1
            int r1 = r2.length
            r0.f54a = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.o.<init>(java.io.OutputStream, byte[]):void");
    }

    public static int a(int i2, int i7) {
        return c(i7) + h(i2);
    }

    public static int b(int i2, int i7) {
        return c(i7) + h(i2);
    }

    public static int c(int i2) {
        if (i2 >= 0) {
            return f(i2);
        }
        return 10;
    }

    public static int d(int i2, C1252b bVar) {
        return e(bVar) + h(i2);
    }

    public static int e(C1252b bVar) {
        int a7 = bVar.a();
        return f(a7) + a7;
    }

    public static int f(int i2) {
        if ((i2 & -128) == 0) {
            return 1;
        }
        if ((i2 & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i2) == 0) {
            return 3;
        }
        if ((i2 & -268435456) == 0) {
            return 4;
        }
        return 5;
    }

    public static int g(long j2) {
        if ((-128 & j2) == 0) {
            return 1;
        }
        if ((-16384 & j2) == 0) {
            return 2;
        }
        if ((-2097152 & j2) == 0) {
            return 3;
        }
        if ((-268435456 & j2) == 0) {
            return 4;
        }
        if ((-34359738368L & j2) == 0) {
            return 5;
        }
        if ((-4398046511104L & j2) == 0) {
            return 6;
        }
        if ((-562949953421312L & j2) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j2) == 0) {
            return 8;
        }
        if ((j2 & Long.MIN_VALUE) == 0) {
            return 9;
        }
        return 10;
    }

    public static int h(int i2) {
        return f(i2 << 3);
    }

    public static o j(OutputStream outputStream, int i2) {
        return new o(outputStream, new byte[i2]);
    }

    public void i() {
        if (((OutputStream) this.d) != null) {
            k();
        }
    }

    public void k() {
        OutputStream outputStream = (OutputStream) this.d;
        if (outputStream != null) {
            outputStream.write((byte[]) this.f55c, 0, this.b);
            this.b = 0;
            return;
        }
        throw new IOException("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    public void l(int i2, int i7) {
        x(i2, 0);
        n(i7);
    }

    public void m(int i2, int i7) {
        x(i2, 0);
        n(i7);
    }

    public void n(int i2) {
        if (i2 >= 0) {
            v(i2);
        } else {
            w((long) i2);
        }
    }

    public void o(int i2, C1252b bVar) {
        x(i2, 2);
        p(bVar);
    }

    public void p(C1252b bVar) {
        v(bVar.a());
        bVar.d(this);
    }

    public void q(int i2) {
        byte b5 = (byte) i2;
        if (this.b == this.f54a) {
            k();
        }
        int i7 = this.b;
        this.b = i7 + 1;
        ((byte[]) this.f55c)[i7] = b5;
    }

    public void r(C1255e eVar) {
        int size = eVar.size();
        byte[] bArr = (byte[]) this.f55c;
        int i2 = this.b;
        int i7 = this.f54a;
        int i8 = i7 - i2;
        if (i8 >= size) {
            eVar.q(0, i2, size, bArr);
            this.b += size;
            return;
        }
        eVar.q(0, i2, i8, bArr);
        int i10 = size - i8;
        this.b = i7;
        k();
        if (i10 <= i7) {
            eVar.q(i8, 0, i10, bArr);
            this.b = i10;
            return;
        }
        OutputStream outputStream = (OutputStream) this.d;
        if (i8 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Source offset < 0: ");
            sb2.append(i8);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else if (i10 >= 0) {
            int i11 = i8 + i10;
            if (i11 > eVar.size()) {
                StringBuilder sb3 = new StringBuilder(39);
                sb3.append("Source end offset exceeded: ");
                sb3.append(i11);
                throw new IndexOutOfBoundsException(sb3.toString());
            } else if (i10 > 0) {
                eVar.B(outputStream, i8, i10);
            }
        } else {
            StringBuilder sb4 = new StringBuilder(23);
            sb4.append("Length < 0: ");
            sb4.append(i10);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
    }

    public void s(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = (byte[]) this.f55c;
        int i2 = this.b;
        int i7 = this.f54a;
        int i8 = i7 - i2;
        if (i8 >= length) {
            System.arraycopy(bArr, 0, bArr2, i2, length);
            this.b += length;
            return;
        }
        System.arraycopy(bArr, 0, bArr2, i2, i8);
        int i10 = length - i8;
        this.b = i7;
        k();
        if (i10 <= i7) {
            System.arraycopy(bArr, i8, bArr2, 0, i10);
            this.b = i10;
            return;
        }
        ((OutputStream) this.d).write(bArr, i8, i10);
    }

    public void t(int i2) {
        q(i2 & ScoverState.TYPE_NFC_SMART_COVER);
        q((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        q((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
        q((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public void u(long j2) {
        q(((int) j2) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 8)) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 16)) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 24)) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 32)) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 40)) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 48)) & ScoverState.TYPE_NFC_SMART_COVER);
        q(((int) (j2 >> 56)) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public void v(int i2) {
        while ((i2 & -128) != 0) {
            q((i2 & 127) | 128);
            i2 >>>= 7;
        }
        q(i2);
    }

    public void w(long j2) {
        while ((-128 & j2) != 0) {
            q((((int) j2) & 127) | 128);
            j2 >>>= 7;
        }
        q((int) j2);
    }

    public void x(int i2, int i7) {
        v((i2 << 3) | i7);
    }

    public o(p pVar, TintTypedArray tintTypedArray) {
        this.f55c = new SparseArray();
        this.d = pVar;
        this.f54a = tintTypedArray.getResourceId(28, 0);
        this.b = tintTypedArray.getResourceId(52, 0);
    }
}
