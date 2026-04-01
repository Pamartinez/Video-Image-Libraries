package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCache {
    private final Object LOCK = new Object();
    int hash;
    long id;
    private volatile Packet packet;
    byte[] stream;
    private boolean updated;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Packet {
        final byte[] data = new byte[1];
        final byte[] key = new byte[1];
    }

    public MediaCache(long j2) {
        this.id = j2;
    }

    public boolean contains(int i2) {
        if (this.packet != null) {
            if ((this.packet.key[i2 / 8] & (1 << (i2 % 8))) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnabled(int i2) {
        if (this.packet != null) {
            if ((this.packet.data[i2 / 8] & (1 << (i2 % 8))) > 0) {
                return true;
            }
        }
        return false;
    }

    public MediaCache pack() {
        if (this.stream == null) {
            this.stream = repack();
        }
        return this;
    }

    public byte[] repack() {
        ByteBuffer allocate = ByteBuffer.allocate(7);
        allocate.putInt(this.hash);
        allocate.put((byte) 1);
        allocate.put(this.packet.key);
        allocate.put(this.packet.data);
        return allocate.array();
    }

    public MediaCache setHash(int i2) {
        int i7 = this.hash;
        if (!(i7 == i2 || i7 == 0)) {
            this.packet = null;
            this.stream = null;
        }
        this.hash = i2;
        return this;
    }

    public String toString() {
        String str;
        String str2;
        Packet packet2 = this.packet;
        StringBuilder sb2 = new StringBuilder("mc[");
        sb2.append(this.id);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(Integer.toHexString(this.hash));
        if (this.updated) {
            str = ",U";
        } else {
            str = ",u";
        }
        sb2.append(str);
        if (packet2 != null) {
            str2 = ",k=" + StringCompat.valueOf(packet2.key) + ",d=" + StringCompat.valueOf(packet2.data);
        } else {
            str2 = ",null";
        }
        return C0212a.p(sb2, str2, "]");
    }

    public MediaCache unpack(byte[] bArr) {
        if (bArr != null && bArr.length >= 7) {
            this.hash = (bArr[0] << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
            this.packet = new Packet();
            this.stream = bArr;
            byte b = bArr[4] & 15;
            int min = Math.min(b, 1);
            System.arraycopy(bArr, 5, this.packet.key, 0, min);
            System.arraycopy(bArr, b + 5, this.packet.data, 0, min);
            if (b != 1) {
                this.updated = true;
                this.stream = null;
            }
        }
        return this;
    }

    public void update(int i2, boolean z) {
        update(i2, z, true);
    }

    public void update(int i2, boolean z, boolean z3) {
        if (this.packet == null) {
            synchronized (this.LOCK) {
                try {
                    if (this.packet == null) {
                        this.packet = new Packet();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        int i7 = i2 / 8;
        int i8 = 1 << (i2 % 8);
        if (z3) {
            byte[] bArr = this.packet.key;
            bArr[i7] = (byte) (bArr[i7] | ((byte) i8));
        }
        if (z) {
            byte[] bArr2 = this.packet.data;
            bArr2[i7] = (byte) (((byte) i8) | bArr2[i7]);
        } else {
            byte[] bArr3 = this.packet.data;
            bArr3[i7] = (byte) (((byte) (~i8)) & bArr3[i7]);
        }
        this.updated = true;
        this.stream = null;
    }
}
