package com.samsung.android.gallery.module.debugger;

import A4.I;
import N2.j;
import U5.b;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.CancellationSignal;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalProviderDebugHelper {
    private final SQLiteOpenHelper mSQLiteHelper;

    public LocalProviderDebugHelper(Context context) {
        this.mSQLiteHelper = LocalDatabaseHelper.getInstance(context);
    }

    public static void addOperationHistory(HistoryTable.ActionKeyword actionKeyword, Collection<FileItemInterface> collection) {
        try {
            String buildIdList = buildIdList(collection, actionKeyword.getConsumer());
            if (!TextUtils.isEmpty(buildIdList)) {
                new LocalProviderDebugHelper(AppResources.getAppContext()).insertOperationHistory(actionKeyword, buildIdList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String buildIdList(Collection<FileItemInterface> collection, Function<FileItemInterface, Long> function) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        collection.stream().filter(new I(11, function)).limit(100).forEach(new b(16, stringJoiner, function));
        return stringJoiner.toString();
    }

    public static void dump(Context context, String str) {
        dumpTable(context, str, "album", LocalDatabase.ALBUM_URI);
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            dumpTable(context, str, "mxalbum", LocalDatabase.MX_ALBUM_URI);
        }
        dumpTable(context, str, "trash", LocalDatabase.TRASH_URI);
        dumpTable(context, str, "suggested", LocalDatabase.SUGGEST_URI);
        dumpTable(context, str, "search_history", LocalDatabase.SEARCH_HISTORY_URI);
        dumpTable(context, str, "cache", LocalDatabase.GALLERY_CACHE_URI);
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            dumpTable(context, str, "my_query", LocalDatabase.MY_QUERY_URI);
        }
    }

    private static void dumpTable(Context context, String str, String str2, Uri uri) {
        Throwable th;
        Cursor rawQuery = LocalDatabaseHelper.getInstance(context).getReadableDatabase().rawQuery(RawQueryExecutor.getSchemaQuery(str2), (String[]) null);
        try {
            CursorHelper.dumpTableToFile(context, uri, str2, str, "", rawQuery);
            if (rawQuery != null) {
                rawQuery.close();
                return;
            }
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static Cursor getBucketCountCursorSecMp() {
        return new SecMpQueryExecutor().rawQuery("select rtrim(IFNULL(_data,_data2),_display_name),bucket_id,count(),count(is_trashed) from files where 1 group by bucket_id limit 100", "getBucketCountCursorSecMp");
    }

    public static Cursor getCountCursorGedMpQ() {
        Context appContext = AppResources.getAppContext();
        if (appContext == null) {
            return null;
        }
        return appContext.getContentResolver().query(MediaUri.getInstance().getFilesUri(), new String[]{"volume_name", "is_cloud", ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "count()", "_data"}, "1) GROUP BY volume_name,is_cloud,(media_type", (String[]) null, (String) null, (CancellationSignal) null);
    }

    public static Cursor getCountCursorNewMp() {
        return new SecMpQueryExecutor().rawQuery("select is_cloud,media_type,count() from files where 1 group by media_type,is_cloud", "getCountCursorNewMp");
    }

    public static Cursor getCountCursorSecMp() {
        return new SecMpQueryExecutor().rawQuery("select volume_name,is_cloud,media_type,count() from files where 1 group by volume_name,is_cloud,media_type", "getCountCursorSecMp");
    }

    private Cursor getCountHistory(Context context) {
        return LocalDatabaseHelper.getInstance(context).getReadableDatabase().rawQuery("select * from count_history limit 0,30", (String[]) null);
    }

    private static String getEssentialFacesInfo() {
        Cursor rawQuery;
        StringBuilder sb2 = new StringBuilder();
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
        StringBuilder sb3 = new StringBuilder("SELECT \n  COUNT(CASE WHEN (essential_group = 0 AND unique_days >= 5 OR essential_group > 0) AND group_type = 0 THEN 1 END) AS __peopleEssential,\n  COUNT(CASE WHEN (essential_group = 0 AND unique_days < 5 OR essential_group < 0) AND group_type = 0 THEN 1 END) AS __peopleHidden");
        long currentTimeMillis = System.currentTimeMillis();
        if (isEnabled) {
            sb3.append(",\n  COUNT(CASE WHEN (essential_group = 0 AND unique_days >= 5 OR essential_group > 0) AND group_type = 1 THEN 1 END) AS __petsEssential,\n  COUNT(CASE WHEN (essential_group = 0 AND unique_days < 5 OR essential_group < 0) AND group_type = 1 THEN 1 END) AS __petsHidden");
        }
        sb3.append(" FROM faces_group");
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery(sb3.toString(), "getEssentialFacesInfo");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    int i2 = rawQuery.getInt(rawQuery.getColumnIndex("__peopleEssential"));
                    int i7 = rawQuery.getInt(rawQuery.getColumnIndex("__peopleHidden"));
                    sb2.append("People(E/H) : " + i2 + "/" + i7 + "\n");
                    if (isEnabled) {
                        int i8 = rawQuery.getInt(rawQuery.getColumnIndex("__petsEssential"));
                        int i10 = rawQuery.getInt(rawQuery.getColumnIndex("__petsHidden"));
                        sb2.append("Pets  (E/H) : " + i8 + "/" + i10);
                        StringBuilder sb4 = new StringBuilder(" +");
                        sb4.append(System.currentTimeMillis() - currentTimeMillis);
                        sb2.append(sb4.toString());
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (SQLiteException e) {
            Log.e("LocalProviderHelper", "write failed. e=" + e.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return sb2.toString();
        throw th;
    }

    private Cursor getLogCursor(String str) {
        return this.mSQLiteHelper.getReadableDatabase().rawQuery(j.f(new StringBuilder("select "), String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, new String[]{"case __category when 1 then 'decodeFail' when 2 then 'FileOp' when 3 then 'version' when 4 then 'mde' when 5 then 'generic' when 6 then 'mtp' when 7 then 'locEdit' when 8 then 'dtEdit' when 9 then 'ppp' when 10 then 'recap' else __category end as __category", "__timestamp", "__log"}), " from log where ", str), (String[]) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$buildIdList$0(Function function, FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || ((Long) function.apply(fileItemInterface)).longValue() == -1) {
            return false;
        }
        return true;
    }

    public static void writeViewHistory(List<Long> list) {
        if (!list.isEmpty()) {
            try {
                System.currentTimeMillis();
                String join = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, list);
                if (!TextUtils.isEmpty(join)) {
                    new LocalProviderDebugHelper(AppResources.getAppContext()).insertOperationHistory(HistoryTable.ActionKeyword.VIEW, join);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:129:0x01db */
    /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x015b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dumpLog(android.content.Context r8, java.io.PrintWriter r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Quram enabled:"
            if (r9 != 0) goto L_0x000c
            java.lang.String r7 = "LocalProviderHelper"
            java.lang.String r8 = "dumpLog skip for null writer"
            com.samsung.android.gallery.support.utils.Log.e(r7, r8)
            return
        L_0x000c:
            com.samsung.android.gallery.support.config.SdkConfig$GED r1 = com.samsung.android.gallery.support.config.SdkConfig.GED.Q
            boolean r1 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r1)
            com.samsung.android.gallery.support.config.SdkConfig$GED r2 = com.samsung.android.gallery.support.config.SdkConfig.GED.R
            boolean r2 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r2)
            com.samsung.android.gallery.support.config.SdkConfig$GED r3 = com.samsung.android.gallery.support.config.SdkConfig.GED.V
            boolean r3 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r3)
            java.lang.String r4 = com.samsung.android.gallery.support.utils.TimeUtil.getTimestamp()     // Catch:{ Exception -> 0x00c8 }
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = "== counts =="
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            r4 = 1
            if (r1 == 0) goto L_0x006f
            android.database.Cursor r5 = getCountCursorSecMp()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursorAndClose(r5, r4)     // Catch:{ Exception -> 0x00c8 }
            r9.print(r5)     // Catch:{ Exception -> 0x00c8 }
            android.database.Cursor r5 = getCountCursorGedMpQ()     // Catch:{ Exception -> 0x005e }
            java.lang.String r6 = "== counts(ged) =="
            r9.println(r6)     // Catch:{ all -> 0x004b }
            if (r5 == 0) goto L_0x004d
            java.lang.String r6 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursor(r5)     // Catch:{ all -> 0x004b }
            r9.println(r6)     // Catch:{ all -> 0x004b }
            goto L_0x004d
        L_0x004b:
            r6 = move-exception
            goto L_0x0053
        L_0x004d:
            if (r5 == 0) goto L_0x005e
            r5.close()     // Catch:{ Exception -> 0x005e }
            goto L_0x005e
        L_0x0053:
            if (r5 == 0) goto L_0x005d
            r5.close()     // Catch:{ all -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r5 = move-exception
            r6.addSuppressed(r5)     // Catch:{ Exception -> 0x005e }
        L_0x005d:
            throw r6     // Catch:{ Exception -> 0x005e }
        L_0x005e:
            java.lang.String r5 = "== counts bucket limit 100 =="
            r9.println(r5)     // Catch:{ Exception -> 0x00c8 }
            android.database.Cursor r5 = getBucketCountCursorSecMp()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursorAndClose(r5, r4)     // Catch:{ Exception -> 0x00c8 }
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            goto L_0x007a
        L_0x006f:
            android.database.Cursor r5 = getCountCursorNewMp()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursorAndClose(r5, r4)     // Catch:{ Exception -> 0x00c8 }
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
        L_0x007a:
            java.lang.String r4 = "== last counts =="
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceDebug()     // Catch:{ Exception -> 0x00c8 }
            com.samsung.android.gallery.support.utils.PreferenceName r5 = com.samsung.android.gallery.support.utils.PreferenceName.LAST_FILE_COUNT     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = r5.key()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = r4.loadAllPrefix(r5)     // Catch:{ Exception -> 0x00c8 }
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceDebug()     // Catch:{ Exception -> 0x00c8 }
            com.samsung.android.gallery.support.utils.PreferenceName r5 = com.samsung.android.gallery.support.utils.PreferenceName.LAST_ALBUM_COUNT     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = r5.key()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = r4.loadAllPrefix(r5)     // Catch:{ Exception -> 0x00c8 }
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = "== count history =="
            r9.println(r4)     // Catch:{ Exception -> 0x00c8 }
            android.database.Cursor r4 = r7.getCountHistory(r8)     // Catch:{ Exception -> 0x00c8 }
            r5 = 0
            java.lang.String r5 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursor(r4, r5)     // Catch:{ all -> 0x00bc }
            java.lang.String r5 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r5)     // Catch:{ all -> 0x00bc }
            r9.println(r5)     // Catch:{ all -> 0x00bc }
            if (r4 == 0) goto L_0x00c8
            r4.close()     // Catch:{ Exception -> 0x00c8 }
            goto L_0x00c8
        L_0x00bc:
            r5 = move-exception
            if (r4 == 0) goto L_0x00c7
            r4.close()     // Catch:{ all -> 0x00c3 }
            goto L_0x00c7
        L_0x00c3:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch:{ Exception -> 0x00c8 }
        L_0x00c7:
            throw r5     // Catch:{ Exception -> 0x00c8 }
        L_0x00c8:
            java.lang.String r4 = "== recent =="
            r9.println(r4)     // Catch:{ Exception -> 0x00fb }
            r4 = 0
            if (r3 == 0) goto L_0x00d3
            java.lang.String r1 = "SELECT _id,media_id,bucket_id, media_type,is_pending,orientation,_size,is_cloud,is_hide,datetaken,datetime,date_added,localtime,sef_file_type,cloud_thumb_path,_data as dx,_data_draft as cx,owner_package_name from files order by _id desc limit 0,30"
            goto L_0x00e1
        L_0x00d3:
            if (r2 == 0) goto L_0x00d8
            java.lang.String r1 = "SELECT _id,media_id, media_type,is_pending,orientation,_size,is_cloud,is_hide,datetaken,datetime,date_added,sef_file_type,cloud_thumb_path,_data as dx,owner_package_name from files order by _id desc limit 0,30"
            goto L_0x00e1
        L_0x00d8:
            if (r1 == 0) goto L_0x00dd
            java.lang.String r1 = "SELECT _id,media_id, media_type,orientation, _size,is_cloud,is_hide,datetaken,datetime,date_added,sef_file_type,cloud_thumb_path,_data as dx from files order by _id desc limit 0,30"
            goto L_0x00e1
        L_0x00dd:
            java.lang.String r1 = "SELECT _id,_data as dx,media_type,_size,datetaken,is_cloud,cloud_thumb_path,is_hide,date_added from files order by _id desc limit 0,10"
            java.lang.String r4 = "SELECT _id,_data as dx,media_type,_size,datetaken,is_cloud,cloud_thumb_path,is_hide,date_added from files where media_type in (1,3) order by _id desc limit 0,10"
        L_0x00e1:
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r2 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00fb }
            r2.<init>()     // Catch:{ Exception -> 0x00fb }
            java.lang.String r1 = r2.logQuery(r1)     // Catch:{ Exception -> 0x00fb }
            r9.print(r1)     // Catch:{ Exception -> 0x00fb }
            if (r4 == 0) goto L_0x00fb
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r1 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00fb }
            r1.<init>()     // Catch:{ Exception -> 0x00fb }
            java.lang.String r1 = r1.logQuery(r4)     // Catch:{ Exception -> 0x00fb }
            r9.print(r1)     // Catch:{ Exception -> 0x00fb }
        L_0x00fb:
            com.samsung.android.gallery.support.utils.Features r1 = com.samsung.android.gallery.support.utils.Features.SUPPORT_CLUSTER_TABLE
            boolean r1 = com.samsung.android.gallery.support.utils.Features.isEnabled(r1)
            if (r1 == 0) goto L_0x0117
            java.lang.String r1 = "== similar =="
            r9.println(r1)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r1 = "select count(distinct group_id) as group_count,count(*) as total from group_contents"
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r2 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x0117 }
            r2.<init>()     // Catch:{ Exception -> 0x0117 }
            java.lang.String r1 = r2.logQuery(r1)     // Catch:{ Exception -> 0x0117 }
            r9.print(r1)     // Catch:{ Exception -> 0x0117 }
        L_0x0117:
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71
            if (r1 == 0) goto L_0x0185
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE
            if (r1 == 0) goto L_0x012b
            java.lang.String r1 = "== local Search : Top5 =="
            r9.println(r1)
            java.util.ArrayList r1 = com.samsung.android.gallery.module.myquery.MyQueryUtil.getTop5List()
            r9.println(r1)
        L_0x012b:
            com.samsung.android.gallery.module.myquery.SearchMyQuery r1 = com.samsung.android.gallery.module.myquery.SearchMyQuery.getInstance()     // Catch:{ Exception -> 0x015b }
            r2 = 20
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x015b }
            android.database.Cursor r1 = r1.getMyQueryCursor(r2)     // Catch:{ Exception -> 0x015b }
            java.lang.String r2 = "== local Search : my query =="
            r9.println(r2)     // Catch:{ all -> 0x014f }
            java.lang.String r2 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursor(r1)     // Catch:{ all -> 0x014f }
            java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r2)     // Catch:{ all -> 0x014f }
            r9.println(r2)     // Catch:{ all -> 0x014f }
            if (r1 == 0) goto L_0x015b
            r1.close()     // Catch:{ Exception -> 0x015b }
            goto L_0x015b
        L_0x014f:
            r2 = move-exception
            if (r1 == 0) goto L_0x015a
            r1.close()     // Catch:{ all -> 0x0156 }
            goto L_0x015a
        L_0x0156:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ Exception -> 0x015b }
        L_0x015a:
            throw r2     // Catch:{ Exception -> 0x015b }
        L_0x015b:
            com.samsung.android.gallery.module.search.history.SearchHistory r1 = com.samsung.android.gallery.module.search.history.SearchHistory.getInstance()     // Catch:{ Exception -> 0x0185 }
            android.database.Cursor r1 = r1.getCursor()     // Catch:{ Exception -> 0x0185 }
            java.lang.String r2 = "== local Search : history =="
            r9.println(r2)     // Catch:{ all -> 0x0179 }
            java.lang.String r2 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursor(r1)     // Catch:{ all -> 0x0179 }
            java.lang.String r2 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r2)     // Catch:{ all -> 0x0179 }
            r9.println(r2)     // Catch:{ all -> 0x0179 }
            if (r1 == 0) goto L_0x0185
            r1.close()     // Catch:{ Exception -> 0x0185 }
            goto L_0x0185
        L_0x0179:
            r2 = move-exception
            if (r1 == 0) goto L_0x0184
            r1.close()     // Catch:{ all -> 0x0180 }
            goto L_0x0184
        L_0x0180:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ Exception -> 0x0185 }
        L_0x0184:
            throw r2     // Catch:{ Exception -> 0x0185 }
        L_0x0185:
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES
            if (r1 == 0) goto L_0x0195
            java.lang.String r1 = "== Search : Essential Faces =="
            r9.println(r1)
            java.lang.String r1 = getEssentialFacesInfo()
            r9.println(r1)
        L_0x0195:
            com.samsung.android.gallery.support.utils.Features r1 = com.samsung.android.gallery.support.utils.Features.SUPPORT_PPP_MENU
            boolean r1 = com.samsung.android.gallery.support.utils.Features.isEnabled(r1)
            if (r1 == 0) goto L_0x01db
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r1 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x01db }
            r1.<init>()     // Catch:{ Exception -> 0x01db }
            java.lang.String r2 = "select _id,sef_file_type,is_cloud,_data as dx from files where is_trashed=1 limit 10"
            java.lang.String r1 = r1.logQuery(r2)     // Catch:{ Exception -> 0x01db }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x01db }
            if (r2 != 0) goto L_0x01d6
            java.lang.String r2 = "== trashed =="
            r9.println(r2)     // Catch:{ Exception -> 0x01db }
            r9.print(r1)     // Catch:{ Exception -> 0x01db }
            java.lang.String r1 = "__category == 9"
            android.database.Cursor r1 = r7.getLogCursor(r1)     // Catch:{ Exception -> 0x01db }
            java.lang.String r2 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursor(r1)     // Catch:{ all -> 0x01ca }
            r9.println(r2)     // Catch:{ all -> 0x01ca }
            if (r1 == 0) goto L_0x01db
            r1.close()     // Catch:{ Exception -> 0x01db }
            goto L_0x01db
        L_0x01ca:
            r2 = move-exception
            if (r1 == 0) goto L_0x01d5
            r1.close()     // Catch:{ all -> 0x01d1 }
            goto L_0x01d5
        L_0x01d1:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ Exception -> 0x01db }
        L_0x01d5:
            throw r2     // Catch:{ Exception -> 0x01db }
        L_0x01d6:
            java.lang.String r1 = "== trashed : none =="
            r9.println(r1)     // Catch:{ Exception -> 0x01db }
        L_0x01db:
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r1 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x01f5 }
            r1.<init>()     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r2 = "select _id,sef_file_type,datetaken,date_modified,_data as dx from files where sef_file_type=2928 limit 10"
            java.lang.String r1 = r1.logQuery(r2)     // Catch:{ Exception -> 0x01f5 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x01f5 }
            if (r2 != 0) goto L_0x01f5
            java.lang.String r2 = "== remained ppp =="
            r9.println(r2)     // Catch:{ Exception -> 0x01f5 }
            r9.print(r1)     // Catch:{ Exception -> 0x01f5 }
        L_0x01f5:
            if (r8 == 0) goto L_0x022a
            java.lang.String r8 = "== log =="
            r9.println(r8)     // Catch:{ Exception -> 0x021b }
            java.lang.String r8 = "__category != 9"
            android.database.Cursor r7 = r7.getLogCursor(r8)     // Catch:{ Exception -> 0x021b }
            java.lang.String r8 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursor(r7)     // Catch:{ all -> 0x020f }
            r9.println(r8)     // Catch:{ all -> 0x020f }
            if (r7 == 0) goto L_0x021b
            r7.close()     // Catch:{ Exception -> 0x021b }
            goto L_0x021b
        L_0x020f:
            r8 = move-exception
            if (r7 == 0) goto L_0x021a
            r7.close()     // Catch:{ all -> 0x0216 }
            goto L_0x021a
        L_0x0216:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch:{ Exception -> 0x021b }
        L_0x021a:
            throw r8     // Catch:{ Exception -> 0x021b }
        L_0x021b:
            java.lang.String r7 = "== Memory usage =="
            r9.println(r7)     // Catch:{ Exception -> 0x0227 }
            java.lang.String r7 = com.samsung.android.gallery.support.utils.MemoryUtils.dumpProcessMemoryStats()     // Catch:{ Exception -> 0x0227 }
            r9.println(r7)     // Catch:{ Exception -> 0x0227 }
        L_0x0227:
            com.samsung.android.gallery.module.graphics.ImageDecoder.dump(r9)
        L_0x022a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0249 }
            r7.<init>(r0)     // Catch:{ Exception -> 0x0249 }
            boolean r8 = com.samsung.android.gallery.module.graphics.QuramBitmapFactory.isEnabled()     // Catch:{ Exception -> 0x0249 }
            r7.append(r8)     // Catch:{ Exception -> 0x0249 }
            java.lang.String r8 = ", Library version:"
            r7.append(r8)     // Catch:{ Exception -> 0x0249 }
            int r8 = com.samsung.android.gallery.module.graphics.QuramBitmapFactory.getLibraryVersion()     // Catch:{ Exception -> 0x0249 }
            r7.append(r8)     // Catch:{ Exception -> 0x0249 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0249 }
            r9.println(r7)     // Catch:{ Exception -> 0x0249 }
        L_0x0249:
            java.lang.String r7 = "== trace log =="
            r9.println(r7)     // Catch:{ Exception -> 0x0251 }
            com.samsung.android.gallery.support.trace.Trace.dumpLog((java.io.PrintWriter) r9)     // Catch:{ Exception -> 0x0251 }
        L_0x0251:
            java.lang.String r7 = "== package monitor log =="
            r9.println(r7)     // Catch:{ Exception -> 0x0259 }
            com.samsung.android.gallery.support.utils.PackageMonitorCompat.dumpLog(r9)     // Catch:{ Exception -> 0x0259 }
        L_0x0259:
            java.lang.String r7 = "== dump handler =="
            r9.println(r7)     // Catch:{ Exception -> 0x0280 }
            com.samsung.android.gallery.support.exception.GalleryUncaughtExceptionHandler r7 = com.samsung.android.gallery.support.exception.GalleryUncaughtExceptionHandler.getInstance()     // Catch:{ Exception -> 0x0280 }
            java.util.concurrent.CopyOnWriteArrayList r7 = r7.getExceptionHandlers()     // Catch:{ Exception -> 0x0280 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x0280 }
        L_0x026a:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x0280 }
            if (r8 == 0) goto L_0x0280
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x0280 }
            java.lang.Thread$UncaughtExceptionHandler r8 = (java.lang.Thread.UncaughtExceptionHandler) r8     // Catch:{ Exception -> 0x0280 }
            boolean r0 = r8 instanceof com.samsung.android.gallery.support.exception.DumpAllHandler     // Catch:{ Exception -> 0x0280 }
            if (r0 == 0) goto L_0x026a
            com.samsung.android.gallery.support.exception.DumpAllHandler r8 = (com.samsung.android.gallery.support.exception.DumpAllHandler) r8     // Catch:{ Exception -> 0x0280 }
            r8.dumpAll(r9)     // Catch:{ Exception -> 0x0280 }
            goto L_0x026a
        L_0x0280:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper.dumpLog(android.content.Context, java.io.PrintWriter):void");
    }

    public void insertOperationHistory(HistoryTable.ActionKeyword actionKeyword, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("action", actionKeyword.toString());
        contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("media_id", str);
        try {
            this.mSQLiteHelper.getWritableDatabase().insertWithOnConflict("operation_history", (String) null, contentValues, 2);
        } catch (SQLiteException e) {
            Log.e("LocalProviderHelper", "write failed. e=" + e.getMessage());
        }
    }

    public void dumpDb(Context context, PrintWriter printWriter) {
    }
}
