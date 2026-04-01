package com.samsung.android.ocr.stride.postocr.entity;

import A.a;
import D3.b;
import android.graphics.Point;
import com.samsung.android.ocr.MOCRLog;
import com.samsung.android.ocr.MOCRResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class URLCorrection extends CorrectionBase {
    private static final String DOMAIN_EXTNS = "(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog)";
    private static String[] FIND_DOMAIN = {"Inkd.in", "inkd.in"};
    private static int GROUP_URL_HTTP = 1;
    private static int GROUP_URL_HTTP_POPULAR_DOMAIN = 8;
    private static int GROUP_URL_HTTP_SYMBOLS = 4;
    private static final String HTTP_SYMBOL_VARIATIONS = "(([.:;\\s]{1,3})([/1l])?\\s*(/))";
    private static final String HTTP_VARIATIONS = "((http|nttp|ntip|htip|htto|httr|htp)(s)?)";
    private static final Pattern PAT_HTTP_SPACE_COMMA = Pattern.compile(REGEX_HTTP_SPACE_COMMA, 2);
    private static final Pattern PAT_STRICT_DOMAIN = Pattern.compile(REGEX_STRICT_DOMAIN, 2);
    private static final Pattern PAT_URL_BASIC = Pattern.compile(REGEX_URL_BASIC, 2);
    private static final Pattern PAT_URL_COMMA = Pattern.compile(REGEX_URL_COMMA, 2);
    private static final Pattern PAT_URL_COMMA_WO_SPACE = Pattern.compile(REGEX_URL_COMMA_WO_SPACE, 2);
    private static final Pattern PAT_URL_HTTP_BASIC = Pattern.compile(REGEX_URL_HTTP_BASIC, 2);
    private static final Pattern PAT_URL_WWW_BASIC = Pattern.compile(REGEX_URL_WWW_BASIC, 2);
    private static final Pattern PAT_WEB_PAGE_URL = Pattern.compile(REGEX_WEB_PAGE_URL, 2);
    private static final String REGEX_HTTP_SPACE_COMMA = "((http|nttp|ntip|htip|htto|httr|htp)(s)?)(([.:;\\s]{1,3})([/1l])?\\s*(/))(www)(\\.|\\s)+((([a-zA-Z0-9-]+)(\\s)*(,)(\\s)*)+)(\\s)*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog)";
    private static final String REGEX_STRICT_DOMAIN = "(?<=^|\\s)((([a-zA-Z0-9-]+)\\s*(\\.)\\s*(\\.)?\\s*)+)(\\s)*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog)(?=($|\\s|/))";
    private static final String REGEX_URL_BASIC = "\b((([a-zA-Z0-9-]+)\\s*(\\.)\\s*(\\.)?\\s*){2,3})(\\s)*(\\.)?(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog)";
    private static final String REGEX_URL_COMMA = "(www)(\\s)*(,)(\\s)*([a-zA-Z0-9-.]+)(\\s)*(,)(\\s)*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog)";
    private static final String REGEX_URL_COMMA_WO_SPACE = "((www)\\s*(,)\\s*([a-zA-Z0-9-.]+)\\s*([,.])\\s*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog))|((www)\\s*([,.])\\s*([a-zA-Z0-9-._]+)\\s*(,)\\s*(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog))";
    private static final String REGEX_URL_HTTP_BASIC = "((http|nttp|ntip|htip|htto|httr|htp)(s)?)(([.:;\\s]{1,3})([/1l])?\\s*(/))(((([a-zA-Z0-9-]+)\\s*(\\.)\\s*(\\.)?\\s*)+)(\\s)*(\\.)?(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog))";
    private static final String REGEX_URL_WWW_BASIC = "(www|vww)(\\s)*(.+?)(\\s)*((([a-zA-Z0-9-]+)\\s*(\\.)\\s*(\\.)?\\s*)+)(\\s)*(\\.)?(com|in|kr|se|org|net|io|gle|mil|tk|cn|de|uk|nl|icu|ru|ir|au|ua|us|fr|es|ca|it|sg|gov|edu|co|ms|at|blog)";
    private static final String REGEX_WEB_PAGE_URL = "(?<!\\/)((\\s*\\/\\s*([a-zA-Z0-9-_]+))+)((\\s*(\\.)\\s*[a-zA-Z]{2,4})|(\\s*(\\?)\\s*)[A-Za-z0-9-_#=+&$%();\\p{InHangul_Syllables}\\p{InCJK_Unified_Ideographs}]+)?";
    private static String[] REPLACE_DOMAIN = {"lnkd.in", "lnkd.in"};
    private static final String TAG = "URLCorrection";
    private static final String WWW_VARIATIONS = "(www|vww)";

    private static void correctDomainName(MOCRResult.Line line, int i2, Matcher matcher, int i7) {
        int indexOf;
        if (matcher != null) {
            MOCRResult.Word[] wordArr = line.words;
            if (i2 < wordArr.length) {
                MOCRResult.Word word = wordArr[i2];
                String group = matcher.group(i7);
                if (group != null && (indexOf = word.wordText.indexOf(group)) != -1) {
                    int length = group.length() + indexOf;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(word.wordText.substring(0, indexOf));
                    int i8 = 0;
                    while (true) {
                        String[] strArr = FIND_DOMAIN;
                        if (i8 >= strArr.length) {
                            return;
                        }
                        if (group.equals(strArr[i8])) {
                            sb2.append(REPLACE_DOMAIN[i8]);
                            sb2.append(word.wordText.substring(length));
                            word.wordText = sb2.toString();
                            MOCRResult.Char[] charArr = word.chars;
                            if (charArr != null && charArr.length > 0) {
                                for (int i10 = 0; i10 < word.wordText.length(); i10++) {
                                    word.chars[i10].unicode = word.wordText.charAt(i10);
                                }
                            }
                            MOCRLog.d(TAG, "Domain corrected :: " + word.getText());
                            return;
                        }
                        i8++;
                    }
                }
            }
        }
    }

    private static void correctHttpPrefix(MOCRResult.Line line, int i2, Matcher matcher) {
        if (matcher != null) {
            MOCRResult.Word[] wordArr = line.words;
            if (i2 < wordArr.length) {
                MOCRResult.Word word = wordArr[i2];
                String group = matcher.group(GROUP_URL_HTTP);
                if (group != null) {
                    int indexOf = word.wordText.indexOf(group);
                    int length = group.length() + indexOf;
                    String str = "http";
                    if (!group.equalsIgnoreCase(str) && !group.equalsIgnoreCase("https") && indexOf != -1) {
                        String str2 = TAG;
                        MOCRLog.d(str2, group + " " + indexOf + " " + length);
                        String str3 = word.wordText;
                        if (group.endsWith("s")) {
                            str = "https";
                        }
                        String str4 = str3.substring(0, indexOf) + str + str3.substring(length);
                        word.wordText = str4;
                        word.charCount = str4.length();
                        MOCRLog.d(str2, "Http Word Correction :: " + line.getText());
                    }
                    String group2 = matcher.group(GROUP_URL_HTTP_SYMBOLS);
                    if (group2 != null) {
                        String replaceAll = group2.replaceAll("\\s", "");
                        int indexOf2 = word.wordText.indexOf(replaceAll);
                        int length2 = replaceAll.length() + indexOf2;
                        if (!replaceAll.equalsIgnoreCase("://") && indexOf2 != -1) {
                            MOCRLog.d(TAG, replaceAll + " " + indexOf2 + " " + length2);
                            int count = length2 - ((int) replaceAll.chars().filter(new b(4)).count());
                            String str5 = word.wordText;
                            int length3 = replaceAll.length();
                            String str6 = str5.substring(0, indexOf2) + "://" + str5.substring(count);
                            word.wordText = str6;
                            if (word.chars == null) {
                                word.charCount = str6.length();
                            } else {
                                ArrayList arrayList = new ArrayList(Arrays.asList(word.chars));
                                MOCRResult.Char charR = (MOCRResult.Char) arrayList.get(indexOf2);
                                MOCRResult.Char charR2 = (MOCRResult.Char) arrayList.get(indexOf2 + 1);
                                int i7 = indexOf2 + 2;
                                MOCRResult.Char charR3 = (MOCRResult.Char) arrayList.get(i7);
                                if (length3 < 3) {
                                    charR.unicode = 58;
                                    charR2.unicode = 47;
                                    MOCRResult.Char charR4 = new MOCRResult.Char();
                                    charR4.unicode = 47;
                                    charR4.cRect = (Point[]) charR2.cRect.clone();
                                    arrayList.add(i7, charR4);
                                } else if (length3 == 3) {
                                    charR.unicode = 58;
                                    charR2.unicode = 47;
                                    charR3.unicode = 47;
                                } else {
                                    charR.unicode = 58;
                                    charR2.unicode = 47;
                                    charR3.unicode = 47;
                                    for (int i8 = 0; i8 < length3 - 3; i8++) {
                                        arrayList.remove(indexOf2 + 3 + i8);
                                    }
                                }
                                MOCRResult.Char[] charArr = (MOCRResult.Char[]) arrayList.toArray(new MOCRResult.Char[0]);
                                word.chars = charArr;
                                word.charCount = charArr.length;
                            }
                            MOCRLog.d(TAG, "Http Symbol Correction :: " + line.getText());
                        }
                    }
                }
            }
        }
    }

    private static void correctWwwDot(MOCRResult.Line line, int i2) {
        int indexOf;
        MOCRResult.Word word = line.words[i2];
        String str = word.wordText;
        if (str.indexOf("www.") == -1 && (indexOf = str.indexOf("www")) != -1) {
            StringBuilder sb2 = new StringBuilder();
            int i7 = indexOf + 3;
            sb2.append(str.substring(0, i7));
            sb2.append(".");
            sb2.append(str.substring(i7));
            String sb3 = sb2.toString();
            word.wordText = sb3;
            if (word.chars == null) {
                word.charCount = sb3.length();
                return;
            }
            ArrayList arrayList = new ArrayList(Arrays.asList(word.chars));
            MOCRResult.Char charR = new MOCRResult.Char();
            charR.unicode = 46;
            charR.cRect = (Point[]) ((MOCRResult.Char) arrayList.get(indexOf + 2)).cRect.clone();
            arrayList.add(i7, charR);
            MOCRResult.Char[] charArr = (MOCRResult.Char[]) arrayList.toArray(new MOCRResult.Char[0]);
            word.chars = charArr;
            word.charCount = charArr.length;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$correctHttpPrefix$0(int i2) {
        if (i2 == 32) {
            return true;
        }
        return false;
    }

    private static void validatePageURL(MOCRResult.Line line, int i2) {
        String text = line.getText();
        validateWwwCase(line.words[i2]);
        Matcher matcher = PAT_WEB_PAGE_URL.matcher(text);
        if (matcher.find()) {
            String str = TAG;
            StringBuilder e = C0280e.e("Original line :: ", text, str, "PAT_WEB_PAGE_URL match: ");
            e.append(matcher.group());
            MOCRLog.d(str, e.toString());
            int[] matchingIds = CorrectionBase.getMatchingIds(matcher, line);
            int i7 = matchingIds[2];
            int i8 = matchingIds[3];
            StringBuilder h5 = a.h(i7, i8, "Page ids: ", " ", " ");
            h5.append(i2);
            MOCRLog.d(str, h5.toString());
            if ((i7 == i2 || i7 == i2 + 1) && i7 <= i8) {
                CorrectionBase.joinWords(line, i2, i8);
            }
            MOCRLog.d(str, "Updated line :: " + line.getText());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01df A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void validateURLLine(com.samsung.android.ocr.MOCRResult.Page r18) {
        /*
            r0 = r18
            com.samsung.android.ocr.MOCRResult$Block[] r0 = r0.blocks
            int r1 = r0.length
            r3 = 0
        L_0x0006:
            if (r3 >= r1) goto L_0x01e7
            r4 = r0[r3]
            com.samsung.android.ocr.MOCRResult$Line[] r4 = r4.lines
            int r5 = r4.length
            r6 = 0
        L_0x000e:
            if (r6 >= r5) goto L_0x01e3
            r7 = r4[r6]
            java.lang.String r8 = r7.getText()
            java.util.regex.Pattern r9 = PAT_HTTP_SPACE_COMMA
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            java.lang.String r11 = "Post OCR Entity Validation"
            r13 = 2
            java.lang.String r14 = "Original line :: "
            r15 = 1
            if (r10 == 0) goto L_0x005f
            java.lang.String r10 = TAG
            java.lang.String r2 = "PAT_HTTP_SPACE_COMMA match: "
            java.lang.StringBuilder r2 = t1.C0280e.e(r14, r8, r10, r2)
            r16 = 3
            java.lang.String r12 = r9.group()
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            com.samsung.android.ocr.MOCRLog.d(r10, r2)
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r12 = r2[r13]
            r2 = r2[r16]
            r17 = r13
            if (r12 > r2) goto L_0x0051
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r13 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_COMMA_IN_EMAIL_OR_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r12, r2, r13)
        L_0x0051:
            correctHttpPrefix(r7, r12, r9)
            correctWwwDot(r7, r12)
            validatePageURL(r7, r12)
            com.samsung.android.ocr.MOCRLog.i(r10, r11)
            r2 = r15
            goto L_0x0064
        L_0x005f:
            r17 = r13
            r16 = 3
            r2 = 0
        L_0x0064:
            if (r2 != 0) goto L_0x009e
            java.util.regex.Pattern r9 = PAT_URL_COMMA_WO_SPACE
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x009e
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_URL_COMMA_WO_SPACE match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r9 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r10 = r9[r17]
            r9 = r9[r16]
            if (r10 > r9) goto L_0x0097
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r12 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_COMMA_MULTIPLE_IN_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r9, r12)
        L_0x0097:
            validatePageURL(r7, r10)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
            r2 = r15
        L_0x009e:
            if (r2 != 0) goto L_0x00d8
            java.util.regex.Pattern r9 = PAT_URL_COMMA
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x00d8
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_URL_COMMA match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r9 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r10 = r9[r17]
            r9 = r9[r16]
            if (r10 > r9) goto L_0x00d4
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r12 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_COMMA_IN_EMAIL_OR_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r9, r12)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
        L_0x00d4:
            validatePageURL(r7, r10)
            r2 = r15
        L_0x00d8:
            if (r2 != 0) goto L_0x011d
            java.util.regex.Pattern r9 = PAT_URL_HTTP_BASIC
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x011d
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_URL_HTTP_BASIC match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r10 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r12 = r10[r17]
            r10 = r10[r16]
            if (r12 > r10) goto L_0x010b
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r13 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_DOUBLE_IN_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r12, r10, r13)
        L_0x010b:
            correctHttpPrefix(r7, r12, r9)
            correctWwwDot(r7, r12)
            int r10 = GROUP_URL_HTTP_POPULAR_DOMAIN
            correctDomainName(r7, r12, r9, r10)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
            validatePageURL(r7, r12)
            r2 = r15
        L_0x011d:
            if (r2 != 0) goto L_0x0157
            java.util.regex.Pattern r9 = PAT_URL_WWW_BASIC
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x0157
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_URL_WWW_BASIC match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r9 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r10 = r9[r17]
            r9 = r9[r16]
            if (r10 > r9) goto L_0x0153
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r12 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_DOUBLE_WITH_COMMA_IN_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r9, r12)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
        L_0x0153:
            validatePageURL(r7, r10)
            r2 = r15
        L_0x0157:
            if (r2 != 0) goto L_0x019b
            java.util.regex.Pattern r9 = PAT_URL_BASIC
            java.util.regex.Matcher r9 = r9.matcher(r8)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x019b
            java.lang.String r10 = r9.group()
            boolean r10 = validateUrl(r10)
            if (r10 == 0) goto L_0x019b
            java.lang.String r2 = TAG
            java.lang.String r10 = "PAT_URL_BASIC match: "
            java.lang.StringBuilder r10 = t1.C0280e.e(r14, r8, r2, r10)
            java.lang.String r12 = r9.group()
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.samsung.android.ocr.MOCRLog.d(r2, r10)
            int[] r9 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r9, r7)
            r10 = r9[r17]
            r9 = r9[r16]
            if (r10 > r9) goto L_0x0197
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r12 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_DOUBLE_IN_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r10, r9, r12)
            com.samsung.android.ocr.MOCRLog.i(r2, r11)
        L_0x0197:
            validatePageURL(r7, r10)
            goto L_0x019c
        L_0x019b:
            r15 = r2
        L_0x019c:
            if (r15 != 0) goto L_0x01df
            java.util.regex.Pattern r2 = PAT_STRICT_DOMAIN
            java.util.regex.Matcher r2 = r2.matcher(r8)
            boolean r9 = r2.find()
            if (r9 == 0) goto L_0x01df
            java.lang.String r9 = r2.group()
            boolean r9 = validateUrl(r9)
            if (r9 == 0) goto L_0x01df
            java.lang.String r9 = TAG
            java.lang.String r10 = "PAT_STRICT_DOMAIN match: "
            java.lang.StringBuilder r8 = t1.C0280e.e(r14, r8, r9, r10)
            java.lang.String r10 = r2.group()
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            com.samsung.android.ocr.MOCRLog.d(r9, r8)
            int[] r2 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.getMatchingIds(r2, r7)
            r8 = r2[r17]
            r2 = r2[r16]
            if (r8 > r2) goto L_0x01dc
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase$RegexActions r10 = com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.RegexActions.REPLACE_DOUBLE_IN_URL
            com.samsung.android.ocr.stride.postocr.entity.CorrectionBase.joinWords(r7, r8, r2, r10)
            com.samsung.android.ocr.MOCRLog.i(r9, r11)
        L_0x01dc:
            validatePageURL(r7, r8)
        L_0x01df:
            int r6 = r6 + 1
            goto L_0x000e
        L_0x01e3:
            int r3 = r3 + 1
            goto L_0x0006
        L_0x01e7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.ocr.stride.postocr.entity.URLCorrection.validateURLLine(com.samsung.android.ocr.MOCRResult$Page):void");
    }

    private static boolean validateUrl(String str) {
        String trim = str.toLowerCase().trim();
        if (trim.endsWith(". in") || trim.endsWith(". it") || trim.endsWith(". at")) {
            return false;
        }
        return true;
    }

    private static void validateWwwCase(MOCRResult.Word word) {
        String str = word.wordText;
        int indexOf = str.toLowerCase().indexOf("www");
        if (indexOf == -1) {
            indexOf = str.toLowerCase().indexOf("vww");
        }
        if (indexOf != -1) {
            int i2 = indexOf + 3;
            if (!str.substring(indexOf, i2).equals("www") && !str.toUpperCase().equals(str)) {
                MOCRLog.d(TAG, "www correction");
                word.wordText = str.substring(0, indexOf) + "www" + str.substring(i2);
                MOCRResult.Char[] charArr = word.chars;
                if (charArr != null) {
                    charArr[indexOf].unicode = 119;
                    charArr[indexOf + 1].unicode = 119;
                    charArr[indexOf + 2].unicode = 119;
                }
            }
        }
    }
}
