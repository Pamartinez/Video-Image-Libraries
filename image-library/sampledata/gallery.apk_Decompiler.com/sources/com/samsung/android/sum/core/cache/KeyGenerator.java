package com.samsung.android.sum.core.cache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyGenerator {
    public static String getSimpleKey(String str) {
        return str.replaceAll("[:/]", "_");
    }
}
