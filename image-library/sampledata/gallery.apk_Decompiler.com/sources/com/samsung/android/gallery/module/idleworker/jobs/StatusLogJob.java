package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.idleworker.jobs.AbsStatusLogJob;
import com.samsung.android.gallery.module.logger.SamsungAnalyticsPrefs;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.analytics.AnalyticsUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Tree;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StatusLogJob extends AbsStatusLogJob {
    static final String ALBUM_TABLE_NAME;

    static {
        String str;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            str = "mxalbum";
        } else {
            str = "album";
        }
        ALBUM_TABLE_NAME = str;
    }

    public StatusLogJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    private String convertCountToString(int i2, int i7) {
        if (i2 == 0) {
            return "0";
        }
        StringBuilder sb2 = new StringBuilder();
        int i8 = (i2 / i7) * i7;
        sb2.append(i8);
        sb2.append("~");
        sb2.append(i8 + i7);
        return sb2.toString();
    }

    private String getCloudItemCount() {
        Cursor rawQuery;
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select count(*) from files where is_cloud > 1", "sa cloud count");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    String convertCountToString = convertCountToString(rawQuery.getInt(0), 100);
                    rawQuery.close();
                    return convertCountToString;
                }
            }
            if (rawQuery == null) {
                return null;
            }
            rawQuery.close();
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getCloudItemCount failed. e="), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private String getCount(AnalyticsStatusId analyticsStatusId, int i2, Integer num) {
        int i7;
        GalleryPreference galleryPreference = this.mPreference;
        String preferenceKey = analyticsStatusId.getPreferenceKey();
        if (num == null) {
            i7 = -1;
        } else {
            i7 = num.intValue();
        }
        int loadInt = galleryPreference.loadInt(preferenceKey, i7);
        if (loadInt >= 0) {
            return convertCountToString(loadInt, i2);
        }
        return null;
    }

    private int getFavoriteCount(int i2) {
        Cursor rawQuery;
        try {
            SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
            rawQuery = secMpQueryExecutor.rawQuery("select count(*) from files where media_type=" + i2 + " and _id in (select _id from files where is_favorite=1)", "sa count");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    int i7 = rawQuery.getInt(0);
                    rawQuery.close();
                    return i7;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("favorite count failed. e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return 0;
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0046 A[SYNTHETIC, Splitter:B:19:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String[] getFileCount() {
        /*
            r7 = this;
            java.lang.String r0 = "0"
            r1 = 2
            java.lang.String[] r1 = new java.lang.String[r1]
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r2 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x004a }
            r2.<init>()     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = "select media_type, count(*) from files where media_type in (1,3) group by media_type"
            java.lang.String r4 = "sa count"
            android.database.Cursor r2 = r2.rawQuery((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x004a }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0040
            boolean r5 = r2.moveToFirst()     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0040
        L_0x001e:
            int r0 = r2.getInt(r4)     // Catch:{ all -> 0x0030 }
            int r5 = r2.getInt(r3)     // Catch:{ all -> 0x0030 }
            if (r5 != r4) goto L_0x0032
            long r5 = (long) r0     // Catch:{ all -> 0x0030 }
            java.lang.String r0 = com.samsung.android.gallery.support.analytics.AnalyticsUtils.convertLargeNumberToString(r5)     // Catch:{ all -> 0x0030 }
            r1[r3] = r0     // Catch:{ all -> 0x0030 }
            goto L_0x0039
        L_0x0030:
            r0 = move-exception
            goto L_0x004d
        L_0x0032:
            long r5 = (long) r0     // Catch:{ all -> 0x0030 }
            java.lang.String r0 = com.samsung.android.gallery.support.analytics.AnalyticsUtils.convertSmallNumberToString(r5)     // Catch:{ all -> 0x0030 }
            r1[r4] = r0     // Catch:{ all -> 0x0030 }
        L_0x0039:
            boolean r0 = r2.moveToNext()     // Catch:{ all -> 0x0030 }
            if (r0 != 0) goto L_0x001e
            goto L_0x0044
        L_0x0040:
            r1[r3] = r0     // Catch:{ all -> 0x0030 }
            r1[r4] = r0     // Catch:{ all -> 0x0030 }
        L_0x0044:
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch:{ Exception -> 0x004a }
            return r1
        L_0x004a:
            r0 = move-exception
            goto L_0x0058
        L_0x004c:
            return r1
        L_0x004d:
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ all -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ Exception -> 0x004a }
        L_0x0057:
            throw r0     // Catch:{ Exception -> 0x004a }
        L_0x0058:
            java.lang.String r7 = r7.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getFileCount failed. e="
            r2.<init>(r3)
            A.a.s(r0, r2, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.idleworker.jobs.StatusLogJob.getFileCount():java.lang.String[]");
    }

    private String getGroupMaxDepth(Context context) {
        Cursor rawQuery;
        SQLiteDatabase readableDatabase = LocalDatabaseHelper.getInstance(context).getReadableDatabase();
        try {
            rawQuery = readableDatabase.rawQuery("select __bucketID,ifnull(folder_id ,0) from " + ALBUM_TABLE_NAME + " where __absPath is null and album_count = -1", (String[]) null);
            if (rawQuery != null) {
                Tree tree = new Tree(0L);
                while (rawQuery.moveToNext()) {
                    tree.addChild(Long.valueOf(rawQuery.getLong(1)), Long.valueOf(rawQuery.getLong(0)));
                }
                String valueOf = String.valueOf(tree.getMaxDepth() - 1);
                rawQuery.close();
                return valueOf;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getGroupMaxDepth failed. e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private String getMainTab() {
        AnalyticsStatusId analyticsStatusId = AnalyticsStatusId.STATUS_MAIN_TAB;
        String loadString = this.mPreference.loadString(analyticsStatusId.getPreferenceKey(), analyticsStatusId.getPreferenceValue());
        if (loadString != null) {
            return loadString.replace("location://", "");
        }
        return null;
    }

    private String getSdcardSize() {
        long totalMemorySize = MemoryUtils.getTotalMemorySize(1);
        if (totalMemorySize == -1) {
            return "0";
        }
        if (totalMemorySize >= 1073741824) {
            long quantizedStorageSize = getQuantizedStorageSize(totalMemorySize / 1000000000);
            Locale locale = Locale.ENGLISH;
            return quantizedStorageSize + " GB";
        }
        long quantizedStorageSize2 = getQuantizedStorageSize(totalMemorySize / 1000000);
        Locale locale2 = Locale.ENGLISH;
        return quantizedStorageSize2 + " MB";
    }

    private String getZoomLevel(AnalyticsStatusId analyticsStatusId) {
        int loadInt = this.mPreference.loadInt(analyticsStatusId.getPreferenceKey(), -1);
        if (loadInt >= 0) {
            return AnalyticsDetail.getZoomLevel(loadInt);
        }
        return null;
    }

    private void updateFavoriteCount() {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        int favoriteCount = getFavoriteCount(MediaType.Image.toInt());
        int favoriteCount2 = getFavoriteCount(MediaType.Video.toInt());
        String quantizeLargeNumber = quantizeLargeNumber(favoriteCount);
        String quantizeSmallNumber = quantizeSmallNumber(favoriteCount2);
        AbsStatusLogJob.SaStringBuilder append = new AbsStatusLogJob.SaStringBuilder().append(AnalyticsStatusId.STATUS_FAVORITES_IMAGE_COUNT, quantizeLargeNumber).append(AnalyticsStatusId.STATUS_FAVORITES_VIDEO_COUNT, quantizeSmallNumber);
        AnalyticsStatusId analyticsStatusId = AnalyticsStatusId.STATUS_FAVORITES_NO_IMAGE_AND_VIDEO_COUNT;
        if (favoriteCount + favoriteCount2 == 0) {
            str = "0";
        } else {
            str = "1";
        }
        append.append(analyticsStatusId, str).commit(this.mSaPreference, "FavoriteCount");
        String str2 = this.TAG;
        a.A(new Object[]{"i=" + favoriteCount + "(" + quantizeLargeNumber + ")", "v=" + favoriteCount2 + "(" + quantizeSmallNumber + ")", Long.valueOf(currentTimeMillis)}, new StringBuilder("favorite count"), str2);
    }

    private void updateStoryAlbumCount() {
        Cursor rawQuery;
        if (Features.isEnabled(Features.USE_CMH)) {
            long currentTimeMillis = System.currentTimeMillis();
            int i2 = 0;
            try {
                rawQuery = new CmhQueryExecutor().rawQuery("select count(*) from story where is_visible=1", "sa story count");
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        i2 = rawQuery.getInt(0);
                    }
                }
                String str = this.TAG;
                Log.d(str, "story album count" + Logger.vt(Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("story album count failed. e="), this.TAG);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            new AbsStatusLogJob.SaStringBuilder().append(AnalyticsStatusId.STATUS_STORY_ALBUM_COUNT, AnalyticsUtils.convertSmallNumberToString((long) i2)).commit(this.mSaPreference, "StoryCount");
            return;
        }
        return;
        throw th;
    }

    public void execute(Context context) {
        SamsungAnalyticsPrefs.writeSettingPrefValue();
        updateExtraStatusLog(context);
        updateFavoriteCount();
        updateStoryAlbumCount();
        new SasCount().summarize().apply();
    }

    public long getQuantizedStorageSize(long j2) {
        int[] iArr = {4, 8, 16, 32, 64, 128, 200, 256, 500, 512, 1024, 2048, 4096, SerializeOptions.SORT};
        for (int i2 = 0; i2 < 14; i2++) {
            int i7 = iArr[i2];
            if (((long) (i7 - 2)) <= j2 && j2 < ((long) (i7 + 2))) {
                return (long) i7;
            }
        }
        return j2;
    }

    public String quantizeLargeNumber(int i2) {
        if (i2 >= 30000) {
            return "30000~";
        }
        if (i2 >= 20000) {
            return "20000~29999";
        }
        if (i2 >= 10000) {
            return "10000~19999";
        }
        if (i2 >= 3000) {
            return "3000~9999";
        }
        if (i2 >= 2000) {
            return "2000~2999";
        }
        if (i2 >= 1000) {
            return "1000~1999";
        }
        if (i2 >= 100) {
            return "100~999";
        }
        if (i2 >= 10) {
            return "10~99";
        }
        if (i2 >= 1) {
            return "1~9";
        }
        return "0";
    }

    public String quantizeSmallNumber(int i2) {
        if (i2 >= 1000) {
            return "1000~";
        }
        if (i2 >= 500) {
            return "500~999";
        }
        if (i2 >= 150) {
            return "150~499";
        }
        if (i2 >= 100) {
            return "100~149";
        }
        if (i2 >= 50) {
            return "50~99";
        }
        if (i2 >= 10) {
            return "10~49";
        }
        if (i2 >= 1) {
            return "1~9";
        }
        return "0";
    }

    public void updateExtraStatusLog(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        AbsStatusLogJob.SaStringBuilder saStringBuilder = new AbsStatusLogJob.SaStringBuilder();
        saStringBuilder.append(AnalyticsStatusId.STATUS_MAIN_TAB, getMainTab());
        AnalyticsStatusId analyticsStatusId = AnalyticsStatusId.STATUS_ALBUM_VIEW_TYPE;
        String zoomLevel = getZoomLevel(analyticsStatusId);
        if (zoomLevel == null) {
            int columnSize = GridHelper.getInstance("location://albums").getColumnSize(context);
            this.mPreference.saveState(analyticsStatusId.getPreferenceKey(), columnSize);
            zoomLevel = AnalyticsDetail.getZoomLevel(columnSize);
        }
        String str = zoomLevel;
        saStringBuilder.append(analyticsStatusId, str);
        AnalyticsStatusId analyticsStatusId2 = AnalyticsStatusId.STATUS_ALBUM_PICTURES_VIEW_TYPE;
        String zoomLevel2 = getZoomLevel(analyticsStatusId2);
        if (zoomLevel2 == null) {
            int columnSize2 = GridHelper.getInstance("location://albums/fileList").getColumnSize(context);
            this.mPreference.saveState(analyticsStatusId2.getPreferenceKey(), columnSize2);
            zoomLevel2 = AnalyticsDetail.getZoomLevel(columnSize2);
        }
        String str2 = zoomLevel2;
        saStringBuilder.append(analyticsStatusId2, str2);
        AnalyticsStatusId analyticsStatusId3 = AnalyticsStatusId.STATUS_TIME_VIEW_TYPE;
        String zoomLevel3 = getZoomLevel(analyticsStatusId3);
        saStringBuilder.append(analyticsStatusId3, zoomLevel3);
        AnalyticsStatusId analyticsStatusId4 = AnalyticsStatusId.STATUS_SPLIT_VIEW_STATE;
        saStringBuilder.append(analyticsStatusId4, analyticsStatusId4.getPreferenceValue());
        String[] fileCount = getFileCount();
        AbsStatusLogJob.SaStringBuilder append = saStringBuilder.append(AnalyticsStatusId.STATUS_DEV_IMAGE_COUNT, fileCount[0]).append(AnalyticsStatusId.STATUS_DEV_VIDEO_COUNT, fileCount[1]);
        AnalyticsStatusId analyticsStatusId5 = AnalyticsStatusId.STATUS_DEV_CLUSTER_COUNT;
        AbsStatusLogJob.SaStringBuilder append2 = append.append(analyticsStatusId5, getCount(analyticsStatusId5, 50, (Integer) null));
        AnalyticsStatusId analyticsStatusId6 = AnalyticsStatusId.STATUS_DEV_BIG_ALBUM_FILE_COUNT;
        AbsStatusLogJob.SaStringBuilder append3 = append2.append(analyticsStatusId6, getCount(analyticsStatusId6, 10, 0));
        AnalyticsStatusId analyticsStatusId7 = AnalyticsStatusId.STATUS_DEV_SHARED_COUNT;
        append3.append(analyticsStatusId7, getCount(analyticsStatusId7, 10, 0)).append(AnalyticsStatusId.STATUS_DEV_SDCARD_SIZE, getSdcardSize()).append(AnalyticsStatusId.STATUS_DEV_CLOUD_COUNT, getCloudItemCount()).append(AnalyticsStatusId.STATUS_DEV_GROUP_MAX_DEPTH, getGroupMaxDepth(context)).commit(this.mSaPreference, "Extra");
        String str3 = this.TAG;
        a.A(new Object[]{zoomLevel3, str, str2, fileCount[0], fileCount[1], Long.valueOf(currentTimeMillis)}, new StringBuilder("update extra"), str3);
    }
}
