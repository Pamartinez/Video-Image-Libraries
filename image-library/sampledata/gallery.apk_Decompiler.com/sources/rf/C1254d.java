package rf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: rf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1254d extends OutputStream {

    /* renamed from: i  reason: collision with root package name */
    public static final byte[] f5056i = new byte[0];
    public final int d = 128;
    public final ArrayList e = new ArrayList();
    public int f;
    public byte[] g = new byte[128];

    /* renamed from: h  reason: collision with root package name */
    public int f5057h;

    public final void a(int i2) {
        this.e.add(new x(this.g));
        int length = this.f + this.g.length;
        this.f = length;
        this.g = new byte[Math.max(this.d, Math.max(i2, length >>> 1))];
        this.f5057h = 0;
    }

    public final void c() {
        int i2 = this.f5057h;
        byte[] bArr = this.g;
        int length = bArr.length;
        ArrayList arrayList = this.e;
        if (i2 >= length) {
            arrayList.add(new x(this.g));
            this.g = f5056i;
        } else if (i2 > 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i2));
            arrayList.add(new x(bArr2));
        }
        this.f += this.f5057h;
        this.f5057h = 0;
    }

    public final synchronized C1255e f() {
        C1255e eVar;
        c();
        ArrayList arrayList = this.e;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add((C1255e) it.next());
            }
            arrayList = arrayList2;
        }
        if (arrayList.isEmpty()) {
            eVar = C1255e.d;
        } else {
            eVar = C1255e.i(arrayList.iterator(), arrayList.size());
        }
        return eVar;
    }

    public final String toString() {
        int i2;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        synchronized (this) {
            i2 = this.f + this.f5057h;
        }
        return String.format("<ByteString.Output@%s size=%d>", new Object[]{hexString, Integer.valueOf(i2)});
    }

    public final synchronized void write(int i2) {
        try {
            if (this.f5057h == this.g.length) {
                a(1);
            }
            byte[] bArr = this.g;
            int i7 = this.f5057h;
            this.f5057h = i7 + 1;
            bArr[i7] = (byte) i2;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void write(byte[] bArr, int i2, int i7) {
        try {
            byte[] bArr2 = this.g;
            int length = bArr2.length;
            int i8 = this.f5057h;
            if (i7 <= length - i8) {
                System.arraycopy(bArr, i2, bArr2, i8, i7);
                this.f5057h += i7;
            } else {
                int length2 = bArr2.length - i8;
                System.arraycopy(bArr, i2, bArr2, i8, length2);
                int i10 = i7 - length2;
                a(i10);
                System.arraycopy(bArr, i2 + length2, this.g, 0, i10);
                this.f5057h = i10;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
