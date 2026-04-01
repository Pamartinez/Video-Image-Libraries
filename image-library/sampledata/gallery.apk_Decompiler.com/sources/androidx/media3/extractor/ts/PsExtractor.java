package androidx.media3.extractor.ts;

import P.a;
import android.util.SparseArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(12);
    private final PsDurationReader durationReader;
    private boolean foundAllTracks;
    private boolean foundAudioTrack;
    private boolean foundVideoTrack;
    private boolean hasOutputSeekMap;
    private long lastTrackPosition;
    private ExtractorOutput output;
    private PsBinarySearchSeeker psBinarySearchSeeker;
    private final ParsableByteArray psPacketBuffer;
    private final SparseArray<PesReader> psPayloadReaders;
    private final TimestampAdjuster timestampAdjuster;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PesReader {
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch = new ParsableBitArray(new byte[64]);
        private boolean ptsFlag;
        private boolean seenFirstDts;
        private long timeUs;
        private final TimestampAdjuster timestampAdjuster;

        public PesReader(ElementaryStreamReader elementaryStreamReader, TimestampAdjuster timestampAdjuster2) {
            this.pesPayloadReader = elementaryStreamReader;
            this.timestampAdjuster = timestampAdjuster2;
        }

        private void parseHeader() {
            this.pesScratch.skipBits(8);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            this.extendedHeaderLength = this.pesScratch.readBits(8);
        }

        private void parseHeaderExtension() {
            this.timeUs = 0;
            if (this.ptsFlag) {
                this.pesScratch.skipBits(4);
                this.pesScratch.skipBits(1);
                this.pesScratch.skipBits(1);
                long readBits = (((long) this.pesScratch.readBits(3)) << 30) | ((long) (this.pesScratch.readBits(15) << 15)) | ((long) this.pesScratch.readBits(15));
                this.pesScratch.skipBits(1);
                if (!this.seenFirstDts && this.dtsFlag) {
                    this.pesScratch.skipBits(4);
                    this.pesScratch.skipBits(1);
                    this.pesScratch.skipBits(1);
                    this.pesScratch.skipBits(1);
                    this.timestampAdjuster.adjustTsTimestamp((((long) this.pesScratch.readBits(3)) << 30) | ((long) (this.pesScratch.readBits(15) << 15)) | ((long) this.pesScratch.readBits(15)));
                    this.seenFirstDts = true;
                }
                this.timeUs = this.timestampAdjuster.adjustTsTimestamp(readBits);
            }
        }

        public void consume(ParsableByteArray parsableByteArray) {
            parsableByteArray.readBytes(this.pesScratch.data, 0, 3);
            this.pesScratch.setPosition(0);
            parseHeader();
            parsableByteArray.readBytes(this.pesScratch.data, 0, this.extendedHeaderLength);
            this.pesScratch.setPosition(0);
            parseHeaderExtension();
            this.pesPayloadReader.packetStarted(this.timeUs, 4);
            this.pesPayloadReader.consume(parsableByteArray);
            this.pesPayloadReader.packetFinished(false);
        }

        public void seek() {
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }
    }

    public PsExtractor() {
        this(new TimestampAdjuster(0));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new PsExtractor()};
    }

    private void maybeOutputSeekMap(long j2) {
        if (!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            if (this.durationReader.getDurationUs() != -9223372036854775807L) {
                PsBinarySearchSeeker psBinarySearchSeeker2 = new PsBinarySearchSeeker(this.durationReader.getScrTimestampAdjuster(), this.durationReader.getDurationUs(), j2);
                this.psBinarySearchSeeker = psBinarySearchSeeker2;
                this.output.seekMap(psBinarySearchSeeker2.getSeekMap());
                return;
            }
            this.output.seekMap(new SeekMap.Unseekable(this.durationReader.getDurationUs()));
        }
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        long j2;
        long j3;
        ElementaryStreamReader elementaryStreamReader;
        Assertions.checkStateNotNull(this.output);
        long length = extractorInput.getLength();
        int i2 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        if (i2 != 0 && !this.durationReader.isDurationReadFinished()) {
            return this.durationReader.readDuration(extractorInput, positionHolder);
        }
        maybeOutputSeekMap(length);
        PsBinarySearchSeeker psBinarySearchSeeker2 = this.psBinarySearchSeeker;
        if (psBinarySearchSeeker2 != null && psBinarySearchSeeker2.isSeeking()) {
            return this.psBinarySearchSeeker.handlePendingSeek(extractorInput, positionHolder);
        }
        extractorInput.resetPeekPosition();
        if (i2 != 0) {
            j2 = length - extractorInput.getPeekPosition();
        } else {
            j2 = -1;
        }
        if ((j2 != -1 && j2 < 4) || !extractorInput.peekFully(this.psPacketBuffer.getData(), 0, 4, true)) {
            return -1;
        }
        this.psPacketBuffer.setPosition(0);
        int readInt = this.psPacketBuffer.readInt();
        if (readInt == 441) {
            return -1;
        }
        if (readInt == 442) {
            extractorInput.peekFully(this.psPacketBuffer.getData(), 0, 10);
            this.psPacketBuffer.setPosition(9);
            extractorInput.skipFully((this.psPacketBuffer.readUnsignedByte() & 7) + 14);
            return 0;
        } else if (readInt == 443) {
            extractorInput.peekFully(this.psPacketBuffer.getData(), 0, 2);
            this.psPacketBuffer.setPosition(0);
            extractorInput.skipFully(this.psPacketBuffer.readUnsignedShort() + 6);
            return 0;
        } else if (((readInt & -256) >> 8) != 1) {
            extractorInput.skipFully(1);
            return 0;
        } else {
            int i7 = readInt & ScoverState.TYPE_NFC_SMART_COVER;
            PesReader pesReader = this.psPayloadReaders.get(i7);
            if (!this.foundAllTracks) {
                if (pesReader == null) {
                    if (i7 == 189) {
                        elementaryStreamReader = new Ac3Reader("video/mp2p");
                        this.foundAudioTrack = true;
                        this.lastTrackPosition = extractorInput.getPosition();
                    } else if ((readInt & 224) == 192) {
                        elementaryStreamReader = new MpegAudioReader("video/mp2p");
                        this.foundAudioTrack = true;
                        this.lastTrackPosition = extractorInput.getPosition();
                    } else if ((readInt & 240) == 224) {
                        elementaryStreamReader = new H262Reader("video/mp2p");
                        this.foundVideoTrack = true;
                        this.lastTrackPosition = extractorInput.getPosition();
                    } else {
                        elementaryStreamReader = null;
                    }
                    if (elementaryStreamReader != null) {
                        elementaryStreamReader.createTracks(this.output, new TsPayloadReader.TrackIdGenerator(i7, 256));
                        pesReader = new PesReader(elementaryStreamReader, this.timestampAdjuster);
                        this.psPayloadReaders.put(i7, pesReader);
                    }
                }
                if (!this.foundAudioTrack || !this.foundVideoTrack) {
                    j3 = 1048576;
                } else {
                    j3 = this.lastTrackPosition + 8192;
                }
                if (extractorInput.getPosition() > j3) {
                    this.foundAllTracks = true;
                    this.output.endTracks();
                }
            }
            extractorInput.peekFully(this.psPacketBuffer.getData(), 0, 2);
            this.psPacketBuffer.setPosition(0);
            int readUnsignedShort = this.psPacketBuffer.readUnsignedShort() + 6;
            if (pesReader == null) {
                extractorInput.skipFully(readUnsignedShort);
            } else {
                this.psPacketBuffer.reset(readUnsignedShort);
                extractorInput.readFully(this.psPacketBuffer.getData(), 0, readUnsignedShort);
                this.psPacketBuffer.setPosition(6);
                pesReader.consume(this.psPacketBuffer);
                ParsableByteArray parsableByteArray = this.psPacketBuffer;
                parsableByteArray.setLimit(parsableByteArray.capacity());
            }
            return 0;
        }
    }

    public void seek(long j2, long j3) {
        boolean z;
        boolean z3 = true;
        if (this.timestampAdjuster.getTimestampOffsetUs() == -9223372036854775807L) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            long firstSampleTimestampUs = this.timestampAdjuster.getFirstSampleTimestampUs();
            if (firstSampleTimestampUs == -9223372036854775807L || firstSampleTimestampUs == 0 || firstSampleTimestampUs == j3) {
                z3 = false;
            }
            z = z3;
        }
        if (z) {
            this.timestampAdjuster.reset(j3);
        }
        PsBinarySearchSeeker psBinarySearchSeeker2 = this.psBinarySearchSeeker;
        if (psBinarySearchSeeker2 != null) {
            psBinarySearchSeeker2.setSeekTargetUs(j3);
        }
        for (int i2 = 0; i2 < this.psPayloadReaders.size(); i2++) {
            this.psPayloadReaders.valueAt(i2).seek();
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        byte[] bArr = new byte[14];
        extractorInput.peekFully(bArr, 0, 14);
        if (442 != (((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        extractorInput.advancePeekPosition(bArr[13] & 7);
        extractorInput.peekFully(bArr, 0, 3);
        if (1 == (((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255))) {
            return true;
        }
        return false;
    }

    public PsExtractor(TimestampAdjuster timestampAdjuster2) {
        this.timestampAdjuster = timestampAdjuster2;
        this.psPacketBuffer = new ParsableByteArray(4096);
        this.psPayloadReaders = new SparseArray<>();
        this.durationReader = new PsDurationReader();
    }

    public void release() {
    }
}
