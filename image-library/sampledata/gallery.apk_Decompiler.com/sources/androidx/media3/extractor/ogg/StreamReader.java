package androidx.media3.extractor.ogg;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class StreamReader {
    private long currentGranule;
    private ExtractorOutput extractorOutput;
    private boolean formatSet;
    private long lengthOfReadPacket;
    private final OggPacket oggPacket = new OggPacket();
    private OggSeeker oggSeeker;
    private long payloadStartPosition;
    private int sampleRate;
    private boolean seekMapSet;
    private SetupData setupData = new SetupData();
    private int state;
    private long targetGranule;
    private TrackOutput trackOutput;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SetupData {
        Format format;
        OggSeeker oggSeeker;
    }

    private void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private boolean readHeaders(ExtractorInput extractorInput) {
        while (this.oggPacket.populate(extractorInput)) {
            this.lengthOfReadPacket = extractorInput.getPosition() - this.payloadStartPosition;
            if (!readHeaders(this.oggPacket.getPayload(), this.payloadStartPosition, this.setupData)) {
                return true;
            }
            this.payloadStartPosition = extractorInput.getPosition();
        }
        this.state = 3;
        return false;
    }

    private int readHeadersAndUpdateState(ExtractorInput extractorInput) {
        boolean z;
        if (!readHeaders(extractorInput)) {
            return -1;
        }
        Format format = this.setupData.format;
        this.sampleRate = format.sampleRate;
        if (!this.formatSet) {
            this.trackOutput.format(format);
            this.formatSet = true;
        }
        OggSeeker oggSeeker2 = this.setupData.oggSeeker;
        if (oggSeeker2 != null) {
            this.oggSeeker = oggSeeker2;
        } else if (extractorInput.getLength() == -1) {
            this.oggSeeker = new UnseekableOggSeeker();
        } else {
            OggPageHeader pageHeader = this.oggPacket.getPageHeader();
            if ((pageHeader.type & 4) != 0) {
                z = true;
            } else {
                z = false;
            }
            this.oggSeeker = new DefaultOggSeeker(this, this.payloadStartPosition, extractorInput.getLength(), (long) (pageHeader.headerSize + pageHeader.bodySize), pageHeader.granulePosition, z);
        }
        this.state = 2;
        this.oggPacket.trimPayload();
        return 0;
    }

    private int readPayload(ExtractorInput extractorInput, PositionHolder positionHolder) {
        ExtractorInput extractorInput2 = extractorInput;
        long read = this.oggSeeker.read(extractorInput2);
        if (read >= 0) {
            positionHolder.position = read;
            return 1;
        }
        if (read < -1) {
            onSeekEnd(-(read + 2));
        }
        if (!this.seekMapSet) {
            SeekMap seekMap = (SeekMap) Assertions.checkStateNotNull(this.oggSeeker.createSeekMap());
            this.extractorOutput.seekMap(seekMap);
            this.trackOutput.durationUs(seekMap.getDurationUs());
            this.seekMapSet = true;
        }
        if (this.lengthOfReadPacket > 0 || this.oggPacket.populate(extractorInput2)) {
            this.lengthOfReadPacket = 0;
            ParsableByteArray payload = this.oggPacket.getPayload();
            long preparePayload = preparePayload(payload);
            if (preparePayload >= 0) {
                long j2 = this.currentGranule;
                if (j2 + preparePayload >= this.targetGranule) {
                    long convertGranuleToTime = convertGranuleToTime(j2);
                    this.trackOutput.sampleData(payload, payload.limit());
                    this.trackOutput.sampleMetadata(convertGranuleToTime, 1, payload.limit(), 0, (TrackOutput.CryptoData) null);
                    this.targetGranule = -1;
                }
            }
            this.currentGranule += preparePayload;
            return 0;
        }
        this.state = 3;
        return -1;
    }

    public long convertGranuleToTime(long j2) {
        return (j2 * 1000000) / ((long) this.sampleRate);
    }

    public long convertTimeToGranule(long j2) {
        return (((long) this.sampleRate) * j2) / 1000000;
    }

    public void init(ExtractorOutput extractorOutput2, TrackOutput trackOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = trackOutput2;
        reset(true);
    }

    public void onSeekEnd(long j2) {
        this.currentGranule = j2;
    }

    public abstract long preparePayload(ParsableByteArray parsableByteArray);

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        assertInitialized();
        int i2 = this.state;
        if (i2 == 0) {
            return readHeadersAndUpdateState(extractorInput);
        }
        if (i2 == 1) {
            extractorInput.skipFully((int) this.payloadStartPosition);
            this.state = 2;
            return 0;
        } else if (i2 == 2) {
            Util.castNonNull(this.oggSeeker);
            return readPayload(extractorInput, positionHolder);
        } else if (i2 == 3) {
            return -1;
        } else {
            throw new IllegalStateException();
        }
    }

    public abstract boolean readHeaders(ParsableByteArray parsableByteArray, long j2, SetupData setupData2);

    public void reset(boolean z) {
        if (z) {
            this.setupData = new SetupData();
            this.payloadStartPosition = 0;
            this.state = 0;
        } else {
            this.state = 1;
        }
        this.targetGranule = -1;
        this.currentGranule = 0;
    }

    public final void seek(long j2, long j3) {
        this.oggPacket.reset();
        if (j2 == 0) {
            reset(!this.seekMapSet);
        } else if (this.state != 0) {
            this.targetGranule = convertTimeToGranule(j3);
            ((OggSeeker) Util.castNonNull(this.oggSeeker)).startSeek(this.targetGranule);
            this.state = 2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnseekableOggSeeker implements OggSeeker {
        private UnseekableOggSeeker() {
        }

        public SeekMap createSeekMap() {
            return new SeekMap.Unseekable(-9223372036854775807L);
        }

        public long read(ExtractorInput extractorInput) {
            return -1;
        }

        public void startSeek(long j2) {
        }
    }
}
