package com.samsung.android.ocr.stride.postocr;

import com.samsung.android.ocr.MOCRResult;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PostProcessCorrection {
    private static final Pattern PAT_CURR_KO = Pattern.compile(REGEX_CURR_KO, 2);
    private static final String REGEX_CURR_KO = "^₩[A-Za-z]+";
    private static final String TAG = "PostProcessCorrection";
    private static String[] findAndReplaceWords = {"l'd", "lt"};
    private static List<Character> slashSurroundingChars = Arrays.asList(new Character[]{'.', ',', '\"', '\''});
    private static String[] toReplaceWords = {"I'd", "It"};

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ed, code lost:
        if (slashSurroundingChars.contains(java.lang.Character.valueOf(r10)) == false) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0184, code lost:
        if (slashSurroundingChars.contains(java.lang.Character.valueOf(r9.wordText.charAt(r0))) != false) goto L_0x0188;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void correctCharI(com.samsung.android.ocr.MOCRResult.Word r9, com.samsung.android.ocr.MOCRResult.Line r10, int r11) {
        /*
            int r0 = r9.charCount
            java.lang.String r1 = "|"
            java.lang.String r2 = "\\"
            java.lang.String r3 = "I"
            r4 = 73
            r5 = 0
            r6 = 1
            if (r0 != r6) goto L_0x0108
            java.lang.String r0 = r9.wordText
            r0.getClass()
            int r7 = r0.hashCode()
            r8 = -1
            switch(r7) {
                case 92: goto L_0x0031;
                case 108: goto L_0x0026;
                case 124: goto L_0x001d;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0039
        L_0x001d:
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0024
            goto L_0x0039
        L_0x0024:
            r8 = 2
            goto L_0x0039
        L_0x0026:
            java.lang.String r1 = "l"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x002f
            goto L_0x0039
        L_0x002f:
            r8 = r6
            goto L_0x0039
        L_0x0031:
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0038
            goto L_0x0039
        L_0x0038:
            r8 = r5
        L_0x0039:
            switch(r8) {
                case 0: goto L_0x00a7;
                case 1: goto L_0x0092;
                case 2: goto L_0x003e;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x019d
        L_0x003e:
            int r0 = r11 + 1
            com.samsung.android.ocr.MOCRResult$Word[] r1 = r10.words
            int r2 = r1.length
            if (r0 >= r2) goto L_0x005b
            r0 = r1[r0]
            java.lang.String r0 = r0.wordText
            char r0 = r0.charAt(r5)
            boolean r1 = java.lang.Character.isUpperCase(r0)
            if (r1 != 0) goto L_0x0059
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 == 0) goto L_0x005b
        L_0x0059:
            r0 = r5
            goto L_0x005c
        L_0x005b:
            r0 = r6
        L_0x005c:
            int r11 = r11 - r6
            if (r11 < 0) goto L_0x007b
            com.samsung.android.ocr.MOCRResult$Word[] r10 = r10.words
            r10 = r10[r11]
            java.lang.String r10 = r10.wordText
            int r11 = r10.length()
            int r11 = r11 - r6
            char r10 = r10.charAt(r11)
            boolean r11 = java.lang.Character.isUpperCase(r10)
            if (r11 != 0) goto L_0x007a
            boolean r10 = java.lang.Character.isDigit(r10)
            if (r10 == 0) goto L_0x007b
        L_0x007a:
            r0 = r5
        L_0x007b:
            if (r0 == 0) goto L_0x019d
            java.lang.String r10 = TAG
            java.lang.String r11 = "Pipe replaced with I - 1 char"
            com.samsung.android.ocr.MOCRLog.d(r10, r11)
            r9.wordText = r3
            com.samsung.android.ocr.MOCRResult$Char[] r9 = r9.chars
            if (r9 == 0) goto L_0x019d
            int r10 = r9.length
            if (r10 <= 0) goto L_0x019d
            r9 = r9[r5]
            r9.unicode = r4
            return
        L_0x0092:
            java.lang.String r10 = TAG
            java.lang.String r11 = "l replaced with I - 1 char"
            com.samsung.android.ocr.MOCRLog.d(r10, r11)
            r9.wordText = r3
            com.samsung.android.ocr.MOCRResult$Char[] r9 = r9.chars
            if (r9 == 0) goto L_0x019d
            int r10 = r9.length
            if (r10 <= 0) goto L_0x019d
            r9 = r9[r5]
            r9.unicode = r4
            return
        L_0x00a7:
            int r0 = r11 + 1
            com.samsung.android.ocr.MOCRResult$Word[] r1 = r10.words
            int r2 = r1.length
            if (r0 >= r2) goto L_0x00ca
            r0 = r1[r0]
            java.lang.String r0 = r0.wordText
            char r0 = r0.charAt(r5)
            boolean r1 = java.lang.Character.isLowerCase(r0)
            if (r1 != 0) goto L_0x00c8
            java.util.List<java.lang.Character> r1 = slashSurroundingChars
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L_0x00ca
        L_0x00c8:
            r0 = r6
            goto L_0x00cb
        L_0x00ca:
            r0 = r5
        L_0x00cb:
            int r11 = r11 - r6
            if (r11 < 0) goto L_0x00f0
            com.samsung.android.ocr.MOCRResult$Word[] r10 = r10.words
            r10 = r10[r11]
            java.lang.String r10 = r10.wordText
            int r11 = r10.length()
            int r11 = r11 - r6
            char r10 = r10.charAt(r11)
            boolean r11 = java.lang.Character.isLowerCase(r10)
            if (r11 != 0) goto L_0x00f1
            java.util.List<java.lang.Character> r11 = slashSurroundingChars
            java.lang.Character r10 = java.lang.Character.valueOf(r10)
            boolean r10 = r11.contains(r10)
            if (r10 == 0) goto L_0x00f0
            goto L_0x00f1
        L_0x00f0:
            r6 = r0
        L_0x00f1:
            if (r6 == 0) goto L_0x019d
            java.lang.String r10 = TAG
            java.lang.String r11 = "\\ replaced with I - 1 char"
            com.samsung.android.ocr.MOCRLog.d(r10, r11)
            r9.wordText = r3
            com.samsung.android.ocr.MOCRResult$Char[] r9 = r9.chars
            if (r9 == 0) goto L_0x019d
            int r10 = r9.length
            if (r10 <= 0) goto L_0x019d
            r9 = r9[r5]
            r9.unicode = r4
            return
        L_0x0108:
            java.lang.String r10 = r9.wordText
            boolean r10 = r10.contains(r1)
            if (r10 == 0) goto L_0x0143
            java.lang.String r10 = TAG
            java.lang.String r11 = "Pipe replaced with I - condition 2"
            com.samsung.android.ocr.MOCRLog.d(r10, r11)
            java.lang.String r10 = r9.wordText
            java.lang.String r11 = "\\|"
            java.lang.String r10 = r10.replaceAll(r11, r3)
            r9.wordText = r10
            com.samsung.android.ocr.MOCRResult$Char[] r10 = r9.chars
            if (r10 == 0) goto L_0x019d
            int r10 = r10.length
            if (r10 <= 0) goto L_0x019d
        L_0x0128:
            java.lang.String r10 = r9.wordText
            int r10 = r10.length()
            if (r5 >= r10) goto L_0x019d
            java.lang.String r10 = r9.wordText
            char r10 = r10.charAt(r5)
            r11 = 124(0x7c, float:1.74E-43)
            if (r10 != r11) goto L_0x0140
            com.samsung.android.ocr.MOCRResult$Char[] r10 = r9.chars
            r10 = r10[r5]
            r10.unicode = r4
        L_0x0140:
            int r5 = r5 + 1
            goto L_0x0128
        L_0x0143:
            java.lang.String r10 = r9.wordText
            boolean r10 = r10.contains(r2)
            if (r10 == 0) goto L_0x019d
            java.lang.String r10 = r9.wordText
            r11 = 92
            int r10 = r10.indexOf(r11)
            int r0 = r10 + -1
            if (r0 < 0) goto L_0x016a
            java.lang.String r1 = r9.wordText
            char r0 = r1.charAt(r0)
            java.util.List<java.lang.Character> r1 = slashSurroundingChars
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L_0x016a
            r5 = r6
        L_0x016a:
            int r0 = r10 + 1
            java.lang.String r1 = r9.wordText
            int r1 = r1.length()
            if (r0 >= r1) goto L_0x0187
            java.lang.String r1 = r9.wordText
            char r0 = r1.charAt(r0)
            java.util.List<java.lang.Character> r1 = slashSurroundingChars
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L_0x0187
            goto L_0x0188
        L_0x0187:
            r6 = r5
        L_0x0188:
            if (r6 == 0) goto L_0x019d
            java.lang.String r0 = r9.wordText
            java.lang.String r11 = r0.replace(r11, r4)
            r9.wordText = r11
            com.samsung.android.ocr.MOCRResult$Char[] r9 = r9.chars
            if (r9 == 0) goto L_0x019d
            int r11 = r9.length
            if (r11 <= 0) goto L_0x019d
            r9 = r9[r10]
            r9.unicode = r4
        L_0x019d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.ocr.stride.postocr.PostProcessCorrection.correctCharI(com.samsung.android.ocr.MOCRResult$Word, com.samsung.android.ocr.MOCRResult$Line, int):void");
    }

    public static void process(MOCRResult.Page page) {
        for (MOCRResult.Block block : page.blocks) {
            for (MOCRResult.Line line : block.lines) {
                int i2 = 0;
                while (true) {
                    MOCRResult.Word[] wordArr = line.words;
                    if (i2 >= wordArr.length) {
                        break;
                    }
                    MOCRResult.Word word = wordArr[i2];
                    correctCharI(word, line, i2);
                    replaceWord(word);
                    replaceInvalidCurrency(word);
                    i2++;
                }
            }
        }
    }

    private static void replaceInvalidCurrency(MOCRResult.Word word) {
        String str = word.wordText;
        if (PAT_CURR_KO.matcher(str).find()) {
            word.wordText = "W" + str.substring(1);
            MOCRResult.Char[] charArr = word.chars;
            if (charArr != null && charArr.length > 0) {
                charArr[0].unicode = 87;
            }
        }
    }

    private static void replaceWord(MOCRResult.Word word) {
        int i2 = 0;
        while (true) {
            String[] strArr = findAndReplaceWords;
            if (i2 < strArr.length) {
                if (word.wordText.equals(strArr[i2])) {
                    word.wordText = toReplaceWords[i2];
                    MOCRResult.Char[] charArr = word.chars;
                    if (charArr != null && charArr.length > 0) {
                        for (int i7 = 0; i7 < word.wordText.length(); i7++) {
                            word.chars[i7].unicode = word.wordText.charAt(i7);
                        }
                    }
                }
                i2++;
            } else {
                return;
            }
        }
    }
}
