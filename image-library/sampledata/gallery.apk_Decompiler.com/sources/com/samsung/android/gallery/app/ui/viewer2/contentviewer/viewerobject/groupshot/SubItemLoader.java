package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot;

import A4.H;
import A9.b;
import B7.d;
import B8.e;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SubItemLoader extends GroupShotItemLoader {
    public SubItemLoader(String str, GroupType groupType) {
        super(str, groupType);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        onItemFocusChanged(objArr[0].intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGroupItemImage$1(Bitmap bitmap, MediaItem mediaItem) {
        this.mActionInvoker.invoke(ViewerAction.PREVIEW_LOADED, bitmap, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGroupItemImage$2(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (MediaItemUtil.equalsSimple(mediaItem, this.mModel.getMediaItem())) {
            Log.d(this.TAG, "setGroupItemImage");
            if (bitmap != null && this.mModel.setPreViewBmp(bitmap, mediaItem)) {
                this.mThread.runOnUiThread(new b(this, bitmap, mediaItem, 6));
                return;
            }
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.w(stringCompat, "setGroupItemImage failed : " + mediaItem.getGroupMediaId());
    }

    private void onItemFocusChanged(int i2) {
        Log.d(this.TAG, "onItemFocusChanged", Integer.valueOf(i2));
        if (i2 >= 0 && i2 < this.mModel.getSubMediaItemCount()) {
            this.mModel.setSubItemCurrentIndex(i2);
            setGroupItemImage(this.mModel.getMediaItem());
            this.mActionInvoker.invoke(ViewerAction.GROUP_CURRENT_ITEM_CHANGED, Integer.valueOf(i2));
        }
    }

    private void setGroupItemImage(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbnailLoader.getInstance().getOrLoad(mediaItem, ThumbKind.MEDIUM_KIND, new e(mediaItem, 1), new H(4, (Object) this, (Object) mediaItem));
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.GROUP_CURRENT_ITEM_CHANGE_REQUEST, new d(1, this));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 20001) {
            return false;
        }
        onItemFocusChanged(eventMessage.arg1);
        return true;
    }

    public void updateModelSubItems(GroupLoader.SubItemsInfo subItemsInfo) {
        boolean z;
        if (this.mModel.getSubMediaItemCount() <= 0 || this.mModel.getSubMediaItemCount() == subItemsInfo.mSubItemList.size()) {
            z = false;
        } else {
            z = true;
        }
        super.updateModelSubItems(subItemsInfo);
        if (z) {
            Log.d(this.TAG, "subItem count changed");
            onItemFocusChanged(subItemsInfo.mCurrentIndex);
        }
    }
}
