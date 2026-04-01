package com.google.android.appfunctions.schema.internal.dependencies;

import L1.d;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f0 implements Iterable, Serializable {
    public static final g0 e = new g0(C0106p.b);
    public int d = 0;

    static {
        Class cls = b0.f1220a;
    }

    public static int w(int i2, int i7, int i8) {
        int i10 = i7 - i2;
        if ((i2 | i7 | i10 | (i8 - i7)) >= 0) {
            return i10;
        }
        if (i2 < 0) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(i2).length() + 21);
            sb2.append("Beginning index: ");
            sb2.append(i2);
            sb2.append(" < 0");
            throw new IndexOutOfBoundsException(sb2.toString());
        } else if (i7 < i2) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(i2).length() + 44 + String.valueOf(i7).length());
            sb3.append("Beginning index larger than ending index: ");
            sb3.append(i2);
            sb3.append(ArcCommonLog.TAG_COMMA);
            sb3.append(i7);
            throw new IndexOutOfBoundsException(sb3.toString());
        } else {
            StringBuilder sb4 = new StringBuilder(String.valueOf(i7).length() + 15 + String.valueOf(i8).length());
            sb4.append("End index: ");
            sb4.append(i7);
            sb4.append(" >= ");
            sb4.append(i8);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
    }

    public static /* synthetic */ boolean x(byte[] bArr, byte[] bArr2, int i2, int i7, int i8) {
        int i10 = i2 + i8;
        w(i2, i10, bArr.length);
        w(i7, i8 + i7, bArr2.length);
        while (i2 < i10) {
            if (bArr[i2] != bArr2[i7]) {
                return false;
            }
            i2++;
            i7++;
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        int r = r();
        if (r != f0Var.r()) {
            return false;
        }
        if (r == 0) {
            return true;
        }
        int i2 = this.d;
        int i7 = f0Var.d;
        if (i2 == 0 || i7 == 0 || i2 == i7) {
            return u(f0Var);
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.d;
        if (i2 == 0) {
            int r = r();
            i2 = v(r, r);
            if (i2 == 0) {
                i2 = 1;
            }
            this.d = i2;
        }
        return i2;
    }

    public final /* synthetic */ Iterator iterator() {
        return new d0(this);
    }

    public abstract byte p(int i2);

    public abstract byte q(int i2);

    public abstract int r();

    public abstract f0 s(int i2, int i7);

    public abstract void t(h0 h0Var);

    public final String toString() {
        String str;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int r = r();
        if (r() <= 50) {
            str = d.A(this);
        } else {
            str = d.A(s(0, 47)).concat("...");
        }
        return C0212a.p(C0212a.u("<ByteString@", hexString, " size=", r, " contents=\""), str, "\">");
    }

    public abstract boolean u(f0 f0Var);

    public abstract int v(int i2, int i7);
}
