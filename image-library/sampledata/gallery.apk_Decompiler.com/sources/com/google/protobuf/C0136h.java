package com.google.protobuf;

import N2.j;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* renamed from: com.google.protobuf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0136h extends C0137i {

    /* renamed from: h  reason: collision with root package name */
    public final int f1608h;

    /* renamed from: i  reason: collision with root package name */
    public final int f1609i;

    public C0136h(byte[] bArr, int i2, int i7) {
        super(bArr);
        ByteString.q(i2, i2 + i7, bArr.length);
        this.f1608h = i2;
        this.f1609i = i7;
    }

    public final int B() {
        return this.f1608h;
    }

    public final byte p(int i2) {
        int i7 = this.f1609i;
        if (((i7 - (i2 + 1)) | i2) >= 0) {
            return this.g[this.f1608h + i2];
        } else if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(C0086a.i(i2, "Index < 0: "));
        } else {
            throw new ArrayIndexOutOfBoundsException(j.b(i2, i7, "Index > length: ", ArcCommonLog.TAG_COMMA));
        }
    }

    public final int size() {
        return this.f1609i;
    }

    public final byte t(int i2) {
        return this.g[this.f1608h + i2];
    }
}
