package com.samsung.android.ocr.stride.postocr.entity;

import android.graphics.Point;
import c0.C0086a;
import com.samsung.android.ocr.MOCRLog;
import com.samsung.android.ocr.MOCRResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhoneNumberCorrection extends CorrectionBase {
    private static final int GROUP_COMMA_PHONE = 1;
    private static final int GROUP_ZERO_BRACKETS = 3;
    private static final Pattern PAT_COMMA_PHONE = Pattern.compile(REGEX_COMMA_PHONE, 2);
    private static final Pattern PAT_REGEX_NO_SPACE_CORRECTION = Pattern.compile(REGEX_NO_SPACE_CORRECTION, 2);
    private static final Pattern PAT_REGEX_PHONE_BASIC1 = Pattern.compile(REGEX_PHONE_BASIC1, 2);
    private static final Pattern PAT_REGEX_PHONE_BASIC2 = Pattern.compile(REGEX_PHONE_BASIC2, 2);
    private static final Pattern PAT_REGEX_PHONE_BASIC3 = Pattern.compile(REGEX_PHONE_BASIC3, 2);
    private static final Pattern PAT_REGEX_PHONE_BASIC4 = Pattern.compile(REGEX_PHONE_BASIC4, 2);
    private static final int PHONE_CNTRY_CODE_MAX_DIGITS = 15;
    private static final int PHONE_CNTRY_CODE_MIN_DIGITS = 11;
    private static final int PHONE_MAX_DIGITS = 12;
    private static final int PHONE_MIN_DIGITS = 10;
    private static final String PHONE_PREFIX = "mobile|mob|m\\.p\\.|m\\.p|phone|ph|phn|tel|fax";
    private static final String REGEX_COMMA_PHONE = "(?:mobile|mob|m\\.p\\.|m\\.p|phone|ph|phn|tel|fax)(?:\\s*:?\\s*)(\\+(\\d){1,3}\\s*(\\.|,)(([\\s0-9]+)\\s*(\\.|,)\\s*){2}\\d+)(?:$|\\s|,|\\.)";
    private static final String REGEX_NO_SPACE_CORRECTION = "(?:^|\\s)([mft](:?))(\\+)(\\d{1,3})\\s*([()0O\\[\\]]{3})\\s*(\\d+)\\s";
    private static final String REGEX_PHONE_BASIC1 = "(?<!\\d)(\\+\\s*)?(\\d){1,3}\\s*(-|\\.)\\s*(\\d){1,3}\\s*(-|\\.)\\s*(\\d)+";
    private static final String REGEX_PHONE_BASIC2 = "(\\+)\\s*(\\d){1,3}\\s*([()0O\\[\\]]{3})\\s*(\\d)+\\s*(\\d)+";
    private static final String REGEX_PHONE_BASIC3 = "(?<!\\d)(((\\d{1,3})(\\s\\d)?(-))(\\d{2,4})((-)?)(\\d{2,6}))(?!\\d)";
    private static final String REGEX_PHONE_BASIC4 = "(?<![\\d-])(\\d{1,3})(-)(\\d{1,2})(-)((\\d|\\s){3,5})(-)((\\d|\\s){3,6})";
    private static final String TAG = "PhoneNumberCorrection";

    private static void correctPhoneNumberZeroBracket(MOCRResult.Line line, Matcher matcher, int i2, int i7) {
        String group = matcher.group(3);
        if (group != null && !group.equals("(0)")) {
            while (i2 <= i7) {
                MOCRResult.Word word = line.words[i2];
                if (word.wordText.contains(group)) {
                    int indexOf = word.wordText.indexOf(group);
                    String str = TAG;
                    StringBuilder o2 = C0086a.o(indexOf, "Zero correction at pos ", " in word ");
                    o2.append(word.wordText);
                    MOCRLog.d(str, o2.toString());
                    if (indexOf != -1) {
                        String str2 = word.wordText.substring(0, indexOf) + "(0)" + word.wordText.substring(indexOf + 3);
                        word.wordText = str2;
                        if (word.chars == null) {
                            word.charCount = str2.length();
                        } else {
                            ArrayList arrayList = new ArrayList(Arrays.asList(word.chars));
                            ((MOCRResult.Char) arrayList.get(indexOf)).unicode = 40;
                            ((MOCRResult.Char) arrayList.get(indexOf + 1)).unicode = 48;
                            ((MOCRResult.Char) arrayList.get(indexOf + 2)).unicode = 41;
                            MOCRResult.Char[] charArr = (MOCRResult.Char[]) arrayList.toArray(new MOCRResult.Char[0]);
                            word.chars = charArr;
                            word.charCount = charArr.length;
                        }
                        MOCRLog.d(str, "Post OCR Phone Zero bracket correction");
                        return;
                    }
                    return;
                }
                i2++;
            }
        }
    }

    private static void splitPhonePrefix(MOCRResult.Line line, int i2) {
        boolean z;
        MOCRResult.Line line2 = line;
        int i7 = i2;
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < i7; i8++) {
            arrayList.add(line2.words[i8]);
        }
        MOCRResult.Word word = line2.words[i7];
        if (word.chars != null) {
            z = true;
        } else {
            z = false;
        }
        String str = word.wordText;
        int indexOf = str.indexOf(43);
        if (indexOf != -1) {
            int i10 = word.charCount;
            MOCRResult.Word word2 = new MOCRResult.Word();
            word2.wordText = str.substring(0, indexOf);
            MOCRResult.Word word3 = new MOCRResult.Word();
            word3.wordText = str.substring(indexOf);
            if (z) {
                Point[] pointArr = new Point[4];
                word2.wRect = pointArr;
                MOCRResult.Char[] charArr = word.chars;
                pointArr[0] = charArr[0].cRect[0];
                int i11 = indexOf - 1;
                pointArr[1] = charArr[i11].cRect[1];
                pointArr[2] = charArr[i11].cRect[2];
                pointArr[3] = charArr[0].cRect[3];
                Point[] pointArr2 = new Point[4];
                word3.wRect = pointArr2;
                pointArr2[0] = charArr[indexOf].cRect[0];
                int i12 = i10 - 1;
                pointArr2[1] = charArr[i12].cRect[1];
                pointArr2[2] = charArr[i12].cRect[2];
                pointArr2[3] = charArr[indexOf].cRect[3];
            } else {
                word2.wRect = (Point[]) word.wRect.clone();
                word3.wRect = (Point[]) word.wRect.clone();
            }
            float f = word.conf / ((float) word.charCount);
            word2.conf = ((float) word2.wordText.length()) * f;
            word3.conf = ((float) word3.wordText.length()) * f;
            if (z) {
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (int i13 = 0; i13 < i10; i13++) {
                    MOCRResult.Char charR = word.chars[i13];
                    if (i13 < indexOf) {
                        arrayList2.add(charR);
                    } else {
                        arrayList3.add(charR);
                    }
                }
                word2.chars = (MOCRResult.Char[]) arrayList2.toArray(new MOCRResult.Char[0]);
                MOCRResult.Char[] charArr2 = (MOCRResult.Char[]) arrayList3.toArray(new MOCRResult.Char[0]);
                word3.chars = charArr2;
                word2.charCount = word2.chars.length;
                word3.charCount = charArr2.length;
            } else {
                word2.chars = null;
                word3.chars = null;
                word2.charCount = word2.wordText.length();
                word3.charCount = word3.wordText.length();
            }
            arrayList.add(word2);
            arrayList.add(word3);
        }
        for (int i14 = i7 + 1; i14 < line2.wordCount; i14++) {
            arrayList.add(line2.words[i14]);
        }
        MOCRResult.Word[] wordArr = (MOCRResult.Word[]) arrayList.toArray(new MOCRResult.Word[0]);
        line2.words = wordArr;
        line2.wordCount = wordArr.length;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0184 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void validatePhoneLine(com.samsung.android.ocr.MOCRResult.Page r18) {
        /*
            r0 = r18
            com.samsung.android.ocr.MOCRResult$Block[] r0 = r0.blocks
            int r1 = r0.length
            r3 = 0
        L_0x0006:
            if (r3 >= r1) goto L_0x018e
            r4 = r0[r3]
            com.samsung.android.ocr.MOCRResult$Line[] r4 = r4.lines
            int r5 = r4.length
            r6 = 0
        L_0x000e:
            if (r6 >= r5) goto L_0x0188
            r7 = r4[r6]
            java.lang.String r8 = r7.getText()
            java.util.regex.Pattern r9 = PAT_COMMA_PHONE
            java.util.regex.Matcher r9 = r9.matcher(r8)
        L_0x001c:
            r10 = 0
        L_0x001d:
            boolean r11 = r9.find()
            java.lang.String r12 = ""
            java.lang.String r13 = "\\D"
            java.lang.String r14 = "Post OCR Entity Validation"
            r16 = 2
            r18 = 3
            java.lang.String r15 = "Original line :: "
            r2 = 1
            if (r11 == 0) goto L_0x006c
            java.lang.String r10 = TAG
            java.lang.String r11 = "PAT_COMMA_PHONE match: "
            java.lang.StringBuilder r11 = t1.C0280e.e(r15, r8, r10, r11)
            java.lang.String r15 = r9.group(r2)
            r11.append(r15)
            java.lang.String r11 = r11.toString()
            com.samsung.android.ocr.MOCRLog.d(r10, r11)
            java.lang.String r11 = r9.group()
            java.lang.String r11 = r11.replaceAll(r13, r12)
            int r11 = r11.length()
            boolean r11 = validatePhoneNumber(r11, r2)
            if (r11 == 0) goto L_0x001c
            int[] r11 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7, r2)
            r12 = r11[r16]
            r11 = r11[r18]
            if (r12 > r11) goto L_0x006a
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r13 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_COMMA_IN_PHONE_NUMBER
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r12, r11, r13)
            com.samsung.android.ocr.MOCRLog.i(r10, r14)
        L_0x006a:
            r10 = r2
            goto L_0x001d
        L_0x006c:
            if (r10 != 0) goto L_0x00b8
            java.util.regex.Pattern r9 = PAT_REGEX_PHONE_BASIC1
            java.util.regex.Matcher r9 = r9.matcher(r8)
        L_0x0074:
            boolean r11 = r9.find()
            if (r11 == 0) goto L_0x00b8
            java.lang.String r10 = TAG
            java.lang.String r11 = "PAT_REGEX_PHONE_BASIC1 match: "
            java.lang.StringBuilder r11 = t1.C0280e.e(r15, r8, r10, r11)
            java.lang.String r2 = r9.group()
            r11.append(r2)
            java.lang.String r2 = r11.toString()
            com.samsung.android.ocr.MOCRLog.d(r10, r2)
            java.lang.String r2 = r9.group()
            java.lang.String r2 = r2.replaceAll(r13, r12)
            int r2 = r2.length()
            r11 = 1
            boolean r2 = validatePhoneNumber(r2, r11)
            if (r2 == 0) goto L_0x00b5
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r11 = r2[r16]
            r2 = r2[r18]
            if (r11 >= r2) goto L_0x00b3
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r11, r2)
            com.samsung.android.ocr.MOCRLog.i(r10, r14)
        L_0x00b3:
            r10 = 1
            goto L_0x00b6
        L_0x00b5:
            r10 = 0
        L_0x00b6:
            r2 = 1
            goto L_0x0074
        L_0x00b8:
            if (r10 != 0) goto L_0x00ec
            java.util.regex.Pattern r2 = PAT_REGEX_PHONE_BASIC2
            java.util.regex.Matcher r2 = r2.matcher(r8)
            boolean r9 = r2.find()
            if (r9 == 0) goto L_0x00ec
            java.lang.String r9 = TAG
            java.lang.String r10 = "PAT_REGEX_PHONE_BASIC2 match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r15, r8, r9, r10)
            java.lang.String r11 = r2.group()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r9, r10)
            int[] r10 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r2, r7)
            r11 = r10[r16]
            r10 = r10[r18]
            correctPhoneNumberZeroBracket(r7, r2, r11, r10)
            com.samsung.android.ocr.MOCRLog.i(r9, r14)
            r11 = 1
            goto L_0x00ed
        L_0x00ec:
            r11 = r10
        L_0x00ed:
            if (r11 != 0) goto L_0x013b
            java.util.regex.Pattern r2 = PAT_REGEX_PHONE_BASIC3
            java.util.regex.Matcher r2 = r2.matcher(r8)
            boolean r9 = r2.find()
            if (r9 == 0) goto L_0x013b
            java.lang.String r9 = TAG
            java.lang.String r10 = "PAT_REGEX_PHONE_BASIC3 match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r15, r8, r9, r10)
            java.lang.String r11 = r2.group()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r9, r10)
            java.lang.String r10 = r2.group()
            java.lang.String r10 = r10.replaceAll(r13, r12)
            int r10 = r10.length()
            r11 = 0
            boolean r10 = validatePhoneNumber(r10, r11)
            if (r10 == 0) goto L_0x0138
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r2, r7)
            r10 = r2[r16]
            r2 = r2[r18]
            if (r10 >= r2) goto L_0x0134
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r2)
            com.samsung.android.ocr.MOCRLog.i(r9, r14)
        L_0x0134:
            r17 = r11
            r11 = 1
            goto L_0x013d
        L_0x0138:
            r17 = r11
            goto L_0x013d
        L_0x013b:
            r17 = 0
        L_0x013d:
            if (r11 != 0) goto L_0x0184
            java.util.regex.Pattern r2 = PAT_REGEX_PHONE_BASIC4
            java.util.regex.Matcher r2 = r2.matcher(r8)
            boolean r9 = r2.find()
            if (r9 == 0) goto L_0x0184
            java.lang.String r9 = TAG
            java.lang.String r10 = "PAT_REGEX_PHONE_BASIC4 match: "
            java.lang.StringBuilder r8 = t1.C0280e.e(r15, r8, r9, r10)
            java.lang.String r10 = r2.group()
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            com.samsung.android.ocr.MOCRLog.d(r9, r8)
            java.lang.String r8 = r2.group()
            java.lang.String r8 = r8.replaceAll(r13, r12)
            int r8 = r8.length()
            r11 = 1
            boolean r8 = validatePhoneNumber(r8, r11)
            if (r8 == 0) goto L_0x0184
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r2, r7)
            r8 = r2[r16]
            r2 = r2[r18]
            if (r8 >= r2) goto L_0x0184
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r8, r2)
            com.samsung.android.ocr.MOCRLog.i(r9, r14)
        L_0x0184:
            int r6 = r6 + 1
            goto L_0x000e
        L_0x0188:
            r17 = 0
            int r3 = r3 + 1
            goto L_0x0006
        L_0x018e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.ocr.stride.postocr.entity.PhoneNumberCorrection.validatePhoneLine(com.samsung.android.ocr.MOCRResult$Page):void");
    }

    private static boolean validatePhoneNumber(int i2, boolean z) {
        if (z) {
            if (i2 < 11 || i2 > 15) {
                return false;
            }
            return true;
        } else if (i2 < 10 || i2 > 12) {
            return false;
        } else {
            return true;
        }
    }
}
