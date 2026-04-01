package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SecureUtils {
    public static String generateRandomString(int i2) {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb2 = new StringBuilder();
        while (sb2.length() < i2) {
            sb2.append(charArray[RandomNumber.nextInt(charArray.length)]);
        }
        return sb2.toString();
    }
}
