package com.samsung.android.ocr.stride.lang;

import com.samsung.android.ocr.MOCRLang;
import java.lang.Character;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnicodeLangDetector extends LanguageDetector {
    private static final String TAG = "UnicodeLangDetector";

    public static boolean isArabic(CharSequence charSequence) {
        for (char c5 : charSequence.toString().toCharArray()) {
            if (Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ARABIC || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ARABIC_EXTENDED_A || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ARABIC_SUPPLEMENT || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChinese(CharSequence charSequence) {
        Character.UnicodeBlock unicodeBlock;
        for (char c5 : charSequence.toString().toCharArray()) {
            if (Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_COMPATIBILITY || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_STROKES || Character.UnicodeBlock.of(c5) == (unicodeBlock = Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.ENCLOSED_IDEOGRAPHIC_SUPPLEMENT || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.KANGXI_RADICALS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS || Character.UnicodeBlock.of(c5) == unicodeBlock) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCyrillic(CharSequence charSequence) {
        for (char c5 : charSequence.toString().toCharArray()) {
            if (Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CYRILLIC || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CYRILLIC_EXTENDED_A || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CYRILLIC_EXTENDED_B || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY) {
                return true;
            }
        }
        return false;
    }

    public static boolean isJapanese(CharSequence charSequence) {
        Character.UnicodeBlock unicodeBlock;
        for (char c5 : charSequence.toString().toCharArray()) {
            if (Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.HIRAGANA || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.KATAKANA || Character.UnicodeBlock.of(c5) == (unicodeBlock = Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) || Character.UnicodeBlock.of(c5) == unicodeBlock || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
                return true;
            }
        }
        return false;
    }

    public static boolean isKorean(CharSequence charSequence) {
        for (char c5 : charSequence.toString().toCharArray()) {
            if (Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.HANGUL_JAMO || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || Character.UnicodeBlock.of(c5) == Character.UnicodeBlock.HANGUL_SYLLABLES) {
                return true;
            }
        }
        return false;
    }

    public int detectLanguage(String str, int i2) {
        if (MOCRLang.isKorean(i2) && isKorean(str)) {
            return 40;
        }
        if (MOCRLang.isChinese(i2) && isChinese(str)) {
            return 60;
        }
        if (MOCRLang.isJapanese(i2) && isJapanese(str)) {
            return 70;
        }
        if (MOCRLang.isCyrillic(i2) && isCyrillic(str)) {
            return 50;
        }
        if (MOCRLang.isArabic(i2) && isArabic(str)) {
            return 80;
        }
        if (MOCRLang.isLatin(i2)) {
            return i2;
        }
        return 1;
    }
}
