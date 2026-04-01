package androidx.media3.extractor;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import com.samsung.android.imagetranslation.common.Config;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlacStreamMetadata {
    public final int bitsPerSample;
    public final int bitsPerSampleLookupKey;
    public final int channels;
    public final int maxBlockSizeSamples;
    public final int maxFrameSize;
    private final Metadata metadata;
    public final int minBlockSizeSamples;
    public final int minFrameSize;
    public final int sampleRate;
    public final int sampleRateLookupKey;
    public final SeekTable seekTable;
    public final long totalSamples;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SeekTable {
        public final long[] pointOffsets;
        public final long[] pointSampleNumbers;

        public SeekTable(long[] jArr, long[] jArr2) {
            this.pointSampleNumbers = jArr;
            this.pointOffsets = jArr2;
        }
    }

    public FlacStreamMetadata(byte[] bArr, int i2) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.setPosition(i2 * 8);
        this.minBlockSizeSamples = parsableBitArray.readBits(16);
        this.maxBlockSizeSamples = parsableBitArray.readBits(16);
        this.minFrameSize = parsableBitArray.readBits(24);
        this.maxFrameSize = parsableBitArray.readBits(24);
        int readBits = parsableBitArray.readBits(20);
        this.sampleRate = readBits;
        this.sampleRateLookupKey = getSampleRateLookupKey(readBits);
        this.channels = parsableBitArray.readBits(3) + 1;
        int readBits2 = parsableBitArray.readBits(5) + 1;
        this.bitsPerSample = readBits2;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(readBits2);
        this.totalSamples = parsableBitArray.readBitsToLong(36);
        this.seekTable = null;
        this.metadata = null;
    }

    private static int getBitsPerSampleLookupKey(int i2) {
        if (i2 == 8) {
            return 1;
        }
        if (i2 == 12) {
            return 2;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 20) {
            return 5;
        }
        if (i2 == 24) {
            return 6;
        }
        if (i2 != 32) {
            return -1;
        }
        return 7;
    }

    private static int getSampleRateLookupKey(int i2) {
        switch (i2) {
            case Encode.BitRate.VIDEO_HD_BITRATE /*8000*/:
                return 4;
            case Config.MAX_RESOLUTION_SUPPORTED /*16000*/:
                return 5;
            case 22050:
                return 6;
            case 24000:
                return 7;
            case 32000:
                return 8;
            case 44100:
                return 9;
            case 48000:
                return 10;
            case 88200:
                return 1;
            case 96000:
                return 11;
            case 176400:
                return 2;
            case 192000:
                return 3;
            default:
                return -1;
        }
    }

    public FlacStreamMetadata copyWithPictureFrames(List<PictureFrame> list) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, getMetadataCopyWithAppendedEntriesFrom(new Metadata((List<? extends Metadata.Entry>) list)));
    }

    public FlacStreamMetadata copyWithSeekTable(SeekTable seekTable2) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, seekTable2, this.metadata);
    }

    public FlacStreamMetadata copyWithVorbisComments(List<String> list) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, getMetadataCopyWithAppendedEntriesFrom(VorbisUtil.parseVorbisComments(list)));
    }

    public long getApproxBytesPerFrame() {
        long j2;
        long j3;
        long j8;
        int i2 = this.maxFrameSize;
        if (i2 > 0) {
            j3 = (((long) i2) + ((long) this.minFrameSize)) / 2;
            j8 = 1;
        } else {
            int i7 = this.minBlockSizeSamples;
            if (i7 != this.maxBlockSizeSamples || i7 <= 0) {
                j2 = 4096;
            } else {
                j2 = (long) i7;
            }
            j3 = ((j2 * ((long) this.channels)) * ((long) this.bitsPerSample)) / 8;
            j8 = 64;
        }
        return j3 + j8;
    }

    public long getDurationUs() {
        long j2 = this.totalSamples;
        if (j2 == 0) {
            return -9223372036854775807L;
        }
        return (j2 * 1000000) / ((long) this.sampleRate);
    }

    public Format getFormat(byte[] bArr, Metadata metadata2) {
        bArr[4] = Byte.MIN_VALUE;
        int i2 = this.maxFrameSize;
        if (i2 <= 0) {
            i2 = -1;
        }
        return new Format.Builder().setSampleMimeType("audio/flac").setMaxInputSize(i2).setChannelCount(this.channels).setSampleRate(this.sampleRate).setPcmEncoding(Util.getPcmEncoding(this.bitsPerSample)).setInitializationData(Collections.singletonList(bArr)).setMetadata(getMetadataCopyWithAppendedEntriesFrom(metadata2)).build();
    }

    public Metadata getMetadataCopyWithAppendedEntriesFrom(Metadata metadata2) {
        Metadata metadata3 = this.metadata;
        if (metadata3 == null) {
            return metadata2;
        }
        return metadata3.copyWithAppendedEntriesFrom(metadata2);
    }

    public long getSampleNumber(long j2) {
        return Util.constrainValue((j2 * ((long) this.sampleRate)) / 1000000, 0, this.totalSamples - 1);
    }

    private FlacStreamMetadata(int i2, int i7, int i8, int i10, int i11, int i12, int i13, long j2, SeekTable seekTable2, Metadata metadata2) {
        this.minBlockSizeSamples = i2;
        this.maxBlockSizeSamples = i7;
        this.minFrameSize = i8;
        this.maxFrameSize = i10;
        this.sampleRate = i11;
        this.sampleRateLookupKey = getSampleRateLookupKey(i11);
        this.channels = i12;
        this.bitsPerSample = i13;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(i13);
        this.totalSamples = j2;
        this.seekTable = seekTable2;
        this.metadata = metadata2;
    }
}
