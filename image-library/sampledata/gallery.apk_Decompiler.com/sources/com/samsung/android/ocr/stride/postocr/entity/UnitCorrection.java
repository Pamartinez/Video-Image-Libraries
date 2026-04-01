package com.samsung.android.ocr.stride.postocr.entity;

import com.samsung.android.ocr.MOCRLog;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.ocr.stride.postocr.entity.CorrectionBase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnitCorrection extends CorrectionBase {
    private static final int GROUP_REPLACE_SUPERSCRIPT_2 = 4;
    private static final Pattern PAT_DIGIT_WITH_UNITS_2 = Pattern.compile(REGEX_DIGIT_WITH_UNITS_2, 2);
    private static final Pattern PAT_DIGIT_WITH_UNITS_3 = Pattern.compile(REGEX_DIGIT_WITH_UNITS_3, 2);
    private static final String REGEX_DIGIT_WITH_UNITS_2 = "(\\d)+(\\s)?(cm|ft|in|m|km|mm|g\\/cm|m\\/s)((2\\b)|(\\?))";
    private static final String REGEX_DIGIT_WITH_UNITS_3 = "(\\d)+(\\s)?(cm|ft|in|m|km|mm|g\\/cm|m\\/s)(3)\\b";
    private static final String TAG = "UnitCorrection";
    private static final String UNITS_WITH_CUBE = "(cm|ft|in|m|km|mm|g\\/cm|m\\/s)";
    private static final String UNITS_WITH_SQUARE = "(cm|ft|in|m|km|mm|g\\/cm|m\\/s)";

    public static void validateUnits(MOCRResult.Page page) {
        char c5;
        for (MOCRResult.Block block : page.blocks) {
            for (MOCRResult.Line line : block.lines) {
                String text = line.getText();
                Matcher matcher = PAT_DIGIT_WITH_UNITS_2.matcher(text);
                while (true) {
                    c5 = 3;
                    if (!matcher.find()) {
                        break;
                    }
                    String str = TAG;
                    StringBuilder e = C0280e.e("Original line :: ", text, str, "PAT_DIGIT_WITH_UNITS_2 match: ");
                    e.append(matcher.group());
                    MOCRLog.d(str, e.toString());
                    int[] matchingIds = CorrectionBase.getMatchingIds(matcher, line);
                    int i2 = matchingIds[2];
                    int i7 = matchingIds[3];
                    if (i2 <= i7) {
                        CorrectionBase.replaceCharInWord(line, i7, CorrectionBase.RegexActions.REPLACE_SUPERSCRIPT_2_IN_UNITS, matcher.group(4));
                        MOCRLog.d(str, "Post OCR Entity Validation");
                    }
                }
                Matcher matcher2 = PAT_DIGIT_WITH_UNITS_3.matcher(text);
                while (matcher2.find()) {
                    String str2 = TAG;
                    StringBuilder e7 = C0280e.e("Original line :: ", text, str2, "PAT_DIGIT_WITH_UNITS_3 match: ");
                    char c6 = c5;
                    e7.append(matcher2.group());
                    MOCRLog.d(str2, e7.toString());
                    int[] matchingIds2 = CorrectionBase.getMatchingIds(matcher2, line, 4);
                    int i8 = matchingIds2[2];
                    int i10 = matchingIds2[c6];
                    if (i8 <= i10) {
                        CorrectionBase.replaceCharInWord(line, i10, CorrectionBase.RegexActions.REPLACE_SUPERSCRIPT_3_IN_UNITS);
                        MOCRLog.d(str2, "Post OCR Entity Validation");
                    }
                    c5 = c6;
                }
            }
        }
    }
}
