package androidx.media3.extractor.ts;

import L1.d;
import L2.a;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import com.samsung.android.imagetranslation.common.Config;
import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MpeghUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MhasPacketHeader {
        public long packetLabel;
        public int packetLength;
        public int packetType;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Mpegh3daConfig {
        public final byte[] compatibleProfileLevelSet;
        public final int profileLevelIndication;
        public final int samplingFrequency;
        public final int standardFrameLength;

        private Mpegh3daConfig(int i2, int i7, int i8, byte[] bArr) {
            this.profileLevelIndication = i2;
            this.samplingFrequency = i7;
            this.standardFrameLength = i8;
            this.compatibleProfileLevelSet = bArr;
        }
    }

    private static int getOutputFrameLength(int i2) {
        if (i2 == 0) {
            return 768;
        }
        if (i2 == 1) {
            return 1024;
        }
        if (i2 == 2 || i2 == 3) {
            return 2048;
        }
        if (i2 == 4) {
            return 4096;
        }
        throw ParserException.createForUnsupportedContainerFeature("Unsupported coreSbrFrameLengthIndex " + i2);
    }

    private static double getResamplingRatio(int i2) {
        switch (i2) {
            case 14700:
            case Config.MAX_RESOLUTION_SUPPORTED /*16000*/:
                return 3.0d;
            case 22050:
            case 24000:
                return 2.0d;
            case 29400:
            case 32000:
            case 58800:
            case 64000:
                return 1.5d;
            case 44100:
            case 48000:
            case 88200:
            case 96000:
                return 1.0d;
            default:
                throw ParserException.createForUnsupportedContainerFeature("Unsupported sampling rate " + i2);
        }
    }

    private static int getSamplingFrequency(int i2) {
        switch (i2) {
            case 0:
                return 96000;
            case 1:
                return 88200;
            case 2:
                return 64000;
            case 3:
                return 48000;
            case 4:
                return 44100;
            case 5:
                return 32000;
            case 6:
                return 24000;
            case 7:
                return 22050;
            case 8:
                return Config.MAX_RESOLUTION_SUPPORTED;
            case 9:
                return 12000;
            case 10:
                return 11025;
            case 11:
                return Encode.BitRate.VIDEO_HD_BITRATE;
            case 12:
                return 7350;
            case 15:
                return 57600;
            case 16:
                return 51200;
            case 17:
                return 40000;
            case 18:
                return 38400;
            case 19:
                return 34150;
            case 20:
                return 28800;
            case 21:
                return 25600;
            case 22:
                return 20000;
            case 23:
                return 19200;
            case 24:
                return 17075;
            case 25:
                return 14400;
            case 26:
                return 12800;
            case 27:
                return 9600;
            default:
                throw ParserException.createForUnsupportedContainerFeature("Unsupported sampling rate index " + i2);
        }
    }

    private static int getSbrRatioIndex(int i2) {
        if (i2 == 0 || i2 == 1) {
            return 0;
        }
        int i7 = 2;
        if (i2 != 2) {
            i7 = 3;
            if (i2 != 3) {
                if (i2 == 4) {
                    return 1;
                }
                throw ParserException.createForUnsupportedContainerFeature("Unsupported coreSbrFrameLengthIndex " + i2);
            }
        }
        return i7;
    }

    public static boolean isSyncWord(int i2) {
        if ((i2 & 16777215) == 12583333) {
            return true;
        }
        return false;
    }

    public static int parseAudioTruncationInfo(ParsableBitArray parsableBitArray) {
        if (!parsableBitArray.readBit()) {
            return 0;
        }
        parsableBitArray.skipBits(2);
        return parsableBitArray.readBits(13);
    }

    public static boolean parseMhasPacketHeader(ParsableBitArray parsableBitArray, MhasPacketHeader mhasPacketHeader) {
        parsableBitArray.getBytePosition();
        int readEscapedIntValue = readEscapedIntValue(parsableBitArray, 3, 8, 8);
        mhasPacketHeader.packetType = readEscapedIntValue;
        if (readEscapedIntValue == -1) {
            return false;
        }
        long readEscapedLongValue = readEscapedLongValue(parsableBitArray, 2, 8, 32);
        mhasPacketHeader.packetLabel = readEscapedLongValue;
        if (readEscapedLongValue == -1) {
            return false;
        }
        if (readEscapedLongValue <= 16) {
            if (readEscapedLongValue == 0) {
                int i2 = mhasPacketHeader.packetType;
                if (i2 == 1) {
                    throw ParserException.createForMalformedContainer("Mpegh3daConfig packet with invalid packet label 0", (Throwable) null);
                } else if (i2 == 2) {
                    throw ParserException.createForMalformedContainer("Mpegh3daFrame packet with invalid packet label 0", (Throwable) null);
                } else if (i2 == 17) {
                    throw ParserException.createForMalformedContainer("AudioTruncation packet with invalid packet label 0", (Throwable) null);
                }
            }
            int readEscapedIntValue2 = readEscapedIntValue(parsableBitArray, 11, 24, 24);
            mhasPacketHeader.packetLength = readEscapedIntValue2;
            if (readEscapedIntValue2 != -1) {
                return true;
            }
            return false;
        }
        throw ParserException.createForUnsupportedContainerFeature("Contains sub-stream with an invalid packet label " + mhasPacketHeader.packetLabel);
    }

    public static Mpegh3daConfig parseMpegh3daConfig(ParsableBitArray parsableBitArray) {
        int i2;
        int readBits = parsableBitArray.readBits(8);
        int readBits2 = parsableBitArray.readBits(5);
        if (readBits2 == 31) {
            i2 = parsableBitArray.readBits(24);
        } else {
            i2 = getSamplingFrequency(readBits2);
        }
        int readBits3 = parsableBitArray.readBits(3);
        int outputFrameLength = getOutputFrameLength(readBits3);
        int sbrRatioIndex = getSbrRatioIndex(readBits3);
        parsableBitArray.skipBits(2);
        skipSpeakerConfig3d(parsableBitArray);
        skipMpegh3daDecoderConfig(parsableBitArray, parseSignals3d(parsableBitArray), sbrRatioIndex);
        byte[] bArr = null;
        if (parsableBitArray.readBit()) {
            int readEscapedIntValue = readEscapedIntValue(parsableBitArray, 2, 4, 8) + 1;
            for (int i7 = 0; i7 < readEscapedIntValue; i7++) {
                int readEscapedIntValue2 = readEscapedIntValue(parsableBitArray, 4, 8, 16);
                int readEscapedIntValue3 = readEscapedIntValue(parsableBitArray, 4, 8, 16);
                if (readEscapedIntValue2 == 7) {
                    int readBits4 = parsableBitArray.readBits(4) + 1;
                    parsableBitArray.skipBits(4);
                    byte[] bArr2 = new byte[readBits4];
                    for (int i8 = 0; i8 < readBits4; i8++) {
                        bArr2[i8] = (byte) parsableBitArray.readBits(8);
                    }
                    bArr = bArr2;
                } else {
                    parsableBitArray.skipBits(readEscapedIntValue3 * 8);
                }
            }
        }
        byte[] bArr3 = bArr;
        double resamplingRatio = getResamplingRatio(i2);
        return new Mpegh3daConfig(readBits, (int) (((double) i2) * resamplingRatio), (int) (((double) outputFrameLength) * resamplingRatio), bArr3);
    }

    private static boolean parseMpegh3daCoreConfig(ParsableBitArray parsableBitArray) {
        parsableBitArray.skipBits(3);
        boolean readBit = parsableBitArray.readBit();
        if (readBit) {
            parsableBitArray.skipBits(13);
        }
        return readBit;
    }

    private static int parseSignals3d(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(5);
        int i2 = 0;
        for (int i7 = 0; i7 < readBits + 1; i7++) {
            int readBits2 = parsableBitArray.readBits(3);
            i2 += readEscapedIntValue(parsableBitArray, 5, 8, 16) + 1;
            if ((readBits2 == 0 || readBits2 == 2) && parsableBitArray.readBit()) {
                skipSpeakerConfig3d(parsableBitArray);
            }
        }
        return i2;
    }

    private static int readEscapedIntValue(ParsableBitArray parsableBitArray, int i2, int i7, int i8) {
        boolean z;
        if (Math.max(Math.max(i2, i7), i8) <= 31) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        int i10 = (1 << i2) - 1;
        int i11 = (1 << i7) - 1;
        d.c(d.c(i10, i11), 1 << i8);
        if (parsableBitArray.bitsLeft() < i2) {
            return -1;
        }
        int readBits = parsableBitArray.readBits(i2);
        if (readBits == i10) {
            if (parsableBitArray.bitsLeft() < i7) {
                return -1;
            }
            int readBits2 = parsableBitArray.readBits(i7);
            readBits += readBits2;
            if (readBits2 == i11) {
                if (parsableBitArray.bitsLeft() < i8) {
                    return -1;
                }
                return parsableBitArray.readBits(i8) + readBits;
            }
        }
        return readBits;
    }

    private static long readEscapedLongValue(ParsableBitArray parsableBitArray, int i2, int i7, int i8) {
        boolean z;
        if (Math.max(Math.max(i2, i7), i8) <= 63) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        long j2 = (1 << i2) - 1;
        long j3 = (1 << i7) - 1;
        a.h(a.h(j2, j3), 1 << i8);
        if (parsableBitArray.bitsLeft() < i2) {
            return -1;
        }
        long readBitsToLong = parsableBitArray.readBitsToLong(i2);
        if (readBitsToLong == j2) {
            if (parsableBitArray.bitsLeft() < i7) {
                return -1;
            }
            long readBitsToLong2 = parsableBitArray.readBitsToLong(i7);
            readBitsToLong += readBitsToLong2;
            if (readBitsToLong2 == j3) {
                if (parsableBitArray.bitsLeft() < i8) {
                    return -1;
                }
                return parsableBitArray.readBitsToLong(i8) + readBitsToLong;
            }
        }
        return readBitsToLong;
    }

    private static void skipMpegh3daDecoderConfig(ParsableBitArray parsableBitArray, int i2, int i7) {
        int i8;
        int readEscapedIntValue = readEscapedIntValue(parsableBitArray, 4, 8, 16) + 1;
        parsableBitArray.skipBit();
        for (int i10 = 0; i10 < readEscapedIntValue; i10++) {
            int readBits = parsableBitArray.readBits(2);
            if (readBits == 0) {
                parseMpegh3daCoreConfig(parsableBitArray);
                if (i7 > 0) {
                    skipSbrConfig(parsableBitArray);
                }
            } else if (readBits == 1) {
                if (parseMpegh3daCoreConfig(parsableBitArray)) {
                    parsableBitArray.skipBit();
                }
                if (i7 > 0) {
                    skipSbrConfig(parsableBitArray);
                    i8 = parsableBitArray.readBits(2);
                } else {
                    i8 = 0;
                }
                if (i8 > 0) {
                    parsableBitArray.skipBits(6);
                    int readBits2 = parsableBitArray.readBits(2);
                    parsableBitArray.skipBits(4);
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(5);
                    }
                    if (i8 == 2 || i8 == 3) {
                        parsableBitArray.skipBits(6);
                    }
                    if (readBits2 == 2) {
                        parsableBitArray.skipBit();
                    }
                }
                int floor = ((int) Math.floor(Math.log((double) (i2 - 1)) / Math.log(2.0d))) + 1;
                int readBits3 = parsableBitArray.readBits(2);
                if (readBits3 > 0 && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(floor);
                }
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(floor);
                }
                if (i7 == 0 && readBits3 == 0) {
                    parsableBitArray.skipBit();
                }
            } else if (readBits == 3) {
                readEscapedIntValue(parsableBitArray, 4, 8, 16);
                int readEscapedIntValue2 = readEscapedIntValue(parsableBitArray, 4, 8, 16);
                if (parsableBitArray.readBit()) {
                    readEscapedIntValue(parsableBitArray, 8, 16, 0);
                }
                parsableBitArray.skipBit();
                if (readEscapedIntValue2 > 0) {
                    parsableBitArray.skipBits(readEscapedIntValue2 * 8);
                }
            }
        }
    }

    private static void skipMpegh3daFlexibleSpeakerConfig(ParsableBitArray parsableBitArray, int i2) {
        int i7;
        int i8;
        int i10;
        boolean readBit = parsableBitArray.readBit();
        int i11 = 5;
        if (readBit) {
            i7 = 1;
        } else {
            i7 = 5;
        }
        if (readBit) {
            i11 = 7;
        }
        if (readBit) {
            i8 = 8;
        } else {
            i8 = 6;
        }
        int i12 = 0;
        while (i12 < i2) {
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(7);
                i10 = 0;
            } else {
                if (parsableBitArray.readBits(2) == 3 && parsableBitArray.readBits(i11) * i7 != 0) {
                    parsableBitArray.skipBit();
                }
                i10 = parsableBitArray.readBits(i8) * i7;
                if (!(i10 == 0 || i10 == 180)) {
                    parsableBitArray.skipBit();
                }
                parsableBitArray.skipBit();
            }
            if (!(i10 == 0 || i10 == 180 || !parsableBitArray.readBit())) {
                i12++;
            }
            i12++;
        }
    }

    private static void skipSbrConfig(ParsableBitArray parsableBitArray) {
        parsableBitArray.skipBits(3);
        parsableBitArray.skipBits(8);
        boolean readBit = parsableBitArray.readBit();
        boolean readBit2 = parsableBitArray.readBit();
        if (readBit) {
            parsableBitArray.skipBits(5);
        }
        if (readBit2) {
            parsableBitArray.skipBits(6);
        }
    }

    private static void skipSpeakerConfig3d(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(2);
        if (readBits == 0) {
            parsableBitArray.skipBits(6);
            return;
        }
        int readEscapedIntValue = readEscapedIntValue(parsableBitArray, 5, 8, 16) + 1;
        if (readBits == 1) {
            parsableBitArray.skipBits(readEscapedIntValue * 7);
        } else if (readBits == 2) {
            skipMpegh3daFlexibleSpeakerConfig(parsableBitArray, readEscapedIntValue);
        }
    }
}
