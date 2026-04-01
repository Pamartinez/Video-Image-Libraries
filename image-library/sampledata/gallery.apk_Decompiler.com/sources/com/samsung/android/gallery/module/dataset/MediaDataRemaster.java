package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.BufferedArray;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataRemaster extends MediaDataCollection {
    public MediaDataRemaster(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadAndCompare$0(MediaItem mediaItem) {
        return !isItemValid(mediaItem);
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || MediaItemSuggest.getRevitalizedType(mediaItem) != MediaItemSuggest.getRevitalizedType(mediaItem2) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(MediaItemSuggest.getOriginPath(mediaItem), MediaItemSuggest.getOriginPath(mediaItem2))) {
            return false;
        }
        return true;
    }

    public int findPosition(long j2) {
        for (int i2 = 0; i2 < this.mArrayData.size(); i2++) {
            MediaItem mediaItem = this.mArrayData.get(i2);
            if (mediaItem != null && mediaItem.getMediaId() == j2) {
                return i2;
            }
        }
        return -1;
    }

    public List<Long> getFileIds() {
        if (this.mLock.acquireReadLock("MDA.getFileIds")) {
            try {
                ArrayList arrayList = new ArrayList();
                Iterator<MediaItem> it = this.mArrayData.iterator();
                while (it.hasNext()) {
                    arrayList.add(Long.valueOf(it.next().getFileId()));
                }
                return arrayList;
            } finally {
                this.mLock.releaseReadLock("MDA.getFileIds");
            }
        } else {
            Log.e(this.TAG, "fail to get lock - file ids");
            return new ArrayList();
        }
    }

    public int getPresetCount() {
        return 3;
    }

    public ThumbKind getPresetThumbnailKind() {
        return ThumbKind.LARGE_KIND;
    }

    public int loadAndCompare(BufferedArray<MediaItem> bufferedArray, Cursor cursor) {
        int loadAndCompare = super.loadAndCompare(bufferedArray, cursor);
        if (loadAndCompare > 0) {
            int size = bufferedArray.size();
            bufferedArray.removeIf(new C0606k(1, this));
            if (size != bufferedArray.size()) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "loadAndCompare revoke {" + size + ',' + bufferedArray.size() + ',' + this.mArrayData.size() + '}');
                loadAndCompare = bufferedArray.size() - this.mArrayData.size();
                if (loadAndCompare == 0) {
                    return compare(bufferedArray, this.mArrayData);
                }
            }
        }
        return loadAndCompare;
    }
}
