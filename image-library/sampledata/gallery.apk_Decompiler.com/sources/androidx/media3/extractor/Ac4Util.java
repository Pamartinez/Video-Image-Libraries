package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Ac4Util {
    private static final int[] SAMPLE_COUNT = {2002, 2000, 1920, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Ac4Presentation {
        public int channelMode;
        public boolean hasBackChannels;
        public boolean isChannelCoded;
        public int level;
        public int numOfUmxObjects;
        public int topChannelPairs;
        public int version;

        private Ac4Presentation() {
            this.isChannelCoded = true;
            this.channelMode = -1;
            this.numOfUmxObjects = -1;
            this.hasBackChannels = true;
            this.topChannelPairs = 2;
            this.version = 1;
            this.level = 0;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        private SyncFrameInfo(int i2, int i7, int i8, int i10, int i11) {
            this.bitstreamVersion = i2;
            this.channelCount = i7;
            this.sampleRate = i8;
            this.frameSize = i10;
            this.sampleCount = i11;
        }
    }

    private static String createCodecsString(int i2, int i7, int i8) {
        return Util.formatInvariant("ac-4.%02d.%02d.%02d", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8));
    }

    public static void getAc4SampleHeader(int i2, ParsableByteArray parsableByteArray) {
        parsableByteArray.reset(7);
        byte[] data = parsableByteArray.getData();
        data[0] = -84;
        data[1] = 64;
        data[2] = -1;
        data[3] = -1;
        data[4] = (byte) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
        data[5] = (byte) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        data[6] = (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
    }

    private static int getAdjustedChannelCount(int i2, boolean z, int i7) {
        int channelCountFromChannelMode = getChannelCountFromChannelMode(i2);
        if (i2 != 11 && i2 != 12 && i2 != 13 && i2 != 14) {
            return channelCountFromChannelMode;
        }
        if (!z) {
            channelCountFromChannelMode -= 2;
        }
        if (i7 == 0) {
            return channelCountFromChannelMode - 4;
        }
        if (i7 != 1) {
            return channelCountFromChannelMode;
        }
        return channelCountFromChannelMode - 2;
    }

    private static int getChannelCountFromChannelMode(int i2) {
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 6;
            case 5:
            case 7:
            case 9:
                return 7;
            case 6:
            case 8:
            case 10:
                return 8;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 24;
            default:
                return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0119, code lost:
        if (r5 == 2) goto L_0x011b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02d7  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x030d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.common.Format parseAc4AnnexEFormat(androidx.media3.common.util.ParsableByteArray r20, java.lang.String r21, java.lang.String r22, androidx.media3.common.DrmInitData r23) {
        /*
            androidx.media3.common.util.ParsableBitArray r0 = new androidx.media3.common.util.ParsableBitArray
            r0.<init>()
            r1 = r20
            r0.reset((androidx.media3.common.util.ParsableByteArray) r1)
            int r1 = r0.bitsLeft()
            r2 = 3
            int r3 = r0.readBits(r2)
            r4 = 1
            if (r3 > r4) goto L_0x0314
            r5 = 7
            int r6 = r0.readBits(r5)
            boolean r7 = r0.readBit()
            if (r7 == 0) goto L_0x0025
            r7 = 48000(0xbb80, float:6.7262E-41)
            goto L_0x0028
        L_0x0025:
            r7 = 44100(0xac44, float:6.1797E-41)
        L_0x0028:
            r8 = 4
            r0.skipBits(r8)
            r9 = 9
            int r9 = r0.readBits(r9)
            r10 = 16
            if (r6 <= r4) goto L_0x0060
            if (r3 == 0) goto L_0x004d
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x0060
            r0.skipBits(r10)
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x0060
            r11 = 128(0x80, float:1.794E-43)
            r0.skipBits(r11)
            goto L_0x0060
        L_0x004d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid AC-4 DSI version: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0060:
            if (r3 != r4) goto L_0x0073
            boolean r11 = skipDsiBitrate(r0)
            if (r11 == 0) goto L_0x006c
            r0.byteAlign()
            goto L_0x0073
        L_0x006c:
            java.lang.String r0 = "Invalid AC-4 DSI bitrate."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0073:
            androidx.media3.extractor.Ac4Util$Ac4Presentation r11 = new androidx.media3.extractor.Ac4Util$Ac4Presentation
            r12 = 0
            r11.<init>()
            r13 = 0
        L_0x007a:
            r15 = 6
            r12 = 8
            r5 = 2
            if (r13 >= r9) goto L_0x0278
            r8 = 5
            if (r3 != 0) goto L_0x0099
            boolean r9 = r0.readBit()
            int r16 = r0.readBits(r8)
            int r17 = r0.readBits(r8)
            r18 = r12
            r14 = r16
            r5 = r17
            r4 = 0
            r10 = 0
            r12 = 0
            goto L_0x00d1
        L_0x0099:
            int r14 = r0.readBits(r12)
            int r4 = r0.readBits(r12)
            r18 = r12
            r12 = 255(0xff, float:3.57E-43)
            if (r4 != r12) goto L_0x00ac
            int r12 = r0.readBits(r10)
            int r4 = r4 + r12
        L_0x00ac:
            if (r14 <= r5) goto L_0x00b9
            int r4 = r4 * 8
            r0.skipBits(r4)
            int r13 = r13 + 1
            r4 = 1
            r5 = 7
            r8 = 4
            goto L_0x007a
        L_0x00b9:
            int r9 = r0.bitsLeft()
            int r9 = r1 - r9
            int r9 = r9 / 8
            int r12 = r0.readBits(r8)
            r10 = 31
            if (r12 != r10) goto L_0x00cb
            r10 = 1
            goto L_0x00cc
        L_0x00cb:
            r10 = 0
        L_0x00cc:
            r5 = r14
            r14 = r12
            r12 = r10
            r10 = r9
            r9 = 0
        L_0x00d1:
            r11.version = r5
            r8 = 15
            if (r9 != 0) goto L_0x00de
            if (r12 != 0) goto L_0x00de
            if (r14 != r15) goto L_0x00de
            r2 = 1
            goto L_0x01f0
        L_0x00de:
            int r15 = r0.readBits(r2)
            r11.level = r15
            boolean r15 = r0.readBit()
            if (r15 == 0) goto L_0x00ee
            r15 = 5
            r0.skipBits(r15)
        L_0x00ee:
            r15 = 2
            r0.skipBits(r15)
            r2 = 1
            if (r3 != r2) goto L_0x00fa
            if (r5 == r2) goto L_0x00fc
            if (r5 != r15) goto L_0x00fa
            goto L_0x00fc
        L_0x00fa:
            r15 = 5
            goto L_0x0100
        L_0x00fc:
            r0.skipBits(r15)
            goto L_0x00fa
        L_0x0100:
            r0.skipBits(r15)
            r15 = 10
            r0.skipBits(r15)
            if (r3 != r2) goto L_0x0172
            if (r5 <= 0) goto L_0x0112
            boolean r15 = r0.readBit()
            r11.isChannelCoded = r15
        L_0x0112:
            boolean r15 = r11.isChannelCoded
            if (r15 == 0) goto L_0x0144
            if (r5 == r2) goto L_0x011b
            r15 = 2
            if (r5 != r15) goto L_0x013d
        L_0x011b:
            r15 = 5
            int r2 = r0.readBits(r15)
            if (r2 < 0) goto L_0x0126
            if (r2 > r8) goto L_0x0126
            r11.channelMode = r2
        L_0x0126:
            r15 = 11
            if (r2 < r15) goto L_0x013c
            r15 = 14
            if (r2 > r15) goto L_0x013c
            boolean r2 = r0.readBit()
            r11.hasBackChannels = r2
            r15 = 2
            int r2 = r0.readBits(r15)
            r11.topChannelPairs = r2
            goto L_0x013d
        L_0x013c:
            r15 = 2
        L_0x013d:
            r2 = 24
            r0.skipBits(r2)
            r2 = 1
            goto L_0x0145
        L_0x0144:
            r15 = 2
        L_0x0145:
            if (r5 == r2) goto L_0x0149
            if (r5 != r15) goto L_0x0172
        L_0x0149:
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0158
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0158
            r0.skipBits(r15)
        L_0x0158:
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0172
            r0.skipBit()
            r2 = r18
            int r15 = r0.readBits(r2)
            r8 = 0
        L_0x0168:
            if (r8 >= r15) goto L_0x0172
            r0.skipBits(r2)
            int r8 = r8 + 1
            r2 = 8
            goto L_0x0168
        L_0x0172:
            if (r9 != 0) goto L_0x01e0
            if (r12 == 0) goto L_0x0178
            goto L_0x01e0
        L_0x0178:
            r0.skipBit()
            if (r14 == 0) goto L_0x01ca
            r2 = 1
            if (r14 == r2) goto L_0x01ca
            r15 = 2
            if (r14 == r15) goto L_0x01ca
            r2 = 3
            if (r14 == r2) goto L_0x01b4
            r2 = 4
            if (r14 == r2) goto L_0x01b4
            r15 = 5
            if (r14 == r15) goto L_0x019c
            r2 = 7
            int r8 = r0.readBits(r2)
            r2 = 0
        L_0x0192:
            if (r2 >= r8) goto L_0x01e9
            r9 = 8
            r0.skipBits(r9)
            int r2 = r2 + 1
            goto L_0x0192
        L_0x019c:
            if (r5 != 0) goto L_0x01a2
            parseDsiSubstream(r0, r11)
            goto L_0x01e9
        L_0x01a2:
            r2 = 3
            int r8 = r0.readBits(r2)
            r2 = 0
        L_0x01a8:
            r19 = 2
            int r9 = r8 + 2
            if (r2 >= r9) goto L_0x01e9
            parseDsiSubstreamGroup(r0, r11)
            int r2 = r2 + 1
            goto L_0x01a8
        L_0x01b4:
            if (r5 != 0) goto L_0x01c0
            r2 = 0
            r8 = 3
        L_0x01b8:
            if (r2 >= r8) goto L_0x01e9
            parseDsiSubstream(r0, r11)
            int r2 = r2 + 1
            goto L_0x01b8
        L_0x01c0:
            r2 = 0
        L_0x01c1:
            r8 = 3
            if (r2 >= r8) goto L_0x01e9
            parseDsiSubstreamGroup(r0, r11)
            int r2 = r2 + 1
            goto L_0x01c1
        L_0x01ca:
            if (r5 != 0) goto L_0x01d6
            r2 = 0
            r15 = 2
        L_0x01ce:
            if (r2 >= r15) goto L_0x01e9
            parseDsiSubstream(r0, r11)
            int r2 = r2 + 1
            goto L_0x01ce
        L_0x01d6:
            r2 = 0
        L_0x01d7:
            r15 = 2
            if (r2 >= r15) goto L_0x01e9
            parseDsiSubstreamGroup(r0, r11)
            int r2 = r2 + 1
            goto L_0x01d7
        L_0x01e0:
            if (r5 != 0) goto L_0x01e6
            parseDsiSubstream(r0, r11)
            goto L_0x01e9
        L_0x01e6:
            parseDsiSubstreamGroup(r0, r11)
        L_0x01e9:
            r0.skipBit()
            boolean r2 = r0.readBit()
        L_0x01f0:
            if (r2 == 0) goto L_0x0202
            r2 = 7
            int r2 = r0.readBits(r2)
            r8 = 0
        L_0x01f8:
            if (r8 >= r2) goto L_0x0202
            r9 = 15
            r0.skipBits(r9)
            int r8 = r8 + 1
            goto L_0x01f8
        L_0x0202:
            if (r5 <= 0) goto L_0x023e
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0218
            boolean r2 = skipDsiBitrate(r0)
            if (r2 == 0) goto L_0x0211
            goto L_0x0218
        L_0x0211:
            java.lang.String r0 = "Can't parse bitrate DSI."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0218:
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x023e
            r0.byteAlign()
            r2 = 16
            int r2 = r0.readBits(r2)
            r0.skipBytes(r2)
            r15 = 5
            int r2 = r0.readBits(r15)
            r12 = 0
        L_0x0230:
            if (r12 >= r2) goto L_0x023e
            r8 = 3
            r0.skipBits(r8)
            r9 = 8
            r0.skipBits(r9)
            int r12 = r12 + 1
            goto L_0x0230
        L_0x023e:
            r9 = 8
            r0.byteAlign()
            r2 = 1
            if (r3 != r2) goto L_0x025b
            int r2 = r0.bitsLeft()
            int r1 = r1 - r2
            int r1 = r1 / r9
            int r1 = r1 - r10
            if (r4 < r1) goto L_0x0254
            int r4 = r4 - r1
            r0.skipBytes(r4)
            goto L_0x025b
        L_0x0254:
            java.lang.String r0 = "pres_bytes is smaller than presentation bytes read."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x025b:
            boolean r0 = r11.isChannelCoded
            if (r0 == 0) goto L_0x0279
            int r0 = r11.channelMode
            r1 = -1
            if (r0 == r1) goto L_0x0265
            goto L_0x0279
        L_0x0265:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Can't determine channel mode of presentation "
            r0.<init>(r1)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0278:
            r9 = r12
        L_0x0279:
            boolean r0 = r11.isChannelCoded
            if (r0 == 0) goto L_0x0288
            int r0 = r11.channelMode
            boolean r1 = r11.hasBackChannels
            int r2 = r11.topChannelPairs
            int r14 = getAdjustedChannelCount(r0, r1, r2)
            goto L_0x02d5
        L_0x0288:
            int r0 = r11.numOfUmxObjects
            if (r0 <= 0) goto L_0x029d
            r17 = 1
            int r0 = r0 + 1
            int r1 = r11.level
            r2 = 4
            if (r1 != r2) goto L_0x029b
            r1 = 17
            if (r0 != r1) goto L_0x029b
            r0 = 21
        L_0x029b:
            r14 = r0
            goto L_0x02d5
        L_0x029d:
            int r0 = r11.level
            if (r0 == 0) goto L_0x02d3
            r2 = 1
            if (r0 == r2) goto L_0x02d1
            r15 = 2
            if (r0 == r15) goto L_0x02cf
            r2 = 3
            if (r0 == r2) goto L_0x02cc
            r2 = 4
            if (r0 == r2) goto L_0x02c9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "AC-4 level "
            r0.<init>(r1)
            int r1 = r11.level
            r0.append(r1)
            java.lang.String r1 = " has not been defined."
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "Ac4Util"
            androidx.media3.common.util.Log.w(r1, r0)
        L_0x02c7:
            r14 = r15
            goto L_0x02d5
        L_0x02c9:
            r14 = 12
            goto L_0x02d5
        L_0x02cc:
            r14 = 10
            goto L_0x02d5
        L_0x02cf:
            r14 = r9
            goto L_0x02d5
        L_0x02d1:
            r14 = 6
            goto L_0x02d5
        L_0x02d3:
            r15 = 2
            goto L_0x02c7
        L_0x02d5:
            if (r14 <= 0) goto L_0x030d
            int r0 = r11.version
            int r1 = r11.level
            java.lang.String r0 = createCodecsString(r6, r0, r1)
            androidx.media3.common.Format$Builder r1 = new androidx.media3.common.Format$Builder
            r1.<init>()
            r2 = r21
            androidx.media3.common.Format$Builder r1 = r1.setId((java.lang.String) r2)
            java.lang.String r2 = "audio/ac4"
            androidx.media3.common.Format$Builder r1 = r1.setSampleMimeType(r2)
            androidx.media3.common.Format$Builder r1 = r1.setChannelCount(r14)
            androidx.media3.common.Format$Builder r1 = r1.setSampleRate(r7)
            r2 = r23
            androidx.media3.common.Format$Builder r1 = r1.setDrmInitData(r2)
            r2 = r22
            androidx.media3.common.Format$Builder r1 = r1.setLanguage(r2)
            androidx.media3.common.Format$Builder r0 = r1.setCodecs(r0)
            androidx.media3.common.Format r0 = r0.build()
            return r0
        L_0x030d:
            java.lang.String r0 = "Cannot determine channel count of presentation."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0314:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported AC-4 DSI version: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.Ac4Util.parseAc4AnnexEFormat(androidx.media3.common.util.ParsableByteArray, java.lang.String, java.lang.String, androidx.media3.common.DrmInitData):androidx.media3.common.Format");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0082, code lost:
        if (r11 != 11) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0089, code lost:
        if (r11 != 11) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008e, code lost:
        if (r11 != 8) goto L_0x0091;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.Ac4Util.SyncFrameInfo parseAc4SyncframeInfo(androidx.media3.common.util.ParsableBitArray r11) {
        /*
            r0 = 16
            int r1 = r11.readBits(r0)
            int r0 = r11.readBits(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 4
            if (r0 != r2) goto L_0x0018
            r0 = 24
            int r0 = r11.readBits(r0)
            r2 = 7
            goto L_0x0019
        L_0x0018:
            r2 = r3
        L_0x0019:
            int r0 = r0 + r2
            r2 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r2) goto L_0x0021
            int r0 = r0 + 2
        L_0x0021:
            r8 = r0
            r0 = 2
            int r1 = r11.readBits(r0)
            r2 = 3
            if (r1 != r2) goto L_0x002f
            int r4 = readVariableBits(r11, r0)
            int r1 = r1 + r4
        L_0x002f:
            r5 = r1
            r1 = 10
            int r1 = r11.readBits(r1)
            boolean r4 = r11.readBit()
            if (r4 == 0) goto L_0x0045
            int r4 = r11.readBits(r2)
            if (r4 <= 0) goto L_0x0045
            r11.skipBits(r0)
        L_0x0045:
            boolean r4 = r11.readBit()
            r6 = 44100(0xac44, float:6.1797E-41)
            r7 = 48000(0xbb80, float:6.7262E-41)
            if (r4 == 0) goto L_0x0053
            r4 = r7
            goto L_0x0055
        L_0x0053:
            r4 = r7
            r7 = r6
        L_0x0055:
            int r11 = r11.readBits(r3)
            if (r7 != r6) goto L_0x0065
            r6 = 13
            if (r11 != r6) goto L_0x0065
            int[] r0 = SAMPLE_COUNT
            r11 = r0[r11]
        L_0x0063:
            r9 = r11
            goto L_0x0095
        L_0x0065:
            if (r7 != r4) goto L_0x0093
            int[] r4 = SAMPLE_COUNT
            int r6 = r4.length
            if (r11 >= r6) goto L_0x0093
            r4 = r4[r11]
            int r1 = r1 % 5
            r6 = 8
            r9 = 1
            if (r1 == r9) goto L_0x008c
            r9 = 11
            if (r1 == r0) goto L_0x0087
            if (r1 == r2) goto L_0x008c
            if (r1 == r3) goto L_0x007e
            goto L_0x0091
        L_0x007e:
            if (r11 == r2) goto L_0x0084
            if (r11 == r6) goto L_0x0084
            if (r11 != r9) goto L_0x0091
        L_0x0084:
            int r11 = r4 + 1
            goto L_0x0063
        L_0x0087:
            if (r11 == r6) goto L_0x0084
            if (r11 != r9) goto L_0x0091
            goto L_0x0084
        L_0x008c:
            if (r11 == r2) goto L_0x0084
            if (r11 != r6) goto L_0x0091
            goto L_0x0084
        L_0x0091:
            r9 = r4
            goto L_0x0095
        L_0x0093:
            r11 = 0
            goto L_0x0063
        L_0x0095:
            androidx.media3.extractor.Ac4Util$SyncFrameInfo r4 = new androidx.media3.extractor.Ac4Util$SyncFrameInfo
            r6 = 2
            r10 = 0
            r4.<init>(r5, r6, r7, r8, r9)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.Ac4Util.parseAc4SyncframeInfo(androidx.media3.common.util.ParsableBitArray):androidx.media3.extractor.Ac4Util$SyncFrameInfo");
    }

    public static int parseAc4SyncframeSize(byte[] bArr, int i2) {
        int i7 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        byte b = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        if (b == 65535) {
            b = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        } else {
            i7 = 4;
        }
        if (i2 == 44097) {
            i7 += 2;
        }
        return b + i7;
    }

    private static void parseDsiSubstream(ParsableBitArray parsableBitArray, Ac4Presentation ac4Presentation) {
        int readBits = parsableBitArray.readBits(5);
        parsableBitArray.skipBits(2);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(5);
        }
        if (readBits >= 7 && readBits <= 10) {
            parsableBitArray.skipBit();
        }
        if (parsableBitArray.readBit()) {
            int readBits2 = parsableBitArray.readBits(3);
            if (ac4Presentation.channelMode == -1 && readBits >= 0 && readBits <= 15 && (readBits2 == 0 || readBits2 == 1)) {
                ac4Presentation.channelMode = readBits;
            }
            if (parsableBitArray.readBit()) {
                skipDsiLanguage(parsableBitArray);
            }
        }
    }

    private static void parseDsiSubstreamGroup(ParsableBitArray parsableBitArray, Ac4Presentation ac4Presentation) {
        parsableBitArray.skipBits(2);
        boolean readBit = parsableBitArray.readBit();
        int readBits = parsableBitArray.readBits(8);
        for (int i2 = 0; i2 < readBits; i2++) {
            parsableBitArray.skipBits(2);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(5);
            }
            if (readBit) {
                parsableBitArray.skipBits(24);
            } else {
                if (parsableBitArray.readBit()) {
                    if (!parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(4);
                    }
                    ac4Presentation.numOfUmxObjects = parsableBitArray.readBits(6) + 1;
                }
                parsableBitArray.skipBits(4);
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(3);
            if (parsableBitArray.readBit()) {
                skipDsiLanguage(parsableBitArray);
            }
        }
    }

    private static int readVariableBits(ParsableBitArray parsableBitArray, int i2) {
        int i7 = 0;
        while (true) {
            int readBits = parsableBitArray.readBits(i2) + i7;
            if (!parsableBitArray.readBit()) {
                return readBits;
            }
            i7 = (readBits + 1) << i2;
        }
    }

    private static boolean skipDsiBitrate(ParsableBitArray parsableBitArray) {
        if (parsableBitArray.bitsLeft() < 66) {
            return false;
        }
        parsableBitArray.skipBits(66);
        return true;
    }

    private static void skipDsiLanguage(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(6);
        if (readBits < 2 || readBits > 42) {
            throw ParserException.createForUnsupportedContainerFeature(String.format("Invalid language tag bytes number: %d. Must be between 2 and 42.", new Object[]{Integer.valueOf(readBits)}));
        }
        parsableBitArray.skipBits(readBits * 8);
    }
}
