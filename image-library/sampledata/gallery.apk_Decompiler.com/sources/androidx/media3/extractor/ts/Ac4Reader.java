package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Ac4Reader implements ElementaryStreamReader {
    private int bytesRead;
    private final String containerMimeType;
    private Format format;
    private String formatId;
    private boolean hasCRC;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWasAC;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public Ac4Reader(String str) {
        this((String) null, 0, str);
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i2) {
        int min = Math.min(parsableByteArray.bytesLeft(), i2 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int i7 = this.bytesRead + min;
        this.bytesRead = i7;
        if (i7 == i2) {
            return true;
        }
        return false;
    }

    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        Ac4Util.SyncFrameInfo parseAc4SyncframeInfo = Ac4Util.parseAc4SyncframeInfo(this.headerScratchBits);
        Format format2 = this.format;
        if (format2 == null || parseAc4SyncframeInfo.channelCount != format2.channelCount || parseAc4SyncframeInfo.sampleRate != format2.sampleRate || !"audio/ac4".equals(format2.sampleMimeType)) {
            Format build = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType("audio/ac4").setChannelCount(parseAc4SyncframeInfo.channelCount).setSampleRate(parseAc4SyncframeInfo.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
            this.format = build;
            this.output.format(build);
        }
        this.sampleSize = parseAc4SyncframeInfo.frameSize;
        this.sampleDurationUs = (((long) parseAc4SyncframeInfo.sampleCount) * 1000000) / ((long) this.format.sampleRate);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean skipToNextSync(androidx.media3.common.util.ParsableByteArray r6) {
        /*
            r5 = this;
        L_0x0000:
            int r0 = r6.bytesLeft()
            r1 = 0
            if (r0 <= 0) goto L_0x0031
            boolean r0 = r5.lastByteWasAC
            r2 = 172(0xac, float:2.41E-43)
            r3 = 1
            if (r0 != 0) goto L_0x0018
            int r0 = r6.readUnsignedByte()
            if (r0 != r2) goto L_0x0015
            r1 = r3
        L_0x0015:
            r5.lastByteWasAC = r1
            goto L_0x0000
        L_0x0018:
            int r0 = r6.readUnsignedByte()
            if (r0 != r2) goto L_0x0020
            r2 = r3
            goto L_0x0021
        L_0x0020:
            r2 = r1
        L_0x0021:
            r5.lastByteWasAC = r2
            r2 = 64
            r4 = 65
            if (r0 == r2) goto L_0x002b
            if (r0 != r4) goto L_0x0000
        L_0x002b:
            if (r0 != r4) goto L_0x002e
            r1 = r3
        L_0x002e:
            r5.hasCRC = r1
            return r3
        L_0x0031:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.Ac4Reader.skipToNextSync(androidx.media3.common.util.ParsableByteArray):boolean");
    }

    public void consume(ParsableByteArray parsableByteArray) {
        int i2;
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i7 = this.state;
            boolean z = true;
            if (i7 != 0) {
                if (i7 != 1) {
                    if (i7 == 2) {
                        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, min);
                        int i8 = this.bytesRead + min;
                        this.bytesRead = i8;
                        if (i8 == this.sampleSize) {
                            if (this.timeUs == -9223372036854775807L) {
                                z = false;
                            }
                            Assertions.checkState(z);
                            this.output.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, (TrackOutput.CryptoData) null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), 16)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 16);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
                this.headerScratchBytes.getData()[0] = -84;
                byte[] data = this.headerScratchBytes.getData();
                if (this.hasCRC) {
                    i2 = 65;
                } else {
                    i2 = 64;
                }
                data[1] = (byte) i2;
                this.bytesRead = 2;
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    public void packetStarted(long j2, int i2) {
        this.timeUs = j2;
    }

    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = -9223372036854775807L;
    }

    public Ac4Reader(String str, int i2, String str2) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[16]);
        this.headerScratchBits = parsableBitArray;
        this.headerScratchBytes = new ParsableByteArray(parsableBitArray.data);
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = -9223372036854775807L;
        this.language = str;
        this.roleFlags = i2;
        this.containerMimeType = str2;
    }

    public void packetFinished(boolean z) {
    }
}
