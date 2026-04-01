package com.samsung.android.gallery.module.search.recommendation;

import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class QuerySuggesterFactory {
    public static IQuerySuggester create() {
        if (!Features.isEnabled(Features.SUPPORT_DYNAMIC_SEARCH_SUGGESTION)) {
            return BasicQuerySuggester.getInstance();
        }
        if (Features.isEnabled(Features.SUPPORT_DYNAMIC_SEARCH_SUGGESTION_WITH_DIRECT)) {
            return SCSQuerySuggesterWithDirect.getInstance();
        }
        return SCSQuerySuggesterWithSDK.getInstance();
    }
}
