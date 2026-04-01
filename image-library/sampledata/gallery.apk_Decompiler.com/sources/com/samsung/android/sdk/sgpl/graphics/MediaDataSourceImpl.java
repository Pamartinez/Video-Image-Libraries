package com.samsung.android.sdk.sgpl.graphics;

import android.media.MediaDataSource;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSourceImpl extends MediaDataSource {
    final byte[] data;

    public MediaDataSourceImpl(byte[] bArr) {
        this.data = bArr;
    }

    public long getSize() {
        return (long) this.data.length;
    }

    public int readAt(long j2, byte[] bArr, int i2, int i7) {
        byte[] bArr2 = this.data;
        if (((long) i7) + j2 > ((long) bArr2.length)) {
            i7 = bArr2.length - ((int) j2);
        }
        System.arraycopy(bArr2, (int) j2, bArr, i2, i7);
        return i7;
    }

    public void close() {
    }
}
