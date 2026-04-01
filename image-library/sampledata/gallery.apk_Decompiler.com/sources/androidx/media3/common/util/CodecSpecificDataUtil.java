package androidx.media3.common.util;

import A.a;
import F2.U;
import android.util.Pair;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.MimeTypes;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CodecSpecificDataUtil {
    private static final String[] HEVC_GENERAL_PROFILE_SPACE_STRINGS = {"", "A", "B", "C"};
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");

    private static int ac4BitstreamAndPresentationVersionsToProfileConst(int i2, int i7) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return -1;
                }
                if (i7 == 1) {
                    return 1026;
                }
                if (i7 == 2) {
                    return 1028;
                }
                return -1;
            } else if (i7 == 0) {
                return ASVLOFFSCREEN.ASVL_PAF_RGB24_B8G8R8;
            } else {
                if (i7 == 1) {
                    return 514;
                }
                return -1;
            }
        } else if (i7 == 0) {
            return 257;
        } else {
            return -1;
        }
    }

    private static int ac4LevelNumberToConst(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 1) {
            return 2;
        }
        if (i2 == 2) {
            return 4;
        }
        if (i2 == 3) {
            return 8;
        }
        if (i2 != 4) {
            return -1;
        }
        return 16;
    }

    private static int av1LevelNumberToConst(int i2) {
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            case 13:
                return SerializeOptions.SORT;
            case 14:
                return 16384;
            case 15:
                return 32768;
            case 16:
                return 65536;
            case 17:
                return 131072;
            case 18:
                return 262144;
            case 19:
                return 524288;
            case 20:
                return MediaDefs.Meta.SEF.SEF_MIN_SIZE;
            case 21:
                return 2097152;
            case 22:
                return OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE;
            case 23:
                return 8388608;
            default:
                return -1;
        }
    }

    private static int avcLevelNumberToConst(int i2) {
        switch (i2) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 8;
            case 13:
                return 16;
            default:
                switch (i2) {
                    case 20:
                        return 32;
                    case 21:
                        return 64;
                    case 22:
                        return 128;
                    default:
                        switch (i2) {
                            case 30:
                                return 256;
                            case 31:
                                return 512;
                            case 32:
                                return 1024;
                            default:
                                switch (i2) {
                                    case 40:
                                        return 2048;
                                    case 41:
                                        return 4096;
                                    case 42:
                                        return SerializeOptions.SORT;
                                    default:
                                        switch (i2) {
                                            case 50:
                                                return 16384;
                                            case 51:
                                                return 32768;
                                            case 52:
                                                return 65536;
                                            default:
                                                return -1;
                                        }
                                }
                        }
                }
        }
    }

    private static int avcProfileNumberToConst(int i2) {
        if (i2 == 66) {
            return 1;
        }
        if (i2 == 77) {
            return 2;
        }
        if (i2 == 88) {
            return 4;
        }
        if (i2 == 100) {
            return 8;
        }
        if (i2 == 110) {
            return 16;
        }
        if (i2 == 122) {
            return 32;
        }
        if (i2 != 244) {
            return -1;
        }
        return 64;
    }

    public static String buildAvcCodecString(int i2, int i7, int i8) {
        return String.format("avc1.%02X%02X%02X", new Object[]{Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8)});
    }

    public static List<byte[]> buildCea708InitializationData(boolean z) {
        return Collections.singletonList(z ? new byte[]{1} : new byte[]{0});
    }

    public static String buildDolbyVisionCodecString(int i2, int i7) {
        if (i2 > 9) {
            return Util.formatInvariant("dvh1.%02d.%02d", Integer.valueOf(i2), Integer.valueOf(i7));
        }
        if (i2 > 8) {
            return Util.formatInvariant("dvav.%02d.%02d", Integer.valueOf(i2), Integer.valueOf(i7));
        }
        return Util.formatInvariant("dvhe.%02d.%02d", Integer.valueOf(i2), Integer.valueOf(i7));
    }

    public static String buildH263CodecString(int i2, int i7) {
        return Util.formatInvariant("s263.%d.%d", Integer.valueOf(i2), Integer.valueOf(i7));
    }

    public static String buildHevcCodecString(int i2, boolean z, int i7, int i8, int[] iArr, int i10) {
        char c5;
        String str = HEVC_GENERAL_PROFILE_SPACE_STRINGS[i2];
        Integer valueOf = Integer.valueOf(i7);
        Integer valueOf2 = Integer.valueOf(i8);
        if (z) {
            c5 = 'H';
        } else {
            c5 = 'L';
        }
        StringBuilder sb2 = new StringBuilder(Util.formatInvariant("hvc1.%s%d.%X.%c%d", str, valueOf, valueOf2, Character.valueOf(c5), Integer.valueOf(i10)));
        int length = iArr.length;
        while (length > 0 && iArr[length - 1] == 0) {
            length--;
        }
        for (int i11 = 0; i11 < length; i11++) {
            sb2.append(String.format(".%02X", new Object[]{Integer.valueOf(iArr[i11])}));
        }
        return sb2.toString();
    }

    public static String buildIamfCodecString(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.skipLeb128();
        parsableByteArray.skipBytes(4);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        parsableByteArray.skipBytes(1);
        parsableByteArray.skipLeb128();
        parsableByteArray.skipLeb128();
        String readString = parsableByteArray.readString(4);
        if (readString.equals("mp4a")) {
            parsableByteArray.skipLeb128();
            parsableByteArray.skipBytes(2);
            ParsableBitArray parsableBitArray = new ParsableBitArray();
            parsableBitArray.reset(parsableByteArray);
            int readBits = parsableBitArray.readBits(5);
            if (readBits == 31) {
                readBits = parsableBitArray.readBits(6) + 32;
            }
            readString = readString + ".40." + readBits;
        }
        return Util.formatInvariant("iamf.%03X.%03X.%s", Integer.valueOf(readUnsignedByte), Integer.valueOf(readUnsignedByte2), readString);
    }

    public static byte[] buildNalUnit(byte[] bArr, int i2, int i7) {
        byte[] bArr2 = NAL_START_CODE;
        byte[] bArr3 = new byte[(bArr2.length + i7)];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i2, bArr3, bArr2.length, i7);
        return bArr3;
    }

    public static U buildVp9CodecPrivateInitializationData(byte b, byte b5, byte b8, byte b10) {
        return U.B(new byte[]{1, 1, b, 2, 1, b5, 3, 1, b8, 4, 1, b10});
    }

    public static int dolbyVisionConstantToLevelNumber(int i2) {
        int i7 = 1;
        if (i2 != 1) {
            i7 = 2;
            if (i2 != 2) {
                switch (i2) {
                    case 4:
                        return 3;
                    case 8:
                        return 4;
                    case 16:
                        return 5;
                    case 32:
                        return 6;
                    case 64:
                        return 7;
                    case 128:
                        return 8;
                    case 256:
                        return 9;
                    case 512:
                        return 10;
                    case 1024:
                        return 11;
                    case 2048:
                        return 12;
                    case 4096:
                        return 13;
                    default:
                        throw new IllegalArgumentException(C0086a.i(i2, "Unknown Dolby Vision level: "));
                }
            }
        }
        return i7;
    }

    public static int dolbyVisionConstantToProfileNumber(int i2) {
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 8) {
            return 3;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 32) {
            return 5;
        }
        if (i2 == 64) {
            return 6;
        }
        if (i2 == 128) {
            return 7;
        }
        if (i2 == 256) {
            return 8;
        }
        if (i2 == 512) {
            return 9;
        }
        if (i2 == 1024) {
            return 10;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Unknown Dolby Vision profile: "));
    }

    private static Integer dolbyVisionStringToLevel(String str) {
        if (str == null) {
            return null;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case ASVLOFFSCREEN.ASVL_PAF_I420 /*1537*/:
                if (str.equals("01")) {
                    c5 = 0;
                    break;
                }
                break;
            case 1538:
                if (str.equals("02")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1539:
                if (str.equals("03")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1540:
                if (str.equals("04")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1541:
                if (str.equals("05")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1542:
                if (str.equals("06")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1543:
                if (str.equals("07")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1544:
                if (str.equals("08")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1545:
                if (str.equals("09")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1567:
                if (str.equals("10")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1568:
                if (str.equals("11")) {
                    c5 = 10;
                    break;
                }
                break;
            case 1569:
                if (str.equals("12")) {
                    c5 = 11;
                    break;
                }
                break;
            case 1570:
                if (str.equals("13")) {
                    c5 = 12;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            default:
                return null;
        }
    }

    private static Integer dolbyVisionStringToProfile(String str) {
        if (str == null) {
            return null;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case 1536:
                if (str.equals("00")) {
                    c5 = 0;
                    break;
                }
                break;
            case ASVLOFFSCREEN.ASVL_PAF_I420 /*1537*/:
                if (str.equals("01")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1538:
                if (str.equals("02")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1539:
                if (str.equals("03")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1540:
                if (str.equals("04")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1541:
                if (str.equals("05")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1542:
                if (str.equals("06")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1543:
                if (str.equals("07")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1544:
                if (str.equals("08")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1545:
                if (str.equals("09")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1567:
                if (str.equals("10")) {
                    c5 = 10;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            default:
                return null;
        }
    }

    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        int mp4aAudioObjectTypeToProfile;
        if (strArr.length != 3) {
            a.C("Ignoring malformed MP4A codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        try {
            if (Encode.CodecsMime.AUDIO_CODEC_AAC.equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16))) && (mp4aAudioObjectTypeToProfile = mp4aAudioObjectTypeToProfile(Integer.parseInt(strArr[2]))) != -1) {
                return new Pair<>(Integer.valueOf(mp4aAudioObjectTypeToProfile), 0);
            }
        } catch (NumberFormatException unused) {
            a.C("Ignoring malformed MP4A codec string: ", str, "CodecSpecificDataUtil");
        }
        return null;
    }

    private static Pair<Integer, Integer> getAc4CodecProfileAndLevel(String str, String[] strArr) {
        if (strArr.length != 4) {
            a.C("Ignoring malformed AC-4 codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2]);
            int parseInt3 = Integer.parseInt(strArr[3]);
            int ac4BitstreamAndPresentationVersionsToProfileConst = ac4BitstreamAndPresentationVersionsToProfileConst(parseInt, parseInt2);
            if (ac4BitstreamAndPresentationVersionsToProfileConst == -1) {
                Log.w("CodecSpecificDataUtil", "Unknown AC-4 profile: " + parseInt + "." + parseInt2);
                return null;
            }
            int ac4LevelNumberToConst = ac4LevelNumberToConst(parseInt3);
            if (ac4LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(ac4BitstreamAndPresentationVersionsToProfileConst), Integer.valueOf(ac4LevelNumberToConst));
            }
            a.D(parseInt3, "Unknown AC-4 level: ", "CodecSpecificDataUtil");
            return null;
        } catch (NumberFormatException unused) {
            a.C("Ignoring malformed AC-4 codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
    }

    private static Pair<Integer, Integer> getAv1ProfileAndLevel(String str, String[] strArr, ColorInfo colorInfo) {
        int i2;
        if (strArr.length < 4) {
            a.C("Ignoring malformed AV1 codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        int i7 = 1;
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2].substring(0, 2));
            int parseInt3 = Integer.parseInt(strArr[3]);
            if (parseInt != 0) {
                a.D(parseInt, "Unknown AV1 profile: ", "CodecSpecificDataUtil");
                return null;
            } else if (parseInt3 == 8 || parseInt3 == 10) {
                if (parseInt3 != 8) {
                    if (colorInfo == null || !(colorInfo.hdrStaticInfo != null || (i2 = colorInfo.colorTransfer) == 7 || i2 == 6)) {
                        i7 = 2;
                    } else {
                        i7 = 4096;
                    }
                }
                int av1LevelNumberToConst = av1LevelNumberToConst(parseInt2);
                if (av1LevelNumberToConst != -1) {
                    return new Pair<>(Integer.valueOf(i7), Integer.valueOf(av1LevelNumberToConst));
                }
                a.D(parseInt2, "Unknown AV1 level: ", "CodecSpecificDataUtil");
                return null;
            } else {
                a.D(parseInt3, "Unknown AV1 bit depth: ", "CodecSpecificDataUtil");
                return null;
            }
        } catch (NumberFormatException unused) {
            a.C("Ignoring malformed AV1 codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        int i2;
        int i7;
        if (strArr.length < 2) {
            a.C("Ignoring malformed AVC codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                i7 = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i2 = Integer.parseInt(strArr[1].substring(4), 16);
            } else if (strArr.length >= 3) {
                int parseInt = Integer.parseInt(strArr[1]);
                i2 = Integer.parseInt(strArr[2]);
                i7 = parseInt;
            } else {
                Log.w("CodecSpecificDataUtil", "Ignoring malformed AVC codec string: " + str);
                return null;
            }
            int avcProfileNumberToConst = avcProfileNumberToConst(i7);
            if (avcProfileNumberToConst == -1) {
                a.D(i7, "Unknown AVC profile: ", "CodecSpecificDataUtil");
                return null;
            }
            int avcLevelNumberToConst = avcLevelNumberToConst(i2);
            if (avcLevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(avcProfileNumberToConst), Integer.valueOf(avcLevelNumberToConst));
            }
            a.D(i2, "Unknown AVC level: ", "CodecSpecificDataUtil");
            return null;
        } catch (NumberFormatException unused) {
            a.C("Ignoring malformed AVC codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
        if (r3.equals("ac-4") == false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(androidx.media3.common.Format r6) {
        /*
            java.lang.String r0 = r6.codecs
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r2 = "\\."
            java.lang.String[] r0 = r0.split(r2)
            java.lang.String r2 = "video/dolby-vision"
            java.lang.String r3 = r6.sampleMimeType
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x001e
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getDolbyVisionProfileAndLevel(r6, r0)
            return r6
        L_0x001e:
            r2 = 0
            r3 = r0[r2]
            r3.getClass()
            int r4 = r3.hashCode()
            r5 = -1
            switch(r4) {
                case 2986313: goto L_0x0096;
                case 3004662: goto L_0x008b;
                case 3006243: goto L_0x0080;
                case 3006244: goto L_0x0075;
                case 3199032: goto L_0x006a;
                case 3214780: goto L_0x005f;
                case 3224753: goto L_0x0054;
                case 3356560: goto L_0x0049;
                case 3475740: goto L_0x003d;
                case 3624515: goto L_0x002f;
                default: goto L_0x002c;
            }
        L_0x002c:
            r2 = r5
            goto L_0x009f
        L_0x002f:
            java.lang.String r2 = "vp09"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0039
            goto L_0x002c
        L_0x0039:
            r2 = 9
            goto L_0x009f
        L_0x003d:
            java.lang.String r2 = "s263"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0046
            goto L_0x002c
        L_0x0046:
            r2 = 8
            goto L_0x009f
        L_0x0049:
            java.lang.String r2 = "mp4a"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0052
            goto L_0x002c
        L_0x0052:
            r2 = 7
            goto L_0x009f
        L_0x0054:
            java.lang.String r2 = "iamf"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x005d
            goto L_0x002c
        L_0x005d:
            r2 = 6
            goto L_0x009f
        L_0x005f:
            java.lang.String r2 = "hvc1"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0068
            goto L_0x002c
        L_0x0068:
            r2 = 5
            goto L_0x009f
        L_0x006a:
            java.lang.String r2 = "hev1"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0073
            goto L_0x002c
        L_0x0073:
            r2 = 4
            goto L_0x009f
        L_0x0075:
            java.lang.String r2 = "avc2"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x007e
            goto L_0x002c
        L_0x007e:
            r2 = 3
            goto L_0x009f
        L_0x0080:
            java.lang.String r2 = "avc1"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0089
            goto L_0x002c
        L_0x0089:
            r2 = 2
            goto L_0x009f
        L_0x008b:
            java.lang.String r2 = "av01"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0094
            goto L_0x002c
        L_0x0094:
            r2 = 1
            goto L_0x009f
        L_0x0096:
            java.lang.String r4 = "ac-4"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x009f
            goto L_0x002c
        L_0x009f:
            switch(r2) {
                case 0: goto L_0x00d8;
                case 1: goto L_0x00cf;
                case 2: goto L_0x00c8;
                case 3: goto L_0x00c8;
                case 4: goto L_0x00bf;
                case 5: goto L_0x00bf;
                case 6: goto L_0x00b8;
                case 7: goto L_0x00b1;
                case 8: goto L_0x00aa;
                case 9: goto L_0x00a3;
                default: goto L_0x00a2;
            }
        L_0x00a2:
            return r1
        L_0x00a3:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getVp9ProfileAndLevel(r6, r0)
            return r6
        L_0x00aa:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getH263ProfileAndLevel(r6, r0)
            return r6
        L_0x00b1:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAacCodecProfileAndLevel(r6, r0)
            return r6
        L_0x00b8:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getIamfCodecProfileAndLevel(r6, r0)
            return r6
        L_0x00bf:
            java.lang.String r1 = r6.codecs
            androidx.media3.common.ColorInfo r6 = r6.colorInfo
            android.util.Pair r6 = getHevcProfileAndLevel(r1, r0, r6)
            return r6
        L_0x00c8:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAvcProfileAndLevel(r6, r0)
            return r6
        L_0x00cf:
            java.lang.String r1 = r6.codecs
            androidx.media3.common.ColorInfo r6 = r6.colorInfo
            android.util.Pair r6 = getAv1ProfileAndLevel(r1, r0, r6)
            return r6
        L_0x00d8:
            java.lang.String r6 = r6.codecs
            android.util.Pair r6 = getAc4CodecProfileAndLevel(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.CodecSpecificDataUtil.getCodecProfileAndLevel(androidx.media3.common.Format):android.util.Pair");
    }

    private static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            a.C("Ignoring malformed Dolby Vision codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            a.C("Ignoring malformed Dolby Vision codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        String group = matcher.group(1);
        Integer dolbyVisionStringToProfile = dolbyVisionStringToProfile(group);
        if (dolbyVisionStringToProfile == null) {
            a.C("Unknown Dolby Vision profile string: ", group, "CodecSpecificDataUtil");
            return null;
        }
        String str2 = strArr[2];
        Integer dolbyVisionStringToLevel = dolbyVisionStringToLevel(str2);
        if (dolbyVisionStringToLevel != null) {
            return new Pair<>(dolbyVisionStringToProfile, dolbyVisionStringToLevel);
        }
        a.C("Unknown Dolby Vision level string: ", str2, "CodecSpecificDataUtil");
        return null;
    }

    private static Pair<Integer, Integer> getH263ProfileAndLevel(String str, String[] strArr) {
        Pair<Integer, Integer> pair = new Pair<>(1, 1);
        if (strArr.length < 3) {
            a.C("Ignoring malformed H263 codec string: ", str, "CodecSpecificDataUtil");
            return pair;
        }
        try {
            return new Pair<>(Integer.valueOf(Integer.parseInt(strArr[1])), Integer.valueOf(Integer.parseInt(strArr[2])));
        } catch (NumberFormatException unused) {
            a.C("Ignoring malformed H263 codec string: ", str, "CodecSpecificDataUtil");
            return pair;
        }
    }

    public static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr, ColorInfo colorInfo) {
        if (strArr.length < 4) {
            a.C("Ignoring malformed HEVC codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        int i2 = 1;
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            a.C("Ignoring malformed HEVC codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        String group = matcher.group(1);
        if (!"1".equals(group)) {
            i2 = 6;
            if ("2".equals(group)) {
                if (colorInfo == null || colorInfo.colorTransfer != 6) {
                    i2 = 2;
                } else {
                    i2 = 4096;
                }
            } else if (!"6".equals(group)) {
                a.C("Unknown HEVC profile string: ", group, "CodecSpecificDataUtil");
                return null;
            }
        }
        String str2 = strArr[3];
        Integer hevcCodecStringToProfileLevel = hevcCodecStringToProfileLevel(str2);
        if (hevcCodecStringToProfileLevel != null) {
            return new Pair<>(Integer.valueOf(i2), hevcCodecStringToProfileLevel);
        }
        a.C("Unknown HEVC level string: ", str2, "CodecSpecificDataUtil");
        return null;
    }

    private static Pair<Integer, Integer> getIamfCodecProfileAndLevel(String str, String[] strArr) {
        int i2 = 4;
        if (strArr.length < 4) {
            a.C("Ignoring malformed IAMF codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        try {
            int parseInt = 1 << (Integer.parseInt(strArr[1]) + 16);
            String str2 = strArr[3];
            str2.getClass();
            char c5 = 65535;
            switch (str2.hashCode()) {
                case 2464863:
                    if (str2.equals("Opus")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 3114792:
                    if (str2.equals("fLaC")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 3238865:
                    if (str2.equals("ipcm")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 3356560:
                    if (str2.equals("mp4a")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    i2 = 1;
                    break;
                case 1:
                    break;
                case 2:
                    i2 = 8;
                    break;
                case 3:
                    i2 = 2;
                    break;
                default:
                    Log.w("CodecSpecificDataUtil", "Ignoring unknown codec identifier for IAMF auxiliary profile: " + strArr[3]);
                    return null;
            }
            return new Pair<>(Integer.valueOf(16777216 | parseInt | i2), 0);
        } catch (NumberFormatException e) {
            Log.w("CodecSpecificDataUtil", "Ignoring malformed primary profile in IAMF codec string: " + strArr[1], e);
            return null;
        }
    }

    private static Pair<Integer, Integer> getVp9ProfileAndLevel(String str, String[] strArr) {
        if (strArr.length < 3) {
            a.C("Ignoring malformed VP9 codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            int parseInt2 = Integer.parseInt(strArr[2]);
            int vp9ProfileNumberToConst = vp9ProfileNumberToConst(parseInt);
            if (vp9ProfileNumberToConst == -1) {
                a.D(parseInt, "Unknown VP9 profile: ", "CodecSpecificDataUtil");
                return null;
            }
            int vp9LevelNumberToConst = vp9LevelNumberToConst(parseInt2);
            if (vp9LevelNumberToConst != -1) {
                return new Pair<>(Integer.valueOf(vp9ProfileNumberToConst), Integer.valueOf(vp9LevelNumberToConst));
            }
            a.D(parseInt2, "Unknown VP9 level: ", "CodecSpecificDataUtil");
            return null;
        } catch (NumberFormatException unused) {
            a.C("Ignoring malformed VP9 codec string: ", str, "CodecSpecificDataUtil");
            return null;
        }
    }

    private static Integer hevcCodecStringToProfileLevel(String str) {
        if (str == null) {
            return null;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case 70821:
                if (str.equals("H30")) {
                    c5 = 0;
                    break;
                }
                break;
            case 70914:
                if (str.equals("H60")) {
                    c5 = 1;
                    break;
                }
                break;
            case 70917:
                if (str.equals("H63")) {
                    c5 = 2;
                    break;
                }
                break;
            case 71007:
                if (str.equals("H90")) {
                    c5 = 3;
                    break;
                }
                break;
            case 71010:
                if (str.equals("H93")) {
                    c5 = 4;
                    break;
                }
                break;
            case 74665:
                if (str.equals("L30")) {
                    c5 = 5;
                    break;
                }
                break;
            case 74758:
                if (str.equals("L60")) {
                    c5 = 6;
                    break;
                }
                break;
            case 74761:
                if (str.equals("L63")) {
                    c5 = 7;
                    break;
                }
                break;
            case 74851:
                if (str.equals("L90")) {
                    c5 = 8;
                    break;
                }
                break;
            case 74854:
                if (str.equals("L93")) {
                    c5 = 9;
                    break;
                }
                break;
            case 2193639:
                if (str.equals("H120")) {
                    c5 = 10;
                    break;
                }
                break;
            case 2193642:
                if (str.equals("H123")) {
                    c5 = 11;
                    break;
                }
                break;
            case 2193732:
                if (str.equals("H150")) {
                    c5 = 12;
                    break;
                }
                break;
            case 2193735:
                if (str.equals("H153")) {
                    c5 = 13;
                    break;
                }
                break;
            case 2193738:
                if (str.equals("H156")) {
                    c5 = 14;
                    break;
                }
                break;
            case 2193825:
                if (str.equals("H180")) {
                    c5 = 15;
                    break;
                }
                break;
            case 2193828:
                if (str.equals("H183")) {
                    c5 = 16;
                    break;
                }
                break;
            case 2193831:
                if (str.equals("H186")) {
                    c5 = 17;
                    break;
                }
                break;
            case 2312803:
                if (str.equals("L120")) {
                    c5 = 18;
                    break;
                }
                break;
            case 2312806:
                if (str.equals("L123")) {
                    c5 = 19;
                    break;
                }
                break;
            case 2312896:
                if (str.equals("L150")) {
                    c5 = 20;
                    break;
                }
                break;
            case 2312899:
                if (str.equals("L153")) {
                    c5 = 21;
                    break;
                }
                break;
            case 2312902:
                if (str.equals("L156")) {
                    c5 = 22;
                    break;
                }
                break;
            case 2312989:
                if (str.equals("L180")) {
                    c5 = 23;
                    break;
                }
                break;
            case 2312992:
                if (str.equals("L183")) {
                    c5 = 24;
                    break;
                }
                break;
            case 2312995:
                if (str.equals("L186")) {
                    c5 = 25;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 2;
            case 1:
                return 8;
            case 2:
                return 32;
            case 3:
                return 128;
            case 4:
                return 512;
            case 5:
                return 1;
            case 6:
                return 4;
            case 7:
                return 16;
            case 8:
                return 64;
            case 9:
                return 256;
            case 10:
                return 2048;
            case 11:
                return Integer.valueOf(SerializeOptions.SORT);
            case 12:
                return 32768;
            case 13:
                return 131072;
            case 14:
                return 524288;
            case 15:
                return 2097152;
            case 16:
                return 8388608;
            case 17:
                return 33554432;
            case 18:
                return 1024;
            case 19:
                return 4096;
            case 20:
                return 16384;
            case 21:
                return 65536;
            case 22:
                return 262144;
            case 23:
                return Integer.valueOf(MediaDefs.Meta.SEF.SEF_MIN_SIZE);
            case 24:
                return Integer.valueOf(OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE);
            case 25:
                return 16777216;
            default:
                return null;
        }
    }

    private static int mp4aAudioObjectTypeToProfile(int i2) {
        int i7 = 17;
        if (i2 != 17) {
            i7 = 20;
            if (i2 != 20) {
                i7 = 23;
                if (i2 != 23) {
                    i7 = 29;
                    if (i2 != 29) {
                        i7 = 39;
                        if (i2 != 39) {
                            i7 = 42;
                            if (i2 != 42) {
                                switch (i2) {
                                    case 1:
                                        return 1;
                                    case 2:
                                        return 2;
                                    case 3:
                                        return 3;
                                    case 4:
                                        return 4;
                                    case 5:
                                        return 5;
                                    case 6:
                                        return 6;
                                    default:
                                        return -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return i7;
    }

    public static Pair<Integer, Integer> parseAlacAudioSpecificConfig(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.setPosition(9);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(20);
        return Pair.create(Integer.valueOf(parsableByteArray.readUnsignedIntToInt()), Integer.valueOf(readUnsignedByte));
    }

    private static int vp9LevelNumberToConst(int i2) {
        if (i2 == 10) {
            return 1;
        }
        if (i2 == 11) {
            return 2;
        }
        if (i2 == 20) {
            return 4;
        }
        if (i2 == 21) {
            return 8;
        }
        if (i2 == 30) {
            return 16;
        }
        if (i2 == 31) {
            return 32;
        }
        if (i2 == 40) {
            return 64;
        }
        if (i2 == 41) {
            return 128;
        }
        if (i2 == 50) {
            return 256;
        }
        if (i2 == 51) {
            return 512;
        }
        switch (i2) {
            case 60:
                return 2048;
            case 61:
                return 4096;
            case 62:
                return SerializeOptions.SORT;
            default:
                return -1;
        }
    }

    private static int vp9ProfileNumberToConst(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 1) {
            return 2;
        }
        if (i2 == 2) {
            return 4;
        }
        if (i2 != 3) {
            return -1;
        }
        return 8;
    }
}
