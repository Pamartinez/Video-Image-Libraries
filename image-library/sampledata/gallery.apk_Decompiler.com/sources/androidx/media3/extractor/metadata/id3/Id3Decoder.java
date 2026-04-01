package androidx.media3.extractor.metadata.id3;

import F2.Q;
import F2.U;
import F2.y0;
import K.a;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Id3Decoder extends SimpleMetadataDecoder {
    public static final FramePredicate NO_FRAMES_PREDICATE = new a(21);
    private final FramePredicate framePredicate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FramePredicate {
        boolean evaluate(int i2, int i7, int i8, int i10, int i11);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Id3Header {
        /* access modifiers changed from: private */
        public final int framesSize;
        /* access modifiers changed from: private */
        public final boolean isUnsynchronized;
        /* access modifiers changed from: private */
        public final int majorVersion;

        public Id3Header(int i2, boolean z, int i7) {
            this.majorVersion = i2;
            this.isUnsynchronized = z;
            this.framesSize = i7;
        }
    }

    public Id3Decoder(FramePredicate framePredicate2) {
        this.framePredicate = framePredicate2;
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i2, int i7) {
        if (i7 <= i2) {
            return Util.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(bArr, i2, i7);
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i2, int i7) {
        int i8;
        String str;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(readUnsignedByte);
        int i10 = i2 - 1;
        byte[] bArr = new byte[i10];
        parsableByteArray.readBytes(bArr, 0, i10);
        if (i7 == 2) {
            str = "image/" + k.S(new String(bArr, 0, 3, StandardCharsets.ISO_8859_1));
            if ("image/jpg".equals(str)) {
                str = "image/jpeg";
            }
            i8 = 2;
        } else {
            i8 = indexOfZeroByte(bArr, 0);
            String S = k.S(new String(bArr, 0, i8, StandardCharsets.ISO_8859_1));
            if (S.indexOf(47) == -1) {
                str = "image/".concat(S);
            } else {
                str = S;
            }
        }
        int i11 = i8 + 2;
        int indexOfTerminator = indexOfTerminator(bArr, i11, readUnsignedByte);
        return new ApicFrame(str, new String(bArr, i11, indexOfTerminator - i11, charset), bArr[i8 + 1] & 255, copyOfRangeIfValid(bArr, indexOfTerminator + delimiterLength(readUnsignedByte), i10));
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i2, String str) {
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new BinaryFrame(str, bArr);
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i2, int i7, boolean z, int i8, FramePredicate framePredicate2) {
        long j2;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray.getData(), position);
        String str = new String(parsableByteArray.getData(), position, indexOfZeroByte - position, StandardCharsets.ISO_8859_1);
        parsableByteArray.setPosition(indexOfZeroByte + 1);
        int readInt = parsableByteArray.readInt();
        int readInt2 = parsableByteArray.readInt();
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (readUnsignedInt == 4294967295L) {
            readUnsignedInt = -1;
        }
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        if (readUnsignedInt2 == 4294967295L) {
            j2 = -1;
        } else {
            j2 = readUnsignedInt2;
        }
        ArrayList arrayList = new ArrayList();
        int i10 = position + i2;
        while (parsableByteArray.getPosition() < i10) {
            Id3Frame decodeFrame = decodeFrame(i7, parsableByteArray, z, i8, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new ChapterFrame(str, readInt, readInt2, readUnsignedInt, j2, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i2, int i7, boolean z, int i8, FramePredicate framePredicate2) {
        boolean z3;
        boolean z7;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray2.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.getData(), position);
        String str = new String(parsableByteArray2.getData(), position, indexOfZeroByte - position, StandardCharsets.ISO_8859_1);
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        if ((readUnsignedByte & 2) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if ((readUnsignedByte & 1) != 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
        String[] strArr = new String[readUnsignedByte2];
        for (int i10 = 0; i10 < readUnsignedByte2; i10++) {
            int position2 = parsableByteArray2.getPosition();
            int indexOfZeroByte2 = indexOfZeroByte(parsableByteArray2.getData(), position2);
            strArr[i10] = new String(parsableByteArray2.getData(), position2, indexOfZeroByte2 - position2, StandardCharsets.ISO_8859_1);
            parsableByteArray2.setPosition(indexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i11 = position + i2;
        while (parsableByteArray2.getPosition() < i11) {
            Id3Frame decodeFrame = decodeFrame(i7, parsableByteArray2, z, i8, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new ChapterTocFrame(str, z3, z7, strArr, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i2) {
        if (i2 < 4) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(readUnsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i7 = i2 - 4;
        byte[] bArr2 = new byte[i7];
        parsableByteArray.readBytes(bArr2, 0, i7);
        int indexOfTerminator = indexOfTerminator(bArr2, 0, readUnsignedByte);
        String str2 = new String(bArr2, 0, indexOfTerminator, charset);
        int delimiterLength = indexOfTerminator + delimiterLength(readUnsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, delimiterLength, indexOfTerminator(bArr2, delimiterLength, readUnsignedByte), charset));
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v51 */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01a9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x01aa, code lost:
        r8 = r1;
        r9 = r2;
        r11 = r5;
        r1 = r6;
        r2 = r10;
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0125, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0126, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:87:0x0117, B:133:0x019e] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0125 A[Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:87:0x0117] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:87:0x0117=Splitter:B:87:0x0117, B:133:0x019e=Splitter:B:133:0x019e} */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.metadata.id3.Id3Frame decodeFrame(int r19, androidx.media3.common.util.ParsableByteArray r20, boolean r21, int r22, androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate r23) {
        /*
            r3 = r19
            r6 = r20
            int r2 = r6.readUnsignedByte()
            int r1 = r6.readUnsignedByte()
            int r4 = r6.readUnsignedByte()
            r7 = 0
            r8 = 3
            if (r3 < r8) goto L_0x001a
            int r0 = r6.readUnsignedByte()
            r5 = r0
            goto L_0x001b
        L_0x001a:
            r5 = r7
        L_0x001b:
            r9 = 4
            if (r3 != r9) goto L_0x003d
            int r0 = r6.readUnsignedIntToInt()
            if (r21 != 0) goto L_0x003b
            r10 = r0 & 255(0xff, float:3.57E-43)
            int r11 = r0 >> 8
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 7
            r10 = r10 | r11
            int r11 = r0 >> 16
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 14
            r10 = r10 | r11
            int r0 = r0 >> 24
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << 21
            r0 = r0 | r10
        L_0x003b:
            r10 = r0
            goto L_0x0049
        L_0x003d:
            if (r3 != r8) goto L_0x0044
            int r0 = r6.readUnsignedIntToInt()
            goto L_0x003b
        L_0x0044:
            int r0 = r6.readUnsignedInt24()
            goto L_0x003b
        L_0x0049:
            if (r3 < r8) goto L_0x0051
            int r0 = r6.readUnsignedShort()
            r11 = r0
            goto L_0x0052
        L_0x0051:
            r11 = r7
        L_0x0052:
            r12 = 0
            if (r2 != 0) goto L_0x0067
            if (r1 != 0) goto L_0x0067
            if (r4 != 0) goto L_0x0067
            if (r5 != 0) goto L_0x0067
            if (r10 != 0) goto L_0x0067
            if (r11 != 0) goto L_0x0067
            int r0 = r6.limit()
            r6.setPosition(r0)
            return r12
        L_0x0067:
            int r0 = r6.getPosition()
            int r13 = r0 + r10
            int r0 = r6.limit()
            java.lang.String r14 = "Id3Decoder"
            if (r13 <= r0) goto L_0x0082
            java.lang.String r0 = "Frame size exceeds remaining tag data"
            androidx.media3.common.util.Log.w(r14, r0)
            int r0 = r6.limit()
            r6.setPosition(r0)
            return r12
        L_0x0082:
            if (r23 == 0) goto L_0x0099
            r0 = r3
            r3 = r1
            r1 = r0
            r0 = r23
            boolean r15 = r0.evaluate(r1, r2, r3, r4, r5)
            r18 = r3
            r3 = r1
            r1 = r2
            r2 = r18
            if (r15 != 0) goto L_0x009e
            r6.setPosition(r13)
            return r12
        L_0x0099:
            r18 = r2
            r2 = r1
            r1 = r18
        L_0x009e:
            r0 = 1
            if (r3 != r8) goto L_0x00ba
            r8 = r11 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x00a7
            r8 = r0
            goto L_0x00a8
        L_0x00a7:
            r8 = r7
        L_0x00a8:
            r15 = r11 & 64
            if (r15 == 0) goto L_0x00ae
            r15 = r0
            goto L_0x00af
        L_0x00ae:
            r15 = r7
        L_0x00af:
            r11 = r11 & 32
            if (r11 == 0) goto L_0x00b5
            r11 = r0
            goto L_0x00b6
        L_0x00b5:
            r11 = r7
        L_0x00b6:
            r17 = r7
            r7 = r8
            goto L_0x00eb
        L_0x00ba:
            if (r3 != r9) goto L_0x00e6
            r8 = r11 & 64
            if (r8 == 0) goto L_0x00c2
            r8 = r0
            goto L_0x00c3
        L_0x00c2:
            r8 = r7
        L_0x00c3:
            r15 = r11 & 8
            if (r15 == 0) goto L_0x00c9
            r15 = r0
            goto L_0x00ca
        L_0x00c9:
            r15 = r7
        L_0x00ca:
            r16 = r11 & 4
            if (r16 == 0) goto L_0x00d1
            r16 = r0
            goto L_0x00d3
        L_0x00d1:
            r16 = r7
        L_0x00d3:
            r17 = r11 & 2
            if (r17 == 0) goto L_0x00da
            r17 = r0
            goto L_0x00dc
        L_0x00da:
            r17 = r7
        L_0x00dc:
            r11 = r11 & r0
            if (r11 == 0) goto L_0x00e0
            r7 = r0
        L_0x00e0:
            r11 = r8
            r8 = r7
            r7 = r15
            r15 = r16
            goto L_0x00eb
        L_0x00e6:
            r8 = r7
            r11 = r8
            r15 = r11
            r17 = r15
        L_0x00eb:
            if (r7 != 0) goto L_0x00ef
            if (r15 == 0) goto L_0x00f4
        L_0x00ef:
            r1 = r6
            r16 = r12
            goto L_0x0255
        L_0x00f4:
            if (r11 == 0) goto L_0x00fb
            int r10 = r10 + -1
            r6.skipBytes(r0)
        L_0x00fb:
            if (r8 == 0) goto L_0x0102
            int r10 = r10 + -4
            r6.skipBytes(r9)
        L_0x0102:
            if (r17 == 0) goto L_0x0108
            int r10 = removeUnsynchronization(r6, r10)
        L_0x0108:
            r0 = 84
            r7 = 88
            r8 = 2
            if (r1 != r0) goto L_0x0134
            if (r2 != r7) goto L_0x0134
            if (r4 != r7) goto L_0x0134
            if (r3 == r8) goto L_0x0117
            if (r5 != r7) goto L_0x0134
        L_0x0117:
            androidx.media3.extractor.metadata.id3.TextInformationFrame r0 = decodeTxxxFrame(r6, r10)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
        L_0x011b:
            r8 = r1
            r9 = r2
            r11 = r5
            r1 = r6
            r2 = r10
            r16 = r12
        L_0x0122:
            r10 = r4
            goto L_0x0225
        L_0x0125:
            r0 = move-exception
            r1 = r6
            goto L_0x022c
        L_0x0129:
            r0 = move-exception
            r8 = r1
            r9 = r2
            r11 = r5
            r1 = r6
            r2 = r10
            r16 = r12
        L_0x0131:
            r10 = r4
            goto L_0x0230
        L_0x0134:
            if (r1 != r0) goto L_0x013f
            java.lang.String r0 = getFrameId(r3, r1, r2, r4, r5)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            androidx.media3.extractor.metadata.id3.TextInformationFrame r0 = decodeTextInformationFrame(r6, r10, r0)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            goto L_0x011b
        L_0x013f:
            r9 = 87
            if (r1 != r9) goto L_0x0150
            if (r2 != r7) goto L_0x0150
            if (r4 != r7) goto L_0x0150
            if (r3 == r8) goto L_0x014b
            if (r5 != r7) goto L_0x0150
        L_0x014b:
            androidx.media3.extractor.metadata.id3.UrlLinkFrame r0 = decodeWxxxFrame(r6, r10)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            goto L_0x011b
        L_0x0150:
            if (r1 != r9) goto L_0x015b
            java.lang.String r0 = getFrameId(r3, r1, r2, r4, r5)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            androidx.media3.extractor.metadata.id3.UrlLinkFrame r0 = decodeUrlLinkFrame(r6, r10, r0)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            goto L_0x011b
        L_0x015b:
            r7 = 73
            r9 = 80
            if (r1 != r9) goto L_0x0170
            r11 = 82
            if (r2 != r11) goto L_0x0170
            if (r4 != r7) goto L_0x0170
            r11 = 86
            if (r5 != r11) goto L_0x0170
            androidx.media3.extractor.metadata.id3.PrivFrame r0 = decodePrivFrame(r6, r10)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            goto L_0x011b
        L_0x0170:
            r11 = 71
            r15 = 79
            if (r1 != r11) goto L_0x0187
            r11 = 69
            if (r2 != r11) goto L_0x0187
            if (r4 != r15) goto L_0x0187
            r11 = 66
            if (r5 == r11) goto L_0x0182
            if (r3 != r8) goto L_0x0187
        L_0x0182:
            androidx.media3.extractor.metadata.id3.GeobFrame r0 = decodeGeobFrame(r6, r10)     // Catch:{ Exception | OutOfMemoryError -> 0x0129, all -> 0x0125 }
            goto L_0x011b
        L_0x0187:
            r11 = 65
            r16 = r12
            r12 = 67
            if (r3 != r8) goto L_0x0196
            if (r1 != r9) goto L_0x01b0
            if (r2 != r7) goto L_0x01b0
            if (r4 != r12) goto L_0x01b0
            goto L_0x019e
        L_0x0196:
            if (r1 != r11) goto L_0x01b0
            if (r2 != r9) goto L_0x01b0
            if (r4 != r7) goto L_0x01b0
            if (r5 != r12) goto L_0x01b0
        L_0x019e:
            androidx.media3.extractor.metadata.id3.ApicFrame r0 = decodeApicFrame(r6, r10, r3)     // Catch:{ Exception | OutOfMemoryError -> 0x01a9, all -> 0x0125 }
        L_0x01a2:
            r8 = r1
            r9 = r2
            r11 = r5
            r1 = r6
            r2 = r10
            goto L_0x0122
        L_0x01a9:
            r0 = move-exception
            r8 = r1
            r9 = r2
            r11 = r5
            r1 = r6
            r2 = r10
            goto L_0x0131
        L_0x01b0:
            r7 = 77
            if (r1 != r12) goto L_0x01c1
            if (r2 != r15) goto L_0x01c1
            if (r4 != r7) goto L_0x01c1
            if (r5 == r7) goto L_0x01bc
            if (r3 != r8) goto L_0x01c1
        L_0x01bc:
            androidx.media3.extractor.metadata.id3.CommentFrame r0 = decodeCommentFrame(r6, r10)     // Catch:{ Exception | OutOfMemoryError -> 0x01a9, all -> 0x0125 }
            goto L_0x01a2
        L_0x01c1:
            if (r1 != r12) goto L_0x01ea
            r8 = 72
            if (r2 != r8) goto L_0x01ea
            if (r4 != r11) goto L_0x01ea
            if (r5 != r9) goto L_0x01ea
            r8 = r1
            r9 = r2
            r11 = r5
            r1 = r6
            r2 = r10
            r5 = r22
            r6 = r23
            r10 = r4
            r4 = r21
            androidx.media3.extractor.metadata.id3.ChapterFrame r0 = decodeChapterFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception | OutOfMemoryError -> 0x01e4, all -> 0x01e0 }
            r3 = r19
            r1 = r20
            goto L_0x0225
        L_0x01e0:
            r0 = move-exception
            r1 = r20
            goto L_0x022c
        L_0x01e4:
            r0 = move-exception
            r3 = r19
            r1 = r20
            goto L_0x0230
        L_0x01ea:
            r8 = r1
            r9 = r2
            r11 = r5
            r2 = r10
            r10 = r4
            if (r8 != r12) goto L_0x020a
            if (r9 != r0) goto L_0x020a
            if (r10 != r15) goto L_0x020a
            if (r11 != r12) goto L_0x020a
            r3 = r19
            r1 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            androidx.media3.extractor.metadata.id3.ChapterTocFrame r0 = decodeChapterTOCFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception | OutOfMemoryError -> 0x0208, all -> 0x0206 }
            goto L_0x0225
        L_0x0206:
            r0 = move-exception
            goto L_0x022c
        L_0x0208:
            r0 = move-exception
            goto L_0x0230
        L_0x020a:
            r3 = r19
            r1 = r20
            if (r8 != r7) goto L_0x021d
            r4 = 76
            if (r9 != r4) goto L_0x021d
            if (r10 != r4) goto L_0x021d
            if (r11 != r0) goto L_0x021d
            androidx.media3.extractor.metadata.id3.MlltFrame r0 = decodeMlltFrame(r1, r2)     // Catch:{ Exception | OutOfMemoryError -> 0x0208, all -> 0x0206 }
            goto L_0x0225
        L_0x021d:
            java.lang.String r0 = getFrameId(r3, r8, r9, r10, r11)     // Catch:{ Exception | OutOfMemoryError -> 0x0208, all -> 0x0206 }
            androidx.media3.extractor.metadata.id3.BinaryFrame r0 = decodeBinaryFrame(r1, r2, r0)     // Catch:{ Exception | OutOfMemoryError -> 0x0208, all -> 0x0206 }
        L_0x0225:
            r1.setPosition(r13)
            r12 = r0
            r0 = r16
            goto L_0x0235
        L_0x022c:
            r1.setPosition(r13)
            throw r0
        L_0x0230:
            r1.setPosition(r13)
            r12 = r16
        L_0x0235:
            if (r12 != 0) goto L_0x0254
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to decode frame: id="
            r1.<init>(r4)
            java.lang.String r3 = getFrameId(r3, r8, r9, r10, r11)
            r1.append(r3)
            java.lang.String r3 = ", frameSize="
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            androidx.media3.common.util.Log.w(r14, r1, r0)
        L_0x0254:
            return r12
        L_0x0255:
            java.lang.String r0 = "Skipping unsupported compressed or encrypted frame"
            androidx.media3.common.util.Log.w(r14, r0)
            r1.setPosition(r13)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.id3.Id3Decoder.decodeFrame(int, androidx.media3.common.util.ParsableByteArray, boolean, int, androidx.media3.extractor.metadata.id3.Id3Decoder$FramePredicate):androidx.media3.extractor.metadata.id3.Id3Frame");
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i2) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        Charset charset = getCharset(readUnsignedByte);
        int i7 = i2 - 1;
        byte[] bArr = new byte[i7];
        parsableByteArray.readBytes(bArr, 0, i7);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        String normalizeMimeType = MimeTypes.normalizeMimeType(new String(bArr, 0, indexOfZeroByte, StandardCharsets.ISO_8859_1));
        int i8 = indexOfZeroByte + 1;
        int indexOfTerminator = indexOfTerminator(bArr, i8, readUnsignedByte);
        String decodeStringIfValid = decodeStringIfValid(bArr, i8, indexOfTerminator, charset);
        int delimiterLength = indexOfTerminator + delimiterLength(readUnsignedByte);
        int indexOfTerminator2 = indexOfTerminator(bArr, delimiterLength, readUnsignedByte);
        return new GeobFrame(normalizeMimeType, decodeStringIfValid, decodeStringIfValid(bArr, delimiterLength, indexOfTerminator2, charset), copyOfRangeIfValid(bArr, indexOfTerminator2 + delimiterLength(readUnsignedByte), i7));
    }

    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w("Id3Decoder", "Data too short to be an ID3 tag");
            return null;
        }
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        if (readUnsignedInt24 != 4801587) {
            Log.w("Id3Decoder", "Unexpected first three bytes of ID3 tag header: 0x".concat(String.format("%06X", new Object[]{Integer.valueOf(readUnsignedInt24)})));
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z = true;
        parsableByteArray.skipBytes(1);
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        if (readUnsignedByte == 2) {
            if ((readUnsignedByte2 & 64) != 0) {
                Log.w("Id3Decoder", "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (readUnsignedByte == 3) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readInt = parsableByteArray.readInt();
                parsableByteArray.skipBytes(readInt);
                readSynchSafeInt -= readInt + 4;
            }
        } else if (readUnsignedByte == 4) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readSynchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(readSynchSafeInt2 - 4);
                readSynchSafeInt -= readSynchSafeInt2;
            }
            if ((readUnsignedByte2 & 16) != 0) {
                readSynchSafeInt -= 10;
            }
        } else {
            A.a.D(readUnsignedByte, "Skipped ID3 tag with unsupported majorVersion=", "Id3Decoder");
            return null;
        }
        if (readUnsignedByte >= 4 || (readUnsignedByte2 & 128) == 0) {
            z = false;
        }
        return new Id3Header(readUnsignedByte, z, readSynchSafeInt);
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray parsableByteArray, int i2) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        int readUnsignedInt242 = parsableByteArray.readUnsignedInt24();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i7 = ((i2 - 10) * 8) / (readUnsignedByte + readUnsignedByte2);
        int i8 = readUnsignedByte2;
        int[] iArr = new int[i7];
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int[] iArr2 = new int[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            int readBits = parsableBitArray2.readBits(readUnsignedByte);
            int readBits2 = parsableBitArray2.readBits(i8);
            iArr[i10] = readBits;
            iArr2[i10] = readBits2;
        }
        return new MlltFrame(readUnsignedShort, readUnsignedInt24, readUnsignedInt242, iArr, iArr2);
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i2) {
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, indexOfZeroByte, StandardCharsets.ISO_8859_1), copyOfRangeIfValid(bArr, indexOfZeroByte + 1, i2));
    }

    private static String decodeStringIfValid(byte[] bArr, int i2, int i7, Charset charset) {
        if (i7 <= i2 || i7 > bArr.length) {
            return "";
        }
        return new String(bArr, i2, i7 - i2, charset);
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i2, String str) {
        if (i2 < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i7 = i2 - 1;
        byte[] bArr = new byte[i7];
        parsableByteArray.readBytes(bArr, 0, i7);
        return new TextInformationFrame(str, (String) null, decodeTextInformationFrameValues(bArr, readUnsignedByte, 0));
    }

    private static U decodeTextInformationFrameValues(byte[] bArr, int i2, int i7) {
        if (i7 >= bArr.length) {
            return U.B("");
        }
        Q x9 = U.x();
        int indexOfTerminator = indexOfTerminator(bArr, i7, i2);
        while (i7 < indexOfTerminator) {
            x9.a(new String(bArr, i7, indexOfTerminator - i7, getCharset(i2)));
            i7 = delimiterLength(i2) + indexOfTerminator;
            indexOfTerminator = indexOfTerminator(bArr, i7, i2);
        }
        y0 f = x9.f();
        if (f.isEmpty()) {
            return U.B("");
        }
        return f;
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i2) {
        if (i2 < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i7 = i2 - 1;
        byte[] bArr = new byte[i7];
        parsableByteArray.readBytes(bArr, 0, i7);
        int indexOfTerminator = indexOfTerminator(bArr, 0, readUnsignedByte);
        return new TextInformationFrame("TXXX", new String(bArr, 0, indexOfTerminator, getCharset(readUnsignedByte)), decodeTextInformationFrameValues(bArr, readUnsignedByte, indexOfTerminator + delimiterLength(readUnsignedByte)));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i2, String str) {
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new UrlLinkFrame(str, (String) null, new String(bArr, 0, indexOfZeroByte(bArr, 0), StandardCharsets.ISO_8859_1));
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i2) {
        if (i2 < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i7 = i2 - 1;
        byte[] bArr = new byte[i7];
        parsableByteArray.readBytes(bArr, 0, i7);
        int indexOfTerminator = indexOfTerminator(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfTerminator, getCharset(readUnsignedByte));
        int delimiterLength = indexOfTerminator + delimiterLength(readUnsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, delimiterLength, indexOfZeroByte(bArr, delimiterLength), StandardCharsets.ISO_8859_1));
    }

    private static int delimiterLength(int i2) {
        if (i2 == 0 || i2 == 3) {
            return 1;
        }
        return 2;
    }

    private static Charset getCharset(int i2) {
        if (i2 == 1) {
            return StandardCharsets.UTF_16;
        }
        if (i2 == 2) {
            return StandardCharsets.UTF_16BE;
        }
        if (i2 != 3) {
            return StandardCharsets.ISO_8859_1;
        }
        return StandardCharsets.UTF_8;
    }

    private static String getFrameId(int i2, int i7, int i8, int i10, int i11) {
        if (i2 == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i10)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i10), Integer.valueOf(i11)});
    }

    private static int indexOfTerminator(byte[] bArr, int i2, int i7) {
        int indexOfZeroByte = indexOfZeroByte(bArr, i2);
        if (i7 == 0 || i7 == 3) {
            return indexOfZeroByte;
        }
        while (indexOfZeroByte < bArr.length - 1) {
            if ((indexOfZeroByte - i2) % 2 == 0 && bArr[indexOfZeroByte + 1] == 0) {
                return indexOfZeroByte;
            }
            indexOfZeroByte = indexOfZeroByte(bArr, indexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i2) {
        while (i2 < bArr.length) {
            if (bArr[i2] == 0) {
                return i2;
            }
            i2++;
        }
        return bArr.length;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0(int i2, int i7, int i8, int i10, int i11) {
        return false;
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i2) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int i7 = position;
        while (true) {
            int i8 = i7 + 1;
            if (i8 >= position + i2) {
                return i2;
            }
            if ((data[i7] & 255) == 255 && data[i8] == 0) {
                System.arraycopy(data, i7 + 2, data, i8, (i2 - (i7 - position)) - 2);
                i2--;
            }
            i7 = i8;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0079, code lost:
        if ((r10 & 1) != 0) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0089, code lost:
        if ((r10 & 128) != 0) goto L_0x008e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009b A[SYNTHETIC, Splitter:B:48:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0097 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean validateFrames(androidx.media3.common.util.ParsableByteArray r18, int r19, int r20, boolean r21) {
        /*
            r1 = r18
            r0 = r19
            int r2 = r1.getPosition()
        L_0x0008:
            int r3 = r1.bytesLeft()     // Catch:{ all -> 0x0022 }
            r4 = 1
            r5 = r20
            if (r3 < r5) goto L_0x00ae
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L_0x0025
            int r7 = r1.readInt()     // Catch:{ all -> 0x0022 }
            long r8 = r1.readUnsignedInt()     // Catch:{ all -> 0x0022 }
            int r10 = r1.readUnsignedShort()     // Catch:{ all -> 0x0022 }
            goto L_0x002f
        L_0x0022:
            r0 = move-exception
            goto L_0x00b2
        L_0x0025:
            int r7 = r1.readUnsignedInt24()     // Catch:{ all -> 0x0022 }
            int r8 = r1.readUnsignedInt24()     // Catch:{ all -> 0x0022 }
            long r8 = (long) r8
            r10 = r6
        L_0x002f:
            r11 = 0
            if (r7 != 0) goto L_0x003d
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x003d
            if (r10 != 0) goto L_0x003d
            r1.setPosition(r2)
            return r4
        L_0x003d:
            r7 = 4
            if (r0 != r7) goto L_0x006e
            if (r21 != 0) goto L_0x006e
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r11 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004e
            r1.setPosition(r2)
            return r6
        L_0x004e:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 14
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 24
            long r8 = r8 >> r15
            long r8 = r8 & r11
            r11 = 21
            long r8 = r8 << r11
            long r8 = r8 | r13
        L_0x006e:
            if (r0 != r7) goto L_0x007e
            r3 = r10 & 64
            if (r3 == 0) goto L_0x0076
            r3 = r4
            goto L_0x0077
        L_0x0076:
            r3 = r6
        L_0x0077:
            r7 = r10 & 1
            if (r7 == 0) goto L_0x007c
            goto L_0x008e
        L_0x007c:
            r4 = r6
            goto L_0x008e
        L_0x007e:
            if (r0 != r3) goto L_0x008c
            r3 = r10 & 32
            if (r3 == 0) goto L_0x0086
            r3 = r4
            goto L_0x0087
        L_0x0086:
            r3 = r6
        L_0x0087:
            r7 = r10 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x007c
            goto L_0x008e
        L_0x008c:
            r3 = r6
            r4 = r3
        L_0x008e:
            if (r4 == 0) goto L_0x0092
            int r3 = r3 + 4
        L_0x0092:
            long r3 = (long) r3
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x009b
            r1.setPosition(r2)
            return r6
        L_0x009b:
            int r3 = r1.bytesLeft()     // Catch:{ all -> 0x0022 }
            long r3 = (long) r3
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x00a8
            r1.setPosition(r2)
            return r6
        L_0x00a8:
            int r3 = (int) r8
            r1.skipBytes(r3)     // Catch:{ all -> 0x0022 }
            goto L_0x0008
        L_0x00ae:
            r1.setPosition(r2)
            return r4
        L_0x00b2:
            r1.setPosition(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.id3.Id3Decoder.validateFrames(androidx.media3.common.util.ParsableByteArray, int, int, boolean):boolean");
    }

    public Metadata decode(byte[] bArr, int i2) {
        int i7;
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i2);
        Id3Header decodeHeader = decodeHeader(parsableByteArray);
        if (decodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        if (decodeHeader.majorVersion == 2) {
            i7 = 6;
        } else {
            i7 = 10;
        }
        int access$100 = decodeHeader.framesSize;
        if (decodeHeader.isUnsynchronized) {
            access$100 = removeUnsynchronization(parsableByteArray, decodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + access$100);
        boolean z = false;
        if (!validateFrames(parsableByteArray, decodeHeader.majorVersion, i7, false)) {
            if (decodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i7, true)) {
                Log.w("Id3Decoder", "Failed to validate ID3 tag with majorVersion=" + decodeHeader.majorVersion);
                return null;
            }
            z = true;
        }
        while (parsableByteArray.bytesLeft() >= i7) {
            Id3Frame decodeFrame = decodeFrame(decodeHeader.majorVersion, parsableByteArray, z, i7, this.framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }
}
