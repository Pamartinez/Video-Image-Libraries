package com.samsung.android.gallery.support.cache;

import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BytesBuffer {
    public byte[] data;
    public int length;
    public int offset;

    public BytesBuffer(int i2) {
        if (i2 > 0) {
            this.data = new byte[i2];
        }
        this.length = i2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BytesBuffer{");
        sb2.append(this.offset);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.length, "}");
    }

    public BytesBuffer(byte[] bArr, int i2, int i7) {
        this.data = bArr;
        this.offset = i2;
        this.length = i7;
    }

    public BytesBuffer() {
        this(0);
    }
}
