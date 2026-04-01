package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GridValue {
    static final int[] CLUSTERS = {0, 0, 1, 2};

    public static int cluster(int i2) {
        if ((i2 & 16) > 0) {
            return 3;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !isMonthXs(i2)) {
            return CLUSTERS[i2 & 15];
        }
        return 4;
    }

    public static int convert(int i2, int i7, boolean z, boolean z3) {
        int i8;
        if (z3) {
            if (z) {
                i8 = 8388608;
            } else {
                i8 = OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE;
            }
        } else if (z) {
            i8 = 2097152;
        } else {
            i8 = MediaDefs.Meta.SEF.SEF_MIN_SIZE;
        }
        return (i2 << 24) | (i8 + i7);
    }

    public static int depthOf(int i2) {
        return (i2 & 251658240) >> 24;
    }

    public static boolean isDay(int i2) {
        if ((i2 & 15) == 1) {
            return true;
        }
        return false;
    }

    public static boolean isFake(int i2) {
        if (i2 > 1048575) {
            return true;
        }
        return false;
    }

    public static boolean isMonth(int i2) {
        if ((i2 & 15) == 2) {
            return true;
        }
        return false;
    }

    public static boolean isMonthXs(int i2) {
        if ((i2 & 32) <= 0 || (i2 & 15) != 2) {
            return false;
        }
        return true;
    }

    public static boolean isRealRatio(int i2) {
        if ((i2 & 16) > 0) {
            return true;
        }
        return false;
    }

    public static boolean isSplit(int i2, boolean z, boolean z3) {
        if (z3) {
            if ((12582912 & i2) > 0) {
                if ((i2 & 8388608) > 0) {
                    return true;
                }
                return false;
            }
        } else if ((15728640 & i2) > 0) {
            if ((i2 & 2097152) > 0) {
                return true;
            }
            return false;
        }
        return z;
    }

    public static boolean isYear(int i2) {
        if ((i2 & 15) == 3) {
            return true;
        }
        return false;
    }

    public static int revert(int i2) {
        return i2 & 4095;
    }
}
