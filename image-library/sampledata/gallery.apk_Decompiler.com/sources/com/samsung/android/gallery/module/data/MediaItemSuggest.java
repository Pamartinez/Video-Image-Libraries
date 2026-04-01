package com.samsung.android.gallery.module.data;

import android.util.Size;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.module.abstraction.MediaItemBase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemSuggest extends MediaItemBase {
    public static int getCleanOutDeleteType(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).cleanOutDeleteType;
    }

    public static int getCleanOutState(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).cleanOutState;
    }

    public static int getDeleteGroupId(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).cleanOutDeleteGroupId;
    }

    public static int getExtra(FileItemInterface fileItemInterface) {
        return Math.max(SuggestData.of(fileItemInterface).extra, 0);
    }

    public static String getExtraData(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).extraData;
    }

    public static String getOriginPath(FileItemInterface fileItemInterface) {
        return MediaItemBase.toString(MediaItemBase.getExtra(fileItemInterface, ExtrasID.ORIGIN_PATH), (String) null);
    }

    public static int getRemasterHeight(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).remasterHeight;
    }

    public static String getRemasterPath(FileItemInterface fileItemInterface) {
        return null;
    }

    public static Size getRemasterSize(FileItemInterface fileItemInterface) {
        SuggestData of2 = SuggestData.of(fileItemInterface);
        return new Size(of2.remasterWidth, of2.remasterHeight);
    }

    public static int getRemasterWidth(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).remasterWidth;
    }

    public static long getRevitalizedResultType(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).remasterResultType;
    }

    public static long getRevitalizedType(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).remasterType;
    }

    public static long getTotalSize(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).cleanOutTotalSize;
    }

    public static int getType(FileItemInterface fileItemInterface) {
        return SuggestData.of(fileItemInterface).suggestType;
    }

    public static boolean isCleanOut(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.CLEANOUT.toInt()) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutBurstSimilar(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.CLEANOUT_BURST_SIMILAR.toInt()) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutDuplicated(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.CLEANOUT_DUPLICATED_IMAGE.toInt()) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutGroup(FileItemInterface fileItemInterface) {
        if (isCleanOut(fileItemInterface) || isCleanOutMotionPhotoClip(fileItemInterface) || isCleanOutDuplicated(fileItemInterface) || isCleanOutBurstSimilar(fileItemInterface)) {
            return true;
        }
        return false;
    }

    public static boolean isCleanOutItem(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getCleanOutDeleteType(fileItemInterface) == -1) {
            return false;
        }
        return true;
    }

    public static boolean isCleanOutMotionPhotoClip(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.toInt()) {
            return false;
        }
        return true;
    }

    public static boolean isHighLight(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.HIGHLIGHT.toInt()) {
            return false;
        }
        return true;
    }

    public static boolean isIntelligentGroup(FileItemInterface fileItemInterface) {
        if (isRemaster(fileItemInterface) || isHighLight(fileItemInterface) || isPortrait(fileItemInterface)) {
            return true;
        }
        return false;
    }

    public static boolean isPortrait(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.PORTRAIT.toInt()) {
            return false;
        }
        return true;
    }

    public static boolean isRemaster(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || getType(fileItemInterface) != SuggestedType.REMASTER.toInt()) {
            return false;
        }
        return true;
    }

    public static void setCleanOutDeleteType(FileItemInterface fileItemInterface, int i2) {
        SuggestData.of(fileItemInterface).cleanOutDeleteType = i2;
    }

    public static void setExtra(FileItemInterface fileItemInterface, int i2) {
        SuggestData.of(fileItemInterface).extra = i2;
    }

    public static void setExtraData(FileItemInterface fileItemInterface, String str) {
        SuggestData.of(fileItemInterface).extraData = str;
    }

    public static void setOriginalPath(FileItemInterface fileItemInterface, String str) {
        MediaItemBase.setExtra(fileItemInterface, ExtrasID.ORIGIN_PATH, str);
    }

    public static void setRemasterSize(FileItemInterface fileItemInterface, int i2, int i7) {
        SuggestData of2 = SuggestData.of(fileItemInterface);
        of2.remasterWidth = i2;
        of2.remasterHeight = i7;
    }

    public static void setRevitalizedResultType(FileItemInterface fileItemInterface, long j2) {
        SuggestData.of(fileItemInterface).remasterResultType = j2;
    }

    public static void setRevitalizedType(FileItemInterface fileItemInterface, long j2) {
        SuggestData.of(fileItemInterface).remasterType = j2;
    }

    public static void setType(FileItemInterface fileItemInterface, int i2) {
        SuggestData.of(fileItemInterface).suggestType = i2;
    }
}
