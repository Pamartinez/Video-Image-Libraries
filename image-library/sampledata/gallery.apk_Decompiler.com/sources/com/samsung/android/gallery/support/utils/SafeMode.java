package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SafeMode {
    public static boolean ENABLED = getAndDecrement();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ValueHolder {
        public static int get() {
            return GalleryPreference.getInstanceDebug().loadInt("SafeModeCount", 0);
        }

        public static void set(int i2) {
            if (i2 > 0) {
                GalleryPreference.getInstanceDebug().saveState("SafeModeCount", i2);
            } else {
                GalleryPreference.getInstanceDebug().removeState("SafeModeCount");
            }
        }
    }

    public static boolean getAndDecrement() {
        try {
            int i2 = ValueHolder.get();
            if (i2 <= 0) {
                return false;
            }
            ValueHolder.set(i2 - 1);
            return true;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static boolean isEnabled() {
        return ENABLED;
    }

    public static boolean setEnabled(boolean z) {
        int i2;
        ENABLED = z;
        if (z) {
            i2 = 3;
        } else {
            i2 = 0;
        }
        ValueHolder.set(i2);
        return true;
    }
}
