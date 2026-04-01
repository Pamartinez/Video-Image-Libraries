package com.samsung.android.gallery.module.similarphoto;

import A.a;
import A4.C0370e;
import Ad.C0720a;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.mobileservice.social.group.BundleKey;
import h4.C0464a;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimilarPhotoHelper {
    private static final ConcurrentHashMap<String, Integer> sSimilarCountCache = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UriHolder {
        /* access modifiers changed from: private */
        public static final boolean SUPPORT;
        /* access modifiers changed from: private */
        public static final Uri URI = Uri.parse("content://com.samsung.cmh/files");

        static {
            boolean z;
            if (!Features.isEnabled(Features.USE_CMH) || !Features.isEnabled(Features.SUPPORT_SIMILAR_PHOTO)) {
                z = false;
            } else {
                z = true;
            }
            SUPPORT = z;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037 A[Catch:{ all -> 0x0024, all -> 0x0064, Exception -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003b A[Catch:{ all -> 0x0024, all -> 0x0064, Exception -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057 A[SYNTHETIC, Splitter:B:21:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkSimilarPhoto(android.content.Context r10) {
        /*
            java.lang.String r0 = "SimilarPhotoHelper"
            java.lang.String r1 = "checkSimilarPhoto {"
            r2 = 0
            if (r10 != 0) goto L_0x0008
            return r2
        L_0x0008:
            long r3 = java.lang.System.currentTimeMillis()
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r10 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x005b }
            r10.<init>()     // Catch:{ Exception -> 0x005b }
            java.lang.String r5 = "SELECT group_id, count(*) as count FROM group_contents WHERE group_type = 2 GROUP BY group_id having count > 1 ORDER BY group_id limit 1"
            java.lang.String r6 = "checkSimilarPhoto"
            android.database.Cursor r10 = r10.rawQuery((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x005b }
            r5 = 1
            if (r10 == 0) goto L_0x0026
            boolean r6 = r10.moveToFirst()     // Catch:{ all -> 0x0024 }
            if (r6 == 0) goto L_0x0026
            r6 = r5
            goto L_0x0027
        L_0x0024:
            r1 = move-exception
            goto L_0x005e
        L_0x0026:
            r6 = r2
        L_0x0027:
            com.samsung.android.gallery.support.utils.GalleryPreference r7 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstance()     // Catch:{ all -> 0x0024 }
            com.samsung.android.gallery.support.utils.PreferenceName r8 = com.samsung.android.gallery.support.utils.PreferenceName.TIMELINE_SIMILAR_PHOTO_EXIST     // Catch:{ all -> 0x0024 }
            r7.saveState((com.samsung.android.gallery.support.utils.PreferenceName) r8, (boolean) r6)     // Catch:{ all -> 0x0024 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
            r7.<init>(r1)     // Catch:{ all -> 0x0024 }
            if (r6 == 0) goto L_0x003b
            java.lang.String r1 = "valid"
            goto L_0x003d
        L_0x003b:
            java.lang.String r1 = "invalid"
        L_0x003d:
            r7.append(r1)     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = "} +"
            r7.append(r1)     // Catch:{ all -> 0x0024 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0024 }
            long r8 = r8 - r3
            r7.append(r8)     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x0024 }
            com.samsung.android.gallery.support.utils.Log.d(r0, r1)     // Catch:{ all -> 0x0024 }
            if (r10 == 0) goto L_0x005d
            r10.close()     // Catch:{ Exception -> 0x005b }
            return r5
        L_0x005b:
            r10 = move-exception
            goto L_0x0069
        L_0x005d:
            return r5
        L_0x005e:
            if (r10 == 0) goto L_0x0068
            r10.close()     // Catch:{ all -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r10 = move-exception
            r1.addSuppressed(r10)     // Catch:{ Exception -> 0x005b }
        L_0x0068:
            throw r1     // Catch:{ Exception -> 0x005b }
        L_0x0069:
            java.lang.String r1 = "checkSimilarPhoto failed"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r0, (java.lang.String) r1, (java.lang.Throwable) r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r1 = "checkSimilarPhoto failed +"
            r10.<init>(r1)
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r3
            r10.append(r5)
            java.lang.String r10 = r10.toString()
            com.samsung.android.gallery.support.utils.Log.w(r0, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper.checkSimilarPhoto(android.content.Context):boolean");
    }

    public static boolean checkToShowSimilarPhotoAnim() {
        if ((!hasSimilarPhoto() || !isSimilarPhotoMode()) && isSimilarPhotoMode()) {
            return false;
        }
        return true;
    }

    public static boolean checkToShowSimilarPhotoToast() {
        if (hasSimilarPhoto() || isSimilarPhotoMode()) {
            return false;
        }
        return true;
    }

    public static void clearSimilarPhoto(Context context, List<MediaItem> list) {
        if (UriHolder.SUPPORT) {
            long currentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("best_image", 0);
            contentValues.put("burst_group_id", 0);
            contentValues.put(BundleKey.GROUP_TYPE, 0);
            try {
                ArrayList arrayList = (ArrayList) list.stream().map(new b(22, contentValues)).collect(Collectors.toCollection(new C0720a(1)));
                long count = Stream.of(context.getContentResolver().applyBatch(UriHolder.URI.getAuthority(), arrayList)).filter(new C0464a(3)).count();
                StringBuilder sb2 = new StringBuilder("clearSimilarPhoto");
                sb2.append(Logger.vt("in=" + arrayList.size(), "p=" + (((long) arrayList.size()) - count), "f=" + count, Long.valueOf(currentTimeMillis)));
                Log.d("SimilarPhotoHelper", sb2.toString());
            } catch (Exception e) {
                a.s(e, new StringBuilder("clearSimilarPhoto failed. e="), "SimilarPhotoHelper");
            }
        }
    }

    public static void deleteGroupContents(Context context, int i2, long j2, long j3) {
        if (context != null) {
            SimpleThreadPool.getInstance().execute(new ha.b(context, i2, j2, j3));
        }
    }

    private static String getCacheKey(int i2, long j2) {
        return i2 + "/" + j2;
    }

    public static Integer getCachedSimilarCount(int i2, long j2) {
        return sSimilarCountCache.get(getCacheKey(i2, j2));
    }

    public static int getSimilarCount(int i2, long j2) {
        Cursor query;
        int i7 = 0;
        try {
            query = DbCompat.query(DbKey.FILES_SIMILAR_COUNT, new ha.a(i2, j2, 0));
            if (query != null) {
                if (query.getCount() == 1 && query.moveToFirst()) {
                    i7 = query.getInt(0);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        sSimilarCountCache.put(getCacheKey(i2, j2), Integer.valueOf(i7));
        return i7;
        throw th;
    }

    public static boolean hasCachedCount(int i2, long j2) {
        return sSimilarCountCache.containsKey(getCacheKey(i2, j2));
    }

    public static boolean hasSimilarPhoto() {
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.TIMELINE_SIMILAR_PHOTO_EXIST, false);
    }

    public static boolean isSimilarPhotoMode() {
        if (!Features.isEnabled(Features.SUPPORT_SIMILAR_PHOTO) || !PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineSimilarPhotoMode)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ContentProviderOperation lambda$clearSimilarPhoto$3(ContentValues contentValues, MediaItem mediaItem) {
        ContentValues contentValues2 = new ContentValues(contentValues);
        contentValues2.put("is_cloud", Integer.valueOf(mediaItem.getStorageType().ordinal()));
        contentValues2.put("sec_media_id", Long.valueOf(mediaItem.getFileId()));
        ContentProviderOperation.Builder withValues = ContentProviderOperation.newUpdate(UriHolder.URI).withValues(contentValues2);
        return withValues.withSelection("group_type=2 AND sec_media_id=" + mediaItem.getFileId(), (String[]) null).build();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$clearSimilarPhoto$4(ContentProviderResult contentProviderResult) {
        if (contentProviderResult == null || contentProviderResult.count.intValue() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deleteGroupContents$0(Context context, int i2, long j2, long j3) {
        try {
            int delete = context.getContentResolver().delete(MediaUri.getInstance().getGroupTableUri(), "group_type=? and sec_media_id=? and group_id=?", new String[]{String.valueOf(i2), String.valueOf(j2), String.valueOf(j3)});
            Log.d("SimilarPhotoHelper", "deleteGroupContents : id=" + j2 + ", r=" + delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadSimilarCountSync(MediaItem mediaItem) {
        new LatchBuilder("SimilarCount").addWorker(new C0370e(mediaItem, 4)).setTimeout(1000).start();
    }

    public static boolean supportSimilarPhoto() {
        return Features.isEnabled(Features.SUPPORT_SIMILAR_PHOTO);
    }

    public static boolean toggleSimilarPhotoMode() {
        return PreferenceFeatures.toggleEnabled(PreferenceFeatures.TimelineSimilarPhotoMode);
    }
}
