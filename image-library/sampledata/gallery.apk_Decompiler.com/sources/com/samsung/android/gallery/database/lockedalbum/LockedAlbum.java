package com.samsung.android.gallery.database.lockedalbum;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LockedAlbum {
    private static final LockedAlbum sInstance = new LockedAlbum();
    private String mBuckets = "";
    private final HashSet<Integer> mSet = new HashSet<>();

    public LockedAlbum() {
        String loadString = GalleryPreference.getInstance().loadString("LockedAlbumList", (String) null);
        if (!TextUtils.isEmpty(loadString)) {
            for (String parseInt : loadString.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                this.mSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
            }
            this.mBuckets = loadString;
            Log.d("LockedAlbum", "construct", loadString);
        }
    }

    public static LockedAlbum getInstance() {
        return sInstance;
    }

    public boolean add(List<Integer> list) {
        if (list == null || list.isEmpty() || !this.mSet.addAll(list)) {
            return false;
        }
        this.mBuckets = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mSet);
        GalleryPreference.getInstance().saveState("LockedAlbumList", this.mBuckets);
        Log.d("LockedAlbum", "add", this.mBuckets);
        return true;
    }

    public void changeToRenamedBucketId(int i2, int i7) {
        if (contains(i2)) {
            remove(i2);
            add(i7);
        }
    }

    public boolean contains(int i2) {
        return this.mSet.contains(Integer.valueOf(i2));
    }

    public Collection<Integer> getBucketList() {
        return this.mSet;
    }

    public boolean remove(List<Integer> list) {
        int size = this.mSet.size();
        if (list != null && !list.isEmpty()) {
            for (Integer next : list) {
                next.intValue();
                this.mSet.remove(next);
            }
        }
        if (this.mSet.size() == size) {
            return false;
        }
        this.mBuckets = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mSet);
        GalleryPreference.getInstance().saveState("LockedAlbumList", this.mBuckets);
        Log.d("LockedAlbum", "remove", this.mBuckets);
        return true;
    }

    public boolean add(int i2) {
        if (!this.mSet.add(Integer.valueOf(i2))) {
            return false;
        }
        this.mBuckets = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mSet);
        GalleryPreference.getInstance().saveState("LockedAlbumList", this.mBuckets);
        Log.d("LockedAlbum", "add : " + i2, this.mBuckets);
        return true;
    }

    public boolean remove(int i2) {
        if (!this.mSet.remove(Integer.valueOf(i2))) {
            return false;
        }
        this.mBuckets = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mSet);
        GalleryPreference.getInstance().saveState("LockedAlbumList", this.mBuckets);
        Log.d("LockedAlbum", "remove : " + i2, this.mBuckets);
        return true;
    }
}
