package androidx.media3.extractor.ts;

import F2.U;
import F2.y0;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.MpeghUtil;
import androidx.media3.extractor.ts.TsPayloadReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MpeghReader implements ElementaryStreamReader {
    private boolean configFound;
    private final String containerMimeType;
    private boolean dataPending;
    private final ParsableByteArray dataScratchBytes = new ParsableByteArray();
    private int flags;
    private String formatId;
    private int frameBytes;
    private MpeghUtil.MhasPacketHeader header = new MpeghUtil.MhasPacketHeader();
    private boolean headerDataFinished = true;
    private final ParsableBitArray headerScratchBits = new ParsableBitArray();
    private final ParsableByteArray headerScratchBytes = new ParsableByteArray(new byte[15], 2);
    private long mainStreamLabel = -1;
    private TrackOutput output;
    private int payloadBytesRead;
    private boolean rapPending = true;
    private int samplingRate = -2147483647;
    private int standardFrameLength = -1;
    private int state = 0;
    private int syncBytes;
    private double timeUs = -9.223372036854776E18d;
    private double timeUsPending = -9.223372036854776E18d;
    private int truncationSamples;

    public MpeghReader(String str) {
        this.containerMimeType = str;
    }

    private void copyData(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
        int position = parsableByteArray.getPosition();
        int min = Math.min(parsableByteArray.bytesLeft(), parsableByteArray2.bytesLeft());
        parsableByteArray.readBytes(parsableByteArray2.getData(), parsableByteArray2.getPosition(), min);
        parsableByteArray2.skipBytes(min);
        if (z) {
            parsableByteArray.setPosition(position);
        }
    }

    private void finalizeFrame() {
        int i2;
        if (this.configFound) {
            this.rapPending = false;
            i2 = 1;
        } else {
            i2 = 0;
        }
        double d = (((double) (this.standardFrameLength - this.truncationSamples)) * 1000000.0d) / ((double) this.samplingRate);
        long round = Math.round(this.timeUs);
        if (this.dataPending) {
            this.dataPending = false;
            this.timeUs = this.timeUsPending;
        } else {
            this.timeUs += d;
        }
        this.output.sampleMetadata(round, i2, this.frameBytes, 0, (TrackOutput.CryptoData) null);
        this.configFound = false;
        this.truncationSamples = 0;
        this.frameBytes = 0;
    }

    private void parseConfig(ParsableBitArray parsableBitArray) {
        y0 y0Var;
        MpeghUtil.Mpegh3daConfig parseMpegh3daConfig = MpeghUtil.parseMpegh3daConfig(parsableBitArray);
        this.samplingRate = parseMpegh3daConfig.samplingFrequency;
        this.standardFrameLength = parseMpegh3daConfig.standardFrameLength;
        long j2 = this.mainStreamLabel;
        long j3 = this.header.packetLabel;
        if (j2 != j3) {
            this.mainStreamLabel = j3;
            int i2 = parseMpegh3daConfig.profileLevelIndication;
            String str = "mhm1";
            if (i2 != -1) {
                str = str.concat(String.format(".%02X", new Object[]{Integer.valueOf(i2)}));
            }
            byte[] bArr = parseMpegh3daConfig.compatibleProfileLevelSet;
            if (bArr == null || bArr.length <= 0) {
                y0Var = null;
            } else {
                y0Var = U.D(Util.EMPTY_BYTE_ARRAY, bArr);
            }
            this.output.format(new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType("audio/mhm1").setSampleRate(this.samplingRate).setCodecs(str).setInitializationData(y0Var).build());
        }
        this.configFound = true;
    }

    private boolean parseHeader() {
        int limit = this.headerScratchBytes.limit();
        this.headerScratchBits.reset(this.headerScratchBytes.getData(), limit);
        boolean parseMhasPacketHeader = MpeghUtil.parseMhasPacketHeader(this.headerScratchBits, this.header);
        if (parseMhasPacketHeader) {
            this.payloadBytesRead = 0;
            this.frameBytes = this.header.packetLength + limit + this.frameBytes;
        }
        return parseMhasPacketHeader;
    }

    private boolean shouldParsePacket(int i2) {
        if (i2 == 1 || i2 == 17) {
            return true;
        }
        return false;
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        int i2 = this.flags;
        if ((i2 & 2) == 0) {
            parsableByteArray.setPosition(parsableByteArray.limit());
            return false;
        } else if ((i2 & 4) != 0) {
            return true;
        } else {
            while (parsableByteArray.bytesLeft() > 0) {
                int i7 = this.syncBytes << 8;
                this.syncBytes = i7;
                int readUnsignedByte = i7 | parsableByteArray.readUnsignedByte();
                this.syncBytes = readUnsignedByte;
                if (MpeghUtil.isSyncWord(readUnsignedByte)) {
                    parsableByteArray.setPosition(parsableByteArray.getPosition() - 3);
                    this.syncBytes = 0;
                    return true;
                }
            }
            return false;
        }
    }

    private void writeSampleData(ParsableByteArray parsableByteArray) {
        int min = Math.min(parsableByteArray.bytesLeft(), this.header.packetLength - this.payloadBytesRead);
        this.output.sampleData(parsableByteArray, min);
        this.payloadBytesRead += min;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i2 = this.state;
            if (i2 != 0) {
                if (i2 == 1) {
                    copyData(parsableByteArray, this.headerScratchBytes, false);
                    if (this.headerScratchBytes.bytesLeft() != 0) {
                        this.headerDataFinished = false;
                    } else if (parseHeader()) {
                        this.headerScratchBytes.setPosition(0);
                        TrackOutput trackOutput = this.output;
                        ParsableByteArray parsableByteArray2 = this.headerScratchBytes;
                        trackOutput.sampleData(parsableByteArray2, parsableByteArray2.limit());
                        this.headerScratchBytes.reset(2);
                        this.dataScratchBytes.reset(this.header.packetLength);
                        this.headerDataFinished = true;
                        this.state = 2;
                    } else if (this.headerScratchBytes.limit() < 15) {
                        ParsableByteArray parsableByteArray3 = this.headerScratchBytes;
                        parsableByteArray3.setLimit(parsableByteArray3.limit() + 1);
                        this.headerDataFinished = false;
                    }
                } else if (i2 == 2) {
                    if (shouldParsePacket(this.header.packetType)) {
                        copyData(parsableByteArray, this.dataScratchBytes, true);
                    }
                    writeSampleData(parsableByteArray);
                    int i7 = this.payloadBytesRead;
                    MpeghUtil.MhasPacketHeader mhasPacketHeader = this.header;
                    if (i7 == mhasPacketHeader.packetLength) {
                        int i8 = mhasPacketHeader.packetType;
                        if (i8 == 1) {
                            parseConfig(new ParsableBitArray(this.dataScratchBytes.getData()));
                        } else if (i8 == 17) {
                            this.truncationSamples = MpeghUtil.parseAudioTruncationInfo(new ParsableBitArray(this.dataScratchBytes.getData()));
                        } else if (i8 == 2) {
                            finalizeFrame();
                        }
                        this.state = 1;
                    }
                } else {
                    throw new IllegalStateException();
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    public void packetStarted(long j2, int i2) {
        this.flags = i2;
        if (!this.rapPending && (this.frameBytes != 0 || !this.headerDataFinished)) {
            this.dataPending = true;
        }
        if (j2 == -9223372036854775807L) {
            return;
        }
        if (this.dataPending) {
            this.timeUsPending = (double) j2;
        } else {
            this.timeUs = (double) j2;
        }
    }

    public void seek() {
        this.state = 0;
        this.syncBytes = 0;
        this.headerScratchBytes.reset(2);
        this.payloadBytesRead = 0;
        this.frameBytes = 0;
        this.samplingRate = -2147483647;
        this.standardFrameLength = -1;
        this.truncationSamples = 0;
        this.mainStreamLabel = -1;
        this.configFound = false;
        this.dataPending = false;
        this.headerDataFinished = true;
        this.rapPending = true;
        this.timeUs = -9.223372036854776E18d;
        this.timeUsPending = -9.223372036854776E18d;
    }

    public void packetFinished(boolean z) {
    }
}
