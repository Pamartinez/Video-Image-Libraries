package rf;

import G0.c;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Stack;

/* renamed from: rf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1255e implements Iterable {
    public static final x d = new x(new byte[0]);

    public static C1255e i(Iterator it, int i2) {
        if (i2 == 1) {
            return (C1255e) it.next();
        }
        int i7 = i2 >>> 1;
        return i(it, i7).p(i(it, i2 - i7));
    }

    public static C1254d v() {
        return new C1254d();
    }

    public final String A() {
        try {
            return z();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public abstract void B(OutputStream outputStream, int i2, int i7);

    public final C1255e p(C1255e eVar) {
        C c5;
        int size = size();
        int size2 = eVar.size();
        if (((long) size) + ((long) size2) < 2147483647L) {
            int[] iArr = C.k;
            if (this instanceof C) {
                c5 = (C) this;
            } else {
                c5 = null;
            }
            if (eVar.size() == 0) {
                return this;
            }
            if (size() == 0) {
                return eVar;
            }
            int size3 = eVar.size() + size();
            if (size3 < 128) {
                int size4 = size();
                int size5 = eVar.size();
                byte[] bArr = new byte[(size4 + size5)];
                q(0, 0, size4, bArr);
                eVar.q(0, size4, size5, bArr);
                return new x(bArr);
            }
            if (c5 != null) {
                C1255e eVar2 = c5.g;
                if (eVar.size() + eVar2.size() < 128) {
                    int size6 = eVar2.size();
                    int size7 = eVar.size();
                    byte[] bArr2 = new byte[(size6 + size7)];
                    eVar2.q(0, 0, size6, bArr2);
                    eVar.q(0, size6, size7, bArr2);
                    return new C(c5.f, new x(bArr2));
                }
            }
            if (c5 != null) {
                C1255e eVar3 = c5.g;
                C1255e eVar4 = c5.f;
                if (eVar4.s() > eVar3.s() && c5.f5051i > eVar.s()) {
                    return new C(eVar4, new C(eVar3, eVar));
                }
            }
            if (size3 >= C.k[Math.max(s(), eVar.s()) + 1]) {
                return new C(this, eVar);
            }
            c cVar = new c(17);
            cVar.j(this);
            cVar.j(eVar);
            Stack stack = (Stack) cVar.e;
            C1255e eVar5 = (C1255e) stack.pop();
            while (!stack.isEmpty()) {
                eVar5 = new C((C1255e) stack.pop(), eVar5);
            }
            return eVar5;
        }
        StringBuilder sb2 = new StringBuilder(53);
        sb2.append("ByteString would be too long: ");
        sb2.append(size);
        sb2.append("+");
        sb2.append(size2);
        throw new IllegalArgumentException(sb2.toString());
    }

    public final void q(int i2, int i7, int i8, byte[] bArr) {
        if (i2 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Source offset < 0: ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else if (i7 < 0) {
            StringBuilder sb3 = new StringBuilder(30);
            sb3.append("Target offset < 0: ");
            sb3.append(i7);
            throw new IndexOutOfBoundsException(sb3.toString());
        } else if (i8 >= 0) {
            int i10 = i2 + i8;
            if (i10 <= size()) {
                int i11 = i7 + i8;
                if (i11 > bArr.length) {
                    StringBuilder sb4 = new StringBuilder(34);
                    sb4.append("Target end offset < 0: ");
                    sb4.append(i11);
                    throw new IndexOutOfBoundsException(sb4.toString());
                } else if (i8 > 0) {
                    r(i2, i7, i8, bArr);
                }
            } else {
                StringBuilder sb5 = new StringBuilder(34);
                sb5.append("Source end offset < 0: ");
                sb5.append(i10);
                throw new IndexOutOfBoundsException(sb5.toString());
            }
        } else {
            StringBuilder sb6 = new StringBuilder(23);
            sb6.append("Length < 0: ");
            sb6.append(i8);
            throw new IndexOutOfBoundsException(sb6.toString());
        }
    }

    public abstract void r(int i2, int i7, int i8, byte[] bArr);

    public abstract int s();

    public abstract int size();

    public abstract boolean t();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    public abstract boolean u();

    public abstract int w(int i2, int i7, int i8);

    public abstract int x(int i2, int i7, int i8);

    public abstract int y();

    public abstract String z();
}
