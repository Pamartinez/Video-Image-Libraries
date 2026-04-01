package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MyQueryItemLoader {
    private static int getInt(Cursor cursor, int i2, int i7) {
        if (i2 < 0) {
            return i7;
        }
        return cursor.getInt(i2);
    }

    private static long getLong(Cursor cursor, int i2, long j2) {
        if (i2 < 0) {
            return j2;
        }
        return cursor.getLong(i2);
    }

    private static int getOrientationTag(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 90) {
            return 6;
        }
        if (i2 == 180) {
            return 3;
        }
        if (i2 != 270) {
            return 0;
        }
        return 8;
    }

    private static String getString(Cursor cursor, int i2, String str) {
        if (i2 < 0) {
            return str;
        }
        return cursor.getString(i2);
    }

    private static MediaItem loadCursor(Cursor cursor) {
        int i2;
        MyQueryColumn.initialize(cursor);
        final String string = getString(cursor, MyQueryColumn.FILTER_DATA.index, "");
        final int i7 = getInt(cursor, MyQueryColumn.CREATE_TYPE.index, 0);
        AnonymousClass1 r42 = new MediaItem() {
            public String toString() {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(super.toString());
                sb2.append("{");
                sb2.append(i7);
                sb2.append(NumericEnum.SEP);
                return C0212a.p(sb2, string, "}");
            }
        };
        r42.mAlbumID = getInt(cursor, MyQueryColumn.ID.index, 0);
        r42.mDisplayName = getString(cursor, MyQueryColumn.MY_QUERY_NAME.index, "");
        r42.mDateAdded = getLong(cursor, MyQueryColumn.DATE_ADDED.index, 0);
        r42.mDateModified = getLong(cursor, MyQueryColumn.DATE_MODIFIED.index, 0);
        r42.mCount = getInt(cursor, MyQueryColumn.COUNT.index, 0);
        r42.mFileID = getLong(cursor, MyQueryColumn.SEC_MEDIA_ID.index, 0);
        r42.mPath = getString(cursor, MyQueryColumn.DATA.index, (String) null);
        r42.mWidth = getInt(cursor, MyQueryColumn.WIDTH.index, 0);
        r42.mHeight = getInt(cursor, MyQueryColumn.HEIGHT.index, 0);
        int i8 = getInt(cursor, MyQueryColumn.ORIENTATION.index, 0);
        r42.mOrientation = i8;
        r42.mOrientationTag = getOrientationTag(i8);
        r42.mMediaType = MediaType.valueOf(getInt(cursor, MyQueryColumn.MEDIA_TYPE.index, MediaType.Unsupported.toInt()));
        if (r42.mDateTaken <= 0 && (i2 = MyQueryColumn.DATE_TAKEN.index) >= 0) {
            r42.mDateTaken = cursor.getLong(i2);
        }
        r42.mStorageType = StorageType.valueOf(getInt(cursor, MyQueryColumn.STORAGE_TYPE.index, 0));
        r42.mFileSize = getLong(cursor, MyQueryColumn.SIZE.index, 0);
        return r42;
    }

    public static MediaItem loadDefaultOnly(Cursor cursor) {
        try {
            return loadCursor(cursor);
        } catch (Exception e) {
            Log.e((CharSequence) "MyQueryItemLoader", "load failed", (Throwable) e);
            return null;
        }
    }
}
