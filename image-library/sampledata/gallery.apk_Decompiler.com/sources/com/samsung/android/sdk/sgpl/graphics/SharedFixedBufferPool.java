package com.samsung.android.sdk.sgpl.graphics;

import android.util.LongSparseArray;
import com.samsung.android.gallery.support.utils.C0680s;
import com.samsung.android.gallery.support.utils.O;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharedFixedBufferPool {
    private final Object LOCK = new Object();
    private final int SIZE;
    private final LongSparseArray<SoftReference<byte[]>> pool = new LongSparseArray<>();

    public SharedFixedBufferPool(int i2) {
        this.SIZE = i2;
    }

    public void clear() {
        ArrayList<SoftReference<byte[]>> values;
        synchronized (this.LOCK) {
            values = values();
            this.pool.clear();
        }
        values.stream().filter(new C0680s(9)).forEach(new O(2));
    }

    public byte[] peek() {
        byte[] bArr;
        synchronized (this.LOCK) {
            try {
                long id = Thread.currentThread().getId();
                SoftReference softReference = this.pool.get(id);
                if (softReference == null) {
                    bArr = null;
                } else {
                    bArr = (byte[]) softReference.get();
                }
                if (bArr == null) {
                    if (softReference != null) {
                        softReference.clear();
                        this.pool.delete(id);
                    }
                    bArr = new byte[this.SIZE];
                    this.pool.put(id, new SoftReference(bArr));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArr;
    }

    public ArrayList<SoftReference<byte[]>> values() {
        ArrayList<SoftReference<byte[]>> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.pool.size(); i2++) {
            arrayList.add(this.pool.valueAt(i2));
        }
        return arrayList;
    }
}
