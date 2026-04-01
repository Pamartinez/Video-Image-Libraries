package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H263Reader implements ElementaryStreamReader {
    private static final float[] PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};
    private final String containerMimeType;
    private final CsdBuffer csdBuffer = new CsdBuffer(128);
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs = -9223372036854775807L;
    private final boolean[] prefixFlags = new boolean[4];
    private SampleReader sampleReader;
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
        private int state;
        public int volStartPosition;

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
            int i8 = this.state;
            if (i8 != 0) {
                if (i8 != 1) {
                    if (i8 != 2) {
                        if (i8 != 3) {
                            if (i8 != 4) {
                                throw new IllegalStateException();
                            } else if (i2 == 179 || i2 == 181) {
                                this.length -= i7;
                                this.isFilling = false;
                                return true;
                            }
                        } else if ((i2 & 240) != 32) {
                            Log.w("H263Reader", "Unexpected start code value");
                            reset();
                        } else {
                            this.volStartPosition = this.length;
                            this.state = 4;
                        }
                    } else if (i2 > 31) {
                        Log.w("H263Reader", "Unexpected start code value");
                        reset();
                    } else {
                        this.state = 3;
                    }
                } else if (i2 != 181) {
                    Log.w("H263Reader", "Unexpected start code value");
                    reset();
                } else {
                    this.state = 2;
                }
            } else if (i2 == 176) {
                this.state = 1;
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.state = 0;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SampleReader {
        private boolean lookingForVopCodingType;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private int startCodeValue;
        private int vopBytesRead;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void onData(byte[] bArr, int i2, int i7) {
            boolean z;
            if (this.lookingForVopCodingType) {
                int i8 = this.vopBytesRead;
                int i10 = (i2 + 1) - i8;
                if (i10 < i7) {
                    if (((bArr[i10] & 192) >> 6) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.sampleIsKeyframe = z;
                    this.lookingForVopCodingType = false;
                    return;
                }
                this.vopBytesRead = (i7 - i2) + i8;
            }
        }

        public void onDataEnd(long j2, int i2, boolean z) {
            boolean z3;
            if (this.sampleTimeUs != -9223372036854775807L) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkState(z3);
            if (this.startCodeValue == 182 && z && this.readingSample) {
                boolean z7 = this.sampleIsKeyframe;
                this.output.sampleMetadata(this.sampleTimeUs, z7 ? 1 : 0, (int) (j2 - this.samplePosition), i2, (TrackOutput.CryptoData) null);
            }
            if (this.startCodeValue != 179) {
                this.samplePosition = j2;
            }
        }

        public void onStartCode(int i2, long j2) {
            boolean z;
            this.startCodeValue = i2;
            this.sampleIsKeyframe = false;
            boolean z3 = true;
            if (i2 == 182 || i2 == 179) {
                z = true;
            } else {
                z = false;
            }
            this.readingSample = z;
            if (i2 != 182) {
                z3 = false;
            }
            this.lookingForVopCodingType = z3;
            this.vopBytesRead = 0;
            this.sampleTimeUs = j2;
        }

        public void reset() {
            this.readingSample = false;
            this.lookingForVopCodingType = false;
            this.sampleIsKeyframe = false;
            this.startCodeValue = -1;
        }
    }

    public H263Reader(UserDataReader userDataReader2, String str) {
        this.userDataReader = userDataReader2;
        this.containerMimeType = str;
        if (userDataReader2 != null) {
            this.userData = new NalUnitTargetBuffer(178, 128);
            this.userDataParsable = new ParsableByteArray();
            return;
        }
        this.userData = null;
        this.userDataParsable = null;
    }

    private static Format parseCsdBuffer(CsdBuffer csdBuffer2, int i2, String str, String str2) {
        byte[] copyOf = Arrays.copyOf(csdBuffer2.data, csdBuffer2.length);
        ParsableBitArray parsableBitArray = new ParsableBitArray(copyOf);
        parsableBitArray.skipBytes(i2);
        parsableBitArray.skipBytes(4);
        parsableBitArray.skipBit();
        parsableBitArray.skipBits(8);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(4);
            parsableBitArray.skipBits(3);
        }
        int readBits = parsableBitArray.readBits(4);
        float f = 1.0f;
        if (readBits == 15) {
            int readBits2 = parsableBitArray.readBits(8);
            int readBits3 = parsableBitArray.readBits(8);
            if (readBits3 == 0) {
                Log.w("H263Reader", "Invalid aspect ratio");
            } else {
                f = ((float) readBits2) / ((float) readBits3);
            }
        } else {
            float[] fArr = PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO;
            if (readBits < fArr.length) {
                f = fArr[readBits];
            } else {
                Log.w("H263Reader", "Invalid aspect ratio");
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(2);
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(3);
                parsableBitArray.skipBits(11);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
            }
        }
        if (parsableBitArray.readBits(2) != 0) {
            Log.w("H263Reader", "Unhandled video object layer shape");
        }
        parsableBitArray.skipBit();
        int readBits4 = parsableBitArray.readBits(16);
        parsableBitArray.skipBit();
        if (parsableBitArray.readBit()) {
            if (readBits4 == 0) {
                Log.w("H263Reader", "Invalid vop_increment_time_resolution");
            } else {
                int i7 = 0;
                for (int i8 = readBits4 - 1; i8 > 0; i8 >>= 1) {
                    i7++;
                }
                parsableBitArray.skipBits(i7);
            }
        }
        parsableBitArray.skipBit();
        int readBits5 = parsableBitArray.readBits(13);
        parsableBitArray.skipBit();
        int readBits6 = parsableBitArray.readBits(13);
        parsableBitArray.skipBit();
        parsableBitArray.skipBit();
        return new Format.Builder().setId(str).setContainerMimeType(str2).setSampleMimeType(Encode.ContentType.VIDEO_MP4V_ES).setWidth(readBits5).setHeight(readBits6).setPixelWidthHeightRatio(f).setInitializationData(Collections.singletonList(copyOf)).build();
    }

    public void consume(ParsableByteArray parsableByteArray) {
        int i2;
        Assertions.checkStateNotNull(this.sampleReader);
        Assertions.checkStateNotNull(this.output);
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                break;
            }
            int i7 = findNalUnit + 3;
            byte b = parsableByteArray.getData()[i7] & 255;
            int i8 = findNalUnit - position;
            int i10 = 0;
            if (!this.hasOutputFormat) {
                if (i8 > 0) {
                    this.csdBuffer.onData(data, position, findNalUnit);
                }
                if (i8 < 0) {
                    i2 = -i8;
                } else {
                    i2 = 0;
                }
                if (this.csdBuffer.onStartCode(b, i2)) {
                    TrackOutput trackOutput = this.output;
                    CsdBuffer csdBuffer2 = this.csdBuffer;
                    trackOutput.format(parseCsdBuffer(csdBuffer2, csdBuffer2.volStartPosition, (String) Assertions.checkNotNull(this.formatId), this.containerMimeType));
                    this.hasOutputFormat = true;
                }
            }
            this.sampleReader.onData(data, position, findNalUnit);
            NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
            if (nalUnitTargetBuffer != null) {
                if (i8 > 0) {
                    nalUnitTargetBuffer.appendToNalUnit(data, position, findNalUnit);
                } else {
                    i10 = -i8;
                }
                if (this.userData.endNalUnit(i10)) {
                    NalUnitTargetBuffer nalUnitTargetBuffer2 = this.userData;
                    ((ParsableByteArray) Util.castNonNull(this.userDataParsable)).reset(this.userData.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
                    ((UserDataReader) Util.castNonNull(this.userDataReader)).consume(this.pesTimeUs, this.userDataParsable);
                }
                if (b == 178 && parsableByteArray.getData()[findNalUnit + 2] == 1) {
                    this.userData.startNalUnit(b);
                }
            }
            int i11 = limit - findNalUnit;
            this.sampleReader.onDataEnd(this.totalBytesWritten - ((long) i11), i11, this.hasOutputFormat);
            this.sampleReader.onStartCode(b, this.pesTimeUs);
            position = i7;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(data, position, limit);
        }
        this.sampleReader.onData(data, position, limit);
        NalUnitTargetBuffer nalUnitTargetBuffer3 = this.userData;
        if (nalUnitTargetBuffer3 != null) {
            nalUnitTargetBuffer3.appendToNalUnit(data, position, limit);
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track);
        UserDataReader userDataReader2 = this.userDataReader;
        if (userDataReader2 != null) {
            userDataReader2.createTracks(extractorOutput, trackIdGenerator);
        }
    }

    public void packetFinished(boolean z) {
        Assertions.checkStateNotNull(this.sampleReader);
        if (z) {
            this.sampleReader.onDataEnd(this.totalBytesWritten, 0, this.hasOutputFormat);
            this.sampleReader.reset();
        }
    }

    public void packetStarted(long j2, int i2) {
        this.pesTimeUs = j2;
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        SampleReader sampleReader2 = this.sampleReader;
        if (sampleReader2 != null) {
            sampleReader2.reset();
        }
        NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
        if (nalUnitTargetBuffer != null) {
            nalUnitTargetBuffer.reset();
        }
        this.totalBytesWritten = 0;
        this.pesTimeUs = -9223372036854775807L;
    }
}
