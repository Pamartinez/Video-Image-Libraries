package com.samsung.android.gallery.module.thumbnail.type;

import android.content.Context;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ThumbKind {
    SMALL_CROP_KIND(MOCRLang.GURMUKHI, MOCRLang.GURMUKHI, 5),
    MEDIUM_KIND(MEDIUM_KIND_SIZE, 224, 0),
    LARGE_KIND(580, 406, 2),
    LARGE_NC_KIND(580, 406, 99),
    XLARGE_NC_KIND(640, 448, 99),
    FULL_NC_KIND(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, 1024, 99),
    SMALL_NC_KIND(MOCRLang.GURMUKHI, MOCRLang.GURMUKHI, 99),
    FREE_KIND(MEDIUM_KIND_SIZE, 224, 99),
    MINI_KIND(32, 32, 99),
    XSMALL_KIND(80, 80, 99);
    
    static final float COMPARE_THUMB_SIZE_FACTOR = 0.78f;
    public static final int MEDIUM_KIND_SIZE = 320;
    static final float MINIMUM_INCH = 0.375f;
    public static final int MINI_KIND_SIZE = 32;
    public static final int SMALL_KIND_SIZE = 128;
    public static final ThumbKind STORY_COVER = null;
    public static final ThumbKind SUBFOLDER_KIND = null;
    final int mCacheId;
    final int mLowerBound;
    final int mSize;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SUPPORTED_KIND {
        public static final ThumbKind[] THUMB_KINDS = null;
        public static final ThumbKind[] THUMB_KINDS_XSMALL = null;
        static final ThumbKind[] kinds = null;

        static {
            ThumbKind thumbKind = ThumbKind.XSMALL_KIND;
            ThumbKind thumbKind2 = ThumbKind.SMALL_CROP_KIND;
            ThumbKind thumbKind3 = ThumbKind.MEDIUM_KIND;
            THUMB_KINDS_XSMALL = new ThumbKind[]{thumbKind, thumbKind2, thumbKind3};
            ThumbKind[] thumbKindArr = {thumbKind2, thumbKind3};
            THUMB_KINDS = thumbKindArr;
            kinds = thumbKindArr;
        }
    }

    static {
        ThumbKind thumbKind;
        ThumbKind thumbKind2;
        STORY_COVER = thumbKind2;
        SUBFOLDER_KIND = thumbKind;
    }

    private ThumbKind(int i2, int i7, int i8) {
        this.mSize = i2;
        this.mCacheId = i8;
        this.mLowerBound = i7;
    }

    public static ThumbKind calculateChildInFolder(int i2, int i7, boolean z) {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || (i2 >= 3 && ((i2 != 3 || i7 != 1) && !z))) {
            return SUBFOLDER_KIND;
        }
        return MEDIUM_KIND;
    }

    private static boolean compareThumbSize(Context context, int i2, ThumbKind thumbKind) {
        float f = (float) i2;
        if (((double) thumbKind.size()) >= Math.floor((double) (COMPARE_THUMB_SIZE_FACTOR * f))) {
            return true;
        }
        if ((isXSmallKind(thumbKind) || isSmallKind(thumbKind)) && convertPixelToInch(context, f) < MINIMUM_INCH) {
            return true;
        }
        return false;
    }

    private static float convertPixelToInch(Context context, float f) {
        return f / ((float) context.getResources().getDisplayMetrics().densityDpi);
    }

    public static ThumbKind getOptimalThumbKind(Context context, int i2) {
        if (context != null) {
            for (ThumbKind thumbKind : SUPPORTED_KIND.kinds) {
                if (compareThumbSize(context, i2, thumbKind)) {
                    return thumbKind;
                }
            }
        }
        return MEDIUM_KIND;
    }

    public static boolean isCropKind(ThumbKind thumbKind) {
        if (thumbKind == SMALL_CROP_KIND || thumbKind == MINI_KIND) {
            return true;
        }
        return false;
    }

    public static boolean isSmallKind(ThumbKind thumbKind) {
        if (thumbKind == SMALL_CROP_KIND) {
            return true;
        }
        return false;
    }

    public static boolean isXSmallKind(ThumbKind thumbKind) {
        if (thumbKind == XSMALL_KIND) {
            return true;
        }
        return false;
    }

    public int cacheId() {
        return this.mCacheId;
    }

    public int lowerBound() {
        return this.mLowerBound;
    }

    public int size() {
        return this.mSize;
    }

    public int volume() {
        return this.mSize * this.mLowerBound;
    }
}
