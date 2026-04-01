package com.samsung.android.imagetranslation.util;

import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class WordCountRule implements Rule {
    private static final int MIN_CHARACTER_COUNT_FOR_CJ = 10;
    private static final int MIN_WORD_COUNT = 3;

    public double getScore(List<LttOcrResult.BlockInfo> list) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (LttOcrResult.BlockInfo next : list) {
            BrokenLineDetector brokenLineDetector = new BrokenLineDetector(next);
            for (LttOcrResult.LineInfo next2 : next.getLineInfo()) {
                if (CJDetector.isCJLanguage(next2.getString())) {
                    if (next2.getString().length() <= 10) {
                        d += 1.0d;
                    }
                } else if (next2.getWordInfo().size() <= 3 && !brokenLineDetector.isContinuousLine(next2)) {
                    d += 1.0d;
                }
                d2 += 1.0d;
                d += 1.0d;
            }
        }
        if (d > MapUtil.INVALID_LOCATION) {
            return d2 / d;
        }
        return MapUtil.INVALID_LOCATION;
    }
}
