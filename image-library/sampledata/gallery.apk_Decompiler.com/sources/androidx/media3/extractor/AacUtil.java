package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import c0.C0086a;
import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AacUtil {
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, com.samsung.android.imagetranslation.common.Config.MAX_RESOLUTION_SUPPORTED, 12000, 11025, Encode.BitRate.VIDEO_HD_BITRATE, 7350};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Config {
        public final int channelCount;
        public final String codecs;
        public final int sampleRateHz;

        private Config(int i2, int i7, String str) {
            this.sampleRateHz = i2;
            this.channelCount = i7;
            this.codecs = str;
        }
    }

    public static byte[] buildAudioSpecificConfig(int i2, int i7, int i8) {
        return new byte[]{(byte) (((i2 << 3) & 248) | ((i7 >> 1) & 7)), (byte) (((i7 << 7) & 128) | ((i8 << 3) & 120))};
    }

    private static int getAudioObjectType(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(5);
        if (readBits == 31) {
            return parsableBitArray.readBits(6) + 32;
        }
        return readBits;
    }

    private static int getSamplingFrequency(ParsableBitArray parsableBitArray) {
        int readBits = parsableBitArray.readBits(4);
        if (readBits == 15) {
            if (parsableBitArray.bitsLeft() >= 24) {
                return parsableBitArray.readBits(24);
            }
            throw ParserException.createForMalformedContainer("AAC header insufficient data", (Throwable) null);
        } else if (readBits < 13) {
            return AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[readBits];
        } else {
            throw ParserException.createForMalformedContainer("AAC header wrong Sampling Frequency Index", (Throwable) null);
        }
    }

    public static Config parseAudioSpecificConfig(byte[] bArr) {
        return parseAudioSpecificConfig(new ParsableBitArray(bArr), false);
    }

    private static void parseGaSpecificConfig(ParsableBitArray parsableBitArray, int i2, int i7) {
        if (parsableBitArray.readBit()) {
            Log.w("AacUtil", "Unexpected frameLengthFlag = 1");
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(14);
        }
        boolean readBit = parsableBitArray.readBit();
        if (i7 != 0) {
            if (i2 == 6 || i2 == 20) {
                parsableBitArray.skipBits(3);
            }
            if (readBit) {
                if (i2 == 22) {
                    parsableBitArray.skipBits(16);
                }
                if (i2 == 17 || i2 == 19 || i2 == 20 || i2 == 23) {
                    parsableBitArray.skipBits(3);
                }
                parsableBitArray.skipBits(1);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException();
    }

    public static Config parseAudioSpecificConfig(ParsableBitArray parsableBitArray, boolean z) {
        int audioObjectType = getAudioObjectType(parsableBitArray);
        int samplingFrequency = getSamplingFrequency(parsableBitArray);
        int readBits = parsableBitArray.readBits(4);
        String i2 = C0086a.i(audioObjectType, "mp4a.40.");
        if (audioObjectType == 5 || audioObjectType == 29) {
            samplingFrequency = getSamplingFrequency(parsableBitArray);
            audioObjectType = getAudioObjectType(parsableBitArray);
            if (audioObjectType == 22) {
                readBits = parsableBitArray.readBits(4);
            }
        }
        if (z) {
            if (!(audioObjectType == 1 || audioObjectType == 2 || audioObjectType == 3 || audioObjectType == 4 || audioObjectType == 6 || audioObjectType == 7 || audioObjectType == 17)) {
                switch (audioObjectType) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        break;
                    default:
                        throw ParserException.createForUnsupportedContainerFeature("Unsupported audio object type: " + audioObjectType);
                }
            }
            parseGaSpecificConfig(parsableBitArray, audioObjectType, readBits);
            switch (audioObjectType) {
                case 17:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    int readBits2 = parsableBitArray.readBits(2);
                    if (readBits2 == 2 || readBits2 == 3) {
                        throw ParserException.createForUnsupportedContainerFeature("Unsupported epConfig: " + readBits2);
                    }
            }
        }
        int i7 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[readBits];
        if (i7 != -1) {
            return new Config(samplingFrequency, i7, i2);
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }
}
