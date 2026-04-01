package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot;

import C7.a;
import N2.j;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.concurrent.atomic.AtomicBoolean;
import u7.C0525f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsGroupItemLoader extends ViewerObject implements FragmentLifeCycle, GroupLoader {
    protected final AtomicBoolean mLoading = new AtomicBoolean(false);

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$0() {
        MediaItem representativeItem = this.mModel.getRepresentativeItem();
        if (representativeItem != null) {
            loadGroupShotMediaItems(representativeItem);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$invalidateSubItems$1(MediaItem mediaItem, MediaItem mediaItem2, GroupLoader.SubItemLoadListener subItemLoadListener) {
        long j2;
        if (mediaItem.isSingleTakenPostProcessing() || this.mModel.getMediaItem() == null) {
            j2 = -1;
        } else {
            j2 = this.mModel.getMediaItem().getFileId();
        }
        GroupLoader.SubItemsInfo loadSubItems = loadSubItems(mediaItem2, j2);
        if (loadSubItems.isLoaded()) {
            C0525f fVar = (C0525f) subItemLoadListener;
            fVar.f2711a.lambda$invalidate$1(fVar.b, fVar.f2712c, fVar.d, 0, fVar.e, loadSubItems);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubItemsUpdated$2() {
        if (!this.mModel.getStateHelper().isQuickViewShrink()) {
            this.mActionInvoker.invoke(ViewerAction.GROUP_SUB_ITEMS_UPDATED, new Object[0]);
            this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
        }
    }

    private void onSubItemsUpdated() {
        this.mThread.runOnUiThread(new a(this, 1));
    }

    public MediaItem getBestItem() {
        return this.mModel.getRepresentativeItem();
    }

    public String getGroupLog() {
        return this.mModel.getSubItemCurrentIndex() + "/" + this.mModel.getSubMediaItemCount() + "/" + MediaItemUtil.getDebugLog(this.mModel.getMediaItem());
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1031) {
            return false;
        }
        this.mThread.runOnBgThread(new a(this, 0));
        return true;
    }

    public void invalidateSubItems(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7, GroupLoader.SubItemLoadListener subItemLoadListener) {
        this.mLoading.set(false);
        this.mThread.runOnBgThread(new A6.a((Object) this, (Object) mediaItem2, (Object) mediaItem, (Object) subItemLoadListener, 5));
    }

    public void loadGroupShotMediaItems(MediaItem mediaItem) {
        if (this.mLoading.get()) {
            Log.d(this.TAG, "already loading");
            return;
        }
        GroupLoader.SubItemsInfo loadSubItems = loadSubItems(mediaItem, -1);
        if (loadSubItems.mSubItemList.size() > 0) {
            updateModelSubItems(loadSubItems);
            return;
        }
        this.mModel.clearSubMediaItem();
        StringCompat stringCompat = this.TAG;
        Log.w(stringCompat, "loadGroupShotMediaItems failed : " + mediaItem.getGroupMediaId());
    }

    public abstract GroupLoader.SubItemsInfo loadSubItems(MediaItem mediaItem, long j2);

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        onBindInternal();
    }

    public abstract void onBindInternal();

    public void onViewRecycled() {
        super.onViewRecycled();
        if (this.mModel.getContainerModel().getStateHelper().isGroupItemLoading()) {
            Log.w(this.TAG, "onViewRecycled during loading");
            this.mModel.setGroupItemLoading(false);
        }
        this.mLoading.set(false);
    }

    public void setCurrentIndex(GroupLoader.SubItemsInfo subItemsInfo) {
        if (subItemsInfo.mCurrentIndex < 0) {
            subItemsInfo.mCurrentIndex = subItemsInfo.mBestItemIndex;
        }
        if (subItemsInfo.mCurrentIndex < 0) {
            subItemsInfo.mCurrentIndex = 0;
        }
    }

    public void updateModelSubItems(GroupLoader.SubItemsInfo subItemsInfo) {
        String groupLog = getGroupLog();
        this.mModel.setGroupMediaItems(subItemsInfo.mSubItemList, subItemsInfo.mBestItemIndex, subItemsInfo.mCurrentIndex);
        StringCompat stringCompat = this.TAG;
        StringBuilder k = j.k("loadGroup : ", groupLog, " >> ");
        k.append(getGroupLog());
        Log.d(stringCompat, k.toString());
        onSubItemsUpdated();
    }
}
