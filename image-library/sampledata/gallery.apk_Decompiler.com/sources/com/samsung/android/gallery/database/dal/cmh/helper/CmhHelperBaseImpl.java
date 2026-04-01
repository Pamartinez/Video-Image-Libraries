package com.samsung.android.gallery.database.dal.cmh.helper;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.impl.BaseImpl;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class CmhHelperBaseImpl extends BaseImpl {
    protected static final Uri CMH_AUTHORITY_URI = CmhUri.getAuthority();
    protected static final Uri CMH_STORY_HIDE_RULE = Uri.parse("content://com.samsung.cmh/hide_rule");
    protected static final Uri CMH_STORY_TABLE_URI = CmhUri.getStory();

    public CmhHelperBaseImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) {
        try {
            ContentResolver contentResolver = getContentResolver();
            String authority = CMH_AUTHORITY_URI.getAuthority();
            Objects.requireNonNull(authority);
            return contentResolver.applyBatch(authority, arrayList);
        } catch (Exception e) {
            String str = this.TAG;
            Log.d(str, "applyBatch failed. e=" + e.getMessage());
            return null;
        }
    }

    public int delete(String str, String[] strArr) {
        try {
            return getContentResolver().delete(CMH_STORY_TABLE_URI, str, strArr);
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.d(str2, "cmhDelete failed. e=" + e.getMessage());
            return -1;
        }
    }

    public Uri insert(ContentValues contentValues) {
        try {
            return getContentResolver().insert(CMH_STORY_TABLE_URI, contentValues);
        } catch (Exception e) {
            String str = this.TAG;
            Log.d(str, "cmhInsert failed. e=" + e.getMessage());
            return null;
        }
    }

    public int update(ContentValues contentValues, String str, String[] strArr) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int update = getContentResolver().update(CMH_STORY_TABLE_URI, contentValues, str, strArr);
            String str2 = this.TAG;
            Log.d(str2, "cmhUpdate" + Logger.vt(Integer.valueOf(update), Long.valueOf(currentTimeMillis)));
            return update;
        } catch (Exception e) {
            String str3 = this.TAG;
            Log.d(str3, "cmhUpdate failed. e=" + e.getMessage());
            return -1;
        }
    }
}
