package com.samsung.android.sdk.spage.card;

import com.samsung.android.sdk.spage.card.base.JsonFieldData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchControllerData extends JsonFieldData<SearchControllerData> {
    private static final String KEY_SEARCH_HINT_TEXT = "searchHintText";

    public SearchControllerData setSearchHintText(String str) {
        return (SearchControllerData) put(KEY_SEARCH_HINT_TEXT, str);
    }
}
