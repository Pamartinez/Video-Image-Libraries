package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot;

import A4.C0372g;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuperSlowGroupItemLoader extends AbsGroupItemLoader {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindInternal$0() {
        MediaItem representativeItem = this.mModel.getRepresentativeItem();
        if (representativeItem != null) {
            loadGroupShotMediaItems(representativeItem);
        } else {
            Log.e(this.TAG, "cannot loadGroupShotMediaItems. mediaitem is null");
        }
    }

    private static MediaItem makeSubItem(MediaItem mediaItem, int i2, long j2, int i7, int i8) {
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.cloneBasicInfo(mediaItem);
        mediaItem2.setSefFileType(0, 0);
        mediaItem2.setOrientation(i2);
        mediaItem2.setTitle(AppResources.getString(i8));
        VideoPropData.of(mediaItem2).superSlowClipEffect = Integer.valueOf(i7);
        mediaItem2.setVideoThumbStartTime(j2);
        mediaItem2.setRecordingMode(mediaItem.getRecordingMode());
        return mediaItem2;
    }

    public GroupLoader.SubItemsInfo loadSubItems(MediaItem mediaItem, long j2) {
        int i2;
        GroupLoader.SubItemsInfo subItemsInfo = new GroupLoader.SubItemsInfo();
        if (mediaItem == null) {
            return subItemsInfo;
        }
        subItemsInfo.mBestItemIndex = -1;
        this.mLoading.set(true);
        this.mModel.setGroupItemLoading(true);
        ArrayList arrayList = new ArrayList();
        if (MediaHelper.isPortraitVideo(mediaItem.getPath())) {
            i2 = 90;
        } else {
            i2 = 0;
        }
        long superSlowStartTime = (long) MediaHelper.getSuperSlowStartTime(mediaItem.getPath());
        long j3 = superSlowStartTime * 1000;
        MediaItem mediaItem2 = mediaItem;
        arrayList.add(makeSubItem(mediaItem2, i2, j3, 1, R.string.superslow_effect_forward));
        arrayList.add(makeSubItem(mediaItem2, i2, (superSlowStartTime + 6000) * 1000, 2, R.string.superslow_effect_reverse));
        arrayList.add(makeSubItem(mediaItem2, i2, j3, 3, R.string.superslow_effect_forward_and_reverse));
        subItemsInfo.mCurrentMediaItem = (MediaItem) arrayList.get(0);
        subItemsInfo.mBestItemIndex = 0;
        subItemsInfo.mSubItemList.addAll(arrayList);
        if (this.mLoading.get()) {
            setCurrentIndex(subItemsInfo);
            this.mLoading.set(false);
            this.mModel.setGroupItemLoading(false);
            return subItemsInfo;
        }
        Log.d(this.TAG, "loading canceled");
        return new GroupLoader.SubItemsInfo();
    }

    public void onBindInternal() {
        this.mThread.runOnBgThread(new C0372g(20, this));
    }
}
