package com.samsung.android.gallery.module.trash.db;

import A.a;
import N2.j;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashMpProvider implements ITrashProvider {
    private static final Uri TRASH_CUSTOM_URI = Uri.parse("content://sectrash/gallery");
    private static final String TRASH_FILTER_MEDIA_TYPE = ("media_type in (" + MediaType.Image.toInt() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + MediaType.Video.toInt() + ")");
    private final boolean SUPPORT_FILE_DURATION = Features.isEnabled(Features.SUPPORT_ONE_TRASH_DURATION);
    private final boolean SUPPORT_ONE_TRASH_NEW_CLOUD = Features.isEnabled(Features.SUPPORT_ONE_TRASH_NEW_CLOUD);
    private final boolean SUPPORT_ORIGIN_FILE_HASH = SdkConfig.atLeast(SdkConfig.GED.V);
    private final ContentResolver mResolver;

    public TrashMpProvider(Context context) {
        this.mResolver = context.getContentResolver();
    }

    private String[] getTrashProjection(boolean z) {
        String str;
        String str2;
        String str3;
        String str4;
        if (this.SUPPORT_FILE_DURATION) {
            str = "duration as __fileDuration";
        } else {
            str = "0 as __fileDuration";
        }
        String str5 = str;
        if (z) {
            str2 = "1 as __storageType";
        } else {
            str2 = "is_cloud as __storageType";
        }
        String str6 = str2;
        if (this.SUPPORT_ORIGIN_FILE_HASH) {
            str3 = "original_file_hash as __origin_file_hash";
        } else {
            str3 = " 0 as __origin_file_hash";
        }
        String str7 = str3;
        if (this.SUPPORT_ONE_TRASH_NEW_CLOUD) {
            str4 = "cloud_thumb_path as __cloudTP";
        } else {
            str4 = "null as __cloudTP";
        }
        return new String[]{"_id as __absID", "_data as __absPath", "media_id as __trashMediaId", "_display_name as __originTitle", "media_type as __mediaType", "width as __width", "height as __height", str5, "orientation as __orientation", "orientation_tag as __orientationTag", "original_path as __originPath", "title_hash as __Title", "date_deleted as __deleteTime", "date_expires as __expiredTime", str6, "cloud_server_id as __cloudServerId", "extra as __restoreExtra", "volume_name as __volumeName", "0 as __count", "delete_package_name as __ownerPackage", str7, str4};
    }

    private String getTrashSelection(boolean z) {
        FileUtils.getMountedVolumeAndExternalNames();
        StringBuilder sb2 = new StringBuilder("media_type in (1, 3)");
        if (z) {
            sb2.append(" AND (is_cloud = 1 OR is_cloud = 3 )");
        }
        return sb2.toString();
    }

    public int deleteAllTrash() {
        return this.mResolver.delete(getTrashUri(), TRASH_FILTER_MEDIA_TYPE, (String[]) null);
    }

    public int deleteTrash(String str, String[] strArr) {
        return this.mResolver.delete(getTrashUri(), str, strArr);
    }

    public Cursor getExpiredTrashCursor(String str) {
        return this.mResolver.query(getTrashUri(), new String[]{"is_cloud as __storageType", "_data as __absPath", "title as __originTitle", "media_type as __mediaType", "cloud_server_id as __cloudServerId", "extra as __restoreExtra", "volume_name as __volumeName"}, str, (String[]) null, (String) null);
    }

    public int[] getTrashCount(boolean z) {
        int i2;
        StringBuilder sb2;
        Cursor query;
        int[] iArr = {0, 0};
        Bundle bundle = new Bundle();
        bundle.putString("android:query-arg-sql-selection", getTrashSelection(z));
        bundle.putString("android:query-arg-sql-group-by", ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
        try {
            query = this.mResolver.query(getTrashUri(), new String[]{ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "count(*)"}, bundle, (CancellationSignal) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        int i7 = query.getInt(0);
                        int i8 = query.getInt(1);
                        if (i7 == MediaType.Image.toInt()) {
                            iArr[0] = i8;
                        } else if (i7 == MediaType.Video.toInt()) {
                            iArr[1] = i8;
                        }
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            sb2 = new StringBuilder("trash count returned [");
            sb2.append(iArr[0]);
            sb2.append("][");
            i2 = iArr[1];
        } catch (Exception e) {
            try {
                e.printStackTrace();
                sb2 = new StringBuilder("trash count returned [");
                sb2.append(iArr[0]);
                sb2.append("][");
                i2 = iArr[1];
            } catch (Throwable th) {
                Log.d("TrashMpProvider", "trash count returned [" + iArr[0] + "][" + iArr[1] + "]");
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        sb2.append(i2);
        sb2.append("]");
        Log.d("TrashMpProvider", sb2.toString());
        return iArr;
        throw th;
    }

    public Cursor getTrashCursor(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Cursor query = this.mResolver.query(getTrashUri(), getTrashProjection(z), getTrashSelection(z), (String[]) null, "date_deleted DESC");
            Log.d("TrashMpProvider", "getTrashCursor" + Logger.vt(currentTimeMillis));
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            Log.d("TrashMpProvider", "getTrashCursor" + Logger.vt(currentTimeMillis));
            throw th2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object[] getTrashInfo() {
        /*
            r12 = this;
            android.content.Context r0 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0018
            boolean r0 = com.samsung.android.gallery.module.cloud.SamsungCloudCompat.isSyncOn(r0)
            if (r0 == 0) goto L_0x0016
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.IS_UPSM
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            if (r0 == 0) goto L_0x0018
        L_0x0016:
            r0 = r1
            goto L_0x0019
        L_0x0018:
            r0 = r2
        L_0x0019:
            java.lang.String r0 = r12.getTrashSelection(r0)
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            java.lang.String r4 = "android:query-arg-sql-group-by"
            java.lang.String r5 = "lower(volume_name)"
            r3.putString(r4, r5)
            java.lang.String r4 = "android:query-arg-sql-selection"
            r3.putString(r4, r0)
            java.lang.String r0 = "volume_name"
            java.lang.String r4 = "sum(_size)"
            java.lang.String r5 = "count(*)"
            java.lang.String[] r0 = new java.lang.String[]{r5, r0, r4}
            android.content.ContentResolver r4 = r12.mResolver
            android.net.Uri r12 = r12.getTrashUri()
            r5 = 0
            android.database.Cursor r12 = r4.query(r12, r0, r3, r5)
            r3 = 0
            if (r12 == 0) goto L_0x008e
            boolean r0 = r12.moveToFirst()     // Catch:{ all -> 0x006e }
            if (r0 == 0) goto L_0x008e
            r0 = r2
            r7 = r0
            r5 = r3
        L_0x0052:
            java.lang.String r8 = r12.getString(r1)     // Catch:{ all -> 0x006e }
            java.lang.String r8 = r8.toLowerCase()     // Catch:{ all -> 0x006e }
            java.lang.String r9 = "external_primary"
            boolean r8 = r9.equals(r8)     // Catch:{ all -> 0x006e }
            r9 = 2
            if (r8 == 0) goto L_0x0070
            int r8 = r12.getInt(r2)     // Catch:{ all -> 0x006e }
            int r7 = r7 + r8
            long r8 = r12.getLong(r9)     // Catch:{ all -> 0x006e }
            long r5 = r5 + r8
            goto L_0x007a
        L_0x006e:
            r0 = move-exception
            goto L_0x0085
        L_0x0070:
            int r8 = r12.getInt(r2)     // Catch:{ all -> 0x006e }
            int r0 = r0 + r8
            long r8 = r12.getLong(r9)     // Catch:{ all -> 0x006e }
            long r3 = r3 + r8
        L_0x007a:
            boolean r8 = r12.moveToNext()     // Catch:{ all -> 0x006e }
            if (r8 != 0) goto L_0x0052
            r10 = r5
            r5 = r3
            r3 = r10
            r2 = r7
            goto L_0x0090
        L_0x0085:
            r12.close()     // Catch:{ all -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r12 = move-exception
            r0.addSuppressed(r12)
        L_0x008d:
            throw r0
        L_0x008e:
            r0 = r2
            r5 = r3
        L_0x0090:
            if (r12 == 0) goto L_0x0095
            r12.close()
        L_0x0095:
            java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            java.lang.Object[] r12 = new java.lang.Object[]{r12, r0, r1, r2}
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.db.TrashMpProvider.getTrashInfo():java.lang.Object[]");
    }

    public int getTrashTotalCount() {
        boolean z;
        Throwable th;
        Context appContext = AppResources.getAppContext();
        if (appContext == null || (SamsungCloudCompat.isSyncOn(appContext) && !Features.isEnabled(Features.IS_UPSM))) {
            z = false;
        } else {
            z = true;
        }
        Cursor query = this.mResolver.query(getTrashUri(), new String[]{"count(*)"}, getTrashSelection(z), (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i2 = query.getInt(0);
                    query.close();
                    return i2;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (query != null) {
            query.close();
        }
        return 0;
        throw th;
    }

    public Uri getTrashUri() {
        return MediaUri.getInstance().getSecTrashUri();
    }

    /* JADX INFO: finally extract failed */
    public Uri insertTrash(ContentValues contentValues) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Uri insert = this.mResolver.insert(getTrashUri(), contentValues);
            j.m(currentTimeMillis, "]", "TrashMpProvider", new StringBuilder("insertTrashDB ["));
            return insert;
        } catch (Exception e) {
            e.printStackTrace();
            j.m(currentTimeMillis, "]", "TrashMpProvider", new StringBuilder("insertTrashDB ["));
            return null;
        } catch (Throwable th) {
            j.m(currentTimeMillis, "]", "TrashMpProvider", new StringBuilder("insertTrashDB ["));
            throw th;
        }
    }

    public boolean isSameOriginFileHashExists(String str) {
        return false;
    }

    public int isSameRecordExist(String str) {
        Cursor query;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            query = this.mResolver.query(getTrashUri(), new String[]{"is_cloud"}, "cloud_server_id =? ", new String[]{str}, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int i2 = query.getInt(0);
                    query.close();
                    return i2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (SecurityException e) {
            SecurityException securityException = e;
            Log.w("TrashMpProvider", "trash query failed. " + securityException.getMessage());
        } catch (SQLiteException e7) {
            SQLiteException sQLiteException = e7;
            Log.w("TrashMpProvider", "trash query failed. " + sQLiteException.getMessage());
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return -1;
        throw th;
    }

    public Cursor loadTrashIdsCursor(boolean z) {
        String str;
        Cursor query;
        TimeTickLog timeTickLog = new TimeTickLog();
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"group_concat(_id) as __idList", "count(*) as __count"};
        Bundle bundle = new Bundle();
        if (z) {
            str = "is_cloud in (1,3) and media_type in (1,3)";
        } else {
            str = "media_type in (1,3)";
        }
        int i2 = 0;
        int i7 = 0;
        while (true) {
            bundle.putString("android:query-arg-sql-limit", i2 + ",250000");
            bundle.putString("android:query-arg-sql-selection", str);
            query = this.mResolver.query(TRASH_CUSTOM_URI, strArr, bundle, (CancellationSignal) null);
            timeTickLog.tick();
            if (query == null || !query.moveToFirst()) {
                Utils.closeSilently(query);
            } else {
                timeTickLog.tick();
                int i8 = query.getInt(1);
                timeTickLog.tick();
                arrayList.add(query);
                i7 += i8;
                if (i8 < 250000) {
                    break;
                }
                i2 += 250000;
            }
        }
        Utils.closeSilently(query);
        Log.d("TrashMpProvider", "loadTrashIdsCursor" + Logger.vt(Integer.valueOf(i7), timeTickLog));
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return null;
    }

    public Cursor loadTrashRealRatioCursor(boolean z) {
        String str;
        Cursor query;
        TimeTickLog timeTickLog = new TimeTickLog();
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"group_concat(case when height is null or height=0 or width is null or width=0 then 1 else substr(case when orientation=90 or orientation=270 then 1.0*height/width else 1.0*width/height end,1,4) end) as __widthList", "count(*) as __count"};
        Bundle bundle = new Bundle();
        if (z) {
            str = "is_cloud in (1,3) and media_type in (1,3)";
        } else {
            str = "media_type in (1,3)";
        }
        int i2 = 0;
        int i7 = 0;
        while (true) {
            bundle.putString("android:query-arg-sql-limit", i2 + ",800000");
            bundle.putString("android:query-arg-sql-selection", str);
            query = this.mResolver.query(TRASH_CUSTOM_URI, strArr, bundle, (CancellationSignal) null);
            timeTickLog.tick();
            if (query == null || !query.moveToFirst()) {
                Utils.closeSilently(query);
            } else {
                timeTickLog.tick();
                int i8 = query.getInt(1);
                timeTickLog.tick();
                arrayList.add(query);
                i7 += i8;
                if (i8 < 800000) {
                    break;
                }
                i2 += 800000;
            }
        }
        Utils.closeSilently(query);
        Log.d("TrashMpProvider", "loadTrashRealRatioCursor" + Logger.vt(Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return null;
    }

    public boolean supportUndoDelete() {
        return PocFeatures.UNDO_DELETE;
    }

    public int updateTrash(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return this.mResolver.update(uri, contentValues, str, strArr);
    }

    public int deleteTrash(String str, String[] strArr, boolean z) {
        return this.mResolver.delete(getTrashUri().buildUpon().appendQueryParameter("restored", z ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).build(), str, strArr);
    }

    public Cursor getTrashCursor(long j2) {
        long j3 = j2;
        if (!supportUndoDelete()) {
            return this.mResolver.query(getTrashUri(), getTrashProjection(false), a.f("sec_media_id = ", j3), (String[]) null, (String) null);
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ContentResolver contentResolver = this.mResolver;
            Uri trashUri = getTrashUri();
            String[] trashProjection = getTrashProjection(false);
            return contentResolver.query(trashUri, trashProjection, getTrashSelection(false) + " and sec_media_id='" + j3 + "'", (String[]) null, "date_deleted DESC");
        } finally {
            j.m(currentTimeMillis, "]", "TrashMpProvider", new StringBuilder("trash cursor returned ["));
        }
    }

    public Cursor getTrashCursor(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Cursor query = this.mResolver.query(getTrashUri(), getTrashProjection(false), getTrashSelection(false) + " AND _id IN (" + str + ")", (String[]) null, "date_deleted DESC");
            j.m(currentTimeMillis, "]", "TrashMpProvider", new StringBuilder("trash cursor returned ["));
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            j.m(currentTimeMillis, "]", "TrashMpProvider", new StringBuilder("trash cursor returned ["));
            throw th2;
        }
    }

    public Cursor getTrashCursor(String str, String[] strArr) {
        return this.mResolver.query(getTrashUri(), new String[]{"original_path"}, str, strArr, (String) null);
    }
}
