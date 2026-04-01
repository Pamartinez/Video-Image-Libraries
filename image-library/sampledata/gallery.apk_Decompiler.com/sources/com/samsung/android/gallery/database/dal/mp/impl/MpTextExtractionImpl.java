package com.samsung.android.gallery.database.dal.mp.impl;

import A.a;
import android.content.ContentValues;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.TextExtractionApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpTextExtractionImpl extends BaseImpl implements TextExtractionApi {
    public MpTextExtractionImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public int getTextExtractionDetectionType(long j2) {
        Cursor query;
        Throwable th;
        if (!this.mParams.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION) {
            return -1;
        }
        try {
            query = this.mQueryExecutor.query(MpAnalyzeInfoUtil.URI, new String[]{"is_live_text"}, a.e(j2, "sec_media_id=", " AND is_live_text is not null"), (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("is_live_text");
                    if (columnIndex > -1) {
                        int i2 = query.getInt(columnIndex);
                        query.close();
                        return i2;
                    }
                    Log.e(this.TAG, "unable to get detection type, index -1");
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            Exception exc = e;
            a.s(exc, new StringBuilder("unable to get detection type, e="), this.TAG);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return -1;
        throw th;
    }

    public String tag() {
        return "MpTextExtractionImpl";
    }

    public int updateTextExtractionDetectionType(long j2, int i2) {
        try {
            if (!this.mParams.SUPPORT_TEXT_EXTRACTION_CMH_DETECTION) {
                return -1;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_live_text", Integer.valueOf(i2));
            return getContentResolver().update(MpAnalyzeInfoUtil.URI, contentValues, "sec_media_id=?", new String[]{String.valueOf(j2)});
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to update detection type, e="), this.TAG);
            return -1;
        }
    }
}
