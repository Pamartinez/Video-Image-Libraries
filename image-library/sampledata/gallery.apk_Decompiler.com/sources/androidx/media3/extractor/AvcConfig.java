package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AvcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderFrames;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    private AvcConfig(List<byte[]> list, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15, float f, String str) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i2;
        this.width = i7;
        this.height = i8;
        this.bitdepthLuma = i10;
        this.bitdepthChroma = i11;
        this.colorSpace = i12;
        this.colorRange = i13;
        this.colorTransfer = i14;
        this.maxNumReorderFrames = i15;
        this.pixelWidthHeightRatio = f;
        this.codecs = str;
    }

    private static byte[] buildNalUnitForChild(ParsableByteArray parsableByteArray) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(readUnsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.getData(), position, readUnsignedShort);
    }

    public static AvcConfig parse(ParsableByteArray parsableByteArray) {
        String str;
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        float f;
        int i13;
        int i14;
        try {
            parsableByteArray.skipBytes(4);
            int readUnsignedByte = (parsableByteArray.readUnsignedByte() & 3) + 1;
            if (readUnsignedByte != 3) {
                ArrayList arrayList = new ArrayList();
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i15 = 0; i15 < readUnsignedByte2; i15++) {
                    arrayList.add(buildNalUnitForChild(parsableByteArray));
                }
                int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                for (int i16 = 0; i16 < readUnsignedByte3; i16++) {
                    arrayList.add(buildNalUnitForChild(parsableByteArray));
                }
                if (readUnsignedByte2 > 0) {
                    NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit((byte[]) arrayList.get(0), NalUnitUtil.NAL_START_CODE.length, ((byte[]) arrayList.get(0)).length);
                    int i17 = parseSpsNalUnit.width;
                    int i18 = parseSpsNalUnit.height;
                    int i19 = parseSpsNalUnit.colorSpace;
                    int i20 = parseSpsNalUnit.colorRange;
                    int i21 = parseSpsNalUnit.colorTransfer;
                    int i22 = parseSpsNalUnit.maxNumReorderFrames;
                    float f5 = parseSpsNalUnit.pixelWidthHeightRatio;
                    str = CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc);
                    i11 = i21;
                    i12 = i22;
                    f = f5;
                    i10 = parseSpsNalUnit.bitDepthChromaMinus8 + 8;
                    i13 = i19;
                    i14 = i20;
                    i2 = i17;
                    i7 = i18;
                    i8 = parseSpsNalUnit.bitDepthLumaMinus8 + 8;
                } else {
                    str = null;
                    i2 = -1;
                    i7 = -1;
                    i8 = -1;
                    i10 = -1;
                    i11 = -1;
                    i12 = 16;
                    f = 1.0f;
                    i13 = -1;
                    i14 = -1;
                }
                return new AvcConfig(arrayList, readUnsignedByte, i2, i7, i8, i10, i13, i14, i11, i12, f, str);
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing AVC config", e);
        }
    }
}
