package com.samsung.android.gallery.database.dal.mp.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.SimilarContactTable;
import com.samsung.android.gallery.support.providers.CmhUri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimilarContactApi extends BaseImpl {
    private static final Uri CONTACTS_RECOMMENDATION_URI = Uri.parse("content://com.samsung.cmh/contacts_recommendation");

    public SimilarContactApi() {
        super(new QueryParams());
    }

    public void disableContactRecommendation(long j2, long j3) {
        String[] strArr = {String.valueOf(j2), String.valueOf(j3)};
        ContentValues contentValues = new ContentValues();
        contentValues.put("face_group_id", Long.valueOf(-j2));
        this.mQueryExecutor.getContentResolver().update(CONTACTS_RECOMMENDATION_URI, contentValues, "face_group_id = ? AND contacts_id = ? ", strArr);
        getContentResolver().update(CmhUri.getContactsRecommendation(), contentValues, "face_group_id = ? AND contacts_id = ? ", strArr);
    }

    public Cursor getSimilarContactTableCursor(String str) {
        SimilarContactTable similarContactTable = new SimilarContactTable(this.mParams);
        similarContactTable.filterGroupIds(str);
        return getCursor(similarContactTable.buildSelectQuery(), "SIMILAR_CONTACT");
    }

    public String tag() {
        return "SimilarContactApi";
    }
}
