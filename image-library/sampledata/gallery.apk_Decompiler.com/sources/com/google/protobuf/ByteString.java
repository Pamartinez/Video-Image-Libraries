package com.google.protobuf;

import B1.a;
import N2.j;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.appfunctions.schema.internal.dependencies.d0;
import i.C0212a;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ByteString implements Iterable, Serializable {
    public static final C0137i e = new C0137i(D.b);
    public static final C0135g f;
    public int d;

    static {
        C0135g gVar;
        if (C0132d.a()) {
            gVar = new C0135g(1);
        } else {
            gVar = new C0135g(0);
        }
        f = gVar;
    }

    public static int q(int i2, int i7, int i8) {
        int i10 = i7 - i2;
        if ((i2 | i7 | i10 | (i8 - i7)) >= 0) {
            return i10;
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException(C0212a.j(i2, "Beginning index: ", " < 0"));
        } else if (i7 < i2) {
            throw new IndexOutOfBoundsException(j.b(i2, i7, "Beginning index larger than ending index: ", ArcCommonLog.TAG_COMMA));
        } else {
            throw new IndexOutOfBoundsException(j.b(i7, i8, "End index: ", " >= "));
        }
    }

    public static C0137i r(int i2, int i7, byte[] bArr) {
        byte[] bArr2;
        q(i2, i2 + i7, bArr.length);
        switch (f.f1605a) {
            case 0:
                bArr2 = Arrays.copyOfRange(bArr, i2, i7 + i2);
                break;
            default:
                bArr2 = new byte[i7];
                System.arraycopy(bArr, i2, bArr2, 0, i7);
                break;
        }
        return new C0137i(bArr2);
    }

    public static C0137i s(String str) {
        return new C0137i(str.getBytes(D.f1578a));
    }

    public abstract void A(CodedOutputStream codedOutputStream);

    public final int hashCode() {
        int i2 = this.d;
        if (i2 == 0) {
            int size = size();
            i2 = w(size, size);
            if (i2 == 0) {
                i2 = 1;
            }
            this.d = i2;
        }
        return i2;
    }

    public Iterator iterator() {
        return new d0(this);
    }

    public abstract byte p(int i2);

    public abstract int size();

    public abstract byte t(int i2);

    public final String toString() {
        String str;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int size = size();
        if (size() <= 50) {
            str = a.t(this);
        } else {
            str = a.t(x(47)) + "...";
        }
        return C0212a.p(C0212a.u("<ByteString@", hexString, " size=", size, " contents=\""), str, "\">");
    }

    public abstract boolean u();

    public abstract CodedInputStream v();

    public abstract int w(int i2, int i7);

    public abstract ByteString x(int i2);

    public abstract String y(Charset charset);

    public final String z() {
        Charset charset = D.f1578a;
        if (size() == 0) {
            return "";
        }
        return y(charset);
    }
}
