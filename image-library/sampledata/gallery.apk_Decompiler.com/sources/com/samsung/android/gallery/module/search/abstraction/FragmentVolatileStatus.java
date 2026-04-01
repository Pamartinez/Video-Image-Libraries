package com.samsung.android.gallery.module.search.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FragmentVolatileStatus {
    private static boolean sIsVolatile;

    public static boolean isVolatile() {
        return sIsVolatile;
    }

    public static void resetVolatile() {
        sIsVolatile = false;
    }

    public static void setVolatile() {
        sIsVolatile = true;
    }
}
