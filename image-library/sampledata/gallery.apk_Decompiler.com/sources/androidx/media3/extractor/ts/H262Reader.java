package androidx.media3.extractor.ts;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private final String containerMimeType;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CsdBuffer {
        private static final byte[] START_CODE = {0, 0, 1};
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i2) {
            this.data = new byte[i2];
        }

        public void onData(byte[] bArr, int i2, int i7) {
            if (this.isFilling) {
                int i8 = i7 - i2;
                byte[] bArr2 = this.data;
                int length2 = bArr2.length;
                int i10 = this.length;
                if (length2 < i10 + i8) {
                    this.data = Arrays.copyOf(bArr2, (i10 + i8) * 2);
                }
                System.arraycopy(bArr, i2, this.data, this.length, i8);
                this.length += i8;
            }
        }

        public boolean onStartCode(int i2, int i7) {
            if (this.isFilling) {
                int i8 = this.length - i7;
                this.length = i8;
                if (this.sequenceExtensionPosition == 0 && i2 == 181) {
                    this.sequenceExtensionPosition = i8;
                } else {
                    this.isFilling = false;
                    return true;
                }
            } else if (i2 == 179) {
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }
    }

    public H262Reader(String str) {
        this((UserDataReader) null, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<androidx.media3.common.Format, java.lang.Long> parseCsdBuffer(androidx.media3.extractor.ts.H262Reader.CsdBuffer r8, java.lang.String r9, java.lang.String r10) {
        /*
            byte[] r0 = r8.data
            int r1 = r8.length
            byte[] r0 = java.util.Arrays.copyOf(r0, r1)
            r1 = 4
            byte r2 = r0[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r3 = 5
            byte r4 = r0[r3]
            r5 = r4 & 255(0xff, float:3.57E-43)
            r6 = 6
            byte r6 = r0[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r1
            int r5 = r5 >> r1
            r2 = r2 | r5
            r4 = r4 & 15
            int r4 = r4 << 8
            r4 = r4 | r6
            r5 = 7
            byte r6 = r0[r5]
            r6 = r6 & 240(0xf0, float:3.36E-43)
            int r6 = r6 >> r1
            r7 = 2
            if (r6 == r7) goto L_0x003e
            r7 = 3
            if (r6 == r7) goto L_0x0038
            if (r6 == r1) goto L_0x0030
            r1 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0044
        L_0x0030:
            int r1 = r4 * 121
            float r1 = (float) r1
            int r6 = r2 * 100
        L_0x0035:
            float r6 = (float) r6
            float r1 = r1 / r6
            goto L_0x0044
        L_0x0038:
            int r1 = r4 * 16
            float r1 = (float) r1
            int r6 = r2 * 9
            goto L_0x0035
        L_0x003e:
            int r1 = r4 * 4
            float r1 = (float) r1
            int r6 = r2 * 3
            goto L_0x0035
        L_0x0044:
            androidx.media3.common.Format$Builder r6 = new androidx.media3.common.Format$Builder
            r6.<init>()
            androidx.media3.common.Format$Builder r9 = r6.setId((java.lang.String) r9)
            androidx.media3.common.Format$Builder r9 = r9.setContainerMimeType(r10)
            java.lang.String r10 = "video/mpeg2"
            androidx.media3.common.Format$Builder r9 = r9.setSampleMimeType(r10)
            androidx.media3.common.Format$Builder r9 = r9.setWidth(r2)
            androidx.media3.common.Format$Builder r9 = r9.setHeight(r4)
            androidx.media3.common.Format$Builder r9 = r9.setPixelWidthHeightRatio(r1)
            java.util.List r10 = java.util.Collections.singletonList(r0)
            androidx.media3.common.Format$Builder r9 = r9.setInitializationData(r10)
            androidx.media3.common.Format r9 = r9.build()
            byte r10 = r0[r5]
            r10 = r10 & 15
            int r10 = r10 + -1
            if (r10 < 0) goto L_0x009d
            double[] r1 = FRAME_RATE_VALUES
            int r2 = r1.length
            if (r10 >= r2) goto L_0x009d
            r1 = r1[r10]
            int r8 = r8.sequenceExtensionPosition
            int r8 = r8 + 9
            byte r8 = r0[r8]
            r10 = r8 & 96
            int r10 = r10 >> r3
            r8 = r8 & 31
            if (r10 == r8) goto L_0x0095
            double r3 = (double) r10
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r3 = r3 + r5
            int r8 = r8 + 1
            double r5 = (double) r8
            double r3 = r3 / r5
            double r1 = r1 * r3
        L_0x0095:
            r3 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r3 = r3 / r1
            long r0 = (long) r3
            goto L_0x009f
        L_0x009d:
            r0 = 0
        L_0x009f:
            java.lang.Long r8 = java.lang.Long.valueOf(r0)
            android.util.Pair r8 = android.util.Pair.create(r9, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.H262Reader.parseCsdBuffer(androidx.media3.extractor.ts.H262Reader$CsdBuffer, java.lang.String, java.lang.String):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0144  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void consume(androidx.media3.common.util.ParsableByteArray r20) {
        /*
            r19 = this;
            r0 = r19
            androidx.media3.extractor.TrackOutput r1 = r0.output
            androidx.media3.common.util.Assertions.checkStateNotNull(r1)
            int r1 = r20.getPosition()
            int r2 = r20.limit()
            byte[] r3 = r20.getData()
            long r4 = r0.totalBytesWritten
            int r6 = r20.bytesLeft()
            long r6 = (long) r6
            long r4 = r4 + r6
            r0.totalBytesWritten = r4
            androidx.media3.extractor.TrackOutput r4 = r0.output
            int r5 = r20.bytesLeft()
            r6 = r20
            r4.sampleData(r6, r5)
        L_0x0028:
            boolean[] r4 = r0.prefixFlags
            int r4 = androidx.media3.container.NalUnitUtil.findNalUnit(r3, r1, r2, r4)
            if (r4 != r2) goto L_0x0041
            boolean r4 = r0.hasOutputFormat
            if (r4 != 0) goto L_0x0039
            androidx.media3.extractor.ts.H262Reader$CsdBuffer r4 = r0.csdBuffer
            r4.onData(r3, r1, r2)
        L_0x0039:
            androidx.media3.extractor.ts.NalUnitTargetBuffer r0 = r0.userData
            if (r0 == 0) goto L_0x0040
            r0.appendToNalUnit(r3, r1, r2)
        L_0x0040:
            return
        L_0x0041:
            byte[] r5 = r6.getData()
            int r7 = r4 + 3
            byte r5 = r5[r7]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r8 = r4 - r1
            boolean r9 = r0.hasOutputFormat
            r10 = 0
            r11 = 1
            if (r9 != 0) goto L_0x008c
            if (r8 <= 0) goto L_0x005a
            androidx.media3.extractor.ts.H262Reader$CsdBuffer r9 = r0.csdBuffer
            r9.onData(r3, r1, r4)
        L_0x005a:
            if (r8 >= 0) goto L_0x005e
            int r9 = -r8
            goto L_0x005f
        L_0x005e:
            r9 = r10
        L_0x005f:
            androidx.media3.extractor.ts.H262Reader$CsdBuffer r12 = r0.csdBuffer
            boolean r9 = r12.onStartCode(r5, r9)
            if (r9 == 0) goto L_0x008c
            androidx.media3.extractor.ts.H262Reader$CsdBuffer r9 = r0.csdBuffer
            java.lang.String r12 = r0.formatId
            java.lang.Object r12 = androidx.media3.common.util.Assertions.checkNotNull(r12)
            java.lang.String r12 = (java.lang.String) r12
            java.lang.String r13 = r0.containerMimeType
            android.util.Pair r9 = parseCsdBuffer(r9, r12, r13)
            androidx.media3.extractor.TrackOutput r12 = r0.output
            java.lang.Object r13 = r9.first
            androidx.media3.common.Format r13 = (androidx.media3.common.Format) r13
            r12.format(r13)
            java.lang.Object r9 = r9.second
            java.lang.Long r9 = (java.lang.Long) r9
            long r12 = r9.longValue()
            r0.frameDurationUs = r12
            r0.hasOutputFormat = r11
        L_0x008c:
            androidx.media3.extractor.ts.NalUnitTargetBuffer r9 = r0.userData
            if (r9 == 0) goto L_0x00db
            if (r8 <= 0) goto L_0x0097
            r9.appendToNalUnit(r3, r1, r4)
            r1 = r10
            goto L_0x0098
        L_0x0097:
            int r1 = -r8
        L_0x0098:
            androidx.media3.extractor.ts.NalUnitTargetBuffer r8 = r0.userData
            boolean r1 = r8.endNalUnit(r1)
            if (r1 == 0) goto L_0x00c8
            androidx.media3.extractor.ts.NalUnitTargetBuffer r1 = r0.userData
            byte[] r8 = r1.nalData
            int r1 = r1.nalLength
            int r1 = androidx.media3.container.NalUnitUtil.unescapeStream(r8, r1)
            androidx.media3.common.util.ParsableByteArray r8 = r0.userDataParsable
            java.lang.Object r8 = androidx.media3.common.util.Util.castNonNull(r8)
            androidx.media3.common.util.ParsableByteArray r8 = (androidx.media3.common.util.ParsableByteArray) r8
            androidx.media3.extractor.ts.NalUnitTargetBuffer r9 = r0.userData
            byte[] r9 = r9.nalData
            r8.reset(r9, r1)
            androidx.media3.extractor.ts.UserDataReader r1 = r0.userDataReader
            java.lang.Object r1 = androidx.media3.common.util.Util.castNonNull(r1)
            androidx.media3.extractor.ts.UserDataReader r1 = (androidx.media3.extractor.ts.UserDataReader) r1
            long r8 = r0.sampleTimeUs
            androidx.media3.common.util.ParsableByteArray r12 = r0.userDataParsable
            r1.consume(r8, r12)
        L_0x00c8:
            r1 = 178(0xb2, float:2.5E-43)
            if (r5 != r1) goto L_0x00db
            byte[] r1 = r6.getData()
            int r8 = r4 + 2
            byte r1 = r1[r8]
            if (r1 != r11) goto L_0x00db
            androidx.media3.extractor.ts.NalUnitTargetBuffer r1 = r0.userData
            r1.startNalUnit(r5)
        L_0x00db:
            if (r5 == 0) goto L_0x00ea
            r1 = 179(0xb3, float:2.51E-43)
            if (r5 != r1) goto L_0x00e2
            goto L_0x00ea
        L_0x00e2:
            r1 = 184(0xb8, float:2.58E-43)
            if (r5 != r1) goto L_0x00e8
            r0.sampleIsKeyframe = r11
        L_0x00e8:
            r4 = r2
            goto L_0x0147
        L_0x00ea:
            int r17 = r2 - r4
            boolean r1 = r0.sampleHasPicture
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r1 == 0) goto L_0x0114
            boolean r1 = r0.hasOutputFormat
            if (r1 == 0) goto L_0x0114
            long r13 = r0.sampleTimeUs
            int r1 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x0114
            boolean r15 = r0.sampleIsKeyframe
            long r11 = r0.totalBytesWritten
            r4 = r2
            long r1 = r0.samplePosition
            long r11 = r11 - r1
            int r1 = (int) r11
            int r16 = r1 - r17
            androidx.media3.extractor.TrackOutput r12 = r0.output
            r18 = 0
            r12.sampleMetadata(r13, r15, r16, r17, r18)
        L_0x0111:
            r2 = r17
            goto L_0x0116
        L_0x0114:
            r4 = r2
            goto L_0x0111
        L_0x0116:
            boolean r1 = r0.startedFirstSample
            if (r1 == 0) goto L_0x0121
            boolean r1 = r0.sampleHasPicture
            if (r1 == 0) goto L_0x011f
            goto L_0x0121
        L_0x011f:
            r1 = 1
            goto L_0x0142
        L_0x0121:
            long r11 = r0.totalBytesWritten
            long r1 = (long) r2
            long r11 = r11 - r1
            r0.samplePosition = r11
            long r1 = r0.pesTimeUs
            int r11 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r11 == 0) goto L_0x012e
            goto L_0x0139
        L_0x012e:
            long r1 = r0.sampleTimeUs
            int r11 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r11 == 0) goto L_0x0138
            long r11 = r0.frameDurationUs
            long r1 = r1 + r11
            goto L_0x0139
        L_0x0138:
            r1 = r8
        L_0x0139:
            r0.sampleTimeUs = r1
            r0.sampleIsKeyframe = r10
            r0.pesTimeUs = r8
            r1 = 1
            r0.startedFirstSample = r1
        L_0x0142:
            if (r5 != 0) goto L_0x0145
            r10 = r1
        L_0x0145:
            r0.sampleHasPicture = r10
        L_0x0147:
            r2 = r4
            r1 = r7
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.H262Reader.consume(androidx.media3.common.util.ParsableByteArray):void");
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        UserDataReader userDataReader2 = this.userDataReader;
        if (userDataReader2 != null) {
            userDataReader2.createTracks(extractorOutput, trackIdGenerator);
        }
    }

    public void packetFinished(boolean z) {
        Assertions.checkStateNotNull(this.output);
        if (z) {
            boolean z3 = this.sampleIsKeyframe;
            this.output.sampleMetadata(this.sampleTimeUs, z3 ? 1 : 0, (int) (this.totalBytesWritten - this.samplePosition), 0, (TrackOutput.CryptoData) null);
        }
    }

    public void packetStarted(long j2, int i2) {
        this.pesTimeUs = j2;
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
        if (nalUnitTargetBuffer != null) {
            nalUnitTargetBuffer.reset();
        }
        this.totalBytesWritten = 0;
        this.startedFirstSample = false;
        this.pesTimeUs = -9223372036854775807L;
        this.sampleTimeUs = -9223372036854775807L;
    }

    public H262Reader(UserDataReader userDataReader2, String str) {
        this.userDataReader = userDataReader2;
        this.containerMimeType = str;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        if (userDataReader2 != null) {
            this.userData = new NalUnitTargetBuffer(178, 128);
            this.userDataParsable = new ParsableByteArray();
        } else {
            this.userData = null;
            this.userDataParsable = null;
        }
        this.pesTimeUs = -9223372036854775807L;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
