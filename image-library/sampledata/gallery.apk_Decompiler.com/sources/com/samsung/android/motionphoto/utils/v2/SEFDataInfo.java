package com.samsung.android.motionphoto.utils.v2;

import Tf.a;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0014\b\b\u0018\u0000 M2\u00020\u0001:\u0001MB\u0001\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\b\u00107\u001a\u00020\u0005H\u0016J\u0006\u00108\u001a\u00020\u0003J\u0006\u00109\u001a\u00020:J\u0006\u0010;\u001a\u00020\u0003J\u0006\u0010<\u001a\u00020\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0007HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0010HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0010HÆ\u0003J\u0001\u0010I\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0010HÆ\u0001J\u0013\u0010J\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010 R\u001a\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010 R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010\u000e\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0017\"\u0004\b,\u0010 R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R\u001a\u0010\u0012\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010 R\u001a\u0010\u0013\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010.\"\u0004\b6\u00100¨\u0006N"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo;", "", "type", "", "name", "", "hasSubInfo", "", "offsetFromTail", "payload", "data", "", "dataFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "dataPayload", "dataPosition", "", "subInfo", "subInfoPayload", "subInfoPosition", "<init>", "(ILjava/lang/String;ZII[BLcom/samsung/android/motionphoto/utils/v2/MediaFile;IJ[BIJ)V", "getType", "()I", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getHasSubInfo", "()Z", "getOffsetFromTail", "setOffsetFromTail", "(I)V", "getPayload", "setPayload", "getData", "()[B", "setData", "([B)V", "getDataFile", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "setDataFile", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "getDataPayload", "setDataPayload", "getDataPosition", "()J", "setDataPosition", "(J)V", "getSubInfo", "setSubInfo", "getSubInfoPayload", "setSubInfoPayload", "getSubInfoPosition", "setSubInfoPosition", "toString", "size", "toByteBuffer", "Ljava/nio/ByteBuffer;", "getSubInfoOffset", "getDataHeader", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SEFDataInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private byte[] data;
    private MediaFile dataFile;
    private int dataPayload;
    private long dataPosition;
    private final boolean hasSubInfo;
    private String name;
    private int offsetFromTail;
    private int payload;
    private byte[] subInfo;
    private int subInfoPayload;
    private long subInfoPosition;
    private final int type;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(SEFDataInfo.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public SEFDataInfo(int i2, String str, boolean z, int i7, int i8, byte[] bArr, MediaFile mediaFile, int i10, long j2, byte[] bArr2, int i11, long j3) {
        j.e(str, "name");
        this.type = i2;
        this.name = str;
        this.hasSubInfo = z;
        this.offsetFromTail = i7;
        this.payload = i8;
        this.data = bArr;
        this.dataFile = mediaFile;
        this.dataPayload = i10;
        this.dataPosition = j2;
        this.subInfo = bArr2;
        this.subInfoPayload = i11;
        this.subInfoPosition = j3;
        this.dataPayload = bArr != null ? bArr.length : mediaFile != null ? (int) mediaFile.size() : 0;
    }

    public static /* synthetic */ SEFDataInfo copy$default(SEFDataInfo sEFDataInfo, int i2, String str, boolean z, int i7, int i8, byte[] bArr, MediaFile mediaFile, int i10, long j2, byte[] bArr2, int i11, long j3, int i12, Object obj) {
        SEFDataInfo sEFDataInfo2 = sEFDataInfo;
        int i13 = i12;
        return sEFDataInfo2.copy((i13 & 1) != 0 ? sEFDataInfo2.type : i2, (i13 & 2) != 0 ? sEFDataInfo2.name : str, (i13 & 4) != 0 ? sEFDataInfo2.hasSubInfo : z, (i13 & 8) != 0 ? sEFDataInfo2.offsetFromTail : i7, (i13 & 16) != 0 ? sEFDataInfo2.payload : i8, (i13 & 32) != 0 ? sEFDataInfo2.data : bArr, (i13 & 64) != 0 ? sEFDataInfo2.dataFile : mediaFile, (i13 & 128) != 0 ? sEFDataInfo2.dataPayload : i10, (i13 & 256) != 0 ? sEFDataInfo2.dataPosition : j2, (i13 & 512) != 0 ? sEFDataInfo2.subInfo : bArr2, (i13 & 1024) != 0 ? sEFDataInfo2.subInfoPayload : i11, (i13 & 2048) != 0 ? sEFDataInfo2.subInfoPosition : j3);
    }

    public final int component1() {
        return this.type;
    }

    public final byte[] component10() {
        return this.subInfo;
    }

    public final int component11() {
        return this.subInfoPayload;
    }

    public final long component12() {
        return this.subInfoPosition;
    }

    public final String component2() {
        return this.name;
    }

    public final boolean component3() {
        return this.hasSubInfo;
    }

    public final int component4() {
        return this.offsetFromTail;
    }

    public final int component5() {
        return this.payload;
    }

    public final byte[] component6() {
        return this.data;
    }

    public final MediaFile component7() {
        return this.dataFile;
    }

    public final int component8() {
        return this.dataPayload;
    }

    public final long component9() {
        return this.dataPosition;
    }

    public final SEFDataInfo copy(int i2, String str, boolean z, int i7, int i8, byte[] bArr, MediaFile mediaFile, int i10, long j2, byte[] bArr2, int i11, long j3) {
        String str2 = str;
        j.e(str2, "name");
        return new SEFDataInfo(i2, str2, z, i7, i8, bArr, mediaFile, i10, j2, bArr2, i11, j3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SEFDataInfo)) {
            return false;
        }
        SEFDataInfo sEFDataInfo = (SEFDataInfo) obj;
        if (this.type == sEFDataInfo.type && j.a(this.name, sEFDataInfo.name) && this.hasSubInfo == sEFDataInfo.hasSubInfo && this.offsetFromTail == sEFDataInfo.offsetFromTail && this.payload == sEFDataInfo.payload && j.a(this.data, sEFDataInfo.data) && j.a(this.dataFile, sEFDataInfo.dataFile) && this.dataPayload == sEFDataInfo.dataPayload && this.dataPosition == sEFDataInfo.dataPosition && j.a(this.subInfo, sEFDataInfo.subInfo) && this.subInfoPayload == sEFDataInfo.subInfoPayload && this.subInfoPosition == sEFDataInfo.subInfoPosition) {
            return true;
        }
        return false;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final MediaFile getDataFile() {
        return this.dataFile;
    }

    public final int getDataHeader() {
        return this.hasSubInfo | (this.type << 16) ? 1 : 0;
    }

    public final int getDataPayload() {
        return this.dataPayload;
    }

    public final long getDataPosition() {
        return this.dataPosition;
    }

    public final boolean getHasSubInfo() {
        return this.hasSubInfo;
    }

    public final String getName() {
        return this.name;
    }

    public final int getOffsetFromTail() {
        return this.offsetFromTail;
    }

    public final int getPayload() {
        return this.payload;
    }

    public final byte[] getSubInfo() {
        return this.subInfo;
    }

    public final int getSubInfoOffset() {
        if (this.hasSubInfo) {
            return this.name.length() + 12;
        }
        return 0;
    }

    public final int getSubInfoPayload() {
        return this.subInfoPayload;
    }

    public final long getSubInfoPosition() {
        return this.subInfoPosition;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        int i2;
        int i7;
        int b = C0212a.b(this.payload, C0212a.b(this.offsetFromTail, C0212a.e(C0212a.d(Integer.hashCode(this.type) * 31, 31, this.name), 31, this.hasSubInfo), 31), 31);
        byte[] bArr = this.data;
        int i8 = 0;
        if (bArr == null) {
            i2 = 0;
        } else {
            i2 = Arrays.hashCode(bArr);
        }
        int i10 = (b + i2) * 31;
        MediaFile mediaFile = this.dataFile;
        if (mediaFile == null) {
            i7 = 0;
        } else {
            i7 = mediaFile.hashCode();
        }
        int c5 = C0212a.c(C0212a.b(this.dataPayload, (i10 + i7) * 31, 31), 31, this.dataPosition);
        byte[] bArr2 = this.subInfo;
        if (bArr2 != null) {
            i8 = Arrays.hashCode(bArr2);
        }
        return Long.hashCode(this.subInfoPosition) + C0212a.b(this.subInfoPayload, (c5 + i8) * 31, 31);
    }

    public final void setData(byte[] bArr) {
        this.data = bArr;
    }

    public final void setDataFile(MediaFile mediaFile) {
        this.dataFile = mediaFile;
    }

    public final void setDataPayload(int i2) {
        this.dataPayload = i2;
    }

    public final void setDataPosition(long j2) {
        this.dataPosition = j2;
    }

    public final void setName(String str) {
        j.e(str, "<set-?>");
        this.name = str;
    }

    public final void setOffsetFromTail(int i2) {
        this.offsetFromTail = i2;
    }

    public final void setPayload(int i2) {
        this.payload = i2;
    }

    public final void setSubInfo(byte[] bArr) {
        this.subInfo = bArr;
    }

    public final void setSubInfoPayload(int i2) {
        this.subInfoPayload = i2;
    }

    public final void setSubInfoPosition(long j2) {
        this.subInfoPosition = j2;
    }

    public final int size() {
        int i2;
        if (this.hasSubInfo) {
            i2 = 12 + this.subInfoPayload;
        } else {
            i2 = 8;
        }
        return this.name.length() + i2 + this.dataPayload;
    }

    public final ByteBuffer toByteBuffer() {
        int i2;
        if (this.data != null) {
            i2 = size();
        } else if (this.dataFile != null) {
            i2 = size() - this.dataPayload;
        } else {
            throw new IllegalStateException();
        }
        ByteBuffer order = ByteBuffer.allocate(i2).order(SEFInfoImpl.Companion.getByteOrder$motionphoto_utils_v2_0_17_release());
        order.putInt(getDataHeader());
        order.putInt(this.name.length());
        if (this.hasSubInfo) {
            order.putInt(this.subInfoPayload);
        }
        byte[] bytes = this.name.getBytes(a.f3815a);
        j.d(bytes, "getBytes(...)");
        order.put(bytes);
        if (this.hasSubInfo) {
            byte[] bArr = this.subInfo;
            j.b(bArr);
            order.put(bArr);
        }
        byte[] bArr2 = this.data;
        if (bArr2 != null) {
            order.put(bArr2);
        }
        order.rewind();
        return order;
    }

    public String toString() {
        int i2 = this.type;
        String str = this.name;
        int i7 = this.offsetFromTail;
        int i8 = this.payload;
        int i10 = this.dataPayload;
        byte[] bArr = this.data;
        MediaFile mediaFile = this.dataFile;
        boolean z = this.hasSubInfo;
        int i11 = this.subInfoPayload;
        byte[] bArr2 = this.subInfo;
        StringBuilder sb2 = new StringBuilder("{type=");
        sb2.append(i2);
        sb2.append(", name=");
        sb2.append(str);
        sb2.append(", offsetFromTail=");
        N2.j.x(sb2, i7, ",\n            | payload=", i8, ", data-payload=");
        sb2.append(i10);
        sb2.append(", data=");
        sb2.append(bArr);
        sb2.append(", dataFile=");
        sb2.append(mediaFile);
        sb2.append("\n            | hasSubInfo=");
        sb2.append(z);
        sb2.append(", subInfoPayload=");
        sb2.append(i11);
        sb2.append(", subInfo=");
        sb2.append(bArr2);
        sb2.append("}\n        ");
        return CommonsKt.trimToOneLine(sb2.toString());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SEFDataInfo(int r16, java.lang.String r17, boolean r18, int r19, int r20, byte[] r21, com.samsung.android.motionphoto.utils.v2.MediaFile r22, int r23, long r24, byte[] r26, int r27, long r28, int r30, kotlin.jvm.internal.e r31) {
        /*
            r15 = this;
            r0 = r30
            r1 = r0 & 2
            if (r1 == 0) goto L_0x0009
            java.lang.String r1 = ""
            goto L_0x000b
        L_0x0009:
            r1 = r17
        L_0x000b:
            r2 = r0 & 4
            r3 = 0
            if (r2 == 0) goto L_0x0012
            r2 = r3
            goto L_0x0014
        L_0x0012:
            r2 = r18
        L_0x0014:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x001a
            r4 = r3
            goto L_0x001c
        L_0x001a:
            r4 = r19
        L_0x001c:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x0022
            r5 = r3
            goto L_0x0024
        L_0x0022:
            r5 = r20
        L_0x0024:
            r6 = r0 & 32
            r7 = 0
            if (r6 == 0) goto L_0x002b
            r6 = r7
            goto L_0x002d
        L_0x002b:
            r6 = r21
        L_0x002d:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0033
            r8 = r7
            goto L_0x0035
        L_0x0033:
            r8 = r22
        L_0x0035:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x003b
            r9 = r3
            goto L_0x003d
        L_0x003b:
            r9 = r23
        L_0x003d:
            r10 = r0 & 256(0x100, float:3.59E-43)
            r11 = 0
            if (r10 == 0) goto L_0x0045
            r13 = r11
            goto L_0x0047
        L_0x0045:
            r13 = r24
        L_0x0047:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x004c
            goto L_0x004e
        L_0x004c:
            r7 = r26
        L_0x004e:
            r10 = r0 & 1024(0x400, float:1.435E-42)
            if (r10 == 0) goto L_0x0053
            goto L_0x0055
        L_0x0053:
            r3 = r27
        L_0x0055:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0074
            r30 = r11
        L_0x005b:
            r17 = r15
            r18 = r16
            r19 = r1
            r20 = r2
            r29 = r3
            r21 = r4
            r22 = r5
            r23 = r6
            r28 = r7
            r24 = r8
            r25 = r9
            r26 = r13
            goto L_0x0077
        L_0x0074:
            r30 = r28
            goto L_0x005b
        L_0x0077:
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r28, r29, r30)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.SEFDataInfo.<init>(int, java.lang.String, boolean, int, int, byte[], com.samsung.android.motionphoto.utils.v2.MediaFile, int, long, byte[], int, long, int, kotlin.jvm.internal.e):void");
    }
}
