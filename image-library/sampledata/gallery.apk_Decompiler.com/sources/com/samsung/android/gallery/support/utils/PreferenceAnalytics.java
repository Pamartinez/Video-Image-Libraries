package com.samsung.android.gallery.support.utils;

import A4.C0371f;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PreferenceAnalytics {
    TimelineColumnCount("SaLogTimelineColumnCount", 0),
    AlbumsColumnCount("SaLogAlbumsColumnCount", 0),
    AlbumPicturesColumnCount("SaLogAlbumPicturesColCount", 0),
    ClusterCount("status_log_cluster_count", 0),
    BigAlbumCount("status_log_big_album_count", 0),
    SharedCount("status_log_shared_count", 0);
    
    private static final String TAG = "PreferenceAnalytics";
    public final String key;
    public final Object valueDef;

    private <T> PreferenceAnalytics(String str, T t) {
        this.key = str;
        this.valueDef = t;
    }

    public static List<String> listOf() {
        return List.of(TimelineColumnCount.key, AlbumsColumnCount.key, AlbumPicturesColumnCount.key);
    }

    public static void migrate(Map<String, ?> map) {
        HashMap hashMap = new HashMap();
        Set<String> keySet = map.keySet();
        ArrayList arrayList = new ArrayList();
        TimeTickLog timeTickLog = new TimeTickLog();
        Stream.of(values()).filter(new J(0, keySet)).forEach(new C0371f((Object) arrayList, (Object) hashMap, (Object) map, 27));
        timeTickLog.tick();
        if (!hashMap.isEmpty()) {
            GalleryPreference.getInstanceAnalytics().saveStateInt(hashMap);
            timeTickLog.tick();
        }
        if (!arrayList.isEmpty()) {
            GalleryPreference.getInstance().removeState((Collection<String>) arrayList);
            timeTickLog.tick();
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("I=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, hashMap.keySet()) + "]");
        arrayList2.add("deprecated=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "]");
        String join = String.join("\n", arrayList2);
        Log.d(TAG, "migrate" + Logger.vt(Integer.valueOf(hashMap.size()), Integer.valueOf(arrayList.size()), timeTickLog) + "\n" + Logger.getEncodedString(join));
    }

    public int getInteger() {
        return GalleryPreference.getInstanceAnalytics().loadInt(this.key, ((Integer) this.valueDef).intValue());
    }

    public void setInteger(int i2) {
        GalleryPreference.getInstanceAnalytics().saveState(this.key, i2);
    }
}
