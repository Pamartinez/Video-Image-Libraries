package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AdtsReader implements ElementaryStreamReader {
    private static final byte[] ID3_IDENTIFIER = {73, 68, 51};
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private final String containerMimeType;
    private int currentFrameVersion;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private final boolean exposeId3;
    private int firstFrameSampleRateIndex;
    private int firstFrameVersion;
    private String formatId;
    private boolean foundFirstFrame;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    private final String language;
    private int matchState;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public AdtsReader(boolean z, String str) {
        this(z, (String) null, 0, str);
    }

    private void assertTracksCreated() {
        Assertions.checkNotNull(this.output);
        Util.castNonNull(this.currentOutput);
        Util.castNonNull(this.id3Output);
    }

    private void checkAdtsHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() != 0) {
            this.adtsScratch.data[0] = parsableByteArray.getData()[parsableByteArray.getPosition()];
            this.adtsScratch.setPosition(2);
            int readBits = this.adtsScratch.readBits(4);
            int i2 = this.firstFrameSampleRateIndex;
            if (i2 == -1 || readBits == i2) {
                if (!this.foundFirstFrame) {
                    this.foundFirstFrame = true;
                    this.firstFrameVersion = this.currentFrameVersion;
                    this.firstFrameSampleRateIndex = readBits;
                }
                setReadingAdtsHeaderState();
                return;
            }
            resetSync();
        }
    }

    private boolean checkSyncPositionValid(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.setPosition(i2 + 1);
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
            return false;
        }
        this.adtsScratch.setPosition(4);
        int readBits = this.adtsScratch.readBits(1);
        int i7 = this.firstFrameVersion;
        if (i7 != -1 && readBits != i7) {
            return false;
        }
        if (this.firstFrameSampleRateIndex != -1) {
            if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
                return true;
            }
            this.adtsScratch.setPosition(2);
            if (this.adtsScratch.readBits(4) != this.firstFrameSampleRateIndex) {
                return false;
            }
            parsableByteArray.setPosition(i2 + 2);
        }
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 4)) {
            return true;
        }
        this.adtsScratch.setPosition(14);
        int readBits2 = this.adtsScratch.readBits(13);
        if (readBits2 < 7) {
            return false;
        }
        byte[] data = parsableByteArray.getData();
        int limit = parsableByteArray.limit();
        int i8 = i2 + readBits2;
        if (i8 >= limit) {
            return true;
        }
        byte b = data[i8];
        if (b == -1) {
            int i10 = i8 + 1;
            if (i10 == limit) {
                return true;
            }
            if (!isAdtsSyncBytes((byte) -1, data[i10]) || ((data[i10] & 8) >> 3) != readBits) {
                return false;
            }
            return true;
        } else if (b != 73) {
            return false;
        } else {
            int i11 = i8 + 1;
            if (i11 == limit) {
                return true;
            }
            if (data[i11] != 68) {
                return false;
            }
            int i12 = i8 + 2;
            if (i12 == limit || data[i12] == 51) {
                return true;
            }
            return false;
        }
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

    private void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit) {
            int i2 = position + 1;
            byte b = data[position];
            byte b5 = b & 255;
            if (this.matchState != 512 || !isAdtsSyncBytes((byte) -1, (byte) b5) || (!this.foundFirstFrame && !checkSyncPositionValid(parsableByteArray, position - 1))) {
                int i7 = this.matchState;
                byte b8 = b5 | i7;
                if (b8 == 329) {
                    this.matchState = 768;
                } else if (b8 == 511) {
                    this.matchState = 512;
                } else if (b8 == 836) {
                    this.matchState = 1024;
                } else if (b8 == 1075) {
                    setReadingId3HeaderState();
                    parsableByteArray.setPosition(i2);
                    return;
                } else if (i7 != 256) {
                    this.matchState = 256;
                }
                position = i2;
            } else {
                this.currentFrameVersion = (b & 8) >> 3;
                boolean z = true;
                if ((b & 1) != 0) {
                    z = false;
                }
                this.hasCrc = z;
                if (!this.foundFirstFrame) {
                    setCheckingAdtsHeaderState();
                } else {
                    setReadingAdtsHeaderState();
                }
                parsableByteArray.setPosition(i2);
                return;
            }
        }
        parsableByteArray.setPosition(position);
    }

    private boolean isAdtsSyncBytes(byte b, byte b5) {
        return isAdtsSyncWord(((b & 255) << 8) | (b5 & 255));
    }

    public static boolean isAdtsSyncWord(int i2) {
        if ((i2 & 65526) == 65520) {
            return true;
        }
        return false;
    }

    private void parseAdtsHeader() {
        this.adtsScratch.setPosition(0);
        if (!this.hasOutputFormat) {
            int i2 = 2;
            int readBits = this.adtsScratch.readBits(2) + 1;
            if (readBits != 2) {
                Log.w("AdtsReader", "Detected audio object type: " + readBits + ", but assuming AAC LC.");
            } else {
                i2 = readBits;
            }
            this.adtsScratch.skipBits(5);
            byte[] buildAudioSpecificConfig = AacUtil.buildAudioSpecificConfig(i2, this.firstFrameSampleRateIndex, this.adtsScratch.readBits(3));
            AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(buildAudioSpecificConfig);
            Format build = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(Encode.CodecsMime.AUDIO_CODEC_AAC).setCodecs(parseAudioSpecificConfig.codecs).setChannelCount(parseAudioSpecificConfig.channelCount).setSampleRate(parseAudioSpecificConfig.sampleRateHz).setInitializationData(Collections.singletonList(buildAudioSpecificConfig)).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
            this.sampleDurationUs = 1024000000 / ((long) build.sampleRate);
            this.output.format(build);
            this.hasOutputFormat = true;
        } else {
            this.adtsScratch.skipBits(10);
        }
        this.adtsScratch.skipBits(4);
        int readBits2 = this.adtsScratch.readBits(13);
        int i7 = readBits2 - 7;
        if (this.hasCrc) {
            i7 = readBits2 - 9;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, i7);
    }

    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    private void readSample(ParsableByteArray parsableByteArray) {
        boolean z;
        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, min);
        int i2 = this.bytesRead + min;
        this.bytesRead = i2;
        if (i2 == this.sampleSize) {
            if (this.timeUs != -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            this.currentOutput.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, (TrackOutput.CryptoData) null);
            this.timeUs += this.currentSampleDuration;
            setFindingSampleState();
        }
    }

    private void resetSync() {
        this.foundFirstFrame = false;
        setFindingSampleState();
    }

    private void setCheckingAdtsHeaderState() {
        this.state = 1;
        this.bytesRead = 0;
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 3;
        this.bytesRead = 0;
    }

    private void setReadingId3HeaderState() {
        this.state = 2;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput trackOutput, long j2, int i2, int i7) {
        this.state = 4;
        this.bytesRead = i2;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j2;
        this.sampleSize = i7;
    }

    private boolean tryRead(ParsableByteArray parsableByteArray, byte[] bArr, int i2) {
        if (parsableByteArray.bytesLeft() < i2) {
            return false;
        }
        parsableByteArray.readBytes(bArr, 0, i2);
        return true;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        int i2;
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int i7 = this.state;
            if (i7 == 0) {
                findNextSample(parsableByteArray);
            } else if (i7 == 1) {
                checkAdtsHeader(parsableByteArray);
            } else if (i7 != 2) {
                if (i7 == 3) {
                    if (this.hasCrc) {
                        i2 = 7;
                    } else {
                        i2 = 5;
                    }
                    if (continueRead(parsableByteArray, this.adtsScratch.data, i2)) {
                        parseAdtsHeader();
                    }
                } else if (i7 == 4) {
                    readSample(parsableByteArray);
                } else {
                    throw new IllegalStateException();
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.getData(), 10)) {
                parseId3Header();
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.output = track;
        this.currentOutput = track;
        if (this.exposeId3) {
            trackIdGenerator.generateNewId();
            TrackOutput track2 = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
            this.id3Output = track2;
            track2.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType("application/id3").build());
            return;
        }
        this.id3Output = new DiscardingTrackOutput();
    }

    public long getSampleDurationUs() {
        return this.sampleDurationUs;
    }

    public void packetStarted(long j2, int i2) {
        this.timeUs = j2;
    }

    public void seek() {
        this.timeUs = -9223372036854775807L;
        resetSync();
    }

    public AdtsReader(boolean z, String str, int i2, String str2) {
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        this.firstFrameVersion = -1;
        this.firstFrameSampleRateIndex = -1;
        this.sampleDurationUs = -9223372036854775807L;
        this.timeUs = -9223372036854775807L;
        this.exposeId3 = z;
        this.language = str;
        this.roleFlags = i2;
        this.containerMimeType = str2;
        setFindingSampleState();
    }

    public void packetFinished(boolean z) {
    }
}
