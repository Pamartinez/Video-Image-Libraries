package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HevcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int decodedHeight;
    public final int decodedWidth;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int maxSubLayers;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int stereoMode;
    public final NalUnitUtil.H265VpsData vpsData;
    public final int width;

    private HevcConfig(List<byte[]> list, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, float f, int i19, String str, NalUnitUtil.H265VpsData h265VpsData) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i2;
        this.maxSubLayers = i7;
        this.width = i8;
        this.height = i10;
        this.decodedWidth = i11;
        this.decodedHeight = i12;
        this.bitdepthLuma = i13;
        this.bitdepthChroma = i14;
        this.colorSpace = i15;
        this.colorRange = i16;
        this.colorTransfer = i17;
        this.stereoMode = i18;
        this.pixelWidthHeightRatio = f;
        this.maxNumReorderPics = i19;
        this.codecs = str;
        this.vpsData = h265VpsData;
    }

    public static HevcConfig parse(ParsableByteArray parsableByteArray) {
        return parseImpl(parsableByteArray, false, (NalUnitUtil.H265VpsData) null);
    }

    private static HevcConfig parseImpl(ParsableByteArray parsableByteArray, boolean z, NalUnitUtil.H265VpsData h265VpsData) {
        boolean z3;
        List singletonList;
        int i2;
        NalUnitUtil.H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo;
        int i7;
        String str;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        if (z) {
            try {
                parsableByteArray2.skipBytes(4);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (z) {
                    str = "L-HEVC config";
                } else {
                    str = "HEVC config";
                }
                throw ParserException.createForMalformedContainer("Error parsing".concat(str), e);
            }
        } else {
            parsableByteArray2.skipBytes(21);
        }
        int readUnsignedByte = parsableByteArray2.readUnsignedByte() & 3;
        int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
        int position = parsableByteArray2.getPosition();
        int i8 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            z3 = true;
            if (i10 >= readUnsignedByte2) {
                break;
            }
            parsableByteArray2.skipBytes(1);
            int readUnsignedShort = parsableByteArray2.readUnsignedShort();
            for (int i12 = 0; i12 < readUnsignedShort; i12++) {
                int readUnsignedShort2 = parsableByteArray2.readUnsignedShort();
                i11 += readUnsignedShort2 + 4;
                parsableByteArray2.skipBytes(readUnsignedShort2);
            }
            i10++;
        }
        parsableByteArray2.setPosition(position);
        byte[] bArr = new byte[i11];
        NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
        int i13 = -1;
        int i14 = -1;
        int i15 = -1;
        int i16 = -1;
        int i17 = -1;
        int i18 = -1;
        int i19 = -1;
        int i20 = -1;
        int i21 = -1;
        int i22 = -1;
        int i23 = -1;
        int i24 = -1;
        float f = 1.0f;
        String str2 = null;
        int i25 = 0;
        int i26 = 0;
        while (i25 < readUnsignedByte2) {
            int readUnsignedByte3 = parsableByteArray2.readUnsignedByte() & 63;
            int readUnsignedShort3 = parsableByteArray2.readUnsignedShort();
            int i27 = i8;
            NalUnitUtil.H265VpsData h265VpsData3 = h265VpsData2;
            while (i27 < readUnsignedShort3) {
                int readUnsignedShort4 = parsableByteArray2.readUnsignedShort();
                boolean z7 = z3;
                byte[] bArr2 = NalUnitUtil.NAL_START_CODE;
                int i28 = readUnsignedByte;
                System.arraycopy(bArr2, i8, bArr, i26, bArr2.length);
                int length = i26 + bArr2.length;
                System.arraycopy(parsableByteArray2.getData(), parsableByteArray2.getPosition(), bArr, length, readUnsignedShort4);
                if (readUnsignedByte3 == 32 && i27 == 0) {
                    h265VpsData3 = NalUnitUtil.parseH265VpsNalUnit(bArr, length, length + readUnsignedShort4);
                    i2 = readUnsignedByte2;
                } else {
                    if (readUnsignedByte3 == 33 && i27 == 0) {
                        NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + readUnsignedShort4, h265VpsData3);
                        i13 = parseH265SpsNalUnit.maxSubLayersMinus1 + 1;
                        i14 = parseH265SpsNalUnit.width;
                        int i29 = parseH265SpsNalUnit.height;
                        int i30 = parseH265SpsNalUnit.decodedWidth;
                        i2 = readUnsignedByte2;
                        int i31 = parseH265SpsNalUnit.decodedHeight;
                        i18 = parseH265SpsNalUnit.bitDepthLumaMinus8 + 8;
                        i19 = parseH265SpsNalUnit.bitDepthChromaMinus8 + 8;
                        int i32 = parseH265SpsNalUnit.colorSpace;
                        int i33 = parseH265SpsNalUnit.colorRange;
                        int i34 = parseH265SpsNalUnit.colorTransfer;
                        float f5 = parseH265SpsNalUnit.pixelWidthHeightRatio;
                        int i35 = parseH265SpsNalUnit.maxNumReorderPics;
                        NalUnitUtil.H265ProfileTierLevel h265ProfileTierLevel = parseH265SpsNalUnit.profileTierLevel;
                        if (h265ProfileTierLevel != null) {
                            i7 = i35;
                            str2 = CodecSpecificDataUtil.buildHevcCodecString(h265ProfileTierLevel.generalProfileSpace, h265ProfileTierLevel.generalTierFlag, h265ProfileTierLevel.generalProfileIdc, h265ProfileTierLevel.generalProfileCompatibilityFlags, h265ProfileTierLevel.constraintBytes, h265ProfileTierLevel.generalLevelIdc);
                        } else {
                            i7 = i35;
                        }
                        f = f5;
                        i24 = i7;
                        i21 = i33;
                        i22 = i34;
                        i17 = i31;
                        i20 = i32;
                        i16 = i30;
                        i15 = i29;
                    } else {
                        i2 = readUnsignedByte2;
                        if (readUnsignedByte3 == 39 && i27 == 0 && (parseH265Sei3dRefDisplayInfo = NalUnitUtil.parseH265Sei3dRefDisplayInfo(bArr, length, length + readUnsignedShort4)) != null && h265VpsData3 != null) {
                            i8 = 0;
                            if (parseH265Sei3dRefDisplayInfo.leftViewId == ((NalUnitUtil.H265LayerInfo) h265VpsData3.layerInfos.get(0)).viewId) {
                                i23 = 4;
                            } else {
                                i23 = 5;
                            }
                        }
                    }
                    i8 = 0;
                }
                i26 = length + readUnsignedShort4;
                parsableByteArray2.skipBytes(readUnsignedShort4);
                i27++;
                z3 = z7;
                readUnsignedByte = i28;
                readUnsignedByte2 = i2;
            }
            int i36 = readUnsignedByte;
            int i37 = readUnsignedByte2;
            boolean z9 = z3;
            i25++;
            h265VpsData2 = h265VpsData3;
        }
        int i38 = readUnsignedByte;
        boolean z10 = z3;
        if (i11 == 0) {
            singletonList = Collections.EMPTY_LIST;
        } else {
            singletonList = Collections.singletonList(bArr);
        }
        return new HevcConfig(singletonList, i38 + 1, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, f, i24, str2, h265VpsData2);
    }

    public static HevcConfig parseLayered(ParsableByteArray parsableByteArray, NalUnitUtil.H265VpsData h265VpsData) {
        return parseImpl(parsableByteArray, true, h265VpsData);
    }
}
