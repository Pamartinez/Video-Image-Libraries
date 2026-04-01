package com.samsung.android.gallery.module.search.recommendation;

import android.content.Context;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.suggestion.Keyword;
import com.samsung.android.sdk.scs.ai.suggestion.KeywordSuggestionType;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SCSQuerySuggesterWithSDK extends AbsSCSQuerySuggester {
    private static volatile SCSQuerySuggesterWithSDK sInstance;

    public static SCSQuerySuggesterWithSDK getInstance() {
        if (sInstance == null) {
            synchronized (SCSQuerySuggesterWithSDK.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SCSQuerySuggesterWithSDK();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public boolean isSuggestionFeatureEnabled(Context context) {
        int checkFeature = Feature.checkFeature(context, Feature.FEATURE_SUGGESTION_SUGGEST_KEYWORD);
        String str = this.TAG;
        Log.i(str, "isSuggestionFeatureEnabled:[" + checkFeature + "]");
        if (checkFeature == 0) {
            return true;
        }
        return false;
    }

    public void loadCandidateSuggestionsInternal(Context context, final int i2) {
        final boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
        AiServices.getKeywordSuggester(context).suggest(KeywordSuggestionType.MEDIA, i2, isEnabled).addOnCompleteListener(new OnCompleteListener<List<Keyword>>() {
            private void resultLog(int i2, boolean z) {
                String str = SCSQuerySuggesterWithSDK.this.TAG;
                Log.s(str, " [Candidate Count]: " + SCSQuerySuggesterWithSDK.this.mCandidateSuggestion.size() + " [Needed Count]: " + i2 + " [Location Granted]: " + z);
            }

            public void onComplete(Task<List<Keyword>> task) {
                if (task.isSuccessful()) {
                    Log.s(SCSQuerySuggesterWithSDK.this.TAG, "Task completed successfully");
                    List<Keyword> result = task.getResult();
                    if (result == null || result.size() == 0) {
                        Log.sw(SCSQuerySuggesterWithSDK.this.TAG, "result is null");
                        return;
                    }
                    for (Keyword keyword : result) {
                        SCSQuerySuggesterWithSDK.this.mCandidateSuggestion.add(SCSQuerySuggesterWithSDK.this.buildSuggestionItem(keyword.getQueryString(), keyword.getTagType()));
                    }
                    resultLog(i2, isEnabled);
                    SCSQuerySuggesterWithSDK.this.fillSuggestionQueue(i2);
                    return;
                }
                String str = SCSQuerySuggesterWithSDK.this.TAG;
                Log.sw(str, "Task failed with an exception : " + i2 + ArcCommonLog.TAG_COMMA + isEnabled);
                task.getException().printStackTrace();
            }
        });
    }
}
