package com.samsung.android.gallery.database.dal.local;

import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.table.LocalAlbumsTable;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalAlbumsApi {
    private static final Uri ALBUM_URI;
    protected boolean EXCLUDE_NAME_MERGED;
    private boolean IS_GTE_Q;
    QueryParams mParams;
    private QueryExecutor mQueryExecutor;

    static {
        Uri uri;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            uri = LocalDatabase.MX_ALBUM_URI;
        } else {
            uri = LocalDatabase.ALBUM_URI;
        }
        ALBUM_URI = uri;
    }

    public LocalAlbumsApi() {
        this.EXCLUDE_NAME_MERGED = PreferenceFeatures.OneUi5x.MX_ALBUMS && !PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames);
        QueryParams queryParams = new QueryParams();
        this.mParams = queryParams;
        init(queryParams.getOsVersion(), new QueryExecutor());
    }

    private String getBaseSelectionString(LocalAlbumsTable localAlbumsTable, boolean z, boolean z3, boolean z7) {
        String str = null;
        if (this.IS_GTE_Q) {
            if (!z) {
                str = z3 ? localAlbumsTable.filterForHidden() : localAlbumsTable.getWhereForAlbum(false);
            } else if (!z3) {
                str = localAlbumsTable.filterVolumeName(false);
            }
        } else if (!z) {
            str = localAlbumsTable.filterForHidden();
        }
        if (!z7 && !this.EXCLUDE_NAME_MERGED) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            return localAlbumsTable.filterForExcludeMergeAlbum();
        }
        StringBuilder t = C0212a.t(str, " AND ");
        t.append(localAlbumsTable.filterForExcludeMergeAlbum());
        return t.toString();
    }

    private void init(int i2, QueryExecutor queryExecutor) {
        boolean z;
        this.mQueryExecutor = queryExecutor;
        if (i2 >= 29) {
            z = true;
        } else {
            z = false;
        }
        this.IS_GTE_Q = z;
    }

    public int createData(ContentValues contentValues) {
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        if (contentResolver == null || contentResolver.insert(ALBUM_URI, contentValues) == null) {
            return 0;
        }
        return 1;
    }

    public Cursor getAlbumAutoCompleteCursor() {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        localAlbumsTable.resetProjectionForAutoComplete();
        String[] strArr = (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]);
        if (!this.IS_GTE_Q) {
            return this.mQueryExecutor.getCursor(ALBUM_URI, strArr, localAlbumsTable.filterForHidden(), (String[]) null, (String) null, "getAlbumCursor");
        }
        String whereForAlbum = localAlbumsTable.getWhereForAlbum(false);
        String filterFullFolder = localAlbumsTable.filterFullFolder();
        if (filterFullFolder != null) {
            whereForAlbum = C0212a.n("(", whereForAlbum, ") AND ", filterFullFolder);
        }
        String exceptAutoAlbum = localAlbumsTable.exceptAutoAlbum();
        if (exceptAutoAlbum != null) {
            whereForAlbum = C0212a.B(whereForAlbum, " AND ", exceptAutoAlbum);
        }
        String exceptVirtualAlbum = localAlbumsTable.exceptVirtualAlbum();
        if (exceptAutoAlbum != null) {
            whereForAlbum = C0212a.B(whereForAlbum, " AND ", exceptVirtualAlbum);
        }
        String str = whereForAlbum;
        if (Logger.QUERY) {
            Log.d("LocalAlbumsApi", "getAlbumCursor : query : select " + Arrays.toString(strArr) + " from album where " + str);
        }
        return this.mQueryExecutor.getCursor(ALBUM_URI, strArr, str, (String[]) null, (String) null, "getAlbumCursor");
    }

    public Cursor getAlbumCursor(boolean z, boolean z3, boolean z7, boolean z9) {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        String[] strArr = (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]);
        String baseSelectionString = getBaseSelectionString(localAlbumsTable, z, z3, z9);
        if (z7) {
            String filterTopAlbumsData = localAlbumsTable.filterTopAlbumsData();
            if (!TextUtils.isEmpty(filterTopAlbumsData)) {
                baseSelectionString = baseSelectionString == null ? filterTopAlbumsData : C0212a.n("(", baseSelectionString, ") AND ", filterTopAlbumsData);
            }
        }
        String str = baseSelectionString;
        if (Logger.QUERY) {
            Log.d("LocalAlbumsApi", "getAlbumCursor : query : select " + Arrays.toString(strArr) + " from album where " + str);
        }
        return this.mQueryExecutor.getCursor(ALBUM_URI, strArr, str, (String[]) null, (String) null, "getAlbumCursor");
    }

    public Cursor getAlbumFolderCursor(String str) {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        String selectionForAlbum = localAlbumsTable.getSelectionForAlbum(FileUtils.getBucketId(str));
        return this.mQueryExecutor.getCursor(ALBUM_URI, (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]), selectionForAlbum, (String[]) null, (String) null, "getAlbumFolderCursor");
    }

    public int getCountAlbumFolder(String str) {
        Throwable th;
        Cursor cursor = this.mQueryExecutor.getCursor(ALBUM_URI, new String[]{"count(*)"}, str, (String[]) null, (String) null, (String) null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    cursor.close();
                    return i2;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return 0;
        throw th;
    }

    public ContentProviderOperation getDeleteOperation(List<Integer> list) {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(ALBUM_URI);
        newDelete.withSelection(localAlbumsTable.getSelectionForBucketIds(list), (String[]) null);
        return newDelete.build();
    }

    public String getFilterVolumeName() {
        return new LocalAlbumsTable(this.mParams).filterVolumeName();
    }

    public Cursor getFolderCursor(int i2) {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        return this.mQueryExecutor.getCursor(ALBUM_URI, (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]), localAlbumsTable.getSelectionForAlbum(i2), (String[]) null, (String) null, "getFolderCursor");
    }

    public Cursor getHiddenAlbumCursor() {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        String[] strArr = (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]);
        String selectionForHidden = localAlbumsTable.selectionForHidden();
        if (this.mParams.getAlbumIdCount() > 0) {
            StringBuilder t = C0212a.t(selectionForHidden, " AND ");
            t.append(localAlbumsTable.getSelectionForBucketIds(this.mParams.getAlbumIdList()));
            selectionForHidden = t.toString();
        }
        return this.mQueryExecutor.getCursor(ALBUM_URI, strArr, selectionForHidden, (String[]) null, (String) null, "getHiddenAlbumCursor");
    }

    public ContentProviderOperation getInsertOperation(ContentValues contentValues) {
        ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ALBUM_URI);
        newInsert.withValues(contentValues);
        return newInsert.build();
    }

    public Uri getTableUri() {
        return ALBUM_URI;
    }

    public ContentProviderOperation getUpdateOperation(ContentValues contentValues, List<Integer> list) {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        ContentProviderOperation.Builder newUpdate = ContentProviderOperation.newUpdate(ALBUM_URI);
        newUpdate.withSelection(localAlbumsTable.getSelectionForBucketIds(list), (String[]) null);
        newUpdate.withValues(contentValues);
        return newUpdate.build();
    }

    public Cursor getValidFolderCursor() {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        return this.mQueryExecutor.getCursor(ALBUM_URI, (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]), localAlbumsTable.getSelectionForValidFolder(), (String[]) null, (String) null, "getValidFolderCursor");
    }

    public String getWatchParam() {
        return "watch";
    }

    public int updateData(List<Integer> list, ContentValues contentValues) {
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        try {
            if (list.isEmpty()) {
                return 1;
            }
            return this.mQueryExecutor.getContentResolver().update(ALBUM_URI, contentValues, localAlbumsTable.getSelectionForBucketIds(list), (String[]) null);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateEmptyFolderName(int i2, ContentValues contentValues) {
        try {
            return this.mQueryExecutor.getContentResolver().update(ALBUM_URI, contentValues, new LocalAlbumsTable(this.mParams).getSelectionForAlbum(i2), (String[]) null);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateFolderData(List<Integer> list, ContentValues contentValues) {
        try {
            return this.mQueryExecutor.getContentResolver().update(ALBUM_URI, contentValues, new LocalAlbumsTable(this.mParams).getSelectionForFolderIds(list), (String[]) null);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateFolderName(int i2, ContentValues contentValues) {
        try {
            return this.mQueryExecutor.getContentResolver().update(ALBUM_URI, contentValues, new LocalAlbumsTable(this.mParams).getSelectionForFolder(i2), (String[]) null);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public LocalAlbumsApi(QueryParams queryParams) {
        this.EXCLUDE_NAME_MERGED = PreferenceFeatures.OneUi5x.MX_ALBUMS && !PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames);
        this.mParams = queryParams;
        init(queryParams.getOsVersion(), new QueryExecutor());
    }

    public Cursor getAlbumCursor(boolean z, List<Integer> list) {
        String str;
        LocalAlbumsTable localAlbumsTable = new LocalAlbumsTable(this.mParams);
        String[] strArr = (String[]) localAlbumsTable.getProjectionArray().toArray(new String[0]);
        String filterVolumeName = z ? localAlbumsTable.filterVolumeName(true) : localAlbumsTable.getWhereForAlbum(true);
        String filterFolderData = localAlbumsTable.filterFolderData();
        if (filterVolumeName != null) {
            StringBuilder k = j.k("(", filterVolumeName, ") AND ");
            k.append(localAlbumsTable.getSelectionForBucketIds(list));
            str = k.toString();
        } else {
            str = localAlbumsTable.getSelectionForBucketIds(list);
        }
        if (filterFolderData != null) {
            str = C0212a.B(str, " AND ", filterFolderData);
        }
        return this.mQueryExecutor.getCursor(ALBUM_URI, strArr, str, (String[]) null, (String) null, "getAlbumCursor");
    }
}
