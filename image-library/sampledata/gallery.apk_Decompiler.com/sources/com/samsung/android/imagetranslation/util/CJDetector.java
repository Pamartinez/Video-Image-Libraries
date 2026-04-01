package com.samsung.android.imagetranslation.util;

import com.samsung.android.gallery.support.utils.MapUtil;
import java.lang.Character;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CJDetector {
    public static boolean isCJLanguage(String str) {
        String replaceAll = str.replaceAll("\\s", "");
        if (replaceAll.isEmpty()) {
            return false;
        }
        double d = MapUtil.INVALID_LOCATION;
        for (char c5 : replaceAll.toCharArray()) {
            if (Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.HIRAGANA || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.KATAKANA || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
                d += 1.0d;
            }
        }
        if (d / ((double) replaceAll.length()) > 0.5d) {
            return true;
        }
        return false;
    }
}
