package com.samsung.android.gallery.support.utils;

import A.a;
import android.text.TextUtils;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sum.core.message.Message;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileType {
    /* access modifiers changed from: private */
    public static final HashMap<String, String> sMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ReverseMapHolder {
        private static final HashMap<String, String> sReverseMap = init();

        public static String get(String str) {
            return sReverseMap.get(str);
        }

        private static HashMap<String, String> init() {
            HashMap<String, String> hashMap = new HashMap<>();
            for (Map.Entry entry : FileType.sMap.entrySet()) {
                hashMap.put((String) entry.getValue(), (String) entry.getKey());
            }
            hashMap.put("image/jpeg", "jpg");
            hashMap.put("image/tiff", "tif");
            hashMap.put("video/quicktime", "mov");
            return hashMap;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Signature {
        static final int[] IMAGE_BMP = {66, 77};
        static final int[] IMAGE_GIF = {71, 73, 70};
        static final int[] IMAGE_JPEG = {ScoverState.TYPE_NFC_SMART_COVER, 216, ScoverState.TYPE_NFC_SMART_COVER};
        static final int[] IMAGE_PNG = {137, 80, 78, 71, 13, 10, 26, 10};
        static final int[] IMAGE_WEBP = {82, 73, 70, 70, -1, -1, -1, -1, 87, 69, 66, 80};
        static final List<String> MP4_HEIC_HEADER = Arrays.asList(new String[]{IFormat.FORMAT_HEIC, "heix"});
        static final List<String> MP4_VIDEO_HEADER = Arrays.asList(new String[]{"avc1", "iso2", "isom", "mmp4", "mp41", "mp42", "mp71", "msnv", "ndas", "ndsc", "ndsh", "ndsm", "ndsp", "ndss", "ndxc", "ndxh", "ndxm", "ndxp", "ndxs"});
        static final int[] VIDEO_AVI = {82, 73, 70, 70, -1, -1, -1, -1, 65, 86, 73, 32};
        static final int[] VIDEO_WEBM = {26, 69, 223, 163};
        static final int[] VIDEO_WMV = {48, 38, 178, 117, 142, 102, 207, 17, 166, 217, 0, MOCRLang.MALAYALAM, 0, 98, Message.END_OF_STREAM, 108};
    }

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        sMap = hashMap;
        hashMap.put("jpg", "image/jpeg");
        hashMap.put(IFormat.FORMAT_JPEG, "image/jpeg");
        hashMap.put(IFormat.FORMAT_HEIC, "image/heic");
        hashMap.put("heif", "image/heif");
        hashMap.put("png", "image/png");
        hashMap.put("gif", "image/gif");
        hashMap.put("webp", "image/webp");
        hashMap.put("avif", "image/avif");
        hashMap.put("bmp", "image/x-ms-bmp");
        hashMap.put("dng", "image/x-adobe-dng");
        hashMap.put("cr2", "image/x-canon-cr2");
        hashMap.put("nef", "image/x-nikon-nef");
        hashMap.put("nrw", "image/x-nikon-nrw");
        hashMap.put("arw", "image/x-sony-arw");
        hashMap.put("rw2", "image/x-panasonic-rw2");
        hashMap.put("orf", "image/x-olympus-orf");
        hashMap.put("raf", "image/x-fuji-raf");
        hashMap.put("pef", "image/x-pentax-pef");
        hashMap.put("srw", "image/x-samsung-srw");
        hashMap.put("tif", "image/tiff");
        hashMap.put("tiff", "image/tiff");
        hashMap.put("jp2", "image/jp2");
        hashMap.put("jpx", "image/jpx");
        hashMap.put("ico", "image/x-icon");
        hashMap.put("svg", "image/svg+xml");
        hashMap.put("apng", "image/apng");
        hashMap.put("mpo", "image/mpo");
        hashMap.put("golf", "image/golf");
        hashMap.put(IFormat.FORMAT_MP4, Encode.ContentType.VIDEO_MP4);
        hashMap.put("webm", Encode.ContentType.VIDEO_WEBM);
        hashMap.put("3gp", "video/3gpp");
        hashMap.put("3g2", Encode.ContentType.VIDEO_3G2);
        hashMap.put("ts", "video/MP2T");
        hashMap.put("mov", "video/quicktime");
        hashMap.put("qt", "video/quicktime");
        hashMap.put("avi", "video/x-msvideo");
        hashMap.put("wmv", Encode.ContentType.VIDEO_WMV);
        hashMap.put("asf", Encode.ContentType.VIDEO_ASF);
        hashMap.put("m4v", "video/x-m4v");
        hashMap.put("m2v", "video/x-m2v");
        hashMap.put("flv", "video/x-flv");
        hashMap.put("mkv", Encode.ContentType.VIDEO_MKV);
        hashMap.put("zip", "application/zip");
    }

    private static int byte2Int(byte b) {
        return b & 255;
    }

    private static boolean compareSignature(int[] iArr, int[] iArr2) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i7 = iArr[i2];
            if (i7 >= 0 && i7 != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsMimeType(String str) {
        return sMap.containsValue(str);
    }

    private static String getExtension(String str) {
        return FileUtils.getExtension(str).toLowerCase(Locale.getDefault());
    }

    public static String getFileExtension(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = ReverseMapHolder.get(str);
        if (str2 != null) {
            return str2;
        }
        if (str.startsWith("image/x-") || str.startsWith("video/x-")) {
            return str.replaceAll("^.*\\-", "");
        }
        return str.replaceFirst("^.*/", "");
    }

    private static String getFirstMp4Header(byte[] bArr) {
        if (bArr[4] != 102 || bArr[5] != 116 || bArr[6] != 121 || bArr[7] != 112) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 8, bArr2, 0, 4);
        return new String(bArr2, StandardCharsets.US_ASCII);
    }

    public static String getMimeType(String str) {
        return getMimeType(str, "*/*");
    }

    public static boolean isImage(String str) {
        return getMimeType(str).startsWith("image");
    }

    public static boolean isKnownImageType(String str) {
        return getMimeType(str).startsWith("image/");
    }

    public static boolean isKnownType(String str) {
        return !"*/*".equals(getMimeType(str));
    }

    public static boolean isVideo(String str) {
        return getMimeType(str).startsWith("video");
    }

    private static int[] byte2Int(byte[] bArr, int i2) {
        int min = Math.min(i2, bArr.length);
        int[] iArr = new int[min];
        for (int i7 = 0; i7 < min; i7++) {
            iArr[i7] = byte2Int(bArr[i7]);
        }
        return iArr;
    }

    public static String getMimeType(String str, String str2) {
        return sMap.getOrDefault(getExtension(str), str2);
    }

    public static String getMimeType(byte[] bArr) {
        try {
            String firstMp4Header = getFirstMp4Header(bArr);
            if (firstMp4Header == null) {
                int[] byte2Int = byte2Int(bArr, 32);
                if (compareSignature(Signature.IMAGE_GIF, byte2Int)) {
                    return "image/gif";
                }
                if (compareSignature(Signature.IMAGE_PNG, byte2Int)) {
                    return "image/png";
                }
                if (compareSignature(Signature.IMAGE_WEBP, byte2Int)) {
                    return "image/webp";
                }
                if (compareSignature(Signature.IMAGE_BMP, byte2Int)) {
                    return "image/x-ms-bmp";
                }
                if (compareSignature(Signature.IMAGE_JPEG, byte2Int)) {
                    return "image/jpeg";
                }
                if (compareSignature(Signature.VIDEO_WMV, byte2Int)) {
                    return Encode.ContentType.VIDEO_WMV;
                }
                if (compareSignature(Signature.VIDEO_AVI, byte2Int)) {
                    return "video/x-msvideo";
                }
                if (compareSignature(Signature.VIDEO_WEBM, byte2Int)) {
                    return Encode.ContentType.VIDEO_WEBM;
                }
                return "*/*";
            } else if (Signature.MP4_HEIC_HEADER.contains(firstMp4Header)) {
                return "image/heic";
            } else {
                if ("avif".equals(firstMp4Header)) {
                    return "image/avif";
                }
                if (Signature.MP4_VIDEO_HEADER.contains(firstMp4Header)) {
                    return Encode.ContentType.VIDEO_MP4;
                }
                if (firstMp4Header.startsWith("3g")) {
                    return Encode.ContentType.VIDEO_3GP;
                }
                if (firstMp4Header.startsWith("qt")) {
                    return "video/quicktime";
                }
                return "*/*";
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("getMimeType failed e="), "FileType");
        }
    }
}
