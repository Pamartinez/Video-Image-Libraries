package com.samsung.android.gallery.module.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ConvertingUsageType {
    NONE(0),
    COMMON_SHARE(1);
    
    public final int value;

    private ConvertingUsageType(int i2) {
        this.value = i2;
    }

    public static ConvertingUsageType of(int i2) {
        if (i2 == 1) {
            return COMMON_SHARE;
        }
        return NONE;
    }
}
