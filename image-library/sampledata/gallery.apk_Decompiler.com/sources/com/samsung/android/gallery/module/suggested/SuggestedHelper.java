package com.samsung.android.gallery.module.suggested;

import A.a;
import B5.e;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedHelper {
    private static final Uri CMH_DELETE_RECOMMENDATION_URI = Uri.withAppendedPath(CmhUri.getAuthority(), "delete_recommendation");
    private static final SuggestedHelper sInstance = new SuggestedHelper();

    public static boolean comparePreference(SuggestedType suggestedType, int i2) {
        List<PreferenceCache> preferenceCache = getPreferenceCache(suggestedType, i2);
        if (preferenceCache.size() <= 1 || preferenceCache.get(0).getLong() >= preferenceCache.get(1).getLong()) {
            return false;
        }
        return true;
    }

    private long getBadQualityLatestId() {
        return Math.max(PreferenceCache.SuggestBadQualityLatestId.getLong(), PreferenceCache.SuggestBadQualityLatestTabId.getLong());
    }

    private int getCleanOutMotionPhotoClipCount() {
        return Math.max(PreferenceCache.CleanOutMotionPhotoClipCount.getInt(), PreferenceCache.CleanOutMotionPhotoClipTabCount.getInt());
    }

    private long getDuplicatedLatestId() {
        return Math.max(PreferenceCache.SuggestDuplicatedLatestId.getLong(), PreferenceCache.SuggestDuplicatedLatestTabId.getLong());
    }

    private long getExpiredLatestId() {
        return Math.max(PreferenceCache.SuggestExpiredLatestId.getLong(), PreferenceCache.SuggestExpiredLatestTabId.getLong());
    }

    private String getFileIdColumnName() {
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SUGGESTIONS)) {
            return "sec_media_id";
        }
        return "media_id";
    }

    private String getFilterHidden() {
        return C0212a.p(new StringBuilder("bucket_id NOT IN (select bucket_id from (select * from files where is_hide=1) where media_type in (1,3) and bucket_id not in "), getNonHideBucketIds(), " group by bucket_id)");
    }

    private long getHighlightVideoLatestTime() {
        return Math.max(PreferenceCache.SuggestHighlightVideoLatestTime.getLong(), PreferenceCache.SuggestHighlightVideoLatestTabTime.getLong());
    }

    public static SuggestedHelper getInstance() {
        return sInstance;
    }

    private String getNonHideBucketIds() {
        return CursorHelper.joinIds(BucketUtils.values());
    }

    private long getPortraitLatestTime() {
        return Math.max(PreferenceCache.SuggestPortraitLatestTime.getLong(), PreferenceCache.SuggestPortraitLatestTabTime.getLong());
    }

    public static List<PreferenceCache> getPreferenceCache(SuggestedType suggestedType, int i2) {
        if (SuggestedType.CLEANOUT.equals(suggestedType)) {
            if (i2 == 1) {
                return List.of(PreferenceCache.SuggestExpiredLatestId, PreferenceCache.SuggestExpiredLatestTabId);
            }
            if (i2 == 2) {
                return List.of(PreferenceCache.SuggestBadQualityLatestId, PreferenceCache.SuggestBadQualityLatestTabId);
            }
        } else if (SuggestedType.CLEANOUT_DUPLICATED_IMAGE.equals(suggestedType)) {
            return List.of(PreferenceCache.SuggestDuplicatedLatestId, PreferenceCache.SuggestDuplicatedLatestTabId);
        } else {
            if (SuggestedType.REMASTER.equals(suggestedType)) {
                return List.of(PreferenceCache.SuggestRemasterLatestTime, PreferenceCache.SuggestRemasterLatestTabTime);
            }
            if (SuggestedType.HIGHLIGHT.equals(suggestedType)) {
                return List.of(PreferenceCache.SuggestHighlightVideoLatestTime, PreferenceCache.SuggestHighlightVideoLatestTabTime);
            }
            if (SuggestedType.PORTRAIT.equals(suggestedType)) {
                return List.of(PreferenceCache.SuggestPortraitLatestTime, PreferenceCache.SuggestPortraitLatestTabTime);
            }
            if (SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.equals(suggestedType)) {
                return List.of(PreferenceCache.CleanOutMotionPhotoClipCount, PreferenceCache.CleanOutMotionPhotoClipTabCount);
            }
            Log.e("SuggestedHelper", "comparePreference failed. not supported type=" + suggestedType + " cleanOutType=" + i2);
        }
        return Collections.EMPTY_LIST;
    }

    private long getRemasterLatestTime() {
        return Math.max(PreferenceCache.SuggestRemasterLatestTime.getLong(), PreferenceCache.SuggestRemasterLatestTabTime.getLong());
    }

    private boolean hasNewCleanOut() {
        if (PreferenceFeatures.OneUi41.DISABLE_CLEANOUT_BAD_QUALITY_IMAGE || queryLatestSuggestedCleanOutId(1) > getExpiredLatestId() || queryLatestSuggestedCleanOutId(2) <= getBadQualityLatestId()) {
            return false;
        }
        return true;
    }

    private boolean hasNewCleanOutMotionPhoto() {
        if (PreferenceFeatures.OneUi41.CLEANOUT_MOTION_PHOTO_CLIP) {
            int cleanOutMotionPhotoClipCount = getCleanOutMotionPhotoClipCount();
            if (cleanOutMotionPhotoClipCount == -1) {
                if (queryCleanOutMotionPhotoClipCount() >= 5) {
                    return true;
                }
                return false;
            } else if (queryCleanOutMotionPhotoClipCount() - cleanOutMotionPhotoClipCount >= 10) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNewDuplicatedImage() {
        if (!PreferenceFeatures.OneUi41.CLEANOUT_DUPLICATED_IMAGE || queryLatestSuggestedCleanOutId(3) <= getDuplicatedLatestId()) {
            return false;
        }
        return true;
    }

    private boolean hasNewHighlight() {
        if (!PreferenceFeatures.OneUi41.HIGHLIGHT_SUGGESTIONS || queryLatestHighlightVideoTime() <= getHighlightVideoLatestTime()) {
            return false;
        }
        return true;
    }

    private boolean hasNewPortrait() {
        if (!PreferenceFeatures.OneUi41.PORTRAIT_SUGGESTIONS || queryLatestPortraitSuggestionsTime() <= getPortraitLatestTime()) {
            return false;
        }
        return true;
    }

    private boolean hasNewRemaster() {
        if (!PreferenceFeatures.OneUi41.REMASTER_SUGGESTIONS || queryLatestSuggestedRemasterImageTime() <= getRemasterLatestTime()) {
            return false;
        }
        return true;
    }

    private static void notifyCmhUpdate() {
        try {
            AppResources.getAppContext().getContentResolver().notifyChange(CmhUri.getStory(), (ContentObserver) null);
        } catch (Exception unused) {
        }
    }

    private int queryCleanOutMotionPhotoClipCount() {
        Cursor rawQuery;
        String A10 = C0212a.A("where A.sef_file_type in (2608) AND A._id in (SELECT sec_media_id FROM scene WHERE scene_name COLLATE NOCASE in ('documents')) AND A._id not in (SELECT sec_media_id FROM scene WHERE scene_name COLLATE NOCASE in ('people')) AND " + getFilterHidden(), " AND A.is_cloud != 2");
        try {
            SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
            rawQuery = secMpQueryExecutor.rawQuery("select count(*) from files as A " + A10, "getCleanOutMotionPhotoClipCount");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    int i2 = rawQuery.getInt(0);
                    rawQuery.close();
                    return i2;
                }
            }
            if (rawQuery == null) {
                return -1;
            }
            rawQuery.close();
            return -1;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getCleanOutMotionPhotoClipCount failed e="), "SuggestedHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private long queryLatestHighlightVideoTime() {
        Cursor rawQuery;
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery(C0212a.A("Select H.highlight_time FROM files AS A INNER JOIN highlight as H ON A._id = H.sec_media_id WHERE highlight_time is not null and dynamic_view_info is not null AND " + getFilterHidden(), " ORDER BY highlight_time desc limit 1"), "getLatestHighlightVideoTime");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
            }
            if (rawQuery == null) {
                return -1;
            }
            rawQuery.close();
            return -1;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLatestHighlightVideoTime failed e="), "SuggestedHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private long queryLatestPortraitSuggestionsTime() {
        Cursor rawQuery;
        String str = "where portrait_single = 1 AND portrait_path is not null AND portrait_path != '' AND " + getFilterHidden();
        try {
            rawQuery = new CmhQueryExecutor().rawQuery("select portrait_time from files " + str + " order by portrait_time desc", "getLatestPortraitSuggestionsTime");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
            }
            if (rawQuery == null) {
                return -1;
            }
            rawQuery.close();
            return -1;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLatestPortraitSuggestionsTime failed e="), "SuggestedHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private long queryLatestSuggestedCleanOutId(int i2) {
        String str;
        String str2;
        Cursor rawQuery;
        Features features = Features.CMH_TO_MP_MIGRATION;
        if (Features.isEnabled(features)) {
            str = "sec_media_id";
        } else {
            str = "fk_file_id";
        }
        if (Features.isEnabled(features)) {
            str2 = "";
        } else {
            str2 = " AND isUsedAsWallpaper = 0";
        }
        String d = j.d(" from delete_recommendation Inner JOIN files ON (delete_recommendation.", str, " = files._id AND (is_favorite is null OR is_favorite = 0)", str2, ")");
        StringBuilder o2 = C0086a.o(i2, " where delete_type in (", ") AND clean_state in (0) AND ");
        o2.append(getFilterHidden());
        String sb2 = o2.toString();
        String A10 = C0212a.A(C0212a.l("select delete_recommendation._id", d), sb2);
        if (i2 == 3) {
            A10 = C0212a.A(A10, C0212a.n(" AND delete_group_id in (select delete_group_id", d, sb2, " GROUP BY delete_group_id HAVING count(distinct files.sec_media_id)>1)"));
        }
        try {
            rawQuery = new CmhQueryExecutor().rawQuery(C0212a.A(A10, " order by delete_recommendation._id desc"), "getLatestSuggestedCleanOutImageID");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
            }
            if (rawQuery == null) {
                return -1;
            }
            rawQuery.close();
            return -1;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLatestSuggestedCleanOutImageID failed e="), "SuggestedHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private long queryLatestSuggestedRemasterImageTime() {
        Cursor rawQuery;
        String str = "where revitalized_type > 0 AND revitalized_path is not null AND revitalized_path != '' AND " + getFilterHidden();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select revitalized_time from files " + str + " order by revitalized_time desc", "getLatestSuggestedRevitalizationImageTime");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    rawQuery.close();
                    return j2;
                }
            }
            if (rawQuery == null) {
                return -1;
            }
            rawQuery.close();
            return -1;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLatestSuggestedRevitalizationImageTime failed e="), "SuggestedHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private int updateCleanOutItems(Context context, ArrayList<Long> arrayList, int i2) {
        if (arrayList.isEmpty()) {
            return 0;
        }
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, C0212a.p(new StringBuilder(), getFileIdColumnName(), " in ("), ")");
        arrayList.forEach(new e(stringJoiner, 10));
        int updateColumn = updateColumn(context, CMH_DELETE_RECOMMENDATION_URI, stringJoiner.toString(), "clean_state", i2, (String[]) null);
        notifyCmhUpdate();
        return updateColumn;
    }

    private int updateColumn(Context context, Uri uri, String str, String str2, int i2, String[] strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, Integer.valueOf(i2));
        try {
            return context.getContentResolver().update(uri, contentValues, str, strArr);
        } catch (Exception e) {
            a.s(e, new StringBuilder("updateColumn failed e="), "SuggestedHelper");
            return 0;
        }
    }

    public static boolean updatePreferenceWithTab(SuggestedType suggestedType, int i2) {
        if (SuggestedType.CLEANOUT.equals(suggestedType)) {
            if (i2 == 1) {
                PreferenceCache.SuggestExpiredLatestId.setLong(PreferenceCache.SuggestExpiredLatestTabId.getLong());
            } else if (i2 == 2) {
                PreferenceCache.SuggestBadQualityLatestId.setLong(PreferenceCache.SuggestBadQualityLatestTabId.getLong());
            } else {
                a.B(i2, "updatePreferenceWithTab failed. not supported clean out type=", "SuggestedHelper");
                return false;
            }
        } else if (SuggestedType.CLEANOUT_DUPLICATED_IMAGE.equals(suggestedType)) {
            PreferenceCache.SuggestDuplicatedLatestId.setLong(PreferenceCache.SuggestDuplicatedLatestTabId.getLong());
        } else if (SuggestedType.REMASTER.equals(suggestedType)) {
            PreferenceCache.SuggestRemasterLatestTime.setLong(PreferenceCache.SuggestRemasterLatestTabTime.getLong());
        } else if (SuggestedType.HIGHLIGHT.equals(suggestedType)) {
            PreferenceCache.SuggestHighlightVideoLatestTime.setLong(PreferenceCache.SuggestHighlightVideoLatestTabTime.getLong());
        } else if (SuggestedType.PORTRAIT.equals(suggestedType)) {
            PreferenceCache.SuggestPortraitLatestTime.setLong(PreferenceCache.SuggestPortraitLatestTabTime.getLong());
        } else if (!SuggestedType.CLEANOUT_BURST_SIMILAR.equals(suggestedType)) {
            Log.e("SuggestedHelper", "updatePreferenceWithTab failed. not supported type=" + suggestedType);
            return false;
        }
        return true;
    }

    public void deleteCleanOutItems(Context context, ArrayList<Long> arrayList) {
        if (arrayList.size() == 1) {
            updateCleanOutItems(context, arrayList, -1);
        } else {
            notifyCmhUpdate();
        }
    }

    public int getCleanOutCount(Context context) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(CMH_DELETE_RECOMMENDATION_URI, new String[]{"count(*)"}, "clean_state = 0", (String[]) null, (String) null);
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
        } catch (Exception e) {
            a.s(e, new StringBuilder("getCleanOutCount failed e="), "SuggestedHelper");
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        Log.d("SuggestedHelper", "New Gallery [Content] CleanContents Count - CMH Query result : 0");
        return 0;
        throw th;
    }

    public int keepCleanOutItems(Context context, ArrayList<Long> arrayList) {
        return updateCleanOutItems(context, arrayList, 1);
    }

    public boolean needNewBadgeInMenuTab() {
        if (hasNewCleanOut() || hasNewDuplicatedImage() || hasNewRemaster() || hasNewHighlight() || hasNewPortrait() || hasNewCleanOutMotionPhoto()) {
            return true;
        }
        return false;
    }

    public void updateAutoItem(int i2, int i7, int i8) {
        if (i8 != SuggestedType.CLEANOUT.toInt()) {
            DbCompat.storyNotificationApi().updateAutoItem(i2, i7);
        }
    }

    public int updatePortraitData(Context context, ArrayList<Long> arrayList, boolean z) {
        int i2;
        if (arrayList.isEmpty()) {
            return 0;
        }
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "sec_media_id in (", ")");
        arrayList.forEach(new e(stringJoiner, 9));
        Uri files = CmhUri.getFiles();
        String stringJoiner2 = stringJoiner.toString();
        if (z) {
            i2 = 2;
        } else {
            i2 = 3;
        }
        return updateColumn(context, files, stringJoiner2, "portrait_single", i2, (String[]) null);
    }

    public void updatePreferenceForTab() {
        PreferenceCache.SuggestExpiredLatestTabId.setLong(queryLatestSuggestedCleanOutId(1));
        PreferenceCache.SuggestBadQualityLatestTabId.setLong(queryLatestSuggestedCleanOutId(2));
        if (PreferenceFeatures.OneUi41.CLEANOUT_DUPLICATED_IMAGE) {
            PreferenceCache.SuggestDuplicatedLatestTabId.setLong(queryLatestSuggestedCleanOutId(3));
        }
        if (PreferenceFeatures.OneUi41.REMASTER_SUGGESTIONS) {
            PreferenceCache.SuggestRemasterLatestTabTime.setLong(queryLatestSuggestedRemasterImageTime());
        }
        if (PreferenceFeatures.OneUi41.HIGHLIGHT_SUGGESTIONS) {
            PreferenceCache.SuggestHighlightVideoLatestTabTime.setLong(queryLatestHighlightVideoTime());
        }
        if (PreferenceFeatures.OneUi41.PORTRAIT_SUGGESTIONS) {
            PreferenceCache.SuggestPortraitLatestTabTime.setLong(queryLatestPortraitSuggestionsTime());
        }
        if (PreferenceFeatures.OneUi41.CLEANOUT_MOTION_PHOTO_CLIP) {
            PreferenceCache.CleanOutMotionPhotoClipTabCount.setInt(queryCleanOutMotionPhotoClipCount());
        }
    }
}
