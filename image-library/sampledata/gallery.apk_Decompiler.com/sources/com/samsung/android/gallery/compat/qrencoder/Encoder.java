package com.samsung.android.gallery.compat.qrencoder;

import c0.C0086a;
import com.samsung.android.gallery.compat.qrencoder.common.BitArray;
import com.samsung.android.gallery.compat.qrencoder.common.Mode;
import com.samsung.android.gallery.compat.qrencoder.common.Version;
import com.samsung.android.gallery.compat.qrencoder.common.reedsolomon.GenericGF;
import com.samsung.android.gallery.compat.qrencoder.common.reedsolomon.ReedSolomonEncoder;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* renamed from: com.samsung.android.gallery.compat.qrencoder.Encoder$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$compat$qrencoder$common$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.compat.qrencoder.common.Mode[] r0 = com.samsung.android.gallery.compat.qrencoder.common.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$compat$qrencoder$common$Mode = r0
                com.samsung.android.gallery.compat.qrencoder.common.Mode r1 = com.samsung.android.gallery.compat.qrencoder.common.Mode.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$compat$qrencoder$common$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.compat.qrencoder.common.Mode r1 = com.samsung.android.gallery.compat.qrencoder.common.Mode.ALPHANUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$compat$qrencoder$common$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.compat.qrencoder.common.Mode r1 = com.samsung.android.gallery.compat.qrencoder.common.Mode.BYTE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$compat$qrencoder$common$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.compat.qrencoder.common.Mode r1 = com.samsung.android.gallery.compat.qrencoder.common.Mode.KANJI     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.compat.qrencoder.Encoder.AnonymousClass1.<clinit>():void");
        }
    }

    public static void append8BitBytes(String str, BitArray bitArray, String str2) {
        try {
            for (byte appendBits : str.getBytes(str2)) {
                bitArray.appendBits(appendBits, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    public static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(i2));
            if (alphanumericCode != -1) {
                int i7 = i2 + 1;
                if (i7 < length) {
                    int alphanumericCode2 = getAlphanumericCode(charSequence.charAt(i7));
                    if (alphanumericCode2 != -1) {
                        bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                        i2 += 2;
                    } else {
                        throw new WriterException();
                    }
                } else {
                    bitArray.appendBits(alphanumericCode, 6);
                    i2 = i7;
                }
            } else {
                throw new WriterException();
            }
        }
    }

    public static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$compat$qrencoder$common$Mode[mode.ordinal()];
        if (i2 == 1) {
            appendNumericBytes(str, bitArray);
        } else if (i2 == 2) {
            appendAlphanumericBytes(str, bitArray);
        } else if (i2 == 3) {
            append8BitBytes(str, bitArray, str2);
        } else if (i2 == 4) {
            appendKanjiBytes(str, bitArray);
        } else {
            throw new WriterException("Invalid mode: " + mode);
        }
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[LOOP:0: B:6:0x000f->B:19:0x003c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void appendKanjiBytes(java.lang.String r6, com.samsung.android.gallery.compat.qrencoder.common.BitArray r7) {
        /*
            java.lang.String r0 = "Shift_JIS"
            byte[] r6 = r6.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x005c }
            int r0 = r6.length
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x0054
            int r0 = r6.length
            int r0 = r0 + -1
            r1 = 0
        L_0x000f:
            if (r1 >= r0) goto L_0x0053
            byte r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r1 + 1
            byte r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r3
            r3 = 33088(0x8140, float:4.6366E-41)
            r4 = -1
            if (r2 < r3) goto L_0x002b
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L_0x002b
        L_0x0029:
            int r2 = r2 - r3
            goto L_0x003a
        L_0x002b:
            r3 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r3) goto L_0x0039
            r3 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r3) goto L_0x0039
            r3 = 49472(0xc140, float:6.9325E-41)
            goto L_0x0029
        L_0x0039:
            r2 = r4
        L_0x003a:
            if (r2 == r4) goto L_0x004b
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.appendBits(r3, r2)
            int r1 = r1 + 2
            goto L_0x000f
        L_0x004b:
            com.samsung.android.gallery.compat.qrencoder.WriterException r6 = new com.samsung.android.gallery.compat.qrencoder.WriterException
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x0053:
            return
        L_0x0054:
            com.samsung.android.gallery.compat.qrencoder.WriterException r6 = new com.samsung.android.gallery.compat.qrencoder.WriterException
            java.lang.String r7 = "Kanji byte size not even"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x005c:
            r6 = move-exception
            com.samsung.android.gallery.compat.qrencoder.WriterException r7 = new com.samsung.android.gallery.compat.qrencoder.WriterException
            r7.<init>((java.lang.Throwable) r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.compat.qrencoder.Encoder.appendKanjiBytes(java.lang.String, com.samsung.android.gallery.compat.qrencoder.common.BitArray):void");
    }

    public static void appendLengthInfo(int i2, Version version, Mode mode, BitArray bitArray) {
        int characterCountBits = mode.getCharacterCountBits(version);
        int i7 = 1 << characterCountBits;
        if (i2 < i7) {
            bitArray.appendBits(i2, characterCountBits);
            return;
        }
        throw new WriterException(i2 + " is bigger than " + (i7 - 1));
    }

    public static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    public static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int charAt = charSequence.charAt(i2) - '0';
            int i7 = i2 + 2;
            if (i7 < length) {
                bitArray.appendBits(((charSequence.charAt(i2 + 1) - '0') * 10) + (charAt * 100) + (charSequence.charAt(i7) - '0'), 10);
                i2 += 3;
            } else {
                i2++;
                if (i2 < length) {
                    bitArray.appendBits((charAt * 10) + (charSequence.charAt(i2) - '0'), 7);
                    i2 = i7;
                } else {
                    bitArray.appendBits(charAt, 4);
                }
            }
        }
    }

    private static int calculateBitsNeeded(Mode mode, BitArray bitArray, BitArray bitArray2, Version version) {
        return bitArray2.getSize() + mode.getCharacterCountBits(version) + bitArray.getSize();
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) {
        int i2 = Integer.MAX_VALUE;
        int i7 = -1;
        for (int i8 = 0; i8 < 8; i8++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, i8, byteMatrix);
            int calculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (calculateMaskPenalty < i2) {
                i7 = i8;
                i2 = calculateMaskPenalty;
            }
        }
        return i7;
    }

    private static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2) && isOnlyDoubleByteKanji(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z3 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= '0' && charAt <= '9') {
                z3 = true;
            } else if (getAlphanumericCode(charAt) == -1) {
                return Mode.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z3) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static Version chooseVersion(int i2, ErrorCorrectionLevel errorCorrectionLevel) {
        for (int i7 = 1; i7 <= 40; i7++) {
            Version versionForNumber = Version.getVersionForNumber(i7);
            if (willFit(i2, versionForNumber, errorCorrectionLevel)) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f4, code lost:
        if (com.samsung.android.gallery.compat.qrencoder.QRCode.isValidMaskPattern(r8) != false) goto L_0x00f8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.compat.qrencoder.QRCode encode(java.lang.String r6, com.samsung.android.gallery.compat.qrencoder.ErrorCorrectionLevel r7, java.util.Map<com.samsung.android.gallery.compat.qrencoder.EncodeHintType, ?> r8) {
        /*
            if (r8 == 0) goto L_0x000c
            com.samsung.android.gallery.compat.qrencoder.EncodeHintType r0 = com.samsung.android.gallery.compat.qrencoder.EncodeHintType.CHARACTER_SET
            boolean r0 = r8.containsKey(r0)
            if (r0 == 0) goto L_0x000c
            r0 = 1
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            if (r0 == 0) goto L_0x001a
            com.samsung.android.gallery.compat.qrencoder.EncodeHintType r1 = com.samsung.android.gallery.compat.qrencoder.EncodeHintType.CHARACTER_SET
            java.lang.Object r1 = r8.get(r1)
            java.lang.String r1 = r1.toString()
            goto L_0x001c
        L_0x001a:
            java.lang.String r1 = "ISO-8859-1"
        L_0x001c:
            com.samsung.android.gallery.compat.qrencoder.common.Mode r2 = chooseMode(r6, r1)
            com.samsung.android.gallery.compat.qrencoder.common.BitArray r3 = new com.samsung.android.gallery.compat.qrencoder.common.BitArray
            r3.<init>()
            com.samsung.android.gallery.compat.qrencoder.common.Mode r4 = com.samsung.android.gallery.compat.qrencoder.common.Mode.BYTE
            if (r2 != r4) goto L_0x0034
            if (r0 == 0) goto L_0x0034
            com.samsung.android.gallery.compat.qrencoder.CharacterSetECI r0 = com.samsung.android.gallery.compat.qrencoder.CharacterSetECI.getCharacterSetECIByName(r1)
            if (r0 == 0) goto L_0x0034
            appendECI(r0, r3)
        L_0x0034:
            if (r8 == 0) goto L_0x0051
            com.samsung.android.gallery.compat.qrencoder.EncodeHintType r0 = com.samsung.android.gallery.compat.qrencoder.EncodeHintType.GS1_FORMAT
            boolean r5 = r8.containsKey(r0)
            if (r5 == 0) goto L_0x0051
            java.lang.Object r0 = r8.get(r0)
            java.lang.String r0 = r0.toString()
            boolean r0 = java.lang.Boolean.parseBoolean(r0)
            if (r0 == 0) goto L_0x0051
            com.samsung.android.gallery.compat.qrencoder.common.Mode r0 = com.samsung.android.gallery.compat.qrencoder.common.Mode.FNC1_FIRST_POSITION
            appendModeInfo(r0, r3)
        L_0x0051:
            appendModeInfo(r2, r3)
            com.samsung.android.gallery.compat.qrencoder.common.BitArray r0 = new com.samsung.android.gallery.compat.qrencoder.common.BitArray
            r0.<init>()
            appendBytes(r6, r2, r0, r1)
            if (r8 == 0) goto L_0x0089
            com.samsung.android.gallery.compat.qrencoder.EncodeHintType r1 = com.samsung.android.gallery.compat.qrencoder.EncodeHintType.QR_VERSION
            boolean r5 = r8.containsKey(r1)
            if (r5 == 0) goto L_0x0089
            java.lang.Object r1 = r8.get(r1)
            java.lang.String r1 = r1.toString()
            int r1 = java.lang.Integer.parseInt(r1)
            com.samsung.android.gallery.compat.qrencoder.common.Version r1 = com.samsung.android.gallery.compat.qrencoder.common.Version.getVersionForNumber(r1)
            int r5 = calculateBitsNeeded(r2, r3, r0, r1)
            boolean r5 = willFit(r5, r1, r7)
            if (r5 == 0) goto L_0x0081
            goto L_0x008d
        L_0x0081:
            com.samsung.android.gallery.compat.qrencoder.WriterException r6 = new com.samsung.android.gallery.compat.qrencoder.WriterException
            java.lang.String r7 = "Data too big for requested version"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x0089:
            com.samsung.android.gallery.compat.qrencoder.common.Version r1 = recommendVersion(r7, r2, r3, r0)
        L_0x008d:
            com.samsung.android.gallery.compat.qrencoder.common.BitArray r5 = new com.samsung.android.gallery.compat.qrencoder.common.BitArray
            r5.<init>()
            r5.appendBitArray(r3)
            if (r2 != r4) goto L_0x009c
            int r6 = r0.getSizeInBytes()
            goto L_0x00a0
        L_0x009c:
            int r6 = r6.length()
        L_0x00a0:
            appendLengthInfo(r6, r1, r2, r5)
            r5.appendBitArray(r0)
            com.samsung.android.gallery.compat.qrencoder.common.Version$ECBlocks r6 = r1.getECBlocksForLevel(r7)
            int r0 = r1.getTotalCodewords()
            int r3 = r6.getTotalECCodewords()
            int r0 = r0 - r3
            terminateBits(r0, r5)
            int r3 = r1.getTotalCodewords()
            int r6 = r6.getNumBlocks()
            com.samsung.android.gallery.compat.qrencoder.common.BitArray r6 = interleaveWithECBytes(r5, r3, r0, r6)
            com.samsung.android.gallery.compat.qrencoder.QRCode r0 = new com.samsung.android.gallery.compat.qrencoder.QRCode
            r0.<init>()
            r0.setECLevel(r7)
            r0.setMode(r2)
            r0.setVersion(r1)
            int r2 = r1.getDimensionForVersion()
            com.samsung.android.gallery.compat.qrencoder.ByteMatrix r3 = new com.samsung.android.gallery.compat.qrencoder.ByteMatrix
            r3.<init>(r2, r2)
            r2 = -1
            if (r8 == 0) goto L_0x00f7
            com.samsung.android.gallery.compat.qrencoder.EncodeHintType r4 = com.samsung.android.gallery.compat.qrencoder.EncodeHintType.QR_MASK_PATTERN
            boolean r5 = r8.containsKey(r4)
            if (r5 == 0) goto L_0x00f7
            java.lang.Object r8 = r8.get(r4)
            java.lang.String r8 = r8.toString()
            int r8 = java.lang.Integer.parseInt(r8)
            boolean r4 = com.samsung.android.gallery.compat.qrencoder.QRCode.isValidMaskPattern(r8)
            if (r4 == 0) goto L_0x00f7
            goto L_0x00f8
        L_0x00f7:
            r8 = r2
        L_0x00f8:
            if (r8 != r2) goto L_0x00fe
            int r8 = chooseMaskPattern(r6, r7, r1, r3)
        L_0x00fe:
            r0.setMaskPattern(r8)
            com.samsung.android.gallery.compat.qrencoder.MatrixUtil.buildMatrix(r6, r7, r1, r8, r3)
            r0.setMatrix(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.compat.qrencoder.Encoder.encode(java.lang.String, com.samsung.android.gallery.compat.qrencoder.ErrorCorrectionLevel, java.util.Map):com.samsung.android.gallery.compat.qrencoder.QRCode");
    }

    public static byte[] generateECBytes(byte[] bArr, int i2) {
        int length = bArr.length;
        int[] iArr = new int[(length + i2)];
        for (int i7 = 0; i7 < length; i7++) {
            iArr[i7] = bArr[i7] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i2);
        byte[] bArr2 = new byte[i2];
        for (int i8 = 0; i8 < i2; i8++) {
            bArr2[i8] = (byte) iArr[length + i8];
        }
        return bArr2;
    }

    public static int getAlphanumericCode(int i2) {
        int[] iArr = ALPHANUMERIC_TABLE;
        if (i2 < iArr.length) {
            return iArr[i2];
        }
        return -1;
    }

    public static void getNumDataBytesAndNumECBytesForBlockID(int i2, int i7, int i8, int i10, int[] iArr, int[] iArr2) {
        if (i10 < i8) {
            int i11 = i2 % i8;
            int i12 = i8 - i11;
            int i13 = i2 / i8;
            int i14 = i13 + 1;
            int i15 = i7 / i8;
            int i16 = i15 + 1;
            int i17 = i13 - i15;
            int i18 = i14 - i16;
            if (i17 != i18) {
                throw new WriterException("EC bytes mismatch");
            } else if (i8 == i12 + i11) {
                if (i2 != ((i16 + i18) * i11) + ((i15 + i17) * i12)) {
                    throw new WriterException("Total bytes mismatch");
                } else if (i10 < i12) {
                    iArr[0] = i15;
                    iArr2[0] = i17;
                } else {
                    iArr[0] = i16;
                    iArr2[0] = i18;
                }
            } else {
                throw new WriterException("RS blocks mismatch");
            }
        } else {
            throw new WriterException("Block ID too large");
        }
    }

    public static BitArray interleaveWithECBytes(BitArray bitArray, int i2, int i7, int i8) {
        if (bitArray.getSizeInBytes() == i7) {
            ArrayList arrayList = new ArrayList(i8);
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (i10 < i8) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                int i14 = i2;
                int i15 = i7;
                int i16 = i8;
                getNumDataBytesAndNumECBytesForBlockID(i14, i15, i16, i10, iArr, iArr2);
                int i17 = iArr[0];
                byte[] bArr = new byte[i17];
                bitArray.toBytes(i11 * 8, bArr, 0, i17);
                byte[] generateECBytes = generateECBytes(bArr, iArr2[0]);
                arrayList.add(new BlockPair(bArr, generateECBytes));
                i12 = Math.max(i12, i17);
                i13 = Math.max(i13, generateECBytes.length);
                i11 += iArr[0];
                i10++;
                i2 = i14;
                i7 = i15;
                i8 = i16;
            }
            int i18 = i2;
            if (i7 == i11) {
                BitArray bitArray2 = new BitArray();
                for (int i19 = 0; i19 < i12; i19++) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        byte[] dataBytes = ((BlockPair) it.next()).getDataBytes();
                        if (i19 < dataBytes.length) {
                            bitArray2.appendBits(dataBytes[i19], 8);
                        }
                    }
                }
                for (int i20 = 0; i20 < i13; i20++) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        byte[] errorCorrectionBytes = ((BlockPair) it2.next()).getErrorCorrectionBytes();
                        if (i20 < errorCorrectionBytes.length) {
                            bitArray2.appendBits(errorCorrectionBytes[i20], 8);
                        }
                    }
                }
                if (i18 == bitArray2.getSizeInBytes()) {
                    return bitArray2;
                }
                StringBuilder o2 = C0086a.o(i18, "Interleaving error: ", " and ");
                o2.append(bitArray2.getSizeInBytes());
                o2.append(" differ.");
                throw new WriterException(o2.toString());
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    private static boolean isOnlyDoubleByteKanji(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i2 = 0; i2 < length; i2 += 2) {
                byte b = bytes[i2] & 255;
                if ((b < 129 || b > 159) && (b < 224 || b > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    private static Version recommendVersion(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, BitArray bitArray, BitArray bitArray2) {
        return chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, Version.getVersionForNumber(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    public static void terminateBits(int i2, BitArray bitArray) {
        int i7;
        int i8 = i2 * 8;
        if (bitArray.getSize() <= i8) {
            for (int i10 = 0; i10 < 4 && bitArray.getSize() < i8; i10++) {
                bitArray.appendBit(false);
            }
            int size = bitArray.getSize() & 7;
            if (size > 0) {
                while (size < 8) {
                    bitArray.appendBit(false);
                    size++;
                }
            }
            int sizeInBytes = i2 - bitArray.getSizeInBytes();
            for (int i11 = 0; i11 < sizeInBytes; i11++) {
                if ((i11 & 1) == 0) {
                    i7 = 236;
                } else {
                    i7 = 17;
                }
                bitArray.appendBits(i7, 8);
            }
            if (bitArray.getSize() != i8) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + i8);
    }

    private static boolean willFit(int i2, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (version.getTotalCodewords() - version.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i2 + 7) / 8) {
            return true;
        }
        return false;
    }
}
