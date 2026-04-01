package com.samsung.android.ocr.stride.postocr.entity;

import android.graphics.Point;
import com.samsung.android.ocr.MOCRResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmailCorrection extends CorrectionBase {
    private static final String DOMAIN_EXTNS = "(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|ac)";
    private static final Pattern PAT_EMAIL_BASIC = Pattern.compile(REGEX_EMAIL_BASIC, 2);
    private static final Pattern PAT_EMAIL_COMMA = Pattern.compile(REGEX_EMAIL_COMMA, 2);
    private static final Pattern PAT_EMAIL_SPACE = Pattern.compile(REGEX_EMAIL_SPACE, 2);
    private static final Pattern PAT_SPACE_BW_NAMES = Pattern.compile(REGEX_SPACE_BW_NAME, 2);
    private static final String REGEX_EMAIL_BASIC = "([a-zA-Z0-9-._]+)(\\s)*@(\\s)*([a-zA-Z0-9]+)(\\s)*(\\.)(\\s)*(\\.)?(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|ac)";
    private static final String REGEX_EMAIL_COMMA = "([a-zA-Z0-9-._]+)(\\s)*@(\\s)*([a-zA-Z0-9]+)(\\s)*(,)(\\s)*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|ac)";
    private static final String REGEX_EMAIL_SPACE = "([a-zA-Z0-9-._]+)@([a-zA-Z0-9]+)(\\s)(kr)(?=($|\\s))";
    private static final String REGEX_SPACE_BW_NAME = "([a-zA-Z0-9]+)(\\s)*(\\.)(\\s)*([a-zA-Z0-9]+)(\\s)*@(\\s)*([a-zA-Z0-9]+)(\\s)*(\\.)(\\s)*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|ac)";
    private static final String TAG = "EmailCorrection";

    private static void joinWordByDot(MOCRResult.Line line, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < i2; i7++) {
            arrayList.add(line.words[i7]);
        }
        MOCRResult.Word word = new MOCRResult.Word();
        Point[] pointArr = new Point[4];
        word.wRect = pointArr;
        MOCRResult.Word[] wordArr = line.words;
        MOCRResult.Word word2 = wordArr[i2];
        MOCRResult.Word word3 = wordArr[i2 + 1];
        Point[] pointArr2 = word2.wRect;
        pointArr[0] = pointArr2[0];
        Point[] pointArr3 = word3.wRect;
        pointArr[1] = pointArr3[1];
        pointArr[2] = pointArr3[2];
        pointArr[3] = pointArr2[3];
        String str = word2.wordText + "." + word3.wordText;
        word.wordText = str;
        word.conf = (word2.conf + word3.conf) / 2.0f;
        if (word2.chars != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(Arrays.asList(word2.chars));
            MOCRResult.Char charR = new MOCRResult.Char();
            charR.unicode = 46;
            charR.cRect = (Point[]) word2.chars[word2.charCount - 1].cRect.clone();
            arrayList2.add(charR);
            arrayList2.addAll(Arrays.asList(word3.chars));
            MOCRResult.Char[] charArr = (MOCRResult.Char[]) arrayList2.toArray(new MOCRResult.Char[0]);
            word.chars = charArr;
            word.charCount = charArr.length;
        } else {
            word.charCount = str.length();
        }
        arrayList.add(word);
        for (int i8 = i2 + 2; i8 < line.wordCount; i8++) {
            arrayList.add(line.words[i8]);
        }
        MOCRResult.Word[] wordArr2 = (MOCRResult.Word[]) arrayList.toArray(new MOCRResult.Word[0]);
        line.words = wordArr2;
        line.wordCount = wordArr2.length;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fa A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void validateEmailLine(com.samsung.android.ocr.MOCRResult.Page r17) {
        /*
            r0 = r17
            com.samsung.android.ocr.MOCRResult$Block[] r0 = r0.blocks
            int r1 = r0.length
            r3 = 0
        L_0x0006:
            if (r3 >= r1) goto L_0x0102
            r4 = r0[r3]
            com.samsung.android.ocr.MOCRResult$Line[] r4 = r4.lines
            int r5 = r4.length
            r6 = 0
        L_0x000e:
            if (r6 >= r5) goto L_0x00fe
            r7 = r4[r6]
            java.lang.String r8 = r7.getText()
            java.util.regex.Pattern r9 = PAT_SPACE_BW_NAMES
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            java.lang.String r11 = "Post OCR Entity Validation"
            r13 = 2
            java.lang.String r14 = "Original line :: "
            r15 = 1
            if (r10 == 0) goto L_0x0052
            java.lang.String r10 = TAG
            java.lang.String r2 = "PAT_SPACE_BW_NAMES match: "
            java.lang.StringBuilder r2 = t1.C0280e.e(r14, r8, r10, r2)
            r16 = 3
            java.lang.String r12 = r9.group()
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            com.samsung.android.ocr.MOCRLog.d(r10, r2)
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r9 = r2[r13]
            r2 = r2[r16]
            if (r9 >= r2) goto L_0x0050
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r9, r2)
            com.samsung.android.ocr.MOCRLog.i(r10, r11)
        L_0x0050:
            r2 = r15
            goto L_0x0055
        L_0x0052:
            r16 = 3
            r2 = 0
        L_0x0055:
            if (r2 != 0) goto L_0x008c
            java.util.regex.Pattern r9 = PAT_EMAIL_BASIC
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x008c
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_EMAIL_BASIC match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r9 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r10 = r9[r13]
            r9 = r9[r16]
            if (r10 >= r9) goto L_0x008b
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r12 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_DOUBLE_IN_EMAIL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r9, r12)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
        L_0x008b:
            r2 = r15
        L_0x008c:
            if (r2 != 0) goto L_0x00c3
            java.util.regex.Pattern r9 = PAT_EMAIL_COMMA
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x00c3
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_EMAIL_COMMA match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r9 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r10 = r9[r13]
            r9 = r9[r16]
            if (r10 >= r9) goto L_0x00c4
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r12 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_COMMA_IN_EMAIL_OR_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r9, r12)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
            goto L_0x00c4
        L_0x00c3:
            r15 = r2
        L_0x00c4:
            if (r15 != 0) goto L_0x00fa
            java.util.regex.Pattern r2 = PAT_EMAIL_SPACE
            java.util.regex.Matcher r2 = r2.matcher(r8)
            boolean r9 = r2.find()
            if (r9 == 0) goto L_0x00fa
            java.lang.String r9 = TAG
            java.lang.String r10 = "PAT_EMAIL_SPACE match: "
            java.lang.StringBuilder r8 = t1.C0280e.e(r14, r8, r9, r10)
            java.lang.String r10 = r2.group()
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            com.samsung.android.ocr.MOCRLog.d(r9, r8)
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r2, r7)
            r8 = r2[r13]
            r2 = r2[r16]
            int r10 = r8 + 1
            if (r10 != r2) goto L_0x00fa
            joinWordByDot(r7, r8)
            com.samsung.android.ocr.MOCRLog.i(r9, r11)
        L_0x00fa:
            int r6 = r6 + 1
            goto L_0x000e
        L_0x00fe:
            int r3 = r3 + 1
            goto L_0x0006
        L_0x0102:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.ocr.stride.postocr.entity.EmailCorrection.validateEmailLine(com.samsung.android.ocr.MOCRResult$Page):void");
    }
}
