package com.samsung.android.ocr.stride.postocr.entity;

import com.samsung.android.ocr.MOCRLog;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.ocr.stride.postocr.entity.CorrectionBase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DateCorrection extends CorrectionBase {
    private static final String DATE_SUFFIX = "(st|nd|rd|th)";
    private static final int GROUP_REPLACE_O_IN_DATE = 1;
    private static final int GROUP_REPLACE_O_IN_DATE1 = 3;
    private static final String MONTH_NAMES = "(January|February|March|April|June|July|August|September|October|November|December|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
    private static final Pattern PAT_DATE1_REPLACE_O = Pattern.compile(REGEX_DATE1_REPLACE_O, 2);
    private static final Pattern PAT_DATE_BASIC1 = Pattern.compile(REGEX_DATE_BASIC1, 2);
    private static final Pattern PAT_DATE_REPLACE_O = Pattern.compile(REGEX_DATE_REPLACE_O);
    private static final String REGEX_DATE1_REPLACE_O = "\\b(January|February|March|April|June|July|August|September|October|November|December|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)(\\s+)([\\dOo]{1,2})(\\s?)(st|nd|rd|th)((\\s+)(\\d{4}))?\\b";
    private static final String REGEX_DATE_BASIC1 = "\\b(\\d)([0-9\\s]{0,1})(-)([0-9\\s]{1,3})(-)(\\d{2}|\\d{4})\\b";
    private static final String REGEX_DATE_REPLACE_O = "\\b(?!O[^\\d])([\\dO]{1,2})(\\s?)(?i:(st|nd|rd|th))?(\\s+)(?i:(January|February|March|April|June|July|August|September|October|November|December|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))(\\s+(\\d{2}|\\d{4}))?\\b";
    private static final String TAG = "DateCorrection";

    public static void validateDateFormat(MOCRResult.Page page) {
        char c5;
        for (MOCRResult.Block block : page.blocks) {
            for (MOCRResult.Line line : block.lines) {
                String text = line.getText();
                Matcher matcher = PAT_DATE_BASIC1.matcher(text);
                while (true) {
                    c5 = 2;
                    if (!matcher.find()) {
                        break;
                    }
                    String str = TAG;
                    StringBuilder e = C0280e.e("Original line :: ", text, str, "PAT_DATE_BASIC1 match: ");
                    e.append(matcher.group());
                    MOCRLog.d(str, e.toString());
                    int[] matchingIds = CorrectionBase.getMatchingIds(matcher, line);
                    int i2 = matchingIds[2];
                    int i7 = matchingIds[3];
                    if (i2 < i7) {
                        CorrectionBase.joinWords(line, i2, i7);
                        MOCRLog.i(str, "Post OCR Entity Validation");
                    }
                }
                Matcher matcher2 = PAT_DATE_REPLACE_O.matcher(text);
                while (matcher2.find()) {
                    String str2 = TAG;
                    StringBuilder e7 = C0280e.e("Original line :: ", text, str2, "PAT_DATE_REPLACE_O match: ");
                    e7.append(matcher2.group());
                    MOCRLog.d(str2, e7.toString());
                    int[] matchingIds2 = CorrectionBase.getMatchingIds(matcher2, line, 1);
                    int i8 = matchingIds2[c5];
                    int i10 = matchingIds2[3];
                    char c6 = c5;
                    if (i8 <= i10) {
                        CorrectionBase.joinWords(line, i8, i10, CorrectionBase.RegexActions.REPLACE_O_IN_DATETIME);
                    }
                    MOCRLog.i(str2, "Post OCR Entity Validation");
                    c5 = c6;
                }
                char c8 = c5;
                Matcher matcher3 = PAT_DATE1_REPLACE_O.matcher(text);
                while (matcher3.find()) {
                    String str3 = TAG;
                    StringBuilder e8 = C0280e.e("Original line :: ", text, str3, "PAT_DATE1_REPLACE_O match: ");
                    e8.append(matcher3.group());
                    MOCRLog.d(str3, e8.toString());
                    int[] matchingIds3 = CorrectionBase.getMatchingIds(matcher3, line, 3);
                    int i11 = matchingIds3[c8];
                    int i12 = matchingIds3[3];
                    if (i11 <= i12) {
                        CorrectionBase.joinWords(line, i11, i12, CorrectionBase.RegexActions.REPLACE_O_IN_DATETIME);
                    }
                    MOCRLog.i(str3, "Post OCR Entity Validation");
                }
            }
        }
    }
}
