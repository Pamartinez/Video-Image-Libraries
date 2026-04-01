package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot;

import A4.C0372g;
import A4.C0384t;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.DynamicViewUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightGroupItemLoader extends AbsGroupItemLoader {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindInternal$0() {
        MediaItem representativeItem = this.mModel.getRepresentativeItem();
        if (representativeItem != null) {
            loadGroupShotMediaItems(representativeItem);
        } else {
            Log.e(this.TAG, "cannot loadGroupShotMediaItems. mediaitem is null");
        }
    }

    public GroupLoader.SubItemsInfo loadSubItems(MediaItem mediaItem, long j2) {
        GroupLoader.SubItemsInfo subItemsInfo = new GroupLoader.SubItemsInfo();
        if (mediaItem != null) {
            DynamicViewInfo dynamicViewInfo = DynamicViewData.of(mediaItem).dynamicViewInfo;
            if (dynamicViewInfo == null) {
                dynamicViewInfo = DynamicViewUtils.getDynamicViewInfo(mediaItem);
            }
            if (dynamicViewInfo != null) {
                subItemsInfo.mBestItemIndex = -1;
                this.mLoading.set(true);
                this.mModel.setGroupItemLoading(true);
                List list = (List) dynamicViewInfo.getClipInfoData().stream().sorted(Comparator.comparing(new C0384t(26)).reversed()).collect(Collectors.toList());
                for (int i2 = 0; i2 < list.size(); i2++) {
                    ClipInfo clipInfo = (ClipInfo) list.get(i2);
                    MediaItem mediaItem2 = new MediaItem();
                    mediaItem2.cloneBasicInfo(mediaItem);
                    mediaItem2.setTitle("Clip" + i2);
                    mediaItem2.setVideoHighlightTime(Long.valueOf(((long) clipInfo.clipStartMs) * 1000), Long.valueOf(((long) clipInfo.clipEndMs) * 1000));
                    subItemsInfo.mSubItemList.add(mediaItem2);
                }
                subItemsInfo.mCurrentMediaItem = subItemsInfo.mSubItemList.get(0);
                subItemsInfo.mBestItemIndex = 0;
                if (this.mLoading.get()) {
                    setCurrentIndex(subItemsInfo);
                    this.mLoading.set(false);
                    this.mModel.setGroupItemLoading(false);
                    return subItemsInfo;
                }
                Log.d(this.TAG, "loading canceled");
                return new GroupLoader.SubItemsInfo();
            }
        }
        return subItemsInfo;
    }

    public void onBindInternal() {
        this.mThread.runOnBgThread(new C0372g(19, this));
    }
}
