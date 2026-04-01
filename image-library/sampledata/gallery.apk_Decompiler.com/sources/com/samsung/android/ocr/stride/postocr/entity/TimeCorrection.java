package com.samsung.android.ocr.stride.postocr.entity;

import com.samsung.android.ocr.MOCRLog;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.ocr.stride.postocr.entity.CorrectionBase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimeCorrection extends CorrectionBase {
    private static final int GROUP_REPLACE_O_IN_TIME = 1;
    private static final int GROUP_REPLACE_O_IN_TIME1 = 1;
    private static final Pattern PAT_TIME1_REPLACE_0 = Pattern.compile(REGEX_TIME1_REPLACE_O);
    private static final Pattern PAT_TIME_REPLACE_O = Pattern.compile(REGEX_TIME_REPLACE_O);
    private static final String REGEX_TIME1_REPLACE_O = "\\b(\\d{1}(\\s+)\\d{1})(\\s?)(o'(\\s?)clock)";
    private static final String REGEX_TIME_REPLACE_O = "\\b([\\dO]{1,2})(\\.|:)([\\dO]{1,2})(\\s?)(?i:(AM|PM))\\b";
    private static final String TAG = "TimeCorrection";
    private static final String TIME_POSTFIX = "(?i:(AM|PM))";

    public static void validateTimeFormat(MOCRResult.Page page) {
        char c5;
        for (MOCRResult.Block block : page.blocks) {
            for (MOCRResult.Line line : block.lines) {
                String text = line.getText();
                Matcher matcher = PAT_TIME_REPLACE_O.matcher(text);
                while (true) {
                    c5 = 3;
                    if (!matcher.find()) {
                        break;
                    }
                    String str = TAG;
                    StringBuilder e = C0280e.e("Original line :: ", text, str, "PAT_TIME_REPLACE_O match: ");
                    e.append(matcher.group());
                    MOCRLog.d(str, e.toString());
                    int[] matchingIds = CorrectionBase.getMatchingIds(matcher, line, 1);
                    int i2 = matchingIds[2];
                    int i7 = matchingIds[3];
                    if (i2 <= i7) {
                        CorrectionBase.joinWords(line, i2, i7, CorrectionBase.RegexActions.REPLACE_O_IN_DATETIME);
                    }
                    MOCRLog.i(str, "Post OCR Entity Validation");
                }
                Matcher matcher2 = PAT_TIME1_REPLACE_0.matcher(text);
                while (matcher2.find()) {
                    String str2 = TAG;
                    StringBuilder e7 = C0280e.e("Original line :: ", text, str2, "PAT_TIME1_REPLACE_O match: ");
                    char c6 = c5;
                    e7.append(matcher2.group());
                    MOCRLog.d(str2, e7.toString());
                    int[] matchingIds2 = CorrectionBase.getMatchingIds(matcher2, line, 1);
                    int i8 = matchingIds2[2];
                    int i10 = matchingIds2[c6];
                    if (i8 < i10) {
                        CorrectionBase.joinWords(line, i8, i10);
                    }
                    MOCRLog.i(str2, "Post OCR Entity Validation");
                    c5 = c6;
                }
            }
        }
    }
}
