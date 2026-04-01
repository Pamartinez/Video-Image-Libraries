package com.samsung.android.gallery.module.trash.abstraction;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITrashProvider {
    int deleteAllTrash();

    int deleteTrash(String str, String[] strArr);

    int deleteTrash(String str, String[] strArr, boolean z);

    Cursor getExpiredTrashCursor(String str);

    int[] getTrashCount(boolean z);

    Cursor getTrashCursor(long j2);

    Cursor getTrashCursor(String str);

    Cursor getTrashCursor(String str, String[] strArr);

    Cursor getTrashCursor(boolean z);

    Object[] getTrashInfo();

    int getTrashTotalCount();

    Uri getTrashUri();

    Uri insertTrash(ContentValues contentValues);

    boolean isSameOriginFileHashExists(String str);

    int isSameRecordExist(String str);

    Cursor loadTrashIdsCursor(boolean z);

    Cursor loadTrashRealRatioCursor(boolean z);

    int updateTrash(ContentValues contentValues, String str, String[] strArr) {
        return updateTrash(getTrashUri(), contentValues, str, strArr);
    }

    int updateTrash(Uri uri, ContentValues contentValues, String str, String[] strArr);
}
