package com.samsung.o3dp.jpeg3dcontainer.model;

import android.util.Log;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.jpeg3dcontainer.exception.MpfException;
import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Mpf {
    public static final String IDENTIFIER = "MPF\u0000";
    private static final int JAVA_LONG_BYTES = 8;
    private static final int MPF_LONG_BYTES = 4;
    private static final byte[] MP_ENDIAN_BIG = {77, 77, 0, 42};
    private static final byte[] MP_ENDIAN_LITTLE = {73, 73, 42, 0};
    private static final String TAG = "Mpf";
    private final int count;
    private final byte[] data;
    private final ByteOrder endian;
    private final int imageCount;
    private final List<MpEntry> mpEntries = new ArrayList();
    private IndexIFDTag mpEntry;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IndexIFDTag {
        public static final int ID_IMAGE_UID_LIST = 45059;
        public static final int ID_MPF_VERSION = 45056;
        public static final int ID_MP_ENTRY = 45058;
        public static final int ID_NUMBER_OF_IMAGES = 45057;
        public static final int ID_TOTAL_FRAMES = 45060;
        public static final int NUM_BYTES = 12;
        public int count;
        public int id;
        public int type;
        public int value;

        public IndexIFDTag(ByteBuffer byteBuffer) {
            this.id = byteBuffer.getShort() & 65535;
            this.type = byteBuffer.getShort() & 65535;
            this.count = byteBuffer.getInt();
            this.value = byteBuffer.getInt();
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("IFD-TAG[");
            sb2.append(this.id);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.type);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.count);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0086a.l(sb2, this.value, "]");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MpEntry {
        public int attribute;
        int dataOffset;
        public int entryNumber1;
        public int entryNumber2;
        public long imageOffset;
        public long imageSize;
        public int imageType;

        public String toString() {
            return "Entry[" + this.imageSize + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.imageOffset + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.entryNumber1 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.entryNumber2 + Integer.toHexString(this.imageType) + "]";
        }
    }

    public Mpf(byte[] bArr) {
        int i2;
        this.data = bArr;
        ByteOrder endian2 = getEndian(bArr, 4, 4);
        this.endian = endian2;
        if (endian2 != null) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr, 8, bArr.length - 8);
            wrap.order(endian2);
            int i7 = wrap.getInt();
            if (i7 > 8) {
                wrap.position(i7 - 8);
            }
            this.count = wrap.getShort();
            for (int i8 = 0; i8 < this.count; i8++) {
                byte[] bArr2 = new byte[12];
                wrap.get(bArr2, 0, 12);
                IndexIFDTag indexIFDTag = new IndexIFDTag(ByteBuffer.wrap(bArr2).order(this.endian));
                if (indexIFDTag.id == 45058) {
                    this.mpEntry = indexIFDTag;
                }
            }
            int i10 = wrap.getInt();
            if (i10 > 0) {
                wrap.position(wrap.position() + i10);
            }
            IndexIFDTag indexIFDTag2 = this.mpEntry;
            if (indexIFDTag2 != null) {
                i2 = indexIFDTag2.count / 16;
            } else {
                i2 = 0;
            }
            this.imageCount = i2;
            for (int i11 = 0; i11 < this.imageCount; i11++) {
                MpEntry mpEntry2 = new MpEntry();
                mpEntry2.dataOffset = wrap.position();
                int i12 = wrap.getInt();
                mpEntry2.attribute = i12;
                mpEntry2.imageType = i12 & 16777215;
                mpEntry2.imageSize = ((long) wrap.getInt()) & 4294967295L;
                mpEntry2.imageOffset = ((long) wrap.getInt()) & 4294967295L;
                mpEntry2.entryNumber1 = wrap.getShort() & 65535;
                mpEntry2.entryNumber2 = wrap.getShort() & 65535;
                this.mpEntries.add(mpEntry2);
            }
            return;
        }
        Log.e(TAG, "construct failed. unknown endian");
        throw new MpfException("endian equals null.");
    }

    private ByteOrder getEndian(byte[] bArr, int i2, int i7) {
        if (ByteUtil.compare(bArr, i2, i7, MP_ENDIAN_LITTLE)) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (ByteUtil.compare(bArr, i2, i7, MP_ENDIAN_BIG)) {
            return ByteOrder.BIG_ENDIAN;
        }
        return null;
    }

    public void applyDeltaToFirstImageSize(int i2) {
        MpEntry mpEntry2 = this.mpEntries.get(0);
        long j2 = mpEntry2.imageSize + ((long) i2);
        mpEntry2.imageSize = j2;
        ByteUtil.putLongAsUInt(j2, this.data, mpEntry2.dataOffset + 4, this.endian);
    }

    public void applyDeltaToFollowingImageOffsets(int i2) {
        for (int i7 = 1; i7 < this.imageCount; i7++) {
            MpEntry mpEntry2 = this.mpEntries.get(i7);
            long j2 = mpEntry2.imageOffset + ((long) i2);
            mpEntry2.imageOffset = j2;
            ByteUtil.putLongAsUInt(j2, this.data, mpEntry2.dataOffset + 8, this.endian);
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public List<MpEntry> getEntries() {
        return this.mpEntries;
    }

    public String toString() {
        return "MPF[" + this.endian + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.count + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mpEntries + "]";
    }
}
