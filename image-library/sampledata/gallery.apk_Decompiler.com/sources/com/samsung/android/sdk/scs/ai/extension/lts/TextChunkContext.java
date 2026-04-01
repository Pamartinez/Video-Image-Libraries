package com.samsung.android.sdk.scs.ai.extension.lts;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TextChunkContext {
    private static final String CHUNK = "Chunk";
    private static final String TAG = "TextChunkContext";
    private final List<TextChunkStrategy> mStrategies = Arrays.asList(new TextChunkStrategy[]{new ParagraphChunkStrategy(), new SentenceChunkStrategy(), new LineChunkStrategy(), new WordChunkStrategy()});

    public List<String> chunkText(String str, int i2, int i7) {
        if (str == null || str.isEmpty()) {
            return new ArrayList();
        }
        for (TextChunkStrategy next : this.mStrategies) {
            if (next.canHandle(str, i2, i7)) {
                Log.i(TAG, "Selected strategy: " + next.getStrategyName());
                List<String> chunkText = next.chunkText(str, i2, i7);
                TextChunkUtils.printLogs(CHUNK, chunkText);
                return chunkText;
            }
        }
        Log.w(TAG, "No strategy could handle the text, using fallback");
        return new ArrayList();
    }

    public List<String> chunkTextWithDefaultSize(String str) {
        return chunkText(str, 2950, 0);
    }
}
