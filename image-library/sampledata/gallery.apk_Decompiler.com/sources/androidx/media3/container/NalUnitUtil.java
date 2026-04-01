package androidx.media3.container;

import F2.C0040v;
import F2.G;
import F2.N;
import F2.Q;
import F2.U;
import F2.y0;
import I2.b;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static int[] scratchEscapePositions = new int[10];
    private static final Object scratchEscapePositionsLock = new Object();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265LayerInfo {
        public final int layerIdInVps;
        public final int viewId;

        public H265LayerInfo(int i2, int i7) {
            this.layerIdInVps = i2;
            this.viewId = i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265NalHeader {
        public final int layerId;
        public final int nalUnitType;
        public final int temporalId;

        public H265NalHeader(int i2, int i7, int i8) {
            this.nalUnitType = i2;
            this.layerId = i7;
            this.temporalId = i8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265ProfileTierLevel {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;

        public H265ProfileTierLevel(int i2, boolean z, int i7, int i8, int[] iArr, int i10) {
            this.generalProfileSpace = i2;
            this.generalTierFlag = z;
            this.generalProfileIdc = i7;
            this.generalProfileCompatibilityFlags = i8;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i10;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265ProfileTierLevelsAndIndices {
        public final int[] indices;
        public final U profileTierLevels;

        public H265ProfileTierLevelsAndIndices(List<H265ProfileTierLevel> list, int[] iArr) {
            this.profileTierLevels = U.y(list);
            this.indices = iArr;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265RepFormat {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int height;
        public final int width;

        public H265RepFormat(int i2, int i7, int i8, int i10, int i11) {
            this.chromaFormatIdc = i2;
            this.bitDepthLumaMinus8 = i7;
            this.bitDepthChromaMinus8 = i8;
            this.width = i10;
            this.height = i11;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265RepFormatsAndIndices {
        public final int[] indices;
        public final U repFormats;

        public H265RepFormatsAndIndices(List<H265RepFormat> list, int[] iArr) {
            this.repFormats = U.y(list);
            this.indices = iArr;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265Sei3dRefDisplayInfoData {
        public final int exponentRefDisplayWidth;
        public final int exponentRefViewingDist;
        public final int leftViewId;
        public final int mantissaRefDisplayWidth;
        public final int mantissaRefViewingDist;
        public final int numRefDisplays;
        public final int precRefDisplayWidth;
        public final int precRefViewingDist;
        public final int rightViewId;

        public H265Sei3dRefDisplayInfoData(int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15) {
            this.precRefDisplayWidth = i2;
            this.precRefViewingDist = i7;
            this.numRefDisplays = i8;
            this.leftViewId = i10;
            this.rightViewId = i11;
            this.exponentRefDisplayWidth = i12;
            this.mantissaRefDisplayWidth = i13;
            this.exponentRefViewingDist = i14;
            this.mantissaRefViewingDist = i15;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int decodedHeight;
        public final int decodedWidth;
        public final int height;
        public final int maxNumReorderPics;
        public final int maxSubLayersMinus1;
        public final H265NalHeader nalHeader;
        public final float pixelWidthHeightRatio;
        public final H265ProfileTierLevel profileTierLevel;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(H265NalHeader h265NalHeader, int i2, H265ProfileTierLevel h265ProfileTierLevel, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15, float f, int i16, int i17, int i18, int i19) {
            this.nalHeader = h265NalHeader;
            this.maxSubLayersMinus1 = i2;
            this.profileTierLevel = h265ProfileTierLevel;
            this.chromaFormatIdc = i7;
            this.bitDepthLumaMinus8 = i8;
            this.bitDepthChromaMinus8 = i10;
            this.seqParameterSetId = i11;
            this.width = i12;
            this.height = i13;
            this.pixelWidthHeightRatio = f;
            this.maxNumReorderPics = i16;
            this.colorSpace = i17;
            this.colorRange = i18;
            this.colorTransfer = i19;
            this.decodedWidth = i14;
            this.decodedHeight = i15;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265VideoSignalInfo {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;

        public H265VideoSignalInfo(int i2, int i7, int i8) {
            this.colorSpace = i2;
            this.colorRange = i7;
            this.colorTransfer = i8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265VideoSignalInfosAndIndices {
        public final int[] indices;
        public final U videoSignalInfos;

        public H265VideoSignalInfosAndIndices(List<H265VideoSignalInfo> list, int[] iArr) {
            this.videoSignalInfos = U.y(list);
            this.indices = iArr;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class H265VpsData {
        public final U layerInfos;
        public final H265NalHeader nalHeader;
        public final H265ProfileTierLevelsAndIndices profileTierLevelsAndIndices;
        public final H265RepFormatsAndIndices repFormatsAndIndices;
        public final H265VideoSignalInfosAndIndices videoSignalInfosAndIndices;

        public H265VpsData(H265NalHeader h265NalHeader, List<H265LayerInfo> list, H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices, H265RepFormatsAndIndices h265RepFormatsAndIndices, H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices) {
            U u;
            this.nalHeader = h265NalHeader;
            if (list != null) {
                u = U.y(list);
            } else {
                G g = U.e;
                u = y0.f278h;
            }
            this.layerInfos = u;
            this.profileTierLevelsAndIndices = h265ProfileTierLevelsAndIndices;
            this.repFormatsAndIndices = h265RepFormatsAndIndices;
            this.videoSignalInfosAndIndices = h265VideoSignalInfosAndIndices;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i2, int i7, boolean z) {
            this.picParameterSetId = i2;
            this.seqParameterSetId = i7;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i2, int i7, int i8, int i10, int i11, int i12, int i13, float f, int i14, int i15, boolean z, boolean z3, int i16, int i17, int i18, boolean z7, int i19, int i20, int i21, int i22) {
            this.profileIdc = i2;
            this.constraintsFlagsAndReservedZero2Bits = i7;
            this.levelIdc = i8;
            this.seqParameterSetId = i10;
            this.maxNumRefFrames = i11;
            this.width = i12;
            this.height = i13;
            this.pixelWidthHeightRatio = f;
            this.bitDepthLumaMinus8 = i14;
            this.bitDepthChromaMinus8 = i15;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z3;
            this.frameNumLength = i16;
            this.picOrderCountType = i17;
            this.picOrderCntLsbLength = i18;
            this.deltaPicOrderAlwaysZeroFlag = z7;
            this.colorSpace = i19;
            this.colorRange = i20;
            this.colorTransfer = i21;
            this.maxNumReorderFrames = i22;
        }
    }

    private static int applyConformanceWindowToHeight(int i2, int i7, int i8, int i10) {
        int i11 = 1;
        if (i7 == 1) {
            i11 = 2;
        }
        return i2 - ((i8 + i10) * i11);
    }

    private static int applyConformanceWindowToWidth(int i2, int i7, int i8, int i10) {
        int i11 = 2;
        if (!(i7 == 1 || i7 == 2)) {
            i11 = 1;
        }
        return i2 - ((i8 + i10) * i11);
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static String createCodecStringFromH265SpsPalyoad(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(4);
        int readBits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        H265ProfileTierLevel parseH265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, readBits, (H265ProfileTierLevel) null);
        return CodecSpecificDataUtil.buildHevcCodecString(parseH265ProfileTierLevel.generalProfileSpace, parseH265ProfileTierLevel.generalTierFlag, parseH265ProfileTierLevel.generalProfileIdc, parseH265ProfileTierLevel.generalProfileCompatibilityFlags, parseH265ProfileTierLevel.constraintBytes, parseH265ProfileTierLevel.generalLevelIdc);
    }

    public static int findNalUnit(byte[] bArr, int i2, int i7, boolean[] zArr) {
        boolean z;
        boolean z3;
        boolean z7;
        int i8 = i7 - i2;
        boolean z9 = false;
        if (i8 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        if (i8 == 0) {
            return i7;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i2 - 3;
        } else if (i8 > 1 && zArr[1] && bArr[i2] == 1) {
            clearPrefixFlags(zArr);
            return i2 - 2;
        } else if (i8 <= 2 || !zArr[2] || bArr[i2] != 0 || bArr[i2 + 1] != 1) {
            int i10 = i7 - 1;
            int i11 = i2 + 2;
            while (i11 < i10) {
                byte b = bArr[i11];
                if ((b & 254) == 0) {
                    int i12 = i11 - 2;
                    if (bArr[i12] == 0 && bArr[i11 - 1] == 0 && b == 1) {
                        clearPrefixFlags(zArr);
                        return i12;
                    }
                    i11 -= 2;
                }
                i11 += 3;
            }
            if (i8 <= 2 ? i8 != 2 ? !zArr[1] || bArr[i10] != 1 : !(zArr[2] && bArr[i7 - 2] == 0 && bArr[i10] == 1) : !(bArr[i7 - 3] == 0 && bArr[i7 - 2] == 0 && bArr[i10] == 1)) {
                z3 = false;
            } else {
                z3 = true;
            }
            zArr[0] = z3;
            if (i8 <= 1 ? !zArr[2] || bArr[i10] != 0 : !(bArr[i7 - 2] == 0 && bArr[i10] == 0)) {
                z7 = false;
            } else {
                z7 = true;
            }
            zArr[1] = z7;
            if (bArr[i10] == 0) {
                z9 = true;
            }
            zArr[2] = z9;
            return i7;
        } else {
            clearPrefixFlags(zArr);
            return i2 - 1;
        }
    }

    private static U findNalUnitPositions(byte[] bArr) {
        boolean[] zArr = new boolean[3];
        Q x9 = U.x();
        int i2 = 0;
        while (i2 < bArr.length) {
            int findNalUnit = findNalUnit(bArr, i2, bArr.length, zArr);
            if (findNalUnit != bArr.length) {
                x9.a(Integer.valueOf(findNalUnit));
            }
            i2 = findNalUnit + 3;
        }
        return x9.f();
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i2, int i7) {
        while (i2 < i7 - 2) {
            if (bArr[i2] == 0 && bArr[i2 + 1] == 0 && bArr[i2 + 2] == 3) {
                return i2;
            }
            i2++;
        }
        return i7;
    }

    public static String getH265BaseLayerCodecsString(List<byte[]> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            byte[] bArr = list.get(i2);
            int length = bArr.length;
            if (length > 3) {
                U findNalUnitPositions = findNalUnitPositions(bArr);
                for (int i7 = 0; i7 < findNalUnitPositions.size(); i7++) {
                    if (((Integer) findNalUnitPositions.get(i7)).intValue() + 3 < length) {
                        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, ((Integer) findNalUnitPositions.get(i7)).intValue() + 3, length);
                        H265NalHeader parseH265NalHeader = parseH265NalHeader(parsableNalUnitBitArray);
                        if (parseH265NalHeader.nalUnitType == 33 && parseH265NalHeader.layerId == 0) {
                            return createCodecStringFromH265SpsPalyoad(parsableNalUnitBitArray);
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static int getH265NalUnitType(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 126) >> 1;
    }

    public static int getNalUnitType(byte[] bArr, int i2) {
        return bArr[i2 + 3] & 31;
    }

    public static boolean isDependedOn(byte[] bArr, int i2, int i7, Format format) {
        if (Objects.equals(format.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264)) {
            return isH264NalUnitDependedOn(bArr[i2]);
        }
        if (Objects.equals(format.sampleMimeType, "video/hevc")) {
            return isH265NalUnitDependedOn(bArr, i2, i7, format);
        }
        return true;
    }

    public static boolean isH264NalUnitDependedOn(byte b) {
        if (((b & 96) >> 5) != 0) {
            return true;
        }
        byte b5 = b & 31;
        if (b5 == 1 || b5 == 9 || b5 == 14) {
            return false;
        }
        return true;
    }

    private static boolean isH265NalUnitDependedOn(byte[] bArr, int i2, int i7, Format format) {
        H265NalHeader parseH265NalHeader = parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i2, i7 + i2));
        int i8 = parseH265NalHeader.nalUnitType;
        if (i8 == 35) {
            return false;
        }
        if (i8 <= 14 && i8 % 2 == 0 && parseH265NalHeader.temporalId == format.maxSubLayers - 1) {
            return false;
        }
        return true;
    }

    public static boolean isNalUnitSei(Format format, byte b) {
        if (((Objects.equals(format.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264) || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, Encode.CodecsMime.VIDEO_CODEC_H264)) && (b & 31) == 6) || ((Objects.equals(format.sampleMimeType, "video/hevc") || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, "video/hevc")) && ((b & 126) >> 1) == 39)) {
            return true;
        }
        return false;
    }

    public static int numberOfBytesInNalUnitHeader(Format format) {
        if (Objects.equals(format.sampleMimeType, Encode.CodecsMime.VIDEO_CODEC_H264)) {
            return 1;
        }
        if (Objects.equals(format.sampleMimeType, "video/hevc") || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, "video/hevc")) {
            return 2;
        }
        return 0;
    }

    private static H265NalHeader parseH265NalHeader(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBit();
        return new H265NalHeader(parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(3) - 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.container.NalUnitUtil.H265ProfileTierLevel parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray r19, boolean r20, int r21, androidx.media3.container.NalUnitUtil.H265ProfileTierLevel r22) {
        /*
            r0 = r19
            r1 = r21
            r2 = r22
            r3 = 6
            int[] r4 = new int[r3]
            r5 = 2
            r6 = 8
            r7 = 0
            if (r20 == 0) goto L_0x0042
            int r2 = r0.readBits(r5)
            boolean r8 = r0.readBit()
            r9 = 5
            int r9 = r0.readBits(r9)
            r10 = r7
            r11 = r10
        L_0x001e:
            r12 = 32
            if (r10 >= r12) goto L_0x002e
            boolean r12 = r0.readBit()
            if (r12 == 0) goto L_0x002b
            r12 = 1
            int r12 = r12 << r10
            r11 = r11 | r12
        L_0x002b:
            int r10 = r10 + 1
            goto L_0x001e
        L_0x002e:
            r10 = r7
        L_0x002f:
            if (r10 >= r3) goto L_0x003a
            int r12 = r0.readBits(r6)
            r4[r10] = r12
            int r10 = r10 + 1
            goto L_0x002f
        L_0x003a:
            r13 = r2
        L_0x003b:
            r17 = r4
            r14 = r8
            r15 = r9
            r16 = r11
            goto L_0x0057
        L_0x0042:
            if (r2 == 0) goto L_0x0050
            int r3 = r2.generalProfileSpace
            boolean r8 = r2.generalTierFlag
            int r9 = r2.generalProfileIdc
            int r11 = r2.generalProfileCompatibilityFlags
            int[] r4 = r2.constraintBytes
            r13 = r3
            goto L_0x003b
        L_0x0050:
            r17 = r4
            r13 = r7
            r14 = r13
            r15 = r14
            r16 = r15
        L_0x0057:
            int r18 = r0.readBits(r6)
            r2 = r7
        L_0x005c:
            if (r7 >= r1) goto L_0x0071
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L_0x0066
            int r2 = r2 + 88
        L_0x0066:
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L_0x006e
            int r2 = r2 + 8
        L_0x006e:
            int r7 = r7 + 1
            goto L_0x005c
        L_0x0071:
            r0.skipBits(r2)
            if (r1 <= 0) goto L_0x007b
            int r6 = r6 - r1
            int r6 = r6 * r5
            r0.skipBits(r6)
        L_0x007b:
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r12 = new androidx.media3.container.NalUnitUtil$H265ProfileTierLevel
            r12.<init>(r13, r14, r15, r16, r17, r18)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray, boolean, int, androidx.media3.container.NalUnitUtil$H265ProfileTierLevel):androidx.media3.container.NalUnitUtil$H265ProfileTierLevel");
    }

    private static H265RepFormat parseH265RepFormat(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i2;
        int i7;
        int i8;
        int readBits = parsableNalUnitBitArray.readBits(16);
        int readBits2 = parsableNalUnitBitArray.readBits(16);
        if (parsableNalUnitBitArray.readBit()) {
            int readBits3 = parsableNalUnitBitArray.readBits(2);
            if (readBits3 == 3) {
                parsableNalUnitBitArray.skipBit();
            }
            int readBits4 = parsableNalUnitBitArray.readBits(4);
            i2 = parsableNalUnitBitArray.readBits(4);
            i7 = readBits4;
            i8 = readBits3;
        } else {
            i8 = 0;
            i7 = 0;
            i2 = 0;
        }
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            readBits = applyConformanceWindowToWidth(readBits, i8, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2);
            readBits2 = applyConformanceWindowToHeight(readBits2, i8, readUnsignedExpGolombCodedInt3, readUnsignedExpGolombCodedInt4);
        }
        return new H265RepFormat(i8, i7, i2, readBits, readBits2);
    }

    private static H265RepFormatsAndIndices parseH265RepFormatsAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i2) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i7 = readUnsignedExpGolombCodedInt + 1;
        G g = U.e;
        C0040v.c(i7, "expectedSize");
        C0040v.c(i7, "initialCapacity");
        Object[] objArr = new Object[i7];
        int[] iArr = new int[i2];
        int i8 = 0;
        int i10 = 0;
        boolean z = false;
        while (i8 < i7) {
            H265RepFormat parseH265RepFormat = parseH265RepFormat(parsableNalUnitBitArray);
            parseH265RepFormat.getClass();
            int e = N.e(objArr.length, i10 + 1);
            if (e > objArr.length || z) {
                objArr = Arrays.copyOf(objArr, e);
                z = false;
            }
            objArr[i10] = parseH265RepFormat;
            i8++;
            i10++;
        }
        int i11 = 1;
        if (i7 <= 1 || !parsableNalUnitBitArray.readBit()) {
            while (i11 < i2) {
                iArr[i11] = Math.min(i11, readUnsignedExpGolombCodedInt);
                i11++;
            }
        } else {
            RoundingMode roundingMode = RoundingMode.CEILING;
            int c5 = b.c((double) i7);
            while (i11 < i2) {
                iArr[i11] = parsableNalUnitBitArray.readBits(c5);
                i11++;
            }
        }
        return new H265RepFormatsAndIndices(U.w(i10, objArr), iArr);
    }

    public static H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo(byte[] bArr, int i2, int i7) {
        byte b;
        int i8;
        int i10;
        int i11;
        byte[] bArr2 = bArr;
        int i12 = i2 + 2;
        int i13 = i7 - 1;
        while (true) {
            b = bArr2[i13];
            if (b == 0 && i13 > i12) {
                i13--;
            }
        }
        if (b != 0 && i13 > i12) {
            ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr2, i12, i13 + 1);
            while (parsableNalUnitBitArray.canReadBits(16)) {
                int readBits = parsableNalUnitBitArray.readBits(8);
                int i14 = 0;
                while (readBits == 255) {
                    i14 += ScoverState.TYPE_NFC_SMART_COVER;
                    readBits = parsableNalUnitBitArray.readBits(8);
                }
                int i15 = i14 + readBits;
                int readBits2 = parsableNalUnitBitArray.readBits(8);
                int i16 = 0;
                while (readBits2 == 255) {
                    i16 += ScoverState.TYPE_NFC_SMART_COVER;
                    readBits2 = parsableNalUnitBitArray.readBits(8);
                }
                int i17 = i16 + readBits2;
                if (i17 == 0 || !parsableNalUnitBitArray.canReadBits(i17)) {
                    break;
                } else if (i15 == 176) {
                    int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    boolean readBit = parsableNalUnitBitArray.readBit();
                    if (readBit) {
                        i8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    } else {
                        i8 = 0;
                    }
                    int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int i18 = -1;
                    int i19 = -1;
                    int i20 = -1;
                    int i21 = -1;
                    int i22 = -1;
                    int i23 = -1;
                    for (int i24 = 0; i24 <= readUnsignedExpGolombCodedInt2; i24++) {
                        i18 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        i19 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        i20 = parsableNalUnitBitArray.readBits(6);
                        if (i20 == 63) {
                            return null;
                        }
                        if (i20 == 0) {
                            i10 = Math.max(0, readUnsignedExpGolombCodedInt - 30);
                        } else {
                            i10 = Math.max(0, (i20 + readUnsignedExpGolombCodedInt) - 31);
                        }
                        i21 = parsableNalUnitBitArray.readBits(i10);
                        if (readBit) {
                            int readBits3 = parsableNalUnitBitArray.readBits(6);
                            if (readBits3 == 63) {
                                return null;
                            }
                            if (readBits3 == 0) {
                                i11 = Math.max(0, i8 - 30);
                            } else {
                                i11 = Math.max(0, (readBits3 + i8) - 31);
                            }
                            i22 = readBits3;
                            i23 = parsableNalUnitBitArray.readBits(i11);
                        }
                        if (parsableNalUnitBitArray.readBit()) {
                            parsableNalUnitBitArray.skipBits(10);
                        }
                    }
                    return new H265Sei3dRefDisplayInfoData(readUnsignedExpGolombCodedInt, i8, readUnsignedExpGolombCodedInt2 + 1, i18, i19, i20, i21, i22, i23);
                } else {
                    parsableNalUnitBitArray.skipBits(i17 * 8);
                }
            }
        }
        return null;
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i2, int i7, H265VpsData h265VpsData) {
        return parseH265SpsNalUnitPayload(bArr, i2 + 2, i7, parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i2, i7)), h265VpsData);
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0236  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.container.NalUnitUtil.H265SpsData parseH265SpsNalUnitPayload(byte[] r20, int r21, int r22, androidx.media3.container.NalUnitUtil.H265NalHeader r23, androidx.media3.container.NalUnitUtil.H265VpsData r24) {
        /*
            r1 = r23
            r0 = r24
            androidx.media3.container.ParsableNalUnitBitArray r2 = new androidx.media3.container.ParsableNalUnitBitArray
            r3 = r20
            r4 = r21
            r5 = r22
            r2.<init>(r3, r4, r5)
            r3 = 4
            r2.skipBits(r3)
            r3 = 3
            int r4 = r2.readBits(r3)
            int r5 = r1.layerId
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0023
            r5 = 7
            if (r4 != r5) goto L_0x0023
            r5 = r6
            goto L_0x0024
        L_0x0023:
            r5 = r7
        L_0x0024:
            if (r0 == 0) goto L_0x0046
            F2.U r8 = r0.layerInfos
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x0046
            int r8 = r1.layerId
            F2.U r9 = r0.layerInfos
            int r9 = r9.size()
            int r9 = r9 - r6
            int r8 = java.lang.Math.min(r8, r9)
            F2.U r9 = r0.layerInfos
            java.lang.Object r8 = r9.get(r8)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r8 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r8
            int r8 = r8.layerIdInVps
            goto L_0x0047
        L_0x0046:
            r8 = r7
        L_0x0047:
            r9 = 0
            if (r5 != 0) goto L_0x0053
            r2.skipBit()
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r9 = parseH265ProfileTierLevel(r2, r6, r4, r9)
        L_0x0051:
            r10 = r7
            goto L_0x006e
        L_0x0053:
            if (r0 == 0) goto L_0x0051
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r10 = r0.profileTierLevelsAndIndices
            int[] r11 = r10.indices
            r11 = r11[r8]
            F2.U r10 = r10.profileTierLevels
            int r10 = r10.size()
            if (r10 <= r11) goto L_0x0051
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r9 = r0.profileTierLevelsAndIndices
            F2.U r9 = r9.profileTierLevels
            java.lang.Object r9 = r9.get(r11)
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r9 = (androidx.media3.container.NalUnitUtil.H265ProfileTierLevel) r9
            goto L_0x0051
        L_0x006e:
            int r7 = r2.readUnsignedExpGolombCodedInt()
            r11 = 8
            r12 = -1
            if (r5 == 0) goto L_0x00b8
            boolean r13 = r2.readBit()
            if (r13 == 0) goto L_0x0082
            int r13 = r2.readBits(r11)
            goto L_0x0083
        L_0x0082:
            r13 = r12
        L_0x0083:
            if (r0 == 0) goto L_0x00b0
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r14 = r0.repFormatsAndIndices
            if (r14 == 0) goto L_0x00b0
            if (r13 != r12) goto L_0x008f
            int[] r13 = r14.indices
            r13 = r13[r8]
        L_0x008f:
            if (r13 == r12) goto L_0x00b0
            F2.U r14 = r14.repFormats
            int r14 = r14.size()
            if (r14 <= r13) goto L_0x00b0
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r14 = r0.repFormatsAndIndices
            F2.U r14 = r14.repFormats
            java.lang.Object r13 = r14.get(r13)
            androidx.media3.container.NalUnitUtil$H265RepFormat r13 = (androidx.media3.container.NalUnitUtil.H265RepFormat) r13
            int r14 = r13.chromaFormatIdc
            int r15 = r13.width
            int r6 = r13.height
            int r10 = r13.bitDepthLumaMinus8
            int r13 = r13.bitDepthChromaMinus8
            r3 = r6
            r12 = r15
            goto L_0x00fd
        L_0x00b0:
            r3 = 0
            r6 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            goto L_0x00fd
        L_0x00b8:
            int r14 = r2.readUnsignedExpGolombCodedInt()
            if (r14 != r3) goto L_0x00c1
            r2.skipBit()
        L_0x00c1:
            int r15 = r2.readUnsignedExpGolombCodedInt()
            int r6 = r2.readUnsignedExpGolombCodedInt()
            boolean r10 = r2.readBit()
            if (r10 == 0) goto L_0x00e8
            int r10 = r2.readUnsignedExpGolombCodedInt()
            int r13 = r2.readUnsignedExpGolombCodedInt()
            int r12 = r2.readUnsignedExpGolombCodedInt()
            int r3 = r2.readUnsignedExpGolombCodedInt()
            int r10 = applyConformanceWindowToWidth(r15, r14, r10, r13)
            int r3 = applyConformanceWindowToHeight(r6, r14, r12, r3)
            goto L_0x00ea
        L_0x00e8:
            r3 = r6
            r10 = r15
        L_0x00ea:
            int r12 = r2.readUnsignedExpGolombCodedInt()
            int r13 = r2.readUnsignedExpGolombCodedInt()
            r19 = r6
            r6 = r3
            r3 = r19
            r19 = r15
            r15 = r10
            r10 = r12
            r12 = r19
        L_0x00fd:
            int r17 = r2.readUnsignedExpGolombCodedInt()
            if (r5 != 0) goto L_0x012b
            boolean r18 = r2.readBit()
            if (r18 == 0) goto L_0x010c
            r18 = 0
            goto L_0x010e
        L_0x010c:
            r18 = r4
        L_0x010e:
            r11 = r18
            r1 = -1
        L_0x0111:
            if (r11 > r4) goto L_0x0128
            r2.readUnsignedExpGolombCodedInt()
            r18 = r3
            int r3 = r2.readUnsignedExpGolombCodedInt()
            int r1 = java.lang.Math.max(r3, r1)
            r2.readUnsignedExpGolombCodedInt()
            int r11 = r11 + 1
            r3 = r18
            goto L_0x0111
        L_0x0128:
            r18 = r3
            goto L_0x012d
        L_0x012b:
            r1 = -1
            goto L_0x0128
        L_0x012d:
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            boolean r3 = r2.readBit()
            if (r3 == 0) goto L_0x015d
            if (r5 == 0) goto L_0x014c
            boolean r3 = r2.readBit()
            goto L_0x014d
        L_0x014c:
            r3 = 0
        L_0x014d:
            if (r3 == 0) goto L_0x0154
            r3 = 6
            r2.skipBits(r3)
            goto L_0x015d
        L_0x0154:
            boolean r3 = r2.readBit()
            if (r3 == 0) goto L_0x015d
            skipH265ScalingList(r2)
        L_0x015d:
            r3 = 2
            r2.skipBits(r3)
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x0175
            r5 = 8
            r2.skipBits(r5)
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.skipBit()
        L_0x0175:
            skipH265ShortTermReferencePictureSets(r2)
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x018e
            int r5 = r2.readUnsignedExpGolombCodedInt()
            r11 = 0
        L_0x0183:
            if (r11 >= r5) goto L_0x018e
            int r3 = r17 + 5
            r2.skipBits(r3)
            int r11 = r11 + 1
            r3 = 2
            goto L_0x0183
        L_0x018e:
            r2.skipBits(r3)
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x024f
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x01c9
            r5 = 8
            int r3 = r2.readBits(r5)
            r5 = 255(0xff, float:3.57E-43)
            if (r3 != r5) goto L_0x01ba
            r3 = 16
            int r5 = r2.readBits(r3)
            int r3 = r2.readBits(r3)
            if (r5 == 0) goto L_0x01c9
            if (r3 == 0) goto L_0x01c9
            float r5 = (float) r5
            float r3 = (float) r3
            float r11 = r5 / r3
            goto L_0x01cb
        L_0x01ba:
            float[] r5 = ASPECT_RATIO_IDC_VALUES
            int r11 = r5.length
            if (r3 >= r11) goto L_0x01c2
            r11 = r5[r3]
            goto L_0x01cb
        L_0x01c2:
            java.lang.String r5 = "NalUnitUtil"
            java.lang.String r11 = "Unexpected aspect_ratio_idc value: "
            A.a.D(r3, r11, r5)
        L_0x01c9:
            r11 = 1065353216(0x3f800000, float:1.0)
        L_0x01cb:
            boolean r3 = r2.readBit()
            if (r3 == 0) goto L_0x01d4
            r2.skipBit()
        L_0x01d4:
            boolean r3 = r2.readBit()
            if (r3 == 0) goto L_0x0206
            r3 = 3
            r2.skipBits(r3)
            boolean r0 = r2.readBit()
            if (r0 == 0) goto L_0x01e6
            r0 = 1
            goto L_0x01e7
        L_0x01e6:
            r0 = 2
        L_0x01e7:
            boolean r3 = r2.readBit()
            if (r3 == 0) goto L_0x0203
            r5 = 8
            int r3 = r2.readBits(r5)
            int r8 = r2.readBits(r5)
            r2.skipBits(r5)
            int r3 = androidx.media3.common.ColorInfo.isoColorPrimariesToColorSpace(r3)
            int r5 = androidx.media3.common.ColorInfo.isoTransferCharacteristicsToColorTransfer(r8)
            goto L_0x0230
        L_0x0203:
            r3 = -1
            r5 = -1
            goto L_0x0230
        L_0x0206:
            if (r0 == 0) goto L_0x022e
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r3 = r0.videoSignalInfosAndIndices
            if (r3 == 0) goto L_0x022e
            int[] r5 = r3.indices
            r5 = r5[r8]
            F2.U r3 = r3.videoSignalInfos
            int r3 = r3.size()
            if (r3 <= r5) goto L_0x022e
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r0 = r0.videoSignalInfosAndIndices
            F2.U r0 = r0.videoSignalInfos
            java.lang.Object r0 = r0.get(r5)
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfo r0 = (androidx.media3.container.NalUnitUtil.H265VideoSignalInfo) r0
            int r3 = r0.colorSpace
            int r5 = r0.colorRange
            int r0 = r0.colorTransfer
            r19 = r5
            r5 = r0
            r0 = r19
            goto L_0x0230
        L_0x022e:
            r0 = -1
            goto L_0x0203
        L_0x0230:
            boolean r8 = r2.readBit()
            if (r8 == 0) goto L_0x023c
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
        L_0x023c:
            r2.skipBit()
            boolean r2 = r2.readBit()
            if (r2 == 0) goto L_0x0247
            int r6 = r6 * 2
        L_0x0247:
            r2 = r4
            r16 = r5
            r4 = r14
            r8 = r15
            r15 = r0
            r14 = r3
            goto L_0x0258
        L_0x024f:
            r2 = r4
            r4 = r14
            r8 = r15
            r11 = 1065353216(0x3f800000, float:1.0)
            r14 = -1
            r15 = -1
            r16 = -1
        L_0x0258:
            androidx.media3.container.NalUnitUtil$H265SpsData r0 = new androidx.media3.container.NalUnitUtil$H265SpsData
            r3 = r9
            r5 = r10
            r10 = r12
            r9 = r6
            r12 = r11
            r6 = r13
            r11 = r18
            r13 = r1
            r1 = r23
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265SpsNalUnitPayload(byte[], int, int, androidx.media3.container.NalUnitUtil$H265NalHeader, androidx.media3.container.NalUnitUtil$H265VpsData):androidx.media3.container.NalUnitUtil$H265SpsData");
    }

    private static H265VideoSignalInfo parseH265VideoSignalInfo(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i2;
        parsableNalUnitBitArray.skipBits(3);
        if (parsableNalUnitBitArray.readBit()) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(parsableNalUnitBitArray.readBits(8));
        int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(parsableNalUnitBitArray.readBits(8));
        parsableNalUnitBitArray.skipBits(8);
        return new H265VideoSignalInfo(isoColorPrimariesToColorSpace, i2, isoTransferCharacteristicsToColorTransfer);
    }

    private static H265VideoSignalInfosAndIndices parseH265VideoSignalInfosAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i2, int i7, int[] iArr) {
        boolean z;
        int i8;
        boolean z3;
        boolean z7;
        if (!parsableNalUnitBitArray.readBit()) {
            z = parsableNalUnitBitArray.readBit();
        } else {
            z = true;
        }
        if (z) {
            parsableNalUnitBitArray.skipBit();
        }
        boolean readBit = parsableNalUnitBitArray.readBit();
        boolean readBit2 = parsableNalUnitBitArray.readBit();
        if (readBit || readBit2) {
            for (int i10 = 0; i10 < i7; i10++) {
                for (int i11 = 0; i11 < iArr[i10]; i11++) {
                    if (readBit) {
                        z3 = parsableNalUnitBitArray.readBit();
                    } else {
                        z3 = false;
                    }
                    if (readBit2) {
                        z7 = parsableNalUnitBitArray.readBit();
                    } else {
                        z7 = false;
                    }
                    if (z3) {
                        parsableNalUnitBitArray.skipBits(32);
                    }
                    if (z7) {
                        parsableNalUnitBitArray.skipBits(18);
                    }
                }
            }
        }
        boolean readBit3 = parsableNalUnitBitArray.readBit();
        if (readBit3) {
            i8 = parsableNalUnitBitArray.readBits(4) + 1;
        } else {
            i8 = i2;
        }
        G g = U.e;
        C0040v.c(i8, "expectedSize");
        C0040v.c(i8, "initialCapacity");
        Object[] objArr = new Object[i8];
        int[] iArr2 = new int[i2];
        int i12 = 0;
        int i13 = 0;
        boolean z9 = false;
        while (i12 < i8) {
            H265VideoSignalInfo parseH265VideoSignalInfo = parseH265VideoSignalInfo(parsableNalUnitBitArray);
            parseH265VideoSignalInfo.getClass();
            int e = N.e(objArr.length, i13 + 1);
            if (e > objArr.length || z9) {
                objArr = Arrays.copyOf(objArr, e);
                z9 = false;
            }
            objArr[i13] = parseH265VideoSignalInfo;
            i12++;
            i13++;
        }
        if (readBit3 && i8 > 1) {
            for (int i14 = 0; i14 < i2; i14++) {
                iArr2[i14] = parsableNalUnitBitArray.readBits(4);
            }
        }
        return new H265VideoSignalInfosAndIndices(U.w(i13, objArr), iArr2);
    }

    public static H265VpsData parseH265VpsNalUnit(byte[] bArr, int i2, int i7) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i2, i7);
        return parseH265VpsNalUnitPayload(parsableNalUnitBitArray, parseH265NalHeader(parsableNalUnitBitArray));
    }

    private static H265VpsData parseH265VpsNalUnitPayload(ParsableNalUnitBitArray parsableNalUnitBitArray, H265NalHeader h265NalHeader) {
        int i2;
        boolean z;
        boolean z3;
        boolean z7;
        int[] iArr;
        int i7;
        int i8;
        int[] iArr2;
        H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices;
        int i10;
        int i11;
        int[] iArr3;
        int i12;
        y0 y0Var;
        int[] iArr4;
        boolean[][] zArr;
        int i13;
        boolean z9;
        int i14;
        int i15;
        int i16;
        ParsableNalUnitBitArray parsableNalUnitBitArray2 = parsableNalUnitBitArray;
        parsableNalUnitBitArray2.skipBits(4);
        boolean readBit = parsableNalUnitBitArray2.readBit();
        boolean readBit2 = parsableNalUnitBitArray2.readBit();
        int readBits = parsableNalUnitBitArray2.readBits(6);
        int i17 = readBits + 1;
        int readBits2 = parsableNalUnitBitArray2.readBits(3);
        parsableNalUnitBitArray2.skipBits(17);
        H265ProfileTierLevel parseH265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray2, true, readBits2, (H265ProfileTierLevel) null);
        boolean z10 = false;
        if (parsableNalUnitBitArray2.readBit()) {
            i2 = 0;
        } else {
            i2 = readBits2;
        }
        while (i2 <= readBits2) {
            parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt();
            i2++;
        }
        int readBits3 = parsableNalUnitBitArray2.readBits(6);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt() + 1;
        H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices = new H265ProfileTierLevelsAndIndices(U.B(parseH265ProfileTierLevel), new int[1]);
        if (i17 < 2 || readUnsignedExpGolombCodedInt < 2) {
            z = false;
        } else {
            z = true;
        }
        if (!readBit || !readBit2) {
            z3 = false;
        } else {
            z3 = true;
        }
        int i18 = readBits3 + 1;
        if (i18 >= i17) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (!z || !z3 || !z7) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int[] iArr5 = new int[2];
        iArr5[1] = i18;
        iArr5[0] = readUnsignedExpGolombCodedInt;
        Class cls = Integer.TYPE;
        int[][] iArr6 = (int[][]) Array.newInstance(cls, iArr5);
        int i19 = 1;
        int[] iArr7 = new int[readUnsignedExpGolombCodedInt];
        int[] iArr8 = new int[readUnsignedExpGolombCodedInt];
        iArr6[0][0] = 0;
        iArr7[0] = 1;
        iArr8[0] = 0;
        for (int i20 = 1; i20 < readUnsignedExpGolombCodedInt; i20++) {
            int i21 = 0;
            for (int i22 = 0; i22 <= readBits3; i22++) {
                if (parsableNalUnitBitArray2.readBit()) {
                    iArr6[i20][i21] = i22;
                    iArr8[i20] = i22;
                    i21++;
                }
                iArr7[i20] = i21;
            }
        }
        if (parsableNalUnitBitArray2.readBit()) {
            parsableNalUnitBitArray2.skipBits(64);
            if (parsableNalUnitBitArray2.readBit()) {
                parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt();
            }
            int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt();
            int i23 = 0;
            while (i23 < readUnsignedExpGolombCodedInt2) {
                parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt();
                if (i23 == 0 || parsableNalUnitBitArray2.readBit()) {
                    z10 = true;
                }
                skipH265HrdParameters(parsableNalUnitBitArray2, z10, readBits2);
                i23++;
                z10 = false;
            }
        }
        if (!parsableNalUnitBitArray2.readBit()) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices2 = h265ProfileTierLevelsAndIndices;
        parsableNalUnitBitArray2.byteAlign();
        H265ProfileTierLevel parseH265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray2, false, readBits2, parseH265ProfileTierLevel);
        boolean readBit3 = parsableNalUnitBitArray2.readBit();
        int i24 = 6;
        boolean[] zArr2 = new boolean[16];
        int[][] iArr9 = iArr6;
        int i25 = 0;
        for (int i26 = 0; i26 < 16; i26++) {
            boolean readBit4 = parsableNalUnitBitArray2.readBit();
            zArr2[i26] = readBit4;
            if (readBit4) {
                i25++;
            }
        }
        if (i25 == 0 || !zArr2[1]) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int[] iArr10 = new int[i25];
        for (int i27 = 0; i27 < i25 - (readBit3 ? 1 : 0); i27++) {
            iArr10[i27] = parsableNalUnitBitArray2.readBits(3);
        }
        int[] iArr11 = new int[(i25 + 1)];
        if (readBit3) {
            int i28 = 1;
            while (i28 < i25) {
                int[] iArr12 = iArr11;
                for (int i29 = 0; i29 < i28; i29++) {
                    iArr12[i28] = iArr10[i29] + 1 + iArr12[i28];
                }
                i28++;
                iArr11 = iArr12;
            }
            iArr = iArr11;
            iArr[i25] = 6;
        } else {
            iArr = iArr11;
        }
        int[] iArr13 = new int[2];
        iArr13[1] = i25;
        iArr13[0] = i17;
        int[] iArr14 = new int[i17];
        iArr14[0] = 0;
        boolean readBit5 = parsableNalUnitBitArray2.readBit();
        int[][] iArr15 = (int[][]) Array.newInstance(cls, iArr13);
        int i30 = 1;
        while (i30 < i17) {
            if (readBit5) {
                i16 = i30;
                iArr14[i16] = parsableNalUnitBitArray2.readBits(i24);
            } else {
                i16 = i30;
                int i31 = i24;
                iArr14[i16] = i16;
            }
            if (!readBit3) {
                int i32 = 0;
                while (i32 < i25) {
                    int i33 = i32;
                    iArr15[i16][i33] = parsableNalUnitBitArray2.readBits(iArr10[i32] + 1);
                    i32 = i33 + 1;
                }
            } else {
                int i34 = 0;
                while (i34 < i25) {
                    int i35 = i34 + 1;
                    iArr15[i16][i34] = (iArr14[i16] & ((1 << iArr[i35]) - 1)) >> iArr[i34];
                    i34 = i35;
                }
            }
            i30 = i16 + 1;
            i24 = 6;
        }
        int[] iArr16 = new int[i18];
        int i36 = 1;
        int i37 = 0;
        while (i37 < i17) {
            iArr16[iArr14[i37]] = -1;
            int[] iArr17 = iArr16;
            int i38 = 0;
            int i39 = 0;
            while (i38 < 16) {
                if (zArr2[i38]) {
                    if (i38 == i19) {
                        iArr17[iArr14[i37]] = iArr15[i37][i39];
                    }
                    i39++;
                }
                i38++;
                i19 = 1;
            }
            if (i37 > 0) {
                int i40 = 0;
                while (true) {
                    if (i40 >= i37) {
                        i36++;
                        break;
                    }
                    int i41 = i40;
                    if (iArr17[iArr14[i37]] == iArr17[iArr14[i40]]) {
                        break;
                    }
                    i40 = i41 + 1;
                }
            }
            i37++;
            iArr16 = iArr17;
            i19 = 1;
        }
        int[] iArr18 = iArr16;
        int readBits4 = parsableNalUnitBitArray2.readBits(4);
        if (i36 < 2 || readBits4 == 0) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int[] iArr19 = new int[i36];
        for (int i42 = 0; i42 < i36; i42++) {
            iArr19[i42] = parsableNalUnitBitArray2.readBits(readBits4);
        }
        int[] iArr20 = new int[i18];
        int i43 = 0;
        while (i43 < i17) {
            int[] iArr21 = iArr20;
            iArr21[Math.min(iArr14[i43], readBits3)] = i43;
            i43++;
            iArr20 = iArr21;
        }
        int[] iArr22 = iArr20;
        Q x9 = U.x();
        int i44 = 0;
        while (i44 <= readBits3) {
            int i45 = i36;
            int[] iArr23 = iArr8;
            int min = Math.min(iArr18[i44], i45 - 1);
            if (min >= 0) {
                i15 = iArr19[min];
            } else {
                i15 = -1;
            }
            x9.a(new H265LayerInfo(iArr22[i44], i15));
            i44++;
            i36 = i45;
            iArr8 = iArr23;
            iArr19 = iArr19;
        }
        int[] iArr24 = iArr8;
        y0 f = x9.f();
        if (((H265LayerInfo) f.get(0)).viewId == -1) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int i46 = 1;
        while (true) {
            if (i46 > readBits3) {
                i7 = -1;
                i8 = -1;
                break;
            }
            i7 = -1;
            if (((H265LayerInfo) f.get(i46)).viewId != -1) {
                i8 = i46;
                break;
            }
            i46++;
        }
        if (i8 == i7) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int[] iArr25 = new int[2];
        iArr25[1] = i17;
        iArr25[0] = i17;
        Class cls2 = Boolean.TYPE;
        boolean[][] zArr3 = (boolean[][]) Array.newInstance(cls2, iArr25);
        int[] iArr26 = new int[2];
        iArr26[1] = i17;
        iArr26[0] = i17;
        boolean[][] zArr4 = (boolean[][]) Array.newInstance(cls2, iArr26);
        int i47 = 1;
        while (i47 < i17) {
            boolean[][] zArr5 = zArr4;
            for (int i48 = 0; i48 < i47; i48++) {
                boolean[] zArr6 = zArr3[i47];
                boolean[] zArr7 = zArr5[i47];
                boolean readBit6 = parsableNalUnitBitArray2.readBit();
                zArr7[i48] = readBit6;
                zArr6[i48] = readBit6;
            }
            i47++;
            zArr4 = zArr5;
        }
        boolean[][] zArr8 = zArr4;
        for (int i49 = 1; i49 < i17; i49++) {
            int i50 = 0;
            while (i50 < readBits) {
                int[] iArr27 = iArr14;
                int i51 = 0;
                while (true) {
                    if (i51 >= i49) {
                        break;
                    }
                    boolean[] zArr9 = zArr8[i49];
                    if (zArr9[i51] && zArr8[i51][i50]) {
                        zArr9[i50] = true;
                        break;
                    }
                    i51++;
                }
                i50++;
                iArr14 = iArr27;
            }
            int[] iArr28 = iArr14;
        }
        int[] iArr29 = iArr14;
        int[] iArr30 = new int[i18];
        for (int i52 = 0; i52 < i17; i52++) {
            int i53 = 0;
            for (int i54 = 0; i54 < i52; i54++) {
                i53 += zArr3[i52][i54] ? 1 : 0;
            }
            iArr30[iArr29[i52]] = i53;
        }
        int i55 = 0;
        for (int i56 = 0; i56 < i17; i56++) {
            if (iArr30[iArr29[i56]] == 0) {
                i55++;
            }
        }
        if (i55 > 1) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int[] iArr31 = new int[i17];
        int[] iArr32 = new int[readUnsignedExpGolombCodedInt];
        if (parsableNalUnitBitArray2.readBit()) {
            iArr2 = iArr30;
            int i57 = 0;
            while (i57 < i17) {
                int i58 = i57;
                iArr31[i58] = parsableNalUnitBitArray2.readBits(3);
                i57 = i58 + 1;
            }
        } else {
            iArr2 = iArr30;
            Arrays.fill(iArr31, 0, i17, readBits2);
        }
        int i59 = 0;
        while (i59 < readUnsignedExpGolombCodedInt) {
            int i60 = i59;
            boolean[][] zArr10 = zArr3;
            int[] iArr33 = iArr31;
            int i61 = 0;
            for (int i62 = 0; i62 < iArr7[i60]; i62++) {
                i61 = Math.max(i61, iArr33[((H265LayerInfo) f.get(iArr9[i60][i62])).layerIdInVps]);
            }
            iArr32[i60] = i61 + 1;
            i59 = i60 + 1;
            iArr31 = iArr33;
            zArr3 = zArr10;
        }
        boolean[][] zArr11 = zArr3;
        if (parsableNalUnitBitArray2.readBit()) {
            int i63 = 0;
            while (i63 < readBits) {
                int i64 = i63 + 1;
                int i65 = i64;
                while (i65 < i17) {
                    if (zArr11[i65][i63]) {
                        i14 = i63;
                        parsableNalUnitBitArray2.skipBits(3);
                    } else {
                        i14 = i63;
                    }
                    i65++;
                    i63 = i14;
                }
                i63 = i64;
            }
        }
        parsableNalUnitBitArray2.skipBit();
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt() + 1;
        Q x10 = U.x();
        x10.a(parseH265ProfileTierLevel);
        if (readUnsignedExpGolombCodedInt3 > 1) {
            x10.a(parseH265ProfileTierLevel2);
            for (int i66 = 2; i66 < readUnsignedExpGolombCodedInt3; i66++) {
                parseH265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray2, parsableNalUnitBitArray2.readBit(), readBits2, parseH265ProfileTierLevel2);
                x10.a(parseH265ProfileTierLevel2);
            }
        }
        y0 f5 = x10.f();
        int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray2.readUnsignedExpGolombCodedInt() + readUnsignedExpGolombCodedInt;
        if (readUnsignedExpGolombCodedInt4 > readUnsignedExpGolombCodedInt) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        int readBits5 = parsableNalUnitBitArray2.readBits(2);
        int[] iArr34 = new int[2];
        iArr34[1] = i18;
        iArr34[0] = readUnsignedExpGolombCodedInt4;
        boolean[][] zArr12 = (boolean[][]) Array.newInstance(cls2, iArr34);
        int[] iArr35 = new int[readUnsignedExpGolombCodedInt4];
        int i67 = 0;
        int[] iArr36 = new int[readUnsignedExpGolombCodedInt4];
        int i68 = 0;
        while (i68 < readUnsignedExpGolombCodedInt) {
            iArr35[i68] = i67;
            iArr36[i68] = iArr24[i68];
            if (readBits5 == 0) {
                i13 = i68;
                zArr = zArr12;
                y0Var = f5;
                iArr4 = iArr35;
                Arrays.fill(zArr12[i13], i67, iArr7[i13], true);
                iArr4[i13] = iArr7[i13];
            } else {
                y0Var = f5;
                i13 = i68;
                zArr = zArr12;
                iArr4 = iArr35;
                if (readBits5 == 1) {
                    int i69 = iArr24[i13];
                    for (int i70 = 0; i70 < iArr7[i13]; i70++) {
                        boolean[] zArr13 = zArr[i13];
                        if (iArr9[i13][i70] == i69) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        zArr13[i70] = z9;
                    }
                    iArr4[i13] = 1;
                } else {
                    i67 = 0;
                    zArr[0][0] = true;
                    iArr4[0] = 1;
                    i68 = i13 + 1;
                    zArr12 = zArr;
                    iArr35 = iArr4;
                    f5 = y0Var;
                }
            }
            i67 = 0;
            i68 = i13 + 1;
            zArr12 = zArr;
            iArr35 = iArr4;
            f5 = y0Var;
        }
        y0 y0Var2 = f5;
        boolean[][] zArr14 = zArr12;
        int[] iArr37 = iArr35;
        int[] iArr38 = new int[i18];
        int i71 = 2;
        int[] iArr39 = new int[2];
        iArr39[1] = i18;
        iArr39[i67] = readUnsignedExpGolombCodedInt4;
        boolean[][] zArr15 = (boolean[][]) Array.newInstance(cls2, iArr39);
        int i72 = 1;
        int i73 = 0;
        while (i72 < readUnsignedExpGolombCodedInt4) {
            if (readBits5 == i71) {
                for (int i74 = 0; i74 < iArr7[i72]; i74++) {
                    zArr14[i72][i74] = parsableNalUnitBitArray2.readBit();
                    int i75 = iArr37[i72];
                    boolean z11 = zArr14[i72][i74];
                    iArr37[i72] = i75 + (z11 ? 1 : 0);
                    if (z11) {
                        iArr36[i72] = iArr9[i72][i74];
                    }
                }
            }
            if (i73 == 0) {
                i10 = 0;
                if (iArr9[i72][0] == 0 && zArr14[i72][0]) {
                    for (int i76 = 1; i76 < iArr7[i72]; i76++) {
                        if (iArr9[i72][i76] == i8 && zArr14[i72][i8]) {
                            i73 = i72;
                        }
                    }
                }
            } else {
                i10 = 0;
            }
            int i77 = i10;
            while (i77 < iArr7[i72]) {
                if (readUnsignedExpGolombCodedInt3 > 1) {
                    zArr15[i72][i77] = zArr14[i72][i77];
                    i12 = i8;
                    iArr3 = iArr38;
                    RoundingMode roundingMode = RoundingMode.CEILING;
                    int c5 = b.c((double) readUnsignedExpGolombCodedInt3);
                    if (!zArr15[i72][i77]) {
                        int i78 = ((H265LayerInfo) f.get(iArr9[i72][i77])).layerIdInVps;
                        i11 = readUnsignedExpGolombCodedInt3;
                        int i79 = i10;
                        while (true) {
                            if (i79 >= i77) {
                                break;
                            }
                            int i80 = i79;
                            if (zArr8[i78][((H265LayerInfo) f.get(iArr9[i72][i80])).layerIdInVps]) {
                                zArr15[i72][i77] = true;
                                break;
                            }
                            i79 = i80 + 1;
                        }
                    } else {
                        i11 = readUnsignedExpGolombCodedInt3;
                    }
                    if (zArr15[i72][i77]) {
                        if (i73 <= 0 || i72 != i73) {
                            parsableNalUnitBitArray2.skipBits(c5);
                        } else {
                            iArr3[i77] = parsableNalUnitBitArray2.readBits(c5);
                        }
                    }
                } else {
                    i11 = readUnsignedExpGolombCodedInt3;
                    i12 = i8;
                    iArr3 = iArr38;
                }
                i77++;
                i8 = i12;
                iArr38 = iArr3;
                readUnsignedExpGolombCodedInt3 = i11;
            }
            int i81 = readUnsignedExpGolombCodedInt3;
            int i82 = i8;
            int[] iArr40 = iArr38;
            if (iArr37[i72] == 1 && iArr2[iArr36[i72]] > 0) {
                parsableNalUnitBitArray2.skipBit();
            }
            i72++;
            i8 = i82;
            iArr38 = iArr40;
            readUnsignedExpGolombCodedInt3 = i81;
            i71 = 2;
        }
        int[] iArr41 = iArr38;
        if (i73 == 0) {
            return new H265VpsData(h265NalHeader, (List<H265LayerInfo>) null, h265ProfileTierLevelsAndIndices2, (H265RepFormatsAndIndices) null, (H265VideoSignalInfosAndIndices) null);
        }
        H265RepFormatsAndIndices parseH265RepFormatsAndIndices = parseH265RepFormatsAndIndices(parsableNalUnitBitArray2, i17);
        parsableNalUnitBitArray2.skipBits(2);
        for (int i83 = 1; i83 < i17; i83++) {
            if (iArr2[iArr29[i83]] == 0) {
                parsableNalUnitBitArray2.skipBit();
            }
        }
        skipH265DpbSize(parsableNalUnitBitArray2, readUnsignedExpGolombCodedInt4, iArr32, iArr7, zArr15);
        skipToH265VuiPresentFlagAfterDpbSize(parsableNalUnitBitArray2, i17, zArr11);
        if (parsableNalUnitBitArray2.readBit()) {
            parsableNalUnitBitArray2.byteAlign();
            h265VideoSignalInfosAndIndices = parseH265VideoSignalInfosAndIndices(parsableNalUnitBitArray2, i17, readUnsignedExpGolombCodedInt, iArr32);
        } else {
            h265VideoSignalInfosAndIndices = null;
        }
        return new H265VpsData(h265NalHeader, f, new H265ProfileTierLevelsAndIndices(y0Var2, iArr41), parseH265RepFormatsAndIndices, h265VideoSignalInfosAndIndices);
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i2, int i7) {
        return parsePpsNalUnitPayload(bArr, i2 + 1, i7);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int i2, int i7) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i2, i7);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i2, int i7) {
        return parseSpsNalUnitPayload(bArr, i2 + 1, i7);
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0210  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.container.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r32, int r33, int r34) {
        /*
            androidx.media3.container.ParsableNalUnitBitArray r0 = new androidx.media3.container.ParsableNalUnitBitArray
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r1 = 8
            int r3 = r0.readBits(r1)
            int r4 = r0.readBits(r1)
            int r5 = r0.readBits(r1)
            int r6 = r0.readUnsignedExpGolombCodedInt()
            r2 = 86
            r7 = 44
            r8 = 244(0xf4, float:3.42E-43)
            r9 = 122(0x7a, float:1.71E-43)
            r10 = 110(0x6e, float:1.54E-43)
            r11 = 3
            r13 = 1
            r15 = 100
            if (r3 == r15) goto L_0x0051
            if (r3 == r10) goto L_0x0051
            if (r3 == r9) goto L_0x0051
            if (r3 == r8) goto L_0x0051
            if (r3 == r7) goto L_0x0051
            r14 = 83
            if (r3 == r14) goto L_0x0051
            if (r3 == r2) goto L_0x0051
            r14 = 118(0x76, float:1.65E-43)
            if (r3 == r14) goto L_0x0051
            r14 = 128(0x80, float:1.794E-43)
            if (r3 == r14) goto L_0x0051
            r14 = 138(0x8a, float:1.93E-43)
            if (r3 != r14) goto L_0x0048
            goto L_0x0051
        L_0x0048:
            r14 = r13
            r33 = 16
            r12 = 0
            r16 = 0
            r17 = 0
            goto L_0x0097
        L_0x0051:
            int r14 = r0.readUnsignedExpGolombCodedInt()
            if (r14 != r11) goto L_0x005c
            boolean r16 = r0.readBit()
            goto L_0x005e
        L_0x005c:
            r16 = 0
        L_0x005e:
            int r17 = r0.readUnsignedExpGolombCodedInt()
            int r18 = r0.readUnsignedExpGolombCodedInt()
            r0.skipBit()
            boolean r19 = r0.readBit()
            if (r19 == 0) goto L_0x0093
            if (r14 == r11) goto L_0x0075
            r12 = r1
        L_0x0072:
            r33 = 16
            goto L_0x007a
        L_0x0075:
            r19 = 12
            r12 = r19
            goto L_0x0072
        L_0x007a:
            r1 = 0
        L_0x007b:
            if (r1 >= r12) goto L_0x0095
            boolean r19 = r0.readBit()
            if (r19 == 0) goto L_0x008e
            r8 = 6
            if (r1 >= r8) goto L_0x0089
            r8 = r33
            goto L_0x008b
        L_0x0089:
            r8 = 64
        L_0x008b:
            skipScalingList(r0, r8)
        L_0x008e:
            int r1 = r1 + 1
            r8 = 244(0xf4, float:3.42E-43)
            goto L_0x007b
        L_0x0093:
            r33 = 16
        L_0x0095:
            r12 = r18
        L_0x0097:
            int r1 = r0.readUnsignedExpGolombCodedInt()
            int r1 = r1 + 4
            int r8 = r0.readUnsignedExpGolombCodedInt()
            if (r8 != 0) goto L_0x00af
            int r18 = r0.readUnsignedExpGolombCodedInt()
            int r18 = r18 + 4
            r21 = r3
            r23 = r8
            r2 = 0
            goto L_0x00db
        L_0x00af:
            if (r8 != r13) goto L_0x00d5
            boolean r18 = r0.readBit()
            r0.readSignedExpGolombCodedInt()
            r0.readSignedExpGolombCodedInt()
            int r9 = r0.readUnsignedExpGolombCodedInt()
            r21 = r3
            long r2 = (long) r9
            r23 = r8
            r9 = 0
        L_0x00c5:
            long r7 = (long) r9
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x00d0
            r0.readUnsignedExpGolombCodedInt()
            int r9 = r9 + 1
            goto L_0x00c5
        L_0x00d0:
            r2 = r18
        L_0x00d2:
            r18 = 0
            goto L_0x00db
        L_0x00d5:
            r21 = r3
            r23 = r8
            r2 = 0
            goto L_0x00d2
        L_0x00db:
            int r7 = r0.readUnsignedExpGolombCodedInt()
            r0.skipBit()
            int r3 = r0.readUnsignedExpGolombCodedInt()
            int r3 = r3 + r13
            int r8 = r0.readUnsignedExpGolombCodedInt()
            int r8 = r8 + r13
            boolean r9 = r0.readBit()
            int r24 = 2 - r9
            int r24 = r24 * r8
            if (r9 != 0) goto L_0x00f9
            r0.skipBit()
        L_0x00f9:
            r0.skipBit()
            int r3 = r3 * 16
            int r24 = r24 * 16
            boolean r8 = r0.readBit()
            r25 = 2
            if (r8 == 0) goto L_0x013b
            int r8 = r0.readUnsignedExpGolombCodedInt()
            int r26 = r0.readUnsignedExpGolombCodedInt()
            int r27 = r0.readUnsignedExpGolombCodedInt()
            int r28 = r0.readUnsignedExpGolombCodedInt()
            if (r14 != 0) goto L_0x011f
            int r14 = 2 - r9
            r29 = r13
            goto L_0x0130
        L_0x011f:
            if (r14 != r11) goto L_0x0124
            r29 = r13
            goto L_0x0126
        L_0x0124:
            r29 = r25
        L_0x0126:
            if (r14 != r13) goto L_0x012b
            r14 = r25
            goto L_0x012c
        L_0x012b:
            r14 = r13
        L_0x012c:
            int r30 = 2 - r9
            int r14 = r14 * r30
        L_0x0130:
            int r8 = r8 + r26
            int r8 = r8 * r29
            int r3 = r3 - r8
            int r27 = r27 + r28
            int r27 = r27 * r14
            int r24 = r24 - r27
        L_0x013b:
            r8 = r3
            r3 = r21
            r14 = 44
            if (r3 == r14) goto L_0x0152
            r14 = 86
            if (r3 == r14) goto L_0x0152
            if (r3 == r15) goto L_0x0152
            if (r3 == r10) goto L_0x0152
            r10 = 122(0x7a, float:1.71E-43)
            if (r3 == r10) goto L_0x0152
            r10 = 244(0xf4, float:3.42E-43)
            if (r3 != r10) goto L_0x0158
        L_0x0152:
            r10 = r4 & 16
            if (r10 == 0) goto L_0x0158
            r14 = 0
            goto L_0x015a
        L_0x0158:
            r14 = r33
        L_0x015a:
            boolean r10 = r0.readBit()
            r19 = 1065353216(0x3f800000, float:1.0)
            if (r10 == 0) goto L_0x0237
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x0194
            r10 = 8
            int r13 = r0.readBits(r10)
            r10 = 255(0xff, float:3.57E-43)
            if (r13 != r10) goto L_0x0185
            r10 = r33
            int r13 = r0.readBits(r10)
            int r10 = r0.readBits(r10)
            if (r13 == 0) goto L_0x0194
            if (r10 == 0) goto L_0x0194
            float r13 = (float) r13
            float r10 = (float) r10
            float r19 = r13 / r10
            goto L_0x0194
        L_0x0185:
            float[] r10 = ASPECT_RATIO_IDC_VALUES
            int r15 = r10.length
            if (r13 >= r15) goto L_0x018d
            r19 = r10[r13]
            goto L_0x0194
        L_0x018d:
            java.lang.String r10 = "NalUnitUtil"
            java.lang.String r15 = "Unexpected aspect_ratio_idc value: "
            A.a.D(r13, r15, r10)
        L_0x0194:
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x019d
            r0.skipBit()
        L_0x019d:
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x01d4
            r0.skipBits(r11)
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x01ae
            r13 = 1
            goto L_0x01b0
        L_0x01ae:
            r13 = r25
        L_0x01b0:
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x01d1
            r10 = 8
            int r11 = r0.readBits(r10)
            int r15 = r0.readBits(r10)
            r0.skipBits(r10)
            int r10 = androidx.media3.common.ColorInfo.isoColorPrimariesToColorSpace(r11)
            int r15 = androidx.media3.common.ColorInfo.isoTransferCharacteristicsToColorTransfer(r15)
            r31 = r15
            r15 = r10
            r10 = r31
            goto L_0x01d7
        L_0x01d1:
            r10 = -1
        L_0x01d2:
            r15 = -1
            goto L_0x01d7
        L_0x01d4:
            r10 = -1
            r13 = -1
            goto L_0x01d2
        L_0x01d7:
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x01e3
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
        L_0x01e3:
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x01ee
            r11 = 65
            r0.skipBits(r11)
        L_0x01ee:
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x01f7
            skipHrdParameters(r0)
        L_0x01f7:
            boolean r20 = r0.readBit()
            if (r20 == 0) goto L_0x0200
            skipHrdParameters(r0)
        L_0x0200:
            if (r11 != 0) goto L_0x0204
            if (r20 == 0) goto L_0x0207
        L_0x0204:
            r0.skipBit()
        L_0x0207:
            r0.skipBit()
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x0226
            r0.skipBit()
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            int r14 = r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
        L_0x0226:
            r21 = r10
            r20 = r13
            r22 = r14
            r11 = r17
            r17 = r18
            r10 = r19
            r18 = r2
            r19 = r15
            goto L_0x0247
        L_0x0237:
            r22 = r14
            r11 = r17
            r17 = r18
            r10 = r19
            r19 = -1
            r20 = -1
            r21 = -1
            r18 = r2
        L_0x0247:
            androidx.media3.container.NalUnitUtil$SpsData r2 = new androidx.media3.container.NalUnitUtil$SpsData
            r15 = r1
            r14 = r9
            r13 = r16
            r16 = r23
            r9 = r24
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):androidx.media3.container.NalUnitUtil$SpsData");
    }

    private static void skipH265DpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i2, int[] iArr, int[] iArr2, boolean[][] zArr) {
        boolean z;
        for (int i7 = 1; i7 < i2; i7++) {
            boolean readBit = parsableNalUnitBitArray.readBit();
            for (int i8 = 0; i8 < iArr[i7]; i8++) {
                if (i8 > 0 && readBit) {
                    z = parsableNalUnitBitArray.readBit();
                } else if (i8 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    for (int i10 = 0; i10 < iArr2[i7]; i10++) {
                        if (zArr[i7][i10]) {
                            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        }
                    }
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
            }
        }
    }

    private static void skipH265HrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray, boolean z, int i2) {
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        int i7;
        if (z) {
            z3 = parsableNalUnitBitArray.readBit();
            z9 = parsableNalUnitBitArray.readBit();
            if (z3 || z9) {
                z7 = parsableNalUnitBitArray.readBit();
                if (z7) {
                    parsableNalUnitBitArray.skipBits(19);
                }
                parsableNalUnitBitArray.skipBits(8);
                if (z7) {
                    parsableNalUnitBitArray.skipBits(4);
                }
                parsableNalUnitBitArray.skipBits(15);
            } else {
                z7 = false;
            }
        } else {
            z3 = false;
            z9 = false;
            z7 = false;
        }
        for (int i8 = 0; i8 <= i2; i8++) {
            boolean readBit = parsableNalUnitBitArray.readBit();
            if (!readBit) {
                readBit = parsableNalUnitBitArray.readBit();
            }
            if (readBit) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                z10 = false;
            } else {
                z10 = parsableNalUnitBitArray.readBit();
            }
            if (!z10) {
                i7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            } else {
                i7 = 0;
            }
            int i10 = (z3 ? 1 : 0) + (z9 ? 1 : 0);
            for (int i11 = 0; i11 < i10; i11++) {
                for (int i12 = 0; i12 <= i7; i12++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    if (z7) {
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    }
                    parsableNalUnitBitArray.skipBit();
                }
            }
        }
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i2 = 0; i2 < 4; i2++) {
            int i7 = 0;
            while (i7 < 6) {
                int i8 = 1;
                if (!parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int min = Math.min(64, 1 << ((i2 << 1) + 4));
                    if (i2 > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i10 = 0; i10 < min; i10++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (i2 == 3) {
                    i8 = 3;
                }
                i7 += i8;
            }
        }
    }

    private static void skipH265ShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i2;
        int i7;
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        int i8 = -1;
        int i10 = -1;
        for (int i11 = 0; i11 < readUnsignedExpGolombCodedInt; i11++) {
            if (i11 == 0 || !parsableNalUnitBitArray.readBit()) {
                int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int[] iArr3 = new int[readUnsignedExpGolombCodedInt2];
                for (int i12 = 0; i12 < readUnsignedExpGolombCodedInt2; i12++) {
                    if (i12 > 0) {
                        i7 = iArr3[i12 - 1];
                    } else {
                        i7 = 0;
                    }
                    iArr3[i12] = i7 - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                }
                int[] iArr4 = new int[readUnsignedExpGolombCodedInt3];
                for (int i13 = 0; i13 < readUnsignedExpGolombCodedInt3; i13++) {
                    if (i13 > 0) {
                        i2 = iArr4[i13 - 1];
                    } else {
                        i2 = 0;
                    }
                    iArr4[i13] = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1 + i2;
                    parsableNalUnitBitArray.skipBit();
                }
                int[] iArr5 = iArr3;
                i8 = readUnsignedExpGolombCodedInt2;
                iArr = iArr5;
                int[] iArr6 = iArr4;
                i10 = readUnsignedExpGolombCodedInt3;
                iArr2 = iArr6;
            } else {
                int i14 = i8 + i10;
                int readUnsignedExpGolombCodedInt4 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * true)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                int i15 = i14 + 1;
                boolean[] zArr = new boolean[i15];
                for (int i16 = 0; i16 <= i14; i16++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[i16] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[i16] = true;
                    }
                }
                int[] iArr7 = new int[i15];
                int[] iArr8 = new int[i15];
                int i17 = 0;
                for (int i18 = i10 - 1; i18 >= 0; i18--) {
                    int i19 = iArr2[i18] + readUnsignedExpGolombCodedInt4;
                    if (i19 < 0 && zArr[i8 + i18]) {
                        iArr7[i17] = i19;
                        i17++;
                    }
                }
                if (readUnsignedExpGolombCodedInt4 < 0 && zArr[i14]) {
                    iArr7[i17] = readUnsignedExpGolombCodedInt4;
                    i17++;
                }
                for (int i20 = 0; i20 < i8; i20++) {
                    int i21 = iArr[i20] + readUnsignedExpGolombCodedInt4;
                    if (i21 < 0 && zArr[i20]) {
                        iArr7[i17] = i21;
                        i17++;
                    }
                }
                int[] copyOf = Arrays.copyOf(iArr7, i17);
                int i22 = 0;
                for (int i23 = i8 - 1; i23 >= 0; i23--) {
                    int i24 = iArr[i23] + readUnsignedExpGolombCodedInt4;
                    if (i24 > 0 && zArr[i23]) {
                        iArr8[i22] = i24;
                        i22++;
                    }
                }
                if (readUnsignedExpGolombCodedInt4 > 0 && zArr[i14]) {
                    iArr8[i22] = readUnsignedExpGolombCodedInt4;
                    i22++;
                }
                for (int i25 = 0; i25 < i10; i25++) {
                    int i26 = iArr2[i25] + readUnsignedExpGolombCodedInt4;
                    if (i26 > 0 && zArr[i8 + i25]) {
                        iArr8[i22] = i26;
                        i22++;
                    }
                }
                iArr2 = Arrays.copyOf(iArr8, i22);
                iArr = copyOf;
                i8 = i17;
                i10 = i22;
            }
        }
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        parsableNalUnitBitArray.skipBits(8);
        for (int i2 = 0; i2 < readUnsignedExpGolombCodedInt; i2++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBits(20);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i2) {
        int i7 = 8;
        int i8 = 8;
        for (int i10 = 0; i10 < i2; i10++) {
            if (i7 != 0) {
                i7 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i8) + 256) % 256;
            }
            if (i7 != 0) {
                i8 = i7;
            }
        }
    }

    private static void skipToH265VuiPresentFlagAfterDpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i2, boolean[][] zArr) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 2;
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt);
        } else {
            for (int i7 = 1; i7 < i2; i7++) {
                for (int i8 = 0; i8 < i7; i8++) {
                    if (zArr[i7][i8]) {
                        parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt);
                    }
                }
            }
        }
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i10 = 1; i10 <= readUnsignedExpGolombCodedInt2; i10++) {
            parsableNalUnitBitArray.skipBits(8);
        }
    }

    public static int unescapeStream(byte[] bArr, int i2) {
        int i7;
        synchronized (scratchEscapePositionsLock) {
            int i8 = 0;
            int i10 = 0;
            while (i8 < i2) {
                try {
                    i8 = findNextUnescapeIndex(bArr, i8, i2);
                    if (i8 < i2) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i10) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i10] = i8;
                        i8 += 3;
                        i10++;
                    }
                } finally {
                }
            }
            i7 = i2 - i10;
            int i11 = 0;
            int i12 = 0;
            for (int i13 = 0; i13 < i10; i13++) {
                int i14 = scratchEscapePositions[i13] - i12;
                System.arraycopy(bArr, i12, bArr, i11, i14);
                int i15 = i11 + i14;
                int i16 = i15 + 1;
                bArr[i15] = 0;
                i11 = i15 + 2;
                bArr[i16] = 0;
                i12 += i14 + 3;
            }
            System.arraycopy(bArr, i12, bArr, i11, i7 - i11);
        }
        return i7;
    }
}
