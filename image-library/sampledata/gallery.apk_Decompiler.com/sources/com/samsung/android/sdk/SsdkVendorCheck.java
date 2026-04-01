package com.samsung.android.sdk;

import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SsdkVendorCheck {
    private static String strBrand = Build.BRAND;
    private static String strManufacturer = Build.MANUFACTURER;

    private SsdkVendorCheck() {
    }

    public static boolean isSamsungDevice() {
        String str = strBrand;
        if (str == null || strManufacturer == null) {
            return false;
        }
        if (str.compareToIgnoreCase("Samsung") == 0 || strManufacturer.compareToIgnoreCase("Samsung") == 0) {
            return true;
        }
        return false;
    }
}
