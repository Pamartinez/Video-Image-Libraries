package androidx.media3.extractor.ts;

import P.a;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TsExtractor implements Extractor {
    @Deprecated
    public static final ExtractorsFactory FACTORY = new a(13);
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private final TsDurationReader durationReader;
    private final int extractorFlags;
    private boolean hasOutputSeekMap;
    /* access modifiers changed from: private */
    public TsPayloadReader id3Reader;
    /* access modifiers changed from: private */
    public final int mode;
    /* access modifiers changed from: private */
    public ExtractorOutput output;
    /* access modifiers changed from: private */
    public final TsPayloadReader.Factory payloadReaderFactory;
    /* access modifiers changed from: private */
    public int pcrPid;
    private boolean pendingSeekToStart;
    /* access modifiers changed from: private */
    public int remainingPmts;
    private final SubtitleParser.Factory subtitleParserFactory;
    /* access modifiers changed from: private */
    public final List<TimestampAdjuster> timestampAdjusters;
    private final int timestampSearchBytes;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackIds;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackPids;
    /* access modifiers changed from: private */
    public boolean tracksEnded;
    private TsBinarySearchSeeker tsBinarySearchSeeker;
    private final ParsableByteArray tsPacketBuffer;
    /* access modifiers changed from: private */
    public final SparseArray<TsPayloadReader> tsPayloadReaders;

    public TsExtractor(int i2, SubtitleParser.Factory factory) {
        this(1, i2, factory, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(0), 112800);
    }

    public static /* synthetic */ int access$108(TsExtractor tsExtractor) {
        int i2 = tsExtractor.remainingPmts;
        tsExtractor.remainingPmts = i2 + 1;
        return i2;
    }

    private boolean fillBufferWithAtLeastOnePacket(ExtractorInput extractorInput) {
        byte[] data = this.tsPacketBuffer.getData();
        if (9400 - this.tsPacketBuffer.getPosition() < 188) {
            int bytesLeft = this.tsPacketBuffer.bytesLeft();
            if (bytesLeft > 0) {
                System.arraycopy(data, this.tsPacketBuffer.getPosition(), data, 0, bytesLeft);
            }
            this.tsPacketBuffer.reset(data, bytesLeft);
        }
        while (this.tsPacketBuffer.bytesLeft() < 188) {
            int limit = this.tsPacketBuffer.limit();
            int read = extractorInput.read(data, limit, 9400 - limit);
            if (read == -1) {
                return false;
            }
            this.tsPacketBuffer.setLimit(limit + read);
        }
        return true;
    }

    private int findEndOfFirstTsPacketInBuffer() {
        int position = this.tsPacketBuffer.getPosition();
        int limit = this.tsPacketBuffer.limit();
        int findSyncBytePosition = TsUtil.findSyncBytePosition(this.tsPacketBuffer.getData(), position, limit);
        this.tsPacketBuffer.setPosition(findSyncBytePosition);
        int i2 = findSyncBytePosition + 188;
        if (i2 > limit) {
            int i7 = (findSyncBytePosition - position) + this.bytesSinceLastSync;
            this.bytesSinceLastSync = i7;
            if (this.mode != 2 || i7 <= 376) {
                return i2;
            }
            throw ParserException.createForMalformedContainer("Cannot find sync byte. Most likely not a Transport Stream.", (Throwable) null);
        }
        this.bytesSinceLastSync = 0;
        return i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new TsExtractor(1, SubtitleParser.Factory.UNSUPPORTED)};
    }

    private void maybeOutputSeekMap(long j2) {
        if (!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            if (this.durationReader.getDurationUs() != -9223372036854775807L) {
                TsBinarySearchSeeker tsBinarySearchSeeker2 = new TsBinarySearchSeeker(this.durationReader.getPcrTimestampAdjuster(), this.durationReader.getDurationUs(), j2, this.pcrPid, this.timestampSearchBytes);
                this.tsBinarySearchSeeker = tsBinarySearchSeeker2;
                this.output.seekMap(tsBinarySearchSeeker2.getSeekMap());
                return;
            }
            this.output.seekMap(new SeekMap.Unseekable(this.durationReader.getDurationUs()));
        }
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> createInitialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
        int size = createInitialPayloadReaders.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.tsPayloadReaders.put(createInitialPayloadReaders.keyAt(i2), createInitialPayloadReaders.valueAt(i2));
        }
        this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
        this.id3Reader = null;
    }

    private boolean shouldConsumePacketPayload(int i2) {
        if (this.mode == 2 || this.tracksEnded || !this.trackPids.get(i2, false)) {
            return true;
        }
        return false;
    }

    public void init(ExtractorOutput extractorOutput) {
        if ((this.extractorFlags & 1) == 0) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.output = extractorOutput;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        boolean z;
        int i2;
        boolean z3;
        TsPayloadReader tsPayloadReader;
        int i7;
        int i8;
        ExtractorInput extractorInput2 = extractorInput;
        PositionHolder positionHolder2 = positionHolder;
        long length = extractorInput2.getLength();
        if (this.mode == 2) {
            z = true;
        } else {
            z = false;
        }
        if (this.tracksEnded) {
            if (length != -1 && !z && !this.durationReader.isDurationReadFinished()) {
                return this.durationReader.readDuration(extractorInput2, positionHolder2, this.pcrPid);
            }
            maybeOutputSeekMap(length);
            if (this.pendingSeekToStart) {
                this.pendingSeekToStart = false;
                seek(0, 0);
                if (extractorInput2.getPosition() != 0) {
                    positionHolder2.position = 0;
                    return 1;
                }
            }
            TsBinarySearchSeeker tsBinarySearchSeeker2 = this.tsBinarySearchSeeker;
            if (tsBinarySearchSeeker2 != null && tsBinarySearchSeeker2.isSeeking()) {
                return this.tsBinarySearchSeeker.handlePendingSeek(extractorInput2, positionHolder2);
            }
        }
        if (!fillBufferWithAtLeastOnePacket(extractorInput)) {
            for (int i10 = 0; i10 < this.tsPayloadReaders.size(); i10++) {
                TsPayloadReader valueAt = this.tsPayloadReaders.valueAt(i10);
                if (valueAt instanceof PesReader) {
                    PesReader pesReader = (PesReader) valueAt;
                    if (pesReader.canConsumeSynthesizedEmptyPusi(z)) {
                        pesReader.consume(new ParsableByteArray(), 1);
                    }
                }
            }
            return -1;
        }
        int findEndOfFirstTsPacketInBuffer = findEndOfFirstTsPacketInBuffer();
        int limit = this.tsPacketBuffer.limit();
        if (findEndOfFirstTsPacketInBuffer > limit) {
            return 0;
        }
        int readInt = this.tsPacketBuffer.readInt();
        if ((8388608 & readInt) != 0) {
            this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
            return 0;
        }
        if ((4194304 & readInt) != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i11 = (2096896 & readInt) >> 8;
        if ((readInt & 32) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if ((readInt & 16) != 0) {
            tsPayloadReader = this.tsPayloadReaders.get(i11);
        } else {
            tsPayloadReader = null;
        }
        if (tsPayloadReader == null) {
            this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
            return 0;
        }
        if (this.mode != 2) {
            int i12 = readInt & 15;
            i7 = 0;
            int i13 = this.continuityCounters.get(i11, i12 - 1);
            this.continuityCounters.put(i11, i12);
            if (i13 == i12) {
                this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
                return 0;
            } else if (i12 != ((i13 + 1) & 15)) {
                tsPayloadReader.seek();
            }
        } else {
            i7 = 0;
        }
        if (z3) {
            int readUnsignedByte = this.tsPacketBuffer.readUnsignedByte();
            if ((this.tsPacketBuffer.readUnsignedByte() & 64) != 0) {
                i8 = 2;
            } else {
                i8 = i7;
            }
            i2 |= i8;
            this.tsPacketBuffer.skipBytes(readUnsignedByte - 1);
        }
        boolean z7 = this.tracksEnded;
        if (shouldConsumePacketPayload(i11)) {
            this.tsPacketBuffer.setLimit(findEndOfFirstTsPacketInBuffer);
            tsPayloadReader.consume(this.tsPacketBuffer, i2);
            this.tsPacketBuffer.setLimit(limit);
        }
        if (this.mode != 2 && !z7 && this.tracksEnded && length != -1) {
            this.pendingSeekToStart = true;
        }
        this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
        return i7;
    }

    public void seek(long j2, long j3) {
        boolean z;
        TsBinarySearchSeeker tsBinarySearchSeeker2;
        boolean z3;
        if (this.mode != 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        int size = this.timestampAdjusters.size();
        for (int i2 = 0; i2 < size; i2++) {
            TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i2);
            if (timestampAdjuster.getTimestampOffsetUs() == -9223372036854775807L) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z3) {
                long firstSampleTimestampUs = timestampAdjuster.getFirstSampleTimestampUs();
                if (firstSampleTimestampUs == -9223372036854775807L || firstSampleTimestampUs == 0 || firstSampleTimestampUs == j3) {
                    z3 = false;
                } else {
                    z3 = true;
                }
            }
            if (z3) {
                timestampAdjuster.reset(j3);
            }
        }
        if (!(j3 == 0 || (tsBinarySearchSeeker2 = this.tsBinarySearchSeeker) == null)) {
            tsBinarySearchSeeker2.setSeekTargetUs(j3);
        }
        this.tsPacketBuffer.reset(0);
        this.continuityCounters.clear();
        for (int i7 = 0; i7 < this.tsPayloadReaders.size(); i7++) {
            this.tsPayloadReaders.valueAt(i7).seek();
        }
        this.bytesSinceLastSync = 0;
    }

    public boolean sniff(ExtractorInput extractorInput) {
        byte[] data = this.tsPacketBuffer.getData();
        extractorInput.peekFully(data, 0, 940);
        int i2 = 0;
        while (i2 < 188) {
            int i7 = 0;
            while (i7 < 5) {
                if (data[(i7 * 188) + i2] != 71) {
                    i2++;
                } else {
                    i7++;
                }
            }
            extractorInput.skipFully(i2);
            return true;
        }
        return false;
    }

    public TsExtractor(int i2, int i7, SubtitleParser.Factory factory, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory2, int i8) {
        this.payloadReaderFactory = (TsPayloadReader.Factory) Assertions.checkNotNull(factory2);
        this.timestampSearchBytes = i8;
        this.mode = i2;
        this.extractorFlags = i7;
        this.subtitleParserFactory = factory;
        if (i2 == 1 || i2 == 2) {
            this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
        } else {
            ArrayList arrayList = new ArrayList();
            this.timestampAdjusters = arrayList;
            arrayList.add(timestampAdjuster);
        }
        this.tsPacketBuffer = new ParsableByteArray(new byte[9400], 0);
        this.trackIds = new SparseBooleanArray();
        this.trackPids = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray<>();
        this.continuityCounters = new SparseIntArray();
        this.durationReader = new TsDurationReader(i8);
        this.output = ExtractorOutput.PLACEHOLDER;
        this.pcrPid = -1;
        resetPayloadReaders();
    }

    public void release() {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch = new ParsableBitArray(new byte[4]);

        public PatReader() {
        }

        public void consume(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.readUnsignedByte() == 0 && (parsableByteArray.readUnsignedByte() & 128) != 0) {
                parsableByteArray.skipBytes(6);
                int bytesLeft = parsableByteArray.bytesLeft() / 4;
                for (int i2 = 0; i2 < bytesLeft; i2++) {
                    parsableByteArray.readBytes(this.patScratch, 4);
                    int readBits = this.patScratch.readBits(16);
                    this.patScratch.skipBits(3);
                    if (readBits == 0) {
                        this.patScratch.skipBits(13);
                    } else {
                        int readBits2 = this.patScratch.readBits(13);
                        if (TsExtractor.this.tsPayloadReaders.get(readBits2) == null) {
                            TsExtractor.this.tsPayloadReaders.put(readBits2, new SectionReader(new PmtReader(readBits2)));
                            TsExtractor.access$108(TsExtractor.this);
                        }
                    }
                }
                if (TsExtractor.this.mode != 2) {
                    TsExtractor.this.tsPayloadReaders.remove(0);
                }
            }
        }

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PmtReader implements SectionPayloadReader {
        private final int pid;
        private final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5]);
        private final SparseIntArray trackIdToPidScratch = new SparseIntArray();
        private final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();

        public PmtReader(int i2) {
            this.pid = i2;
        }

        private TsPayloadReader.EsInfo readEsInfo(ParsableByteArray parsableByteArray, int i2) {
            int i7;
            int position = parsableByteArray.getPosition();
            int i8 = i2 + position;
            int i10 = -1;
            String str = null;
            ArrayList arrayList = null;
            int i11 = 0;
            while (parsableByteArray.getPosition() < i8) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                int position2 = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
                if (position2 > i8) {
                    break;
                }
                if (readUnsignedByte == 5) {
                    long readUnsignedInt = parsableByteArray.readUnsignedInt();
                    if (readUnsignedInt != 1094921523) {
                        if (readUnsignedInt != 1161904947) {
                            if (readUnsignedInt != 1094921524) {
                                if (readUnsignedInt == 1212503619) {
                                    i10 = 36;
                                }
                                parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                            }
                        }
                        i10 = 135;
                        parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                    }
                    i10 = 129;
                    parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                } else {
                    if (readUnsignedByte != 106) {
                        if (readUnsignedByte != 122) {
                            if (readUnsignedByte == 127) {
                                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                                if (readUnsignedByte2 != 21) {
                                    if (readUnsignedByte2 == 14) {
                                        i10 = 136;
                                    } else if (readUnsignedByte2 == 33) {
                                        i10 = 139;
                                    }
                                }
                            } else {
                                if (readUnsignedByte == 123) {
                                    i7 = 138;
                                } else if (readUnsignedByte == 10) {
                                    String trim = parsableByteArray.readString(3).trim();
                                    i11 = parsableByteArray.readUnsignedByte();
                                    str = trim;
                                } else if (readUnsignedByte == 89) {
                                    ArrayList arrayList2 = new ArrayList();
                                    while (parsableByteArray.getPosition() < position2) {
                                        String trim2 = parsableByteArray.readString(3).trim();
                                        int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                                        byte[] bArr = new byte[4];
                                        parsableByteArray.readBytes(bArr, 0, 4);
                                        arrayList2.add(new TsPayloadReader.DvbSubtitleInfo(trim2, readUnsignedByte3, bArr));
                                    }
                                    arrayList = arrayList2;
                                    i10 = 89;
                                } else if (readUnsignedByte == 111) {
                                    i7 = 257;
                                }
                                i10 = i7;
                            }
                            parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                        }
                        i10 = 135;
                        parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                    }
                    i10 = 129;
                    parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                }
                i10 = 172;
                parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
            }
            parsableByteArray.setPosition(i8);
            return new TsPayloadReader.EsInfo(i10, str, i11, arrayList, Arrays.copyOfRange(parsableByteArray.getData(), position, i8));
        }

        public void consume(ParsableByteArray parsableByteArray) {
            TimestampAdjuster timestampAdjuster;
            int i2;
            int i7;
            TsPayloadReader tsPayloadReader;
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            if (parsableByteArray2.readUnsignedByte() == 2) {
                if (TsExtractor.this.mode == 1 || TsExtractor.this.mode == 2 || TsExtractor.this.remainingPmts == 1) {
                    timestampAdjuster = (TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0);
                } else {
                    timestampAdjuster = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0)).getFirstSampleTimestampUs());
                    TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
                }
                if ((parsableByteArray2.readUnsignedByte() & 128) != 0) {
                    parsableByteArray2.skipBytes(1);
                    int readUnsignedShort = parsableByteArray2.readUnsignedShort();
                    int i8 = 3;
                    parsableByteArray2.skipBytes(3);
                    parsableByteArray2.readBytes(this.pmtScratch, 2);
                    this.pmtScratch.skipBits(3);
                    int i10 = 13;
                    int unused = TsExtractor.this.pcrPid = this.pmtScratch.readBits(13);
                    parsableByteArray2.readBytes(this.pmtScratch, 2);
                    int i11 = 4;
                    this.pmtScratch.skipBits(4);
                    parsableByteArray2.skipBytes(this.pmtScratch.readBits(12));
                    if (TsExtractor.this.mode == 2 && TsExtractor.this.id3Reader == null) {
                        TsPayloadReader.EsInfo esInfo = new TsPayloadReader.EsInfo(21, (String) null, 0, (List<TsPayloadReader.DvbSubtitleInfo>) null, Util.EMPTY_BYTE_ARRAY);
                        TsExtractor tsExtractor = TsExtractor.this;
                        TsPayloadReader unused2 = tsExtractor.id3Reader = tsExtractor.payloadReaderFactory.createPayloadReader(21, esInfo);
                        if (TsExtractor.this.id3Reader != null) {
                            TsExtractor.this.id3Reader.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(readUnsignedShort, 21, SerializeOptions.SORT));
                        }
                    }
                    this.trackIdToReaderScratch.clear();
                    this.trackIdToPidScratch.clear();
                    int bytesLeft = parsableByteArray2.bytesLeft();
                    while (bytesLeft > 0) {
                        parsableByteArray2.readBytes(this.pmtScratch, 5);
                        int readBits = this.pmtScratch.readBits(8);
                        this.pmtScratch.skipBits(i8);
                        int readBits2 = this.pmtScratch.readBits(i10);
                        this.pmtScratch.skipBits(i11);
                        int readBits3 = this.pmtScratch.readBits(12);
                        TsPayloadReader.EsInfo readEsInfo = readEsInfo(parsableByteArray2, readBits3);
                        if (readBits == 6 || readBits == 5) {
                            readBits = readEsInfo.streamType;
                        }
                        bytesLeft -= readBits3 + 5;
                        if (TsExtractor.this.mode == 2) {
                            i7 = readBits;
                        } else {
                            i7 = readBits2;
                        }
                        if (!TsExtractor.this.trackIds.get(i7)) {
                            if (TsExtractor.this.mode == 2 && readBits == 21) {
                                tsPayloadReader = TsExtractor.this.id3Reader;
                            } else {
                                tsPayloadReader = TsExtractor.this.payloadReaderFactory.createPayloadReader(readBits, readEsInfo);
                            }
                            if (TsExtractor.this.mode != 2 || readBits2 < this.trackIdToPidScratch.get(i7, SerializeOptions.SORT)) {
                                this.trackIdToPidScratch.put(i7, readBits2);
                                this.trackIdToReaderScratch.put(i7, tsPayloadReader);
                            }
                        }
                        i8 = 3;
                        i11 = 4;
                        i10 = 13;
                    }
                    int size = this.trackIdToPidScratch.size();
                    for (int i12 = 0; i12 < size; i12++) {
                        int keyAt = this.trackIdToPidScratch.keyAt(i12);
                        int valueAt = this.trackIdToPidScratch.valueAt(i12);
                        TsExtractor.this.trackIds.put(keyAt, true);
                        TsExtractor.this.trackPids.put(valueAt, true);
                        TsPayloadReader valueAt2 = this.trackIdToReaderScratch.valueAt(i12);
                        if (valueAt2 != null) {
                            if (valueAt2 != TsExtractor.this.id3Reader) {
                                valueAt2.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(readUnsignedShort, keyAt, SerializeOptions.SORT));
                            }
                            TsExtractor.this.tsPayloadReaders.put(valueAt, valueAt2);
                        }
                    }
                    if (TsExtractor.this.mode != 2) {
                        TsExtractor.this.tsPayloadReaders.remove(this.pid);
                        TsExtractor tsExtractor2 = TsExtractor.this;
                        if (tsExtractor2.mode == 1) {
                            i2 = 0;
                        } else {
                            i2 = TsExtractor.this.remainingPmts - 1;
                        }
                        int unused3 = tsExtractor2.remainingPmts = i2;
                        if (TsExtractor.this.remainingPmts == 0) {
                            TsExtractor.this.output.endTracks();
                            boolean unused4 = TsExtractor.this.tracksEnded = true;
                        }
                    } else if (!TsExtractor.this.tracksEnded) {
                        TsExtractor.this.output.endTracks();
                        int unused5 = TsExtractor.this.remainingPmts = 0;
                        boolean unused6 = TsExtractor.this.tracksEnded = true;
                    }
                }
            }
        }

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }
}
