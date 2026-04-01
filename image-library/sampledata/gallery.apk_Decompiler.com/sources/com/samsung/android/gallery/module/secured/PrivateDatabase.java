package com.samsung.android.gallery.module.secured;

import A.a;
import A4.C0385u;
import Ba.h;
import L5.b;
import N2.j;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.CursorCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.group.BundleKey;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import com.samsung.android.sum.core.functional.g;
import com.samsung.scsp.media.api.d;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrivateDatabase {
    private static final boolean DEBUG = false;
    private static final Uri URI = Uri.withAppendedPath(LocalDatabase.URI, "private_album");
    static final PrivateDatabase sInstance = new PrivateDatabase();
    PrivateDatabaseHelper sqlOpenHelper;

    public PrivateDatabase() {
        if (!SeApiCompat.isMyUserIdAsUserOwner()) {
            new InternalException("PrivateDatabase is supported only on user 0. current user=" + SeApiCompat.getMyUserId()).post();
            return;
        }
        GalleryPreference.getInstanceDebug().computeBooleanIfAbsent("FixSecuredWrongOwner", new C0385u(15, this));
    }

    public static PrivateDatabase getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getNdeFile$5(String[] strArr) {
        if (strArr.length > 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getNdeFile$6(String[] strArr) {
        return strArr[1];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$parseSqlSelection$3(String[] strArr, AtomicInteger atomicInteger, HashMap hashMap, String[] strArr2) {
        String str;
        String trim = strArr2[1].trim();
        if ("?".equals(trim)) {
            str = strArr[atomicInteger.getAndIncrement()];
        } else {
            str = trim.replaceAll("'", "");
        }
        hashMap.put(strArr2[0].trim(), str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveUserTags$0(SQLiteDatabase sQLiteDatabase, String str, Object obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_tag", (String) obj);
        sQLiteDatabase.update("files", contentValues, "_data='" + str + "'", (String[]) null);
    }

    private int[] loadDataCount(boolean z) {
        Cursor rawQuery;
        int[] iArr = new int[5];
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null) {
            Log.w("PrivateDatabase", "loadDataCount skipped - database is null");
            return new int[]{0, 0};
        }
        try {
            rawQuery = sqlOpenHelper2.getReadableDatabase().rawQuery(C0212a.j(z ? 1 : 0, "SELECT media_type, count(_id) FROM files WHERE is_pending=", " GROUP BY media_type"), (String[]) null);
            if (rawQuery.moveToFirst()) {
                do {
                    int i2 = rawQuery.getInt(0);
                    if (i2 < 5) {
                        iArr[i2] = rawQuery.getInt(1);
                    }
                } while (rawQuery.moveToNext());
            }
            rawQuery.close();
        } catch (Exception e) {
            Log.e((CharSequence) "PrivateDatabase", "loadDataCount failed. e=" + e.getMessage(), Boolean.valueOf(z));
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return new int[]{iArr[1], iArr[3]};
        throw th;
    }

    public boolean backup(String str, String str2) {
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null) {
            Log.w("PrivateDatabase", "backup skipped - database helper is null");
            return false;
        }
        File file = new File(str);
        if (!makeParentIfAbsent(file)) {
            Log.e("PrivateDatabase", "backup warning. mkdir parent failed");
        } else if (!FileUtils.delete(file)) {
            Log.w("PrivateDatabase", "backup warning. delete file failed");
        }
        return sqlOpenHelper2.backup(str, str2);
    }

    public MpFilesTable buildFilesTable() {
        return buildFilesTable(false, GroupType.BURST, GroupType.SINGLE_TAKEN);
    }

    public MpFilesTable buildTrashFilesTable() {
        return buildFilesTable(true, new GroupType[0]);
    }

    public int delete(FileItemInterface[] fileItemInterfaceArr) {
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        for (FileItemInterface delete : fileItemInterfaceArr) {
            int delete2 = delete(delete);
            if (delete2 > 0) {
                i2 += delete2;
            }
        }
        a.A(new Object[]{Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder(OpenSessionApi.ITEM_LIMIT_DELETE), "PrivateDatabase");
        if (i2 > 0) {
            notifyChange();
        }
        return i2;
    }

    public boolean deleteIfPresent(String str, String str2) {
        File file;
        if (str == null) {
            file = null;
        } else {
            file = new File(str);
        }
        if (file != null && file.exists() && !file.delete()) {
            return false;
        }
        File ndeFile = getNdeFile(str2);
        if (ndeFile == null || !ndeFile.exists() || ndeFile.delete()) {
            return true;
        }
        Log.e("PrivateDatabase", "delete nde failed " + Logger.getEncodedString(ndeFile.getPath()));
        return true;
    }

    public void deleteTrashExpired() {
        Cursor query;
        long currentTimeMillis = System.currentTimeMillis();
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null) {
            Log.w("PrivateDatabase", "deleteTrashExpired skipped - database is null");
            return;
        }
        PrivateFilesTable privateFilesTable = new PrivateFilesTable(new QueryParams());
        privateFilesTable.getQueryBuilder().andCondition("A.is_pending=1");
        QueryBuilder queryBuilder = privateFilesTable.getQueryBuilder();
        queryBuilder.andCondition("A.date_deleted<" + TimeUtil.getDaysAgo(30));
        QueryBuilder queryBuilder2 = privateFilesTable.getQueryBuilder();
        queryBuilder2.clearProjection();
        queryBuilder2.addProjection("A._id, A._data, A.original_file_hash");
        try {
            query = query(new Query(queryBuilder2), "deleteTrashExpired");
            if (query != null) {
                if (query.moveToFirst()) {
                    ArrayList arrayList = new ArrayList();
                    do {
                        if (deleteIfPresent(query.getString(1), query.getString(2))) {
                            arrayList.add(Long.valueOf(query.getLong(0)));
                        }
                    } while (query.moveToNext());
                    String joinText = StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList);
                    SQLiteDatabase writableDatabase = sqlOpenHelper2.getWritableDatabase();
                    int delete = writableDatabase.delete("files", "_id in (" + joinText + ")", (String[]) null);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("deleteTrashExpired");
                    Integer valueOf = Integer.valueOf(delete);
                    Integer valueOf2 = Integer.valueOf(arrayList.size());
                    sb2.append(Logger.vt(valueOf, valueOf2, "[" + joinText + "]", Long.valueOf(currentTimeMillis)));
                    Log.d("PrivateDatabase", sb2.toString());
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            a.s(e, new StringBuilder("deleteTrashExpired failed. e="), "PrivateDatabase");
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean fixWrongOwner() {
        long currentTimeMillis = System.currentTimeMillis();
        File file = new File("/data/sec/gallery/secured");
        if (!file.exists()) {
            return false;
        }
        String owner = FileUtils.getOwner(file);
        if (!owner.startsWith("u0")) {
            boolean renameTo = file.renameTo(new File("/data/sec/gallery/secured_".concat(owner)));
            Log.e("PrivateDatabase", "fixWrongOwner" + Logger.vt(Boolean.valueOf(renameTo), owner, Long.valueOf(currentTimeMillis)));
            return true;
        }
        a.A(new Object[]{owner, Long.valueOf(currentTimeMillis)}, new StringBuilder("fixWrongOwner skip"), "PrivateDatabase");
        return false;
    }

    public File getNdeFile(String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = (String) Optional.of(str).map(new d(8)).filter(new com.samsung.android.gallery.module.dynamicview.a(18)).map(new d(9)).orElse((Object) null);
            if (!TextUtils.isEmpty(str2)) {
                return new File(KeepStorageManager.getInstance().getNdePath(), new File(str2).getName());
            }
        }
        return null;
    }

    public PrivateDatabaseHelper getSqlOpenHelper() {
        if (this.sqlOpenHelper == null && SeApiCompat.isMyUserIdAsUserOwner()) {
            this.sqlOpenHelper = PrivateDatabaseHelper.getInstance();
        }
        return this.sqlOpenHelper;
    }

    public ArrayList<Long> loadIdsInGroup(FileItemInterface[] fileItemInterfaceArr, boolean z) {
        Cursor queryGroup;
        ArrayList<Long> arrayList = new ArrayList<>();
        for (FileItemInterface fileItemInterface : fileItemInterfaceArr) {
            if (!z || !fileItemInterface.isGroupShot() || fileItemInterface.getCount() <= 1) {
                arrayList.add(Long.valueOf(fileItemInterface.getFileId()));
            } else {
                try {
                    queryGroup = queryGroup(fileItemInterface);
                    if (queryGroup != null) {
                        if (queryGroup.moveToFirst()) {
                            int columnIndex = queryGroup.getColumnIndex("__absID");
                            do {
                                arrayList.add(Long.valueOf(queryGroup.getLong(columnIndex)));
                            } while (queryGroup.moveToNext());
                        }
                    }
                    if (queryGroup != null) {
                        queryGroup.close();
                    }
                } catch (Exception unused) {
                    continue;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        return arrayList;
        throw th;
    }

    public int[] loadPrivateAlbumCount() {
        return loadDataCount(false);
    }

    public ArrayList<MediaItem> loadSharePrivateGroupItems(MediaItem[] mediaItemArr) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isSimilarShot() || mediaItem.isSingleTakenShot()) {
                Cursor queryGroup = queryGroup(mediaItem);
                if (queryGroup != null) {
                    while (queryGroup.moveToNext()) {
                        arrayList.add(MediaItemBuilder.create(queryGroup));
                    }
                    queryGroup.close();
                }
            } else {
                arrayList.add(mediaItem);
            }
        }
        a.A(new Object[]{Integer.valueOf(mediaItemArr.length), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadSharePrivateGroupItems"), "PrivateDatabase");
        return arrayList;
    }

    public int[] loadTrashCount() {
        return loadDataCount(true);
    }

    public void loadUserTags(String str, BiConsumer<String, String> biConsumer) {
        Cursor rawQuery;
        long currentTimeMillis = System.currentTimeMillis();
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null) {
            Log.w("PrivateDatabase", "loadUserTags skipped - database is null");
            return;
        }
        try {
            rawQuery = sqlOpenHelper2.getReadableDatabase().rawQuery(C0212a.m("SELECT _id, _data, user_tag FROM files WHERE _id in (", str, ")"), (String[]) null);
            int count = rawQuery.getCount();
            if (rawQuery.moveToFirst()) {
                do {
                    biConsumer.accept(rawQuery.getString(1), rawQuery.getString(2));
                } while (rawQuery.moveToNext());
            }
            Log.d("PrivateDatabase", "loadUserTags" + Logger.vt(Integer.valueOf(count), Long.valueOf(currentTimeMillis)));
            rawQuery.close();
            return;
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadUserTags failed. e="), "PrivateDatabase");
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean makeParentIfAbsent(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        if (parentFile.exists() || parentFile.mkdirs()) {
            return true;
        }
        return false;
    }

    public void notifyChange() {
        AppResources.getAppContext().getContentResolver().notifyChange(URI, (ContentObserver) null);
    }

    public HashMap<String, String> parseSqlSelection(String str, String[] strArr) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(str)) {
            Stream.of(str.split(" (?i)and ")).map(new d(10)).forEach(new g(strArr, new AtomicInteger(0), hashMap, 2));
        }
        return hashMap;
    }

    public Cursor query(Query query, String str) {
        String buildSql = query.buildSql();
        if (DEBUG) {
            Log.v("PrivateDatabase", "[QUERY:" + str + "] " + buildSql);
        }
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 != null) {
            return sqlOpenHelper2.getReadableDatabase().rawQuery(buildSql, (String[]) null);
        }
        Log.w("PrivateDatabase", "query skipped - database is null");
        return CursorCompat.EMPTY_CURSOR;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x010f A[Catch:{ all -> 0x0141, all -> 0x018d }, LOOP:0: B:47:0x010f->B:58:0x0181, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor queryBackupFiles(java.lang.String r12, java.lang.String[] r13) {
        /*
            r11 = this;
            java.lang.String r0 = "media_type"
            java.lang.String r1 = "_data"
            java.lang.String r2 = "_display_name"
            java.lang.String r3 = "_size"
            java.lang.String r4 = "mime_type"
            java.lang.String[] r0 = new java.lang.String[]{r1, r2, r3, r4, r0}
            android.database.MatrixCursor r1 = new android.database.MatrixCursor
            r1.<init>(r0)
            com.samsung.android.gallery.module.secured.PrivateDatabaseHelper r2 = r11.getSqlOpenHelper()
            if (r2 != 0) goto L_0x0021
            java.lang.String r11 = "PrivateDatabase"
            java.lang.String r12 = "queryBackupFiles skipped - database is null"
            com.samsung.android.gallery.support.utils.Log.w(r11, r12)
            return r1
        L_0x0021:
            com.samsung.android.gallery.module.secured.KeepStorageManager r3 = com.samsung.android.gallery.module.secured.KeepStorageManager.getInstance()
            java.lang.String r3 = r3.getNdePath()
            com.samsung.android.gallery.module.secured.KeepStorageManager r5 = com.samsung.android.gallery.module.secured.KeepStorageManager.getInstance()
            r5.getRoot()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.HashMap r11 = r11.parseSqlSelection(r12, r13)
            java.lang.Object r12 = r11.get(r4)
            java.lang.String r12 = (java.lang.String) r12
            java.util.Optional r12 = java.util.Optional.ofNullable(r12)
            com.samsung.scsp.media.api.d r13 = new com.samsung.scsp.media.api.d
            r4 = 7
            r13.<init>(r4)
            java.util.Optional r12 = r12.map(r13)
            r13 = 0
            java.lang.Object r12 = r12.orElse(r13)
            java.lang.String r12 = (java.lang.String) r12
            if (r12 != 0) goto L_0x0058
        L_0x0056:
            r12 = r13
            goto L_0x006e
        L_0x0058:
            java.lang.String r4 = "image/"
            boolean r4 = r12.startsWith(r4)
            if (r4 == 0) goto L_0x0063
            java.lang.String r12 = "media_type=1"
            goto L_0x006e
        L_0x0063:
            java.lang.String r4 = "video/"
            boolean r12 = r12.startsWith(r4)
            if (r12 == 0) goto L_0x0056
            java.lang.String r12 = "media_type=3"
        L_0x006e:
            boolean r4 = android.text.TextUtils.isEmpty(r12)
            if (r4 != 0) goto L_0x0077
            r5.add(r12)
        L_0x0077:
            java.lang.String r12 = "dir"
            java.lang.Object r11 = r11.get(r12)
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = "%"
            java.lang.String r4 = "*"
            java.lang.String r6 = "all"
            r7 = 0
            r8 = 1
            if (r11 == 0) goto L_0x00a6
            java.lang.String r9 = "files"
            boolean r9 = r9.equalsIgnoreCase(r11)
            if (r9 != 0) goto L_0x00a6
            boolean r9 = r6.equals(r11)
            if (r9 != 0) goto L_0x00a6
            boolean r9 = r4.equals(r11)
            if (r9 != 0) goto L_0x00a6
            boolean r9 = r12.equals(r11)
            if (r9 == 0) goto L_0x00a4
            goto L_0x00a6
        L_0x00a4:
            r9 = r7
            goto L_0x00a7
        L_0x00a6:
            r9 = r8
        L_0x00a7:
            if (r11 == 0) goto L_0x00c6
            java.lang.String r10 = "nde"
            boolean r10 = r10.equalsIgnoreCase(r11)
            if (r10 != 0) goto L_0x00c6
            boolean r6 = r6.equals(r11)
            if (r6 != 0) goto L_0x00c6
            boolean r4 = r4.equals(r11)
            if (r4 != 0) goto L_0x00c6
            boolean r11 = r12.equals(r11)
            if (r11 == 0) goto L_0x00c4
            goto L_0x00c6
        L_0x00c4:
            r11 = r7
            goto L_0x00c7
        L_0x00c6:
            r11 = r8
        L_0x00c7:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r4 = "select "
            r12.<init>(r4)
            java.lang.String r4 = ","
            java.lang.String r0 = java.lang.String.join(r4, r0)
            r12.append(r0)
            java.lang.String r0 = ",original_file_hash from files"
            r12.append(r0)
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x00e6
            java.lang.String r0 = ""
            goto L_0x00fa
        L_0x00e6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = " where "
            r0.<init>(r4)
            java.lang.String r4 = "AND"
            java.lang.String r4 = java.lang.String.join(r4, r5)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
        L_0x00fa:
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            android.database.sqlite.SQLiteDatabase r0 = r2.getReadableDatabase()
            android.database.Cursor r12 = r0.rawQuery(r12, r13)
            boolean r13 = r12.moveToFirst()     // Catch:{ all -> 0x0141 }
            if (r13 == 0) goto L_0x0183
        L_0x010f:
            r13 = 5
            java.lang.Object[] r0 = new java.lang.Object[r13]     // Catch:{ all -> 0x0141 }
            java.lang.String r2 = r12.getString(r7)     // Catch:{ all -> 0x0141 }
            r0[r7] = r2     // Catch:{ all -> 0x0141 }
            java.lang.String r2 = r12.getString(r8)     // Catch:{ all -> 0x0141 }
            r0[r8] = r2     // Catch:{ all -> 0x0141 }
            r2 = 2
            long r4 = r12.getLong(r2)     // Catch:{ all -> 0x0141 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0141 }
            r0[r2] = r4     // Catch:{ all -> 0x0141 }
            r4 = 3
            java.lang.String r5 = r12.getString(r4)     // Catch:{ all -> 0x0141 }
            r0[r4] = r5     // Catch:{ all -> 0x0141 }
            r4 = 4
            int r5 = r12.getInt(r4)     // Catch:{ all -> 0x0141 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0141 }
            r0[r4] = r5     // Catch:{ all -> 0x0141 }
            if (r9 == 0) goto L_0x0143
            r1.addRow(r0)     // Catch:{ all -> 0x0141 }
            goto L_0x0143
        L_0x0141:
            r11 = move-exception
            goto L_0x0187
        L_0x0143:
            java.lang.String r13 = r12.getString(r13)     // Catch:{ all -> 0x0141 }
            if (r11 == 0) goto L_0x017d
            boolean r4 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x0141 }
            if (r4 != 0) goto L_0x017d
            java.lang.String r4 = java.io.File.separator     // Catch:{ all -> 0x0141 }
            int r5 = r13.lastIndexOf(r4)     // Catch:{ all -> 0x0141 }
            int r5 = r5 + r8
            java.lang.String r13 = r13.substring(r5)     // Catch:{ all -> 0x0141 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0141 }
            r5.<init>()     // Catch:{ all -> 0x0141 }
            r5.append(r3)     // Catch:{ all -> 0x0141 }
            r5.append(r4)     // Catch:{ all -> 0x0141 }
            r5.append(r13)     // Catch:{ all -> 0x0141 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0141 }
            r0[r7] = r4     // Catch:{ all -> 0x0141 }
            r0[r8] = r13     // Catch:{ all -> 0x0141 }
            long r4 = com.samsung.android.gallery.support.utils.FileUtils.length(r4)     // Catch:{ all -> 0x0141 }
            java.lang.Long r13 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0141 }
            r0[r2] = r13     // Catch:{ all -> 0x0141 }
            r1.addRow(r0)     // Catch:{ all -> 0x0141 }
        L_0x017d:
            boolean r13 = r12.moveToNext()     // Catch:{ all -> 0x0141 }
            if (r13 != 0) goto L_0x010f
        L_0x0183:
            r12.close()
            return r1
        L_0x0187:
            if (r12 == 0) goto L_0x0191
            r12.close()     // Catch:{ all -> 0x018d }
            goto L_0x0191
        L_0x018d:
            r12 = move-exception
            r11.addSuppressed(r12)
        L_0x0191:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.secured.PrivateDatabase.queryBackupFiles(java.lang.String, java.lang.String[]):android.database.Cursor");
    }

    public Cursor queryData() {
        return query(buildFilesTable().buildSelectQuery(), "queryData");
    }

    public Cursor queryDays() {
        MpFilesTable buildFilesTable = buildFilesTable();
        buildFilesTable.modifyForTimelineDateData(DateType.DAY, IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME);
        return query(buildFilesTable.buildSelectQuery(), "queryDays");
    }

    public Cursor queryGroup(FileItemInterface fileItemInterface) {
        return queryGroup(fileItemInterface.getAlbumID(), fileItemInterface.getGroupType(), fileItemInterface.getGroupMediaId());
    }

    public Cursor queryRealRatio() {
        return queryRealRatio(buildFilesTable());
    }

    public Cursor queryTrashData() {
        return query(buildTrashFilesTable().buildSelectQuery(), "queryTrashData");
    }

    public Cursor queryTrashRealRatio() {
        return queryRealRatio(buildTrashFilesTable());
    }

    public void registerObserver(ContentObserver contentObserver) {
        AppResources.getAppContext().getContentResolver().registerContentObserver(URI, false, contentObserver);
    }

    public boolean restore(String str, String str2) {
        if (!FileUtils.exists(str)) {
            Log.e("PrivateDatabase", "restore skip. no file exists");
            return false;
        }
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null || !sqlOpenHelper2.restore(str, str2)) {
            return false;
        }
        return true;
    }

    public void saveUserTags(HashMap<String, Object> hashMap) {
        if (hashMap != null && !hashMap.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
            if (sqlOpenHelper2 == null) {
                Log.w("PrivateDatabase", "saveUserTags skipped - database is null");
                return;
            }
            hashMap.forEach(new h(29, sqlOpenHelper2.getWritableDatabase()));
            a.A(new Object[]{Integer.valueOf(hashMap.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("saveUserTags"), "PrivateDatabase");
        }
    }

    public int trash(FileItemInterface[] fileItemInterfaceArr, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<Long> loadIdsInGroup = loadIdsInGroup(fileItemInterfaceArr, z);
        int i2 = 0;
        if (!loadIdsInGroup.isEmpty()) {
            PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
            if (sqlOpenHelper2 == null) {
                Log.w("PrivateDatabase", "trash skipped - database is null");
                return 0;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_pending", 1);
            contentValues.put("date_deleted", Long.valueOf(currentTimeMillis));
            i2 = sqlOpenHelper2.getWritableDatabase().update("files", contentValues, C0212a.m("_id in (", (String) loadIdsInGroup.stream().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), ")"), (String[]) null);
        }
        Log.d("PrivateDatabase", "trash" + Logger.vt(Integer.valueOf(fileItemInterfaceArr.length), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        deleteInstance.insertLog("[MOVE_TO_PRIVATE_TRASH] total=" + loadIdsInGroup.size() + ",success=" + i2 + ",input=" + fileItemInterfaceArr.length + ",id=[" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, loadIdsInGroup.stream().limit(30).iterator()) + "]");
        if (i2 > 0) {
            notifyChange();
        }
        return i2;
    }

    public void unregisterObserver(ContentObserver contentObserver) {
        AppResources.getAppContext().getContentResolver().unregisterContentObserver(contentObserver);
    }

    public int untrash(FileItemInterface[] fileItemInterfaceArr) {
        if (fileItemInterfaceArr == null || fileItemInterfaceArr.length == 0) {
            Log.d("PrivateDatabase", "untrash skip. empty");
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null) {
            Log.w("PrivateDatabase", "untrash skipped - database is null");
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_pending", 0);
        contentValues.put("date_deleted", 0);
        List list = (List) Arrays.stream(fileItemInterfaceArr).map(new e(26)).collect(Collectors.toList());
        int update = sqlOpenHelper2.getWritableDatabase().update("files", contentValues, "is_pending=1 AND _id in (" + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list) + ")", (String[]) null);
        StringBuilder sb2 = new StringBuilder("untrash");
        sb2.append(Logger.vt(Integer.valueOf(update), Long.valueOf(currentTimeMillis)));
        Log.d("PrivateDatabase", sb2.toString());
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        StringBuilder sb3 = new StringBuilder("[RESTORE_FROM_PRIVATE_TRASH] total=");
        j.x(sb3, fileItemInterfaceArr.length, ",success=", update, ",id=[");
        sb3.append(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list.stream().limit(30).iterator()));
        sb3.append("]");
        deleteInstance.insertLog(sb3.toString());
        if (update > 0) {
            notifyChange();
        }
        return update;
    }

    public void updateMpViewMode(long j2, int i2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
            if (sqlOpenHelper2 == null) {
                Log.w("PrivateDatabase", "updateMpViewMode skipped - database is null");
                return;
            }
            SQLiteDatabase writableDatabase = sqlOpenHelper2.getWritableDatabase();
            writableDatabase.execSQL("UPDATE files SET motionphoto_viewmode=" + i2 + " WHERE _id=" + j2);
            StringBuilder sb2 = new StringBuilder("updateMpViewMode");
            sb2.append(Logger.vt(Long.valueOf(j2), Long.valueOf(currentTimeMillis)));
            Log.d("PrivateDatabase", sb2.toString());
        } catch (Exception e) {
            a.s(e, new StringBuilder("updateMpViewMode failed. e="), "PrivateDatabase");
        }
    }

    public long upsert(FileItemInterface fileItemInterface, String str) {
        String path = fileItemInterface.getPath();
        if ((path == null || fileItemInterface.isCloudOnly() || path.startsWith("/data/sec/cloud/")) && (path = fileItemInterface.getCloudServerPath()) == null) {
            path = "/storage/emulated/0/Download/" + fileItemInterface.getTitle();
        }
        ContentValues c5 = C0086a.c("_data", str);
        c5.put(IParameterKey.SIZE, Long.valueOf(fileItemInterface.getFileSize()));
        c5.put("mime_type", fileItemInterface.getMimeType());
        c5.put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(fileItemInterface.getMediaType().toInt()));
        c5.put("orientation", Integer.valueOf(fileItemInterface.getOrientation()));
        c5.put("orientation_tag", Integer.valueOf(fileItemInterface.getOrientationTag()));
        c5.put("width", Integer.valueOf(fileItemInterface.getWidth()));
        c5.put("height", Integer.valueOf(fileItemInterface.getHeight()));
        c5.put(EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION, fileItemInterface.getResolution());
        if (fileItemInterface.isGroupShot()) {
            c5.put(BundleKey.GROUP_TYPE, Integer.valueOf(fileItemInterface.getGroupType()));
            c5.put(com.samsung.android.sdk.mobileservice.social.share.BundleKey.GROUP_ID, Long.valueOf(fileItemInterface.getGroupMediaId()));
            c5.put("burst_group_id", Long.valueOf(fileItemInterface.getGroupMediaId()));
            c5.put("best_image", Integer.valueOf(fileItemInterface.getBestImage()));
        }
        c5.put("_display_name", fileItemInterface.getTitle());
        c5.put("bucket_id", Integer.valueOf(fileItemInterface.getBucketID()));
        c5.put("bucket_display_name", FileUtils.getBucketNameFromPath(path));
        c5.put("date_added", Long.valueOf(fileItemInterface.getDateAdded()));
        c5.put(IParameterKey.DATE_MODIFIED, Long.valueOf(fileItemInterface.getDateModified()));
        c5.put(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME, Long.valueOf(fileItemInterface.getDateTaken()));
        c5.put("sef_file_type", Integer.valueOf(fileItemInterface.getSefFileType()));
        c5.put("sef_file_sub_type", Integer.valueOf(fileItemInterface.getSefFileSubType()));
        c5.put("sef_file_types", fileItemInterface.getSefFileTypes());
        c5.put("latitude", Double.valueOf(fileItemInterface.getLatitude()));
        c5.put("longitude", Double.valueOf(fileItemInterface.getLongitude()));
        if (fileItemInterface.isVideo()) {
            VideoPropData of2 = VideoPropData.of(fileItemInterface);
            if (fileItemInterface.is360Video()) {
                c5.put("is_360_video", 1);
            }
            c5.put("recordingtype", Integer.valueOf(fileItemInterface.getRecordingType()));
            c5.put("recording_mode", Integer.valueOf(fileItemInterface.getRecordingMode()));
            c5.put("duration", Integer.valueOf(fileItemInterface.getFileDuration()));
            c5.put("audio_codec_info", of2.audioCodec);
            c5.put("video_codec_info", of2.videoCodec);
            c5.put("capture_framerate", Double.valueOf(of2.videoCaptureFrameRate));
            c5.put("is_hdr10_video", Integer.valueOf(of2.hdr10Video));
            c5.put("color_transfer", Integer.valueOf(of2.colorTransfer));
        }
        c5.put("is_favorite", Boolean.valueOf(fileItemInterface.isFavourite()));
        c5.put("owner_package_name", fileItemInterface.getOwnerPackage());
        DetailsData of3 = DetailsData.of(fileItemInterface);
        c5.put("captured_url", of3.capturedUrl);
        c5.put("captured_app", of3.capturedApp);
        String str2 = of3.capturedPath;
        if (str2 != null) {
            c5.put("captured_original_path", str2);
        }
        c5.put("original_path", path);
        c5.put("cam_model", fileItemInterface.getCamModel());
        MotionPhotoViewMode motionPhotoViewMode = of3.motionPhotoViewMode;
        if (motionPhotoViewMode != null) {
            c5.put("motionphoto_viewmode", Integer.valueOf(motionPhotoViewMode.ordinal()));
        }
        String originalFileHash = fileItemInterface.getOriginalFileHash();
        String computePath = TextUtils.isEmpty(originalFileHash) ? null : NonDestructionManager.getInstance().computePath(originalFileHash);
        if (!TextUtils.isEmpty(computePath)) {
            c5.put("original_file_hash", originalFileHash + GlobalPostProcInternalPPInterface.SPLIT_REGEX + computePath);
        }
        try {
            PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
            if (sqlOpenHelper2 != null) {
                return sqlOpenHelper2.getWritableDatabase().insertWithOnConflict("files", (String) null, c5, 2);
            }
            Log.w("PrivateDatabase", "upsert skipped - database is null");
            return 0;
        } catch (SQLiteException e) {
            Log.e("PrivateDatabase", "write failed. e=" + e.getMessage());
            return 0;
        }
    }

    public MpFilesTable buildFilesTable(boolean z, GroupType... groupTypeArr) {
        QueryParams queryParams = new QueryParams();
        if (groupTypeArr != null && groupTypeArr.length > 0) {
            queryParams.setGroupTypes(groupTypeArr);
        }
        PrivateFilesTable privateFilesTable = new PrivateFilesTable(queryParams);
        if (groupTypeArr != null && groupTypeArr.length > 0) {
            privateFilesTable.filterGroupMediaBest(true);
        }
        if (z) {
            privateFilesTable.getQueryBuilder().andCondition("A.is_pending=1");
            QueryBuilder queryBuilder = privateFilesTable.getQueryBuilder();
            queryBuilder.andCondition("A.date_deleted>=" + TimeUtil.getDaysAgo(30));
            privateFilesTable.addProjection("date_deleted", "__deleteTime");
            privateFilesTable.addProjection("''", "__restoreExtra");
            privateFilesTable.addProjection("31", "__expiredPeriod");
            return privateFilesTable;
        }
        privateFilesTable.getQueryBuilder().andCondition("A.is_pending=0");
        return privateFilesTable;
    }

    public Cursor queryGroup(int i2, int i7, long j2) {
        PrivateFilesTable privateFilesTable = new PrivateFilesTable(new QueryParams().setGroupTypes(i7 == 1 ? GroupType.BURST : i7 == 3 ? GroupType.SINGLE_TAKEN : null).setShowHidden(true).setCloudSync(false).setStorageTypes(QueryParams.DbStorageType.LocalOnly));
        privateFilesTable.filterAlbumID(i2);
        privateFilesTable.filterGroupMediaId(i7, j2);
        privateFilesTable.addGroupMediaCountProjection();
        privateFilesTable.getQueryBuilder().andCondition("A.is_pending=0");
        return query(privateFilesTable.buildSelectQuery(), "queryGroupItems");
    }

    public Cursor queryRealRatio(MpFilesTable mpFilesTable) {
        QueryBuilder queryBuilder = mpFilesTable.getQueryBuilder();
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id, A.width, A.height, A.orientation");
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(queryBuilder.build());
        addFromSelect.addProjection("count(*)", "__count");
        addFromSelect.addProjection("group_concat(case when height is null or height=0 or width is null or width=0 then 1 else substr(case when orientation=90 or orientation=270 then 1.0*height/width else 1.0*width/height end,1,4) end)", "__widthList");
        return query(new Query(addFromSelect), "queryRealRatio");
    }

    public int delete(FileItemInterface fileItemInterface) {
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 == null) {
            Log.w("PrivateDatabase", "delete skipped - database helper is null");
            return 0;
        } else if (deleteIfPresent(fileItemInterface.getPath(), fileItemInterface.getOriginalFileHash())) {
            SQLiteDatabase writableDatabase = sqlOpenHelper2.getWritableDatabase();
            return writableDatabase.delete("files", "_id=" + fileItemInterface.getFileId(), (String[]) null);
        } else {
            Log.e("PrivateDatabase", "delete failed" + Logger.v(Long.valueOf(fileItemInterface.getFileId()), Boolean.valueOf(FileUtils.exists(fileItemInterface.getPath()))));
            return 0;
        }
    }

    public int delete(long j2) {
        PrivateDatabaseHelper sqlOpenHelper2 = getSqlOpenHelper();
        if (sqlOpenHelper2 != null) {
            return sqlOpenHelper2.getWritableDatabase().delete("files", a.f("_id=", j2), (String[]) null);
        }
        Log.w("PrivateDatabase", "delete skipped - database is null");
        return 0;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivateFilesTable extends MpFilesTable {
        public PrivateFilesTable(QueryParams queryParams) {
            super(queryParams);
            this.mQueryBuilder.replaceProjectionByAlias("A._data", "__absPath");
            this.mQueryBuilder.removeProjectionByAlias("__fileMediaId", "__draftPath", "__media_cache", "__isDrm", "__capturedPATH", "__storageType", "__origin_file_hash", "__originPath", "__analyze_info", "local_time");
            this.mQueryBuilder.addProjection("A.original_path", "__originPath");
            this.mQueryBuilder.addProjection("concat_ws(',','vm='||A.motionphoto_viewmode,'cc='||A.ai_cache)", "__analyze_info");
            this.mQueryBuilder.addProjection("A.original_file_hash", "__origin_file_hash");
            this.mQueryBuilder.addProjection("A.captured_original_path", "__capturedPATH");
            this.mQueryBuilder.addProjection(String.valueOf(StorageType.PrivateItem.toInt()), "__storageType");
        }

        public void setDefaultCondition() {
            filterGalleryMedia(this.mParams.getMediaTypeFilter());
        }

        public void addProjectionCloud() {
        }

        public void addProjectionDynamicViewInfo() {
        }

        public void addProjectionRelativePath() {
        }

        public void addRevitalizeProjection() {
        }
    }
}
