package com.samsung.android.ocr.stride.postocr.entity;

import android.graphics.Point;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CorrectionBase {
    private static final String TAG = "CorrectionBase";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RegexActions {
        REPLACE_COMMA_IN_PHONE_NUMBER,
        REPLACE_COMMA_IN_EMAIL_OR_URL,
        REPLACE_DOUBLE_IN_EMAIL,
        REPLACE_DOUBLE_IN_URL,
        REPLACE_COMMA_MULTIPLE_IN_URL,
        REPLACE_DOUBLE_WITH_COMMA_IN_URL,
        REPLACE_SQUARE_BRACKET_IN_PHONE_NUMBER,
        ADD_SPACE_IN_TELPHONE_FAX,
        ADD_SPACE_IN_PHONE,
        REPLACE_O_IN_DATETIME,
        REPLACE_SUPERSCRIPT_2_IN_UNITS,
        REPLACE_SUPERSCRIPT_3_IN_UNITS
    }

    public static int[] getMatchingIds(Matcher matcher, MOCRResult.Line line) {
        return getMatchingIds(matcher, line, 0);
    }

    public static void joinWords(MOCRResult.Line line, int i2, int i7) {
        joinWords(line, i2, i7, (RegexActions) null);
    }

    public static void replaceCharInWord(MOCRResult.Line line, int i2, RegexActions regexActions) {
        replaceCharInWord(line, i2, regexActions, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0048 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0064 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] getMatchingIds(java.util.regex.Matcher r7, com.samsung.android.ocr.MOCRResult.Line r8, int r9) {
        /*
            r0 = -1
            if (r9 != 0) goto L_0x000e
            int r9 = r7.start()
            int r7 = r7.end()
        L_0x000b:
            r2 = r7
            r1 = r9
            goto L_0x002d
        L_0x000e:
            java.lang.String r7 = r7.group(r9)
            if (r7 == 0) goto L_0x002b
            java.lang.String r9 = r8.getText()
            int r9 = r9.indexOf(r7)
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L_0x0025
            r1 = r9
        L_0x0023:
            r2 = r1
            goto L_0x002d
        L_0x0025:
            int r7 = r7.length()
            int r7 = r7 + r9
            goto L_0x000b
        L_0x002b:
            r1 = r0
            goto L_0x0023
        L_0x002d:
            r7 = 0
            r9 = r7
        L_0x002f:
            int r3 = r8.wordCount
            if (r7 >= r3) goto L_0x0048
            com.samsung.android.ocr.MOCRResult$Word[] r3 = r8.words
            r3 = r3[r7]
            int r3 = r3.charCount
            int r4 = r3 + r9
            if (r4 >= r1) goto L_0x0043
            int r9 = r9 + r3
            int r9 = r9 + 1
            int r7 = r7 + 1
            goto L_0x002f
        L_0x0043:
            int r3 = r1 - r9
            r5 = r3
            r3 = r7
            goto L_0x004a
        L_0x0048:
            r3 = r0
            r5 = r3
        L_0x004a:
            r7 = r3
        L_0x004b:
            int r4 = r8.wordCount
            if (r7 >= r4) goto L_0x0064
            com.samsung.android.ocr.MOCRResult$Word[] r4 = r8.words
            r4 = r4[r7]
            int r4 = r4.charCount
            int r6 = r4 + r9
            if (r6 >= r2) goto L_0x005f
            int r9 = r9 + r4
            int r9 = r9 + 1
            int r7 = r7 + 1
            goto L_0x004b
        L_0x005f:
            int r0 = r2 - r9
            r4 = r7
            r6 = r0
            goto L_0x0066
        L_0x0064:
            r4 = r0
            r6 = r4
        L_0x0066:
            java.lang.String r7 = TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r9 = " - "
            r8.append(r9)
            r8.append(r2)
            r8.append(r9)
            r8.append(r3)
            java.lang.String r9 = " "
            r8.append(r9)
            r8.append(r4)
            java.lang.String r8 = r8.toString()
            com.samsung.android.ocr.MOCRLog.d(r7, r8)
            int[] r7 = new int[]{r1, r2, r3, r4, r5, r6}
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(java.util.regex.Matcher, com.samsung.android.ocr.MOCRResult$Line, int):int[]");
    }

    public static void joinWords(MOCRResult.Line line, int i2, int i7, RegexActions regexActions) {
        int indexOf;
        int indexOf2;
        int indexOf3;
        MOCRResult.Line line2 = line;
        int i8 = i2;
        int i10 = i7;
        RegexActions regexActions2 = regexActions;
        ArrayList arrayList = new ArrayList();
        for (int i11 = 0; i11 < i8; i11++) {
            arrayList.add(line2.words[i11]);
        }
        boolean z = line2.words[i8].chars != null;
        MOCRResult.Word word = new MOCRResult.Word();
        Point[] pointArr = new Point[4];
        word.wRect = pointArr;
        MOCRResult.Word[] wordArr = line2.words;
        MOCRResult.Word word2 = wordArr[i8];
        MOCRResult.Word word3 = wordArr[i10];
        Point[] pointArr2 = word2.wRect;
        pointArr[0] = pointArr2[0];
        Point[] pointArr3 = word3.wRect;
        pointArr[1] = pointArr3[1];
        pointArr[2] = pointArr3[2];
        pointArr[3] = pointArr2[3];
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb2 = new StringBuilder();
        float f = 0.0f;
        for (int i12 = i8; i12 <= i10; i12++) {
            MOCRResult.Word word4 = line2.words[i12];
            sb2.append(word4.wordText);
            f += word4.conf;
            if (z) {
                arrayList2.addAll(Arrays.asList(word4.chars));
            }
        }
        if (regexActions2 == RegexActions.REPLACE_COMMA_IN_PHONE_NUMBER) {
            for (int indexOf4 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX); indexOf4 >= 0; indexOf4 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX, indexOf4 + 1)) {
                if (!(indexOf4 == 0 || indexOf4 == sb2.length() - 1)) {
                    sb2.setCharAt(indexOf4, '.');
                    if (z) {
                        ((MOCRResult.Char) arrayList2.get(indexOf4)).unicode = 46;
                    }
                }
            }
        }
        if (regexActions2 == RegexActions.REPLACE_SQUARE_BRACKET_IN_PHONE_NUMBER && (indexOf3 = sb2.indexOf("]")) != -1) {
            sb2.setCharAt(indexOf3, ')');
            if (z) {
                ((MOCRResult.Char) arrayList2.get(indexOf3)).unicode = 41;
            }
        }
        if (regexActions2 == RegexActions.REPLACE_COMMA_IN_EMAIL_OR_URL && (indexOf2 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) != -1) {
            sb2.setCharAt(indexOf2, '.');
            if (z) {
                ((MOCRResult.Char) arrayList2.get(indexOf2)).unicode = 46;
            }
        }
        if (regexActions2 == RegexActions.REPLACE_DOUBLE_IN_EMAIL && (indexOf = sb2.indexOf("..")) != -1) {
            sb2.deleteCharAt(indexOf);
            if (z) {
                arrayList2.remove(indexOf);
            }
        }
        if (regexActions2 == RegexActions.REPLACE_DOUBLE_IN_URL) {
            int indexOf5 = sb2.indexOf("..");
            if (indexOf5 != -1) {
                sb2.deleteCharAt(indexOf5);
                if (z) {
                    arrayList2.remove(indexOf5);
                }
            }
            int indexOf6 = sb2.indexOf("::");
            if (indexOf6 != -1) {
                sb2.deleteCharAt(indexOf6);
                if (z) {
                    arrayList2.remove(indexOf6);
                }
            }
        }
        if (regexActions2 == RegexActions.REPLACE_COMMA_MULTIPLE_IN_URL) {
            for (int indexOf7 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX); indexOf7 >= 0; indexOf7 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX, indexOf7 + 1)) {
                if (!(indexOf7 == 0 || indexOf7 == sb2.length() - 1)) {
                    sb2.setCharAt(indexOf7, '.');
                    if (z) {
                        ((MOCRResult.Char) arrayList2.get(indexOf7)).unicode = 46;
                    }
                }
            }
        }
        if (regexActions2 == RegexActions.REPLACE_DOUBLE_WITH_COMMA_IN_URL) {
            int indexOf8 = sb2.indexOf("..");
            if (indexOf8 != -1) {
                sb2.deleteCharAt(indexOf8);
                if (z) {
                    arrayList2.remove(indexOf8);
                }
            }
            for (int indexOf9 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX); indexOf9 >= 0; indexOf9 = sb2.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX, indexOf9 + 1)) {
                if (!(indexOf9 == 0 || indexOf9 == sb2.length() - 1)) {
                    sb2.setCharAt(indexOf9, '.');
                    if (z) {
                        ((MOCRResult.Char) arrayList2.get(indexOf9)).unicode = 46;
                    }
                }
            }
        }
        if (regexActions2 == RegexActions.REPLACE_O_IN_DATETIME) {
            for (int indexOf10 = sb2.indexOf("O"); indexOf10 >= 0; indexOf10 = sb2.indexOf("O", indexOf10 + 1)) {
                if (!(indexOf10 == 0 || indexOf10 == sb2.length() - 1)) {
                    sb2.setCharAt(indexOf10, '0');
                    if (z) {
                        ((MOCRResult.Char) arrayList2.get(indexOf10)).unicode = 48;
                    }
                }
            }
            for (int indexOf11 = sb2.indexOf("o"); indexOf11 >= 0; indexOf11 = sb2.indexOf("o", indexOf11 + 1)) {
                sb2.setCharAt(indexOf11, '0');
                if (z) {
                    ((MOCRResult.Char) arrayList2.get(indexOf11)).unicode = 48;
                }
            }
        }
        String sb3 = sb2.toString();
        word.wordText = sb3;
        word.conf = f / ((float) ((i10 - i8) + 1));
        if (z) {
            MOCRResult.Char[] charArr = (MOCRResult.Char[]) arrayList2.toArray(new MOCRResult.Char[0]);
            word.chars = charArr;
            word.charCount = charArr.length;
        } else {
            word.chars = null;
            word.charCount = sb3.length();
        }
        arrayList.add(word);
        for (int i13 = i10 + 1; i13 < line2.wordCount; i13++) {
            arrayList.add(line2.words[i13]);
        }
        MOCRResult.Word[] wordArr2 = (MOCRResult.Word[]) arrayList.toArray(new MOCRResult.Word[0]);
        line2.words = wordArr2;
        line2.wordCount = wordArr2.length;
    }

    public static void replaceCharInWord(MOCRResult.Line line, int i2, RegexActions regexActions, String str) {
        int lastIndexOf;
        MOCRResult.Word word = line.words[i2];
        boolean z = word.chars != null;
        StringBuilder sb2 = new StringBuilder(word.wordText);
        if (regexActions == RegexActions.REPLACE_SUPERSCRIPT_2_IN_UNITS) {
            if (str == null || str.isEmpty()) {
                str = "2";
            }
            int lastIndexOf2 = sb2.lastIndexOf(str);
            if (lastIndexOf2 != -1) {
                sb2.setCharAt(lastIndexOf2, 178);
                line.words[i2].wordText = sb2.toString();
                if (z) {
                    line.words[i2].chars[lastIndexOf2].unicode = 178;
                }
            }
        }
        if (regexActions == RegexActions.REPLACE_SUPERSCRIPT_3_IN_UNITS && (lastIndexOf = sb2.lastIndexOf("3")) != -1) {
            sb2.setCharAt(lastIndexOf, 179);
            line.words[i2].wordText = sb2.toString();
            if (z) {
                line.words[i2].chars[lastIndexOf].unicode = 179;
            }
        }
    }
}
