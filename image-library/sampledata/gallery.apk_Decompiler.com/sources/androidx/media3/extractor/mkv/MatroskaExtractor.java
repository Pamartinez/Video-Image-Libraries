package androidx.media3.extractor.mkv;

import N2.j;
import P.a;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MatroskaExtractor implements Extractor {
    @Deprecated
    public static final ExtractorsFactory FACTORY = new a(4);
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    /* access modifiers changed from: private */
    public static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private boolean isWebm;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final boolean parseSubtitlesDuringExtraction;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        public void binaryElement(int i2, int i7, ExtractorInput extractorInput) {
            MatroskaExtractor.this.binaryElement(i2, i7, extractorInput);
        }

        public void endMasterElement(int i2) {
            MatroskaExtractor.this.endMasterElement(i2);
        }

        public void floatElement(int i2, double d) {
            MatroskaExtractor.this.floatElement(i2, d);
        }

        public int getElementType(int i2) {
            return MatroskaExtractor.this.getElementType(i2);
        }

        public void integerElement(int i2, long j2) {
            MatroskaExtractor.this.integerElement(i2, j2);
        }

        public boolean isLevel1Element(int i2) {
            return MatroskaExtractor.this.isLevel1Element(i2);
        }

        public void startMasterElement(int i2, long j2, long j3) {
            MatroskaExtractor.this.startMasterElement(i2, j2, j3);
        }

        public void stringElement(int i2, String str) {
            MatroskaExtractor.this.stringElement(i2, str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Track {
        public int audioBitDepth = -1;
        public int bitsPerChannel = -1;
        /* access modifiers changed from: private */
        public int blockAddIdType;
        public int channelCount = 1;
        public long codecDelayNs = 0;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange = -1;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int displayWidth = -1;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagDefault = true;
        public boolean flagForced;
        public boolean hasColorInfo = false;
        public boolean hasContentEncryption;
        public int height = -1;
        public boolean isWebm;
        /* access modifiers changed from: private */
        public String language = "eng";
        public int maxBlockAdditionId;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public byte[] projectionData = null;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public float projectionPoseYaw = 0.0f;
        public int projectionType = -1;
        public int sampleRate = Encode.BitRate.VIDEO_HD_BITRATE;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs = 0;
        public int stereoMode = -1;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public int width = -1;

        /* access modifiers changed from: private */
        public void assertOutputInitialized() {
            Assertions.checkNotNull(this.output);
        }

        private byte[] getCodecPrivate(String str) {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, (Throwable) null);
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            order.put((byte) 0);
            order.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            order.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            order.putShort((short) this.maxContentLuminance);
            order.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(Encode.ContentType.VIDEO_DIVX, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>("video/3gpp", (Object) null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] data = parsableByteArray.getData();
                    for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                        if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                            return new Pair<>("video/wvc1", Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                        }
                    }
                    throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", (Throwable) null);
                }
                Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>("video/x-unknown", (Object) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", (Throwable) null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort == 65534) {
                    parsableByteArray.setPosition(24);
                    if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                        return true;
                    }
                    return false;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", (Throwable) null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) {
            byte b;
            byte b5;
            try {
                if (bArr[0] == 2) {
                    int i2 = 0;
                    int i7 = 1;
                    while (true) {
                        b = bArr[i7];
                        if ((b & 255) != 255) {
                            break;
                        }
                        i2 += ScoverState.TYPE_NFC_SMART_COVER;
                        i7++;
                    }
                    int i8 = i7 + 1;
                    int i10 = i2 + (b & 255);
                    int i11 = 0;
                    while (true) {
                        b5 = bArr[i8];
                        if ((b5 & 255) != 255) {
                            break;
                        }
                        i11 += ScoverState.TYPE_NFC_SMART_COVER;
                        i8++;
                    }
                    int i12 = i8 + 1;
                    int i13 = i11 + (b5 & 255);
                    if (bArr[i12] == 1) {
                        byte[] bArr2 = new byte[i10];
                        System.arraycopy(bArr, i12, bArr2, 0, i10);
                        int i14 = i12 + i10;
                        if (bArr[i14] == 3) {
                            int i15 = i14 + i13;
                            if (bArr[i15] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i15)];
                                System.arraycopy(bArr, i15, bArr3, 0, bArr.length - i15);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                        }
                        throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                    }
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                }
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            }
        }

        /* access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z) {
            if ("A_OPUS".equals(this.codecId)) {
                return z;
            }
            if (this.maxBlockAdditionId > 0) {
                return true;
            }
            return false;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v12, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v156, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v159, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v161, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v162, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v163, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v165, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v167, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v170, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v171, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v172, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v174, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v175, resolved type: F2.y0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v176, resolved type: F2.y0} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x0238, code lost:
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:0x0239, code lost:
            r9 = -1;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x0248, code lost:
            r3 = null;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:114:0x0249, code lost:
            r4 = -1;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x024d, code lost:
            r1 = null;
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x0274, code lost:
            r4 = r3;
            r3 = r1;
            r1 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:126:0x02a7, code lost:
            r1 = null;
            r3 = null;
            r17 = "audio/x-unknown";
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:0x02ac, code lost:
            r9 = r1;
            r1 = null;
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x02af, code lost:
            r4 = -1;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:163:0x039a, code lost:
            r4 = -1;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:165:0x03b7, code lost:
            r3 = null;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x03bc, code lost:
            r1 = null;
            r3 = null;
            r4 = 4096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x0438, code lost:
            if (r0.dolbyVisionConfigBytes == null) goto L_0x044c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x043a, code lost:
            r11 = androidx.media3.container.DolbyVisionConfig.parse(new androidx.media3.common.util.ParsableByteArray(r0.dolbyVisionConfigBytes));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0445, code lost:
            if (r11 == null) goto L_0x044c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x0447, code lost:
            r3 = r11.codecs;
            r17 = "video/dolby-vision";
         */
        /* JADX WARNING: Code restructure failed: missing block: B:189:0x044c, code lost:
            r11 = r17;
            r12 = r0.flagDefault;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x0452, code lost:
            if (r0.flagForced == false) goto L_0x0456;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x0454, code lost:
            r7 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x0456, code lost:
            r7 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:193:0x0457, code lost:
            r7 = r7 | r12;
            r12 = new androidx.media3.common.Format.Builder();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:194:0x0461, code lost:
            if (androidx.media3.common.MimeTypes.isAudio(r11) == false) goto L_0x0475;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:195:0x0463, code lost:
            r12.setChannelCount(r0.channelCount).setSampleRate(r0.sampleRate).setPcmEncoding(r9);
            r5 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:197:0x0479, code lost:
            if (androidx.media3.common.MimeTypes.isVideo(r11) == false) goto L_0x056e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:199:0x047d, code lost:
            if (r0.displayUnit != 0) goto L_0x0491;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:200:0x047f, code lost:
            r2 = r0.displayWidth;
            r5 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:201:0x0482, code lost:
            if (r2 != -1) goto L_0x0486;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:202:0x0484, code lost:
            r2 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:203:0x0486, code lost:
            r0.displayWidth = r2;
            r2 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x048a, code lost:
            if (r2 != -1) goto L_0x048e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:205:0x048c, code lost:
            r2 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:206:0x048e, code lost:
            r0.displayHeight = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:207:0x0491, code lost:
            r5 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:208:0x0492, code lost:
            r2 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:209:0x0494, code lost:
            if (r2 == r5) goto L_0x04a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:210:0x0496, code lost:
            r6 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:211:0x0498, code lost:
            if (r6 == r5) goto L_0x04a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:212:0x049a, code lost:
            r2 = ((float) (r0.height * r2)) / ((float) (r0.width * r6));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x04a4, code lost:
            r2 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:215:0x04a8, code lost:
            if (r0.hasColorInfo == false) goto L_0x04d9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:216:0x04aa, code lost:
            r8 = new androidx.media3.common.ColorInfo.Builder().setColorSpace(r0.colorSpace).setColorRange(r0.colorRange).setColorTransfer(r0.colorTransfer).setHdrStaticInfo(getHdrStaticInfo()).setLumaBitdepth(r0.bitsPerChannel).setChromaBitdepth(r0.bitsPerChannel).build();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:218:0x04db, code lost:
            if (r0.name == null) goto L_0x04f9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:220:0x04e7, code lost:
            if (androidx.media3.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) == false) goto L_0x04f9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:221:0x04e9, code lost:
            r5 = ((java.lang.Integer) androidx.media3.extractor.mkv.MatroskaExtractor.access$600().get(r0.name)).intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:223:0x04fb, code lost:
            if (r0.projectionType != 0) goto L_0x0549;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:225:0x0504, code lost:
            if (java.lang.Float.compare(r0.projectionPoseYaw, 0.0f) != 0) goto L_0x0549;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:227:0x050c, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 0.0f) != 0) goto L_0x0549;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:229:0x0514, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 0.0f) != 0) goto L_0x0518;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:230:0x0516, code lost:
            r5 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:232:0x0520, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 90.0f) != 0) goto L_0x0525;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:233:0x0522, code lost:
            r5 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:235:0x052d, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, -180.0f) == 0) goto L_0x0547;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:237:0x0537, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 180.0f) != 0) goto L_0x053a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:239:0x0542, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, -90.0f) != 0) goto L_0x0549;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:240:0x0544, code lost:
            r5 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:241:0x0547, code lost:
            r5 = com.samsung.android.ocr.MOCRLang.KHMER;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:242:0x0549, code lost:
            r12.setWidth(r0.width).setHeight(r0.height).setPixelWidthHeightRatio(r2).setRotationDegrees(r5).setProjectionData(r0.projectionData).setStereoMode(r0.stereoMode).setColorInfo(r8);
            r5 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:244:0x0572, code lost:
            if ("application/x-subrip".equals(r11) != false) goto L_0x059a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:246:0x0578, code lost:
            if ("text/x-ssa".equals(r11) != false) goto L_0x059a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:248:0x057e, code lost:
            if ("text/vtt".equals(r11) != false) goto L_0x059a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:250:0x0584, code lost:
            if ("application/vobsub".equals(r11) != false) goto L_0x059a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:252:0x058a, code lost:
            if ("application/pgs".equals(r11) != false) goto L_0x059a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:254:0x0590, code lost:
            if ("application/dvbsubs".equals(r11) == false) goto L_0x0593;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:256:0x0599, code lost:
            throw androidx.media3.common.ParserException.createForMalformedContainer("Unexpected MIME type.", (java.lang.Throwable) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:257:0x059a, code lost:
            r5 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:259:0x059d, code lost:
            if (r0.name == null) goto L_0x05b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:261:0x05a9, code lost:
            if (androidx.media3.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) != false) goto L_0x05b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:262:0x05ab, code lost:
            r12.setLabel(r0.name);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:263:0x05b0, code lost:
            r2 = r12.setId(r21);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:264:0x05b8, code lost:
            if (r0.isWebm == false) goto L_0x05be;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:265:0x05ba, code lost:
            r6 = com.samsung.android.sdk.sgpl.pip.core.Encode.ContentType.VIDEO_WEBM;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:266:0x05be, code lost:
            r6 = com.samsung.android.sdk.sgpl.pip.core.Encode.ContentType.VIDEO_MKV;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:267:0x05c1, code lost:
            r1 = r2.setContainerMimeType(r6).setSampleMimeType(r11).setMaxInputSize(r4).setLanguage(r0.language).setSelectionFlags(r7).setInitializationData(r1).setCodecs(r3).setDrmInitData(r0.drmInitData).build();
            r2 = r20.track(r0.number, r5);
            r0.output = r2;
            r2.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:268:0x05f6, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(androidx.media3.extractor.ExtractorOutput r20, int r21) {
            /*
                r19 = this;
                r0 = r19
                java.lang.String r1 = r0.codecId
                r1.getClass()
                int r2 = r1.hashCode()
                r3 = 24
                r4 = 16
                r7 = 32
                r9 = 8
                r10 = 3
                switch(r2) {
                    case -2095576542: goto L_0x01cc;
                    case -2095575984: goto L_0x01c0;
                    case -1985379776: goto L_0x01b4;
                    case -1784763192: goto L_0x01a8;
                    case -1730367663: goto L_0x019c;
                    case -1482641358: goto L_0x0190;
                    case -1482641357: goto L_0x0184;
                    case -1373388978: goto L_0x0178;
                    case -933872740: goto L_0x016b;
                    case -538363189: goto L_0x015d;
                    case -538363109: goto L_0x014f;
                    case -425012669: goto L_0x0141;
                    case -356037306: goto L_0x0133;
                    case 62923557: goto L_0x0125;
                    case 62923603: goto L_0x0117;
                    case 62927045: goto L_0x0109;
                    case 82318131: goto L_0x00fc;
                    case 82338133: goto L_0x00ee;
                    case 82338134: goto L_0x00e0;
                    case 99146302: goto L_0x00d2;
                    case 444813526: goto L_0x00c4;
                    case 542569478: goto L_0x00b6;
                    case 635596514: goto L_0x00a8;
                    case 725948237: goto L_0x009a;
                    case 725957860: goto L_0x008e;
                    case 738597099: goto L_0x0081;
                    case 738614379: goto L_0x0074;
                    case 855502857: goto L_0x0067;
                    case 1045209816: goto L_0x005a;
                    case 1422270023: goto L_0x004d;
                    case 1809237540: goto L_0x0040;
                    case 1950749482: goto L_0x0033;
                    case 1950789798: goto L_0x0027;
                    case 1951062397: goto L_0x001a;
                    default: goto L_0x0017;
                }
            L_0x0017:
                r1 = -1
                goto L_0x01d7
            L_0x001a:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0023
                goto L_0x0017
            L_0x0023:
                r1 = 33
                goto L_0x01d7
            L_0x0027:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0030
                goto L_0x0017
            L_0x0030:
                r1 = r7
                goto L_0x01d7
            L_0x0033:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x003c
                goto L_0x0017
            L_0x003c:
                r1 = 31
                goto L_0x01d7
            L_0x0040:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0049
                goto L_0x0017
            L_0x0049:
                r1 = 30
                goto L_0x01d7
            L_0x004d:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0056
                goto L_0x0017
            L_0x0056:
                r1 = 29
                goto L_0x01d7
            L_0x005a:
                java.lang.String r2 = "S_TEXT/WEBVTT"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0063
                goto L_0x0017
            L_0x0063:
                r1 = 28
                goto L_0x01d7
            L_0x0067:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0070
                goto L_0x0017
            L_0x0070:
                r1 = 27
                goto L_0x01d7
            L_0x0074:
                java.lang.String r2 = "S_TEXT/SSA"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x007d
                goto L_0x0017
            L_0x007d:
                r1 = 26
                goto L_0x01d7
            L_0x0081:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x008a
                goto L_0x0017
            L_0x008a:
                r1 = 25
                goto L_0x01d7
            L_0x008e:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0097
                goto L_0x0017
            L_0x0097:
                r1 = r3
                goto L_0x01d7
            L_0x009a:
                java.lang.String r2 = "A_PCM/INT/BIG"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00a4
                goto L_0x0017
            L_0x00a4:
                r1 = 23
                goto L_0x01d7
            L_0x00a8:
                java.lang.String r2 = "A_PCM/FLOAT/IEEE"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00b2
                goto L_0x0017
            L_0x00b2:
                r1 = 22
                goto L_0x01d7
            L_0x00b6:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00c0
                goto L_0x0017
            L_0x00c0:
                r1 = 21
                goto L_0x01d7
            L_0x00c4:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00ce
                goto L_0x0017
            L_0x00ce:
                r1 = 20
                goto L_0x01d7
            L_0x00d2:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00dc
                goto L_0x0017
            L_0x00dc:
                r1 = 19
                goto L_0x01d7
            L_0x00e0:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00ea
                goto L_0x0017
            L_0x00ea:
                r1 = 18
                goto L_0x01d7
            L_0x00ee:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00f8
                goto L_0x0017
            L_0x00f8:
                r1 = 17
                goto L_0x01d7
            L_0x00fc:
                java.lang.String r2 = "V_AV1"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0106
                goto L_0x0017
            L_0x0106:
                r1 = r4
                goto L_0x01d7
            L_0x0109:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0113
                goto L_0x0017
            L_0x0113:
                r1 = 15
                goto L_0x01d7
            L_0x0117:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0121
                goto L_0x0017
            L_0x0121:
                r1 = 14
                goto L_0x01d7
            L_0x0125:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x012f
                goto L_0x0017
            L_0x012f:
                r1 = 13
                goto L_0x01d7
            L_0x0133:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x013d
                goto L_0x0017
            L_0x013d:
                r1 = 12
                goto L_0x01d7
            L_0x0141:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x014b
                goto L_0x0017
            L_0x014b:
                r1 = 11
                goto L_0x01d7
            L_0x014f:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0159
                goto L_0x0017
            L_0x0159:
                r1 = 10
                goto L_0x01d7
            L_0x015d:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0167
                goto L_0x0017
            L_0x0167:
                r1 = 9
                goto L_0x01d7
            L_0x016b:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0175
                goto L_0x0017
            L_0x0175:
                r1 = r9
                goto L_0x01d7
            L_0x0178:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0182
                goto L_0x0017
            L_0x0182:
                r1 = 7
                goto L_0x01d7
            L_0x0184:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x018e
                goto L_0x0017
            L_0x018e:
                r1 = 6
                goto L_0x01d7
            L_0x0190:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x019a
                goto L_0x0017
            L_0x019a:
                r1 = 5
                goto L_0x01d7
            L_0x019c:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01a6
                goto L_0x0017
            L_0x01a6:
                r1 = 4
                goto L_0x01d7
            L_0x01a8:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01b2
                goto L_0x0017
            L_0x01b2:
                r1 = r10
                goto L_0x01d7
            L_0x01b4:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01be
                goto L_0x0017
            L_0x01be:
                r1 = 2
                goto L_0x01d7
            L_0x01c0:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01ca
                goto L_0x0017
            L_0x01ca:
                r1 = 1
                goto L_0x01d7
            L_0x01cc:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01d6
                goto L_0x0017
            L_0x01d6:
                r1 = 0
            L_0x01d7:
                java.lang.String r13 = "application/dvbsubs"
                java.lang.String r14 = "application/vobsub"
                java.lang.String r15 = "application/pgs"
                java.lang.String r2 = "text/x-ssa"
                java.lang.String r5 = "text/vtt"
                java.lang.String r6 = "application/x-subrip"
                java.lang.String r12 = ". Setting mimeType to audio/x-unknown"
                java.lang.String r17 = "audio/raw"
                java.lang.String r11 = "MatroskaExtractor"
                java.lang.String r18 = "audio/x-unknown"
                r8 = 0
                switch(r1) {
                    case 0: goto L_0x0427;
                    case 1: goto L_0x0427;
                    case 2: goto L_0x03e6;
                    case 3: goto L_0x03d9;
                    case 4: goto L_0x03c6;
                    case 5: goto L_0x03c2;
                    case 6: goto L_0x03b9;
                    case 7: goto L_0x039d;
                    case 8: goto L_0x0386;
                    case 9: goto L_0x0427;
                    case 10: goto L_0x036a;
                    case 11: goto L_0x035b;
                    case 12: goto L_0x0357;
                    case 13: goto L_0x0339;
                    case 14: goto L_0x0335;
                    case 15: goto L_0x0331;
                    case 16: goto L_0x0322;
                    case 17: goto L_0x031d;
                    case 18: goto L_0x030e;
                    case 19: goto L_0x0308;
                    case 20: goto L_0x0303;
                    case 21: goto L_0x0331;
                    case 22: goto L_0x02e1;
                    case 23: goto L_0x02b3;
                    case 24: goto L_0x0289;
                    case 25: goto L_0x0278;
                    case 26: goto L_0x0278;
                    case 27: goto L_0x025a;
                    case 28: goto L_0x0257;
                    case 29: goto L_0x0254;
                    case 30: goto L_0x0250;
                    case 31: goto L_0x024b;
                    case 32: goto L_0x023c;
                    case 33: goto L_0x01f8;
                    default: goto L_0x01f1;
                }
            L_0x01f1:
                java.lang.String r0 = "Unrecognized codec identifier."
                androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r8)
                throw r0
            L_0x01f8:
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r10)
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.add(r3)
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r9)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r11 = r0.codecDelayNs
                java.nio.ByteBuffer r3 = r3.putLong(r11)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r9)
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r11 = r0.seekPreRollNs
                java.nio.ByteBuffer r3 = r3.putLong(r11)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.lang.String r17 = "audio/opus"
                r3 = 5760(0x1680, float:8.071E-42)
                r4 = r3
                r3 = r8
            L_0x0238:
                r7 = 0
            L_0x0239:
                r9 = -1
                goto L_0x0436
            L_0x023c:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r17 = "audio/flac"
            L_0x0248:
                r3 = r8
            L_0x0249:
                r4 = -1
                goto L_0x0238
            L_0x024b:
                java.lang.String r17 = "audio/eac3"
            L_0x024d:
                r1 = r8
                r3 = r1
                goto L_0x0249
            L_0x0250:
                java.lang.String r17 = "video/mpeg2"
                goto L_0x024d
            L_0x0254:
                r17 = r6
                goto L_0x024d
            L_0x0257:
                r17 = r5
                goto L_0x024d
            L_0x025a:
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                androidx.media3.extractor.HevcConfig r1 = androidx.media3.extractor.HevcConfig.parse(r1)
                java.util.List<byte[]> r3 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r17 = "video/hevc"
            L_0x0274:
                r4 = r3
                r3 = r1
                r1 = r4
                goto L_0x0249
            L_0x0278:
                byte[] r1 = androidx.media3.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                F2.y0 r1 = F2.U.D(r1, r3)
                r17 = r2
                goto L_0x0248
            L_0x0289:
                int r1 = r0.audioBitDepth
                int r1 = androidx.media3.common.util.Util.getPcmEncoding(r1)
                if (r1 != 0) goto L_0x02ac
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported little endian PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r12)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r11, r1)
            L_0x02a7:
                r1 = r8
                r3 = r1
                r17 = r18
                goto L_0x0249
            L_0x02ac:
                r9 = r1
                r1 = r8
                r3 = r1
            L_0x02af:
                r4 = -1
                r7 = 0
                goto L_0x0436
            L_0x02b3:
                int r1 = r0.audioBitDepth
                if (r1 != r9) goto L_0x02bb
                r1 = r8
                r3 = r1
                r9 = r10
                goto L_0x02af
            L_0x02bb:
                if (r1 != r4) goto L_0x02c0
                r1 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x02ac
            L_0x02c0:
                if (r1 != r3) goto L_0x02c5
                r1 = 1342177280(0x50000000, float:8.5899346E9)
                goto L_0x02ac
            L_0x02c5:
                if (r1 != r7) goto L_0x02ca
                r1 = 1610612736(0x60000000, float:3.6893488E19)
                goto L_0x02ac
            L_0x02ca:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported big endian PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r12)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r11, r1)
                goto L_0x02a7
            L_0x02e1:
                int r1 = r0.audioBitDepth
                if (r1 != r7) goto L_0x02ec
                r1 = r8
                r3 = r1
                r4 = -1
                r7 = 0
                r9 = 4
                goto L_0x0436
            L_0x02ec:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported floating point PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r12)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r11, r1)
                goto L_0x02a7
            L_0x0303:
                java.lang.String r17 = "video/x-unknown"
                goto L_0x024d
            L_0x0308:
                r1 = r8
                r3 = r1
                r17 = r15
                goto L_0x0249
            L_0x030e:
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x0314
                r1 = r8
                goto L_0x0318
            L_0x0314:
                F2.y0 r1 = F2.U.B(r1)
            L_0x0318:
                java.lang.String r17 = "video/x-vnd.on2.vp9"
                goto L_0x0248
            L_0x031d:
                java.lang.String r17 = "video/x-vnd.on2.vp8"
                goto L_0x024d
            L_0x0322:
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x0328
                r1 = r8
                goto L_0x032c
            L_0x0328:
                F2.y0 r1 = F2.U.B(r1)
            L_0x032c:
                java.lang.String r17 = "video/av01"
                goto L_0x0248
            L_0x0331:
                java.lang.String r17 = "audio/vnd.dts"
                goto L_0x024d
            L_0x0335:
                java.lang.String r17 = "audio/ac3"
                goto L_0x024d
            L_0x0339:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                byte[] r3 = r0.codecPrivate
                androidx.media3.extractor.AacUtil$Config r3 = androidx.media3.extractor.AacUtil.parseAudioSpecificConfig(r3)
                int r4 = r3.sampleRateHz
                r0.sampleRate = r4
                int r4 = r3.channelCount
                r0.channelCount = r4
                java.lang.String r3 = r3.codecs
                java.lang.String r17 = "audio/mp4a-latm"
                goto L_0x0249
            L_0x0357:
                java.lang.String r17 = "audio/vnd.dts.hd"
                goto L_0x024d
            L_0x035b:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                F2.y0 r1 = F2.U.B(r1)
                r3 = r8
                r17 = r14
                goto L_0x0249
            L_0x036a:
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                androidx.media3.extractor.AvcConfig r1 = androidx.media3.extractor.AvcConfig.parse(r1)
                java.util.List<byte[]> r3 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r17 = "video/avc"
                goto L_0x0274
            L_0x0386:
                r1 = 4
                byte[] r3 = new byte[r1]
                java.lang.String r4 = r0.codecId
                byte[] r4 = r0.getCodecPrivate(r4)
                r7 = 0
                java.lang.System.arraycopy(r4, r7, r3, r7, r1)
                F2.y0 r1 = F2.U.B(r3)
                r3 = r8
                r17 = r13
            L_0x039a:
                r4 = -1
                goto L_0x0239
            L_0x039d:
                r7 = 0
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r3 = r1.first
                r17 = r3
                java.lang.String r17 = (java.lang.String) r17
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
            L_0x03b7:
                r3 = r8
                goto L_0x039a
            L_0x03b9:
                r7 = 0
                java.lang.String r17 = "audio/mpeg"
            L_0x03bc:
                r1 = r8
                r3 = r1
                r4 = 4096(0x1000, float:5.74E-42)
                goto L_0x0239
            L_0x03c2:
                r7 = 0
                java.lang.String r17 = "audio/mpeg-L2"
                goto L_0x03bc
            L_0x03c6:
                r7 = 0
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = parseVorbisCodecPrivate(r1)
                java.lang.String r17 = "audio/vorbis"
                r3 = 8192(0x2000, float:1.14794E-41)
                r4 = r3
                r3 = r8
                goto L_0x0239
            L_0x03d9:
                r7 = 0
                androidx.media3.extractor.TrueHdSampleRechunker r1 = new androidx.media3.extractor.TrueHdSampleRechunker
                r1.<init>()
                r0.trueHdSampleRechunker = r1
                java.lang.String r17 = "audio/true-hd"
                r1 = r8
                r3 = r1
                goto L_0x039a
            L_0x03e6:
                r7 = 0
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                boolean r1 = parseMsAcmCodecPrivate(r1)
                if (r1 == 0) goto L_0x0421
                int r1 = r0.audioBitDepth
                int r1 = androidx.media3.common.util.Util.getPcmEncoding(r1)
                if (r1 != 0) goto L_0x041c
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r12)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r11, r1)
            L_0x0416:
                r1 = r8
                r3 = r1
                r17 = r18
                goto L_0x039a
            L_0x041c:
                r9 = r1
                r1 = r8
                r3 = r1
                r4 = -1
                goto L_0x0436
            L_0x0421:
                java.lang.String r1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown"
                androidx.media3.common.util.Log.w(r11, r1)
                goto L_0x0416
            L_0x0427:
                r7 = 0
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x042e
                r1 = r8
                goto L_0x0432
            L_0x042e:
                java.util.List r1 = java.util.Collections.singletonList(r1)
            L_0x0432:
                java.lang.String r17 = "video/mp4v-es"
                goto L_0x03b7
            L_0x0436:
                byte[] r11 = r0.dolbyVisionConfigBytes
                if (r11 == 0) goto L_0x044c
                androidx.media3.common.util.ParsableByteArray r11 = new androidx.media3.common.util.ParsableByteArray
                byte[] r12 = r0.dolbyVisionConfigBytes
                r11.<init>((byte[]) r12)
                androidx.media3.container.DolbyVisionConfig r11 = androidx.media3.container.DolbyVisionConfig.parse(r11)
                if (r11 == 0) goto L_0x044c
                java.lang.String r3 = r11.codecs
                java.lang.String r17 = "video/dolby-vision"
            L_0x044c:
                r11 = r17
                boolean r12 = r0.flagDefault
                boolean r7 = r0.flagForced
                if (r7 == 0) goto L_0x0456
                r7 = 2
                goto L_0x0457
            L_0x0456:
                r7 = 0
            L_0x0457:
                r7 = r7 | r12
                androidx.media3.common.Format$Builder r12 = new androidx.media3.common.Format$Builder
                r12.<init>()
                boolean r16 = androidx.media3.common.MimeTypes.isAudio(r11)
                if (r16 == 0) goto L_0x0475
                int r2 = r0.channelCount
                androidx.media3.common.Format$Builder r2 = r12.setChannelCount(r2)
                int r5 = r0.sampleRate
                androidx.media3.common.Format$Builder r2 = r2.setSampleRate(r5)
                r2.setPcmEncoding(r9)
                r5 = 1
                goto L_0x059b
            L_0x0475:
                boolean r9 = androidx.media3.common.MimeTypes.isVideo(r11)
                if (r9 == 0) goto L_0x056e
                int r2 = r0.displayUnit
                if (r2 != 0) goto L_0x0491
                int r2 = r0.displayWidth
                r5 = -1
                if (r2 != r5) goto L_0x0486
                int r2 = r0.width
            L_0x0486:
                r0.displayWidth = r2
                int r2 = r0.displayHeight
                if (r2 != r5) goto L_0x048e
                int r2 = r0.height
            L_0x048e:
                r0.displayHeight = r2
                goto L_0x0492
            L_0x0491:
                r5 = -1
            L_0x0492:
                int r2 = r0.displayWidth
                if (r2 == r5) goto L_0x04a4
                int r6 = r0.displayHeight
                if (r6 == r5) goto L_0x04a4
                int r9 = r0.height
                int r9 = r9 * r2
                float r2 = (float) r9
                int r9 = r0.width
                int r9 = r9 * r6
                float r6 = (float) r9
                float r2 = r2 / r6
                goto L_0x04a6
            L_0x04a4:
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x04a6:
                boolean r6 = r0.hasColorInfo
                if (r6 == 0) goto L_0x04d9
                byte[] r6 = r0.getHdrStaticInfo()
                androidx.media3.common.ColorInfo$Builder r8 = new androidx.media3.common.ColorInfo$Builder
                r8.<init>()
                int r9 = r0.colorSpace
                androidx.media3.common.ColorInfo$Builder r8 = r8.setColorSpace(r9)
                int r9 = r0.colorRange
                androidx.media3.common.ColorInfo$Builder r8 = r8.setColorRange(r9)
                int r9 = r0.colorTransfer
                androidx.media3.common.ColorInfo$Builder r8 = r8.setColorTransfer(r9)
                androidx.media3.common.ColorInfo$Builder r6 = r8.setHdrStaticInfo(r6)
                int r8 = r0.bitsPerChannel
                androidx.media3.common.ColorInfo$Builder r6 = r6.setLumaBitdepth(r8)
                int r8 = r0.bitsPerChannel
                androidx.media3.common.ColorInfo$Builder r6 = r6.setChromaBitdepth(r8)
                androidx.media3.common.ColorInfo r8 = r6.build()
            L_0x04d9:
                java.lang.String r6 = r0.name
                if (r6 == 0) goto L_0x04f9
                java.util.Map r6 = androidx.media3.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r9 = r0.name
                boolean r6 = r6.containsKey(r9)
                if (r6 == 0) goto L_0x04f9
                java.util.Map r5 = androidx.media3.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r6 = r0.name
                java.lang.Object r5 = r5.get(r6)
                java.lang.Integer r5 = (java.lang.Integer) r5
                int r5 = r5.intValue()
            L_0x04f9:
                int r6 = r0.projectionType
                if (r6 != 0) goto L_0x0549
                float r6 = r0.projectionPoseYaw
                r9 = 0
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 != 0) goto L_0x0549
                float r6 = r0.projectionPosePitch
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 != 0) goto L_0x0549
                float r6 = r0.projectionPoseRoll
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 != 0) goto L_0x0518
                r5 = 0
                goto L_0x0549
            L_0x0518:
                float r6 = r0.projectionPoseRoll
                r9 = 1119092736(0x42b40000, float:90.0)
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 != 0) goto L_0x0525
                r5 = 90
                goto L_0x0549
            L_0x0525:
                float r6 = r0.projectionPoseRoll
                r9 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 == 0) goto L_0x0547
                float r6 = r0.projectionPoseRoll
                r9 = 1127481344(0x43340000, float:180.0)
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 != 0) goto L_0x053a
                goto L_0x0547
            L_0x053a:
                float r6 = r0.projectionPoseRoll
                r9 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r6 = java.lang.Float.compare(r6, r9)
                if (r6 != 0) goto L_0x0549
                r5 = 270(0x10e, float:3.78E-43)
                goto L_0x0549
            L_0x0547:
                r5 = 180(0xb4, float:2.52E-43)
            L_0x0549:
                int r6 = r0.width
                androidx.media3.common.Format$Builder r6 = r12.setWidth(r6)
                int r9 = r0.height
                androidx.media3.common.Format$Builder r6 = r6.setHeight(r9)
                androidx.media3.common.Format$Builder r2 = r6.setPixelWidthHeightRatio(r2)
                androidx.media3.common.Format$Builder r2 = r2.setRotationDegrees(r5)
                byte[] r5 = r0.projectionData
                androidx.media3.common.Format$Builder r2 = r2.setProjectionData(r5)
                int r5 = r0.stereoMode
                androidx.media3.common.Format$Builder r2 = r2.setStereoMode(r5)
                r2.setColorInfo(r8)
                r5 = 2
                goto L_0x059b
            L_0x056e:
                boolean r6 = r6.equals(r11)
                if (r6 != 0) goto L_0x059a
                boolean r2 = r2.equals(r11)
                if (r2 != 0) goto L_0x059a
                boolean r2 = r5.equals(r11)
                if (r2 != 0) goto L_0x059a
                boolean r2 = r14.equals(r11)
                if (r2 != 0) goto L_0x059a
                boolean r2 = r15.equals(r11)
                if (r2 != 0) goto L_0x059a
                boolean r2 = r13.equals(r11)
                if (r2 == 0) goto L_0x0593
                goto L_0x059a
            L_0x0593:
                java.lang.String r0 = "Unexpected MIME type."
                androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r8)
                throw r0
            L_0x059a:
                r5 = r10
            L_0x059b:
                java.lang.String r2 = r0.name
                if (r2 == 0) goto L_0x05b0
                java.util.Map r2 = androidx.media3.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r6 = r0.name
                boolean r2 = r2.containsKey(r6)
                if (r2 != 0) goto L_0x05b0
                java.lang.String r2 = r0.name
                r12.setLabel(r2)
            L_0x05b0:
                r2 = r21
                androidx.media3.common.Format$Builder r2 = r12.setId((int) r2)
                boolean r6 = r0.isWebm
                if (r6 == 0) goto L_0x05be
                java.lang.String r6 = "video/webm"
                goto L_0x05c1
            L_0x05be:
                java.lang.String r6 = "video/x-matroska"
            L_0x05c1:
                androidx.media3.common.Format$Builder r2 = r2.setContainerMimeType(r6)
                androidx.media3.common.Format$Builder r2 = r2.setSampleMimeType(r11)
                androidx.media3.common.Format$Builder r2 = r2.setMaxInputSize(r4)
                java.lang.String r4 = r0.language
                androidx.media3.common.Format$Builder r2 = r2.setLanguage(r4)
                androidx.media3.common.Format$Builder r2 = r2.setSelectionFlags(r7)
                androidx.media3.common.Format$Builder r1 = r2.setInitializationData(r1)
                androidx.media3.common.Format$Builder r1 = r1.setCodecs(r3)
                androidx.media3.common.DrmInitData r2 = r0.drmInitData
                androidx.media3.common.Format$Builder r1 = r1.setDrmInitData(r2)
                androidx.media3.common.Format r1 = r1.build()
                int r2 = r0.number
                r3 = r20
                androidx.media3.extractor.TrackOutput r2 = r3.track(r2, r5)
                r0.output = r2
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.Track.initializeOutput(androidx.media3.extractor.ExtractorOutput, int):void");
        }

        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        j.l(0, hashMap, "htc_video_rotA-000", 90, "htc_video_rotA-090");
        j.l(MOCRLang.KHMER, hashMap, "htc_video_rotA-180", 270, "htc_video_rotA-270");
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(hashMap);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory, int i2) {
        this(new DefaultEbmlReader(), i2, factory);
    }

    private void assertInCues(int i2) {
        if (this.cueTimesUs == null || this.cueClusterPositions == null) {
            throw ParserException.createForMalformedContainer("Element " + i2 + " must be in a Cues", (Throwable) null);
        }
    }

    private void assertInTrackEntry(int i2) {
        if (this.currentTrack == null) {
            throw ParserException.createForMalformedContainer("Element " + i2 + " must be in a TrackEntry", (Throwable) null);
        }
    }

    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    private SeekMap buildSeekMap(LongArray longArray, LongArray longArray2) {
        int i2;
        if (this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L || longArray == null || longArray.size() == 0 || longArray2 == null || longArray2.size() != longArray.size()) {
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = longArray.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            jArr3[i8] = longArray.get(i8);
            jArr[i8] = longArray2.get(i8) + this.segmentContentPosition;
        }
        while (true) {
            i2 = size - 1;
            if (i7 >= i2) {
                break;
            }
            int i10 = i7 + 1;
            iArr[i7] = (int) (jArr[i10] - jArr[i7]);
            jArr2[i7] = jArr3[i10] - jArr3[i7];
            i7 = i10;
        }
        int i11 = i2;
        while (i11 > 0 && jArr3[i11] > this.durationUs) {
            i11--;
        }
        iArr[i11] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i11]);
        jArr2[i11] = this.durationUs - jArr3[i11];
        if (i11 < i2) {
            Log.w("MatroskaExtractor", "Discarding trailing cue points with timestamps greater than total duration");
            int i12 = i11 + 1;
            iArr = Arrays.copyOf(iArr, i12);
            jArr = Arrays.copyOf(jArr, i12);
            jArr2 = Arrays.copyOf(jArr2, i12);
            jArr3 = Arrays.copyOf(jArr3, i12);
        }
        return new ChunkIndex(iArr, jArr, jArr2, jArr3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void commitSampleToOutput(androidx.media3.extractor.mkv.MatroskaExtractor.Track r18, long r19, int r21, int r22, int r23) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.media3.extractor.TrueHdSampleRechunker r2 = r1.trueHdSampleRechunker
            r9 = 1
            if (r2 == 0) goto L_0x001c
            r3 = r2
            androidx.media3.extractor.TrackOutput r2 = r1.output
            androidx.media3.extractor.TrackOutput$CryptoData r8 = r1.cryptoData
            r5 = r21
            r6 = r22
            r7 = r23
            r1 = r3
            r3 = r19
            r1.sampleMetadata(r2, r3, r5, r6, r7, r8)
            goto L_0x00d2
        L_0x001c:
            java.lang.String r2 = "S_TEXT/UTF8"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0044
            java.lang.String r2 = "S_TEXT/ASS"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0044
            java.lang.String r2 = "S_TEXT/SSA"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0044
            java.lang.String r2 = "S_TEXT/WEBVTT"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0060
        L_0x0044:
            int r2 = r0.blockSampleCount
            java.lang.String r3 = "MatroskaExtractor"
            if (r2 <= r9) goto L_0x0050
            java.lang.String r2 = "Skipping subtitle sample in laced block."
            androidx.media3.common.util.Log.w(r3, r2)
            goto L_0x0060
        L_0x0050:
            long r4 = r0.blockDurationUs
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x0063
            java.lang.String r2 = "Skipping subtitle sample with no duration."
            androidx.media3.common.util.Log.w(r3, r2)
        L_0x0060:
            r2 = r22
            goto L_0x00a2
        L_0x0063:
            java.lang.String r2 = r1.codecId
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            byte[] r3 = r3.getData()
            setSubtitleEndTime(r2, r4, r3)
            androidx.media3.common.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.getPosition()
        L_0x0074:
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            int r3 = r3.limit()
            if (r2 >= r3) goto L_0x008f
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            byte[] r3 = r3.getData()
            byte r3 = r3[r2]
            if (r3 != 0) goto L_0x008c
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            r3.setLimit(r2)
            goto L_0x008f
        L_0x008c:
            int r2 = r2 + 1
            goto L_0x0074
        L_0x008f:
            androidx.media3.extractor.TrackOutput r2 = r1.output
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            int r4 = r3.limit()
            r2.sampleData(r3, r4)
            androidx.media3.common.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.limit()
            int r2 = r2 + r22
        L_0x00a2:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            r3 = r21 & r3
            if (r3 == 0) goto L_0x00c2
            int r3 = r0.blockSampleCount
            if (r3 <= r9) goto L_0x00b3
            androidx.media3.common.util.ParsableByteArray r3 = r0.supplementalData
            r4 = 0
            r3.reset((int) r4)
            goto L_0x00c2
        L_0x00b3:
            androidx.media3.common.util.ParsableByteArray r3 = r0.supplementalData
            int r3 = r3.limit()
            androidx.media3.extractor.TrackOutput r4 = r1.output
            androidx.media3.common.util.ParsableByteArray r5 = r0.supplementalData
            r6 = 2
            r4.sampleData((androidx.media3.common.util.ParsableByteArray) r5, (int) r3, (int) r6)
            int r2 = r2 + r3
        L_0x00c2:
            r14 = r2
            androidx.media3.extractor.TrackOutput r10 = r1.output
            androidx.media3.extractor.TrackOutput$CryptoData r1 = r1.cryptoData
            r11 = r19
            r13 = r21
            r15 = r23
            r16 = r1
            r10.sampleMetadata(r11, r13, r14, r15, r16)
        L_0x00d2:
            r0.haveOutputSample = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.commitSampleToOutput(androidx.media3.extractor.mkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i2) {
        if (iArr == null) {
            return new int[i2];
        }
        if (iArr.length >= i2) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i2)];
    }

    private int finishWriteSampleData() {
        int i2 = this.sampleBytesWritten;
        resetWriteSampleData();
        return i2;
    }

    private static byte[] formatSubtitleTimecode(long j2, String str, long j3) {
        boolean z;
        if (j2 != -9223372036854775807L) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        int i2 = (int) (j2 / 3600000000L);
        long j8 = j2 - (((long) i2) * 3600000000L);
        int i7 = (int) (j8 / 60000000);
        long j10 = j8 - (((long) i7) * 60000000);
        int i8 = (int) (j10 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf((int) ((j10 - (((long) i8) * 1000000)) / j3))}));
    }

    private static boolean isCodecSupported(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals("V_MPEG4/ISO/AP")) {
                    c5 = 0;
                    break;
                }
                break;
            case -2095575984:
                if (str.equals("V_MPEG4/ISO/SP")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1985379776:
                if (str.equals("A_MS/ACM")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1784763192:
                if (str.equals("A_TRUEHD")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1730367663:
                if (str.equals("A_VORBIS")) {
                    c5 = 4;
                    break;
                }
                break;
            case -1482641358:
                if (str.equals("A_MPEG/L2")) {
                    c5 = 5;
                    break;
                }
                break;
            case -1482641357:
                if (str.equals("A_MPEG/L3")) {
                    c5 = 6;
                    break;
                }
                break;
            case -1373388978:
                if (str.equals("V_MS/VFW/FOURCC")) {
                    c5 = 7;
                    break;
                }
                break;
            case -933872740:
                if (str.equals("S_DVBSUB")) {
                    c5 = 8;
                    break;
                }
                break;
            case -538363189:
                if (str.equals("V_MPEG4/ISO/ASP")) {
                    c5 = 9;
                    break;
                }
                break;
            case -538363109:
                if (str.equals("V_MPEG4/ISO/AVC")) {
                    c5 = 10;
                    break;
                }
                break;
            case -425012669:
                if (str.equals("S_VOBSUB")) {
                    c5 = 11;
                    break;
                }
                break;
            case -356037306:
                if (str.equals("A_DTS/LOSSLESS")) {
                    c5 = 12;
                    break;
                }
                break;
            case 62923557:
                if (str.equals("A_AAC")) {
                    c5 = 13;
                    break;
                }
                break;
            case 62923603:
                if (str.equals("A_AC3")) {
                    c5 = 14;
                    break;
                }
                break;
            case 62927045:
                if (str.equals("A_DTS")) {
                    c5 = 15;
                    break;
                }
                break;
            case 82318131:
                if (str.equals("V_AV1")) {
                    c5 = 16;
                    break;
                }
                break;
            case 82338133:
                if (str.equals("V_VP8")) {
                    c5 = 17;
                    break;
                }
                break;
            case 82338134:
                if (str.equals("V_VP9")) {
                    c5 = 18;
                    break;
                }
                break;
            case 99146302:
                if (str.equals("S_HDMV/PGS")) {
                    c5 = 19;
                    break;
                }
                break;
            case 444813526:
                if (str.equals("V_THEORA")) {
                    c5 = 20;
                    break;
                }
                break;
            case 542569478:
                if (str.equals("A_DTS/EXPRESS")) {
                    c5 = 21;
                    break;
                }
                break;
            case 635596514:
                if (str.equals("A_PCM/FLOAT/IEEE")) {
                    c5 = 22;
                    break;
                }
                break;
            case 725948237:
                if (str.equals("A_PCM/INT/BIG")) {
                    c5 = 23;
                    break;
                }
                break;
            case 725957860:
                if (str.equals("A_PCM/INT/LIT")) {
                    c5 = 24;
                    break;
                }
                break;
            case 738597099:
                if (str.equals("S_TEXT/ASS")) {
                    c5 = 25;
                    break;
                }
                break;
            case 738614379:
                if (str.equals("S_TEXT/SSA")) {
                    c5 = 26;
                    break;
                }
                break;
            case 855502857:
                if (str.equals("V_MPEGH/ISO/HEVC")) {
                    c5 = 27;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals("S_TEXT/WEBVTT")) {
                    c5 = 28;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals("S_TEXT/UTF8")) {
                    c5 = 29;
                    break;
                }
                break;
            case 1809237540:
                if (str.equals("V_MPEG2")) {
                    c5 = 30;
                    break;
                }
                break;
            case 1950749482:
                if (str.equals("A_EAC3")) {
                    c5 = 31;
                    break;
                }
                break;
            case 1950789798:
                if (str.equals("A_FLAC")) {
                    c5 = ' ';
                    break;
                }
                break;
            case 1951062397:
                if (str.equals("A_OPUS")) {
                    c5 = '!';
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
            case '!':
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2)};
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j2) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j2;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j3 = this.seekPositionAfterBuildingCues;
            if (j3 != -1) {
                positionHolder.position = j3;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private void readScratch(ExtractorInput extractorInput, int i2) {
        if (this.scratch.limit() < i2) {
            if (this.scratch.capacity() < i2) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, i2));
            }
            extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), i2 - this.scratch.limit());
            this.scratch.setLimit(i2);
        }
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private long scaleTimecodeToUs(long j2) {
        long j3 = this.timecodeScale;
        if (j3 != -9223372036854775807L) {
            return Util.scaleLargeTimestamp(j2, j3, 1000);
        }
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", (Throwable) null);
    }

    private static void setSubtitleEndTime(String str, long j2, byte[] bArr) {
        int i2;
        byte[] bArr2;
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 738597099:
                if (str.equals("S_TEXT/ASS")) {
                    c5 = 0;
                    break;
                }
                break;
            case 738614379:
                if (str.equals("S_TEXT/SSA")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals("S_TEXT/WEBVTT")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals("S_TEXT/UTF8")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
                bArr2 = formatSubtitleTimecode(j2, "%01d:%02d:%02d:%02d", 10000);
                i2 = 21;
                break;
            case 2:
                bArr2 = formatSubtitleTimecode(j2, "%02d:%02d:%02d.%03d", 1000);
                i2 = 25;
                break;
            case 3:
                bArr2 = formatSubtitleTimecode(j2, "%02d:%02d:%02d,%03d", 1000);
                i2 = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
    }

    private int writeSampleData(ExtractorInput extractorInput, Track track, int i2, boolean z) {
        boolean z3;
        int i7;
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i2);
            return finishWriteSampleData();
        } else if ("S_TEXT/ASS".equals(track.codecId) || "S_TEXT/SSA".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i2);
            return finishWriteSampleData();
        } else if ("S_TEXT/WEBVTT".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i2);
            return finishWriteSampleData();
        } else {
            TrackOutput trackOutput = track.output;
            boolean z7 = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i8 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.getData(), 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.getData()[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.getData()[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", (Throwable) null);
                        }
                    }
                    byte b = this.sampleSignalByte;
                    if ((b & 1) == 1) {
                        if ((b & 2) == 2) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] data = this.scratch.getData();
                            if (!z3) {
                                i8 = 0;
                            }
                            data[0] = (byte) (i8 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                            this.sampleBytesWritten += 8;
                        }
                        if (z3) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.getData(), 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i10 = this.samplePartitionCount * 4;
                            this.scratch.reset(i10);
                            extractorInput.readFully(this.scratch.getData(), 0, i10);
                            this.sampleBytesRead += i10;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i11 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i11) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i11);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i12 = 0;
                            int i13 = 0;
                            while (true) {
                                i7 = this.samplePartitionCount;
                                if (i12 >= i7) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i12 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i13));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i13);
                                }
                                i12++;
                                i13 = readUnsignedIntToInt;
                            }
                            int i14 = (i2 - this.sampleBytesRead) - i13;
                            if (i7 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i14);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i14);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i11);
                            trackOutput.sampleData(this.encryptionSubsampleData, i11, 1);
                            this.sampleBytesWritten += i11;
                        }
                    }
                } else {
                    byte[] bArr = track.sampleStrippedBytes;
                    if (bArr != null) {
                        this.sampleStrippedBytes.reset(bArr, bArr.length);
                    }
                }
                if (track.samplesHaveSupplementalData(z)) {
                    this.blockFlags |= 268435456;
                    this.supplementalData.reset(0);
                    int limit = (this.sampleStrippedBytes.limit() + i2) - this.sampleBytesRead;
                    this.scratch.reset(4);
                    this.scratch.getData()[0] = (byte) ((limit >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
                    this.scratch.getData()[1] = (byte) ((limit >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
                    this.scratch.getData()[2] = (byte) ((limit >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                    this.scratch.getData()[3] = (byte) (limit & ScoverState.TYPE_NFC_SMART_COVER);
                    trackOutput.sampleData(this.scratch, 4, 2);
                    this.sampleBytesWritten += 4;
                }
                this.sampleEncodingHandled = true;
            }
            int limit2 = this.sampleStrippedBytes.limit() + i2;
            if (!"V_MPEG4/ISO/AVC".equals(track.codecId) && !"V_MPEGH/ISO/HEVC".equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z7 = false;
                    }
                    Assertions.checkState(z7);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int i15 = this.sampleBytesRead;
                    if (i15 >= limit2) {
                        break;
                    }
                    int writeToOutput = writeToOutput(extractorInput, trackOutput, limit2 - i15);
                    this.sampleBytesRead += writeToOutput;
                    this.sampleBytesWritten += writeToOutput;
                }
            } else {
                byte[] data2 = this.nalLength.getData();
                data2[0] = 0;
                data2[1] = 0;
                data2[2] = 0;
                int i16 = track.nalUnitLengthFieldLength;
                int i17 = 4 - i16;
                while (this.sampleBytesRead < limit2) {
                    int i18 = this.sampleCurrentNalBytesRemaining;
                    if (i18 == 0) {
                        writeToTarget(extractorInput, data2, i17, i16);
                        this.sampleBytesRead += i16;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int writeToOutput2 = writeToOutput(extractorInput, trackOutput, i18);
                        this.sampleBytesRead += writeToOutput2;
                        this.sampleBytesWritten += writeToOutput2;
                        this.sampleCurrentNalBytesRemaining -= writeToOutput2;
                    }
                }
            }
            if ("A_VORBIS".equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
            return finishWriteSampleData();
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i2) {
        int length = bArr.length + i2;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.reset(Arrays.copyOf(bArr, length + i2));
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, i2);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i2) {
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft <= 0) {
            return trackOutput.sampleData((DataReader) extractorInput, i2, false);
        }
        int min = Math.min(i2, bytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, min);
        return min;
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i2, int i7) {
        int min = Math.min(i7, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i2 + min, i7 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i2, min);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r24, int r25, androidx.media3.extractor.ExtractorInput r26) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            r7 = r26
            r3 = 161(0xa1, float:2.26E-43)
            r4 = 163(0xa3, float:2.28E-43)
            r5 = 0
            r6 = 2
            r8 = 0
            r9 = 1
            if (r1 == r3) goto L_0x00bc
            if (r1 == r4) goto L_0x00bc
            r3 = 165(0xa5, float:2.31E-43)
            if (r1 == r3) goto L_0x00a6
            r3 = 16877(0x41ed, float:2.365E-41)
            if (r1 == r3) goto L_0x009e
            r3 = 16981(0x4255, float:2.3795E-41)
            if (r1 == r3) goto L_0x0091
            r3 = 18402(0x47e2, float:2.5787E-41)
            if (r1 == r3) goto L_0x0080
            r3 = 21419(0x53ab, float:3.0014E-41)
            if (r1 == r3) goto L_0x005d
            r3 = 25506(0x63a2, float:3.5742E-41)
            if (r1 == r3) goto L_0x0050
            r3 = 30322(0x7672, float:4.249E-41)
            if (r1 != r3) goto L_0x003d
            r23.assertInTrackEntry(r24)
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r0 = r0.currentTrack
            byte[] r1 = new byte[r2]
            r0.projectionData = r1
            r7.readFully(r1, r8, r2)
            return
        L_0x003d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected id: "
            r0.<init>(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r5)
            throw r0
        L_0x0050:
            r23.assertInTrackEntry(r24)
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r0 = r0.currentTrack
            byte[] r1 = new byte[r2]
            r0.codecPrivate = r1
            r7.readFully(r1, r8, r2)
            return
        L_0x005d:
            androidx.media3.common.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.getData()
            java.util.Arrays.fill(r1, r8)
            androidx.media3.common.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.getData()
            int r3 = 4 - r2
            r7.readFully(r1, r3, r2)
            androidx.media3.common.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            r1.setPosition(r8)
            androidx.media3.common.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            long r1 = r1.readUnsignedInt()
            int r1 = (int) r1
            r0.seekEntryId = r1
            return
        L_0x0080:
            byte[] r3 = new byte[r2]
            r7.readFully(r3, r8, r2)
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r0 = r23.getCurrentTrack(r24)
            androidx.media3.extractor.TrackOutput$CryptoData r1 = new androidx.media3.extractor.TrackOutput$CryptoData
            r1.<init>(r9, r3, r8, r8)
            r0.cryptoData = r1
            return
        L_0x0091:
            r23.assertInTrackEntry(r24)
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r0 = r0.currentTrack
            byte[] r1 = new byte[r2]
            r0.sampleStrippedBytes = r1
            r7.readFully(r1, r8, r2)
            return
        L_0x009e:
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r1 = r23.getCurrentTrack(r24)
            r0.handleBlockAddIDExtraData(r1, r7, r2)
            return
        L_0x00a6:
            int r1 = r0.blockState
            if (r1 == r6) goto L_0x00ac
            goto L_0x02fe
        L_0x00ac:
            android.util.SparseArray<androidx.media3.extractor.mkv.MatroskaExtractor$Track> r1 = r0.tracks
            int r3 = r0.blockTrackNumber
            java.lang.Object r1 = r1.get(r3)
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r1 = (androidx.media3.extractor.mkv.MatroskaExtractor.Track) r1
            int r3 = r0.blockAdditionalId
            r0.handleBlockAdditionalData(r1, r3, r7, r2)
            return
        L_0x00bc:
            int r3 = r0.blockState
            r10 = 8
            if (r3 != 0) goto L_0x00e1
            androidx.media3.extractor.mkv.VarintReader r3 = r0.varintReader
            long r11 = r3.readUnsignedVarint(r7, r8, r9, r10)
            int r3 = (int) r11
            r0.blockTrackNumber = r3
            androidx.media3.extractor.mkv.VarintReader r3 = r0.varintReader
            int r3 = r3.getLastLength()
            r0.blockTrackNumberLength = r3
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.blockDurationUs = r11
            r0.blockState = r9
            androidx.media3.common.util.ParsableByteArray r3 = r0.scratch
            r3.reset((int) r8)
        L_0x00e1:
            android.util.SparseArray<androidx.media3.extractor.mkv.MatroskaExtractor$Track> r3 = r0.tracks
            int r11 = r0.blockTrackNumber
            java.lang.Object r3 = r3.get(r11)
            androidx.media3.extractor.mkv.MatroskaExtractor$Track r3 = (androidx.media3.extractor.mkv.MatroskaExtractor.Track) r3
            if (r3 != 0) goto L_0x00f7
            int r1 = r0.blockTrackNumberLength
            int r1 = r2 - r1
            r7.skipFully(r1)
            r0.blockState = r8
            return
        L_0x00f7:
            r3.assertOutputInitialized()
            int r11 = r0.blockState
            if (r11 != r9) goto L_0x02ac
            r11 = 3
            r0.readScratch(r7, r11)
            androidx.media3.common.util.ParsableByteArray r12 = r0.scratch
            byte[] r12 = r12.getData()
            byte r12 = r12[r6]
            r12 = r12 & 6
            int r12 = r12 >> r9
            r13 = 255(0xff, float:3.57E-43)
            if (r12 != 0) goto L_0x0129
            r0.blockSampleCount = r9
            int[] r5 = r0.blockSampleSizes
            int[] r5 = ensureArrayCapacity(r5, r9)
            r0.blockSampleSizes = r5
            int r12 = r0.blockTrackNumberLength
            int r2 = r2 - r12
            int r2 = r2 - r11
            r5[r8] = r2
        L_0x0121:
            r17 = r8
            r16 = r9
            r18 = r10
            goto L_0x0258
        L_0x0129:
            r14 = 4
            r0.readScratch(r7, r14)
            androidx.media3.common.util.ParsableByteArray r15 = r0.scratch
            byte[] r15 = r15.getData()
            byte r15 = r15[r11]
            r15 = r15 & r13
            int r15 = r15 + r9
            r0.blockSampleCount = r15
            r16 = r14
            int[] r14 = r0.blockSampleSizes
            int[] r14 = ensureArrayCapacity(r14, r15)
            r0.blockSampleSizes = r14
            if (r12 != r6) goto L_0x0151
            int r5 = r0.blockTrackNumberLength
            int r2 = r2 - r5
            int r2 = r2 + -4
            int r5 = r0.blockSampleCount
            int r2 = r2 / r5
            java.util.Arrays.fill(r14, r8, r5, r2)
            goto L_0x0121
        L_0x0151:
            if (r12 != r9) goto L_0x018c
            r5 = r8
            r11 = r5
            r14 = r16
        L_0x0157:
            int r12 = r0.blockSampleCount
            int r15 = r12 + -1
            if (r5 >= r15) goto L_0x0181
            int[] r12 = r0.blockSampleSizes
            r12[r5] = r8
        L_0x0161:
            int r12 = r14 + 1
            r0.readScratch(r7, r12)
            androidx.media3.common.util.ParsableByteArray r15 = r0.scratch
            byte[] r15 = r15.getData()
            byte r14 = r15[r14]
            r14 = r14 & r13
            int[] r15 = r0.blockSampleSizes
            r16 = r15[r5]
            int r16 = r16 + r14
            r15[r5] = r16
            if (r14 == r13) goto L_0x017f
            int r11 = r11 + r16
            int r5 = r5 + 1
            r14 = r12
            goto L_0x0157
        L_0x017f:
            r14 = r12
            goto L_0x0161
        L_0x0181:
            int[] r5 = r0.blockSampleSizes
            int r12 = r12 - r9
            int r15 = r0.blockTrackNumberLength
            int r2 = r2 - r15
            int r2 = r2 - r14
            int r2 = r2 - r11
            r5[r12] = r2
            goto L_0x0121
        L_0x018c:
            if (r12 != r11) goto L_0x0299
            r11 = r8
            r12 = r11
            r14 = r16
        L_0x0192:
            int r15 = r0.blockSampleCount
            r16 = r9
            int r9 = r15 + -1
            if (r11 >= r9) goto L_0x0249
            int[] r9 = r0.blockSampleSizes
            r9[r11] = r8
            int r9 = r14 + 1
            r0.readScratch(r7, r9)
            androidx.media3.common.util.ParsableByteArray r15 = r0.scratch
            byte[] r15 = r15.getData()
            byte r15 = r15[r14]
            if (r15 == 0) goto L_0x0242
            r15 = r8
        L_0x01ae:
            if (r15 >= r10) goto L_0x0209
            int r17 = 7 - r15
            r18 = r10
            int r10 = r16 << r17
            r17 = r8
            androidx.media3.common.util.ParsableByteArray r8 = r0.scratch
            byte[] r8 = r8.getData()
            byte r8 = r8[r14]
            r8 = r8 & r10
            if (r8 == 0) goto L_0x01fd
            int r9 = r9 + r15
            r0.readScratch(r7, r9)
            androidx.media3.common.util.ParsableByteArray r8 = r0.scratch
            byte[] r8 = r8.getData()
            int r19 = r14 + 1
            byte r8 = r8[r14]
            r8 = r8 & r13
            int r10 = ~r10
            r8 = r8 & r10
            long r6 = (long) r8
        L_0x01d5:
            r8 = r19
            if (r8 >= r9) goto L_0x01ed
            long r6 = r6 << r18
            androidx.media3.common.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.getData()
            int r19 = r8 + 1
            byte r8 = r14[r8]
            r8 = r8 & r13
            r20 = r11
            long r10 = (long) r8
            long r6 = r6 | r10
            r11 = r20
            goto L_0x01d5
        L_0x01ed:
            r20 = r11
            if (r20 <= 0) goto L_0x01fb
            int r15 = r15 * 7
            int r15 = r15 + 6
            r10 = 1
            long r14 = r10 << r15
            long r14 = r14 - r10
            long r6 = r6 - r14
        L_0x01fb:
            r14 = r9
            goto L_0x0212
        L_0x01fd:
            r20 = r11
            int r15 = r15 + 1
            r7 = r26
            r8 = r17
            r10 = r18
            r6 = 2
            goto L_0x01ae
        L_0x0209:
            r17 = r8
            r18 = r10
            r20 = r11
            r6 = 0
            goto L_0x01fb
        L_0x0212:
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 < 0) goto L_0x023b
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 > 0) goto L_0x023b
            int r6 = (int) r6
            int[] r7 = r0.blockSampleSizes
            if (r20 != 0) goto L_0x0226
            goto L_0x022b
        L_0x0226:
            int r11 = r20 + -1
            r8 = r7[r11]
            int r6 = r6 + r8
        L_0x022b:
            r7[r20] = r6
            int r12 = r12 + r6
            int r11 = r20 + 1
            r7 = r26
            r9 = r16
            r8 = r17
            r10 = r18
            r6 = 2
            goto L_0x0192
        L_0x023b:
            java.lang.String r0 = "EBML lacing sample size out of range."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r5)
            throw r0
        L_0x0242:
            java.lang.String r0 = "No valid varint length mask found"
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r5)
            throw r0
        L_0x0249:
            r17 = r8
            r18 = r10
            int[] r5 = r0.blockSampleSizes
            int r15 = r15 + -1
            int r6 = r0.blockTrackNumberLength
            int r2 = r2 - r6
            int r2 = r2 - r14
            int r2 = r2 - r12
            r5[r15] = r2
        L_0x0258:
            androidx.media3.common.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.getData()
            byte r2 = r2[r17]
            int r2 = r2 << 8
            androidx.media3.common.util.ParsableByteArray r5 = r0.scratch
            byte[] r5 = r5.getData()
            byte r5 = r5[r16]
            r5 = r5 & r13
            r2 = r2 | r5
            long r5 = r0.clusterTimecodeUs
            long r7 = (long) r2
            long r7 = r0.scaleTimecodeToUs(r7)
            long r5 = r5 + r7
            r0.blockTimeUs = r5
            int r2 = r3.type
            r10 = 2
            if (r2 == r10) goto L_0x028e
            if (r1 != r4) goto L_0x028b
            androidx.media3.common.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.getData()
            byte r2 = r2[r10]
            r5 = 128(0x80, float:1.794E-43)
            r2 = r2 & r5
            if (r2 != r5) goto L_0x028b
            goto L_0x028e
        L_0x028b:
            r2 = r17
            goto L_0x0290
        L_0x028e:
            r2 = r16
        L_0x0290:
            r0.blockFlags = r2
            r0.blockState = r10
            r2 = r17
            r0.blockSampleIndex = r2
            goto L_0x02ae
        L_0x0299:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected lacing value: "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedContainer(r0, r5)
            throw r0
        L_0x02ac:
            r16 = r9
        L_0x02ae:
            if (r1 != r4) goto L_0x02e3
        L_0x02b0:
            int r1 = r0.blockSampleIndex
            int r2 = r0.blockSampleCount
            if (r1 >= r2) goto L_0x02df
            int[] r2 = r0.blockSampleSizes
            r1 = r2[r1]
            r7 = r26
            r2 = 0
            int r5 = r0.writeSampleData(r7, r3, r1, r2)
            long r1 = r0.blockTimeUs
            int r4 = r0.blockSampleIndex
            int r6 = r3.defaultSampleDurationNs
            int r4 = r4 * r6
            int r4 = r4 / 1000
            long r8 = (long) r4
            long r1 = r1 + r8
            int r4 = r0.blockFlags
            r6 = 0
            r21 = r1
            r1 = r3
            r2 = r21
            r0.commitSampleToOutput(r1, r2, r4, r5, r6)
            int r2 = r0.blockSampleIndex
            int r2 = r2 + 1
            r0.blockSampleIndex = r2
            r3 = r1
            goto L_0x02b0
        L_0x02df:
            r2 = 0
            r0.blockState = r2
            return
        L_0x02e3:
            r7 = r26
            r1 = r3
        L_0x02e6:
            int r2 = r0.blockSampleIndex
            int r3 = r0.blockSampleCount
            if (r2 >= r3) goto L_0x02fe
            int[] r3 = r0.blockSampleSizes
            r4 = r3[r2]
            r5 = r16
            int r4 = r0.writeSampleData(r7, r1, r4, r5)
            r3[r2] = r4
            int r2 = r0.blockSampleIndex
            int r2 = r2 + r5
            r0.blockSampleIndex = r2
            goto L_0x02e6
        L_0x02fe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.binaryElement(int, int, androidx.media3.extractor.ExtractorInput):void");
    }

    public void endMasterElement(int i2) {
        assertInitialized();
        if (i2 != 160) {
            if (i2 == 174) {
                Track track = (Track) Assertions.checkStateNotNull(this.currentTrack);
                String str = track.codecId;
                if (str != null) {
                    if (isCodecSupported(str)) {
                        track.initializeOutput(this.extractorOutput, track.number);
                        this.tracks.put(track.number, track);
                    }
                    this.currentTrack = null;
                    return;
                }
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", (Throwable) null);
            } else if (i2 == 19899) {
                int i7 = this.seekEntryId;
                if (i7 != -1) {
                    long j2 = this.seekEntryPosition;
                    if (j2 != -1) {
                        if (i7 == 475249515) {
                            this.cuesContentPosition = j2;
                            return;
                        }
                        return;
                    }
                }
                throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", (Throwable) null);
            } else if (i2 == 25152) {
                assertInTrackEntry(i2);
                Track track2 = this.currentTrack;
                if (!track2.hasContentEncryption) {
                    return;
                }
                if (track2.cryptoData != null) {
                    track2.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, Encode.ContentType.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", (Throwable) null);
            } else if (i2 == 28032) {
                assertInTrackEntry(i2);
                Track track3 = this.currentTrack;
                if (track3.hasContentEncryption && track3.sampleStrippedBytes != null) {
                    throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", (Throwable) null);
                }
            } else if (i2 == 357149030) {
                if (this.timecodeScale == -9223372036854775807L) {
                    this.timecodeScale = 1000000;
                }
                long j3 = this.durationTimecode;
                if (j3 != -9223372036854775807L) {
                    this.durationUs = scaleTimecodeToUs(j3);
                }
            } else if (i2 != 374648427) {
                if (i2 == 475249515) {
                    if (!this.sentSeekMap) {
                        this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
                        this.sentSeekMap = true;
                    }
                    this.cueTimesUs = null;
                    this.cueClusterPositions = null;
                }
            } else if (this.tracks.size() != 0) {
                this.extractorOutput.endTracks();
            } else {
                throw ParserException.createForMalformedContainer("No valid tracks were found", (Throwable) null);
            }
        } else if (this.blockState == 2) {
            Track track4 = this.tracks.get(this.blockTrackNumber);
            track4.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && "A_OPUS".equals(track4.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int i8 = 0;
            for (int i10 = 0; i10 < this.blockSampleCount; i10++) {
                i8 += this.blockSampleSizes[i10];
            }
            int i11 = 0;
            while (i11 < this.blockSampleCount) {
                long j8 = this.blockTimeUs + ((long) ((track4.defaultSampleDurationNs * i11) / 1000));
                int i12 = this.blockFlags;
                if (i11 == 0 && !this.blockHasReferenceBlock) {
                    i12 |= 1;
                }
                int i13 = this.blockSampleSizes[i11];
                int i14 = i8 - i13;
                commitSampleToOutput(track4, j8, i12, i13, i14);
                i11++;
                i8 = i14;
            }
            this.blockState = 0;
        }
    }

    public void floatElement(int i2, double d) {
        if (i2 == 181) {
            getCurrentTrack(i2).sampleRate = (int) d;
        } else if (i2 != 17545) {
            switch (i2) {
                case 21969:
                    getCurrentTrack(i2).primaryRChromaticityX = (float) d;
                    return;
                case 21970:
                    getCurrentTrack(i2).primaryRChromaticityY = (float) d;
                    return;
                case 21971:
                    getCurrentTrack(i2).primaryGChromaticityX = (float) d;
                    return;
                case 21972:
                    getCurrentTrack(i2).primaryGChromaticityY = (float) d;
                    return;
                case 21973:
                    getCurrentTrack(i2).primaryBChromaticityX = (float) d;
                    return;
                case 21974:
                    getCurrentTrack(i2).primaryBChromaticityY = (float) d;
                    return;
                case 21975:
                    getCurrentTrack(i2).whitePointChromaticityX = (float) d;
                    return;
                case 21976:
                    getCurrentTrack(i2).whitePointChromaticityY = (float) d;
                    return;
                case 21977:
                    getCurrentTrack(i2).maxMasteringLuminance = (float) d;
                    return;
                case 21978:
                    getCurrentTrack(i2).minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i2) {
                        case 30323:
                            getCurrentTrack(i2).projectionPoseYaw = (float) d;
                            return;
                        case 30324:
                            getCurrentTrack(i2).projectionPosePitch = (float) d;
                            return;
                        case 30325:
                            getCurrentTrack(i2).projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    public Track getCurrentTrack(int i2) {
        assertInTrackEntry(i2);
        return this.currentTrack;
    }

    public int getElementType(int i2) {
        switch (i2) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 238:
            case 241:
            case 251:
            case 16871:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21938:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 21998:
            case 22186:
            case 22203:
            case 25188:
            case 30114:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 21358:
            case 2274716:
                return 3;
            case MOCRLang.GURMUKHI /*160*/:
            case 166:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 16868:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30113:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case MOCRLang.PUNJABI /*161*/:
            case 163:
            case 165:
            case 16877:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    public void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i2) {
        if (track.blockAddIdType == 1685485123 || track.blockAddIdType == 1685480259) {
            byte[] bArr = new byte[i2];
            track.dolbyVisionConfigBytes = bArr;
            extractorInput.readFully(bArr, 0, i2);
            return;
        }
        extractorInput.skipFully(i2);
    }

    public void handleBlockAdditionalData(Track track, int i2, ExtractorInput extractorInput, int i7) {
        if (i2 != 4 || !"V_VP9".equals(track.codecId)) {
            extractorInput.skipFully(i7);
            return;
        }
        this.supplementalData.reset(i7);
        extractorInput.readFully(this.supplementalData.getData(), 0, i7);
    }

    public final void init(ExtractorOutput extractorOutput2) {
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
    }

    public void integerElement(int i2, long j2) {
        if (i2 != 20529) {
            if (i2 != 20530) {
                boolean z = false;
                switch (i2) {
                    case 131:
                        getCurrentTrack(i2).type = (int) j2;
                        return;
                    case 136:
                        Track currentTrack2 = getCurrentTrack(i2);
                        if (j2 == 1) {
                            z = true;
                        }
                        currentTrack2.flagDefault = z;
                        return;
                    case 155:
                        this.blockDurationUs = scaleTimecodeToUs(j2);
                        return;
                    case 159:
                        getCurrentTrack(i2).channelCount = (int) j2;
                        return;
                    case 176:
                        getCurrentTrack(i2).width = (int) j2;
                        return;
                    case 179:
                        assertInCues(i2);
                        this.cueTimesUs.add(scaleTimecodeToUs(j2));
                        return;
                    case 186:
                        getCurrentTrack(i2).height = (int) j2;
                        return;
                    case 215:
                        getCurrentTrack(i2).number = (int) j2;
                        return;
                    case 231:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j2);
                        return;
                    case 238:
                        this.blockAdditionalId = (int) j2;
                        return;
                    case 241:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            assertInCues(i2);
                            this.cueClusterPositions.add(j2);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case 251:
                        this.blockHasReferenceBlock = true;
                        return;
                    case 16871:
                        int unused = getCurrentTrack(i2).blockAddIdType = (int) j2;
                        return;
                    case 16980:
                        if (j2 != 3) {
                            throw ParserException.createForMalformedContainer("ContentCompAlgo " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case 17029:
                        if (j2 < 1 || j2 > 2) {
                            throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case 17143:
                        if (j2 != 1) {
                            throw ParserException.createForMalformedContainer("EBMLReadVersion " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case 18401:
                        if (j2 != 5) {
                            throw ParserException.createForMalformedContainer("ContentEncAlgo " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case 18408:
                        if (j2 != 1) {
                            throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case 21420:
                        this.seekEntryPosition = j2 + this.segmentContentPosition;
                        return;
                    case 21432:
                        int i7 = (int) j2;
                        assertInTrackEntry(i2);
                        if (i7 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i7 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i7 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i7 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case 21680:
                        getCurrentTrack(i2).displayWidth = (int) j2;
                        return;
                    case 21682:
                        getCurrentTrack(i2).displayUnit = (int) j2;
                        return;
                    case 21690:
                        getCurrentTrack(i2).displayHeight = (int) j2;
                        return;
                    case 21930:
                        Track currentTrack3 = getCurrentTrack(i2);
                        if (j2 == 1) {
                            z = true;
                        }
                        currentTrack3.flagForced = z;
                        return;
                    case 21938:
                        assertInTrackEntry(i2);
                        Track track = this.currentTrack;
                        track.hasColorInfo = true;
                        track.bitsPerChannel = (int) j2;
                        return;
                    case 21998:
                        getCurrentTrack(i2).maxBlockAdditionId = (int) j2;
                        return;
                    case 22186:
                        getCurrentTrack(i2).codecDelayNs = j2;
                        return;
                    case 22203:
                        getCurrentTrack(i2).seekPreRollNs = j2;
                        return;
                    case 25188:
                        getCurrentTrack(i2).audioBitDepth = (int) j2;
                        return;
                    case 30114:
                        this.blockGroupDiscardPaddingNs = j2;
                        return;
                    case 30321:
                        assertInTrackEntry(i2);
                        int i8 = (int) j2;
                        if (i8 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i8 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i8 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i8 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case 2352003:
                        getCurrentTrack(i2).defaultSampleDurationNs = (int) j2;
                        return;
                    case 2807729:
                        this.timecodeScale = j2;
                        return;
                    default:
                        switch (i2) {
                            case 21945:
                                assertInTrackEntry(i2);
                                int i10 = (int) j2;
                                if (i10 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i10 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case 21946:
                                assertInTrackEntry(i2);
                                int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j2);
                                if (isoTransferCharacteristicsToColorTransfer != -1) {
                                    this.currentTrack.colorTransfer = isoTransferCharacteristicsToColorTransfer;
                                    return;
                                }
                                return;
                            case 21947:
                                assertInTrackEntry(i2);
                                this.currentTrack.hasColorInfo = true;
                                int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j2);
                                if (isoColorPrimariesToColorSpace != -1) {
                                    this.currentTrack.colorSpace = isoColorPrimariesToColorSpace;
                                    return;
                                }
                                return;
                            case 21948:
                                getCurrentTrack(i2).maxContentLuminance = (int) j2;
                                return;
                            case 21949:
                                getCurrentTrack(i2).maxFrameAverageLuminance = (int) j2;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j2 != 1) {
                throw ParserException.createForMalformedContainer("ContentEncodingScope " + j2 + " not supported", (Throwable) null);
            }
        } else if (j2 != 0) {
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j2 + " not supported", (Throwable) null);
        }
    }

    public boolean isLevel1Element(int i2) {
        if (i2 == 357149030 || i2 == 524531317 || i2 == 475249515 || i2 == 374648427) {
            return true;
        }
        return false;
    }

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i2 = 0; i2 < this.tracks.size(); i2++) {
            Track valueAt = this.tracks.valueAt(i2);
            valueAt.assertOutputInitialized();
            valueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    public void seek(long j2, long j3) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int i2 = 0; i2 < this.tracks.size(); i2++) {
            this.tracks.valueAt(i2).reset();
        }
    }

    public final boolean sniff(ExtractorInput extractorInput) {
        return new Sniffer().sniff(extractorInput);
    }

    public void startMasterElement(int i2, long j2, long j3) {
        assertInitialized();
        if (i2 == 160) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0;
        } else if (i2 == 174) {
            Track track = new Track();
            this.currentTrack = track;
            track.isWebm = this.isWebm;
        } else if (i2 == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i2 == 19899) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i2 == 20533) {
            getCurrentTrack(i2).hasContentEncryption = true;
        } else if (i2 == 21968) {
            getCurrentTrack(i2).hasColorInfo = true;
        } else if (i2 == 408125543) {
            long j8 = this.segmentContentPosition;
            if (j8 == -1 || j8 == j2) {
                this.segmentContentPosition = j2;
                this.segmentContentSize = j3;
                return;
            }
            throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", (Throwable) null);
        } else if (i2 == 475249515) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i2 != 524531317 || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    public void stringElement(int i2, String str) {
        if (i2 == 134) {
            getCurrentTrack(i2).codecId = str;
        } else if (i2 != 17026) {
            if (i2 == 21358) {
                getCurrentTrack(i2).name = str;
            } else if (i2 == 2274716) {
                String unused = getCurrentTrack(i2).language = str;
            }
        } else if ("webm".equals(str) || "matroska".equals(str)) {
            this.isWebm = Objects.equals(str, "webm");
        } else {
            throw ParserException.createForMalformedContainer("DocType " + str + " not supported", (Throwable) null);
        }
    }

    public MatroskaExtractor(EbmlReader ebmlReader, int i2, SubtitleParser.Factory factory) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.subtitleParserFactory = factory;
        boolean z = false;
        this.seekForCuesEnabled = (i2 & 1) == 0;
        this.parseSubtitlesDuringExtraction = (i2 & 2) == 0 ? true : z;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
    }

    public final void release() {
    }
}
