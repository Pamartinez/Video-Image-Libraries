package com.samsung.android.gallery.module.album;

import A.a;
import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseIntArray;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.helper.AlbumApi;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumHelper {
    private static final AlbumHelper sInstance = new AlbumHelper();

    private boolean checkValidityAddNewFolder(int i2, String str) {
        int bucketId = FileUtils.getBucketId(str);
        String i7 = C0086a.i(bucketId, "__bucketID = ");
        if (i2 != -1) {
            i7 = i7 + " OR (__bucketID = " + i2 + " AND folder_id = " + bucketId + ")";
        }
        if (new LocalAlbumsApi().getCountAlbumFolder(i7) <= 0) {
            return true;
        }
        Log.d("AlbumHelper", "checkValidityAddNewFolder: false, duplicate name or circular reference");
        return false;
    }

    private int deleteNewEmptyAlbum(Context context, int i2, String str) {
        if (!FileUtils.isEmptyDummyImage(str)) {
            return 0;
        }
        return context.getContentResolver().delete(new LocalAlbumsApi().getTableUri(), C0086a.i(i2, "__bucketID = "), (String[]) null);
    }

    private Cursor getAlbumsCursorForHide(Context context) {
        String p6 = C0212a.p(new StringBuilder("folder_id is null AND __bucketID not in ( "), getPredefinedBucketIds(context), " ) AND (album_count != -1)");
        return context.getContentResolver().query(new LocalAlbumsApi().getTableUri(), new String[]{"count(*)"}, p6, (String[]) null, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getFolderId(int r1) {
        /*
            r0 = this;
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r0 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi
            r0.<init>()
            android.database.Cursor r0 = r0.getFolderCursor(r1)
            if (r0 == 0) goto L_0x0026
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x0026
            java.lang.String r1 = "folder_id"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ all -> 0x001c }
            int r1 = r0.getInt(r1)     // Catch:{ all -> 0x001c }
            goto L_0x0027
        L_0x001c:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0025:
            throw r1
        L_0x0026:
            r1 = 0
        L_0x0027:
            if (r0 == 0) goto L_0x002c
            r0.close()
        L_0x002c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.getFolderId(int):int");
    }

    public static AlbumHelper getInstance() {
        return sInstance;
    }

    private Cursor getMxAlbumsCursorForHide(Context context) {
        return LocalDatabaseHelper.getInstance(context).getReadableDatabase().rawQuery(C0212a.l("select count(*) from mxalbum as F where ", C0212a.p(new StringBuilder("(folder_id is null OR (select __albumType from mxalbum where __bucketID = F.folder_id AND folder_id is null) = 2) AND __bucketID not in ("), getPredefinedBucketIds(context), ") AND album_count != -1")), (String[]) null);
    }

    private int getNewFolderId(String str) {
        return FileUtils.getBucketId(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054 A[SYNTHETIC, Splitter:B:28:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getOnePathByBucketId(int r10) {
        /*
            r9 = this;
            java.lang.String r9 = "_data"
            java.lang.String r0 = "cloud_server_path"
            java.lang.String[] r3 = new java.lang.String[]{r9, r0}
            java.lang.String r9 = "bucket_id="
            java.lang.String r4 = c0.C0086a.i(r10, r9)
            java.lang.String r6 = "_id limit 1"
            r9 = 0
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r1 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x005b }
            r1.<init>()     // Catch:{ Exception -> 0x005b }
            com.samsung.android.gallery.support.providers.UriInterface r10 = com.samsung.android.gallery.support.providers.MediaUri.getInstance()     // Catch:{ Exception -> 0x005b }
            android.net.Uri r2 = r10.getSecMediaUri()     // Catch:{ Exception -> 0x005b }
            java.lang.String r7 = "getCloudServerPath"
            r5 = 0
            android.database.Cursor r10 = r1.getCursor(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x005b }
            if (r10 == 0) goto L_0x0051
            boolean r0 = r10.moveToFirst()     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0051
            r0 = 0
            java.lang.String r1 = r10.getString(r0)     // Catch:{ all -> 0x003f }
            r0 = 1
            java.lang.String r9 = r10.getString(r0)     // Catch:{ all -> 0x003b }
            r8 = r1
            r1 = r9
            r9 = r8
            goto L_0x0052
        L_0x003b:
            r0 = move-exception
            r2 = r1
        L_0x003d:
            r1 = r0
            goto L_0x0042
        L_0x003f:
            r0 = move-exception
            r2 = r9
            goto L_0x003d
        L_0x0042:
            r10.close()     // Catch:{ all -> 0x0046 }
            goto L_0x004b
        L_0x0046:
            r0 = move-exception
            r10 = r0
            r1.addSuppressed(r10)     // Catch:{ Exception -> 0x004c }
        L_0x004b:
            throw r1     // Catch:{ Exception -> 0x004c }
        L_0x004c:
            r0 = move-exception
            r10 = r0
            r1 = r9
            r9 = r2
            goto L_0x005e
        L_0x0051:
            r1 = r9
        L_0x0052:
            if (r10 == 0) goto L_0x0061
            r10.close()     // Catch:{ Exception -> 0x0058 }
            goto L_0x0061
        L_0x0058:
            r0 = move-exception
            r10 = r0
            goto L_0x005e
        L_0x005b:
            r0 = move-exception
            r10 = r0
            r1 = r9
        L_0x005e:
            r10.printStackTrace()
        L_0x0061:
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 == 0) goto L_0x0068
            r9 = r1
        L_0x0068:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.getOnePathByBucketId(int):java.lang.String");
    }

    private String getPredefinedBucketIds(Context context) {
        List<Integer> list;
        StringBuilder sb2 = new StringBuilder();
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            list = BucketUtils.valuesMx();
        } else {
            list = BucketUtils.values();
        }
        for (Integer intValue : list) {
            sb2.append(intValue.intValue());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        File externalFilesDir = context.getExternalFilesDir(".share");
        if (externalFilesDir != null) {
            sb2.append(FileUtils.getBucketId(externalFilesDir.getAbsolutePath()));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        sb2.deleteCharAt(sb2.length() - 1);
        return sb2.toString();
    }

    private boolean isAlbumAlreadyExist(String str) {
        String str2 = "__bucketID = " + FileUtils.getBucketId(str);
        LocalAlbumsApi localAlbumsApi = new LocalAlbumsApi();
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames)) {
            StringBuilder t = C0212a.t(j.d("(", str2, " OR (__Title = '", QueryBuilder.escapeString(FileUtils.getNameFromPath(str)), "' COLLATE NOCASE))"), " AND (__albumType not in (");
            t.append(AlbumType.Folder.toInt());
            t.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            t.append(AlbumType.Merged.toInt());
            t.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            t.append(AlbumType.SystemMerged.toInt());
            t.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            t.append(AlbumType.AutoUpdated.toInt());
            t.append("))");
            StringBuilder t3 = C0212a.t(t.toString(), " AND ");
            t3.append(localAlbumsApi.getFilterVolumeName());
            str2 = t3.toString();
        }
        if (localAlbumsApi.getCountAlbumFolder(str2) > 0) {
            return true;
        }
        return false;
    }

    private void updateFolderModifiedTime(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        new LocalAlbumsApi().updateData(arrayList, contentValues);
    }

    private int updateNewEmptyAlbumsHideState(int i2, boolean z) {
        int i7;
        ContentValues contentValues = new ContentValues();
        if (z) {
            i7 = 1;
        } else {
            i7 = -1;
        }
        contentValues.put("__ishide", Integer.valueOf(i7));
        return new LocalAlbumsApi().updateData(List.of(Integer.valueOf(i2)), contentValues);
    }

    public int addNewEmptyAlbum(String str, String str2, int i2, String str3) {
        ContentValues c5 = C0086a.c("__absPath", str);
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append("!$&Welcome@#Image.jpg");
        c5.put("default_cover_path", s.toString());
        c5.put("__bucketID", Integer.valueOf(FileUtils.getBucketId(str)));
        c5.put("__ishide", 0);
        c5.put("album_order", 1000000000000000007L);
        c5.put("album_count", 0);
        c5.put("__Title", str2);
        c5.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        if (i2 != 0 && !TextUtils.isEmpty(str3)) {
            c5.put("folder_id", Integer.valueOf(i2));
            c5.put("folder_name", str3);
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            c5.put("__albumType", Integer.valueOf(AlbumType.MyAlbum.toInt()));
            c5.put("__albumLevel", 1);
        }
        c5.put("__volumeName", FileUtils.getVolumeName(str));
        try {
            if (FileUtils.makeDirectoryIfAbsent(str)) {
                return new LocalAlbumsApi().createData(c5);
            }
        } catch (IllegalArgumentException e) {
            Log.e("AlbumHelper", e.toString());
        }
        return 0;
    }

    public int addNewEmptyAutoAlbum(int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__bucketID", Integer.valueOf(i2));
        contentValues.put("album_order", 1000000000000000007L);
        contentValues.put("album_count", 0);
        contentValues.put("__Title", str);
        contentValues.put("__albumType", Integer.valueOf(AlbumType.AutoUpdated.toInt()));
        contentValues.put("__albumLevel", 1);
        return new LocalAlbumsApi().createData(contentValues);
    }

    public int addNewEmptyFolder(String str) {
        return addNewEmptyFolder(str, 1000000000000000007L);
    }

    public int addNewFolder(List<Integer> list, String str, long j2) {
        return addNewFolder(list, -1, (String) null, str, j2);
    }

    public boolean checkAlbumExistInAlbumData(String str, int i2) {
        if (AlbumType.isAutoAlbum(i2)) {
            return DbCompat.autoAlbumApi().isAutoAlbumAlreadyExist(FileUtils.getNameFromPath(str));
        }
        return isAlbumAlreadyExist(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String checkCoverRatio(java.lang.String r3) {
        /*
            r2 = this;
            java.util.StringTokenizer r2 = new java.util.StringTokenizer
            java.lang.String r0 = ","
            r2.<init>(r3, r0)
        L_0x0007:
            boolean r0 = r2.hasMoreTokens()
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r2.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0020
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x0007
        L_0x0020:
            java.lang.String r2 = "0, 0, 0, 0"
            return r2
        L_0x0023:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.checkCoverRatio(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0023 A[Catch:{ all -> 0x0014, all -> 0x0019, Exception -> 0x001e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkDirectoriesFolderExist(java.lang.String r2) {
        /*
            r1 = this;
            r1 = 0
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r0 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi     // Catch:{ Exception -> 0x001e }
            r0.<init>()     // Catch:{ Exception -> 0x001e }
            android.database.Cursor r2 = r0.getAlbumFolderCursor(r2)     // Catch:{ Exception -> 0x001e }
            if (r2 == 0) goto L_0x0020
            int r0 = r2.getCount()     // Catch:{ all -> 0x0014 }
            if (r0 <= 0) goto L_0x0020
            r0 = 1
            goto L_0x0021
        L_0x0014:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ Exception -> 0x001e }
        L_0x001d:
            throw r0     // Catch:{ Exception -> 0x001e }
        L_0x001e:
            r2 = move-exception
            goto L_0x0027
        L_0x0020:
            r0 = r1
        L_0x0021:
            if (r2 == 0) goto L_0x0026
            r2.close()     // Catch:{ Exception -> 0x001e }
        L_0x0026:
            return r0
        L_0x0027:
            r2.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.checkDirectoriesFolderExist(java.lang.String):boolean");
    }

    public boolean checkFolderExistence(String str) {
        Cursor cursor;
        Throwable th;
        try {
            cursor = new SecMpQueryExecutor().getCursor(MediaUri.getInstance().getSecMediaUri(), new String[]{"_id", "substr(_data, 0, length(_data)-length(_display_name)) as dir_path", "substr(cloud_server_path, 0, length(cloud_server_path)-length(_display_name)) as dir_path2"}, "dir_path = ? COLLATE NOCASE or dir_path2 = ? COLLATE NOCASE", new String[]{str, str}, (String) null, "checkFolderExistence");
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    return true;
                }
            }
            if (cursor == null) {
                return false;
            }
            cursor.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public ErrorType checkNameExist(String str, int i2) {
        if (checkAlbumExistInAlbumData(str, i2)) {
            return ErrorType.ALBUM_NAME_ALREADY_IN_USE;
        }
        try {
            SecureFile secureFile = new SecureFile(str);
            if (!secureFile.exists() || !secureFile.isFile()) {
                if (!secureFile.exists() || !secureFile.isDirectory()) {
                    if (getCountInBucket(FileUtils.getBucketId(secureFile.getAbsolutePath())) != 0) {
                        Log.e("AlbumHelper", "Cloud Only directory exist!");
                        return ErrorType.ALBUM_NAME_ALREADY_IN_USE;
                    }
                } else if (!secureFile.canWrite()) {
                    Log.e("AlbumHelper", "Directory don't have write permission!");
                    return ErrorType.UNABLE_TO_CREATE_ALBUM;
                } else if (checkFolderExistence(secureFile.getAbsolutePath())) {
                    Log.e("AlbumHelper", "Media directory exist!");
                    return ErrorType.ALBUM_NAME_ALREADY_IN_USE;
                }
                return ErrorType.NONE;
            }
            Log.e("AlbumHelper", "File is exist already! create directory fail!");
            return ErrorType.FILE_ALREADY_EXIST;
        } catch (Exception e) {
            a.s(e, new StringBuilder("checkNameExists failed. e="), "AlbumHelper");
            return ErrorType.EXCEPTION_HAPPENED;
        }
    }

    public void clearAlbumOrderForHidden() {
        ArrayList arrayList = new ArrayList();
        Cursor hiddenAlbumCursor = new LocalAlbumsApi().getHiddenAlbumCursor();
        if (hiddenAlbumCursor != null) {
            try {
                if (hiddenAlbumCursor.getCount() > 0 && hiddenAlbumCursor.moveToFirst()) {
                    do {
                        arrayList.add(Integer.valueOf(hiddenAlbumCursor.getInt(hiddenAlbumCursor.getColumnIndex("__bucketID"))));
                    } while (hiddenAlbumCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (hiddenAlbumCursor != null) {
            hiddenAlbumCursor.close();
        }
        if (!arrayList.isEmpty()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("album_order", 1000000000000000007L);
            new LocalAlbumsApi().updateData(arrayList, contentValues);
            return;
        }
        return;
        throw th;
    }

    public boolean clearCoverInfo(int i2, String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("cover_path");
        if (!TextUtils.isEmpty(str)) {
            contentValues.put("cover_rect", str);
        } else {
            contentValues.putNull("cover_rect");
        }
        if (useAlbumCoverSync()) {
            AlbumDelegate.clearAlbumCoverSyncDB(contentValues, z);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        if (new LocalAlbumsApi().updateData(arrayList, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public boolean clearFolderInfoFromFolder(List<Integer> list) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("folder_name");
        contentValues.putNull("folder_id");
        contentValues.put("album_order", 1000000000000000007L);
        if (new LocalAlbumsApi().updateFolderData(list, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteAlbumFromFolder(List<Integer> list) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("folder_name");
        contentValues.putNull("folder_id");
        contentValues.put("album_order", 1000000000000000007L);
        updateFolderModifiedTime(getFolderId(list.get(0).intValue()));
        if (new LocalAlbumsApi().updateData(list, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public int deleteAlbumsForTrash(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        try {
            LocalAlbumsApi localAlbumsApi = new LocalAlbumsApi();
            String authority = localAlbumsApi.getTableUri().getAuthority();
            if (authority == null) {
                return 0;
            }
            ContentProviderOperation deleteOperation = localAlbumsApi.getDeleteOperation(list);
            ArrayList arrayList = new ArrayList();
            arrayList.add(deleteOperation);
            return AppResources.getAppContext().getContentResolver().applyBatch(authority, arrayList).length;
        } catch (Exception e) {
            a.s(e, new StringBuilder("deleteAlbumsForTrash:"), "AlbumHelper");
            return 0;
        }
    }

    public int deleteEmptyAlbum(Context context, int i2, String str, String str2) {
        return deleteNewEmptyAlbum(context, i2, str2);
    }

    public int getBucketIdFrom(long j2) {
        Cursor cursor;
        Throwable th;
        try {
            cursor = new SecMpQueryExecutor().getCursor(MediaUri.getInstance().getSecMediaUri(), new String[]{"bucket_id"}, a.f("media_id=", j2), (String[]) null, (String) null, "getBucketIdFrom");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    cursor.close();
                    return i2;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception unused) {
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return 0;
        throw th;
    }

    public HashMap<Integer, String> getCloudAlbumList() {
        Cursor rawQuery;
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select bucket_id, cloud_server_path from files where is_cloud = 2 GROUP BY bucket_id", "getCloudAlbumList");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        int i2 = rawQuery.getInt(rawQuery.getColumnIndex("bucket_id"));
                        String string = rawQuery.getString(rawQuery.getColumnIndex("cloud_server_path"));
                        if (i2 != 0 && !TextUtils.isEmpty(string)) {
                            hashMap.put(Integer.valueOf(i2), string);
                        }
                    } while (rawQuery.moveToNext());
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return hashMap;
        } catch (Exception e) {
            a.s(e, new StringBuilder("query cloud album list failed, e="), "AlbumHelper");
            return hashMap;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int getCountInBucket(int i2) {
        return new AlbumApi().getCountInBucket(i2);
    }

    public String getFolderLocationKey(Context context, int i2) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(new LocalAlbumsApi().getTableUri(), (String[]) null, "album_count = -1 ", (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("folder_id");
                    int columnIndex2 = query.getColumnIndex("__bucketID");
                    SparseIntArray sparseIntArray = new SparseIntArray();
                    do {
                        sparseIntArray.put(query.getInt(columnIndex2), query.getInt(columnIndex));
                    } while (query.moveToNext());
                    int i7 = sparseIntArray.get(i2, -1);
                    if (i7 != -1) {
                        Stack stack = new Stack();
                        stack.push(Integer.valueOf(i2));
                        while (i7 != 0) {
                            stack.push(Integer.valueOf(i7));
                            i7 = sparseIntArray.get(i7);
                        }
                        StringBuilder sb2 = new StringBuilder("location://folder/root");
                        int size = stack.size();
                        for (int i8 = 0; i8 < size; i8++) {
                            sb2.append('/');
                            sb2.append(stack.pop());
                        }
                        String sb3 = sb2.toString();
                        query.close();
                        return sb3;
                    }
                }
            }
            if (query == null) {
                return "location://albums";
            }
            query.close();
            return "location://albums";
        } catch (Exception e) {
            Log.e("AlbumHelper", e.toString());
            return "location://albums";
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public Object[] getTargetAlbumInfoForRename(int i2) {
        Cursor folderCursor;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            return null;
        }
        try {
            folderCursor = new LocalAlbumsApi().getFolderCursor(i2);
            if (folderCursor != null) {
                if (folderCursor.getCount() > 0 && folderCursor.moveToFirst() && folderCursor.getInt(folderCursor.getColumnIndex("__albumLevel")) == 1) {
                    int i7 = folderCursor.getInt(folderCursor.getColumnIndex("folder_id"));
                    Object[] objArr = {Integer.valueOf(i7), folderCursor.getString(folderCursor.getColumnIndex("folder_name"))};
                    folderCursor.close();
                    return objArr;
                }
            }
            if (folderCursor == null) {
                return null;
            }
            folderCursor.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getValidPath(MediaItem mediaItem) {
        String str;
        if (mediaItem.isEmptyAlbum()) {
            str = mediaItem.getPath();
        } else {
            String albumPath = mediaItem.getAlbumPath();
            if (!FileUtils.isValidPath(albumPath) || FileUtils.isInExternalFilesDir(albumPath)) {
                int albumID = mediaItem.getAlbumID();
                if (mediaItem.isMergedAlbum()) {
                    List<MediaItem> childList = mediaItem.getChildList();
                    if (!childList.isEmpty()) {
                        albumID = childList.get(0).getAlbumID();
                    }
                }
                str = getOnePathByBucketId(albumID);
            } else {
                str = albumPath;
            }
        }
        if (!FileUtils.isValidPath(str)) {
            Log.e("AlbumHelper", "getValidPath: path is not migrated " + str);
            DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
            deleteInstance.insertLog("[Invalid path][" + Logger.getEncodedString(str) + "]");
            return null;
        }
        Log.d("AlbumHelper", "getValidPath: path = " + Logger.getEncodedString(str));
        return FileUtils.getDirectoryFromPath(str);
    }

    public boolean hasAlbumSyncStatus(Context context) {
        return AlbumDelegate.hasAlbumSyncStatus(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0039 A[SYNTHETIC, Splitter:B:26:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasAlbumsForHide(android.content.Context r4) {
        /*
            r3 = this;
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi5x.MX_ALBUMS
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0010
            com.samsung.android.gallery.support.utils.PreferenceFeatures r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.MxAlbumsMergeNames
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r0)
            if (r0 == 0) goto L_0x0010
            r0 = r1
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            if (r0 == 0) goto L_0x001b
            android.database.Cursor r3 = r3.getMxAlbumsCursorForHide(r4)     // Catch:{ Exception -> 0x0018 }
            goto L_0x001f
        L_0x0018:
            r3 = move-exception
            r4 = r2
            goto L_0x003e
        L_0x001b:
            android.database.Cursor r3 = r3.getAlbumsCursorForHide(r4)     // Catch:{ Exception -> 0x0018 }
        L_0x001f:
            if (r3 == 0) goto L_0x0036
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x002c }
            if (r4 == 0) goto L_0x0036
            int r4 = r3.getInt(r2)     // Catch:{ all -> 0x002c }
            goto L_0x0037
        L_0x002c:
            r4 = move-exception
            r3.close()     // Catch:{ all -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ Exception -> 0x0018 }
        L_0x0035:
            throw r4     // Catch:{ Exception -> 0x0018 }
        L_0x0036:
            r4 = r2
        L_0x0037:
            if (r3 == 0) goto L_0x0041
            r3.close()     // Catch:{ Exception -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r3 = move-exception
        L_0x003e:
            r3.printStackTrace()
        L_0x0041:
            if (r4 <= 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r1 = r2
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.hasAlbumsForHide(android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A[Catch:{ all -> 0x0027, all -> 0x002d, Exception -> 0x0033 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasGroupNHideAlbum(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r6 = "*"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            java.lang.String r3 = "__ishide = 1 or album_count = -1"
            java.lang.String r5 = "__bucketID limit 1"
            r6 = 0
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x0033 }
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r7 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi     // Catch:{ Exception -> 0x0033 }
            r7.<init>()     // Catch:{ Exception -> 0x0033 }
            android.net.Uri r1 = r7.getTableUri()     // Catch:{ Exception -> 0x0033 }
            r4 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0033 }
            if (r7 == 0) goto L_0x0036
            int r0 = r7.getCount()     // Catch:{ all -> 0x0027 }
            if (r0 <= 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0027:
            r0 = move-exception
            r1 = r0
            r7.close()     // Catch:{ all -> 0x002d }
            goto L_0x0032
        L_0x002d:
            r0 = move-exception
            r7 = r0
            r1.addSuppressed(r7)     // Catch:{ Exception -> 0x0033 }
        L_0x0032:
            throw r1     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            r0 = move-exception
            r7 = r0
            goto L_0x003d
        L_0x0036:
            r0 = r6
        L_0x0037:
            if (r7 == 0) goto L_0x003c
            r7.close()     // Catch:{ Exception -> 0x0033 }
        L_0x003c:
            return r0
        L_0x003d:
            r7.printStackTrace()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.hasGroupNHideAlbum(android.content.Context):boolean");
    }

    public boolean isCloudOnlyAlbum(FileItemInterface fileItemInterface) {
        return isCloudOnlyAlbum(fileItemInterface.getAlbumID());
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0032 A[Catch:{ all -> 0x0023, all -> 0x0028, Exception -> 0x002d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isHiddenAlbum(int r3) {
        /*
            r2 = this;
            r2 = 0
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r0 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi     // Catch:{ Exception -> 0x002d }
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams     // Catch:{ Exception -> 0x002d }
            r1.<init>()     // Catch:{ Exception -> 0x002d }
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = r1.addAlbumId(r3)     // Catch:{ Exception -> 0x002d }
            r0.<init>(r3)     // Catch:{ Exception -> 0x002d }
            android.database.Cursor r3 = r0.getHiddenAlbumCursor()     // Catch:{ Exception -> 0x002d }
            if (r3 == 0) goto L_0x002f
            int r0 = r3.getCount()     // Catch:{ all -> 0x0023 }
            if (r0 <= 0) goto L_0x002f
            boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x002f
            r0 = 1
            goto L_0x0030
        L_0x0023:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch:{ Exception -> 0x002d }
        L_0x002c:
            throw r0     // Catch:{ Exception -> 0x002d }
        L_0x002d:
            r3 = move-exception
            goto L_0x0036
        L_0x002f:
            r0 = r2
        L_0x0030:
            if (r3 == 0) goto L_0x0035
            r3.close()     // Catch:{ Exception -> 0x002d }
        L_0x0035:
            return r0
        L_0x0036:
            r3.printStackTrace()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.isHiddenAlbum(int):boolean");
    }

    public boolean isSystemAlbum(FileItemInterface fileItemInterface) {
        if (BucketUtils.contains(fileItemInterface.getAlbumID()) || FileUtils.isRootDirectory(FileUtils.getDirectoryFromPath(fileItemInterface.getPath(), false))) {
            return true;
        }
        return false;
    }

    public void removeAlbumCover(int i2) {
        if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER) {
            String str = FileUtils.getExternalFilesDir(".album") + File.separator + i2;
            if (FileUtils.exists(str)) {
                FileUtils.deleteDirectory(new SecureFile(str));
                Log.d("AlbumHelper", "removeAlbumCover", Integer.valueOf(i2));
            }
        }
    }

    public boolean removeFolder(Context context, List<Integer> list, List<Integer> list2) {
        boolean z;
        Uri tableUri = new LocalAlbumsApi().getTableUri();
        if (context.getContentResolver().delete(tableUri, "__bucketID IN " + CursorHelper.joinIds(list), (String[]) null) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (list2 == null || list2.isEmpty()) {
            return z;
        }
        if (!z || !deleteAlbumFromFolder(list2)) {
            return false;
        }
        return true;
    }

    public boolean renameEmptyFolderName(int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__bucketID", Integer.valueOf(FileUtils.getBucketId(str)));
        contentValues.put("__Title", str);
        contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        if (new LocalAlbumsApi().updateEmptyFolderName(i2, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public boolean renameFolderName(int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("folder_id", Integer.valueOf(FileUtils.getBucketId(str)));
        contentValues.put("folder_name", str);
        contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        if (new LocalAlbumsApi().updateFolderName(i2, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public int renameNewEmptyAlbum(Context context, int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__bucketID", Integer.valueOf(FileUtils.getBucketId(str)));
        contentValues.put("__Title", new File(str).getName());
        contentValues.put("default_cover_path", str + File.separator + "!$&Welcome@#Image.jpg");
        contentValues.put("__absPath", str);
        LocalAlbumsApi localAlbumsApi = new LocalAlbumsApi();
        return context.getContentResolver().update(localAlbumsApi.getTableUri().buildUpon().appendQueryParameter(localAlbumsApi.getWatchParam(), SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).build(), contentValues, C0086a.i(i2, "__bucketID = "), (String[]) null);
    }

    public boolean replaceAlbumCover(int i2, String str) {
        return AlbumDelegate.replaceAlbumCover(i2, str);
    }

    public String replaceCoverRect(String str, String str2, String str3) {
        return AlbumDelegate.replaceCoverRect(str, str2, str3);
    }

    public String[] saveCoverFile(Context context, int i2, String str) {
        return AlbumDelegate.saveCoverFile(context, i2, str);
    }

    public boolean setAlbumInfoShown(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__albumShowInfo", 1);
        if (new LocalAlbumsApi().updateData((List) Stream.of(Integer.valueOf(i2)).collect(Collectors.toList()), contentValues) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateAlbumCoverDB(int i2, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ContentValues c5 = C0086a.c("cover_path", str);
        if (!TextUtils.isEmpty(str2)) {
            c5.put("cover_rect", str2);
        }
        if (useAlbumCoverSync()) {
            AlbumDelegate.updateAlbumCoverSyncDB(c5);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        if (new LocalAlbumsApi().updateData(arrayList, c5) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateAlbumLevel(List<Integer> list, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__albumLevel", Integer.valueOf(i2));
        if (new LocalAlbumsApi().updateData(list, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateAlbumOrder(ArrayList<Integer> arrayList, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_order", Long.valueOf(j2));
        if (new LocalAlbumsApi().updateData(arrayList, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public int updateAlbumsHideState(MediaItem mediaItem) {
        Log.d("AlbumHelper", "updateAlbumsHideState {" + mediaItem.isAlbumHide() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.isEmptyAlbum() + "}");
        if (mediaItem.isEmptyAlbum()) {
            return updateNewEmptyAlbumsHideState(mediaItem.getAlbumID(), mediaItem.isAlbumHide());
        }
        return new AlbumApi().updateAlbumsHideState(mediaItem.getAlbumID(), mediaItem.isAlbumHide());
    }

    public boolean updateFolder(Context context, String str, int i2, int i7) {
        Throwable th;
        Cursor query = context.getContentResolver().query(new LocalAlbumsApi().getTableUri(), (String[]) null, C0086a.i(i2, "__bucketID = "), (String[]) null, (String) null);
        boolean z = false;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndex("__Title"));
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(str)) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("__bucketID", Integer.valueOf(FileUtils.getBucketId(str)));
                        contentValues.put("__Title", str.substring(str.lastIndexOf(47) + 1));
                        contentValues.put("__absPath", str);
                        contentValues.put("album_count", Integer.valueOf(i7));
                        contentValues.put("folder_id", Integer.valueOf(i2));
                        contentValues.put("folder_name", string);
                        contentValues.put("__ishide", 0);
                        contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
                        contentValues.put("album_order", 1000000000000000007L);
                        contentValues.put("default_cover_path", str + File.separator + "!$&Welcome@#Image.jpg");
                        contentValues.put("__volumeName", FileUtils.getVolumeName(str));
                        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                            contentValues.put("__albumLevel", Integer.valueOf(query.getInt(query.getColumnIndex("__albumLevel"))));
                        }
                        if (new LocalAlbumsApi().createData(contentValues) > 0) {
                            z = true;
                        }
                        query.close();
                        return z;
                    }
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (query != null) {
            query.close();
        }
        return false;
        throw th;
    }

    public void updateInvalidAlbums(Context context) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, 0);
            int update = context.getContentResolver().update(MediaUri.getInstance().getFilesUri(), contentValues, "_data LIKE '/data/sec/cloud/%' AND media_type IN (1,3)", (String[]) null);
            Log.d("AlbumHelper", "updateInvalidAlbums {" + update + "}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007d A[SYNTHETIC, Splitter:B:31:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateNewEmptyAlbumData(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r10 = "__bucketID"
            java.lang.String r0 = "default_cover_path"
            java.lang.String r1 = "__absPath"
            java.lang.String[] r4 = new java.lang.String[]{r1, r10, r0}
            java.lang.String r5 = "default_cover_path LIKE '%!$&Welcome@#Image.jpg' AND album_count != 0"
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r10 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi
            r10.<init>()
            android.net.Uri r3 = r10.getTableUri()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r8 = -1
            android.content.ContentResolver r2 = r11.getContentResolver()     // Catch:{ Exception -> 0x0084 }
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0084 }
            if (r2 == 0) goto L_0x007a
            boolean r0 = r2.moveToFirst()     // Catch:{ all -> 0x006c }
            if (r0 == 0) goto L_0x007a
            int r4 = r2.getCount()     // Catch:{ all -> 0x006c }
        L_0x0036:
            r0 = 2
            java.lang.String r0 = r2.getString(r0)     // Catch:{ all -> 0x0056 }
            java.lang.String r5 = "!$&Welcome@#Image.jpg"
            boolean r5 = r0.contains(r5)     // Catch:{ all -> 0x0056 }
            if (r5 == 0) goto L_0x0065
            boolean r0 = com.samsung.android.gallery.support.utils.FileUtils.isFile(r0)     // Catch:{ all -> 0x0056 }
            r5 = 1
            if (r0 == 0) goto L_0x005a
            int r0 = r2.getInt(r5)     // Catch:{ all -> 0x0056 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0056 }
            r1.add(r0)     // Catch:{ all -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r0 = move-exception
            r5 = r4
            r4 = r0
            goto L_0x006f
        L_0x005a:
            int r0 = r2.getInt(r5)     // Catch:{ all -> 0x0056 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0056 }
            r10.add(r0)     // Catch:{ all -> 0x0056 }
        L_0x0065:
            boolean r0 = r2.moveToNext()     // Catch:{ all -> 0x0056 }
            if (r0 != 0) goto L_0x0036
            goto L_0x007b
        L_0x006c:
            r0 = move-exception
            r4 = r0
            r5 = r8
        L_0x006f:
            r2.close()     // Catch:{ all -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r0 = move-exception
            r4.addSuppressed(r0)     // Catch:{ Exception -> 0x0078 }
        L_0x0077:
            throw r4     // Catch:{ Exception -> 0x0078 }
        L_0x0078:
            r0 = move-exception
            goto L_0x0086
        L_0x007a:
            r4 = r8
        L_0x007b:
            if (r2 == 0) goto L_0x008a
            r2.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x008a
        L_0x0081:
            r0 = move-exception
            r5 = r4
            goto L_0x0086
        L_0x0084:
            r0 = move-exception
            r5 = r8
        L_0x0086:
            r0.printStackTrace()
            r4 = r5
        L_0x008a:
            int r0 = r10.size()
            if (r0 <= 0) goto L_0x00e3
            java.lang.String r10 = com.samsung.android.gallery.support.helper.CursorHelper.joinIds(r10)
            int r0 = r1.size()
            r2 = 0
            if (r0 <= 0) goto L_0x00a0
            java.lang.String r0 = com.samsung.android.gallery.support.helper.CursorHelper.joinIds(r1)
            goto L_0x00a1
        L_0x00a0:
            r0 = r2
        L_0x00a1:
            java.lang.String r1 = "__bucketID IN "
            java.lang.String r10 = i.C0212a.l(r1, r10)
            if (r0 != 0) goto L_0x00ab
            r0 = r2
            goto L_0x00b1
        L_0x00ab:
            java.lang.String r1 = "_data LIKE '%!$&Welcome@#Image.jpg' AND bucket_id IN "
            java.lang.String r0 = r1.concat(r0)
        L_0x00b1:
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r5 = "cover_rect"
            r1.putNull(r5)
            r5 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "album_count"
            r1.put(r6, r5)
            if (r0 == 0) goto L_0x00d7
            android.content.ContentResolver r5 = r11.getContentResolver()
            com.samsung.android.gallery.support.providers.UriInterface r6 = com.samsung.android.gallery.support.providers.MediaUri.getInstance()
            android.net.Uri r6 = r6.getImageUri()
            int r8 = r5.delete(r6, r0, r2)
        L_0x00d7:
            android.content.ContentResolver r11 = r11.getContentResolver()
            int r10 = r11.update(r3, r1, r10, r2)
            r9 = r8
            r8 = r10
            r10 = r9
            goto L_0x00e4
        L_0x00e3:
            r10 = r8
        L_0x00e4:
            java.lang.String r11 = "updateNewEmptyAlbumData {"
            java.lang.String r0 = ","
            java.lang.StringBuilder r11 = A.a.h(r8, r4, r11, r0, r0)
            r11.append(r10)
            java.lang.String r10 = "}"
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            java.lang.String r11 = "AlbumHelper"
            com.samsung.android.gallery.support.utils.Log.d(r11, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.updateNewEmptyAlbumData(android.content.Context):void");
    }

    public boolean updateOldWelcomeImages(Context context) {
        return AlbumDelegate.updateLegacyWelcomeImages(context);
    }

    public boolean useAlbumCoverSync() {
        return false;
    }

    public int addNewEmptyFolder(String str, long j2) {
        return addNewEmptyFolder(-1, (String) null, str, j2);
    }

    public int addNewFolder(List<Integer> list, int i2, String str, String str2, long j2) {
        int newFolderId = getNewFolderId(str2);
        String str3 = str2;
        if (addNewEmptyFolder(i2, str, str3, j2) == 0) {
            return -1;
        }
        ContentValues c5 = C0086a.c("folder_name", str3);
        c5.put("folder_id", Integer.valueOf(newFolderId));
        c5.put("album_order", 1000000000000000007L);
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            c5.put("__albumLevel", 1);
        }
        if (new LocalAlbumsApi().updateData(list, c5) > 0) {
            return newFolderId;
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002e A[Catch:{ all -> 0x001f, all -> 0x0024, Exception -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isCloudOnlyAlbum(int r3) {
        /*
            r2 = this;
            java.lang.String r2 = "select _id from files where bucket_id="
            java.lang.String r0 = " AND is_cloud in (1,3) limit 1"
            java.lang.String r2 = i.C0212a.j(r3, r2, r0)
            r3 = 0
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r0 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x0029 }
            r0.<init>()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r1 = "is_cloud_only"
            android.database.Cursor r2 = r0.rawQuery((java.lang.String) r2, (java.lang.String) r1)     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x002b
            int r0 = r2.getCount()     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x002b
            r0 = 1
            goto L_0x002c
        L_0x001f:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ Exception -> 0x0029 }
        L_0x0028:
            throw r0     // Catch:{ Exception -> 0x0029 }
        L_0x0029:
            r2 = move-exception
            goto L_0x0032
        L_0x002b:
            r0 = r3
        L_0x002c:
            if (r2 == 0) goto L_0x0031
            r2.close()     // Catch:{ Exception -> 0x0029 }
        L_0x0031:
            return r0
        L_0x0032:
            r2.printStackTrace()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumHelper.isCloudOnlyAlbum(int):boolean");
    }

    public int addNewEmptyFolder(int i2, String str, String str2, long j2) {
        if (!checkValidityAddNewFolder(i2, str2)) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_order", Long.valueOf(j2));
        int newFolderId = getNewFolderId(str2);
        if (!TextUtils.isEmpty(str)) {
            contentValues.put("__bucketID", Integer.valueOf(newFolderId));
            contentValues.put("__Title", str2);
            contentValues.put("folder_name", str);
            contentValues.put("folder_id", Integer.valueOf(i2));
        } else {
            contentValues.put("__bucketID", Integer.valueOf(newFolderId));
            contentValues.put("__Title", str2);
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            contentValues.put("__albumLevel", 1);
            contentValues.put("__albumType", Integer.valueOf(AlbumType.Folder.toInt()));
        }
        contentValues.put("album_count", -1);
        contentValues.put("__ishide", -1);
        contentValues.put("__sefFileType", -1);
        contentValues.put("__isDrm", 0);
        contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        return new LocalAlbumsApi().createData(contentValues);
    }

    public boolean updateFolder(int[] iArr, int i2, String str) {
        ContentValues c5 = C0086a.c("folder_name", str);
        c5.put("folder_id", Integer.valueOf(i2));
        c5.put("album_order", 1000000000000000007L);
        ArrayList arrayList = new ArrayList();
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        updateFolderModifiedTime(i2);
        if (new LocalAlbumsApi().updateData(arrayList, c5) > 0) {
            return true;
        }
        return false;
    }
}
