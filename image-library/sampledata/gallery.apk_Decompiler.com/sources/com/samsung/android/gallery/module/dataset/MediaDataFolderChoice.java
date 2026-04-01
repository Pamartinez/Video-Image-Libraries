package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataFolderChoice extends MediaDataAlbumChoice {
    public MediaDataFolderChoice(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mFolderId = ArgumentsUtil.getArgValue(str, "id", -1);
    }

    public ArrayList<MediaItem> getFullData() {
        MediaData mediaData;
        if (!PreferenceFeatures.OneUi6x.SUPPORT_PICKER_PRESELECTED || (mediaData = this.mMediaData) == null) {
            return super.getFullData();
        }
        return mediaData.getFullData();
    }

    public void updateData() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        if (this.mFolderId == -1) {
            fetchDataForRoot(arrayList);
        } else {
            fetchDataForNested(arrayList);
        }
        if (this.mLock.acquireWriteLock()) {
            this.mDataList = arrayList;
            this.mDataCount = arrayList.size();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "data refreshed : count = " + this.mDataCount);
            try {
                notifyChanged();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "updateData#notifyChanged failed", e.getMessage());
            }
            this.mLock.releaseWriteLock();
        }
    }
}
