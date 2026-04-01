package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ParsableNalUnitBitArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H264Reader implements ElementaryStreamReader {
    private final boolean allowNonIdrKeyframes;
    private final String containerMimeType;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs = -9223372036854775807L;
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    private final boolean[] prefixFlags = new boolean[3];
    private boolean randomAccessIndicator;
    private SampleReader sampleReader;
    private final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    private long totalBytesWritten;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SampleReader {
        private final boolean allowNonIdrKeyframes;
        private final ParsableNalUnitBitArray bitArray;
        private byte[] buffer;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private final SparseArray<NalUnitUtil.PpsData> pps = new SparseArray<>();
        private SliceHeaderData previousSliceHeader = new SliceHeaderData();
        private boolean randomAccessIndicator;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader = new SliceHeaderData();
        private final SparseArray<NalUnitUtil.SpsData> sps = new SparseArray<>();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SliceHeaderData {
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private NalUnitUtil.SpsData spsData;

            private SliceHeaderData() {
            }

            /* access modifiers changed from: private */
            public boolean isFirstVclNalUnitOfPicture(SliceHeaderData sliceHeaderData) {
                int i2;
                int i7;
                int i8;
                boolean z;
                if (!this.isComplete) {
                    return false;
                }
                if (!sliceHeaderData.isComplete) {
                    return true;
                }
                NalUnitUtil.SpsData spsData2 = (NalUnitUtil.SpsData) Assertions.checkStateNotNull(this.spsData);
                NalUnitUtil.SpsData spsData3 = (NalUnitUtil.SpsData) Assertions.checkStateNotNull(sliceHeaderData.spsData);
                if (this.frameNum == sliceHeaderData.frameNum && this.picParameterSetId == sliceHeaderData.picParameterSetId && this.fieldPicFlag == sliceHeaderData.fieldPicFlag && ((!this.bottomFieldFlagPresent || !sliceHeaderData.bottomFieldFlagPresent || this.bottomFieldFlag == sliceHeaderData.bottomFieldFlag) && (((i2 = this.nalRefIdc) == (i7 = sliceHeaderData.nalRefIdc) || (i2 != 0 && i7 != 0)) && (((i8 = spsData2.picOrderCountType) != 0 || spsData3.picOrderCountType != 0 || (this.picOrderCntLsb == sliceHeaderData.picOrderCntLsb && this.deltaPicOrderCntBottom == sliceHeaderData.deltaPicOrderCntBottom)) && ((i8 != 1 || spsData3.picOrderCountType != 1 || (this.deltaPicOrderCnt0 == sliceHeaderData.deltaPicOrderCnt0 && this.deltaPicOrderCnt1 == sliceHeaderData.deltaPicOrderCnt1)) && (z = this.idrPicFlag) == sliceHeaderData.idrPicFlag && (!z || this.idrPicId == sliceHeaderData.idrPicId)))))) {
                    return false;
                }
                return true;
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public boolean isISlice() {
                if (!this.hasSliceType) {
                    return false;
                }
                int i2 = this.sliceType;
                if (i2 == 7 || i2 == 2) {
                    return true;
                }
                return false;
            }

            public void setAll(NalUnitUtil.SpsData spsData2, int i2, int i7, int i8, int i10, boolean z, boolean z3, boolean z7, boolean z9, int i11, int i12, int i13, int i14, int i15) {
                this.spsData = spsData2;
                this.nalRefIdc = i2;
                this.sliceType = i7;
                this.frameNum = i8;
                this.picParameterSetId = i10;
                this.fieldPicFlag = z;
                this.bottomFieldFlagPresent = z3;
                this.bottomFieldFlag = z7;
                this.idrPicFlag = z9;
                this.idrPicId = i11;
                this.picOrderCntLsb = i12;
                this.deltaPicOrderCntBottom = i13;
                this.deltaPicOrderCnt0 = i14;
                this.deltaPicOrderCnt1 = i15;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public void setSliceType(int i2) {
                this.sliceType = i2;
                this.hasSliceType = true;
            }
        }

        public SampleReader(TrackOutput trackOutput, boolean z, boolean z3) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z;
            this.detectAccessUnits = z3;
            byte[] bArr = new byte[128];
            this.buffer = bArr;
            this.bitArray = new ParsableNalUnitBitArray(bArr, 0, 0);
            reset();
        }

        private void outputSample(int i2) {
            long j2 = this.sampleTimeUs;
            if (j2 != -9223372036854775807L) {
                long j3 = this.nalUnitStartPosition;
                long j8 = this.samplePosition;
                if (j3 != j8) {
                    long j10 = j3;
                    boolean z = this.sampleIsKeyframe;
                    this.output.sampleMetadata(j2, z ? 1 : 0, (int) (j10 - j8), i2, (TrackOutput.CryptoData) null);
                }
            }
        }

        private void setSampleIsKeyframe() {
            boolean z;
            if (this.allowNonIdrKeyframes) {
                z = this.sliceHeader.isISlice();
            } else {
                z = this.randomAccessIndicator;
            }
            boolean z3 = this.sampleIsKeyframe;
            int i2 = this.nalUnitType;
            boolean z7 = true;
            if (i2 != 5 && (!z || i2 != 1)) {
                z7 = false;
            }
            this.sampleIsKeyframe = z3 | z7;
        }

        /* JADX WARNING: Removed duplicated region for block: B:42:0x0109  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x010c  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0110  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0123  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0129  */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x0161  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void appendToNalUnit(byte[] r24, int r25, int r26) {
            /*
                r23 = this;
                r0 = r23
                r1 = r25
                boolean r2 = r0.isFilling
                if (r2 != 0) goto L_0x000a
                goto L_0x0184
            L_0x000a:
                int r2 = r26 - r1
                byte[] r3 = r0.buffer
                int r4 = r3.length
                int r5 = r0.bufferLength
                int r6 = r5 + r2
                r7 = 2
                if (r4 >= r6) goto L_0x001e
                int r5 = r5 + r2
                int r5 = r5 * r7
                byte[] r3 = java.util.Arrays.copyOf(r3, r5)
                r0.buffer = r3
            L_0x001e:
                byte[] r3 = r0.buffer
                int r4 = r0.bufferLength
                r5 = r24
                java.lang.System.arraycopy(r5, r1, r3, r4, r2)
                int r1 = r0.bufferLength
                int r1 = r1 + r2
                r0.bufferLength = r1
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                byte[] r3 = r0.buffer
                r4 = 0
                r2.reset(r3, r4, r1)
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                r2 = 8
                boolean r1 = r1.canReadBits(r2)
                if (r1 != 0) goto L_0x0040
                goto L_0x0184
            L_0x0040:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                r1.skipBit()
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                int r10 = r1.readBits(r7)
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                r2 = 5
                r1.skipBits(r2)
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x005b
                goto L_0x0184
            L_0x005b:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                r1.readUnsignedExpGolombCodedInt()
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x006a
                goto L_0x0184
            L_0x006a:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                int r11 = r1.readUnsignedExpGolombCodedInt()
                boolean r1 = r0.detectAccessUnits
                if (r1 != 0) goto L_0x007c
                r0.isFilling = r4
                androidx.media3.extractor.ts.H264Reader$SampleReader$SliceHeaderData r0 = r0.sliceHeader
                r0.setSliceType(r11)
                return
            L_0x007c:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x0086
                goto L_0x0184
            L_0x0086:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                int r13 = r1.readUnsignedExpGolombCodedInt()
                android.util.SparseArray<androidx.media3.container.NalUnitUtil$PpsData> r1 = r0.pps
                int r1 = r1.indexOfKey(r13)
                if (r1 >= 0) goto L_0x0097
                r0.isFilling = r4
                return
            L_0x0097:
                android.util.SparseArray<androidx.media3.container.NalUnitUtil$PpsData> r1 = r0.pps
                java.lang.Object r1 = r1.get(r13)
                androidx.media3.container.NalUnitUtil$PpsData r1 = (androidx.media3.container.NalUnitUtil.PpsData) r1
                android.util.SparseArray<androidx.media3.container.NalUnitUtil$SpsData> r3 = r0.sps
                int r5 = r1.seqParameterSetId
                java.lang.Object r3 = r3.get(r5)
                r9 = r3
                androidx.media3.container.NalUnitUtil$SpsData r9 = (androidx.media3.container.NalUnitUtil.SpsData) r9
                boolean r3 = r9.separateColorPlaneFlag
                if (r3 == 0) goto L_0x00bd
                androidx.media3.container.ParsableNalUnitBitArray r3 = r0.bitArray
                boolean r3 = r3.canReadBits(r7)
                if (r3 != 0) goto L_0x00b8
                goto L_0x0184
            L_0x00b8:
                androidx.media3.container.ParsableNalUnitBitArray r3 = r0.bitArray
                r3.skipBits(r7)
            L_0x00bd:
                androidx.media3.container.ParsableNalUnitBitArray r3 = r0.bitArray
                int r5 = r9.frameNumLength
                boolean r3 = r3.canReadBits(r5)
                if (r3 != 0) goto L_0x00c9
                goto L_0x0184
            L_0x00c9:
                androidx.media3.container.ParsableNalUnitBitArray r3 = r0.bitArray
                int r5 = r9.frameNumLength
                int r12 = r3.readBits(r5)
                boolean r3 = r9.frameMbsOnlyFlag
                r5 = 1
                if (r3 != 0) goto L_0x0102
                androidx.media3.container.ParsableNalUnitBitArray r3 = r0.bitArray
                boolean r3 = r3.canReadBits(r5)
                if (r3 != 0) goto L_0x00e0
                goto L_0x0184
            L_0x00e0:
                androidx.media3.container.ParsableNalUnitBitArray r3 = r0.bitArray
                boolean r3 = r3.readBit()
                if (r3 == 0) goto L_0x00fd
                androidx.media3.container.ParsableNalUnitBitArray r6 = r0.bitArray
                boolean r6 = r6.canReadBits(r5)
                if (r6 != 0) goto L_0x00f2
                goto L_0x0184
            L_0x00f2:
                androidx.media3.container.ParsableNalUnitBitArray r6 = r0.bitArray
                boolean r6 = r6.readBit()
                r14 = r3
                r15 = r5
                r16 = r6
                goto L_0x0105
            L_0x00fd:
                r14 = r3
                r15 = r4
            L_0x00ff:
                r16 = r15
                goto L_0x0105
            L_0x0102:
                r14 = r4
                r15 = r14
                goto L_0x00ff
            L_0x0105:
                int r3 = r0.nalUnitType
                if (r3 != r2) goto L_0x010c
                r17 = r5
                goto L_0x010e
            L_0x010c:
                r17 = r4
            L_0x010e:
                if (r17 == 0) goto L_0x0123
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                boolean r2 = r2.canReadExpGolombCodedNum()
                if (r2 != 0) goto L_0x011a
                goto L_0x0184
            L_0x011a:
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                int r2 = r2.readUnsignedExpGolombCodedInt()
                r18 = r2
                goto L_0x0125
            L_0x0123:
                r18 = r4
            L_0x0125:
                int r2 = r9.picOrderCountType
                if (r2 != 0) goto L_0x0161
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                int r3 = r9.picOrderCntLsbLength
                boolean r2 = r2.canReadBits(r3)
                if (r2 != 0) goto L_0x0134
                goto L_0x0184
            L_0x0134:
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                int r3 = r9.picOrderCntLsbLength
                int r2 = r2.readBits(r3)
                boolean r1 = r1.bottomFieldPicOrderInFramePresentFlag
                if (r1 == 0) goto L_0x015a
                if (r14 != 0) goto L_0x015a
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x014b
                goto L_0x0184
            L_0x014b:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                int r1 = r1.readSignedExpGolombCodedInt()
                r20 = r1
                r19 = r2
                r21 = r4
            L_0x0157:
                r22 = r21
                goto L_0x01a2
            L_0x015a:
                r19 = r2
                r20 = r4
            L_0x015e:
                r21 = r20
                goto L_0x0157
            L_0x0161:
                if (r2 != r5) goto L_0x019d
                boolean r2 = r9.deltaPicOrderAlwaysZeroFlag
                if (r2 != 0) goto L_0x019d
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                boolean r2 = r2.canReadExpGolombCodedNum()
                if (r2 != 0) goto L_0x0170
                goto L_0x0184
            L_0x0170:
                androidx.media3.container.ParsableNalUnitBitArray r2 = r0.bitArray
                int r2 = r2.readSignedExpGolombCodedInt()
                boolean r1 = r1.bottomFieldPicOrderInFramePresentFlag
                if (r1 == 0) goto L_0x0194
                if (r14 != 0) goto L_0x0194
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x0185
            L_0x0184:
                return
            L_0x0185:
                androidx.media3.container.ParsableNalUnitBitArray r1 = r0.bitArray
                int r1 = r1.readSignedExpGolombCodedInt()
                r22 = r1
                r21 = r2
                r19 = r4
                r20 = r19
                goto L_0x01a2
            L_0x0194:
                r21 = r2
                r19 = r4
                r20 = r19
                r22 = r20
                goto L_0x01a2
            L_0x019d:
                r19 = r4
                r20 = r19
                goto L_0x015e
            L_0x01a2:
                androidx.media3.extractor.ts.H264Reader$SampleReader$SliceHeaderData r8 = r0.sliceHeader
                r8.setAll(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                r0.isFilling = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.H264Reader.SampleReader.appendToNalUnit(byte[], int, int):void");
        }

        public boolean endNalUnit(long j2, int i2, boolean z) {
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (z && this.readingSample) {
                    outputSample(i2 + ((int) (j2 - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            setSampleIsKeyframe();
            this.nalUnitType = 24;
            return this.sampleIsKeyframe;
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putPps(NalUnitUtil.PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void putSps(NalUnitUtil.SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j2, int i2, long j3, boolean z) {
            this.nalUnitType = i2;
            this.nalUnitTimeUs = j3;
            this.nalUnitStartPosition = j2;
            this.randomAccessIndicator = z;
            if (!this.allowNonIdrKeyframes || i2 != 1) {
                if (!this.detectAccessUnits) {
                    return;
                }
                if (!(i2 == 5 || i2 == 1 || i2 == 2)) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            sliceHeaderData.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }
    }

    public H264Reader(SeiReader seiReader2, boolean z, boolean z3, String str) {
        this.seiReader = seiReader2;
        this.allowNonIdrKeyframes = z;
        this.detectAccessUnits = z3;
        this.containerMimeType = str;
    }

    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    private void endNalUnit(long j2, int i2, int i7, long j3) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.endNalUnit(i7);
            this.pps.endNalUnit(i7);
            if (!this.hasOutputFormat) {
                if (this.sps.isCompleted() && this.pps.isCompleted()) {
                    ArrayList arrayList = new ArrayList();
                    NalUnitTargetBuffer nalUnitTargetBuffer = this.sps;
                    arrayList.add(Arrays.copyOf(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
                    NalUnitTargetBuffer nalUnitTargetBuffer2 = this.pps;
                    arrayList.add(Arrays.copyOf(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
                    NalUnitTargetBuffer nalUnitTargetBuffer3 = this.sps;
                    NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer3.nalData, 3, nalUnitTargetBuffer3.nalLength);
                    NalUnitTargetBuffer nalUnitTargetBuffer4 = this.pps;
                    NalUnitUtil.PpsData parsePpsNalUnit = NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer4.nalData, 3, nalUnitTargetBuffer4.nalLength);
                    this.output.format(new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(Encode.CodecsMime.VIDEO_CODEC_H264).setCodecs(CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc)).setWidth(parseSpsNalUnit.width).setHeight(parseSpsNalUnit.height).setColorInfo(new ColorInfo.Builder().setColorSpace(parseSpsNalUnit.colorSpace).setColorRange(parseSpsNalUnit.colorRange).setColorTransfer(parseSpsNalUnit.colorTransfer).setLumaBitdepth(parseSpsNalUnit.bitDepthLumaMinus8 + 8).setChromaBitdepth(parseSpsNalUnit.bitDepthChromaMinus8 + 8).build()).setPixelWidthHeightRatio(parseSpsNalUnit.pixelWidthHeightRatio).setInitializationData(arrayList).setMaxNumReorderSamples(parseSpsNalUnit.maxNumReorderFrames).build());
                    this.hasOutputFormat = true;
                    this.seiReader.setReorderingQueueSize(parseSpsNalUnit.maxNumReorderFrames);
                    this.sampleReader.putSps(parseSpsNalUnit);
                    this.sampleReader.putPps(parsePpsNalUnit);
                    this.sps.reset();
                    this.pps.reset();
                }
            } else if (this.sps.isCompleted()) {
                NalUnitTargetBuffer nalUnitTargetBuffer5 = this.sps;
                NalUnitUtil.SpsData parseSpsNalUnit2 = NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer5.nalData, 3, nalUnitTargetBuffer5.nalLength);
                this.seiReader.setReorderingQueueSize(parseSpsNalUnit2.maxNumReorderFrames);
                this.sampleReader.putSps(parseSpsNalUnit2);
                this.sps.reset();
            } else if (this.pps.isCompleted()) {
                NalUnitTargetBuffer nalUnitTargetBuffer6 = this.pps;
                this.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer6.nalData, 3, nalUnitTargetBuffer6.nalLength));
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i7)) {
            NalUnitTargetBuffer nalUnitTargetBuffer7 = this.sei;
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer7.nalData, nalUnitTargetBuffer7.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j3, this.seiWrapper);
        }
        if (this.sampleReader.endNalUnit(j2, i2, this.hasOutputFormat)) {
            this.randomAccessIndicator = false;
        }
    }

    private void nalUnitData(byte[] bArr, int i2, int i7) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.appendToNalUnit(bArr, i2, i7);
            this.pps.appendToNalUnit(bArr, i2, i7);
        }
        this.sei.appendToNalUnit(bArr, i2, i7);
        this.sampleReader.appendToNalUnit(bArr, i2, i7);
    }

    private void startNalUnit(long j2, int i2, long j3) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.startNalUnit(i2);
            this.pps.startNalUnit(i2);
        }
        this.sei.startNalUnit(i2);
        this.sampleReader.startNalUnit(j2, i2, j3, this.randomAccessIndicator);
    }

    public void consume(ParsableByteArray parsableByteArray) {
        int i2;
        int i7;
        assertTracksCreated();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                nalUnitData(data, position, limit);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(data, findNalUnit);
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
            startNalUnit(j8, nalUnitType, this.pesTimeUs);
            position = i8 + i10;
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    public void packetFinished(boolean z) {
        assertTracksCreated();
        if (z) {
            this.seiReader.flush();
            endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
            startNalUnit(this.totalBytesWritten, 9, this.pesTimeUs);
            endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
        }
    }

    public void packetStarted(long j2, int i2) {
        boolean z;
        this.pesTimeUs = j2;
        boolean z3 = this.randomAccessIndicator;
        if ((i2 & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.randomAccessIndicator = z3 | z;
    }

    public void seek() {
        this.totalBytesWritten = 0;
        this.randomAccessIndicator = false;
        this.pesTimeUs = -9223372036854775807L;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        this.seiReader.clear();
        SampleReader sampleReader2 = this.sampleReader;
        if (sampleReader2 != null) {
            sampleReader2.reset();
        }
    }
}
