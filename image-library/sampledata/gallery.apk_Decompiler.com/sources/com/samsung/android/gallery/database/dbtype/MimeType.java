package com.samsung.android.gallery.database.dbtype;

import A.a;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileType;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MimeType {
    JPG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    BMP("image/bmp"),
    DNG("image/dng"),
    RAW("image/raw"),
    HEIF("image/heif"),
    HEIC("image/heic"),
    WEBP("image/webp"),
    AVIF("image/avif"),
    TIFF("image/tiff"),
    JP2("image/jp2"),
    JPX("image/jpx"),
    ETC("image/etc");
    
    public static final HashMap<String, MimeType> DNG_MIME_TYPE_MAP = null;
    private static final HashMap<String, MimeType> MIME_TYPE_MAP = null;
    public static final HashMap<String, MimeType> RAW_MIME_TYPE_MAP = null;
    public final String mimeType;

    static {
        DNG_MIME_TYPE_MAP = new HashMap<String, MimeType>() {
            {
                put("image/x-adobe-dng", MimeType.DNG);
            }
        };
        RAW_MIME_TYPE_MAP = new HashMap<String, MimeType>() {
            {
                MimeType mimeType = MimeType.RAW;
                put("image/x-canon-cr2", mimeType);
                put("image/x-canon-cr3", mimeType);
                put("image/x-nikon-nef", mimeType);
                put("image/x-nikon-nrw", mimeType);
                put("image/x-sony-arw", mimeType);
                put("image/x-panasonic-rw2", mimeType);
                put("image/x-olympus-orf", mimeType);
                put("image/x-fuji-raf", mimeType);
                put("image/x-pentax-pef", mimeType);
                put("image/x-samsung-srw", mimeType);
                put("image/dng", mimeType);
            }
        };
        MIME_TYPE_MAP = new HashMap<String, MimeType>() {
            {
                MimeType mimeType = MimeType.JPG;
                put("image/jpeg", mimeType);
                put("image/jpg", mimeType);
                put("image/png", MimeType.PNG);
                put("image/gif", MimeType.GIF);
                MimeType mimeType2 = MimeType.BMP;
                put("image/bmp", mimeType2);
                put("image/x-ms-bmp", mimeType2);
                put("image/heif", MimeType.HEIF);
                put("image/heic", MimeType.HEIC);
                put("image/avif", MimeType.AVIF);
                put("image/webp", MimeType.WEBP);
                put("image/tiff", MimeType.TIFF);
                put("image/jp2", MimeType.JP2);
                put("image/jpx", MimeType.JPX);
                putAll(MimeType.DNG_MIME_TYPE_MAP);
                putAll(MimeType.RAW_MIME_TYPE_MAP);
            }
        };
    }

    private MimeType(String str) {
        this.mimeType = str;
    }

    public static MimeType from(String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[32];
            MimeType mimeType2 = fileInputStream.read(bArr, 0, 32) == 32 ? getMimeType(bArr) : ETC;
            fileInputStream.close();
            return mimeType2;
        } catch (Error | Exception unused) {
            return ETC;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static MimeType getMimeType(String str, String str2) {
        MimeType mimeType2 = str != null ? str.startsWith("video/") ? ETC : MIME_TYPE_MAP.get(str) : null;
        if (mimeType2 != null) {
            return mimeType2;
        }
        return getMimeType(str2);
    }

    public static boolean isBmp(String str) {
        return str.contains("bmp");
    }

    public static boolean isHeif(String str) {
        if ("image/heif".equals(str) || "image/heic".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isJpeg(String str) {
        if ("image/jpeg".equals(str) || "image/jpg".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isPng(String str) {
        return "image/png".equals(str);
    }

    public static boolean isQuramDecodable(MimeType mimeType2) {
        if (mimeType2.ordinal() < DNG.ordinal()) {
            return true;
        }
        return false;
    }

    public static boolean isRaw(String str) {
        MimeType orDefault = MIME_TYPE_MAP.getOrDefault(str, ETC);
        if (orDefault == RAW || orDefault == DNG) {
            return true;
        }
        return false;
    }

    public static boolean isWebp(String str) {
        return "image/webp".equals(str);
    }

    public static MimeType getMimeType(String str) {
        return MIME_TYPE_MAP.getOrDefault(FileType.getMimeType(str), ETC);
    }

    public static MimeType getMimeType(byte[] bArr) {
        return MIME_TYPE_MAP.getOrDefault(FileType.getMimeType(bArr), ETC);
    }

    public static String from(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.getDefault()));
            if (mimeTypeFromExtension == null) {
                mimeTypeFromExtension = FileType.getMimeType(uri.getPath(), (String) null);
            }
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        try {
            return AppResources.getAppContext().getContentResolver().getType(uri);
        } catch (Exception e) {
            a.s(e, new StringBuilder("from#uri failed. e="), "MimeType");
            return null;
        }
    }
}
