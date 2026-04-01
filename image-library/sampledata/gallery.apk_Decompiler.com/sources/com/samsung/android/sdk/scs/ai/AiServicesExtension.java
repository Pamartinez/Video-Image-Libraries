package com.samsung.android.sdk.scs.ai;

import android.content.Context;
import com.samsung.android.sdk.scs.ai.extension.lts.ILongTextSummarizer;
import com.samsung.android.sdk.scs.ai.extension.lts.LongTextSummarizer;
import com.samsung.android.sdk.scs.ai.language.Summarizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiServicesExtension {
    private AiServicesExtension() {
    }

    public static ILongTextSummarizer getLongTextSummarizer(Context context, Summarizer summarizer) {
        return new LongTextSummarizer(context, summarizer);
    }
}
