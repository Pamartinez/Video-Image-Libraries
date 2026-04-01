package com.samsung.android.gallery.support.library.sef;

import N2.j;
import android.util.Log;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SefData {
    byte[] data;
    long dataOffset;
    Drdh drdh;
    byte[] dsi;
    int dsiLen;
    long fileOffset;
    String name;
    byte[] packet;
    int packetSize;

    public SefData(long j2, int i2) {
        this.fileOffset = j2;
        this.packetSize = i2;
    }

    public long[] getDataPosition() {
        long j2 = this.fileOffset;
        long j3 = this.dataOffset;
        return new long[]{j2 + j3, ((long) this.packetSize) - j3};
    }

    public SefData load(RandomAccessFile randomAccessFile) {
        byte[] bArr = new byte[8];
        randomAccessFile.seek(this.fileOffset);
        if (randomAccessFile.read(bArr) != 8) {
            Log.e("SefParser", "load failed");
        }
        int i2 = 0;
        this.drdh = new Drdh(BitOp.toInt(bArr, 0));
        int i7 = BitOp.toInt(bArr, 4);
        if (this.drdh.option > 0) {
            this.dsiLen = BitOp.flipInt(randomAccessFile.readInt());
        }
        int i8 = i7 + 8;
        int i10 = this.dsiLen;
        if (i10 > 0) {
            i2 = i10 + 4;
        }
        this.dataOffset = (long) (i8 + i2);
        byte[] bArr2 = new byte[i7];
        randomAccessFile.read(bArr2);
        this.name = new String(bArr2);
        return this;
    }

    public SefData pack() {
        int i2;
        byte[] bArr = this.data;
        if (bArr != null && bArr.length > 0) {
            byte[] bytes = this.name.getBytes();
            int length = bytes.length + 8 + this.data.length;
            byte[] bArr2 = this.dsi;
            if (bArr2 != null) {
                i2 = bArr2.length + 4;
            } else {
                i2 = 0;
            }
            ByteBuffer allocate = ByteBuffer.allocate(length + i2);
            allocate.putInt(BitOp.flipInt(this.drdh.value));
            allocate.putInt(BitOp.flipInt(bytes.length));
            byte[] bArr3 = this.dsi;
            if (bArr3 != null) {
                allocate.putInt(BitOp.flipInt(bArr3.length));
            }
            allocate.put(bytes);
            byte[] bArr4 = this.dsi;
            if (bArr4 != null) {
                allocate.put(bArr4);
            }
            allocate.put(this.data);
            this.fileOffset = 0;
            byte[] array = allocate.array();
            this.packet = array;
            this.packetSize = array.length;
        }
        return this;
    }

    public String toString() {
        int i2;
        StringBuilder sb2 = new StringBuilder("SEF{");
        sb2.append(this.name);
        sb2.append(',');
        sb2.append(this.drdh.dataType);
        sb2.append(',');
        sb2.append(this.fileOffset);
        sb2.append(',');
        sb2.append(this.dataOffset);
        sb2.append(',');
        byte[] bArr = this.data;
        if (bArr != null) {
            i2 = bArr.length;
        } else {
            i2 = -1;
        }
        sb2.append(i2);
        sb2.append(',');
        return j.e(sb2, this.packetSize, '}');
    }

    public SefData(String str, int i2, byte[] bArr) {
        this.name = str;
        this.data = bArr;
        this.drdh = new Drdh(i2, 0);
    }

    public SefData(long j2, byte[] bArr) {
        this.fileOffset = j2;
        this.packetSize = bArr.length;
        this.drdh = new Drdh(BitOp.toInt(bArr, 0));
        int i2 = BitOp.toInt(bArr, 4);
        int i7 = 8;
        if (this.drdh.option > 0) {
            this.dsiLen = BitOp.toInt(bArr, 8);
            i7 = 12;
        }
        int i8 = i2 + i7;
        this.name = new String(Arrays.copyOfRange(bArr, i7, i8));
        int i10 = this.dsiLen;
        if (i10 > 0) {
            this.dsi = Arrays.copyOfRange(bArr, i8, i10 + i8);
        }
        int i11 = i8 + this.dsiLen;
        this.dataOffset = (long) i11;
        this.data = Arrays.copyOfRange(bArr, i11, bArr.length);
    }
}
