package androidx.media3.extractor.wav;

import P.a;
import android.util.Pair;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.WavUtil;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(14);
    private long dataEndPosition = -1;
    private int dataStartPosition = -1;
    private ExtractorOutput extractorOutput;
    private OutputWriter outputWriter;
    private long rf64SampleDataSize = -1;
    private int state = 0;
    private TrackOutput trackOutput;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ImaAdPcmOutputWriter implements OutputWriter {
        private static final int[] INDEX_TABLE = {-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};
        private static final int[] STEP_TABLE = {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143, 157, 173, MOCRLang.GREEK, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};
        private final ParsableByteArray decodedData;
        private final ExtractorOutput extractorOutput;
        private final Format format;
        private final int framesPerBlock;
        private final byte[] inputData;
        private long outputFrameCount;
        private int pendingInputBytes;
        private int pendingOutputBytes;
        private long startTimeUs;
        private final int targetSampleSizeFrames;
        private final TrackOutput trackOutput;
        private final WavFormat wavFormat;

        public ImaAdPcmOutputWriter(ExtractorOutput extractorOutput2, TrackOutput trackOutput2, WavFormat wavFormat2) {
            this.extractorOutput = extractorOutput2;
            this.trackOutput = trackOutput2;
            this.wavFormat = wavFormat2;
            int max = Math.max(1, wavFormat2.frameRateHz / 10);
            this.targetSampleSizeFrames = max;
            ParsableByteArray parsableByteArray = new ParsableByteArray(wavFormat2.extraData);
            parsableByteArray.readLittleEndianUnsignedShort();
            int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
            this.framesPerBlock = readLittleEndianUnsignedShort;
            int i2 = wavFormat2.numChannels;
            int i7 = (((wavFormat2.blockSize - (i2 * 4)) * 8) / (wavFormat2.bitsPerSample * i2)) + 1;
            if (readLittleEndianUnsignedShort == i7) {
                int ceilDivide = Util.ceilDivide(max, readLittleEndianUnsignedShort);
                this.inputData = new byte[(wavFormat2.blockSize * ceilDivide)];
                this.decodedData = new ParsableByteArray(ceilDivide * numOutputFramesToBytes(readLittleEndianUnsignedShort, i2));
                int i8 = ((wavFormat2.frameRateHz * wavFormat2.blockSize) * 8) / readLittleEndianUnsignedShort;
                this.format = new Format.Builder().setSampleMimeType("audio/raw").setAverageBitrate(i8).setPeakBitrate(i8).setMaxInputSize(numOutputFramesToBytes(max, i2)).setChannelCount(wavFormat2.numChannels).setSampleRate(wavFormat2.frameRateHz).setPcmEncoding(2).build();
                return;
            }
            throw ParserException.createForMalformedContainer("Expected frames per block: " + i7 + "; got: " + readLittleEndianUnsignedShort, (Throwable) null);
        }

        private void decode(byte[] bArr, int i2, ParsableByteArray parsableByteArray) {
            for (int i7 = 0; i7 < i2; i7++) {
                for (int i8 = 0; i8 < this.wavFormat.numChannels; i8++) {
                    decodeBlockForChannel(bArr, i7, i8, parsableByteArray.getData());
                }
            }
            int numOutputFramesToBytes = numOutputFramesToBytes(this.framesPerBlock * i2);
            parsableByteArray.setPosition(0);
            parsableByteArray.setLimit(numOutputFramesToBytes);
        }

        private void decodeBlockForChannel(byte[] bArr, int i2, int i7, byte[] bArr2) {
            int i8;
            WavFormat wavFormat2 = this.wavFormat;
            int i10 = wavFormat2.blockSize;
            int i11 = wavFormat2.numChannels;
            int i12 = (i7 * 4) + (i2 * i10);
            int i13 = (i11 * 4) + i12;
            int i14 = (i10 / i11) - 4;
            int i15 = (short) (((bArr[i12 + 1] & 255) << 8) | (bArr[i12] & 255));
            int min = Math.min(bArr[i12 + 2] & 255, 88);
            int i16 = STEP_TABLE[min];
            int i17 = ((i2 * this.framesPerBlock * i11) + i7) * 2;
            bArr2[i17] = (byte) (i15 & ScoverState.TYPE_NFC_SMART_COVER);
            bArr2[i17 + 1] = (byte) (i15 >> 8);
            for (int i18 = 0; i18 < i14 * 2; i18++) {
                byte b = bArr[((i18 / 8) * i11 * 4) + i13 + ((i18 / 2) % 4)];
                byte b5 = b & 255;
                if (i18 % 2 == 0) {
                    i8 = b & 15;
                } else {
                    i8 = b5 >> 4;
                }
                int i19 = ((((i8 & 7) * 2) + 1) * i16) >> 3;
                if ((i8 & 8) != 0) {
                    i19 = -i19;
                }
                i15 = Util.constrainValue(i15 + i19, -32768, 32767);
                i17 += i11 * 2;
                bArr2[i17] = (byte) (i15 & ScoverState.TYPE_NFC_SMART_COVER);
                bArr2[i17 + 1] = (byte) (i15 >> 8);
                int i20 = min + INDEX_TABLE[i8];
                int[] iArr = STEP_TABLE;
                min = Util.constrainValue(i20, 0, iArr.length - 1);
                i16 = iArr[min];
            }
        }

        private int numOutputBytesToFrames(int i2) {
            return i2 / (this.wavFormat.numChannels * 2);
        }

        private static int numOutputFramesToBytes(int i2, int i7) {
            return i2 * 2 * i7;
        }

        private void writeSampleMetadata(int i2) {
            int numOutputFramesToBytes = numOutputFramesToBytes(i2);
            this.trackOutput.sampleMetadata(this.startTimeUs + Util.scaleLargeTimestamp(this.outputFrameCount, 1000000, (long) this.wavFormat.frameRateHz), 1, numOutputFramesToBytes, this.pendingOutputBytes - numOutputFramesToBytes, (TrackOutput.CryptoData) null);
            this.outputFrameCount += (long) i2;
            this.pendingOutputBytes -= numOutputFramesToBytes;
        }

        public void init(int i2, long j2) {
            WavSeekMap wavSeekMap = new WavSeekMap(this.wavFormat, this.framesPerBlock, (long) i2, j2);
            this.extractorOutput.seekMap(wavSeekMap);
            this.trackOutput.format(this.format);
            this.trackOutput.durationUs(wavSeekMap.getDurationUs());
        }

        public void reset(long j2) {
            this.pendingInputBytes = 0;
            this.startTimeUs = j2;
            this.pendingOutputBytes = 0;
            this.outputFrameCount = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x003e A[EDGE_INSN: B:22:0x003e->B:10:0x003e ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:5:0x0020  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean sampleData(androidx.media3.extractor.ExtractorInput r7, long r8) {
            /*
                r6 = this;
                int r0 = r6.targetSampleSizeFrames
                int r1 = r6.pendingOutputBytes
                int r1 = r6.numOutputBytesToFrames(r1)
                int r0 = r0 - r1
                int r1 = r6.framesPerBlock
                int r0 = androidx.media3.common.util.Util.ceilDivide(r0, r1)
                androidx.media3.extractor.wav.WavFormat r1 = r6.wavFormat
                int r1 = r1.blockSize
                int r0 = r0 * r1
                r1 = 0
                int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
                r2 = 1
                if (r1 != 0) goto L_0x001d
            L_0x001b:
                r1 = r2
                goto L_0x001e
            L_0x001d:
                r1 = 0
            L_0x001e:
                if (r1 != 0) goto L_0x003e
                int r3 = r6.pendingInputBytes
                if (r3 >= r0) goto L_0x003e
                int r3 = r0 - r3
                long r3 = (long) r3
                long r3 = java.lang.Math.min(r3, r8)
                int r3 = (int) r3
                byte[] r4 = r6.inputData
                int r5 = r6.pendingInputBytes
                int r3 = r7.read(r4, r5, r3)
                r4 = -1
                if (r3 != r4) goto L_0x0038
                goto L_0x001b
            L_0x0038:
                int r4 = r6.pendingInputBytes
                int r4 = r4 + r3
                r6.pendingInputBytes = r4
                goto L_0x001e
            L_0x003e:
                int r7 = r6.pendingInputBytes
                androidx.media3.extractor.wav.WavFormat r8 = r6.wavFormat
                int r8 = r8.blockSize
                int r7 = r7 / r8
                if (r7 <= 0) goto L_0x0075
                byte[] r8 = r6.inputData
                androidx.media3.common.util.ParsableByteArray r9 = r6.decodedData
                r6.decode(r8, r7, r9)
                int r8 = r6.pendingInputBytes
                androidx.media3.extractor.wav.WavFormat r9 = r6.wavFormat
                int r9 = r9.blockSize
                int r7 = r7 * r9
                int r8 = r8 - r7
                r6.pendingInputBytes = r8
                androidx.media3.common.util.ParsableByteArray r7 = r6.decodedData
                int r7 = r7.limit()
                androidx.media3.extractor.TrackOutput r8 = r6.trackOutput
                androidx.media3.common.util.ParsableByteArray r9 = r6.decodedData
                r8.sampleData(r9, r7)
                int r8 = r6.pendingOutputBytes
                int r8 = r8 + r7
                r6.pendingOutputBytes = r8
                int r7 = r6.numOutputBytesToFrames(r8)
                int r8 = r6.targetSampleSizeFrames
                if (r7 < r8) goto L_0x0075
                r6.writeSampleMetadata(r8)
            L_0x0075:
                if (r1 == 0) goto L_0x0082
                int r7 = r6.pendingOutputBytes
                int r7 = r6.numOutputBytesToFrames(r7)
                if (r7 <= 0) goto L_0x0082
                r6.writeSampleMetadata(r7)
            L_0x0082:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.wav.WavExtractor.ImaAdPcmOutputWriter.sampleData(androidx.media3.extractor.ExtractorInput, long):boolean");
        }

        private int numOutputFramesToBytes(int i2) {
            return numOutputFramesToBytes(i2, this.wavFormat.numChannels);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OutputWriter {
        void init(int i2, long j2);

        void reset(long j2);

        boolean sampleData(ExtractorInput extractorInput, long j2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PassthroughOutputWriter implements OutputWriter {
        private final ExtractorOutput extractorOutput;
        private final Format format;
        private long outputFrameCount;
        private int pendingOutputBytes;
        private long startTimeUs;
        private final int targetSampleSizeBytes;
        private final TrackOutput trackOutput;
        private final WavFormat wavFormat;

        public PassthroughOutputWriter(ExtractorOutput extractorOutput2, TrackOutput trackOutput2, WavFormat wavFormat2, String str, int i2) {
            this.extractorOutput = extractorOutput2;
            this.trackOutput = trackOutput2;
            this.wavFormat = wavFormat2;
            int i7 = (wavFormat2.numChannels * wavFormat2.bitsPerSample) / 8;
            if (wavFormat2.blockSize == i7) {
                int i8 = wavFormat2.frameRateHz;
                int i10 = i8 * i7 * 8;
                int max = Math.max(i7, (i8 * i7) / 10);
                this.targetSampleSizeBytes = max;
                this.format = new Format.Builder().setContainerMimeType("audio/wav").setSampleMimeType(str).setAverageBitrate(i10).setPeakBitrate(i10).setMaxInputSize(max).setChannelCount(wavFormat2.numChannels).setSampleRate(wavFormat2.frameRateHz).setPcmEncoding(i2).build();
                return;
            }
            StringBuilder o2 = C0086a.o(i7, "Expected block size: ", "; got: ");
            o2.append(wavFormat2.blockSize);
            throw ParserException.createForMalformedContainer(o2.toString(), (Throwable) null);
        }

        public void init(int i2, long j2) {
            WavSeekMap wavSeekMap = new WavSeekMap(this.wavFormat, 1, (long) i2, j2);
            this.extractorOutput.seekMap(wavSeekMap);
            this.trackOutput.format(this.format);
            this.trackOutput.durationUs(wavSeekMap.getDurationUs());
        }

        public void reset(long j2) {
            this.startTimeUs = j2;
            this.pendingOutputBytes = 0;
            this.outputFrameCount = 0;
        }

        public boolean sampleData(ExtractorInput extractorInput, long j2) {
            int i2;
            int i7;
            int i8;
            long j3 = j2;
            while (true) {
                i2 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
                if (i2 <= 0 || (i7 = this.pendingOutputBytes) >= (i8 = this.targetSampleSizeBytes)) {
                    WavFormat wavFormat2 = this.wavFormat;
                    int i10 = wavFormat2.blockSize;
                    int i11 = this.pendingOutputBytes / i10;
                } else {
                    int sampleData = this.trackOutput.sampleData((DataReader) extractorInput, (int) Math.min((long) (i8 - i7), j3), true);
                    if (sampleData == -1) {
                        j3 = 0;
                    } else {
                        this.pendingOutputBytes += sampleData;
                        j3 -= (long) sampleData;
                    }
                }
            }
            WavFormat wavFormat22 = this.wavFormat;
            int i102 = wavFormat22.blockSize;
            int i112 = this.pendingOutputBytes / i102;
            if (i112 > 0) {
                int i12 = i112 * i102;
                int i13 = this.pendingOutputBytes - i12;
                this.trackOutput.sampleMetadata(this.startTimeUs + Util.scaleLargeTimestamp(this.outputFrameCount, 1000000, (long) wavFormat22.frameRateHz), 1, i12, i13, (TrackOutput.CryptoData) null);
                this.outputFrameCount += (long) i112;
                this.pendingOutputBytes = i13;
            }
            if (i2 <= 0) {
                return true;
            }
            return false;
        }
    }

    private void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new WavExtractor()};
    }

    private void readFileType(ExtractorInput extractorInput) {
        boolean z;
        if (extractorInput.getPosition() == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        int i2 = this.dataStartPosition;
        if (i2 != -1) {
            extractorInput.skipFully(i2);
            this.state = 4;
        } else if (WavHeaderReader.checkFileType(extractorInput)) {
            extractorInput.skipFully((int) (extractorInput.getPeekPosition() - extractorInput.getPosition()));
            this.state = 1;
        } else {
            throw ParserException.createForMalformedContainer("Unsupported or unrecognized wav file type.", (Throwable) null);
        }
    }

    private void readFormat(ExtractorInput extractorInput) {
        WavFormat readFormat = WavHeaderReader.readFormat(extractorInput);
        int i2 = readFormat.formatType;
        if (i2 == 17) {
            this.outputWriter = new ImaAdPcmOutputWriter(this.extractorOutput, this.trackOutput, readFormat);
        } else if (i2 == 6) {
            this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, readFormat, "audio/g711-alaw", -1);
        } else if (i2 == 7) {
            this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, readFormat, "audio/g711-mlaw", -1);
        } else {
            int pcmEncodingForType = WavUtil.getPcmEncodingForType(i2, readFormat.bitsPerSample);
            if (pcmEncodingForType != 0) {
                this.outputWriter = new PassthroughOutputWriter(this.extractorOutput, this.trackOutput, readFormat, "audio/raw", pcmEncodingForType);
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Unsupported WAV format type: " + readFormat.formatType);
            }
        }
        this.state = 3;
    }

    private void readRf64SampleDataSize(ExtractorInput extractorInput) {
        this.rf64SampleDataSize = WavHeaderReader.readRf64SampleDataSize(extractorInput);
        this.state = 2;
    }

    private int readSampleData(ExtractorInput extractorInput) {
        boolean z;
        if (this.dataEndPosition != -1) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        if (((OutputWriter) Assertions.checkNotNull(this.outputWriter)).sampleData(extractorInput, this.dataEndPosition - extractorInput.getPosition())) {
            return -1;
        }
        return 0;
    }

    private void skipToSampleData(ExtractorInput extractorInput) {
        Pair<Long, Long> skipToSampleData = WavHeaderReader.skipToSampleData(extractorInput);
        this.dataStartPosition = ((Long) skipToSampleData.first).intValue();
        long longValue = ((Long) skipToSampleData.second).longValue();
        long j2 = this.rf64SampleDataSize;
        if (j2 != -1 && longValue == 4294967295L) {
            longValue = j2;
        }
        this.dataEndPosition = ((long) this.dataStartPosition) + longValue;
        long length = extractorInput.getLength();
        if (length != -1 && this.dataEndPosition > length) {
            Log.w("WavExtractor", "Data exceeds input length: " + this.dataEndPosition + ArcCommonLog.TAG_COMMA + length);
            this.dataEndPosition = length;
        }
        ((OutputWriter) Assertions.checkNotNull(this.outputWriter)).init(this.dataStartPosition, this.dataEndPosition);
        this.state = 4;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = extractorOutput2.track(0, 1);
        extractorOutput2.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        assertInitialized();
        int i2 = this.state;
        if (i2 == 0) {
            readFileType(extractorInput);
            return 0;
        } else if (i2 == 1) {
            readRf64SampleDataSize(extractorInput);
            return 0;
        } else if (i2 == 2) {
            readFormat(extractorInput);
            return 0;
        } else if (i2 == 3) {
            skipToSampleData(extractorInput);
            return 0;
        } else if (i2 == 4) {
            return readSampleData(extractorInput);
        } else {
            throw new IllegalStateException();
        }
    }

    public void seek(long j2, long j3) {
        int i2;
        if (j2 == 0) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        this.state = i2;
        OutputWriter outputWriter2 = this.outputWriter;
        if (outputWriter2 != null) {
            outputWriter2.reset(j3);
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        return WavHeaderReader.checkFileType(extractorInput);
    }

    public void release() {
    }
}
