package androidx.media3.extractor.ts;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DtsReader implements ElementaryStreamReader {
    private int bytesRead;
    private final String containerMimeType;
    private int extensionSubstreamHeaderSize = -1;
    private Format format;
    private String formatId;
    private int frameType;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state = 0;
    private int syncBytes;
    private long timeUs = -9223372036854775807L;
    private final AtomicInteger uhdAudioChunkId = new AtomicInteger();
    private int uhdHeaderSize = -1;

    public DtsReader(String str, int i2, int i7, String str2) {
        this.headerScratchBytes = new ParsableByteArray(new byte[i7]);
        this.language = str;
        this.roleFlags = i2;
        this.containerMimeType = str2;
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

    private void parseCoreHeader() {
        byte[] data = this.headerScratchBytes.getData();
        if (this.format == null) {
            Format parseDtsFormat = DtsUtil.parseDtsFormat(data, this.formatId, this.language, this.roleFlags, this.containerMimeType, (DrmInitData) null);
            this.format = parseDtsFormat;
            this.output.format(parseDtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(data);
        this.sampleDurationUs = (long) C0246a.N(Util.sampleCountToDurationUs((long) DtsUtil.parseDtsAudioSampleCount(data), this.format.sampleRate));
    }

    private void parseExtensionSubstreamHeader() {
        DtsUtil.DtsHeader parseDtsHdHeader = DtsUtil.parseDtsHdHeader(this.headerScratchBytes.getData());
        updateFormatWithDtsHeaderInfo(parseDtsHdHeader);
        this.sampleSize = parseDtsHdHeader.frameSize;
        long j2 = parseDtsHdHeader.frameDurationUs;
        if (j2 == -9223372036854775807L) {
            j2 = 0;
        }
        this.sampleDurationUs = j2;
    }

    private void parseUhdHeader() {
        DtsUtil.DtsHeader parseDtsUhdHeader = DtsUtil.parseDtsUhdHeader(this.headerScratchBytes.getData(), this.uhdAudioChunkId);
        if (this.frameType == 3) {
            updateFormatWithDtsHeaderInfo(parseDtsUhdHeader);
        }
        this.sampleSize = parseDtsUhdHeader.frameSize;
        long j2 = parseDtsUhdHeader.frameDurationUs;
        if (j2 == -9223372036854775807L) {
            j2 = 0;
        }
        this.sampleDurationUs = j2;
    }

    private boolean skipToNextSyncWord(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i2 = this.syncBytes << 8;
            this.syncBytes = i2;
            int readUnsignedByte = i2 | parsableByteArray.readUnsignedByte();
            this.syncBytes = readUnsignedByte;
            int frameType2 = DtsUtil.getFrameType(readUnsignedByte);
            this.frameType = frameType2;
            if (frameType2 != 0) {
                byte[] data = this.headerScratchBytes.getData();
                int i7 = this.syncBytes;
                data[0] = (byte) ((i7 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
                data[1] = (byte) ((i7 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
                data[2] = (byte) ((i7 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                data[3] = (byte) (i7 & ScoverState.TYPE_NFC_SMART_COVER);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    private void updateFormatWithDtsHeaderInfo(DtsUtil.DtsHeader dtsHeader) {
        int i2;
        Format.Builder builder;
        int i7 = dtsHeader.sampleRate;
        if (i7 != -2147483647 && (i2 = dtsHeader.channelCount) != -1) {
            Format format2 = this.format;
            if (format2 == null || i2 != format2.channelCount || i7 != format2.sampleRate || !Objects.equals(dtsHeader.mimeType, format2.sampleMimeType)) {
                Format format3 = this.format;
                if (format3 == null) {
                    builder = new Format.Builder();
                } else {
                    builder = format3.buildUpon();
                }
                Format build = builder.setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(dtsHeader.mimeType).setChannelCount(dtsHeader.channelCount).setSampleRate(dtsHeader.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
                this.format = build;
                this.output.format(build);
            }
        }
    }

    public void consume(ParsableByteArray parsableByteArray) {
        boolean z;
        int i2;
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            switch (this.state) {
                case 0:
                    if (skipToNextSyncWord(parsableByteArray)) {
                        int i7 = this.frameType;
                        if (i7 != 3 && i7 != 4) {
                            if (i7 != 1) {
                                this.state = 2;
                                break;
                            } else {
                                this.state = 1;
                                break;
                            }
                        } else {
                            this.state = 4;
                            break;
                        }
                    } else {
                        break;
                    }
                case 1:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), 18)) {
                        break;
                    } else {
                        parseCoreHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, 18);
                        this.state = 6;
                        break;
                    }
                case 2:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), 7)) {
                        break;
                    } else {
                        this.extensionSubstreamHeaderSize = DtsUtil.parseDtsHdHeaderSize(this.headerScratchBytes.getData());
                        this.state = 3;
                        break;
                    }
                case 3:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), this.extensionSubstreamHeaderSize)) {
                        break;
                    } else {
                        parseExtensionSubstreamHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.extensionSubstreamHeaderSize);
                        this.state = 6;
                        break;
                    }
                case 4:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), 6)) {
                        break;
                    } else {
                        int parseDtsUhdHeaderSize = DtsUtil.parseDtsUhdHeaderSize(this.headerScratchBytes.getData());
                        this.uhdHeaderSize = parseDtsUhdHeaderSize;
                        int i8 = this.bytesRead;
                        if (i8 > parseDtsUhdHeaderSize) {
                            int i10 = i8 - parseDtsUhdHeaderSize;
                            this.bytesRead = i8 - i10;
                            parsableByteArray.setPosition(parsableByteArray.getPosition() - i10);
                        }
                        this.state = 5;
                        break;
                    }
                case 5:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), this.uhdHeaderSize)) {
                        break;
                    } else {
                        parseUhdHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.uhdHeaderSize);
                        this.state = 6;
                        break;
                    }
                case 6:
                    int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    this.output.sampleData(parsableByteArray, min);
                    int i11 = this.bytesRead + min;
                    this.bytesRead = i11;
                    if (i11 == this.sampleSize) {
                        if (this.timeUs != -9223372036854775807L) {
                            z = true;
                        } else {
                            z = false;
                        }
                        Assertions.checkState(z);
                        TrackOutput trackOutput = this.output;
                        long j2 = this.timeUs;
                        if (this.frameType == 4) {
                            i2 = 0;
                        } else {
                            i2 = 1;
                        }
                        trackOutput.sampleMetadata(j2, i2, this.sampleSize, 0, (TrackOutput.CryptoData) null);
                        this.timeUs += this.sampleDurationUs;
                        this.state = 0;
                        break;
                    } else {
                        break;
                    }
                default:
                    throw new IllegalStateException();
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
        this.syncBytes = 0;
        this.timeUs = -9223372036854775807L;
        this.uhdAudioChunkId.set(0);
    }

    public void packetFinished(boolean z) {
    }
}
