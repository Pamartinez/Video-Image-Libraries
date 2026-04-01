package com.samsung.android.gallery.module.search.result;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.mp.helper.SearchResultApi;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class OthersResult extends SearchResult {
    public OthersResult(Context context, String str) {
        super(context, str);
    }

    public IntelligentSearchIndex.TagType getIndexingTagType() {
        return IntelligentSearchIndex.TagType.SCENE;
    }

    public String getSelection() {
        return new SearchResultApi().getOthersSelection();
    }

    public String[] getSelectionArgs(long j2) {
        return new String[]{String.valueOf(j2), this.mSubCategory};
    }

    public Uri getUri() {
        return new SearchResultApi().getOthersUri();
    }
}
