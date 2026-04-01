package androidx.media3.common;

import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MimeTypes {
    private static final Pattern MP4A_RFC_6381_CODEC_PATTERN = Pattern.compile("^mp4a\\.([a-zA-Z0-9]{2})(?:\\.([0-9]{1,2}))?$");
    private static final ArrayList<Object> customMimeTypes = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Mp4aObjectType {
        public final int audioObjectTypeIndication;
        public final int objectTypeIndication;

        public Mp4aObjectType(int i2, int i7) {
            this.objectTypeIndication = i2;
            this.audioObjectTypeIndication = i7;
        }

        public int getEncoding() {
            int i2 = this.audioObjectTypeIndication;
            if (i2 == 2) {
                return 10;
            }
            if (i2 == 5) {
                return 11;
            }
            if (i2 == 29) {
                return 12;
            }
            if (i2 == 42) {
                return 16;
            }
            if (i2 == 22) {
                return 1073741824;
            }
            if (i2 != 23) {
                return 0;
            }
            return 15;
        }
    }

    public static boolean allSamplesAreSyncSamples(String str, String str2) {
        Mp4aObjectType objectTypeFromMp4aRFC6381CodecString;
        int encoding;
        if (str == null) {
            return false;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2123537834:
                if (str.equals("audio/eac3-joc")) {
                    c5 = 0;
                    break;
                }
                break;
            case -432837260:
                if (str.equals("audio/mpeg-L1")) {
                    c5 = 1;
                    break;
                }
                break;
            case -432837259:
                if (str.equals("audio/mpeg-L2")) {
                    c5 = 2;
                    break;
                }
                break;
            case -53558318:
                if (str.equals(Encode.CodecsMime.AUDIO_CODEC_AAC)) {
                    c5 = 3;
                    break;
                }
                break;
            case 187078296:
                if (str.equals("audio/ac3")) {
                    c5 = 4;
                    break;
                }
                break;
            case 187094639:
                if (str.equals("audio/raw")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1504578661:
                if (str.equals("audio/eac3")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1504619009:
                if (str.equals("audio/flac")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1504831518:
                if (str.equals("audio/mpeg")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1903231877:
                if (str.equals("audio/g711-alaw")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1903589369:
                if (str.equals("audio/g711-mlaw")) {
                    c5 = 10;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            case 3:
                if (str2 == null || (objectTypeFromMp4aRFC6381CodecString = getObjectTypeFromMp4aRFC6381CodecString(str2)) == null || (encoding = objectTypeFromMp4aRFC6381CodecString.getEncoding()) == 0 || encoding == 16) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public static boolean containsCodecsCorrespondingToMimeType(String str, String str2) {
        if (getCodecsCorrespondingToMimeType(str, str2) != null) {
            return true;
        }
        return false;
    }

    public static String getCodecsCorrespondingToMimeType(String str, String str2) {
        if (!(str == null || str2 == null)) {
            String[] splitCodecs = Util.splitCodecs(str);
            StringBuilder sb2 = new StringBuilder();
            for (String str3 : splitCodecs) {
                if (str2.equals(getMediaMimeType(str3))) {
                    if (sb2.length() > 0) {
                        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                    sb2.append(str3);
                }
            }
            if (sb2.length() > 0) {
                return sb2.toString();
            }
        }
        return null;
    }

    private static String getCustomMimeTypeForCodec(String str) {
        ArrayList<Object> arrayList = customMimeTypes;
        if (arrayList.size() <= 0) {
            return null;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    public static String getMediaMimeType(String str) {
        Mp4aObjectType objectTypeFromMp4aRFC6381CodecString;
        String str2 = null;
        if (str == null) {
            return null;
        }
        String S = k.S(str.trim());
        if (S.startsWith("avc1") || S.startsWith("avc3")) {
            return Encode.CodecsMime.VIDEO_CODEC_H264;
        }
        if (S.startsWith("hev1") || S.startsWith("hvc1")) {
            return "video/hevc";
        }
        if (S.startsWith("dvav") || S.startsWith("dva1") || S.startsWith("dvhe") || S.startsWith("dvh1")) {
            return "video/dolby-vision";
        }
        if (S.startsWith("av01")) {
            return "video/av01";
        }
        if (S.startsWith("vp9") || S.startsWith("vp09")) {
            return "video/x-vnd.on2.vp9";
        }
        if (S.startsWith("vp8") || S.startsWith("vp08")) {
            return "video/x-vnd.on2.vp8";
        }
        if (S.startsWith("mp4a")) {
            if (S.startsWith("mp4a.") && (objectTypeFromMp4aRFC6381CodecString = getObjectTypeFromMp4aRFC6381CodecString(S)) != null) {
                str2 = getMimeTypeFromMp4ObjectType(objectTypeFromMp4aRFC6381CodecString.objectTypeIndication);
            }
            if (str2 == null) {
                return Encode.CodecsMime.AUDIO_CODEC_AAC;
            }
            return str2;
        } else if (S.startsWith("mha1")) {
            return "audio/mha1";
        } else {
            if (S.startsWith("mhm1")) {
                return "audio/mhm1";
            }
            if (S.startsWith("ac-3") || S.startsWith("dac3")) {
                return "audio/ac3";
            }
            if (S.startsWith("ec-3") || S.startsWith("dec3")) {
                return "audio/eac3";
            }
            if (S.startsWith("ec+3")) {
                return "audio/eac3-joc";
            }
            if (S.startsWith("ac-4") || S.startsWith("dac4")) {
                return "audio/ac4";
            }
            if (S.startsWith("dtsc")) {
                return "audio/vnd.dts";
            }
            if (S.startsWith("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (S.startsWith("dtsh") || S.startsWith("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (S.startsWith("dtsx")) {
                return "audio/vnd.dts.uhd;profile=p2";
            }
            if (S.startsWith("opus")) {
                return "audio/opus";
            }
            if (S.startsWith("vorbis")) {
                return "audio/vorbis";
            }
            if (S.startsWith("flac")) {
                return "audio/flac";
            }
            if (S.startsWith("stpp")) {
                return "application/ttml+xml";
            }
            if (S.startsWith("wvtt")) {
                return "text/vtt";
            }
            if (S.contains("cea708")) {
                return "application/cea-708";
            }
            if (S.contains("eia608") || S.contains("cea608")) {
                return "application/cea-608";
            }
            return getCustomMimeTypeForCodec(S);
        }
    }

    public static String getMimeTypeFromMp4ObjectType(int i2) {
        if (i2 == 32) {
            return Encode.ContentType.VIDEO_MP4V_ES;
        }
        if (i2 == 33) {
            return Encode.CodecsMime.VIDEO_CODEC_H264;
        }
        if (i2 == 35) {
            return "video/hevc";
        }
        if (i2 == 64) {
            return Encode.CodecsMime.AUDIO_CODEC_AAC;
        }
        if (i2 == 163) {
            return "video/wvc1";
        }
        if (i2 == 177) {
            return "video/x-vnd.on2.vp9";
        }
        if (i2 == 221) {
            return "audio/vorbis";
        }
        if (i2 == 165) {
            return "audio/ac3";
        }
        if (i2 == 166) {
            return "audio/eac3";
        }
        switch (i2) {
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
                return "video/mpeg2";
            case 102:
            case 103:
            case 104:
                return Encode.CodecsMime.AUDIO_CODEC_AAC;
            case 105:
            case 107:
                return "audio/mpeg";
            case 106:
                return Encode.ContentType.VIDEO_MPEG;
            case 108:
                return "image/jpeg";
            default:
                switch (i2) {
                    case 169:
                    case 172:
                        return "audio/vnd.dts";
                    case MOCRLang.MALAYALAM /*170*/:
                    case 171:
                        return "audio/vnd.dts.hd";
                    case 173:
                        return "audio/opus";
                    case 174:
                        return "audio/ac4";
                    default:
                        return null;
                }
        }
    }

    public static Mp4aObjectType getObjectTypeFromMp4aRFC6381CodecString(String str) {
        int i2;
        Matcher matcher = MP4A_RFC_6381_CODEC_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String str2 = (String) Assertions.checkNotNull(matcher.group(1));
        String group = matcher.group(2);
        try {
            int parseInt = Integer.parseInt(str2, 16);
            if (group != null) {
                i2 = Integer.parseInt(group);
            } else {
                i2 = 0;
            }
            return new Mp4aObjectType(parseInt, i2);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static String getTopLevelType(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(47)) == -1) {
            return null;
        }
        return str.substring(0, indexOf);
    }

    public static int getTrackType(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isAudio(str)) {
            return 1;
        }
        if (isVideo(str)) {
            return 2;
        }
        if (isText(str)) {
            return 3;
        }
        if (isImage(str)) {
            return 4;
        }
        if ("application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-icy".equals(str) || "application/vnd.dvb.ait".equals(str)) {
            return 5;
        }
        if ("application/x-camera-motion".equals(str)) {
            return 6;
        }
        return getTrackTypeForCustomMimeType(str);
    }

    private static int getTrackTypeForCustomMimeType(String str) {
        ArrayList<Object> arrayList = customMimeTypes;
        if (arrayList.size() <= 0) {
            return -1;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    public static boolean isAudio(String str) {
        return "audio".equals(getTopLevelType(str));
    }

    public static boolean isImage(String str) {
        if ("image".equals(getTopLevelType(str)) || "application/x-image-uri".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isText(String str) {
        if ("text".equals(getTopLevelType(str)) || "application/x-media3-cues".equals(str) || "application/cea-608".equals(str) || "application/cea-708".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/x-subrip".equals(str) || "application/ttml+xml".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-rawcc".equals(str) || "application/vobsub".equals(str) || "application/pgs".equals(str) || "application/dvbsubs".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isVideo(String str) {
        return "video".equals(getTopLevelType(str));
    }

    public static String normalizeMimeType(String str) {
        if (str == null) {
            return null;
        }
        String S = k.S(str);
        S.getClass();
        char c5 = 65535;
        switch (S.hashCode()) {
            case -1833600100:
                if (S.equals("video/x-mvhevc")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1007807498:
                if (S.equals("audio/x-flac")) {
                    c5 = 1;
                    break;
                }
                break;
            case -979095690:
                if (S.equals("application/x-mpegurl")) {
                    c5 = 2;
                    break;
                }
                break;
            case -586683234:
                if (S.equals("audio/x-wav")) {
                    c5 = 3;
                    break;
                }
                break;
            case -432836268:
                if (S.equals("audio/mpeg-l1")) {
                    c5 = 4;
                    break;
                }
                break;
            case -432836267:
                if (S.equals("audio/mpeg-l2")) {
                    c5 = 5;
                    break;
                }
                break;
            case 187090231:
                if (S.equals("audio/mp3")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "video/mv-hevc";
            case 1:
                return "audio/flac";
            case 2:
                return "application/x-mpegURL";
            case 3:
                return "audio/wav";
            case 4:
                return "audio/mpeg-L1";
            case 5:
                return "audio/mpeg-L2";
            case 6:
                return "audio/mpeg";
            default:
                return S;
        }
    }
}
