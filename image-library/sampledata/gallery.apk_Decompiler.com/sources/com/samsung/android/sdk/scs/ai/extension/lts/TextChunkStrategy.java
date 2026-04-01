package com.samsung.android.sdk.scs.ai.extension.lts;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface TextChunkStrategy {
    boolean canHandle(String str, int i2, int i7);

    List<String> chunkText(String str, int i2, int i7);

    String getStrategyName();
}
