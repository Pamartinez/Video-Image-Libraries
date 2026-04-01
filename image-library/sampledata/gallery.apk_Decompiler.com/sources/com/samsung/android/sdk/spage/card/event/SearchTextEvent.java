package com.samsung.android.sdk.spage.card.event;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchTextEvent extends Event {
    public static final String EVENT_SEARCH_REQUEST = "SPAGE_ON_SEARCH_REQUEST";
    private static final String EXTRA_SEARCH_TEXT = "searchText";
    public static final String TYPE = "SearchTextEvent";
    private String mSearchText;

    public SearchTextEvent(String str, Bundle bundle) {
        super(str, bundle);
    }

    public String getSearchText() {
        return this.mSearchText;
    }

    public void initialize(Bundle bundle) {
        this.mSearchText = bundle.getString(EXTRA_SEARCH_TEXT, "");
    }
}
