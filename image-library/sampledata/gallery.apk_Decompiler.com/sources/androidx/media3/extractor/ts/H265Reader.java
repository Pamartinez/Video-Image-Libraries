package androidx.media3.extractor.ts;

import He.F;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H265Reader implements ElementaryStreamReader {
    private final String containerMimeType;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs = -9223372036854775807L;
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(34, 128);
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer prefixSei = new NalUnitTargetBuffer(39, 128);
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(33, 128);
    private final NalUnitTargetBuffer suffixSei = new NalUnitTargetBuffer(40, 128);
    private long totalBytesWritten;
    private final NalUnitTargetBuffer vps = new NalUnitTargetBuffer(32, 128);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SampleReader {
        private boolean isFirstPrefixNalUnit;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingPrefix;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        private static boolean isPrefixNalUnit(int i2) {
            if ((32 > i2 || i2 > 35) && i2 != 39) {
                return false;
            }
            return true;
        }

        private static boolean isVclBodyNalUnit(int i2) {
            if (i2 < 32 || i2 == 40) {
                return true;
            }
            return false;
        }

        private void outputSample(int i2) {
            long j2 = this.sampleTimeUs;
            if (j2 != -9223372036854775807L) {
                long j3 = this.nalUnitPosition;
                long j8 = this.samplePosition;
                if (j3 != j8) {
                    long j10 = j3;
                    boolean z = this.sampleIsKeyframe;
                    this.output.sampleMetadata(j2, z ? 1 : 0, (int) (j10 - j8), i2, (TrackOutput.CryptoData) null);
                }
            }
        }

        public void endNalUnit(long j2, int i2, boolean z) {
            if (this.readingPrefix && this.isFirstSlice) {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingPrefix = false;
            } else if (this.isFirstPrefixNalUnit || this.isFirstSlice) {
                if (z && this.readingSample) {
                    outputSample(i2 + ((int) (j2 - this.nalUnitPosition)));
                }
                this.samplePosition = this.nalUnitPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingSample = true;
            }
        }

        public void readNalUnitData(byte[] bArr, int i2, int i7) {
            boolean z;
            if (this.lookingForFirstSliceFlag) {
                int i8 = this.nalUnitBytesRead;
                int i10 = (i2 + 2) - i8;
                if (i10 < i7) {
                    if ((bArr[i10] & 128) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.isFirstSlice = z;
                    this.lookingForFirstSliceFlag = false;
                    return;
                }
                this.nalUnitBytesRead = (i7 - i2) + i8;
            }
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.readingSample = false;
            this.readingPrefix = false;
        }

        public void startNalUnit(long j2, int i2, int i7, long j3, boolean z) {
            boolean z3;
            boolean z7 = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.nalUnitTimeUs = j3;
            this.nalUnitBytesRead = 0;
            this.nalUnitPosition = j2;
            if (!isVclBodyNalUnit(i7)) {
                if (this.readingSample && !this.readingPrefix) {
                    if (z) {
                        outputSample(i2);
                    }
                    this.readingSample = false;
                }
                if (isPrefixNalUnit(i7)) {
                    this.isFirstPrefixNalUnit = !this.readingPrefix;
                    this.readingPrefix = true;
                }
            }
            if (i7 < 16 || i7 > 21) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.nalUnitHasKeyframeData = z3;
            if (z3 || i7 <= 9) {
                z7 = true;
            }
            this.lookingForFirstSliceFlag = z7;
        }
    }

    public H265Reader(SeiReader seiReader2, String str) {
        this.seiReader = seiReader2;
        this.containerMimeType = str;
    }

    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    private void endNalUnit(long j2, int i2, int i7, long j3) {
        boolean z;
        this.sampleReader.endNalUnit(j2, i2, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.endNalUnit(i7);
            this.sps.endNalUnit(i7);
            this.pps.endNalUnit(i7);
            if (this.vps.isCompleted() && this.sps.isCompleted() && this.pps.isCompleted()) {
                Format parseMediaFormat = parseMediaFormat(this.formatId, this.vps, this.sps, this.pps, this.containerMimeType);
                this.output.format(parseMediaFormat);
                if (parseMediaFormat.maxNumReorderSamples != -1) {
                    z = true;
                } else {
                    z = false;
                }
                F.r(z);
                this.seiReader.setReorderingQueueSize(parseMediaFormat.maxNumReorderSamples);
                this.hasOutputFormat = true;
            }
        }
        if (this.prefixSei.endNalUnit(i7)) {
            NalUnitTargetBuffer nalUnitTargetBuffer = this.prefixSei;
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j3, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(i7)) {
            NalUnitTargetBuffer nalUnitTargetBuffer2 = this.suffixSei;
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j3, this.seiWrapper);
        }
    }

    private void nalUnitData(byte[] bArr, int i2, int i7) {
        this.sampleReader.readNalUnitData(bArr, i2, i7);
        if (!this.hasOutputFormat) {
            this.vps.appendToNalUnit(bArr, i2, i7);
            this.sps.appendToNalUnit(bArr, i2, i7);
            this.pps.appendToNalUnit(bArr, i2, i7);
        }
        this.prefixSei.appendToNalUnit(bArr, i2, i7);
        this.suffixSei.appendToNalUnit(bArr, i2, i7);
    }

    private static Format parseMediaFormat(String str, NalUnitTargetBuffer nalUnitTargetBuffer, NalUnitTargetBuffer nalUnitTargetBuffer2, NalUnitTargetBuffer nalUnitTargetBuffer3, String str2) {
        int i2 = nalUnitTargetBuffer.nalLength;
        byte[] bArr = new byte[(nalUnitTargetBuffer2.nalLength + i2 + nalUnitTargetBuffer3.nalLength)];
        System.arraycopy(nalUnitTargetBuffer.nalData, 0, bArr, 0, i2);
        System.arraycopy(nalUnitTargetBuffer2.nalData, 0, bArr, nalUnitTargetBuffer.nalLength, nalUnitTargetBuffer2.nalLength);
        System.arraycopy(nalUnitTargetBuffer3.nalData, 0, bArr, nalUnitTargetBuffer.nalLength + nalUnitTargetBuffer2.nalLength, nalUnitTargetBuffer3.nalLength);
        String str3 = null;
        NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(nalUnitTargetBuffer2.nalData, 3, nalUnitTargetBuffer2.nalLength, (NalUnitUtil.H265VpsData) null);
        NalUnitUtil.H265ProfileTierLevel h265ProfileTierLevel = parseH265SpsNalUnit.profileTierLevel;
        if (h265ProfileTierLevel != null) {
            str3 = CodecSpecificDataUtil.buildHevcCodecString(h265ProfileTierLevel.generalProfileSpace, h265ProfileTierLevel.generalTierFlag, h265ProfileTierLevel.generalProfileIdc, h265ProfileTierLevel.generalProfileCompatibilityFlags, h265ProfileTierLevel.constraintBytes, h265ProfileTierLevel.generalLevelIdc);
        }
        return new Format.Builder().setId(str).setContainerMimeType(str2).setSampleMimeType("video/hevc").setCodecs(str3).setWidth(parseH265SpsNalUnit.width).setHeight(parseH265SpsNalUnit.height).setDecodedWidth(parseH265SpsNalUnit.decodedWidth).setDecodedHeight(parseH265SpsNalUnit.decodedHeight).setColorInfo(new ColorInfo.Builder().setColorSpace(parseH265SpsNalUnit.colorSpace).setColorRange(parseH265SpsNalUnit.colorRange).setColorTransfer(parseH265SpsNalUnit.colorTransfer).setLumaBitdepth(parseH265SpsNalUnit.bitDepthLumaMinus8 + 8).setChromaBitdepth(parseH265SpsNalUnit.bitDepthChromaMinus8 + 8).build()).setPixelWidthHeightRatio(parseH265SpsNalUnit.pixelWidthHeightRatio).setMaxNumReorderSamples(parseH265SpsNalUnit.maxNumReorderPics).setMaxSubLayers(parseH265SpsNalUnit.maxSubLayersMinus1 + 1).setInitializationData(Collections.singletonList(bArr)).build();
    }

    private void startNalUnit(long j2, int i2, int i7, long j3) {
        int i8 = i7;
        this.sampleReader.startNalUnit(j2, i2, i8, j3, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.startNalUnit(i8);
            this.sps.startNalUnit(i8);
            this.pps.startNalUnit(i8);
        }
        this.prefixSei.startNalUnit(i8);
        this.suffixSei.startNalUnit(i8);
    }

    public void consume(ParsableByteArray parsableByteArray) {
        int i2;
        int i7;
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int limit = parsableByteArray.limit();
            byte[] data = parsableByteArray.getData();
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            while (true) {
                if (position < limit) {
                    int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
                    if (findNalUnit == limit) {
                        nalUnitData(data, position, limit);
                        return;
                    }
                    int h265NalUnitType = NalUnitUtil.getH265NalUnitType(data, findNalUnit);
                    if (findNalUnit <= 0 || data[findNalUnit - 1] != 0) {
                        i2 = 3;
                    } else {
                        findNalUnit--;
                        i2 = 4;
                    }
                    int i8 = findNalUnit;
                    int i10 = i2;
                    int i11 = i8 - position;
                    if (i11 > 0) {
                        nalUnitData(data, position, i8);
                    }
                    int i12 = limit - i8;
                    long j2 = this.totalBytesWritten - ((long) i12);
                    if (i11 < 0) {
                        i7 = -i11;
                    } else {
                        i7 = 0;
                    }
                    long j3 = j2;
                    int i13 = i7;
                    long j8 = j3;
                    endNalUnit(j8, i12, i13, this.pesTimeUs);
                    startNalUnit(j8, i12, h265NalUnitType, this.pesTimeUs);
                    position = i8 + i10;
                }
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    public void packetFinished(boolean z) {
        assertTracksCreated();
        if (z) {
            this.seiReader.flush();
            endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
            startNalUnit(this.totalBytesWritten, 0, 48, this.pesTimeUs);
        }
    }

    public void packetStarted(long j2, int i2) {
        this.pesTimeUs = j2;
    }

    public void seek() {
        this.totalBytesWritten = 0;
        this.pesTimeUs = -9223372036854775807L;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.seiReader.clear();
        SampleReader sampleReader2 = this.sampleReader;
        if (sampleReader2 != null) {
            sampleReader2.reset();
        }
    }
}
