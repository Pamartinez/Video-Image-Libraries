package com.samsung.android.sdk.scs.ai.extension.lts;

import android.util.Log;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ParagraphChunkStrategy implements TextChunkStrategy {
    private static final double MARGIN_RATIO = 0.3d;
    private static final String PARAGRAPH_SPLIT_PATTERN = "(?m)^$.*?^";
    private static final String TAG = "ParagraphChunkStrategy";

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$canHandle$0(int i2, String str) {
        if (str.length() > i2) {
            return true;
        }
        return false;
    }

    private String[] splitByParagraph(String str) {
        return Pattern.compile(PARAGRAPH_SPLIT_PATTERN).split(str);
    }

    public boolean canHandle(String str, int i2, int i7) {
        return Arrays.stream(splitByParagraph(str)).noneMatch(new a((int) ((((double) i7) * MARGIN_RATIO) + ((double) i2)), 1));
    }

    public List<String> chunkText(String str, int i2, int i7) {
        Log.d(TAG, "Using paragraph chunking strategy");
        return TextChunkUtils.splitWordsIntoChunks(splitByParagraph(str), (int) ((((double) i7) * MARGIN_RATIO) + ((double) i2)));
    }

    public String getStrategyName() {
        return "Paragraph";
    }
}
