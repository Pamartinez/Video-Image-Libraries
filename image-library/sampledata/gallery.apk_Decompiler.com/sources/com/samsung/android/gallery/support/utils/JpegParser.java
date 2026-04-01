package com.samsung.android.gallery.support.utils;

import N2.j;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JpegParser {
    /* access modifiers changed from: private */
    public static final byte[] IDENTIFIER_MPF_APP2;
    /* access modifiers changed from: private */
    public static final byte[] IDENTIFIER_XMP_APP1;
    boolean incMpf;
    boolean incXmp;
    final ArrayList<Header> list = new ArrayList<>();
    long pos;
    final ArrayList<Header> primary = new ArrayList<>();
    MpfInfo primaryMpfInfo;
    boolean progressive;
    final ArrayList<MpfInfo> secondaries = new ArrayList<>();
    long size;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Header {
        int key;
        int length;
        Mpf mpf;
        long position;
        byte[] xmp;

        public Header(int i2, long j2, int i7) {
            this.key = i2;
            this.position = j2;
            this.length = i7;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Marker{");
            sb2.append(JpegParser.toReadableString(this.key));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.position);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.length);
            String str = "";
            sb2.append(str);
            if (this.mpf != null) {
                str = GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mpf;
            }
            return C0212a.p(sb2, str, "}");
        }

        public Header unpackApp1(RandomAccessFile randomAccessFile, int i2, int i7) {
            if (i2 == 225 && i7 > JpegParser.IDENTIFIER_XMP_APP1.length) {
                try {
                    byte[] bArr = new byte[JpegParser.IDENTIFIER_XMP_APP1.length];
                    randomAccessFile.read(bArr);
                    if (Arrays.equals(bArr, JpegParser.IDENTIFIER_XMP_APP1)) {
                        byte[] bArr2 = new byte[((i7 - 2) - JpegParser.IDENTIFIER_XMP_APP1.length)];
                        if (randomAccessFile.read(bArr2) > 0) {
                            this.xmp = bArr2;
                            return this;
                        }
                    }
                } catch (Exception e) {
                    j.s(e, C0086a.o(i2, "unpackApp1(", ") failed. e="), "JpegParser");
                }
            }
            return this;
        }

        public Header unpackApp2(RandomAccessFile randomAccessFile, int i2, int i7) {
            if (i2 == 226) {
                try {
                    byte[] bArr = new byte[(i7 - 2)];
                    if (randomAccessFile.read(bArr) > 0 && JpegParser.compare(bArr, 0, JpegParser.IDENTIFIER_MPF_APP2.length, JpegParser.IDENTIFIER_MPF_APP2)) {
                        this.mpf = new Mpf(bArr);
                        return this;
                    }
                } catch (Exception e) {
                    j.s(e, C0086a.o(i2, "unpackApp2(", ") failed. e="), "JpegParser");
                }
            }
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Mpf {
        static final byte[] MP_ENDIAN_BIG = {77, 77, 0, 42};
        static final byte[] MP_ENDIAN_LITTLE = {73, 73, 42, 0};
        int count;
        ByteOrder endian;
        int imageCount;
        IndexIFDTag individualUidList;
        final ArrayList<MpEntry> mpEntries = new ArrayList<>();
        IndexIFDTag mpEntry;
        IndexIFDTag numberOfImage;
        final int offset = 8;
        int offsetOfNext;
        int offsetToFirstIfd;
        IndexIFDTag totalNumberOfCapturedFrames;
        IndexIFDTag version;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class IndexIFDTag {
            int count;
            int tag;
            int type;
            int value;

            public String toString() {
                StringBuilder sb2 = new StringBuilder("IFD-TAG[");
                sb2.append(this.tag);
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
            int attribute;
            int entryNumber1;
            int entryNumber2;
            long imageOffset;
            long imageSize;
            int imageType;

            public String toString() {
                StringBuilder sb2 = new StringBuilder("Entry[");
                sb2.append(this.imageOffset);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(this.imageSize);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(Integer.toHexString(this.imageType));
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(this.entryNumber1);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                return C0086a.l(sb2, this.entryNumber2, "]");
            }
        }

        public Mpf(byte[] bArr) {
            int i2;
            ByteOrder endian2 = getEndian(bArr, 4, 4);
            this.endian = endian2;
            if (endian2 == null) {
                Log.e("JpegParser", "construct failed. unknown endian");
                return;
            }
            ByteBuffer wrap = ByteBuffer.wrap(bArr, 8, bArr.length - 8);
            wrap.order(this.endian);
            int i7 = wrap.getInt();
            this.offsetToFirstIfd = i7;
            if (i7 > 8) {
                wrap.position(i7 - 8);
            }
            this.count = wrap.getShort();
            for (int i8 = 0; i8 < this.count; i8++) {
                IndexIFDTag indexIFDTag = new IndexIFDTag();
                indexIFDTag.tag = wrap.getShort() & 65535;
                indexIFDTag.type = 65535 & wrap.getShort();
                indexIFDTag.count = wrap.getInt();
                indexIFDTag.value = wrap.getInt();
                int i10 = indexIFDTag.tag;
                if (i10 == 45056) {
                    this.version = indexIFDTag;
                } else if (i10 == 45057) {
                    this.numberOfImage = indexIFDTag;
                } else if (i10 == 45058) {
                    this.mpEntry = indexIFDTag;
                } else if (i10 == 45059) {
                    this.individualUidList = indexIFDTag;
                } else if (i10 == 45060) {
                    this.totalNumberOfCapturedFrames = indexIFDTag;
                }
            }
            int i11 = wrap.getInt();
            this.offsetOfNext = i11;
            if (i11 > 0) {
                wrap.position(wrap.position() + this.offsetOfNext);
            }
            IndexIFDTag indexIFDTag2 = this.mpEntry;
            if (indexIFDTag2 != null) {
                i2 = indexIFDTag2.count / 16;
            } else {
                i2 = 0;
            }
            this.imageCount = i2;
            for (int i12 = 0; i12 < this.imageCount; i12++) {
                MpEntry mpEntry2 = new MpEntry();
                int i13 = wrap.getInt();
                mpEntry2.attribute = i13;
                mpEntry2.imageType = i13 & 16777215;
                mpEntry2.imageSize = (long) wrap.getInt();
                mpEntry2.imageOffset = (long) wrap.getInt();
                mpEntry2.entryNumber1 = wrap.getShort() & 65535;
                mpEntry2.entryNumber2 = wrap.getShort() & 65535;
                this.mpEntries.add(mpEntry2);
            }
        }

        public ByteOrder getEndian(byte[] bArr, int i2, int i7) {
            if (JpegParser.compare(bArr, i2, i7, MP_ENDIAN_LITTLE)) {
                return ByteOrder.LITTLE_ENDIAN;
            }
            if (JpegParser.compare(bArr, i2, i7, MP_ENDIAN_BIG)) {
                return ByteOrder.BIG_ENDIAN;
            }
            return null;
        }

        public String toString() {
            return "MPF[" + this.endian + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.count + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mpEntries + "]";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MpfInfo {
        final int imageType;
        final ArrayList<Header> list = new ArrayList<>();
        final long offset;
        final long size;

        public MpfInfo(long j2, long j3, int i2) {
            this.offset = j2;
            this.size = j3;
            this.imageType = i2;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("MpfInfo[");
            sb2.append(this.offset);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.size);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0086a.l(sb2, this.imageType, "]");
        }
    }

    static {
        Charset charset = StandardCharsets.US_ASCII;
        IDENTIFIER_XMP_APP1 = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(charset);
        IDENTIFIER_MPF_APP2 = com.samsung.o3dp.jpeg3dcontainer.model.Mpf.IDENTIFIER.getBytes(charset);
    }

    public static boolean compare(byte[] bArr, int i2, int i7, byte[] bArr2) {
        for (int i8 = 0; i8 < i7; i8++) {
            if (bArr[i2 + i8] != bArr2[i8]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isProgressive(String str) {
        JpegParser jpegParser = new JpegParser();
        if (!jpegParser.unpack(str, true) || !jpegParser.progressive) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadXmp$0(ArrayList arrayList, Header header) {
        byte[] bArr;
        if (header.key == 225 && (bArr = header.xmp) != null) {
            arrayList.add(new String(bArr));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadXmp$1(ArrayList arrayList, Header header) {
        byte[] bArr;
        if (header.key == 225 && (bArr = header.xmp) != null) {
            arrayList.add(new String(bArr));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadXmp$2(ArrayList arrayList, MpfInfo mpfInfo) {
        if (mpfInfo.imageType == 0) {
            mpfInfo.list.forEach(new C0683v(arrayList, 2));
        }
    }

    public static ArrayList<String> loadXmp(String str, boolean z) {
        ArrayList<String> arrayList = new ArrayList<>();
        JpegParser includeMpf = new JpegParser().includeXmp(true).includeMpf(z);
        if (includeMpf.unpack(str, true)) {
            includeMpf.primary.forEach(new C0683v(arrayList, 0));
            if (z) {
                includeMpf.secondaries.forEach(new C0683v(arrayList, 1));
            }
        }
        return arrayList;
    }

    public static int toInt(byte b) {
        return b & 255;
    }

    public static String toReadableString(int i2) {
        if (i2 == 196) {
            return "DHT";
        }
        if (i2 == 204) {
            return "DAC";
        }
        if (i2 == 254) {
            return "COM";
        }
        switch (i2) {
            case 216:
                return "SOI";
            case 217:
                return "EOI";
            case 218:
                return "SOS";
            case 219:
                return "DQT";
            default:
                switch (i2) {
                    case 221:
                        return "DRI";
                    case 222:
                        return "DHP";
                    case 223:
                        return "EXP";
                    default:
                        if (i2 < 208 || i2 > 215) {
                            int i7 = i2 & 240;
                            if (i7 == 192) {
                                return "SOF" + Integer.toHexString(i2 & 15);
                            } else if (i7 == 224) {
                                return "APP" + Integer.toHexString(i2 & 15);
                            } else {
                                return "0x" + Integer.toHexString(i2).toUpperCase();
                            }
                        } else {
                            return "RST" + Integer.toHexString(i2 & 15);
                        }
                }
        }
    }

    public boolean findEcs(RandomAccessFile randomAccessFile) {
        byte[] bArr = new byte[32768];
        while (randomAccessFile.getFilePointer() < this.size) {
            randomAccessFile.seek(this.pos);
            int read = randomAccessFile.read(bArr);
            if (read == 0) {
                return false;
            }
            int findNextMarker = findNextMarker(bArr, read);
            if (findNextMarker >= 0) {
                this.pos += (long) findNextMarker;
                return true;
            }
            this.pos += (long) (read - 1);
        }
        return false;
    }

    public boolean findMarkers(RandomAccessFile randomAccessFile, boolean z) {
        if (!findSoi(randomAccessFile)) {
            return false;
        }
        byte[] bArr = new byte[4];
        while (true) {
            long j2 = this.pos;
            boolean z3 = true;
            if (j2 >= this.size) {
                break;
            }
            randomAccessFile.seek(j2);
            if (randomAccessFile.read(bArr) == 0) {
                break;
            } else if (toInt(bArr[0]) != 255) {
                this.pos++;
            } else {
                int i2 = toInt(bArr[1]);
                if (i2 == 221 || i2 == 196 || i2 == 204 || i2 == 219 || i2 == 254 || i2 == 222 || i2 == 223 || (i2 >= 240 && i2 < 254)) {
                    int i7 = toInt(bArr[2], bArr[3]);
                    this.list.add(new Header(i2, this.pos, i7));
                    this.pos += (long) (i7 + 2);
                } else {
                    int i8 = i2 & 240;
                    if (i8 == 224) {
                        int i10 = toInt(bArr[2], bArr[3]);
                        Header header = new Header(i2, this.pos, i10);
                        this.list.add(header);
                        if (i2 == 225 && this.incXmp) {
                            header.unpackApp1(randomAccessFile, i2, i10);
                        } else if (i2 == 226 && this.incMpf) {
                            header.unpackApp2(randomAccessFile, i2, i10);
                        }
                        this.pos += (long) (i10 + 2);
                    } else if (i8 == 192) {
                        int i11 = toInt(bArr[2], bArr[3]);
                        this.list.add(new Header(i2, this.pos, i11));
                        this.pos += (long) (i11 + 2);
                        if (i2 != 194) {
                            z3 = false;
                        }
                        this.progressive = z3;
                    } else if (i2 == 218) {
                        int i12 = toInt(bArr[2], bArr[3]);
                        this.list.add(new Header(i2, this.pos, i12));
                        this.pos += (long) (i12 + 2);
                        if (z || !findEcs(randomAccessFile)) {
                            break;
                        }
                    } else if (i2 == 217) {
                        this.list.add(new Header(i2, this.pos, 0));
                        this.pos += 2;
                        break;
                    } else {
                        this.pos++;
                    }
                }
            }
        }
        return true;
    }

    public int findNextMarker(byte[] bArr, int i2) {
        int i7;
        for (int i8 = 0; i8 < i2 - 1; i8++) {
            if (toInt(bArr[i8]) == 255 && (((i7 = toInt(bArr[i8 + 1])) < 208 || i7 > 215) && i7 != 0)) {
                return i8;
            }
        }
        return -1;
    }

    public boolean findSoi(RandomAccessFile randomAccessFile) {
        byte[] bArr = new byte[2];
        randomAccessFile.seek(this.pos);
        long min = Math.min(this.pos + 64, this.size);
        while (this.pos < min && randomAccessFile.read(bArr) > 0) {
            long j2 = 2;
            if (toInt(bArr[0]) == 255 && toInt(bArr[1]) == 216) {
                this.list.add(new Header(216, this.pos, 0));
                this.pos += 2;
                return true;
            }
            long j3 = this.pos;
            if (toInt(bArr[1]) == 255) {
                j2 = 1;
            }
            long j8 = j3 + j2;
            this.pos = j8;
            randomAccessFile.seek(j8);
        }
        Log.e("JpegParser", "findSoi failed");
        return false;
    }

    public JpegParser includeMpf(boolean z) {
        this.incMpf = z;
        return this;
    }

    public JpegParser includeXmp(boolean z) {
        this.incXmp = z;
        return this;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("Jpeg{");
        if (this.progressive) {
            str = "progressive";
        } else {
            str = "baseline";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.primaryMpfInfo);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.secondaries);
        sb2.append("}");
        return sb2.toString();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean unpack(java.lang.String r21, boolean r22) {
        /*
            r20 = this;
            r1 = r20
            r2 = r22
            java.lang.String r3 = "JpegParser"
            long r4 = java.lang.System.currentTimeMillis()
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ Error | Exception -> 0x0127 }
            java.lang.String r0 = "r"
            r8 = r21
            r7.<init>(r8, r0)     // Catch:{ Error | Exception -> 0x0127 }
            long r8 = r7.length()     // Catch:{ all -> 0x00c7 }
            r1.size = r8     // Catch:{ all -> 0x00c7 }
            boolean r0 = r1.findMarkers(r7, r2)     // Catch:{ all -> 0x00c7 }
            if (r0 == 0) goto L_0x002d
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r0 = r1.primary     // Catch:{ all -> 0x0027 }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r8 = r1.list     // Catch:{ all -> 0x0027 }
            r0.addAll(r8)     // Catch:{ all -> 0x0027 }
            goto L_0x002d
        L_0x0027:
            r0 = move-exception
            r1 = r0
            r6 = r7
            r10 = 0
            goto L_0x011e
        L_0x002d:
            boolean r0 = r1.incMpf     // Catch:{ all -> 0x00c7 }
            if (r0 == 0) goto L_0x004e
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r0 = r1.primary     // Catch:{ all -> 0x0027 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0027 }
        L_0x0037:
            boolean r8 = r0.hasNext()     // Catch:{ all -> 0x0027 }
            if (r8 == 0) goto L_0x004e
            java.lang.Object r8 = r0.next()     // Catch:{ all -> 0x0027 }
            com.samsung.android.gallery.support.utils.JpegParser$Header r8 = (com.samsung.android.gallery.support.utils.JpegParser.Header) r8     // Catch:{ all -> 0x0027 }
            int r9 = r8.key     // Catch:{ all -> 0x0027 }
            r10 = 226(0xe2, float:3.17E-43)
            if (r9 != r10) goto L_0x0037
            com.samsung.android.gallery.support.utils.JpegParser$Mpf r9 = r8.mpf     // Catch:{ all -> 0x0027 }
            if (r9 == 0) goto L_0x0037
            goto L_0x004f
        L_0x004e:
            r8 = 0
        L_0x004f:
            if (r8 == 0) goto L_0x00e9
            r9 = 0
        L_0x0052:
            com.samsung.android.gallery.support.utils.JpegParser$Mpf r0 = r8.mpf     // Catch:{ all -> 0x00c7 }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Mpf$MpEntry> r0 = r0.mpEntries     // Catch:{ all -> 0x00c7 }
            int r0 = r0.size()     // Catch:{ all -> 0x00c7 }
            if (r9 >= r0) goto L_0x00e9
            com.samsung.android.gallery.support.utils.JpegParser$Mpf r0 = r8.mpf     // Catch:{ Exception -> 0x007d }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Mpf$MpEntry> r0 = r0.mpEntries     // Catch:{ Exception -> 0x007d }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ Exception -> 0x007d }
            com.samsung.android.gallery.support.utils.JpegParser$Mpf$MpEntry r0 = (com.samsung.android.gallery.support.utils.JpegParser.Mpf.MpEntry) r0     // Catch:{ Exception -> 0x007d }
            long r11 = r0.imageOffset     // Catch:{ Exception -> 0x007d }
            long r13 = r0.imageSize     // Catch:{ Exception -> 0x007d }
            r15 = 0
            int r10 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r10 != 0) goto L_0x0081
            com.samsung.android.gallery.support.utils.JpegParser$MpfInfo r10 = new com.samsung.android.gallery.support.utils.JpegParser$MpfInfo     // Catch:{  }
            int r15 = r0.imageType     // Catch:{  }
            r10.<init>(r11, r13, r15)     // Catch:{  }
            r1.primaryMpfInfo = r10     // Catch:{  }
            r6 = r7
            r10 = 0
            goto L_0x00e4
        L_0x007d:
            r0 = move-exception
            r6 = r7
            r10 = 0
            goto L_0x00cb
        L_0x0081:
            com.samsung.android.gallery.support.utils.JpegParser$Mpf r10 = r8.mpf     // Catch:{ Exception -> 0x007d }
            java.util.Objects.requireNonNull(r10)     // Catch:{ Exception -> 0x007d }
            r19 = r7
            r10 = 0
            long r6 = r8.position     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            r15 = 8
            long r6 = r6 + r15
            long r6 = r6 + r11
            r16 = r13
            com.samsung.android.gallery.support.utils.JpegParser$MpfInfo r13 = new com.samsung.android.gallery.support.utils.JpegParser$MpfInfo     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            int r0 = r0.imageType     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            r18 = r0
            r14 = r6
            r13.<init>(r14, r16, r18)     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            r1.pos = r14     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
            r6 = r19
            r6.seek(r14)     // Catch:{ Exception -> 0x00bd }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r0 = r1.list     // Catch:{ Exception -> 0x00bd }
            r0.clear()     // Catch:{ Exception -> 0x00bd }
            boolean r0 = r1.findMarkers(r6, r2)     // Catch:{ Exception -> 0x00bd }
            if (r0 == 0) goto L_0x00e4
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r0 = r13.list     // Catch:{ Exception -> 0x00bd }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r7 = r1.list     // Catch:{ Exception -> 0x00bd }
            r0.addAll(r7)     // Catch:{ Exception -> 0x00bd }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$MpfInfo> r0 = r1.secondaries     // Catch:{ Exception -> 0x00bd }
            r0.add(r13)     // Catch:{ Exception -> 0x00bd }
            goto L_0x00e4
        L_0x00ba:
            r0 = move-exception
        L_0x00bb:
            r1 = r0
            goto L_0x011e
        L_0x00bd:
            r0 = move-exception
            goto L_0x00cb
        L_0x00bf:
            r0 = move-exception
            r6 = r19
            goto L_0x00bb
        L_0x00c3:
            r0 = move-exception
            r6 = r19
            goto L_0x00cb
        L_0x00c7:
            r0 = move-exception
            r6 = r7
            r10 = 0
            goto L_0x00bb
        L_0x00cb:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r7.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r11 = "unpack mpf failed. e="
            r7.append(r11)     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00ba }
            r7.append(r0)     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x00ba }
            com.samsung.android.gallery.support.utils.Log.e(r3, r0)     // Catch:{ all -> 0x00ba }
        L_0x00e4:
            int r9 = r9 + 1
            r7 = r6
            goto L_0x0052
        L_0x00e9:
            r6 = r7
            r10 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = "unpack "
            r0.append(r2)     // Catch:{ all -> 0x00ba }
            r0.append(r1)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = " +"
            r0.append(r2)     // Catch:{ all -> 0x00ba }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ba }
            long r7 = r7 - r4
            r0.append(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00ba }
            com.samsung.android.gallery.support.utils.Log.d(r3, r0)     // Catch:{ all -> 0x00ba }
            java.util.ArrayList<com.samsung.android.gallery.support.utils.JpegParser$Header> r0 = r1.primary     // Catch:{ all -> 0x00ba }
            int r0 = r0.size()     // Catch:{ all -> 0x00ba }
            if (r0 <= 0) goto L_0x0117
            r0 = 1
            goto L_0x0118
        L_0x0117:
            r0 = r10
        L_0x0118:
            r6.close()     // Catch:{ Error | Exception -> 0x011c }
            return r0
        L_0x011c:
            r0 = move-exception
            goto L_0x0129
        L_0x011e:
            r6.close()     // Catch:{ all -> 0x0122 }
            goto L_0x0126
        L_0x0122:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Error | Exception -> 0x011c }
        L_0x0126:
            throw r1     // Catch:{ Error | Exception -> 0x011c }
        L_0x0127:
            r0 = move-exception
            r10 = 0
        L_0x0129:
            java.lang.String r1 = "unpack failed"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r3, (java.lang.String) r1, (java.lang.Throwable) r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.JpegParser.unpack(java.lang.String, boolean):boolean");
    }

    public static int toInt(byte b, byte b5) {
        return ((b & 255) << 8) + (b5 & 255);
    }
}
