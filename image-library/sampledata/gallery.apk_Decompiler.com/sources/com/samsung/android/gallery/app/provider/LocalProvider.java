package com.samsung.android.gallery.app.provider;

import V3.b;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.abstraction.ShareData;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalProvider extends ContentProvider {
    private static final String[] FILE_PROVIDER_URI_COLUMNS = {"_display_name", IParameterKey.SIZE};
    private static final String[] LOG_COLUMNS = {"_id"};
    private static final String[] UNLIMITED_SHARE_PATH_COLUMNS = {"share_path"};
    private static final String[] UNLIMITED_SHARE_URI_COLUMNS = {"share_uri", "share_bucket_id", "share_bucket_name"};
    protected final String TAG = Trace.makeTag(this);
    private volatile LocalDatabaseHelper mDatabaseOpenHelper;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PackageHolder {
        static final HashSet<String> PACKAGES = new HashSet<>(List.of("com.sec.android.gallery3d", "com.samsung.android.allshare.service.fileshare", "com.samsung.knox.securefolder", "com.samsung.android.knox.containeragent", "com.samsung.android.knox.containercore", "com.samsung.android.widget.pictureframe", "com.samsung.android.aware.service", "com.samsung.android.app.sharelive", "com.google.android.gms"));

        public static boolean isSystemPackage(String str) {
            if (str == null || !PACKAGES.contains(str) || !SignatureChecker.checkSignature(AppResources.getAppContext(), str)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UriMatcherHolder {
        static final UriMatcher URI_MATCHER = new UriMatcher(-1) {
            {
                addURI("com.sec.android.gallery3d.provider", "album_display_info_table", 7);
                addURI("com.sec.android.gallery3d.provider", "album", 6);
                addURI("com.sec.android.gallery3d.provider", "mxalbum", 15);
                addURI("com.sec.android.gallery3d.provider", "search_history", 1);
                addURI("com.sec.android.gallery3d.provider", "trash", 3);
                addURI("com.sec.android.gallery3d.provider", "share", 2);
                addURI("com.sec.android.gallery3d.provider", "unlimited_move_list", 4);
                addURI("com.sec.android.gallery3d.provider", "unlimited_share_list", 5);
                addURI("com.sec.android.gallery3d.provider", "log", 8);
                addURI("com.sec.android.gallery3d.provider", "suggested", 9);
            }
        };
    }

    public LocalProvider() {
        Trace.endSection();
    }

    private LocalDatabaseHelper getDataBaseHelper() {
        if (this.mDatabaseOpenHelper == null) {
            synchronized (LocalProvider.class) {
                try {
                    if (this.mDatabaseOpenHelper == null) {
                        this.mDatabaseOpenHelper = LocalDatabaseHelper.getInstance(getContext());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mDatabaseOpenHelper;
    }

    private SQLiteDatabase getReadableDatabase() {
        return getDataBaseHelper().getReadableDatabase();
    }

    private String getTableName(Uri uri) {
        switch (matchUri(uri)) {
            case 1:
                return "search_history";
            case 2:
                return "share";
            case 3:
                return "trash";
            case 6:
                return "album";
            case 7:
                return "album_display_info_table";
            case 8:
                return "log";
            case 9:
                return "suggested";
            case 11:
                return "operation_history";
            case 12:
                return "filesystem_monitor";
            case 14:
                return "cache";
            case 15:
                return "mxalbum";
            case 19:
                return "album_cover_history";
            case 21:
                return "recover";
            case 22:
                return "my_query";
            default:
                return null;
        }
    }

    private SQLiteDatabase getWritableDatabase() {
        return getDataBaseHelper().getWritableDatabase();
    }

    /* access modifiers changed from: private */
    public void initOnBg() {
        Trace.beginSection("LocalProvider initOnBg");
        getDataBaseHelper();
        Trace.endSection();
    }

    private long insertOrUpdateLog(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase2;
        Throwable th;
        if (contentValues == null || !contentValues.containsKey("hash")) {
            sQLiteDatabase2 = sQLiteDatabase;
        } else {
            sQLiteDatabase2 = sQLiteDatabase;
            Cursor query = sQLiteDatabase2.query("log", LOG_COLUMNS, "hash=?", new String[]{contentValues.getAsString("hash")}, (String) null, (String) null, (String) null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        String str2 = this.TAG;
                        Log.v(str2, "duplicated log : " + contentValues.getAsString("__log"));
                        if (sQLiteDatabase2.update("log", contentValues, "hash=?", new String[]{contentValues.getAsString("hash")}) > 0) {
                            long j2 = query.getLong(0);
                            query.close();
                            return j2;
                        }
                    }
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            if (query != null) {
                query.close();
            }
        }
        return sQLiteDatabase2.insert(str, (String) null, contentValues);
        throw th;
    }

    private Cursor makeLocalFileCursorFromUri(Uri uri) {
        String str;
        if (uri == null || uri.getPath() == null) {
            str = null;
        } else {
            str = uri.getLastPathSegment();
        }
        if (str != null) {
            String readablePath = FileProviderUtil.toReadablePath(str);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                MatrixCursor matrixCursor = new MatrixCursor(FILE_PROVIDER_URI_COLUMNS);
                SecureFile secureFile = new SecureFile(readablePath);
                matrixCursor.addRow(new Object[]{secureFile.getName(), Long.valueOf(secureFile.length())});
                return matrixCursor;
            } catch (Exception e) {
                Log.e(this.TAG, "makeLocalFileCursorFromUri=" + e.getMessage());
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return null;
    }

    private Cursor querySharePathList() {
        ArrayList<String> sharePathList = ShareList.getSharePathList();
        if (sharePathList == null || sharePathList.isEmpty()) {
            Log.e(this.TAG, "query failed for share-path empty");
            return null;
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            MatrixCursor matrixCursor = new MatrixCursor(UNLIMITED_SHARE_PATH_COLUMNS);
            Iterator<String> it = sharePathList.iterator();
            while (it.hasNext()) {
                matrixCursor.addRow(new Object[]{it.next()});
            }
            String str = this.TAG;
            Log.d(str, "query for share-path" + Logger.vt(Integer.valueOf(sharePathList.size()), Long.valueOf(currentTimeMillis)));
            sharePathList.clear();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return matrixCursor;
        } catch (Throwable th) {
            sharePathList.clear();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    private Cursor queryShareUriList() {
        String str;
        ArrayList<Integer> shareBucketList = ShareList.getShareBucketList();
        if (shareBucketList == null || shareBucketList.isEmpty()) {
            ArrayList<ShareData> shareList = ShareList.getShareList();
            if (shareList == null || shareList.isEmpty()) {
                Log.e(this.TAG, "query failed for share-uri empty");
                return null;
            }
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                MatrixCursor matrixCursor = new MatrixCursor(UNLIMITED_SHARE_URI_COLUMNS);
                Iterator<ShareData> it = shareList.iterator();
                while (it.hasNext()) {
                    ShareData next = it.next();
                    matrixCursor.addRow(new Object[]{next.uri, Integer.valueOf(next.bucketId), next.bucketName});
                }
                Log.d(this.TAG, "query for share-uri" + Logger.vt(Integer.valueOf(shareList.size()), Long.valueOf(currentTimeMillis)));
                shareList.clear();
                ShareList.clearShareList();
                Binder.restoreCallingIdentity(clearCallingIdentity);
                return matrixCursor;
            } catch (Throwable th) {
                shareList.clear();
                ShareList.clearShareList();
                Binder.restoreCallingIdentity(clearCallingIdentity);
                throw th;
            }
        } else {
            long clearCallingIdentity2 = Binder.clearCallingIdentity();
            try {
                if (BucketUtils.isRecent(shareBucketList.get(0).intValue())) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    Cursor query = DbCompat.query(new QueryParams(DbKey.VIRTUAL_ALBUM_RECENT_SHARE_DATA));
                    String str2 = this.TAG;
                    StringBuilder sb2 = new StringBuilder("query share-bucket ");
                    if (query == null) {
                        str = "failed";
                    } else {
                        str = "#" + query.getCount();
                    }
                    sb2.append(str);
                    sb2.append(" +");
                    sb2.append(System.currentTimeMillis() - currentTimeMillis2);
                    Log.d(str2, sb2.toString());
                    shareBucketList.clear();
                    ShareList.clearShareList();
                    Binder.restoreCallingIdentity(clearCallingIdentity2);
                    return query;
                }
                shareBucketList.clear();
                ShareList.clearShareList();
                Binder.restoreCallingIdentity(clearCallingIdentity2);
                return null;
            } catch (Throwable th2) {
                shareBucketList.clear();
                ShareList.clearShareList();
                Binder.restoreCallingIdentity(clearCallingIdentity2);
                throw th2;
            }
        }
    }

    private Cursor queryVirtualAlbum(int i2, String[] strArr) {
        String str;
        String str2;
        if (FileUtils.initializeIfAbsent(getContext())) {
            AppResources.setAppContext(getContext());
        }
        if (i2 == 16) {
            str = DbKey.VIRTUAL_ALBUM_RECENT;
        } else {
            str = DbKey.VIRTUAL_ALBUM_FAVORITE;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            QueryParams queryParams = new QueryParams(str);
            if (strArr != null && strArr.length > 0 && !TextUtils.isEmpty(strArr[0])) {
                if ("image".equals(strArr[0])) {
                    queryParams.setMediaTypeFilter(MediaFilterType.IMAGE_ONLY.toString());
                } else if ("video".equals(strArr[0])) {
                    queryParams.setMediaTypeFilter(MediaFilterType.VIDEO_ONLY.toString());
                }
            }
            Cursor query = DbCompat.query(queryParams);
            String str3 = this.TAG;
            StringBuilder sb2 = new StringBuilder("query virtual album(");
            sb2.append(i2);
            sb2.append(")");
            if (query == null) {
                str2 = " failed";
            } else {
                str2 = " #" + query.getCount();
            }
            sb2.append(str2);
            sb2.append(" +");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            Log.d(str3, sb2.toString());
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return query;
        } catch (Exception e) {
            Log.e(this.TAG, "query virtual album(" + i2 + ") failed. e=" + e.getMessage());
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return null;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    public void assertSecPreloadedPackage() {
        String caller = getCaller();
        if ("com.samsung.android.knox.containeragent".equals(caller) || "com.google.android.gms".equals(caller)) {
            throw new SecurityException(C0212a.A(caller, " is not allowed"));
        }
    }

    public void assertSystemPackage() {
        String callingPackage = getCallingPackage();
        if (!"com.sec.android.gallery3d".equals(callingPackage) && !PackageHolder.isSystemPackage(callingPackage)) {
            String str = this.TAG;
            Log.e(str, "assert system package : package is not allowed " + callingPackage);
            throw new SecurityException(C0212a.A(callingPackage, " is not allowed"));
        }
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        return super.call(str, str2, bundle);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        assertSystemPackage();
        String tableName = getTableName(uri);
        if (tableName == null) {
            Log.e(this.TAG, "unable to find matched table");
            return -1;
        }
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.isOpen()) {
                return writableDatabase.delete(tableName, str, strArr);
            }
        } catch (SQLiteException e) {
            String str2 = this.TAG;
            Log.e(str2, "unable to delete. " + e.getMessage());
        }
        return -1;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        Context context = getContext();
        if (context != null) {
            try {
                new LocalProviderDebugHelper(context).dumpLog(context, printWriter);
            } catch (Exception e) {
                printWriter.println("dump failed e=" + e.getMessage());
            }
        }
    }

    public String getCaller() {
        return getCallingPackageUnchecked();
    }

    public String getType(Uri uri) {
        String str;
        assertSystemPackage();
        if (matchUri(uri) != 13) {
            Log.e(this.TAG, "getType unable to find matched table : " + uri);
        } else {
            if (uri.getPath() != null) {
                str = uri.getLastPathSegment();
            } else {
                str = null;
            }
            if (str != null) {
                return FileType.getMimeType(str);
            }
            Log.w(this.TAG, "getType failed for local : " + uri);
        }
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase;
        long j2;
        assertSystemPackage();
        String tableName = getTableName(uri);
        if (tableName == null) {
            Log.e(this.TAG, "unable to find matched table");
            return null;
        }
        try {
            writableDatabase = getWritableDatabase();
            writableDatabase.beginTransaction();
            if (writableDatabase.isOpen()) {
                int matchUri = matchUri(uri);
                if (matchUri == 6) {
                    j2 = writableDatabase.insertWithOnConflict(tableName, (String) null, contentValues, 5);
                } else if (matchUri == 8) {
                    j2 = insertOrUpdateLog(writableDatabase, tableName, contentValues);
                } else if (matchUri != 15) {
                    j2 = writableDatabase.insert(tableName, (String) null, contentValues);
                } else {
                    j2 = insertWithOnConflict(writableDatabase, tableName, uri, contentValues);
                }
                if (j2 != -1) {
                    writableDatabase.setTransactionSuccessful();
                    Uri build = LocalDatabase.URI.buildUpon().appendPath(tableName).appendPath(String.valueOf(j2)).build();
                    writableDatabase.endTransaction();
                    return build;
                }
            }
            writableDatabase.endTransaction();
        } catch (SQLiteException e) {
            String str = this.TAG;
            Log.e(str, "unable to insert. " + e.getMessage());
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
        return null;
    }

    public long insertWithOnConflict(SQLiteDatabase sQLiteDatabase, String str, Uri uri, ContentValues contentValues) {
        return sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, 5);
    }

    public boolean isWatchUri(Uri uri) {
        return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(uri.getQueryParameter("watch"));
    }

    public int matchUri(Uri uri) {
        return UriMatcherHolder.URI_MATCHER.match(uri);
    }

    /* JADX INFO: finally extract failed */
    public boolean onCreate() {
        try {
            Trace.beginSection("APP_LocalProvider onCreate");
            ThreadUtil.postOnBgThread(new b(11, this));
            Trace.endSection();
            return true;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        assertSystemPackage();
        int matchUri = matchUri(uri);
        switch (matchUri) {
            case 1:
            case 2:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 19:
            case 21:
            case 22:
                break;
            case 3:
                assertSecPreloadedPackage();
                break;
            case 4:
                return querySharePathList();
            case 5:
                return queryShareUriList();
            case 10:
                return null;
            case 13:
                return makeLocalFileCursorFromUri(uri);
            case 16:
            case 17:
                return queryVirtualAlbum(matchUri, strArr);
            default:
                Log.e(this.TAG, "unable to find matched table");
                return null;
        }
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            if (readableDatabase.isOpen()) {
                return readableDatabase.query(getTableName(uri), strArr, str, strArr2, (String) null, (String) null, str2);
            }
            return null;
        } catch (SQLiteException e) {
            String str3 = this.TAG;
            Log.e(str3, "failed to query to database. " + e.getMessage());
            return null;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Context context;
        assertSystemPackage();
        String tableName = getTableName(uri);
        int i2 = -1;
        if (tableName == null) {
            Log.e(this.TAG, "unable to find matched table");
            return -1;
        }
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.isOpen()) {
                i2 = writableDatabase.update(tableName, contentValues, str, strArr);
            }
            if (i2 > 0 && isWatchUri(uri) && (context = getContext()) != null) {
                context.getContentResolver().notifyChange(uri, (ContentObserver) null);
            }
            return i2;
        } catch (SQLiteException e) {
            String str2 = this.TAG;
            Log.e(str2, "unable to update database. " + e.getMessage());
            return -1;
        }
    }
}
