package com.samsung.android.sdk.scs.ai.suggestion;

import android.content.Context;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeywordSuggester {
    private static final String TAG = "ScsApi@KeywordSuggester";
    private Context mContext;
    private SuggestionProviderExecutor mProviderExecutor;

    public KeywordSuggester(Context context) {
        this.mContext = context;
        this.mProviderExecutor = new SuggestionProviderExecutor(context);
    }

    public Task<List<Keyword>> suggest(KeywordSuggestionType keywordSuggestionType, int i2, boolean z) {
        KeywordSuggestionRunnable keywordSuggestionRunnable = new KeywordSuggestionRunnable(this.mContext, keywordSuggestionType, i2, z);
        this.mProviderExecutor.execute(keywordSuggestionRunnable);
        return keywordSuggestionRunnable.getTask();
    }
}
