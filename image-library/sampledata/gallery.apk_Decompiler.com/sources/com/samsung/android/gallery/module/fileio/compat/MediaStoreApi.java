package com.samsung.android.gallery.module.fileio.compat;

import A.a;
import N2.j;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaStoreApi {
    public static final Uri FILES_URI = MediaStore.Files.getContentUri("external");
    static final String[] PROJECTION = {"_id", "volume_name"};
    static boolean SUPPORT_ALL_DIR;
    private static final boolean SUPPORT_MOVE_TO_TRASH_FILE_OP_OVERWRITE = SdkConfig.atLeast(SdkConfig.GED.U);
    static boolean SUPPORT_PARTIAL_DIR;

    static {
        boolean z;
        boolean atLeast = SdkConfig.atLeast(SdkConfig.GED.S);
        SUPPORT_ALL_DIR = atLeast;
        if (atLeast || !SdkConfig.atLeast(SdkConfig.GED.R)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_PARTIAL_DIR = z;
    }

    public static boolean applyBatch(ArrayList<ContentProviderOperation> arrayList) {
        return applyBatch(FILES_URI, arrayList);
    }

    public static ContentValues buildRename(String str) {
        ContentValues contentValues = new ContentValues();
        String nameFromPath = FileUtils.getNameFromPath(str);
        contentValues.put("relative_path", FileUtils.getRelativePath(str));
        contentValues.put("_display_name", nameFromPath);
        contentValues.put("title", FileUtils.getBaseName(nameFromPath));
        return contentValues;
    }

    public static boolean delete(Uri uri) {
        return AppResources.getAppContext().getContentResolver().delete(uri, (String) null, (String[]) null) > 0;
    }

    public static int getAlbumCount(int i2) {
        Cursor query;
        int i7;
        Throwable th;
        try {
            query = AppResources.getAppContext().getContentResolver().query(FILES_URI, new String[]{"_id"}, C0212a.j(i2, "bucket_id=", " AND is_pending=0"), (String[]) null, (String) null);
            if (query != null) {
                i7 = query.getCount();
            } else {
                i7 = -1;
            }
            if (query != null) {
                query.close();
            }
            return i7;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getAlbumCount failed. e="), "MediaStoreApi");
            return -1;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private static int moveToGoogleTrash(String str) {
        Uri uri = toUri(str);
        if (uri == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_trashed", 1);
        return AppResources.getAppContext().getContentResolver().update(uri, contentValues, (Bundle) null);
    }

    public static boolean moveToTrash(String str) {
        if (!SUPPORT_MOVE_TO_TRASH_FILE_OP_OVERWRITE || moveToGoogleTrash(str) <= 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x005b A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean rename(android.net.Uri r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "MediaStoreApi"
            java.lang.String r1 = "rename failed"
            r2 = 0
            android.content.ContentValues r3 = buildRename(r9)     // Catch:{ Exception -> 0x003c }
            android.content.Context r4 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ Exception -> 0x003c }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ Exception -> 0x003c }
            r5 = 0
            int r3 = r4.update(r8, r3, r5, r5)     // Catch:{ Exception -> 0x003c }
            if (r3 > 0) goto L_0x0059
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003a }
            r4.<init>(r1)     // Catch:{ Exception -> 0x003a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x003a }
            r6 = 0
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x003a }
            java.lang.Object[] r5 = new java.lang.Object[]{r8, r5, r6}     // Catch:{ Exception -> 0x003a }
            java.lang.String r5 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r5)     // Catch:{ Exception -> 0x003a }
            r4.append(r5)     // Catch:{ Exception -> 0x003a }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x003a }
            com.samsung.android.gallery.support.utils.Log.e(r0, r4)     // Catch:{ Exception -> 0x003a }
            goto L_0x0059
        L_0x003a:
            r4 = move-exception
            goto L_0x003e
        L_0x003c:
            r4 = move-exception
            r3 = r2
        L_0x003e:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r9 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r9)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r9}
            java.lang.String r8 = com.samsung.android.gallery.support.utils.Logger.v(r8)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r0, (java.lang.String) r8, (java.lang.Throwable) r4)
        L_0x0059:
            if (r3 <= 0) goto L_0x005c
            r2 = 1
        L_0x005c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.fileio.compat.MediaStoreApi.rename(android.net.Uri, java.lang.String):boolean");
    }

    public static Uri toUri(String str) {
        Cursor query;
        Throwable th;
        try {
            query = AppResources.getAppContext().getContentResolver().query(FILES_URI, PROJECTION, "_data=?", new String[]{str}, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    long j2 = query.getLong(0);
                    String string = query.getString(1);
                    if (j2 > 0) {
                        Uri build = MediaStore.Files.getContentUri(string).buildUpon().appendEncodedPath(String.valueOf(j2)).build();
                        query.close();
                        return build;
                    }
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("toUri failed. e="), "MediaStoreApi");
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static boolean applyBatch(Uri uri, ArrayList<ContentProviderOperation> arrayList) {
        if (arrayList.size() == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String authority = uri.getAuthority();
        try {
            ContentProviderResult[] applyBatch = AppResources.getAppContext().getContentResolver().applyBatch(authority, arrayList);
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < applyBatch.length; i2++) {
                ContentProviderResult contentProviderResult = applyBatch[i2];
                if (contentProviderResult != null) {
                    if (contentProviderResult.count.intValue() != 0) {
                    }
                }
                arrayList2.add(arrayList.get(i2));
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("applyBatch");
            sb2.append(Logger.vt(authority, "in=" + arrayList.size(), "failed=" + arrayList2.size(), Long.valueOf(currentTimeMillis)));
            Log.d("MediaStoreApi", sb2.toString());
            arrayList.clear();
            if (arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
            if (arrayList2.size() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e("MediaStoreApi", "applyBatch failed" + Logger.vt(authority, j.g(new StringBuilder("in="), arrayList), Long.valueOf(currentTimeMillis)) + " e=" + e.getMessage());
            return false;
        }
    }

    public static boolean delete(String str) {
        if (AppResources.getAppContext().getContentResolver().delete(FILES_URI, "_data=?", new String[]{str}) > 0) {
            return true;
        }
        if (!FileUtils.delete(str)) {
            Log.e("MediaStoreApi", "delete failed by uri and file api " + FileUtils.info(str));
        }
        Log.d("MediaStoreApi", "delete by file api");
        return true;
    }
}
