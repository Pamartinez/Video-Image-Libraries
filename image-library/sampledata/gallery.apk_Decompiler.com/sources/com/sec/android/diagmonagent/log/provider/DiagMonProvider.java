package com.sec.android.diagmonagent.log.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DiagMonProvider extends ContentProvider {
    public Bundle d;
    public String e;

    public static Bundle a(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(str, z);
        return bundle;
    }

    public final Bundle b(ArrayList arrayList) {
        Bundle bundle = new Bundle();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                str = new File(str).getCanonicalPath();
            } catch (IOException unused) {
            }
            bundle.putParcelable(str, new Uri.Builder().scheme("content").authority(this.e).path(str).build());
        }
        return bundle;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(3:1|2|(1:4))|5|(2:7|8)|9|11|12|13|14|(1:16)(1:17)|18|19|(2:23|21)|26|24|25) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x00a7 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00b7 A[Catch:{ Exception -> 0x00bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00b8 A[Catch:{ Exception -> 0x00bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e7 A[LOOP:0: B:21:0x00e1->B:23:0x00e7, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean c() {
        /*
            r10 = this;
            java.lang.String r0 = "deviceInfo"
            java.lang.String r1 = "diagmonSupportV1VersionCode"
            java.lang.Class<Dd.b> r2 = Dd.C0731b.class
            java.lang.String r3 = ""
            r10.e = r3
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            r10.d = r4
            java.lang.String r5 = "diagmonSupportV1VersionName"
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            r7 = 0
            java.lang.String r8 = "b"
            java.lang.reflect.Field r8 = r2.getDeclaredField(r8)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0032 }
            java.lang.Object r8 = r8.get(r7)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0032 }
            boolean r9 = r8 instanceof java.lang.String     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0032 }
            if (r9 == 0) goto L_0x0032
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            java.lang.Object r8 = r9.cast(r8)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0032 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0032 }
            r6.putString(r5, r8)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0032 }
        L_0x0032:
            r4.putBundle(r5, r6)
            android.os.Bundle r4 = r10.d
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            java.lang.String r6 = "a"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r6)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0049 }
            int r2 = r2.getInt(r7)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0049 }
            r5.putInt(r1, r2)     // Catch:{ IllegalAccessException | IllegalArgumentException | NoSuchFieldException -> 0x0049 }
        L_0x0049:
            r4.putBundle(r1, r5)
            android.os.Bundle r1 = r10.d
            java.lang.String r2 = "registered"
            r4 = 0
            android.os.Bundle r5 = a(r2, r4)
            r1.putBundle(r2, r5)
            android.os.Bundle r1 = r10.d
            java.lang.String r2 = "pushRegistered"
            android.os.Bundle r4 = a(r2, r4)
            r1.putBundle(r2, r4)
            android.os.Bundle r1 = r10.d
            java.lang.String r2 = "tryRegistering"
            r4 = 1
            android.os.Bundle r5 = a(r2, r4)
            r1.putBundle(r2, r5)
            android.os.Bundle r1 = r10.d
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.String r5 = "nonce"
            r2.putString(r5, r3)
            r1.putBundle(r5, r2)
            android.os.Bundle r1 = r10.d
            java.lang.String r2 = "uploadWifionly"
            android.os.Bundle r5 = a(r2, r4)
            r1.putBundle(r2, r5)
            android.os.Bundle r1 = r10.d
            java.lang.String r2 = "supportPush"
            android.os.Bundle r5 = a(r2, r4)
            r1.putBundle(r2, r5)
            android.os.Bundle r1 = r10.d
            android.content.Context r2 = r10.getContext()
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ Exception -> 0x00a7 }
            r6.<init>()     // Catch:{ Exception -> 0x00a7 }
            r5.putBundle(r0, r6)     // Catch:{ Exception -> 0x00a7 }
        L_0x00a7:
            android.os.Bundle r6 = r5.getBundle(r0)     // Catch:{ Exception -> 0x00bd }
            java.lang.String r7 = "serviceClientVer"
            java.lang.String r2 = a.C0068a.G(r2)     // Catch:{ Exception -> 0x00bd }
            boolean r8 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00bd }
            if (r8 != 0) goto L_0x00b8
            goto L_0x00ba
        L_0x00b8:
            java.lang.String r2 = "unknown"
        L_0x00ba:
            r6.putString(r7, r2)     // Catch:{ Exception -> 0x00bd }
        L_0x00bd:
            r1.putBundle(r0, r5)
            android.os.Bundle r0 = r10.d
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "serviceName"
            java.lang.String r5 = "Samsung Software"
            r1.putString(r2, r5)
            r0.putBundle(r2, r1)
            android.os.Bundle r0 = r10.d
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e1:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x00f1
            java.lang.Object r5 = r1.next()
            java.lang.String r5 = (java.lang.String) r5
            r2.putString(r5, r5)
            goto L_0x00e1
        L_0x00f1:
            java.lang.String r1 = "authorityList"
            r0.putBundle(r1, r2)
            android.os.Bundle r0 = r10.d
            java.lang.String r1 = ae.C0902a.f3987a
            java.lang.String r2 = "setDeviceId : "
            android.util.Log.d(r1, r2)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "deviceId"
            r1.putString(r2, r3)
            r0.putBundle(r2, r1)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.os.Bundle r0 = r10.b(r0)
            android.os.Bundle r1 = r10.d
            java.lang.String r2 = "logList"
            r1.putBundle(r2, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.os.Bundle r0 = r10.b(r0)
            android.os.Bundle r10 = r10.d
            java.lang.String r1 = "plainLogList"
            r10.putBundle(r1, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sec.android.diagmonagent.log.provider.DiagMonProvider.c():boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:58|59|60|61|63|64|65|66|67|69|70|71|73|74|75) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0183 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle call(java.lang.String r5, java.lang.String r6, android.os.Bundle r7) {
        /*
            r4 = this;
            java.lang.String r0 = "service_registration"
            boolean r0 = r0.equals(r5)
            r1 = 0
            if (r0 == 0) goto L_0x0071
            java.lang.String r5 = "serviceId"
            java.lang.String r6 = ""
            java.lang.String r5 = r7.getString(r5, r6)
            r4.e = r5
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0.add(r5)
            android.os.Bundle r5 = r4.d
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0026:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0036
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            r2.putString(r3, r3)
            goto L_0x0026
        L_0x0036:
            java.lang.String r0 = "authorityList"
            r5.putBundle(r0, r2)
            java.lang.String r5 = "deviceId"
            java.lang.String r0 = r7.getString(r5, r6)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x004f
            java.lang.String r0 = ae.C0902a.f3987a
            java.lang.String r2 = "setDeviceId : "
            android.util.Log.d(r0, r2)
            goto L_0x0050
        L_0x004f:
            r6 = r0
        L_0x0050:
            android.os.Bundle r0 = r4.d
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            r2.putString(r5, r6)
            r0.putBundle(r5, r2)
            android.os.Bundle r4 = r4.d
            java.lang.String r5 = "serviceAgreeType"
            boolean r5 = r7.getBoolean(r5, r1)
            java.lang.String r6 = "agreed"
            android.os.Bundle r5 = a(r6, r5)
            r4.putBundle(r6, r5)
            android.os.Bundle r4 = android.os.Bundle.EMPTY
            return r4
        L_0x0071:
            java.lang.String r0 = "update_path"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x00be
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x00b0 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x00b0 }
            java.io.File[] r6 = r7.listFiles()     // Catch:{ Exception -> 0x00b0 }
            if (r6 == 0) goto L_0x00b0
            int r7 = r6.length     // Catch:{ Exception -> 0x00b0 }
        L_0x008a:
            if (r1 >= r7) goto L_0x00b0
            r0 = r6[r1]     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r2 = r0.getPath()     // Catch:{ Exception -> 0x00b0 }
            r5.add(r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b0 }
            r2.<init>()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r3 = "found file : "
            r2.append(r3)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r0 = r0.getPath()     // Catch:{ Exception -> 0x00b0 }
            r2.append(r0)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00b0 }
            D1.f.L(r0)     // Catch:{ Exception -> 0x00b0 }
            int r1 = r1 + 1
            goto L_0x008a
        L_0x00b0:
            android.os.Bundle r5 = r4.b(r5)
            android.os.Bundle r4 = r4.d
            java.lang.String r6 = "logList"
            r4.putBundle(r6, r5)
            android.os.Bundle r4 = android.os.Bundle.EMPTY
            return r4
        L_0x00be:
            java.lang.String r0 = "clear"
            boolean r0 = r0.equals(r5)
            java.lang.String r2 = "diagmon_preferences"
            if (r0 == 0) goto L_0x00dd
            android.content.Context r4 = r4.getContext()
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r2, r1)
            android.content.SharedPreferences$Editor r4 = r4.edit()
            r4.clear()
            r4.apply()
            android.os.Bundle r4 = android.os.Bundle.EMPTY
            return r4
        L_0x00dd:
            java.lang.String r0 = "set"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x013c
            android.content.Context r4 = r4.getContext()
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r2, r1)
            android.content.SharedPreferences$Editor r4 = r4.edit()
            java.lang.Object r5 = r7.get(r6)
            boolean r7 = r5 instanceof java.lang.Boolean
            if (r7 == 0) goto L_0x0103
            r7 = r5
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r4.putBoolean(r6, r7)
        L_0x0103:
            boolean r7 = r5 instanceof java.lang.Float
            if (r7 == 0) goto L_0x0111
            r7 = r5
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            r4.putFloat(r6, r7)
        L_0x0111:
            boolean r7 = r5 instanceof java.lang.Integer
            if (r7 == 0) goto L_0x011f
            r7 = r5
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4.putInt(r6, r7)
        L_0x011f:
            boolean r7 = r5 instanceof java.lang.Long
            if (r7 == 0) goto L_0x012d
            r7 = r5
            java.lang.Long r7 = (java.lang.Long) r7
            long r0 = r7.longValue()
            r4.putLong(r6, r0)
        L_0x012d:
            boolean r7 = r5 instanceof java.lang.String
            if (r7 == 0) goto L_0x0136
            java.lang.String r5 = (java.lang.String) r5
            r4.putString(r6, r5)
        L_0x0136:
            r4.apply()
            android.os.Bundle r4 = android.os.Bundle.EMPTY
            return r4
        L_0x013c:
            java.lang.String r0 = "get"
            boolean r3 = r0.equals(r5)
            if (r3 == 0) goto L_0x0161
            android.content.Context r3 = r4.getContext()
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r2, r1)
            boolean r3 = r3.contains(r6)
            if (r3 != 0) goto L_0x0161
            android.os.Bundle r3 = r4.d
            android.os.Bundle r3 = r3.getBundle(r6)
            if (r3 == 0) goto L_0x0161
            android.os.Bundle r4 = r4.d
            android.os.Bundle r4 = r4.getBundle(r6)
            return r4
        L_0x0161:
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x019c
            android.content.Context r4 = r4.getContext()
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r2, r1)
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            boolean r7 = r4.getBoolean(r6, r1)     // Catch:{ ClassCastException -> 0x017b }
            r5.putBoolean(r6, r7)     // Catch:{ ClassCastException -> 0x017b }
        L_0x017b:
            r7 = 0
            float r7 = r4.getFloat(r6, r7)     // Catch:{ ClassCastException -> 0x0183 }
            r5.putFloat(r6, r7)     // Catch:{ ClassCastException -> 0x0183 }
        L_0x0183:
            int r7 = r4.getInt(r6, r1)     // Catch:{ ClassCastException -> 0x018a }
            r5.putInt(r6, r7)     // Catch:{ ClassCastException -> 0x018a }
        L_0x018a:
            r0 = 0
            long r0 = r4.getLong(r6, r0)     // Catch:{ ClassCastException -> 0x0193 }
            r5.putLong(r6, r0)     // Catch:{ ClassCastException -> 0x0193 }
        L_0x0193:
            r7 = 0
            java.lang.String r4 = r4.getString(r6, r7)     // Catch:{ ClassCastException -> 0x019b }
            r5.putString(r6, r4)     // Catch:{ ClassCastException -> 0x019b }
        L_0x019b:
            return r5
        L_0x019c:
            android.os.Bundle r4 = super.call(r5, r6, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sec.android.diagmonagent.log.provider.DiagMonProvider.call(java.lang.String, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        throw new RuntimeException("Operation not supported");
    }

    public final String getType(Uri uri) {
        return "text/plain";
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new RuntimeException("Operation not supported");
    }

    public final boolean onCreate() {
        c();
        return true;
    }

    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        String path = uri.getPath();
        if (this.d.getBundle("logList") == null || this.d.getBundle("plainLogList") == null) {
            throw new RuntimeException("Data is corrupted");
        } else if (this.d.getBundle("logList").containsKey(path) || this.d.getBundle("plainLogList").containsKey(path)) {
            return ParcelFileDescriptor.open(new File(path), 268435456);
        } else {
            throw new FileNotFoundException();
        }
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new RuntimeException("Operation not supported");
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new RuntimeException("Operation not supported");
    }
}
