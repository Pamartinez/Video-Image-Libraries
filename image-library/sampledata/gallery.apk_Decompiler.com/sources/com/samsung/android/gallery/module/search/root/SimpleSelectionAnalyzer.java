package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.database.dbtype.SearchFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleSelectionAnalyzer implements ISelectionAnalyzer {
    private final SearchFilter mSearchFilter;

    public SimpleSelectionAnalyzer(SearchFilter searchFilter) {
        this.mSearchFilter = searchFilter;
    }

    public String getSelection() {
        return this.mSearchFilter.getRawKeyword();
    }

    public String[] getSelectionArgs() {
        return null;
    }
}
