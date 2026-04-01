package androidx.media3.extractor.flv;

import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int[] AUDIO_SAMPLING_RATE_TABLE = {5512, 11025, 22050, 44100};
    private int audioFormat;
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    public AudioTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    public boolean parseHeader(ParsableByteArray parsableByteArray) {
        String str;
        if (!this.hasParsedAudioDataHeader) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int i2 = (readUnsignedByte >> 4) & 15;
            this.audioFormat = i2;
            if (i2 == 2) {
                this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType("audio/mpeg").setChannelCount(1).setSampleRate(AUDIO_SAMPLING_RATE_TABLE[(readUnsignedByte >> 2) & 3]).build());
                this.hasOutputFormat = true;
            } else if (i2 == 7 || i2 == 8) {
                if (i2 == 7) {
                    str = "audio/g711-alaw";
                } else {
                    str = "audio/g711-mlaw";
                }
                this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType(str).setChannelCount(1).setSampleRate(Encode.BitRate.VIDEO_HD_BITRATE).build());
                this.hasOutputFormat = true;
            } else if (i2 != 10) {
                throw new TagPayloadReader.UnsupportedFormatException("Audio format not supported: " + this.audioFormat);
            }
            this.hasParsedAudioDataHeader = true;
        } else {
            parsableByteArray.skipBytes(1);
        }
        return true;
    }

    public boolean parsePayload(ParsableByteArray parsableByteArray, long j2) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        if (this.audioFormat == 2) {
            int bytesLeft = parsableByteArray2.bytesLeft();
            this.output.sampleData(parsableByteArray2, bytesLeft);
            this.output.sampleMetadata(j2, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
            return true;
        }
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        if (readUnsignedByte == 0 && !this.hasOutputFormat) {
            int bytesLeft2 = parsableByteArray2.bytesLeft();
            byte[] bArr = new byte[bytesLeft2];
            parsableByteArray2.readBytes(bArr, 0, bytesLeft2);
            AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(bArr);
            this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType(Encode.CodecsMime.AUDIO_CODEC_AAC).setCodecs(parseAudioSpecificConfig.codecs).setChannelCount(parseAudioSpecificConfig.channelCount).setSampleRate(parseAudioSpecificConfig.sampleRateHz).setInitializationData(Collections.singletonList(bArr)).build());
            this.hasOutputFormat = true;
            return false;
        } else if (this.audioFormat == 10 && readUnsignedByte != 1) {
            return false;
        } else {
            int bytesLeft3 = parsableByteArray2.bytesLeft();
            this.output.sampleData(parsableByteArray2, bytesLeft3);
            this.output.sampleMetadata(j2, 1, bytesLeft3, 0, (TrackOutput.CryptoData) null);
            return true;
        }
    }
}
