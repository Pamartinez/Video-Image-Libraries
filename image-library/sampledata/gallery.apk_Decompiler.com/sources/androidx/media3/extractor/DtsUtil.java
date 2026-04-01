package androidx.media3.extractor;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.Util;
import com.samsung.android.imagetranslation.common.Config;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] SAMPLE_RATE_BY_INDEX = {Encode.BitRate.VIDEO_HD_BITRATE, Config.MAX_RESOLUTION_SUPPORTED, 32000, 64000, 128000, 22050, 44100, 88200, 176400, 352800, 12000, 24000, 48000, 96000, 192000, 384000};
    private static final int[] SAMPLE_RATE_BY_SFREQ = {-1, Encode.BitRate.VIDEO_HD_BITRATE, Config.MAX_RESOLUTION_SUPPORTED, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
    private static final int[] UHD_AUDIO_CHUNK_ID_LENGTH_TABLE = {2, 4, 6, 8};
    private static final int[] UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE = {9, 11, 13, 16};
    private static final int[] UHD_FTOC_PAYLOAD_LENGTH_TABLE = {5, 8, 10, 12};
    private static final int[] UHD_HEADER_SIZE_LENGTH_TABLE = {5, 8, 10, 12};
    private static final int[] UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE = {6, 9, 12, 15};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DtsHeader {
        public final int bitrate;
        public final int channelCount;
        public final long frameDurationUs;
        public final int frameSize;
        public final String mimeType;
        public final int sampleRate;

        private DtsHeader(String str, int i2, int i7, int i8, long j2, int i10) {
            this.mimeType = str;
            this.channelCount = i2;
            this.sampleRate = i7;
            this.frameSize = i8;
            this.frameDurationUs = j2;
            this.bitrate = i10;
        }
    }

    private static void checkCrc(byte[] bArr, int i2) {
        int i7 = i2 - 2;
        if (((bArr[i2 - 1] & 255) | ((bArr[i7] << 8) & 65535)) != Util.crc16(bArr, 0, i7, 65535)) {
            throw ParserException.createForMalformedContainer("CRC check failed", (Throwable) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getDtsFrameSize(byte[] r7) {
        /*
            r0 = 0
            byte r1 = r7[r0]
            r2 = -2
            r3 = 7
            r4 = 6
            r5 = 1
            r6 = 4
            if (r1 == r2) goto L_0x004f
            r2 = -1
            if (r1 == r2) goto L_0x003e
            r2 = 31
            if (r1 == r2) goto L_0x0026
            r1 = 5
            byte r1 = r7[r1]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r7[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            byte r7 = r7[r3]
        L_0x0020:
            r7 = r7 & 240(0xf0, float:3.36E-43)
            int r7 = r7 >> r6
            r7 = r7 | r1
            int r7 = r7 + r5
            goto L_0x005e
        L_0x0026:
            byte r0 = r7[r4]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r7[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 8
            byte r7 = r7[r1]
        L_0x0036:
            r7 = r7 & 60
            int r7 = r7 >> 2
            r7 = r7 | r0
            int r7 = r7 + r5
            r0 = r5
            goto L_0x005e
        L_0x003e:
            byte r0 = r7[r3]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r7[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 9
            byte r7 = r7[r1]
            goto L_0x0036
        L_0x004f:
            byte r1 = r7[r6]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r7[r3]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            byte r7 = r7[r4]
            goto L_0x0020
        L_0x005e:
            if (r0 == 0) goto L_0x0064
            int r7 = r7 * 16
            int r7 = r7 / 14
        L_0x0064:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.DtsUtil.getDtsFrameSize(byte[]):int");
    }

    public static int getFrameType(int i2) {
        if (i2 == 2147385345 || i2 == -25230976 || i2 == 536864768 || i2 == -14745368) {
            return 1;
        }
        if (i2 == 1683496997 || i2 == 622876772) {
            return 2;
        }
        if (i2 == 1078008818 || i2 == -233094848) {
            return 3;
        }
        if (i2 == 1908687592 || i2 == -398277519) {
            return 4;
        }
        return 0;
    }

    private static ParsableBitArray getNormalizedFrame(byte[] bArr) {
        byte b = bArr[0];
        if (b == Byte.MAX_VALUE || b == 100 || b == 64 || b == 113) {
            return new ParsableBitArray(bArr);
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        if (isLittleEndianFrameHeader(copyOf)) {
            for (int i2 = 0; i2 < copyOf.length - 1; i2 += 2) {
                byte b5 = copyOf[i2];
                int i7 = i2 + 1;
                copyOf[i2] = copyOf[i7];
                copyOf[i7] = b5;
            }
        }
        ParsableBitArray parsableBitArray = new ParsableBitArray(copyOf);
        if (copyOf[0] == 31) {
            ParsableBitArray parsableBitArray2 = new ParsableBitArray(copyOf);
            while (parsableBitArray2.bitsLeft() >= 16) {
                parsableBitArray2.skipBits(2);
                parsableBitArray.putInt(parsableBitArray2.readBits(14), 14);
            }
        }
        parsableBitArray.reset(copyOf);
        return parsableBitArray;
    }

    private static boolean isLittleEndianFrameHeader(byte[] bArr) {
        byte b = bArr[0];
        if (b == -2 || b == -1 || b == 37 || b == -14 || b == -24) {
            return true;
        }
        return false;
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        int i2;
        byte b;
        byte b5;
        int i7;
        byte b8;
        byte b10 = bArr[0];
        if (b10 != -2) {
            if (b10 == -1) {
                i7 = (bArr[4] & 7) << 4;
                b8 = bArr[7];
            } else if (b10 != 31) {
                i2 = (bArr[4] & 1) << 6;
                b = bArr[5];
            } else {
                i7 = (bArr[5] & 7) << 4;
                b8 = bArr[6];
            }
            b5 = b8 & 60;
            return (((b5 >> 2) | i7) + 1) * 32;
        }
        i2 = (bArr[5] & 1) << 6;
        b = bArr[4];
        b5 = b & 252;
        return (((b5 >> 2) | i7) + 1) * 32;
    }

    public static Format parseDtsFormat(byte[] bArr, String str, String str2, int i2, String str3, DrmInitData drmInitData) {
        int i7;
        int i8;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(60);
        int i10 = CHANNELS_BY_AMODE[normalizedFrame.readBits(6)];
        int i11 = SAMPLE_RATE_BY_SFREQ[normalizedFrame.readBits(4)];
        int readBits = normalizedFrame.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        if (readBits >= iArr.length) {
            i7 = -1;
        } else {
            i7 = (iArr[readBits] * 1000) / 2;
        }
        normalizedFrame.skipBits(10);
        if (normalizedFrame.readBits(2) > 0) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        return new Format.Builder().setId(str).setContainerMimeType(str3).setSampleMimeType("audio/vnd.dts").setAverageBitrate(i7).setChannelCount(i10 + i8).setSampleRate(i11).setDrmInitData(drmInitData).setLanguage(str2).setRoleFlags(i2).build();
    }

    public static DtsHeader parseDtsHdHeader(byte[] bArr) {
        int i2;
        int i7;
        int i8;
        int i10;
        long j2;
        int i11;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(40);
        int readBits = normalizedFrame.readBits(2);
        if (!normalizedFrame.readBit()) {
            i7 = 16;
            i2 = 8;
        } else {
            i7 = 20;
            i2 = 12;
        }
        normalizedFrame.skipBits(i2);
        int readBits2 = normalizedFrame.readBits(i7) + 1;
        boolean readBit = normalizedFrame.readBit();
        int i12 = -1;
        int i13 = 0;
        if (readBit) {
            i8 = normalizedFrame.readBits(2);
            int readBits3 = (normalizedFrame.readBits(3) + 1) * 512;
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            int readBits4 = normalizedFrame.readBits(3) + 1;
            int readBits5 = normalizedFrame.readBits(3) + 1;
            if (readBits4 == 1 && readBits5 == 1) {
                int i14 = readBits + 1;
                int readBits6 = normalizedFrame.readBits(i14);
                for (int i15 = 0; i15 < i14; i15++) {
                    if (((readBits6 >> i15) & 1) == 1) {
                        normalizedFrame.skipBits(8);
                    }
                }
                if (normalizedFrame.readBit()) {
                    normalizedFrame.skipBits(2);
                    int readBits7 = (normalizedFrame.readBits(2) + 1) << 2;
                    int readBits8 = normalizedFrame.readBits(2) + 1;
                    while (i13 < readBits8) {
                        normalizedFrame.skipBits(readBits7);
                        i13++;
                    }
                }
                i13 = readBits3;
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Multiple audio presentations or assets not supported");
            }
        } else {
            i8 = -1;
        }
        normalizedFrame.skipBits(i7);
        normalizedFrame.skipBits(12);
        if (readBit) {
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(4);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(24);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBytes(normalizedFrame.readBits(10) + 1);
            }
            normalizedFrame.skipBits(5);
            i10 = SAMPLE_RATE_BY_INDEX[normalizedFrame.readBits(4)];
            i12 = normalizedFrame.readBits(8) + 1;
        } else {
            i10 = -2147483647;
        }
        int i16 = i10;
        if (readBit) {
            if (i8 == 0) {
                i11 = 32000;
            } else if (i8 == 1) {
                i11 = 44100;
            } else if (i8 == 2) {
                i11 = 48000;
            } else {
                throw ParserException.createForMalformedContainer("Unsupported reference clock code in DTS HD header: " + i8, (Throwable) null);
            }
            j2 = Util.scaleLargeTimestamp((long) i13, 1000000, (long) i11);
        } else {
            j2 = -9223372036854775807L;
        }
        return new DtsHeader("audio/vnd.dts.hd;profile=lbr", i12, i16, readBits2, j2, 0);
    }

    public static int parseDtsHdHeaderSize(byte[] bArr) {
        int i2;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(42);
        if (normalizedFrame.readBit()) {
            i2 = 12;
        } else {
            i2 = 8;
        }
        return normalizedFrame.readBits(i2) + 1;
    }

    public static DtsHeader parseDtsUhdHeader(byte[] bArr, AtomicInteger atomicInteger) {
        int i2;
        int i7;
        long j2;
        AtomicInteger atomicInteger2;
        int i8;
        int i10;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        int i11 = 0;
        if (normalizedFrame.readBits(32) == 1078008818) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int parseUnsignedVarInt = parseUnsignedVarInt(normalizedFrame, UHD_FTOC_PAYLOAD_LENGTH_TABLE, true) + 1;
        if (i2 == 0) {
            i7 = -2147483647;
            j2 = -9223372036854775807L;
        } else if (normalizedFrame.readBit()) {
            checkCrc(bArr, parseUnsignedVarInt);
            int readBits = normalizedFrame.readBits(2);
            if (readBits == 0) {
                i8 = 512;
            } else if (readBits == 1) {
                i8 = 480;
            } else if (readBits == 2) {
                i8 = 384;
            } else {
                throw ParserException.createForMalformedContainer("Unsupported base duration index in DTS UHD header: " + readBits, (Throwable) null);
            }
            int readBits2 = (normalizedFrame.readBits(3) + 1) * i8;
            int readBits3 = normalizedFrame.readBits(2);
            if (readBits3 == 0) {
                i10 = 32000;
            } else if (readBits3 == 1) {
                i10 = 44100;
            } else if (readBits3 == 2) {
                i10 = 48000;
            } else {
                throw ParserException.createForMalformedContainer("Unsupported clock rate index in DTS UHD header: " + readBits3, (Throwable) null);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            i7 = (1 << normalizedFrame.readBits(2)) * i10;
            j2 = Util.scaleLargeTimestamp((long) readBits2, 1000000, (long) i10);
        } else {
            throw ParserException.createForUnsupportedContainerFeature("Only supports full channel mask-based audio presentation");
        }
        int i12 = i7;
        long j3 = j2;
        int i13 = 0;
        for (int i14 = 0; i14 < i2; i14++) {
            i13 += parseUnsignedVarInt(normalizedFrame, UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE, true);
        }
        if (i2 != 0) {
            atomicInteger2 = atomicInteger;
            atomicInteger2.set(parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_ID_LENGTH_TABLE, true));
        } else {
            atomicInteger2 = atomicInteger;
        }
        if (atomicInteger2.get() != 0) {
            i11 = parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE, true);
        }
        return new DtsHeader("audio/vnd.dts.uhd;profile=p2", 2, i12, i13 + i11 + parseUnsignedVarInt, j3, 0);
    }

    public static int parseDtsUhdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(32);
        return parseUnsignedVarInt(normalizedFrame, UHD_HEADER_SIZE_LENGTH_TABLE, true) + 1;
    }

    private static int parseUnsignedVarInt(ParsableBitArray parsableBitArray, int[] iArr, boolean z) {
        int i2 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < 3 && parsableBitArray.readBit(); i8++) {
            i7++;
        }
        if (z) {
            int i10 = 0;
            while (i2 < i7) {
                i10 += 1 << iArr[i2];
                i2++;
            }
            i2 = i10;
        }
        return parsableBitArray.readBits(iArr[i7]) + i2;
    }
}
