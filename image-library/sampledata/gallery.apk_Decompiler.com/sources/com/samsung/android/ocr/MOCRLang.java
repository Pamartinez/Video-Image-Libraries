package com.samsung.android.ocr;

import c0.C0086a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRLang {
    public static final int AFRIKAANS = 32;
    public static final int ALBANIAN = 2;
    public static final int ARABIC = 80;
    public static final int ARMENIAN = 210;
    public static final int AUTO = 1001;
    public static final int BASQUE = 3;
    public static final int BENGALI = 140;
    public static final int BULGARIAN = 51;
    public static final int CATALAN = 4;
    public static final int CHINESE = 60;
    public static final int CROATIAN = 5;
    public static final int CYRILLIC = 50;
    public static final int CZECH = 6;
    public static final int DANISH = 7;
    public static final int DEVANAGARI = 90;
    public static final int DUTCH = 8;
    public static final int ENGLISH = 1;
    public static final int ESTONIAN = 9;
    public static final int FINNISH = 10;
    public static final int FRENCH = 11;
    public static final int GALICIAN = 12;
    public static final int GERMAN = 13;
    public static final int GREEK = 190;
    public static final int GUJARATI = 150;
    public static final int GURMUKHI = 160;
    public static final int HEBREW = 200;
    public static final int HINDI = 91;
    public static final int HUNGARIAN = 14;
    public static final int ICELANDIC = 15;
    public static final int INDONESIAN = 16;
    public static final int IRISH = 17;
    public static final int ITALIAN = 18;
    public static final int JAPANESE = 70;
    public static final int KANNADA = 120;
    public static final int KHMER = 180;
    public static final int KOREAN = 40;
    public static final int LAO = 220;
    public static final int LATIN = 0;
    public static final int LATVIAN = 19;
    public static final int LITHUANIAN = 20;
    public static final int MACEDONIAN = 52;
    public static final int MALAY = 21;
    public static final int MALAYALAM = 170;
    public static final int MARATHI = 92;
    public static final int NEPALI = 93;
    public static final int NORWEGIAN = 22;
    public static final int POLISH = 23;
    public static final int PORTUGUESE = 24;
    public static final int PUNJABI = 161;
    public static final int ROMANIAN = 25;
    public static final int RUSSIAN = 53;
    public static final int SERBIAN = 54;
    public static final int SLOVAK = 27;
    public static final int SLOVENIAN = 28;
    public static final int SPANISH = 26;
    public static final int SWEDISH = 29;
    public static final int TAMIL = 100;
    public static final int TELUGU = 110;
    public static final int THAI = 130;
    public static final int TURKISH = 30;
    public static final int UKRAINIAN = 55;
    public static final int UZBEK = 31;
    public static final int VIETNAMESE = 34;
    private static Map<String, Integer> localeMap = new HashMap<String, Integer>() {
        {
            C0086a.r(0, this, "la", 1, "en");
            C0086a.r(2, this, "sq", 3, "eu");
            C0086a.r(4, this, "ca", 5, "hr");
            C0086a.r(6, this, "cs", 7, "da");
            C0086a.r(8, this, "nl", 9, "et");
            C0086a.r(10, this, "fi", 11, "fr");
            C0086a.r(12, this, "gl", 13, "de");
            C0086a.r(14, this, "hu", 15, "is");
            C0086a.r(16, this, "id", 17, "ga");
            C0086a.r(18, this, "it", 19, "lv");
            C0086a.r(20, this, "lt", 21, "ms");
            put("no", 22);
            put("nb", 22);
            put("nn", 22);
            C0086a.r(23, this, "pl", 24, "pt");
            C0086a.r(25, this, "ro", 26, "es");
            C0086a.r(27, this, "sk", 28, "sl");
            C0086a.r(29, this, "sv", 30, "tr");
            C0086a.r(31, this, "uz", 32, "af");
            C0086a.r(34, this, "vi", 40, "ko");
            C0086a.r(51, this, "bg", 52, "mk");
            C0086a.r(53, this, "ru", 54, "sr");
            C0086a.r(55, this, "uk", 60, "zh");
            C0086a.r(70, this, "ja", 80, "ar");
            C0086a.r(130, this, "th", 91, "hi");
            C0086a.r(92, this, "mr", 93, "ne");
            C0086a.r(MOCRLang.PUNJABI, this, "pa", 100, "ta");
            C0086a.r(110, this, "te", 120, "kn");
            C0086a.r(140, this, "bn", 150, "gu");
            C0086a.r(MOCRLang.MALAYALAM, this, "ml", MOCRLang.KHMER, "km");
            put("el", Integer.valueOf(MOCRLang.GREEK));
            put("he", 200);
            put("iw", 200);
            put("hy", Integer.valueOf(MOCRLang.ARMENIAN));
            put("lo", Integer.valueOf(MOCRLang.LAO));
        }
    };

    public static int getLangFromLocale(Locale locale) {
        if (locale == null) {
            return 0;
        }
        String language = locale.getLanguage();
        String script = locale.getScript();
        for (Map.Entry next : localeMap.entrySet()) {
            if (isLocaleEquals(language, (String) next.getKey())) {
                return ((Integer) next.getValue()).intValue();
            }
        }
        if (script.equals("Kore") || script.equals("Hang")) {
            return 40;
        }
        if (script.equals("Jpan") || script.equals("Hani") || script.equals("Hira") || script.equals("Kana")) {
            return 70;
        }
        if (script.equals("Hans") || script.equals("Hant")) {
            return 60;
        }
        if (script.equals("Cyrl")) {
            return 50;
        }
        if (script.equals("Arab")) {
            return 80;
        }
        if (script.equals("Thai")) {
            return 130;
        }
        if (script.equals("Deva")) {
            return 90;
        }
        if (script.equals("Taml")) {
            return 100;
        }
        if (script.equals("Telu")) {
            return 110;
        }
        if (script.equals("Knda")) {
            return 120;
        }
        if (script.equals("Beng")) {
            return 140;
        }
        if (script.equals("Gujr")) {
            return 150;
        }
        if (script.equals("Guru")) {
            return GURMUKHI;
        }
        if (script.equals("Mlym")) {
            return MALAYALAM;
        }
        if (script.equals("Khmr")) {
            return KHMER;
        }
        if (script.equals("Grek")) {
            return GREEK;
        }
        if (script.equals("Hebr")) {
            return 200;
        }
        if (script.equals("Armn")) {
            return ARMENIAN;
        }
        if (script.equals("Laoo")) {
            return LAO;
        }
        return 0;
    }

    public static int getLangScript(int i2) {
        if (i2 >= 0 && i2 < 40) {
            return 0;
        }
        if (i2 >= 40 && i2 < 50) {
            return 40;
        }
        if (i2 >= 50 && i2 < 60) {
            return 50;
        }
        if (i2 >= 60 && i2 < 70) {
            return 60;
        }
        if (i2 >= 70 && i2 < 80) {
            return 70;
        }
        if (i2 >= 80 && i2 < 90) {
            return 80;
        }
        if (i2 >= 90 && i2 < 100) {
            return 90;
        }
        if (i2 >= 100 && i2 < 110) {
            return 100;
        }
        if (i2 >= 110 && i2 < 120) {
            return 110;
        }
        if (i2 >= 120 && i2 < 130) {
            return 120;
        }
        if (i2 >= 130 && i2 < 140) {
            return 130;
        }
        if (i2 >= 140 && i2 < 150) {
            return 140;
        }
        if (i2 >= 150 && i2 < 160) {
            return 150;
        }
        if (i2 >= 160 && i2 < 170) {
            return GURMUKHI;
        }
        if (i2 >= 170 && i2 < 180) {
            return MALAYALAM;
        }
        if (i2 >= 180 && i2 < 190) {
            return KHMER;
        }
        if (i2 >= 190 && i2 < 200) {
            return GREEK;
        }
        if (i2 >= 200 && i2 < 210) {
            return 200;
        }
        if (i2 >= 210 && i2 < 220) {
            return ARMENIAN;
        }
        if (i2 < 220 || i2 >= 230) {
            return -1;
        }
        return LAO;
    }

    public static boolean isArabic(int i2) {
        if (getLangScript(i2) == 80) {
            return true;
        }
        return false;
    }

    public static boolean isChinese(int i2) {
        if (getLangScript(i2) == 60) {
            return true;
        }
        return false;
    }

    public static boolean isCyrillic(int i2) {
        if (getLangScript(i2) == 50) {
            return true;
        }
        return false;
    }

    public static boolean isDevanagari(int i2) {
        if (getLangScript(i2) == 91) {
            return true;
        }
        return false;
    }

    public static boolean isJapanese(int i2) {
        if (getLangScript(i2) == 70) {
            return true;
        }
        return false;
    }

    public static boolean isKorean(int i2) {
        if (getLangScript(i2) == 40) {
            return true;
        }
        return false;
    }

    public static boolean isLatin(int i2) {
        if (getLangScript(i2) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isLocaleEquals(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.startsWith(str2 + "-")) {
            return true;
        }
        if (str.startsWith(str2 + "_")) {
            return true;
        }
        return false;
    }
}
