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
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashLocalProvider implements ITrashProvider {
    private final int mNewDBVersion;
    private final ContentResolver mResolver;

    public TrashLocalProvider(Context context) {
        this.mResolver = context.getContentResolver();
        this.mNewDBVersion = LocalDatabaseHelper.getInstance(context).getDatabaseVersion();
    }

    private String buildTrashIdsQuerySentence(int i2, int i7, boolean z) {
        StringBuilder sb2 = new StringBuilder("select group_concat(__absID) as __idList, count(*) as __count from (select __absID from trash where ");
        sb2.append(getTrashSelection(false, z));
        sb2.append(" order by __deleteTime DESC limit ");
        sb2.append(i2);
        sb2.append(ArcCommonLog.TAG_COMMA);
        return C0086a.l(sb2, i7, ")");
    }

    private String buildTrashRealRatioQuerySentence(int i2, int i7, boolean z) {
        return C0212a.m("select group_concat(case when height is null or height=0 or width is null or width=0 then 1 else substr(case when orientation=90 or orientation=270 then 1.0*height/width else 1.0*width/height end,1,4) end) as __widthList, count(*) as __count from (", "select __width as width, __height as height, __orientation as orientation  from trash where " + getTrashSelection(false, z) + "ORDER BY __deleteTime DESC LIMIT " + i2 + ArcCommonLog.TAG_COMMA + i7, ")");
    }

    private Cursor getTrashCursorByRawQuery(String str) {
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            return LocalDatabaseHelper.getInstance(appContext).getReadableDatabase().rawQuery(str, (String[]) null);
        }
        Log.e("TrashLocalProvider", "get trash cursor failed : null context");
        return null;
    }

    private String[] getTrashProjection(boolean z) {
        String str;
        if (z) {
            str = "1 as __storageType";
        } else {
            str = "__storageType";
        }
        return new String[]{"__absID", "__absPath", "__Title", "__mediaType", "__width", "__height", "__orientation", "__orientationTag", "__originPath", "__originTitle", "__deleteTime", str, "__burstGroupID as __GroupMediaID", "__bestImage as __GroupMediaBest", "__cloudServerId", "__cloudTP", "__restoreExtra", "__volumeName", "__expiredPeriod", "__database_version", "0 as __count"};
    }

    private String getTrashSelection(boolean z, boolean z3) {
        StringBuilder sb2 = new StringBuilder("__volumeName COLLATE NOCASE in (" + FileUtils.getMountedVolumeAndExternalNames() + ")");
        if (z3) {
            sb2.append(" AND (__storageType = 1 OR __storageType = 3 )");
        }
        if (z) {
            sb2.append(" GROUP BY __mediaType");
        }
        return sb2.toString();
    }

    public int deleteAllTrash() {
        return this.mResolver.delete(getTrashUri(), (String) null, (String[]) null);
    }

    public int deleteTrash(String str, String[] strArr) {
        return this.mResolver.delete(getTrashUri(), str, strArr);
    }

    public Cursor getExpiredTrashCursor(String str) {
        return this.mResolver.query(getTrashUri(), new String[]{"__storageType", "__absPath", "__originTitle", "__mediaType", "__cloudServerId", "__cloudTP", "__restoreExtra", "__volumeName"}, str, (String[]) null, (String) null);
    }

    public int[] getTrashCount(boolean z) {
        Cursor query;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        int[] iArr = {0, 0};
        try {
            query = this.mResolver.query(getTrashUri(), new String[]{"__mediaType", "count(*)"}, getTrashSelection(true, z), (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        int i2 = query.getInt(0);
                        int i7 = query.getInt(1);
                        if (i2 == MediaType.Image.toInt()) {
                            iArr[0] = i7;
                        } else if (i2 == MediaType.Video.toInt()) {
                            iArr[1] = i7;
                        }
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            a.A(new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Long.valueOf(currentTimeMillis)}, new StringBuilder("trash count"), "TrashLocalProvider");
            return iArr;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            a.A(new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Long.valueOf(currentTimeMillis)}, new StringBuilder("trash count"), "TrashLocalProvider");
            throw th3;
        }
        throw th;
    }

    public Cursor getTrashCursor(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Cursor query = this.mResolver.query(getTrashUri(), getTrashProjection(z), getTrashSelection(false, z), (String[]) null, "__deleteTime DESC");
            Log.d("TrashLocalProvider", "getTrashCursor" + Logger.vt(currentTimeMillis));
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            Log.d("TrashLocalProvider", "getTrashCursor" + Logger.vt(currentTimeMillis));
            throw th2;
        }
    }

    public Object[] getTrashInfo() {
        return new Object[0];
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
        Cursor query = this.mResolver.query(getTrashUri(), new String[]{"count(*)"}, getTrashSelection(false, z), (String[]) null, (String) null);
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
        return LocalDatabase.TRASH_URI;
    }

    public Uri insertTrash(ContentValues contentValues) {
        return this.mResolver.insert(getTrashUri(), contentValues);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003a A[Catch:{ all -> 0x0025, all -> 0x002b, SQLiteException -> 0x0034, SecurityException -> 0x0031 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSameOriginFileHashExists(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r1 = "trash query failed. "
            java.lang.String r2 = "TrashLocalProvider"
            java.lang.String r0 = "__restoreExtra LIKE '%"
            java.lang.String r3 = "%'"
            java.lang.String r7 = i.C0212a.m(r0, r11, r3)
            r11 = 0
            android.content.ContentResolver r4 = r10.mResolver     // Catch:{ SQLiteException -> 0x0034, SecurityException -> 0x0031 }
            android.net.Uri r5 = r10.getTrashUri()     // Catch:{ SQLiteException -> 0x0034, SecurityException -> 0x0031 }
            r8 = 0
            r9 = 0
            r6 = 0
            android.database.Cursor r10 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0034, SecurityException -> 0x0031 }
            if (r10 == 0) goto L_0x0037
            int r0 = r10.getCount()     // Catch:{ all -> 0x0025 }
            if (r0 <= 0) goto L_0x0037
            r0 = 1
            goto L_0x0038
        L_0x0025:
            r0 = move-exception
            r3 = r0
            r10.close()     // Catch:{ all -> 0x002b }
            goto L_0x0030
        L_0x002b:
            r0 = move-exception
            r10 = r0
            r3.addSuppressed(r10)     // Catch:{ SQLiteException -> 0x0034, SecurityException -> 0x0031 }
        L_0x0030:
            throw r3     // Catch:{ SQLiteException -> 0x0034, SecurityException -> 0x0031 }
        L_0x0031:
            r0 = move-exception
            r10 = r0
            goto L_0x003e
        L_0x0034:
            r0 = move-exception
            r10 = r0
            goto L_0x0052
        L_0x0037:
            r0 = r11
        L_0x0038:
            if (r10 == 0) goto L_0x003d
            r10.close()     // Catch:{ SQLiteException -> 0x0034, SecurityException -> 0x0031 }
        L_0x003d:
            return r0
        L_0x003e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.String r10 = r10.getMessage()
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.w(r2, r10)
            goto L_0x0065
        L_0x0052:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.String r10 = r10.getMessage()
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.w(r2, r10)
        L_0x0065:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.db.TrashLocalProvider.isSameOriginFileHashExists(java.lang.String):boolean");
    }

    public int isSameRecordExist(String str) {
        Cursor query;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            query = this.mResolver.query(getTrashUri(), new String[]{"__storageType"}, "__cloudServerId =? ", new String[]{str}, (String) null);
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
            Log.w("TrashLocalProvider", "trash query failed. " + securityException.getMessage());
        } catch (SQLiteException e7) {
            SQLiteException sQLiteException = e7;
            Log.w("TrashLocalProvider", "trash query failed. " + sQLiteException.getMessage());
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return -1;
        throw th;
    }

    public Cursor loadTrashIdsCursor(boolean z) {
        Cursor trashCursorByRawQuery;
        TimeTickLog timeTickLog = new TimeTickLog();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i7 = 0;
        while (true) {
            trashCursorByRawQuery = getTrashCursorByRawQuery(buildTrashIdsQuerySentence(i2, 250000, z));
            timeTickLog.tick();
            if (trashCursorByRawQuery == null || !trashCursorByRawQuery.moveToFirst()) {
                Utils.closeSilently(trashCursorByRawQuery);
            } else {
                timeTickLog.tick();
                int i8 = trashCursorByRawQuery.getInt(1);
                timeTickLog.tick();
                arrayList.add(trashCursorByRawQuery);
                i7 += i8;
                if (i8 < 250000) {
                    break;
                }
                i2 += 250000;
            }
        }
        Utils.closeSilently(trashCursorByRawQuery);
        Log.d("TrashLocalProvider", "loadTrashIdsCursor" + Logger.vt(Integer.valueOf(i7), timeTickLog));
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return null;
    }

    public Cursor loadTrashRealRatioCursor(boolean z) {
        TimeTickLog timeTickLog = new TimeTickLog();
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i7 = 0;
        while (true) {
            Cursor trashCursorByRawQuery = getTrashCursorByRawQuery(buildTrashRealRatioQuerySentence(i2, 800000, z));
            timeTickLog.tick();
            if (trashCursorByRawQuery == null || !trashCursorByRawQuery.moveToFirst()) {
                Utils.closeSilently(trashCursorByRawQuery);
            } else {
                timeTickLog.tick();
                int i8 = trashCursorByRawQuery.getInt(1);
                timeTickLog.tick();
                arrayList.add(trashCursorByRawQuery);
                i7 += i8;
                if (i8 < 800000) {
                    break;
                }
                i2 += 800000;
            }
        }
        Log.d("TrashLocalProvider", "loadTrashRealRatioCursor" + Logger.vt(Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
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
        return this.mResolver.delete(getTrashUri(), str, strArr);
    }

    public Cursor getTrashCursor(long j2) {
        if (!supportUndoDelete()) {
            return getTrashCursor(String.valueOf(j2));
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ContentResolver contentResolver = this.mResolver;
            Uri trashUri = getTrashUri();
            String[] trashProjection = getTrashProjection(false);
            Cursor query = contentResolver.query(trashUri, trashProjection, getTrashSelection(false, false) + " and __absID='" + j2 + "'", (String[]) null, "__deleteTime DESC");
            j.m(currentTimeMillis, "]", "TrashLocalProvider", new StringBuilder("trash cursor returned ["));
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            j.m(currentTimeMillis, "]", "TrashLocalProvider", new StringBuilder("trash cursor returned ["));
            throw th2;
        }
    }

    public Cursor getTrashCursor(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Cursor query = this.mResolver.query(getTrashUri(), getTrashProjection(false), getTrashSelection(false, false) + " AND __absID IN (" + str + ")", (String[]) null, "__deleteTime DESC");
            j.m(currentTimeMillis, "]", "TrashLocalProvider", new StringBuilder("trash cursor returned ["));
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            j.m(currentTimeMillis, "]", "TrashLocalProvider", new StringBuilder("trash cursor returned ["));
            throw th2;
        }
    }

    public Cursor getTrashCursor(String str, String[] strArr) {
        return this.mResolver.query(getTrashUri(), new String[]{"__originPath"}, str, strArr, (String) null);
    }
}
