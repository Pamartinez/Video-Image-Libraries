package com.samsung.android.gallery.module.media;

import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreateMediaHelper {
    public static final int COLLAGE_MAX_ITEM_COUNT;
    static boolean SKIP_MIME_TYPE_FILTER = SdkConfig.atLeast(SdkConfig.GED.Q);
    static boolean SUPPORT_HEIF_HEIC = SdkConfig.atLeast(SdkConfig.GED.P);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SupportType {
        SUPPORT,
        NOT_SUPPORT_VIDEO_FOR_COLLAGE,
        NOT_SUPPORT_VIDEO_COUNT_FOR_COLLAGE,
        NOT_SUPPORT_VIDEO_FOR_GIF,
        NOT_SUPPORT_VIDEO_TYPE,
        NOT_SUPPORT_GIF,
        NOT_SUPPORT_CLOUD_VIDEO,
        NOT_SUPPORT_CLOUD_ITEM,
        NOT_SUPPORT_COMMON,
        NOT_SUPPORT_EXCEED_MAX_COUNT,
        NOT_SUPPORT_MEITU_POST_ITEM_COUNT,
        NOT_SUPPORT_MEITU_POST_VIDEO_COUNT,
        NOT_SUPPORT_CLOUD_VIDEO_FOR_COLLAGE
    }

    static {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
            i2 = 14;
        } else {
            i2 = 6;
        }
        COLLAGE_MAX_ITEM_COUNT = i2;
    }

    private static SupportType getImageSupportType(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1487464693:
                if (str.equals("image/heic")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1487464690:
                if (str.equals("image/heif")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1487394660:
                if (str.equals("image/jpeg")) {
                    c5 = 2;
                    break;
                }
                break;
            case -879272239:
                if (str.equals("image/bmp")) {
                    c5 = 3;
                    break;
                }
                break;
            case -879267568:
                if (str.equals("image/gif")) {
                    c5 = 4;
                    break;
                }
                break;
            case -879264467:
                if (str.equals("image/jpg")) {
                    c5 = 5;
                    break;
                }
                break;
            case -879258763:
                if (str.equals("image/png")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
                if (SUPPORT_HEIF_HEIC) {
                    return SupportType.SUPPORT;
                }
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                return SupportType.SUPPORT;
            case 4:
                return SupportType.NOT_SUPPORT_GIF;
        }
        if (!str.endsWith("/bmp") && !str.endsWith("-bmp") && !str.endsWith("/bitmap") && !str.endsWith("-bitmap")) {
            return SupportType.NOT_SUPPORT_COMMON;
        }
        Log.d("supportMovieMaker", "special mime type of bmp : ".concat(str));
        return SupportType.SUPPORT;
    }

    private static SupportType getVideoSupportType(String str) {
        if (str.contains(IFormat.FORMAT_MP4) || str.contains("mpeg") || str.contains("webm")) {
            return SupportType.SUPPORT;
        }
        return SupportType.NOT_SUPPORT_VIDEO_TYPE;
    }

    public static SupportType supportCollageMaker(MediaType mediaType) {
        if (mediaType.equals(MediaType.Image)) {
            return SupportType.SUPPORT;
        }
        return SupportType.NOT_SUPPORT_VIDEO_FOR_COLLAGE;
    }

    public static SupportType supportGifMaker(MediaType mediaType) {
        if (mediaType.equals(MediaType.Image)) {
            return SupportType.SUPPORT;
        }
        return SupportType.NOT_SUPPORT_VIDEO_FOR_GIF;
    }

    public static SupportType supportMovieMaker(MediaType mediaType, String str, boolean z) {
        if (SKIP_MIME_TYPE_FILTER) {
            if (z) {
                if (mediaType == null) {
                    return SupportType.NOT_SUPPORT_CLOUD_ITEM;
                }
                if (mediaType.equals(MediaType.Video)) {
                    return SupportType.NOT_SUPPORT_CLOUD_VIDEO;
                }
            }
            return SupportType.SUPPORT;
        } else if (str == null) {
            return SupportType.NOT_SUPPORT_COMMON;
        } else {
            if (z) {
                return SupportType.NOT_SUPPORT_CLOUD_ITEM;
            }
            if (mediaType.equals(MediaType.Image)) {
                return getImageSupportType(str);
            }
            if (mediaType.equals(MediaType.Video)) {
                return getVideoSupportType(str);
            }
            return SupportType.NOT_SUPPORT_COMMON;
        }
    }

    public static SupportType supportVideoCollageMaker(int i2) {
        if (i2 <= 4) {
            return SupportType.SUPPORT;
        }
        return SupportType.NOT_SUPPORT_VIDEO_COUNT_FOR_COLLAGE;
    }
}
