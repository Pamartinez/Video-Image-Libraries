package androidx.media3.extractor.flac;

import P.a;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacMetadataReader;
import androidx.media3.extractor.FlacSeekTableSeekMap;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlacExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(2);
    private FlacBinarySearchSeeker binarySearchSeeker;
    private final ParsableByteArray buffer;
    private int currentFrameBytesWritten;
    private long currentFrameFirstSampleNumber;
    private ExtractorOutput extractorOutput;
    private FlacStreamMetadata flacStreamMetadata;
    private int frameStartMarker;
    private Metadata id3Metadata;
    private final boolean id3MetadataDisabled;
    private int minFrameSize;
    private final FlacFrameReader.SampleNumberHolder sampleNumberHolder;
    private int state;
    private final byte[] streamMarkerAndInfoBlock;
    private TrackOutput trackOutput;

    public FlacExtractor() {
        this(0);
    }

    private long findFrame(ParsableByteArray parsableByteArray, boolean z) {
        boolean z3;
        Assertions.checkNotNull(this.flacStreamMetadata);
        int position = parsableByteArray.getPosition();
        while (position <= parsableByteArray.limit() - 16) {
            parsableByteArray.setPosition(position);
            if (FlacFrameReader.checkAndReadFrameHeader(parsableByteArray, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder)) {
                parsableByteArray.setPosition(position);
                return this.sampleNumberHolder.sampleNumber;
            }
            position++;
        }
        if (z) {
            while (position <= parsableByteArray.limit() - this.minFrameSize) {
                parsableByteArray.setPosition(position);
                boolean z7 = false;
                try {
                    z3 = FlacFrameReader.checkAndReadFrameHeader(parsableByteArray, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder);
                } catch (IndexOutOfBoundsException unused) {
                    z3 = false;
                }
                if (parsableByteArray.getPosition() <= parsableByteArray.limit()) {
                    z7 = z3;
                }
                if (z7) {
                    parsableByteArray.setPosition(position);
                    return this.sampleNumberHolder.sampleNumber;
                }
                position++;
            }
            parsableByteArray.setPosition(parsableByteArray.limit());
            return -1;
        }
        parsableByteArray.setPosition(position);
        return -1;
    }

    private void getFrameStartMarker(ExtractorInput extractorInput) {
        this.frameStartMarker = FlacMetadataReader.getFrameStartMarker(extractorInput);
        ((ExtractorOutput) Util.castNonNull(this.extractorOutput)).seekMap(getSeekMap(extractorInput.getPosition(), extractorInput.getLength()));
        this.state = 5;
    }

    private SeekMap getSeekMap(long j2, long j3) {
        Assertions.checkNotNull(this.flacStreamMetadata);
        FlacStreamMetadata flacStreamMetadata2 = this.flacStreamMetadata;
        FlacStreamMetadata.SeekTable seekTable = flacStreamMetadata2.seekTable;
        if (seekTable != null && seekTable.pointSampleNumbers.length > 0) {
            return new FlacSeekTableSeekMap(flacStreamMetadata2, j2);
        }
        if (j3 == -1 || flacStreamMetadata2.totalSamples <= 0) {
            return new SeekMap.Unseekable(flacStreamMetadata2.getDurationUs());
        }
        FlacBinarySearchSeeker flacBinarySearchSeeker = new FlacBinarySearchSeeker(flacStreamMetadata2, this.frameStartMarker, j2, j3);
        this.binarySearchSeeker = flacBinarySearchSeeker;
        return flacBinarySearchSeeker.getSeekMap();
    }

    private void getStreamMarkerAndInfoBlockBytes(ExtractorInput extractorInput) {
        byte[] bArr = this.streamMarkerAndInfoBlock;
        extractorInput.peekFully(bArr, 0, bArr.length);
        extractorInput.resetPeekPosition();
        this.state = 2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new FlacExtractor()};
    }

    private void outputSampleMetadata() {
        ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata((this.currentFrameFirstSampleNumber * 1000000) / ((long) ((FlacStreamMetadata) Util.castNonNull(this.flacStreamMetadata)).sampleRate), 1, this.currentFrameBytesWritten, 0, (TrackOutput.CryptoData) null);
    }

    private int readFrames(ExtractorInput extractorInput, PositionHolder positionHolder) {
        boolean z;
        Assertions.checkNotNull(this.trackOutput);
        Assertions.checkNotNull(this.flacStreamMetadata);
        FlacBinarySearchSeeker flacBinarySearchSeeker = this.binarySearchSeeker;
        if (flacBinarySearchSeeker != null && flacBinarySearchSeeker.isSeeking()) {
            return this.binarySearchSeeker.handlePendingSeek(extractorInput, positionHolder);
        }
        if (this.currentFrameFirstSampleNumber == -1) {
            this.currentFrameFirstSampleNumber = FlacFrameReader.getFirstSampleNumber(extractorInput, this.flacStreamMetadata);
            return 0;
        }
        int limit = this.buffer.limit();
        if (limit < 32768) {
            int read = extractorInput.read(this.buffer.getData(), limit, 32768 - limit);
            if (read == -1) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                this.buffer.setLimit(limit + read);
            } else if (this.buffer.bytesLeft() == 0) {
                outputSampleMetadata();
                return -1;
            }
        } else {
            z = false;
        }
        int position = this.buffer.getPosition();
        int i2 = this.currentFrameBytesWritten;
        int i7 = this.minFrameSize;
        if (i2 < i7) {
            ParsableByteArray parsableByteArray = this.buffer;
            parsableByteArray.skipBytes(Math.min(i7 - i2, parsableByteArray.bytesLeft()));
        }
        long findFrame = findFrame(this.buffer, z);
        int position2 = this.buffer.getPosition() - position;
        this.buffer.setPosition(position);
        this.trackOutput.sampleData(this.buffer, position2);
        this.currentFrameBytesWritten += position2;
        if (findFrame != -1) {
            outputSampleMetadata();
            this.currentFrameBytesWritten = 0;
            this.currentFrameFirstSampleNumber = findFrame;
        }
        int length = this.buffer.getData().length - this.buffer.limit();
        if (this.buffer.bytesLeft() < 16 && length < 16) {
            int bytesLeft = this.buffer.bytesLeft();
            System.arraycopy(this.buffer.getData(), this.buffer.getPosition(), this.buffer.getData(), 0, bytesLeft);
            this.buffer.setPosition(0);
            this.buffer.setLimit(bytesLeft);
        }
        return 0;
    }

    private void readId3Metadata(ExtractorInput extractorInput) {
        this.id3Metadata = FlacMetadataReader.readId3Metadata(extractorInput, !this.id3MetadataDisabled);
        this.state = 1;
    }

    private void readMetadataBlocks(ExtractorInput extractorInput) {
        FlacMetadataReader.FlacStreamMetadataHolder flacStreamMetadataHolder = new FlacMetadataReader.FlacStreamMetadataHolder(this.flacStreamMetadata);
        boolean z = false;
        while (!z) {
            z = FlacMetadataReader.readMetadataBlock(extractorInput, flacStreamMetadataHolder);
            this.flacStreamMetadata = (FlacStreamMetadata) Util.castNonNull(flacStreamMetadataHolder.flacStreamMetadata);
        }
        Assertions.checkNotNull(this.flacStreamMetadata);
        this.minFrameSize = Math.max(this.flacStreamMetadata.minFrameSize, 6);
        ((TrackOutput) Util.castNonNull(this.trackOutput)).format(this.flacStreamMetadata.getFormat(this.streamMarkerAndInfoBlock, this.id3Metadata).buildUpon().setContainerMimeType("audio/flac").build());
        ((TrackOutput) Util.castNonNull(this.trackOutput)).durationUs(this.flacStreamMetadata.getDurationUs());
        this.state = 4;
    }

    private void readStreamMarker(ExtractorInput extractorInput) {
        FlacMetadataReader.readStreamMarker(extractorInput);
        this.state = 3;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = extractorOutput2.track(0, 1);
        extractorOutput2.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        int i2 = this.state;
        if (i2 == 0) {
            readId3Metadata(extractorInput);
            return 0;
        } else if (i2 == 1) {
            getStreamMarkerAndInfoBlockBytes(extractorInput);
            return 0;
        } else if (i2 == 2) {
            readStreamMarker(extractorInput);
            return 0;
        } else if (i2 == 3) {
            readMetadataBlocks(extractorInput);
            return 0;
        } else if (i2 == 4) {
            getFrameStartMarker(extractorInput);
            return 0;
        } else if (i2 == 5) {
            return readFrames(extractorInput, positionHolder);
        } else {
            throw new IllegalStateException();
        }
    }

    public void seek(long j2, long j3) {
        long j8 = 0;
        if (j2 == 0) {
            this.state = 0;
        } else {
            FlacBinarySearchSeeker flacBinarySearchSeeker = this.binarySearchSeeker;
            if (flacBinarySearchSeeker != null) {
                flacBinarySearchSeeker.setSeekTargetUs(j3);
            }
        }
        if (j3 != 0) {
            j8 = -1;
        }
        this.currentFrameFirstSampleNumber = j8;
        this.currentFrameBytesWritten = 0;
        this.buffer.reset(0);
    }

    public boolean sniff(ExtractorInput extractorInput) {
        FlacMetadataReader.peekId3Metadata(extractorInput, false);
        return FlacMetadataReader.checkAndPeekStreamMarker(extractorInput);
    }

    public FlacExtractor(int i2) {
        this.streamMarkerAndInfoBlock = new byte[42];
        this.buffer = new ParsableByteArray(new byte[32768], 0);
        this.id3MetadataDisabled = (i2 & 1) == 0 ? false : true;
        this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        this.state = 0;
    }

    public void release() {
    }
}
