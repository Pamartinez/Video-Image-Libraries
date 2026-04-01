package androidx.media3.extractor.ogg;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class DefaultOggSeeker implements OggSeeker {
    private long end;
    private long endGranule;
    private final OggPageHeader pageHeader;
    /* access modifiers changed from: private */
    public final long payloadEndPosition;
    /* access modifiers changed from: private */
    public final long payloadStartPosition;
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private int state;
    /* access modifiers changed from: private */
    public final StreamReader streamReader;
    private long targetGranule;
    /* access modifiers changed from: private */
    public long totalGranules;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class OggSeekMap implements SeekMap {
        private OggSeekMap() {
        }

        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }

        public SeekMap.SeekPoints getSeekPoints(long j2) {
            long convertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j2);
            return new SeekMap.SeekPoints(new SeekPoint(j2, Util.constrainValue((BigInteger.valueOf(convertTimeToGranule).multiply(BigInteger.valueOf(DefaultOggSeeker.this.payloadEndPosition - DefaultOggSeeker.this.payloadStartPosition)).divide(BigInteger.valueOf(DefaultOggSeeker.this.totalGranules)).longValue() + DefaultOggSeeker.this.payloadStartPosition) - 30000, DefaultOggSeeker.this.payloadStartPosition, DefaultOggSeeker.this.payloadEndPosition - 1)));
        }

        public boolean isSeekable() {
            return true;
        }
    }

    public DefaultOggSeeker(StreamReader streamReader2, long j2, long j3, long j8, long j10, boolean z) {
        boolean z3;
        if (j2 < 0 || j3 <= j2) {
            z3 = false;
        } else {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        this.streamReader = streamReader2;
        this.payloadStartPosition = j2;
        this.payloadEndPosition = j3;
        if (j8 == j3 - j2 || z) {
            this.totalGranules = j10;
            this.state = 4;
        } else {
            this.state = 0;
        }
        this.pageHeader = new OggPageHeader();
    }

    private long getNextSeekPosition(ExtractorInput extractorInput) {
        long j2;
        ExtractorInput extractorInput2 = extractorInput;
        if (this.start == this.end) {
            return -1;
        }
        long position = extractorInput2.getPosition();
        if (!this.pageHeader.skipToNextPage(extractorInput2, this.end)) {
            long j3 = this.start;
            if (j3 != position) {
                return j3;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput2, false);
        extractorInput2.resetPeekPosition();
        long j8 = this.targetGranule;
        OggPageHeader oggPageHeader = this.pageHeader;
        long j10 = oggPageHeader.granulePosition;
        long j11 = j8 - j10;
        int i2 = oggPageHeader.headerSize + oggPageHeader.bodySize;
        if (0 <= j11 && j11 < 72000) {
            return -1;
        }
        int i7 = (j11 > 0 ? 1 : (j11 == 0 ? 0 : -1));
        if (i7 < 0) {
            this.end = position;
            this.endGranule = j10;
        } else {
            this.start = extractorInput2.getPosition() + ((long) i2);
            this.startGranule = this.pageHeader.granulePosition;
        }
        long j12 = this.end;
        long j13 = this.start;
        if (j12 - j13 < 100000) {
            this.end = j13;
            return j13;
        }
        long j14 = (long) i2;
        if (i7 <= 0) {
            j2 = 2;
        } else {
            j2 = 1;
        }
        long position2 = extractorInput2.getPosition() - (j14 * j2);
        long j15 = this.end;
        long j16 = this.start;
        return Util.constrainValue((((j15 - j16) * j11) / (this.endGranule - this.startGranule)) + position2, j16, j15 - 1);
    }

    private void skipToPageOfTargetGranule(ExtractorInput extractorInput) {
        while (true) {
            this.pageHeader.skipToNextPage(extractorInput);
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            if (oggPageHeader.granulePosition > this.targetGranule) {
                extractorInput.resetPeekPosition();
                return;
            }
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
            this.start = extractorInput.getPosition();
            this.startGranule = this.pageHeader.granulePosition;
        }
    }

    public long read(ExtractorInput extractorInput) {
        int i2 = this.state;
        if (i2 == 0) {
            long position = extractorInput.getPosition();
            this.positionBeforeSeekToEnd = position;
            this.state = 1;
            long j2 = this.payloadEndPosition - 65307;
            if (j2 > position) {
                return j2;
            }
        } else if (i2 != 1) {
            if (i2 == 2) {
                long nextSeekPosition = getNextSeekPosition(extractorInput);
                if (nextSeekPosition != -1) {
                    return nextSeekPosition;
                }
                this.state = 3;
            } else if (i2 != 3) {
                if (i2 == 4) {
                    return -1;
                }
                throw new IllegalStateException();
            }
            skipToPageOfTargetGranule(extractorInput);
            this.state = 4;
            return -(this.startGranule + 2);
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 4;
        return this.positionBeforeSeekToEnd;
    }

    public long readGranuleOfLastPage(ExtractorInput extractorInput) {
        this.pageHeader.reset();
        if (this.pageHeader.skipToNextPage(extractorInput)) {
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
            long j2 = this.pageHeader.granulePosition;
            while (true) {
                OggPageHeader oggPageHeader2 = this.pageHeader;
                if ((oggPageHeader2.type & 4) == 4 || !oggPageHeader2.skipToNextPage(extractorInput) || extractorInput.getPosition() >= this.payloadEndPosition || !this.pageHeader.populate(extractorInput, true)) {
                    break;
                }
                OggPageHeader oggPageHeader3 = this.pageHeader;
                if (!ExtractorUtil.skipFullyQuietly(extractorInput, oggPageHeader3.headerSize + oggPageHeader3.bodySize)) {
                    break;
                }
                j2 = this.pageHeader.granulePosition;
            }
            return j2;
        }
        throw new EOFException();
    }

    public void startSeek(long j2) {
        this.targetGranule = Util.constrainValue(j2, 0, this.totalGranules - 1);
        this.state = 2;
        this.start = this.payloadStartPosition;
        this.end = this.payloadEndPosition;
        this.startGranule = 0;
        this.endGranule = this.totalGranules;
    }

    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }
}
