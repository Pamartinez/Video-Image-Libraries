package com.samsung.android.gallery.module.search.autocomplete;

import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AutoCompleteDataLoaderFactory {
    public static AutoCompleteDataLoader create() {
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE)) {
            return new ScsAutoCompleteDataLoader();
        }
        return new MpAutoCompleteDataLoader();
    }
}
