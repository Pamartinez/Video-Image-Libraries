package com.samsung.android.gallery.database.dal.cmh.helper;

import A.a;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.TextExtractionApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.cmh.table.CmhTextExtractionView;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhTextExtractionImpl extends BaseImpl implements TextExtractionApi {
    private static final Uri CMH_FILES_TABLE_URI = Uri.parse("content://com.samsung.cmh/files");

    public CmhTextExtractionImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public int getTextExtractionDetectionType(long j2) {
        Cursor cursor;
        if (!this.mParams.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION) {
            return -1;
        }
        CmhTextExtractionView cmhTextExtractionView = new CmhTextExtractionView(this.mParams);
        cmhTextExtractionView.filterSecMediaId(j2);
        try {
            cursor = this.mQueryExecutor.getCursor(cmhTextExtractionView.buildSelectQuery(), "getIsLiveTextCursor");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("__is_live_text");
                    if (columnIndex > -1) {
                        int i2 = cursor.getInt(columnIndex);
                        cursor.close();
                        return i2;
                    }
                    Log.e(this.TAG, "unable to get detection type, index -1");
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to get detection type, e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return -1;
        throw th;
    }

    public String tag() {
        return "CmhTextExtractionImpl";
    }

    public int updateTextExtractionDetectionType(long j2, int i2) {
        try {
            if (!this.mParams.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION) {
                return -1;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_liveText", Integer.valueOf(i2));
            return getContentResolver().update(CMH_FILES_TABLE_URI, contentValues, "sec_media_id=?", new String[]{String.valueOf(j2)});
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to update detection type, e="), this.TAG);
            return -1;
        }
    }
}
