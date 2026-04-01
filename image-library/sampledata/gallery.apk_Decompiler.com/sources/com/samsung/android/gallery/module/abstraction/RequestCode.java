package com.samsung.android.gallery.module.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RequestCode {
    public static boolean isPickerRequestCode(int i2) {
        if (i2 == 2316 || i2 == 2317) {
            return true;
        }
        return false;
    }
}
