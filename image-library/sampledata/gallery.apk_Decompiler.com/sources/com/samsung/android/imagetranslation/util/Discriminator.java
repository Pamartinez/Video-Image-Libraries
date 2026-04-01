package com.samsung.android.imagetranslation.util;

import android.content.Context;
import androidx.core.util.Pair;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Discriminator {
    private static final String TAG = "Discriminator";

    private static double calculateScore(List<LttOcrResult.BlockInfo> list) {
        double d = MapUtil.INVALID_LOCATION;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (Pair next : getWeightedRules()) {
            d2 += ((Double) next.second).doubleValue();
            double doubleValue = ((Double) next.second).doubleValue() * ((Rule) next.first).getScore(list);
            d3 += doubleValue;
            LTTLogger.d(TAG, ((Rule) next.first).getClass().getSimpleName() + " weighted score : " + doubleValue);
        }
        if (d2 > MapUtil.INVALID_LOCATION) {
            d = d3 / d2;
        }
        LTTLogger.d(TAG, "score: " + d);
        return d;
    }

    private static List<Pair<Rule, Double>> getWeightedRules() {
        WordCountRule wordCountRule = new WordCountRule();
        Double valueOf = Double.valueOf(1.0d);
        return List.of(new Pair(wordCountRule, valueOf), new Pair(new LineWidthRule(), valueOf));
    }

    public static boolean isDocument(LttOcrResult lttOcrResult) {
        if (calculateScore(lttOcrResult.getBlockInfoList()) > 0.5d) {
            return true;
        }
        return false;
    }

    public static boolean isParagraph(LttOcrResult.BlockInfo blockInfo) {
        return calculateScore(List.of(blockInfo)) > 0.5d;
    }

    public static boolean isShortSentence(Context context, LttOcrResult.BlockInfo blockInfo) {
        if (BlockInfoUtil.getWordCount(blockInfo) >= 24) {
            return false;
        }
        return new NLPAnalyzer(context).isSentenceBlock(blockInfo);
    }

    public static boolean isParagraph(Context context, LttOcrResult.BlockInfo blockInfo) {
        return calculateScore(List.of(blockInfo)) > 0.5d || isShortSentence(context, blockInfo);
    }
}
