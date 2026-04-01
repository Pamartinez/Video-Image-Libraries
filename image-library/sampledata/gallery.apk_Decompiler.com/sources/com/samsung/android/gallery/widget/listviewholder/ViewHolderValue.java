package com.samsung.android.gallery.widget.listviewholder;

import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewHolderValue {
    public static boolean isData(int i2) {
        if (i2 >= 0) {
            return true;
        }
        return false;
    }

    public static boolean isDivider(int i2) {
        if (i2 == -1 || i2 == -2) {
            return true;
        }
        return false;
    }

    public static boolean isFirstDivider(int i2) {
        if (i2 == -1) {
            return true;
        }
        return false;
    }

    public static boolean isFooter(int i2) {
        if (i2 == -4) {
            return true;
        }
        return false;
    }

    public static boolean isHeader(int i2) {
        if (i2 == -3) {
            return true;
        }
        return false;
    }

    public static boolean isMonthForViewing(int i2) {
        if (i2 == 3) {
            return true;
        }
        return false;
    }

    public static boolean isYear(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    public static boolean useConcatThumbnail(int i2) {
        if (isYear(i2)) {
            return true;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !isMonthForViewing(i2)) {
            return false;
        }
        return true;
    }
}
