package com.samsung.android.gallery.app.controller.sharing.request;

import Ba.h;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDownloadCover extends AbsRequest {
    public RequestDownloadCover(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private MediaItem getMdeItem() {
        return (MediaItem) this.mParams[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDownloadCover$0(Integer num, String str) {
        String str2 = this.TAG;
        Log.sh(str2, "requestDownloadCover result" + Logger.v(num, str));
    }

    private void requestDownloadCover() {
        Log.sh(this.TAG, "requestDownloadCover called");
        MediaItem mdeItem = getMdeItem();
        if (mdeItem != null && !mdeItem.isEmpty()) {
            MdeSharingHelper.requestThumbnailDownload(MediaItemMde.getGroupId(mdeItem), MediaItemMde.getSpaceId(mdeItem), MediaItemMde.getSpaceCoverId(mdeItem), (String) null, ShareApi.HD_1280_SIZE_IMAGE, new h(12, this));
        }
    }

    public int getNetworkErrorStringId() {
        return MdeServiceString.getDownloadNetworkError(getItemTypeWithMediaList());
    }

    public void onPostExecute(boolean z) {
        if (z) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    public void request() {
        requestDownloadCover();
    }
}
