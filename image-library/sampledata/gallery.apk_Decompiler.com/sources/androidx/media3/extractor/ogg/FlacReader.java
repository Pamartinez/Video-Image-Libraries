package androidx.media3.extractor.ogg;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacMetadataReader;
import androidx.media3.extractor.FlacSeekTableSeekMap;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ogg.StreamReader;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FlacReader extends StreamReader {
    private FlacOggSeeker flacOggSeeker;
    private FlacStreamMetadata streamMetadata;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FlacOggSeeker implements OggSeeker {
        private long firstFrameOffset = -1;
        private long pendingSeekGranule = -1;
        private FlacStreamMetadata.SeekTable seekTable;
        private FlacStreamMetadata streamMetadata;

        public FlacOggSeeker(FlacStreamMetadata flacStreamMetadata, FlacStreamMetadata.SeekTable seekTable2) {
            this.streamMetadata = flacStreamMetadata;
            this.seekTable = seekTable2;
        }

        public SeekMap createSeekMap() {
            boolean z;
            if (this.firstFrameOffset != -1) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            return new FlacSeekTableSeekMap(this.streamMetadata, this.firstFrameOffset);
        }

        public long read(ExtractorInput extractorInput) {
            long j2 = this.pendingSeekGranule;
            if (j2 < 0) {
                return -1;
            }
            long j3 = -(j2 + 2);
            this.pendingSeekGranule = -1;
            return j3;
        }

        public void setFirstFrameOffset(long j2) {
            this.firstFrameOffset = j2;
        }

        public void startSeek(long j2) {
            long[] jArr = this.seekTable.pointSampleNumbers;
            this.pendingSeekGranule = jArr[Util.binarySearchFloor(jArr, j2, true, true)];
        }
    }

    private int getFlacFrameBlockSize(ParsableByteArray parsableByteArray) {
        int i2 = (parsableByteArray.getData()[2] & 255) >> 4;
        if (i2 == 6 || i2 == 7) {
            parsableByteArray.skipBytes(4);
            parsableByteArray.readUtf8EncodedLong();
        }
        int readFrameBlockSizeSamplesFromKey = FlacFrameReader.readFrameBlockSizeSamplesFromKey(parsableByteArray, i2);
        parsableByteArray.setPosition(0);
        return readFrameBlockSizeSamplesFromKey;
    }

    private static boolean isAudioPacket(byte[] bArr) {
        if (bArr[0] == -1) {
            return true;
        }
        return false;
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() >= 5 && parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563) {
            return true;
        }
        return false;
    }

    public long preparePayload(ParsableByteArray parsableByteArray) {
        if (!isAudioPacket(parsableByteArray.getData())) {
            return -1;
        }
        return (long) getFlacFrameBlockSize(parsableByteArray);
    }

    public boolean readHeaders(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        byte[] data = parsableByteArray.getData();
        FlacStreamMetadata flacStreamMetadata = this.streamMetadata;
        if (flacStreamMetadata == null) {
            FlacStreamMetadata flacStreamMetadata2 = new FlacStreamMetadata(data, 17);
            this.streamMetadata = flacStreamMetadata2;
            setupData.format = flacStreamMetadata2.getFormat(Arrays.copyOfRange(data, 9, parsableByteArray.limit()), (Metadata) null).buildUpon().setContainerMimeType("audio/ogg").build();
            return true;
        } else if ((data[0] & Byte.MAX_VALUE) == 3) {
            FlacStreamMetadata.SeekTable readSeekTableMetadataBlock = FlacMetadataReader.readSeekTableMetadataBlock(parsableByteArray);
            FlacStreamMetadata copyWithSeekTable = flacStreamMetadata.copyWithSeekTable(readSeekTableMetadataBlock);
            this.streamMetadata = copyWithSeekTable;
            this.flacOggSeeker = new FlacOggSeeker(copyWithSeekTable, readSeekTableMetadataBlock);
            return true;
        } else if (!isAudioPacket(data)) {
            return true;
        } else {
            FlacOggSeeker flacOggSeeker2 = this.flacOggSeeker;
            if (flacOggSeeker2 != null) {
                flacOggSeeker2.setFirstFrameOffset(j2);
                setupData.oggSeeker = this.flacOggSeeker;
            }
            Assertions.checkNotNull(setupData.format);
            return false;
        }
    }

    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.streamMetadata = null;
            this.flacOggSeeker = null;
        }
    }
}
