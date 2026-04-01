package com.samsung.android.gallery.module.album;

import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.StorageInfo;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumInfo {
    static boolean SUPPORT_SCREEN_SHOT_GROUP = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataHolder {
        static final HashMap<Integer, HashSet<Integer>> sGroupedMap;
        static final HashSet<Integer> sScreenShotSet;

        static {
            HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
            sGroupedMap = hashMap;
            HashSet<Integer> hashSet = new HashSet<>();
            sScreenShotSet = hashSet;
            if (AlbumInfo.SUPPORT_SCREEN_SHOT_GROUP) {
                StorageInfo.BucketHolder buckets = StorageInfo.getDefault().buckets();
                hashSet.add(Integer.valueOf(buckets.screenShot));
                hashSet.add(Integer.valueOf(buckets.screenRecordings));
                hashSet.add(Integer.valueOf(buckets.screenShotAlt));
                hashMap.put(Integer.valueOf(BucketUtils.VirtualBucketHolder.screenShots), hashSet);
            }
        }
    }

    public static int getDuplicateNameFolderId(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        return FileUtils.getBucketId("/Merged_Album_name/".concat(str));
    }

    public static HashSet<Integer> getSubAlbumIds(int i2) {
        return DataHolder.sGroupedMap.get(Integer.valueOf(i2));
    }
}
