package androidx.media3.extractor;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.imagetranslation.common.Config;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Ac3Util {
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, MOCRLang.GURMUKHI, 192, 224, 256, ThumbKind.MEDIUM_KIND_SIZE, 384, 448, 512, 576, 640};
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {48000, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, Config.MAX_RESOLUTION_SUPPORTED};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SyncFrameInfo {
        public final int bitrate;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        private SyncFrameInfo(String str, int i2, int i7, int i8, int i10, int i11, int i12) {
            this.mimeType = str;
            this.streamType = i2;
            this.channelCount = i7;
            this.sampleRate = i8;
            this.frameSize = i10;
            this.sampleCount = i11;
            this.bitrate = i12;
        }
    }

    private static int calculateEac3Bitrate(int i2, int i7, int i8) {
        return (i2 * i7) / (i8 * 32);
    }

    private static int getAc3SyncframeSize(int i2, int i7) {
        int i8 = i7 / 2;
        if (i2 < 0) {
            return -1;
        }
        int[] iArr = SAMPLE_RATE_BY_FSCOD;
        if (i2 >= iArr.length || i7 < 0) {
            return -1;
        }
        int[] iArr2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (i8 >= iArr2.length) {
            return -1;
        }
        int i10 = iArr[i2];
        if (i10 == 44100) {
            return ((i7 % 2) + iArr2[i8]) * 2;
        }
        int i11 = BITRATE_BY_HALF_FRMSIZECOD[i8];
        if (i10 == 32000) {
            return i11 * 6;
        }
        return i11 * 4;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i2 = SAMPLE_RATE_BY_FSCOD[parsableBitArray.readBits(2)];
        parsableBitArray.skipBits(8);
        int i7 = CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)];
        if (parsableBitArray.readBits(1) != 0) {
            i7++;
        }
        int i8 = BITRATE_BY_HALF_FRMSIZECOD[parsableBitArray.readBits(5)] * 1000;
        parsableBitArray.byteAlign();
        parsableByteArray.setPosition(parsableBitArray.getBytePosition());
        return new Format.Builder().setId(str).setSampleMimeType("audio/ac3").setChannelCount(i7).setSampleRate(i2).setDrmInitData(drmInitData).setLanguage(str2).setAverageBitrate(i8).setPeakBitrate(i8).build();
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        boolean z;
        String str;
        int ac3SyncframeSize;
        int i2;
        int i7;
        int i8;
        String str2;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        String str3;
        int i15;
        int i16;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int position = parsableBitArray2.getPosition();
        parsableBitArray2.skipBits(40);
        if (parsableBitArray2.readBits(5) > 10) {
            z = true;
        } else {
            z = false;
        }
        parsableBitArray2.setPosition(position);
        int i17 = -1;
        if (z) {
            parsableBitArray2.skipBits(16);
            int readBits = parsableBitArray2.readBits(2);
            if (readBits == 0) {
                i17 = 0;
            } else if (readBits == 1) {
                i17 = 1;
            } else if (readBits == 2) {
                i17 = 2;
            }
            parsableBitArray2.skipBits(3);
            ac3SyncframeSize = (parsableBitArray2.readBits(11) + 1) * 2;
            int readBits2 = parsableBitArray2.readBits(2);
            if (readBits2 == 3) {
                i2 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray2.readBits(2)];
                i13 = 3;
                i12 = 6;
            } else {
                int readBits3 = parsableBitArray2.readBits(2);
                int i18 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[readBits3];
                i13 = readBits3;
                i2 = SAMPLE_RATE_BY_FSCOD[readBits2];
                i12 = i18;
            }
            i8 = i12 * 256;
            int calculateEac3Bitrate = calculateEac3Bitrate(ac3SyncframeSize, i2, i12);
            int readBits4 = parsableBitArray2.readBits(3);
            boolean readBit = parsableBitArray2.readBit();
            i7 = CHANNEL_COUNT_BY_ACMOD[readBits4] + (readBit ? 1 : 0);
            parsableBitArray2.skipBits(10);
            if (parsableBitArray2.readBit()) {
                parsableBitArray2.skipBits(8);
            }
            if (readBits4 == 0) {
                parsableBitArray2.skipBits(5);
                if (parsableBitArray2.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
            }
            if (i17 == 1 && parsableBitArray2.readBit()) {
                parsableBitArray2.skipBits(16);
            }
            if (parsableBitArray2.readBit()) {
                if (readBits4 > 2) {
                    parsableBitArray2.skipBits(2);
                }
                if ((readBits4 & 1) == 0 || readBits4 <= 2) {
                    i15 = 6;
                } else {
                    i15 = 6;
                    parsableBitArray2.skipBits(6);
                }
                if ((readBits4 & 4) != 0) {
                    parsableBitArray2.skipBits(i15);
                }
                if (readBit && parsableBitArray2.readBit()) {
                    parsableBitArray2.skipBits(5);
                }
                if (i17 == 0) {
                    if (parsableBitArray2.readBit()) {
                        i16 = 6;
                        parsableBitArray2.skipBits(6);
                    } else {
                        i16 = 6;
                    }
                    if (readBits4 == 0 && parsableBitArray2.readBit()) {
                        parsableBitArray2.skipBits(i16);
                    }
                    if (parsableBitArray2.readBit()) {
                        parsableBitArray2.skipBits(i16);
                    }
                    int readBits5 = parsableBitArray2.readBits(2);
                    if (readBits5 == 1) {
                        parsableBitArray2.skipBits(5);
                    } else if (readBits5 == 2) {
                        parsableBitArray2.skipBits(12);
                    } else if (readBits5 == 3) {
                        int readBits6 = parsableBitArray2.readBits(5);
                        if (parsableBitArray2.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray2.readBit()) {
                                if (parsableBitArray2.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                                if (parsableBitArray2.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                            }
                        }
                        if (parsableBitArray2.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray2.readBit()) {
                                parsableBitArray2.skipBits(7);
                                if (parsableBitArray2.readBit()) {
                                    parsableBitArray2.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray2.skipBits((readBits6 + 2) * 8);
                        parsableBitArray2.byteAlign();
                    }
                    if (readBits4 < 2) {
                        if (parsableBitArray2.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                        if (readBits4 == 0 && parsableBitArray2.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                    }
                    if (parsableBitArray2.readBit()) {
                        if (i13 == 0) {
                            parsableBitArray2.skipBits(5);
                        } else {
                            for (int i19 = 0; i19 < i12; i19++) {
                                if (parsableBitArray2.readBit()) {
                                    parsableBitArray2.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray2.readBit()) {
                parsableBitArray2.skipBits(5);
                if (readBits4 == 2) {
                    parsableBitArray2.skipBits(4);
                }
                if (readBits4 >= 6) {
                    parsableBitArray2.skipBits(2);
                }
                if (parsableBitArray2.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits4 == 0 && parsableBitArray2.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits2 < 3) {
                    parsableBitArray2.skipBit();
                }
            }
            if (i17 == 0 && i13 != 3) {
                parsableBitArray2.skipBit();
            }
            if (i17 != 2 || (i13 != 3 && !parsableBitArray2.readBit())) {
                i14 = 6;
            } else {
                i14 = 6;
                parsableBitArray2.skipBits(6);
            }
            if (parsableBitArray2.readBit() && parsableBitArray2.readBits(i14) == 1 && parsableBitArray2.readBits(8) == 1) {
                str3 = "audio/eac3-joc";
            } else {
                str3 = "audio/eac3";
            }
            str2 = str3;
            i10 = calculateEac3Bitrate;
        } else {
            parsableBitArray2.skipBits(32);
            int readBits7 = parsableBitArray2.readBits(2);
            if (readBits7 == 3) {
                str = null;
            } else {
                str = "audio/ac3";
            }
            int readBits8 = parsableBitArray2.readBits(6);
            int i20 = BITRATE_BY_HALF_FRMSIZECOD[readBits8 / 2] * 1000;
            ac3SyncframeSize = getAc3SyncframeSize(readBits7, readBits8);
            parsableBitArray2.skipBits(8);
            int readBits9 = parsableBitArray2.readBits(3);
            if (!((readBits9 & 1) == 0 || readBits9 == 1)) {
                parsableBitArray2.skipBits(2);
            }
            if ((readBits9 & 4) != 0) {
                parsableBitArray2.skipBits(2);
            }
            if (readBits9 == 2) {
                parsableBitArray2.skipBits(2);
            }
            int[] iArr = SAMPLE_RATE_BY_FSCOD;
            if (readBits7 < iArr.length) {
                i11 = iArr[readBits7];
            } else {
                i11 = -1;
            }
            i7 = CHANNEL_COUNT_BY_ACMOD[readBits9] + (parsableBitArray2.readBit() ? 1 : 0);
            i8 = 1536;
            str2 = str;
            i10 = i20;
        }
        return new SyncFrameInfo(str2, i17, i7, i2, ac3SyncframeSize, i8, i10);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            return (((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1) * 2;
        }
        byte b = bArr[4];
        return getAc3SyncframeSize((b & 192) >> 6, b & 63);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        String str3;
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int readBits = parsableBitArray.readBits(13) * 1000;
        parsableBitArray.skipBits(3);
        int i2 = SAMPLE_RATE_BY_FSCOD[parsableBitArray.readBits(2)];
        parsableBitArray.skipBits(10);
        int i7 = CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)];
        if (parsableBitArray.readBits(1) != 0) {
            i7++;
        }
        parsableBitArray.skipBits(3);
        int readBits2 = parsableBitArray.readBits(4);
        parsableBitArray.skipBits(1);
        if (readBits2 > 0) {
            parsableBitArray.skipBits(6);
            if (parsableBitArray.readBits(1) != 0) {
                i7 += 2;
            }
            parsableBitArray.skipBits(1);
        }
        if (parsableBitArray.bitsLeft() > 7) {
            parsableBitArray.skipBits(7);
            if (parsableBitArray.readBits(1) != 0) {
                str3 = "audio/eac3-joc";
                parsableBitArray.byteAlign();
                parsableByteArray.setPosition(parsableBitArray.getBytePosition());
                return new Format.Builder().setId(str).setSampleMimeType(str3).setChannelCount(i7).setSampleRate(i2).setDrmInitData(drmInitData).setLanguage(str2).setPeakBitrate(readBits).build();
            }
        }
        str3 = "audio/eac3";
        parsableBitArray.byteAlign();
        parsableByteArray.setPosition(parsableBitArray.getBytePosition());
        return new Format.Builder().setId(str).setSampleMimeType(str3).setChannelCount(i7).setSampleRate(i2).setDrmInitData(drmInitData).setLanguage(str2).setPeakBitrate(readBits).build();
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] bArr) {
        char c5;
        boolean z = false;
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111) {
            byte b = bArr[7];
            if ((b & 254) == 186) {
                if ((b & 255) == 187) {
                    z = true;
                }
                if (z) {
                    c5 = 9;
                } else {
                    c5 = 8;
                }
                return 40 << ((bArr[c5] >> 4) & 7);
            }
        }
        return 0;
    }
}
