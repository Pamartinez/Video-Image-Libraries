package com.google.android.appfunctions.schema.internal.dependencies;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.nio.charset.Charset;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g0 extends f0 {
    public final byte[] f;

    public g0(byte[] bArr) {
        bArr.getClass();
        this.f = bArr;
    }

    public final byte p(int i2) {
        return this.f[i2];
    }

    public final byte q(int i2) {
        return this.f[i2];
    }

    public final int r() {
        return this.f.length;
    }

    public final f0 s(int i2, int i7) {
        byte[] bArr = this.f;
        int w = f0.w(0, i7, bArr.length);
        if (w == 0) {
            return f0.e;
        }
        return new e0(bArr, 0, w);
    }

    public final void t(h0 h0Var) {
        byte[] bArr = this.f;
        h0Var.d0(0, bArr.length, bArr);
    }

    public final boolean u(f0 f0Var) {
        boolean z = f0Var instanceof g0;
        byte[] bArr = this.f;
        if (z) {
            return Arrays.equals(bArr, ((g0) f0Var).f);
        }
        if (!(f0Var instanceof e0)) {
            return f0Var.u(this);
        }
        e0 e0Var = (e0) f0Var;
        int i2 = e0Var.f1223h;
        int length = bArr.length;
        if (length > i2) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(length).length() + 18 + String.valueOf(length).length());
            sb2.append("Length too large: ");
            sb2.append(length);
            sb2.append(length);
            throw new IllegalArgumentException(sb2.toString());
        } else if (length <= i2) {
            return f0.x(bArr, e0Var.f, 0, e0Var.g, length);
        } else {
            StringBuilder sb3 = new StringBuilder(String.valueOf(length).length() + 27 + String.valueOf(i2).length());
            sb3.append("Ran off end of other: 0, ");
            sb3.append(length);
            sb3.append(ArcCommonLog.TAG_COMMA);
            sb3.append(i2);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public final int v(int i2, int i7) {
        Charset charset = C0106p.f1226a;
        for (int i8 = 0; i8 < i7; i8++) {
            i2 = (i2 * 31) + this.f[i8];
        }
        return i2;
    }
}
