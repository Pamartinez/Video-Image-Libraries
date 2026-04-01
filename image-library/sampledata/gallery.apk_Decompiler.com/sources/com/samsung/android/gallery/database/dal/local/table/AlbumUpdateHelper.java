package com.samsung.android.gallery.database.dal.local.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumUpdateHelper {
    private final SQLiteDatabase mDatabase;

    public AlbumUpdateHelper(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    private ContentValues getAlbumCoverUpdateContentValues(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        if (str != null) {
            contentValues.put("__absPath", getSdRoPath(str));
        }
        if (str2 != null) {
            contentValues.put("default_cover_path", getSdRoPath(str2));
        }
        if (str3 != null) {
            contentValues.put("cover_path", getSdRoPath(str3));
        }
        return contentValues;
    }

    private Cursor getAlbumCursor() {
        return this.mDatabase.query("album", new String[]{"__absPath", "__bucketID"}, "__absPath is not null", (String[]) null, (String) null, (String) null, (String) null);
    }

    private Cursor getAlbumPathCursor() {
        return this.mDatabase.query("album", new String[]{"__absPath", "__bucketID", "default_cover_path", "cover_path"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
    }

    private ContentValues getAlbumUpdateContentValues(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__volumeName", FileUtils.getVolumeName(str));
        return contentValues;
    }

    private String getSdRoPath(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/mnt/media_rw/")) {
            return str;
        }
        return str.replaceFirst("/mnt/media_rw/", "/storage/");
    }

    private boolean isRwPath(String str) {
        if (str == null || !str.startsWith("/mnt/media_rw/")) {
            return false;
        }
        return true;
    }

    private int updateAlbum(Cursor cursor) {
        String string = cursor.getString(0);
        return this.mDatabase.update("album", getAlbumUpdateContentValues(string), C0086a.i(cursor.getInt(1), "__bucketID = "), (String[]) null);
    }

    private int updateAlbumPath(Cursor cursor) {
        String string = cursor.getString(0);
        String string2 = cursor.getString(2);
        String string3 = cursor.getString(3);
        String str = "__bucketID = " + cursor.getInt(1);
        if (isRwPath(string) || isRwPath(string2) || isRwPath(string3)) {
            return this.mDatabase.update("album", getAlbumCoverUpdateContentValues(string, string2, string3), str, (String[]) null);
        }
        return 0;
    }

    public int[] updateAlbumPathData() {
        Cursor albumPathCursor;
        int[] iArr = new int[2];
        try {
            albumPathCursor = getAlbumPathCursor();
            if (albumPathCursor != null) {
                if (albumPathCursor.moveToFirst()) {
                    iArr[0] = albumPathCursor.getCount();
                    do {
                        iArr[1] = iArr[1] + updateAlbumPath(albumPathCursor);
                    } while (albumPathCursor.moveToNext());
                }
            }
            if (albumPathCursor != null) {
                albumPathCursor.close();
            }
        } catch (Exception e) {
            Log.e("AlbumUpdateHelper", e.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("AlbumUpdateHelper", "updateAlbumPathData [" + iArr[0] + "][" + iArr[1] + "]");
        return iArr;
        throw th;
    }

    public int[] updateAlbumVolumeData() {
        Cursor albumCursor;
        int[] iArr = new int[2];
        try {
            albumCursor = getAlbumCursor();
            if (albumCursor != null) {
                if (albumCursor.moveToFirst()) {
                    iArr[0] = albumCursor.getCount();
                    do {
                        iArr[1] = iArr[1] + updateAlbum(albumCursor);
                    } while (albumCursor.moveToNext());
                }
            }
            if (albumCursor != null) {
                albumCursor.close();
            }
        } catch (Exception e) {
            Log.e("AlbumUpdateHelper", e.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("AlbumUpdateHelper", "updateAlbumData [" + iArr[0] + "][" + iArr[1] + "]");
        return iArr;
        throw th;
    }
}
