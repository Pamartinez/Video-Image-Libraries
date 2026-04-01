package com.samsung.android.gallery.module.search.engine;

import android.content.Context;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchEngineFactory {
    public static BaseSearchEngine create(Context context) {
        if (!Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            return new SearchEngine(context);
        }
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return new IntelligentExpandedSearchEngine(context, IntelligentSearch.getInstance());
        }
        return new IntelligentSearchEngine(context, IntelligentSearch.getInstance());
    }
}
