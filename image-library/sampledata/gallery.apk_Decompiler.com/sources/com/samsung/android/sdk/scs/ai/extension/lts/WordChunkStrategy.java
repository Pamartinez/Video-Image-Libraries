package com.samsung.android.sdk.scs.ai.extension.lts;

import android.util.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class WordChunkStrategy implements TextChunkStrategy {
    private static final double MARGIN_RATIO = 0.05d;
    private static final String TAG = "WordChunkStrategy";
    private static final String WORD_SPLIT_PATTERN = "\\s+";

    public boolean canHandle(String str, int i2, int i7) {
        return true;
    }

    public List<String> chunkText(String str, int i2, int i7) {
        Log.d(TAG, "Using word chunking strategy (fallback)");
        return TextChunkUtils.splitWordsIntoChunks(str.trim().split(WORD_SPLIT_PATTERN), (int) ((((double) i7) * MARGIN_RATIO) + ((double) i2)));
    }

    public String getStrategyName() {
        return "Word";
    }
}
