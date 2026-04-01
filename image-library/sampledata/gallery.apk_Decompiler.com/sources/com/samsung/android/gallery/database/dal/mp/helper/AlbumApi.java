package com.samsung.android.gallery.database.dal.mp.helper;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumApi extends BaseImpl {
    private static final boolean UPDATE_GED_MP = (!SdkConfig.atLeast(SdkConfig.GED.R));

    public AlbumApi() {
        super(new QueryParams());
    }

    private int updateAlbumsDB(String str, ContentValues contentValues) {
        int i2;
        ContentResolver contentResolver;
        String l = C0212a.l("bucket_id=", str);
        if (!UPDATE_GED_MP || (contentResolver = this.mQueryExecutor.getContentResolver()) == null) {
            i2 = 0;
        } else {
            i2 = contentResolver.update(MediaStore.Files.getContentUri("external"), contentValues, l, (String[]) null);
        }
        return updateAlbumsDbForSec(contentValues, l) + i2;
    }

    public int getCountInBucket(int i2) {
        Cursor cursor;
        Throwable th;
        try {
            cursor = this.mQueryExecutor.getCursor(MediaUri.getInstance().getSecMediaUri(), new String[]{"count(*)"}, "bucket_id = ?", new String[]{String.valueOf(i2)}, (String) null, "getCountInBucket");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int i7 = cursor.getInt(0);
                    String str = this.TAG;
                    Log.d(str, "getCount " + i7);
                    cursor.close();
                    return i7;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (RuntimeException e) {
            Log.e(this.TAG, e.toString());
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return 0;
        throw th;
    }

    public String tag() {
        return "AlbumApi";
    }

    public int updateAlbumsDbForSec(ContentValues contentValues, String str) {
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        if (contentResolver != null) {
            return contentResolver.update(MediaUri.getInstance().getSecMediaUri(), contentValues, str, (String[]) null);
        }
        return 0;
    }

    public int updateAlbumsHideState(int i2, boolean z) {
        int i7;
        ContentValues contentValues = new ContentValues();
        if (z) {
            i7 = 1;
        } else {
            i7 = -1;
        }
        contentValues.put("is_hide", Integer.valueOf(i7));
        return updateAlbumsDB(String.valueOf(i2), contentValues);
    }
}
