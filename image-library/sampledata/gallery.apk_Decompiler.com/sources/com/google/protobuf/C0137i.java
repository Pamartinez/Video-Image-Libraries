package com.google.protobuf;

import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.appfunctions.schema.internal.dependencies.d0;
import java.nio.charset.Charset;
import java.util.Iterator;

/* renamed from: com.google.protobuf.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0137i extends ByteString {
    public final byte[] g;

    public C0137i(byte[] bArr) {
        this.d = 0;
        bArr.getClass();
        this.g = bArr;
    }

    public final void A(CodedOutputStream codedOutputStream) {
        codedOutputStream.X(B(), size(), this.g);
    }

    public int B() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString) || size() != ((ByteString) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof C0137i)) {
            return obj.equals(this);
        }
        C0137i iVar = (C0137i) obj;
        int i2 = this.d;
        int i7 = iVar.d;
        if (i2 != 0 && i7 != 0 && i2 != i7) {
            return false;
        }
        int size = size();
        if (size > iVar.size()) {
            throw new IllegalArgumentException("Length too large: " + size + size());
        } else if (size <= iVar.size()) {
            byte[] bArr = iVar.g;
            int B = B() + size;
            int B9 = B();
            int B10 = iVar.B();
            while (B9 < B) {
                if (this.g[B9] != bArr[B10]) {
                    return false;
                }
                B9++;
                B10++;
            }
            return true;
        } else {
            StringBuilder o2 = C0086a.o(size, "Ran off end of other: 0, ", ArcCommonLog.TAG_COMMA);
            o2.append(iVar.size());
            throw new IllegalArgumentException(o2.toString());
        }
    }

    public final Iterator iterator() {
        return new d0((ByteString) this);
    }

    public byte p(int i2) {
        return this.g[i2];
    }

    public int size() {
        return this.g.length;
    }

    public byte t(int i2) {
        return this.g[i2];
    }

    public final boolean u() {
        int B = B();
        return s0.f1629a.Q(B, size() + B, this.g);
    }

    public final CodedInputStream v() {
        return CodedInputStream.f(this.g, B(), size(), true);
    }

    public final int w(int i2, int i7) {
        int B = B();
        Charset charset = D.f1578a;
        for (int i8 = B; i8 < B + i7; i8++) {
            i2 = (i2 * 31) + this.g[i8];
        }
        return i2;
    }

    public final ByteString x(int i2) {
        int q = ByteString.q(0, i2, size());
        if (q == 0) {
            return ByteString.e;
        }
        return new C0136h(this.g, B(), q);
    }

    public final String y(Charset charset) {
        return new String(this.g, B(), size(), charset);
    }
}
