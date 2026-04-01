package com.samsung.android.ocr;

import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRScript {
    public static final int ARABIC = 4;
    public static final int ARMENIAN = 16;
    public static final int BENGALI = 11;
    public static final int CHINESE = 2;
    public static final int CYRILLIC = 9;
    public static final int DEVANAGARI = 5;
    public static final int GREEK = 19;
    public static final int GUJARATI = 12;
    public static final int GURUMUKHI = 13;
    public static final int HEBREW = 17;
    public static final int JAPANESE = 3;
    public static final int KANNADA = 8;
    public static final int KHMER = 15;
    public static final int KOREAN = 1;
    public static final int LAO = 18;
    public static final int LATIN = 0;
    public static final int MALAYALAM = 14;
    public static final int SUPPORTED_COUNT = 20;
    public static final int TAMIL = 6;
    public static final int TELUGU = 7;
    public static final int THAI = 10;

    public static List<Integer> getColors() {
        return Arrays.asList(new Integer[]{-8388608, -1697461, -344364, -6659292, -687567, -10063, -8355840, -7911, -1336, -4198587, -12798901, -5570621, -12150384, -12397324, -12360744, -16777099, -7266636, -2310401, -1035546, -5658199, -16777216});
    }

    public static int getScriptID(int i2) {
        if (i2 >= 0 && i2 < 40) {
            return 0;
        }
        if (i2 >= 40 && i2 < 50) {
            return 1;
        }
        if (i2 >= 50 && i2 < 60) {
            return 9;
        }
        if (i2 >= 60 && i2 < 70) {
            return 2;
        }
        if (i2 >= 70 && i2 < 80) {
            return 3;
        }
        if (i2 >= 80 && i2 < 90) {
            return 4;
        }
        if (i2 >= 90 && i2 < 100) {
            return 5;
        }
        if (i2 < 130 || i2 >= 140) {
            return -1;
        }
        return 10;
    }

    public static String getString(int i2) {
        if (i2 == 0) {
            return "Latin";
        }
        if (i2 == 1) {
            return "Korean";
        }
        if (i2 == 2) {
            return "Chinese";
        }
        if (i2 == 3) {
            return "Japanese";
        }
        if (i2 == 4) {
            return "Arabic";
        }
        if (i2 == 5) {
            return "Devanagari";
        }
        if (i2 == 6) {
            return "Tamil";
        }
        if (i2 == 7) {
            return "Telugu";
        }
        if (i2 == 8) {
            return "Kannada";
        }
        if (i2 == 9) {
            return "Cyrillic";
        }
        if (i2 == 10) {
            return "Thai";
        }
        if (i2 == 11) {
            return "Bengali";
        }
        if (i2 == 12) {
            return "Gujarati";
        }
        if (i2 == 13) {
            return "Gurumukhi";
        }
        if (i2 == 15) {
            return "Khmer";
        }
        if (i2 == 14) {
            return "Malayalam";
        }
        if (i2 == 19) {
            return "Greek";
        }
        if (i2 == 17) {
            return "Hebrew";
        }
        if (i2 == 16) {
            return "Armenian";
        }
        if (i2 == 18) {
            return "Lao";
        }
        return "NoScript";
    }
}
