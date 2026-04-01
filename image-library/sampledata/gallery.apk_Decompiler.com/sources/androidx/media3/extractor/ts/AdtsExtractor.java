package androidx.media3.extractor.ts;

import P.a;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(11);
    private int averageFrameSize;
    private ExtractorOutput extractorOutput;
    private long firstFramePosition;
    private long firstSampleTimestampUs;
    private final int flags;
    private boolean hasCalculatedAverageFrameSize;
    private boolean hasOutputSeekMap;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private final ParsableByteArray scratch;
    private final ParsableBitArray scratchBits;
    private boolean startedPacket;

    public AdtsExtractor() {
        this(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void calculateAverageFrameSize(androidx.media3.extractor.ExtractorInput r10) {
        /*
            r9 = this;
            boolean r0 = r9.hasCalculatedAverageFrameSize
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = -1
            r9.averageFrameSize = r0
            r10.resetPeekPosition()
            long r1 = r10.getPosition()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0018
            r9.peekId3Header(r10)
        L_0x0018:
            r1 = 0
            r2 = r1
        L_0x001a:
            r5 = 1
            androidx.media3.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            byte[] r6 = r6.getData()     // Catch:{ EOFException -> 0x0076 }
            r7 = 2
            boolean r6 = r10.peekFully(r6, r1, r7, r5)     // Catch:{ EOFException -> 0x0076 }
            if (r6 == 0) goto L_0x0076
            androidx.media3.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            r6.setPosition(r1)     // Catch:{ EOFException -> 0x0076 }
            androidx.media3.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            int r6 = r6.readUnsignedShort()     // Catch:{ EOFException -> 0x0076 }
            boolean r6 = androidx.media3.extractor.ts.AdtsReader.isAdtsSyncWord(r6)     // Catch:{ EOFException -> 0x0076 }
            if (r6 != 0) goto L_0x003a
            goto L_0x0077
        L_0x003a:
            androidx.media3.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            byte[] r6 = r6.getData()     // Catch:{ EOFException -> 0x0076 }
            r7 = 4
            boolean r6 = r10.peekFully(r6, r1, r7, r5)     // Catch:{ EOFException -> 0x0076 }
            if (r6 != 0) goto L_0x0048
            goto L_0x0076
        L_0x0048:
            androidx.media3.common.util.ParsableBitArray r6 = r9.scratchBits     // Catch:{ EOFException -> 0x0076 }
            r7 = 14
            r6.setPosition(r7)     // Catch:{ EOFException -> 0x0076 }
            androidx.media3.common.util.ParsableBitArray r6 = r9.scratchBits     // Catch:{ EOFException -> 0x0076 }
            r7 = 13
            int r6 = r6.readBits(r7)     // Catch:{ EOFException -> 0x0076 }
            r7 = 6
            if (r6 <= r7) goto L_0x006c
            long r7 = (long) r6     // Catch:{ EOFException -> 0x0076 }
            long r3 = r3 + r7
            int r2 = r2 + 1
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r2 != r7) goto L_0x0063
            goto L_0x006b
        L_0x0063:
            int r6 = r6 + -6
            boolean r6 = r10.advancePeekPosition(r6, r5)     // Catch:{ EOFException -> 0x0076 }
            if (r6 != 0) goto L_0x001a
        L_0x006b:
            goto L_0x0076
        L_0x006c:
            r9.hasCalculatedAverageFrameSize = r5     // Catch:{ EOFException -> 0x0076 }
            java.lang.String r1 = "Malformed ADTS stream"
            r6 = 0
            androidx.media3.common.ParserException r1 = androidx.media3.common.ParserException.createForMalformedContainer(r1, r6)     // Catch:{ EOFException -> 0x0076 }
            throw r1     // Catch:{ EOFException -> 0x0076 }
        L_0x0076:
            r1 = r2
        L_0x0077:
            r10.resetPeekPosition()
            if (r1 <= 0) goto L_0x0082
            long r0 = (long) r1
            long r3 = r3 / r0
            int r10 = (int) r3
            r9.averageFrameSize = r10
            goto L_0x0084
        L_0x0082:
            r9.averageFrameSize = r0
        L_0x0084:
            r9.hasCalculatedAverageFrameSize = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.AdtsExtractor.calculateAverageFrameSize(androidx.media3.extractor.ExtractorInput):void");
    }

    private static int getBitrateFromFrameSize(int i2, long j2) {
        return (int) ((((long) i2) * 8000000) / j2);
    }

    private SeekMap getConstantBitrateSeekMap(long j2, boolean z) {
        return new ConstantBitrateSeekMap(j2, this.firstFramePosition, getBitrateFromFrameSize(this.averageFrameSize, this.reader.getSampleDurationUs()), this.averageFrameSize, z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new AdtsExtractor()};
    }

    private void maybeOutputSeekMap(long j2, boolean z) {
        boolean z3;
        if (!this.hasOutputSeekMap) {
            boolean z7 = false;
            if ((this.flags & 1) == 0 || this.averageFrameSize <= 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (!z3 || this.reader.getSampleDurationUs() != -9223372036854775807L || z) {
                if (!z3 || this.reader.getSampleDurationUs() == -9223372036854775807L) {
                    this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
                } else {
                    ExtractorOutput extractorOutput2 = this.extractorOutput;
                    if ((this.flags & 2) != 0) {
                        z7 = true;
                    }
                    extractorOutput2.seekMap(getConstantBitrateSeekMap(j2, z7));
                }
                this.hasOutputSeekMap = true;
            }
        }
    }

    private int peekId3Header(ExtractorInput extractorInput) {
        int i2 = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.getData(), 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != 4801587) {
                break;
            }
            this.scratch.skipBytes(3);
            int readSynchSafeInt = this.scratch.readSynchSafeInt();
            i2 += readSynchSafeInt + 10;
            extractorInput.advancePeekPosition(readSynchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i2);
        if (this.firstFramePosition == -1) {
            this.firstFramePosition = (long) i2;
        }
        return i2;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.reader.createTracks(extractorOutput2, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput2.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        boolean z;
        Assertions.checkStateNotNull(this.extractorOutput);
        long length = extractorInput.getLength();
        int i2 = this.flags;
        if (!((i2 & 2) == 0 && ((i2 & 1) == 0 || length == -1))) {
            calculateAverageFrameSize(extractorInput);
        }
        int read = extractorInput.read(this.packetBuffer.getData(), 0, 2048);
        if (read == -1) {
            z = true;
        } else {
            z = false;
        }
        maybeOutputSeekMap(length, z);
        if (z) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(read);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }

    public void seek(long j2, long j3) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = j3;
    }

    public boolean sniff(ExtractorInput extractorInput) {
        int peekId3Header = peekId3Header(extractorInput);
        int i2 = peekId3Header;
        int i7 = 0;
        int i8 = 0;
        do {
            extractorInput.peekFully(this.scratch.getData(), 0, 2);
            this.scratch.setPosition(0);
            if (!AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort())) {
                i2++;
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i2);
            } else {
                i7++;
                if (i7 >= 4 && i8 > 188) {
                    return true;
                }
                extractorInput.peekFully(this.scratch.getData(), 0, 4);
                this.scratchBits.setPosition(14);
                int readBits = this.scratchBits.readBits(13);
                if (readBits <= 6) {
                    i2++;
                    extractorInput.resetPeekPosition();
                    extractorInput.advancePeekPosition(i2);
                } else {
                    extractorInput.advancePeekPosition(readBits - 6);
                    i8 += readBits;
                }
            }
            i7 = 0;
            i8 = 0;
        } while (i2 - peekId3Header < 8192);
        return false;
    }

    public AdtsExtractor(int i2) {
        this.flags = (i2 & 2) != 0 ? i2 | 1 : i2;
        this.reader = new AdtsReader(true, Encode.CodecsMime.AUDIO_CODEC_AAC);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1;
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        this.scratch = parsableByteArray;
        this.scratchBits = new ParsableBitArray(parsableByteArray.getData());
    }

    public void release() {
    }
}
