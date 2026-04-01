package com.samsung.android.gallery.database.dal.local.table;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HiddenShareHelper {
    private static final Uri SHARE_TABLE_URI = LocalDatabase.SHARE_URI;

    public static int delete(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                Log.e("HiddenShareHelper", "invalid path");
                return -1;
            }
            long currentTimeMillis = System.currentTimeMillis();
            int delete = getResolver().delete(LocalDatabase.SHARE_URI, "__absPath =?", new String[]{str});
            Log.d("HiddenShareHelper", "delete from local db [" + (System.currentTimeMillis() - currentTimeMillis) + "]", Integer.valueOf(delete));
            return delete;
        } catch (Exception e) {
            Log.e("HiddenShareHelper", e.getMessage());
            return -1;
        }
    }

    private static ContentResolver getResolver() {
        return AppResources.getAppContext().getContentResolver();
    }

    public static Uri insertToShareTable(String str, String str2, String str3) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("__absPath", str);
            contentValues.put("date_added", Long.valueOf(currentTimeMillis));
            contentValues.put("meta_data", str2);
            contentValues.put("owner", str3);
            Uri insert = getResolver().insert(SHARE_TABLE_URI, contentValues);
            Log.d("HiddenShareHelper", "insert to local db [" + (System.currentTimeMillis() - currentTimeMillis) + "]");
            return insert;
        } catch (Exception e) {
            Log.e("HiddenShareHelper", e.getMessage());
            return null;
        }
    }

    public static Cursor queryToShareTable(String[] strArr, String str, String[] strArr2, String str2) {
        ThreadUtil.assertBgThread("queryToShareTable should run on bg thread");
        try {
            return getResolver().query(SHARE_TABLE_URI, strArr, str, strArr2, str2);
        } catch (Exception e) {
            Exception exc = e;
            Log.e("HiddenShareHelper", exc.getMessage());
            exc.printStackTrace();
            return null;
        }
    }
}
