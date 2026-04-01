package com.samsung.android.gallery.database.dal.abstraction.query;

import N2.j;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.SparseArray;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QueryExecutor {
    private static final boolean GMP_ENABLED;
    protected final String TAG = tag();
    protected SparseArray<String> mLatestQuery = new SparseArray<>();
    private volatile boolean mPrintLogcat = false;

    static {
        boolean z;
        if (PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            z = true;
        } else {
            z = false;
        }
        GMP_ENABLED = z;
    }

    private String getSectionName(String str) {
        String l = C0212a.l("query#", str);
        if (l.length() > 120) {
            return l.substring(0, 120);
        }
        return l;
    }

    public void enableLogcatOnce() {
        this.mPrintLogcat = true;
    }

    public ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    public Context getContext() {
        return AppResources.getAppContext();
    }

    public Cursor getCursor(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        ThreadUtil.assertBgThread("Query should run on background thread");
        try {
            return query(uri, strArr, str, strArr2, str2, str3);
        } catch (SQLiteException e) {
            SQLiteException sQLiteException = e;
            Log.e(this.TAG, sQLiteException.toString());
            throw sQLiteException;
        }
    }

    public void onQueryFail(String str) {
        new InternalException(j.f(new StringBuilder(), this.TAG, " ", str)).post();
    }

    public void printQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3, long j2) {
        String str4;
        if (strArr == null && strArr2 == null && str2 == null) {
            StringBuilder k = j.k("[QUERY/", str3, "] ");
            k.append(uri.getAuthority());
            k.append(" [");
            k.append(str);
            k.append("] +");
            k.append(j2);
            str4 = k.toString();
        } else {
            StringBuilder k10 = j.k("[QUERY/", str3, "] ");
            k10.append(Logger.toString(uri.getAuthority(), strArr, str, strArr2, str2));
            k10.append(" +");
            k10.append(j2);
            str4 = k10.toString();
        }
        if (str4.length() > 2000) {
            int length = str4.length();
            int i2 = 0;
            while (i2 < length) {
                int i7 = i2 + 2000;
                int indexOf = str4.indexOf(" ", i7);
                if (indexOf >= 0) {
                    i7 = indexOf;
                }
                String substring = str4.substring(i2, Math.min(i7, length));
                String str5 = this.TAG;
                if (i2 != 0) {
                    substring = C0212a.n("[QUERY/", str3, "] continued\n", substring);
                }
                Log.v(str5, substring);
                i2 = i7;
            }
            return;
        }
        Log.v(this.TAG, str4);
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            Trace.beginSection(Contract.QUERY);
            Context context = getContext();
            if (context == null) {
                Log.e(this.TAG, "context is null");
                onQueryFail("QueryFail : null context");
                Trace.endSection();
                return null;
            }
            Cursor query = query(context, uri, strArr, str, strArr2, str2, "tag");
            Trace.endSection();
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            Trace.endSection();
            throw th2;
        }
    }

    public String tag() {
        return "QueryExecutor";
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        try {
            Trace.beginSection(getSectionName(str3));
            Context context = getContext();
            if (context == null) {
                Log.e(this.TAG, "query failed. null context");
                onQueryFail("QueryFail : null context");
                Trace.endSection();
                return null;
            }
            Cursor query = query(context, uri, strArr, str, strArr2, str2, str3);
            Trace.endSection();
            return query;
        } catch (Throwable th) {
            Throwable th2 = th;
            Trace.endSection();
            throw th2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x013e, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0140, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0141, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e2 A[Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }, ExcHandler: SQLiteFullException (e android.database.sqlite.SQLiteFullException), PHI: r9 
      PHI: (r9v4 java.lang.String) = (r9v0 java.lang.String), (r9v0 java.lang.String), (r9v10 java.lang.String), (r9v11 java.lang.String) binds: [B:4:0x002e, B:7:0x0040, B:53:0x0104, B:31:0x0090] A[DONT_GENERATE, DONT_INLINE], Splitter:B:4:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0157 A[Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x0179, SecurityException -> 0x0143, all -> 0x01c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0178 A[SYNTHETIC, Splitter:B:75:0x0178] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.database.Cursor query(android.content.Context r20, android.net.Uri r21, java.lang.String[] r22, java.lang.String r23, java.lang.String[] r24, java.lang.String r25, java.lang.String r26) {
        /*
            r19 = this;
            r1 = r19
            r9 = r26
            java.lang.String r10 = "data://user/SecurityExceptionOnSecMp"
            java.lang.String r11 = "secmedia"
            java.lang.String r12 = " failed"
            java.lang.String r13 = "query "
            java.lang.String r14 = "query selection compare\norig{"
            java.lang.String r0 = "[QUERY/"
            java.lang.String r2 = r1.getSectionName(r9)     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.trace.Trace.beginSection(r2)     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.utils.TimeTickLog r15 = new com.samsung.android.gallery.support.utils.TimeTickLog     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r2.<init>(r13)     // Catch:{ all -> 0x01c7 }
            r2.append(r9)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01c7 }
            r15.<init>(r2)     // Catch:{ all -> 0x01c7 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01c7 }
            r16 = 0
            android.content.ContentResolver r4 = r20.getContentResolver()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x0179, SecurityException -> 0x0143 }
            r8 = 0
            r5 = r23
            r6 = r24
            r7 = r25
            r17 = r2
            r2 = r4
            r3 = r21
            r4 = r22
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x0140, SecurityException -> 0x013d }
            long r8 = r15.getElapsedTime()     // Catch:{ SQLiteFullException -> 0x007c, SQLiteException -> 0x0075, SecurityException -> 0x006e }
            boolean r3 = com.samsung.android.gallery.support.utils.Logger.QUERY     // Catch:{ SQLiteFullException -> 0x007c, SQLiteException -> 0x0075, SecurityException -> 0x006e }
            if (r3 == 0) goto L_0x0081
            r0 = 0
            r1.mPrintLogcat = r0     // Catch:{ SQLiteFullException -> 0x007c, SQLiteException -> 0x0075, SecurityException -> 0x006e }
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r0 = r2
            r2 = r21
            r1.printQuery(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteFullException -> 0x006a, SQLiteException -> 0x0066, SecurityException -> 0x0062 }
            r9 = r7
            r3 = r0
            goto L_0x00a9
        L_0x0062:
            r0 = move-exception
            r9 = r7
            goto L_0x0146
        L_0x0066:
            r0 = move-exception
            r9 = r7
            goto L_0x017c
        L_0x006a:
            r0 = move-exception
            r9 = r7
            goto L_0x01af
        L_0x006e:
            r0 = move-exception
            r2 = r21
            r9 = r26
            goto L_0x0146
        L_0x0075:
            r0 = move-exception
            r4 = r23
            r9 = r26
            goto L_0x017c
        L_0x007c:
            r0 = move-exception
            r9 = r26
            goto L_0x01af
        L_0x0081:
            r4 = r23
            r3 = r2
            r5 = r8
            r2 = r21
            r9 = r26
            r7 = 100
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 > 0) goto L_0x0090
            goto L_0x00a9
        L_0x0090:
            java.lang.String r7 = r1.TAG     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r8.<init>(r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r8.append(r9)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r0 = "] +"
            r8.append(r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r8.append(r5)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r0 = r8.toString()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            com.samsung.android.gallery.support.utils.Log.v(r7, r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
        L_0x00a9:
            r5 = 200(0xc8, double:9.9E-322)
            r15.tockWithLog(r5)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            if (r3 != 0) goto L_0x0102
            java.lang.String r0 = r1.TAG     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r3.<init>(r13)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r3.append(r9)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r5 = " failed. null cursor for "
            r3.append(r5)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r3.append(r2)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            com.samsung.android.gallery.support.utils.Log.e(r0, r3)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r0 = r2.toString()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r3 = "content://com.samsung.android.mobileservice"
            boolean r0 = r0.startsWith(r3)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            if (r0 == 0) goto L_0x00e5
            java.lang.String r0 = r1.TAG     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r3 = "QueryFail but skip throw exception for managed profile"
            com.samsung.android.gallery.support.utils.Log.e(r0, r3)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            goto L_0x00fe
        L_0x00dd:
            r0 = move-exception
            goto L_0x0146
        L_0x00df:
            r0 = move-exception
            goto L_0x017c
        L_0x00e2:
            r0 = move-exception
            goto L_0x01af
        L_0x00e5:
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.IS_ON_SECURE_FOLDER     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            if (r0 == 0) goto L_0x00f5
            java.lang.String r0 = r1.TAG     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r3 = "QueryFail but skip throw exception for secure folder profile"
            com.samsung.android.gallery.support.utils.Log.e(r0, r3)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            goto L_0x00fe
        L_0x00f5:
            boolean r0 = GMP_ENABLED     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            if (r0 != 0) goto L_0x00fe
            java.lang.String r0 = "QueryFail"
            r1.onQueryFail(r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
        L_0x00fe:
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r16
        L_0x0102:
            if (r2 == 0) goto L_0x0115
            java.lang.String r0 = r2.toString()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            boolean r0 = r0.contains(r11)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            if (r0 == 0) goto L_0x0115
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r0.erase(r10)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
        L_0x0115:
            boolean r0 = com.samsung.android.gallery.support.config.DeviceConfig.UNIT_TEST     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            if (r0 == 0) goto L_0x0134
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.toString((android.net.Uri) r21, (java.lang.String[]) r22, (java.lang.String) r23, (java.lang.String[]) r24, (java.lang.String) r25)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            android.util.SparseArray<java.lang.String> r5 = r1.mLatestQuery     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            int r6 = android.system.Os.gettid()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r5.put(r6, r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r5.<init>()     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            java.lang.String r6 = "queryV2"
            r5.putString(r6, r0)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            r3.setExtras(r5)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
            goto L_0x0139
        L_0x0134:
            r5 = r17
            com.samsung.android.gallery.support.helper.CursorHelper.setQueryTime(r3, r5)     // Catch:{ SQLiteFullException -> 0x00e2, SQLiteException -> 0x00df, SecurityException -> 0x00dd }
        L_0x0139:
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r3
        L_0x013d:
            r0 = move-exception
            r2 = r3
            goto L_0x0146
        L_0x0140:
            r0 = move-exception
            r4 = r5
            goto L_0x017c
        L_0x0143:
            r0 = move-exception
            r2 = r21
        L_0x0146:
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x01c7 }
            r1.onQueryFail(r3)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01c7 }
            boolean r2 = r2.contains(r11)     // Catch:{ all -> 0x01c7 }
            if (r2 == 0) goto L_0x0178
            java.lang.String r1 = r1.TAG     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r2.<init>(r13)     // Catch:{ all -> 0x01c7 }
            r2.append(r9)     // Catch:{ all -> 0x01c7 }
            r2.append(r12)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r1, (java.lang.String) r2, (java.lang.Throwable) r0)     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()     // Catch:{ all -> 0x01c7 }
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x01c7 }
            r0.publish(r10, r1)     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r16
        L_0x0178:
            throw r0     // Catch:{ all -> 0x01c7 }
        L_0x0179:
            r0 = move-exception
            r4 = r23
        L_0x017c:
            java.lang.String r2 = r1.TAG     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r3.<init>(r14)     // Catch:{ all -> 0x01c7 }
            r3.append(r4)     // Catch:{ all -> 0x01c7 }
            java.lang.String r5 = "}\nexec{"
            r3.append(r5)     // Catch:{ all -> 0x01c7 }
            r3.append(r4)     // Catch:{ all -> 0x01c7 }
            java.lang.String r4 = "}"
            r3.append(r4)     // Catch:{ all -> 0x01c7 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.utils.Log.e(r2, r3)     // Catch:{ all -> 0x01c7 }
            java.lang.String r1 = r1.TAG     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r2.<init>(r13)     // Catch:{ all -> 0x01c7 }
            r2.append(r9)     // Catch:{ all -> 0x01c7 }
            r2.append(r12)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r1, (java.lang.String) r2, (java.lang.Throwable) r0)     // Catch:{ all -> 0x01c7 }
            throw r0     // Catch:{ all -> 0x01c7 }
        L_0x01af:
            java.lang.String r1 = r1.TAG     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r2.<init>(r13)     // Catch:{ all -> 0x01c7 }
            r2.append(r9)     // Catch:{ all -> 0x01c7 }
            r2.append(r12)     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r1, (java.lang.String) r2, (java.lang.Throwable) r0)     // Catch:{ all -> 0x01c7 }
            com.samsung.android.gallery.support.trace.Trace.endSection()
            return r16
        L_0x01c7:
            r0 = move-exception
            com.samsung.android.gallery.support.trace.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor.query(android.content.Context, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String):android.database.Cursor");
    }
}
