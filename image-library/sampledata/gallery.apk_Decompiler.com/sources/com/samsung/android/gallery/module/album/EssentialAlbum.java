package com.samsung.android.gallery.module.album;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EssentialAlbum {
    private static final HashSet<Integer> sEssentialAlbums;
    private static final EssentialAlbum sInstance = new EssentialAlbum();
    private String mBuckets = "";
    private final HashSet<Integer> mSet = new HashSet<>();

    static {
        HashSet<Integer> hashSet = new HashSet<>();
        sEssentialAlbums = hashSet;
        StorageInfo storageInfo = StorageInfo.getDefault();
        StorageInfo.BucketHolder buckets = storageInfo.buckets();
        StorageInfo.BucketHolder buckets2 = StorageInfo.getRemovable().buckets();
        hashSet.add(Integer.valueOf(buckets.camera));
        hashSet.add(Integer.valueOf(buckets2.camera));
        hashSet.add(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedCamera));
        hashSet.add(Integer.valueOf(buckets.screenShot));
        hashSet.add(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedScreenShot));
        hashSet.add(Integer.valueOf(buckets.download));
        hashSet.add(Integer.valueOf(buckets2.download));
        hashSet.add(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedDownload));
        hashSet.add(Integer.valueOf(FileUtils.getBucketId(storageInfo.quickShare)));
        hashSet.add(Integer.valueOf(BucketUtils.VirtualBucketHolder.mergedQuickShare));
    }

    public EssentialAlbum() {
        String loadString = GalleryPreference.getInstance().loadString("EssentialAlbumList", (String) null);
        if (!TextUtils.isEmpty(loadString)) {
            for (String parseInt : loadString.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                this.mSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
            }
            this.mBuckets = loadString;
            Log.d("EssentialAlbum", "construct", loadString);
        }
    }

    public static EssentialAlbum getInstance() {
        return sInstance;
    }

    private boolean isEssentialAlbum(int i2) {
        return sEssentialAlbums.contains(Integer.valueOf(i2));
    }

    public boolean add(int i2) {
        if (!isEssentialAlbum(i2) || !this.mSet.add(Integer.valueOf(i2))) {
            return false;
        }
        this.mBuckets = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mSet);
        GalleryPreference.getInstance().saveState("EssentialAlbumList", this.mBuckets);
        Log.d("EssentialAlbum", "add", Integer.valueOf(i2), this.mBuckets);
        return true;
    }

    public boolean contains(int i2) {
        if (!isEssentialAlbum(i2) || this.mSet.contains(Integer.valueOf(i2))) {
            return false;
        }
        return true;
    }

    public boolean remove(int i2) {
        if (!isEssentialAlbum(i2) || !this.mSet.remove(Integer.valueOf(i2))) {
            return false;
        }
        this.mBuckets = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mSet);
        GalleryPreference.getInstance().saveState("EssentialAlbumList", this.mBuckets);
        Log.d("EssentialAlbum", "remove", Integer.valueOf(i2), this.mBuckets);
        return true;
    }
}
