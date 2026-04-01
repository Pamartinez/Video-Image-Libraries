package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item;

import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CollagePageItem extends PageItem {
    private CollageInfo mCollageInfo;

    public boolean equalItems(PageItem pageItem) {
        CollageInfo collageInfo = this.mCollageInfo;
        if (collageInfo == null || pageItem == null || !collageInfo.equals(((CollagePageItem) pageItem).mCollageInfo)) {
            return false;
        }
        return true;
    }

    public CollageInfo getCollageInfo() {
        return this.mCollageInfo;
    }

    public abstract int getType();

    public boolean isCollage() {
        return true;
    }

    public void setCollageInfo(CollageInfo collageInfo) {
        this.mCollageInfo = collageInfo;
    }

    public String toString() {
        return "CollagePageItem@" + Integer.toHexString(hashCode()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCollageInfo;
    }
}
