package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EmojiList {
    private static boolean containsEmoji(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < length - 1 && isEmojiChar(str.charAt(i2), str.charAt(i2 + 1))) {
                return true;
            }
            char charAt = str.charAt(i2);
            if (charAt >= 9728 && charAt <= 9983 && charAt != 9825 && charAt != 9826 && charAt != 9828 && charAt != 9831 && charAt != 9734 && charAt != 9770) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasEmojiString(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 0 || !containsEmoji(charSequence.toString())) {
            return false;
        }
        return true;
    }

    private static boolean isEmojiChar(char c5, char c6) {
        return Character.isSurrogatePair(c5, c6);
    }
}
