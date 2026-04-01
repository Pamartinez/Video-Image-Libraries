package com.samsung.android.gallery.support.utils;

import android.util.LongSparseArray;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedByteBufferPool {
    private final Object LOCK = new Object();
    private final LongSparseArray<SoftReference<byte[]>> mByteBufferPool = new LongSparseArray<>();
    private final int mDefaultSize;

    public SharedByteBufferPool(int i2) {
        this.mDefaultSize = i2;
    }

    public void clear() {
        synchronized (this.LOCK) {
            values().stream().filter(new C0680s(9)).forEach(new O(2));
            this.mByteBufferPool.clear();
        }
    }

    public byte[] getByteBuffer(int i2) {
        byte[] bArr;
        synchronized (this.LOCK) {
            try {
                long id = Thread.currentThread().getId();
                SoftReference softReference = this.mByteBufferPool.get(id);
                if (softReference == null) {
                    bArr = null;
                } else {
                    bArr = (byte[]) softReference.get();
                }
                if (bArr != null) {
                    if (bArr.length < i2) {
                    }
                }
                if (softReference != null) {
                    softReference.clear();
                    this.mByteBufferPool.delete(id);
                }
                bArr = new byte[Math.max(i2, this.mDefaultSize)];
                this.mByteBufferPool.put(id, new SoftReference(bArr));
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArr;
    }

    public String toDebugString() {
        byte[] bArr;
        int i2;
        ArrayList arrayList = new ArrayList();
        synchronized (this.LOCK) {
            int i7 = 0;
            while (i7 < this.mByteBufferPool.size()) {
                try {
                    SoftReference valueAt = this.mByteBufferPool.valueAt(i7);
                    if (valueAt == null) {
                        bArr = null;
                    } else {
                        bArr = (byte[]) valueAt.get();
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.mByteBufferPool.keyAt(i7));
                    sb2.append("=");
                    if (bArr == null) {
                        i2 = -1;
                    } else {
                        i2 = bArr.length;
                    }
                    sb2.append(i2);
                    arrayList.add(sb2.toString());
                    i7++;
                } finally {
                    while (true) {
                    }
                }
            }
        }
        return "SharedByteBufferPool{" + this.mDefaultSize + GlobalPostProcInternalPPInterface.SPLIT_REGEX + arrayList.size() + ",[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "]}";
    }

    public ArrayList<SoftReference<byte[]>> values() {
        ArrayList<SoftReference<byte[]>> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.mByteBufferPool.size(); i2++) {
            arrayList.add(this.mByteBufferPool.valueAt(i2));
        }
        return arrayList;
    }
}
