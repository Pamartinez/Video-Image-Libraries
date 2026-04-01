package com.samsung.android.gallery.app.ui.list.stories.highlight.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Size;
import com.samsung.android.gallery.support.helper.DeviceInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CustomRatioHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Ratio {
        PORTRAIT_FULL(-1, -1, 1),
        RATIO_1_TO_1(1080, 1080, 1),
        RATIO_3_TO_4(1080, 1440, 1),
        RATIO_9_TO_16(1080, 1920, 1),
        LANDSCAPE_FULL(-1, -1, 2),
        RATIO_1_TO_1_(1080, 1080, 2),
        RATIO_4_TO_3(1440, 1080, 2),
        RATIO_16_TO_9(1920, 1080, 2);
        
        /* access modifiers changed from: private */
        public final int orientation;
        /* access modifiers changed from: private */
        public final int[] outSize;

        private Ratio(int i2, int i7, int i8) {
            this.outSize = new int[]{i2, i7};
            this.orientation = i8;
        }
    }

    public static int getDefaultRatio(Resources resources) {
        Ratio ratio;
        if (resources.getConfiguration().orientation == 2) {
            ratio = Ratio.LANDSCAPE_FULL;
        } else {
            ratio = Ratio.PORTRAIT_FULL;
        }
        return ratio.ordinal();
    }

    public static int getOrientationIndex(int i2) {
        return i2 / 4;
    }

    public static int[] getOutSize(Context context, int i2, int i7) {
        boolean z;
        int i8;
        int i10;
        Ratio ratio = Ratio.values()[i2];
        int[] b = ratio.outSize;
        if (b[0] < 0) {
            if (ratio.orientation == i7) {
                z = true;
            } else {
                z = false;
            }
            Size displaySize = DeviceInfo.getDisplaySize(context);
            if (z) {
                i8 = displaySize.getWidth();
            } else {
                i8 = displaySize.getHeight();
            }
            b[0] = i8;
            if (z) {
                i10 = displaySize.getHeight();
            } else {
                i10 = displaySize.getWidth();
            }
            b[1] = i10;
            b[0] = b[0] & -2;
            b[1] = i10 & -2;
        }
        return b;
    }

    public static int getRatio(int i2, int i7) {
        return (i2 * 4) + i7;
    }

    public static int getRatioIndex(int i2) {
        return i2 % 4;
    }
}
