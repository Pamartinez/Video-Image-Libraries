package androidx.media3.extractor.flv;

import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;
import c0.C0086a;
import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class VideoTagPayloadReader extends TagPayloadReader {
    private int frameType;
    private boolean hasOutputFormat;
    private boolean hasOutputKeyframe;
    private final ParsableByteArray nalLength = new ParsableByteArray(4);
    private final ParsableByteArray nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
    private int nalUnitLengthFieldLength;

    public VideoTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    public boolean parseHeader(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = (readUnsignedByte >> 4) & 15;
        int i7 = readUnsignedByte & 15;
        if (i7 == 7) {
            this.frameType = i2;
            if (i2 != 5) {
                return true;
            }
            return false;
        }
        throw new TagPayloadReader.UnsupportedFormatException(C0086a.i(i7, "Video format not supported: "));
    }

    public boolean parsePayload(ParsableByteArray parsableByteArray, long j2) {
        int i2;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        long readInt24 = (((long) parsableByteArray.readInt24()) * 1000) + j2;
        if (readUnsignedByte == 0 && !this.hasOutputFormat) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray(new byte[parsableByteArray.bytesLeft()]);
            parsableByteArray.readBytes(parsableByteArray2.getData(), 0, parsableByteArray.bytesLeft());
            AvcConfig parse = AvcConfig.parse(parsableByteArray2);
            this.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
            this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType(Encode.CodecsMime.VIDEO_CODEC_H264).setCodecs(parse.codecs).setWidth(parse.width).setHeight(parse.height).setPixelWidthHeightRatio(parse.pixelWidthHeightRatio).setInitializationData(parse.initializationData).build());
            this.hasOutputFormat = true;
            return false;
        } else if (readUnsignedByte != 1 || !this.hasOutputFormat) {
            return false;
        } else {
            if (this.frameType == 1) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (!this.hasOutputKeyframe && i2 == 0) {
                return false;
            }
            byte[] data = this.nalLength.getData();
            data[0] = 0;
            data[1] = 0;
            data[2] = 0;
            int i7 = 4 - this.nalUnitLengthFieldLength;
            int i8 = 0;
            while (parsableByteArray.bytesLeft() > 0) {
                parsableByteArray.readBytes(this.nalLength.getData(), i7, this.nalUnitLengthFieldLength);
                this.nalLength.setPosition(0);
                int readUnsignedIntToInt = this.nalLength.readUnsignedIntToInt();
                this.nalStartCode.setPosition(0);
                this.output.sampleData(this.nalStartCode, 4);
                this.output.sampleData(parsableByteArray, readUnsignedIntToInt);
                i8 = i8 + 4 + readUnsignedIntToInt;
            }
            this.output.sampleMetadata(readInt24, i2, i8, 0, (TrackOutput.CryptoData) null);
            this.hasOutputKeyframe = true;
            return true;
        }
    }
}
