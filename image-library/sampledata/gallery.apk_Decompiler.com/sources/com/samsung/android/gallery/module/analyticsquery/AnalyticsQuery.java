package com.samsung.android.gallery.module.analyticsquery;

import A4.I;
import C3.p;
import D8.C0546a;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.adobe.internal.xmp.XMPConst;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AnalyticsQuery {
    static final boolean SEC_MP_STORY = SdkConfig.atLeast(SdkConfig.SEM.B_MR5);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SearchEnginList {
        static final ArrayList<String> checkList;
        static final ArrayList<String> list;

        static {
            AnonymousClass1 r0 = new ArrayList<String>() {
                {
                    if (Features.isEnabled(Features.SUPPORT_IMAGE_TAGGER)) {
                        add("Tag");
                    }
                    if (Features.isEnabled(Features.SUPPORT_OCR_ENGINE)) {
                        add("OCR");
                    }
                    if (Features.isEnabled(Features.SUPPORT_SEMANTIC_SEARCH)) {
                        add("MediaSearch");
                    }
                    if (!Features.isEnabled(Features.IS_JDM)) {
                        add("Face");
                        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER) && SdkConfig.lessThan(SdkConfig.SEM.B_MR5)) {
                            add("Pet");
                        }
                    }
                }
            };
            list = r0;
            AnonymousClass2 r1 = new ArrayList<String>() {
                {
                    addAll(SearchEnginList.list);
                    if (Features.isEnabled(Features.SUPPORT_SEMANTIC_SEARCH)) {
                        remove("Tag");
                    }
                }
            };
            checkList = r1;
            Log.d("AnalyticsQuery", "SearchEngineList ", r0, r1);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecMpStatusHolder {
        static Uri uri = Uri.parse("content://secmedia.cmh/status");

        public static Cursor query() {
            return AppResources.getAppContext().getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SolutionRemap {
        static final HashMap<String, String> map = new HashMap<String, String>() {
            {
                put("MFContents", "MFC");
                put("VideoScan_VideoSearch", "VideoScan");
            }
        };

        public static String get(String str) {
            return map.getOrDefault(str, str);
        }
    }

    public static long getAiEditImageCount() {
        MpFilesTable filesTable = getFilesTable();
        filesTable.addProjectionForCursorCount(1);
        filesTable.filterGenerated();
        return getLongByQuery(filesTable.buildSelectQuery().buildSql(), "getAiEditImageCount");
    }

    public static long getCountryCount() {
        return getLongByQuery("select count(distinct country_code) from location where locale like '" + Locale.getDefault() + "'", "getCountryCount");
    }

    public static HashMap<String, Integer> getDocumentCount() {
        Cursor query;
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            query = DbCompat.query("mp://Document");
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("__subCategory");
                    int columnIndex2 = query.getColumnIndex("__count");
                    do {
                        String string = query.getString(columnIndex);
                        if (!TextUtils.isEmpty(string)) {
                            if (string.startsWith("doc_")) {
                                string = string.substring(4);
                            }
                            hashMap.put(string, Integer.valueOf(query.getInt(columnIndex2)));
                        }
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return hashMap;
        throw th;
    }

    public static long getFaceClusterCount() {
        return getLongByQuery("select count(distinct recommended_id) from faces", "getFaceClusterCount");
    }

    public static long getFavoriteStoryCount() {
        if (SEC_MP_STORY) {
            return getLongByQuery("select count(*) from story where story_favorite>0", "getFavoriteStoryCount");
        }
        return getLongFromCmh("select count(*) from story where story_favorite>0", "getFavoriteStoryCount");
    }

    private static MpFilesTable getFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(new QueryParams());
        mpFilesTable.clearProjection();
        mpFilesTable.clearSelection();
        mpFilesTable.clearOrderBy();
        return mpFilesTable;
    }

    public static long getHiddenFaceClusterCount() {
        return getLongByQuery("select count(distinct recommended_id) from faces where hide=1", "getHiddenFaceClusterCount");
    }

    public static long getImageCount() {
        return getLongByQuery("select count(*) from files where media_type=1", "getImageCount");
    }

    public static long getLocalityCount() {
        return getLongByQuery("select count(distinct locality) from location where locale like '" + Locale.getDefault() + "'", "getLocalityCount");
    }

    private static long getLongByQuery(String str, String str2) {
        Cursor rawQuery;
        try {
            SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
            rawQuery = secMpQueryExecutor.rawQuery(str, "AQ:" + str2);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
            }
            if (rawQuery == null) {
                return 0;
            }
            rawQuery.close();
            return 0;
        } catch (Exception unused) {
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static long getLongFromCmh(String str, String str2) {
        Cursor rawQuery;
        try {
            CmhQueryExecutor cmhQueryExecutor = new CmhQueryExecutor();
            rawQuery = cmhQueryExecutor.rawQuery(str, "AQ:" + str2);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
            }
            if (rawQuery == null) {
                return 0;
            }
            rawQuery.close();
            return 0;
        } catch (Exception unused) {
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static long getManualStoryCount() {
        if (SEC_MP_STORY) {
            return getLongByQuery("select count(*) from story where story_type=1 and ifnull(sa_type,0)!=3281", "getManualStoryCount");
        }
        return getLongFromCmh("select count(*) from story where type=1 and ifnull(sa_type,0)!=3281", "getManualStoryCount");
    }

    public static long getMoreStoryCount() {
        if (SEC_MP_STORY) {
            return getLongByQuery("select count(*) from story where story_type<100 and story_type>1 and story_type!=39", "getMoreStoryCount");
        }
        return getLongFromCmh("select count(*) from story where type<100 and type>1 and type!=39", "getMoreStoryCount");
    }

    public static long getNamedFaceClusterCount() {
        return getLongByQuery("select count(distinct recommended_id) from faces where person_id>1", "getNamedFaceClusterCount");
    }

    public static long getNamedPetClusterCount() {
        return getLongByQuery("select count(distinct recommended_id) from cluster_rect where cluster_info_id>1", "getNamedFaceClusterCount");
    }

    public static long getNamedRelationshipCount() {
        return getLongByQuery("select count(*) from persons where name is not null and relationship is not null", "getNamedRelationshipCount");
    }

    public static long getOnDemandStoryCount() {
        if (SEC_MP_STORY) {
            return getLongByQuery("select count(*) from story where story_type=1 and sa_type==3281", "getOnDemandStoryCount");
        }
        return getLongFromCmh("select count(*) from story where type=1 and sa_type==3281", "getOnDemandStoryCount");
    }

    public static long getPetClusterCount() {
        return getLongByQuery("select count(distinct recommended_id) from cluster_rect", "getFaceClusterCount");
    }

    public static long getRecentlyEditImageCount() {
        MpFilesTable filesTable = getFilesTable();
        filesTable.addProjectionForCursorCount(1);
        filesTable.filterRecentlyEdited();
        return getLongByQuery(filesTable.buildSelectQuery().buildSql(), "getRecentlyEditImageCount");
    }

    public static long getRecentlyEditVideoCount() {
        MpFilesTable filesTable = getFilesTable();
        filesTable.addProjectionForCursorCount(3);
        filesTable.filterRecentlyEdited();
        return getLongByQuery(filesTable.buildSelectQuery().buildSql(), "getRecentlyEditVideoCount");
    }

    public static HashMap<String, Integer> getRelationshipCount() {
        Cursor rawQuery;
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select relationship, count(*) from persons where relationship is not null group by relationship", "getRelationshipCount");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        String string = rawQuery.getString(0);
                        if (!TextUtils.isEmpty(string) && !isKnownRelative(string)) {
                            if (string.contains("_sister")) {
                                string = "sister";
                            } else if (string.contains("_brother")) {
                                string = "brother";
                            }
                            hashMap.compute(string, new C0546a(rawQuery.getInt(1), 0));
                        }
                    } while (rawQuery.moveToNext());
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return hashMap;
        throw th;
    }

    public static long getScreenRecordingCount() {
        return getLongByQuery("select count(*) from files where bucket_id=" + StorageInfo.getDefault().buckets().screenRecordings, "getScreenRecordingCount");
    }

    public static long getScreenShotCount() {
        return getLongByQuery("select count(*) from files where bucket_id=" + StorageInfo.getDefault().buckets().screenShot, "getScreenShotCount");
    }

    public static ArrayList<String> getSearchEngineCheckList() {
        return SearchEnginList.checkList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0174 A[Catch:{ Exception -> 0x0179 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.HashMap<java.lang.String, java.lang.Integer> getShotModeCount() {
        /*
            java.lang.String r0 = "instant_slow_mo"
            java.lang.String r1 = "long_exposure"
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r3 = "mp://ShotMode"
            android.database.Cursor r3 = com.samsung.android.gallery.database.dal.DbCompat.query((java.lang.String) r3)     // Catch:{ Exception -> 0x00a9 }
            if (r3 == 0) goto L_0x00a4
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x0048 }
            if (r4 == 0) goto L_0x00a4
            java.lang.String r4 = "__subCategory"
            int r4 = r3.getColumnIndex(r4)     // Catch:{ all -> 0x0048 }
            java.lang.String r5 = "__count"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = "__sefFileType"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x0048 }
            java.lang.String r7 = "__recordingMode"
            int r7 = r3.getColumnIndex(r7)     // Catch:{ all -> 0x0048 }
        L_0x002f:
            java.lang.String r8 = r3.getString(r4)     // Catch:{ all -> 0x0048 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0048 }
            if (r9 == 0) goto L_0x005d
            int r9 = r3.getInt(r7)     // Catch:{ all -> 0x0048 }
            if (r9 <= 0) goto L_0x004a
            com.samsung.android.gallery.support.shotmode.ShotModeList r10 = com.samsung.android.gallery.support.shotmode.ShotModeList.getInstance()     // Catch:{ all -> 0x0048 }
            com.samsung.android.gallery.support.shotmode.ShotMode r9 = r10.getByRecMode(r9)     // Catch:{ all -> 0x0048 }
            goto L_0x004b
        L_0x0048:
            r4 = move-exception
            goto L_0x009b
        L_0x004a:
            r9 = 0
        L_0x004b:
            if (r9 != 0) goto L_0x0059
            com.samsung.android.gallery.support.shotmode.ShotModeList r9 = com.samsung.android.gallery.support.shotmode.ShotModeList.getInstance()     // Catch:{ all -> 0x0048 }
            int r10 = r3.getInt(r6)     // Catch:{ all -> 0x0048 }
            com.samsung.android.gallery.support.shotmode.ShotMode r9 = r9.getBySefValue(r10)     // Catch:{ all -> 0x0048 }
        L_0x0059:
            if (r9 == 0) goto L_0x005d
            java.lang.String r8 = r9.type     // Catch:{ all -> 0x0048 }
        L_0x005d:
            int r9 = r3.getInt(r5)     // Catch:{ all -> 0x0048 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0048 }
            r2.put(r8, r9)     // Catch:{ all -> 0x0048 }
            boolean r8 = r3.moveToNext()     // Catch:{ all -> 0x0048 }
            if (r8 != 0) goto L_0x002f
            java.util.Set r4 = r2.entrySet()     // Catch:{ all -> 0x0048 }
            java.util.stream.Stream r4 = r4.stream()     // Catch:{ all -> 0x0048 }
            A4.j r5 = new A4.j     // Catch:{ all -> 0x0048 }
            r6 = 16
            r5.<init>(r6)     // Catch:{ all -> 0x0048 }
            java.util.stream.Stream r4 = r4.filter(r5)     // Catch:{ all -> 0x0048 }
            A8.b r5 = new A8.b     // Catch:{ all -> 0x0048 }
            r6 = 0
            r5.<init>(r6)     // Catch:{ all -> 0x0048 }
            java.util.stream.IntStream r4 = r4.mapToInt(r5)     // Catch:{ all -> 0x0048 }
            int r4 = r4.sum()     // Catch:{ all -> 0x0048 }
            long r4 = (long) r4     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = "Dual_Recording_Info"
            int r4 = (int) r4     // Catch:{ all -> 0x0048 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0048 }
            r2.put(r6, r4)     // Catch:{ all -> 0x0048 }
            goto L_0x00a4
        L_0x009b:
            r3.close()     // Catch:{ all -> 0x009f }
            goto L_0x00a3
        L_0x009f:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ Exception -> 0x00a9 }
        L_0x00a3:
            throw r4     // Catch:{ Exception -> 0x00a9 }
        L_0x00a4:
            if (r3 == 0) goto L_0x00a9
            r3.close()     // Catch:{ Exception -> 0x00a9 }
        L_0x00a9:
            java.lang.String r3 = "select count(_id) from files where sef_file_types like '%,3409,%'"
            r4 = 0
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r5 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x00d9 }
            r5.<init>()     // Catch:{ Exception -> 0x00d9 }
            android.database.Cursor r3 = r5.rawQuery((java.lang.String) r3, (java.lang.String) r1)     // Catch:{ Exception -> 0x00d9 }
            if (r3 == 0) goto L_0x00d4
            boolean r5 = r3.moveToFirst()     // Catch:{ all -> 0x00ca }
            if (r5 == 0) goto L_0x00d4
            int r5 = r3.getInt(r4)     // Catch:{ all -> 0x00ca }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00ca }
            r2.put(r1, r5)     // Catch:{ all -> 0x00ca }
            goto L_0x00d4
        L_0x00ca:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x00cf }
            goto L_0x00d3
        L_0x00cf:
            r3 = move-exception
            r1.addSuppressed(r3)     // Catch:{ Exception -> 0x00d9 }
        L_0x00d3:
            throw r1     // Catch:{ Exception -> 0x00d9 }
        L_0x00d4:
            if (r3 == 0) goto L_0x00d9
            r3.close()     // Catch:{ Exception -> 0x00d9 }
        L_0x00d9:
            java.lang.String r1 = "select media_type, count(_id) from files where sef_file_types like '%,3505,%' group by media_type"
            r3 = 1
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r5 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x011d }
            r5.<init>()     // Catch:{ Exception -> 0x011d }
            java.lang.String r6 = "live_effect"
            android.database.Cursor r1 = r5.rawQuery((java.lang.String) r1, (java.lang.String) r6)     // Catch:{ Exception -> 0x011d }
            if (r1 == 0) goto L_0x0118
            boolean r5 = r1.moveToFirst()     // Catch:{ all -> 0x00fd }
            if (r5 == 0) goto L_0x0118
        L_0x00f0:
            int r5 = r1.getInt(r4)     // Catch:{ all -> 0x00fd }
            int r6 = r1.getInt(r3)     // Catch:{ all -> 0x00fd }
            if (r5 != r3) goto L_0x00ff
            java.lang.String r5 = "live_effect_photo"
            goto L_0x0101
        L_0x00fd:
            r5 = move-exception
            goto L_0x010f
        L_0x00ff:
            java.lang.String r5 = "live_effect_video"
        L_0x0101:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00fd }
            r2.put(r5, r6)     // Catch:{ all -> 0x00fd }
            boolean r5 = r1.moveToNext()     // Catch:{ all -> 0x00fd }
            if (r5 != 0) goto L_0x00f0
            goto L_0x0118
        L_0x010f:
            r1.close()     // Catch:{ all -> 0x0113 }
            goto L_0x0117
        L_0x0113:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ Exception -> 0x011d }
        L_0x0117:
            throw r5     // Catch:{ Exception -> 0x011d }
        L_0x0118:
            if (r1 == 0) goto L_0x011d
            r1.close()     // Catch:{ Exception -> 0x011d }
        L_0x011d:
            java.lang.String r1 = "3d_capture"
            java.lang.Object r1 = r2.get(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto L_0x018b
            int r5 = r1.intValue()
            if (r5 <= 0) goto L_0x018b
            java.lang.String r5 = "select sef_file_type, count(_id) from files where sef_file_type in (3568,3584) group by sef_file_type"
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r6 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x0178 }
            r6.<init>()     // Catch:{ Exception -> 0x0178 }
            java.lang.String r7 = "capture_3d"
            android.database.Cursor r5 = r6.rawQuery((java.lang.String) r5, (java.lang.String) r7)     // Catch:{ Exception -> 0x0178 }
            if (r5 == 0) goto L_0x0171
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x0166 }
            if (r6 == 0) goto L_0x0171
            r6 = r4
        L_0x0144:
            int r7 = r5.getInt(r4)     // Catch:{ all -> 0x0153 }
            int r8 = r5.getInt(r3)     // Catch:{ all -> 0x0153 }
            r9 = 3584(0xe00, float:5.022E-42)
            if (r7 != r9) goto L_0x0155
            java.lang.String r7 = "3d_capture_vst"
            goto L_0x0157
        L_0x0153:
            r3 = move-exception
            goto L_0x0168
        L_0x0155:
            java.lang.String r7 = "3d_capture_mobile"
        L_0x0157:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0153 }
            r2.put(r7, r9)     // Catch:{ all -> 0x0153 }
            int r6 = r6 + r8
            boolean r7 = r5.moveToNext()     // Catch:{ all -> 0x0153 }
            if (r7 != 0) goto L_0x0144
            goto L_0x0172
        L_0x0166:
            r3 = move-exception
            r6 = r4
        L_0x0168:
            r5.close()     // Catch:{ all -> 0x016c }
            goto L_0x0170
        L_0x016c:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ Exception -> 0x0179 }
        L_0x0170:
            throw r3     // Catch:{ Exception -> 0x0179 }
        L_0x0171:
            r6 = r4
        L_0x0172:
            if (r5 == 0) goto L_0x0179
            r5.close()     // Catch:{ Exception -> 0x0179 }
            goto L_0x0179
        L_0x0178:
            r6 = r4
        L_0x0179:
            int r1 = r1.intValue()
            int r1 = r1 - r6
            int r1 = java.lang.Math.max(r4, r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "3d_capture_video"
            r2.put(r3, r1)
        L_0x018b:
            java.lang.String r1 = "select count(_id) from files where recording_mode=89"
            com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor r3 = new com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor     // Catch:{ Exception -> 0x01ba }
            r3.<init>()     // Catch:{ Exception -> 0x01ba }
            android.database.Cursor r1 = r3.rawQuery((java.lang.String) r1, (java.lang.String) r0)     // Catch:{ Exception -> 0x01ba }
            if (r1 == 0) goto L_0x01b5
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x01ab }
            if (r3 == 0) goto L_0x01b5
            int r3 = r1.getInt(r4)     // Catch:{ all -> 0x01ab }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01ab }
            r2.put(r0, r3)     // Catch:{ all -> 0x01ab }
            goto L_0x01b5
        L_0x01ab:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x01b0 }
            goto L_0x01b4
        L_0x01b0:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch:{ Exception -> 0x01ba }
        L_0x01b4:
            throw r0     // Catch:{ Exception -> 0x01ba }
        L_0x01b5:
            if (r1 == 0) goto L_0x01ba
            r1.close()     // Catch:{ Exception -> 0x01ba }
        L_0x01ba:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.analyticsquery.AnalyticsQuery.getShotModeCount():java.util.HashMap");
    }

    public static long getTripStoryCount() {
        if (SEC_MP_STORY) {
            return getLongByQuery("select count(*) from story where story_type=39", "getTripStoryCount");
        }
        return getLongFromCmh("select count(*) from story where type=39", "getTripStoryCount");
    }

    public static long getVideoCount() {
        return getLongByQuery("select count(*) from files where media_type=3", "getVideoCount");
    }

    public static boolean isKnownRelative(String str) {
        if (str.equals("daughterinlaw") || str.equals("soninlaw") || str.startsWith("paternal_") || str.startsWith("maternal_") || str.startsWith("motherinlaw_") || str.startsWith("fatherinlaw_")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getRelationshipCount$1(int i2, String str, Integer num) {
        if (num != null) {
            i2 += num.intValue();
        }
        return Integer.valueOf(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getShotModeCount$0(Map.Entry entry) {
        if ("directors_view".equals(entry.getKey()) || "Dual_Recording_Info".equals(entry.getKey())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadSearchAnalysisStatus$2(HashMap hashMap, String str) {
        return !hashMap.containsKey(str);
    }

    public static String loadAnalyzedSolutions(long j2) {
        if (SdkConfig.lessThan(SdkConfig.SEM.B_MR5)) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        ArrayList arrayList = new ArrayList();
        Bundle bundle = new Bundle();
        bundle.putLong("sec_media_id", j2);
        Bundle callSafe = AndroidCompat.callSafe(AppResources.getAppContext(), Uri.parse("content://com.samsung.mediasearch/nlsearch"), "getImageAnalysisStatus", (String) null, bundle);
        if (callSafe != null && callSafe.getBoolean("complete", false)) {
            arrayList.add("MediaSearch");
        }
        Bundle callSafe2 = AndroidCompat.callSafe(AppResources.getAppContext(), Uri.parse("content://com.samsung.cmh"), "getImageAnalysisStatus", (String) null, bundle);
        if (callSafe2 != null && callSafe2.getBoolean("complete", false)) {
            arrayList.add("OCR");
        }
        Bundle callSafe3 = AndroidCompat.callSafe(AppResources.getAppContext(), Uri.parse("content://com.samsung.faceservice"), "getImageAnalysisStatus", (String) null, bundle);
        if (callSafe3 != null && callSafe3.getBoolean("complete", false)) {
            arrayList.add("Face/Pet");
        }
        return (String) arrayList.stream().collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
    }

    public static HashMap<String, Integer> loadPackageStatus(String... strArr) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String str : strArr) {
            hashMap.put(str, Integer.valueOf(PackageMonitorCompat.getInstance().getPackageEnabledSetting(str)));
        }
        return hashMap;
    }

    public static HashMap<String, Integer> loadScreenCaptureTypeCount() {
        Cursor rawQuery;
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select S.scene_name as _name, count(S.scene_name) as _count from scene as S where S.parent_name is 'capture' group by S.scene_name", "loadScreenCaptureTypeCount");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        hashMap.put(rawQuery.getString(0), Integer.valueOf(rawQuery.getInt(1)));
                    } while (rawQuery.moveToNext());
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return hashMap;
        throw th;
    }

    public static HashMap<String, SearchAnalysisStatus> loadSearchAnalysisStatus() {
        Cursor query;
        HashMap<String, SearchAnalysisStatus> hashMap = new HashMap<>();
        try {
            query = SecMpStatusHolder.query();
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("solution_name");
                    int columnIndex2 = query.getColumnIndex("data");
                    do {
                        String string = query.getString(columnIndex);
                        String string2 = query.getString(columnIndex2);
                        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                            hashMap.put(SolutionRemap.get(string), SearchAnalysisStatus.of(string2));
                        }
                    } while (query.moveToNext());
                }
            }
            SearchEnginList.list.stream().filter(new I(4, hashMap)).forEach(new p(1, hashMap));
            if (query != null) {
                query.close();
            }
        } catch (Error | Exception unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("AnalyticsQuery", "loadSearchAnalysisStatus = " + Logger.getEncodedString((Object) hashMap));
        return hashMap;
        throw th;
    }
}
