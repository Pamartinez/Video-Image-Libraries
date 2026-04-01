package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LatmReader implements ElementaryStreamReader {
    private int audioMuxVersionA;
    private int bytesRead;
    private int channelCount;
    private String codecs;
    private final String containerMimeType;
    private Format format;
    private String formatId;
    private int frameLengthType;
    private final String language;
    private int numSubframes;
    private long otherDataLenBits;
    private boolean otherDataPresent;
    private TrackOutput output;
    private final int roleFlags;
    private final ParsableBitArray sampleBitArray;
    private final ParsableByteArray sampleDataBuffer;
    private long sampleDurationUs;
    private int sampleRateHz;
    private int sampleSize;
    private int secondHeaderByte;
    private int state;
    private boolean streamMuxRead;
    private long timeUs = -9223372036854775807L;

    public LatmReader(String str, int i2, String str2) {
        this.language = str;
        this.roleFlags = i2;
        this.containerMimeType = str2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(1024);
        this.sampleDataBuffer = parsableByteArray;
        this.sampleBitArray = new ParsableBitArray(parsableByteArray.getData());
    }

    private static long latmGetValue(ParsableBitArray parsableBitArray) {
        return (long) parsableBitArray.readBits((parsableBitArray.readBits(2) + 1) * 8);
    }

    private void parseAudioMuxElement(ParsableBitArray parsableBitArray) {
        if (!parsableBitArray.readBit()) {
            this.streamMuxRead = true;
            parseStreamMuxConfig(parsableBitArray);
        } else if (!this.streamMuxRead) {
            return;
        }
        if (this.audioMuxVersionA != 0) {
            throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
        } else if (this.numSubframes == 0) {
            parsePayloadMux(parsableBitArray, parsePayloadLengthInfo(parsableBitArray));
            if (this.otherDataPresent) {
                parsableBitArray.skipBits((int) this.otherDataLenBits);
            }
        } else {
            throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
        }
    }

    private int parseAudioSpecificConfig(ParsableBitArray parsableBitArray) {
        int bitsLeft = parsableBitArray.bitsLeft();
        AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(parsableBitArray, true);
        this.codecs = parseAudioSpecificConfig.codecs;
        this.sampleRateHz = parseAudioSpecificConfig.sampleRateHz;
        this.channelCount = parseAudioSpecificConfig.channelCount;
        return bitsLeft - parsableBitArray.bitsLeft();
    }

    private void parseFrameLength(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(3);
        this.frameLengthType = readBits;
        if (readBits == 0) {
            parsableBitArray.skipBits(8);
        } else if (readBits == 1) {
            parsableBitArray.skipBits(9);
        } else if (readBits == 3 || readBits == 4 || readBits == 5) {
            parsableBitArray.skipBits(6);
        } else if (readBits == 6 || readBits == 7) {
            parsableBitArray.skipBits(1);
        } else {
            throw new IllegalStateException();
        }
    }

    private int parsePayloadLengthInfo(ParsableBitArray parsableBitArray) {
        int readBits;
        if (this.frameLengthType == 0) {
            int i2 = 0;
            do {
                readBits = parsableBitArray.readBits(8);
                i2 += readBits;
            } while (readBits == 255);
            return i2;
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }

    private void parsePayloadMux(ParsableBitArray parsableBitArray, int i2) {
        int position = parsableBitArray.getPosition();
        boolean z = false;
        if ((position & 7) == 0) {
            this.sampleDataBuffer.setPosition(position >> 3);
        } else {
            parsableBitArray.readBits(this.sampleDataBuffer.getData(), 0, i2 * 8);
            this.sampleDataBuffer.setPosition(0);
        }
        this.output.sampleData(this.sampleDataBuffer, i2);
        if (this.timeUs != -9223372036854775807L) {
            z = true;
        }
        Assertions.checkState(z);
        this.output.sampleMetadata(this.timeUs, 1, i2, 0, (TrackOutput.CryptoData) null);
        this.timeUs += this.sampleDurationUs;
    }

    private void parseStreamMuxConfig(ParsableBitArray parsableBitArray) {
        int i2;
        boolean readBit;
        int readBits = parsableBitArray.readBits(1);
        if (readBits == 1) {
            i2 = parsableBitArray.readBits(1);
        } else {
            i2 = 0;
        }
        this.audioMuxVersionA = i2;
        if (i2 == 0) {
            if (readBits == 1) {
                latmGetValue(parsableBitArray);
            }
            if (parsableBitArray.readBit()) {
                this.numSubframes = parsableBitArray.readBits(6);
                int readBits2 = parsableBitArray.readBits(4);
                int readBits3 = parsableBitArray.readBits(3);
                if (readBits2 == 0 && readBits3 == 0) {
                    if (readBits == 0) {
                        int position = parsableBitArray.getPosition();
                        int parseAudioSpecificConfig = parseAudioSpecificConfig(parsableBitArray);
                        parsableBitArray.setPosition(position);
                        byte[] bArr = new byte[((parseAudioSpecificConfig + 7) / 8)];
                        parsableBitArray.readBits(bArr, 0, parseAudioSpecificConfig);
                        Format build = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(Encode.CodecsMime.AUDIO_CODEC_AAC).setCodecs(this.codecs).setChannelCount(this.channelCount).setSampleRate(this.sampleRateHz).setInitializationData(Collections.singletonList(bArr)).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
                        if (!build.equals(this.format)) {
                            this.format = build;
                            this.sampleDurationUs = 1024000000 / ((long) build.sampleRate);
                            this.output.format(build);
                        }
                    } else {
                        parsableBitArray.skipBits(((int) latmGetValue(parsableBitArray)) - parseAudioSpecificConfig(parsableBitArray));
                    }
                    parseFrameLength(parsableBitArray);
                    boolean readBit2 = parsableBitArray.readBit();
                    this.otherDataPresent = readBit2;
                    this.otherDataLenBits = 0;
                    if (readBit2) {
                        if (readBits == 1) {
                            this.otherDataLenBits = latmGetValue(parsableBitArray);
                        } else {
                            do {
                                readBit = parsableBitArray.readBit();
                                this.otherDataLenBits = (this.otherDataLenBits << 8) + ((long) parsableBitArray.readBits(8));
                            } while (readBit);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(8);
                        return;
                    }
                    return;
                }
                throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
            }
            throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }

    private void resetBufferForSize(int i2) {
        this.sampleDataBuffer.reset(i2);
        this.sampleBitArray.reset(this.sampleDataBuffer.getData());
    }

    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int i2 = this.state;
            if (i2 != 0) {
                if (i2 == 1) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    if ((readUnsignedByte & 224) == 224) {
                        this.secondHeaderByte = readUnsignedByte;
                        this.state = 2;
                    } else if (readUnsignedByte != 86) {
                        this.state = 0;
                    }
                } else if (i2 == 2) {
                    int readUnsignedByte2 = ((this.secondHeaderByte & -225) << 8) | parsableByteArray.readUnsignedByte();
                    this.sampleSize = readUnsignedByte2;
                    if (readUnsignedByte2 > this.sampleDataBuffer.getData().length) {
                        resetBufferForSize(this.sampleSize);
                    }
                    this.bytesRead = 0;
                    this.state = 3;
                } else if (i2 == 3) {
                    int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    parsableByteArray.readBytes(this.sampleBitArray.data, this.bytesRead, min);
                    int i7 = this.bytesRead + min;
                    this.bytesRead = i7;
                    if (i7 == this.sampleSize) {
                        this.sampleBitArray.setPosition(0);
                        parseAudioMuxElement(this.sampleBitArray);
                        this.state = 0;
                    }
                } else {
                    throw new IllegalStateException();
                }
            } else if (parsableByteArray.readUnsignedByte() == 86) {
                this.state = 1;
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.formatId = trackIdGenerator.getFormatId();
    }

    public void packetStarted(long j2, int i2) {
        this.timeUs = j2;
    }

    public void seek() {
        this.state = 0;
        this.timeUs = -9223372036854775807L;
        this.streamMuxRead = false;
    }

    public void packetFinished(boolean z) {
    }
}
