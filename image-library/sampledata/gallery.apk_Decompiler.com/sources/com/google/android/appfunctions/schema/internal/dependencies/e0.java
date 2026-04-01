package com.google.android.appfunctions.schema.internal.dependencies;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.nio.charset.Charset;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e0 extends f0 {
    public final byte[] f;
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1223h;

    public e0(byte[] bArr, int i2, int i7) {
        f0.w(i2, i2 + i7, bArr.length);
        this.f = bArr;
        this.g = i2;
        this.f1223h = i7;
    }

    public final byte p(int i2) {
        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException;
        int i7 = this.f1223h;
        if (((i7 - (i2 + 1)) | i2) >= 0) {
            return this.f[this.g + i2];
        } else if (i2 < 0) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(i2).length() + 11);
            sb2.append("Index < 0: ");
            sb2.append(i2);
            String sb3 = sb2.toString();
            throw arrayIndexOutOfBoundsException;
        } else {
            StringBuilder sb4 = new StringBuilder(String.valueOf(i2).length() + 18 + String.valueOf(i7).length());
            sb4.append("Index > length: ");
            sb4.append(i2);
            sb4.append(ArcCommonLog.TAG_COMMA);
            sb4.append(i7);
            arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(sb4.toString());
            throw arrayIndexOutOfBoundsException;
        }
    }

    public final byte q(int i2) {
        return this.f[this.g + i2];
    }

    public final int r() {
        return this.f1223h;
    }

    public final f0 s(int i2, int i7) {
        int w = f0.w(i2, i7, this.f1223h);
        if (w == 0) {
            return f0.e;
        }
        return new e0(this.f, this.g + i2, w);
    }

    public final void t(h0 h0Var) {
        h0Var.d0(this.g, this.f1223h, this.f);
    }

    public final boolean u(f0 f0Var) {
        boolean z = f0Var instanceof g0;
        if (!z && !(f0Var instanceof e0)) {
            return f0Var.u(this);
        }
        int r = f0Var.r();
        int i2 = this.f1223h;
        if (i2 > r) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(i2).length() + 18 + String.valueOf(i2).length());
            sb2.append("Length too large: ");
            sb2.append(i2);
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (i2 <= f0Var.r()) {
            byte[] bArr = this.f;
            int i7 = this.g;
            if (z) {
                return f0.x(bArr, ((g0) f0Var).f, i7, 0, i2);
            }
            if (!(f0Var instanceof e0)) {
                return f0Var.s(0, i2).equals(s(i7, i2 + i7));
            }
            e0 e0Var = (e0) f0Var;
            return f0.x(bArr, e0Var.f, i7, e0Var.g, i2);
        } else {
            int r5 = f0Var.r();
            StringBuilder sb3 = new StringBuilder(String.valueOf(i2).length() + 27 + String.valueOf(r5).length());
            sb3.append("Ran off end of other: 0, ");
            sb3.append(i2);
            sb3.append(ArcCommonLog.TAG_COMMA);
            sb3.append(r5);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public final int v(int i2, int i7) {
        Charset charset = C0106p.f1226a;
        int i8 = this.g;
        for (int i10 = i8; i10 < i8 + i7; i10++) {
            i2 = (i2 * 31) + this.f[i10];
        }
        return i2;
    }
}
