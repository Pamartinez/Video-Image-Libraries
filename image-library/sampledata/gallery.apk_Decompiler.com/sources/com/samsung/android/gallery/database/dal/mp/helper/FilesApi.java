package com.samsung.android.gallery.database.dal.mp.helper;

import A.a;
import H7.B;
import N2.j;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilesApi {
    private static final CharSequence TAG = "FilesApi";
    QueryParams mParams = new QueryParams();
    private final QueryExecutor mQueryExecutor = new QueryExecutor();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Cache {
        static final FilesTableCache instance;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class AnalyzeTableCache extends FilesTableCache {
            static final Uri ANALYZE_TABLE_URI = Uri.parse("content://secmedia/cmh/analyze_info");

            private int updateInternal(Context context, long j2, ContentValues contentValues) {
                try {
                    if (context.getContentResolver().insert(ANALYZE_TABLE_URI, contentValues) != null) {
                        return 1;
                    }
                    return 0;
                } catch (Exception e) {
                    a.s(e, new StringBuilder("update failed. e="), "MediaCache#analyze");
                    return 0;
                }
            }

            public int update(Context context, long j2, byte[] bArr) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("sec_media_id", Long.valueOf(j2));
                contentValues.put("ai_cache", bArr);
                return updateInternal(context, j2, contentValues);
            }

            public int updateMotionPhotoViewMode(Context context, long j2, int i2) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("sec_media_id", Long.valueOf(j2));
                contentValues.put("motionphoto_viewmode", Integer.valueOf(i2));
                return updateInternal(context, j2, contentValues);
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class FilesTableCache {
            static final Uri SEC_MP_URI = MediaUri.getSecMediaUri(false);

            public int update(Context context, long j2, byte[] bArr) {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ai_cache", bArr);
                    return context.getContentResolver().update(SEC_MP_URI, contentValues, "_id=?", new String[]{String.valueOf(j2)});
                } catch (Exception e) {
                    a.s(e, new StringBuilder("update failed. e="), "MediaCache#files");
                    return 0;
                }
            }

            public int updateMotionPhotoViewMode(Context context, long j2, int i2) {
                return 0;
            }
        }

        static {
            FilesTableCache filesTableCache;
            if (Features.isEnabled(Features.SUPPORT_MP_MEDIA_CACHE_TABLE)) {
                filesTableCache = new AnalyzeTableCache();
            } else {
                filesTableCache = new FilesTableCache();
            }
            instance = filesTableCache;
        }

        public static int update(Context context, long j2, byte[] bArr) {
            return instance.update(context, j2, bArr);
        }

        public static int updateMotionPhotoViewMode(Context context, long j2, int i2) {
            return instance.updateMotionPhotoViewMode(context, j2, i2);
        }
    }

    public static long findBestId(long j2) {
        Cursor rawQuery;
        if (j2 != 0) {
            try {
                rawQuery = new SecMpQueryExecutor().rawQuery(C0212a.o(j.j(j2, "select F._id, F.burst_group_id, G.group_id from files as F left join group_contents as G on F._id=G.sec_media_id where (F.burst_group_id=", " or G.group_id="), j2, ") and (F.is_trashed is null or F.is_trashed=0) and (F.bucket_id not in (select bucket_id from files where is_hide=1 group by bucket_id)) order by (case when F.burst_group_id > 0 then F.best_image when G.group_id > 0 then G.best_image end) desc, F.datetime desc limit 1"), "findBestId");
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        long j3 = rawQuery.getLong(0);
                        rawQuery.close();
                        return j3;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Exception e) {
                CharSequence charSequence = TAG;
                Log.e(charSequence, "findBestId failed. e=" + e.getMessage());
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return 0;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        if (r6 != false) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long findGroupId(long r4, boolean r6) {
        /*
            java.lang.String r0 = "select F._id, F.burst_group_id, G.group_id from files as F left join group_contents as G on F._id=G.sec_media_id where F._id="
            java.lang.String r4 = A.a.f(r0, r4)
            r0 = 0
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r5 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x0028 }
            r5.<init>()     // Catch:{ Exception -> 0x0028 }
            java.lang.String r2 = "findGroupId"
            android.database.Cursor r4 = r5.rawQuery((java.lang.String) r4, (java.lang.String) r2)     // Catch:{ Exception -> 0x0028 }
            if (r4 == 0) goto L_0x0040
            boolean r5 = r4.moveToFirst()     // Catch:{ all -> 0x0036 }
            if (r5 == 0) goto L_0x0040
            r5 = 1
            long r2 = r4.getLong(r5)     // Catch:{ all -> 0x0036 }
            int r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r5 == 0) goto L_0x002a
        L_0x0024:
            r4.close()     // Catch:{ Exception -> 0x0028 }
            return r2
        L_0x0028:
            r4 = move-exception
            goto L_0x0046
        L_0x002a:
            r5 = 2
            long r2 = r4.getLong(r5)     // Catch:{ all -> 0x0036 }
            int r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r5 == 0) goto L_0x0040
            if (r6 == 0) goto L_0x0040
            goto L_0x0024
        L_0x0036:
            r5 = move-exception
            r4.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch:{ Exception -> 0x0028 }
        L_0x003f:
            throw r5     // Catch:{ Exception -> 0x0028 }
        L_0x0040:
            if (r4 == 0) goto L_0x005d
            r4.close()     // Catch:{ Exception -> 0x0028 }
            goto L_0x005d
        L_0x0046:
            java.lang.CharSequence r5 = TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r2 = "findGroupId failed. e="
            r6.<init>(r2)
            java.lang.String r4 = r4.getMessage()
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            com.samsung.android.gallery.support.utils.Log.e(r5, r4)
        L_0x005d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.helper.FilesApi.findGroupId(long, boolean):long");
    }

    private static void setImageSize(ContentValues contentValues, int i2, int i7) {
        contentValues.put("width", Integer.valueOf(i2));
        contentValues.put("height", Integer.valueOf(i7));
    }

    public static void updateMediaAsync(Uri uri, int i2, int i7) {
        if (i2 > 0 && i7 > 0 && MediaUri.isSecMediaUri(uri)) {
            SimpleThreadPool.getInstance().execute(new B(uri, i2, i7, 6));
        }
    }

    public int deleteHighlightTime(List<Long> list) {
        try {
            if (list.size() <= 0) {
                return 0;
            }
            Uri parse = Uri.parse("content://secmedia/cmh/highlight");
            String str = "sec_media_id IN " + CursorHelper.joinIds(list);
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("highlight_time");
            return this.mQueryExecutor.getContentResolver().update(parse, contentValues, str, (String[]) null);
        } catch (Exception e) {
            Log.e(TAG, "deleteHighlightTime failed e=" + e);
            return 0;
        }
    }

    public int deleteRevitalizationData(List<Long> list) {
        try {
            String str = "_id IN " + CursorHelper.joinIds(list);
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("revitalized_type");
            contentValues.putNull("revitalized_time");
            contentValues.putNull("revitalized_path");
            return this.mQueryExecutor.getContentResolver().update(MediaUri.getSecMediaUri(true), contentValues, str, (String[]) null);
        } catch (Exception e) {
            Log.e(TAG, "deleteRevitalizationData failed", (Throwable) e);
            return 0;
        }
    }

    public String getCloudOriginalBinaryHash(long j2) {
        Cursor query;
        Throwable th;
        try {
            String[] strArr = {"cloud_original_binary_hash"};
            ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
            if (contentResolver == null) {
                return null;
            }
            Uri secMediaUri = MediaUri.getSecMediaUri(false);
            query = contentResolver.query(secMediaUri, strArr, "_id=" + j2, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    query.close();
                    return string;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (SQLiteException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public long getSecFileId(long j2) {
        Throwable th;
        String[] strArr = {"_id"};
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        if (contentResolver == null) {
            return -1;
        }
        Cursor query = contentResolver.query(MediaUri.getSecMediaUri(false), strArr, a.f("media_id=", j2), (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    long j3 = query.getLong(0);
                    query.close();
                    return j3;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (query == null) {
            return -1;
        }
        query.close();
        return -1;
        throw th;
    }

    public Uri insertImage(Bitmap bitmap, File file, long j2, double d, double d2, int i2, String str) {
        String baseName = FileUtils.getBaseName(file.getAbsolutePath());
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        ContentValues c5 = C0086a.c("title", baseName);
        c5.put("_display_name", file.getName());
        c5.put(IParameterKey.DATE_TAKEN, Long.valueOf(j2));
        c5.put(IParameterKey.DATE_MODIFIED, Long.valueOf(currentTimeMillis));
        c5.put("date_added", Long.valueOf(currentTimeMillis));
        c5.put("mime_type", str);
        c5.put("orientation", Integer.valueOf(i2));
        c5.put("_data", file.getAbsolutePath());
        c5.put(IParameterKey.SIZE, Long.valueOf(file.length()));
        setImageSize(c5, bitmap.getWidth(), bitmap.getHeight());
        if (MapUtil.isValidLocation(d, d2)) {
            c5.put("latitude", Double.valueOf(d));
            c5.put("longitude", Double.valueOf(d2));
        }
        return this.mQueryExecutor.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, c5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        if (r7.getCount() > 0) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isUriItemValid(java.lang.String r8, android.net.Uri r9) {
        /*
            r7 = this;
            android.net.Uri r1 = android.net.Uri.parse(r8)
            if (r9 == 0) goto L_0x000b
            java.lang.String r8 = r9.getHost()
            goto L_0x000c
        L_0x000b:
            r8 = 0
        L_0x000c:
            r6 = 1
            if (r8 == 0) goto L_0x0080
            java.lang.String r0 = r1.getHost()
            boolean r8 = r8.equals(r0)
            if (r8 != 0) goto L_0x001a
            goto L_0x0080
        L_0x001a:
            r8 = 0
            com.samsung.android.gallery.database.dal.abstraction.query.QueryExecutor r7 = r7.mQueryExecutor     // Catch:{ Exception -> 0x003e }
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x003e }
            r4 = 0
            r5 = 0
            r2 = 0
            r3 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x003e }
            if (r7 == 0) goto L_0x0041
            int r0 = r7.getCount()     // Catch:{ all -> 0x0032 }
            if (r0 <= 0) goto L_0x0041
            goto L_0x0042
        L_0x0032:
            r0 = move-exception
            r2 = r0
            r7.close()     // Catch:{ all -> 0x0038 }
            goto L_0x003d
        L_0x0038:
            r0 = move-exception
            r7 = r0
            r2.addSuppressed(r7)     // Catch:{ Exception -> 0x003e }
        L_0x003d:
            throw r2     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            r0 = move-exception
            r7 = r0
            goto L_0x0048
        L_0x0041:
            r6 = r8
        L_0x0042:
            if (r7 == 0) goto L_0x0047
            r7.close()     // Catch:{ Exception -> 0x003e }
        L_0x0047:
            return r6
        L_0x0048:
            java.lang.CharSequence r0 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isUriItemValid {"
            r2.<init>(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = ","
            r3.append(r1)
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            java.lang.String r9 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r9)
            r2.append(r9)
            java.lang.String r9 = "} e="
            r2.append(r9)
            java.lang.String r7 = r7.getMessage()
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.samsung.android.gallery.support.utils.Log.e(r0, r7)
            return r8
        L_0x0080:
            java.lang.CharSequence r7 = TAG
            java.lang.String r8 = "isUriItemValid different host > ignored"
            com.samsung.android.gallery.support.utils.Log.d(r7, r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.helper.FilesApi.isUriItemValid(java.lang.String, android.net.Uri):boolean");
    }

    public int reserveDateTime(long j2, long j3) {
        try {
            String str = "_id=" + j2;
            ContentValues contentValues = new ContentValues();
            contentValues.put("date_restored", Long.valueOf(j3));
            return this.mQueryExecutor.getContentResolver().update(MediaUri.getSecMediaUri(false), contentValues, str, (String[]) null);
        } catch (Exception e) {
            Log.e(TAG, "reserveDateTime failed", (Throwable) e);
            return 0;
        }
    }

    public Uri saveMediaFile(String str, byte[] bArr) {
        Uri uri;
        OutputStream openOutputStream;
        ContentResolver contentResolver = AppResources.getAppContext().getContentResolver();
        if (contentResolver == null) {
            Log.e(TAG, "saveMediaFile failed. null resolver");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", FileUtils.getRelativePath(str));
        contentValues.put("_display_name", FileUtils.getNameFromPath(str));
        contentValues.put("is_pending", 1);
        try {
            uri = contentResolver.insert(MediaUri.getInstance().getFilesUri(), contentValues);
        } catch (IllegalArgumentException unused) {
            uri = contentResolver.insert(MediaUri.getInstance().getProperUri(str), contentValues);
        }
        try {
            openOutputStream = contentResolver.openOutputStream(uri);
            openOutputStream.write(bArr);
            openOutputStream.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("is_pending", 0);
        int update = contentResolver.update(uri, contentValues2, (String) null, (String[]) null);
        CharSequence charSequence = TAG;
        Log.d(charSequence, "saveMediaFile" + Logger.vt(uri, Integer.valueOf(update), "", Long.valueOf(currentTimeMillis)));
        if (update > 0) {
            return uri;
        }
        return null;
        throw th;
    }

    public int updateGroupMediaBestImage(Uri uri, long j2, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("best_image", Integer.valueOf(i2));
        try {
            return this.mQueryExecutor.getContentResolver().update(uri, contentValues, "sec_media_id = " + j2, (String[]) null);
        } catch (SQLiteException | NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return 0;
        }
    }

    public void updateMedia(String str, long j2, long j3, int i2, long j8) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IParameterKey.DATE_TAKEN, Long.valueOf(j3));
        contentValues.put(IParameterKey.DATE_MODIFIED, Long.valueOf(j8));
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("orientation", Integer.valueOf(i2));
        contentValues.put(IParameterKey.SIZE, Long.valueOf(j2));
        try {
            this.mQueryExecutor.getContentResolver().update(Uri.parse(str), contentValues, (String) null, (String[]) null);
        } catch (SQLiteException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public int updateMediaBestImage(Uri uri, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("best_image", Integer.valueOf(i2));
        try {
            return this.mQueryExecutor.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        } catch (SQLiteException | NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return 0;
        }
    }

    public void updateMediaSize(Uri uri, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IParameterKey.DATE_MODIFIED, Long.valueOf(System.currentTimeMillis() / 1000));
        if (j2 > 0) {
            contentValues.put(IParameterKey.SIZE, Long.valueOf(j2));
        }
        try {
            this.mQueryExecutor.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        } catch (SQLiteException | NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public int updateMedia(Uri uri, int i2, int i7, boolean z) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("width", Integer.valueOf(i2));
            contentValues.put("height", Integer.valueOf(i7));
            if (!z) {
                uri = uri.buildUpon().appendQueryParameter("nonotify", "1").build();
            }
            int update = this.mQueryExecutor.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
            CharSequence charSequence = TAG;
            StringBuilder sb2 = new StringBuilder("updateMedia");
            Integer valueOf = Integer.valueOf(i2);
            Integer valueOf2 = Integer.valueOf(i7);
            sb2.append(Logger.vt(uri, valueOf, valueOf2, "#" + update, Long.valueOf(currentTimeMillis)));
            Log.d(charSequence, sb2.toString());
            return update;
        } catch (SQLiteException | NullPointerException e) {
            CharSequence charSequence2 = TAG;
            Log.e(charSequence2, "updateMedia failed. e=" + e.getMessage());
            return 0;
        }
    }
}
