package androidx.media3.extractor;

import com.samsung.android.imagetranslation.common.Config;
import com.samsung.android.sdk.sgpl.pip.core.Encode;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MpegAudioUtil {
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V1_L1 = {32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V1_L2 = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V1_L3 = {32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V2 = {Encode.BitRate.VIDEO_HD_BITRATE, Config.MAX_RESOLUTION_SUPPORTED, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V2_L1 = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};
    /* access modifiers changed from: private */
    public static final String[] MIME_TYPE_BY_LAYER = {"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
    /* access modifiers changed from: private */
    public static final int[] SAMPLING_RATE_V1 = {44100, 48000, 32000};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Header {
        public int bitrate;
        public int channels;
        public int frameSize;
        public String mimeType;
        public int sampleRate;
        public int samplesPerFrame;
        public int version;

        public Header() {
        }

        public boolean setForHeaderData(int i2) {
            int i7;
            int i8;
            int i10;
            int i11;
            int i12;
            int i13;
            if (!MpegAudioUtil.isMagicPresent(i2) || (i7 = (i2 >>> 19) & 3) == 1 || (i8 = (i2 >>> 17) & 3) == 0 || (i10 = (i2 >>> 12) & 15) == 0 || i10 == 15 || (i11 = (i2 >>> 10) & 3) == 3) {
                return false;
            }
            this.version = i7;
            this.mimeType = MpegAudioUtil.MIME_TYPE_BY_LAYER[3 - i8];
            int i14 = MpegAudioUtil.SAMPLING_RATE_V1[i11];
            this.sampleRate = i14;
            int i15 = 2;
            if (i7 == 2) {
                this.sampleRate = i14 / 2;
            } else if (i7 == 0) {
                this.sampleRate = i14 / 4;
            }
            int i16 = (i2 >>> 9) & 1;
            this.samplesPerFrame = MpegAudioUtil.getFrameSizeInSamples(i7, i8);
            if (i8 == 3) {
                if (i7 == 3) {
                    i13 = MpegAudioUtil.BITRATE_V1_L1[i10 - 1];
                } else {
                    i13 = MpegAudioUtil.BITRATE_V2_L1[i10 - 1];
                }
                this.bitrate = i13;
                this.frameSize = (((i13 * 12) / this.sampleRate) + i16) * 4;
            } else {
                int i17 = 144;
                if (i7 == 3) {
                    if (i8 == 2) {
                        i12 = MpegAudioUtil.BITRATE_V1_L2[i10 - 1];
                    } else {
                        i12 = MpegAudioUtil.BITRATE_V1_L3[i10 - 1];
                    }
                    this.bitrate = i12;
                    this.frameSize = ((i12 * 144) / this.sampleRate) + i16;
                } else {
                    int i18 = MpegAudioUtil.BITRATE_V2[i10 - 1];
                    this.bitrate = i18;
                    if (i8 == 1) {
                        i17 = 72;
                    }
                    this.frameSize = ((i17 * i18) / this.sampleRate) + i16;
                }
            }
            if (((i2 >> 6) & 3) == 3) {
                i15 = 1;
            }
            this.channels = i15;
            return true;
        }

        public Header(Header header) {
            this.version = header.version;
            this.mimeType = header.mimeType;
            this.frameSize = header.frameSize;
            this.sampleRate = header.sampleRate;
            this.channels = header.channels;
            this.bitrate = header.bitrate;
            this.samplesPerFrame = header.samplesPerFrame;
        }
    }

    public static int getFrameSize(int i2) {
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        if (!isMagicPresent(i2) || (i7 = (i2 >>> 19) & 3) == 1 || (i8 = (i2 >>> 17) & 3) == 0 || (i10 = (i2 >>> 12) & 15) == 0 || i10 == 15 || (i11 = (i2 >>> 10) & 3) == 3) {
            return -1;
        }
        int i14 = SAMPLING_RATE_V1[i11];
        if (i7 == 2) {
            i14 /= 2;
        } else if (i7 == 0) {
            i14 /= 4;
        }
        int i15 = (i2 >>> 9) & 1;
        if (i8 == 3) {
            if (i7 == 3) {
                i13 = BITRATE_V1_L1[i10 - 1];
            } else {
                i13 = BITRATE_V2_L1[i10 - 1];
            }
            return (((i13 * 12) / i14) + i15) * 4;
        }
        if (i7 != 3) {
            i12 = BITRATE_V2[i10 - 1];
        } else if (i8 == 2) {
            i12 = BITRATE_V1_L2[i10 - 1];
        } else {
            i12 = BITRATE_V1_L3[i10 - 1];
        }
        int i16 = 144;
        if (i7 == 3) {
            return ((i12 * 144) / i14) + i15;
        }
        if (i8 == 1) {
            i16 = 72;
        }
        return ((i16 * i12) / i14) + i15;
    }

    /* access modifiers changed from: private */
    public static int getFrameSizeInSamples(int i2, int i7) {
        if (i7 != 1) {
            if (i7 == 2) {
                return 1152;
            }
            if (i7 == 3) {
                return 384;
            }
            throw new IllegalArgumentException();
        } else if (i2 == 3) {
            return 1152;
        } else {
            return 576;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isMagicPresent(int i2) {
        if ((i2 & -2097152) == -2097152) {
            return true;
        }
        return false;
    }
}
