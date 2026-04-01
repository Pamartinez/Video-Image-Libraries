package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import N2.j;
import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.idleworker.jobs.AbsStatusLogJob;
import com.samsung.android.gallery.support.analytics.AnalyticsStatusId;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumStatusLogJob extends AbsStatusLogJob {
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

    public AlbumStatusLogJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    private String getCountRatioString(int i2, int i7) {
        if (i2 == 0) {
            return "0%";
        }
        return "" + ((i2 * 100) / i7) + "%";
    }

    private boolean isGalaxyAlbum(int i2) {
        return AlbumTitleHelper.isTranslatedAlbum(i2).booleanValue();
    }

    private boolean isSystemAlbum(int i2) {
        if (BucketUtils.isCameras(i2) || BucketUtils.isScreenshot(i2) || BucketUtils.isDownload(i2)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045 A[SYNTHETIC, Splitter:B:19:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006f A[SYNTHETIC, Splitter:B:26:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008a A[SYNTHETIC, Splitter:B:41:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateAlbumGroupCount(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "select count(*) from "
            long r1 = java.lang.System.currentTimeMillis()
            com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper r11 = com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper.getInstance(r11)
            android.database.sqlite.SQLiteDatabase r11 = r11.getReadableDatabase()
            r3 = 0
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003f }
            r5.<init>(r0)     // Catch:{ Exception -> 0x003f }
            java.lang.String r6 = ALBUM_TABLE_NAME     // Catch:{ Exception -> 0x003f }
            r5.append(r6)     // Catch:{ Exception -> 0x003f }
            java.lang.String r6 = " where __absPath is null and album_count = -1 and __albumType = 1"
            r5.append(r6)     // Catch:{ Exception -> 0x003f }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x003f }
            android.database.Cursor r5 = r11.rawQuery(r5, r3)     // Catch:{ Exception -> 0x003f }
            if (r5 == 0) goto L_0x0042
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x0035 }
            if (r6 == 0) goto L_0x0042
            int r6 = r5.getInt(r4)     // Catch:{ all -> 0x0035 }
            goto L_0x0043
        L_0x0035:
            r6 = move-exception
            r5.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r5 = move-exception
            r6.addSuppressed(r5)     // Catch:{ Exception -> 0x003f }
        L_0x003e:
            throw r6     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            r5 = move-exception
            r6 = r4
            goto L_0x004a
        L_0x0042:
            r6 = r4
        L_0x0043:
            if (r5 == 0) goto L_0x0056
            r5.close()     // Catch:{ Exception -> 0x0049 }
            goto L_0x0056
        L_0x0049:
            r5 = move-exception
        L_0x004a:
            java.lang.String r7 = r10.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "group count failed. e="
            r8.<init>(r9)
            A.a.s(r5, r8, r7)
        L_0x0056:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r0 = ALBUM_TABLE_NAME     // Catch:{ Exception -> 0x0084 }
            r5.append(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r0 = " where __absPath is not null and __volumeName is not null"
            r5.append(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r0 = r5.toString()     // Catch:{ Exception -> 0x0084 }
            android.database.Cursor r11 = r11.rawQuery(r0, r3)     // Catch:{ Exception -> 0x0084 }
            if (r11 == 0) goto L_0x0087
            boolean r0 = r11.moveToFirst()     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x0087
            int r0 = r11.getInt(r4)     // Catch:{ all -> 0x007a }
            goto L_0x0088
        L_0x007a:
            r0 = move-exception
            r11.close()     // Catch:{ all -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r11 = move-exception
            r0.addSuppressed(r11)     // Catch:{ Exception -> 0x0084 }
        L_0x0083:
            throw r0     // Catch:{ Exception -> 0x0084 }
        L_0x0084:
            r11 = move-exception
            r0 = r4
            goto L_0x008f
        L_0x0087:
            r0 = r4
        L_0x0088:
            if (r11 == 0) goto L_0x009b
            r11.close()     // Catch:{ Exception -> 0x008e }
            goto L_0x009b
        L_0x008e:
            r11 = move-exception
        L_0x008f:
            java.lang.String r3 = r10.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "album count failed. e="
            r5.<init>(r7)
            A.a.s(r11, r5, r3)
        L_0x009b:
            int r11 = r6 + r0
            if (r11 != 0) goto L_0x00a0
            r4 = 1
        L_0x00a0:
            com.samsung.android.gallery.module.idleworker.jobs.AbsStatusLogJob$SaStringBuilder r11 = new com.samsung.android.gallery.module.idleworker.jobs.AbsStatusLogJob$SaStringBuilder
            r11.<init>()
            com.samsung.android.gallery.support.analytics.AnalyticsStatusId r3 = com.samsung.android.gallery.support.analytics.AnalyticsStatusId.STATUS_GROUP_COUNT
            com.samsung.android.gallery.module.idleworker.jobs.AbsStatusLogJob$SaStringBuilder r11 = r11.append((com.samsung.android.gallery.support.analytics.AnalyticsStatusId) r3, (int) r6)
            com.samsung.android.gallery.support.analytics.AnalyticsStatusId r3 = com.samsung.android.gallery.support.analytics.AnalyticsStatusId.STATUS_ALBUM_COUNT
            if (r4 == 0) goto L_0x00b2
            java.lang.String r5 = "n/a"
            goto L_0x00b6
        L_0x00b2:
            java.lang.String r5 = java.lang.String.valueOf(r0)
        L_0x00b6:
            com.samsung.android.gallery.module.idleworker.jobs.AbsStatusLogJob$SaStringBuilder r11 = r11.append((com.samsung.android.gallery.support.analytics.AnalyticsStatusId) r3, (java.lang.String) r5)
            com.samsung.android.gallery.support.utils.GalleryPreference r3 = r10.mSaPreference
            java.lang.String r5 = "AlbumCount"
            r11.commit(r3, r5)
            java.lang.String r10 = r10.TAG
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r3 = "album group count"
            r11.<init>(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r4, r0, r1}
            A.a.A(r0, r11, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.idleworker.jobs.AlbumStatusLogJob.updateAlbumGroupCount(android.content.Context):void");
    }

    private void updateAlbumTypeAndRatio(Context context) {
        Cursor rawQuery;
        Throwable th;
        int i2;
        int i7;
        int i8;
        int i10;
        TimeTickLog timeTickLog;
        String predefinedKeyword;
        TimeTickLog timeTickLog2 = new TimeTickLog();
        ArrayList arrayList = new ArrayList();
        boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums);
        try {
            rawQuery = LocalDatabaseHelper.getInstance(context).getReadableDatabase().rawQuery(C0212a.p(j.k("select ", StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, List.of("__bucketID", "__albumType", "album_count", "__albumLevel", "__Title")), " from "), ALBUM_TABLE_NAME, " where __absPath is not null or __albumType is not 1"), (String[]) null);
            timeTickLog2.tick();
            if (rawQuery != null && rawQuery.moveToFirst()) {
                int columnIndex = rawQuery.getColumnIndex("__bucketID");
                int columnIndex2 = rawQuery.getColumnIndex("__albumType");
                int columnIndex3 = rawQuery.getColumnIndex("album_count");
                int columnIndex4 = rawQuery.getColumnIndex("__albumLevel");
                int i11 = 0;
                boolean z = isEnabled;
                int i12 = 0;
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                while (true) {
                    int i21 = rawQuery.getInt(columnIndex);
                    int i22 = columnIndex;
                    MediaItem mediaItem = new MediaItem();
                    int i23 = columnIndex2;
                    mediaItem.setAlbumType(AlbumType.get(rawQuery.getInt(columnIndex2)));
                    mediaItem.setCount(rawQuery.getInt(columnIndex3));
                    if (isSystemAlbum(i21)) {
                        i11++;
                    } else if (mediaItem.isVirtualAlbum()) {
                        i12++;
                    } else if (isGalaxyAlbum(i21)) {
                        i14++;
                    } else {
                        i13++;
                    }
                    if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                        mediaItem.setAlbumLevel(rawQuery.getInt(columnIndex4));
                        if (mediaItem.isMergedAlbum()) {
                            i15++;
                        }
                        if (mediaItem.isAutoAlbum()) {
                            i16++;
                        }
                        if (mediaItem.isAlbumLvFirst()) {
                            i17++;
                            if (z && BucketUtils.containsMx(i21) && (predefinedKeyword = BucketUtils.getPredefinedKeyword(i21)) != null) {
                                arrayList.add(predefinedKeyword);
                            }
                        }
                    }
                    i2 = i17;
                    if (!mediaItem.isVirtualAlbum() && !mediaItem.isAutoAlbum() && mediaItem.getCount() > 0) {
                        i18 += mediaItem.getCount();
                    }
                    int i24 = i21;
                    i7 = i18;
                    if (BucketUtils.isCamera(i24)) {
                        i19 += mediaItem.getCount();
                    } else if (BucketUtils.isSdCamera(i24)) {
                        i20 += mediaItem.getCount();
                    }
                    int i25 = columnIndex3;
                    i8 = i19;
                    i10 = i20;
                    if (!rawQuery.moveToNext()) {
                        break;
                    }
                    int i26 = i13;
                    i18 = i7;
                    i20 = i10;
                    columnIndex3 = i25;
                    i19 = i8;
                    i17 = i2;
                    columnIndex2 = i23;
                    columnIndex = i22;
                }
                String str = "n/a";
                if (z) {
                    String str2 = str;
                    StringBuilder sb2 = new StringBuilder();
                    timeTickLog = timeTickLog2;
                    sb2.append("");
                    sb2.append(i2);
                    sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    sb2.append(arrayList.size() > 0 ? StringCompat.joinText((CharSequence) NumericEnum.SEP, arrayList) : str2);
                    str = sb2.toString();
                } else {
                    String str3 = str;
                    timeTickLog = timeTickLog2;
                }
                int i27 = i13;
                new AbsStatusLogJob.SaStringBuilder().append(AnalyticsStatusId.STATUS_CREATED_BY_USER_ALBUM_COUNT, i13).append(AnalyticsStatusId.STATUS_MERGED_ALBUM_COUNT, i15).append(AnalyticsStatusId.STATUS_AUTO_UPDATED_ALBUM_COUNT, i16).append(AnalyticsStatusId.STATUS_SYSTEM_ALBUM_COUNT, i11).append(AnalyticsStatusId.STATUS_GALAXY_ALBUM_COUNT, i14).append(AnalyticsStatusId.STATUS_ESSENTIAL_ALBUM_COUNT, str).append(AnalyticsStatusId.STATUS_CAMERA_ALBUM_COUNT_RATIO, getCountRatioString(i8, i7)).append(AnalyticsStatusId.STATUS_CAMERA_SD_ALBUM_COUNT_RATIO, getCountRatioString(i10, i7)).commit(this.mSaPreference, "AlbumTypeRatio");
                timeTickLog.tick();
                String str4 = this.TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("album info");
                Integer valueOf = Integer.valueOf(i27);
                StringBuilder sb4 = sb3;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("m=");
                sb5.append(i15);
                String str5 = "i=(" + i7 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i8 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i10 + ")";
                String str6 = "s=" + i11;
                String sb6 = sb5.toString();
                String str7 = "g=" + i14;
                StringBuilder sb7 = sb4;
                sb7.append(Logger.vt(valueOf, sb6, "a=" + i16, "v=" + i12, str6, str7, "e=" + i2, str5, "[" + str + "]", timeTickLog));
                Log.d(str4, sb7.toString());
            }
            if (rawQuery != null) {
                rawQuery.close();
                return;
            }
            return;
        } catch (Exception e) {
            a.s(e, new StringBuilder("album info failed. e="), this.TAG);
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void execute(Context context) {
        updateAlbumGroupCount(context);
        updateAlbumTypeAndRatio(context);
    }
}
