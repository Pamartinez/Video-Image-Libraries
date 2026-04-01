package com.samsung.android.gallery.support.library.sef;

import android.util.Log;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TailData {
    int count;
    byte[] data;
    final ArrayList<Sdr> list = new ArrayList<>();
    int packetSize;
    int version;

    public int getVersion() {
        int i2 = this.version;
        if (i2 == 0) {
            return 106;
        }
        return i2;
    }

    public TailData load(RandomAccessFile randomAccessFile) {
        long length = randomAccessFile.length();
        byte[] bArr = new byte[8];
        randomAccessFile.seek(length - 8);
        if (randomAccessFile.read(bArr) != 8) {
            Log.e("SefParser", "load failed");
        }
        if (bArr[4] == 83 && bArr[5] == 69 && bArr[6] == 70 && bArr[7] == 84) {
            int i2 = BitOp.toInt(bArr, 0) + 8;
            this.packetSize = i2;
            byte[] bArr2 = new byte[i2];
            randomAccessFile.seek(length - ((long) i2));
            randomAccessFile.read(bArr2);
            if (bArr2[0] == 83 && bArr2[1] == 69 && bArr2[2] == 70 && bArr2[3] == 72) {
                this.data = bArr2;
            }
        }
        return this;
    }

    public TailData pack(ArrayList<SefData> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int sum = arrayList.stream().mapToInt(new g(1)).sum();
        Iterator<SefData> it = arrayList.iterator();
        while (it.hasNext()) {
            SefData next = it.next();
            arrayList2.add(new Sdr(next, sum).pack());
            sum -= next.packetSize;
        }
        int size = arrayList2.size() * 12;
        int i2 = size + 12;
        ByteBuffer allocate = ByteBuffer.allocate(size + 20);
        allocate.put(MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE.getBytes());
        allocate.putInt(BitOp.flipInt(getVersion()));
        allocate.putInt(BitOp.flipInt(arrayList2.size()));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            allocate.put(((Sdr) it2.next()).packet);
        }
        allocate.putInt(BitOp.flipInt(i2));
        allocate.put(MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE.getBytes());
        this.data = allocate.array();
        this.list.clear();
        this.list.addAll(arrayList2);
        return this;
    }

    public TailData unpack() {
        byte[] bArr = this.data;
        if (bArr != null) {
            this.version = BitOp.toInt(bArr, 4);
            this.count = BitOp.toInt(this.data, 8);
            for (int i2 = 0; i2 < this.count; i2++) {
                int i7 = i2 * 12;
                this.list.add(new Sdr(Arrays.copyOfRange(this.data, i7 + 12, i7 + 24)));
            }
        }
        return this;
    }
}
