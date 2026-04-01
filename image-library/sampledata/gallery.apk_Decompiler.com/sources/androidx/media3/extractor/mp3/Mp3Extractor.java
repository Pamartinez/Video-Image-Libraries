package androidx.media3.extractor.mp3;

import P.a;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.Id3Peeker;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.id3.MlltFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.extractor.mp3.Seeker;
import java.io.EOFException;
import java.math.RoundingMode;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Mp3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(5);
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = new W.a(0);
    private long basisTimeUs;
    private TrackOutput currentTrackOutput;
    private boolean disableSeeking;
    private long endPositionOfLastSampleRead;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private boolean isSeekInProgress;
    private Metadata metadata;
    private TrackOutput realTrackOutput;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private long seekTimeUs;
    private Seeker seeker;
    private final TrackOutput skippingTrackOutput;
    private final MpegAudioUtil.Header synchronizedHeader;
    private int synchronizedHeaderData;

    public Mp3Extractor() {
        this(0);
    }

    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private Seeker computeSeeker(ExtractorInput extractorInput) {
        boolean z;
        long j2;
        long length;
        long id3TlenUs;
        long j3;
        long dataEndPosition;
        Seeker maybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
        MlltSeeker maybeHandleSeekMetadata = maybeHandleSeekMetadata(this.metadata, extractorInput.getPosition());
        if (this.disableSeeking) {
            return new Seeker.UnseekableSeeker();
        }
        if ((this.flags & 4) != 0) {
            if (maybeHandleSeekMetadata != null) {
                id3TlenUs = maybeHandleSeekMetadata.getDurationUs();
                dataEndPosition = maybeHandleSeekMetadata.getDataEndPosition();
            } else if (maybeReadSeekFrame != null) {
                id3TlenUs = maybeReadSeekFrame.getDurationUs();
                dataEndPosition = maybeReadSeekFrame.getDataEndPosition();
            } else {
                id3TlenUs = getId3TlenUs(this.metadata);
                j3 = -1;
                maybeReadSeekFrame = new IndexSeeker(id3TlenUs, extractorInput.getPosition(), j3);
            }
            j3 = dataEndPosition;
            maybeReadSeekFrame = new IndexSeeker(id3TlenUs, extractorInput.getPosition(), j3);
        } else if (maybeHandleSeekMetadata != null) {
            maybeReadSeekFrame = maybeHandleSeekMetadata;
        } else if (maybeReadSeekFrame == null) {
            maybeReadSeekFrame = null;
        }
        if (maybeReadSeekFrame != null && shouldFallbackToConstantBitrateSeeking(maybeReadSeekFrame) && maybeReadSeekFrame.getDurationUs() != -9223372036854775807L && (maybeReadSeekFrame.getDataEndPosition() != -1 || extractorInput.getLength() != -1)) {
            if (maybeReadSeekFrame.getDataStartPosition() != -1) {
                j2 = maybeReadSeekFrame.getDataStartPosition();
            } else {
                j2 = 0;
            }
            long j8 = j2;
            if (maybeReadSeekFrame.getDataEndPosition() != -1) {
                length = maybeReadSeekFrame.getDataEndPosition();
            } else {
                length = extractorInput.getLength();
            }
            long j10 = length;
            maybeReadSeekFrame = new ConstantBitrateSeeker(j10, j8, C0246a.j0(Util.scaleLargeValue(j10 - j8, 8000000, maybeReadSeekFrame.getDurationUs(), RoundingMode.HALF_UP)), -1, false);
        } else if (maybeReadSeekFrame == null || shouldFallbackToConstantBitrateSeeking(maybeReadSeekFrame)) {
            if ((this.flags & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            maybeReadSeekFrame = getConstantBitrateSeeker(extractorInput, z);
        }
        this.realTrackOutput.durationUs(maybeReadSeekFrame.getDurationUs());
        return maybeReadSeekFrame;
    }

    private long computeTimeUs(long j2) {
        return ((j2 * 1000000) / ((long) this.synchronizedHeader.sampleRate)) + this.basisTimeUs;
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput, boolean z) {
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, z);
    }

    private static long getId3TlenUs(Metadata metadata2) {
        if (metadata2 == null) {
            return -9223372036854775807L;
        }
        int length = metadata2.length();
        for (int i2 = 0; i2 < length; i2++) {
            Metadata.Entry entry = metadata2.get(i2);
            if (entry instanceof TextInformationFrame) {
                TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                if (textInformationFrame.id.equals("TLEN")) {
                    return Util.msToUs(Long.parseLong((String) textInformationFrame.values.get(0)));
                }
            }
        }
        return -9223372036854775807L;
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i2) {
        if (parsableByteArray.limit() >= i2 + 4) {
            parsableByteArray.setPosition(i2);
            int readInt = parsableByteArray.readInt();
            if (readInt == 1483304551 || readInt == 1231971951) {
                return readInt;
            }
        }
        if (parsableByteArray.limit() < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        if (parsableByteArray.readInt() == 1447187017) {
            return 1447187017;
        }
        return 0;
    }

    private static boolean headersMatch(int i2, long j2) {
        if (((long) (i2 & -128000)) == (j2 & -128000)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp3Extractor()};
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$1(int i2, int i7, int i8, int i10, int i11) {
        if (i7 == 67 && i8 == 79 && i10 == 77 && (i11 == 77 || i2 == 2)) {
            return true;
        }
        if (i7 != 77 || i8 != 76 || i10 != 76) {
            return false;
        }
        if (i11 == 84 || i2 == 2) {
            return true;
        }
        return false;
    }

    private static MlltSeeker maybeHandleSeekMetadata(Metadata metadata2, long j2) {
        if (metadata2 == null) {
            return null;
        }
        int length = metadata2.length();
        for (int i2 = 0; i2 < length; i2++) {
            Metadata.Entry entry = metadata2.get(i2);
            if (entry instanceof MlltFrame) {
                return MlltSeeker.create(j2, (MlltFrame) entry, getId3TlenUs(metadata2));
            }
        }
        return null;
    }

    private Seeker maybeReadSeekFrame(ExtractorInput extractorInput) {
        int i2;
        int i7;
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.getData(), 0, this.synchronizedHeader.frameSize);
        MpegAudioUtil.Header header = this.synchronizedHeader;
        int i8 = 21;
        if ((header.version & 1) != 0) {
            if (header.channels != 1) {
                i8 = 36;
            }
        } else if (header.channels == 1) {
            i8 = 13;
        }
        int seekFrameHeader = getSeekFrameHeader(parsableByteArray, i8);
        if (seekFrameHeader != 1231971951) {
            if (seekFrameHeader == 1447187017) {
                VbriSeeker create = VbriSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
                extractorInput.skipFully(this.synchronizedHeader.frameSize);
                return create;
            } else if (seekFrameHeader != 1483304551) {
                extractorInput.resetPeekPosition();
                return null;
            }
        }
        XingFrame parse = XingFrame.parse(this.synchronizedHeader, parsableByteArray);
        if (!(this.gaplessInfoHolder.hasGaplessInfo() || (i2 = parse.encoderDelay) == -1 || (i7 = parse.encoderPadding) == -1)) {
            GaplessInfoHolder gaplessInfoHolder2 = this.gaplessInfoHolder;
            gaplessInfoHolder2.encoderDelay = i2;
            gaplessInfoHolder2.encoderPadding = i7;
        }
        long position = extractorInput.getPosition();
        if (!(extractorInput.getLength() == -1 || parse.dataSize == -1 || extractorInput.getLength() == parse.dataSize + position)) {
            Log.i("Mp3Extractor", "Data size mismatch between stream (" + extractorInput.getLength() + ") and Xing frame (" + (parse.dataSize + position) + "), using Xing value.");
        }
        extractorInput.skipFully(this.synchronizedHeader.frameSize);
        if (seekFrameHeader == 1483304551) {
            return XingSeeker.create(parse, position);
        }
        return getConstantBitrateSeeker(position, parse, extractorInput.getLength());
    }

    private void maybeUpdateCbrDurationToLastSample() {
        Seeker seeker2 = this.seeker;
        if ((seeker2 instanceof ConstantBitrateSeeker) && seeker2.isSeekable()) {
            long j2 = this.endPositionOfLastSampleRead;
            if (j2 != -1 && j2 != this.seeker.getDataEndPosition()) {
                this.seeker = ((ConstantBitrateSeeker) this.seeker).copyWithNewDataEndPosition(this.endPositionOfLastSampleRead);
                ((ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)).seekMap(this.seeker);
                ((TrackOutput) Assertions.checkNotNull(this.realTrackOutput)).durationUs(this.seeker.getDurationUs());
            }
        }
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput extractorInput) {
        Seeker seeker2 = this.seeker;
        if (seeker2 != null) {
            long dataEndPosition = seeker2.getDataEndPosition();
            if (dataEndPosition != -1 && extractorInput.getPeekPosition() > dataEndPosition - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.peekFully(this.scratch.getData(), 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    private int readInternal(ExtractorInput extractorInput) {
        Metadata metadata2;
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            Seeker computeSeeker = computeSeeker(extractorInput);
            this.seeker = computeSeeker;
            this.extractorOutput.seekMap(computeSeeker);
            Format.Builder encoderPadding = new Format.Builder().setContainerMimeType("audio/mpeg").setSampleMimeType(this.synchronizedHeader.mimeType).setMaxInputSize(4096).setChannelCount(this.synchronizedHeader.channels).setSampleRate(this.synchronizedHeader.sampleRate).setEncoderDelay(this.gaplessInfoHolder.encoderDelay).setEncoderPadding(this.gaplessInfoHolder.encoderPadding);
            if ((this.flags & 8) != 0) {
                metadata2 = null;
            } else {
                metadata2 = this.metadata;
            }
            Format.Builder metadata3 = encoderPadding.setMetadata(metadata2);
            if (this.seeker.getAverageBitrate() != -2147483647) {
                metadata3.setAverageBitrate(this.seeker.getAverageBitrate());
            }
            this.currentTrackOutput.format(metadata3.build());
            this.firstSamplePosition = extractorInput.getPosition();
        } else if (this.firstSamplePosition != 0) {
            long position = extractorInput.getPosition();
            long j2 = this.firstSamplePosition;
            if (position < j2) {
                extractorInput.skipFully((int) (j2 - position));
            }
        }
        return readSample(extractorInput);
    }

    private int readSample(ExtractorInput extractorInput) {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int readInt = this.scratch.readInt();
            if (!headersMatch(readInt, (long) this.synchronizedHeaderData) || MpegAudioUtil.getFrameSize(readInt) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            this.synchronizedHeader.setForHeaderData(readInt);
            if (this.basisTimeUs == -9223372036854775807L) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != -9223372036854775807L) {
                    this.basisTimeUs = (this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0)) + this.basisTimeUs;
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
            long position = extractorInput.getPosition();
            MpegAudioUtil.Header header = this.synchronizedHeader;
            this.endPositionOfLastSampleRead = position + ((long) header.frameSize);
            Seeker seeker2 = this.seeker;
            if (seeker2 instanceof IndexSeeker) {
                IndexSeeker indexSeeker = (IndexSeeker) seeker2;
                indexSeeker.maybeAddSeekPoint(computeTimeUs(this.samplesRead + ((long) header.samplesPerFrame)), this.endPositionOfLastSampleRead);
                if (this.isSeekInProgress && indexSeeker.isTimeUsInIndex(this.seekTimeUs)) {
                    this.isSeekInProgress = false;
                    this.currentTrackOutput = this.realTrackOutput;
                }
            }
        }
        int sampleData = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.sampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        int i2 = this.sampleBytesRemaining - sampleData;
        this.sampleBytesRemaining = i2;
        if (i2 > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, (TrackOutput.CryptoData) null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean shouldFallbackToConstantBitrateSeeking(Seeker seeker2) {
        if (seeker2.isSeekable() || (this.flags & 1) == 0) {
            return false;
        }
        return true;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z) {
        int i2;
        int i7;
        int i8;
        int frameSize;
        Id3Decoder.FramePredicate framePredicate;
        if (z) {
            i2 = 32768;
        } else {
            i2 = 131072;
        }
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            if ((this.flags & 8) == 0) {
                framePredicate = null;
            } else {
                framePredicate = REQUIRED_ID3_FRAME_PREDICATE;
            }
            Metadata peekId3Data = this.id3Peeker.peekId3Data(extractorInput, framePredicate);
            this.metadata = peekId3Data;
            if (peekId3Data != null) {
                this.gaplessInfoHolder.setFromMetadata(peekId3Data);
            }
            i7 = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(i7);
            }
            i8 = 0;
        } else {
            i7 = 0;
            i8 = 0;
        }
        int i10 = i8;
        int i11 = i10;
        while (true) {
            if (!peekEndOfStreamOrHeader(extractorInput)) {
                this.scratch.setPosition(0);
                int readInt = this.scratch.readInt();
                if ((i8 == 0 || headersMatch(readInt, (long) i8)) && (frameSize = MpegAudioUtil.getFrameSize(readInt)) != -1) {
                    i10++;
                    if (i10 != 1) {
                        if (i10 == 4) {
                            break;
                        }
                    } else {
                        this.synchronizedHeader.setForHeaderData(readInt);
                        i8 = readInt;
                    }
                    extractorInput.advancePeekPosition(frameSize - 4);
                } else {
                    int i12 = i11 + 1;
                    if (i11 != i2) {
                        if (z) {
                            extractorInput.resetPeekPosition();
                            extractorInput.advancePeekPosition(i7 + i12);
                        } else {
                            extractorInput.skipFully(1);
                        }
                        i10 = 0;
                        i11 = i12;
                        i8 = 0;
                    } else if (z) {
                        return false;
                    } else {
                        maybeUpdateCbrDurationToLastSample();
                        throw new EOFException();
                    }
                }
            } else if (i10 <= 0) {
                maybeUpdateCbrDurationToLastSample();
                throw new EOFException();
            }
        }
        if (z) {
            extractorInput.skipFully(i7 + i11);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i8;
        return true;
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        TrackOutput track = extractorOutput2.track(0, 1);
        this.realTrackOutput = track;
        this.currentTrackOutput = track;
        this.extractorOutput.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        assertInitialized();
        int readInternal = readInternal(extractorInput);
        if (readInternal == -1 && (this.seeker instanceof IndexSeeker)) {
            long computeTimeUs = computeTimeUs(this.samplesRead);
            if (this.seeker.getDurationUs() != computeTimeUs) {
                ((IndexSeeker) this.seeker).setDurationUs(computeTimeUs);
                this.extractorOutput.seekMap(this.seeker);
                this.realTrackOutput.durationUs(this.seeker.getDurationUs());
            }
        }
        return readInternal;
    }

    public void seek(long j2, long j3) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = -9223372036854775807L;
        this.samplesRead = 0;
        this.sampleBytesRemaining = 0;
        this.seekTimeUs = j3;
        Seeker seeker2 = this.seeker;
        if ((seeker2 instanceof IndexSeeker) && !((IndexSeeker) seeker2).isTimeUsInIndex(j3)) {
            this.isSeekInProgress = true;
            this.currentTrackOutput = this.skippingTrackOutput;
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        return synchronize(extractorInput, true);
    }

    public Mp3Extractor(int i2) {
        this(i2, -9223372036854775807L);
    }

    public Mp3Extractor(int i2, long j2) {
        this.flags = (i2 & 2) != 0 ? i2 | 1 : i2;
        this.forcedFirstSampleTimestampUs = j2;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = -9223372036854775807L;
        this.id3Peeker = new Id3Peeker();
        DiscardingTrackOutput discardingTrackOutput = new DiscardingTrackOutput();
        this.skippingTrackOutput = discardingTrackOutput;
        this.currentTrackOutput = discardingTrackOutput;
        this.endPositionOfLastSampleRead = -1;
    }

    private Seeker getConstantBitrateSeeker(long j2, XingFrame xingFrame, long j3) {
        long j8;
        long j10;
        XingFrame xingFrame2 = xingFrame;
        long computeDurationUs = xingFrame2.computeDurationUs();
        if (computeDurationUs == -9223372036854775807L) {
            return null;
        }
        long j11 = xingFrame2.dataSize;
        if (j11 != -1) {
            long j12 = j2 + j11;
            j8 = j11 - ((long) xingFrame2.header.frameSize);
            j10 = j12;
        } else if (j3 == -1) {
            return null;
        } else {
            j8 = (j3 - j2) - ((long) xingFrame2.header.frameSize);
            j10 = j3;
        }
        long j13 = j8;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        return new ConstantBitrateSeeker(j10, j2 + ((long) xingFrame2.header.frameSize), C0246a.N(Util.scaleLargeValue(j13, 8000000, computeDurationUs, roundingMode)), C0246a.N(L2.a.m(j13, xingFrame2.frameCount, roundingMode)), false);
    }

    public void release() {
    }
}
