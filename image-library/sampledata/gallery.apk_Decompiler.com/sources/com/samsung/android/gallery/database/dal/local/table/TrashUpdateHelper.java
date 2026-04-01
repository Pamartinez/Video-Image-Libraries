package com.samsung.android.gallery.database.dal.local.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TrashUpdateHelper {
    private final SQLiteDatabase mDatabase;

    public TrashUpdateHelper(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    private String getSdRoPath(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/mnt/media_rw/")) {
            return str;
        }
        return str.replaceFirst("/mnt/media_rw/", "/storage/");
    }

    private Cursor getTrashCursor() {
        return this.mDatabase.query("trash", new String[]{"__absPath", "__originPath", "__absID"}, "__volumeName != ? ", new String[]{FileUtils.EXTERNAL_STORAGE_DIR}, (String) null, (String) null, (String) null);
    }

    private ContentValues getTrashUpdateContentValues(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__absPath", getSdRoPath(str));
        contentValues.put("__originPath", getSdRoPath(str2));
        return contentValues;
    }

    private int updateTrash(Cursor cursor) {
        String string = cursor.getString(0);
        String string2 = cursor.getString(1);
        return this.mDatabase.update("trash", getTrashUpdateContentValues(string, string2), "__absID = " + cursor.getInt(2), (String[]) null);
    }

    public int[] updateTrashPathData() {
        Cursor trashCursor;
        int[] iArr = new int[2];
        try {
            trashCursor = getTrashCursor();
            if (trashCursor != null) {
                if (trashCursor.moveToFirst()) {
                    iArr[0] = trashCursor.getCount();
                    do {
                        iArr[1] = iArr[1] + updateTrash(trashCursor);
                    } while (trashCursor.moveToNext());
                }
            }
            if (trashCursor != null) {
                trashCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("TrashUpdateHelper", "updateTrashPathData [" + iArr[0] + "][" + iArr[1] + "]");
        return iArr;
        throw th;
    }

    public void updateTrashVolumeValid() {
        try {
            this.mDatabase.execSQL("UPDATE trash SET __volumeValid = 1, __deleteTime = CASE WHEN (__deleteTime + (__expiredPeriod *24*60*60*1000) - strftime('%s','now','localtime')*1000) / (24*60*60*1000) < 15 THEN strftime('%s','now','localtime')*1000 - (__expiredPeriod-15)*24*60*60*1000 ELSE __deleteTime END WHERE __volumeValid = 0 AND " + ("__volumeName COLLATE NOCASE IN (" + FileUtils.getMountedVolumeAndExternalNames() + ")"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
