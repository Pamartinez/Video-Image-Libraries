package androidx.media3.extractor.mp4;

import A.a;
import E2.h;
import E2.i;
import F2.Q;
import F2.U;
import F2.y0;
import android.util.Pair;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.DolbyVisionConfig;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4AlternateGroupData;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.HevcConfig;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.sgpl.media.iso.IsoInterface;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BoxParser {
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BtrtData {
        /* access modifiers changed from: private */
        public final long avgBitrate;
        /* access modifiers changed from: private */
        public final long maxBitrate;

        public BtrtData(long j2, long j3) {
            this.avgBitrate = j2;
            this.maxBitrate = j3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j2;
            int i2;
            int i7 = this.index + 1;
            this.index = i7;
            if (i7 == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j2 = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j2 = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j2;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i8 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i8;
                if (i8 > 0) {
                    i2 = this.stsc.readUnsignedIntToInt() - 1;
                } else {
                    i2 = -1;
                }
                this.nextSamplesPerChunkChangeIndex = i2;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EsdsData {
        /* access modifiers changed from: private */
        public final long bitrate;
        /* access modifiers changed from: private */
        public final byte[] initializationData;
        /* access modifiers changed from: private */
        public final String mimeType;
        /* access modifiers changed from: private */
        public final long peakBitrate;

        public EsdsData(String str, byte[] bArr, long j2, long j3) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = j2;
            this.peakBitrate = j3;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EyesData {
        /* access modifiers changed from: private */
        public final StriData striData;

        public EyesData(StriData striData2) {
            this.striData = striData2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MdhdData {
        /* access modifiers changed from: private */
        public final String language;
        /* access modifiers changed from: private */
        public final long mediaDurationUs;
        /* access modifiers changed from: private */
        public final long timescale;

        public MdhdData(long j2, long j3, String str) {
            this.timescale = j2;
            this.mediaDurationUs = j3;
            this.language = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class StriData {
        /* access modifiers changed from: private */
        public final boolean eyeViewsReversed;
        /* access modifiers changed from: private */
        public final boolean hasLeftEyeView;
        /* access modifiers changed from: private */
        public final boolean hasRightEyeView;

        public StriData(boolean z, boolean z3, boolean z7) {
            this.hasLeftEyeView = z;
            this.hasRightEyeView = z3;
            this.eyeViewsReversed = z7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class StsdData {
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i2) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i2];
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Mp4Box.LeafBox leafBox, Format format) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if ("audio/raw".equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (readUnsignedIntToInt == 0 || readUnsignedIntToInt % pcmFrameSize != 0) {
                    Log.w("BoxParsers", "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + readUnsignedIntToInt);
                    readUnsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = readUnsignedIntToInt == 0 ? -1 : readUnsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i2 = this.fixedSampleSize;
            if (i2 == -1) {
                return this.data.readUnsignedIntToInt();
            }
            return i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(Mp4Box.LeafBox leafBox) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & ScoverState.TYPE_NFC_SMART_COVER;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        public int getFixedSampleSize() {
            return -1;
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i2 = this.fieldSize;
            if (i2 == 8) {
                return this.data.readUnsignedByte();
            }
            if (i2 == 16) {
                return this.data.readUnsignedShort();
            }
            int i7 = this.sampleIndex;
            this.sampleIndex = i7 + 1;
            if (i7 % 2 != 0) {
                return this.currentByte & 15;
            }
            int readUnsignedByte = this.data.readUnsignedByte();
            this.currentByte = readUnsignedByte;
            return (readUnsignedByte & 240) >> 4;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TkhdData {
        /* access modifiers changed from: private */
        public final int alternateGroup;
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int height;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;
        /* access modifiers changed from: private */
        public final int width;

        public TkhdData(int i2, long j2, int i7, int i8, int i10, int i11) {
            this.id = i2;
            this.duration = j2;
            this.alternateGroup = i7;
            this.rotationDegrees = i8;
            this.width = i10;
            this.height = i11;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VexuData {
        /* access modifiers changed from: private */
        public final EyesData eyesData;

        public VexuData(EyesData eyesData2) {
            this.eyesData = eyesData2;
        }

        public boolean hasBothEyeViews() {
            EyesData eyesData2 = this.eyesData;
            if (eyesData2 == null || !eyesData2.striData.hasLeftEyeView || !this.eyesData.striData.hasRightEyeView) {
                return false;
            }
            return true;
        }
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j2, long j3, long j8) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(4, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 4, 0, length);
        if (jArr[0] > j3 || j3 >= jArr[constrainValue] || jArr[constrainValue2] >= j8 || j8 > j2) {
            return false;
        }
        return true;
    }

    private static int findBoxPosition(ParsableByteArray parsableByteArray, int i2, int i7, int i8) {
        boolean z;
        boolean z3;
        int position = parsableByteArray.getPosition();
        if (position >= i7) {
            z = true;
        } else {
            z = false;
        }
        ExtractorUtil.checkContainerInput(z, (String) null);
        while (position - i7 < i8) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            if (readInt > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            ExtractorUtil.checkContainerInput(z3, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i2) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static String formatVobsubIdx(byte[] bArr, int i2, int i7) {
        boolean z;
        if (bArr.length == 64) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        ArrayList arrayList = new ArrayList(16);
        for (int i8 = 0; i8 < bArr.length - 3; i8 += 4) {
            arrayList.add(String.format("%06x", new Object[]{Integer.valueOf(vobsubYuvToRgb(C0246a.V(bArr[i8], bArr[i8 + 1], bArr[i8 + 2], bArr[i8 + 3])))}));
        }
        StringBuilder h5 = a.h(i2, i7, "size: ", "x", "\npalette: ");
        i iVar = new i(ArcCommonLog.TAG_COMMA);
        Iterator it = arrayList.iterator();
        StringBuilder sb2 = new StringBuilder();
        iVar.a(sb2, it);
        h5.append(sb2.toString());
        h5.append("\n");
        return h5.toString();
    }

    private static String getLanguageFromCode(int i2) {
        char[] cArr = {(char) (((i2 >> 10) & 31) + 96), (char) (((i2 >> 5) & 31) + 96), (char) ((i2 & 31) + 96)};
        for (int i7 = 0; i7 < 3; i7++) {
            char c5 = cArr[i7];
            if (c5 < 'a' || c5 > 'z') {
                return null;
            }
        }
        return new String(cArr);
    }

    private static int getTrackTypeForHdlr(int i2) {
        if (i2 == 1936684398) {
            return 1;
        }
        if (i2 == 1986618469) {
            return 2;
        }
        if (i2 == 1952807028 || i2 == 1935832172 || i2 == 1937072756 || i2 == 1668047728 || i2 == 1937072752) {
            return 3;
        }
        if (i2 == 1835365473) {
            return 5;
        }
        return -1;
    }

    public static void maybeSkipRemainingMetaBoxHeaderBytes(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            position += 4;
        }
        parsableByteArray.setPosition(position);
    }

    private static ColorInfo parseApvc(ParsableByteArray parsableByteArray) {
        int i2;
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int readBits = parsableBitArray.readBits(8);
        for (int i7 = 0; i7 < readBits; i7++) {
            parsableBitArray.skipBytes(1);
            int readBits2 = parsableBitArray.readBits(8);
            for (int i8 = 0; i8 < readBits2; i8++) {
                parsableBitArray.skipBits(6);
                boolean readBit = parsableBitArray.readBit();
                parsableBitArray.skipBit();
                parsableBitArray.skipBytes(11);
                parsableBitArray.skipBits(4);
                int readBits3 = parsableBitArray.readBits(4) + 8;
                builder.setLumaBitdepth(readBits3);
                builder.setChromaBitdepth(readBits3);
                parsableBitArray.skipBytes(1);
                if (readBit) {
                    int readBits4 = parsableBitArray.readBits(8);
                    int readBits5 = parsableBitArray.readBits(8);
                    parsableBitArray.skipBytes(1);
                    boolean readBit2 = parsableBitArray.readBit();
                    ColorInfo.Builder colorSpace = builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(readBits4));
                    if (readBit2) {
                        i2 = 1;
                    } else {
                        i2 = 2;
                    }
                    colorSpace.setColorRange(i2).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(readBits5));
                }
            }
        }
        return builder.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:135:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x04a4 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:263:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseAudioSampleEntry(androidx.media3.common.util.ParsableByteArray r26, int r27, int r28, int r29, int r30, java.lang.String r31, boolean r32, androidx.media3.common.DrmInitData r33, androidx.media3.extractor.mp4.BoxParser.StsdData r34, int r35) {
        /*
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r29
            r4 = r30
            r5 = r31
            r6 = r33
            r7 = r34
            int r8 = r2 + 16
            r0.setPosition(r8)
            r8 = 6
            r9 = 8
            if (r32 == 0) goto L_0x0022
            int r11 = r0.readUnsignedShort()
            r0.skipBytes(r8)
            goto L_0x0026
        L_0x0022:
            r0.skipBytes(r9)
            r11 = 0
        L_0x0026:
            r14 = 32
            r15 = 4
            r17 = 3
            r13 = 2
            r10 = 1
            r12 = 16
            if (r11 == 0) goto L_0x0097
            if (r11 != r10) goto L_0x0035
            goto L_0x0097
        L_0x0035:
            if (r11 != r13) goto L_0x050c
            r0.skipBytes(r12)
            double r19 = r0.readDouble()
            long r10 = java.lang.Math.round(r19)
            int r8 = (int) r10
            int r10 = r0.readUnsignedIntToInt()
            r0.skipBytes(r15)
            int r11 = r0.readUnsignedIntToInt()
            int r19 = r0.readUnsignedIntToInt()
            r20 = r19 & 1
            if (r20 == 0) goto L_0x0059
            r20 = 1
            goto L_0x005b
        L_0x0059:
            r20 = 0
        L_0x005b:
            r19 = r19 & 2
            if (r19 == 0) goto L_0x0062
            r19 = 1
            goto L_0x0064
        L_0x0062:
            r19 = 0
        L_0x0064:
            if (r20 != 0) goto L_0x008a
            if (r11 != r9) goto L_0x006b
            r11 = r17
            goto L_0x008f
        L_0x006b:
            if (r11 != r12) goto L_0x0074
            if (r19 == 0) goto L_0x0072
            r11 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x008f
        L_0x0072:
            r11 = r13
            goto L_0x008f
        L_0x0074:
            r12 = 24
            if (r11 != r12) goto L_0x0080
            if (r19 == 0) goto L_0x007d
            r11 = 1342177280(0x50000000, float:8.5899346E9)
            goto L_0x008f
        L_0x007d:
            r11 = 21
            goto L_0x008f
        L_0x0080:
            if (r11 != r14) goto L_0x008e
            if (r19 == 0) goto L_0x0087
            r11 = 1610612736(0x60000000, float:3.6893488E19)
            goto L_0x008f
        L_0x0087:
            r11 = 22
            goto L_0x008f
        L_0x008a:
            if (r11 != r14) goto L_0x008e
            r11 = r15
            goto L_0x008f
        L_0x008e:
            r11 = -1
        L_0x008f:
            r0.skipBytes(r9)
            r9 = r10
            r19 = r13
            r10 = 0
            goto L_0x00b7
        L_0x0097:
            int r9 = r0.readUnsignedShort()
            r0.skipBytes(r8)
            int r8 = r0.readUnsignedFixedPoint1616()
            int r10 = r0.getPosition()
            int r10 = r10 - r15
            r0.setPosition(r10)
            int r10 = r0.readInt()
            r19 = r13
            r13 = 1
            if (r11 != r13) goto L_0x00b6
            r0.skipBytes(r12)
        L_0x00b6:
            r11 = -1
        L_0x00b7:
            r12 = 1935767394(0x73617762, float:1.7863284E31)
            r13 = 1935764850(0x73616d72, float:1.7860208E31)
            r14 = 1767992678(0x69616d66, float:1.7032808E25)
            if (r1 != r14) goto L_0x00c5
            r8 = -1
            r9 = -1
            goto L_0x00d0
        L_0x00c5:
            if (r1 != r13) goto L_0x00cb
            r8 = 8000(0x1f40, float:1.121E-41)
        L_0x00c9:
            r9 = 1
            goto L_0x00d0
        L_0x00cb:
            if (r1 != r12) goto L_0x00d0
            r8 = 16000(0x3e80, float:2.2421E-41)
            goto L_0x00c9
        L_0x00d0:
            int r15 = r0.getPosition()
            r14 = 1701733217(0x656e6361, float:7.0359778E22)
            if (r1 != r14) goto L_0x0100
            android.util.Pair r14 = parseSampleEntryEncryptionData(r0, r2, r3)
            if (r14 == 0) goto L_0x00fd
            java.lang.Object r1 = r14.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r6 != 0) goto L_0x00eb
            r6 = 0
            goto L_0x00f5
        L_0x00eb:
            java.lang.Object r12 = r14.second
            androidx.media3.extractor.mp4.TrackEncryptionBox r12 = (androidx.media3.extractor.mp4.TrackEncryptionBox) r12
            java.lang.String r12 = r12.schemeType
            androidx.media3.common.DrmInitData r6 = r6.copyWithSchemeType(r12)
        L_0x00f5:
            androidx.media3.extractor.mp4.TrackEncryptionBox[] r12 = r7.trackEncryptionBoxes
            java.lang.Object r14 = r14.second
            androidx.media3.extractor.mp4.TrackEncryptionBox r14 = (androidx.media3.extractor.mp4.TrackEncryptionBox) r14
            r12[r35] = r14
        L_0x00fd:
            r0.setPosition(r15)
        L_0x0100:
            r12 = 1633889587(0x61632d33, float:2.6191674E20)
            java.lang.String r14 = "audio/mhm1"
            java.lang.String r24 = "audio/raw"
            if (r1 != r12) goto L_0x0112
            java.lang.String r12 = "audio/ac3"
        L_0x010b:
            r25 = r12
            r12 = r11
            r11 = r25
            goto L_0x01d9
        L_0x0112:
            r12 = 1700998451(0x65632d33, float:6.7050686E22)
            if (r1 != r12) goto L_0x011a
            java.lang.String r12 = "audio/eac3"
            goto L_0x010b
        L_0x011a:
            r12 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r1 != r12) goto L_0x0122
            java.lang.String r12 = "audio/ac4"
            goto L_0x010b
        L_0x0122:
            r12 = 1685353315(0x64747363, float:1.803728E22)
            if (r1 != r12) goto L_0x012a
            java.lang.String r12 = "audio/vnd.dts"
            goto L_0x010b
        L_0x012a:
            r12 = 1685353320(0x64747368, float:1.8037286E22)
            if (r1 == r12) goto L_0x01d5
            r12 = 1685353324(0x6474736c, float:1.803729E22)
            if (r1 != r12) goto L_0x0136
            goto L_0x01d5
        L_0x0136:
            r12 = 1685353317(0x64747365, float:1.8037282E22)
            if (r1 != r12) goto L_0x013e
            java.lang.String r12 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x010b
        L_0x013e:
            r12 = 1685353336(0x64747378, float:1.8037304E22)
            if (r1 != r12) goto L_0x0146
            java.lang.String r12 = "audio/vnd.dts.uhd;profile=p2"
            goto L_0x010b
        L_0x0146:
            if (r1 != r13) goto L_0x014b
            java.lang.String r12 = "audio/3gpp"
            goto L_0x010b
        L_0x014b:
            r12 = 1935767394(0x73617762, float:1.7863284E31)
            if (r1 != r12) goto L_0x0153
            java.lang.String r12 = "audio/amr-wb"
            goto L_0x010b
        L_0x0153:
            r12 = 1936684916(0x736f7774, float:1.89725E31)
            if (r1 != r12) goto L_0x015e
        L_0x0158:
            r12 = r19
        L_0x015a:
            r11 = r24
            goto L_0x01d9
        L_0x015e:
            r12 = 1953984371(0x74776f73, float:7.841539E31)
            if (r1 != r12) goto L_0x0169
            r11 = r24
            r12 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x01d9
        L_0x0169:
            r12 = 1819304813(0x6c70636d, float:1.1624469E27)
            if (r1 != r12) goto L_0x0174
            r12 = -1
            if (r11 != r12) goto L_0x0172
            goto L_0x0158
        L_0x0172:
            r12 = r11
            goto L_0x015a
        L_0x0174:
            r12 = 778924082(0x2e6d7032, float:5.398721E-11)
            if (r1 == r12) goto L_0x01d1
            r12 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r1 != r12) goto L_0x017f
            goto L_0x01d1
        L_0x017f:
            r12 = 1835557169(0x6d686131, float:4.4948762E27)
            if (r1 != r12) goto L_0x0187
            java.lang.String r12 = "audio/mha1"
            goto L_0x010b
        L_0x0187:
            r12 = 1835560241(0x6d686d31, float:4.495783E27)
            if (r1 != r12) goto L_0x018f
            r12 = r11
            r11 = r14
            goto L_0x01d9
        L_0x018f:
            r12 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r1 != r12) goto L_0x0198
            java.lang.String r12 = "audio/alac"
            goto L_0x010b
        L_0x0198:
            r12 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r1 != r12) goto L_0x01a1
            java.lang.String r12 = "audio/g711-alaw"
            goto L_0x010b
        L_0x01a1:
            r12 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r1 != r12) goto L_0x01aa
            java.lang.String r12 = "audio/g711-mlaw"
            goto L_0x010b
        L_0x01aa:
            r12 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r1 != r12) goto L_0x01b3
            java.lang.String r12 = "audio/opus"
            goto L_0x010b
        L_0x01b3:
            r12 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r1 != r12) goto L_0x01bc
            java.lang.String r12 = "audio/flac"
            goto L_0x010b
        L_0x01bc:
            r12 = 1835823201(0x6d6c7061, float:4.573395E27)
            if (r1 != r12) goto L_0x01c5
            java.lang.String r12 = "audio/true-hd"
            goto L_0x010b
        L_0x01c5:
            r12 = 1767992678(0x69616d66, float:1.7032808E25)
            if (r1 != r12) goto L_0x01ce
            java.lang.String r12 = "audio/iamf"
            goto L_0x010b
        L_0x01ce:
            r12 = r11
            r11 = 0
            goto L_0x01d9
        L_0x01d1:
            java.lang.String r12 = "audio/mpeg"
            goto L_0x010b
        L_0x01d5:
            java.lang.String r12 = "audio/vnd.dts.hd"
            goto L_0x010b
        L_0x01d9:
            r23 = r12
            r2 = 0
            r13 = 0
            r16 = 0
            r22 = 0
        L_0x01e1:
            int r12 = r15 - r28
            if (r12 >= r3) goto L_0x049e
            r0.setPosition(r15)
            int r12 = r0.readInt()
            if (r12 <= 0) goto L_0x01f2
            r3 = 1
        L_0x01ef:
            r33 = r13
            goto L_0x01f4
        L_0x01f2:
            r3 = 0
            goto L_0x01ef
        L_0x01f4:
            java.lang.String r13 = "childAtomSize must be positive"
            androidx.media3.extractor.ExtractorUtil.checkContainerInput(r3, r13)
            int r3 = r0.readInt()
            r13 = 1835557187(0x6d686143, float:4.4948815E27)
            if (r3 != r13) goto L_0x0264
            int r3 = r15 + 8
            r0.setPosition(r3)
            r13 = 1
            r0.skipBytes(r13)
            int r3 = r0.readUnsignedByte()
            r0.skipBytes(r13)
            boolean r13 = java.util.Objects.equals(r11, r14)
            if (r13 == 0) goto L_0x0228
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r13 = "mhm1.%02X"
            java.lang.String r3 = java.lang.String.format(r13, r3)
        L_0x0226:
            r13 = r3
            goto L_0x0237
        L_0x0228:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r13 = "mha1.%02X"
            java.lang.String r3 = java.lang.String.format(r13, r3)
            goto L_0x0226
        L_0x0237:
            int r3 = r0.readUnsignedShort()
            r33 = r13
            byte[] r13 = new byte[r3]
            r35 = r14
            r14 = 0
            r0.readBytes(r13, r14, r3)
            if (r2 != 0) goto L_0x024c
            F2.y0 r2 = F2.U.B(r13)
            goto L_0x0256
        L_0x024c:
            java.lang.Object r2 = r2.get(r14)
            byte[] r2 = (byte[]) r2
            F2.y0 r2 = F2.U.D(r13, r2)
        L_0x0256:
            r3 = r33
        L_0x0258:
            r13 = -1
            r14 = 32
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            r20 = 0
        L_0x0260:
            r21 = 1
            goto L_0x0496
        L_0x0264:
            r35 = r14
            r13 = 1835557200(0x6d686150, float:4.4948854E27)
            if (r3 != r13) goto L_0x028e
            int r3 = r15 + 8
            r0.setPosition(r3)
            int r3 = r0.readUnsignedByte()
            if (r3 <= 0) goto L_0x0256
            byte[] r13 = new byte[r3]
            r14 = 0
            r0.readBytes(r13, r14, r3)
            if (r2 != 0) goto L_0x0283
            F2.y0 r2 = F2.U.B(r13)
            goto L_0x0256
        L_0x0283:
            java.lang.Object r2 = r2.get(r14)
            byte[] r2 = (byte[]) r2
            F2.y0 r2 = F2.U.D(r2, r13)
            goto L_0x0256
        L_0x028e:
            r13 = 1702061171(0x65736473, float:7.183675E22)
            if (r3 == r13) goto L_0x029a
            if (r32 == 0) goto L_0x02a5
            r14 = 2002876005(0x77617665, float:4.5729223E33)
            if (r3 != r14) goto L_0x02a5
        L_0x029a:
            r14 = 32
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            r20 = 0
            r21 = 1
            goto L_0x0450
        L_0x02a5:
            r13 = 1651798644(0x62747274, float:1.1273132E21)
            if (r3 != r13) goto L_0x02af
            androidx.media3.extractor.mp4.BoxParser$BtrtData r22 = parseBtrtFromParent(r0, r15)
            goto L_0x0256
        L_0x02af:
            r13 = 1684103987(0x64616333, float:1.6630662E22)
            if (r3 != r13) goto L_0x02c9
            int r3 = r15 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r4)
            androidx.media3.common.Format r3 = androidx.media3.extractor.Ac3Util.parseAc3AnnexFFormat(r0, r3, r5, r6)
            r7.format = r3
        L_0x02c3:
            r14 = 32
            r21 = 1
            goto L_0x0447
        L_0x02c9:
            r13 = 1684366131(0x64656333, float:1.692581E22)
            if (r3 != r13) goto L_0x02de
            int r3 = r15 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r4)
            androidx.media3.common.Format r3 = androidx.media3.extractor.Ac3Util.parseEAc3AnnexFFormat(r0, r3, r5, r6)
            r7.format = r3
            goto L_0x02c3
        L_0x02de:
            r13 = 1684103988(0x64616334, float:1.6630663E22)
            if (r3 != r13) goto L_0x02f3
            int r3 = r15 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r4)
            androidx.media3.common.Format r3 = androidx.media3.extractor.Ac4Util.parseAc4AnnexEFormat(r0, r3, r5, r6)
            r7.format = r3
            goto L_0x02c3
        L_0x02f3:
            r13 = 1684892784(0x646d6c70, float:1.7518768E22)
            if (r3 != r13) goto L_0x0315
            if (r10 <= 0) goto L_0x0301
            r3 = r33
            r8 = r10
            r9 = r19
            goto L_0x0258
        L_0x0301:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid sample rate for Dolby TrueHD MLP stream: "
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            r14 = 0
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r14)
            throw r0
        L_0x0315:
            r14 = 0
            r13 = 1684305011(0x64647473, float:1.6856995E22)
            if (r3 == r13) goto L_0x0320
            r13 = 1969517683(0x75647473, float:2.8960097E32)
            if (r3 != r13) goto L_0x0326
        L_0x0320:
            r14 = 32
            r21 = 1
            goto L_0x0424
        L_0x0326:
            r13 = 1682927731(0x644f7073, float:1.5306315E22)
            if (r3 != r13) goto L_0x0344
            int r2 = r12 + -8
            byte[] r3 = opusMagic
            int r13 = r3.length
            int r13 = r13 + r2
            byte[] r13 = java.util.Arrays.copyOf(r3, r13)
            int r14 = r15 + 8
            r0.setPosition(r14)
            int r3 = r3.length
            r0.readBytes(r13, r3, r2)
            java.util.List r2 = androidx.media3.extractor.OpusUtil.buildInitializationData(r13)
            goto L_0x0256
        L_0x0344:
            r13 = 1684425825(0x64664c61, float:1.6993019E22)
            if (r3 != r13) goto L_0x0372
            int r2 = r12 + -12
            int r3 = r12 + -8
            byte[] r3 = new byte[r3]
            r13 = 102(0x66, float:1.43E-43)
            r18 = 0
            r3[r18] = r13
            r13 = 76
            r21 = 1
            r3[r21] = r13
            r13 = 97
            r3[r19] = r13
            r13 = 67
            r3[r17] = r13
            int r13 = r15 + 12
            r0.setPosition(r13)
            r14 = 4
            r0.readBytes(r3, r14, r2)
            F2.y0 r2 = F2.U.B(r3)
            goto L_0x0256
        L_0x0372:
            r13 = 1634492771(0x616c6163, float:2.7252807E20)
            r14 = 4
            if (r3 != r13) goto L_0x03ac
            int r2 = r12 + -12
            byte[] r3 = new byte[r2]
            int r8 = r15 + 12
            r0.setPosition(r8)
            r8 = 0
            r0.readBytes(r3, r8, r2)
            android.util.Pair r2 = androidx.media3.common.util.CodecSpecificDataUtil.parseAlacAudioSpecificConfig(r3)
            java.lang.Object r8 = r2.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.Object r2 = r2.second
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            F2.y0 r3 = F2.U.B(r3)
            r9 = r2
            r2 = r3
            r18 = r13
            r13 = -1
            r14 = 32
            r20 = 0
            r21 = 1
        L_0x03a8:
            r3 = r33
            goto L_0x0496
        L_0x03ac:
            r13 = 1767990114(0x69616362, float:1.7029852E25)
            if (r3 != r13) goto L_0x03d5
            int r2 = r15 + 9
            r0.setPosition(r2)
            int r2 = r0.readUnsignedLeb128ToInt()
            byte[] r3 = new byte[r2]
            r13 = 0
            r0.readBytes(r3, r13, r2)
            java.lang.String r2 = androidx.media3.common.util.CodecSpecificDataUtil.buildIamfCodecString(r3)
            F2.y0 r3 = F2.U.B(r3)
            r14 = r3
            r3 = r2
            r2 = r14
            r20 = r13
            r13 = -1
            r14 = 32
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            goto L_0x0260
        L_0x03d5:
            r13 = 1885564227(0x70636d43, float:2.8154075E29)
            if (r3 != r13) goto L_0x02c3
            int r3 = r15 + 12
            r0.setPosition(r3)
            int r3 = r0.readUnsignedByte()
            r21 = 1
            r3 = r3 & 1
            if (r3 == 0) goto L_0x03ec
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            goto L_0x03ee
        L_0x03ec:
            java.nio.ByteOrder r3 = java.nio.ByteOrder.BIG_ENDIAN
        L_0x03ee:
            int r13 = r0.readUnsignedByte()
            r14 = 1768973165(0x6970636d, float:1.8163233E25)
            if (r1 != r14) goto L_0x03ff
            int r3 = androidx.media3.common.util.Util.getPcmEncoding(r13, r3)
            r13 = -1
            r14 = 32
            goto L_0x0418
        L_0x03ff:
            r14 = 1718641517(0x6670636d, float:2.8380051E23)
            if (r1 != r14) goto L_0x0413
            r14 = 32
            if (r13 != r14) goto L_0x0415
            java.nio.ByteOrder r13 = java.nio.ByteOrder.LITTLE_ENDIAN
            boolean r3 = r3.equals(r13)
            if (r3 == 0) goto L_0x0415
            r3 = 4
        L_0x0411:
            r13 = -1
            goto L_0x0418
        L_0x0413:
            r14 = 32
        L_0x0415:
            r3 = r23
            goto L_0x0411
        L_0x0418:
            r23 = r3
            if (r3 == r13) goto L_0x041e
            r11 = r24
        L_0x041e:
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            r20 = 0
            goto L_0x03a8
        L_0x0424:
            androidx.media3.common.Format$Builder r3 = new androidx.media3.common.Format$Builder
            r3.<init>()
            androidx.media3.common.Format$Builder r3 = r3.setId((int) r4)
            androidx.media3.common.Format$Builder r3 = r3.setSampleMimeType(r11)
            androidx.media3.common.Format$Builder r3 = r3.setChannelCount(r9)
            androidx.media3.common.Format$Builder r3 = r3.setSampleRate(r8)
            androidx.media3.common.Format$Builder r3 = r3.setDrmInitData(r6)
            androidx.media3.common.Format$Builder r3 = r3.setLanguage(r5)
            androidx.media3.common.Format r3 = r3.build()
            r7.format = r3
        L_0x0447:
            r3 = r33
            r13 = -1
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            r20 = 0
            goto L_0x0496
        L_0x0450:
            if (r3 != r13) goto L_0x0455
            r3 = r15
        L_0x0453:
            r13 = -1
            goto L_0x045a
        L_0x0455:
            int r3 = findBoxPosition(r0, r13, r15, r12)
            goto L_0x0453
        L_0x045a:
            if (r3 == r13) goto L_0x03a8
            androidx.media3.extractor.mp4.BoxParser$EsdsData r16 = parseEsdsFromParent(r0, r3)
            java.lang.String r11 = r16.mimeType
            byte[] r3 = r16.initializationData
            if (r3 == 0) goto L_0x03a8
            java.lang.String r2 = "audio/vorbis"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x0478
            F2.U r2 = androidx.media3.extractor.VorbisUtil.parseVorbisCsdFromEsdsInitializationData(r3)
            goto L_0x03a8
        L_0x0478:
            java.lang.String r2 = "audio/mp4a-latm"
            boolean r2 = r2.equals(r11)
            if (r2 == 0) goto L_0x048b
            androidx.media3.extractor.AacUtil$Config r2 = androidx.media3.extractor.AacUtil.parseAudioSpecificConfig(r3)
            int r8 = r2.sampleRateHz
            int r9 = r2.channelCount
            java.lang.String r2 = r2.codecs
            goto L_0x048d
        L_0x048b:
            r2 = r33
        L_0x048d:
            F2.y0 r3 = F2.U.B(r3)
            r25 = r3
            r3 = r2
            r2 = r25
        L_0x0496:
            int r15 = r15 + r12
            r14 = r35
            r13 = r3
            r3 = r29
            goto L_0x01e1
        L_0x049e:
            r33 = r13
            androidx.media3.common.Format r0 = r7.format
            if (r0 != 0) goto L_0x050c
            if (r11 == 0) goto L_0x050c
            androidx.media3.common.Format$Builder r0 = new androidx.media3.common.Format$Builder
            r0.<init>()
            androidx.media3.common.Format$Builder r0 = r0.setId((int) r4)
            androidx.media3.common.Format$Builder r0 = r0.setSampleMimeType(r11)
            r3 = r33
            androidx.media3.common.Format$Builder r0 = r0.setCodecs(r3)
            androidx.media3.common.Format$Builder r0 = r0.setChannelCount(r9)
            androidx.media3.common.Format$Builder r0 = r0.setSampleRate(r8)
            r12 = r23
            androidx.media3.common.Format$Builder r0 = r0.setPcmEncoding(r12)
            androidx.media3.common.Format$Builder r0 = r0.setInitializationData(r2)
            androidx.media3.common.Format$Builder r0 = r0.setDrmInitData(r6)
            androidx.media3.common.Format$Builder r0 = r0.setLanguage(r5)
            if (r16 == 0) goto L_0x04ed
            long r1 = r16.bitrate
            int r1 = o1.C0246a.j0(r1)
            androidx.media3.common.Format$Builder r1 = r0.setAverageBitrate(r1)
            long r2 = r16.peakBitrate
            int r2 = o1.C0246a.j0(r2)
            r1.setPeakBitrate(r2)
            goto L_0x0506
        L_0x04ed:
            if (r22 == 0) goto L_0x0506
            long r1 = r22.avgBitrate
            int r1 = o1.C0246a.j0(r1)
            androidx.media3.common.Format$Builder r1 = r0.setAverageBitrate(r1)
            long r2 = r22.maxBitrate
            int r2 = o1.C0246a.j0(r2)
            r1.setPeakBitrate(r2)
        L_0x0506:
            androidx.media3.common.Format r0 = r0.build()
            r7.format = r0
        L_0x050c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.BoxParser.parseAudioSampleEntry(androidx.media3.common.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, androidx.media3.common.DrmInitData, androidx.media3.extractor.mp4.BoxParser$StsdData, int):void");
    }

    private static ColorInfo parseAv1c(ParsableByteArray parsableByteArray) {
        int i2;
        int i7;
        int i8;
        int i10;
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        int i11 = 1;
        parsableBitArray.skipBytes(1);
        int readBits = parsableBitArray.readBits(3);
        parsableBitArray.skipBits(6);
        boolean readBit = parsableBitArray.readBit();
        boolean readBit2 = parsableBitArray.readBit();
        int i12 = 10;
        if (readBits == 2 && readBit) {
            if (readBit2) {
                i10 = 12;
            } else {
                i10 = 10;
            }
            builder.setLumaBitdepth(i10);
            if (readBit2) {
                i12 = 12;
            }
            builder.setChromaBitdepth(i12);
        } else if (readBits <= 2) {
            if (readBit) {
                i8 = 10;
            } else {
                i8 = 8;
            }
            builder.setLumaBitdepth(i8);
            if (!readBit) {
                i12 = 8;
            }
            builder.setChromaBitdepth(i12);
        }
        parsableBitArray.skipBits(13);
        parsableBitArray.skipBit();
        int readBits2 = parsableBitArray.readBits(4);
        if (readBits2 != 1) {
            Log.i("BoxParsers", "Unsupported obu_type: " + readBits2);
            return builder.build();
        } else if (parsableBitArray.readBit()) {
            Log.i("BoxParsers", "Unsupported obu_extension_flag");
            return builder.build();
        } else {
            boolean readBit3 = parsableBitArray.readBit();
            parsableBitArray.skipBit();
            if (!readBit3 || parsableBitArray.readBits(8) <= 127) {
                int readBits3 = parsableBitArray.readBits(3);
                parsableBitArray.skipBit();
                if (parsableBitArray.readBit()) {
                    Log.i("BoxParsers", "Unsupported reduced_still_picture_header");
                    return builder.build();
                } else if (parsableBitArray.readBit()) {
                    Log.i("BoxParsers", "Unsupported timing_info_present_flag");
                    return builder.build();
                } else if (parsableBitArray.readBit()) {
                    Log.i("BoxParsers", "Unsupported initial_display_delay_present_flag");
                    return builder.build();
                } else {
                    int readBits4 = parsableBitArray.readBits(5);
                    boolean z = false;
                    for (int i13 = 0; i13 <= readBits4; i13++) {
                        parsableBitArray.skipBits(12);
                        if (parsableBitArray.readBits(5) > 7) {
                            parsableBitArray.skipBit();
                        }
                    }
                    int readBits5 = parsableBitArray.readBits(4);
                    int readBits6 = parsableBitArray.readBits(4);
                    parsableBitArray.skipBits(readBits5 + 1);
                    parsableBitArray.skipBits(readBits6 + 1);
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(7);
                    }
                    parsableBitArray.skipBits(7);
                    boolean readBit4 = parsableBitArray.readBit();
                    if (readBit4) {
                        parsableBitArray.skipBits(2);
                    }
                    if (parsableBitArray.readBit()) {
                        i2 = 2;
                    } else {
                        i2 = parsableBitArray.readBits(1);
                    }
                    if (i2 > 0 && !parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(1);
                    }
                    if (readBit4) {
                        parsableBitArray.skipBits(3);
                    }
                    parsableBitArray.skipBits(3);
                    boolean readBit5 = parsableBitArray.readBit();
                    if (readBits3 == 2 && readBit5) {
                        parsableBitArray.skipBit();
                    }
                    if (readBits3 != 1 && parsableBitArray.readBit()) {
                        z = true;
                    }
                    if (parsableBitArray.readBit()) {
                        int readBits7 = parsableBitArray.readBits(8);
                        int readBits8 = parsableBitArray.readBits(8);
                        int readBits9 = parsableBitArray.readBits(8);
                        if (!z && readBits7 == 1 && readBits8 == 13 && readBits9 == 0) {
                            i7 = 1;
                        } else {
                            i7 = parsableBitArray.readBits(1);
                        }
                        ColorInfo.Builder colorSpace = builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(readBits7));
                        if (i7 != 1) {
                            i11 = 2;
                        }
                        colorSpace.setColorRange(i11).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(readBits8));
                    }
                    return builder.build();
                }
            } else {
                Log.i("BoxParsers", "Excessive obu_size");
                return builder.build();
            }
        }
    }

    private static BtrtData parseBtrtFromParent(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.setPosition(i2 + 8);
        parsableByteArray.skipBytes(4);
        return new BtrtData(parsableByteArray.readUnsignedInt(), parsableByteArray.readUnsignedInt());
    }

    public static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i2, int i7) {
        boolean z;
        boolean z3;
        int i8 = i2 + 8;
        boolean z7 = false;
        int i10 = -1;
        int i11 = 0;
        String str = null;
        Integer num = null;
        while (i8 - i2 < i7) {
            parsableByteArray.setPosition(i8);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == 1935894637) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == 1935894633) {
                i10 = i8;
                i11 = readInt;
            }
            i8 += readInt;
        }
        if (!"cenc".equals(str) && !"cbc1".equals(str) && !"cens".equals(str) && !"cbcs".equals(str)) {
            return null;
        }
        if (num != null) {
            z = true;
        } else {
            z = false;
        }
        ExtractorUtil.checkContainerInput(z, "frma atom is mandatory");
        if (i10 != -1) {
            z3 = true;
        } else {
            z3 = false;
        }
        ExtractorUtil.checkContainerInput(z3, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i10, i11, str);
        if (parseSchiFromParent != null) {
            z7 = true;
        }
        ExtractorUtil.checkContainerInput(z7, "tenc atom is mandatory");
        return Pair.create(num, (TrackEncryptionBox) Util.castNonNull(parseSchiFromParent));
    }

    private static Pair<long[], long[]> parseEdts(Mp4Box.ContainerBox containerBox) {
        long j2;
        long j3;
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(1701606260);
        if (leafBoxOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        int i2 = 0;
        while (i2 < readUnsignedIntToInt) {
            if (parseFullBoxVersion == 1) {
                j2 = parsableByteArray.readUnsignedLongToLong();
            } else {
                j2 = parsableByteArray.readUnsignedInt();
            }
            jArr[i2] = j2;
            if (parseFullBoxVersion == 1) {
                j3 = parsableByteArray.readLong();
            } else {
                j3 = (long) parsableByteArray.readInt();
            }
            jArr2[i2] = j3;
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
                i2++;
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i2) {
        long j2;
        parsableByteArray.setPosition(i2 + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if ("audio/mpeg".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts.hd".equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, (byte[]) null, -1, -1);
        }
        parsableByteArray.skipBytes(4);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        long j3 = readUnsignedInt2;
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        long j8 = readUnsignedInt;
        if (j3 <= 0) {
            j3 = -1;
        }
        if (j8 > 0) {
            j2 = j8;
        } else {
            j2 = -1;
        }
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, j3, j2);
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i2 = (i2 << 7) | (readUnsignedByte & 127);
        }
        return i2;
    }

    public static int parseFullBoxFlags(int i2) {
        return i2 & 16777215;
    }

    public static int parseFullBoxVersion(int i2) {
        return (i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER;
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i2) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static MdhdData parseMdhd(ParsableByteArray parsableByteArray) {
        int i2;
        long j2;
        long j3;
        int i7 = 8;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        if (parseFullBoxVersion == 0) {
            i2 = 8;
        } else {
            i2 = 16;
        }
        parsableByteArray.skipBytes(i2);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        int position = parsableByteArray.getPosition();
        if (parseFullBoxVersion == 0) {
            i7 = 4;
        }
        int i8 = 0;
        while (true) {
            j2 = -9223372036854775807L;
            if (i8 >= i7) {
                parsableByteArray.skipBytes(i7);
                break;
            } else if (parsableByteArray.getData()[position + i8] != -1) {
                if (parseFullBoxVersion == 0) {
                    j3 = parsableByteArray.readUnsignedInt();
                } else {
                    j3 = parsableByteArray.readUnsignedLongToLong();
                }
                if (j3 != 0) {
                    long j8 = readUnsignedInt;
                    readUnsignedInt = j8;
                    j2 = Util.scaleLargeTimestamp(j3, 1000000, j8);
                }
            } else {
                i8++;
            }
        }
        return new MdhdData(readUnsignedInt, j2, getLanguageFromCode(parsableByteArray.readUnsignedShort()));
    }

    public static Metadata parseMdtaFromMeta(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(IsoInterface.BOX_HDLR);
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(1801812339);
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(IsoInterface.BOX_ILST);
        if (leafBoxOfType == null || leafBoxOfType2 == null || leafBoxOfType3 == null || parseHdlr(leafBoxOfType.data) != 1835299937) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i2] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafBoxOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= readInt) {
                a.D(readInt4, "Skipped metadata with unknown key index: ", "BoxParsers");
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i2, int i7, int i8, StsdData stsdData) {
        parsableByteArray.setPosition(i7 + 16);
        if (i2 == 1835365492) {
            parsableByteArray.readNullTerminatedString();
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString();
            if (readNullTerminatedString != null) {
                stsdData.format = new Format.Builder().setId(i8).setSampleMimeType(readNullTerminatedString).build();
            }
        }
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray parsableByteArray) {
        long readLong;
        long readLong2;
        parsableByteArray.setPosition(8);
        if (parseFullBoxVersion(parsableByteArray.readInt()) == 0) {
            readLong = parsableByteArray.readUnsignedInt();
            readLong2 = parsableByteArray.readUnsignedInt();
        } else {
            readLong = parsableByteArray.readLong();
            readLong2 = parsableByteArray.readLong();
        }
        return new Mp4TimestampData(readLong, readLong2, parsableByteArray.readUnsignedInt());
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.setPosition(i2 + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i2, int i7) {
        int i8 = i2 + 8;
        while (i8 - i2 < i7) {
            parsableByteArray.setPosition(i8);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.getData(), i8, readInt + i8);
            }
            i8 += readInt;
        }
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i2, int i7) {
        boolean z;
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i2 < i7) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            if (readInt > 0) {
                z = true;
            } else {
                z = false;
            }
            ExtractorUtil.checkContainerInput(z, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i2, int i7, String str) {
        int i8;
        int i10;
        boolean z;
        int i11 = i2 + 8;
        while (true) {
            byte[] bArr = null;
            if (i11 - i2 >= i7) {
                return null;
            }
            parsableByteArray.setPosition(i11);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullBoxVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i10 = 0;
                    i8 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i8 = readUnsignedByte & 15;
                    i10 = (readUnsignedByte & 240) >> 4;
                }
                if (parsableByteArray.readUnsignedByte() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i10, i8, bArr);
            }
            String str2 = str;
            i11 += readInt;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x043a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.mp4.TrackSampleTable parseStbl(androidx.media3.extractor.mp4.Track r36, androidx.media3.container.Mp4Box.ContainerBox r37, androidx.media3.extractor.GaplessInfoHolder r38) {
        /*
            r1 = r36
            r0 = r37
            r3 = 1937011578(0x7374737a, float:1.936741E31)
            androidx.media3.container.Mp4Box$LeafBox r3 = r0.getLeafBoxOfType(r3)
            if (r3 == 0) goto L_0x0015
            androidx.media3.extractor.mp4.BoxParser$StszSampleSizeBox r5 = new androidx.media3.extractor.mp4.BoxParser$StszSampleSizeBox
            androidx.media3.common.Format r6 = r1.format
            r5.<init>(r3, r6)
            goto L_0x0023
        L_0x0015:
            r3 = 1937013298(0x73747a32, float:1.9369489E31)
            androidx.media3.container.Mp4Box$LeafBox r3 = r0.getLeafBoxOfType(r3)
            if (r3 == 0) goto L_0x056f
            androidx.media3.extractor.mp4.BoxParser$Stz2SampleSizeBox r5 = new androidx.media3.extractor.mp4.BoxParser$Stz2SampleSizeBox
            r5.<init>(r3)
        L_0x0023:
            int r3 = r5.getSampleCount()
            r6 = 0
            if (r3 != 0) goto L_0x003b
            androidx.media3.extractor.mp4.TrackSampleTable r0 = new androidx.media3.extractor.mp4.TrackSampleTable
            long[] r2 = new long[r6]
            int[] r3 = new int[r6]
            long[] r5 = new long[r6]
            int[] r6 = new int[r6]
            r7 = 0
            r4 = 0
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r0
        L_0x003b:
            int r7 = r1.type
            r8 = 2
            r9 = 0
            if (r7 != r8) goto L_0x0061
            long r11 = r1.mediaDurationUs
            int r7 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r7 <= 0) goto L_0x0061
            float r7 = (float) r3
            float r11 = (float) r11
            r12 = 1232348160(0x49742400, float:1000000.0)
            float r11 = r11 / r12
            float r7 = r7 / r11
            androidx.media3.common.Format r11 = r1.format
            androidx.media3.common.Format$Builder r11 = r11.buildUpon()
            androidx.media3.common.Format$Builder r7 = r11.setFrameRate(r7)
            androidx.media3.common.Format r7 = r7.build()
            androidx.media3.extractor.mp4.Track r1 = r1.copyWithFormat(r7)
        L_0x0061:
            r7 = 1937007471(0x7374636f, float:1.9362445E31)
            androidx.media3.container.Mp4Box$LeafBox r7 = r0.getLeafBoxOfType(r7)
            r11 = 1
            if (r7 != 0) goto L_0x007a
            r7 = 1668232756(0x636f3634, float:4.4126776E21)
            androidx.media3.container.Mp4Box$LeafBox r7 = r0.getLeafBoxOfType(r7)
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)
            androidx.media3.container.Mp4Box$LeafBox r7 = (androidx.media3.container.Mp4Box.LeafBox) r7
            r12 = r11
            goto L_0x007b
        L_0x007a:
            r12 = r6
        L_0x007b:
            androidx.media3.common.util.ParsableByteArray r7 = r7.data
            r13 = 1937011555(0x73747363, float:1.9367382E31)
            androidx.media3.container.Mp4Box$LeafBox r13 = r0.getLeafBoxOfType(r13)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            androidx.media3.container.Mp4Box$LeafBox r13 = (androidx.media3.container.Mp4Box.LeafBox) r13
            androidx.media3.common.util.ParsableByteArray r13 = r13.data
            r14 = 1937011827(0x73747473, float:1.9367711E31)
            androidx.media3.container.Mp4Box$LeafBox r14 = r0.getLeafBoxOfType(r14)
            java.lang.Object r14 = androidx.media3.common.util.Assertions.checkNotNull(r14)
            androidx.media3.container.Mp4Box$LeafBox r14 = (androidx.media3.container.Mp4Box.LeafBox) r14
            androidx.media3.common.util.ParsableByteArray r14 = r14.data
            r15 = 1937011571(0x73747373, float:1.9367401E31)
            androidx.media3.container.Mp4Box$LeafBox r15 = r0.getLeafBoxOfType(r15)
            if (r15 == 0) goto L_0x00a9
            androidx.media3.common.util.ParsableByteArray r15 = r15.data
        L_0x00a6:
            r16 = r9
            goto L_0x00ab
        L_0x00a9:
            r15 = 0
            goto L_0x00a6
        L_0x00ab:
            r9 = 1668576371(0x63747473, float:4.5093966E21)
            androidx.media3.container.Mp4Box$LeafBox r0 = r0.getLeafBoxOfType(r9)
            if (r0 == 0) goto L_0x00b7
            androidx.media3.common.util.ParsableByteArray r0 = r0.data
            goto L_0x00b8
        L_0x00b7:
            r0 = 0
        L_0x00b8:
            androidx.media3.extractor.mp4.BoxParser$ChunkIterator r9 = new androidx.media3.extractor.mp4.BoxParser$ChunkIterator
            r9.<init>(r13, r7, r12)
            r7 = 12
            r14.setPosition(r7)
            int r10 = r14.readUnsignedIntToInt()
            int r10 = r10 - r11
            int r12 = r14.readUnsignedIntToInt()
            int r13 = r14.readUnsignedIntToInt()
            if (r0 == 0) goto L_0x00d9
            r0.setPosition(r7)
            int r18 = r0.readUnsignedIntToInt()
            goto L_0x00db
        L_0x00d9:
            r18 = r6
        L_0x00db:
            r4 = -1
            if (r15 == 0) goto L_0x00f6
            r15.setPosition(r7)
            int r7 = r15.readUnsignedIntToInt()
            if (r7 <= 0) goto L_0x00f0
            int r19 = r15.readUnsignedIntToInt()
            int r19 = r19 + -1
            r20 = r6
            goto L_0x00fb
        L_0x00f0:
            r19 = r4
            r20 = r6
            r15 = 0
            goto L_0x00fb
        L_0x00f6:
            r19 = r4
            r7 = r6
            r20 = r7
        L_0x00fb:
            int r6 = r5.getFixedSampleSize()
            androidx.media3.common.Format r8 = r1.format
            java.lang.String r8 = r8.sampleMimeType
            if (r6 == r4) goto L_0x0125
            java.lang.String r4 = "audio/raw"
            boolean r4 = r4.equals(r8)
            if (r4 != 0) goto L_0x011d
            java.lang.String r4 = "audio/g711-mlaw"
            boolean r4 = r4.equals(r8)
            if (r4 != 0) goto L_0x011d
            java.lang.String r4 = "audio/g711-alaw"
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x0125
        L_0x011d:
            if (r10 != 0) goto L_0x0125
            if (r18 != 0) goto L_0x0125
            if (r7 != 0) goto L_0x0125
            r4 = r11
            goto L_0x0127
        L_0x0125:
            r4 = r20
        L_0x0127:
            if (r4 == 0) goto L_0x015e
            int r0 = r9.length
            long[] r4 = new long[r0]
            int[] r0 = new int[r0]
        L_0x012f:
            boolean r5 = r9.moveNext()
            if (r5 == 0) goto L_0x0140
            int r5 = r9.index
            long r7 = r9.offset
            r4[r5] = r7
            int r7 = r9.numSamples
            r0[r5] = r7
            goto L_0x012f
        L_0x0140:
            long r7 = (long) r13
            androidx.media3.extractor.mp4.FixedSampleSizeRechunker$Results r0 = androidx.media3.extractor.mp4.FixedSampleSizeRechunker.rechunk(r6, r4, r0, r7)
            long[] r4 = r0.offsets
            int[] r5 = r0.sizes
            int r6 = r0.maximumSize
            long[] r7 = r0.timestamps
            int[] r8 = r0.flags
            long r9 = r0.duration
            long r12 = r0.totalSize
            r22 = r9
            r36 = r11
            r9 = r7
            r10 = r8
            r8 = r6
            r7 = r5
            r6 = r4
            goto L_0x02ba
        L_0x015e:
            long[] r4 = new long[r3]
            int[] r6 = new int[r3]
            long[] r8 = new long[r3]
            r36 = r11
            int[] r11 = new int[r3]
            r37 = r0
            r23 = r5
            r0 = r13
            r24 = r14
            r27 = r15
            r25 = r16
            r29 = r25
            r22 = r18
            r5 = r19
            r14 = r20
            r15 = r14
            r28 = r15
            r31 = r28
            r13 = r12
            r18 = r29
            r12 = r10
            r10 = r7
            r7 = r31
        L_0x0187:
            java.lang.String r2 = "BoxParsers"
            if (r14 >= r3) goto L_0x0243
            r32 = r29
            r29 = r28
            r28 = r36
        L_0x0191:
            if (r29 != 0) goto L_0x01ae
            boolean r28 = r9.moveNext()
            if (r28 == 0) goto L_0x01ae
            r30 = r12
            r34 = r13
            long r12 = r9.offset
            r35 = r3
            int r3 = r9.numSamples
            r29 = r3
            r32 = r12
            r12 = r30
            r13 = r34
            r3 = r35
            goto L_0x0191
        L_0x01ae:
            r35 = r3
            r30 = r12
            r34 = r13
            if (r28 != 0) goto L_0x01d4
            java.lang.String r0 = "Unexpected end of chunk data"
            androidx.media3.common.util.Log.w(r2, r0)
            long[] r0 = java.util.Arrays.copyOf(r4, r14)
            int[] r3 = java.util.Arrays.copyOf(r6, r14)
            long[] r4 = java.util.Arrays.copyOf(r8, r14)
            int[] r5 = java.util.Arrays.copyOf(r11, r14)
            r8 = r4
            r11 = r5
            r4 = r0
            r5 = r3
            r3 = r14
            r0 = r29
            goto L_0x024c
        L_0x01d4:
            if (r37 == 0) goto L_0x01eb
            r2 = r31
        L_0x01d8:
            if (r2 != 0) goto L_0x01e7
            if (r22 <= 0) goto L_0x01e7
            int r2 = r37.readUnsignedIntToInt()
            int r15 = r37.readInt()
            int r22 = r22 + -1
            goto L_0x01d8
        L_0x01e7:
            int r2 = r2 + -1
            r31 = r2
        L_0x01eb:
            r4[r14] = r32
            int r2 = r23.readNextSampleSize()
            r6[r14] = r2
            long r12 = (long) r2
            long r18 = r18 + r12
            if (r2 <= r7) goto L_0x01f9
            r7 = r2
        L_0x01f9:
            long r2 = (long) r15
            long r2 = r25 + r2
            r8[r14] = r2
            if (r27 != 0) goto L_0x0203
            r2 = r36
            goto L_0x0205
        L_0x0203:
            r2 = r20
        L_0x0205:
            r11[r14] = r2
            if (r14 != r5) goto L_0x021c
            r11[r14] = r36
            int r10 = r10 + -1
            if (r10 <= 0) goto L_0x021c
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r27)
            androidx.media3.common.util.ParsableByteArray r2 = (androidx.media3.common.util.ParsableByteArray) r2
            int r2 = r2.readUnsignedIntToInt()
            int r2 = r2 + -1
            r5 = r2
        L_0x021c:
            long r2 = (long) r0
            long r25 = r25 + r2
            int r13 = r34 + -1
            if (r13 != 0) goto L_0x0232
            if (r30 <= 0) goto L_0x0232
            int r0 = r24.readUnsignedIntToInt()
            int r2 = r24.readInt()
            int r12 = r30 + -1
            r13 = r0
            r0 = r2
            goto L_0x0234
        L_0x0232:
            r12 = r30
        L_0x0234:
            r2 = r6[r14]
            long r2 = (long) r2
            long r2 = r32 + r2
            int r28 = r29 + -1
            int r14 = r14 + 1
            r29 = r2
            r3 = r35
            goto L_0x0187
        L_0x0243:
            r35 = r3
            r30 = r12
            r34 = r13
            r5 = r6
            r0 = r28
        L_0x024c:
            long r12 = (long) r15
            long r12 = r25 + r12
            if (r37 == 0) goto L_0x0262
        L_0x0251:
            if (r22 <= 0) goto L_0x0262
            int r6 = r37.readUnsignedIntToInt()
            if (r6 == 0) goto L_0x025c
            r6 = r20
            goto L_0x0264
        L_0x025c:
            r37.readInt()
            int r22 = r22 + -1
            goto L_0x0251
        L_0x0262:
            r6 = r36
        L_0x0264:
            if (r10 != 0) goto L_0x0274
            if (r34 != 0) goto L_0x0274
            if (r0 != 0) goto L_0x0274
            if (r30 != 0) goto L_0x0274
            if (r31 != 0) goto L_0x0274
            if (r6 != 0) goto L_0x0271
            goto L_0x0274
        L_0x0271:
            r37 = r3
            goto L_0x02af
        L_0x0274:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r14 = "Inconsistent stbl box for track "
            r9.<init>(r14)
            int r14 = r1.id
            java.lang.String r15 = ": remainingSynchronizationSamples "
            r37 = r3
            java.lang.String r3 = ", remainingSamplesAtTimestampDelta "
            N2.j.x(r9, r14, r15, r10, r3)
            java.lang.String r3 = ", remainingSamplesInChunk "
            java.lang.String r10 = ", remainingTimestampDeltaChanges "
            r14 = r34
            N2.j.x(r9, r14, r3, r0, r10)
            r10 = r30
            r9.append(r10)
            java.lang.String r0 = ", remainingSamplesAtTimestampOffset "
            r9.append(r0)
            r0 = r31
            r9.append(r0)
            if (r6 != 0) goto L_0x02a3
            java.lang.String r0 = ", ctts invalid"
            goto L_0x02a5
        L_0x02a3:
            java.lang.String r0 = ""
        L_0x02a5:
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            androidx.media3.common.util.Log.w(r2, r0)
        L_0x02af:
            r3 = r37
            r9 = r8
            r10 = r11
            r22 = r12
            r12 = r18
            r8 = r7
            r6 = r4
            r7 = r5
        L_0x02ba:
            long r4 = r1.mediaDurationUs
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            r18 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r0 <= 0) goto L_0x02ed
            r14 = 8
            long r24 = r12 * r14
            r26 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r30 = java.math.RoundingMode.HALF_DOWN
            r28 = r4
            long r4 = androidx.media3.common.util.Util.scaleLargeValue(r24, r26, r28, r30)
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x02ed
            int r0 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r0 >= 0) goto L_0x02ed
            androidx.media3.common.Format r0 = r1.format
            androidx.media3.common.Format$Builder r0 = r0.buildUpon()
            int r2 = (int) r4
            androidx.media3.common.Format$Builder r0 = r0.setAverageBitrate(r2)
            androidx.media3.common.Format r0 = r0.build()
            androidx.media3.extractor.mp4.Track r1 = r1.copyWithFormat(r0)
        L_0x02ed:
            r5 = r1
            r24 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r5.timescale
            r26 = r0
            long r11 = androidx.media3.common.util.Util.scaleLargeTimestamp(r22, r24, r26)
            long[] r0 = r5.editListDurations
            r1 = 1000000(0xf4240, double:4.940656E-318)
            if (r0 != 0) goto L_0x030b
            long r3 = r5.timescale
            androidx.media3.common.util.Util.scaleLargeTimestampsInPlace(r9, r1, r3)
            androidx.media3.extractor.mp4.TrackSampleTable r4 = new androidx.media3.extractor.mp4.TrackSampleTable
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            return r4
        L_0x030b:
            r4 = r10
            int r0 = r0.length
            r10 = r36
            if (r0 != r10) goto L_0x036a
            int r0 = r5.type
            if (r0 != r10) goto L_0x036a
            int r0 = r9.length
            r10 = 2
            if (r0 < r10) goto L_0x036a
            long[] r0 = r5.editListMediaTimes
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            long[] r0 = (long[]) r0
            r12 = r0[r20]
            long[] r0 = r5.editListDurations
            r24 = r0[r20]
            long r10 = r5.timescale
            long r14 = r5.movieTimescale
            r26 = r10
            r28 = r14
            long r10 = androidx.media3.common.util.Util.scaleLargeTimestamp(r24, r26, r28)
            long r14 = r12 + r10
            r10 = r22
            boolean r0 = canApplyEditWithGaplessInfo(r9, r10, r12, r14)
            if (r0 == 0) goto L_0x036a
            long r24 = r22 - r14
            r10 = r9[r20]
            long r26 = r12 - r10
            androidx.media3.common.Format r0 = r5.format
            int r0 = r0.sampleRate
            long r10 = (long) r0
            long r12 = r5.timescale
            r28 = r10
            r30 = r12
            long r10 = androidx.media3.common.util.Util.scaleLargeTimestamp(r26, r28, r30)
            androidx.media3.common.Format r0 = r5.format
            int r0 = r0.sampleRate
            long r12 = (long) r0
            long r14 = r5.timescale
            r26 = r12
            r28 = r14
            long r12 = androidx.media3.common.util.Util.scaleLargeTimestamp(r24, r26, r28)
            int r0 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x036c
            int r0 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x036a
            goto L_0x036c
        L_0x036a:
            r10 = r4
            goto L_0x0395
        L_0x036c:
            int r0 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r0 > 0) goto L_0x036a
            int r0 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r0 > 0) goto L_0x036a
            int r0 = (int) r10
            r3 = r38
            r3.encoderDelay = r0
            int r0 = (int) r12
            r3.encoderPadding = r0
            long r10 = r5.timescale
            androidx.media3.common.util.Util.scaleLargeTimestampsInPlace(r9, r1, r10)
            long[] r0 = r5.editListDurations
            r10 = r0[r20]
            r12 = 1000000(0xf4240, double:4.940656E-318)
            long r14 = r5.movieTimescale
            long r11 = androidx.media3.common.util.Util.scaleLargeTimestamp(r10, r12, r14)
            r10 = r4
            androidx.media3.extractor.mp4.TrackSampleTable r4 = new androidx.media3.extractor.mp4.TrackSampleTable
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            return r4
        L_0x0395:
            long[] r0 = r5.editListDurations
            int r1 = r0.length
            r2 = 1
            if (r1 != r2) goto L_0x03d5
            r1 = r0[r20]
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 != 0) goto L_0x03d5
            long[] r0 = r5.editListMediaTimes
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            long[] r0 = (long[]) r0
            r0 = r0[r20]
            r2 = r20
        L_0x03ad:
            int r3 = r9.length
            if (r2 >= r3) goto L_0x03c3
            r3 = r9[r2]
            long r11 = r3 - r0
            r13 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r5.timescale
            r15 = r3
            long r3 = androidx.media3.common.util.Util.scaleLargeTimestamp(r11, r13, r15)
            r9[r2] = r3
            int r2 = r2 + 1
            goto L_0x03ad
        L_0x03c3:
            long r11 = r22 - r0
            r13 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r5.timescale
            r15 = r0
            long r11 = androidx.media3.common.util.Util.scaleLargeTimestamp(r11, r13, r15)
            androidx.media3.extractor.mp4.TrackSampleTable r4 = new androidx.media3.extractor.mp4.TrackSampleTable
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            return r4
        L_0x03d5:
            int r1 = r5.type
            r2 = 1
            if (r1 != r2) goto L_0x03dc
            r1 = 1
            goto L_0x03de
        L_0x03dc:
            r1 = r20
        L_0x03de:
            int r2 = r0.length
            int[] r2 = new int[r2]
            int r0 = r0.length
            int[] r0 = new int[r0]
            long[] r4 = r5.editListMediaTimes
            java.lang.Object r4 = androidx.media3.common.util.Assertions.checkNotNull(r4)
            long[] r4 = (long[]) r4
            r11 = r20
            r12 = r11
            r13 = r12
            r14 = r13
        L_0x03f1:
            long[] r15 = r5.editListDurations
            r18 = r0
            int r0 = r15.length
            if (r11 >= r0) goto L_0x0494
            r0 = r11
            r37 = r12
            r11 = r4[r0]
            r22 = -1
            int r19 = (r11 > r22 ? 1 : (r11 == r22 ? 0 : -1))
            if (r19 == 0) goto L_0x0480
            r22 = r15[r0]
            r15 = r7
            r38 = r8
            long r7 = r5.timescale
            r24 = r7
            long r7 = r5.movieTimescale
            r26 = r7
            long r7 = androidx.media3.common.util.Util.scaleLargeTimestamp(r22, r24, r26)
            r19 = r0
            r0 = 1
            int r22 = androidx.media3.common.util.Util.binarySearchFloor((long[]) r9, (long) r11, (boolean) r0, (boolean) r0)
            r2[r19] = r22
            long r11 = r11 + r7
            r8 = r20
            int r7 = androidx.media3.common.util.Util.binarySearchCeil(r9, r11, r1, r8)
            r18[r19] = r7
            r7 = r2[r19]
        L_0x0428:
            r20 = r2[r19]
            if (r20 < 0) goto L_0x0438
            r22 = r10[r20]
            r22 = r22 & 1
            if (r22 != 0) goto L_0x0438
            int r20 = r20 + -1
            r2[r19] = r20
            r0 = 1
            goto L_0x0428
        L_0x0438:
            if (r20 >= 0) goto L_0x044f
            r2[r19] = r7
        L_0x043c:
            r0 = r2[r19]
            r7 = r18[r19]
            if (r0 >= r7) goto L_0x044f
            r7 = r10[r0]
            r20 = 1
            r7 = r7 & 1
            if (r7 != 0) goto L_0x044f
            int r0 = r0 + 1
            r2[r19] = r0
            goto L_0x043c
        L_0x044f:
            int r0 = r5.type
            r7 = 2
            if (r0 != r7) goto L_0x0470
            r0 = r2[r19]
            r7 = r18[r19]
            if (r0 == r7) goto L_0x0470
        L_0x045a:
            r0 = r18[r19]
            int r7 = r9.length
            r20 = 1
            int r7 = r7 + -1
            if (r0 >= r7) goto L_0x0470
            int r7 = r0 + 1
            r22 = r9[r7]
            int r7 = (r22 > r11 ? 1 : (r22 == r11 ? 0 : -1))
            if (r7 > 0) goto L_0x0470
            int r0 = r0 + 1
            r18[r19] = r0
            goto L_0x045a
        L_0x0470:
            r0 = r18[r19]
            r7 = r2[r19]
            int r11 = r0 - r7
            int r13 = r13 + r11
            if (r14 == r7) goto L_0x047b
            r7 = 1
            goto L_0x047c
        L_0x047b:
            r7 = r8
        L_0x047c:
            r12 = r37 | r7
            r14 = r0
            goto L_0x0489
        L_0x0480:
            r19 = r0
            r15 = r7
            r38 = r8
            r8 = r20
            r12 = r37
        L_0x0489:
            int r11 = r19 + 1
            r20 = r8
            r7 = r15
            r0 = r18
            r8 = r38
            goto L_0x03f1
        L_0x0494:
            r15 = r7
            r38 = r8
            r37 = r12
            r8 = r20
            if (r13 == r3) goto L_0x049f
            r0 = 1
            goto L_0x04a0
        L_0x049f:
            r0 = r8
        L_0x04a0:
            r0 = r37 | r0
            if (r0 == 0) goto L_0x04a7
            long[] r1 = new long[r13]
            goto L_0x04a8
        L_0x04a7:
            r1 = r6
        L_0x04a8:
            if (r0 == 0) goto L_0x04ad
            int[] r7 = new int[r13]
            goto L_0x04ae
        L_0x04ad:
            r7 = r15
        L_0x04ae:
            if (r0 == 0) goto L_0x04b2
            r3 = r8
            goto L_0x04b4
        L_0x04b2:
            r3 = r38
        L_0x04b4:
            if (r0 == 0) goto L_0x04b9
            int[] r4 = new int[r13]
            goto L_0x04ba
        L_0x04b9:
            r4 = r10
        L_0x04ba:
            long[] r11 = new long[r13]
            r23 = r3
            r3 = r8
            r12 = r3
            r24 = r16
        L_0x04c2:
            long[] r13 = r5.editListDurations
            int r13 = r13.length
            if (r8 >= r13) goto L_0x053d
            long[] r13 = r5.editListMediaTimes
            r13 = r13[r8]
            r37 = r0
            r0 = r2[r8]
            r19 = r2
            r2 = r18[r8]
            r38 = r3
            if (r37 == 0) goto L_0x04e2
            int r3 = r2 - r0
            java.lang.System.arraycopy(r6, r0, r1, r12, r3)
            java.lang.System.arraycopy(r15, r0, r7, r12, r3)
            java.lang.System.arraycopy(r10, r0, r4, r12, r3)
        L_0x04e2:
            r3 = r38
            r21 = r1
            r1 = r23
        L_0x04e8:
            if (r0 >= r2) goto L_0x0528
            r26 = 1000000(0xf4240, double:4.940656E-318)
            r20 = r2
            r38 = r3
            long r2 = r5.movieTimescale
            r28 = r2
            long r2 = androidx.media3.common.util.Util.scaleLargeTimestamp(r24, r26, r28)
            r22 = r9[r0]
            long r26 = r22 - r13
            r28 = 1000000(0xf4240, double:4.940656E-318)
            r22 = r2
            long r2 = r5.timescale
            r30 = r2
            long r2 = androidx.media3.common.util.Util.scaleLargeTimestamp(r26, r28, r30)
            int r26 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r26 >= 0) goto L_0x0511
            r26 = 1
            goto L_0x0513
        L_0x0511:
            r26 = r38
        L_0x0513:
            long r2 = r22 + r2
            r11[r12] = r2
            if (r37 == 0) goto L_0x051f
            r2 = r7[r12]
            if (r2 <= r1) goto L_0x051f
            r1 = r15[r0]
        L_0x051f:
            int r12 = r12 + 1
            int r0 = r0 + 1
            r2 = r20
            r3 = r26
            goto L_0x04e8
        L_0x0528:
            r38 = r3
            long[] r0 = r5.editListDurations
            r2 = r0[r8]
            long r24 = r24 + r2
            int r8 = r8 + 1
            r0 = r37
            r3 = r38
            r23 = r1
            r2 = r19
            r1 = r21
            goto L_0x04c2
        L_0x053d:
            r21 = r1
            r38 = r3
            r26 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r5.movieTimescale
            r28 = r0
            long r26 = androidx.media3.common.util.Util.scaleLargeTimestamp(r24, r26, r28)
            if (r38 == 0) goto L_0x0561
            androidx.media3.common.Format r0 = r5.format
            androidx.media3.common.Format$Builder r0 = r0.buildUpon()
            r2 = 1
            androidx.media3.common.Format$Builder r0 = r0.setHasPrerollSamples(r2)
            androidx.media3.common.Format r0 = r0.build()
            androidx.media3.extractor.mp4.Track r5 = r5.copyWithFormat(r0)
        L_0x0561:
            r20 = r5
            androidx.media3.extractor.mp4.TrackSampleTable r19 = new androidx.media3.extractor.mp4.TrackSampleTable
            r25 = r4
            r22 = r7
            r24 = r11
            r19.<init>(r20, r21, r22, r23, r24, r25, r26)
            return r19
        L_0x056f:
            java.lang.String r0 = "Track has no sample table size information"
            r1 = 0
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.BoxParser.parseStbl(androidx.media3.extractor.mp4.Track, androidx.media3.container.Mp4Box$ContainerBox, androidx.media3.extractor.GaplessInfoHolder):androidx.media3.extractor.mp4.TrackSampleTable");
    }

    private static EyesData parseStereoViewBox(ParsableByteArray parsableByteArray, int i2, int i7) {
        boolean z;
        boolean z3;
        boolean z7;
        parsableByteArray.setPosition(i2 + 8);
        int position = parsableByteArray.getPosition();
        while (position - i2 < i7) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            boolean z9 = false;
            if (readInt > 0) {
                z = true;
            } else {
                z = false;
            }
            ExtractorUtil.checkContainerInput(z, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1937011305) {
                parsableByteArray.skipBytes(4);
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                if ((readUnsignedByte & 1) == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((readUnsignedByte & 2) == 2) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if ((readUnsignedByte & 8) == 8) {
                    z9 = true;
                }
                return new EyesData(new StriData(z3, z7, z9));
            }
            position += readInt;
        }
        return null;
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, TkhdData tkhdData, String str, DrmInitData drmInitData, boolean z) {
        boolean z3;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            ExtractorUtil.checkContainerInput(z3, "childAtomSize must be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == 1635148593 || readInt3 == 1635148595 || readInt3 == 1701733238 || readInt3 == 1831958048 || readInt3 == 1836070006 || readInt3 == 1752589105 || readInt3 == 1751479857 || readInt3 == 1932670515 || readInt3 == 1211250227 || readInt3 == 1748121139 || readInt3 == 1987063864 || readInt3 == 1987063865 || readInt3 == 1635135537 || readInt3 == 1685479798 || readInt3 == 1685479729 || readInt3 == 1685481573 || readInt3 == 1685481521 || readInt3 == 1634760241) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, tkhdData.id, str, tkhdData.rotationDegrees, drmInitData, stsdData, i2);
            } else if (readInt3 == 1836069985 || readInt3 == 1701733217 || readInt3 == 1633889587 || readInt3 == 1700998451 || readInt3 == 1633889588 || readInt3 == 1835823201 || readInt3 == 1685353315 || readInt3 == 1685353317 || readInt3 == 1685353320 || readInt3 == 1685353324 || readInt3 == 1685353336 || readInt3 == 1935764850 || readInt3 == 1935767394 || readInt3 == 1819304813 || readInt3 == 1936684916 || readInt3 == 1953984371 || readInt3 == 778924082 || readInt3 == 778924083 || readInt3 == 1835557169 || readInt3 == 1835560241 || readInt3 == 1634492771 || readInt3 == 1634492791 || readInt3 == 1970037111 || readInt3 == 1332770163 || readInt3 == 1716281667 || readInt3 == 1767992678 || readInt3 == 1768973165 || readInt3 == 1718641517) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, tkhdData.id, str, z, drmInitData, stsdData, i2);
            } else if (readInt3 == 1414810956 || readInt3 == 1954034535 || readInt3 == 2004251764 || readInt3 == 1937010800 || readInt3 == 1664495672 || readInt3 == 1836070003) {
                StsdData stsdData2 = stsdData;
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, tkhdData, str, stsdData2);
                stsdData = stsdData2;
            } else if (readInt3 == 1835365492) {
                parseMetaDataSampleEntry(parsableByteArray, readInt3, position, tkhdData.id, stsdData);
            } else if (readInt3 == 1667329389) {
                stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType("application/x-camera-motion").build();
            }
            parsableByteArray.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i2, int i7, int i8, TkhdData tkhdData, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i7 + 16);
        String str2 = "application/ttml+xml";
        y0 y0Var = null;
        long j2 = Long.MAX_VALUE;
        if (i2 != 1414810956) {
            if (i2 == 1954034535) {
                int i10 = i8 - 16;
                byte[] bArr = new byte[i10];
                parsableByteArray.readBytes(bArr, 0, i10);
                y0Var = U.B(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i2 == 2004251764) {
                str2 = "application/x-mp4-vtt";
            } else if (i2 == 1937010800) {
                j2 = 0;
            } else if (i2 == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = "application/x-mp4-cea-608";
            } else if (i2 == 1836070003) {
                int position = parsableByteArray.getPosition();
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.readInt() == 1702061171) {
                    EsdsData parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, position);
                    if (parseEsdsFromParent.initializationData != null && parseEsdsFromParent.initializationData.length == 64) {
                        y0Var = U.B(Util.getUtf8Bytes(formatVobsubIdx(parseEsdsFromParent.initializationData, tkhdData.width, tkhdData.height)));
                        str2 = "application/vobsub";
                    } else {
                        return;
                    }
                } else {
                    str2 = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
        if (str2 != null) {
            stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(str2).setLanguage(str).setSubsampleOffsetUs(j2).setInitializationData(y0Var).build();
        }
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        int i2;
        long j2;
        long j3;
        int i7 = 8;
        parsableByteArray.setPosition(8);
        int parseFullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        if (parseFullBoxVersion == 0) {
            i2 = 8;
        } else {
            i2 = 16;
        }
        parsableByteArray.skipBytes(i2);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullBoxVersion == 0) {
            i7 = 4;
        }
        int i8 = 0;
        while (true) {
            j2 = -9223372036854775807L;
            if (i8 >= i7) {
                parsableByteArray.skipBytes(i7);
                break;
            } else if (parsableByteArray.getData()[position + i8] != -1) {
                if (parseFullBoxVersion == 0) {
                    j3 = parsableByteArray.readUnsignedInt();
                } else {
                    j3 = parsableByteArray.readUnsignedLongToLong();
                }
                if (j3 != 0) {
                    j2 = j3;
                }
            } else {
                i8++;
            }
        }
        parsableByteArray.skipBytes(10);
        int i10 = 0;
        long j8 = j2;
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(4);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && ((readInt4 == -65536 || readInt4 == 65536) && readInt5 == 0)) {
            i10 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && ((readInt4 == 65536 || readInt4 == -65536) && readInt5 == 0)) {
            i10 = 270;
        } else if ((readInt2 == -65536 || readInt2 == 65536) && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i10 = MOCRLang.KHMER;
        }
        int i11 = i10;
        parsableByteArray.skipBytes(16);
        short readShort = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        return new TkhdData(readInt, j8, readUnsignedShort, i11, readShort, parsableByteArray.readShort());
    }

    public static Track parseTrak(Mp4Box.ContainerBox containerBox, Mp4Box.LeafBox leafBox, long j2, DrmInitData drmInitData, boolean z, boolean z3) {
        long j3;
        long[] jArr;
        long[] jArr2;
        Format format;
        Metadata metadata;
        Mp4Box.ContainerBox containerBoxOfType;
        Pair<long[], long[]> parseEdts;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        Mp4Box.ContainerBox containerBox3 = (Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(IsoInterface.BOX_MDIA));
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox3.getLeafBoxOfType(IsoInterface.BOX_HDLR))).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(IsoInterface.BOX_TKHD))).data);
        long j8 = -9223372036854775807L;
        if (j2 == -9223372036854775807L) {
            j3 = parseTkhd.duration;
        } else {
            j3 = j2;
        }
        long j10 = parseMvhd(leafBox.data).timescale;
        if (j3 != -9223372036854775807L) {
            j8 = Util.scaleLargeTimestamp(j3, 1000000, j10);
        }
        long j11 = j8;
        MdhdData parseMdhd = parseMdhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox3.getLeafBoxOfType(IsoInterface.BOX_MDHD))).data);
        Mp4Box.LeafBox leafBoxOfType = ((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox3.getContainerBoxOfType(1835626086))).getContainerBoxOfType(1937007212))).getLeafBoxOfType(1937011556);
        if (leafBoxOfType != null) {
            StsdData parseStsd = parseStsd(leafBoxOfType.data, parseTkhd, parseMdhd.language, drmInitData, z3);
            if (z || (containerBoxOfType = containerBox2.getContainerBoxOfType(1701082227)) == null || (parseEdts = parseEdts(containerBoxOfType)) == null) {
                jArr2 = null;
                jArr = null;
            } else {
                jArr = (long[]) parseEdts.second;
                jArr2 = (long[]) parseEdts.first;
            }
            if (parseStsd.format == null) {
                return null;
            }
            if (parseTkhd.alternateGroup != 0) {
                Mp4AlternateGroupData mp4AlternateGroupData = new Mp4AlternateGroupData(parseTkhd.alternateGroup);
                Format.Builder buildUpon = parseStsd.format.buildUpon();
                Metadata metadata2 = parseStsd.format.metadata;
                if (metadata2 != null) {
                    metadata = metadata2.copyWithAppendedEntries(mp4AlternateGroupData);
                } else {
                    metadata = new Metadata(mp4AlternateGroupData);
                }
                format = buildUpon.setMetadata(metadata).build();
            } else {
                format = parseStsd.format;
            }
            return new Track(parseTkhd.id, trackTypeForHdlr, parseMdhd.timescale, j10, j11, parseMdhd.mediaDurationUs, format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr2, jArr);
        }
        throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", (Throwable) null);
    }

    public static List<TrackSampleTable> parseTraks(Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder, long j2, DrmInitData drmInitData, boolean z, boolean z3, h hVar) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < containerBox.containerChildren.size(); i2++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i2);
            if (containerBox2.type != 1953653099) {
                h hVar2 = hVar;
            } else {
                Mp4Box.ContainerBox containerBox3 = containerBox2;
                Track track = (Track) hVar.apply(parseTrak(containerBox2, (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(IsoInterface.BOX_MVHD)), j2, drmInitData, z, z3));
                if (track != null) {
                    arrayList.add(parseStbl(track, (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox3.getContainerBoxOfType(IsoInterface.BOX_MDIA))).getContainerBoxOfType(1835626086))).getContainerBoxOfType(1937007212)), gaplessInfoHolder));
                }
            }
        }
        return arrayList;
    }

    public static Metadata parseUdta(Mp4Box.LeafBox leafBox) {
        ParsableByteArray parsableByteArray = leafBox.data;
        parsableByteArray.setPosition(8);
        Metadata metadata = new Metadata(new Metadata.Entry[0]);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1835365473) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(parseUdtaMeta(parsableByteArray, position + readInt));
            } else if (readInt2 == 1936553057) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(parsableByteArray, position + readInt));
            } else if (readInt2 == -1451722374) {
                metadata = metadata.copyWithAppendedEntriesFrom(parseXyz(parsableByteArray));
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return metadata;
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaBoxHeaderBytes(parsableByteArray);
        while (parsableByteArray.getPosition() < i2) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    public static VexuData parseVideoExtendedUsageBox(ParsableByteArray parsableByteArray, int i2, int i7) {
        boolean z;
        parsableByteArray.setPosition(i2 + 8);
        int position = parsableByteArray.getPosition();
        EyesData eyesData = null;
        while (position - i2 < i7) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            if (readInt > 0) {
                z = true;
            } else {
                z = false;
            }
            ExtractorUtil.checkContainerInput(z, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1702454643) {
                eyesData = parseStereoViewBox(parsableByteArray, position, readInt);
            }
            position += readInt;
        }
        if (eyesData == null) {
            return null;
        }
        return new VexuData(eyesData);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i2, int i7, int i8, int i10, String str, int i11, DrmInitData drmInitData, StsdData stsdData, int i12) {
        int i13;
        int i14;
        String str2;
        int i15;
        DrmInitData drmInitData2;
        int i16;
        int i17;
        NalUnitUtil.H265VpsData h265VpsData;
        int i18;
        int i19;
        y0 y0Var;
        int i20;
        List<byte[]> B;
        int i21;
        NalUnitUtil.H265VpsData h265VpsData2;
        DrmInitData drmInitData3;
        y0 y0Var2;
        int i22;
        NalUnitUtil.H265VpsData h265VpsData3;
        int i23;
        int i24;
        int i25;
        int i26;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i27 = i7;
        int i28 = i8;
        DrmInitData drmInitData4 = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i27 + 16);
        parsableByteArray2.skipBytes(16);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray2.readUnsignedShort();
        parsableByteArray2.skipBytes(50);
        int position = parsableByteArray2.getPosition();
        int i29 = i2;
        if (i29 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i27, i28);
            if (parseSampleEntryEncryptionData != null) {
                i29 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData4 == null) {
                    drmInitData4 = null;
                } else {
                    drmInitData4 = drmInitData4.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData2.trackEncryptionBoxes[i12] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        String str3 = "video/3gpp";
        String str4 = i29 == 1831958048 ? Encode.ContentType.VIDEO_MPEG : i29 == 1211250227 ? str3 : null;
        float f = 1.0f;
        int i30 = 8;
        int i31 = 8;
        ByteBuffer byteBuffer = null;
        List<byte[]> list = null;
        String str5 = null;
        byte[] bArr = null;
        int i32 = -1;
        int i33 = -1;
        int i34 = -1;
        int i35 = -1;
        int i36 = -1;
        int i37 = -1;
        int i38 = -1;
        int i39 = -1;
        BtrtData btrtData = null;
        EsdsData esdsData = null;
        NalUnitUtil.H265VpsData h265VpsData4 = null;
        boolean z = false;
        while (position - i27 < i28) {
            parsableByteArray2.setPosition(position);
            int position2 = parsableByteArray2.getPosition();
            int readInt = parsableByteArray2.readInt();
            if (readInt == 0 && parsableByteArray2.getPosition() - i7 == i28) {
                break;
            }
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            int readInt2 = parsableByteArray2.readInt();
            if (readInt2 == 1635148611) {
                ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                parsableByteArray2.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray2);
                List<byte[]> list2 = parse.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse.pixelWidthHeightRatio;
                }
                String str6 = parse.codecs;
                int i40 = parse.maxNumReorderFrames;
                int i41 = parse.colorSpace;
                int i42 = parse.colorRange;
                list = list2;
                int i43 = parse.colorTransfer;
                int i44 = parse.bitdepthLuma;
                NalUnitUtil.H265VpsData h265VpsData5 = h265VpsData4;
                drmInitData2 = drmInitData4;
                h265VpsData = h265VpsData5;
                i17 = parse.bitdepthChroma;
                i15 = position;
                i16 = i29;
                str2 = str3;
                i14 = i41;
                i13 = i42;
                i39 = i43;
                i30 = i44;
                str5 = str6;
                str4 = Encode.CodecsMime.VIDEO_CODEC_H264;
                i33 = i40;
            } else {
                i15 = position;
                if (readInt2 == 1752589123) {
                    ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                    parsableByteArray2.setPosition(position2 + 8);
                    HevcConfig parse2 = HevcConfig.parse(parsableByteArray2);
                    List<byte[]> list3 = parse2.initializationData;
                    stsdData2.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                    if (!z) {
                        f = parse2.pixelWidthHeightRatio;
                    }
                    int i45 = parse2.maxNumReorderPics;
                    int i46 = parse2.maxSubLayers;
                    String str7 = parse2.codecs;
                    int i47 = parse2.stereoMode;
                    B = list3;
                    if (i47 != -1) {
                        i32 = i47;
                    }
                    int i48 = parse2.decodedWidth;
                    int i49 = parse2.decodedHeight;
                    int i50 = parse2.colorSpace;
                    int i51 = parse2.colorRange;
                    int i52 = parse2.colorTransfer;
                    i36 = i49;
                    i30 = parse2.bitdepthLuma;
                    i17 = parse2.bitdepthChroma;
                    drmInitData2 = drmInitData4;
                    i16 = i29;
                    str2 = str3;
                    i14 = i50;
                    i13 = i51;
                    i21 = i52;
                    h265VpsData2 = parse2.vpsData;
                    i33 = i45;
                    i34 = i46;
                    i35 = i48;
                    str4 = "video/hevc";
                    str5 = str7;
                } else {
                    str2 = str3;
                    if (readInt2 == 1818785347) {
                        ExtractorUtil.checkContainerInput("video/hevc".equals(str4), "lhvC must follow hvcC atom");
                        NalUnitUtil.H265VpsData h265VpsData6 = h265VpsData4;
                        ExtractorUtil.checkContainerInput(h265VpsData6 != null && h265VpsData6.layerInfos.size() >= 2, "must have at least two layers");
                        parsableByteArray2.setPosition(position2 + 8);
                        HevcConfig parseLayered = HevcConfig.parseLayered(parsableByteArray2, (NalUnitUtil.H265VpsData) Assertions.checkNotNull(h265VpsData6));
                        ExtractorUtil.checkContainerInput(stsdData2.nalUnitLengthFieldLength == parseLayered.nalUnitLengthFieldLength, "nalUnitLengthFieldLength must be same for both hvcC and lhvC atoms");
                        int i53 = parseLayered.colorSpace;
                        int i54 = i37;
                        if (i53 != -1) {
                            ExtractorUtil.checkContainerInput(i54 == i53, "colorSpace must be the same for both views");
                        }
                        int i55 = parseLayered.colorRange;
                        int i56 = i38;
                        if (i55 != -1) {
                            ExtractorUtil.checkContainerInput(i56 == i55, "colorRange must be the same for both views");
                        }
                        int i57 = parseLayered.colorTransfer;
                        if (i57 != -1) {
                            int i58 = i39;
                            i25 = i58;
                            ExtractorUtil.checkContainerInput(i58 == i57, "colorTransfer must be the same for both views");
                        } else {
                            i25 = i39;
                        }
                        ExtractorUtil.checkContainerInput(i30 == parseLayered.bitdepthLuma, "bitdepthLuma must be the same for both views");
                        ExtractorUtil.checkContainerInput(i31 == parseLayered.bitdepthChroma, "bitdepthChroma must be the same for both views");
                        y0 y0Var3 = list;
                        if (y0Var3 != null) {
                            Q x9 = U.x();
                            x9.c(y0Var3);
                            x9.c(parseLayered.initializationData);
                            y0Var3 = x9.f();
                            i26 = i54;
                        } else {
                            i26 = i54;
                            ExtractorUtil.checkContainerInput(false, "initializationData must be already set from hvcC atom");
                        }
                        str4 = "video/mv-hevc";
                        drmInitData2 = drmInitData4;
                        i16 = i29;
                        i13 = i56;
                        i17 = i31;
                        i14 = i26;
                        i39 = i25;
                        str5 = parseLayered.codecs;
                        h265VpsData = h265VpsData6;
                        list = y0Var3;
                    } else {
                        y0 y0Var4 = list;
                        i14 = i37;
                        i13 = i38;
                        int i59 = i39;
                        NalUnitUtil.H265VpsData h265VpsData7 = h265VpsData4;
                        if (readInt2 == 1986361461) {
                            VexuData parseVideoExtendedUsageBox = parseVideoExtendedUsageBox(parsableByteArray2, position2, readInt);
                            if (!(parseVideoExtendedUsageBox == null || parseVideoExtendedUsageBox.eyesData == null)) {
                                if (h265VpsData7 == null || h265VpsData7.layerInfos.size() < 2) {
                                    i24 = i32;
                                    if (i24 == -1) {
                                        i32 = parseVideoExtendedUsageBox.eyesData.striData.eyeViewsReversed ? 5 : 4;
                                        drmInitData2 = drmInitData4;
                                        list = y0Var4;
                                        i16 = i29;
                                        i17 = i31;
                                        i39 = i59;
                                        h265VpsData = h265VpsData7;
                                    }
                                    i32 = i24;
                                    drmInitData2 = drmInitData4;
                                    list = y0Var4;
                                    i16 = i29;
                                    i17 = i31;
                                    i39 = i59;
                                    h265VpsData = h265VpsData7;
                                } else {
                                    ExtractorUtil.checkContainerInput(parseVideoExtendedUsageBox.hasBothEyeViews(), "both eye views must be marked as available");
                                    ExtractorUtil.checkContainerInput(!parseVideoExtendedUsageBox.eyesData.striData.eyeViewsReversed, "for MV-HEVC, eye_views_reversed must be set to false");
                                }
                            }
                            i24 = i32;
                            i32 = i24;
                            drmInitData2 = drmInitData4;
                            list = y0Var4;
                            i16 = i29;
                            i17 = i31;
                            i39 = i59;
                            h265VpsData = h265VpsData7;
                        } else {
                            int i60 = i32;
                            NalUnitUtil.H265VpsData h265VpsData8 = h265VpsData7;
                            if (readInt2 == 1685480259 || readInt2 == 1685485123 || readInt2 == 1685485379) {
                                drmInitData2 = drmInitData4;
                                i16 = i29;
                                i18 = i60;
                                i17 = i31;
                                float f5 = f;
                                int i61 = i30;
                                int i62 = i14;
                                i19 = i59;
                                int i63 = readInt - 8;
                                byte[] bArr2 = new byte[i63];
                                parsableByteArray2.readBytes(bArr2, 0, i63);
                                if (y0Var4 != null) {
                                    Q x10 = U.x();
                                    x10.c(y0Var4);
                                    x10.a(bArr2);
                                    y0Var = x10.f();
                                } else {
                                    ExtractorUtil.checkContainerInput(false, "initializationData must already be set from hvcC or avcC atom");
                                    y0Var = y0Var4;
                                }
                                parsableByteArray2.setPosition(position2 + 8);
                                DolbyVisionConfig parse3 = DolbyVisionConfig.parse(parsableByteArray2);
                                if (parse3 != null) {
                                    str4 = "video/dolby-vision";
                                    str5 = parse3.codecs;
                                }
                                i14 = i62;
                                i20 = i61;
                                f = f5;
                            } else if (readInt2 == 1987076931) {
                                ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                                String str8 = i29 == 1987063864 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                                parsableByteArray2.setPosition(position2 + 12);
                                byte readUnsignedByte = (byte) parsableByteArray2.readUnsignedByte();
                                byte readUnsignedByte2 = (byte) parsableByteArray2.readUnsignedByte();
                                int readUnsignedByte3 = parsableByteArray2.readUnsignedByte();
                                i30 = readUnsignedByte3 >> 4;
                                i16 = i29;
                                byte b = (byte) ((readUnsignedByte3 >> 1) & 7);
                                if (str8.equals("video/x-vnd.on2.vp9")) {
                                    y0Var4 = CodecSpecificDataUtil.buildVp9CodecPrivateInitializationData(readUnsignedByte, readUnsignedByte2, (byte) i30, b);
                                }
                                boolean z3 = (readUnsignedByte3 & 1) != 0;
                                int readUnsignedByte4 = parsableByteArray2.readUnsignedByte();
                                int readUnsignedByte5 = parsableByteArray2.readUnsignedByte();
                                int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedByte4);
                                int i64 = z3 ? 1 : 2;
                                i39 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedByte5);
                                str4 = str8;
                                drmInitData2 = drmInitData4;
                                i13 = i64;
                                h265VpsData = h265VpsData8;
                                i14 = isoColorPrimariesToColorSpace;
                                list = y0Var4;
                                i32 = i60;
                                i17 = i30;
                            } else {
                                i16 = i29;
                                if (readInt2 == 1635135811) {
                                    int i65 = readInt - 8;
                                    byte[] bArr3 = new byte[i65];
                                    parsableByteArray2.readBytes(bArr3, 0, i65);
                                    y0Var2 = U.B(bArr3);
                                    parsableByteArray2.setPosition(position2 + 8);
                                    ColorInfo parseAv1c = parseAv1c(parsableByteArray2);
                                    int i66 = parseAv1c.lumaBitdepth;
                                    int i67 = parseAv1c.chromaBitdepth;
                                    int i68 = parseAv1c.colorSpace;
                                    int i69 = parseAv1c.colorRange;
                                    i23 = parseAv1c.colorTransfer;
                                    i30 = i66;
                                    drmInitData3 = drmInitData4;
                                    i22 = i67;
                                    i14 = i68;
                                    i13 = i69;
                                    str4 = "video/av01";
                                    h265VpsData3 = h265VpsData8;
                                } else if (readInt2 == 1668050025) {
                                    if (byteBuffer == null) {
                                        byteBuffer = allocateHdrStaticInfo();
                                    }
                                    ByteBuffer byteBuffer2 = byteBuffer;
                                    byteBuffer2.position(21);
                                    byteBuffer2.putShort(parsableByteArray2.readShort());
                                    byteBuffer2.putShort(parsableByteArray2.readShort());
                                    byteBuffer = byteBuffer2;
                                    drmInitData3 = drmInitData4;
                                    y0Var2 = y0Var4;
                                    i22 = i31;
                                    h265VpsData3 = h265VpsData8;
                                    i23 = i59;
                                } else if (readInt2 == 1835295606) {
                                    if (byteBuffer == null) {
                                        byteBuffer = allocateHdrStaticInfo();
                                    }
                                    ByteBuffer byteBuffer3 = byteBuffer;
                                    short readShort = parsableByteArray2.readShort();
                                    short readShort2 = parsableByteArray2.readShort();
                                    short readShort3 = parsableByteArray2.readShort();
                                    short readShort4 = parsableByteArray2.readShort();
                                    i17 = i31;
                                    short readShort5 = parsableByteArray2.readShort();
                                    int i70 = i30;
                                    short readShort6 = parsableByteArray2.readShort();
                                    drmInitData2 = drmInitData4;
                                    short readShort7 = parsableByteArray2.readShort();
                                    int i71 = i60;
                                    short readShort8 = parsableByteArray2.readShort();
                                    long readUnsignedInt = parsableByteArray2.readUnsignedInt();
                                    long readUnsignedInt2 = parsableByteArray2.readUnsignedInt();
                                    byteBuffer3.position(1);
                                    byteBuffer3.putShort(readShort5);
                                    byteBuffer3.putShort(readShort6);
                                    byteBuffer3.putShort(readShort);
                                    byteBuffer3.putShort(readShort2);
                                    byteBuffer3.putShort(readShort3);
                                    byteBuffer3.putShort(readShort4);
                                    byteBuffer3.putShort(readShort7);
                                    byteBuffer3.putShort(readShort8);
                                    byteBuffer3.putShort((short) ((int) (readUnsignedInt / 10000)));
                                    byteBuffer3.putShort((short) ((int) (readUnsignedInt2 / 10000)));
                                    byteBuffer = byteBuffer3;
                                    B = y0Var4;
                                    h265VpsData2 = h265VpsData8;
                                    i30 = i70;
                                    i21 = i59;
                                    i32 = i71;
                                    f = f;
                                } else {
                                    drmInitData2 = drmInitData4;
                                    i18 = i60;
                                    i17 = i31;
                                    float f8 = f;
                                    int i72 = i30;
                                    if (readInt2 == 1681012275) {
                                        ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                                        B = y0Var4;
                                        h265VpsData2 = h265VpsData8;
                                        i30 = i72;
                                        str4 = str2;
                                    } else {
                                        if (readInt2 == 1702061171) {
                                            ExtractorUtil.checkContainerInput(str4 == null, (String) null);
                                            esdsData = parseEsdsFromParent(parsableByteArray2, position2);
                                            String access$1300 = esdsData.mimeType;
                                            byte[] access$700 = esdsData.initializationData;
                                            B = access$700 != null ? U.B(access$700) : y0Var4;
                                            str4 = access$1300;
                                        } else {
                                            if (readInt2 == 1651798644) {
                                                btrtData = parseBtrtFromParent(parsableByteArray2, position2);
                                            } else if (readInt2 == 1885434736) {
                                                f = parsePaspFromParent(parsableByteArray2, position2);
                                                list = y0Var4;
                                                h265VpsData = h265VpsData8;
                                                i30 = i72;
                                                i39 = i59;
                                                i32 = i18;
                                                z = true;
                                            } else if (readInt2 == 1937126244) {
                                                bArr = parseProjFromParent(parsableByteArray2, position2, readInt);
                                            } else if (readInt2 == 1936995172) {
                                                int readUnsignedByte6 = parsableByteArray2.readUnsignedByte();
                                                parsableByteArray2.skipBytes(3);
                                                if (readUnsignedByte6 == 0) {
                                                    int readUnsignedByte7 = parsableByteArray2.readUnsignedByte();
                                                    if (readUnsignedByte7 == 0) {
                                                        i18 = 0;
                                                    } else if (readUnsignedByte7 == 1) {
                                                        i18 = 1;
                                                    } else if (readUnsignedByte7 == 2) {
                                                        i18 = 2;
                                                    } else if (readUnsignedByte7 == 3) {
                                                        i18 = 3;
                                                    }
                                                }
                                            } else if (readInt2 == 1634760259) {
                                                int i73 = readInt - 12;
                                                byte[] bArr4 = new byte[i73];
                                                parsableByteArray2.setPosition(position2 + 12);
                                                parsableByteArray2.readBytes(bArr4, 0, i73);
                                                B = U.B(bArr4);
                                                ColorInfo parseApvc = parseApvc(new ParsableByteArray(bArr4));
                                                int i74 = parseApvc.lumaBitdepth;
                                                int i75 = parseApvc.chromaBitdepth;
                                                int i76 = parseApvc.colorSpace;
                                                int i77 = parseApvc.colorRange;
                                                i21 = parseApvc.colorTransfer;
                                                i30 = i74;
                                                i17 = i75;
                                                i14 = i76;
                                                i13 = i77;
                                                str4 = "video/apv";
                                                h265VpsData2 = h265VpsData8;
                                                i32 = i18;
                                                f = f8;
                                            } else {
                                                int i78 = i14;
                                                if (readInt2 == 1668246642) {
                                                    i19 = i59;
                                                    if (i78 == -1 && i19 == -1) {
                                                        int readInt3 = parsableByteArray2.readInt();
                                                        if (readInt3 == 1852009592 || readInt3 == 1852009571) {
                                                            int readUnsignedShort3 = parsableByteArray2.readUnsignedShort();
                                                            int readUnsignedShort4 = parsableByteArray2.readUnsignedShort();
                                                            int i79 = 2;
                                                            parsableByteArray2.skipBytes(2);
                                                            boolean z7 = readInt == 19 && (parsableByteArray2.readUnsignedByte() & 128) != 0;
                                                            int isoColorPrimariesToColorSpace2 = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedShort3);
                                                            if (z7) {
                                                                i79 = 1;
                                                            }
                                                            i14 = isoColorPrimariesToColorSpace2;
                                                            list = y0Var4;
                                                            i13 = i79;
                                                            h265VpsData = h265VpsData8;
                                                            i30 = i72;
                                                            i32 = i18;
                                                            f = f8;
                                                            i39 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedShort4);
                                                        } else {
                                                            Log.w("BoxParsers", "Unsupported color type: " + Mp4Box.getBoxTypeString(readInt3));
                                                        }
                                                    }
                                                } else {
                                                    i19 = i59;
                                                }
                                                y0Var = y0Var4;
                                                i14 = i78;
                                                i20 = i72;
                                                f = f8;
                                            }
                                            B = y0Var4;
                                        }
                                        h265VpsData2 = h265VpsData8;
                                        i30 = i72;
                                    }
                                    i21 = i59;
                                    i32 = i18;
                                    f = f8;
                                }
                                i32 = i60;
                            }
                            i39 = i19;
                            h265VpsData = h265VpsData8;
                            i32 = i18;
                        }
                    }
                }
            }
            position = i15 + readInt;
            DrmInitData drmInitData5 = drmInitData2;
            h265VpsData4 = h265VpsData;
            drmInitData4 = drmInitData5;
            i27 = i7;
            i28 = i8;
            stsdData2 = stsdData;
            i31 = i17;
            i29 = i16;
            str3 = str2;
            i37 = i14;
            i38 = i13;
        }
        DrmInitData drmInitData6 = drmInitData4;
        float f10 = f;
        y0 y0Var5 = list;
        int i80 = i32;
        int i81 = i37;
        int i82 = i38;
        int i83 = i39;
        int i84 = i31;
        int i85 = i30;
        if (str4 != null) {
            Format.Builder colorInfo = new Format.Builder().setId(i10).setSampleMimeType(str4).setCodecs(str5).setWidth(readUnsignedShort).setHeight(readUnsignedShort2).setDecodedWidth(i35).setDecodedHeight(i36).setPixelWidthHeightRatio(f10).setRotationDegrees(i11).setProjectionData(bArr).setStereoMode(i80).setInitializationData(y0Var5).setMaxNumReorderSamples(i33).setMaxSubLayers(i34).setDrmInitData(drmInitData6).setLanguage(str).setColorInfo(new ColorInfo.Builder().setColorSpace(i81).setColorRange(i82).setColorTransfer(i83).setHdrStaticInfo(byteBuffer != null ? byteBuffer.array() : null).setLumaBitdepth(i85).setChromaBitdepth(i84).build());
            if (btrtData != null) {
                colorInfo.setAverageBitrate(C0246a.j0(btrtData.avgBitrate)).setPeakBitrate(C0246a.j0(btrtData.maxBitrate));
            } else if (esdsData != null) {
                colorInfo.setAverageBitrate(C0246a.j0(esdsData.bitrate)).setPeakBitrate(C0246a.j0(esdsData.peakBitrate));
            }
            stsdData.format = colorInfo.build();
        }
    }

    private static Metadata parseXyz(ParsableByteArray parsableByteArray) {
        short readShort = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        String readString = parsableByteArray.readString(readShort);
        int max = Math.max(readString.lastIndexOf(43), readString.lastIndexOf(45));
        try {
            return new Metadata(new Mp4LocationData(Float.parseFloat(readString.substring(0, max)), Float.parseFloat(readString.substring(max, readString.length() - 1))));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    private static int vobsubYuvToRgb(int i2) {
        int i7 = (i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER;
        int i8 = ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER) - 128;
        int i10 = (i2 & ScoverState.TYPE_NFC_SMART_COVER) - 128;
        return Util.constrainValue(((i10 * 17790) / 10000) + i7, 0, (int) ScoverState.TYPE_NFC_SMART_COVER) | (Util.constrainValue(((i8 * 14075) / 10000) + i7, 0, (int) ScoverState.TYPE_NFC_SMART_COVER) << 16) | (Util.constrainValue((i7 - ((i10 * 3455) / 10000)) - ((i8 * 7169) / 10000), 0, (int) ScoverState.TYPE_NFC_SMART_COVER) << 8);
    }
}
