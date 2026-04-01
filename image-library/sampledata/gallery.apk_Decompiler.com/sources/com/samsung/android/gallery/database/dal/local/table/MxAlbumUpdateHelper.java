package com.samsung.android.gallery.database.dal.local.table;

import A.a;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumUpdateHelper {
    private final SQLiteDatabase mDatabase;

    public MxAlbumUpdateHelper(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    private ContentValues getContentValue(Cursor cursor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__bucketID", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("__bucketID"))));
        contentValues.put("__Title", cursor.getString(cursor.getColumnIndex("__Title")));
        contentValues.put("__absPath", cursor.getString(cursor.getColumnIndex("__absPath")));
        contentValues.put("folder_id", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("folder_id"))));
        contentValues.put("folder_name", cursor.getString(cursor.getColumnIndex("folder_name")));
        contentValues.put("default_cover_path", cursor.getString(cursor.getColumnIndex("default_cover_path")));
        contentValues.put("cover_path", cursor.getString(cursor.getColumnIndex("cover_path")));
        contentValues.put("cover_rect", cursor.getString(cursor.getColumnIndex("cover_rect")));
        contentValues.put("album_count", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("album_count"))));
        contentValues.put("album_order", Long.valueOf(cursor.getLong(cursor.getColumnIndex("album_order"))));
        contentValues.put("__ishide", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("__ishide"))));
        contentValues.put("__sefFileType", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("__sefFileType"))));
        contentValues.put("__isDrm", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("__isDrm"))));
        contentValues.put("__dateModified", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("__dateModified"))));
        contentValues.put("__volumeName", cursor.getString(cursor.getColumnIndex("__volumeName")));
        contentValues.put("album_sync_status", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("album_sync_status"))));
        if (isFolder(cursor)) {
            contentValues.put("__albumType", Integer.valueOf(AlbumType.Folder.toInt()));
        }
        return contentValues;
    }

    private ContentValues getContentValuesVirtual(int i2, String str, AlbumType albumType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__bucketID", Integer.valueOf(i2));
        contentValues.put("__albumType", Integer.valueOf(albumType.toInt()));
        contentValues.put("__albumLevel", 1);
        contentValues.put("__Title", str);
        contentValues.put("__ishide", -1);
        contentValues.put("__sefFileType", -1);
        contentValues.put("__isDrm", 0);
        contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        return contentValues;
    }

    private long[] getMinMaxOrder() {
        Cursor query;
        Throwable th;
        try {
            query = this.mDatabase.query("mxalbum", new String[]{"min(album_order), max(album_order)"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    long[] jArr = {query.getLong(0), query.getLong(1)};
                    query.close();
                    return jArr;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            Log.e("MxAlbumUpdateHelper", e.toString());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private ContentValues getOrderContentValue(ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("album_order", contentValues.getAsLong("album_order"));
        return contentValues2;
    }

    private String getUpdateWhere(int i2) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i7 = 0; i7 < i2; i7++) {
            stringJoiner.add("?");
        }
        return "__bucketID IN (" + stringJoiner.toString() + ")";
    }

    private boolean hasScreenShots() {
        Cursor query;
        Throwable th;
        StorageInfo.BucketHolder buckets = StorageInfo.getDefault().buckets();
        String[] strArr = {"count(*)"};
        StringBuilder sb2 = new StringBuilder("__bucketID in (");
        sb2.append(buckets.screenRecordings);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        try {
            query = this.mDatabase.query("mxalbum", strArr, C0086a.l(sb2, buckets.screenShot, ")"), (String[]) null, (String) null, (String) null, (String) null);
            if (query != null) {
                if (query.moveToFirst() && query.getInt(0) > 0) {
                    query.close();
                    return true;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            Log.e("MxAlbumUpdateHelper", e.getMessage());
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return false;
        throw th;
    }

    private int insertVirtualAlbum() {
        int i2 = 0;
        try {
            if (isSupportScreenShotGroup()) {
                if (hasScreenShots()) {
                    this.mDatabase.insert("mxalbum", (String) null, getContentValuesVirtual(BucketUtils.VirtualBucketHolder.screenShots, "Screenshots", AlbumType.SystemMerged));
                    i2 = 1;
                    int updateScreenShotsFolderInfo = updateScreenShotsFolderInfo();
                    Log.d("MxAlbumUpdateHelper", "insertVirtualAlbum: screenshots update count = " + updateScreenShotsFolderInfo);
                }
            }
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            int i7 = BucketUtils.VirtualBucketHolder.recent;
            AlbumType albumType = AlbumType.Virtual;
            sQLiteDatabase.insert("mxalbum", (String) null, getContentValuesVirtual(i7, "Recents", albumType));
            int i8 = i2 + 1;
            try {
                this.mDatabase.insert("mxalbum", (String) null, getContentValuesVirtual(BucketUtils.VirtualBucketHolder.favorite, "Favorite", albumType));
                i2 += 2;
            } catch (Exception e) {
                e = e;
                i2 = i8;
                Log.e("MxAlbumUpdateHelper", e.getMessage());
                Log.i("MxAlbumUpdateHelper", "insertVirtualAlbum [" + i2 + "]");
                return i2;
            }
        } catch (Exception e7) {
            e = e7;
            Log.e("MxAlbumUpdateHelper", e.getMessage());
            Log.i("MxAlbumUpdateHelper", "insertVirtualAlbum [" + i2 + "]");
            return i2;
        }
        Log.i("MxAlbumUpdateHelper", "insertVirtualAlbum [" + i2 + "]");
        return i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069 A[Catch:{ Exception -> 0x0063 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int updateAlbumData() {
        /*
            r14 = this;
            java.lang.String r0 = "mxalbum"
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.String r3 = "updateAlbumData#start"
            java.lang.String r4 = "MxAlbumUpdateHelper"
            com.samsung.android.gallery.support.utils.Log.d(r4, r3)
            r3 = 0
            android.database.Cursor r5 = r14.getLegacyAlbumCursor()     // Catch:{ Exception -> 0x006d }
            if (r5 == 0) goto L_0x0065
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x0057 }
            if (r6 == 0) goto L_0x0065
            int r6 = r5.getCount()     // Catch:{ all -> 0x0057 }
            r7 = r3
        L_0x001f:
            android.content.ContentValues r8 = r14.getContentValue(r5)     // Catch:{ all -> 0x0055 }
            java.lang.String r9 = "__bucketID"
            java.lang.String r9 = r8.getAsString(r9)     // Catch:{ all -> 0x0055 }
            android.database.sqlite.SQLiteDatabase r10 = r14.mDatabase     // Catch:{ all -> 0x0055 }
            r11 = 0
            r12 = 4
            long r10 = r10.insertWithOnConflict(r0, r11, r8, r12)     // Catch:{ all -> 0x0055 }
            r12 = 0
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 <= 0) goto L_0x003a
            int r3 = r3 + 1
            goto L_0x004e
        L_0x003a:
            android.content.ContentValues r8 = r14.getOrderContentValue(r8)     // Catch:{ all -> 0x0055 }
            android.database.sqlite.SQLiteDatabase r10 = r14.mDatabase     // Catch:{ all -> 0x0055 }
            java.lang.String r11 = "__bucketID=?"
            java.lang.String[] r9 = new java.lang.String[]{r9}     // Catch:{ all -> 0x0055 }
            int r8 = r10.update(r0, r8, r11, r9)     // Catch:{ all -> 0x0055 }
            if (r8 <= 0) goto L_0x004e
            int r7 = r7 + 1
        L_0x004e:
            boolean r8 = r5.moveToNext()     // Catch:{ all -> 0x0055 }
            if (r8 != 0) goto L_0x001f
            goto L_0x0067
        L_0x0055:
            r14 = move-exception
            goto L_0x005a
        L_0x0057:
            r14 = move-exception
            r6 = r3
            r7 = r6
        L_0x005a:
            r5.close()     // Catch:{ all -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r0 = move-exception
            r14.addSuppressed(r0)     // Catch:{ Exception -> 0x0063 }
        L_0x0062:
            throw r14     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            r14 = move-exception
            goto L_0x0070
        L_0x0065:
            r6 = r3
            r7 = r6
        L_0x0067:
            if (r5 == 0) goto L_0x0073
            r5.close()     // Catch:{ Exception -> 0x0063 }
            goto L_0x0073
        L_0x006d:
            r14 = move-exception
            r6 = r3
            r7 = r6
        L_0x0070:
            r14.printStackTrace()
        L_0x0073:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "updateAlbumData#end"
            r14.<init>(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r5, r6, r1}
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r0)
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            com.samsung.android.gallery.support.utils.Log.d(r4, r14)
            int r3 = r3 + r7
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.local.table.MxAlbumUpdateHelper.updateAlbumData():int");
    }

    private int updateAlbumOrder() {
        long[] minMaxOrder = getMinMaxOrder();
        int i2 = 0;
        if (minMaxOrder != null) {
            try {
                if (!(minMaxOrder[0] == 0 || minMaxOrder[1] == 0)) {
                    StorageInfo storageInfo = StorageInfo.getDefault();
                    StorageInfo removable = StorageInfo.getRemovable();
                    StorageInfo.BucketHolder buckets = storageInfo.buckets();
                    Log.d("MxAlbumUpdateHelper", "updateAlbumOrder : min (" + minMaxOrder[0] + ") max (" + minMaxOrder[1] + ")");
                    int i7 = BucketUtils.VirtualBucketHolder.recent;
                    long albumOrder = getAlbumOrder(0, minMaxOrder[0]);
                    int updateAlbumOrder = updateAlbumOrder(i7, albumOrder);
                    try {
                        int i8 = BucketUtils.VirtualBucketHolder.favorite;
                        long albumOrder2 = getAlbumOrder(albumOrder, minMaxOrder[0]);
                        int updateAlbumOrder2 = updateAlbumOrder + updateAlbumOrder(i8, albumOrder2);
                        int i10 = buckets.camera;
                        long albumOrder3 = getAlbumOrder(albumOrder2, minMaxOrder[0]);
                        int updateAlbumOrder3 = updateAlbumOrder2 + updateAlbumOrder(i10, albumOrder3);
                        int i11 = removable.buckets().camera;
                        long albumOrder4 = getAlbumOrder(albumOrder3, minMaxOrder[0]);
                        int updateAlbumOrder4 = updateAlbumOrder3 + updateAlbumOrder(i11, albumOrder4);
                        int i12 = isSupportScreenShotGroup() ? BucketUtils.VirtualBucketHolder.screenShots : buckets.screenShot;
                        long albumOrder5 = getAlbumOrder(albumOrder4, minMaxOrder[0]);
                        int updateAlbumOrder5 = updateAlbumOrder4 + updateAlbumOrder(i12, albumOrder5);
                        int i13 = buckets.download;
                        long albumOrder6 = getAlbumOrder(albumOrder5, minMaxOrder[0]);
                        i2 = updateAlbumOrder5 + updateAlbumOrder(i13, albumOrder6) + updateAlbumOrder(FileUtils.getBucketId(storageInfo.quickShare), getAlbumOrder(albumOrder6, minMaxOrder[0]));
                    } catch (Exception e) {
                        e = e;
                        i2 = updateAlbumOrder;
                        Log.e("MxAlbumUpdateHelper", e.toString());
                        a.k(i2, "updateAlbumOrder : update count = ", "MxAlbumUpdateHelper");
                        return i2;
                    }
                }
            } catch (Exception e7) {
                e = e7;
                Log.e("MxAlbumUpdateHelper", e.toString());
                a.k(i2, "updateAlbumOrder : update count = ", "MxAlbumUpdateHelper");
                return i2;
            }
        }
        a.k(i2, "updateAlbumOrder : update count = ", "MxAlbumUpdateHelper");
        return i2;
    }

    private void updateAlbumTypeForFolder() {
        ArrayList arrayList = new ArrayList();
        Cursor folderCursorWithNonAlbumType = getFolderCursorWithNonAlbumType();
        if (folderCursorWithNonAlbumType != null) {
            try {
                if (folderCursorWithNonAlbumType.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(folderCursorWithNonAlbumType.getInt(0)));
                    } while (folderCursorWithNonAlbumType.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (folderCursorWithNonAlbumType != null) {
            folderCursorWithNonAlbumType.close();
        }
        if (arrayList.size() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("__albumType", Integer.valueOf(AlbumType.Folder.toInt()));
            a.k(this.mDatabase.update("mxalbum", contentValues, getUpdateWhere(arrayList.size()), (String[]) arrayList.toArray(new String[0])), "updateAlbumType : ", "MxAlbumUpdateHelper");
            return;
        }
        return;
        throw th;
    }

    private void updateEmptyAlbumDateModified() {
        ArrayList arrayList = new ArrayList();
        Cursor emptyAlbumCursor = getEmptyAlbumCursor();
        if (emptyAlbumCursor != null) {
            try {
                if (emptyAlbumCursor.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(emptyAlbumCursor.getInt(0)));
                    } while (emptyAlbumCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (emptyAlbumCursor != null) {
            emptyAlbumCursor.close();
        }
        if (arrayList.size() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
            a.k(this.mDatabase.update("mxalbum", contentValues, getUpdateWhere(arrayList.size()), (String[]) arrayList.toArray(new String[0])), "updateEmpty : ", "MxAlbumUpdateHelper");
            return;
        }
        return;
        throw th;
    }

    private int updateScreenShotsFolderInfo() {
        StorageInfo.BucketHolder buckets = StorageInfo.getDefault().buckets();
        StringBuilder sb2 = new StringBuilder("__bucketID in (");
        sb2.append(buckets.screenRecordings);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        String l = C0086a.l(sb2, buckets.screenShot, ")");
        ContentValues contentValues = new ContentValues();
        contentValues.put("folder_id", Integer.valueOf(BucketUtils.VirtualBucketHolder.screenShots));
        contentValues.put("folder_name", "ScreenShots");
        return this.mDatabase.update("mxalbum", contentValues, l, (String[]) null);
    }

    public long getAlbumOrder(long j2, long j3) {
        return (j2 + j3) / 2;
    }

    public Cursor getEmptyAlbumCursor() {
        return this.mDatabase.query("mxalbum", new String[]{"__bucketID"}, "album_count = 0 AND default_cover_path like '%!$&Welcome@#Image.jpg'", (String[]) null, (String) null, (String) null, (String) null);
    }

    public Cursor getFolderCursorWithNonAlbumType() {
        return this.mDatabase.query("mxalbum", new String[]{"__bucketID"}, "album_count = -1 AND coalesce(__albumType, 0) = 0", (String[]) null, (String) null, (String) null, (String) null);
    }

    public Cursor getLegacyAlbumCursor() {
        return this.mDatabase.query("album", new String[]{"__bucketID", "__absPath", "__Title", "folder_id", "folder_name", "default_cover_path", "cover_path", "cover_rect", "album_count", "album_order", "__ishide", "__sefFileType", "__isDrm", "__dateModified", "__volumeName", "album_sync_status"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
    }

    public boolean isFolder(Cursor cursor) {
        if (cursor.getInt(cursor.getColumnIndex("album_count")) == -1) {
            return true;
        }
        return false;
    }

    public boolean isSupportScreenShotGroup() {
        return false;
    }

    public void migrationAlbumData() {
        updateAlbumData();
        insertVirtualAlbum();
        updateAlbumOrder();
        GalleryPreference.getInstance().saveState(PreferenceName.MX_ALBUM_SHOW_DEFAULT_NUM, true);
    }

    public void updateMissingAlbumData() {
        try {
            updateEmptyAlbumDateModified();
            updateAlbumTypeForFolder();
        } catch (Exception e) {
            Log.d("MxAlbumUpdateHelper", "updateMissingAlbumData:" + e.getMessage());
        }
    }

    private int updateAlbumOrder(int i2, long j2) {
        String i7 = C0086a.i(i2, "__bucketID=");
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_order", Long.valueOf(j2));
        if (BucketUtils.isCameras(i2)) {
            contentValues.putNull("folder_id");
            contentValues.putNull("folder_name");
        }
        Log.d("MxAlbumUpdateHelper", "updateAlbumOrder:  bucketId = " + i2 + " albumOrder = " + j2);
        return this.mDatabase.update("mxalbum", contentValues, i7, (String[]) null);
    }
}
