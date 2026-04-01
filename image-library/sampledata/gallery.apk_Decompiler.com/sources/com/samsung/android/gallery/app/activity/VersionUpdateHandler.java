package com.samsung.android.gallery.app.activity;

import A.a;
import Ba.h;
import C3.o;
import C3.p;
import android.content.Context;
import android.content.Intent;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.store.AppRatingHelper;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.sec.android.gallery3d.R;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VersionUpdateHandler {
    private final Blackboard mBlackboard;

    public VersionUpdateHandler(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private boolean isBuildTimeChangedOnly(String[] strArr, String[] strArr2) {
        try {
            if (!strArr[0].equals(strArr2[0]) || !strArr[1].equals(strArr2[1])) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("isBuildTimeChangedOnly failed e="), "VersionUpdateHandler");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateVersion15$0(HashSet hashSet, String str) {
        if (hashSet.contains(str) || str.startsWith("disk_cache_size") || str.startsWith("view_history") || str.startsWith("lfc_") || str.startsWith("lac_")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateVersion15$1(HashMap hashMap, String str) {
        String str2 = (String) hashMap.get(str);
        if (str2 != null) {
            GalleryPreference.getInstanceCache().saveState(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateVersion15$2(HashMap hashMap, String str, String str2) {
        if (str.startsWith("lfc_") || str.startsWith("lac_")) {
            hashMap.put(str, str2);
        }
    }

    private void updateVersion10(Context context, long j2) {
        if (j2 < 1000000013) {
            CacheManager.getInstance().clear(4);
        }
        if (j2 < 1010000000) {
            CacheManager.getInstance().clear(2);
        }
        if (j2 < 1090000011) {
            AlbumHelper.getInstance().updateInvalidAlbums(context);
        }
    }

    private void updateVersion11(Context context, long j2) {
        if (j2 < 1100000009) {
            AlbumHelper.getInstance().updateNewEmptyAlbumData(context);
        }
        if (j2 <= 1110100000) {
            CacheManager.getInstance().clear(4);
        }
    }

    private void updateVersion14(Context context, long j2, String[] strArr) {
        if (j2 <= 1400000009) {
            GalleryPreference.getInstance().removeState(PreferenceCache.LastHiddenScannedId.key);
        }
        if (j2 <= 1450000009) {
            AlbumBnRHelper.getInstance().keepRestoreFile();
        }
        if (j2 <= 1450000031) {
            GalleryPreference.getInstance().removeState((Collection<String>) List.of("group_count", "album_count", "created_by_user_album_count", "merged_album_count", "auto_updated_album_count", "system_album_count", "galaxy_album_count", "essential_album_count", "camera_album_count_ratio", "camera_sd_album_count_ratio"));
        }
        if (j2 < 1450200000 && !Features.isEnabled(Features.SUPPORT_POI)) {
            context.sendBroadcast(new Intent("com.samsung.cmh.autoevent").putExtra("location_on_off_state", false));
        }
    }

    private void updateVersion15(Context context, long j2) {
        if (j2 < 1510000017) {
            YearThumbnailLoader.getInstance().moveAllYearData();
        }
        if (j2 <= 1550000000) {
            HashSet hashSet = new HashSet(List.of("search_map_latest_loc", PreferenceCache.YearCacheDataStamp.key));
            HashMap<String, String> removeStateIf = GalleryPreference.getInstance().removeStateIf(new o(hashSet, 0));
            if (removeStateIf.size() > 0) {
                hashSet.forEach(new p(0, removeStateIf));
                HashMap hashMap = new HashMap();
                removeStateIf.forEach(new h(2, hashMap));
                GalleryPreference.getInstanceDebug().saveState((HashMap<String, String>) hashMap);
            }
        }
        if (j2 < 1560000010) {
            AlbumBnRHelper.getInstance().updateMissingAlbumData(context, LocalDatabaseHelper.getInstance(context).getWritableDatabase());
        }
        if (j2 < 1560000018) {
            CacheManager.getInstance().clear(2);
            CacheManager.getInstance().clear(0);
            CacheManager.getInstance().clear(6);
            CacheManager.getInstance().clear(8);
        }
    }

    private void updateVersion16(Context context, long j2) {
        HashSet hashSet = new HashSet();
        if (j2 < 1560000023) {
            hashSet.add("oneui31_remastered");
            hashSet.add(PreferenceCache.SuggestRemasterLatestTabTime.key);
            hashSet.add(PreferenceCache.SuggestHighlightVideoLatestTabTime.key);
            hashSet.add(PreferenceCache.SuggestPortraitLatestTime.key);
        }
        if (!hashSet.isEmpty()) {
            GalleryPreference.getInstance().removeStateIf(new o(hashSet, 1));
        }
        if (j2 < 1560100000) {
            CustomRelationshipKeySet.getInstance().reassignPreference(PeopleDataManager.getRelationships());
        }
    }

    private void updateVersion17(Context context, long j2) {
        if (j2 <= 1570000000) {
            PreferenceCache.migrate();
        }
    }

    private void updateVersion18(int i2) {
        if (i2 < 1580000050) {
            SearchHistory.getInstance().filterKeyword();
        }
    }

    private void updateVersionFromSecGallery(Context context) {
        Log.d("VersionUpdateHandler", "updateBuildVersion : update from sec-gallery");
        AlbumHelper.getInstance().updateOldWelcomeImages(context);
    }

    private void updateVersionImmediately(Context context, String str, String str2) {
        String str3;
        String[] split = str.split("#");
        String[] split2 = str2.split("#");
        if (isBuildTimeChangedOnly(split, split2)) {
            Log.d("VersionUpdateHandler", "updateBuildVersion : same version except build time ".concat(str2));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        AppRatingHelper.reset();
        int i2 = -1;
        if (split.length > 1) {
            i2 = UnsafeCast.toInt(split[1], -1);
        }
        GalleryPreference.getInstanceDebug().removeStatePrefix("InternalException");
        long j2 = (long) i2;
        updateVersion10(context, j2);
        updateVersion11(context, j2);
        updateVersion12(context, j2);
        updateVersion14(context, j2, split2);
        updateVersion15(context, j2);
        updateVersion16(context, j2);
        updateVersion17(context, j2);
        updateVersion18(i2);
        String str4 = TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()) + TimeUtil.getSystemTimeZoneOffsetTag();
        StringBuilder sb2 = new StringBuilder("Upgrade");
        Locale locale = Locale.getDefault();
        if (AppResources.getBoolean(R.bool.isNightTheme)) {
            str3 = "dark";
        } else {
            str3 = "light";
        }
        sb2.append(Logger.v(str4, str2, str, locale, str3, AppResources.getSecTheme()));
        String sb3 = sb2.toString();
        DebugLogger.getInstance().insertLog(sb3);
        Log.i("VersionUpdateHandler", sb3 + " +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void updateBuildVersion() {
        GalleryPreference instance = GalleryPreference.getInstance();
        PreferenceName preferenceName = PreferenceName.BUILD_ID;
        String loadString = instance.loadString(preferenceName, "-1");
        String buildSignature = PackageMonitorCompat.getBuildSignature();
        if (loadString == null || !loadString.equals(buildSignature)) {
            this.mBlackboard.publish("data://version/updated", Boolean.TRUE);
            Context readAppContext = BlackboardUtils.readAppContext(this.mBlackboard);
            if (readAppContext == null) {
                Log.d("VersionUpdateHandler", "updateBuildVersion failed with null context. update " + loadString + ArcCommonLog.TAG_COMMA + buildSignature);
                return;
            }
            GalleryPreference.getInstance().saveState(preferenceName, buildSignature);
            if ("-1".equals(loadString)) {
                loadString = SdkConfig.FirstApiLevel.getAppInitVersion();
            }
            if ("-1".equals(loadString)) {
                updateVersionFromSecGallery(readAppContext);
            } else if (loadString != null) {
                updateVersionImmediately(readAppContext, loadString, buildSignature);
            }
        }
    }

    private void updateVersion12(Context context, long j2) {
    }
}
