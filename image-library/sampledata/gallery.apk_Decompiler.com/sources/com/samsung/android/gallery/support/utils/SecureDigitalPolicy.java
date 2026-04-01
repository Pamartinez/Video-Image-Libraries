package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SecureDigitalPolicy {
    private static volatile SecureDigital sInstance = new SecureDigitalLegacy();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SecureDigital {
        String getReadablePath(String str);

        String getWritablePath(String str);
    }

    public static String getReadablePath(String str) {
        return sInstance.getReadablePath(str);
    }

    public static String getWritablePath(String str) {
        return sInstance.getWritablePath(str);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecureDigitalLegacy implements SecureDigital {
        public String getReadablePath(String str) {
            return str;
        }

        public String getWritablePath(String str) {
            return str;
        }
    }
}
