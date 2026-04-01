package com.samsung.android.gallery.module.search.result;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.mp.helper.SearchResultApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ExpressionResult extends SearchResult {
    public ExpressionResult(Context context, String str) {
        super(context, str);
    }

    public IntelligentSearchIndex.TagType getIndexingTagType() {
        return IntelligentSearchIndex.TagType.EXPRESSIONS;
    }

    public String getSelection() {
        return new SearchResultApi().getExpressionSelection();
    }

    public String[] getSelectionArgs(long j2) {
        return new String[]{String.valueOf(j2)};
    }

    public Uri getUri() {
        return new SearchResultApi().getFacesUri();
    }

    public int removeTo(MediaItem mediaItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(new SearchResultApi().getColumnForExpression(this.mSubCategory), 0);
        return this.mAppContext.getContentResolver().update(getUri(), contentValues, getSelection(), getSelectionArgs(mediaItem.getFileId()));
    }
}
