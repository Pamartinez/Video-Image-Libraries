package com.samsung.android.sum.core;

import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ColorUtils {
    public static int getDataSpaceColorRange(int i2) {
        if (i2 == 1) {
            return 134217728;
        }
        if (i2 == 2) {
            return 268435456;
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "unknown range: "));
    }

    public static int getDataSpaceColorStandard(int i2) {
        if (i2 == 1) {
            return 65536;
        }
        if (i2 == 2) {
            return 131072;
        }
        if (i2 == 4) {
            return 262144;
        }
        if (i2 == 6) {
            return 393216;
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "unknown standard: "));
    }

    public static int getDataSpaceColorTransfer(int i2) {
        if (i2 == 1) {
            return OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE;
        }
        if (i2 == 3) {
            return 8388608;
        }
        if (i2 == 6) {
            return 29360128;
        }
        if (i2 == 7) {
            return 33554432;
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "unknown transfer: "));
    }

    public static int getMediaFormatColorRange(int i2) {
        if (i2 == 134217728) {
            return 1;
        }
        if (i2 == 268435456) {
            return 2;
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "unknown range: "));
    }

    public static int getMediaFormatColorStandard(int i2) {
        if (i2 == 65536) {
            return 1;
        }
        if (i2 == 131072) {
            return 2;
        }
        if (i2 == 262144) {
            return 4;
        }
        if (i2 == 393216) {
            return 6;
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "unknown standard: "));
    }

    public static int getMediaFormatColorTransfer(int i2) {
        if (i2 == 4194304) {
            return 1;
        }
        if (i2 == 8388608 || i2 == 12582912) {
            return 3;
        }
        if (i2 == 29360128) {
            return 6;
        }
        if (i2 == 33554432) {
            return 7;
        }
        throw new UnsupportedOperationException(C0086a.i(i2, "unknown transfer: "));
    }
}
