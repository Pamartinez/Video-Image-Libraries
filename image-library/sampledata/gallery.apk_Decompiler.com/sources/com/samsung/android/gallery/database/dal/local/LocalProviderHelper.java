package com.samsung.android.gallery.database.dal.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalProviderHelper {
    private final ContentResolver mResolver;

    public LocalProviderHelper(ContentResolver contentResolver) {
        this.mResolver = contentResolver;
    }

    private boolean deleteFile(String str) {
        try {
            return FileUtils.delete(str);
        } catch (SecurityException e) {
            Log.e("LocalProviderHelper", "Delete failed. " + e.getMessage());
            return false;
        }
    }

    private boolean deleteShareRecord(String str) {
        if (this.mResolver.delete(LocalDatabase.SHARE_URI, "__absPath =? ", new String[]{str}) > 0) {
            return true;
        }
        return false;
    }

    public void deleteShareFile(String str) {
        if (deleteFile(str) && deleteShareRecord(str)) {
            Log.d("LocalProviderHelper", "File delete done.");
        }
    }

    public Cursor getExpiredConvertedFileCursor(long j2) {
        return this.mResolver.query(LocalDatabase.SHARE_URI, new String[]{"__absPath"}, "date_added <?", new String[]{String.valueOf(j2)}, (String) null);
    }

    public void insertConvertedFile(ContentValues contentValues) {
        this.mResolver.insert(LocalDatabase.SHARE_URI, contentValues);
    }
}
